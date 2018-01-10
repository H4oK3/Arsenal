// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import java.util.Collection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.ComponentName;
import android.support.v4.g.e;
import android.view.SubMenu;
import android.util.SparseArray;
import android.support.v4.g.q;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.KeyCharacterMap$KeyData;
import java.util.List;
import android.view.KeyEvent;
import java.util.Iterator;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;
import android.view.ContextMenu$ContextMenuInfo;
import java.util.ArrayList;
import android.content.res.Resources;
import android.content.Context;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.support.v4.d.a.a;

public class h implements android.support.v4.d.a.a
{
    private static final int[] d;
    CharSequence a;
    Drawable b;
    View c;
    private final Context e;
    private final Resources f;
    private boolean g;
    private boolean h;
    private a i;
    private ArrayList<j> j;
    private ArrayList<j> k;
    private boolean l;
    private ArrayList<j> m;
    private ArrayList<j> n;
    private boolean o;
    private int p;
    private ContextMenu$ContextMenuInfo q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private ArrayList<j> w;
    private CopyOnWriteArrayList<WeakReference<o>> x;
    private j y;
    private boolean z;
    
    static {
        d = new int[] { 1, 4, 5, 3, 2, 0 };
    }
    
    public h(final Context e) {
        this.p = 0;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = new ArrayList<j>();
        this.x = new CopyOnWriteArrayList<WeakReference<o>>();
        this.e = e;
        this.f = e.getResources();
        this.j = new ArrayList<j>();
        this.k = new ArrayList<j>();
        this.l = true;
        this.m = new ArrayList<j>();
        this.n = new ArrayList<j>();
        this.e(this.o = true);
    }
    
    private static int a(final ArrayList<j> list, final int n) {
        for (int i = list.size() - 1; i >= 0; --i) {
            if (list.get(i).c() <= n) {
                return i + 1;
            }
        }
        return 0;
    }
    
    private j a(final int n, final int n2, final int n3, final int n4, final CharSequence charSequence, final int n5) {
        return new j(this, n, n2, n3, n4, charSequence, n5);
    }
    
    private void a(final int n, final CharSequence a, final int n2, final Drawable b, final View c) {
        final Resources d = this.d();
        if (c != null) {
            this.c = c;
            this.a = null;
            this.b = null;
        }
        else {
            if (n > 0) {
                this.a = d.getText(n);
            }
            else if (a != null) {
                this.a = a;
            }
            if (n2 > 0) {
                this.b = android.support.v4.b.a.a(this.e(), n2);
            }
            else if (b != null) {
                this.b = b;
            }
            this.c = null;
        }
        this.b(false);
    }
    
    private void a(final int n, final boolean b) {
        if (n >= 0 && n < this.j.size()) {
            this.j.remove(n);
            if (b) {
                this.b(true);
            }
        }
    }
    
    private boolean a(final u u, final o o) {
        boolean b = false;
        if (this.x.isEmpty()) {
            return false;
        }
        if (o != null) {
            b = o.a(u);
        }
        for (final WeakReference<o> weakReference : this.x) {
            final o o2 = weakReference.get();
            if (o2 == null) {
                this.x.remove(weakReference);
            }
            else {
                if (b) {
                    continue;
                }
                b = o2.a(u);
            }
        }
        return b;
    }
    
    private void d(final boolean b) {
        if (this.x.isEmpty()) {
            return;
        }
        this.g();
        for (final WeakReference<o> weakReference : this.x) {
            final o o = weakReference.get();
            if (o == null) {
                this.x.remove(weakReference);
            }
            else {
                o.b(b);
            }
        }
        this.h();
    }
    
    private void e(final boolean b) {
        final boolean b2 = true;
        this.h = (b && this.f.getConfiguration().keyboard != 1 && this.f.getBoolean(android.support.v7.a.a.b.abc_config_showMenuShortcutsWhenKeyboardPresent) && b2);
    }
    
    private static int f(final int n) {
        final int n2 = (0xFFFF0000 & n) >> 16;
        if (n2 < 0 || n2 >= h.d.length) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        return h.d[n2] << 16 | (0xFFFF & n);
    }
    
    public int a(final int n, final int n2) {
        final int size = this.size();
        int i = n2;
        if (n2 < 0) {
            i = 0;
        }
        while (i < size) {
            if (this.j.get(i).getGroupId() == n) {
                return i;
            }
            ++i;
        }
        return -1;
    }
    
    public h a(final int p) {
        this.p = p;
        return this;
    }
    
