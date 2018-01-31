// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.support.v4.util.DebugUtils;
import java.util.List;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.ContextMenu;
import android.view.animation.Animation;
import android.view.MenuItem;
import android.content.res.Configuration;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.content.res.Resources;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.support.annotation.Nullable;
import android.content.Context;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import android.support.v4.util.SimpleArrayMap;
import android.view.View$OnCreateContextMenuListener;
import android.content.ComponentCallbacks;

public class Fragment implements ComponentCallbacks, View$OnCreateContextMenuListener
{
    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int RESUMED = 5;
    static final int STARTED = 4;
    static final int STOPPED = 3;
    static final Object USE_DEFAULT_TRANSITION;
    private static final SimpleArrayMap<String, Class<?>> sClassMap;
    boolean mAdded;
    Boolean mAllowEnterTransitionOverlap;
    Boolean mAllowReturnTransitionOverlap;
    View mAnimatingAway;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mCalled;
    boolean mCheckedForLoaderManager;
    FragmentManagerImpl mChildFragmentManager;
    ViewGroup mContainer;
    int mContainerId;
    boolean mDeferStart;
    boolean mDetached;
    Object mEnterTransition;
    SharedElementCallback mEnterTransitionCallback;
    Object mExitTransition;
    SharedElementCallback mExitTransitionCallback;
    int mFragmentId;
    FragmentManagerImpl mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    FragmentHostCallback mHost;
    boolean mInLayout;
    int mIndex;
    View mInnerView;
    LoaderManagerImpl mLoaderManager;
    boolean mLoadersStarted;
    boolean mMenuVisible;
    int mNextAnim;
    Fragment mParentFragment;
    Object mReenterTransition;
    boolean mRemoving;
    boolean mRestored;
    boolean mResumed;
    boolean mRetainInstance;
    boolean mRetainLoader;
    boolean mRetaining;
    Object mReturnTransition;
    Bundle mSavedFragmentState;
    SparseArray<Parcelable> mSavedViewState;
    Object mSharedElementEnterTransition;
    Object mSharedElementReturnTransition;
    int mState;
    int mStateAfterAnimating;
    String mTag;
    Fragment mTarget;
    int mTargetIndex;
    int mTargetRequestCode;
    boolean mUserVisibleHint;
    View mView;
    String mWho;
    
    static {
        sClassMap = new SimpleArrayMap<String, Class<?>>();
        USE_DEFAULT_TRANSITION = new Object();
    }
    
    public Fragment() {
        this.mState = 0;
        this.mIndex = -1;
        this.mTargetIndex = -1;
        this.mMenuVisible = true;
        this.mUserVisibleHint = true;
        this.mEnterTransition = null;
        this.mReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
        this.mExitTransition = null;
        this.mReenterTransition = Fragment.USE_DEFAULT_TRANSITION;
        this.mSharedElementEnterTransition = null;
        this.mSharedElementReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
        this.mEnterTransitionCallback = null;
        this.mExitTransitionCallback = null;
    }
    
    public static Fragment instantiate(final Context context, final String s) {
        return instantiate(context, s, null);
    }
    
    public static Fragment instantiate(final Context context, final String s, @Nullable final Bundle mArguments) {
        try {
            Class<?> loadClass;
            if ((loadClass = Fragment.sClassMap.get(s)) == null) {
                loadClass = context.getClassLoader().loadClass(s);
                Fragment.sClassMap.put(s, loadClass);
            }
            final Fragment fragment = (Fragment)loadClass.newInstance();
            if (mArguments != null) {
                mArguments.setClassLoader(fragment.getClass().getClassLoader());
                fragment.mArguments = mArguments;
            }
            return fragment;
        }
        catch (ClassNotFoundException ex) {
            throw new InstantiationException("Unable to instantiate fragment " + s + ": make sure class name exists, is public, and has an" + " empty constructor that is public", ex);
        }
        catch (java.lang.InstantiationException ex2) {
            throw new InstantiationException("Unable to instantiate fragment " + s + ": make sure class name exists, is public, and has an" + " empty constructor that is public", ex2);
        }
        catch (IllegalAccessException ex3) {
            throw new InstantiationException("Unable to instantiate fragment " + s + ": make sure class name exists, is public, and has an" + " empty constructor that is public", ex3);
        }
    }
    
