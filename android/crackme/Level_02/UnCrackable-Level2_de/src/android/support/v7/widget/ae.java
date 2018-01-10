// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.widget.ListView;
import android.support.v4.g.t;
import android.view.MotionEvent;
import android.os.Build$VERSION;
import android.view.View;
import android.util.AttributeSet;
import android.support.v7.a.a;
import android.content.Context;
import android.support.v4.widget.k;
import android.support.v4.g.as;

class ae extends aj
{
    private boolean g;
    private boolean h;
    private boolean i;
    private as j;
    private k k;
    
    public ae(final Context context, final boolean h) {
        super(context, null, android.support.v7.a.a.a.dropDownListViewStyle);
        this.h = h;
        this.setCacheColorHint(0);
    }
    
    private void a(final View view, final int n) {
        this.performItemClick(view, n, this.getItemIdAtPosition(n));
    }
    
    private void a(final View view, final int f, final float n, final float n2) {
        this.i = true;
        if (Build$VERSION.SDK_INT >= 21) {
            this.drawableHotspotChanged(n, n2);
        }
        if (!this.isPressed()) {
            this.setPressed(true);
        }
        this.layoutChildren();
        if (this.f != -1) {
            final View child = this.getChildAt(this.f - this.getFirstVisiblePosition());
            if (child != null && child != view && child.isPressed()) {
                child.setPressed(false);
            }
        }
        this.f = f;
        final float n3 = view.getLeft();
        final float n4 = view.getTop();
        if (Build$VERSION.SDK_INT >= 21) {
            view.drawableHotspotChanged(n - n3, n2 - n4);
        }
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        this.a(f, view, n, n2);
        this.setSelectorEnabled(false);
        this.refreshDrawableState();
    }
    
    private void d() {
        this.setPressed(this.i = false);
        this.drawableStateChanged();
        final View child = this.getChildAt(this.f - this.getFirstVisiblePosition());
        if (child != null) {
            child.setPressed(false);
        }
        if (this.j != null) {
            this.j.b();
            this.j = null;
        }
    }
    
    @Override
    protected boolean a() {
        return this.i || super.a();
    }
    
    public boolean a(final MotionEvent motionEvent, int n) {
        final int a = t.a(motionEvent);
    Label_0037_Outer:
        while (true) {
            int n2;
            int pointToPosition;
            while (true) {
                Label_0107: {
                    boolean b = false;
                    switch (a) {
                        default: {
                            n = 0;
                            b = true;
                            break;
                        }
                        case 3: {
                            n = 0;
                            b = false;
                            break;
                        }
                        case 1: {
                            b = false;
                            break Label_0107;
                        }
                        case 2: {
                            b = true;
                            break Label_0107;
                        }
                    }
                    if (!b || n != 0) {
                        this.d();
                    }
                    if (b) {
                        if (this.k == null) {
                            this.k = new k(this);
                        }
                        this.k.a(true);
                        this.k.onTouch((View)this, motionEvent);
                    }
                    else if (this.k != null) {
                        this.k.a(false);
                        return b;
                    }
                    return b;
                }
                final int pointerIndex = motionEvent.findPointerIndex(n);
                if (pointerIndex < 0) {
                    n = 0;
                    final boolean b = false;
                    continue;
                }
                n = (int)motionEvent.getX(pointerIndex);
                n2 = (int)motionEvent.getY(pointerIndex);
                pointToPosition = this.pointToPosition(n, n2);
                if (pointToPosition == -1) {
                    n = 1;
                    continue;
                }
                break;
            }
            final View child = this.getChildAt(pointToPosition - this.getFirstVisiblePosition());
            this.a(child, pointToPosition, n, n2);
            if (a == 1) {
                this.a(child, pointToPosition);
            }
            continue Label_0037_Outer;
        }
    }
    
    public boolean hasFocus() {
        return this.h || super.hasFocus();
    }
    
    public boolean hasWindowFocus() {
        return this.h || super.hasWindowFocus();
    }
    
    public boolean isFocused() {
        return this.h || super.isFocused();
    }
    
    public boolean isInTouchMode() {
        return (this.h && this.g) || super.isInTouchMode();
    }
    
    void setListSelectionHidden(final boolean g) {
        this.g = g;
    }
}