    protected h a(final Drawable drawable) {
        this.a(0, null, 0, drawable, null);
        return this;
    }
    
    protected h a(final View view) {
        this.a(0, null, 0, null, view);
        return this;
    }
    
    protected h a(final CharSequence charSequence) {
        this.a(0, charSequence, 0, null, null);
        return this;
    }
    
    j a(final int n, final KeyEvent keyEvent) {
        final ArrayList<j> w = this.w;
        w.clear();
        this.a(w, n, keyEvent);
        j j;
        if (w.isEmpty()) {
            j = null;
        }
        else {
            final int metaState = keyEvent.getMetaState();
            final KeyCharacterMap$KeyData keyCharacterMap$KeyData = new KeyCharacterMap$KeyData();
            keyEvent.getKeyData(keyCharacterMap$KeyData);
            final int size = w.size();
            if (size == 1) {
                return w.get(0);
            }
            final boolean b = this.b();
            for (int i = 0; i < size; ++i) {
                final j k = w.get(i);
                char c;
                if (b) {
                    c = k.getAlphabeticShortcut();
                }
                else {
                    c = k.getNumericShortcut();
                }
                if (c == keyCharacterMap$KeyData.meta[0]) {
                    j = k;
                    if ((metaState & 0x2) == 0x0) {
                        return j;
                    }
                }
                if (c == keyCharacterMap$KeyData.meta[2]) {
                    j = k;
                    if ((metaState & 0x2) != 0x0) {
                        return j;
                    }
                }
                if (b && c == '\b') {
                    j = k;
                    if (n == 67) {
                        return j;
                    }
                }
            }
            return null;
        }
        return j;
    }
    
    protected MenuItem a(final int n, final int n2, final int n3, final CharSequence charSequence) {
        final int f = f(n3);
        final j a = this.a(n, n2, n3, f, charSequence, this.p);
        if (this.q != null) {
            a.a(this.q);
        }
        this.j.add(a(this.j, f), a);
        this.b(true);
        return (MenuItem)a;
    }
    
    protected String a() {
        return "android:menu:actionviewstates";
    }
    
    public void a(final Bundle bundle) {
        final int size = this.size();
        int i = 0;
        SparseArray sparseArray = null;
        while (i < size) {
            final MenuItem item = this.getItem(i);
            final View a = android.support.v4.g.q.a(item);
            SparseArray sparseArray2 = sparseArray;
            if (a != null) {
                sparseArray2 = sparseArray;
                if (a.getId() != -1) {
                    SparseArray sparseArray3;
                    if ((sparseArray3 = sparseArray) == null) {
                        sparseArray3 = new SparseArray();
                    }
                    a.saveHierarchyState(sparseArray3);
                    sparseArray2 = sparseArray3;
                    if (android.support.v4.g.q.c(item)) {
                        bundle.putInt("android:menu:expandedactionview", item.getItemId());
                        sparseArray2 = sparseArray3;
                    }
                }
            }
            if (item.hasSubMenu()) {
                ((u)item.getSubMenu()).a(bundle);
            }
            ++i;
            sparseArray = sparseArray2;
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(this.a(), sparseArray);
        }
    }
    
    public void a(final a i) {
        this.i = i;
    }
    
    void a(final j j) {
        this.b(this.l = true);
    }
    
    public void a(final o o) {
        this.a(o, this.e);
    }
    
    public void a(final o o, final Context context) {
        this.x.add(new WeakReference<o>(o));
        o.a(context, this);
        this.o = true;
    }
    
    void a(final MenuItem menuItem) {
        final int groupId = menuItem.getGroupId();
        final int size = this.j.size();
        this.g();
        for (int i = 0; i < size; ++i) {
            final j j = this.j.get(i);
            if (j.getGroupId() == groupId && j.g() && j.isCheckable()) {
                j.b(j == menuItem);
            }
        }
        this.h();
    }
    
    void a(final List<j> list, final int n, final KeyEvent keyEvent) {
        final boolean b = this.b();
        final int metaState = keyEvent.getMetaState();
        final KeyCharacterMap$KeyData keyCharacterMap$KeyData = new KeyCharacterMap$KeyData();
        if (keyEvent.getKeyData(keyCharacterMap$KeyData) || n == 67) {
            for (int size = this.j.size(), i = 0; i < size; ++i) {
                final j j = this.j.get(i);
                if (j.hasSubMenu()) {
                    ((h)j.getSubMenu()).a(list, n, keyEvent);
                }
                char c;
                if (b) {
                    c = j.getAlphabeticShortcut();
                }
                else {
                    c = j.getNumericShortcut();
                }
                if ((metaState & 0x5) == 0x0 && c != '\0' && (c == keyCharacterMap$KeyData.meta[0] || c == keyCharacterMap$KeyData.meta[2] || (b && c == '\b' && n == 67)) && j.isEnabled()) {
                    list.add(j);
                }
            }
        }
    }
    
