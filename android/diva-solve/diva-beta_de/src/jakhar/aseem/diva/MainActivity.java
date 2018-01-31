// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.MenuItem;
import android.view.Menu;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968616);
        this.setSupportActionBar((Toolbar)this.findViewById(2131493015));
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(2131558400, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        return menuItem.getItemId() == 2131493058 || super.onOptionsItemSelected(menuItem);
    }
    
    public void startChallenge(final View view) {
        if (view == this.findViewById(2131493023)) {
            this.startActivity(new Intent((Context)this, (Class)LogActivity.class));
        }
        else {
            if (view == this.findViewById(2131493024)) {
                this.startActivity(new Intent((Context)this, (Class)HardcodeActivity.class));
                return;
            }
            if (view == this.findViewById(2131493025)) {
                this.startActivity(new Intent((Context)this, (Class)InsecureDataStorage1Activity.class));
                return;
            }
            if (view == this.findViewById(2131493026)) {
                this.startActivity(new Intent((Context)this, (Class)InsecureDataStorage2Activity.class));
                return;
            }
            if (view == this.findViewById(2131493027)) {
                this.startActivity(new Intent((Context)this, (Class)InsecureDataStorage3Activity.class));
                return;
            }
            if (view == this.findViewById(2131493028)) {
                this.startActivity(new Intent((Context)this, (Class)InsecureDataStorage4Activity.class));
                return;
            }
            if (view == this.findViewById(2131493029)) {
                this.startActivity(new Intent((Context)this, (Class)SQLInjectionActivity.class));
                return;
            }
            if (view == this.findViewById(2131493030)) {
                this.startActivity(new Intent((Context)this, (Class)InputValidation2URISchemeActivity.class));
                return;
            }
            if (view == this.findViewById(2131493031)) {
                this.startActivity(new Intent((Context)this, (Class)AccessControl1Activity.class));
                return;
            }
            if (view == this.findViewById(2131493032)) {
                this.startActivity(new Intent((Context)this, (Class)AccessControl2Activity.class));
                return;
            }
            if (view == this.findViewById(2131493033)) {
                this.startActivity(new Intent((Context)this, (Class)AccessControl3Activity.class));
                return;
            }
            if (view == this.findViewById(2131493034)) {
                this.startActivity(new Intent((Context)this, (Class)Hardcode2Activity.class));
                return;
            }
            if (view == this.findViewById(2131493035)) {
                this.startActivity(new Intent((Context)this, (Class)InputValidation3Activity.class));
            }
        }
    }
}