    static boolean isSupportFragmentClass(final Context context, final String s) {
        try {
            Class<?> loadClass;
            if ((loadClass = Fragment.sClassMap.get(s)) == null) {
                loadClass = context.getClassLoader().loadClass(s);
                Fragment.sClassMap.put(s, loadClass);
            }
            return Fragment.class.isAssignableFrom(loadClass);
        }
        catch (ClassNotFoundException ex) {
            return false;
        }
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.print(s);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.mFragmentId));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.mContainerId));
        printWriter.print(" mTag=");
        printWriter.println(this.mTag);
        printWriter.print(s);
        printWriter.print("mState=");
        printWriter.print(this.mState);
        printWriter.print(" mIndex=");
        printWriter.print(this.mIndex);
        printWriter.print(" mWho=");
        printWriter.print(this.mWho);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.mBackStackNesting);
        printWriter.print(s);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoving=");
        printWriter.print(this.mRemoving);
        printWriter.print(" mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.mFromLayout);
        printWriter.print(" mInLayout=");
        printWriter.println(this.mInLayout);
        printWriter.print(s);
        printWriter.print("mHidden=");
        printWriter.print(this.mHidden);
        printWriter.print(" mDetached=");
        printWriter.print(this.mDetached);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.mMenuVisible);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.mHasMenu);
        printWriter.print(s);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.mRetainInstance);
        printWriter.print(" mRetaining=");
        printWriter.print(this.mRetaining);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            printWriter.print(s);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            printWriter.print(s);
            printWriter.print("mHost=");
            printWriter.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            printWriter.print(s);
            printWriter.print("mParentFragment=");
            printWriter.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            printWriter.print(s);
            printWriter.print("mArguments=");
            printWriter.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            printWriter.print(s);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            printWriter.print(s);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.mSavedViewState);
        }
        if (this.mTarget != null) {
            printWriter.print(s);
            printWriter.print("mTarget=");
            printWriter.print(this.mTarget);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.mTargetRequestCode);
        }
        if (this.mNextAnim != 0) {
            printWriter.print(s);
            printWriter.print("mNextAnim=");
            printWriter.println(this.mNextAnim);
        }
        if (this.mContainer != null) {
            printWriter.print(s);
            printWriter.print("mContainer=");
            printWriter.println(this.mContainer);
        }
        if (this.mView != null) {
            printWriter.print(s);
            printWriter.print("mView=");
            printWriter.println(this.mView);
        }
        if (this.mInnerView != null) {
            printWriter.print(s);
            printWriter.print("mInnerView=");
            printWriter.println(this.mView);
        }
        if (this.mAnimatingAway != null) {
            printWriter.print(s);
            printWriter.print("mAnimatingAway=");
            printWriter.println(this.mAnimatingAway);
            printWriter.print(s);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(this.mStateAfterAnimating);
        }
        if (this.mLoaderManager != null) {
            printWriter.print(s);
            printWriter.println("Loader Manager:");
            this.mLoaderManager.dump(s + "  ", fileDescriptor, printWriter, array);
        }
        if (this.mChildFragmentManager != null) {
            printWriter.print(s);
            printWriter.println("Child " + this.mChildFragmentManager + ":");
            this.mChildFragmentManager.dump(s + "  ", fileDescriptor, printWriter, array);
        }
    }
    
    @Override
    public final boolean equals(final Object o) {
        return super.equals(o);
    }
    
    Fragment findFragmentByWho(final String s) {
        if (s.equals(this.mWho)) {
            return this;
        }
        if (this.mChildFragmentManager != null) {
            return this.mChildFragmentManager.findFragmentByWho(s);
        }
        return null;
    }
    
    public final FragmentActivity getActivity() {
        if (this.mHost == null) {
            return null;
        }
        return (FragmentActivity)this.mHost.getActivity();
    }
    
    public boolean getAllowEnterTransitionOverlap() {
        return this.mAllowEnterTransitionOverlap == null || this.mAllowEnterTransitionOverlap;
    }
    
    public boolean getAllowReturnTransitionOverlap() {
        return this.mAllowReturnTransitionOverlap == null || this.mAllowReturnTransitionOverlap;
    }
    
    public final Bundle getArguments() {
        return this.mArguments;
    }
    
    public final FragmentManager getChildFragmentManager() {
        if (this.mChildFragmentManager == null) {
            this.instantiateChildFragmentManager();
            if (this.mState >= 5) {
                this.mChildFragmentManager.dispatchResume();
            }
            else if (this.mState >= 4) {
                this.mChildFragmentManager.dispatchStart();
            }
            else if (this.mState >= 2) {
                this.mChildFragmentManager.dispatchActivityCreated();
            }
            else if (this.mState >= 1) {
                this.mChildFragmentManager.dispatchCreate();
            }
        }
        return this.mChildFragmentManager;
    }
    
    public Context getContext() {
        if (this.mHost == null) {
            return null;
        }
        return this.mHost.getContext();
    }
    
    public Object getEnterTransition() {
        return this.mEnterTransition;
    }
    
    public Object getExitTransition() {
        return this.mExitTransition;
    }
    
    public final FragmentManager getFragmentManager() {
        return this.mFragmentManager;
    }
    
    public final Object getHost() {
        if (this.mHost == null) {
            return null;
        }
        return this.mHost.onGetHost();
    }
    
    public final int getId() {
        return this.mFragmentId;
    }
    
    public LayoutInflater getLayoutInflater(final Bundle bundle) {
        final LayoutInflater onGetLayoutInflater = this.mHost.onGetLayoutInflater();
        this.getChildFragmentManager();
        LayoutInflaterCompat.setFactory(onGetLayoutInflater, this.mChildFragmentManager.getLayoutInflaterFactory());
        return onGetLayoutInflater;
    }
    
    public LoaderManager getLoaderManager() {
        if (this.mLoaderManager != null) {
            return this.mLoaderManager;
        }
        if (this.mHost == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        this.mCheckedForLoaderManager = true;
        return this.mLoaderManager = this.mHost.getLoaderManager(this.mWho, this.mLoadersStarted, true);
    }
    
    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }
    
    public Object getReenterTransition() {
        if (this.mReenterTransition == Fragment.USE_DEFAULT_TRANSITION) {
            return this.getExitTransition();
        }
        return this.mReenterTransition;
    }
    
    public final Resources getResources() {
        if (this.mHost == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        return this.mHost.getContext().getResources();
    }
    
    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }
    
    public Object getReturnTransition() {
        if (this.mReturnTransition == Fragment.USE_DEFAULT_TRANSITION) {
            return this.getEnterTransition();
        }
        return this.mReturnTransition;
    }
    
    public Object getSharedElementEnterTransition() {
        return this.mSharedElementEnterTransition;
    }
    
    public Object getSharedElementReturnTransition() {
        if (this.mSharedElementReturnTransition == Fragment.USE_DEFAULT_TRANSITION) {
            return this.getSharedElementEnterTransition();
        }
        return this.mSharedElementReturnTransition;
    }
    
    public final String getString(@StringRes final int n) {
        return this.getResources().getString(n);
    }
    
    public final String getString(@StringRes final int n, final Object... array) {
        return this.getResources().getString(n, array);
    }
    
    public final String getTag() {
        return this.mTag;
    }
    
    public final Fragment getTargetFragment() {
        return this.mTarget;
    }
    
    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }
    
    public final CharSequence getText(@StringRes final int n) {
        return this.getResources().getText(n);
    }
    
    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }
    
    @Nullable
    public View getView() {
        return this.mView;
    }
    
    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }
    
    @Override
    public final int hashCode() {
        return super.hashCode();
    }
    
    void initState() {
        this.mIndex = -1;
        this.mWho = null;
        this.mAdded = false;
        this.mRemoving = false;
        this.mResumed = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = null;
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
        this.mRetaining = false;
        this.mLoaderManager = null;
        this.mLoadersStarted = false;
        this.mCheckedForLoaderManager = false;
    }
    
    void instantiateChildFragmentManager() {
        (this.mChildFragmentManager = new FragmentManagerImpl()).attachController(this.mHost, new FragmentContainer() {
            @Nullable
            @Override
            public View onFindViewById(final int n) {
                if (Fragment.this.mView == null) {
                    throw new IllegalStateException("Fragment does not have a view");
                }
                return Fragment.this.mView.findViewById(n);
            }
            
            @Override
            public boolean onHasView() {
                return Fragment.this.mView != null;
            }
        }, this);
    }
    
    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }
    
    public final boolean isDetached() {
        return this.mDetached;
    }
    
    public final boolean isHidden() {
        return this.mHidden;
    }
    
    final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }
    
    public final boolean isInLayout() {
        return this.mInLayout;
    }
    
    public final boolean isMenuVisible() {
        return this.mMenuVisible;
    }
    
    public final boolean isRemoving() {
        return this.mRemoving;
    }
    
    public final boolean isResumed() {
        return this.mResumed;
    }
    
    public final boolean isVisible() {
        return this.isAdded() && !this.isHidden() && this.mView != null && this.mView.getWindowToken() != null && this.mView.getVisibility() == 0;
    }
    
    public void onActivityCreated(@Nullable final Bundle bundle) {
        this.mCalled = true;
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
    }
    
    @Deprecated
    public void onAttach(final Activity activity) {
        this.mCalled = true;
    }
    
    public void onAttach(final Context context) {
        this.mCalled = true;
        Activity activity;
        if (this.mHost == null) {
            activity = null;
        }
        else {
            activity = this.mHost.getActivity();
        }
        if (activity != null) {
            this.mCalled = false;
            this.onAttach(activity);
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        this.mCalled = true;
    }
    
    public boolean onContextItemSelected(final MenuItem menuItem) {
        return false;
    }
    
    public void onCreate(@Nullable final Bundle bundle) {
        this.mCalled = true;
    }
    
    public Animation onCreateAnimation(final int n, final boolean b, final int n2) {
        return null;
    }
    
    public void onCreateContextMenu(final ContextMenu contextMenu, final View view, final ContextMenu$ContextMenuInfo contextMenu$ContextMenuInfo) {
        this.getActivity().onCreateContextMenu(contextMenu, view, contextMenu$ContextMenuInfo);
    }
    
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater menuInflater) {
    }
    
    @Nullable
    public View onCreateView(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, @Nullable final Bundle bundle) {
        return null;
    }
    
    public void onDestroy() {
        this.mCalled = true;
        if (!this.mCheckedForLoaderManager) {
            this.mCheckedForLoaderManager = true;
            this.mLoaderManager = this.mHost.getLoaderManager(this.mWho, this.mLoadersStarted, false);
        }
        if (this.mLoaderManager != null) {
            this.mLoaderManager.doDestroy();
        }
    }
    
    public void onDestroyOptionsMenu() {
    }
    
    public void onDestroyView() {
        this.mCalled = true;
    }
    
    public void onDetach() {
        this.mCalled = true;
    }
    
    public void onHiddenChanged(final boolean b) {
    }
    
    @Deprecated
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        this.mCalled = true;
    }
    
    public void onInflate(final Context context, final AttributeSet set, final Bundle bundle) {
        this.mCalled = true;
        Activity activity;
        if (this.mHost == null) {
            activity = null;
        }
        else {
            activity = this.mHost.getActivity();
        }
        if (activity != null) {
            this.mCalled = false;
            this.onInflate(activity, set, bundle);
        }
    }
    
    public void onLowMemory() {
        this.mCalled = true;
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        return false;
    }
    
    public void onOptionsMenuClosed(final Menu menu) {
    }
    
    public void onPause() {
        this.mCalled = true;
    }
    
    public void onPrepareOptionsMenu(final Menu menu) {
    }
    
    public void onRequestPermissionsResult(final int n, @NonNull final String[] array, @NonNull final int[] array2) {
    }
    
    public void onResume() {
        this.mCalled = true;
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
    }
    
    public void onStart() {
        this.mCalled = true;
        if (!this.mLoadersStarted) {
            this.mLoadersStarted = true;
            if (!this.mCheckedForLoaderManager) {
                this.mCheckedForLoaderManager = true;
                this.mLoaderManager = this.mHost.getLoaderManager(this.mWho, this.mLoadersStarted, false);
            }
            if (this.mLoaderManager != null) {
                this.mLoaderManager.doStart();
            }
        }
    }
    
    public void onStop() {
        this.mCalled = true;
    }
    
    public void onViewCreated(final View view, @Nullable final Bundle bundle) {
    }
    
    public void onViewStateRestored(@Nullable final Bundle bundle) {
        this.mCalled = true;
    }
    
    void performActivityCreated(final Bundle bundle) {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
        }
        this.mCalled = false;
        this.onActivityCreated(bundle);
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()");
        }
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchActivityCreated();
        }
    }
    
    void performConfigurationChanged(final Configuration configuration) {
        this.onConfigurationChanged(configuration);
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchConfigurationChanged(configuration);
        }
    }
    
    boolean performContextItemSelected(final MenuItem menuItem) {
        return !this.mHidden && (this.onContextItemSelected(menuItem) || (this.mChildFragmentManager != null && this.mChildFragmentManager.dispatchContextItemSelected(menuItem)));
    }
    
    void performCreate(final Bundle bundle) {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
        }
        this.mCalled = false;
        this.onCreate(bundle);
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()");
        }
        if (bundle != null) {
            final Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            if (parcelable != null) {
                if (this.mChildFragmentManager == null) {
                    this.instantiateChildFragmentManager();
                }
                this.mChildFragmentManager.restoreAllState(parcelable, null);
                this.mChildFragmentManager.dispatchCreate();
            }
        }
    }
    
    boolean performCreateOptionsMenu(final Menu menu, final MenuInflater menuInflater) {
        boolean b = false;
        final boolean b2 = false;
        if (!this.mHidden) {
            boolean b3 = b2;
            if (this.mHasMenu) {
                b3 = b2;
                if (this.mMenuVisible) {
                    b3 = true;
                    this.onCreateOptionsMenu(menu, menuInflater);
                }
            }
            b = b3;
            if (this.mChildFragmentManager != null) {
                b = (b3 | this.mChildFragmentManager.dispatchCreateOptionsMenu(menu, menuInflater));
            }
        }
        return b;
    }
    
    View performCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
        }
        return this.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    void performDestroy() {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchDestroy();
        }
        this.mCalled = false;
        this.onDestroy();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()");
        }
    }
    
    void performDestroyView() {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchDestroyView();
        }
        this.mCalled = false;
        this.onDestroyView();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()");
        }
        if (this.mLoaderManager != null) {
            this.mLoaderManager.doReportNextStart();
        }
    }
    
    void performLowMemory() {
        this.onLowMemory();
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchLowMemory();
        }
    }
    
    boolean performOptionsItemSelected(final MenuItem menuItem) {
        return !this.mHidden && ((this.mHasMenu && this.mMenuVisible && this.onOptionsItemSelected(menuItem)) || (this.mChildFragmentManager != null && this.mChildFragmentManager.dispatchOptionsItemSelected(menuItem)));
    }
    
    void performOptionsMenuClosed(final Menu menu) {
        if (!this.mHidden) {
            if (this.mHasMenu && this.mMenuVisible) {
                this.onOptionsMenuClosed(menu);
            }
            if (this.mChildFragmentManager != null) {
                this.mChildFragmentManager.dispatchOptionsMenuClosed(menu);
            }
        }
    }
    
    void performPause() {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchPause();
        }
        this.mCalled = false;
        this.onPause();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()");
        }
    }
    
    boolean performPrepareOptionsMenu(final Menu menu) {
        boolean b = false;
        final boolean b2 = false;
        if (!this.mHidden) {
            boolean b3 = b2;
            if (this.mHasMenu) {
                b3 = b2;
                if (this.mMenuVisible) {
                    b3 = true;
                    this.onPrepareOptionsMenu(menu);
                }
            }
            b = b3;
            if (this.mChildFragmentManager != null) {
                b = (b3 | this.mChildFragmentManager.dispatchPrepareOptionsMenu(menu));
            }
        }
        return b;
    }
    
    void performReallyStop() {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchReallyStop();
        }
        if (this.mLoadersStarted) {
            this.mLoadersStarted = false;
            if (!this.mCheckedForLoaderManager) {
                this.mCheckedForLoaderManager = true;
                this.mLoaderManager = this.mHost.getLoaderManager(this.mWho, this.mLoadersStarted, false);
            }
            if (this.mLoaderManager != null) {
                if (!this.mRetainLoader) {
                    this.mLoaderManager.doStop();
                    return;
                }
                this.mLoaderManager.doRetain();
            }
        }
    }
    
    void performResume() {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
            this.mChildFragmentManager.execPendingActions();
        }
        this.mCalled = false;
        this.onResume();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()");
        }
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchResume();
            this.mChildFragmentManager.execPendingActions();
        }
    }
    
    void performSaveInstanceState(final Bundle bundle) {
        this.onSaveInstanceState(bundle);
        if (this.mChildFragmentManager != null) {
            final Parcelable saveAllState = this.mChildFragmentManager.saveAllState();
            if (saveAllState != null) {
                bundle.putParcelable("android:support:fragments", saveAllState);
            }
        }
    }
    
    void performStart() {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.noteStateNotSaved();
            this.mChildFragmentManager.execPendingActions();
        }
        this.mCalled = false;
        this.onStart();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()");
        }
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchStart();
        }
        if (this.mLoaderManager != null) {
            this.mLoaderManager.doReportStart();
        }
    }
    
    void performStop() {
        if (this.mChildFragmentManager != null) {
            this.mChildFragmentManager.dispatchStop();
        }
        this.mCalled = false;
        this.onStop();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()");
        }
    }
    
    public void registerForContextMenu(final View view) {
        view.setOnCreateContextMenuListener((View$OnCreateContextMenuListener)this);
    }
    
    public final void requestPermissions(@NonNull final String[] array, final int n) {
        if (this.mHost == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        this.mHost.onRequestPermissionsFromFragment(this, array, n);
    }
    
    final void restoreViewState(final Bundle bundle) {
        if (this.mSavedViewState != null) {
            this.mInnerView.restoreHierarchyState((SparseArray)this.mSavedViewState);
            this.mSavedViewState = null;
        }
        this.mCalled = false;
        this.onViewStateRestored(bundle);
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }
    
    public void setAllowEnterTransitionOverlap(final boolean b) {
        this.mAllowEnterTransitionOverlap = b;
    }
    
    public void setAllowReturnTransitionOverlap(final boolean b) {
        this.mAllowReturnTransitionOverlap = b;
    }
    
    public void setArguments(final Bundle mArguments) {
        if (this.mIndex >= 0) {
            throw new IllegalStateException("Fragment already active");
        }
        this.mArguments = mArguments;
    }
    
    public void setEnterSharedElementCallback(final SharedElementCallback mEnterTransitionCallback) {
        this.mEnterTransitionCallback = mEnterTransitionCallback;
    }
    
    public void setEnterTransition(final Object mEnterTransition) {
        this.mEnterTransition = mEnterTransition;
    }
    
    public void setExitSharedElementCallback(final SharedElementCallback mExitTransitionCallback) {
        this.mExitTransitionCallback = mExitTransitionCallback;
    }
    
    public void setExitTransition(final Object mExitTransition) {
        this.mExitTransition = mExitTransition;
    }
    
    public void setHasOptionsMenu(final boolean mHasMenu) {
        if (this.mHasMenu != mHasMenu) {
            this.mHasMenu = mHasMenu;
            if (this.isAdded() && !this.isHidden()) {
                this.mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }
    
    final void setIndex(final int mIndex, final Fragment fragment) {
        this.mIndex = mIndex;
        if (fragment != null) {
            this.mWho = fragment.mWho + ":" + this.mIndex;
            return;
        }
        this.mWho = "android:fragment:" + this.mIndex;
    }
    
    public void setInitialSavedState(final SavedState savedState) {
        if (this.mIndex >= 0) {
            throw new IllegalStateException("Fragment already active");
        }
        Bundle mState;
        if (savedState != null && savedState.mState != null) {
            mState = savedState.mState;
        }
        else {
            mState = null;
        }
        this.mSavedFragmentState = mState;
    }
    
    public void setMenuVisibility(final boolean mMenuVisible) {
        if (this.mMenuVisible != mMenuVisible) {
            this.mMenuVisible = mMenuVisible;
            if (this.mHasMenu && this.isAdded() && !this.isHidden()) {
                this.mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }
    
    public void setReenterTransition(final Object mReenterTransition) {
        this.mReenterTransition = mReenterTransition;
    }
    
    public void setRetainInstance(final boolean mRetainInstance) {
        if (mRetainInstance && this.mParentFragment != null) {
            throw new IllegalStateException("Can't retain fragements that are nested in other fragments");
        }
        this.mRetainInstance = mRetainInstance;
    }
    
    public void setReturnTransition(final Object mReturnTransition) {
        this.mReturnTransition = mReturnTransition;
    }
    
    public void setSharedElementEnterTransition(final Object mSharedElementEnterTransition) {
        this.mSharedElementEnterTransition = mSharedElementEnterTransition;
    }
    
    public void setSharedElementReturnTransition(final Object mSharedElementReturnTransition) {
        this.mSharedElementReturnTransition = mSharedElementReturnTransition;
    }
    
    public void setTargetFragment(final Fragment mTarget, final int mTargetRequestCode) {
        this.mTarget = mTarget;
        this.mTargetRequestCode = mTargetRequestCode;
    }
    
    public void setUserVisibleHint(final boolean mUserVisibleHint) {
        if (!this.mUserVisibleHint && mUserVisibleHint && this.mState < 4) {
            this.mFragmentManager.performPendingDeferredStart(this);
        }
        this.mDeferStart = !(this.mUserVisibleHint = mUserVisibleHint);
    }
    
    public boolean shouldShowRequestPermissionRationale(@NonNull final String s) {
        return this.mHost != null && this.mHost.onShouldShowRequestPermissionRationale(s);
    }
    
    public void startActivity(final Intent intent) {
        if (this.mHost == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        this.mHost.onStartActivityFromFragment(this, intent, -1);
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        if (this.mHost == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        this.mHost.onStartActivityFromFragment(this, intent, n);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        DebugUtils.buildShortClassTag(this, sb);
        if (this.mIndex >= 0) {
            sb.append(" #");
            sb.append(this.mIndex);
        }
        if (this.mFragmentId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            sb.append(" ");
            sb.append(this.mTag);
        }
        sb.append('}');
        return sb.toString();
    }
    
    public void unregisterForContextMenu(final View view) {
        view.setOnCreateContextMenuListener((View$OnCreateContextMenuListener)null);
    }
    
    public static class InstantiationException extends RuntimeException
    {
        public InstantiationException(final String s, final Exception ex) {
            super(s, ex);
        }
    }
    
    public static class SavedState implements Parcelable
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        final Bundle mState;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
                public SavedState createFromParcel(final Parcel parcel) {
                    return new SavedState(parcel, null);
                }
                
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            };
        }
        
        SavedState(final Bundle mState) {
            this.mState = mState;
        }
        
        SavedState(final Parcel parcel, final ClassLoader classLoader) {
            this.mState = parcel.readBundle();
            if (classLoader != null && this.mState != null) {
                this.mState.setClassLoader(classLoader);
            }
        }
        
        public int describeContents() {
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            parcel.writeBundle(this.mState);
        }
    }
}
