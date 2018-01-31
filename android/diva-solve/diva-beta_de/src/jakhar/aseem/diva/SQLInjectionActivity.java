// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.database.Cursor;
import android.content.Context;
import android.widget.Toast;
import android.widget.EditText;
import android.view.View;
import android.util.Log;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

public class SQLInjectionActivity extends AppCompatActivity
{
    private SQLiteDatabase mDB;
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        while (true) {
            try {
                (this.mDB = this.openOrCreateDatabase("sqli", 0, (SQLiteDatabase$CursorFactory)null)).execSQL("DROP TABLE IF EXISTS sqliuser;");
                this.mDB.execSQL("CREATE TABLE IF NOT EXISTS sqliuser(user VARCHAR, password VARCHAR, credit_card VARCHAR);");
                this.mDB.execSQL("INSERT INTO sqliuser VALUES ('admin', 'passwd123', '1234567812345678');");
                this.mDB.execSQL("INSERT INTO sqliuser VALUES ('diva', 'p@ssword', '1111222233334444');");
                this.mDB.execSQL("INSERT INTO sqliuser VALUES ('john', 'password123', '5555666677778888');");
                this.setContentView(2130968617);
            }
            catch (Exception ex) {
                Log.d("Diva-sqli", "Error occurred while creating database for SQLI: " + ex.getMessage());
                continue;
            }
            break;
        }
    }
    
    public void search(final View view) {
        final EditText editText = (EditText)this.findViewById(2131493017);
        try {
            final Cursor rawQuery = this.mDB.rawQuery("SELECT * FROM sqliuser WHERE user = '" + editText.getText().toString() + "'", (String[])null);
            final StringBuilder sb = new StringBuilder("");
            if (rawQuery != null && rawQuery.getCount() > 0) {
                rawQuery.moveToFirst();
                do {
                    sb.append("User: (" + rawQuery.getString(0) + ") pass: (" + rawQuery.getString(1) + ") Credit card: (" + rawQuery.getString(2) + ")\n");
                } while (rawQuery.moveToNext());
            }
            else {
                sb.append("User: (" + editText.getText().toString() + ") not found");
            }
            Toast.makeText((Context)this, (CharSequence)sb.toString(), 0).show();
        }
        catch (Exception ex) {
            Log.d("Diva-sqli", "Error occurred while searching in database: " + ex.getMessage());
        }
    }
}
