// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.content.Context;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

public class AccessControl3NotesActivity extends AppCompatActivity
{
    public void accessNotes(final View view) {
        final EditText editText = (EditText)this.findViewById(2131492979);
        final Button button = (Button)this.findViewById(2131492980);
        if (editText.getText().toString().equals(PreferenceManager.getDefaultSharedPreferences((Context)this).getString(this.getString(2131099722), ""))) {
            ((ListView)this.findViewById(2131492981)).setAdapter((ListAdapter)new SimpleCursorAdapter((Context)this, 2130968630, this.getContentResolver().query(NotesProvider.CONTENT_URI, new String[] { "_id", "title", "note" }, (String)null, (String[])null, (String)null), new String[] { "title", "note" }, new int[] { 2131493043, 2131493044 }, 0));
            editText.setVisibility(4);
            button.setVisibility(4);
            return;
        }
        Toast.makeText((Context)this, (CharSequence)"Please Enter a valid pin!", 0).show();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968604);
    }
}
