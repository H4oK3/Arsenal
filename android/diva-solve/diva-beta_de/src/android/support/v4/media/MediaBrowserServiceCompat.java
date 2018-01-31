// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.text.TextUtils;
import android.os.Binder;
import java.util.HashSet;
import android.content.Intent;
import java.util.Iterator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.os.Parcelable;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import java.util.List;
import android.support.v4.os.ResultReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.util.ArrayMap;
import android.app.Service;

public abstract class MediaBrowserServiceCompat extends Service
{
    private static final boolean DBG = false;
    public static final String KEY_MEDIA_ITEM = "media_item";
    public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserServiceCompat";
    private static final String TAG = "MediaBrowserServiceCompat";
    private ServiceBinder mBinder;
    private final ArrayMap<IBinder, ConnectionRecord> mConnections;
    private final Handler mHandler;
    MediaSessionCompat.Token mSession;
    
    public MediaBrowserServiceCompat() {
        this.mConnections = new ArrayMap<IBinder, ConnectionRecord>();
        this.mHandler = new Handler();
    }
    
    private void addSubscription(final String s, final ConnectionRecord connectionRecord) {
        connectionRecord.subscriptions.add(s);
        this.performLoadChildren(s, connectionRecord);
    }
    
    private boolean isValidPackage(final String s, int i) {
        if (s != null) {
            final String[] packagesForUid = this.getPackageManager().getPackagesForUid(i);
            int length;
            for (length = packagesForUid.length, i = 0; i < length; ++i) {
                if (packagesForUid[i].equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void performLoadChildren(final String s, final ConnectionRecord connectionRecord) {
        final Result<List<MediaBrowserCompat.MediaItem>> result = new Result<List<MediaBrowserCompat.MediaItem>>(s) {
            void onResultSent(final List<MediaBrowserCompat.MediaItem> list) {
                if (list == null) {
                    throw new IllegalStateException("onLoadChildren sent null list for id " + s);
                }
                if (MediaBrowserServiceCompat.this.mConnections.get(connectionRecord.callbacks.asBinder()) != connectionRecord) {
                    return;
                }
                try {
                    connectionRecord.callbacks.onLoadChildren(s, list);
                }
                catch (RemoteException ex) {
                    Log.w("MediaBrowserServiceCompat", "Calling onLoadChildren() failed for id=" + s + " package=" + connectionRecord.pkg);
                }
            }
        };
        this.onLoadChildren(s, (Result<List<MediaBrowserCompat.MediaItem>>)result);
        if (!((Result)result).isDone()) {
            throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + connectionRecord.pkg + " id=" + s);
        }
    }
    
    private void performLoadItem(final String s, final ResultReceiver resultReceiver) {
        final Result<MediaBrowserCompat.MediaItem> result = new Result<MediaBrowserCompat.MediaItem>(s) {
            void onResultSent(final MediaBrowserCompat.MediaItem mediaItem) {
                final Bundle bundle = new Bundle();
                bundle.putParcelable("media_item", (Parcelable)mediaItem);
                resultReceiver.send(0, bundle);
            }
        };
        this.onLoadItem(s, (Result<MediaBrowserCompat.MediaItem>)result);
        if (!((Result)result).isDone()) {
            throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + s);
        }
    }
    
    public void dump(final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
    }
    
    @Nullable
    public MediaSessionCompat.Token getSessionToken() {
        return this.mSession;
    }
    
    public void notifyChildrenChanged(@NonNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        }
        this.mHandler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                final Iterator<IBinder> iterator = MediaBrowserServiceCompat.this.mConnections.keySet().iterator();
                while (iterator.hasNext()) {
                    final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(iterator.next());
                    if (connectionRecord.subscriptions.contains(s)) {
                        MediaBrowserServiceCompat.this.performLoadChildren(s, connectionRecord);
                    }
                }
            }
        });
    }
    
    public IBinder onBind(final Intent intent) {
        if ("android.media.browse.MediaBrowserServiceCompat".equals(intent.getAction())) {
            return (IBinder)this.mBinder;
        }
        return null;
    }
    
    public void onCreate() {
        super.onCreate();
        this.mBinder = new ServiceBinder();
    }
    
    @Nullable
    public abstract BrowserRoot onGetRoot(@NonNull final String p0, final int p1, @Nullable final Bundle p2);
    
    public abstract void onLoadChildren(@NonNull final String p0, @NonNull final Result<List<MediaBrowserCompat.MediaItem>> p1);
    
    public void onLoadItem(final String s, final Result<MediaBrowserCompat.MediaItem> result) {
        result.sendResult(null);
    }
    
    public void setSessionToken(final MediaSessionCompat.Token mSession) {
        if (mSession == null) {
            throw new IllegalArgumentException("Session token may not be null.");
        }
        if (this.mSession != null) {
            throw new IllegalStateException("The session token has already been set.");
        }
        this.mSession = mSession;
        this.mHandler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                for (final IBinder binder : MediaBrowserServiceCompat.this.mConnections.keySet()) {
                    final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(binder);
                    try {
                        connectionRecord.callbacks.onConnect(connectionRecord.root.getRootId(), mSession, connectionRecord.root.getExtras());
                    }
                    catch (RemoteException ex) {
                        Log.w("MediaBrowserServiceCompat", "Connection for " + connectionRecord.pkg + " is no longer valid.");
                        MediaBrowserServiceCompat.this.mConnections.remove(binder);
                    }
                }
            }
        });
    }
    
    public static final class BrowserRoot
    {
        private final Bundle mExtras;
        private final String mRootId;
        
        public BrowserRoot(@NonNull final String mRootId, @Nullable final Bundle mExtras) {
            if (mRootId == null) {
                throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
            }
            this.mRootId = mRootId;
            this.mExtras = mExtras;
        }
        
        public Bundle getExtras() {
            return this.mExtras;
        }
        
        public String getRootId() {
            return this.mRootId;
        }
    }
    
    private class ConnectionRecord
    {
        IMediaBrowserServiceCompatCallbacks callbacks;
        String pkg;
        BrowserRoot root;
        Bundle rootHints;
        HashSet<String> subscriptions;
        
        private ConnectionRecord() {
            this.subscriptions = new HashSet<String>();
        }
    }
    
    public class Result<T>
    {
        private Object mDebug;
        private boolean mDetachCalled;
        private boolean mSendResultCalled;
        
        Result(final Object mDebug) {
            this.mDebug = mDebug;
        }
        
        public void detach() {
            if (this.mDetachCalled) {
                throw new IllegalStateException("detach() called when detach() had already been called for: " + this.mDebug);
            }
            if (this.mSendResultCalled) {
                throw new IllegalStateException("detach() called when sendResult() had already been called for: " + this.mDebug);
            }
            this.mDetachCalled = true;
        }
        
        boolean isDone() {
            return this.mDetachCalled || this.mSendResultCalled;
        }
        
        void onResultSent(final T t) {
        }
        
        public void sendResult(final T t) {
            if (this.mSendResultCalled) {
                throw new IllegalStateException("sendResult() called twice for: " + this.mDebug);
            }
            this.mSendResultCalled = true;
            this.onResultSent(t);
        }
    }
    
    private class ServiceBinder extends Stub
    {
        public void addSubscription(final String s, final IMediaBrowserServiceCompatCallbacks mediaBrowserServiceCompatCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(mediaBrowserServiceCompatCallbacks.asBinder());
                    if (connectionRecord == null) {
                        Log.w("MediaBrowserServiceCompat", "addSubscription for callback that isn't registered id=" + s);
                        return;
                    }
                    MediaBrowserServiceCompat.this.addSubscription(s, connectionRecord);
                }
            });
        }
        
        public void connect(final String s, final Bundle bundle, final IMediaBrowserServiceCompatCallbacks mediaBrowserServiceCompatCallbacks) {
            final int callingUid = Binder.getCallingUid();
            if (!MediaBrowserServiceCompat.this.isValidPackage(s, callingUid)) {
                throw new IllegalArgumentException("Package/uid mismatch: uid=" + callingUid + " package=" + s);
            }
            MediaBrowserServiceCompat.this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    final IBinder binder = mediaBrowserServiceCompatCallbacks.asBinder();
                    MediaBrowserServiceCompat.this.mConnections.remove(binder);
                    final ConnectionRecord connectionRecord = new ConnectionRecord();
                    connectionRecord.pkg = s;
                    connectionRecord.rootHints = bundle;
                    connectionRecord.callbacks = mediaBrowserServiceCompatCallbacks;
                    connectionRecord.root = MediaBrowserServiceCompat.this.onGetRoot(s, callingUid, bundle);
                    Label_0181: {
                        if (connectionRecord.root != null) {
                            break Label_0181;
                        }
                        Log.i("MediaBrowserServiceCompat", "No root for client " + s + " from service " + this.getClass().getName());
                        try {
                            mediaBrowserServiceCompatCallbacks.onConnectFailed();
                            return;
                        }
                        catch (RemoteException ex) {
                            Log.w("MediaBrowserServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + s);
                            return;
                        }
                        try {
                            MediaBrowserServiceCompat.this.mConnections.put(binder, connectionRecord);
                            if (MediaBrowserServiceCompat.this.mSession != null) {
                                mediaBrowserServiceCompatCallbacks.onConnect(connectionRecord.root.getRootId(), MediaBrowserServiceCompat.this.mSession, connectionRecord.root.getExtras());
                            }
                        }
                        catch (RemoteException ex2) {
                            Log.w("MediaBrowserServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + s);
                            MediaBrowserServiceCompat.this.mConnections.remove(binder);
                        }
                    }
                }
            });
        }
        
        public void disconnect(final IMediaBrowserServiceCompatCallbacks mediaBrowserServiceCompatCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (MediaBrowserServiceCompat.this.mConnections.remove(mediaBrowserServiceCompatCallbacks.asBinder()) != null) {}
                }
            });
        }
        
        public void getMediaItem(final String s, final ResultReceiver resultReceiver) {
            if (TextUtils.isEmpty((CharSequence)s) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    MediaBrowserServiceCompat.this.performLoadItem(s, resultReceiver);
                }
            });
        }
        
        public void removeSubscription(final String s, final IMediaBrowserServiceCompatCallbacks mediaBrowserServiceCompatCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(mediaBrowserServiceCompatCallbacks.asBinder());
                    if (connectionRecord == null) {
                        Log.w("MediaBrowserServiceCompat", "removeSubscription for callback that isn't registered id=" + s);
                    }
                    else if (!connectionRecord.subscriptions.remove(s)) {
                        Log.w("MediaBrowserServiceCompat", "removeSubscription called for " + s + " which is not subscribed");
                    }
                }
            });
        }
    }
}
