// add intent-filter into manifest.xml
<intent-filter>
	<action android:name="android.intent.action.VIEW" />
	<category android:name="android.intent.category.DEFAULT"/>
	<data android:mimeType="text/plain" />
</intent-filter>


// Handle the intent as normal
@Override
protected void onResume() {
    super.onResume();
    Intent intent = getIntent();
    if (intent != null) {
        String msg = intent.getStringExtra("payload");
        if (msg != null) {
            logMessage(msg);
        }
    }
}


// sender side:

public void sendText(View view) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.putExtra("payload", "Hello From this freaking restaurant");
    intent.setType("text/plain");

    if (intent.resolveActivity(getPackageManager()) != null){
        startActivity(intent);
    }else{
        Toast.makeText(this, "No available intent handler was found", Toast.LENGTH_SHORT).show();
    }

}
