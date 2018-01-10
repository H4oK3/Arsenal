// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import android.view.LayoutInflater;
import android.view.Window;
import android.annotation.SuppressLint;
import android.content.IntentSender;
import android.view.MenuItem;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.res.Configuration;
import android.util.Log;
import android.content.Intent;
import java.io.FileDescriptor;
import android.app.Activity;
import android.os.Build$VERSION;
import android.view.Menu;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup;
import java.io.PrintWriter;
import android.content.res.Resources;
import android.content.res.Resources$NotFoundException;
import android.view.View;
import android.os.Message;
import android.support.v4.f.k;
import android.os.Handler;

public class l extends j
{
    final Handler c;
    final n d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    boolean i;
    boolean j;
    int k;
    k<String> l;
    
    public l() {
        this.c = new Handler() {
            public void handleMessage(final Message message) {
                switch (message.what) {
                    default: {
                        super.handleMessage(message);
                        break;
                    }
                    case 1: {
                        if (android.support.v4.a.l.this.g) {
                            android.support.v4.a.l.this.a(false);
                            return;
                        }
                        break;
                    }
                    case 2: {
                        android.support.v4.a.l.this.a_();
                        android.support.v4.a.l.this.d.n();
                    }
                }
            }
        };
        this.d = n.a(new a());
        this.g = true;
        this.h = true;
    }
    