    public final void a(final boolean b) {
        if (this.v) {
            return;
        }
        this.v = true;
        for (final WeakReference<o> weakReference : this.x) {
            final o o = weakReference.get();
            if (o == null) {
                this.x.remove(weakReference);
            }
            else {
                o.a(this, b);
            }
        }
        this.v = false;
    }
    
    boolean a(final h h, final MenuItem menuItem) {
        return this.i != null && this.i.a(h, menuItem);
    }
    
    public boolean a(final MenuItem menuItem, final int n) {
        return this.a(menuItem, null, n);
    }
    
    public boolean a(final MenuItem menuItem, final o o, final int n) {
        final boolean b = false;
        final j j = (j)menuItem;
        boolean b2 = b;
        if (j != null) {
            if (!j.isEnabled()) {
                b2 = b;
            }
            else {
                final boolean b3 = j.b();
                final e a = j.a();
                final boolean b4 = a != null && a.e();
                if (j.n()) {
                    final boolean b5 = b2 = (j.expandActionView() | b3);
                    if (b5) {
                        this.a(true);
                        return b5;
                    }
                }
                else {
                    if (!j.hasSubMenu() && !b4) {
                        if ((n & 0x1) == 0x0) {
                            this.a(true);
                        }
                        return b3;
                    }
                    if ((n & 0x4) == 0x0) {
                        this.a(false);
                    }
                    if (!j.hasSubMenu()) {
                        j.a(new u(this.e(), this, j));
                    }
                    final u u = (u)j.getSubMenu();
                    if (b4) {
                        a.a((SubMenu)u);
                    }
                    final boolean b6 = b2 = (this.a(u, o) | b3);
                    if (!b6) {
                        this.a(true);
                        return b6;
                    }
                }
            }
        }
        return b2;
    }
    
    public MenuItem add(final int n) {
        return this.a(0, 0, 0, this.f.getString(n));
    }
    
    public MenuItem add(final int n, final int n2, final int n3, final int n4) {
        return this.a(n, n2, n3, this.f.getString(n4));
    }
    
    public MenuItem add(final int n, final int n2, final int n3, final CharSequence charSequence) {
        return this.a(n, n2, n3, charSequence);
    }
    
    public MenuItem add(final CharSequence charSequence) {
        return this.a(0, 0, 0, charSequence);
    }
    
