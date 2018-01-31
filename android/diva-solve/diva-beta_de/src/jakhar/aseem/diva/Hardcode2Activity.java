// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.os.Bundle;
import android.content.Context;
import android.widget.Toast;
import android.widget.EditText;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

public class Hardcode2Activity extends AppCompatActivity
{
    private DivaJni djni;
    
    public void access(final View view) {
        if (this.djni.access(((EditText)this.findViewById(2131492990)).getText().toString()) != 0) {
            Toast.makeText((Context)this, (CharSequence)"Access granted! See you on the other side :)", 0).show();
            return;
        }
        Toast.makeText((Context)this, (CharSequence)"Access denied! See you in hell :D", 0).show();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968608);
        this.djni = new DivaJni();
    }
}