    private static String a(final View view) {
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
                    Label_0534_Outer:Label_0586_Outer:Label_0598_Outer:Label_0604_Outer:
                    while (true) {
                        Label_0261: {
                            while (true) {
                                Label_0244: {
                                    while (true) {
                                        Label_0220: {
                                            while (true) {
                                                Label_0203: {
                                                    Label_0562_Outer:Label_0580_Outer:
                                                    while (true) {
                                                        Label_0186: {
                                                            while (true) {
                                                                Label_0169: {
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
                                                                                                                            sb.append('I');
                                                                                                                            break;
                                                                                                                            c3 = '.';
                                                                                                                            break Label_0203;
                                                                                                                            sb.append('G');
                                                                                                                            break;
                                                                                                                            resourcePackageName = "android";
                                                                                                                            continue Label_0509_Outer;
                                                                                                                            c4 = '.';
                                                                                                                            break Label_0118;
                                                                                                                            c5 = '.';
                                                                                                                            break Label_0135;
                                                                                                                            sb.append('V');
                                                                                                                            break;
                                                                                                                            c6 = '.';
                                                                                                                            break Label_0244;
                                                                                                                            c7 = '.';
                                                                                                                            break Label_0261;
                                                                                                                            c8 = '.';
                                                                                                                            break Label_0169;
                                                                                                                            resourcePackageName = "app";
                                                                                                                            continue Label_0509_Outer;
                                                                                                                        }
                                                                                                                        c9 = '.';
                                                                                                                        break Label_0220;
                                                                                                                        c10 = 'D';
                                                                                                                        break Label_0152;
                                                                                                                        c11 = '.';
                                                                                                                        break Label_0186;
                                                                                                                    }
                                                                                                                    catch (Resources$NotFoundException ex) {
                                                                                                                        continue Label_0534_Outer;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            }
                                                                                                            case 4: {
                                                                                                                continue Label_0586_Outer;
                                                                                                            }
                                                                                                            case 8: {
                                                                                                                continue Label_0616_Outer;
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                    }
                                                                                                    break;
                                                                                                }
                                                                                                if (!view.isFocusable()) {
                                                                                                    continue Label_0562_Outer;
                                                                                                }
                                                                                                break;
                                                                                            }
                                                                                            c4 = 'F';
                                                                                        }
                                                                                        sb.append(c4);
                                                                                        if (!view.isEnabled()) {
                                                                                            continue Label_0598_Outer;
                                                                                        }
                                                                                        break;
                                                                                    }
                                                                                    c5 = 'E';
                                                                                }
                                                                                sb.append(c5);
                                                                                if (!view.willNotDraw()) {
                                                                                    continue Label_0580_Outer;
                                                                                }
                                                                                break;
                                                                            }
                                                                            c10 = '.';
                                                                        }
                                                                        sb.append(c10);
                                                                        if (!view.isHorizontalScrollBarEnabled()) {
                                                                            continue Label_0610_Outer;
                                                                        }
                                                                        break;
                                                                    }
                                                                    c8 = 'H';
                                                                }
                                                                sb.append(c8);
                                                                if (!view.isVerticalScrollBarEnabled()) {
                                                                    continue;
                                                                }
                                                                break;
                                                            }
                                                            c11 = 'V';
                                                        }
                                                        sb.append(c11);
                                                        if (!view.isClickable()) {
                                                            continue Label_0616_Outer;
                                                        }
                                                        break;
                                                    }
                                                    c3 = 'C';
                                                }
                                                sb.append(c3);
                                                if (!view.isLongClickable()) {
                                                    continue;
                                                }
                                                break;
                                            }
                                            c9 = 'L';
                                        }
                                        sb.append(c9);
                                        sb.append(' ');
                                        if (!view.isFocused()) {
                                            continue Label_0604_Outer;
                                        }
                                        break;
                                    }
                                    c6 = c;
                                }
                                sb.append(c6);
                                if (!view.isSelected()) {
                                    continue Label_0610_Outer;
                                }
                                break;
                            }
                            c7 = 'S';
                        }
                        sb.append(c7);
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
                            continue Label_0586_Outer;
                        }
                        sb.append(" #");
                        sb.append(Integer.toHexString(id));
                        resources = view.getResources();
                        if (id == 0 || resources == null) {
                            continue Label_0586_Outer;
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
    
    private void a(String string, final PrintWriter printWriter, final View view) {
        printWriter.print(string);
        if (view == null) {
            printWriter.println("null");
        }
        else {
            printWriter.println(a(view));
            if (view instanceof ViewGroup) {
                final ViewGroup viewGroup = (ViewGroup)view;
                final int childCount = viewGroup.getChildCount();
                if (childCount > 0) {
                    string += "  ";
                    for (int i = 0; i < childCount; ++i) {
                        this.a(string, printWriter, viewGroup.getChildAt(i));
                    }
                }
            }
        }
    }
    
    @Override
    final View a(final View view, final String s, final Context context, final AttributeSet set) {
        return this.d.a(view, s, context, set);
    }
    
    public void a(final android.support.v4.a.k k) {
    }
    
    void a(final boolean i) {
        if (!this.h) {
            this.h = true;
            this.i = i;
            this.c.removeMessages(1);
            this.d();
        }
        else if (i) {
            this.d.o();
            this.d.c(true);
        }
    }
    
    protected boolean a(final View view, final Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }
    
    protected void a_() {
        this.d.h();
    }
    
    public Object b() {
        return null;
    }
    
    public void c() {
        if (Build$VERSION.SDK_INT >= 11) {
            android.support.v4.a.b.a(this);
            return;
        }
        this.j = true;
    }
    
    void d() {
        this.d.c(this.i);
        this.d.k();
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
        printWriter.print(this.e);
        printWriter.print("mResumed=");
        printWriter.print(this.f);
        printWriter.print(" mStopped=");
        printWriter.print(this.g);
        printWriter.print(" mReallyStopped=");
        printWriter.println(this.h);
        this.d.a(string, fileDescriptor, printWriter, array);
        this.d.a().a(s, fileDescriptor, printWriter, array);
        printWriter.print(s);
        printWriter.println("View Hierarchy:");
        this.a(s + "  ", printWriter, this.getWindow().getDecorView());
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        this.d.b();
        final int n3 = n >> 16;
        if (n3 == 0) {
            super.onActivityResult(n, n2, intent);
            return;
        }
        final int n4 = n3 - 1;
        final String s = this.l.a(n4);
        this.l.c(n4);
        if (s == null) {
            Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
            return;
        }
        final android.support.v4.a.k a = this.d.a(s);
        if (a == null) {
            Log.w("FragmentActivity", "Activity result no fragment exists for who: " + s);
            return;
        }
        a.a(0xFFFF & n, n2, intent);
    }
    
    public void onBackPressed() {
        if (!this.d.a().a()) {
            super.onBackPressed();
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.d.a(configuration);
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        this.d.a((android.support.v4.a.k)null);
        super.onCreate(bundle);
        final b b = (b)this.getLastNonConfigurationInstance();
        if (b != null) {
            this.d.a(b.c);
        }
        if (bundle != null) {
            final Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            final n d = this.d;
            r b2;
            if (b != null) {
                b2 = b.b;
            }
            else {
                b2 = null;
            }
            d.a(parcelable, b2);
            if (bundle.containsKey("android:support:next_request_index")) {
                this.k = bundle.getInt("android:support:next_request_index");
                final int[] intArray = bundle.getIntArray("android:support:request_indicies");
                final String[] stringArray = bundle.getStringArray("android:support:request_fragment_who");
                if (intArray == null || stringArray == null || intArray.length != stringArray.length) {
                    Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
                }
                else {
                    this.l = new k<String>(intArray.length);
                    for (int i = 0; i < intArray.length; ++i) {
                        this.l.b(intArray[i], stringArray[i]);
                    }
                }
            }
        }
        if (this.l == null) {
            this.l = new k<String>();
            this.k = 0;
        }
        this.d.e();
    }
    
    public boolean onCreatePanelMenu(final int n, final Menu menu) {
        if (n == 0) {
            final boolean onCreatePanelMenu = super.onCreatePanelMenu(n, menu);
            final boolean a = this.d.a(menu, this.getMenuInflater());
            return Build$VERSION.SDK_INT < 11 || (onCreatePanelMenu | a);
        }
        return super.onCreatePanelMenu(n, menu);
    }
    
    protected void onDestroy() {
        super.onDestroy();
        this.a(false);
        this.d.l();
        this.d.p();
    }
    
    public void onLowMemory() {
        super.onLowMemory();
        this.d.m();
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
                return this.d.a(menuItem);
            }
            case 6: {
                return this.d.b(menuItem);
            }
        }
    }
    
    public void onMultiWindowModeChanged(final boolean b) {
        this.d.a(b);
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.d.b();
    }
    
    public void onPanelClosed(final int n, final Menu menu) {
        switch (n) {
            case 0: {
                this.d.b(menu);
                break;
            }
        }
        super.onPanelClosed(n, menu);
    }
    
    protected void onPause() {
        super.onPause();
        this.f = false;
        if (this.c.hasMessages(2)) {
            this.c.removeMessages(2);
            this.a_();
        }
        this.d.i();
    }
    
    public void onPictureInPictureModeChanged(final boolean b) {
        this.d.b(b);
    }
    
    protected void onPostResume() {
        super.onPostResume();
        this.c.removeMessages(2);
        this.a_();
        this.d.n();
    }
    
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        if (n == 0 && menu != null) {
            if (this.j) {
                this.j = false;
                menu.clear();
                this.onCreatePanelMenu(n, menu);
            }
            return this.a(view, menu) | this.d.a(menu);
        }
        return super.onPreparePanel(n, view, menu);
    }
    
    public void onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        final int n2 = n >> 16 & 0xFFFF;
        if (n2 != 0) {
            final int n3 = n2 - 1;
            final String s = this.l.a(n3);
            this.l.c(n3);
            if (s == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
            }
            else {
                final android.support.v4.a.k a = this.d.a(s);
                if (a == null) {
                    Log.w("FragmentActivity", "Activity result no fragment exists for who: " + s);
                    return;
                }
                a.a(n & 0xFFFF, array, array2);
            }
        }
    }
    
    protected void onResume() {
        super.onResume();
        this.c.sendEmptyMessage(2);
        this.f = true;
        this.d.n();
    }
    
    public final Object onRetainNonConfigurationInstance() {
        if (this.g) {
            this.a(true);
        }
        final Object b = this.b();
        final r d = this.d.d();
        final android.support.v4.f.j<String, x> r = this.d.r();
        if (d == null && r == null && b == null) {
            return null;
        }
        final b b2 = new b();
        b2.a = b;
        b2.b = d;
        b2.c = r;
        return b2;
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        final Parcelable c = this.d.c();
        if (c != null) {
            bundle.putParcelable("android:support:fragments", c);
        }
        if (this.l.b() > 0) {
            bundle.putInt("android:support:next_request_index", this.k);
            final int[] array = new int[this.l.b()];
            final String[] array2 = new String[this.l.b()];
            for (int i = 0; i < this.l.b(); ++i) {
                array[i] = this.l.d(i);
                array2[i] = this.l.e(i);
            }
            bundle.putIntArray("android:support:request_indicies", array);
            bundle.putStringArray("android:support:request_fragment_who", array2);
        }
    }
    
    protected void onStart() {
        super.onStart();
        this.g = false;
        this.h = false;
        this.c.removeMessages(1);
        if (!this.e) {
            this.e = true;
            this.d.f();
        }
        this.d.b();
        this.d.n();
        this.d.o();
        this.d.g();
        this.d.q();
    }
    
    public void onStateNotSaved() {
        this.d.b();
    }
    
    protected void onStop() {
        super.onStop();
        this.g = true;
        this.c.sendEmptyMessage(1);
        this.d.j();
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        if (!this.b && n != -1) {
            android.support.v4.a.h.a(n);
        }
        super.startActivityForResult(intent, n);
    }
    
    class a extends o<l>
    {
        public a() {
            super(android.support.v4.a.l.this);
        }
        
        @Override
        public View a(final int n) {
            return android.support.v4.a.l.this.findViewById(n);
        }
        
        @SuppressLint({ "NewApi" })
        @Override
        public void a(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
            android.support.v4.a.l.this.dump(s, fileDescriptor, printWriter, array);
        }
        
        @Override
        public boolean a() {
            final Window window = android.support.v4.a.l.this.getWindow();
            return window != null && window.peekDecorView() != null;
        }
        
        @Override
        public boolean a(final android.support.v4.a.k k) {
            return !android.support.v4.a.l.this.isFinishing();
        }
        
        @Override
        public LayoutInflater b() {
            return android.support.v4.a.l.this.getLayoutInflater().cloneInContext((Context)android.support.v4.a.l.this);
        }
        
        public void b(final android.support.v4.a.k k) {
            android.support.v4.a.l.this.a(k);
        }
        
        @Override
        public void c() {
            android.support.v4.a.l.this.c();
        }
        
        @Override
        public boolean d() {
            return android.support.v4.a.l.this.getWindow() != null;
        }
        
        @Override
        public int e() {
            final Window window = android.support.v4.a.l.this.getWindow();
            if (window == null) {
                return 0;
            }
            return window.getAttributes().windowAnimations;
        }
    }
    
    static final class b
    {
        Object a;
        r b;
        android.support.v4.f.j<String, x> c;
    }
}