    public int addIntentOptions(final int n, final int n2, final int n3, final ComponentName componentName, final Intent[] array, final Intent intent, int i, final MenuItem[] array2) {
        final PackageManager packageManager = this.e.getPackageManager();
        final List queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, array, intent, 0);
        int size;
        if (queryIntentActivityOptions != null) {
            size = queryIntentActivityOptions.size();
        }
        else {
            size = 0;
        }
        if ((i & 0x1) == 0x0) {
            this.removeGroup(n);
        }
        ResolveInfo resolveInfo;
        Intent intent2;
        Intent intent3;
        MenuItem setIntent;
        for (i = 0; i < size; ++i) {
            resolveInfo = queryIntentActivityOptions.get(i);
            if (resolveInfo.specificIndex < 0) {
                intent2 = intent;
            }
            else {
                intent2 = array[resolveInfo.specificIndex];
            }
            intent3 = new Intent(intent2);
            intent3.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            setIntent = this.add(n, n2, n3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent3);
            if (array2 != null && resolveInfo.specificIndex >= 0) {
                array2[resolveInfo.specificIndex] = setIntent;
            }
        }
        return size;
    }
    
    public SubMenu addSubMenu(final int n) {
        return this.addSubMenu(0, 0, 0, this.f.getString(n));
    }
    
    public SubMenu addSubMenu(final int n, final int n2, final int n3, final int n4) {
        return this.addSubMenu(n, n2, n3, this.f.getString(n4));
    }
    
    public SubMenu addSubMenu(final int n, final int n2, final int n3, final CharSequence charSequence) {
        final j j = (j)this.a(n, n2, n3, charSequence);
        final u u = new u(this.e, this, j);
        j.a(u);
        return (SubMenu)u;
    }
    
    public SubMenu addSubMenu(final CharSequence charSequence) {
        return this.addSubMenu(0, 0, 0, charSequence);
    }
    
    public int b(final int n) {
        for (int size = this.size(), i = 0; i < size; ++i) {
            if (this.j.get(i).getItemId() == n) {
                return i;
            }
        }
        return -1;
    }
    
    public void b(final Bundle bundle) {
        if (bundle != null) {
            final SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(this.a());
            for (int size = this.size(), i = 0; i < size; ++i) {
                final MenuItem item = this.getItem(i);
                final View a = android.support.v4.g.q.a(item);
                if (a != null && a.getId() != -1) {
                    a.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((u)item.getSubMenu()).b(bundle);
                }
            }
            final int int1 = bundle.getInt("android:menu:expandedactionview");
            if (int1 > 0) {
                final MenuItem item2 = this.findItem(int1);
                if (item2 != null) {
                    android.support.v4.g.q.b(item2);
                }
            }
        }
    }
    
    void b(final j j) {
        this.b(this.o = true);
    }
    
    public void b(final o o) {
        for (final WeakReference<o> weakReference : this.x) {
            final o o2 = weakReference.get();
            if (o2 == null || o2 == o) {
                this.x.remove(weakReference);
            }
        }
    }
    
    public void b(final boolean b) {
        if (!this.r) {
            if (b) {
                this.l = true;
                this.o = true;
            }
            this.d(b);
        }
        else {
            this.s = true;
            if (b) {
                this.t = true;
            }
        }
    }
    
    boolean b() {
        return this.g;
    }
    
    public int c(final int n) {
        return this.a(n, 0);
    }
    
    public void c(final boolean z) {
        this.z = z;
    }
    
    public boolean c() {
        return this.h;
    }
    
    public boolean c(final j y) {
        boolean b = false;
        if (!this.x.isEmpty()) {
            this.g();
            final Iterator<WeakReference<o>> iterator = this.x.iterator();
            boolean a = false;
            while (true) {
                while (iterator.hasNext()) {
                    final WeakReference<o> weakReference = iterator.next();
                    final o o = weakReference.get();
                    if (o == null) {
                        this.x.remove(weakReference);
                    }
                    else {
                        final boolean b2 = a = o.a(this, y);
                        if (!b2) {
                            continue;
                        }
                        a = b2;
                        this.h();
                        b = a;
                        if (a) {
                            this.y = y;
                            return a;
                        }
                        return b;
                    }
                }
                continue;
            }
        }
        return b;
    }
    
    public void clear() {
        if (this.y != null) {
            this.d(this.y);
        }
        this.j.clear();
        this.b(true);
    }
    
    public void clearHeader() {
        this.b = null;
        this.a = null;
        this.c = null;
        this.b(false);
    }
    
    public void close() {
        this.a(true);
    }
    
    Resources d() {
        return this.f;
    }
    
    protected h d(final int n) {
        this.a(n, null, 0, null, null);
        return this;
    }
    
    public boolean d(final j j) {
        boolean b = false;
        if (!this.x.isEmpty()) {
            if (this.y == j) {
                this.g();
                final Iterator<WeakReference<o>> iterator = this.x.iterator();
                boolean b2 = false;
                while (true) {
                    while (iterator.hasNext()) {
                        final WeakReference<o> weakReference = iterator.next();
                        final o o = weakReference.get();
                        if (o == null) {
                            this.x.remove(weakReference);
                        }
                        else {
                            final boolean b3 = b2 = o.b(this, j);
                            if (!b3) {
                                continue;
                            }
                            b2 = b3;
                            this.h();
                            b = b2;
                            if (b2) {
                                this.y = null;
                                return b2;
                            }
                            return b;
                        }
                    }
                    continue;
                }
            }
            b = b;
        }
        return b;
    }
    
    public Context e() {
        return this.e;
    }
    
    protected h e(final int n) {
        this.a(0, null, n, null, null);
        return this;
    }
    
    public void f() {
        if (this.i != null) {
            this.i.a(this);
        }
    }
    
    public MenuItem findItem(final int n) {
        for (int size = this.size(), i = 0; i < size; ++i) {
            Object item = this.j.get(i);
            if (((j)item).getItemId() == n || (((j)item).hasSubMenu() && (item = ((j)item).getSubMenu().findItem(n)) != null)) {
                return (MenuItem)item;
            }
        }
        return null;
    }
    
    public void g() {
        if (!this.r) {
            this.r = true;
            this.s = false;
            this.t = false;
        }
    }
    
    public MenuItem getItem(final int n) {
        return (MenuItem)this.j.get(n);
    }
    
    public void h() {
        this.r = false;
        if (this.s) {
            this.s = false;
            this.b(this.t);
        }
    }
    
    public boolean hasVisibleItems() {
        if (this.z) {
            return true;
        }
        for (int size = this.size(), i = 0; i < size; ++i) {
            if (this.j.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<j> i() {
        if (!this.l) {
            return this.k;
        }
        this.k.clear();
        for (int size = this.j.size(), i = 0; i < size; ++i) {
            final j j = this.j.get(i);
            if (j.isVisible()) {
                this.k.add(j);
            }
        }
        this.l = false;
        this.o = true;
        return this.k;
    }
    
    public boolean isShortcutKey(final int n, final KeyEvent keyEvent) {
        return this.a(n, keyEvent) != null;
    }
    
    public void j() {
        final ArrayList<j> i = this.i();
        if (!this.o) {
            return;
        }
        final Iterator<WeakReference<o>> iterator = this.x.iterator();
        boolean b = false;
        while (iterator.hasNext()) {
            final WeakReference<o> weakReference = iterator.next();
            final o o = weakReference.get();
            if (o == null) {
                this.x.remove(weakReference);
            }
            else {
                b |= o.b();
            }
        }
        if (b) {
            this.m.clear();
            this.n.clear();
            for (int size = i.size(), j = 0; j < size; ++j) {
                final j k = i.get(j);
                if (k.j()) {
                    this.m.add(k);
                }
                else {
                    this.n.add(k);
                }
            }
        }
        else {
            this.m.clear();
            this.n.clear();
            this.n.addAll(this.i());
        }
        this.o = false;
    }
    
    public ArrayList<j> k() {
        this.j();
        return this.m;
    }
    
    public ArrayList<j> l() {
        this.j();
        return this.n;
    }
    
    public CharSequence m() {
        return this.a;
    }
    
    public Drawable n() {
        return this.b;
    }
    
    public View o() {
        return this.c;
    }
    
    public h p() {
        return this;
    }
    
    public boolean performIdentifierAction(final int n, final int n2) {
        return this.a(this.findItem(n), n2);
    }
    
    public boolean performShortcut(final int n, final KeyEvent keyEvent, final int n2) {
        final j a = this.a(n, keyEvent);
        boolean a2 = false;
        if (a != null) {
            a2 = this.a((MenuItem)a, n2);
        }
        if ((n2 & 0x2) != 0x0) {
            this.a(true);
        }
        return a2;
    }
    
    boolean q() {
        return this.u;
    }
    
    public j r() {
        return this.y;
    }
    
    public void removeGroup(final int n) {
        final int c = this.c(n);
        if (c >= 0) {
            for (int size = this.j.size(), n2 = 0; n2 < size - c && this.j.get(c).getGroupId() == n; ++n2) {
                this.a(c, false);
            }
            this.b(true);
        }
    }
    
    public void removeItem(final int n) {
        this.a(this.b(n), true);
    }
    
    public void setGroupCheckable(final int n, final boolean checkable, final boolean b) {
        for (int size = this.j.size(), i = 0; i < size; ++i) {
            final j j = this.j.get(i);
            if (j.getGroupId() == n) {
                j.a(b);
                j.setCheckable(checkable);
            }
        }
    }
    
    public void setGroupEnabled(final int n, final boolean enabled) {
        for (int size = this.j.size(), i = 0; i < size; ++i) {
            final j j = this.j.get(i);
            if (j.getGroupId() == n) {
                j.setEnabled(enabled);
            }
        }
    }
    
    public void setGroupVisible(final int n, final boolean b) {
        final int size = this.j.size();
        int i = 0;
        boolean b2 = false;
        while (i < size) {
            final j j = this.j.get(i);
            if (j.getGroupId() == n && j.c(b)) {
                b2 = true;
            }
            ++i;
        }
        if (b2) {
            this.b(true);
        }
    }
    
    public void setQwertyMode(final boolean g) {
        this.g = g;
        this.b(false);
    }
    
    public int size() {
        return this.j.size();
    }
    
    public interface a
    {
        void a(final h p0);
        
        boolean a(final h p0, final MenuItem p1);
    }
    
    public interface b
    {
        boolean a(final j p0);
    }
}
