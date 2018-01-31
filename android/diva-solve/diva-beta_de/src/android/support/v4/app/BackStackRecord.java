// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.v4.util.SimpleArrayMap;
import java.io.FileDescriptor;
import java.io.Writer;
import java.io.PrintWriter;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.view.ViewGroup;
import java.util.Map;
import java.util.List;
import java.util.Collection;
import android.util.SparseArray;
import android.view.View;
import android.support.v4.util.ArrayMap;
import android.os.Build$VERSION;
import java.util.ArrayList;

final class BackStackRecord extends FragmentTransaction implements BackStackEntry, Runnable
{
    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SHOW = 5;
    static final boolean SUPPORTS_TRANSITIONS;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    Op mHead;
    int mIndex;
    final FragmentManagerImpl mManager;
    String mName;
    int mNumOp;
    int mPopEnterAnim;
    int mPopExitAnim;
    ArrayList<String> mSharedElementSourceNames;
    ArrayList<String> mSharedElementTargetNames;
    Op mTail;
    int mTransition;
    int mTransitionStyle;
    
    static {
        SUPPORTS_TRANSITIONS = (Build$VERSION.SDK_INT >= 21);
    }
    
    public BackStackRecord(final FragmentManagerImpl mManager) {
        this.mAllowAddToBackStack = true;
        this.mIndex = -1;
        this.mManager = mManager;
    }
    
    private TransitionState beginTransition(final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2, final boolean b) {
        final TransitionState transitionState = new TransitionState();
        transitionState.nonExistentView = new View(this.mManager.mHost.getContext());
        int n = 0;
        for (int i = 0; i < sparseArray.size(); ++i) {
            if (this.configureTransitions(sparseArray.keyAt(i), transitionState, b, sparseArray, sparseArray2)) {
                n = 1;
            }
        }
        int n2;
        for (int j = 0; j < sparseArray2.size(); ++j, n = n2) {
            final int key = sparseArray2.keyAt(j);
            n2 = n;
            if (sparseArray.get(key) == null) {
                n2 = n;
                if (this.configureTransitions(key, transitionState, b, sparseArray, sparseArray2)) {
                    n2 = 1;
                }
            }
        }
        TransitionState transitionState2 = transitionState;
        if (n == 0) {
            transitionState2 = null;
        }
        return transitionState2;
    }
    
    private void calculateFragments(final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2) {
        if (this.mManager.mContainer.onHasView()) {
            for (Op op = this.mHead; op != null; op = op.next) {
                switch (op.cmd) {
                    case 1: {
                        this.setLastIn(sparseArray2, op.fragment);
                        break;
                    }
                    case 2: {
                        Fragment fragment2;
                        Fragment fragment = fragment2 = op.fragment;
                        if (this.mManager.mAdded != null) {
                            int n = 0;
                            while (true) {
                                fragment2 = fragment;
                                if (n >= this.mManager.mAdded.size()) {
                                    break;
                                }
                                final Fragment fragment3 = this.mManager.mAdded.get(n);
                                Fragment fragment4 = null;
                                Label_0184: {
                                    if (fragment != null) {
                                        fragment4 = fragment;
                                        if (fragment3.mContainerId != fragment.mContainerId) {
                                            break Label_0184;
                                        }
                                    }
                                    if (fragment3 == fragment) {
                                        fragment4 = null;
                                    }
                                    else {
                                        setFirstOut(sparseArray, fragment3);
                                        fragment4 = fragment;
                                    }
                                }
                                ++n;
                                fragment = fragment4;
                            }
                        }
                        this.setLastIn(sparseArray2, fragment2);
                        break;
                    }
                    case 3: {
                        setFirstOut(sparseArray, op.fragment);
                        break;
                    }
                    case 4: {
                        setFirstOut(sparseArray, op.fragment);
                        break;
                    }
                    case 5: {
                        this.setLastIn(sparseArray2, op.fragment);
                        break;
                    }
                    case 6: {
                        setFirstOut(sparseArray, op.fragment);
                        break;
                    }
                    case 7: {
                        this.setLastIn(sparseArray2, op.fragment);
                        break;
                    }
                }
            }
        }
    }
    
    private void callSharedElementEnd(final TransitionState transitionState, final Fragment fragment, final Fragment fragment2, final boolean b, final ArrayMap<String, View> arrayMap) {
        SharedElementCallback sharedElementCallback;
        if (b) {
            sharedElementCallback = fragment2.mEnterTransitionCallback;
        }
        else {
            sharedElementCallback = fragment.mEnterTransitionCallback;
        }
        if (sharedElementCallback != null) {
            sharedElementCallback.onSharedElementEnd(new ArrayList<String>(arrayMap.keySet()), new ArrayList<View>(arrayMap.values()), null);
        }
    }
    
    private static Object captureExitingViews(final Object o, final Fragment fragment, final ArrayList<View> list, final ArrayMap<String, View> arrayMap, final View view) {
        Object captureExitingViews = o;
        if (o != null) {
            captureExitingViews = FragmentTransitionCompat21.captureExitingViews(o, fragment.getView(), list, arrayMap, view);
        }
        return captureExitingViews;
    }
    
