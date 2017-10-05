- Under this folder, I noted everything I learnt form chapter 7, hook note app and add a character count method

```
# dump headers on Mac
class-dump -S -s -H MobileNotes -o ./headers

# cycript + reveal get the view
cy# [[UIApp keyWindow ]recursiveDescription ].toString()
cy# ICView = #0x1570a2200   # just search the text appears in Notes in the terminal
cy# [ICView setHidden:YES]  # Verify this is the one we are looking for

# Get the Controller from View
cy# [ICView nextResponder]
#"<NotesBackgroundView: 0x156ed0510; frame = (0 0; 414 736); autoresize = W+H; gestureRecognizers = <NSArray: 0x158790d00>; layer = <CALayer: 0x156ed06a0>>"
cy# [[ICView nextResponder]nextResponder]
#"<ICNoteEditorViewController: 0x157013800>"

cy# [#0x157013800 setTitle:@"asdf"] # Verify this is the controller that we were looking for
cy# ICNoteEditorViewController = #0x157013800

# Look at the ICNoteEditorViewController.h, there is a property names: ICNote 
@property(retain, nonatomic) ICNote *note; // @synthesize note=_note;
- (id)noteActivityItemsForSharing;

cy# [ICNoteEditorViewController note].toString()
`<ICNote: 0x156f39cd0> (entity: ICNote; id: 0xd00000000004000a <x-coredata://8E5AB974-5E76-49EE-8AB5-41438807844E/ICNote/p1> ; data: {
    -REDATED--
    title = "Hao 1337";
    type = nil;
})`
# So this note contains the exact text info, it is the Model here
```

To Wrap up: We have found our Model(ICNote), View(ICTextView), Controller(ICNoteEditorViewController) for the target here.
For some readon the ICNote.h is not there after we do class-dump, I did this to get that specifically:

```
cy# dlopen("/usr/lib/libclassdumpdyld.dylib",RTLD_NOW)
(typedef void*)(0x156da3a00)
cy# extern "C" NSString * dumpClass(Class *aClass);
(extern "C" NSString *dumpClass(Class *))
cy# dumpClass(ICNote)
@"Wrote file /var/mobile/Containers/Data/Application/86B711C5-A26A-46E9-9120-F74F5286DCD5/Documents/ICNote.h"

scp -P 2222 root@localhost:/var/mobile/Containers/Data/Application/86B711C5-A26A-46E9-9120-F74F5286DCD5/Documents/ICNote.h ./headers
```

- Next step is to get the correct function to set/get the text(I just browsing the header file and drop in cycript to see what it does.)

```
# this method in the header file -(id)noteAsPlainText;
cy# [[ICNoteEditorViewController note] noteAsPlainText]
@"Hao 1337"

cy# length = [[[ICNoteEditorViewController note] noteAsPlainText]length]
8

cy# [ICNoteEditorViewController setTitle:length.toString()]
```

- Next step is find a way to do it dynamically, like real-time length count. What I need is a real-time monitoring function, and those function are often defined in protocal. After looking around, I found this:

```
@interface ICNoteEditorViewController : ICScrollViewDelegateViewController <ICStyleSelectorDelegate, UIImagePickerControllerDelegate, UINavigationControllerDelegate, NSUserActivityDelegate, UIPopoverPresentationControllerDelegate, UIGestureRecognizerDelegate, UIViewControllerPreviewingDelegate_Deprecated, UITextViewDelegate, ICAudioBarViewControllerDelegate>

# Class within that <> was protocals that we should look at
@protocol UITextViewDelegate <NSObject, UIScrollViewDelegate>
-[ICNoteEditorViewController textViewDidChange:]:

# Verified via lldb, set a bp there, edit text, see if bp hit
# I was quite lucky here, I found the text easily
(lldb) po $x2
<ICTextView: 0x157298000; baseClass = UITextView; frame = (0 0; 414 736); text = 'Hao 1337fh'; clipsToBounds = YES; gestureRecognizers = <NSArray: 0x15b5dc670>; layer = <CALayer: 0x15b824d00>; contentOffset: {0, -64}; contentSize: {414, 37}>


(lldb) po [$x2 text]
Hao 1337f
```

So it seems like we can just hook into this function: 
`-[ICNoteEditorViewController textViewDidChange:]:  //- (void)textViewDidChange:(id)arg1;`

- Test run with frida:
See hook_notes.js for details.

```
-> % frida -U -n Notes -l hook_notes.js
     ____
    / _  |   Frida 10.3.14 - A world-class dynamic instrumentation framework
   | (_| |
    > _  |   Commands:
   /_/ |_|       help      -> Displays the help system
   . . . .       object?   -> Display information about 'object'
   . . . .       exit/quit -> Exit
   . . . .
   . . . .   More info at http://www.frida.re/docs/home/

[iPhone::Notes]->
[ICNoteEditorViewController textViewDidChange arg1: <ICTextView: 0x13e09fe00; baseClass = UITextView; frame = (0 0; 414 736); text = 'Hao1337 h'; clipsToBounds = YES; gestureRecognizers = <NSArray: 0x142b3a070>; layer = <CALayer: 0x142b2f6f0>; contentOffset: {0, -64}; contentSize: {414, 37}>"]
Text: Hao1337 h
9

[ICNoteEditorViewController textViewDidChange arg1: <ICTextView: 0x13e09fe00; baseClass = UITextView; frame = (0 0; 414 736); text = 'Hao1337 hj'; clipsToBounds = YES; gestureRecognizers = <NSArray: 0x142b3a070>; layer = <CALayer: 0x142b2f6f0>; contentOffset: {0, -64}; contentSize: {414, 37}>"]
Text: Hao1337 hj
10

# Successfully get the text and length dynamically
```

**Note: To my understanding, frida is not quite fit to call objective-C method directly, so here I didnt manage to do sth like `arg0["- setTitle:"](NSString.stringWithString_('Hello World'));` to change the title**
*TODO: Maybe figure out how to dynamic add the length to the app using tweak or frida.*


