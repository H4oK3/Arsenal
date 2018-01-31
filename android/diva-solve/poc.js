// frida -U -f jakhar.aseem.diva --no-pause -l poc.js
// olsdfgad;lh

setImmediate(function() {
  Java.perform(function() {
      console.log("[*] Here we go");
      var strncmp = undefined;
      imports = Module.enumerateImportsSync("libdivajni.so");

      for(i = 0; i < imports.length; i++) {
      if(imports[i].name == "strncmp") {
              strncmp = imports[i].address;
              break;
          }
      }

      Interceptor.attach(Module.findExportByName("libdivajni.so", "Java_jakhar_aseem_diva_DivaJni_access"), {

      onEnter: function(args) {
        console.log("Access-jni called");
      },
      // onLeave: function(retval) {
      //   if (this.flag) {
      //     // print retval
      //     console.log("\nretval: " + retval);
      //     console.warn("\n*** exiting open");
      //   }
      // }
      });

      Interceptor.attach(strncmp, {
          onEnter: function (args) {
            var arg0 = Memory.readCString(args[0]).toString();
            var arg1 = Memory.readCString(args[1]).toString();
            if(!(arg0.startsWith('debug')) & (arg1 == 'aaaaaaaaaaa')){
            console.log(arg0 + ": " + arg1)
            // [*] Intercepting strncmp
            // olsdfgad;lh: aaaaaaaaaaa
          }
        },
      });
      console.log("[*] Intercepting strncmp");
  });
});