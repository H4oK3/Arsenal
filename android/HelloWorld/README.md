A Simple Android app that implements **INTENT**, **SERVICE**, **ACTIVITY**, **BINDER** that can help me get a better understanding of how Android app works.

Also, [this blog](http://blog.csdn.net/guolin_blog/article/details/11952435/) is helpful.

- In the HelloWorld app, it implemented these basic features:
	+ Start another activity by using Intent(explicit) with message
	+ Displayed service start/stop life-cycle:
	`onCreate -> onStartCommand -> onDestroy`
	
	~~~
	-> % adb logcat | ag Service_Tag
	D/Service_Tag( 8519): Created Srvis..
	D/Service_Tag( 8519): onStartCommand() executed
	D/Service_Tag( 8519): onDestroy() executed
	~~~
	
	+ Bind/unbind activity with service
	
	A bound service is an implementation of the Service class that allows other applications to bind to it and interact with it. To provide binding for a service, you must implement the onBind() callback method. This method returns an IBinder object that defines the programming interface that clients can use to interact with the service.
	`bindService(bindIntent, connection, BIND_AUTO_CREATE);`
	
	~~~
	D/Service_Tag( 8519): Created Srvis..
	D/Service_Tag( 8519): onBind() executed
	D/Service_Tag( 8519): startDownload() executed, trying to download: evil.bin
	D/Service_Tag( 8519): onDestroy() executed
	~~~
	Lifecycle:
	`onCreate -> onBind -> MyBinder.startDownload() -> onDestroy`
	
	+ Save/Retrieve data to SharedPreference file:
	`/data/data/com.ha0k3.helloworld/shared_prefs/mypref.xml`
	
	+ Save/retrieve text to internal file

	~~~JAVA
	public void write_to_internal_file(View view) {

        EditText et = (EditText) findViewById(R.id.editText);
        String msg = et.getText().toString();
        FileOutputStream fileOutputStream = null;

        File file = new File(Internal_File);

        try {
            fileOutputStream = openFileOutput(Internal_File, MODE_PRIVATE);
            fileOutputStream.write(msg.getBytes());
            Toast.makeText(this, "Saving the text to file...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
	~~~
	
	+ Save/retrieve text to external file
	`/data/media/0/ext_file.txt`
	`/storage/emulated/0/ext_file.txt`
	
	The pathname differs with different devices.
	
	~~~JAVA
	private File getFile(){
        return new File(Environment.getExternalStorageDirectory(),EXTERNAL_FILE_NAME);
    }
	~~~
	
	+ Database operations:
	database file location: `/data/data/com.ha0k3.helloworld/databases/database.db`
	
	`public class DBHelper extends SQLiteOpenHelper`: Database operations class; all db operations has to be done by using DBHelper object;
	
	~~~JAVA
	// Then this can be used to get the descriptor of the database
	SQLiteOpenHelper dbHelper = new DBHelper(this);
	database = dbHelper.getWritableDatabase();  //will call onCreate if db not exist
	~~~
	
	A good programming behavior is to store all necessary SQL query in a constant java file and access it via:
	`database.execSQL(SQLConstant.SQL_DELETE);`
	
	
	+ Simple JNI implementation:
	check the `jni` folder for details; 
	`ndk-build` in the jni dir will build the .so files; copy them into a folder under app/src/main will do.
	
	