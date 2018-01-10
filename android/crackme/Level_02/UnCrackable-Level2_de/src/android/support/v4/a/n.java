// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.Menu;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.support.v4.f.j;
import android.os.Parcelable;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;

public class n
{
    private final o<?> a;
    
    private n(final o<?> a) {
        this.a = a;
    }
    
    public static final n a(final o<?> o) {
        return new n(o);
    }
    
    public k a(final String s) {
        return this.a.d.b(s);
    }
    
    public p a() {
        return this.a.i();
    }
    
    public View a(final View view, final String s, final Context context, final AttributeSet set) {
        return this.a.d.a(view, s, context, set);
    }
    
    public void a(final Configuration configuration) {
        this.a.d.a(configuration);
    }
    
    public void a(final Parcelable parcelable, final r r) {
        this.a.d.a(parcelable, r);
    }
    
    public void a(final k k) {
        this.a.d.a(this.a, this.a, k);
    }
    
    public void a(final j<String, x> j) {
        this.a.a(j);
    }
    
    public void a(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        this.a.b(s, fileDescriptor, printWriter, array);
    }
    
    public void a(final boolean b) {
        this.a.d.a(b);
    }
    
    public boolean a(final Menu menu) {
        return this.a.d.a(menu);
    }
    
    public boolean a(final Menu menu, final MenuInflater menuInflater) {
        return this.a.d.a(menu, menuInflater);
    }
    
    public boolean a(final MenuItem menuItem) {
        return this.a.d.a(menuItem);
    }
    
    public void b() {
        this.a.d.h();
    }
    
    public void b(final Menu menu) {
        this.a.d.b(menu);
    }
    
    public void b(final boolean b) {
        this.a.d.b(b);
    }
    
    public boolean b(final MenuItem menuItem) {
        return this.a.d.b(menuItem);
    }
    
    public Parcelable c() {
        return this.a.d.g();
    }
    
    public void c(final boolean b) {
        this.a.a(b);
    }
    
    public r d() {
        return this.a.d.f();
    }
    
    public void e() {
        this.a.d.i();
    }
    
    public void f() {
        this.a.d.j();
    }
    
    public void g() {
        this.a.d.k();
    }
    
    public void h() {
        this.a.d.l();
    }
    
    public void i() {
        this.a.d.m();
    }
    
    public void j() {
        this.a.d.n();
    }
    
    public void k() {
        this.a.d.o();
    }
    
    public void l() {
        this.a.d.q();
    }
    
    public void m() {
        this.a.d.r();
    }
    
    public boolean n() {
        return this.a.d.c();
    }
    
    public void o() {
        this.a.k();
    }
    
    public void p() {
        this.a.l();
    }
    
    public void q() {
        this.a.m();
    }
    
    public j<String, x> r() {
        return this.a.n();
    }
}
