// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.os.Bundle;
import android.content.Context;
import android.widget.Toast;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

public class LogActivity extends AppCompatActivity
{
    private void processCC(final String s) {
        throw new RuntimeException();
    }
    
    public void checkout(View view) {
        view = this.findViewById(2131493014);
        try {
            this.processCC(((EditText)view).getText().toString());
        }
        catch (RuntimeException ex) {
            Log.e("diva-log", "Error while processing transaction with credit card: " + ((EditText)view).getText().toString());
            Toast.makeText((Context)this, (CharSequence)"An error occured. Please try again later", 0).show();
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968615);
    }
}
