// - (void)textViewDidChange:(id)arg1;
// -[ICNoteEditorViewController textViewDidChange:]:
var class_ICNoteEditorViewController = ObjC.classes.ICNoteEditorViewController;
var textViewDidChange = class_ICNoteEditorViewController["- textViewDidChange:"];
var arg0 = null
var arg0_1 = null
var arg1 = null

//- (void)setTitleStyle:(id)arg1;


Interceptor.attach(textViewDidChange.implementation, {
  onEnter: function(args) {
    // args[0] is self
    // args[1] is selector (SEL "textViewDidChangeWithText:")
    // args[2] holds the first function argument, an NSString
    arg0 = ObjC.Object(args[0]);
    arg1 = ObjC.Object(args[2]);
    console.log("\n[ICNoteEditorViewController textViewDidChange arg1: " + arg1.toString() + "\"]");

    var text = arg1.text()
    console.log("Text: " + text)

    var length = text.length()
    console.log(length)
  }
});

