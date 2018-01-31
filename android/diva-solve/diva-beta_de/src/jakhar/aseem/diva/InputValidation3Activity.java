// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.content.Context;
import android.widget.Toast;
import android.widget.EditText;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class InputValidation3Activity extends AppCompatActivity
{
    private DivaJni djni;
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968610);
        this.djni = new DivaJni();
    }
    
    public void push(final View view) {
        if (this.djni.initiateLaunchSequence(((EditText)this.findViewById(2131492997)).getText().toString()) != 0) {
            Toast.makeText((Context)this, (CharSequence)"Launching in T - 10 ...", 0).show();
            return;
        }
        Toast.makeText((Context)this, (CharSequence)"Access denied!", 0).show();
    }
}
