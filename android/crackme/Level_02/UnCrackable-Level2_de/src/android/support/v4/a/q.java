// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import android.support.v4.e.c;
import android.support.v4.f.d;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.Menu;
import java.util.Arrays;
import java.util.Iterator;
import android.content.res.Resources$NotFoundException;
import android.content.res.Configuration;
import android.view.animation.AnimationUtils;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.os.Looper;
import android.graphics.Paint;
import android.view.animation.Animation$AnimationListener;
import java.util.List;
import android.support.v4.g.ae;
import java.util.Collection;
import java.io.FileDescriptor;
import java.io.Writer;
import java.io.PrintWriter;
import android.support.v4.f.e;
import android.util.Log;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.content.Context;
import android.support.v4.f.b;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.v4.f.i;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;
import android.os.Parcelable;
import android.util.SparseArray;
import java.lang.reflect.Field;
import android.view.animation.Interpolator;
import android.support.v4.g.n;

final class q extends p implements n
{
    static final Interpolator D;
    static final Interpolator E;
    static final Interpolator F;
    static final Interpolator G;
    static boolean a;
    static final boolean b;
    static Field q;
    SparseArray<Parcelable> A;
    ArrayList<d> B;
    Runnable C;
    private CopyOnWriteArrayList<i<p.a, Boolean>> H;
    ArrayList<c> c;
    boolean d;
    ArrayList<k> e;
    ArrayList<k> f;
    ArrayList<Integer> g;
    ArrayList<f> h;
    ArrayList<k> i;
    ArrayList<f> j;
    ArrayList<Integer> k;
    ArrayList<p.b> l;
    int m;
    o n;
    m o;
    k p;
    boolean r;
    boolean s;
    boolean t;
    String u;
    boolean v;
    ArrayList<f> w;
    ArrayList<Boolean> x;
    ArrayList<k> y;
    Bundle z;
    
    static {
        boolean b2 = false;
        android.support.v4.a.q.a = false;
        if (Build$VERSION.SDK_INT >= 11) {
            b2 = true;
        }
        b = b2;
        android.support.v4.a.q.q = null;
        D = (Interpolator)new DecelerateInterpolator(2.5f);
        E = (Interpolator)new DecelerateInterpolator(1.5f);
        F = (Interpolator)new AccelerateInterpolator(2.5f);
        G = (Interpolator)new AccelerateInterpolator(1.5f);
    }
    
    q() {
        this.m = 0;
        this.z = null;
        this.A = null;
        this.C = new Runnable() {
            @Override
            public void run() {
                android.support.v4.a.q.this.c();
            }
        };
    }
    
    private int a(final ArrayList<f> list, final ArrayList<Boolean> list2, final int n, final int n2, final android.support.v4.f.b<k> b) {
        int i = n2 - 1;
        int n3 = n2;
        while (i >= n) {
            final f f = list.get(i);
            final boolean booleanValue = list2.get(i);
            int n4;
            if (f.b() && !f.a(list, i + 1, n2)) {
                n4 = 1;
            }
            else {
                n4 = 0;
            }
            if (n4 != 0) {
                if (this.B == null) {
                    this.B = new ArrayList<d>();
                }
                final d d = new d(f, booleanValue);
                this.B.add(d);
                f.a(d);
                if (booleanValue) {
                    f.a();
                }
                else {
                    f.a(false);
                }
                --n3;
                if (i != n3) {
                    list.remove(i);
                    list.add(n3, f);
                }
                this.b(b);
            }
            --i;
        }
        return n3;
    }
    
    static Animation a(final Context context, final float n, final float n2) {
        final AlphaAnimation alphaAnimation = new AlphaAnimation(n, n2);
        alphaAnimation.setInterpolator(android.support.v4.a.q.E);
        alphaAnimation.setDuration(220L);
        return (Animation)alphaAnimation;
    }
    
