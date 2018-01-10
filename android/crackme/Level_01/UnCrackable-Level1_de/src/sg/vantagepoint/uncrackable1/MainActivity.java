// 
// Decompiled by Procyon v0.5.30
// 

package sg.vantagepoint.uncrackable1;

import android.widget.EditText;
import android.view.View;
import sg.vantagepoint.a.c;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.app.Activity;

public class MainActivity extends Activity
{
    private void a(final String title) {
        final AlertDialog create = new AlertDialog$Builder((Context)this).create();
        create.setTitle((CharSequence)title);
        create.setMessage((CharSequence)"This in unacceptable. The app is now going to exit.");
        create.setButton(-3, (CharSequence)"OK", (DialogInterface$OnClickListener)new b(this));
        create.setCancelable(false);
        create.show();
    }
    
    protected void onCreate(final Bundle bundle) {
        if (c.a() || c.b() || c.c()) {
            this.a("Root detected!");
        }
        if (sg.vantagepoint.a.b.a(this.getApplicationContext())) {
            this.a("App is debuggable!");
        }
        super.onCreate(bundle);
        this.setContentView(2130903040);
    }
    
    public void verify(final View view) {
        final String string = ((EditText)this.findViewById(2131230720)).getText().toString();
        final AlertDialog create = new AlertDialog$Builder((Context)this).create();
        if (a.a(string)) {
            create.setTitle((CharSequence)"Success!");
            create.setMessage((CharSequence)"This is the correct secret.");
        }
        else {
            create.setTitle((CharSequence)"Nope...");
            create.setMessage((CharSequence)"That's not it. Try again.");
        }
        create.setButton(-3, (CharSequence)"OK", (DialogInterface$OnClickListener)new sg.vantagepoint.uncrackable1.c(this));
        create.show();
    }
}
