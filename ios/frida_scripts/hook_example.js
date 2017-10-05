// var sendMessage = ObjC.classes.MessageViewController["- sendMessageWithText:"];

// Interceptor.attach(sendMessage.implementation, {
//   onEnter: function(args) {
//     // args[0] is self
//     // args[1] is selector (SEL "sendMessageWithText:")
//     // args[2] holds the first function argument, an NSString

//     example hook & modify as follows:
//     var message = ObjC.Object(args[2]);
//     console.log("\n[MessageViewController sendMessageWithText:@\""
//         + message.toString() + "\"]");

//     var message = ObjC.Object(args[2]);
//     var modifiedMessage = message["- stringByAppendingString:"](" :)");
//     args[2] = modifiedMessage;
//   }
// });

// - (void)flashColor:(id)arg1 withCompletion:(CDUnknownBlockType)arg2;
// In Hopper: -[SBScreenFlash flashColor:withCompletion:]: 

var sendMessage = ObjC.classes.SBScreenFlash["- flashColor:withCompletion:"];

Interceptor.attach(sendMessage.implementation, {
  onEnter: function(args) {
    // args[0] is self
    // args[1] is selector (SEL "sendMessageWithText:")
    // args[2] holds the first function argument, an NSString

    var arg1 = ObjC.Object(args[2]);
    var arg2 = ObjC.Object(args[3]);
    console.log("\n[SBScreenFlash flashColor arg1: " + arg1.toString() + "\nclassname: " + classname_1.toString()+ "\"]");
  }
});

// Frida stuff above can also be done using teak:
// %hook SBScreenFlash
// - (void)flashColor:(id)arg1 withCompletion:(id)arg2 {
// %orig;
// NSLog(@"iOSRE: flashColor: %s, %@", object_getClassName(arg1), arg1); // [arg1 description]       arg1
// }
// %end
