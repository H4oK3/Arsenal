// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.util.Log;
import android.content.Context;
import android.widget.Toast;
import java.io.FileWriter;
import java.io.File;
import android.widget.EditText;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class InsecureDataStorage3Activity extends AppCompatActivity
{
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968613);
    }
    
    public void saveCredentials(final View view) {
        final EditText editText = (EditText)this.findViewById(2131493006);
        final EditText editText2 = (EditText)this.findViewById(2131493007);
        final File file = new File(this.getApplicationInfo().dataDir);
        try {
            final File tempFile = File.createTempFile("uinfo", "tmp", file);
            tempFile.setReadable(true);
            tempFile.setWritable(true);
            final FileWriter fileWriter = new FileWriter(tempFile);
            fileWriter.write(editText.getText().toString() + ":" + editText2.getText().toString() + "\n");
            fileWriter.close();
            Toast.makeText((Context)this, (CharSequence)"3rd party credentials saved successfully!", 0).show();
        }
        catch (Exception ex) {
            Toast.makeText((Context)this, (CharSequence)"File error occurred", 0).show();
            Log.d("Diva", "File error: " + ex.getMessage());
        }
    }
}
