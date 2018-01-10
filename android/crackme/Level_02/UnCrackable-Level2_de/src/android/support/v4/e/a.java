// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.e;

import android.os.Build$VERSION;
import android.os.AsyncTask;

public final class a
{
    public static <Params, Progress, Result> AsyncTask<Params, Progress, Result> a(final AsyncTask<Params, Progress, Result> asyncTask, final Params... array) {
        if (asyncTask == null) {
            throw new IllegalArgumentException("task can not be null");
        }
        if (Build$VERSION.SDK_INT >= 11) {
            b.a((android.os.AsyncTask<Params, Object, Object>)asyncTask, array);
            return asyncTask;
        }
        asyncTask.execute((Object[])array);
        return asyncTask;
    }
}
