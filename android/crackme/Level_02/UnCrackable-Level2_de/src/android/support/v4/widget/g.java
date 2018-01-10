// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.widget.Filter$FilterResults;
import android.database.Cursor;
import android.widget.Filter;

class g extends Filter
{
    a a;
    
    g(final a a) {
        this.a = a;
    }
    
    public CharSequence convertResultToString(final Object o) {
        return this.a.c((Cursor)o);
    }
    
    protected Filter$FilterResults performFiltering(final CharSequence charSequence) {
        final Cursor a = this.a.a(charSequence);
        final Filter$FilterResults filter$FilterResults = new Filter$FilterResults();
        if (a != null) {
            filter$FilterResults.count = a.getCount();
            filter$FilterResults.values = a;
            return filter$FilterResults;
        }
        filter$FilterResults.count = 0;
        filter$FilterResults.values = null;
        return filter$FilterResults;
    }
    
    protected void publishResults(final CharSequence charSequence, final Filter$FilterResults filter$FilterResults) {
        final Cursor a = this.a.a();
        if (filter$FilterResults.values != null && filter$FilterResults.values != a) {
            this.a.a((Cursor)filter$FilterResults.values);
        }
    }
    
    interface a
    {
        Cursor a();
        
        Cursor a(final CharSequence p0);
        
        void a(final Cursor p0);
        
        CharSequence c(final Cursor p0);
    }
}
