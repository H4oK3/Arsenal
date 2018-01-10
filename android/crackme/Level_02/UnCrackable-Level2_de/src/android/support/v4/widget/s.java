// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.View;
import android.view.ViewGroup;
import android.database.Cursor;
import android.content.Context;
import android.view.LayoutInflater;

public abstract class s extends f
{
    private int j;
    private int k;
    private LayoutInflater l;
    
    public s(final Context context, final int n, final Cursor cursor, final boolean b) {
        super(context, cursor, b);
        this.k = n;
        this.j = n;
        this.l = (LayoutInflater)context.getSystemService("layout_inflater");
    }
    
    @Override
    public View a(final Context context, final Cursor cursor, final ViewGroup viewGroup) {
        return this.l.inflate(this.j, viewGroup, false);
    }
    
    @Override
    public View b(final Context context, final Cursor cursor, final ViewGroup viewGroup) {
        return this.l.inflate(this.k, viewGroup, false);
    }
}
