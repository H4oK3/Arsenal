// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.widget.Toolbar;
import android.support.annotation.LayoutRes;
import android.support.v4.app.ActivityCompat;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.content.res.Configuration;
import android.support.v4.app.NavUtils;
import android.content.Intent;
import android.view.MenuInflater;
import android.support.annotation.Nullable;
import android.app.Activity;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.app.FragmentActivity;

public class AppCompatActivity extends FragmentActivity implements AppCompatCallback, SupportParentable, DelegateProvider
{
    private AppCompatDelegate mDelegate;
    
    public void addContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.getDelegate().addContentView(view, viewGroup$LayoutParams);
    }
    
    public AppCompatDelegate getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = AppCompatDelegate.create(this, this);
        }
        return this.mDelegate;
    }
    
    @Nullable
    @Override
    public Delegate getDrawerToggleDelegate() {
        return this.getDelegate().getDrawerToggleDelegate();
    }
    
    public MenuInflater getMenuInflater() {
        return this.getDelegate().getMenuInflater();
    }
    
    @Nullable
    public ActionBar getSupportActionBar() {
        return this.getDelegate().getSupportActionBar();
    }
    
    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        return NavUtils.getParentActivityIntent(this);
    }
    
    public void invalidateOptionsMenu() {
        this.getDelegate().invalidateOptionsMenu();
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.getDelegate().onConfigurationChanged(configuration);
    }
    
    public void onContentChanged() {
        this.onSupportContentChanged();
    }
    
    @Override
    protected void onCreate(@Nullable final Bundle bundle) {
        this.getDelegate().installViewFactory();
        this.getDelegate().onCreate(bundle);
        super.onCreate(bundle);
    }
    
    public void onCreateSupportNavigateUpTaskStack(final TaskStackBuilder taskStackBuilder) {
        taskStackBuilder.addParentStack(this);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.getDelegate().onDestroy();
    }
    
    @Override
    public final boolean onMenuItemSelected(final int n, final MenuItem menuItem) {
        if (super.onMenuItemSelected(n, menuItem)) {
            return true;
        }
        final ActionBar supportActionBar = this.getSupportActionBar();
        return menuItem.getItemId() == 16908332 && supportActionBar != null && (supportActionBar.getDisplayOptions() & 0x4) != 0x0 && this.onSupportNavigateUp();
    }
    
    public boolean onMenuOpened(final int n, final Menu menu) {
        return super.onMenuOpened(n, menu);
    }
    
    @Override
    public void onPanelClosed(final int n, final Menu menu) {
        super.onPanelClosed(n, menu);
    }
    
    protected void onPostCreate(@Nullable final Bundle bundle) {
        super.onPostCreate(bundle);
        this.getDelegate().onPostCreate(bundle);
    }
    
    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.getDelegate().onPostResume();
    }
    
    public void onPrepareSupportNavigateUpTaskStack(final TaskStackBuilder taskStackBuilder) {
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        this.getDelegate().onStop();
    }
    
    @CallSuper
    @Override
    public void onSupportActionModeFinished(final ActionMode actionMode) {
    }
    
    @CallSuper
    @Override
    public void onSupportActionModeStarted(final ActionMode actionMode) {
    }
    
    @Deprecated
    public void onSupportContentChanged() {
    }
    
    public boolean onSupportNavigateUp() {
        final Intent supportParentActivityIntent = this.getSupportParentActivityIntent();
        if (supportParentActivityIntent != null) {
            Label_0050: {
                if (!this.supportShouldUpRecreateTask(supportParentActivityIntent)) {
                    break Label_0050;
                }
                final TaskStackBuilder create = TaskStackBuilder.create((Context)this);
                this.onCreateSupportNavigateUpTaskStack(create);
                this.onPrepareSupportNavigateUpTaskStack(create);
                create.startActivities();
                try {
                    ActivityCompat.finishAffinity(this);
                    return true;
                }
                catch (IllegalStateException ex) {
                    this.finish();
                    return true;
                }
            }
            this.supportNavigateUpTo(supportParentActivityIntent);
            return true;
        }
        return false;
    }
    
    protected void onTitleChanged(final CharSequence title, final int n) {
        super.onTitleChanged(title, n);
        this.getDelegate().setTitle(title);
    }
    
    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(final ActionMode.Callback callback) {
        return null;
    }
    
    public void setContentView(@LayoutRes final int contentView) {
        this.getDelegate().setContentView(contentView);
    }
    
    public void setContentView(final View contentView) {
        this.getDelegate().setContentView(contentView);
    }
    
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.getDelegate().setContentView(view, viewGroup$LayoutParams);
    }
    
    public void setSupportActionBar(@Nullable final Toolbar supportActionBar) {
        this.getDelegate().setSupportActionBar(supportActionBar);
    }
    
    @Deprecated
    public void setSupportProgress(final int n) {
    }
    
    @Deprecated
    public void setSupportProgressBarIndeterminate(final boolean b) {
    }
    
    @Deprecated
    public void setSupportProgressBarIndeterminateVisibility(final boolean b) {
    }
    
    @Deprecated
    public void setSupportProgressBarVisibility(final boolean b) {
    }
    
    public ActionMode startSupportActionMode(final ActionMode.Callback callback) {
        return this.getDelegate().startSupportActionMode(callback);
    }
    
    @Override
    public void supportInvalidateOptionsMenu() {
        this.getDelegate().invalidateOptionsMenu();
    }
    
    public void supportNavigateUpTo(final Intent intent) {
        NavUtils.navigateUpTo(this, intent);
    }
    
    public boolean supportRequestWindowFeature(final int n) {
        return this.getDelegate().requestWindowFeature(n);
    }
    
    public boolean supportShouldUpRecreateTask(final Intent intent) {
        return NavUtils.shouldUpRecreateTask(this, intent);
    }
}
