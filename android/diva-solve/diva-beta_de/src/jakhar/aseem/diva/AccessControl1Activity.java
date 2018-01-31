// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.util.Log;
import android.content.Context;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AccessControl1Activity extends AppCompatActivity
{
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968601);
    }
    
    public void viewAPICredentials(final View view) {
        final Intent intent = new Intent();
        intent.setAction("jakhar.aseem.diva.action.VIEW_CREDS");
        if (intent.resolveActivity(this.getPackageManager()) != null) {
            this.startActivity(intent);
            return;
        }
        Toast.makeText((Context)this, (CharSequence)"Error while getting API details", 0).show();
        Log.e("Diva-aci1", "Couldn't resolve the Intent VIEW_CREDS to our activity");
    }
}