    static Animation a(final Context context, final float n, final float n2, final float n3, final float n4) {
        final AnimationSet set = new AnimationSet(false);
        final ScaleAnimation scaleAnimation = new ScaleAnimation(n, n2, n, n2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(android.support.v4.a.q.D);
        scaleAnimation.setDuration(220L);
        set.addAnimation((Animation)scaleAnimation);
        final AlphaAnimation alphaAnimation = new AlphaAnimation(n3, n4);
        alphaAnimation.setInterpolator(android.support.v4.a.q.E);
        alphaAnimation.setDuration(220L);
        set.addAnimation((Animation)alphaAnimation);
        return (Animation)set;
    }
    
    private void a(final f f, final boolean b, final boolean b2, final boolean b3) {
        final ArrayList<f> list = new ArrayList<f>(1);
        final ArrayList<Boolean> list2 = new ArrayList<Boolean>(1);
        list.add(f);
        list2.add(b);
        b(list, list2, 0, 1);
        if (b2) {
            android.support.v4.a.v.a(this, list, list2, 0, 1, true);
        }
        if (b3) {
            this.a(this.m, true);
        }
        if (this.e != null) {
            for (int size = this.e.size(), i = 0; i < size; ++i) {
                final k k = this.e.get(i);
                if (k != null && k.G != null && k.O && f.b(k.w)) {
                    if (Build$VERSION.SDK_INT >= 11 && k.Q > 0.0f) {
                        k.G.setAlpha(k.Q);
                    }
                    if (b3) {
                        k.Q = 0.0f;
                    }
                    else {
                        k.Q = -1.0f;
                        k.O = false;
                    }
                }
            }
        }
    }
    
    private void a(final android.support.v4.f.b<k> b) {
        for (int size = b.size(), i = 0; i < size; ++i) {
            final k k = b.b(i);
            if (!k.k) {
                final View f = k.f();
                if (Build$VERSION.SDK_INT < 11) {
                    k.f().setVisibility(4);
                }
                else {
                    k.Q = f.getAlpha();
                    f.setAlpha(0.0f);
                }
            }
        }
    }
    
    private void a(final RuntimeException ex) {
        Log.e("FragmentManager", ex.getMessage());
        Log.e("FragmentManager", "Activity state:");
        final PrintWriter printWriter = new PrintWriter(new e("FragmentManager"));
        while (true) {
            Label_0079: {
                if (this.n == null) {
                    break Label_0079;
                }
                try {
                    this.n.a("  ", null, printWriter, new String[0]);
                    throw ex;
                }
                catch (Exception ex2) {
                    Log.e("FragmentManager", "Failed dumping state", (Throwable)ex2);
                    throw ex;
                }
                try {
                    this.a("  ", null, printWriter, new String[0]);
                    continue;
                }
                catch (Exception ex3) {
                    Log.e("FragmentManager", "Failed dumping state", (Throwable)ex3);
                    continue;
                }
            }
            continue;
        }
    }
    
    private void a(final ArrayList<f> list, final ArrayList<Boolean> list2) {
        int size;
        if (this.B == null) {
            size = 0;
        }
        else {
            size = this.B.size();
        }
        int i = 0;
    Label_0081_Outer:
        while (i < size) {
            final d d = this.B.get(i);
            while (true) {
                Label_0101: {
                    if (list == null || d.a) {
                        break Label_0101;
                    }
                    final int index = list.indexOf(d.b);
                    if (index == -1 || !list2.get(index)) {
                        break Label_0101;
                    }
                    d.e();
                    ++i;
                    continue Label_0081_Outer;
                }
                int n = 0;
                int n2 = 0;
                Label_0226: {
                    if (!d.c()) {
                        n = i;
                        n2 = size;
                        if (list == null) {
                            break Label_0226;
                        }
                        n = i;
                        n2 = size;
                        if (!d.b.a(list, 0, list.size())) {
                            break Label_0226;
                        }
                    }
                    this.B.remove(i);
                    n = i - 1;
                    n2 = size - 1;
                    if (list != null && !d.a) {
                        final int index2 = list.indexOf(d.b);
                        if (index2 != -1 && list2.get(index2)) {
                            d.e();
                            i = n;
                            size = n2;
                            continue;
                        }
                    }
                    d.d();
                }
                i = n;
                size = n2;
                continue;
            }
        }
    }
    
    private void a(final ArrayList<f> list, final ArrayList<Boolean> list2, final int n, final int n2) {
        final boolean u = list.get(n).u;
        if (this.y == null) {
            this.y = new ArrayList<k>();
        }
        else {
            this.y.clear();
        }
        if (this.f != null) {
            this.y.addAll(this.f);
        }
        int i = n;
        int n3 = 0;
        while (i < n2) {
            final f f = list.get(i);
            if (!list2.get(i)) {
                f.a(this.y);
            }
            else {
                f.b(this.y);
            }
            if (n3 != 0 || f.j) {
                n3 = 1;
            }
            else {
                n3 = 0;
            }
            ++i;
        }
        this.y.clear();
        if (!u) {
            android.support.v4.a.v.a(this, list, list2, n, n2, false);
        }
        b(list, list2, n, n2);
        int a;
        if (u) {
            final android.support.v4.f.b<k> b = new android.support.v4.f.b<k>();
            this.b(b);
            a = this.a(list, list2, n, n2, b);
            this.a(b);
        }
        else {
            a = n2;
        }
        int j = n;
        if (a != n) {
            j = n;
            if (u) {
                android.support.v4.a.v.a(this, list, list2, n, a, true);
                this.a(this.m, true);
                j = n;
            }
        }
        while (j < n2) {
            final f f2 = list.get(j);
            if (list2.get(j) && f2.n >= 0) {
                this.c(f2.n);
                f2.n = -1;
            }
            ++j;
        }
        if (n3 != 0) {
            this.e();
        }
    }
    
    static boolean a(final View view, final Animation animation) {
        return Build$VERSION.SDK_INT >= 19 && ae.b(view) == 0 && ae.m(view) && a(animation);
    }
    
    static boolean a(final Animation animation) {
        final boolean b = false;
        boolean b2;
        if (animation instanceof AlphaAnimation) {
            b2 = true;
        }
        else {
            b2 = b;
            if (animation instanceof AnimationSet) {
                final List animations = ((AnimationSet)animation).getAnimations();
                int n = 0;
                while (true) {
                    b2 = b;
                    if (n >= animations.size()) {
                        return b2;
                    }
                    if (animations.get(n) instanceof AlphaAnimation) {
                        break;
                    }
                    ++n;
                }
                return true;
            }
        }
        return b2;
    }
    
    private boolean a(final String s, final int n, final int n2) {
        this.c();
        this.c(true);
        final boolean a = this.a(this.w, this.x, s, n, n2);
        Label_0053: {
            if (!a) {
                break Label_0053;
            }
            this.d = true;
            try {
                this.b(this.w, this.x);
                this.v();
                this.d();
                return a;
            }
            finally {
                this.v();
            }
        }
    }
    
    public static int b(final int n, final boolean b) {
        switch (n) {
            default: {
                return -1;
            }
            case 4097: {
                if (b) {
                    return 1;
                }
                return 2;
            }
            case 8194: {
                if (b) {
                    return 3;
                }
                return 4;
            }
            case 4099: {
                if (b) {
                    return 5;
                }
                return 6;
            }
        }
    }
    
    private void b(final android.support.v4.f.b<k> b) {
        if (this.m >= 1) {
            final int min = Math.min(this.m, 4);
            int size;
            if (this.f == null) {
                size = 0;
            }
            else {
                size = this.f.size();
            }
            for (int i = 0; i < size; ++i) {
                final k k = this.f.get(i);
                if (k.b < min) {
                    this.a(k, min, k.I(), k.J(), false);
                    if (k.G != null && !k.y && k.O) {
                        b.add(k);
                    }
                }
            }
        }
    }
    
    private void b(final View view, final Animation animation) {
        if (view == null || animation == null || !a(view, animation)) {
            return;
        }
        while (true) {
            try {
                if (android.support.v4.a.q.q == null) {
                    (android.support.v4.a.q.q = Animation.class.getDeclaredField("mListener")).setAccessible(true);
                }
                final Animation$AnimationListener animation$AnimationListener = (Animation$AnimationListener)android.support.v4.a.q.q.get(animation);
                ae.a(view, 2, null);
                animation.setAnimationListener((Animation$AnimationListener)new a(view, animation, animation$AnimationListener));
            }
            catch (NoSuchFieldException ex) {
                Log.e("FragmentManager", "No field with the name mListener is found in Animation class", (Throwable)ex);
                final Animation$AnimationListener animation$AnimationListener = null;
                continue;
            }
            catch (IllegalAccessException ex2) {
                Log.e("FragmentManager", "Cannot access Animation's mListener field", (Throwable)ex2);
                final Animation$AnimationListener animation$AnimationListener = null;
                continue;
            }
            break;
        }
    }
    
    private void b(final ArrayList<f> list, final ArrayList<Boolean> list2) {
        int i = 0;
        if (list != null && !list.isEmpty()) {
            if (list2 == null || list.size() != list2.size()) {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            this.a(list, list2);
            final int size = list.size();
            int n = 0;
            while (i < size) {
                if (!list.get(i).u) {
                    if (n != i) {
                        this.a(list, list2, n, i);
                    }
                    int n3;
                    int n2 = n3 = i + 1;
                    if (list2.get(i)) {
                        while ((n3 = n2) < size) {
                            n3 = n2;
                            if (!list2.get(n2)) {
                                break;
                            }
                            n3 = n2;
                            if (list.get(n2).u) {
                                break;
                            }
                            ++n2;
                        }
                    }
                    this.a(list, list2, i, n3);
                    final int n4 = n3;
                    final int n5 = n3 - 1;
                    n = n4;
                    i = n5;
                }
                ++i;
            }
            if (n != size) {
                this.a(list, list2, n, size);
            }
        }
    }
    
    private static void b(final ArrayList<f> list, final ArrayList<Boolean> list2, int i, final int n) {
        while (i < n) {
            final f f = list.get(i);
            if (list2.get(i)) {
                f.a(-1);
                f.a(i == n - 1);
            }
            else {
                f.a(1);
                f.a();
            }
            ++i;
        }
    }
    
    private void c(final boolean b) {
        if (this.d) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (Looper.myLooper() != this.n.h().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!b) {
            this.t();
        }
        if (this.w == null) {
            this.w = new ArrayList<f>();
            this.x = new ArrayList<Boolean>();
        }
        this.d = true;
        try {
            this.a(null, (ArrayList<Boolean>)null);
        }
        finally {
            this.d = false;
        }
    }
    
    private boolean c(final ArrayList<f> list, final ArrayList<Boolean> list2) {
        synchronized (this) {
            if (this.c == null || this.c.size() == 0) {
                return false;
            }
            final int size = this.c.size();
            for (int i = 0; i < size; ++i) {
                this.c.get(i).a(list, list2);
            }
            this.c.clear();
            this.n.h().removeCallbacks(this.C);
            // monitorexit(this)
            if (size > 0) {
                return true;
            }
        }
        return false;
    }
    
    public static int d(final int n) {
        switch (n) {
            default: {
                return 0;
            }
            case 4097: {
                return 8194;
            }
            case 8194: {
                return 4097;
            }
            case 4099: {
                return 4099;
            }
        }
    }
    
    private k n(k k) {
        final ViewGroup f = k.F;
        final View g = k.G;
        if (f != null && g != null) {
            for (int i = this.f.indexOf(k) - 1; i >= 0; --i) {
                final k j = this.f.get(i);
                if (j.F == f) {
                    k = j;
                    if (j.G != null) {
                        return k;
                    }
                }
            }
            return null;
        }
        k = null;
        return k;
    }
    
    private void t() {
        if (this.s) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.u != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.u);
        }
    }
    
    private void u() {
    Label_0081_Outer:
        while (true) {
            int n = 1;
        Label_0081:
            while (true) {
            Label_0097:
                while (true) {
                    Label_0092: {
                        while (true) {
                            final int n2;
                            synchronized (this) {
                                if (this.B == null || this.B.isEmpty()) {
                                    break Label_0092;
                                }
                                n2 = 1;
                                if (this.c != null && this.c.size() == 1) {
                                    break Label_0081;
                                }
                                break Label_0097;
                                this.n.h().removeCallbacks(this.C);
                                this.n.h().post(this.C);
                                return;
                            }
                            if (n2 == 0 && n == 0) {
                                return;
                            }
                            continue;
                        }
                    }
                    int n2 = 0;
                    continue Label_0081_Outer;
                }
                n = 0;
                continue Label_0081;
            }
        }
    }
    
    private void v() {
        this.d = false;
        this.x.clear();
        this.w.clear();
    }
    
    private void w() {
        if (this.B != null) {
            while (!this.B.isEmpty()) {
                this.B.remove(0).d();
            }
        }
    }
    
    private void x() {
        int size;
        if (this.e == null) {
            size = 0;
        }
        else {
            size = this.e.size();
        }
        for (int i = 0; i < size; ++i) {
            final k k = this.e.get(i);
            if (k != null && k.N() != null) {
                final int o = k.O();
                final View n = k.N();
                k.a((View)null);
                final Animation animation = n.getAnimation();
                if (animation != null) {
                    animation.cancel();
                }
                this.a(k, o, 0, 0, false);
            }
        }
    }
    
    public k a(final Bundle bundle, final String s) {
        final int int1 = bundle.getInt(s, -1);
        k k;
        if (int1 == -1) {
            k = null;
        }
        else {
            if (int1 >= this.e.size()) {
                this.a(new IllegalStateException("Fragment no longer exists for key " + s + ": index " + int1));
            }
            final k i = this.e.get(int1);
            if ((k = i) == null) {
                this.a(new IllegalStateException("Fragment no longer exists for key " + s + ": index " + int1));
                return i;
            }
        }
        return k;
    }
    
    public k a(final String s) {
        if (this.f != null && s != null) {
            for (int i = this.f.size() - 1; i >= 0; --i) {
                final k k = this.f.get(i);
                if (k != null && s.equals(k.x)) {
                    return k;
                }
            }
        }
        Label_0061: {
            break Label_0061;
        }
        if (this.e != null && s != null) {
            for (int j = this.e.size() - 1; j >= 0; --j) {
                final k l = this.e.get(j);
                if (l != null) {
                    final k k = l;
                    if (s.equals(l.x)) {
                        return k;
                    }
                }
            }
        }
        return null;
    }
    
