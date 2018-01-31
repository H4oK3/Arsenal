// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import java.lang.ref.WeakReference;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.content.Intent;
import java.util.Iterator;
import android.os.RemoteException;
import java.util.Collections;
import android.util.Log;
import android.content.ServiceConnection;
import java.util.List;
import android.support.v4.util.ArrayMap;
import android.os.Handler;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;

public final class MediaBrowserCompat
{
    private final MediaBrowserImplBase mImpl;
    
    public MediaBrowserCompat(final Context context, final ComponentName componentName, final ConnectionCallback connectionCallback, final Bundle bundle) {
        this.mImpl = new MediaBrowserImplBase(context, componentName, connectionCallback, bundle);
    }
    
    public void connect() {
        this.mImpl.connect();
    }
    
    public void disconnect() {
        this.mImpl.disconnect();
    }
    
    @Nullable
    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }
    
    public void getItem(@NonNull final String s, @NonNull final ItemCallback itemCallback) {
        this.mImpl.getItem(s, itemCallback);
    }
    
    @NonNull
    public String getRoot() {
        return this.mImpl.getRoot();
    }
    
    @NonNull
    public ComponentName getServiceComponent() {
        return this.mImpl.getServiceComponent();
    }
    
    @NonNull
    public MediaSessionCompat.Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }
    
    public boolean isConnected() {
        return this.mImpl.isConnected();
    }
    
    public void subscribe(@NonNull final String s, @NonNull final SubscriptionCallback subscriptionCallback) {
        this.mImpl.subscribe(s, subscriptionCallback);
    }
    
    public void unsubscribe(@NonNull final String s) {
        this.mImpl.unsubscribe(s);
    }
    
    public static class ConnectionCallback
    {
        public void onConnected() {
        }
        
        public void onConnectionFailed() {
        }
        
        public void onConnectionSuspended() {
        }
    }
    
    public abstract static class ItemCallback
    {
        public void onError(@NonNull final String s) {
        }
        
        public void onItemLoaded(final MediaItem mediaItem) {
        }
    }
    
    static class MediaBrowserImplBase
    {
        private static final int CONNECT_STATE_CONNECTED = 2;
        private static final int CONNECT_STATE_CONNECTING = 1;
        private static final int CONNECT_STATE_DISCONNECTED = 0;
        private static final int CONNECT_STATE_SUSPENDED = 3;
        private static final boolean DBG = false;
        private static final String TAG = "MediaBrowserCompat";
        private final ConnectionCallback mCallback;
        private final Context mContext;
        private Bundle mExtras;
        private final Handler mHandler;
        private MediaSessionCompat.Token mMediaSessionToken;
        private final Bundle mRootHints;
        private String mRootId;
        private IMediaBrowserServiceCompat mServiceBinder;
        private IMediaBrowserServiceCompatCallbacks mServiceCallbacks;
        private final ComponentName mServiceComponent;
        private MediaServiceConnection mServiceConnection;
        private int mState;
        private final ArrayMap<String, Subscription> mSubscriptions;
        
        public MediaBrowserImplBase(final Context mContext, final ComponentName mServiceComponent, final ConnectionCallback mCallback, final Bundle mRootHints) {
            this.mHandler = new Handler();
            this.mSubscriptions = new ArrayMap<String, Subscription>();
            this.mState = 0;
            if (mContext == null) {
                throw new IllegalArgumentException("context must not be null");
            }
            if (mServiceComponent == null) {
                throw new IllegalArgumentException("service component must not be null");
            }
            if (mCallback == null) {
                throw new IllegalArgumentException("connection callback must not be null");
            }
            this.mContext = mContext;
            this.mServiceComponent = mServiceComponent;
            this.mCallback = mCallback;
            this.mRootHints = mRootHints;
        }
        
        private void forceCloseConnection() {
            if (this.mServiceConnection != null) {
                this.mContext.unbindService((ServiceConnection)this.mServiceConnection);
            }
            this.mState = 0;
            this.mServiceConnection = null;
            this.mServiceBinder = null;
            this.mServiceCallbacks = null;
            this.mRootId = null;
            this.mMediaSessionToken = null;
        }
        
        private ServiceCallbacks getNewServiceCallbacks() {
            return new ServiceCallbacks(this);
        }
        
        private static String getStateLabel(final int n) {
            switch (n) {
                default: {
                    return "UNKNOWN/" + n;
                }
                case 0: {
                    return "CONNECT_STATE_DISCONNECTED";
                }
                case 1: {
                    return "CONNECT_STATE_CONNECTING";
                }
                case 2: {
                    return "CONNECT_STATE_CONNECTED";
                }
                case 3: {
                    return "CONNECT_STATE_SUSPENDED";
                }
            }
        }
        
        private boolean isCurrent(final IMediaBrowserServiceCompatCallbacks mediaBrowserServiceCompatCallbacks, final String s) {
            if (this.mServiceCallbacks != mediaBrowserServiceCompatCallbacks) {
                if (this.mState != 0) {
                    Log.i("MediaBrowserCompat", s + " for " + this.mServiceComponent + " with mServiceConnection=" + this.mServiceCallbacks + " this=" + this);
                }
                return false;
            }
            return true;
        }
        
        private final void onConnectionFailed(final IMediaBrowserServiceCompatCallbacks mediaBrowserServiceCompatCallbacks) {
            this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    Log.e("MediaBrowserCompat", "onConnectFailed for " + MediaBrowserImplBase.this.mServiceComponent);
                    if (!MediaBrowserImplBase.this.isCurrent(mediaBrowserServiceCompatCallbacks, "onConnectFailed")) {
                        return;
                    }
                    if (MediaBrowserImplBase.this.mState != 1) {
                        Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(MediaBrowserImplBase.this.mState) + "... ignoring");
                        return;
                    }
                    MediaBrowserImplBase.this.forceCloseConnection();
                    MediaBrowserImplBase.this.mCallback.onConnectionFailed();
                }
            });
        }
        
        private final void onLoadChildren(final IMediaBrowserServiceCompatCallbacks mediaBrowserServiceCompatCallbacks, final String s, final List list) {
            this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (MediaBrowserImplBase.this.isCurrent(mediaBrowserServiceCompatCallbacks, "onLoadChildren")) {
                        List<MediaItem> list;
                        if ((list = (List<MediaItem>)list) == null) {
                            list = Collections.emptyList();
                        }
                        final Subscription subscription = (Subscription)MediaBrowserImplBase.this.mSubscriptions.get(s);
                        if (subscription != null) {
                            subscription.callback.onChildrenLoaded(s, list);
                        }
                    }
                }
            });
        }
        
        private final void onServiceConnected(final IMediaBrowserServiceCompatCallbacks mediaBrowserServiceCompatCallbacks, final String s, final MediaSessionCompat.Token token, final Bundle bundle) {
            this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (MediaBrowserImplBase.this.isCurrent(mediaBrowserServiceCompatCallbacks, "onConnect")) {
                        if (MediaBrowserImplBase.this.mState != 1) {
                            Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(MediaBrowserImplBase.this.mState) + "... ignoring");
                            return;
                        }
                        MediaBrowserImplBase.this.mRootId = s;
                        MediaBrowserImplBase.this.mMediaSessionToken = token;
                        MediaBrowserImplBase.this.mExtras = bundle;
                        MediaBrowserImplBase.this.mState = 2;
                        MediaBrowserImplBase.this.mCallback.onConnected();
                        for (final String s : MediaBrowserImplBase.this.mSubscriptions.keySet()) {
                            try {
                                MediaBrowserImplBase.this.mServiceBinder.addSubscription(s, MediaBrowserImplBase.this.mServiceCallbacks);
                            }
                            catch (RemoteException ex) {
                                Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + s);
                            }
                        }
                    }
                }
            });
        }
        
        public void connect() {
            if (this.mState != 0) {
                throw new IllegalStateException("connect() called while not disconnected (state=" + getStateLabel(this.mState) + ")");
            }
            if (this.mServiceBinder != null) {
                throw new RuntimeException("mServiceBinder should be null. Instead it is " + this.mServiceBinder);
            }
            if (this.mServiceCallbacks != null) {
                throw new RuntimeException("mServiceCallbacks should be null. Instead it is " + this.mServiceCallbacks);
            }
            this.mState = 1;
            final Intent intent = new Intent("android.media.browse.MediaBrowserServiceCompat");
            intent.setComponent(this.mServiceComponent);
            final MediaServiceConnection mServiceConnection = new MediaServiceConnection();
            this.mServiceConnection = mServiceConnection;
            int bindService = 0;
            while (true) {
                try {
                    bindService = (this.mContext.bindService(intent, (ServiceConnection)this.mServiceConnection, 1) ? 1 : 0);
                    if (bindService == 0) {
                        this.mHandler.post((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                if (mServiceConnection == MediaBrowserImplBase.this.mServiceConnection) {
                                    MediaBrowserImplBase.this.forceCloseConnection();
                                    MediaBrowserImplBase.this.mCallback.onConnectionFailed();
                                }
                            }
                        });
                    }
                }
                catch (Exception ex) {
                    Log.e("MediaBrowserCompat", "Failed binding to service " + this.mServiceComponent);
                    continue;
                }
                break;
            }
        }
        
        public void disconnect() {
            while (true) {
                if (this.mServiceCallbacks == null) {
                    break Label_0020;
                }
                try {
                    this.mServiceBinder.disconnect(this.mServiceCallbacks);
                    this.forceCloseConnection();
                }
                catch (RemoteException ex) {
                    Log.w("MediaBrowserCompat", "RemoteException during connect for " + this.mServiceComponent);
                    continue;
                }
                break;
            }
        }
        
        void dump() {
            Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
            Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.mServiceComponent);
            Log.d("MediaBrowserCompat", "  mCallback=" + this.mCallback);
            Log.d("MediaBrowserCompat", "  mRootHints=" + this.mRootHints);
            Log.d("MediaBrowserCompat", "  mState=" + getStateLabel(this.mState));
            Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.mServiceConnection);
            Log.d("MediaBrowserCompat", "  mServiceBinder=" + this.mServiceBinder);
            Log.d("MediaBrowserCompat", "  mServiceCallbacks=" + this.mServiceCallbacks);
            Log.d("MediaBrowserCompat", "  mRootId=" + this.mRootId);
            Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.mMediaSessionToken);
        }
        
        @Nullable
        public Bundle getExtras() {
            if (!this.isConnected()) {
                throw new IllegalStateException("getExtras() called while not connected (state=" + getStateLabel(this.mState) + ")");
            }
            return this.mExtras;
        }
        
        public void getItem(@NonNull final String s, @NonNull final ItemCallback itemCallback) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                throw new IllegalArgumentException("mediaId is empty.");
            }
            if (itemCallback == null) {
                throw new IllegalArgumentException("cb is null.");
            }
            if (this.mState != 2) {
                Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        itemCallback.onError(s);
                    }
                });
                return;
            }
            final ResultReceiver resultReceiver = new ResultReceiver(this.mHandler) {
                @Override
                protected void onReceiveResult(final int n, final Bundle bundle) {
                    if (n != 0 || bundle == null || !bundle.containsKey("media_item")) {
                        itemCallback.onError(s);
                        return;
                    }
                    final Parcelable parcelable = bundle.getParcelable("media_item");
                    if (!(parcelable instanceof MediaItem)) {
                        itemCallback.onError(s);
                        return;
                    }
                    itemCallback.onItemLoaded((MediaItem)parcelable);
                }
            };
            try {
                this.mServiceBinder.getMediaItem(s, resultReceiver);
            }
            catch (RemoteException ex) {
                Log.i("MediaBrowserCompat", "Remote error getting media item.");
                this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        itemCallback.onError(s);
                    }
                });
            }
        }
        
        @NonNull
        public String getRoot() {
            if (!this.isConnected()) {
                throw new IllegalStateException("getSessionToken() called while not connected(state=" + getStateLabel(this.mState) + ")");
            }
            return this.mRootId;
        }
        
        @NonNull
        public ComponentName getServiceComponent() {
            if (!this.isConnected()) {
                throw new IllegalStateException("getServiceComponent() called while not connected (state=" + this.mState + ")");
            }
            return this.mServiceComponent;
        }
        
        @NonNull
        public MediaSessionCompat.Token getSessionToken() {
            if (!this.isConnected()) {
                throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.mState + ")");
            }
            return this.mMediaSessionToken;
        }
        
        public boolean isConnected() {
            return this.mState == 2;
        }
        
        public void subscribe(@NonNull final String s, @NonNull final SubscriptionCallback callback) {
            if (s == null) {
                throw new IllegalArgumentException("parentId is null");
            }
            if (callback == null) {
                throw new IllegalArgumentException("callback is null");
            }
            Subscription subscription = this.mSubscriptions.get(s);
            Label_0104: {
                if (subscription != null) {
                    break Label_0104;
                }
                int n = 1;
                while (true) {
                    if (n != 0) {
                        subscription = new Subscription(s);
                        this.mSubscriptions.put(s, subscription);
                    }
                    subscription.callback = callback;
                    if (this.mState != 2) {
                        return;
                    }
                    try {
                        this.mServiceBinder.addSubscription(s, this.mServiceCallbacks);
                        return;
                        n = 0;
                        continue;
                    }
                    catch (RemoteException ex) {
                        Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + s);
                    }
                    break;
                }
            }
        }
        
        public void unsubscribe(@NonNull final String s) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                throw new IllegalArgumentException("parentId is empty.");
            }
            final Subscription subscription = this.mSubscriptions.remove(s);
            if (this.mState != 2 || subscription == null) {
                return;
            }
            try {
                this.mServiceBinder.removeSubscription(s, this.mServiceCallbacks);
            }
            catch (RemoteException ex) {
                Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + s);
            }
        }
        
        private class MediaServiceConnection implements ServiceConnection
        {
            private boolean isCurrent(final String s) {
                if (MediaBrowserImplBase.this.mServiceConnection != this) {
                    if (MediaBrowserImplBase.this.mState != 0) {
                        Log.i("MediaBrowserCompat", s + " for " + MediaBrowserImplBase.this.mServiceComponent + " with mServiceConnection=" + MediaBrowserImplBase.this.mServiceConnection + " this=" + this);
                    }
                    return false;
                }
                return true;
            }
            
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                if (!this.isCurrent("onServiceConnected")) {
                    return;
                }
                MediaBrowserImplBase.this.mServiceBinder = IMediaBrowserServiceCompat.Stub.asInterface(binder);
                MediaBrowserImplBase.this.mServiceCallbacks = MediaBrowserImplBase.this.getNewServiceCallbacks();
                MediaBrowserImplBase.this.mState = 1;
                try {
                    MediaBrowserImplBase.this.mServiceBinder.connect(MediaBrowserImplBase.this.mContext.getPackageName(), MediaBrowserImplBase.this.mRootHints, MediaBrowserImplBase.this.mServiceCallbacks);
                }
                catch (RemoteException ex) {
                    Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserImplBase.this.mServiceComponent);
                }
            }
            
            public void onServiceDisconnected(final ComponentName componentName) {
                if (!this.isCurrent("onServiceDisconnected")) {
                    return;
                }
                MediaBrowserImplBase.this.mServiceBinder = null;
                MediaBrowserImplBase.this.mServiceCallbacks = null;
                MediaBrowserImplBase.this.mState = 3;
                MediaBrowserImplBase.this.mCallback.onConnectionSuspended();
            }
        }
        
        private static class ServiceCallbacks extends Stub
        {
            private WeakReference<MediaBrowserImplBase> mMediaBrowser;
            
            public ServiceCallbacks(final MediaBrowserImplBase mediaBrowserImplBase) {
                this.mMediaBrowser = new WeakReference<MediaBrowserImplBase>(mediaBrowserImplBase);
            }
            
            public void onConnect(final String s, final MediaSessionCompat.Token token, final Bundle bundle) {
                final MediaBrowserImplBase mediaBrowserImplBase = this.mMediaBrowser.get();
                if (mediaBrowserImplBase != null) {
                    mediaBrowserImplBase.onServiceConnected(this, s, token, bundle);
                }
            }
            
            public void onConnectFailed() {
                final MediaBrowserImplBase mediaBrowserImplBase = this.mMediaBrowser.get();
                if (mediaBrowserImplBase != null) {
                    mediaBrowserImplBase.onConnectionFailed(this);
                }
            }
            
            public void onLoadChildren(final String s, final List list) {
                final MediaBrowserImplBase mediaBrowserImplBase = this.mMediaBrowser.get();
                if (mediaBrowserImplBase != null) {
                    mediaBrowserImplBase.onLoadChildren(this, s, list);
                }
            }
        }
        
        private static class Subscription
        {
            SubscriptionCallback callback;
            final String id;
            
            Subscription(final String id) {
                this.id = id;
            }
        }
    }
    
    public static class MediaItem implements Parcelable
    {
        public static final Parcelable$Creator<MediaItem> CREATOR;
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescriptionCompat mDescription;
        private final int mFlags;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<MediaItem>() {
                public MediaItem createFromParcel(final Parcel parcel) {
                    return new MediaItem(parcel);
                }
                
                public MediaItem[] newArray(final int n) {
                    return new MediaItem[n];
                }
            };
        }
        
        private MediaItem(final Parcel parcel) {
            this.mFlags = parcel.readInt();
            this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }
        
        public MediaItem(@NonNull final MediaDescriptionCompat mDescription, final int mFlags) {
            if (mDescription == null) {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty((CharSequence)mDescription.getMediaId())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            this.mFlags = mFlags;
            this.mDescription = mDescription;
        }
        
        public int describeContents() {
            return 0;
        }
        
        @NonNull
        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }
        
        public int getFlags() {
            return this.mFlags;
        }
        
        @NonNull
        public String getMediaId() {
            return this.mDescription.getMediaId();
        }
        
        public boolean isBrowsable() {
            return (this.mFlags & 0x1) != 0x0;
        }
        
        public boolean isPlayable() {
            return (this.mFlags & 0x2) != 0x0;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("MediaItem{");
            sb.append("mFlags=").append(this.mFlags);
            sb.append(", mDescription=").append(this.mDescription);
            sb.append('}');
            return sb.toString();
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            parcel.writeInt(this.mFlags);
            this.mDescription.writeToParcel(parcel, n);
        }
        
        @Retention(RetentionPolicy.SOURCE)
        public @interface Flags {
        }
    }
    
    public abstract static class SubscriptionCallback
    {
        public void onChildrenLoaded(@NonNull final String s, @NonNull final List<MediaItem> list) {
        }
        
        public void onError(@NonNull final String s) {
        }
    }
}