    private boolean configureTransitions(final int n, final TransitionState transitionState, final boolean b, final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2) {
        final ViewGroup viewGroup = (ViewGroup)this.mManager.mContainer.onFindViewById(n);
        if (viewGroup == null) {
            return false;
        }
        final Fragment fragment = (Fragment)sparseArray2.get(n);
        final Fragment fragment2 = (Fragment)sparseArray.get(n);
        final Object enterTransition = getEnterTransition(fragment, b);
        final Object sharedElementTransition = getSharedElementTransition(fragment, fragment2, b);
        final Object exitTransition = getExitTransition(fragment2, b);
        Map<String, View> map = null;
        final ArrayList<View> list = new ArrayList<View>();
        Object o;
        if ((o = sharedElementTransition) != null) {
            final ArrayMap<String, View> remapSharedElements = this.remapSharedElements(transitionState, fragment2, b);
            if (remapSharedElements.isEmpty()) {
                o = null;
                map = null;
            }
            else {
                SharedElementCallback sharedElementCallback;
                if (b) {
                    sharedElementCallback = fragment2.mEnterTransitionCallback;
                }
                else {
                    sharedElementCallback = fragment.mEnterTransitionCallback;
                }
                if (sharedElementCallback != null) {
                    sharedElementCallback.onSharedElementStart(new ArrayList<String>(remapSharedElements.keySet()), new ArrayList<View>(remapSharedElements.values()), null);
                }
                this.prepareSharedElementTransition(transitionState, (View)viewGroup, sharedElementTransition, fragment, fragment2, b, list);
                o = sharedElementTransition;
                map = remapSharedElements;
            }
        }
        if (enterTransition == null && o == null && exitTransition == null) {
            return false;
        }
        final ArrayList<View> list2 = new ArrayList<View>();
        final Object captureExitingViews = captureExitingViews(exitTransition, fragment2, list2, (ArrayMap<String, View>)map, transitionState.nonExistentView);
        if (this.mSharedElementTargetNames != null && map != null) {
            final View view = ((SimpleArrayMap<Object, View>)map).get(this.mSharedElementTargetNames.get(0));
            if (view != null) {
                if (captureExitingViews != null) {
                    FragmentTransitionCompat21.setEpicenter(captureExitingViews, view);
                }
                if (o != null) {
                    FragmentTransitionCompat21.setEpicenter(o, view);
                }
            }
        }
        final FragmentTransitionCompat21.ViewRetriever viewRetriever = new FragmentTransitionCompat21.ViewRetriever() {
            @Override
            public View getView() {
                return fragment.getView();
            }
        };
        final ArrayList<View> list3 = new ArrayList<View>();
        final ArrayMap<String, View> arrayMap = new ArrayMap<String, View>();
        boolean b2 = true;
        if (fragment != null) {
            if (b) {
                b2 = fragment.getAllowReturnTransitionOverlap();
            }
            else {
                b2 = fragment.getAllowEnterTransitionOverlap();
            }
        }
        final Object mergeTransitions = FragmentTransitionCompat21.mergeTransitions(enterTransition, captureExitingViews, o, b2);
        if (mergeTransitions != null) {
            FragmentTransitionCompat21.addTransitionTargets(enterTransition, o, (View)viewGroup, (FragmentTransitionCompat21.ViewRetriever)viewRetriever, transitionState.nonExistentView, transitionState.enteringEpicenterView, transitionState.nameOverrides, list3, map, arrayMap, list);
            this.excludeHiddenFragmentsAfterEnter((View)viewGroup, transitionState, n, mergeTransitions);
            FragmentTransitionCompat21.excludeTarget(mergeTransitions, transitionState.nonExistentView, true);
            this.excludeHiddenFragments(transitionState, n, mergeTransitions);
            FragmentTransitionCompat21.beginDelayedTransition(viewGroup, mergeTransitions);
            FragmentTransitionCompat21.cleanupTransitions((View)viewGroup, transitionState.nonExistentView, enterTransition, list3, captureExitingViews, list2, o, list, mergeTransitions, transitionState.hiddenFragmentViews, arrayMap);
        }
        return mergeTransitions != null;
    }
    
