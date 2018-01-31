// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.Window;
import android.view.LayoutInflater;
import android.support.v4.util.SimpleArrayMap;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.Menu;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.content.res.Configuration;
import java.util.List;
import java.util.ArrayList;
import android.util.Log;
import android.content.Intent;
import android.os.Build$VERSION;
import java.io.FileDescriptor;
import android.util.AttributeSet;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources$NotFoundException;
import android.app.Activity;
import android.view.ViewGroup;
import android.view.View;
import java.io.PrintWriter;
import android.os.Message;
import android.support.v4.media.session.MediaControllerCompat;
import android.os.Handler;

public class FragmentActivity extends BaseFragmentActivityHoneycomb implements OnRequestPermissionsResultCallback, RequestPermissionsRequestCodeValidator
{
    static final String FRAGMENTS_TAG = "android:support:fragments";
    private static final int HONEYCOMB = 11;
    static final int MSG_REALLY_STOPPED = 1;
    static final int MSG_RESUME_PENDING = 2;
    private static final String TAG = "FragmentActivity";
    boolean mCreated;
    final FragmentController mFragments;
    final Handler mHandler;
    MediaControllerCompat mMediaController;
    boolean mOptionsMenuInvalidated;
    boolean mReallyStopped;
    boolean mRequestedPermissionsFromFragment;
    boolean mResumed;
    boolean mRetaining;
    boolean mStopped;
    
    public FragmentActivity() {
        this.mHandler = new Handler() {
            public void handleMessage(final Message message) {
                switch (message.what) {
                    default: {
                        super.handleMessage(message);
                        break;
                    }
                    case 1: {
                        if (FragmentActivity.this.mStopped) {
                            FragmentActivity.this.doReallyStop(false);
                            return;
                        }
                        break;
                    }
                    case 2: {
                        FragmentActivity.this.onResumeFragments();
                        FragmentActivity.this.mFragments.execPendingActions();
                    }
                }
            }
        };
        this.mFragments = FragmentController.createController(new HostCallbacks());
    }
    
    private void dumpViewHierarchy(String string, final PrintWriter printWriter, final View view) {
        printWriter.print(string);
        if (view == null) {
            printWriter.println("null");
        }
        else {
            printWriter.println(viewToString(view));
            if (view instanceof ViewGroup) {
                final ViewGroup viewGroup = (ViewGroup)view;
                final int childCount = viewGroup.getChildCount();
                if (childCount > 0) {
                    string += "  ";
                    for (int i = 0; i < childCount; ++i) {
                        this.dumpViewHierarchy(string, printWriter, viewGroup.getChildAt(i));
                    }
                }
            }
        }
    }
    
    private void requestPermissionsFromFragment(final Fragment fragment, final String[] array, final int n) {
        if (n == -1) {
            ActivityCompat.requestPermissions(this, array, n);
            return;
        }
        if ((n & 0xFFFFFF00) != 0x0) {
            throw new IllegalArgumentException("Can only use lower 8 bits for requestCode");
        }
        this.mRequestedPermissionsFromFragment = true;
        ActivityCompat.requestPermissions(this, array, (fragment.mIndex + 1 << 8) + (n & 0xFF));
    }
    
