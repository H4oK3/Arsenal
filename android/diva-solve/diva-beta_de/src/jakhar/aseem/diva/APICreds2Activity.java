// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.content.Context;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class APICreds2Activity extends AppCompatActivity
{
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968606);
        final TextView textView = (TextView)this.findViewById(2131492983);
        final EditText editText = (EditText)this.findViewById(2131492984);
        final Button button = (Button)this.findViewById(2131492985);
        if (!this.getIntent().getBooleanExtra(this.getString(2131099686), true)) {
            textView.setText((CharSequence)"TVEETER API Key: secrettveeterapikey\nAPI User name: diva2\nAPI Password: p@ssword2");
            return;
        }
        textView.setText((CharSequence)"Register yourself at http://payatu.com to get your PIN and then login with that PIN!");
        editText.setVisibility(0);
        button.setVisibility(0);
    }
    
    public void viewCreds(final View view) {
        Toast.makeText((Context)this, (CharSequence)"Invalid PIN. Please try again", 0).show();
    }
}
