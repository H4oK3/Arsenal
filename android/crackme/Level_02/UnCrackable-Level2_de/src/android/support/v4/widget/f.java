// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Handler;
import android.widget.Filter;
import android.database.ContentObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FilterQueryProvider;
import android.database.DataSetObserver;
import android.content.Context;
import android.database.Cursor;
import android.widget.Filterable;
import android.widget.BaseAdapter;

public abstract class f extends BaseAdapter implements g.a, Filterable
{
    protected boolean a;
    protected boolean b;
    protected Cursor c;
    protected Context d;
    protected int e;
    protected a f;
    protected DataSetObserver g;
    protected g h;
    protected FilterQueryProvider i;
    
    public f(final Context context, final Cursor cursor, final boolean b) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 2;
        }
        this.a(context, cursor, n);
    }
    
    public Cursor a() {
        return this.c;
    }
    
    public Cursor a(final CharSequence charSequence) {
        if (this.i != null) {
            return this.i.runQuery(charSequence);
        }
        return this.c;
    }
    
    public abstract View a(final Context p0, final Cursor p1, final ViewGroup p2);
    
    void a(final Context d, final Cursor c, int n) {
        boolean a = true;
        if ((n & 0x1) == 0x1) {
            n |= 0x2;
            this.b = true;
        }
        else {
            this.b = false;
        }
        if (c == null) {
            a = false;
        }
        this.c = c;
        this.a = a;
        this.d = d;
        int columnIndexOrThrow;
        if (a) {
            columnIndexOrThrow = c.getColumnIndexOrThrow("_id");
        }
        else {
            columnIndexOrThrow = -1;
        }
        this.e = columnIndexOrThrow;
        if ((n & 0x2) == 0x2) {
            this.f = new a();
            this.g = new b();
        }
        else {
            this.f = null;
            this.g = null;
        }
        if (a) {
            if (this.f != null) {
                c.registerContentObserver((ContentObserver)this.f);
            }
            if (this.g != null) {
                c.registerDataSetObserver(this.g);
            }
        }
    }
    
    public void a(Cursor b) {
        b = this.b(b);
        if (b != null) {
            b.close();
        }
    }
    
    public abstract void a(final View p0, final Context p1, final Cursor p2);
    
    public Cursor b(final Cursor c) {
        if (c == this.c) {
            return null;
        }
        final Cursor c2 = this.c;
        if (c2 != null) {
            if (this.f != null) {
                c2.unregisterContentObserver((ContentObserver)this.f);
            }
            if (this.g != null) {
                c2.unregisterDataSetObserver(this.g);
            }
        }
        if ((this.c = c) != null) {
            if (this.f != null) {
                c.registerContentObserver((ContentObserver)this.f);
            }
            if (this.g != null) {
                c.registerDataSetObserver(this.g);
            }
            this.e = c.getColumnIndexOrThrow("_id");
            this.a = true;
            this.notifyDataSetChanged();
            return c2;
        }
        this.e = -1;
        this.a = false;
        this.notifyDataSetInvalidated();
        return c2;
    }
    
    public View b(final Context context, final Cursor cursor, final ViewGroup viewGroup) {
        return this.a(context, cursor, viewGroup);
    }
    
    protected void b() {
        if (this.b && this.c != null && !this.c.isClosed()) {
            this.a = this.c.requery();
        }
    }
    
    public CharSequence c(final Cursor cursor) {
        if (cursor == null) {
            return "";
        }
        return cursor.toString();
    }
    
    public int getCount() {
        if (this.a && this.c != null) {
            return this.c.getCount();
        }
        return 0;
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        if (this.a) {
            this.c.moveToPosition(n);
            View b;
            if ((b = view) == null) {
                b = this.b(this.d, this.c, viewGroup);
            }
            this.a(b, this.d, this.c);
            return b;
        }
        return null;
    }
    
    public Filter getFilter() {
        if (this.h == null) {
            this.h = new g((g.a)this);
        }
        return this.h;
    }
    
    public Object getItem(final int n) {
        if (this.a && this.c != null) {
            this.c.moveToPosition(n);
            return this.c;
        }
        return null;
    }
    
    public long getItemId(final int n) {
        long long1;
        final long n2 = long1 = 0L;
        if (this.a) {
            long1 = n2;
            if (this.c != null) {
                long1 = n2;
                if (this.c.moveToPosition(n)) {
                    long1 = this.c.getLong(this.e);
                }
            }
        }
        return long1;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        if (!this.a) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!this.c.moveToPosition(n)) {
            throw new IllegalStateException("couldn't move cursor to position " + n);
        }
        View a;
        if ((a = view) == null) {
            a = this.a(this.d, this.c, viewGroup);
        }
        this.a(a, this.d, this.c);
        return a;
    }
    
    public boolean hasStableIds() {
        return true;
    }
    
    private class a extends ContentObserver
    {
        a() {
            super(new Handler());
        }
        
        public boolean deliverSelfNotifications() {
            return true;
        }
        
        public void onChange(final boolean b) {
            android.support.v4.widget.f.this.b();
        }
    }
    
    private class b extends DataSetObserver
    {
        public void onChanged() {
            android.support.v4.widget.f.this.a = true;
            android.support.v4.widget.f.this.notifyDataSetChanged();
        }
        
        public void onInvalidated() {
            android.support.v4.widget.f.this.a = false;
            android.support.v4.widget.f.this.notifyDataSetInvalidated();
        }
    }
}