    @Override
    public View a(final View view, final String s, final Context context, final AttributeSet set) {
        if (!"fragment".equals(s)) {
            return null;
        }
        String s2 = set.getAttributeValue((String)null, "class");
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, android.support.v4.a.q.b.a);
        if (s2 == null) {
            s2 = obtainStyledAttributes.getString(0);
        }
        final int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        final String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!android.support.v4.a.k.b(this.n.g(), s2)) {
            return null;
        }
        int id;
        if (view != null) {
            id = view.getId();
        }
        else {
            id = 0;
        }
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(set.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + s2);
        }
        k b;
        if (resourceId != -1) {
            b = this.b(resourceId);
        }
        else {
            b = null;
        }
        k a = b;
        if (b == null) {
            a = b;
            if (string != null) {
                a = this.a(string);
            }
        }
        k b2;
        if ((b2 = a) == null) {
            b2 = a;
            if (id != -1) {
                b2 = this.b(id);
            }
        }
        if (android.support.v4.a.q.a) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + s2 + " existing=" + b2);
        }
        k a2;
        if (b2 == null) {
            a2 = android.support.v4.a.k.a(context, s2);
            a2.m = true;
            int v;
            if (resourceId != 0) {
                v = resourceId;
            }
            else {
                v = id;
            }
            a2.v = v;
            a2.w = id;
            a2.x = string;
            a2.n = true;
            a2.q = this;
            a2.r = this.n;
            a2.a(this.n.g(), set, a2.c);
            this.a(a2, true);
        }
        else {
            if (b2.n) {
                throw new IllegalArgumentException(set.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + s2);
            }
            b2.n = true;
            b2.r = this.n;
            if (!b2.B) {
                b2.a(this.n.g(), set, b2.c);
            }
            a2 = b2;
        }
        if (this.m < 1 && a2.m) {
            this.a(a2, 1, 0, 0, false);
        }
        else {
            this.b(a2);
        }
        if (a2.G == null) {
            throw new IllegalStateException("Fragment " + s2 + " did not create a view.");
        }
        if (resourceId != 0) {
            a2.G.setId(resourceId);
        }
        if (a2.G.getTag() == null) {
            a2.G.setTag((Object)string);
        }
        return a2.G;
    }
    
    Animation a(final k k, int n, final boolean b, final int n2) {
        final Animation a = k.a(n, b, k.I());
        Animation loadAnimation;
        if (a != null) {
            loadAnimation = a;
        }
        else if (k.I() == 0 || (loadAnimation = AnimationUtils.loadAnimation(this.n.g(), k.I())) == null) {
            if (n == 0) {
                return null;
            }
            n = b(n, b);
            if (n < 0) {
                return null;
            }
            switch (n) {
                default: {
                    n = n2;
                    if (n2 == 0) {
                        n = n2;
                        if (this.n.d()) {
                            n = this.n.e();
                        }
                    }
                    if (n == 0) {
                        return null;
                    }
                    return null;
                }
                case 1: {
                    return a(this.n.g(), 1.125f, 1.0f, 0.0f, 1.0f);
                }
                case 2: {
                    return a(this.n.g(), 1.0f, 0.975f, 1.0f, 0.0f);
                }
                case 3: {
                    return a(this.n.g(), 0.975f, 1.0f, 0.0f, 1.0f);
                }
                case 4: {
                    return a(this.n.g(), 1.0f, 1.075f, 1.0f, 0.0f);
                }
                case 5: {
                    return a(this.n.g(), 0.0f, 1.0f);
                }
                case 6: {
                    return a(this.n.g(), 1.0f, 0.0f);
                }
            }
        }
        return loadAnimation;
    }
    
    public void a(final int n, final f f) {
        synchronized (this) {
            if (this.j == null) {
                this.j = new ArrayList<f>();
            }
            int i;
            if (n < (i = this.j.size())) {
                if (android.support.v4.a.q.a) {
                    Log.v("FragmentManager", "Setting back stack index " + n + " to " + f);
                }
                this.j.set(n, f);
            }
            else {
                while (i < n) {
                    this.j.add(null);
                    if (this.k == null) {
                        this.k = new ArrayList<Integer>();
                    }
                    if (android.support.v4.a.q.a) {
                        Log.v("FragmentManager", "Adding available back stack index " + i);
                    }
                    this.k.add(i);
                    ++i;
                }
                if (android.support.v4.a.q.a) {
                    Log.v("FragmentManager", "Adding back stack index " + n + " with " + f);
                }
                this.j.add(f);
            }
        }
    }
    
    void a(int m, final boolean b) {
        if (this.n == null && m != 0) {
            throw new IllegalStateException("No activity");
        }
        if (b || m != this.m) {
            this.m = m;
            if (this.e != null) {
                boolean b2;
                if (this.f != null) {
                    final int size = this.f.size();
                    int n = 0;
                    m = 0;
                    while (true) {
                        b2 = (m != 0);
                        if (n >= size) {
                            break;
                        }
                        final k k = this.f.get(n);
                        this.d(k);
                        if (k.K != null) {
                            m |= (k.K.a() ? 1 : 0);
                        }
                        ++n;
                    }
                }
                else {
                    b2 = false;
                }
                final int size2 = this.e.size();
                int i = 0;
            Label_0214_Outer:
                while (i < size2) {
                    final k j = this.e.get(i);
                    while (true) {
                        Label_0268: {
                            if (j == null || (!j.l && !j.z) || j.O) {
                                break Label_0268;
                            }
                            this.d(j);
                            if (j.K == null) {
                                break Label_0268;
                            }
                            m = ((j.K.a() | b2) ? 1 : 0);
                            ++i;
                            b2 = (m != 0);
                            continue Label_0214_Outer;
                        }
                        m = (b2 ? 1 : 0);
                        continue;
                    }
                }
                if (!b2) {
                    this.b();
                }
                if (this.r && this.n != null && this.m == 5) {
                    this.n.c();
                    this.r = false;
                }
            }
        }
    }
    
    public void a(final Configuration configuration) {
        if (this.f != null) {
            for (int i = 0; i < this.f.size(); ++i) {
                final k k = this.f.get(i);
                if (k != null) {
                    k.a(configuration);
                }
            }
        }
    }
    
    public void a(final Bundle bundle, final String s, final k k) {
        if (k.e < 0) {
            this.a(new IllegalStateException("Fragment " + k + " is not currently in the FragmentManager"));
        }
        bundle.putInt(s, k.e);
    }
    
    void a(final Parcelable parcelable, final r r) {
        if (parcelable != null) {
            final s s = (s)parcelable;
            if (s.a != null) {
                List<r> b;
                if (r != null) {
                    final List<k> a = r.a();
                    b = r.b();
                    int size;
                    if (a != null) {
                        size = a.size();
                    }
                    else {
                        size = 0;
                    }
                    for (int i = 0; i < size; ++i) {
                        final k l = a.get(i);
                        if (android.support.v4.a.q.a) {
                            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + l);
                        }
                        final t t = s.a[l.e];
                        t.l = l;
                        l.d = null;
                        l.p = 0;
                        l.n = false;
                        l.k = false;
                        l.h = null;
                        if (t.k != null) {
                            t.k.setClassLoader(this.n.g().getClassLoader());
                            l.d = (SparseArray<Parcelable>)t.k.getSparseParcelableArray("android:view_state");
                            l.c = t.k;
                        }
                    }
                }
                else {
                    b = null;
                }
                this.e = new ArrayList<k>(s.a.length);
                if (this.g != null) {
                    this.g.clear();
                }
                for (int j = 0; j < s.a.length; ++j) {
                    final t t2 = s.a[j];
                    if (t2 != null) {
                        r r2;
                        if (b != null && j < b.size()) {
                            r2 = b.get(j);
                        }
                        else {
                            r2 = null;
                        }
                        final k a2 = t2.a(this.n, this.p, r2);
                        if (android.support.v4.a.q.a) {
                            Log.v("FragmentManager", "restoreAllState: active #" + j + ": " + a2);
                        }
                        this.e.add(a2);
                        t2.l = null;
                    }
                    else {
                        this.e.add(null);
                        if (this.g == null) {
                            this.g = new ArrayList<Integer>();
                        }
                        if (android.support.v4.a.q.a) {
                            Log.v("FragmentManager", "restoreAllState: avail #" + j);
                        }
                        this.g.add(j);
                    }
                }
                if (r != null) {
                    final List<k> a3 = r.a();
                    int size2;
                    if (a3 != null) {
                        size2 = a3.size();
                    }
                    else {
                        size2 = 0;
                    }
                    for (int k = 0; k < size2; ++k) {
                        final k m = a3.get(k);
                        if (m.i >= 0) {
                            if (m.i < this.e.size()) {
                                m.h = this.e.get(m.i);
                            }
                            else {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + m + " target no longer exists: " + m.i);
                                m.h = null;
                            }
                        }
                    }
                }
                if (s.b != null) {
                    this.f = new ArrayList<k>(s.b.length);
                    for (int n = 0; n < s.b.length; ++n) {
                        final k k2 = this.e.get(s.b[n]);
                        if (k2 == null) {
                            this.a(new IllegalStateException("No instantiated fragment for index #" + s.b[n]));
                        }
                        k2.k = true;
                        if (android.support.v4.a.q.a) {
                            Log.v("FragmentManager", "restoreAllState: added #" + n + ": " + k2);
                        }
                        if (this.f.contains(k2)) {
                            throw new IllegalStateException("Already added!");
                        }
                        this.f.add(k2);
                    }
                }
                else {
                    this.f = null;
                }
                if (s.c == null) {
                    this.h = null;
                    return;
                }
                this.h = new ArrayList<f>(s.c.length);
                for (int n2 = 0; n2 < s.c.length; ++n2) {
                    final f a4 = s.c[n2].a(this);
                    if (android.support.v4.a.q.a) {
                        Log.v("FragmentManager", "restoreAllState: back stack #" + n2 + " (index " + a4.n + "): " + a4);
                        final PrintWriter printWriter = new PrintWriter(new e("FragmentManager"));
                        a4.a("  ", printWriter, false);
                        printWriter.close();
                    }
                    this.h.add(a4);
                    if (a4.n >= 0) {
                        this.a(a4.n, a4);
                    }
                }
            }
        }
    }
    
    void a(final f f) {
        if (this.h == null) {
            this.h = new ArrayList<f>();
        }
        this.h.add(f);
        this.e();
    }
    
    public void a(final k k) {
        if (k.I) {
            if (!this.d) {
                k.I = false;
                this.a(k, this.m, 0, 0, false);
                return;
            }
            this.v = true;
        }
    }
    
    void a(final k k, int n, int n2, int n3, final boolean b) {
        final boolean b2 = true;
        int n4 = 0;
        Label_0031: {
            if (k.k) {
                n4 = n;
                if (!k.z) {
                    break Label_0031;
                }
            }
            if ((n4 = n) > 1) {
                n4 = 1;
            }
        }
        int b3 = n4;
        if (k.l && (b3 = n4) > k.b) {
            b3 = k.b;
        }
        n = b3;
        if (k.I) {
            n = b3;
            if (k.b < 4 && (n = b3) > 3) {
                n = 3;
            }
        }
        int b4 = 0;
        Label_0191: {
            if (k.b < n) {
                if (!k.m || k.n) {
                    if (k.N() != null) {
                        k.a((View)null);
                        this.a(k, k.O(), 0, 0, true);
                    }
                    n2 = n;
                    int n5 = n;
                    int n6 = n;
                    n3 = n;
                    q q;
                    ViewGroup f;
                    ViewGroup viewGroup;
                    String resourceName;
                    boolean o;
                    Label_0701:Label_1282_Outer:Label_1268_Outer:
                    while (true) {
                        Label_0656: {
                            while (true) {
                            Label_1252_Outer:
                                while (true) {
                                    Label_0589: {
                                        while (true) {
                                            Label_1054:Label_1114_Outer:
                                            while (true) {
                                            Label_1171_Outer:
                                                while (true) {
                                                    while (true) {
                                                        switch (k.b) {
                                                            default: {
                                                                b4 = n;
                                                                break Label_0191;
                                                            }
                                                            case 0: {
                                                                if (android.support.v4.a.q.a) {
                                                                    Log.v("FragmentManager", "moveto CREATED: " + k);
                                                                }
                                                                n3 = n;
                                                                if (k.c != null) {
                                                                    k.c.setClassLoader(this.n.g().getClassLoader());
                                                                    k.d = (SparseArray<Parcelable>)k.c.getSparseParcelableArray("android:view_state");
                                                                    k.h = this.a(k.c, "android:target_state");
                                                                    if (k.h != null) {
                                                                        k.j = k.c.getInt("android:target_req_state", 0);
                                                                    }
                                                                    k.J = k.c.getBoolean("android:user_visible_hint", true);
                                                                    n3 = n;
                                                                    if (!k.J) {
                                                                        k.I = true;
                                                                        if ((n3 = n) > 3) {
                                                                            n3 = 3;
                                                                        }
                                                                    }
                                                                }
                                                                k.r = this.n;
                                                                k.u = this.p;
                                                                if (this.p != null) {
                                                                    q = this.p.s;
                                                                }
                                                                else {
                                                                    q = this.n.i();
                                                                }
                                                                k.q = q;
                                                                this.a(k, this.n.g(), false);
                                                                k.E = false;
                                                                k.a(this.n.g());
                                                                if (!k.E) {
                                                                    throw new android.support.v4.a.ae("Fragment " + k + " did not call through to super.onAttach()");
                                                                }
                                                                if (k.u == null) {
                                                                    this.n.b(k);
                                                                    break;
                                                                }
                                                                break Label_1054;
                                                            }
                                                            case 1: {
                                                                if ((n5 = n2) <= 1) {
                                                                    break Label_1054;
                                                                }
                                                                if (android.support.v4.a.q.a) {
                                                                    Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + k);
                                                                }
                                                                if (k.m) {
                                                                    break Label_1054;
                                                                }
                                                                if (k.w == 0) {
                                                                    f = null;
                                                                    break Label_1171_Outer;
                                                                }
                                                                if (k.w == -1) {
                                                                    this.a(new IllegalArgumentException("Cannot create fragment " + k + " for a container view with no id"));
                                                                }
                                                                viewGroup = (ViewGroup)this.o.a(k.w);
                                                                if ((f = viewGroup) != null) {
                                                                    break Label_1171_Outer;
                                                                }
                                                                f = viewGroup;
                                                                if (!k.o) {
                                                                    break Label_1171_Outer;
                                                                }
                                                                break Label_1171_Outer;
                                                            }
                                                            case 2: {
                                                                break Label_1054;
                                                                Label_0971_Outer:Label_1048_Outer:
                                                                while (true) {
                                                                    Label_1323: {
                                                                        while (true) {
                                                                        Label_1317:
                                                                            while (true) {
                                                                            Label_1303:
                                                                                while (true) {
                                                                                    try {
                                                                                        resourceName = k.c().getResourceName(k.w);
                                                                                        this.a(new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(k.w) + " (" + resourceName + ") for fragment " + k));
                                                                                        f = viewGroup;
                                                                                        k.F = f;
                                                                                        k.G = k.b(k.b(k.c), f, k.c);
                                                                                        if (k.G == null) {
                                                                                            break Label_1323;
                                                                                        }
                                                                                        k.H = k.G;
                                                                                        if (Build$VERSION.SDK_INT < 11) {
                                                                                            break Label_1303;
                                                                                        }
                                                                                        ae.a(k.G, false);
                                                                                        if (f != null) {
                                                                                            f.addView(k.G);
                                                                                        }
                                                                                        if (k.y) {
                                                                                            k.G.setVisibility(8);
                                                                                        }
                                                                                        k.a(k.G, k.c);
                                                                                        this.a(k, k.G, k.c, false);
                                                                                        if (k.G.getVisibility() != 0 || k.F == null) {
                                                                                            break Label_1317;
                                                                                        }
                                                                                        o = b2;
                                                                                        k.O = o;
                                                                                        k.i(k.c);
                                                                                        this.b(k, k.c, false);
                                                                                        if (k.G != null) {
                                                                                            k.a(k.c);
                                                                                        }
                                                                                        k.c = null;
                                                                                        n5 = n2;
                                                                                        if ((n6 = n5) > 2) {
                                                                                            k.b = 3;
                                                                                            n6 = n5;
                                                                                        }
                                                                                        if ((n3 = n6) > 3) {
                                                                                            if (android.support.v4.a.q.a) {
                                                                                                Log.v("FragmentManager", "moveto STARTED: " + k);
                                                                                            }
                                                                                            k.z();
                                                                                            this.b(k, false);
                                                                                            n3 = n6;
                                                                                        }
                                                                                        if ((b4 = n3) > 4) {
                                                                                            if (android.support.v4.a.q.a) {
                                                                                                Log.v("FragmentManager", "moveto RESUMED: " + k);
                                                                                            }
                                                                                            k.A();
                                                                                            this.c(k, false);
                                                                                            k.c = null;
                                                                                            k.d = null;
                                                                                            b4 = n3;
                                                                                        }
                                                                                        break Label_0191;
                                                                                        k.H = null;
                                                                                        n2 = n3;
                                                                                        continue Label_0701;
                                                                                        k.u.a(k);
                                                                                        break Label_1054;
                                                                                        k.d(k.c);
                                                                                        k.b = 1;
                                                                                        break Label_0589;
                                                                                        k.G = (View)ab.a(k.G);
                                                                                        break Label_0656;
                                                                                    }
                                                                                    catch (Resources$NotFoundException ex) {
                                                                                        resourceName = "unknown";
                                                                                        continue Label_0971_Outer;
                                                                                    }
                                                                                    break;
                                                                                }
                                                                                k.G = (View)ab.a(k.G);
                                                                                continue Label_1048_Outer;
                                                                            }
                                                                            o = false;
                                                                            continue Label_1114_Outer;
                                                                        }
                                                                    }
                                                                    k.H = null;
                                                                    continue Label_1054;
                                                                }
                                                                break;
                                                            }
                                                            case 3: {
                                                                continue Label_1171_Outer;
                                                            }
                                                            case 4: {
                                                                continue Label_1282_Outer;
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            this.b(k, this.n.g(), false);
                                            if (k.B) {
                                                continue Label_1268_Outer;
                                            }
                                            break;
                                        }
                                        k.h(k.c);
                                        this.a(k, k.c, false);
                                    }
                                    k.B = false;
                                    n2 = n3;
                                    if (!k.m) {
                                        continue Label_0701;
                                    }
                                    k.G = k.b(k.b(k.c), null, k.c);
                                    if (k.G == null) {
                                        continue Label_1252_Outer;
                                    }
                                    break;
                                }
                                k.H = k.G;
                                if (Build$VERSION.SDK_INT < 11) {
                                    continue;
                                }
                                break;
                            }
                            ae.a(k.G, false);
                        }
                        if (k.y) {
                            k.G.setVisibility(8);
                        }
                        k.a(k.G, k.c);
                        this.a(k, k.G, k.c, false);
                        n2 = n3;
                        continue Label_0701;
                    }
                }
            }
            else {
                if (k.b <= (b4 = n)) {
                    break Label_0191;
                }
                switch (k.b) {
                    default: {
                        b4 = n;
                        break Label_0191;
                    }
                    case 3: {
                        if (n < 3) {
                            if (android.support.v4.a.q.a) {
                                Log.v("FragmentManager", "movefrom STOPPED: " + k);
                            }
                            k.E();
                        }
                    }
                    case 2: {
                        if (n < 2) {
                            if (android.support.v4.a.q.a) {
                                Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + k);
                            }
                            if (k.G != null && this.n.a(k) && k.d == null) {
                                this.l(k);
                            }
                            k.F();
                            this.f(k, false);
                            if (k.G != null && k.F != null) {
                                Animation a;
                                if (this.m > 0 && !this.t && k.G.getVisibility() == 0 && k.Q >= 0.0f) {
                                    a = this.a(k, n2, false, n3);
                                }
                                else {
                                    a = null;
                                }
                                k.Q = 0.0f;
                                if (a != null) {
                                    k.a(k.G);
                                    k.b(n);
                                    a.setAnimationListener((Animation$AnimationListener)new a(k.G, a) {
                                        @Override
                                        public void onAnimationEnd(final Animation animation) {
                                            super.onAnimationEnd(animation);
                                            if (k.N() != null) {
                                                k.a((View)null);
                                                android.support.v4.a.q.this.a(k, k.O(), 0, 0, false);
                                            }
                                        }
                                    });
                                    k.G.startAnimation(a);
                                }
                                k.F.removeView(k.G);
                            }
                            k.F = null;
                            k.G = null;
                            k.H = null;
                        }
                    }
                    case 1: {
                        if ((b4 = n) >= 1) {
                            break Label_0191;
                        }
                        if (this.t && k.N() != null) {
                            final View n7 = k.N();
                            k.a((View)null);
                            n7.clearAnimation();
                        }
                        if (k.N() != null) {
                            k.b(n);
                            b4 = 1;
                            break Label_0191;
                        }
                        if (android.support.v4.a.q.a) {
                            Log.v("FragmentManager", "movefrom CREATED: " + k);
                        }
                        if (!k.B) {
                            k.G();
                            this.g(k, false);
                        }
                        else {
                            k.b = 0;
                        }
                        k.H();
                        this.h(k, false);
                        b4 = n;
                        if (b) {
                            break Label_0191;
                        }
                        if (!k.B) {
                            this.f(k);
                            b4 = n;
                            break Label_0191;
                        }
                        k.r = null;
                        k.u = null;
                        k.q = null;
                        b4 = n;
                        break Label_0191;
                    }
                    case 5: {
                        if (n < 5) {
                            if (android.support.v4.a.q.a) {
                                Log.v("FragmentManager", "movefrom RESUMED: " + k);
                            }
                            k.C();
                            this.d(k, false);
                        }
                    }
                    case 4: {
                        if (n < 4) {
                            if (android.support.v4.a.q.a) {
                                Log.v("FragmentManager", "movefrom STARTED: " + k);
                            }
                            k.D();
                            this.e(k, false);
                        }
                    }
                }
            }
            return;
        }
        if (k.b != b4) {
            Log.w("FragmentManager", "moveToState: Fragment state for " + k + " not updated inline; " + "expected state " + b4 + " found " + k.b);
            k.b = b4;
        }
    }
    
    void a(final k k, final Context context, final boolean b) {
        if (this.p != null) {
            final p d = this.p.d();
            if (d instanceof q) {
                ((q)d).a(k, context, true);
            }
        }
        if (this.H != null) {
            for (final i<p.a, Boolean> i : this.H) {
                if (!b || i.b) {
                    i.a.a(this, k, context);
                }
            }
        }
    }
    
    void a(final k k, final Bundle bundle, final boolean b) {
        if (this.p != null) {
            final p d = this.p.d();
            if (d instanceof q) {
                ((q)d).a(k, bundle, true);
            }
        }
        if (this.H != null) {
            for (final i<p.a, Boolean> i : this.H) {
                if (!b || i.b) {
                    i.a.a(this, k, bundle);
                }
            }
        }
    }
    
    void a(final k k, final View view, final Bundle bundle, final boolean b) {
        if (this.p != null) {
            final p d = this.p.d();
            if (d instanceof q) {
                ((q)d).a(k, view, bundle, true);
            }
        }
        if (this.H != null) {
            for (final i<p.a, Boolean> i : this.H) {
                if (!b || i.b) {
                    i.a.a(this, k, view, bundle);
                }
            }
        }
    }
    
    public void a(final k k, final boolean b) {
        if (this.f == null) {
            this.f = new ArrayList<k>();
        }
        if (android.support.v4.a.q.a) {
            Log.v("FragmentManager", "add: " + k);
        }
        this.e(k);
        if (!k.z) {
            if (this.f.contains(k)) {
                throw new IllegalStateException("Fragment already added: " + k);
            }
            this.f.add(k);
            k.k = true;
            k.l = false;
            if (k.G == null) {
                k.P = false;
            }
            if (k.C && k.D) {
                this.r = true;
            }
            if (b) {
                this.b(k);
            }
        }
    }
    
    public void a(final o n, final m o, final k p3) {
        if (this.n != null) {
            throw new IllegalStateException("Already attached");
        }
        this.n = n;
        this.o = o;
        this.p = p3;
    }
    
    @Override
    public void a(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        final int n = 0;
        final String string = s + "    ";
        if (this.e != null) {
            final int size = this.e.size();
            if (size > 0) {
                printWriter.print(s);
                printWriter.print("Active Fragments in ");
                printWriter.print(Integer.toHexString(System.identityHashCode(this)));
                printWriter.println(":");
                for (int i = 0; i < size; ++i) {
                    final k k = this.e.get(i);
                    printWriter.print(s);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(k);
                    if (k != null) {
                        k.a(string, fileDescriptor, printWriter, array);
                    }
                }
            }
        }
        if (this.f != null) {
            final int size2 = this.f.size();
            if (size2 > 0) {
                printWriter.print(s);
                printWriter.println("Added Fragments:");
                for (int j = 0; j < size2; ++j) {
                    final k l = this.f.get(j);
                    printWriter.print(s);
                    printWriter.print("  #");
                    printWriter.print(j);
                    printWriter.print(": ");
                    printWriter.println(l.toString());
                }
            }
        }
        if (this.i != null) {
            final int size3 = this.i.size();
            if (size3 > 0) {
                printWriter.print(s);
                printWriter.println("Fragments Created Menus:");
                for (int n2 = 0; n2 < size3; ++n2) {
                    final k m = this.i.get(n2);
                    printWriter.print(s);
                    printWriter.print("  #");
                    printWriter.print(n2);
                    printWriter.print(": ");
                    printWriter.println(m.toString());
                }
            }
        }
        if (this.h != null) {
            final int size4 = this.h.size();
            if (size4 > 0) {
                printWriter.print(s);
                printWriter.println("Back Stack:");
                for (int n3 = 0; n3 < size4; ++n3) {
                    final f f = this.h.get(n3);
                    printWriter.print(s);
                    printWriter.print("  #");
                    printWriter.print(n3);
                    printWriter.print(": ");
                    printWriter.println(f.toString());
                    f.a(string, fileDescriptor, printWriter, array);
                }
            }
        }
        synchronized (this) {
            if (this.j != null) {
                final int size5 = this.j.size();
                if (size5 > 0) {
                    printWriter.print(s);
                    printWriter.println("Back Stack Indices:");
                    for (int n4 = 0; n4 < size5; ++n4) {
                        final f f2 = this.j.get(n4);
                        printWriter.print(s);
                        printWriter.print("  #");
                        printWriter.print(n4);
                        printWriter.print(": ");
                        printWriter.println(f2);
                    }
                }
            }
            if (this.k != null && this.k.size() > 0) {
                printWriter.print(s);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.k.toArray()));
            }
            // monitorexit(this)
            if (this.c != null) {
                final int size6 = this.c.size();
                if (size6 > 0) {
                    printWriter.print(s);
                    printWriter.println("Pending Actions:");
                    for (int n5 = n; n5 < size6; ++n5) {
                        final c c = this.c.get(n5);
                        printWriter.print(s);
                        printWriter.print("  #");
                        printWriter.print(n5);
                        printWriter.print(": ");
                        printWriter.println(c);
                    }
                }
            }
        }
        final String s2;
        printWriter.print(s2);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(s2);
        printWriter.print("  mHost=");
        printWriter.println(this.n);
        printWriter.print(s2);
        printWriter.print("  mContainer=");
        printWriter.println(this.o);
        if (this.p != null) {
            printWriter.print(s2);
            printWriter.print("  mParent=");
            printWriter.println(this.p);
        }
        printWriter.print(s2);
        printWriter.print("  mCurState=");
        printWriter.print(this.m);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.s);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.t);
        if (this.r) {
            printWriter.print(s2);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.r);
        }
        if (this.u != null) {
            printWriter.print(s2);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.u);
        }
        if (this.g != null && this.g.size() > 0) {
            printWriter.print(s2);
            printWriter.print("  mAvailIndices: ");
            printWriter.println(Arrays.toString(this.g.toArray()));
        }
    }
    
    public void a(final boolean b) {
        if (this.f != null) {
            for (int i = this.f.size() - 1; i >= 0; --i) {
                final k k = this.f.get(i);
                if (k != null) {
                    k.d(b);
                }
            }
        }
    }
    
    @Override
    public boolean a() {
        this.t();
        return this.a(null, -1, 0);
    }
    
    boolean a(final int n) {
        return this.m >= n;
    }
    
    public boolean a(final Menu menu) {
        boolean b2;
        if (this.f != null) {
            int n = 0;
            boolean b = false;
            while (true) {
                b2 = b;
                if (n >= this.f.size()) {
                    break;
                }
                final k k = this.f.get(n);
                boolean b3 = b;
                if (k != null) {
                    b3 = b;
                    if (k.c(menu)) {
                        b3 = true;
                    }
                }
                ++n;
                b = b3;
            }
        }
        else {
            b2 = false;
        }
        return b2;
    }
    
    public boolean a(final Menu menu, final MenuInflater menuInflater) {
        final int n = 0;
        ArrayList<k> i = null;
        ArrayList<k> list = null;
        boolean b2;
        if (this.f != null) {
            int n2 = 0;
            boolean b = false;
            while (true) {
                i = list;
                b2 = b;
                if (n2 >= this.f.size()) {
                    break;
                }
                final k k = this.f.get(n2);
                ArrayList<k> list2 = list;
                boolean b3 = b;
                if (k != null) {
                    list2 = list;
                    b3 = b;
                    if (k.b(menu, menuInflater)) {
                        b3 = true;
                        if ((list2 = list) == null) {
                            list2 = new ArrayList<k>();
                        }
                        list2.add(k);
                    }
                }
                ++n2;
                b = b3;
                list = list2;
            }
        }
        else {
            b2 = false;
        }
        if (this.i != null) {
            for (int j = n; j < this.i.size(); ++j) {
                final k l = this.i.get(j);
                if (i == null || !i.contains(l)) {
                    l.o();
                }
            }
        }
        this.i = i;
        return b2;
    }
    
    public boolean a(final MenuItem menuItem) {
        boolean b = false;
        if (this.f != null) {
            int n = 0;
            while (true) {
                b = b;
                if (n >= this.f.size()) {
                    break;
                }
                final k k = this.f.get(n);
                if (k != null && k.c(menuItem)) {
                    b = true;
                    break;
                }
                ++n;
            }
        }
        return b;
    }
    
    boolean a(final ArrayList<f> list, final ArrayList<Boolean> list2, final String s, int i, int n) {
        if (this.h == null) {
            return false;
        }
        if (s == null && i < 0 && (n & 0x1) == 0x0) {
            i = this.h.size() - 1;
            if (i < 0) {
                return false;
            }
            list.add(this.h.remove(i));
            list2.add(true);
        }
        else {
            int n2 = -1;
            if (s != null || i >= 0) {
                int j;
                for (j = this.h.size() - 1; j >= 0; --j) {
                    final f f = this.h.get(j);
                    if ((s != null && s.equals(f.c())) || (i >= 0 && i == f.n)) {
                        break;
                    }
                }
                if (j < 0) {
                    return false;
                }
                n2 = j;
                if ((n & 0x1) != 0x0) {
                    n = j - 1;
                    while (true) {
                        n2 = n;
                        if (n < 0) {
                            break;
                        }
                        final f f2 = this.h.get(n);
                        if (s == null || !s.equals(f2.c())) {
                            n2 = n;
                            if (i < 0) {
                                break;
                            }
                            n2 = n;
                            if (i != f2.n) {
                                break;
                            }
                        }
                        --n;
                    }
                }
            }
            if (n2 == this.h.size() - 1) {
                return false;
            }
            for (i = this.h.size() - 1; i > n2; --i) {
                list.add(this.h.remove(i));
                list2.add(true);
            }
        }
        return true;
    }
    
    public k b(final int n) {
        if (this.f != null) {
            for (int i = this.f.size() - 1; i >= 0; --i) {
                final k k = this.f.get(i);
                if (k != null && k.v == n) {
                    return k;
                }
            }
        }
        Label_0054: {
            break Label_0054;
        }
        if (this.e != null) {
            for (int j = this.e.size() - 1; j >= 0; --j) {
                final k l = this.e.get(j);
                if (l != null) {
                    final k k = l;
                    if (l.v == n) {
                        return k;
                    }
                }
            }
        }
        return null;
    }
    
    public k b(final String s) {
        if (this.e != null && s != null) {
            for (int i = this.e.size() - 1; i >= 0; --i) {
                final k k = this.e.get(i);
                if (k != null) {
                    final k a = k.a(s);
                    if (a != null) {
                        return a;
                    }
                }
            }
        }
        return null;
    }
    
    void b() {
        if (this.e != null) {
            for (int i = 0; i < this.e.size(); ++i) {
                final k k = this.e.get(i);
                if (k != null) {
                    this.a(k);
                }
            }
        }
    }
    
    void b(final k k) {
        this.a(k, this.m, 0, 0, false);
    }
    
    void b(final k k, final Context context, final boolean b) {
        if (this.p != null) {
            final p d = this.p.d();
            if (d instanceof q) {
                ((q)d).b(k, context, true);
            }
        }
        if (this.H != null) {
            for (final i<p.a, Boolean> i : this.H) {
                if (!b || i.b) {
                    i.a.b(this, k, context);
                }
            }
        }
    }
    
    void b(final k k, final Bundle bundle, final boolean b) {
        if (this.p != null) {
            final p d = this.p.d();
            if (d instanceof q) {
                ((q)d).b(k, bundle, true);
            }
        }
        if (this.H != null) {
            for (final i<p.a, Boolean> i : this.H) {
                if (!b || i.b) {
                    i.a.b(this, k, bundle);
                }
            }
        }
    }
    
    void b(final k k, final boolean b) {
        if (this.p != null) {
            final p d = this.p.d();
            if (d instanceof q) {
                ((q)d).b(k, true);
            }
        }
        if (this.H != null) {
            for (final i<p.a, Boolean> i : this.H) {
                if (!b || i.b) {
                    i.a.a(this, k);
                }
            }
        }
    }
    
    public void b(final Menu menu) {
        if (this.f != null) {
            for (int i = 0; i < this.f.size(); ++i) {
                final k k = this.f.get(i);
                if (k != null) {
                    k.d(menu);
                }
            }
        }
    }
    
    public void b(final boolean b) {
        if (this.f != null) {
            for (int i = this.f.size() - 1; i >= 0; --i) {
                final k k = this.f.get(i);
                if (k != null) {
                    k.e(b);
                }
            }
        }
    }
    
    public boolean b(final MenuItem menuItem) {
        boolean b = false;
        if (this.f != null) {
            int n = 0;
            while (true) {
                b = b;
                if (n >= this.f.size()) {
                    break;
                }
                final k k = this.f.get(n);
                if (k != null && k.d(menuItem)) {
                    b = true;
                    break;
                }
                ++n;
            }
        }
        return b;
    }
    
    public void c(final int n) {
        synchronized (this) {
            this.j.set(n, null);
            if (this.k == null) {
                this.k = new ArrayList<Integer>();
            }
            if (android.support.v4.a.q.a) {
                Log.v("FragmentManager", "Freeing back stack index " + n);
            }
            this.k.add(n);
        }
    }
    
    void c(final k k) {
        if (k.G != null) {
            final Animation a = this.a(k, k.J(), !k.y, k.K());
            if (a != null) {
                this.b(k.G, a);
                k.G.startAnimation(a);
                this.b(k.G, a);
                a.start();
            }
            int visibility;
            if (k.y && !k.Q()) {
                visibility = 8;
            }
            else {
                visibility = 0;
            }
            k.G.setVisibility(visibility);
            if (k.Q()) {
                k.f(false);
            }
        }
        if (k.k && k.C && k.D) {
            this.r = true;
        }
        k.P = false;
        k.a(k.y);
    }
    
    void c(final k k, final Bundle bundle, final boolean b) {
        if (this.p != null) {
            final p d = this.p.d();
            if (d instanceof q) {
                ((q)d).c(k, bundle, true);
            }
        }
        if (this.H != null) {
            for (final i<p.a, Boolean> i : this.H) {
                if (!b || i.b) {
                    i.a.c(this, k, bundle);
                }
            }
        }
    }
    
    void c(final k k, final boolean b) {
        if (this.p != null) {
            final p d = this.p.d();
            if (d instanceof q) {
                ((q)d).c(k, true);
            }
        }
        if (this.H != null) {
            for (final i<p.a, Boolean> i : this.H) {
                if (!b || i.b) {
                    i.a.b(this, k);
                }
            }
        }
    }
    
    public boolean c() {
        this.c(true);
        boolean b = false;
        while (this.c(this.w, this.x)) {
            this.d = true;
            try {
                this.b(this.w, this.x);
                this.v();
                b = true;
                continue;
            }
            finally {
                this.v();
            }
            break;
        }
        this.d();
        return b;
    }
    
    void d() {
        if (this.v) {
            int i = 0;
            boolean b = false;
            while (i < this.e.size()) {
                final k k = this.e.get(i);
                boolean b2 = b;
                if (k != null) {
                    b2 = b;
                    if (k.K != null) {
                        b2 = (b | k.K.a());
                    }
                }
                ++i;
                b = b2;
            }
            if (!b) {
                this.v = false;
                this.b();
            }
        }
    }
    
    void d(final k k) {
        if (k != null) {
            int n2;
            final int n = n2 = this.m;
            if (k.l) {
                if (k.a()) {
                    n2 = Math.min(n, 1);
                }
                else {
                    n2 = Math.min(n, 0);
                }
            }
            this.a(k, n2, k.J(), k.K(), false);
            if (k.G != null) {
                final k n3 = this.n(k);
                if (n3 != null) {
                    final View g = n3.G;
                    final ViewGroup f = k.F;
                    final int indexOfChild = f.indexOfChild(g);
                    final int indexOfChild2 = f.indexOfChild(k.G);
                    if (indexOfChild2 < indexOfChild) {
                        f.removeViewAt(indexOfChild2);
                        f.addView(k.G, indexOfChild);
                    }
                }
                if (k.O && k.F != null) {
                    if (Build$VERSION.SDK_INT < 11) {
                        k.G.setVisibility(0);
                    }
                    else if (k.Q > 0.0f) {
                        k.G.setAlpha(k.Q);
                    }
                    k.Q = 0.0f;
                    k.O = false;
                    final Animation a = this.a(k, k.J(), true, k.K());
                    if (a != null) {
                        this.b(k.G, a);
                        k.G.startAnimation(a);
                    }
                }
            }
            if (k.P) {
                this.c(k);
            }
        }
    }
    
    void d(final k k, final boolean b) {
        if (this.p != null) {
            final p d = this.p.d();
            if (d instanceof q) {
                ((q)d).d(k, true);
            }
        }
        if (this.H != null) {
            for (final i<p.a, Boolean> i : this.H) {
                if (!b || i.b) {
                    i.a.c(this, k);
                }
            }
        }
    }
    
    void e() {
        if (this.l != null) {
            for (int i = 0; i < this.l.size(); ++i) {
                this.l.get(i).a();
            }
        }
    }
    
    void e(final k k) {
        if (k.e < 0) {
            if (this.g == null || this.g.size() <= 0) {
                if (this.e == null) {
                    this.e = new ArrayList<k>();
                }
                k.a(this.e.size(), this.p);
                this.e.add(k);
            }
            else {
                k.a(this.g.remove(this.g.size() - 1), this.p);
                this.e.set(k.e, k);
            }
            if (android.support.v4.a.q.a) {
                Log.v("FragmentManager", "Allocated fragment index " + k);
            }
        }
    }
    
    void e(final k k, final boolean b) {
        if (this.p != null) {
            final p d = this.p.d();
            if (d instanceof q) {
                ((q)d).e(k, true);
            }
        }
        if (this.H != null) {
            for (final i<p.a, Boolean> i : this.H) {
                if (!b || i.b) {
                    i.a.d(this, k);
                }
            }
        }
    }
    
    r f() {
        List<r> list3;
        List<k> list4;
        if (this.e != null) {
            int n = 0;
            ArrayList<r> list = null;
            ArrayList<k> list2 = null;
            while (true) {
                list3 = list;
                list4 = list2;
                if (n >= this.e.size()) {
                    break;
                }
                final k k = this.e.get(n);
                ArrayList<r> list5 = list;
                ArrayList<k> list6 = list2;
                Label_0284: {
                    if (k != null) {
                        ArrayList<k> list7 = list2;
                        if (k.A) {
                            ArrayList<k> list8;
                            if ((list8 = list2) == null) {
                                list8 = new ArrayList<k>();
                            }
                            list8.add(k);
                            k.B = true;
                            int e;
                            if (k.h != null) {
                                e = k.h.e;
                            }
                            else {
                                e = -1;
                            }
                            k.i = e;
                            list7 = list8;
                            if (android.support.v4.a.q.a) {
                                Log.v("FragmentManager", "retainNonConfig: keeping retained " + k);
                                list7 = list8;
                            }
                        }
                        while (true) {
                            Label_0328: {
                                if (k.s == null) {
                                    break Label_0328;
                                }
                                final r f = k.s.f();
                                if (f == null) {
                                    break Label_0328;
                                }
                                ArrayList<r> list10;
                                if (list == null) {
                                    final ArrayList<r> list9 = new ArrayList<r>();
                                    int n2 = 0;
                                    while (true) {
                                        list10 = list9;
                                        if (n2 >= n) {
                                            break;
                                        }
                                        list9.add(null);
                                        ++n2;
                                    }
                                }
                                else {
                                    list10 = list;
                                }
                                list10.add(f);
                                final int n3 = 1;
                                list = list10;
                                list5 = list;
                                list6 = list7;
                                if (list == null) {
                                    break Label_0284;
                                }
                                list5 = list;
                                list6 = list7;
                                if (n3 == 0) {
                                    list.add(null);
                                    list6 = list7;
                                    list5 = list;
                                }
                                break Label_0284;
                            }
                            final int n3 = 0;
                            continue;
                        }
                    }
                }
                ++n;
                list2 = list6;
                list = list5;
            }
        }
        else {
            list3 = null;
            list4 = null;
        }
        if (list4 == null && list3 == null) {
            return null;
        }
        return new r(list4, list3);
    }
    
    void f(final k k) {
        if (k.e < 0) {
            return;
        }
        if (android.support.v4.a.q.a) {
            Log.v("FragmentManager", "Freeing fragment index " + k);
        }
        this.e.set(k.e, null);
        if (this.g == null) {
            this.g = new ArrayList<Integer>();
        }
        this.g.add(k.e);
        this.n.a(k.f);
        k.m();
    }
    
    void f(final k k, final boolean b) {
        if (this.p != null) {
            final p d = this.p.d();
            if (d instanceof q) {
                ((q)d).f(k, true);
            }
        }
        if (this.H != null) {
            for (final i<p.a, Boolean> i : this.H) {
                if (!b || i.b) {
                    i.a.e(this, k);
                }
            }
        }
    }
    
    Parcelable g() {
        final g[] array = null;
        this.w();
        this.x();
        this.c();
        if (android.support.v4.a.q.b) {
            this.s = true;
        }
        if (this.e != null && this.e.size() > 0) {
            final int size = this.e.size();
            final t[] a = new t[size];
            int i = 0;
            boolean b = false;
            while (i < size) {
                final k k = this.e.get(i);
                if (k != null) {
                    if (k.e < 0) {
                        this.a(new IllegalStateException("Failure saving state: active " + k + " has cleared index: " + k.e));
                    }
                    final t t = new t(k);
                    a[i] = t;
                    if (k.b > 0 && t.k == null) {
                        t.k = this.m(k);
                        if (k.h != null) {
                            if (k.h.e < 0) {
                                this.a(new IllegalStateException("Failure saving state: " + k + " has target not in fragment manager: " + k.h));
                            }
                            if (t.k == null) {
                                t.k = new Bundle();
                            }
                            this.a(t.k, "android:target_state", k.h);
                            if (k.j != 0) {
                                t.k.putInt("android:target_req_state", k.j);
                            }
                        }
                    }
                    else {
                        t.k = k.c;
                    }
                    if (android.support.v4.a.q.a) {
                        Log.v("FragmentManager", "Saved state of " + k + ": " + t.k);
                    }
                    b = true;
                }
                ++i;
            }
            if (b) {
                int[] b2 = null;
                Label_0574: {
                    if (this.f != null) {
                        final int size2 = this.f.size();
                        if (size2 > 0) {
                            final int[] array2 = new int[size2];
                            int n = 0;
                            while (true) {
                                b2 = array2;
                                if (n >= size2) {
                                    break Label_0574;
                                }
                                array2[n] = this.f.get(n).e;
                                if (array2[n] < 0) {
                                    this.a(new IllegalStateException("Failure saving state: active " + this.f.get(n) + " has cleared index: " + array2[n]));
                                }
                                if (android.support.v4.a.q.a) {
                                    Log.v("FragmentManager", "saveAllState: adding fragment #" + n + ": " + this.f.get(n));
                                }
                                ++n;
                            }
                        }
                    }
                    b2 = null;
                }
                g[] c = array;
                if (this.h != null) {
                    final int size3 = this.h.size();
                    c = array;
                    if (size3 > 0) {
                        final g[] array3 = new g[size3];
                        int n2 = 0;
                        while (true) {
                            c = array3;
                            if (n2 >= size3) {
                                break;
                            }
                            array3[n2] = new g(this.h.get(n2));
                            if (android.support.v4.a.q.a) {
                                Log.v("FragmentManager", "saveAllState: adding back stack #" + n2 + ": " + this.h.get(n2));
                            }
                            ++n2;
                        }
                    }
                }
                final s s = new s();
                s.a = a;
                s.b = b2;
                s.c = c;
                return (Parcelable)s;
            }
            if (android.support.v4.a.q.a) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
                return null;
            }
        }
        return null;
    }
    
    public void g(final k k) {
        if (android.support.v4.a.q.a) {
            Log.v("FragmentManager", "remove: " + k + " nesting=" + k.p);
        }
        boolean b;
        if (!k.a()) {
            b = true;
        }
        else {
            b = false;
        }
        if (!k.z || b) {
            if (this.f != null) {
                this.f.remove(k);
            }
            if (k.C && k.D) {
                this.r = true;
            }
            k.k = false;
            k.l = true;
        }
    }
    
    void g(final k k, final boolean b) {
        if (this.p != null) {
            final p d = this.p.d();
            if (d instanceof q) {
                ((q)d).g(k, true);
            }
        }
        if (this.H != null) {
            for (final i<p.a, Boolean> i : this.H) {
                if (!b || i.b) {
                    i.a.f(this, k);
                }
            }
        }
    }
    
    public void h() {
        this.s = false;
    }
    
    public void h(final k k) {
        boolean p = true;
        if (android.support.v4.a.q.a) {
            Log.v("FragmentManager", "hide: " + k);
        }
        if (!k.y) {
            k.y = true;
            if (k.P) {
                p = false;
            }
            k.P = p;
        }
    }
    
    void h(final k k, final boolean b) {
        if (this.p != null) {
            final p d = this.p.d();
            if (d instanceof q) {
                ((q)d).h(k, true);
            }
        }
        if (this.H != null) {
            for (final i<p.a, Boolean> i : this.H) {
                if (!b || i.b) {
                    i.a.g(this, k);
                }
            }
        }
    }
    
    public void i() {
        this.s = false;
        this.d = true;
        this.a(1, false);
        this.d = false;
    }
    
    public void i(final k k) {
        boolean p = false;
        if (android.support.v4.a.q.a) {
            Log.v("FragmentManager", "show: " + k);
        }
        if (k.y) {
            k.y = false;
            if (!k.P) {
                p = true;
            }
            k.P = p;
        }
    }
    
    public void j() {
        this.s = false;
        this.d = true;
        this.a(2, false);
        this.d = false;
    }
    
    public void j(final k k) {
        if (android.support.v4.a.q.a) {
            Log.v("FragmentManager", "detach: " + k);
        }
        if (!k.z) {
            k.z = true;
            if (k.k) {
                if (this.f != null) {
                    if (android.support.v4.a.q.a) {
                        Log.v("FragmentManager", "remove from detach: " + k);
                    }
                    this.f.remove(k);
                }
                if (k.C && k.D) {
                    this.r = true;
                }
                k.k = false;
            }
        }
    }
    
    public void k() {
        this.s = false;
        this.d = true;
        this.a(4, false);
        this.d = false;
    }
    
    public void k(final k k) {
        if (android.support.v4.a.q.a) {
            Log.v("FragmentManager", "attach: " + k);
        }
        if (k.z) {
            k.z = false;
            if (!k.k) {
                if (this.f == null) {
                    this.f = new ArrayList<k>();
                }
                if (this.f.contains(k)) {
                    throw new IllegalStateException("Fragment already added: " + k);
                }
                if (android.support.v4.a.q.a) {
                    Log.v("FragmentManager", "add from attach: " + k);
                }
                this.f.add(k);
                k.k = true;
                if (k.C && k.D) {
                    this.r = true;
                }
            }
        }
    }
    
    public void l() {
        this.s = false;
        this.d = true;
        this.a(5, false);
        this.d = false;
    }
    
    void l(final k k) {
        if (k.H != null) {
            if (this.A == null) {
                this.A = (SparseArray<Parcelable>)new SparseArray();
            }
            else {
                this.A.clear();
            }
            k.H.saveHierarchyState((SparseArray)this.A);
            if (this.A.size() > 0) {
                k.d = this.A;
                this.A = null;
            }
        }
    }
    
    Bundle m(final k k) {
        if (this.z == null) {
            this.z = new Bundle();
        }
        k.j(this.z);
        this.c(k, this.z, false);
        Bundle z;
        if (!this.z.isEmpty()) {
            z = this.z;
            this.z = null;
        }
        else {
            z = null;
        }
        if (k.G != null) {
            this.l(k);
        }
        Bundle bundle = z;
        if (k.d != null) {
            if ((bundle = z) == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", (SparseArray)k.d);
        }
        Bundle bundle2 = bundle;
        if (!k.J) {
            if ((bundle2 = bundle) == null) {
                bundle2 = new Bundle();
            }
            bundle2.putBoolean("android:user_visible_hint", k.J);
        }
        return bundle2;
    }
    
    public void m() {
        this.d = true;
        this.a(4, false);
        this.d = false;
    }
    
    public void n() {
        this.s = true;
        this.d = true;
        this.a(3, false);
        this.d = false;
    }
    
    public void o() {
        this.d = true;
        this.a(2, false);
        this.d = false;
    }
    
    public void p() {
        this.d = true;
        this.a(1, false);
        this.d = false;
    }
    
    public void q() {
        this.t = true;
        this.c();
        this.d = true;
        this.a(0, false);
        this.d = false;
        this.n = null;
        this.o = null;
        this.p = null;
    }
    
    public void r() {
        if (this.f != null) {
            for (int i = 0; i < this.f.size(); ++i) {
                final k k = this.f.get(i);
                if (k != null) {
                    k.B();
                }
            }
        }
    }
    
    n s() {
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        if (this.p != null) {
            android.support.v4.f.d.a(this.p, sb);
        }
        else {
            android.support.v4.f.d.a(this.n, sb);
        }
        sb.append("}}");
        return sb.toString();
    }
    
    static class a implements Animation$AnimationListener
    {
        private Animation$AnimationListener a;
        private boolean b;
        View c;
        
        public a(final View c, final Animation animation) {
            if (c == null || animation == null) {
                return;
            }
            this.c = c;
        }
        
        public a(final View c, final Animation animation, final Animation$AnimationListener a) {
            if (c == null || animation == null) {
                return;
            }
            this.a = a;
            this.c = c;
            this.b = true;
        }
        
        public void onAnimationEnd(final Animation animation) {
            if (this.c != null && this.b) {
                if (ae.r(this.c) || android.support.v4.e.c.a()) {
                    this.c.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ae.a(android.support.v4.a.q.a.this.c, 0, null);
                        }
                    });
                }
                else {
                    ae.a(this.c, 0, null);
                }
            }
            if (this.a != null) {
                this.a.onAnimationEnd(animation);
            }
        }
        
        public void onAnimationRepeat(final Animation animation) {
            if (this.a != null) {
                this.a.onAnimationRepeat(animation);
            }
        }
        
        public void onAnimationStart(final Animation animation) {
            if (this.a != null) {
                this.a.onAnimationStart(animation);
            }
        }
    }
    
    static class b
    {
        public static final int[] a;
        
        static {
            a = new int[] { 16842755, 16842960, 16842961 };
        }
    }
    
    interface c
    {
        boolean a(final ArrayList<f> p0, final ArrayList<Boolean> p1);
    }
    
    static class d implements k.c
    {
        private final boolean a;
        private final f b;
        private int c;
        
        d(final f b, final boolean a) {
            this.a = a;
            this.b = b;
        }
        
        @Override
        public void a() {
            --this.c;
            if (this.c != 0) {
                return;
            }
            this.b.b.u();
        }
        
        @Override
        public void b() {
            ++this.c;
        }
        
        public boolean c() {
            return this.c == 0;
        }
        
        public void d() {
            boolean b = false;
            boolean b2;
            if (this.c > 0) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            final q b3 = this.b.b;
            for (int size = b3.f.size(), i = 0; i < size; ++i) {
                final k k = b3.f.get(i);
                k.a((k.c)null);
                if (b2 && k.P()) {
                    k.x();
                }
            }
            final q b4 = this.b.b;
            final f b5 = this.b;
            final boolean a = this.a;
            if (!b2) {
                b = true;
            }
            b4.a(b5, a, b, true);
        }
        
        public void e() {
            this.b.b.a(this.b, this.a, false, false);
        }
    }
}
