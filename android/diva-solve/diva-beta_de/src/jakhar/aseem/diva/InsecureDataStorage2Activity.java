// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.content.Context;
import android.widget.Toast;
import android.widget.EditText;
import android.view.View;
import android.util.Log;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

public class InsecureDataStorage2Activity extends AppCompatActivity
{
    private SQLiteDatabase mDB;
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        while (true) {
            try {
                (this.mDB = this.openOrCreateDatabase("ids2", 0, (SQLiteDatabase$CursorFactory)null)).execSQL("CREATE TABLE IF NOT EXISTS myuser(user VARCHAR, password VARCHAR);");
                this.setContentView(2130968612);
            }
            catch (Exception ex) {
                Log.d("Diva", "Error occurred while creating database: " + ex.getMessage());
                continue;
            }
            break;
        }
    }
    
    public void saveCredentials(final View view) {
        final EditText editText = (EditText)this.findViewById(2131493003);
        final EditText editText2 = (EditText)this.findViewById(2131493004);
        while (true) {
            try {
                this.mDB.execSQL("INSERT INTO myuser VALUES ('" + editText.getText().toString() + "', '" + editText2.getText().toString() + "');");
                this.mDB.close();
                Toast.makeText((Context)this, (CharSequence)"3rd party credentials saved successfully!", 0).show();
            }
            catch (Exception ex) {
                Log.d("Diva", "Error occurred while inserting into database: " + ex.getMessage());
                continue;
            }
            break;
        }
    }
}
