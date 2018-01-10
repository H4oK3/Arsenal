// package_name: sg.vantagepoint.uncrackable2
// thanks for all the fish

setImmediate(function() {
  Java.perform(function() {
      console.log("[*] Here we go");

      system_class = Java.use("java.lang.System");
      system_class.exit.implementation = function(){
        console.log("[*] Exit function hooked here, we are all good")
      }

      var strncmp = undefined;
      imports = Module.enumerateImportsSync("libfoo.so");

      for(i = 0; i < imports.length; i++) {
      if(imports[i].name == "strncmp") {
              strncmp = imports[i].address;
              break;
          }
      }

      Interceptor.attach(strncmp, {
          onEnter: function (args) {
             if(args[2].toInt32() == 23 && Memory.readUtf8String(args[0],23) == "01234567890123456789012") {
                  console.log("[*] Secret string at " + args[1] + ": " + Memory.readUtf8String(args[1],23));
              }
           },
      });
      console.log("[*] Intercepting strncmp");
  });
});

