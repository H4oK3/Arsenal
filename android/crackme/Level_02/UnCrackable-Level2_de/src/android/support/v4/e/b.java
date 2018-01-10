// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.e;

import android.os.AsyncTask;
import android.annotation.TargetApi;

@TargetApi(11)
class b
{
    static <Params, Progress, Result> void a(final AsyncTask<Params, Progress, Result> asyncTask, final Params... array) {
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])array);
    }
}
