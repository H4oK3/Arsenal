// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import java.util.List;
import android.os.RemoteException;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.os.IInterface;

public interface IMediaBrowserServiceCompatCallbacks extends IInterface
{
    void onConnect(final String p0, final MediaSessionCompat.Token p1, final Bundle p2) throws RemoteException;
    
    void onConnectFailed() throws RemoteException;
    
    void onLoadChildren(final String p0, final List p1) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IMediaBrowserServiceCompatCallbacks
    {
        private static final String DESCRIPTOR = "android.support.v4.media.IMediaBrowserServiceCompatCallbacks";
        static final int TRANSACTION_onConnect = 1;
        static final int TRANSACTION_onConnectFailed = 2;
        static final int TRANSACTION_onLoadChildren = 3;
        
        public Stub() {
            this.attachInterface((IInterface)this, "android.support.v4.media.IMediaBrowserServiceCompatCallbacks");
        }
        
        public static IMediaBrowserServiceCompatCallbacks asInterface(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("android.support.v4.media.IMediaBrowserServiceCompatCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof IMediaBrowserServiceCompatCallbacks) {
                return (IMediaBrowserServiceCompatCallbacks)queryLocalInterface;
            }
            return new Proxy(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("android.support.v4.media.IMediaBrowserServiceCompatCallbacks");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("android.support.v4.media.IMediaBrowserServiceCompatCallbacks");
                    final String string = parcel.readString();
                    MediaSessionCompat.Token token;
                    if (parcel.readInt() != 0) {
                        token = (MediaSessionCompat.Token)MediaSessionCompat.Token.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        token = null;
                    }
                    Bundle bundle;
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle = null;
                    }
                    this.onConnect(string, token, bundle);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("android.support.v4.media.IMediaBrowserServiceCompatCallbacks");
                    this.onConnectFailed();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("android.support.v4.media.IMediaBrowserServiceCompatCallbacks");
                    this.onLoadChildren(parcel.readString(), parcel.readArrayList(this.getClass().getClassLoader()));
                    return true;
                }
            }
        }
        
        private static class Proxy implements IMediaBrowserServiceCompatCallbacks
        {
            private IBinder mRemote;
            
            Proxy(final IBinder mRemote) {
                this.mRemote = mRemote;
            }
            
            public IBinder asBinder() {
                return this.mRemote;
            }
            
            public String getInterfaceDescriptor() {
                return "android.support.v4.media.IMediaBrowserServiceCompatCallbacks";
            }
            
            @Override
            public void onConnect(final String s, final MediaSessionCompat.Token token, final Bundle bundle) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("android.support.v4.media.IMediaBrowserServiceCompatCallbacks");
                            obtain.writeString(s);
                            if (token != null) {
                                obtain.writeInt(1);
                                token.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (bundle != null) {
                                obtain.writeInt(1);
                                bundle.writeToParcel(obtain, 0);
                                this.mRemote.transact(1, obtain, (Parcel)null, 1);
                                return;
                            }
                        }
                        finally {
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue;
                    }
                }
            }
            
            @Override
            public void onConnectFailed() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.IMediaBrowserServiceCompatCallbacks");
                    this.mRemote.transact(2, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void onLoadChildren(final String s, final List list) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.IMediaBrowserServiceCompatCallbacks");
                    obtain.writeString(s);
                    obtain.writeList(list);
                    this.mRemote.transact(3, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
