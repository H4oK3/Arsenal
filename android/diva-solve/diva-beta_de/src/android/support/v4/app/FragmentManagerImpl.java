// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.annotation.CallSuper;
import android.graphics.Paint;
import android.support.v4.util.DebugUtils;
import android.os.Handler;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.os.Looper;
import java.util.Arrays;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.content.res.Configuration;
import java.io.FileDescriptor;
import java.io.Writer;
import java.io.PrintWriter;
import android.support.v4.util.LogWriter;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.animation.Animation$AnimationListener;
import android.view.View;
import java.util.List;
import android.view.animation.ScaleAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.content.Context;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import java.util.ArrayList;
import java.lang.reflect.Field;
import android.view.animation.Interpolator;
import android.support.v4.view.LayoutInflaterFactory;

final class FragmentManagerImpl extends FragmentManager implements LayoutInflaterFactory
{
    static final Interpolator ACCELERATE_CUBIC;
    static final Interpolator ACCELERATE_QUINT;
    static final int ANIM_DUR = 220;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static boolean DEBUG = false;
    static final Interpolator DECELERATE_CUBIC;
    static final Interpolator DECELERATE_QUINT;
    static final boolean HONEYCOMB;
    static final String TAG = "FragmentManager";
    static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    static final String TARGET_STATE_TAG = "android:target_state";
    static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    static final String VIEW_STATE_TAG = "android:view_state";
    static Field sAnimationListenerField;
    ArrayList<Fragment> mActive;
    ArrayList<Fragment> mAdded;
    ArrayList<Integer> mAvailBackStackIndices;
    ArrayList<Integer> mAvailIndices;
    ArrayList<BackStackRecord> mBackStack;
    ArrayList<OnBackStackChangedListener> mBackStackChangeListeners;
    ArrayList<BackStackRecord> mBackStackIndices;
    FragmentContainer mContainer;
    FragmentController mController;
    ArrayList<Fragment> mCreatedMenus;
    int mCurState;
    boolean mDestroyed;
    Runnable mExecCommit;
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    FragmentHostCallback mHost;
    boolean mNeedMenuInvalidate;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList<Runnable> mPendingActions;
    SparseArray<Parcelable> mStateArray;
    Bundle mStateBundle;
    boolean mStateSaved;
    Runnable[] mTmpActions;
    
    static {
        boolean honeycomb = false;
        FragmentManagerImpl.DEBUG = false;
        if (Build$VERSION.SDK_INT >= 11) {
            honeycomb = true;
        }
        HONEYCOMB = honeycomb;
        FragmentManagerImpl.sAnimationListenerField = null;
        DECELERATE_QUINT = (Interpolator)new DecelerateInterpolator(2.5f);
        DECELERATE_CUBIC = (Interpolator)new DecelerateInterpolator(1.5f);
        ACCELERATE_QUINT = (Interpolator)new AccelerateInterpolator(2.5f);
        ACCELERATE_CUBIC = (Interpolator)new AccelerateInterpolator(1.5f);
    }
    
    FragmentManagerImpl() {
        this.mCurState = 0;
        this.mStateBundle = null;
        this.mStateArray = null;
        this.mExecCommit = new Runnable() {
            @Override
            public void run() {
                FragmentManagerImpl.this.execPendingActions();
            }
        };
    }
    
