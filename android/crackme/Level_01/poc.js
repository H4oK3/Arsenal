// package_name: sg.vantagepoint.uncrackable1
Java.perform(function(){
  Java.enumerateLoadedClasses(
  {
  "onMatch": function(className){ 
        if (className.match(/vantagepoint/i)){
          console.log(className)
        }
         
    },
  "onComplete":function(){}
  }
  )

})

setImmediate(function() { //prevent timeout
    console.log("[*] Starting script");

    Java.perform(function() {

      bClass = Java.use("sg.vantagepoint.uncrackable1.b");
      bClass.onClick.implementation = function(v) {
         console.log("[*] onClick called");
      }
      console.log("[*] onClick handler modified");
      console.log("[*] Won't exit anymore");

      aaClass = Java.use("sg.vantagepoint.a.a");
      aaClass.a.implementation = function(arg1,arg2){
      	retval = this.a(arg1, arg2);
      	password = '';
      	for(i = 0; i < retval.length; i++){
      		password += String.fromCharCode(retval[i]);
      	}
      	console.log("[+] Decrypted as:" + password);
      	return retval
      }
      console.log("[*] sg.vantagepoint.a.a.a modified");
    })
})
