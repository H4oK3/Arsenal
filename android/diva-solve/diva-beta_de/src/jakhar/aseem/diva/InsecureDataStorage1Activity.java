// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.content.SharedPreferences$Editor;
import android.widget.Toast;
import android.widget.EditText;
import android.content.Context;
import android.preference.PreferenceManager;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class InsecureDataStorage1Activity extends AppCompatActivity
{
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968611);
    }
    
    public void saveCredentials(final View view) {
        final SharedPreferences$Editor edit = PreferenceManager.getDefaultSharedPreferences((Context)this).edit();
        final EditText editText = (EditText)this.findViewById(2131493000);
        final EditText editText2 = (EditText)this.findViewById(2131493001);
        edit.putString("user", editText.getText().toString());
        edit.putString("password", editText2.getText().toString());
        edit.commit();
        Toast.makeText((Context)this, (CharSequence)"3rd party credentials saved successfully!", 0).show();
    }
}
