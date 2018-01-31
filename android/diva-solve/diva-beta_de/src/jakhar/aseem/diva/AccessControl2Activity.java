// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.util.Log;
import android.content.Context;
import android.widget.Toast;
import android.content.Intent;
import android.widget.RadioButton;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AccessControl2Activity extends AppCompatActivity
{
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968602);
    }
    
    public void viewAPICredentials(final View view) {
        final RadioButton radioButton = (RadioButton)this.findViewById(2131492973);
        final Intent intent = new Intent();
        final boolean checked = radioButton.isChecked();
        intent.setAction("jakhar.aseem.diva.action.VIEW_CREDS2");
        intent.putExtra(this.getString(2131099686), checked);
        if (intent.resolveActivity(this.getPackageManager()) != null) {
            this.startActivity(intent);
            return;
        }
        Toast.makeText((Context)this, (CharSequence)"Error while getting Tveeter API details", 0).show();
        Log.e("Diva-aci1", "Couldn't resolve the Intent VIEW_CREDS2 to our activity");
    }
}
