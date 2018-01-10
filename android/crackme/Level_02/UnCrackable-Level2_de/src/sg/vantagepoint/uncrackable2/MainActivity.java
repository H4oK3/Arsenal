// 
// Decompiled by Procyon v0.5.30
// 

package sg.vantagepoint.uncrackable2;

import android.widget.EditText;
import android.view.View;
import android.os.SystemClock;
import android.os.Debug;
import android.os.AsyncTask;
import sg.vantagepoint.a.a;
import sg.vantagepoint.a.b;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.support.v7.app.c;

public class MainActivity extends c
{
    private CodeCheck m;
    
    static {
        System.loadLibrary("foo");
    }
    
    private void a(final String title) {
        final AlertDialog create = new AlertDialog$Builder((Context)this).create();
        create.setTitle((CharSequence)title);
        create.setMessage((CharSequence)"This in unacceptable. The app is now going to exit.");
        create.setButton(-3, (CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                System.exit(0);
            }
        });
        create.setCancelable(false);
        create.show();
    }
    
    private native void init();
    
    @Override
    protected void onCreate(final Bundle bundle) {
        this.init();
        if (b.a() || b.b() || b.c()) {
            this.a("Root detected!");
        }
        if (a.a(this.getApplicationContext())) {
            this.a("App is debuggable!");
        }
        new AsyncTask<Void, String, String>() {
            protected String a(final Void... array) {
                while (!Debug.isDebuggerConnected()) {
                    SystemClock.sleep(100L);
                }
                return null;
            }
            
            protected void a(final String s) {
                MainActivity.this.a("Debugger detected!");
            }
        }.execute((Object[])new Void[] { null, null, null });
        this.m = new CodeCheck();
        super.onCreate(bundle);
        this.setContentView(2130968603);
    }
    
    public void verify(final View view) {
        final String string = ((EditText)this.findViewById(2131427422)).getText().toString();
        final AlertDialog create = new AlertDialog$Builder((Context)this).create();
        if (this.m.a(string)) {
            create.setTitle((CharSequence)"Success!");
            create.setMessage((CharSequence)"This is the correct secret.");
        }
        else {
            create.setTitle((CharSequence)"Nope...");
            create.setMessage((CharSequence)"That's not it. Try again.");
        }
        create.setButton(-3, (CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.dismiss();
            }
        });
        create.show();
    }
}
