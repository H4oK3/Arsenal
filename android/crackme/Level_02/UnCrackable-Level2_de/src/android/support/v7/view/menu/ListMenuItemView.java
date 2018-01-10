// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.widget.CompoundButton;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.support.v4.g.ae;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.av;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ListMenuItemView extends LinearLayout implements a
{
    private j a;
    private ImageView b;
    private RadioButton c;
    private TextView d;
    private CheckBox e;
    private TextView f;
    private ImageView g;
    private Drawable h;
    private int i;
    private Context j;
    private boolean k;
    private Drawable l;
    private int m;
    private LayoutInflater n;
    private boolean o;
    
    public ListMenuItemView(final Context context, final AttributeSet set) {
        this(context, set, android.support.v7.a.a.a.listMenuViewStyle);
    }
    
    public ListMenuItemView(final Context j, final AttributeSet set, final int n) {
        super(j, set);
        final av a = av.a(this.getContext(), set, android.support.v7.a.a.j.MenuView, n, 0);
        this.h = a.a(android.support.v7.a.a.j.MenuView_android_itemBackground);
        this.i = a.g(android.support.v7.a.a.j.MenuView_android_itemTextAppearance, -1);
        this.k = a.a(android.support.v7.a.a.j.MenuView_preserveIconSpacing, false);
        this.j = j;
        this.l = a.a(android.support.v7.a.a.j.MenuView_subMenuArrow);
        a.a();
    }
    
    private void b() {
        this.addView((View)(this.b = (ImageView)this.getInflater().inflate(android.support.v7.a.a.g.abc_list_menu_item_icon, (ViewGroup)this, false)), 0);
    }
    
    private void c() {
        this.addView((View)(this.c = (RadioButton)this.getInflater().inflate(android.support.v7.a.a.g.abc_list_menu_item_radio, (ViewGroup)this, false)));
    }
    
    private void d() {
        this.addView((View)(this.e = (CheckBox)this.getInflater().inflate(android.support.v7.a.a.g.abc_list_menu_item_checkbox, (ViewGroup)this, false)));
    }
    
    private LayoutInflater getInflater() {
        if (this.n == null) {
            this.n = LayoutInflater.from(this.getContext());
        }
        return this.n;
    }
    
    private void setSubMenuArrowVisible(final boolean b) {
        if (this.g != null) {
            final ImageView g = this.g;
            int visibility;
            if (b) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            g.setVisibility(visibility);
        }
    }
    
    public void a(final j a, int n) {
        this.a = a;
        this.m = n;
        if (a.isVisible()) {
            n = 0;
        }
        else {
            n = 8;
        }
        this.setVisibility(n);
        this.setTitle(a.a(this));
        this.setCheckable(a.isCheckable());
        this.a(a.f(), a.d());
        this.setIcon(a.getIcon());
        this.setEnabled(a.isEnabled());
        this.setSubMenuArrowVisible(a.hasSubMenu());
    }
    
    public void a(final boolean b, final char c) {
        int visibility;
        if (b && this.a.f()) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        if (visibility == 0) {
            this.f.setText((CharSequence)this.a.e());
        }
        if (this.f.getVisibility() != visibility) {
            this.f.setVisibility(visibility);
        }
    }
    
    public boolean a() {
        return false;
    }
    
    public j getItemData() {
        return this.a;
    }
    
    protected void onFinishInflate() {
        super.onFinishInflate();
        ae.a((View)this, this.h);
        this.d = (TextView)this.findViewById(android.support.v7.a.a.f.title);
        if (this.i != -1) {
            this.d.setTextAppearance(this.j, this.i);
        }
        this.f = (TextView)this.findViewById(android.support.v7.a.a.f.shortcut);
        this.g = (ImageView)this.findViewById(android.support.v7.a.a.f.submenuarrow);
        if (this.g != null) {
            this.g.setImageDrawable(this.l);
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        if (this.b != null && this.k) {
            final ViewGroup$LayoutParams layoutParams = this.getLayoutParams();
            final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)this.b.getLayoutParams();
            if (layoutParams.height > 0 && linearLayout$LayoutParams.width <= 0) {
                linearLayout$LayoutParams.width = layoutParams.height;
            }
        }
        super.onMeasure(n, n2);
    }
    
    public void setCheckable(final boolean b) {
        if (b || this.c != null || this.e != null) {
            Object o;
            Object o2;
            if (this.a.g()) {
                if (this.c == null) {
                    this.c();
                }
                o = this.c;
                o2 = this.e;
            }
            else {
                if (this.e == null) {
                    this.d();
                }
                o = this.e;
                o2 = this.c;
            }
            if (b) {
                ((CompoundButton)o).setChecked(this.a.isChecked());
                int visibility;
                if (b) {
                    visibility = 0;
                }
                else {
                    visibility = 8;
                }
                if (((CompoundButton)o).getVisibility() != visibility) {
                    ((CompoundButton)o).setVisibility(visibility);
                }
                if (o2 != null && ((CompoundButton)o2).getVisibility() != 8) {
                    ((CompoundButton)o2).setVisibility(8);
                }
            }
            else {
                if (this.e != null) {
                    this.e.setVisibility(8);
                }
                if (this.c != null) {
                    this.c.setVisibility(8);
                }
            }
        }
    }
    
    public void setChecked(final boolean checked) {
        Object o;
        if (this.a.g()) {
            if (this.c == null) {
                this.c();
            }
            o = this.c;
        }
        else {
            if (this.e == null) {
                this.d();
            }
            o = this.e;
        }
        ((CompoundButton)o).setChecked(checked);
    }
    
    public void setForceShowIcon(final boolean b) {
        this.o = b;
        this.k = b;
    }
    
    public void setIcon(Drawable imageDrawable) {
        boolean b;
        if (this.a.i() || this.o) {
            b = true;
        }
        else {
            b = false;
        }
        if ((b || this.k) && (this.b != null || imageDrawable != null || this.k)) {
            if (this.b == null) {
                this.b();
            }
            if (imageDrawable == null && !this.k) {
                this.b.setVisibility(8);
                return;
            }
            final ImageView b2 = this.b;
            if (!b) {
                imageDrawable = null;
            }
            b2.setImageDrawable(imageDrawable);
            if (this.b.getVisibility() != 0) {
                this.b.setVisibility(0);
            }
        }
    }
    
    public void setTitle(final CharSequence text) {
        if (text != null) {
            this.d.setText(text);
            if (this.d.getVisibility() != 0) {
                this.d.setVisibility(0);
            }
        }
        else if (this.d.getVisibility() != 8) {
            this.d.setVisibility(8);
        }
    }
}
