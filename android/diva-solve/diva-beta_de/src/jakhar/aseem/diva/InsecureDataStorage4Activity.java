// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.util.Log;
import android.content.Context;
import android.widget.Toast;
import java.io.FileWriter;
import java.io.File;
import android.os.Environment;
import android.widget.EditText;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class InsecureDataStorage4Activity extends AppCompatActivity
{
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968614);
    }
    
    public void saveCredentials(final View view) {
        final EditText editText = (EditText)this.findViewById(2131493010);
        final EditText editText2 = (EditText)this.findViewById(2131493011);
        final File externalStorageDirectory = Environment.getExternalStorageDirectory();
        try {
            final File file = new File(externalStorageDirectory.getAbsolutePath() + "/.uinfo.txt");
            file.setReadable(true);
            file.setWritable(true);
            final FileWriter fileWriter = new FileWriter(file);
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
