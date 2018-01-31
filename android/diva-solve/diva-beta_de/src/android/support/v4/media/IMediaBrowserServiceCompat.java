// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.support.v4.os.ResultReceiver;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IInterface;

public interface IMediaBrowserServiceCompat extends IInterface
{
    void addSubscription(final String p0, final IMediaBrowserServiceCompatCallbacks p1) throws RemoteException;
    
    void connect(final String p0, final Bundle p1, final IMediaBrowserServiceCompatCallbacks p2) throws RemoteException;
    
    void disconnect(final IMediaBrowserServiceCompatCallbacks p0) throws RemoteException;
    
    void getMediaItem(final String p0, final ResultReceiver p1) throws RemoteException;
    
    void removeSubscription(final String p0, final IMediaBrowserServiceCompatCallbacks p1) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IMediaBrowserServiceCompat
    {
        private static final String DESCRIPTOR = "android.support.v4.media.IMediaBrowserServiceCompat";
        static final int TRANSACTION_addSubscription = 3;
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_getMediaItem = 5;
        static final int TRANSACTION_removeSubscription = 4;
        
        public Stub() {
            this.attachInterface((IInterface)this, "android.support.v4.media.IMediaBrowserServiceCompat");
        }
        
        public static IMediaBrowserServiceCompat asInterface(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("android.support.v4.media.IMediaBrowserServiceCompat");
            if (queryLocalInterface != null && queryLocalInterface instanceof IMediaBrowserServiceCompat) {
                return (IMediaBrowserServiceCompat)queryLocalInterface;
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
                    parcel2.writeString("android.support.v4.media.IMediaBrowserServiceCompat");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("android.support.v4.media.IMediaBrowserServiceCompat");
                    final String string = parcel.readString();
                    Bundle bundle;
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle = null;
                    }
                    this.connect(string, bundle, IMediaBrowserServiceCompatCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("android.support.v4.media.IMediaBrowserServiceCompat");
                    this.disconnect(IMediaBrowserServiceCompatCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("android.support.v4.media.IMediaBrowserServiceCompat");
                    this.addSubscription(parcel.readString(), IMediaBrowserServiceCompatCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("android.support.v4.media.IMediaBrowserServiceCompat");
                    this.removeSubscription(parcel.readString(), IMediaBrowserServiceCompatCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("android.support.v4.media.IMediaBrowserServiceCompat");
                    final String string2 = parcel.readString();
                    ResultReceiver resultReceiver;
                    if (parcel.readInt() != 0) {
                        resultReceiver = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        resultReceiver = null;
                    }
                    this.getMediaItem(string2, resultReceiver);
                    return true;
                }
            }
        }
        
        private static class Proxy implements IMediaBrowserServiceCompat
        {
            private IBinder mRemote;
            
            Proxy(final IBinder mRemote) {
                this.mRemote = mRemote;
            }
            
            @Override
            public void addSubscription(final String s, final IMediaBrowserServiceCompatCallbacks mediaBrowserServiceCompatCallbacks) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.IMediaBrowserServiceCompat");
                    obtain.writeString(s);
                    IBinder binder2 = binder;
                    if (mediaBrowserServiceCompatCallbacks != null) {
                        binder2 = mediaBrowserServiceCompatCallbacks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    this.mRemote.transact(3, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.mRemote;
            }
            
            @Override
            public void connect(final String s, final Bundle bundle, final IMediaBrowserServiceCompatCallbacks mediaBrowserServiceCompatCallbacks) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.IMediaBrowserServiceCompat");
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (mediaBrowserServiceCompatCallbacks != null) {
                        binder2 = mediaBrowserServiceCompatCallbacks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    this.mRemote.transact(1, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void disconnect(final IMediaBrowserServiceCompatCallbacks mediaBrowserServiceCompatCallbacks) throws RemoteException {
                IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.IMediaBrowserServiceCompat");
                    if (mediaBrowserServiceCompatCallbacks != null) {
                        binder = mediaBrowserServiceCompatCallbacks.asBinder();
                    }
                    obtain.writeStrongBinder(binder);
                    this.mRemote.transact(2, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            public String getInterfaceDescriptor() {
                return "android.support.v4.media.IMediaBrowserServiceCompat";
            }
            
            @Override
            public void getMediaItem(final String s, final ResultReceiver resultReceiver) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.IMediaBrowserServiceCompat");
                    obtain.writeString(s);
                    if (resultReceiver != null) {
                        obtain.writeInt(1);
                        resultReceiver.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(5, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void removeSubscription(final String s, final IMediaBrowserServiceCompatCallbacks mediaBrowserServiceCompatCallbacks) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.IMediaBrowserServiceCompat");
                    obtain.writeString(s);
                    IBinder binder2 = binder;
                    if (mediaBrowserServiceCompatCallbacks != null) {
                        binder2 = mediaBrowserServiceCompatCallbacks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    this.mRemote.transact(4, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
