// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences$Editor;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.content.Context;
import android.preference.PreferenceManager;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

public class AccessControl3Activity extends AppCompatActivity
{
    public void addPin(final View view) {
        final SharedPreferences$Editor edit = PreferenceManager.getDefaultSharedPreferences((Context)this).edit();
        final String string = ((EditText)this.findViewById(2131492976)).getText().toString();
        if (string == null || string.isEmpty()) {
            Toast.makeText((Context)this, (CharSequence)"Please Enter a valid pin!", 0).show();
            return;
        }
        final Button button = (Button)this.findViewById(2131492978);
        edit.putString(this.getString(2131099722), string);
        edit.commit();
        if (button.getVisibility() != 0) {
            button.setVisibility(0);
        }
        Toast.makeText((Context)this, (CharSequence)"PIN Created successfully. Private notes are now protected with PIN", 0).show();
    }
    
    public void goToNotes(final View view) {
        this.startActivity(new Intent((Context)this, (Class)AccessControl3NotesActivity.class));
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968603);
        if (!PreferenceManager.getDefaultSharedPreferences((Context)this).getString(this.getString(2131099722), "").isEmpty()) {
            ((Button)this.findViewById(2131492978)).setVisibility(0);
        }
    }
}