    private static String viewToString(final View view) {
        final char c = 'F';
        final char c2 = '.';
        final StringBuilder sb = new StringBuilder(128);
        sb.append(view.getClass().getName());
        sb.append('{');
        sb.append(Integer.toHexString(System.identityHashCode(view)));
        sb.append(' ');
        Resources resources;
        int id = 0;
        String resourcePackageName;
        String resourceTypeName;
        String resourceEntryName;
        char c3 = '\0';
        char c4 = '\0';
        char c5 = '\0';
        char c6 = '\0';
        char c7 = '\0';
        char c8 = '\0';
        char c9 = '\0';
        char c10 = '\0';
        char c11 = '\0';
        char c12;
        Label_0509_Outer:Label_0616_Outer:
        while (true) {
        Label_0610_Outer:
            while (true) {
                while (true) {
                    Label_0562_Outer:Label_0604_Outer:
                    while (true) {
                        Label_0261: {
                        Label_0598_Outer:
                            while (true) {
                                Label_0244: {
                                Label_0592_Outer:
                                    while (true) {
                                        Label_0220: {
                                            Label_0580_Outer:Label_0586_Outer:
                                            while (true) {
                                                Label_0203: {
                                                    while (true) {
                                                        Label_0186: {
                                                        Label_0574_Outer:
                                                            while (true) {
                                                                Label_0169: {
                                                                Label_0568_Outer:
                                                                    while (true) {
                                                                        Label_0152: {
                                                                            while (true) {
                                                                                Label_0135: {
                                                                                    while (true) {
                                                                                        Label_0118: {
                                                                                            while (true) {
                                                                                                while (true) {
                                                                                                    while (true) {
                                                                                                        switch (view.getVisibility()) {
                                                                                                            default: {
                                                                                                                sb.append('.');
                                                                                                                break;
                                                                                                            }
                                                                                                            case 0: {
                                                                                                                Label_0523: {
                                                                                                                    break Label_0523;
                                                                                                                    try {
                                                                                                                        resourcePackageName = resources.getResourcePackageName(id);
                                                                                                                        while (true) {
                                                                                                                            resourceTypeName = resources.getResourceTypeName(id);
                                                                                                                            resourceEntryName = resources.getResourceEntryName(id);
                                                                                                                            sb.append(" ");
                                                                                                                            sb.append(resourcePackageName);
                                                                                                                            sb.append(":");
                                                                                                                            sb.append(resourceTypeName);
                                                                                                                            sb.append("/");
                                                                                                                            sb.append(resourceEntryName);
                                                                                                                            sb.append("}");
                                                                                                                            return sb.toString();
                                                                                                                            sb.append('V');
                                                                                                                            break;
                                                                                                                            c3 = '.';
                                                                                                                            break Label_0135;
                                                                                                                            c4 = '.';
                                                                                                                            break Label_0261;
                                                                                                                            c5 = '.';
                                                                                                                            break Label_0244;
                                                                                                                            c6 = '.';
                                                                                                                            break Label_0220;
                                                                                                                            c7 = '.';
                                                                                                                            break Label_0186;
                                                                                                                            sb.append('G');
                                                                                                                            break;
                                                                                                                            c8 = '.';
                                                                                                                            break Label_0169;
                                                                                                                            c9 = '.';
                                                                                                                            break Label_0203;
                                                                                                                            resourcePackageName = "android";
                                                                                                                            continue Label_0509_Outer;
                                                                                                                            c10 = '.';
                                                                                                                            break Label_0118;
                                                                                                                            c11 = 'D';
                                                                                                                            break Label_0152;
                                                                                                                            sb.append('I');
                                                                                                                            break;
                                                                                                                            resourcePackageName = "app";
                                                                                                                            continue Label_0509_Outer;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    catch (Resources$NotFoundException ex) {
                                                                                                                        continue Label_0562_Outer;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            }
                                                                                                            case 4: {
                                                                                                                continue Label_0610_Outer;
                                                                                                            }
                                                                                                            case 8: {
                                                                                                                continue Label_0574_Outer;
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                    }
                                                                                                    break;
                                                                                                }
                                                                                                if (!view.isFocusable()) {
                                                                                                    continue Label_0568_Outer;
                                                                                                }
                                                                                                break;
                                                                                            }
                                                                                            c10 = 'F';
                                                                                        }
                                                                                        sb.append(c10);
                                                                                        if (!view.isEnabled()) {
                                                                                            continue Label_0604_Outer;
                                                                                        }
                                                                                        break;
                                                                                    }
                                                                                    c3 = 'E';
                                                                                }
                                                                                sb.append(c3);
                                                                                if (!view.willNotDraw()) {
                                                                                    continue Label_0610_Outer;
                                                                                }
                                                                                break;
                                                                            }
                                                                            c11 = '.';
                                                                        }
                                                                        sb.append(c11);
                                                                        if (!view.isHorizontalScrollBarEnabled()) {
                                                                            continue Label_0586_Outer;
                                                                        }
                                                                        break;
                                                                    }
                                                                    c8 = 'H';
                                                                }
                                                                sb.append(c8);
                                                                if (!view.isVerticalScrollBarEnabled()) {
                                                                    continue Label_0574_Outer;
                                                                }
                                                                break;
                                                            }
                                                            c7 = 'V';
                                                        }
                                                        sb.append(c7);
                                                        if (!view.isClickable()) {
                                                            continue Label_0616_Outer;
                                                        }
                                                        break;
                                                    }
                                                    c9 = 'C';
                                                }
                                                sb.append(c9);
                                                if (!view.isLongClickable()) {
                                                    continue Label_0580_Outer;
                                                }
                                                break;
                                            }
                                            c6 = 'L';
                                        }
                                        sb.append(c6);
                                        sb.append(' ');
                                        if (!view.isFocused()) {
                                            continue Label_0592_Outer;
                                        }
                                        break;
                                    }
                                    c5 = c;
                                }
                                sb.append(c5);
                                if (!view.isSelected()) {
                                    continue Label_0598_Outer;
                                }
                                break;
                            }
                            c4 = 'S';
                        }
                        sb.append(c4);
                        c12 = c2;
                        if (view.isPressed()) {
                            c12 = 'P';
                        }
                        sb.append(c12);
                        sb.append(' ');
                        sb.append(view.getLeft());
                        sb.append(',');
                        sb.append(view.getTop());
                        sb.append('-');
                        sb.append(view.getRight());
                        sb.append(',');
                        sb.append(view.getBottom());
                        id = view.getId();
                        if (id == -1) {
                            continue Label_0562_Outer;
                        }
                        sb.append(" #");
                        sb.append(Integer.toHexString(id));
                        resources = view.getResources();
                        if (id == 0 || resources == null) {
                            continue Label_0562_Outer;
                        }
                        break;
                    }
                    switch (0xFF000000 & id) {
                        default: {
                            continue Label_0509_Outer;
                        }
                        case 2130706432: {
                            continue;
                        }
                        case 16777216: {
                            continue Label_0610_Outer;
                        }
                    }
                    break;
                }
                break;
            }
            break;
        }
    }
    
    @Override
    final View dispatchFragmentsOnCreateView(final View view, final String s, final Context context, final AttributeSet set) {
        return this.mFragments.onCreateView(view, s, context, set);
    }
    
    void doReallyStop(final boolean mRetaining) {
        if (!this.mReallyStopped) {
            this.mReallyStopped = true;
            this.mRetaining = mRetaining;
            this.mHandler.removeMessages(1);
            this.onReallyStop();
        }
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        if (Build$VERSION.SDK_INT >= 11) {}
        printWriter.print(s);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        final String string = s + "  ";
        printWriter.print(string);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print("mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        printWriter.print(" mReallyStopped=");
        printWriter.println(this.mReallyStopped);
        this.mFragments.dumpLoaders(string, fileDescriptor, printWriter, array);
        this.mFragments.getSupportFragmentManager().dump(s, fileDescriptor, printWriter, array);
        printWriter.print(s);
        printWriter.println("View Hierarchy:");
        this.dumpViewHierarchy(s + "  ", printWriter, this.getWindow().getDecorView());
    }
    
    public Object getLastCustomNonConfigurationInstance() {
        final NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances)this.getLastNonConfigurationInstance();
        if (nonConfigurationInstances != null) {
            return nonConfigurationInstances.custom;
        }
        return null;
    }
    
    public FragmentManager getSupportFragmentManager() {
        return this.mFragments.getSupportFragmentManager();
    }
    
    public LoaderManager getSupportLoaderManager() {
        return this.mFragments.getSupportLoaderManager();
    }
    
    public final MediaControllerCompat getSupportMediaController() {
        return this.mMediaController;
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        this.mFragments.noteStateNotSaved();
        final int n3 = n >> 16;
        if (n3 == 0) {
            super.onActivityResult(n, n2, intent);
            return;
        }
        final int n4 = n3 - 1;
        final int activeFragmentsCount = this.mFragments.getActiveFragmentsCount();
        if (activeFragmentsCount == 0 || n4 < 0 || n4 >= activeFragmentsCount) {
            Log.w("FragmentActivity", "Activity result fragment index out of range: 0x" + Integer.toHexString(n));
            return;
        }
        final Fragment fragment = this.mFragments.getActiveFragments(new ArrayList<Fragment>(activeFragmentsCount)).get(n4);
        if (fragment == null) {
            Log.w("FragmentActivity", "Activity result no fragment exists for index: 0x" + Integer.toHexString(n));
            return;
        }
        fragment.onActivityResult(0xFFFF & n, n2, intent);
    }
    
    public void onAttachFragment(final Fragment fragment) {
    }
    
    public void onBackPressed() {
        if (!this.mFragments.getSupportFragmentManager().popBackStackImmediate()) {
            this.supportFinishAfterTransition();
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mFragments.dispatchConfigurationChanged(configuration);
    }
    
    @Override
    protected void onCreate(@Nullable final Bundle bundle) {
        final List<Fragment> list = null;
        this.mFragments.attachHost(null);
        super.onCreate(bundle);
        final NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances)this.getLastNonConfigurationInstance();
        if (nonConfigurationInstances != null) {
            this.mFragments.restoreLoaderNonConfig(nonConfigurationInstances.loaders);
        }
        if (bundle != null) {
            final Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            final FragmentController mFragments = this.mFragments;
            List<Fragment> fragments = list;
            if (nonConfigurationInstances != null) {
                fragments = nonConfigurationInstances.fragments;
            }
            mFragments.restoreAllState(parcelable, fragments);
        }
        this.mFragments.dispatchCreate();
    }
    
    public boolean onCreatePanelMenu(final int n, final Menu menu) {
        if (n == 0) {
            final boolean onCreatePanelMenu = super.onCreatePanelMenu(n, menu);
            final boolean dispatchCreateOptionsMenu = this.mFragments.dispatchCreateOptionsMenu(menu, this.getMenuInflater());
            return Build$VERSION.SDK_INT < 11 || (onCreatePanelMenu | dispatchCreateOptionsMenu);
        }
        return super.onCreatePanelMenu(n, menu);
    }
    
    protected void onDestroy() {
        super.onDestroy();
        this.doReallyStop(false);
        this.mFragments.dispatchDestroy();
        this.mFragments.doLoaderDestroy();
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (Build$VERSION.SDK_INT < 5 && n == 4 && keyEvent.getRepeatCount() == 0) {
            this.onBackPressed();
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.dispatchLowMemory();
    }
    
    public boolean onMenuItemSelected(final int n, final MenuItem menuItem) {
        if (super.onMenuItemSelected(n, menuItem)) {
            return true;
        }
        switch (n) {
            default: {
                return false;
            }
            case 0: {
                return this.mFragments.dispatchOptionsItemSelected(menuItem);
            }
            case 6: {
                return this.mFragments.dispatchContextItemSelected(menuItem);
            }
        }
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.noteStateNotSaved();
    }
    
    public void onPanelClosed(final int n, final Menu menu) {
        switch (n) {
            case 0: {
                this.mFragments.dispatchOptionsMenuClosed(menu);
                break;
            }
        }
        super.onPanelClosed(n, menu);
    }
    
    protected void onPause() {
        super.onPause();
        this.mResumed = false;
        if (this.mHandler.hasMessages(2)) {
            this.mHandler.removeMessages(2);
            this.onResumeFragments();
        }
        this.mFragments.dispatchPause();
    }
    
    protected void onPostResume() {
        super.onPostResume();
        this.mHandler.removeMessages(2);
        this.onResumeFragments();
        this.mFragments.execPendingActions();
    }
    
    protected boolean onPrepareOptionsPanel(final View view, final Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }
    
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        if (n == 0 && menu != null) {
            if (this.mOptionsMenuInvalidated) {
                this.mOptionsMenuInvalidated = false;
                menu.clear();
                this.onCreatePanelMenu(n, menu);
            }
            return this.onPrepareOptionsPanel(view, menu) | this.mFragments.dispatchPrepareOptionsMenu(menu);
        }
        return super.onPreparePanel(n, view, menu);
    }
    
    void onReallyStop() {
        this.mFragments.doLoaderStop(this.mRetaining);
        this.mFragments.dispatchReallyStop();
    }
    
    @Override
    public void onRequestPermissionsResult(final int n, @NonNull final String[] array, @NonNull final int[] array2) {
        final int n2 = n >> 8 & 0xFF;
        if (n2 != 0) {
            final int n3 = n2 - 1;
            final int activeFragmentsCount = this.mFragments.getActiveFragmentsCount();
            if (activeFragmentsCount == 0 || n3 < 0 || n3 >= activeFragmentsCount) {
                Log.w("FragmentActivity", "Activity result fragment index out of range: 0x" + Integer.toHexString(n));
            }
            else {
                final Fragment fragment = this.mFragments.getActiveFragments(new ArrayList<Fragment>(activeFragmentsCount)).get(n3);
                if (fragment == null) {
                    Log.w("FragmentActivity", "Activity result no fragment exists for index: 0x" + Integer.toHexString(n));
                    return;
                }
                fragment.onRequestPermissionsResult(n & 0xFF, array, array2);
            }
        }
    }
    
    protected void onResume() {
        super.onResume();
        this.mHandler.sendEmptyMessage(2);
        this.mResumed = true;
        this.mFragments.execPendingActions();
    }
    
    protected void onResumeFragments() {
        this.mFragments.dispatchResume();
    }
    
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }
    
    public final Object onRetainNonConfigurationInstance() {
        if (this.mStopped) {
            this.doReallyStop(true);
        }
        final Object onRetainCustomNonConfigurationInstance = this.onRetainCustomNonConfigurationInstance();
        final List<Fragment> retainNonConfig = this.mFragments.retainNonConfig();
        final SimpleArrayMap<String, LoaderManager> retainLoaderNonConfig = this.mFragments.retainLoaderNonConfig();
        if (retainNonConfig == null && retainLoaderNonConfig == null && onRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        final NonConfigurationInstances nonConfigurationInstances = new NonConfigurationInstances();
        nonConfigurationInstances.custom = onRetainCustomNonConfigurationInstance;
        nonConfigurationInstances.fragments = retainNonConfig;
        nonConfigurationInstances.loaders = retainLoaderNonConfig;
        return nonConfigurationInstances;
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        final Parcelable saveAllState = this.mFragments.saveAllState();
        if (saveAllState != null) {
            bundle.putParcelable("android:support:fragments", saveAllState);
        }
    }
    
    protected void onStart() {
        super.onStart();
        this.mStopped = false;
        this.mReallyStopped = false;
        this.mHandler.removeMessages(1);
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.dispatchActivityCreated();
        }
        this.mFragments.noteStateNotSaved();
        this.mFragments.execPendingActions();
        this.mFragments.doLoaderStart();
        this.mFragments.dispatchStart();
        this.mFragments.reportLoaderStart();
    }
    
    public void onStateNotSaved() {
        this.mFragments.noteStateNotSaved();
    }
    
    protected void onStop() {
        super.onStop();
        this.mStopped = true;
        this.mHandler.sendEmptyMessage(1);
        this.mFragments.dispatchStop();
    }
    
    public void setEnterSharedElementCallback(final SharedElementCallback sharedElementCallback) {
        ActivityCompat.setEnterSharedElementCallback(this, sharedElementCallback);
    }
    
    public void setExitSharedElementCallback(final SharedElementCallback sharedElementCallback) {
        ActivityCompat.setExitSharedElementCallback(this, sharedElementCallback);
    }
    
    public final void setSupportMediaController(final MediaControllerCompat mMediaController) {
        this.mMediaController = mMediaController;
        if (Build$VERSION.SDK_INT >= 21) {
            ActivityCompat21.setMediaController(this, mMediaController.getMediaController());
        }
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        if (n != -1 && (0xFFFF0000 & n) != 0x0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
        super.startActivityForResult(intent, n);
    }
    
    public void startActivityFromFragment(final Fragment fragment, final Intent intent, final int n) {
        if (n == -1) {
            super.startActivityForResult(intent, -1);
            return;
        }
        if ((0xFFFF0000 & n) != 0x0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
        super.startActivityForResult(intent, (fragment.mIndex + 1 << 16) + (0xFFFF & n));
    }
    
    public void supportFinishAfterTransition() {
        ActivityCompat.finishAfterTransition(this);
    }
    
    public void supportInvalidateOptionsMenu() {
        if (Build$VERSION.SDK_INT >= 11) {
            ActivityCompatHoneycomb.invalidateOptionsMenu(this);
            return;
        }
        this.mOptionsMenuInvalidated = true;
    }
    
    public void supportPostponeEnterTransition() {
        ActivityCompat.postponeEnterTransition(this);
    }
    
    public void supportStartPostponedEnterTransition() {
        ActivityCompat.startPostponedEnterTransition(this);
    }
    
    @Override
    public final void validateRequestPermissionsRequestCode(final int n) {
        if (this.mRequestedPermissionsFromFragment) {
            this.mRequestedPermissionsFromFragment = false;
        }
        else if ((n & 0xFFFFFF00) != 0x0) {
            throw new IllegalArgumentException("Can only use lower 8 bits for requestCode");
        }
    }
    
    class HostCallbacks extends FragmentHostCallback<FragmentActivity>
    {
        public HostCallbacks() {
            super(FragmentActivity.this);
        }
        
        public void onAttachFragment(final Fragment fragment) {
            FragmentActivity.this.onAttachFragment(fragment);
        }
        
        @Override
        public void onDump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
            FragmentActivity.this.dump(s, fileDescriptor, printWriter, array);
        }
        
        @Nullable
        @Override
        public View onFindViewById(final int n) {
            return FragmentActivity.this.findViewById(n);
        }
        
        @Override
        public FragmentActivity onGetHost() {
            return FragmentActivity.this;
        }
        
        @Override
        public LayoutInflater onGetLayoutInflater() {
            return FragmentActivity.this.getLayoutInflater().cloneInContext((Context)FragmentActivity.this);
        }
        
        @Override
        public int onGetWindowAnimations() {
            final Window window = FragmentActivity.this.getWindow();
            if (window == null) {
                return 0;
            }
            return window.getAttributes().windowAnimations;
        }
        
        @Override
        public boolean onHasView() {
            final Window window = FragmentActivity.this.getWindow();
            return window != null && window.peekDecorView() != null;
        }
        
        @Override
        public boolean onHasWindowAnimations() {
            return FragmentActivity.this.getWindow() != null;
        }
        
        @Override
        public void onRequestPermissionsFromFragment(@NonNull final Fragment fragment, @NonNull final String[] array, final int n) {
            FragmentActivity.this.requestPermissionsFromFragment(fragment, array, n);
        }
        
        @Override
        public boolean onShouldSaveFragmentState(final Fragment fragment) {
            return !FragmentActivity.this.isFinishing();
        }
        
        @Override
        public boolean onShouldShowRequestPermissionRationale(@NonNull final String s) {
            return ActivityCompat.shouldShowRequestPermissionRationale(FragmentActivity.this, s);
        }
        
        @Override
        public void onStartActivityFromFragment(final Fragment fragment, final Intent intent, final int n) {
            FragmentActivity.this.startActivityFromFragment(fragment, intent, n);
        }
        
        @Override
        public void onSupportInvalidateOptionsMenu() {
            FragmentActivity.this.supportInvalidateOptionsMenu();
        }
    }
    
    static final class NonConfigurationInstances
    {
        Object custom;
        List<Fragment> fragments;
        SimpleArrayMap<String, LoaderManager> loaders;
    }
}