    private void doAddOp(final int n, final Fragment fragment, final String mTag, final int cmd) {
        fragment.mFragmentManager = this.mManager;
        if (mTag != null) {
            if (fragment.mTag != null && !mTag.equals(fragment.mTag)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + mTag);
            }
            fragment.mTag = mTag;
        }
        if (n != 0) {
            if (fragment.mFragmentId != 0 && fragment.mFragmentId != n) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + n);
            }
            fragment.mFragmentId = n;
            fragment.mContainerId = n;
        }
        final Op op = new Op();
        op.cmd = cmd;
        op.fragment = fragment;
        this.addOp(op);
    }
    
    private void excludeHiddenFragments(final TransitionState transitionState, final int n, final Object o) {
        if (this.mManager.mAdded != null) {
            for (int i = 0; i < this.mManager.mAdded.size(); ++i) {
                final Fragment fragment = this.mManager.mAdded.get(i);
                if (fragment.mView != null && fragment.mContainer != null && fragment.mContainerId == n) {
                    if (fragment.mHidden) {
                        if (!transitionState.hiddenFragmentViews.contains(fragment.mView)) {
                            FragmentTransitionCompat21.excludeTarget(o, fragment.mView, true);
                            transitionState.hiddenFragmentViews.add(fragment.mView);
                        }
                    }
                    else {
                        FragmentTransitionCompat21.excludeTarget(o, fragment.mView, false);
                        transitionState.hiddenFragmentViews.remove(fragment.mView);
                    }
                }
            }
        }
    }
    
    private void excludeHiddenFragmentsAfterEnter(final View view, final TransitionState transitionState, final int n, final Object o) {
        view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new ViewTreeObserver$OnPreDrawListener() {
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
                BackStackRecord.this.excludeHiddenFragments(transitionState, n, o);
                return true;
            }
        });
    }
    
    private static Object getEnterTransition(final Fragment fragment, final boolean b) {
        if (fragment == null) {
            return null;
        }
        Object o;
        if (b) {
            o = fragment.getReenterTransition();
        }
        else {
            o = fragment.getEnterTransition();
        }
        return FragmentTransitionCompat21.cloneTransition(o);
    }
    
    private static Object getExitTransition(final Fragment fragment, final boolean b) {
        if (fragment == null) {
            return null;
        }
        Object o;
        if (b) {
            o = fragment.getReturnTransition();
        }
        else {
            o = fragment.getExitTransition();
        }
        return FragmentTransitionCompat21.cloneTransition(o);
    }
    
    private static Object getSharedElementTransition(final Fragment fragment, final Fragment fragment2, final boolean b) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        Object o;
        if (b) {
            o = fragment2.getSharedElementReturnTransition();
        }
        else {
            o = fragment.getSharedElementEnterTransition();
        }
        return FragmentTransitionCompat21.wrapSharedElementTransition(o);
    }
    
    private ArrayMap<String, View> mapEnteringSharedElements(final TransitionState transitionState, final Fragment fragment, final boolean b) {
        final ArrayMap<String, View> arrayMap = new ArrayMap<String, View>();
        final View view = fragment.getView();
        ArrayMap<String, View> remapNames = arrayMap;
        if (view != null) {
            remapNames = arrayMap;
            if (this.mSharedElementSourceNames != null) {
                FragmentTransitionCompat21.findNamedViews(arrayMap, view);
                if (!b) {
                    arrayMap.retainAll(this.mSharedElementTargetNames);
                    return arrayMap;
                }
                remapNames = remapNames(this.mSharedElementSourceNames, this.mSharedElementTargetNames, arrayMap);
            }
        }
        return remapNames;
    }
    
    private ArrayMap<String, View> mapSharedElementsIn(final TransitionState transitionState, final boolean b, final Fragment fragment) {
        final ArrayMap<String, View> mapEnteringSharedElements = this.mapEnteringSharedElements(transitionState, fragment, b);
        if (b) {
            if (fragment.mExitTransitionCallback != null) {
                fragment.mExitTransitionCallback.onMapSharedElements(this.mSharedElementTargetNames, mapEnteringSharedElements);
            }
            this.setBackNameOverrides(transitionState, mapEnteringSharedElements, true);
            return mapEnteringSharedElements;
        }
        if (fragment.mEnterTransitionCallback != null) {
            fragment.mEnterTransitionCallback.onMapSharedElements(this.mSharedElementTargetNames, mapEnteringSharedElements);
        }
        this.setNameOverrides(transitionState, mapEnteringSharedElements, true);
        return mapEnteringSharedElements;
    }
    
    private void prepareSharedElementTransition(final TransitionState transitionState, final View view, final Object o, final Fragment fragment, final Fragment fragment2, final boolean b, final ArrayList<View> list) {
        view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new ViewTreeObserver$OnPreDrawListener() {
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
                if (o != null) {
                    FragmentTransitionCompat21.removeTargets(o, list);
                    list.clear();
                    final ArrayMap access$000 = BackStackRecord.this.mapSharedElementsIn(transitionState, b, fragment);
                    FragmentTransitionCompat21.setSharedElementTargets(o, transitionState.nonExistentView, access$000, list);
                    BackStackRecord.this.setEpicenterIn(access$000, transitionState);
                    BackStackRecord.this.callSharedElementEnd(transitionState, fragment, fragment2, b, access$000);
                }
                return true;
            }
        });
    }
    
    private static ArrayMap<String, View> remapNames(final ArrayList<String> list, final ArrayList<String> list2, final ArrayMap<String, View> arrayMap) {
        if (arrayMap.isEmpty()) {
            return arrayMap;
        }
        final ArrayMap<String, View> arrayMap2 = new ArrayMap<String, View>();
        for (int size = list.size(), i = 0; i < size; ++i) {
            final View view = arrayMap.get(list.get(i));
            if (view != null) {
                arrayMap2.put(list2.get(i), view);
            }
        }
        return arrayMap2;
    }
    
    private ArrayMap<String, View> remapSharedElements(final TransitionState transitionState, final Fragment fragment, final boolean b) {
        ArrayMap<String, View> remapNames;
        final ArrayMap<String, View> arrayMap = remapNames = new ArrayMap<String, View>();
        if (this.mSharedElementSourceNames != null) {
            FragmentTransitionCompat21.findNamedViews(arrayMap, fragment.getView());
            if (b) {
                arrayMap.retainAll(this.mSharedElementTargetNames);
                remapNames = arrayMap;
            }
            else {
                remapNames = remapNames(this.mSharedElementSourceNames, this.mSharedElementTargetNames, arrayMap);
            }
        }
        if (b) {
            if (fragment.mEnterTransitionCallback != null) {
                fragment.mEnterTransitionCallback.onMapSharedElements(this.mSharedElementTargetNames, remapNames);
            }
            this.setBackNameOverrides(transitionState, remapNames, false);
            return remapNames;
        }
        if (fragment.mExitTransitionCallback != null) {
            fragment.mExitTransitionCallback.onMapSharedElements(this.mSharedElementTargetNames, remapNames);
        }
        this.setNameOverrides(transitionState, remapNames, false);
        return remapNames;
    }
    
    private void setBackNameOverrides(final TransitionState transitionState, final ArrayMap<String, View> arrayMap, final boolean b) {
        int size;
        if (this.mSharedElementTargetNames == null) {
            size = 0;
        }
        else {
            size = this.mSharedElementTargetNames.size();
        }
        for (int i = 0; i < size; ++i) {
            final String s = this.mSharedElementSourceNames.get(i);
            final View view = arrayMap.get(this.mSharedElementTargetNames.get(i));
            if (view != null) {
                final String transitionName = FragmentTransitionCompat21.getTransitionName(view);
                if (b) {
                    setNameOverride(transitionState.nameOverrides, s, transitionName);
                }
                else {
                    setNameOverride(transitionState.nameOverrides, transitionName, s);
                }
            }
        }
    }
    
    private void setEpicenterIn(final ArrayMap<String, View> arrayMap, final TransitionState transitionState) {
        if (this.mSharedElementTargetNames != null && !arrayMap.isEmpty()) {
            final View epicenter = arrayMap.get(this.mSharedElementTargetNames.get(0));
            if (epicenter != null) {
                transitionState.enteringEpicenterView.epicenter = epicenter;
            }
        }
    }
    
    private static void setFirstOut(final SparseArray<Fragment> sparseArray, final Fragment fragment) {
        if (fragment != null) {
            final int mContainerId = fragment.mContainerId;
            if (mContainerId != 0 && !fragment.isHidden() && fragment.isAdded() && fragment.getView() != null && sparseArray.get(mContainerId) == null) {
                sparseArray.put(mContainerId, (Object)fragment);
            }
        }
    }
    
    private void setLastIn(final SparseArray<Fragment> sparseArray, final Fragment fragment) {
        if (fragment != null) {
            final int mContainerId = fragment.mContainerId;
            if (mContainerId != 0) {
                sparseArray.put(mContainerId, (Object)fragment);
            }
        }
    }
    
    private static void setNameOverride(final ArrayMap<String, String> arrayMap, final String s, final String s2) {
        if (s != null && s2 != null) {
            for (int i = 0; i < arrayMap.size(); ++i) {
                if (s.equals(arrayMap.valueAt(i))) {
                    arrayMap.setValueAt(i, s2);
                    return;
                }
            }
            arrayMap.put(s, s2);
        }
    }
    
    private void setNameOverrides(final TransitionState transitionState, final ArrayMap<String, View> arrayMap, final boolean b) {
        for (int size = arrayMap.size(), i = 0; i < size; ++i) {
            final String s = arrayMap.keyAt(i);
            final String transitionName = FragmentTransitionCompat21.getTransitionName(arrayMap.valueAt(i));
            if (b) {
                setNameOverride(transitionState.nameOverrides, s, transitionName);
            }
            else {
                setNameOverride(transitionState.nameOverrides, transitionName, s);
            }
        }
    }
    
    private static void setNameOverrides(final TransitionState transitionState, final ArrayList<String> list, final ArrayList<String> list2) {
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                setNameOverride(transitionState.nameOverrides, list.get(i), list2.get(i));
            }
        }
    }
    
    @Override
    public FragmentTransaction add(final int n, final Fragment fragment) {
        this.doAddOp(n, fragment, null, 1);
        return this;
    }
    
    @Override
    public FragmentTransaction add(final int n, final Fragment fragment, final String s) {
        this.doAddOp(n, fragment, s, 1);
        return this;
    }
    
    @Override
    public FragmentTransaction add(final Fragment fragment, final String s) {
        this.doAddOp(0, fragment, s, 1);
        return this;
    }
    
    void addOp(final Op op) {
        if (this.mHead == null) {
            this.mTail = op;
            this.mHead = op;
        }
        else {
            op.prev = this.mTail;
            this.mTail.next = op;
            this.mTail = op;
        }
        op.enterAnim = this.mEnterAnim;
        op.exitAnim = this.mExitAnim;
        op.popEnterAnim = this.mPopEnterAnim;
        op.popExitAnim = this.mPopExitAnim;
        ++this.mNumOp;
    }
    
    @Override
    public FragmentTransaction addSharedElement(final View view, final String s) {
        if (BackStackRecord.SUPPORTS_TRANSITIONS) {
            final String transitionName = FragmentTransitionCompat21.getTransitionName(view);
            if (transitionName == null) {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (this.mSharedElementSourceNames == null) {
                this.mSharedElementSourceNames = new ArrayList<String>();
                this.mSharedElementTargetNames = new ArrayList<String>();
            }
            this.mSharedElementSourceNames.add(transitionName);
            this.mSharedElementTargetNames.add(s);
        }
        return this;
    }
    
    @Override
    public FragmentTransaction addToBackStack(final String mName) {
        if (!this.mAllowAddToBackStack) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        this.mAddToBackStack = true;
        this.mName = mName;
        return this;
    }
    
    @Override
    public FragmentTransaction attach(final Fragment fragment) {
        final Op op = new Op();
        op.cmd = 7;
        op.fragment = fragment;
        this.addOp(op);
        return this;
    }
    
    void bumpBackStackNesting(final int n) {
        if (this.mAddToBackStack) {
            if (FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + n);
            }
            for (Op op = this.mHead; op != null; op = op.next) {
                if (op.fragment != null) {
                    final Fragment fragment = op.fragment;
                    fragment.mBackStackNesting += n;
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Bump nesting of " + op.fragment + " to " + op.fragment.mBackStackNesting);
                    }
                }
                if (op.removed != null) {
                    for (int i = op.removed.size() - 1; i >= 0; --i) {
                        final Fragment fragment2 = op.removed.get(i);
                        fragment2.mBackStackNesting += n;
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "Bump nesting of " + fragment2 + " to " + fragment2.mBackStackNesting);
                        }
                    }
                }
            }
        }
    }
    
    public void calculateBackFragments(final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2) {
        if (this.mManager.mContainer.onHasView()) {
            for (Op op = this.mHead; op != null; op = op.next) {
                switch (op.cmd) {
                    case 1: {
                        setFirstOut(sparseArray, op.fragment);
                        break;
                    }
                    case 2: {
                        if (op.removed != null) {
                            for (int i = op.removed.size() - 1; i >= 0; --i) {
                                this.setLastIn(sparseArray2, op.removed.get(i));
                            }
                        }
                        setFirstOut(sparseArray, op.fragment);
                        break;
                    }
                    case 3: {
                        this.setLastIn(sparseArray2, op.fragment);
                        break;
                    }
                    case 4: {
                        this.setLastIn(sparseArray2, op.fragment);
                        break;
                    }
                    case 5: {
                        setFirstOut(sparseArray, op.fragment);
                        break;
                    }
                    case 6: {
                        this.setLastIn(sparseArray2, op.fragment);
                        break;
                    }
                    case 7: {
                        setFirstOut(sparseArray, op.fragment);
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public int commit() {
        return this.commitInternal(false);
    }
    
    @Override
    public int commitAllowingStateLoss() {
        return this.commitInternal(true);
    }
    
    int commitInternal(final boolean b) {
        if (this.mCommitted) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Commit: " + this);
            this.dump("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        this.mCommitted = true;
        if (this.mAddToBackStack) {
            this.mIndex = this.mManager.allocBackStackIndex(this);
        }
        else {
            this.mIndex = -1;
        }
        this.mManager.enqueueAction(this, b);
        return this.mIndex;
    }
    
    @Override
    public FragmentTransaction detach(final Fragment fragment) {
        final Op op = new Op();
        op.cmd = 6;
        op.fragment = fragment;
        this.addOp(op);
        return this;
    }
    
    @Override
    public FragmentTransaction disallowAddToBackStack() {
        if (this.mAddToBackStack) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.mAllowAddToBackStack = false;
        return this;
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        this.dump(s, printWriter, true);
    }
    
    public void dump(final String s, final PrintWriter printWriter, final boolean b) {
        if (b) {
            printWriter.print(s);
            printWriter.print("mName=");
            printWriter.print(this.mName);
            printWriter.print(" mIndex=");
            printWriter.print(this.mIndex);
            printWriter.print(" mCommitted=");
            printWriter.println(this.mCommitted);
            if (this.mTransition != 0) {
                printWriter.print(s);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.mTransition));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.mTransitionStyle));
            }
            if (this.mEnterAnim != 0 || this.mExitAnim != 0) {
                printWriter.print(s);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mEnterAnim));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.mExitAnim));
            }
            if (this.mPopEnterAnim != 0 || this.mPopExitAnim != 0) {
                printWriter.print(s);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mPopEnterAnim));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.mPopExitAnim));
            }
            if (this.mBreadCrumbTitleRes != 0 || this.mBreadCrumbTitleText != null) {
                printWriter.print(s);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.mBreadCrumbTitleText);
            }
            if (this.mBreadCrumbShortTitleRes != 0 || this.mBreadCrumbShortTitleText != null) {
                printWriter.print(s);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.mBreadCrumbShortTitleText);
            }
        }
        if (this.mHead != null) {
            printWriter.print(s);
            printWriter.println("Operations:");
            final String string = s + "    ";
            Op op = this.mHead;
            for (int n = 0; op != null; op = op.next, ++n) {
                String string2 = null;
                switch (op.cmd) {
                    default: {
                        string2 = "cmd=" + op.cmd;
                        break;
                    }
                    case 0: {
                        string2 = "NULL";
                        break;
                    }
                    case 1: {
                        string2 = "ADD";
                        break;
                    }
                    case 2: {
                        string2 = "REPLACE";
                        break;
                    }
                    case 3: {
                        string2 = "REMOVE";
                        break;
                    }
                    case 4: {
                        string2 = "HIDE";
                        break;
                    }
                    case 5: {
                        string2 = "SHOW";
                        break;
                    }
                    case 6: {
                        string2 = "DETACH";
                        break;
                    }
                    case 7: {
                        string2 = "ATTACH";
                        break;
                    }
                }
                printWriter.print(s);
                printWriter.print("  Op #");
                printWriter.print(n);
                printWriter.print(": ");
                printWriter.print(string2);
                printWriter.print(" ");
                printWriter.println(op.fragment);
                if (b) {
                    if (op.enterAnim != 0 || op.exitAnim != 0) {
                        printWriter.print(s);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(op.enterAnim));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(op.exitAnim));
                    }
                    if (op.popEnterAnim != 0 || op.popExitAnim != 0) {
                        printWriter.print(s);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(op.popEnterAnim));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(op.popExitAnim));
                    }
                }
                if (op.removed != null && op.removed.size() > 0) {
                    for (int i = 0; i < op.removed.size(); ++i) {
                        printWriter.print(string);
                        if (op.removed.size() == 1) {
                            printWriter.print("Removed: ");
                        }
                        else {
                            if (i == 0) {
                                printWriter.println("Removed:");
                            }
                            printWriter.print(string);
                            printWriter.print("  #");
                            printWriter.print(i);
                            printWriter.print(": ");
                        }
                        printWriter.println(op.removed.get(i));
                    }
                }
            }
        }
    }
    
    @Override
    public CharSequence getBreadCrumbShortTitle() {
        if (this.mBreadCrumbShortTitleRes != 0) {
            return this.mManager.mHost.getContext().getText(this.mBreadCrumbShortTitleRes);
        }
        return this.mBreadCrumbShortTitleText;
    }
    
    @Override
    public int getBreadCrumbShortTitleRes() {
        return this.mBreadCrumbShortTitleRes;
    }
    
    @Override
    public CharSequence getBreadCrumbTitle() {
        if (this.mBreadCrumbTitleRes != 0) {
            return this.mManager.mHost.getContext().getText(this.mBreadCrumbTitleRes);
        }
        return this.mBreadCrumbTitleText;
    }
    
    @Override
    public int getBreadCrumbTitleRes() {
        return this.mBreadCrumbTitleRes;
    }
    
    @Override
    public int getId() {
        return this.mIndex;
    }
    
    @Override
    public String getName() {
        return this.mName;
    }
    
    public int getTransition() {
        return this.mTransition;
    }
    
    public int getTransitionStyle() {
        return this.mTransitionStyle;
    }
    
    @Override
    public FragmentTransaction hide(final Fragment fragment) {
        final Op op = new Op();
        op.cmd = 4;
        op.fragment = fragment;
        this.addOp(op);
        return this;
    }
    
    @Override
    public boolean isAddToBackStackAllowed() {
        return this.mAllowAddToBackStack;
    }
    
    @Override
    public boolean isEmpty() {
        return this.mNumOp == 0;
    }
    
    public TransitionState popFromBackStack(final boolean b, final TransitionState transitionState, final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "popFromBackStack: " + this);
            this.dump("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        TransitionState beginTransition = transitionState;
        Label_0098: {
            if (BackStackRecord.SUPPORTS_TRANSITIONS) {
                if (transitionState == null) {
                    if (sparseArray.size() == 0) {
                        beginTransition = transitionState;
                        if (sparseArray2.size() == 0) {
                            break Label_0098;
                        }
                    }
                    beginTransition = this.beginTransition(sparseArray, sparseArray2, true);
                }
                else {
                    beginTransition = transitionState;
                    if (!b) {
                        setNameOverrides(transitionState, this.mSharedElementTargetNames, this.mSharedElementSourceNames);
                        beginTransition = transitionState;
                    }
                }
            }
        }
        this.bumpBackStackNesting(-1);
        int mTransitionStyle;
        if (beginTransition != null) {
            mTransitionStyle = 0;
        }
        else {
            mTransitionStyle = this.mTransitionStyle;
        }
        int mTransition;
        if (beginTransition != null) {
            mTransition = 0;
        }
        else {
            mTransition = this.mTransition;
        }
        for (Op op = this.mTail; op != null; op = op.prev) {
            int popEnterAnim;
            if (beginTransition != null) {
                popEnterAnim = 0;
            }
            else {
                popEnterAnim = op.popEnterAnim;
            }
            int popExitAnim;
            if (beginTransition != null) {
                popExitAnim = 0;
            }
            else {
                popExitAnim = op.popExitAnim;
            }
            switch (op.cmd) {
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + op.cmd);
                }
                case 1: {
                    final Fragment fragment = op.fragment;
                    fragment.mNextAnim = popExitAnim;
                    this.mManager.removeFragment(fragment, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
                case 2: {
                    final Fragment fragment2 = op.fragment;
                    if (fragment2 != null) {
                        fragment2.mNextAnim = popExitAnim;
                        this.mManager.removeFragment(fragment2, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    }
                    if (op.removed != null) {
                        for (int i = 0; i < op.removed.size(); ++i) {
                            final Fragment fragment3 = op.removed.get(i);
                            fragment3.mNextAnim = popEnterAnim;
                            this.mManager.addFragment(fragment3, false);
                        }
                        break;
                    }
                    break;
                }
                case 3: {
                    final Fragment fragment4 = op.fragment;
                    fragment4.mNextAnim = popEnterAnim;
                    this.mManager.addFragment(fragment4, false);
                    break;
                }
                case 4: {
                    final Fragment fragment5 = op.fragment;
                    fragment5.mNextAnim = popEnterAnim;
                    this.mManager.showFragment(fragment5, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
                case 5: {
                    final Fragment fragment6 = op.fragment;
                    fragment6.mNextAnim = popExitAnim;
                    this.mManager.hideFragment(fragment6, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
                case 6: {
                    final Fragment fragment7 = op.fragment;
                    fragment7.mNextAnim = popEnterAnim;
                    this.mManager.attachFragment(fragment7, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
                case 7: {
                    final Fragment fragment8 = op.fragment;
                    fragment8.mNextAnim = popEnterAnim;
                    this.mManager.detachFragment(fragment8, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
            }
        }
        if (b) {
            this.mManager.moveToState(this.mManager.mCurState, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle, true);
            beginTransition = null;
        }
        if (this.mIndex >= 0) {
            this.mManager.freeBackStackIndex(this.mIndex);
            this.mIndex = -1;
        }
        return beginTransition;
    }
    
    @Override
    public FragmentTransaction remove(final Fragment fragment) {
        final Op op = new Op();
        op.cmd = 3;
        op.fragment = fragment;
        this.addOp(op);
        return this;
    }
    
    @Override
    public FragmentTransaction replace(final int n, final Fragment fragment) {
        return this.replace(n, fragment, null);
    }
    
    @Override
    public FragmentTransaction replace(final int n, final Fragment fragment, final String s) {
        if (n == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        this.doAddOp(n, fragment, s, 2);
        return this;
    }
    
    @Override
    public void run() {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Run: " + this);
        }
        if (this.mAddToBackStack && this.mIndex < 0) {
            throw new IllegalStateException("addToBackStack() called after commit()");
        }
        this.bumpBackStackNesting(1);
        TransitionState beginTransition = null;
        if (BackStackRecord.SUPPORTS_TRANSITIONS) {
            final SparseArray sparseArray = new SparseArray();
            final SparseArray sparseArray2 = new SparseArray();
            this.calculateFragments((SparseArray<Fragment>)sparseArray, (SparseArray<Fragment>)sparseArray2);
            beginTransition = this.beginTransition((SparseArray<Fragment>)sparseArray, (SparseArray<Fragment>)sparseArray2, false);
        }
        int mTransitionStyle;
        if (beginTransition != null) {
            mTransitionStyle = 0;
        }
        else {
            mTransitionStyle = this.mTransitionStyle;
        }
        int mTransition;
        if (beginTransition != null) {
            mTransition = 0;
        }
        else {
            mTransition = this.mTransition;
        }
        for (Op op = this.mHead; op != null; op = op.next) {
            int enterAnim;
            if (beginTransition != null) {
                enterAnim = 0;
            }
            else {
                enterAnim = op.enterAnim;
            }
            int exitAnim;
            if (beginTransition != null) {
                exitAnim = 0;
            }
            else {
                exitAnim = op.exitAnim;
            }
            switch (op.cmd) {
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + op.cmd);
                }
                case 1: {
                    final Fragment fragment = op.fragment;
                    fragment.mNextAnim = enterAnim;
                    this.mManager.addFragment(fragment, false);
                    break;
                }
                case 2: {
                    Fragment fragment2 = op.fragment;
                    final int mContainerId = fragment2.mContainerId;
                    Fragment fragment3 = fragment2;
                    if (this.mManager.mAdded != null) {
                        int n = 0;
                        while (true) {
                            fragment3 = fragment2;
                            if (n >= this.mManager.mAdded.size()) {
                                break;
                            }
                            final Fragment fragment4 = this.mManager.mAdded.get(n);
                            if (FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "OP_REPLACE: adding=" + fragment2 + " old=" + fragment4);
                            }
                            Fragment fragment5 = fragment2;
                            if (fragment4.mContainerId == mContainerId) {
                                if (fragment4 == fragment2) {
                                    fragment5 = null;
                                    op.fragment = null;
                                }
                                else {
                                    if (op.removed == null) {
                                        op.removed = new ArrayList<Fragment>();
                                    }
                                    op.removed.add(fragment4);
                                    fragment4.mNextAnim = exitAnim;
                                    if (this.mAddToBackStack) {
                                        ++fragment4.mBackStackNesting;
                                        if (FragmentManagerImpl.DEBUG) {
                                            Log.v("FragmentManager", "Bump nesting of " + fragment4 + " to " + fragment4.mBackStackNesting);
                                        }
                                    }
                                    this.mManager.removeFragment(fragment4, mTransition, mTransitionStyle);
                                    fragment5 = fragment2;
                                }
                            }
                            ++n;
                            fragment2 = fragment5;
                        }
                    }
                    if (fragment3 != null) {
                        fragment3.mNextAnim = enterAnim;
                        this.mManager.addFragment(fragment3, false);
                        break;
                    }
                    break;
                }
                case 3: {
                    final Fragment fragment6 = op.fragment;
                    fragment6.mNextAnim = exitAnim;
                    this.mManager.removeFragment(fragment6, mTransition, mTransitionStyle);
                    break;
                }
                case 4: {
                    final Fragment fragment7 = op.fragment;
                    fragment7.mNextAnim = exitAnim;
                    this.mManager.hideFragment(fragment7, mTransition, mTransitionStyle);
                    break;
                }
                case 5: {
                    final Fragment fragment8 = op.fragment;
                    fragment8.mNextAnim = enterAnim;
                    this.mManager.showFragment(fragment8, mTransition, mTransitionStyle);
                    break;
                }
                case 6: {
                    final Fragment fragment9 = op.fragment;
                    fragment9.mNextAnim = exitAnim;
                    this.mManager.detachFragment(fragment9, mTransition, mTransitionStyle);
                    break;
                }
                case 7: {
                    final Fragment fragment10 = op.fragment;
                    fragment10.mNextAnim = enterAnim;
                    this.mManager.attachFragment(fragment10, mTransition, mTransitionStyle);
                    break;
                }
            }
        }
        this.mManager.moveToState(this.mManager.mCurState, mTransition, mTransitionStyle, true);
        if (this.mAddToBackStack) {
            this.mManager.addBackStackState(this);
        }
    }
    
    @Override
    public FragmentTransaction setBreadCrumbShortTitle(final int mBreadCrumbShortTitleRes) {
        this.mBreadCrumbShortTitleRes = mBreadCrumbShortTitleRes;
        this.mBreadCrumbShortTitleText = null;
        return this;
    }
    
    @Override
    public FragmentTransaction setBreadCrumbShortTitle(final CharSequence mBreadCrumbShortTitleText) {
        this.mBreadCrumbShortTitleRes = 0;
        this.mBreadCrumbShortTitleText = mBreadCrumbShortTitleText;
        return this;
    }
    
    @Override
    public FragmentTransaction setBreadCrumbTitle(final int mBreadCrumbTitleRes) {
        this.mBreadCrumbTitleRes = mBreadCrumbTitleRes;
        this.mBreadCrumbTitleText = null;
        return this;
    }
    
    @Override
    public FragmentTransaction setBreadCrumbTitle(final CharSequence mBreadCrumbTitleText) {
        this.mBreadCrumbTitleRes = 0;
        this.mBreadCrumbTitleText = mBreadCrumbTitleText;
        return this;
    }
    
    @Override
    public FragmentTransaction setCustomAnimations(final int n, final int n2) {
        return this.setCustomAnimations(n, n2, 0, 0);
    }
    
    @Override
    public FragmentTransaction setCustomAnimations(final int mEnterAnim, final int mExitAnim, final int mPopEnterAnim, final int mPopExitAnim) {
        this.mEnterAnim = mEnterAnim;
        this.mExitAnim = mExitAnim;
        this.mPopEnterAnim = mPopEnterAnim;
        this.mPopExitAnim = mPopExitAnim;
        return this;
    }
    
    @Override
    public FragmentTransaction setTransition(final int mTransition) {
        this.mTransition = mTransition;
        return this;
    }
    
    @Override
    public FragmentTransaction setTransitionStyle(final int mTransitionStyle) {
        this.mTransitionStyle = mTransitionStyle;
        return this;
    }
    
    @Override
    public FragmentTransaction show(final Fragment fragment) {
        final Op op = new Op();
        op.cmd = 5;
        op.fragment = fragment;
        this.addOp(op);
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.mIndex >= 0) {
            sb.append(" #");
            sb.append(this.mIndex);
        }
        if (this.mName != null) {
            sb.append(" ");
            sb.append(this.mName);
        }
        sb.append("}");
        return sb.toString();
    }
    
    static final class Op
    {
        int cmd;
        int enterAnim;
        int exitAnim;
        Fragment fragment;
        Op next;
        int popEnterAnim;
        int popExitAnim;
        Op prev;
        ArrayList<Fragment> removed;
    }
    
    public class TransitionState
    {
        public FragmentTransitionCompat21.EpicenterView enteringEpicenterView;
        public ArrayList<View> hiddenFragmentViews;
        public ArrayMap<String, String> nameOverrides;
        public View nonExistentView;
        
        public TransitionState() {
            this.nameOverrides = new ArrayMap<String, String>();
            this.hiddenFragmentViews = new ArrayList<View>();
            this.enteringEpicenterView = new FragmentTransitionCompat21.EpicenterView();
        }
    }
}