    private void checkStateLoss() {
        if (this.mStateSaved) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.mNoTransactionsBecause != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.mNoTransactionsBecause);
        }
    }
    
    static Animation makeFadeAnimation(final Context context, final float n, final float n2) {
        final AlphaAnimation alphaAnimation = new AlphaAnimation(n, n2);
        alphaAnimation.setInterpolator(FragmentManagerImpl.DECELERATE_CUBIC);
        alphaAnimation.setDuration(220L);
        return (Animation)alphaAnimation;
    }
    
    static Animation makeOpenCloseAnimation(final Context context, final float n, final float n2, final float n3, final float n4) {
        final AnimationSet set = new AnimationSet(false);
        final ScaleAnimation scaleAnimation = new ScaleAnimation(n, n2, n, n2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(FragmentManagerImpl.DECELERATE_QUINT);
        scaleAnimation.setDuration(220L);
        set.addAnimation((Animation)scaleAnimation);
        final AlphaAnimation alphaAnimation = new AlphaAnimation(n3, n4);
        alphaAnimation.setInterpolator(FragmentManagerImpl.DECELERATE_CUBIC);
        alphaAnimation.setDuration(220L);
        set.addAnimation((Animation)alphaAnimation);
        return (Animation)set;
    }
    
    static boolean modifiesAlpha(final Animation animation) {
        if (!(animation instanceof AlphaAnimation)) {
            if (animation instanceof AnimationSet) {
                final List animations = ((AnimationSet)animation).getAnimations();
                for (int i = 0; i < animations.size(); ++i) {
                    if (animations.get(i) instanceof AlphaAnimation) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }
    
    public static int reverseTransit(final int n) {
        switch (n) {
            default: {
                return 0;
            }
            case 4097: {
                return 8194;
            }
            case 8194: {
                return 4097;
            }
            case 4099: {
                return 4099;
            }
        }
    }
    
    private void setHWLayerAnimListenerIfAlpha(final View view, final Animation animation) {
        if (view == null || animation == null || !shouldRunOnHWLayer(view, animation)) {
            return;
        }
        Animation$AnimationListener animation$AnimationListener = null;
        while (true) {
            try {
                if (FragmentManagerImpl.sAnimationListenerField == null) {
                    (FragmentManagerImpl.sAnimationListenerField = Animation.class.getDeclaredField("mListener")).setAccessible(true);
                }
                animation$AnimationListener = (Animation$AnimationListener)FragmentManagerImpl.sAnimationListenerField.get(animation);
                animation.setAnimationListener((Animation$AnimationListener)new AnimateOnHWLayerIfNeededListener(view, animation, animation$AnimationListener));
            }
            catch (NoSuchFieldException ex) {
                Log.e("FragmentManager", "No field with the name mListener is found in Animation class", (Throwable)ex);
                continue;
            }
            catch (IllegalAccessException ex2) {
                Log.e("FragmentManager", "Cannot access Animation's mListener field", (Throwable)ex2);
                continue;
            }
            break;
        }
    }
    
    static boolean shouldRunOnHWLayer(final View view, final Animation animation) {
        return Build$VERSION.SDK_INT >= 19 && ViewCompat.getLayerType(view) == 0 && ViewCompat.hasOverlappingRendering(view) && modifiesAlpha(animation);
    }
    
    private void throwException(final RuntimeException ex) {
        Log.e("FragmentManager", ex.getMessage());
        Log.e("FragmentManager", "Activity state:");
        final PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
        while (true) {
            Label_0075: {
                if (this.mHost == null) {
                    break Label_0075;
                }
                try {
                    this.mHost.onDump("  ", null, printWriter, new String[0]);
                    throw ex;
                }
                catch (Exception ex2) {
                    Log.e("FragmentManager", "Failed dumping state", (Throwable)ex2);
                    throw ex;
                }
                try {
                    this.dump("  ", null, printWriter, new String[0]);
                    continue;
                }
                catch (Exception ex3) {
                    Log.e("FragmentManager", "Failed dumping state", (Throwable)ex3);
                    continue;
                }
            }
            continue;
        }
    }
    
    public static int transitToStyleIndex(final int n, final boolean b) {
        switch (n) {
            default: {
                return -1;
            }
            case 4097: {
                if (b) {
                    return 1;
                }
                return 2;
            }
            case 8194: {
                if (b) {
                    return 3;
                }
                return 4;
            }
            case 4099: {
                if (b) {
                    return 5;
                }
                return 6;
            }
        }
    }
    
    void addBackStackState(final BackStackRecord backStackRecord) {
        if (this.mBackStack == null) {
            this.mBackStack = new ArrayList<BackStackRecord>();
        }
        this.mBackStack.add(backStackRecord);
        this.reportBackStackChanged();
    }
    
    public void addFragment(final Fragment fragment, final boolean b) {
        if (this.mAdded == null) {
            this.mAdded = new ArrayList<Fragment>();
        }
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        this.makeActive(fragment);
        if (!fragment.mDetached) {
            if (this.mAdded.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            this.mAdded.add(fragment);
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            if (b) {
                this.moveToState(fragment);
            }
        }
    }
    
    @Override
    public void addOnBackStackChangedListener(final OnBackStackChangedListener onBackStackChangedListener) {
        if (this.mBackStackChangeListeners == null) {
            this.mBackStackChangeListeners = new ArrayList<OnBackStackChangedListener>();
        }
        this.mBackStackChangeListeners.add(onBackStackChangedListener);
    }
    
    public int allocBackStackIndex(final BackStackRecord backStackRecord) {
        synchronized (this) {
            if (this.mAvailBackStackIndices == null || this.mAvailBackStackIndices.size() <= 0) {
                if (this.mBackStackIndices == null) {
                    this.mBackStackIndices = new ArrayList<BackStackRecord>();
                }
                final int size = this.mBackStackIndices.size();
                if (FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Setting back stack index " + size + " to " + backStackRecord);
                }
                this.mBackStackIndices.add(backStackRecord);
                return size;
            }
            final int intValue = this.mAvailBackStackIndices.remove(this.mAvailBackStackIndices.size() - 1);
            if (FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Adding back stack index " + intValue + " with " + backStackRecord);
            }
            this.mBackStackIndices.set(intValue, backStackRecord);
            return intValue;
        }
    }
    
    public void attachController(final FragmentHostCallback mHost, final FragmentContainer mContainer, final Fragment mParent) {
        if (this.mHost != null) {
            throw new IllegalStateException("Already attached");
        }
        this.mHost = mHost;
        this.mContainer = mContainer;
        this.mParent = mParent;
    }
    
    public void attachFragment(final Fragment fragment, final int n, final int n2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (!fragment.mAdded) {
                if (this.mAdded == null) {
                    this.mAdded = new ArrayList<Fragment>();
                }
                if (this.mAdded.contains(fragment)) {
                    throw new IllegalStateException("Fragment already added: " + fragment);
                }
                if (FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "add from attach: " + fragment);
                }
                this.mAdded.add(fragment);
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                this.moveToState(fragment, this.mCurState, n, n2, false);
            }
        }
    }
    
    @Override
    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }
    
    public void detachFragment(final Fragment fragment, final int n, final int n2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (this.mAdded != null) {
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "remove from detach: " + fragment);
                    }
                    this.mAdded.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                this.moveToState(fragment, 1, n, n2, fragment.mAdded = false);
            }
        }
    }
    
    public void dispatchActivityCreated() {
        this.moveToState(2, this.mStateSaved = false);
    }
    
    public void dispatchConfigurationChanged(final Configuration configuration) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); ++i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null) {
                    fragment.performConfigurationChanged(configuration);
                }
            }
        }
    }
    
    public boolean dispatchContextItemSelected(final MenuItem menuItem) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); ++i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void dispatchCreate() {
        this.moveToState(1, this.mStateSaved = false);
    }
    
    public boolean dispatchCreateOptionsMenu(final Menu menu, final MenuInflater menuInflater) {
        boolean b = false;
        boolean b2 = false;
        ArrayList<Fragment> mCreatedMenus = null;
        ArrayList<Fragment> list = null;
        if (this.mAdded != null) {
            int n = 0;
            while (true) {
                mCreatedMenus = list;
                b = b2;
                if (n >= this.mAdded.size()) {
                    break;
                }
                final Fragment fragment = this.mAdded.get(n);
                ArrayList<Fragment> list2 = list;
                boolean b3 = b2;
                if (fragment != null) {
                    list2 = list;
                    b3 = b2;
                    if (fragment.performCreateOptionsMenu(menu, menuInflater)) {
                        b3 = true;
                        if ((list2 = list) == null) {
                            list2 = new ArrayList<Fragment>();
                        }
                        list2.add(fragment);
                    }
                }
                ++n;
                list = list2;
                b2 = b3;
            }
        }
        if (this.mCreatedMenus != null) {
            for (int i = 0; i < this.mCreatedMenus.size(); ++i) {
                final Fragment fragment2 = this.mCreatedMenus.get(i);
                if (mCreatedMenus == null || !mCreatedMenus.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = mCreatedMenus;
        return b;
    }
    
    public void dispatchDestroy() {
        this.mDestroyed = true;
        this.execPendingActions();
        this.moveToState(0, false);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
    }
    
    public void dispatchDestroyView() {
        this.moveToState(1, false);
    }
    
    public void dispatchLowMemory() {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); ++i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null) {
                    fragment.performLowMemory();
                }
            }
        }
    }
    
    public boolean dispatchOptionsItemSelected(final MenuItem menuItem) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); ++i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void dispatchOptionsMenuClosed(final Menu menu) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); ++i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null) {
                    fragment.performOptionsMenuClosed(menu);
                }
            }
        }
    }
    
    public void dispatchPause() {
        this.moveToState(4, false);
    }
    
    public boolean dispatchPrepareOptionsMenu(final Menu menu) {
        boolean b = false;
        boolean b2 = false;
        if (this.mAdded != null) {
            int n = 0;
            while (true) {
                b = b2;
                if (n >= this.mAdded.size()) {
                    break;
                }
                final Fragment fragment = this.mAdded.get(n);
                boolean b3 = b2;
                if (fragment != null) {
                    b3 = b2;
                    if (fragment.performPrepareOptionsMenu(menu)) {
                        b3 = true;
                    }
                }
                ++n;
                b2 = b3;
            }
        }
        return b;
    }
    
    public void dispatchReallyStop() {
        this.moveToState(2, false);
    }
    
    public void dispatchResume() {
        this.moveToState(5, this.mStateSaved = false);
    }
    
    public void dispatchStart() {
        this.moveToState(4, this.mStateSaved = false);
    }
    
    public void dispatchStop() {
        this.mStateSaved = true;
        this.moveToState(3, false);
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        final String string = s + "    ";
        if (this.mActive != null) {
            final int size = this.mActive.size();
            if (size > 0) {
                printWriter.print(s);
                printWriter.print("Active Fragments in ");
                printWriter.print(Integer.toHexString(System.identityHashCode(this)));
                printWriter.println(":");
                for (int i = 0; i < size; ++i) {
                    final Fragment fragment = this.mActive.get(i);
                    printWriter.print(s);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment);
                    if (fragment != null) {
                        fragment.dump(string, fileDescriptor, printWriter, array);
                    }
                }
            }
        }
        if (this.mAdded != null) {
            final int size2 = this.mAdded.size();
            if (size2 > 0) {
                printWriter.print(s);
                printWriter.println("Added Fragments:");
                for (int j = 0; j < size2; ++j) {
                    final Fragment fragment2 = this.mAdded.get(j);
                    printWriter.print(s);
                    printWriter.print("  #");
                    printWriter.print(j);
                    printWriter.print(": ");
                    printWriter.println(fragment2.toString());
                }
            }
        }
        if (this.mCreatedMenus != null) {
            final int size3 = this.mCreatedMenus.size();
            if (size3 > 0) {
                printWriter.print(s);
                printWriter.println("Fragments Created Menus:");
                for (int k = 0; k < size3; ++k) {
                    final Fragment fragment3 = this.mCreatedMenus.get(k);
                    printWriter.print(s);
                    printWriter.print("  #");
                    printWriter.print(k);
                    printWriter.print(": ");
                    printWriter.println(fragment3.toString());
                }
            }
        }
        if (this.mBackStack != null) {
            final int size4 = this.mBackStack.size();
            if (size4 > 0) {
                printWriter.print(s);
                printWriter.println("Back Stack:");
                for (int l = 0; l < size4; ++l) {
                    final BackStackRecord backStackRecord = this.mBackStack.get(l);
                    printWriter.print(s);
                    printWriter.print("  #");
                    printWriter.print(l);
                    printWriter.print(": ");
                    printWriter.println(backStackRecord.toString());
                    backStackRecord.dump(string, fileDescriptor, printWriter, array);
                }
            }
        }
        synchronized (this) {
            if (this.mBackStackIndices != null) {
                final int size5 = this.mBackStackIndices.size();
                if (size5 > 0) {
                    printWriter.print(s);
                    printWriter.println("Back Stack Indices:");
                    for (int n = 0; n < size5; ++n) {
                        final BackStackRecord backStackRecord2 = this.mBackStackIndices.get(n);
                        printWriter.print(s);
                        printWriter.print("  #");
                        printWriter.print(n);
                        printWriter.print(": ");
                        printWriter.println(backStackRecord2);
                    }
                }
            }
            if (this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                printWriter.print(s);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
            }
            // monitorexit(this)
            if (this.mPendingActions != null) {
                final int size6 = this.mPendingActions.size();
                if (size6 > 0) {
                    printWriter.print(s);
                    printWriter.println("Pending Actions:");
                    for (int n2 = 0; n2 < size6; ++n2) {
                        final Runnable runnable = this.mPendingActions.get(n2);
                        printWriter.print(s);
                        printWriter.print("  #");
                        printWriter.print(n2);
                        printWriter.print(": ");
                        printWriter.println(runnable);
                    }
                }
            }
        }
        final String s2;
        printWriter.print(s2);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(s2);
        printWriter.print("  mHost=");
        printWriter.println(this.mHost);
        printWriter.print(s2);
        printWriter.print("  mContainer=");
        printWriter.println(this.mContainer);
        if (this.mParent != null) {
            printWriter.print(s2);
            printWriter.print("  mParent=");
            printWriter.println(this.mParent);
        }
        printWriter.print(s2);
        printWriter.print("  mCurState=");
        printWriter.print(this.mCurState);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.mStateSaved);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            printWriter.print(s2);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.mNeedMenuInvalidate);
        }
        if (this.mNoTransactionsBecause != null) {
            printWriter.print(s2);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.mNoTransactionsBecause);
        }
        if (this.mAvailIndices != null && this.mAvailIndices.size() > 0) {
            printWriter.print(s2);
            printWriter.print("  mAvailIndices: ");
            printWriter.println(Arrays.toString(this.mAvailIndices.toArray()));
        }
    }
    
    public void enqueueAction(final Runnable runnable, final boolean b) {
        if (!b) {
            this.checkStateLoss();
        }
        synchronized (this) {
            if (this.mDestroyed || this.mHost == null) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
        if (this.mPendingActions == null) {
            this.mPendingActions = new ArrayList<Runnable>();
        }
        final Runnable runnable2;
        this.mPendingActions.add(runnable2);
        if (this.mPendingActions.size() == 1) {
            this.mHost.getHandler().removeCallbacks(this.mExecCommit);
            this.mHost.getHandler().post(this.mExecCommit);
        }
    }
    // monitorexit(this)
    
    public boolean execPendingActions() {
        if (this.mExecutingActions) {
            throw new IllegalStateException("Recursive entry to executePendingTransactions");
        }
        if (Looper.myLooper() != this.mHost.getHandler().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of process");
        }
        boolean b = false;
        while (true) {
            int size = 0;
            Label_0263: {
                synchronized (this) {
                    if (this.mPendingActions == null || this.mPendingActions.size() == 0) {
                        // monitorexit(this)
                        if (this.mHavePendingDeferredStart) {
                            size = 0;
                            int n;
                            for (int i = 0; i < this.mActive.size(); ++i, size = n) {
                                final Fragment fragment = this.mActive.get(i);
                                n = size;
                                if (fragment != null) {
                                    n = size;
                                    if (fragment.mLoaderManager != null) {
                                        n = (size | (fragment.mLoaderManager.hasRunningLoaders() ? 1 : 0));
                                    }
                                }
                            }
                            break Label_0263;
                        }
                        break;
                    }
                    else {
                        size = this.mPendingActions.size();
                        if (this.mTmpActions == null || this.mTmpActions.length < size) {
                            this.mTmpActions = new Runnable[size];
                        }
                        this.mPendingActions.toArray(this.mTmpActions);
                        this.mPendingActions.clear();
                        this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                        // monitorexit(this)
                        this.mExecutingActions = true;
                        for (int j = 0; j < size; ++j) {
                            this.mTmpActions[j].run();
                            this.mTmpActions[j] = null;
                        }
                    }
                }
                this.mExecutingActions = false;
                b = true;
                continue;
            }
            if (size == 0) {
                this.mHavePendingDeferredStart = false;
                this.startPendingDeferredFragments();
                break;
            }
            break;
        }
        return b;
    }
    
    @Override
    public boolean executePendingTransactions() {
        return this.execPendingActions();
    }
    
    @Override
    public Fragment findFragmentById(final int n) {
        if (this.mAdded != null) {
            for (int i = this.mAdded.size() - 1; i >= 0; --i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null && fragment.mFragmentId == n) {
                    return fragment;
                }
            }
        }
        Label_0054: {
            break Label_0054;
        }
        if (this.mActive != null) {
            for (int j = this.mActive.size() - 1; j >= 0; --j) {
                final Fragment fragment2 = this.mActive.get(j);
                if (fragment2 != null) {
                    final Fragment fragment = fragment2;
                    if (fragment2.mFragmentId == n) {
                        return fragment;
                    }
                }
            }
        }
        return null;
    }
    
    @Override
    public Fragment findFragmentByTag(final String s) {
        if (this.mAdded != null && s != null) {
            for (int i = this.mAdded.size() - 1; i >= 0; --i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null && s.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        Label_0061: {
            break Label_0061;
        }
        if (this.mActive != null && s != null) {
            for (int j = this.mActive.size() - 1; j >= 0; --j) {
                final Fragment fragment2 = this.mActive.get(j);
                if (fragment2 != null) {
                    final Fragment fragment = fragment2;
                    if (s.equals(fragment2.mTag)) {
                        return fragment;
                    }
                }
            }
        }
        return null;
    }
    
    public Fragment findFragmentByWho(final String s) {
        if (this.mActive != null && s != null) {
            for (int i = this.mActive.size() - 1; i >= 0; --i) {
                final Fragment fragment = this.mActive.get(i);
                if (fragment != null) {
                    final Fragment fragmentByWho = fragment.findFragmentByWho(s);
                    if (fragmentByWho != null) {
                        return fragmentByWho;
                    }
                }
            }
        }
        return null;
    }
    
    public void freeBackStackIndex(final int n) {
        synchronized (this) {
            this.mBackStackIndices.set(n, null);
            if (this.mAvailBackStackIndices == null) {
                this.mAvailBackStackIndices = new ArrayList<Integer>();
            }
            if (FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Freeing back stack index " + n);
            }
            this.mAvailBackStackIndices.add(n);
        }
    }
    
    @Override
    public BackStackEntry getBackStackEntryAt(final int n) {
        return this.mBackStack.get(n);
    }
    
    @Override
    public int getBackStackEntryCount() {
        if (this.mBackStack != null) {
            return this.mBackStack.size();
        }
        return 0;
    }
    
    @Override
    public Fragment getFragment(final Bundle bundle, final String s) {
        final int int1 = bundle.getInt(s, -1);
        Fragment fragment;
        if (int1 == -1) {
            fragment = null;
        }
        else {
            if (int1 >= this.mActive.size()) {
                this.throwException(new IllegalStateException("Fragment no longer exists for key " + s + ": index " + int1));
            }
            final Fragment fragment2 = this.mActive.get(int1);
            if ((fragment = fragment2) == null) {
                this.throwException(new IllegalStateException("Fragment no longer exists for key " + s + ": index " + int1));
                return fragment2;
            }
        }
        return fragment;
    }
    
    @Override
    public List<Fragment> getFragments() {
        return this.mActive;
    }
    
    LayoutInflaterFactory getLayoutInflaterFactory() {
        return this;
    }
    
    public void hideFragment(final Fragment fragment, final int n, final int n2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            if (fragment.mView != null) {
                final Animation loadAnimation = this.loadAnimation(fragment, n, false, n2);
                if (loadAnimation != null) {
                    this.setHWLayerAnimListenerIfAlpha(fragment.mView, loadAnimation);
                    fragment.mView.startAnimation(loadAnimation);
                }
                fragment.mView.setVisibility(8);
            }
            if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.onHiddenChanged(true);
        }
    }
    
    @Override
    public boolean isDestroyed() {
        return this.mDestroyed;
    }
    
    Animation loadAnimation(final Fragment fragment, int n, final boolean b, final int n2) {
        final Animation onCreateAnimation = fragment.onCreateAnimation(n, b, fragment.mNextAnim);
        if (onCreateAnimation != null) {
            return onCreateAnimation;
        }
        if (fragment.mNextAnim != 0) {
            final Animation loadAnimation = AnimationUtils.loadAnimation(this.mHost.getContext(), fragment.mNextAnim);
            if (loadAnimation != null) {
                return loadAnimation;
            }
        }
        if (n == 0) {
            return null;
        }
        n = transitToStyleIndex(n, b);
        if (n < 0) {
            return null;
        }
        switch (n) {
            default: {
                n = n2;
                if (n2 == 0) {
                    n = n2;
                    if (this.mHost.onHasWindowAnimations()) {
                        n = this.mHost.onGetWindowAnimations();
                    }
                }
                if (n == 0) {
                    return null;
                }
                return null;
            }
            case 1: {
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.125f, 1.0f, 0.0f, 1.0f);
            }
            case 2: {
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 0.975f, 1.0f, 0.0f);
            }
            case 3: {
                return makeOpenCloseAnimation(this.mHost.getContext(), 0.975f, 1.0f, 0.0f, 1.0f);
            }
            case 4: {
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 1.075f, 1.0f, 0.0f);
            }
            case 5: {
                return makeFadeAnimation(this.mHost.getContext(), 0.0f, 1.0f);
            }
            case 6: {
                return makeFadeAnimation(this.mHost.getContext(), 1.0f, 0.0f);
            }
        }
    }
    
    void makeActive(final Fragment fragment) {
        if (fragment.mIndex < 0) {
            if (this.mAvailIndices == null || this.mAvailIndices.size() <= 0) {
                if (this.mActive == null) {
                    this.mActive = new ArrayList<Fragment>();
                }
                fragment.setIndex(this.mActive.size(), this.mParent);
                this.mActive.add(fragment);
            }
            else {
                fragment.setIndex(this.mAvailIndices.remove(this.mAvailIndices.size() - 1), this.mParent);
                this.mActive.set(fragment.mIndex, fragment);
            }
            if (FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Allocated fragment index " + fragment);
            }
        }
    }
    
    void makeInactive(final Fragment fragment) {
        if (fragment.mIndex < 0) {
            return;
        }
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Freeing fragment index " + fragment);
        }
        this.mActive.set(fragment.mIndex, null);
        if (this.mAvailIndices == null) {
            this.mAvailIndices = new ArrayList<Integer>();
        }
        this.mAvailIndices.add(fragment.mIndex);
        this.mHost.inactivateFragment(fragment.mWho);
        fragment.initState();
    }
    
    void moveToState(final int mCurState, final int n, final int n2, final boolean b) {
        if (this.mHost == null && mCurState != 0) {
            throw new IllegalStateException("No host");
        }
        if (b || this.mCurState != mCurState) {
            this.mCurState = mCurState;
            if (this.mActive != null) {
                boolean b2 = false;
                boolean b3;
                for (int i = 0; i < this.mActive.size(); ++i, b2 = b3) {
                    final Fragment fragment = this.mActive.get(i);
                    b3 = b2;
                    if (fragment != null) {
                        this.moveToState(fragment, mCurState, n, n2, false);
                        b3 = b2;
                        if (fragment.mLoaderManager != null) {
                            b3 = (b2 | fragment.mLoaderManager.hasRunningLoaders());
                        }
                    }
                }
                if (!b2) {
                    this.startPendingDeferredFragments();
                }
                if (this.mNeedMenuInvalidate && this.mHost != null && this.mCurState == 5) {
                    this.mHost.onSupportInvalidateOptionsMenu();
                    this.mNeedMenuInvalidate = false;
                }
            }
        }
    }
    
    void moveToState(final int n, final boolean b) {
        this.moveToState(n, 0, 0, b);
    }
    
    void moveToState(final Fragment fragment) {
        this.moveToState(fragment, this.mCurState, 0, 0, false);
    }
    
    void moveToState(final Fragment fragment, int n, final int n2, final int n3, final boolean b) {
        int n4 = 0;
        Label_0028: {
            if (fragment.mAdded) {
                n4 = n;
                if (!fragment.mDetached) {
                    break Label_0028;
                }
            }
            if ((n4 = n) > 1) {
                n4 = 1;
            }
        }
        int mState = n4;
        if (fragment.mRemoving && (mState = n4) > fragment.mState) {
            mState = fragment.mState;
        }
        n = mState;
        if (fragment.mDeferStart) {
            n = mState;
            if (fragment.mState < 4 && (n = mState) > 3) {
                n = 3;
            }
        }
        int mState2 = 0;
        if (fragment.mState < n) {
            if (fragment.mFromLayout && !fragment.mInLayout) {
                return;
            }
            if (fragment.mAnimatingAway != null) {
                fragment.mAnimatingAway = null;
                this.moveToState(fragment, fragment.mStateAfterAnimating, 0, 0, true);
            }
            int n5 = n;
            int n6 = n;
            int n7 = n;
            Label_0578: {
                switch (fragment.mState) {
                    default: {
                        mState2 = n;
                        break;
                    }
                    case 0: {
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "moveto CREATED: " + fragment);
                        }
                        int n8 = n;
                        if (fragment.mSavedFragmentState != null) {
                            fragment.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
                            fragment.mSavedViewState = (SparseArray<Parcelable>)fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                            fragment.mTarget = this.getFragment(fragment.mSavedFragmentState, "android:target_state");
                            if (fragment.mTarget != null) {
                                fragment.mTargetRequestCode = fragment.mSavedFragmentState.getInt("android:target_req_state", 0);
                            }
                            fragment.mUserVisibleHint = fragment.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
                            n8 = n;
                            if (!fragment.mUserVisibleHint) {
                                fragment.mDeferStart = true;
                                if ((n8 = n) > 3) {
                                    n8 = 3;
                                }
                            }
                        }
                        fragment.mHost = this.mHost;
                        fragment.mParentFragment = this.mParent;
                        FragmentManagerImpl mFragmentManager;
                        if (this.mParent != null) {
                            mFragmentManager = this.mParent.mChildFragmentManager;
                        }
                        else {
                            mFragmentManager = this.mHost.getFragmentManagerImpl();
                        }
                        fragment.mFragmentManager = mFragmentManager;
                        fragment.mCalled = false;
                        fragment.onAttach(this.mHost.getContext());
                        if (!fragment.mCalled) {
                            throw new SuperNotCalledException("Fragment " + fragment + " did not call through to super.onAttach()");
                        }
                        if (fragment.mParentFragment == null) {
                            this.mHost.onAttachFragment(fragment);
                        }
                        if (!fragment.mRetaining) {
                            fragment.performCreate(fragment.mSavedFragmentState);
                        }
                        fragment.mRetaining = false;
                        n5 = n8;
                        if (!fragment.mFromLayout) {
                            break Label_0578;
                        }
                        fragment.mView = fragment.performCreateView(fragment.getLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
                        if (fragment.mView != null) {
                            fragment.mInnerView = fragment.mView;
                            if (Build$VERSION.SDK_INT >= 11) {
                                ViewCompat.setSaveFromParentEnabled(fragment.mView, false);
                            }
                            else {
                                fragment.mView = (View)NoSaveStateFrameLayout.wrap(fragment.mView);
                            }
                            if (fragment.mHidden) {
                                fragment.mView.setVisibility(8);
                            }
                            fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                            n5 = n8;
                            break Label_0578;
                        }
                        fragment.mInnerView = null;
                        n5 = n8;
                        break Label_0578;
                    }
                    case 1: {
                        if ((n6 = n5) > 1) {
                            if (FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + fragment);
                            }
                            if (!fragment.mFromLayout) {
                                ViewGroup mContainer = null;
                                if (fragment.mContainerId != 0) {
                                    final ViewGroup viewGroup = (ViewGroup)this.mContainer.onFindViewById(fragment.mContainerId);
                                    if ((mContainer = viewGroup) == null) {
                                        mContainer = viewGroup;
                                        if (!fragment.mRestored) {
                                            this.throwException(new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(fragment.mContainerId) + " (" + fragment.getResources().getResourceName(fragment.mContainerId) + ") for fragment " + fragment));
                                            mContainer = viewGroup;
                                        }
                                    }
                                }
                                fragment.mContainer = mContainer;
                                fragment.mView = fragment.performCreateView(fragment.getLayoutInflater(fragment.mSavedFragmentState), mContainer, fragment.mSavedFragmentState);
                                if (fragment.mView != null) {
                                    fragment.mInnerView = fragment.mView;
                                    if (Build$VERSION.SDK_INT >= 11) {
                                        ViewCompat.setSaveFromParentEnabled(fragment.mView, false);
                                    }
                                    else {
                                        fragment.mView = (View)NoSaveStateFrameLayout.wrap(fragment.mView);
                                    }
                                    if (mContainer != null) {
                                        final Animation loadAnimation = this.loadAnimation(fragment, n2, true, n3);
                                        if (loadAnimation != null) {
                                            this.setHWLayerAnimListenerIfAlpha(fragment.mView, loadAnimation);
                                            fragment.mView.startAnimation(loadAnimation);
                                        }
                                        mContainer.addView(fragment.mView);
                                    }
                                    if (fragment.mHidden) {
                                        fragment.mView.setVisibility(8);
                                    }
                                    fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                                }
                                else {
                                    fragment.mInnerView = null;
                                }
                            }
                            fragment.performActivityCreated(fragment.mSavedFragmentState);
                            if (fragment.mView != null) {
                                fragment.restoreViewState(fragment.mSavedFragmentState);
                            }
                            fragment.mSavedFragmentState = null;
                            n6 = n5;
                        }
                    }
                    case 2:
                    case 3: {
                        if ((n7 = n6) > 3) {
                            if (FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "moveto STARTED: " + fragment);
                            }
                            fragment.performStart();
                            n7 = n6;
                        }
                    }
                    case 4: {
                        if ((mState2 = n7) > 4) {
                            if (FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "moveto RESUMED: " + fragment);
                            }
                            fragment.mResumed = true;
                            fragment.performResume();
                            fragment.mSavedFragmentState = null;
                            fragment.mSavedViewState = null;
                            mState2 = n7;
                            break;
                        }
                        break;
                    }
                }
            }
        }
        else if (fragment.mState > (mState2 = n)) {
            switch (fragment.mState) {
                default: {
                    mState2 = n;
                    break;
                }
                case 3: {
                    if (n < 3) {
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "movefrom STOPPED: " + fragment);
                        }
                        fragment.performReallyStop();
                    }
                }
                case 2: {
                    if (n < 2) {
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + fragment);
                        }
                        if (fragment.mView != null && this.mHost.onShouldSaveFragmentState(fragment) && fragment.mSavedViewState == null) {
                            this.saveFragmentViewState(fragment);
                        }
                        fragment.performDestroyView();
                        if (fragment.mView != null && fragment.mContainer != null) {
                            Animation loadAnimation2;
                            final Animation animation = loadAnimation2 = null;
                            if (this.mCurState > 0) {
                                loadAnimation2 = animation;
                                if (!this.mDestroyed) {
                                    loadAnimation2 = this.loadAnimation(fragment, n2, false, n3);
                                }
                            }
                            if (loadAnimation2 != null) {
                                fragment.mAnimatingAway = fragment.mView;
                                fragment.mStateAfterAnimating = n;
                                loadAnimation2.setAnimationListener((Animation$AnimationListener)new AnimateOnHWLayerIfNeededListener(fragment.mView, loadAnimation2) {
                                    @Override
                                    public void onAnimationEnd(final Animation animation) {
                                        super.onAnimationEnd(animation);
                                        if (fragment.mAnimatingAway != null) {
                                            fragment.mAnimatingAway = null;
                                            FragmentManagerImpl.this.moveToState(fragment, fragment.mStateAfterAnimating, 0, 0, false);
                                        }
                                    }
                                });
                                fragment.mView.startAnimation(loadAnimation2);
                            }
                            fragment.mContainer.removeView(fragment.mView);
                        }
                        fragment.mContainer = null;
                        fragment.mView = null;
                        fragment.mInnerView = null;
                    }
                }
                case 1: {
                    if ((mState2 = n) >= 1) {
                        break;
                    }
                    if (this.mDestroyed && fragment.mAnimatingAway != null) {
                        final View mAnimatingAway = fragment.mAnimatingAway;
                        fragment.mAnimatingAway = null;
                        mAnimatingAway.clearAnimation();
                    }
                    if (fragment.mAnimatingAway != null) {
                        fragment.mStateAfterAnimating = n;
                        mState2 = 1;
                        break;
                    }
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "movefrom CREATED: " + fragment);
                    }
                    if (!fragment.mRetaining) {
                        fragment.performDestroy();
                    }
                    fragment.mCalled = false;
                    fragment.onDetach();
                    if (!fragment.mCalled) {
                        throw new SuperNotCalledException("Fragment " + fragment + " did not call through to super.onDetach()");
                    }
                    mState2 = n;
                    if (b) {
                        break;
                    }
                    if (!fragment.mRetaining) {
                        this.makeInactive(fragment);
                        mState2 = n;
                        break;
                    }
                    fragment.mHost = null;
                    fragment.mParentFragment = null;
                    fragment.mFragmentManager = null;
                    fragment.mChildFragmentManager = null;
                    mState2 = n;
                    break;
                }
                case 5: {
                    if (n < 5) {
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "movefrom RESUMED: " + fragment);
                        }
                        fragment.performPause();
                        fragment.mResumed = false;
                    }
                }
                case 4: {
                    if (n < 4) {
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "movefrom STARTED: " + fragment);
                        }
                        fragment.performStop();
                    }
                }
            }
        }
        fragment.mState = mState2;
    }
    
    public void noteStateNotSaved() {
        this.mStateSaved = false;
    }
    
    @Override
    public View onCreateView(final View view, String attributeValue, final Context context, final AttributeSet set) {
        if ("fragment".equals(attributeValue)) {
            attributeValue = set.getAttributeValue((String)null, "class");
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, FragmentTag.Fragment);
            String string;
            if ((string = attributeValue) == null) {
                string = obtainStyledAttributes.getString(0);
            }
            final int resourceId = obtainStyledAttributes.getResourceId(1, -1);
            final String string2 = obtainStyledAttributes.getString(2);
            obtainStyledAttributes.recycle();
            if (Fragment.isSupportFragmentClass(this.mHost.getContext(), string)) {
                int id;
                if (view != null) {
                    id = view.getId();
                }
                else {
                    id = 0;
                }
                if (id == -1 && resourceId == -1 && string2 == null) {
                    throw new IllegalArgumentException(set.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + string);
                }
                Fragment fragmentById;
                if (resourceId != -1) {
                    fragmentById = this.findFragmentById(resourceId);
                }
                else {
                    fragmentById = null;
                }
                Fragment fragmentByTag = fragmentById;
                if (fragmentById == null) {
                    fragmentByTag = fragmentById;
                    if (string2 != null) {
                        fragmentByTag = this.findFragmentByTag(string2);
                    }
                }
                Fragment fragmentById2;
                if ((fragmentById2 = fragmentByTag) == null) {
                    fragmentById2 = fragmentByTag;
                    if (id != -1) {
                        fragmentById2 = this.findFragmentById(id);
                    }
                }
                if (FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + string + " existing=" + fragmentById2);
                }
                Fragment instantiate;
                if (fragmentById2 == null) {
                    instantiate = Fragment.instantiate(context, string);
                    instantiate.mFromLayout = true;
                    int mFragmentId;
                    if (resourceId != 0) {
                        mFragmentId = resourceId;
                    }
                    else {
                        mFragmentId = id;
                    }
                    instantiate.mFragmentId = mFragmentId;
                    instantiate.mContainerId = id;
                    instantiate.mTag = string2;
                    instantiate.mInLayout = true;
                    instantiate.mFragmentManager = this;
                    instantiate.mHost = this.mHost;
                    instantiate.onInflate(this.mHost.getContext(), set, instantiate.mSavedFragmentState);
                    this.addFragment(instantiate, true);
                }
                else {
                    if (fragmentById2.mInLayout) {
                        throw new IllegalArgumentException(set.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string2 + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + string);
                    }
                    fragmentById2.mInLayout = true;
                    instantiate = fragmentById2;
                    if (!fragmentById2.mRetaining) {
                        fragmentById2.onInflate(this.mHost.getContext(), set, fragmentById2.mSavedFragmentState);
                        instantiate = fragmentById2;
                    }
                }
                if (this.mCurState < 1 && instantiate.mFromLayout) {
                    this.moveToState(instantiate, 1, 0, 0, false);
                }
                else {
                    this.moveToState(instantiate);
                }
                if (instantiate.mView == null) {
                    throw new IllegalStateException("Fragment " + string + " did not create a view.");
                }
                if (resourceId != 0) {
                    instantiate.mView.setId(resourceId);
                }
                if (instantiate.mView.getTag() == null) {
                    instantiate.mView.setTag((Object)string2);
                }
                return instantiate.mView;
            }
        }
        return null;
    }
    
    public void performPendingDeferredStart(final Fragment fragment) {
        if (fragment.mDeferStart) {
            if (!this.mExecutingActions) {
                fragment.mDeferStart = false;
                this.moveToState(fragment, this.mCurState, 0, 0, false);
                return;
            }
            this.mHavePendingDeferredStart = true;
        }
    }
    
    @Override
    public void popBackStack() {
        this.enqueueAction(new Runnable() {
            @Override
            public void run() {
                FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mHost.getHandler(), null, -1, 0);
            }
        }, false);
    }
    
    @Override
    public void popBackStack(final int n, final int n2) {
        if (n < 0) {
            throw new IllegalArgumentException("Bad id: " + n);
        }
        this.enqueueAction(new Runnable() {
            @Override
            public void run() {
                FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mHost.getHandler(), null, n, n2);
            }
        }, false);
    }
    
    @Override
    public void popBackStack(final String s, final int n) {
        this.enqueueAction(new Runnable() {
            @Override
            public void run() {
                FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mHost.getHandler(), s, -1, n);
            }
        }, false);
    }
    
    @Override
    public boolean popBackStackImmediate() {
        this.checkStateLoss();
        this.executePendingTransactions();
        return this.popBackStackState(this.mHost.getHandler(), null, -1, 0);
    }
    
    @Override
    public boolean popBackStackImmediate(final int n, final int n2) {
        this.checkStateLoss();
        this.executePendingTransactions();
        if (n < 0) {
            throw new IllegalArgumentException("Bad id: " + n);
        }
        return this.popBackStackState(this.mHost.getHandler(), null, n, n2);
    }
    
    @Override
    public boolean popBackStackImmediate(final String s, final int n) {
        this.checkStateLoss();
        this.executePendingTransactions();
        return this.popBackStackState(this.mHost.getHandler(), s, -1, n);
    }
    
    boolean popBackStackState(final Handler handler, final String s, int i, int n) {
        if (this.mBackStack == null) {
            return false;
        }
        if (s == null && i < 0 && (n & 0x1) == 0x0) {
            i = this.mBackStack.size() - 1;
            if (i < 0) {
                return false;
            }
            final BackStackRecord backStackRecord = this.mBackStack.remove(i);
            final SparseArray sparseArray = new SparseArray();
            final SparseArray sparseArray2 = new SparseArray();
            backStackRecord.calculateBackFragments((SparseArray<Fragment>)sparseArray, (SparseArray<Fragment>)sparseArray2);
            backStackRecord.popFromBackStack(true, null, (SparseArray<Fragment>)sparseArray, (SparseArray<Fragment>)sparseArray2);
            this.reportBackStackChanged();
        }
        else {
            int n2 = -1;
            if (s != null || i >= 0) {
                int j;
                for (j = this.mBackStack.size() - 1; j >= 0; --j) {
                    final BackStackRecord backStackRecord2 = this.mBackStack.get(j);
                    if ((s != null && s.equals(backStackRecord2.getName())) || (i >= 0 && i == backStackRecord2.mIndex)) {
                        break;
                    }
                }
                if (j < 0) {
                    return false;
                }
                n2 = j;
                if ((n & 0x1) != 0x0) {
                    n = j - 1;
                    while (true) {
                        n2 = n;
                        if (n < 0) {
                            break;
                        }
                        final BackStackRecord backStackRecord3 = this.mBackStack.get(n);
                        if (s == null || !s.equals(backStackRecord3.getName())) {
                            n2 = n;
                            if (i < 0) {
                                break;
                            }
                            n2 = n;
                            if (i != backStackRecord3.mIndex) {
                                break;
                            }
                        }
                        --n;
                    }
                }
            }
            if (n2 == this.mBackStack.size() - 1) {
                return false;
            }
            final ArrayList<BackStackRecord> list = new ArrayList<BackStackRecord>();
            for (i = this.mBackStack.size() - 1; i > n2; --i) {
                list.add(this.mBackStack.remove(i));
            }
            n = list.size() - 1;
            final SparseArray sparseArray3 = new SparseArray();
            final SparseArray sparseArray4 = new SparseArray();
            for (i = 0; i <= n; ++i) {
                list.get(i).calculateBackFragments((SparseArray<Fragment>)sparseArray3, (SparseArray<Fragment>)sparseArray4);
            }
            BackStackRecord.TransitionState popFromBackStack = null;
            BackStackRecord backStackRecord4;
            boolean b;
            for (i = 0; i <= n; ++i) {
                if (FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Popping back stack state: " + list.get(i));
                }
                backStackRecord4 = list.get(i);
                if (i == n) {
                    b = true;
                }
                else {
                    b = false;
                }
                popFromBackStack = backStackRecord4.popFromBackStack(b, popFromBackStack, (SparseArray<Fragment>)sparseArray3, (SparseArray<Fragment>)sparseArray4);
            }
            this.reportBackStackChanged();
        }
        return true;
    }
    
    @Override
    public void putFragment(final Bundle bundle, final String s, final Fragment fragment) {
        if (fragment.mIndex < 0) {
            this.throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putInt(s, fragment.mIndex);
    }
    
    public void removeFragment(final Fragment fragment, final int n, final int n2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean b;
        if (!fragment.isInBackStack()) {
            b = true;
        }
        else {
            b = false;
        }
        if (!fragment.mDetached || b) {
            if (this.mAdded != null) {
                this.mAdded.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
            int n3;
            if (b) {
                n3 = 0;
            }
            else {
                n3 = 1;
            }
            this.moveToState(fragment, n3, n, n2, false);
        }
    }
    
    @Override
    public void removeOnBackStackChangedListener(final OnBackStackChangedListener onBackStackChangedListener) {
        if (this.mBackStackChangeListeners != null) {
            this.mBackStackChangeListeners.remove(onBackStackChangedListener);
        }
    }
    
    void reportBackStackChanged() {
        if (this.mBackStackChangeListeners != null) {
            for (int i = 0; i < this.mBackStackChangeListeners.size(); ++i) {
                this.mBackStackChangeListeners.get(i).onBackStackChanged();
            }
        }
    }
    
    void restoreAllState(final Parcelable parcelable, final List<Fragment> list) {
        if (parcelable != null) {
            final FragmentManagerState fragmentManagerState = (FragmentManagerState)parcelable;
            if (fragmentManagerState.mActive != null) {
                if (list != null) {
                    for (int i = 0; i < list.size(); ++i) {
                        final Fragment mInstance = list.get(i);
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + mInstance);
                        }
                        final FragmentState fragmentState = fragmentManagerState.mActive[mInstance.mIndex];
                        fragmentState.mInstance = mInstance;
                        mInstance.mSavedViewState = null;
                        mInstance.mBackStackNesting = 0;
                        mInstance.mInLayout = false;
                        mInstance.mAdded = false;
                        mInstance.mTarget = null;
                        if (fragmentState.mSavedFragmentState != null) {
                            fragmentState.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
                            mInstance.mSavedViewState = (SparseArray<Parcelable>)fragmentState.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                            mInstance.mSavedFragmentState = fragmentState.mSavedFragmentState;
                        }
                    }
                }
                this.mActive = new ArrayList<Fragment>(fragmentManagerState.mActive.length);
                if (this.mAvailIndices != null) {
                    this.mAvailIndices.clear();
                }
                for (int j = 0; j < fragmentManagerState.mActive.length; ++j) {
                    final FragmentState fragmentState2 = fragmentManagerState.mActive[j];
                    if (fragmentState2 != null) {
                        final Fragment instantiate = fragmentState2.instantiate(this.mHost, this.mParent);
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: active #" + j + ": " + instantiate);
                        }
                        this.mActive.add(instantiate);
                        fragmentState2.mInstance = null;
                    }
                    else {
                        this.mActive.add(null);
                        if (this.mAvailIndices == null) {
                            this.mAvailIndices = new ArrayList<Integer>();
                        }
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: avail #" + j);
                        }
                        this.mAvailIndices.add(j);
                    }
                }
                if (list != null) {
                    for (int k = 0; k < list.size(); ++k) {
                        final Fragment fragment = list.get(k);
                        if (fragment.mTargetIndex >= 0) {
                            if (fragment.mTargetIndex < this.mActive.size()) {
                                fragment.mTarget = this.mActive.get(fragment.mTargetIndex);
                            }
                            else {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + fragment + " target no longer exists: " + fragment.mTargetIndex);
                                fragment.mTarget = null;
                            }
                        }
                    }
                }
                if (fragmentManagerState.mAdded != null) {
                    this.mAdded = new ArrayList<Fragment>(fragmentManagerState.mAdded.length);
                    for (int l = 0; l < fragmentManagerState.mAdded.length; ++l) {
                        final Fragment fragment2 = this.mActive.get(fragmentManagerState.mAdded[l]);
                        if (fragment2 == null) {
                            this.throwException(new IllegalStateException("No instantiated fragment for index #" + fragmentManagerState.mAdded[l]));
                        }
                        fragment2.mAdded = true;
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: added #" + l + ": " + fragment2);
                        }
                        if (this.mAdded.contains(fragment2)) {
                            throw new IllegalStateException("Already added!");
                        }
                        this.mAdded.add(fragment2);
                    }
                }
                else {
                    this.mAdded = null;
                }
                if (fragmentManagerState.mBackStack == null) {
                    this.mBackStack = null;
                    return;
                }
                this.mBackStack = new ArrayList<BackStackRecord>(fragmentManagerState.mBackStack.length);
                for (int n = 0; n < fragmentManagerState.mBackStack.length; ++n) {
                    final BackStackRecord instantiate2 = fragmentManagerState.mBackStack[n].instantiate(this);
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "restoreAllState: back stack #" + n + " (index " + instantiate2.mIndex + "): " + instantiate2);
                        instantiate2.dump("  ", new PrintWriter(new LogWriter("FragmentManager")), false);
                    }
                    this.mBackStack.add(instantiate2);
                    if (instantiate2.mIndex >= 0) {
                        this.setBackStackIndex(instantiate2.mIndex, instantiate2);
                    }
                }
            }
        }
    }
    
    ArrayList<Fragment> retainNonConfig() {
        ArrayList<Fragment> list = null;
        ArrayList<Fragment> list2 = null;
        if (this.mActive != null) {
            int n = 0;
            while (true) {
                list = list2;
                if (n >= this.mActive.size()) {
                    break;
                }
                final Fragment fragment = this.mActive.get(n);
                ArrayList<Fragment> list3 = list2;
                if (fragment != null) {
                    list3 = list2;
                    if (fragment.mRetainInstance) {
                        ArrayList<Fragment> list4;
                        if ((list4 = list2) == null) {
                            list4 = new ArrayList<Fragment>();
                        }
                        list4.add(fragment);
                        fragment.mRetaining = true;
                        int mIndex;
                        if (fragment.mTarget != null) {
                            mIndex = fragment.mTarget.mIndex;
                        }
                        else {
                            mIndex = -1;
                        }
                        fragment.mTargetIndex = mIndex;
                        list3 = list4;
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "retainNonConfig: keeping retained " + fragment);
                            list3 = list4;
                        }
                    }
                }
                ++n;
                list2 = list3;
            }
        }
        return list;
    }
    
    Parcelable saveAllState() {
        this.execPendingActions();
        if (FragmentManagerImpl.HONEYCOMB) {
            this.mStateSaved = true;
        }
        if (this.mActive != null && this.mActive.size() > 0) {
            final int size = this.mActive.size();
            final FragmentState[] mActive = new FragmentState[size];
            boolean b = false;
            for (int i = 0; i < size; ++i) {
                final Fragment fragment = this.mActive.get(i);
                if (fragment != null) {
                    if (fragment.mIndex < 0) {
                        this.throwException(new IllegalStateException("Failure saving state: active " + fragment + " has cleared index: " + fragment.mIndex));
                    }
                    final boolean b2 = true;
                    final FragmentState fragmentState = new FragmentState(fragment);
                    mActive[i] = fragmentState;
                    if (fragment.mState > 0 && fragmentState.mSavedFragmentState == null) {
                        fragmentState.mSavedFragmentState = this.saveFragmentBasicState(fragment);
                        if (fragment.mTarget != null) {
                            if (fragment.mTarget.mIndex < 0) {
                                this.throwException(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.mTarget));
                            }
                            if (fragmentState.mSavedFragmentState == null) {
                                fragmentState.mSavedFragmentState = new Bundle();
                            }
                            this.putFragment(fragmentState.mSavedFragmentState, "android:target_state", fragment.mTarget);
                            if (fragment.mTargetRequestCode != 0) {
                                fragmentState.mSavedFragmentState.putInt("android:target_req_state", fragment.mTargetRequestCode);
                            }
                        }
                    }
                    else {
                        fragmentState.mSavedFragmentState = fragment.mSavedFragmentState;
                    }
                    b = b2;
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.mSavedFragmentState);
                        b = b2;
                    }
                }
            }
            if (b) {
                final int[] array = null;
                final BackStackState[] array2 = null;
                int[] mAdded = array;
                if (this.mAdded != null) {
                    final int size2 = this.mAdded.size();
                    mAdded = array;
                    if (size2 > 0) {
                        final int[] array3 = new int[size2];
                        int n = 0;
                        while (true) {
                            mAdded = array3;
                            if (n >= size2) {
                                break;
                            }
                            array3[n] = this.mAdded.get(n).mIndex;
                            if (array3[n] < 0) {
                                this.throwException(new IllegalStateException("Failure saving state: active " + this.mAdded.get(n) + " has cleared index: " + array3[n]));
                            }
                            if (FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "saveAllState: adding fragment #" + n + ": " + this.mAdded.get(n));
                            }
                            ++n;
                        }
                    }
                }
                BackStackState[] mBackStack = array2;
                if (this.mBackStack != null) {
                    final int size3 = this.mBackStack.size();
                    mBackStack = array2;
                    if (size3 > 0) {
                        final BackStackState[] array4 = new BackStackState[size3];
                        int n2 = 0;
                        while (true) {
                            mBackStack = array4;
                            if (n2 >= size3) {
                                break;
                            }
                            array4[n2] = new BackStackState(this.mBackStack.get(n2));
                            if (FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "saveAllState: adding back stack #" + n2 + ": " + this.mBackStack.get(n2));
                            }
                            ++n2;
                        }
                    }
                }
                final FragmentManagerState fragmentManagerState = new FragmentManagerState();
                fragmentManagerState.mActive = mActive;
                fragmentManagerState.mAdded = mAdded;
                fragmentManagerState.mBackStack = mBackStack;
                return (Parcelable)fragmentManagerState;
            }
            if (FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
                return null;
            }
        }
        return null;
    }
    
    Bundle saveFragmentBasicState(final Fragment fragment) {
        Bundle mStateBundle = null;
        if (this.mStateBundle == null) {
            this.mStateBundle = new Bundle();
        }
        fragment.performSaveInstanceState(this.mStateBundle);
        if (!this.mStateBundle.isEmpty()) {
            mStateBundle = this.mStateBundle;
            this.mStateBundle = null;
        }
        if (fragment.mView != null) {
            this.saveFragmentViewState(fragment);
        }
        Bundle bundle = mStateBundle;
        if (fragment.mSavedViewState != null) {
            if ((bundle = mStateBundle) == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", (SparseArray)fragment.mSavedViewState);
        }
        Bundle bundle2 = bundle;
        if (!fragment.mUserVisibleHint) {
            if ((bundle2 = bundle) == null) {
                bundle2 = new Bundle();
            }
            bundle2.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle2;
    }
    
    @Override
    public Fragment.SavedState saveFragmentInstanceState(final Fragment fragment) {
        final Fragment.SavedState savedState = null;
        if (fragment.mIndex < 0) {
            this.throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        Fragment.SavedState savedState2 = savedState;
        if (fragment.mState > 0) {
            final Bundle saveFragmentBasicState = this.saveFragmentBasicState(fragment);
            savedState2 = savedState;
            if (saveFragmentBasicState != null) {
                savedState2 = new Fragment.SavedState(saveFragmentBasicState);
            }
        }
        return savedState2;
    }
    
    void saveFragmentViewState(final Fragment fragment) {
        if (fragment.mInnerView != null) {
            if (this.mStateArray == null) {
                this.mStateArray = (SparseArray<Parcelable>)new SparseArray();
            }
            else {
                this.mStateArray.clear();
            }
            fragment.mInnerView.saveHierarchyState((SparseArray)this.mStateArray);
            if (this.mStateArray.size() > 0) {
                fragment.mSavedViewState = this.mStateArray;
                this.mStateArray = null;
            }
        }
    }
    
    public void setBackStackIndex(final int n, final BackStackRecord backStackRecord) {
        synchronized (this) {
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList<BackStackRecord>();
            }
            int i;
            if (n < (i = this.mBackStackIndices.size())) {
                if (FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Setting back stack index " + n + " to " + backStackRecord);
                }
                this.mBackStackIndices.set(n, backStackRecord);
            }
            else {
                while (i < n) {
                    this.mBackStackIndices.add(null);
                    if (this.mAvailBackStackIndices == null) {
                        this.mAvailBackStackIndices = new ArrayList<Integer>();
                    }
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Adding available back stack index " + i);
                    }
                    this.mAvailBackStackIndices.add(i);
                    ++i;
                }
                if (FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Adding back stack index " + n + " with " + backStackRecord);
                }
                this.mBackStackIndices.add(backStackRecord);
            }
        }
    }
    
    void setRetainLoader(final boolean mRetainLoader) {
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); ++i) {
                final Fragment fragment = this.mActive.get(i);
                if (fragment != null) {
                    fragment.mRetainLoader = mRetainLoader;
                }
            }
        }
    }
    
    public void showFragment(final Fragment fragment, final int n, final int n2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            if (fragment.mView != null) {
                final Animation loadAnimation = this.loadAnimation(fragment, n, true, n2);
                if (loadAnimation != null) {
                    this.setHWLayerAnimListenerIfAlpha(fragment.mView, loadAnimation);
                    fragment.mView.startAnimation(loadAnimation);
                }
                fragment.mView.setVisibility(0);
            }
            if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.onHiddenChanged(false);
        }
    }
    
    void startPendingDeferredFragments() {
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); ++i) {
                final Fragment fragment = this.mActive.get(i);
                if (fragment != null) {
                    this.performPendingDeferredStart(fragment);
                }
            }
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        if (this.mParent != null) {
            DebugUtils.buildShortClassTag(this.mParent, sb);
        }
        else {
            DebugUtils.buildShortClassTag(this.mHost, sb);
        }
        sb.append("}}");
        return sb.toString();
    }
    
    static class AnimateOnHWLayerIfNeededListener implements Animation$AnimationListener
    {
        private Animation$AnimationListener mOrignalListener;
        private boolean mShouldRunOnHWLayer;
        private View mView;
        
        public AnimateOnHWLayerIfNeededListener(final View mView, final Animation animation) {
            this.mOrignalListener = null;
            this.mShouldRunOnHWLayer = false;
            this.mView = null;
            if (mView == null || animation == null) {
                return;
            }
            this.mView = mView;
        }
        
        public AnimateOnHWLayerIfNeededListener(final View mView, final Animation animation, final Animation$AnimationListener mOrignalListener) {
            this.mOrignalListener = null;
            this.mShouldRunOnHWLayer = false;
            this.mView = null;
            if (mView == null || animation == null) {
                return;
            }
            this.mOrignalListener = mOrignalListener;
            this.mView = mView;
        }
        
        @CallSuper
        public void onAnimationEnd(final Animation animation) {
            if (this.mView != null && this.mShouldRunOnHWLayer) {
                this.mView.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        ViewCompat.setLayerType(AnimateOnHWLayerIfNeededListener.this.mView, 0, null);
                    }
                });
            }
            if (this.mOrignalListener != null) {
                this.mOrignalListener.onAnimationEnd(animation);
            }
        }
        
        public void onAnimationRepeat(final Animation animation) {
            if (this.mOrignalListener != null) {
                this.mOrignalListener.onAnimationRepeat(animation);
            }
        }
        
        @CallSuper
        public void onAnimationStart(final Animation animation) {
            if (this.mView != null) {
                this.mShouldRunOnHWLayer = FragmentManagerImpl.shouldRunOnHWLayer(this.mView, animation);
                if (this.mShouldRunOnHWLayer) {
                    this.mView.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ViewCompat.setLayerType(AnimateOnHWLayerIfNeededListener.this.mView, 2, null);
                        }
                    });
                }
            }
            if (this.mOrignalListener != null) {
                this.mOrignalListener.onAnimationStart(animation);
            }
        }
    }
    
    static class FragmentTag
    {
        public static final int[] Fragment;
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;
        
        static {
            Fragment = new int[] { 16842755, 16842960, 16842961 };
        }
    }
}
