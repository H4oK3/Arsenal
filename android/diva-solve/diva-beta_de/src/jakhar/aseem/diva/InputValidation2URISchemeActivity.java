// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.os.Bundle;
import android.widget.EditText;
import android.webkit.WebView;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

public class InputValidation2URISchemeActivity extends AppCompatActivity
{
    public void get(final View view) {
        ((WebView)this.findViewById(2131492995)).loadUrl(((EditText)this.findViewById(2131492993)).getText().toString());
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968609);
        ((WebView)this.findViewById(2131492995)).getSettings().setJavaScriptEnabled(true);
    }
}
