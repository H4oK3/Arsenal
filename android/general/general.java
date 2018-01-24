// make a toast
Toast.makeText(getApplicationContext(), "Your toast message.",Toast.LENGTH_SHORT).show();

// create an onclick listener
Button runButton = (Button) findViewById(R.id.run_button);
runButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        runCode(view);
    }
});


//Start activity using explicit Intent
Intent intent = new Intent(this, StartersActivity.class);
intent.putExtra(NAME_KEY,"Hao");    //with intent msg
startActivity(intent);

//In the activity being started, the Intent can be accessed by:
Intent intent = getIntent();
String name = intent.getStringExtra(MainActivity.NAME_KEY);

//Parent activity
<activity android:name=".StartersActivity">
	<meta-data
		android:name="android.support.PARENT_ACTIVITY"
		android:value=".MainActivity"
		/>
</activity>




