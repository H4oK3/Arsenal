// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.widget.ScrollerCompat;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;
import java.util.Collections;
import android.util.SparseIntArray;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.annotation.CallSuper;
import android.view.ViewGroup$MarginLayoutParams;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.database.Observable;
import android.support.v4.view.ViewConfigurationCompat;
import android.os.SystemClock;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.view.ViewParent;
import android.view.FocusFinder;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.Log;
import android.support.v4.view.MotionEventCompat;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.View$MeasureSpec;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import android.support.v4.os.TraceCompat;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.v7.recyclerview.R;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.graphics.Rect;
import android.support.v4.view.NestedScrollingChildHelper;
import java.util.List;
import java.util.ArrayList;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.ScrollingView;
import android.view.ViewGroup;

public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild
{
    private static final boolean DEBUG = false;
    private static final boolean DISPATCH_TEMP_DETACH = false;
    private static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
    public static final int HORIZONTAL = 0;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    private static final int MAX_SCROLL_DURATION = 2000;
    public static final long NO_ID = -1L;
    public static final int NO_POSITION = -1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    private static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    private static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    private static final String TRACE_SCROLL_TAG = "RV Scroll";
    public static final int VERTICAL = 1;
    private static final Interpolator sQuinticInterpolator;
    private RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    private OnItemTouchListener mActiveOnItemTouchListener;
    private Adapter mAdapter;
    AdapterHelper mAdapterHelper;
    private boolean mAdapterUpdateDuringMeasure;
    private EdgeEffectCompat mBottomGlow;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    ChildHelper mChildHelper;
    private boolean mClipToPadding;
    private boolean mDataSetHasChangedAfterLayout;
    private boolean mEatRequestLayout;
    private int mEatenAccessibilityChangeFlags;
    private boolean mFirstLayoutComplete;
    private boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private boolean mIsAttached;
    ItemAnimator mItemAnimator;
    private ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    private final ArrayList<ItemDecoration> mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    private LayoutManager mLayout;
    private boolean mLayoutFrozen;
    private int mLayoutOrScrollCounter;
    private boolean mLayoutRequestEaten;
    private EdgeEffectCompat mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final RecyclerViewDataObserver mObserver;
    private List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
    private final ArrayList<OnItemTouchListener> mOnItemTouchListeners;
    private SavedState mPendingSavedState;
    private final boolean mPostUpdatesOnAnimation;
    private boolean mPostedAnimatorRunner;
    final Recycler mRecycler;
    private RecyclerListener mRecyclerListener;
    private EdgeEffectCompat mRightGlow;
    private final int[] mScrollConsumed;
    private float mScrollFactor;
    private OnScrollListener mScrollListener;
    private List<OnScrollListener> mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    private final NestedScrollingChildHelper mScrollingChildHelper;
    final State mState;
    private final Rect mTempRect;
    private EdgeEffectCompat mTopGlow;
    private int mTouchSlop;
    private final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    private final ViewFlinger mViewFlinger;
    private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
    final ViewInfoStore mViewInfoStore;
    
    static {
        FORCE_INVALIDATE_DISPLAY_LIST = (Build$VERSION.SDK_INT == 18 || Build$VERSION.SDK_INT == 19 || Build$VERSION.SDK_INT == 20);
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[] { Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE };
        sQuinticInterpolator = (Interpolator)new Interpolator() {
            public float getInterpolation(float n) {
                --n;
                return n * n * n * n * n + 1.0f;
            }
        };
    }
    
    public RecyclerView(final Context context) {
        this(context, null);
    }
    
    public RecyclerView(final Context context, @Nullable final AttributeSet set) {
        this(context, set, 0);
    }
    
    public RecyclerView(final Context context, @Nullable final AttributeSet set, final int n) {
        final boolean b = false;
        super(context, set, n);
        this.mObserver = new RecyclerViewDataObserver();
        this.mRecycler = new Recycler();
        this.mViewInfoStore = new ViewInfoStore();
        this.mUpdateChildViewsRunnable = new Runnable() {
            @Override
            public void run() {
                if (!RecyclerView.this.mFirstLayoutComplete || RecyclerView.this.isLayoutRequested()) {
                    return;
                }
                if (RecyclerView.this.mLayoutFrozen) {
                    RecyclerView.this.mLayoutRequestEaten = true;
                    return;
                }
                RecyclerView.this.consumePendingUpdateOperations();
            }
        };
        this.mTempRect = new Rect();
        this.mItemDecorations = new ArrayList<ItemDecoration>();
        this.mOnItemTouchListeners = new ArrayList<OnItemTouchListener>();
        this.mDataSetHasChangedAfterLayout = false;
        this.mLayoutOrScrollCounter = 0;
        this.mItemAnimator = (ItemAnimator)new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScrollFactor = Float.MIN_VALUE;
        this.mViewFlinger = new ViewFlinger();
        this.mState = new State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = (ItemAnimatorListener)new ItemAnimatorRestoreListener();
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mNestedOffsets = new int[2];
        this.mItemAnimatorRunner = new Runnable() {
            @Override
            public void run() {
                if (RecyclerView.this.mItemAnimator != null) {
                    RecyclerView.this.mItemAnimator.runPendingAnimations();
                }
                RecyclerView.this.mPostedAnimatorRunner = false;
            }
        };
        this.mViewInfoProcessCallback = new ViewInfoStore.ProcessCallback() {
            @Override
            public void processAppeared(final ViewHolder viewHolder, final ItemHolderInfo itemHolderInfo, final ItemHolderInfo itemHolderInfo2) {
                RecyclerView.this.animateAppearance(viewHolder, itemHolderInfo, itemHolderInfo2);
            }
            
            @Override
            public void processDisappeared(final ViewHolder viewHolder, final ItemHolderInfo itemHolderInfo, final ItemHolderInfo itemHolderInfo2) {
                RecyclerView.this.mRecycler.unscrapView(viewHolder);
                RecyclerView.this.animateDisappearance(viewHolder, itemHolderInfo, itemHolderInfo2);
            }
            
            @Override
            public void processPersistent(final ViewHolder viewHolder, @NonNull final ItemHolderInfo itemHolderInfo, @NonNull final ItemHolderInfo itemHolderInfo2) {
                viewHolder.setIsRecyclable(false);
                if (RecyclerView.this.mDataSetHasChangedAfterLayout) {
                    if (RecyclerView.this.mItemAnimator.animateChange(viewHolder, viewHolder, itemHolderInfo, itemHolderInfo2)) {
                        RecyclerView.this.postAnimationRunner();
                    }
                }
                else if (RecyclerView.this.mItemAnimator.animatePersistence(viewHolder, itemHolderInfo, itemHolderInfo2)) {
                    RecyclerView.this.postAnimationRunner();
                }
            }
            
            @Override
            public void unused(final ViewHolder viewHolder) {
                RecyclerView.this.mLayout.removeAndRecycleView(viewHolder.itemView, RecyclerView.this.mRecycler);
            }
        };
        this.setScrollContainer(true);
        this.setFocusableInTouchMode(true);
        this.mPostUpdatesOnAnimation = (Build$VERSION.SDK_INT >= 16);
        final ViewConfiguration value = ViewConfiguration.get(context);
        this.mTouchSlop = value.getScaledTouchSlop();
        this.mMinFlingVelocity = value.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = value.getScaledMaximumFlingVelocity();
        boolean willNotDraw = b;
        if (ViewCompat.getOverScrollMode((View)this) == 2) {
            willNotDraw = true;
        }
        this.setWillNotDraw(willNotDraw);
        this.mItemAnimator.setListener(this.mItemAnimatorListener);
        this.initAdapterManager();
        this.initChildrenHelper();
        if (ViewCompat.getImportantForAccessibility((View)this) == 0) {
            ViewCompat.setImportantForAccessibility((View)this, 1);
        }
        this.mAccessibilityManager = (AccessibilityManager)this.getContext().getSystemService("accessibility");
        this.setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        if (set != null) {
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.RecyclerView, n, 0);
            final String string = obtainStyledAttributes.getString(R.styleable.RecyclerView_layoutManager);
            obtainStyledAttributes.recycle();
            this.createLayoutManager(context, string, set, n, 0);
        }
        this.mScrollingChildHelper = new NestedScrollingChildHelper((View)this);
        this.setNestedScrollingEnabled(true);
    }
    
    static /* synthetic */ void access$1400(final RecyclerView recyclerView, final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        recyclerView.attachViewToParent(view, n, viewGroup$LayoutParams);
    }
    
    static /* synthetic */ void access$1500(final RecyclerView recyclerView, final int n) {
        recyclerView.detachViewFromParent(n);
    }
    
    static /* synthetic */ boolean access$3600(final RecyclerView recyclerView) {
        return recyclerView.awakenScrollBars();
    }
    
    static /* synthetic */ void access$5400(final RecyclerView recyclerView, final int n, final int n2) {
        recyclerView.setMeasuredDimension(n, n2);
    }
    
    private void addAnimatingView(final ViewHolder viewHolder) {
        final View itemView = viewHolder.itemView;
        final boolean b = itemView.getParent() == this;
        this.mRecycler.unscrapView(this.getChildViewHolder(itemView));
        if (viewHolder.isTmpDetached()) {
            this.mChildHelper.attachViewToParent(itemView, -1, itemView.getLayoutParams(), true);
            return;
        }
        if (!b) {
            this.mChildHelper.addView(itemView, true);
            return;
        }
        this.mChildHelper.hide(itemView);
    }
    
    private void animateAppearance(@NonNull final ViewHolder viewHolder, @Nullable final ItemHolderInfo itemHolderInfo, @NonNull final ItemHolderInfo itemHolderInfo2) {
        viewHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateAppearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            this.postAnimationRunner();
        }
    }
    
    private void animateChange(@NonNull final ViewHolder mShadowingHolder, @NonNull final ViewHolder mShadowedHolder, @NonNull final ItemHolderInfo itemHolderInfo, @NonNull final ItemHolderInfo itemHolderInfo2) {
        mShadowingHolder.setIsRecyclable(false);
        if (mShadowingHolder != mShadowedHolder) {
            mShadowingHolder.mShadowedHolder = mShadowedHolder;
            this.addAnimatingView(mShadowingHolder);
            this.mRecycler.unscrapView(mShadowingHolder);
            mShadowedHolder.setIsRecyclable(false);
            mShadowedHolder.mShadowingHolder = mShadowingHolder;
        }
        if (this.mItemAnimator.animateChange(mShadowingHolder, mShadowedHolder, itemHolderInfo, itemHolderInfo2)) {
            this.postAnimationRunner();
        }
    }
    
    private void animateDisappearance(@NonNull final ViewHolder viewHolder, @NonNull final ItemHolderInfo itemHolderInfo, @Nullable final ItemHolderInfo itemHolderInfo2) {
        this.addAnimatingView(viewHolder);
        viewHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateDisappearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            this.postAnimationRunner();
        }
    }
    
    private boolean canReuseUpdatedViewHolder(final ViewHolder viewHolder) {
        return this.mItemAnimator == null || this.mItemAnimator.canReuseUpdatedViewHolder(viewHolder);
    }
    
    private void cancelTouch() {
        this.resetTouch();
        this.setScrollState(0);
    }
    
    private void considerReleasingGlowsOnScroll(final int n, final int n2) {
        boolean onRelease;
        final boolean b = onRelease = false;
        if (this.mLeftGlow != null) {
            onRelease = b;
            if (!this.mLeftGlow.isFinished()) {
                onRelease = b;
                if (n > 0) {
                    onRelease = this.mLeftGlow.onRelease();
                }
            }
        }
        boolean b2 = onRelease;
        if (this.mRightGlow != null) {
            b2 = onRelease;
            if (!this.mRightGlow.isFinished()) {
                b2 = onRelease;
                if (n < 0) {
                    b2 = (onRelease | this.mRightGlow.onRelease());
                }
            }
        }
        boolean b3 = b2;
        if (this.mTopGlow != null) {
            b3 = b2;
            if (!this.mTopGlow.isFinished()) {
                b3 = b2;
                if (n2 > 0) {
                    b3 = (b2 | this.mTopGlow.onRelease());
                }
            }
        }
        boolean b4 = b3;
        if (this.mBottomGlow != null) {
            b4 = b3;
            if (!this.mBottomGlow.isFinished()) {
                b4 = b3;
                if (n2 < 0) {
                    b4 = (b3 | this.mBottomGlow.onRelease());
                }
            }
        }
        if (b4) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    private void consumePendingUpdateOperations() {
        if (this.mFirstLayoutComplete) {
            if (this.mDataSetHasChangedAfterLayout) {
                TraceCompat.beginSection("RV FullInvalidate");
                this.dispatchLayout();
                TraceCompat.endSection();
                return;
            }
            if (this.mAdapterHelper.hasPendingUpdates()) {
                if (this.mAdapterHelper.hasAnyUpdateTypes(4) && !this.mAdapterHelper.hasAnyUpdateTypes(11)) {
                    TraceCompat.beginSection("RV PartialInvalidate");
                    this.eatRequestLayout();
                    this.mAdapterHelper.preProcess();
                    if (!this.mLayoutRequestEaten) {
                        if (this.hasUpdatedView()) {
                            this.dispatchLayout();
                        }
                        else {
                            this.mAdapterHelper.consumePostponedUpdates();
                        }
                    }
                    this.resumeRequestLayout(true);
                    TraceCompat.endSection();
                    return;
                }
                if (this.mAdapterHelper.hasPendingUpdates()) {
                    TraceCompat.beginSection("RV FullInvalidate");
                    this.dispatchLayout();
                    TraceCompat.endSection();
                }
            }
        }
    }
    
    private void createLayoutManager(final Context context, String o, final AttributeSet set, final int n, final int n2) {
        if (o != null) {
            o = ((String)o).trim();
            if (((String)o).length() != 0) {
                final String fullClassName = this.getFullClassName(context, (String)o);
                try {
                    Label_0114: {
                        if (!this.isInEditMode()) {
                            break Label_0114;
                        }
                        o = this.getClass().getClassLoader();
                        while (true) {
                            final Class<? extends LayoutManager> subclass = ((ClassLoader)o).loadClass(fullClassName).asSubclass(LayoutManager.class);
                            o = null;
                            try {
                                final Constructor<? extends LayoutManager> constructor = subclass.getConstructor(RecyclerView.LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                                o = new Object[] { context, set, n, n2 };
                                final Constructor<? extends LayoutManager> constructor2 = constructor;
                                constructor2.setAccessible(true);
                                this.setLayoutManager((LayoutManager)constructor2.newInstance((Object[])o));
                                return;
                                o = context.getClassLoader();
                            }
                            catch (NoSuchMethodException ex2) {
                                try {
                                    final Constructor<? extends LayoutManager> constructor2 = subclass.getConstructor((Class<?>[])new Class[0]);
                                }
                                catch (NoSuchMethodException ex) {
                                    ex.initCause(ex2);
                                    throw new IllegalStateException(set.getPositionDescription() + ": Error creating LayoutManager " + fullClassName, ex);
                                }
                            }
                        }
                    }
                }
                catch (ClassNotFoundException ex3) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Unable to find LayoutManager " + fullClassName, ex3);
                }
                catch (InvocationTargetException ex4) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, ex4);
                }
                catch (InstantiationException ex5) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, ex5);
                }
                catch (IllegalAccessException ex6) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Cannot access non-public constructor " + fullClassName, ex6);
                }
                catch (ClassCastException ex7) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Class is not a LayoutManager " + fullClassName, ex7);
                }
            }
        }
    }
    
    private void defaultOnMeasure(int n, int n2) {
        final int mode = View$MeasureSpec.getMode(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        n = View$MeasureSpec.getSize(n);
        n2 = View$MeasureSpec.getSize(n2);
        switch (mode) {
            default: {
                n = ViewCompat.getMinimumWidth((View)this);
                break;
            }
            case Integer.MIN_VALUE:
            case 1073741824: {
                break;
            }
        }
        switch (mode2) {
            default: {
                n2 = ViewCompat.getMinimumHeight((View)this);
                break;
            }
            case Integer.MIN_VALUE:
            case 1073741824: {
                break;
            }
        }
        this.setMeasuredDimension(n, n2);
    }
    
    private boolean didChildRangeChange(final int n, final int n2) {
        final boolean b = false;
        final int childCount = this.mChildHelper.getChildCount();
        if (childCount != 0) {
            int n3 = 0;
            while (true) {
                final boolean b2 = b;
                if (n3 >= childCount) {
                    return b2;
                }
                final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(n3));
                if (!childViewHolderInt.shouldIgnore()) {
                    final int layoutPosition = childViewHolderInt.getLayoutPosition();
                    if (layoutPosition < n || layoutPosition > n2) {
                        break;
                    }
                }
                ++n3;
            }
            return true;
        }
        if (n == 0) {
            final boolean b2 = b;
            if (n2 == 0) {
                return b2;
            }
        }
        return true;
    }
    
    private void dispatchChildAttached(final View view) {
        final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        this.onChildAttachedToWindow(view);
        if (this.mAdapter != null && childViewHolderInt != null) {
            this.mAdapter.onViewAttachedToWindow(childViewHolderInt);
        }
        if (this.mOnChildAttachStateListeners != null) {
            for (int i = this.mOnChildAttachStateListeners.size() - 1; i >= 0; --i) {
                this.mOnChildAttachStateListeners.get(i).onChildViewAttachedToWindow(view);
            }
        }
    }
    
    private void dispatchChildDetached(final View view) {
        final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        this.onChildDetachedFromWindow(view);
        if (this.mAdapter != null && childViewHolderInt != null) {
            this.mAdapter.onViewDetachedFromWindow(childViewHolderInt);
        }
        if (this.mOnChildAttachStateListeners != null) {
            for (int i = this.mOnChildAttachStateListeners.size() - 1; i >= 0; --i) {
                this.mOnChildAttachStateListeners.get(i).onChildViewDetachedFromWindow(view);
            }
        }
    }
    
    private void dispatchContentChangedIfNecessary() {
        final int mEatenAccessibilityChangeFlags = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (mEatenAccessibilityChangeFlags != 0 && this.isAccessibilityEnabled()) {
            final AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(2048);
            AccessibilityEventCompat.setContentChangeTypes(obtain, mEatenAccessibilityChangeFlags);
            this.sendAccessibilityEventUnchecked(obtain);
        }
    }
    
    private boolean dispatchOnItemTouch(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        if (this.mActiveOnItemTouchListener != null) {
            if (action == 0) {
                this.mActiveOnItemTouchListener = null;
            }
            else {
                this.mActiveOnItemTouchListener.onTouchEvent(this, motionEvent);
                if (action == 3 || action == 1) {
                    this.mActiveOnItemTouchListener = null;
                    return true;
                }
                return true;
            }
        }
        if (action != 0) {
            for (int size = this.mOnItemTouchListeners.size(), i = 0; i < size; ++i) {
                final OnItemTouchListener mActiveOnItemTouchListener = this.mOnItemTouchListeners.get(i);
                if (mActiveOnItemTouchListener.onInterceptTouchEvent(this, motionEvent)) {
                    this.mActiveOnItemTouchListener = mActiveOnItemTouchListener;
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dispatchOnItemTouchIntercept(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.mActiveOnItemTouchListener = null;
        }
        for (int size = this.mOnItemTouchListeners.size(), i = 0; i < size; ++i) {
            final OnItemTouchListener mActiveOnItemTouchListener = this.mOnItemTouchListeners.get(i);
            if (mActiveOnItemTouchListener.onInterceptTouchEvent(this, motionEvent) && action != 3) {
                this.mActiveOnItemTouchListener = mActiveOnItemTouchListener;
                return true;
            }
        }
        return false;
    }
    
    private void findMinMaxChildLayoutPositions(final int[] array) {
        final int childCount = this.mChildHelper.getChildCount();
        if (childCount == 0) {
            array[1] = (array[0] = 0);
            return;
        }
        int n = Integer.MAX_VALUE;
        int n2 = Integer.MIN_VALUE;
        int n3;
        int n4;
        for (int i = 0; i < childCount; ++i, n2 = n4, n = n3) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
            if (childViewHolderInt.shouldIgnore()) {
                n3 = n;
                n4 = n2;
            }
            else {
                final int layoutPosition = childViewHolderInt.getLayoutPosition();
                int n5;
                if (layoutPosition < (n5 = n)) {
                    n5 = layoutPosition;
                }
                n4 = n2;
                n3 = n5;
                if (layoutPosition > n2) {
                    n4 = layoutPosition;
                    n3 = n5;
                }
            }
        }
        array[0] = n;
        array[1] = n2;
    }
    
    private int getAdapterPositionFor(final ViewHolder viewHolder) {
        if (viewHolder.hasAnyOfTheFlags(524) || !viewHolder.isBound()) {
            return -1;
        }
        return this.mAdapterHelper.applyPendingUpdatesToPosition(viewHolder.mPosition);
    }
    
    static ViewHolder getChildViewHolderInt(final View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams)view.getLayoutParams()).mViewHolder;
    }
    
    private String getFullClassName(final Context context, final String s) {
        String string;
        if (s.charAt(0) == '.') {
            string = context.getPackageName() + s;
        }
        else {
            string = s;
            if (!s.contains(".")) {
                return RecyclerView.class.getPackage().getName() + '.' + s;
            }
        }
        return string;
    }
    
    private float getScrollFactor() {
        if (this.mScrollFactor == Float.MIN_VALUE) {
            final TypedValue typedValue = new TypedValue();
            if (!this.getContext().getTheme().resolveAttribute(16842829, typedValue, true)) {
                return 0.0f;
            }
            this.mScrollFactor = typedValue.getDimension(this.getContext().getResources().getDisplayMetrics());
        }
        return this.mScrollFactor;
    }
    
    private boolean hasUpdatedView() {
        for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.isUpdated()) {
                return true;
            }
        }
        return false;
    }
    
    private void initChildrenHelper() {
        this.mChildHelper = new ChildHelper((ChildHelper.Callback)new ChildHelper.Callback() {
            @Override
            public void addView(final View view, final int n) {
                RecyclerView.this.addView(view, n);
                RecyclerView.this.dispatchChildAttached(view);
            }
            
            @Override
            public void attachViewToParent(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
                final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    if (!childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                        throw new IllegalArgumentException("Called attach on a child which is not detached: " + childViewHolderInt);
                    }
                    childViewHolderInt.clearTmpDetachFlag();
                }
                RecyclerView.access$1400(RecyclerView.this, view, n, viewGroup$LayoutParams);
            }
            
            @Override
            public void detachViewFromParent(final int n) {
                final View child = this.getChildAt(n);
                if (child != null) {
                    final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(child);
                    if (childViewHolderInt != null) {
                        if (childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                            throw new IllegalArgumentException("called detach on an already detached child " + childViewHolderInt);
                        }
                        childViewHolderInt.addFlags(256);
                    }
                }
                RecyclerView.access$1500(RecyclerView.this, n);
            }
            
            @Override
            public View getChildAt(final int n) {
                return RecyclerView.this.getChildAt(n);
            }
            
            @Override
            public int getChildCount() {
                return RecyclerView.this.getChildCount();
            }
            
            @Override
            public ViewHolder getChildViewHolder(final View view) {
                return RecyclerView.getChildViewHolderInt(view);
            }
            
            @Override
            public int indexOfChild(final View view) {
                return RecyclerView.this.indexOfChild(view);
            }
            
            @Override
            public void onEnteredHiddenState(final View view) {
                final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    childViewHolderInt.onEnteredHiddenState();
                }
            }
            
            @Override
            public void onLeftHiddenState(final View view) {
                final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    childViewHolderInt.onLeftHiddenState();
                }
            }
            
            @Override
            public void removeAllViews() {
                for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                    RecyclerView.this.dispatchChildDetached(this.getChildAt(i));
                }
                RecyclerView.this.removeAllViews();
            }
            
            @Override
            public void removeViewAt(final int n) {
                final View child = RecyclerView.this.getChildAt(n);
                if (child != null) {
                    RecyclerView.this.dispatchChildDetached(child);
                }
                RecyclerView.this.removeViewAt(n);
            }
        });
    }
    
    private void jumpToPositionForSmoothScroller(final int n) {
        if (this.mLayout == null) {
            return;
        }
        this.mLayout.scrollToPosition(n);
        this.awakenScrollBars();
    }
    
    private void onEnterLayoutOrScroll() {
        ++this.mLayoutOrScrollCounter;
    }
    
    private void onExitLayoutOrScroll() {
        --this.mLayoutOrScrollCounter;
        if (this.mLayoutOrScrollCounter < 1) {
            this.mLayoutOrScrollCounter = 0;
            this.dispatchContentChangedIfNecessary();
        }
    }
    
    private void onPointerUp(final MotionEvent motionEvent) {
        final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mScrollPointerId) {
            int n;
            if (actionIndex == 0) {
                n = 1;
            }
            else {
                n = 0;
            }
            this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, n);
            final int n2 = (int)(MotionEventCompat.getX(motionEvent, n) + 0.5f);
            this.mLastTouchX = n2;
            this.mInitialTouchX = n2;
            final int n3 = (int)(MotionEventCompat.getY(motionEvent, n) + 0.5f);
            this.mLastTouchY = n3;
            this.mInitialTouchY = n3;
        }
    }
    
    private void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            ViewCompat.postOnAnimation((View)this, this.mItemAnimatorRunner);
            this.mPostedAnimatorRunner = true;
        }
    }
    
    private boolean predictiveItemAnimationsEnabled() {
        return this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations();
    }
    
    private void processAdapterUpdatesAndSetAnimationFlags() {
        final boolean b = true;
        if (this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper.reset();
            this.markKnownViewsInvalid();
            this.mLayout.onItemsChanged(this);
        }
        if (this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations()) {
            this.mAdapterHelper.preProcess();
        }
        else {
            this.mAdapterHelper.consumeUpdatesInOnePass();
        }
        boolean b2;
        if (this.mItemsAddedOrRemoved || this.mItemsChanged) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        this.mState.mRunSimpleAnimations = (this.mFirstLayoutComplete && this.mItemAnimator != null && (this.mDataSetHasChangedAfterLayout || b2 || this.mLayout.mRequestedSimpleAnimations) && (!this.mDataSetHasChangedAfterLayout || this.mAdapter.hasStableIds()));
        this.mState.mRunPredictiveAnimations = (this.mState.mRunSimpleAnimations && b2 && !this.mDataSetHasChangedAfterLayout && this.predictiveItemAnimationsEnabled() && b);
    }
    
    private void pullGlows(final float n, final float n2, final float n3, final float n4) {
        final boolean b = false;
        boolean b2;
        if (n2 < 0.0f) {
            this.ensureLeftGlow();
            b2 = b;
            if (this.mLeftGlow.onPull(-n2 / this.getWidth(), 1.0f - n3 / this.getHeight())) {
                b2 = true;
            }
        }
        else {
            b2 = b;
            if (n2 > 0.0f) {
                this.ensureRightGlow();
                b2 = b;
                if (this.mRightGlow.onPull(n2 / this.getWidth(), n3 / this.getHeight())) {
                    b2 = true;
                }
            }
        }
        boolean b3;
        if (n4 < 0.0f) {
            this.ensureTopGlow();
            b3 = b2;
            if (this.mTopGlow.onPull(-n4 / this.getHeight(), n / this.getWidth())) {
                b3 = true;
            }
        }
        else {
            b3 = b2;
            if (n4 > 0.0f) {
                this.ensureBottomGlow();
                b3 = b2;
                if (this.mBottomGlow.onPull(n4 / this.getHeight(), 1.0f - n / this.getWidth())) {
                    b3 = true;
                }
            }
        }
        if (b3 || n2 != 0.0f || n4 != 0.0f) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    private void recordAnimationInfoIfBouncedHiddenView(final ViewHolder viewHolder, final ItemHolderInfo itemHolderInfo) {
        viewHolder.setFlags(0, 8192);
        if (this.mState.mTrackOldChangeHolders && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore()) {
            this.mViewInfoStore.addToOldChangeHolders(this.getChangedHolderKey(viewHolder), viewHolder);
        }
        this.mViewInfoStore.addToPreLayout(viewHolder, itemHolderInfo);
    }
    
    private void releaseGlows() {
        boolean onRelease = false;
        if (this.mLeftGlow != null) {
            onRelease = this.mLeftGlow.onRelease();
        }
        boolean b = onRelease;
        if (this.mTopGlow != null) {
            b = (onRelease | this.mTopGlow.onRelease());
        }
        boolean b2 = b;
        if (this.mRightGlow != null) {
            b2 = (b | this.mRightGlow.onRelease());
        }
        boolean b3 = b2;
        if (this.mBottomGlow != null) {
            b3 = (b2 | this.mBottomGlow.onRelease());
        }
        if (b3) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    private boolean removeAnimatingView(final View view) {
        this.eatRequestLayout();
        final boolean removeViewIfHidden = this.mChildHelper.removeViewIfHidden(view);
        if (removeViewIfHidden) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
            this.mRecycler.unscrapView(childViewHolderInt);
            this.mRecycler.recycleViewHolderInternal(childViewHolderInt);
        }
        this.resumeRequestLayout(false);
        return removeViewIfHidden;
    }
    
    private void repositionShadowingViews() {
        for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.mChildHelper.getChildAt(i);
            final ViewHolder childViewHolder = this.getChildViewHolder(child);
            if (childViewHolder != null && childViewHolder.mShadowingHolder != null) {
                final View itemView = childViewHolder.mShadowingHolder.itemView;
                final int left = child.getLeft();
                final int top = child.getTop();
                if (left != itemView.getLeft() || top != itemView.getTop()) {
                    itemView.layout(left, top, itemView.getWidth() + left, itemView.getHeight() + top);
                }
            }
        }
    }
    
    private void resetTouch() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.clear();
        }
        this.stopNestedScroll();
        this.releaseGlows();
    }
    
    private void setAdapterInternal(final Adapter mAdapter, final boolean b, final boolean b2) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterAdapterDataObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }
        if (!b || b2) {
            if (this.mItemAnimator != null) {
                this.mItemAnimator.endAnimations();
            }
            if (this.mLayout != null) {
                this.mLayout.removeAndRecycleAllViews(this.mRecycler);
                this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
            }
            this.mRecycler.clear();
        }
        this.mAdapterHelper.reset();
        final Adapter mAdapter2 = this.mAdapter;
        if ((this.mAdapter = mAdapter) != null) {
            mAdapter.registerAdapterDataObserver(this.mObserver);
            mAdapter.onAttachedToRecyclerView(this);
        }
        if (this.mLayout != null) {
            this.mLayout.onAdapterChanged(mAdapter2, this.mAdapter);
        }
        this.mRecycler.onAdapterChanged(mAdapter2, this.mAdapter, b);
        this.mState.mStructureChanged = true;
        this.markKnownViewsInvalid();
    }
    
    private void setDataSetChangedAfterLayout() {
        if (this.mDataSetHasChangedAfterLayout) {
            return;
        }
        this.mDataSetHasChangedAfterLayout = true;
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(512);
            }
        }
        this.mRecycler.setAdapterPositionsAsUnknown();
    }
    
    private void setScrollState(final int mScrollState) {
        if (mScrollState == this.mScrollState) {
            return;
        }
        if ((this.mScrollState = mScrollState) != 2) {
            this.stopScrollersInternal();
        }
        this.dispatchOnScrollStateChanged(mScrollState);
    }
    
    private void stopScrollersInternal() {
        this.mViewFlinger.stop();
        if (this.mLayout != null) {
            this.mLayout.stopSmoothScroller();
        }
    }
    
    void absorbGlows(final int n, final int n2) {
        if (n < 0) {
            this.ensureLeftGlow();
            this.mLeftGlow.onAbsorb(-n);
        }
        else if (n > 0) {
            this.ensureRightGlow();
            this.mRightGlow.onAbsorb(n);
        }
        if (n2 < 0) {
            this.ensureTopGlow();
            this.mTopGlow.onAbsorb(-n2);
        }
        else if (n2 > 0) {
            this.ensureBottomGlow();
            this.mBottomGlow.onAbsorb(n2);
        }
        if (n != 0 || n2 != 0) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    public void addFocusables(final ArrayList<View> list, final int n, final int n2) {
        if (this.mLayout == null || !this.mLayout.onAddFocusables(this, list, n, n2)) {
            super.addFocusables((ArrayList)list, n, n2);
        }
    }
    
    public void addItemDecoration(final ItemDecoration itemDecoration) {
        this.addItemDecoration(itemDecoration, -1);
    }
    
    public void addItemDecoration(final ItemDecoration itemDecoration, final int n) {
        if (this.mLayout != null) {
            this.mLayout.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            this.setWillNotDraw(false);
        }
        if (n < 0) {
            this.mItemDecorations.add(itemDecoration);
        }
        else {
            this.mItemDecorations.add(n, itemDecoration);
        }
        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }
    
    public void addOnChildAttachStateChangeListener(final OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList<OnChildAttachStateChangeListener>();
        }
        this.mOnChildAttachStateListeners.add(onChildAttachStateChangeListener);
    }
    
    public void addOnItemTouchListener(final OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.add(onItemTouchListener);
    }
    
    public void addOnScrollListener(final OnScrollListener onScrollListener) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList<OnScrollListener>();
        }
        this.mScrollListeners.add(onScrollListener);
    }
    
    void assertInLayoutOrScroll(final String s) {
        if (this.isComputingLayout()) {
            return;
        }
        if (s == null) {
            throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling");
        }
        throw new IllegalStateException(s);
    }
    
    void assertNotInLayoutOrScroll(final String s) {
        if (!this.isComputingLayout()) {
            return;
        }
        if (s == null) {
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling");
        }
        throw new IllegalStateException(s);
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof LayoutParams && this.mLayout.checkLayoutParams((LayoutParams)viewGroup$LayoutParams);
    }
    
    void clearOldPositions() {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        this.mRecycler.clearOldPositions();
    }
    
    public void clearOnChildAttachStateChangeListeners() {
        if (this.mOnChildAttachStateListeners != null) {
            this.mOnChildAttachStateListeners.clear();
        }
    }
    
    public void clearOnScrollListeners() {
        if (this.mScrollListeners != null) {
            this.mScrollListeners.clear();
        }
    }
    
    public int computeHorizontalScrollExtent() {
        if (this.mLayout.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollExtent(this.mState);
        }
        return 0;
    }
    
    public int computeHorizontalScrollOffset() {
        if (this.mLayout.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollOffset(this.mState);
        }
        return 0;
    }
    
    public int computeHorizontalScrollRange() {
        if (this.mLayout.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollRange(this.mState);
        }
        return 0;
    }
    
    public int computeVerticalScrollExtent() {
        if (this.mLayout.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollExtent(this.mState);
        }
        return 0;
    }
    
    public int computeVerticalScrollOffset() {
        if (this.mLayout.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollOffset(this.mState);
        }
        return 0;
    }
    
    public int computeVerticalScrollRange() {
        if (this.mLayout.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollRange(this.mState);
        }
        return 0;
    }
    
    void dispatchLayout() {
        if (this.mAdapter == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
        }
        else {
            if (this.mLayout == null) {
                Log.e("RecyclerView", "No layout manager attached; skipping layout");
                return;
            }
            this.mViewInfoStore.clear();
            this.eatRequestLayout();
            this.onEnterLayoutOrScroll();
            this.processAdapterUpdatesAndSetAnimationFlags();
            this.mState.mTrackOldChangeHolders = (this.mState.mRunSimpleAnimations && this.mItemsChanged);
            this.mItemsChanged = false;
            this.mItemsAddedOrRemoved = false;
            this.mState.mInPreLayout = this.mState.mRunPredictiveAnimations;
            this.mState.mItemCount = this.mAdapter.getItemCount();
            this.findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
            if (this.mState.mRunSimpleAnimations) {
                for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
                    final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                    if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.hasStableIds())) {
                        this.mViewInfoStore.addToPreLayout(childViewHolderInt, this.mItemAnimator.recordPreLayoutInformation(this.mState, childViewHolderInt, ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt), childViewHolderInt.getUnmodifiedPayloads()));
                        if (this.mState.mTrackOldChangeHolders && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                            this.mViewInfoStore.addToOldChangeHolders(this.getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                        }
                    }
                }
            }
            if (this.mState.mRunPredictiveAnimations) {
                this.saveOldPositions();
                final boolean access$1900 = this.mState.mStructureChanged;
                this.mState.mStructureChanged = false;
                this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
                this.mState.mStructureChanged = access$1900;
                for (int j = 0; j < this.mChildHelper.getChildCount(); ++j) {
                    final ViewHolder childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.getChildAt(j));
                    if (!childViewHolderInt2.shouldIgnore() && !this.mViewInfoStore.isInPreLayout(childViewHolderInt2)) {
                        final int buildAdapterChangeFlagsForAnimations = ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt2);
                        final boolean hasAnyOfTheFlags = childViewHolderInt2.hasAnyOfTheFlags(8192);
                        int n = buildAdapterChangeFlagsForAnimations;
                        if (!hasAnyOfTheFlags) {
                            n = (buildAdapterChangeFlagsForAnimations | 0x1000);
                        }
                        final ItemHolderInfo recordPreLayoutInformation = this.mItemAnimator.recordPreLayoutInformation(this.mState, childViewHolderInt2, n, childViewHolderInt2.getUnmodifiedPayloads());
                        if (hasAnyOfTheFlags) {
                            this.recordAnimationInfoIfBouncedHiddenView(childViewHolderInt2, recordPreLayoutInformation);
                        }
                        else {
                            this.mViewInfoStore.addToAppearedInPreLayoutHolders(childViewHolderInt2, recordPreLayoutInformation);
                        }
                    }
                }
                this.clearOldPositions();
                this.mAdapterHelper.consumePostponedUpdates();
            }
            else {
                this.clearOldPositions();
            }
            this.mState.mItemCount = this.mAdapter.getItemCount();
            this.mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
            this.mState.mInPreLayout = false;
            this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
            this.mState.mStructureChanged = false;
            this.mPendingSavedState = null;
            this.mState.mRunSimpleAnimations = (this.mState.mRunSimpleAnimations && this.mItemAnimator != null);
            if (this.mState.mRunSimpleAnimations) {
                for (int childCount2 = this.mChildHelper.getChildCount(), k = 0; k < childCount2; ++k) {
                    final ViewHolder childViewHolderInt3 = getChildViewHolderInt(this.mChildHelper.getChildAt(k));
                    if (!childViewHolderInt3.shouldIgnore()) {
                        final long changedHolderKey = this.getChangedHolderKey(childViewHolderInt3);
                        final ItemHolderInfo recordPostLayoutInformation = this.mItemAnimator.recordPostLayoutInformation(this.mState, childViewHolderInt3);
                        final ViewHolder fromOldChangeHolders = this.mViewInfoStore.getFromOldChangeHolders(changedHolderKey);
                        if (fromOldChangeHolders != null && !fromOldChangeHolders.shouldIgnore()) {
                            this.animateChange(fromOldChangeHolders, childViewHolderInt3, this.mViewInfoStore.popFromPreLayout(fromOldChangeHolders), recordPostLayoutInformation);
                        }
                        else {
                            this.mViewInfoStore.addToPostLayout(childViewHolderInt3, recordPostLayoutInformation);
                        }
                    }
                }
                this.mViewInfoStore.process(this.mViewInfoProcessCallback);
            }
            this.resumeRequestLayout(false);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
            this.mState.mPreviousLayoutItemCount = this.mState.mItemCount;
            this.mDataSetHasChangedAfterLayout = false;
            this.mState.mRunSimpleAnimations = false;
            this.mState.mRunPredictiveAnimations = false;
            this.onExitLayoutOrScroll();
            this.mLayout.mRequestedSimpleAnimations = false;
            if (this.mRecycler.mChangedScrap != null) {
                this.mRecycler.mChangedScrap.clear();
            }
            this.mViewInfoStore.clear();
            if (this.didChildRangeChange(this.mMinMaxLayoutPositions[0], this.mMinMaxLayoutPositions[1])) {
                this.dispatchOnScrolled(0, 0);
            }
        }
    }
    
    public boolean dispatchNestedFling(final float n, final float n2, final boolean b) {
        return this.mScrollingChildHelper.dispatchNestedFling(n, n2, b);
    }
    
    public boolean dispatchNestedPreFling(final float n, final float n2) {
        return this.mScrollingChildHelper.dispatchNestedPreFling(n, n2);
    }
    
    public boolean dispatchNestedPreScroll(final int n, final int n2, final int[] array, final int[] array2) {
        return this.mScrollingChildHelper.dispatchNestedPreScroll(n, n2, array, array2);
    }
    
    public boolean dispatchNestedScroll(final int n, final int n2, final int n3, final int n4, final int[] array) {
        return this.mScrollingChildHelper.dispatchNestedScroll(n, n2, n3, n4, array);
    }
    
    void dispatchOnScrollStateChanged(final int n) {
        if (this.mLayout != null) {
            this.mLayout.onScrollStateChanged(n);
        }
        this.onScrollStateChanged(n);
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrollStateChanged(this, n);
        }
        if (this.mScrollListeners != null) {
            for (int i = this.mScrollListeners.size() - 1; i >= 0; --i) {
                this.mScrollListeners.get(i).onScrollStateChanged(this, n);
            }
        }
    }
    
    void dispatchOnScrolled(final int n, final int n2) {
        final int scrollX = this.getScrollX();
        final int scrollY = this.getScrollY();
        this.onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        this.onScrolled(n, n2);
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrolled(this, n, n2);
        }
        if (this.mScrollListeners != null) {
            for (int i = this.mScrollListeners.size() - 1; i >= 0; --i) {
                this.mScrollListeners.get(i).onScrolled(this, n, n2);
            }
        }
    }
    
    protected void dispatchRestoreInstanceState(final SparseArray<Parcelable> sparseArray) {
        this.dispatchThawSelfOnly((SparseArray)sparseArray);
    }
    
    protected void dispatchSaveInstanceState(final SparseArray<Parcelable> sparseArray) {
        this.dispatchFreezeSelfOnly((SparseArray)sparseArray);
    }
    
    public void draw(final Canvas canvas) {
        final boolean b = true;
        super.draw(canvas);
        for (int size = this.mItemDecorations.size(), i = 0; i < size; ++i) {
            this.mItemDecorations.get(i).onDrawOver(canvas, this, this.mState);
        }
        boolean b3;
        final boolean b2 = b3 = false;
        if (this.mLeftGlow != null) {
            b3 = b2;
            if (!this.mLeftGlow.isFinished()) {
                final int save = canvas.save();
                int paddingBottom;
                if (this.mClipToPadding) {
                    paddingBottom = this.getPaddingBottom();
                }
                else {
                    paddingBottom = 0;
                }
                canvas.rotate(270.0f);
                canvas.translate((float)(-this.getHeight() + paddingBottom), 0.0f);
                if (this.mLeftGlow != null && this.mLeftGlow.draw(canvas)) {
                    b3 = true;
                }
                else {
                    b3 = false;
                }
                canvas.restoreToCount(save);
            }
        }
        boolean b4 = b3;
        if (this.mTopGlow != null) {
            b4 = b3;
            if (!this.mTopGlow.isFinished()) {
                final int save2 = canvas.save();
                if (this.mClipToPadding) {
                    canvas.translate((float)this.getPaddingLeft(), (float)this.getPaddingTop());
                }
                boolean b5;
                if (this.mTopGlow != null && this.mTopGlow.draw(canvas)) {
                    b5 = true;
                }
                else {
                    b5 = false;
                }
                b4 = (b3 | b5);
                canvas.restoreToCount(save2);
            }
        }
        boolean b6 = b4;
        if (this.mRightGlow != null) {
            b6 = b4;
            if (!this.mRightGlow.isFinished()) {
                final int save3 = canvas.save();
                final int width = this.getWidth();
                int paddingTop;
                if (this.mClipToPadding) {
                    paddingTop = this.getPaddingTop();
                }
                else {
                    paddingTop = 0;
                }
                canvas.rotate(90.0f);
                canvas.translate((float)(-paddingTop), (float)(-width));
                boolean b7;
                if (this.mRightGlow != null && this.mRightGlow.draw(canvas)) {
                    b7 = true;
                }
                else {
                    b7 = false;
                }
                b6 = (b4 | b7);
                canvas.restoreToCount(save3);
            }
        }
        boolean b8 = b6;
        if (this.mBottomGlow != null) {
            b8 = b6;
            if (!this.mBottomGlow.isFinished()) {
                final int save4 = canvas.save();
                canvas.rotate(180.0f);
                if (this.mClipToPadding) {
                    canvas.translate((float)(-this.getWidth() + this.getPaddingRight()), (float)(-this.getHeight() + this.getPaddingBottom()));
                }
                else {
                    canvas.translate((float)(-this.getWidth()), (float)(-this.getHeight()));
                }
                b8 = (b6 | (this.mBottomGlow != null && this.mBottomGlow.draw(canvas) && b));
                canvas.restoreToCount(save4);
            }
        }
        boolean b9;
        if (!(b9 = b8)) {
            b9 = b8;
            if (this.mItemAnimator != null) {
                b9 = b8;
                if (this.mItemDecorations.size() > 0) {
                    b9 = b8;
                    if (this.mItemAnimator.isRunning()) {
                        b9 = true;
                    }
                }
            }
        }
        if (b9) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    public boolean drawChild(final Canvas canvas, final View view, final long n) {
        return super.drawChild(canvas, view, n);
    }
    
    void eatRequestLayout() {
        if (!this.mEatRequestLayout) {
            this.mEatRequestLayout = true;
            if (!this.mLayoutFrozen) {
                this.mLayoutRequestEaten = false;
            }
        }
    }
    
    void ensureBottomGlow() {
        if (this.mBottomGlow != null) {
            return;
        }
        this.mBottomGlow = new EdgeEffectCompat(this.getContext());
        if (this.mClipToPadding) {
            this.mBottomGlow.setSize(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom());
            return;
        }
        this.mBottomGlow.setSize(this.getMeasuredWidth(), this.getMeasuredHeight());
    }
    
    void ensureLeftGlow() {
        if (this.mLeftGlow != null) {
            return;
        }
        this.mLeftGlow = new EdgeEffectCompat(this.getContext());
        if (this.mClipToPadding) {
            this.mLeftGlow.setSize(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight());
            return;
        }
        this.mLeftGlow.setSize(this.getMeasuredHeight(), this.getMeasuredWidth());
    }
    
    void ensureRightGlow() {
        if (this.mRightGlow != null) {
            return;
        }
        this.mRightGlow = new EdgeEffectCompat(this.getContext());
        if (this.mClipToPadding) {
            this.mRightGlow.setSize(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight());
            return;
        }
        this.mRightGlow.setSize(this.getMeasuredHeight(), this.getMeasuredWidth());
    }
    
    void ensureTopGlow() {
        if (this.mTopGlow != null) {
            return;
        }
        this.mTopGlow = new EdgeEffectCompat(this.getContext());
        if (this.mClipToPadding) {
            this.mTopGlow.setSize(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom());
            return;
        }
        this.mTopGlow.setSize(this.getMeasuredWidth(), this.getMeasuredHeight());
    }
    
    public View findChildViewUnder(final float n, final float n2) {
        for (int i = this.mChildHelper.getChildCount() - 1; i >= 0; --i) {
            final View child = this.mChildHelper.getChildAt(i);
            final float translationX = ViewCompat.getTranslationX(child);
            final float translationY = ViewCompat.getTranslationY(child);
            if (n >= child.getLeft() + translationX && n <= child.getRight() + translationX && n2 >= child.getTop() + translationY && n2 <= child.getBottom() + translationY) {
                return child;
            }
        }
        return null;
    }
    
    public ViewHolder findViewHolderForAdapterPosition(final int n) {
        if (!this.mDataSetHasChangedAfterLayout) {
            for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
                final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved()) {
                    final ViewHolder viewHolder = childViewHolderInt;
                    if (this.getAdapterPositionFor(childViewHolderInt) == n) {
                        return viewHolder;
                    }
                }
            }
            return null;
        }
        return null;
    }
    
    public ViewHolder findViewHolderForItemId(final long n) {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && childViewHolderInt.getItemId() == n) {
                return childViewHolderInt;
            }
        }
        return null;
    }
    
    public ViewHolder findViewHolderForLayoutPosition(final int n) {
        return this.findViewHolderForPosition(n, false);
    }
    
    @Deprecated
    public ViewHolder findViewHolderForPosition(final int n) {
        return this.findViewHolderForPosition(n, false);
    }
    
    ViewHolder findViewHolderForPosition(final int n, final boolean b) {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved()) {
                if (b) {
                    if (childViewHolderInt.mPosition != n) {
                        continue;
                    }
                }
                else if (childViewHolderInt.getLayoutPosition() != n) {
                    continue;
                }
                return childViewHolderInt;
            }
        }
        return null;
    }
    
    public boolean fling(int max, int max2) {
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        }
        else if (!this.mLayoutFrozen) {
            final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
            final boolean canScrollVertically = this.mLayout.canScrollVertically();
            int n = 0;
            Label_0063: {
                if (canScrollHorizontally) {
                    n = max;
                    if (Math.abs(max) >= this.mMinFlingVelocity) {
                        break Label_0063;
                    }
                }
                n = 0;
            }
            Label_0083: {
                if (canScrollVertically) {
                    max = max2;
                    if (Math.abs(max2) >= this.mMinFlingVelocity) {
                        break Label_0083;
                    }
                }
                max = 0;
            }
            if ((n != 0 || max != 0) && !this.dispatchNestedPreFling(n, max)) {
                final boolean b = canScrollHorizontally || canScrollVertically;
                this.dispatchNestedFling(n, max, b);
                if (b) {
                    max2 = Math.max(-this.mMaxFlingVelocity, Math.min(n, this.mMaxFlingVelocity));
                    max = Math.max(-this.mMaxFlingVelocity, Math.min(max, this.mMaxFlingVelocity));
                    this.mViewFlinger.fling(max2, max);
                    return true;
                }
            }
        }
        return false;
    }
    
    public View focusSearch(final View view, final int n) {
        final View onInterceptFocusSearch = this.mLayout.onInterceptFocusSearch(view, n);
        if (onInterceptFocusSearch != null) {
            return onInterceptFocusSearch;
        }
        final View nextFocus = FocusFinder.getInstance().findNextFocus((ViewGroup)this, view, n);
        View onFocusSearchFailed;
        if ((onFocusSearchFailed = nextFocus) == null) {
            onFocusSearchFailed = nextFocus;
            if (this.mAdapter != null) {
                onFocusSearchFailed = nextFocus;
                if (this.mLayout != null) {
                    onFocusSearchFailed = nextFocus;
                    if (!this.isComputingLayout()) {
                        onFocusSearchFailed = nextFocus;
                        if (!this.mLayoutFrozen) {
                            this.eatRequestLayout();
                            onFocusSearchFailed = this.mLayout.onFocusSearchFailed(view, n, this.mRecycler, this.mState);
                            this.resumeRequestLayout(false);
                        }
                    }
                }
            }
        }
        if (onFocusSearchFailed != null) {
            return onFocusSearchFailed;
        }
        return super.focusSearch(view, n);
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        if (this.mLayout == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        }
        return (ViewGroup$LayoutParams)this.mLayout.generateDefaultLayoutParams();
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        if (this.mLayout == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        }
        return (ViewGroup$LayoutParams)this.mLayout.generateLayoutParams(this.getContext(), set);
    }
    
    protected ViewGroup$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (this.mLayout == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        }
        return (ViewGroup$LayoutParams)this.mLayout.generateLayoutParams(viewGroup$LayoutParams);
    }
    
    public Adapter getAdapter() {
        return this.mAdapter;
    }
    
    public int getBaseline() {
        if (this.mLayout != null) {
            return this.mLayout.getBaseline();
        }
        return super.getBaseline();
    }
    
    long getChangedHolderKey(final ViewHolder viewHolder) {
        if (this.mAdapter.hasStableIds()) {
            return viewHolder.getItemId();
        }
        return viewHolder.mPosition;
    }
    
    public int getChildAdapterPosition(final View view) {
        final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getAdapterPosition();
        }
        return -1;
    }
    
    protected int getChildDrawingOrder(final int n, final int n2) {
        if (this.mChildDrawingOrderCallback == null) {
            return super.getChildDrawingOrder(n, n2);
        }
        return this.mChildDrawingOrderCallback.onGetChildDrawingOrder(n, n2);
    }
    
    public long getChildItemId(final View view) {
        if (this.mAdapter != null && this.mAdapter.hasStableIds()) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
            if (childViewHolderInt != null) {
                return childViewHolderInt.getItemId();
            }
        }
        return -1L;
    }
    
    public int getChildLayoutPosition(final View view) {
        final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getLayoutPosition();
        }
        return -1;
    }
    
    @Deprecated
    public int getChildPosition(final View view) {
        return this.getChildAdapterPosition(view);
    }
    
    public ViewHolder getChildViewHolder(final View view) {
        final ViewParent parent = view.getParent();
        if (parent != null && parent != this) {
            throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
        }
        return getChildViewHolderInt(view);
    }
    
    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }
    
    public ItemAnimator getItemAnimator() {
        return this.mItemAnimator;
    }
    
    Rect getItemDecorInsetsForChild(final View view) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (!layoutParams.mInsetsDirty) {
            return layoutParams.mDecorInsets;
        }
        final Rect mDecorInsets = layoutParams.mDecorInsets;
        mDecorInsets.set(0, 0, 0, 0);
        for (int size = this.mItemDecorations.size(), i = 0; i < size; ++i) {
            this.mTempRect.set(0, 0, 0, 0);
            this.mItemDecorations.get(i).getItemOffsets(this.mTempRect, view, this, this.mState);
            mDecorInsets.left += this.mTempRect.left;
            mDecorInsets.top += this.mTempRect.top;
            mDecorInsets.right += this.mTempRect.right;
            mDecorInsets.bottom += this.mTempRect.bottom;
        }
        layoutParams.mInsetsDirty = false;
        return mDecorInsets;
    }
    
    public LayoutManager getLayoutManager() {
        return this.mLayout;
    }
    
    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }
    
    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }
    
    public RecycledViewPool getRecycledViewPool() {
        return this.mRecycler.getRecycledViewPool();
    }
    
    public int getScrollState() {
        return this.mScrollState;
    }
    
    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }
    
    public boolean hasNestedScrollingParent() {
        return this.mScrollingChildHelper.hasNestedScrollingParent();
    }
    
    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates();
    }
    
    void initAdapterManager() {
        this.mAdapterHelper = new AdapterHelper((AdapterHelper.Callback)new AdapterHelper.Callback() {
            void dispatchUpdate(final UpdateOp updateOp) {
                switch (updateOp.cmd) {
                    default: {}
                    case 1: {
                        RecyclerView.this.mLayout.onItemsAdded(RecyclerView.this, updateOp.positionStart, updateOp.itemCount);
                    }
                    case 2: {
                        RecyclerView.this.mLayout.onItemsRemoved(RecyclerView.this, updateOp.positionStart, updateOp.itemCount);
                    }
                    case 4: {
                        RecyclerView.this.mLayout.onItemsUpdated(RecyclerView.this, updateOp.positionStart, updateOp.itemCount, updateOp.payload);
                    }
                    case 8: {
                        RecyclerView.this.mLayout.onItemsMoved(RecyclerView.this, updateOp.positionStart, updateOp.itemCount, 1);
                    }
                }
            }
            
            @Override
            public ViewHolder findViewHolder(final int n) {
                final ViewHolder viewHolderForPosition = RecyclerView.this.findViewHolderForPosition(n, true);
                ViewHolder viewHolder;
                if (viewHolderForPosition == null) {
                    viewHolder = null;
                }
                else {
                    viewHolder = viewHolderForPosition;
                    if (RecyclerView.this.mChildHelper.isHidden(viewHolderForPosition.itemView)) {
                        return null;
                    }
                }
                return viewHolder;
            }
            
            @Override
            public void markViewHoldersUpdated(final int n, final int n2, final Object o) {
                RecyclerView.this.viewRangeUpdate(n, n2, o);
                RecyclerView.this.mItemsChanged = true;
            }
            
            @Override
            public void offsetPositionsForAdd(final int n, final int n2) {
                RecyclerView.this.offsetPositionRecordsForInsert(n, n2);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }
            
            @Override
            public void offsetPositionsForMove(final int n, final int n2) {
                RecyclerView.this.offsetPositionRecordsForMove(n, n2);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }
            
            @Override
            public void offsetPositionsForRemovingInvisible(final int n, final int n2) {
                RecyclerView.this.offsetPositionRecordsForRemove(n, n2, true);
                RecyclerView.this.mItemsAddedOrRemoved = true;
                State.access$1812(RecyclerView.this.mState, n2);
            }
            
            @Override
            public void offsetPositionsForRemovingLaidOutOrNewView(final int n, final int n2) {
                RecyclerView.this.offsetPositionRecordsForRemove(n, n2, false);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }
            
            @Override
            public void onDispatchFirstPass(final UpdateOp updateOp) {
                this.dispatchUpdate(updateOp);
            }
            
            @Override
            public void onDispatchSecondPass(final UpdateOp updateOp) {
                this.dispatchUpdate(updateOp);
            }
        });
    }
    
    void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }
    
    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() == 0) {
            return;
        }
        if (this.mLayout != null) {
            this.mLayout.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
        }
        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }
    
    boolean isAccessibilityEnabled() {
        return this.mAccessibilityManager != null && this.mAccessibilityManager.isEnabled();
    }
    
    public boolean isAnimating() {
        return this.mItemAnimator != null && this.mItemAnimator.isRunning();
    }
    
    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }
    
    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }
    
    public boolean isLayoutFrozen() {
        return this.mLayoutFrozen;
    }
    
    public boolean isNestedScrollingEnabled() {
        return this.mScrollingChildHelper.isNestedScrollingEnabled();
    }
    
    void markItemDecorInsetsDirty() {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            ((LayoutParams)this.mChildHelper.getUnfilteredChildAt(i).getLayoutParams()).mInsetsDirty = true;
        }
        this.mRecycler.markItemDecorInsetsDirty();
    }
    
    void markKnownViewsInvalid() {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        this.markItemDecorInsetsDirty();
        this.mRecycler.markKnownViewsInvalid();
    }
    
    public void offsetChildrenHorizontal(final int n) {
        for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
            this.mChildHelper.getChildAt(i).offsetLeftAndRight(n);
        }
    }
    
    public void offsetChildrenVertical(final int n) {
        for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
            this.mChildHelper.getChildAt(i).offsetTopAndBottom(n);
        }
    }
    
    void offsetPositionRecordsForInsert(final int n, final int n2) {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= n) {
                childViewHolderInt.offsetPosition(n2, false);
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForInsert(n, n2);
        this.requestLayout();
    }
    
    void offsetPositionRecordsForMove(final int n, final int n2) {
        final int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        int n3;
        int n4;
        int n5;
        if (n < n2) {
            n3 = n;
            n4 = n2;
            n5 = -1;
        }
        else {
            n3 = n2;
            n4 = n;
            n5 = 1;
        }
        for (int i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && childViewHolderInt.mPosition >= n3 && childViewHolderInt.mPosition <= n4) {
                if (childViewHolderInt.mPosition == n) {
                    childViewHolderInt.offsetPosition(n2 - n, false);
                }
                else {
                    childViewHolderInt.offsetPosition(n5, false);
                }
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForMove(n, n2);
        this.requestLayout();
    }
    
    void offsetPositionRecordsForRemove(final int n, final int n2, final boolean b) {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                if (childViewHolderInt.mPosition >= n + n2) {
                    childViewHolderInt.offsetPosition(-n2, b);
                    this.mState.mStructureChanged = true;
                }
                else if (childViewHolderInt.mPosition >= n) {
                    childViewHolderInt.flagRemovedAndOffsetPosition(n - 1, -n2, b);
                    this.mState.mStructureChanged = true;
                }
            }
        }
        this.mRecycler.offsetPositionRecordsForRemove(n, n2, b);
        this.requestLayout();
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        this.mIsAttached = true;
        this.mFirstLayoutComplete = false;
        if (this.mLayout != null) {
            this.mLayout.dispatchAttachedToWindow(this);
        }
        this.mPostedAnimatorRunner = false;
    }
    
    public void onChildAttachedToWindow(final View view) {
    }
    
    public void onChildDetachedFromWindow(final View view) {
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mItemAnimator != null) {
            this.mItemAnimator.endAnimations();
        }
        this.mFirstLayoutComplete = false;
        this.stopScroll();
        this.mIsAttached = false;
        if (this.mLayout != null) {
            this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
        }
        this.removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.onDetach();
    }
    
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        for (int size = this.mItemDecorations.size(), i = 0; i < size; ++i) {
            this.mItemDecorations.get(i).onDraw(canvas, this, this.mState);
        }
    }
    
    public boolean onGenericMotionEvent(final MotionEvent motionEvent) {
        if (this.mLayout != null && !this.mLayoutFrozen && (MotionEventCompat.getSource(motionEvent) & 0x2) != 0x0 && motionEvent.getAction() == 8) {
            float n;
            if (this.mLayout.canScrollVertically()) {
                n = -MotionEventCompat.getAxisValue(motionEvent, 9);
            }
            else {
                n = 0.0f;
            }
            float axisValue;
            if (this.mLayout.canScrollHorizontally()) {
                axisValue = MotionEventCompat.getAxisValue(motionEvent, 10);
            }
            else {
                axisValue = 0.0f;
            }
            if (n != 0.0f || axisValue != 0.0f) {
                final float scrollFactor = this.getScrollFactor();
                this.scrollByInternal((int)(axisValue * scrollFactor), (int)(n * scrollFactor), motionEvent);
                return false;
            }
        }
        return false;
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        if (this.mLayoutFrozen) {
            return false;
        }
        if (this.dispatchOnItemTouchIntercept(motionEvent)) {
            this.cancelTouch();
            return true;
        }
        if (this.mLayout == null) {
            return false;
        }
        final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
        final boolean canScrollVertically = this.mLayout.canScrollVertically();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        switch (actionMasked) {
            case 0: {
                if (this.mIgnoreMotionEventTillDown) {
                    this.mIgnoreMotionEventTillDown = false;
                }
                this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                final int n = (int)(motionEvent.getX() + 0.5f);
                this.mLastTouchX = n;
                this.mInitialTouchX = n;
                final int n2 = (int)(motionEvent.getY() + 0.5f);
                this.mLastTouchY = n2;
                this.mInitialTouchY = n2;
                if (this.mScrollState == 2) {
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                    this.setScrollState(1);
                }
                this.mNestedOffsets[this.mNestedOffsets[1] = 0] = 0;
                int n3 = 0;
                if (canScrollHorizontally) {
                    n3 = ((false | true) ? 1 : 0);
                }
                int n4 = n3;
                if (canScrollVertically) {
                    n4 = (n3 | 0x2);
                }
                this.startNestedScroll(n4);
                break;
            }
            case 5: {
                this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                final int n5 = (int)(MotionEventCompat.getX(motionEvent, actionIndex) + 0.5f);
                this.mLastTouchX = n5;
                this.mInitialTouchX = n5;
                final int n6 = (int)(MotionEventCompat.getY(motionEvent, actionIndex) + 0.5f);
                this.mLastTouchY = n6;
                this.mInitialTouchY = n6;
                break;
            }
            case 2: {
                final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mScrollPointerId);
                if (pointerIndex < 0) {
                    Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                    return false;
                }
                final int n7 = (int)(MotionEventCompat.getX(motionEvent, pointerIndex) + 0.5f);
                final int n8 = (int)(MotionEventCompat.getY(motionEvent, pointerIndex) + 0.5f);
                if (this.mScrollState == 1) {
                    break;
                }
                final int n9 = n7 - this.mInitialTouchX;
                final int n10 = n8 - this.mInitialTouchY;
                boolean b2;
                final boolean b = b2 = false;
                if (canScrollHorizontally) {
                    b2 = b;
                    if (Math.abs(n9) > this.mTouchSlop) {
                        final int mInitialTouchX = this.mInitialTouchX;
                        final int mTouchSlop = this.mTouchSlop;
                        int n11;
                        if (n9 < 0) {
                            n11 = -1;
                        }
                        else {
                            n11 = 1;
                        }
                        this.mLastTouchX = n11 * mTouchSlop + mInitialTouchX;
                        b2 = true;
                    }
                }
                boolean b3 = b2;
                if (canScrollVertically) {
                    b3 = b2;
                    if (Math.abs(n10) > this.mTouchSlop) {
                        final int mInitialTouchY = this.mInitialTouchY;
                        final int mTouchSlop2 = this.mTouchSlop;
                        int n12;
                        if (n10 < 0) {
                            n12 = -1;
                        }
                        else {
                            n12 = 1;
                        }
                        this.mLastTouchY = n12 * mTouchSlop2 + mInitialTouchY;
                        b3 = true;
                    }
                }
                if (b3) {
                    this.setScrollState(1);
                    break;
                }
                break;
            }
            case 6: {
                this.onPointerUp(motionEvent);
                break;
            }
            case 1: {
                this.mVelocityTracker.clear();
                this.stopNestedScroll();
                break;
            }
            case 3: {
                this.cancelTouch();
                break;
            }
        }
        return this.mScrollState == 1;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.eatRequestLayout();
        TraceCompat.beginSection("RV OnLayout");
        this.dispatchLayout();
        TraceCompat.endSection();
        this.resumeRequestLayout(false);
        this.mFirstLayoutComplete = true;
    }
    
    protected void onMeasure(final int n, final int n2) {
        if (this.mAdapterUpdateDuringMeasure) {
            this.eatRequestLayout();
            this.processAdapterUpdatesAndSetAnimationFlags();
            if (this.mState.mRunPredictiveAnimations) {
                this.mState.mInPreLayout = true;
            }
            else {
                this.mAdapterHelper.consumeUpdatesInOnePass();
                this.mState.mInPreLayout = false;
            }
            this.resumeRequestLayout(this.mAdapterUpdateDuringMeasure = false);
        }
        if (this.mAdapter != null) {
            this.mState.mItemCount = this.mAdapter.getItemCount();
        }
        else {
            this.mState.mItemCount = 0;
        }
        if (this.mLayout == null) {
            this.defaultOnMeasure(n, n2);
        }
        else {
            this.mLayout.onMeasure(this.mRecycler, this.mState, n, n2);
        }
        this.mState.mInPreLayout = false;
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        this.mPendingSavedState = (SavedState)parcelable;
        super.onRestoreInstanceState(this.mPendingSavedState.getSuperState());
        if (this.mLayout != null && this.mPendingSavedState.mLayoutState != null) {
            this.mLayout.onRestoreInstanceState(this.mPendingSavedState.mLayoutState);
        }
    }
    
    protected Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.mPendingSavedState != null) {
            savedState.copyFrom(this.mPendingSavedState);
            return (Parcelable)savedState;
        }
        if (this.mLayout != null) {
            savedState.mLayoutState = this.mLayout.onSaveInstanceState();
            return (Parcelable)savedState;
        }
        savedState.mLayoutState = null;
        return (Parcelable)savedState;
    }
    
    public void onScrollStateChanged(final int n) {
    }
    
    public void onScrolled(final int n, final int n2) {
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (n != n3 || n2 != n4) {
            this.invalidateGlows();
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (this.mLayoutFrozen || this.mIgnoreMotionEventTillDown) {
            return false;
        }
        if (this.dispatchOnItemTouch(motionEvent)) {
            this.cancelTouch();
            return true;
        }
        if (this.mLayout == null) {
            return false;
        }
        final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
        final boolean canScrollVertically = this.mLayout.canScrollVertically();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        final boolean b = false;
        final MotionEvent obtain = MotionEvent.obtain(motionEvent);
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (actionMasked == 0) {
            this.mNestedOffsets[this.mNestedOffsets[1] = 0] = 0;
        }
        obtain.offsetLocation((float)this.mNestedOffsets[0], (float)this.mNestedOffsets[1]);
        int n = b ? 1 : 0;
        while (true) {
            switch (actionMasked) {
                default: {
                    n = (b ? 1 : 0);
                    break Label_0188;
                }
                case 3: {
                    this.cancelTouch();
                    n = (b ? 1 : 0);
                    break Label_0188;
                }
                case 1: {
                    this.mVelocityTracker.addMovement(obtain);
                    n = 1;
                    this.mVelocityTracker.computeCurrentVelocity(1000, (float)this.mMaxFlingVelocity);
                    float n2;
                    if (canScrollHorizontally) {
                        n2 = -VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mScrollPointerId);
                    }
                    else {
                        n2 = 0.0f;
                    }
                    float n3;
                    if (canScrollVertically) {
                        n3 = -VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mScrollPointerId);
                    }
                    else {
                        n3 = 0.0f;
                    }
                    if ((n2 == 0.0f && n3 == 0.0f) || !this.fling((int)n2, (int)n3)) {
                        this.setScrollState(0);
                    }
                    this.resetTouch();
                    break Label_0188;
                }
                case 6: {
                    this.onPointerUp(motionEvent);
                    n = (b ? 1 : 0);
                    break Label_0188;
                }
                case 5: {
                    this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    final int n4 = (int)(MotionEventCompat.getX(motionEvent, actionIndex) + 0.5f);
                    this.mLastTouchX = n4;
                    this.mInitialTouchX = n4;
                    final int n5 = (int)(MotionEventCompat.getY(motionEvent, actionIndex) + 0.5f);
                    this.mLastTouchY = n5;
                    this.mInitialTouchY = n5;
                    n = (b ? 1 : 0);
                    break Label_0188;
                }
                case 0: {
                    this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    final int n6 = (int)(motionEvent.getX() + 0.5f);
                    this.mLastTouchX = n6;
                    this.mInitialTouchX = n6;
                    final int n7 = (int)(motionEvent.getY() + 0.5f);
                    this.mLastTouchY = n7;
                    this.mInitialTouchY = n7;
                    int n8 = 0;
                    if (canScrollHorizontally) {
                        n8 = ((false | true) ? 1 : 0);
                    }
                    int n9 = n8;
                    if (canScrollVertically) {
                        n9 = (n8 | 0x2);
                    }
                    this.startNestedScroll(n9);
                    n = (b ? 1 : 0);
                }
                case 4: {
                    if (n == 0) {
                        this.mVelocityTracker.addMovement(obtain);
                    }
                    obtain.recycle();
                    return true;
                }
                case 2: {
                    final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mScrollPointerId);
                    if (pointerIndex < 0) {
                        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                        return false;
                    }
                    final int n10 = (int)(MotionEventCompat.getX(motionEvent, pointerIndex) + 0.5f);
                    final int n11 = (int)(MotionEventCompat.getY(motionEvent, pointerIndex) + 0.5f);
                    final int n12 = this.mLastTouchX - n10;
                    final int n13 = this.mLastTouchY - n11;
                    int n14 = n12;
                    int n15 = n13;
                    if (this.dispatchNestedPreScroll(n12, n13, this.mScrollConsumed, this.mScrollOffset)) {
                        n14 = n12 - this.mScrollConsumed[0];
                        n15 = n13 - this.mScrollConsumed[1];
                        obtain.offsetLocation((float)this.mScrollOffset[0], (float)this.mScrollOffset[1]);
                        final int[] mNestedOffsets = this.mNestedOffsets;
                        mNestedOffsets[0] += this.mScrollOffset[0];
                        final int[] mNestedOffsets2 = this.mNestedOffsets;
                        mNestedOffsets2[1] += this.mScrollOffset[1];
                    }
                    int n16 = n14;
                    int n17 = n15;
                    if (this.mScrollState != 1) {
                        final boolean b2 = false;
                        int n18 = n14;
                        boolean b3 = b2;
                        if (canScrollHorizontally) {
                            n18 = n14;
                            b3 = b2;
                            if (Math.abs(n14) > this.mTouchSlop) {
                                if (n14 > 0) {
                                    n18 = n14 - this.mTouchSlop;
                                }
                                else {
                                    n18 = n14 + this.mTouchSlop;
                                }
                                b3 = true;
                            }
                        }
                        int n19 = n15;
                        boolean b4 = b3;
                        if (canScrollVertically) {
                            n19 = n15;
                            b4 = b3;
                            if (Math.abs(n15) > this.mTouchSlop) {
                                if (n15 > 0) {
                                    n19 = n15 - this.mTouchSlop;
                                }
                                else {
                                    n19 = n15 + this.mTouchSlop;
                                }
                                b4 = true;
                            }
                        }
                        n16 = n18;
                        n17 = n19;
                        if (b4) {
                            this.setScrollState(1);
                            n17 = n19;
                            n16 = n18;
                        }
                    }
                    n = (b ? 1 : 0);
                    if (this.mScrollState != 1) {
                        continue;
                    }
                    this.mLastTouchX = n10 - this.mScrollOffset[0];
                    this.mLastTouchY = n11 - this.mScrollOffset[1];
                    if (!canScrollHorizontally) {
                        n16 = 0;
                    }
                    if (!canScrollVertically) {
                        n17 = 0;
                    }
                    n = (b ? 1 : 0);
                    if (this.scrollByInternal(n16, n17, obtain)) {
                        this.getParent().requestDisallowInterceptTouchEvent(true);
                        n = (b ? 1 : 0);
                    }
                    continue;
                }
            }
            break;
        }
    }
    
    protected void removeDetachedView(final View view, final boolean b) {
        final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.clearTmpDetachFlag();
            }
            else if (!childViewHolderInt.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + childViewHolderInt);
            }
        }
        this.dispatchChildDetached(view);
        super.removeDetachedView(view, b);
    }
    
    public void removeItemDecoration(final ItemDecoration itemDecoration) {
        if (this.mLayout != null) {
            this.mLayout.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(itemDecoration);
        if (this.mItemDecorations.isEmpty()) {
            this.setWillNotDraw(ViewCompat.getOverScrollMode((View)this) == 2);
        }
        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }
    
    public void removeOnChildAttachStateChangeListener(final OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        if (this.mOnChildAttachStateListeners == null) {
            return;
        }
        this.mOnChildAttachStateListeners.remove(onChildAttachStateChangeListener);
    }
    
    public void removeOnItemTouchListener(final OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.remove(onItemTouchListener);
        if (this.mActiveOnItemTouchListener == onItemTouchListener) {
            this.mActiveOnItemTouchListener = null;
        }
    }
    
    public void removeOnScrollListener(final OnScrollListener onScrollListener) {
        if (this.mScrollListeners != null) {
            this.mScrollListeners.remove(onScrollListener);
        }
    }
    
    public void requestChildFocus(final View view, final View view2) {
        boolean b = false;
        if (!this.mLayout.onRequestChildFocus(this, this.mState, view, view2) && view2 != null) {
            this.mTempRect.set(0, 0, view2.getWidth(), view2.getHeight());
            final ViewGroup$LayoutParams layoutParams = view2.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                final LayoutParams layoutParams2 = (LayoutParams)layoutParams;
                if (!layoutParams2.mInsetsDirty) {
                    final Rect mDecorInsets = layoutParams2.mDecorInsets;
                    final Rect mTempRect = this.mTempRect;
                    mTempRect.left -= mDecorInsets.left;
                    final Rect mTempRect2 = this.mTempRect;
                    mTempRect2.right += mDecorInsets.right;
                    final Rect mTempRect3 = this.mTempRect;
                    mTempRect3.top -= mDecorInsets.top;
                    final Rect mTempRect4 = this.mTempRect;
                    mTempRect4.bottom += mDecorInsets.bottom;
                }
            }
            this.offsetDescendantRectToMyCoords(view2, this.mTempRect);
            this.offsetRectIntoDescendantCoords(view, this.mTempRect);
            final Rect mTempRect5 = this.mTempRect;
            if (!this.mFirstLayoutComplete) {
                b = true;
            }
            this.requestChildRectangleOnScreen(view, mTempRect5, b);
        }
        super.requestChildFocus(view, view2);
    }
    
    public boolean requestChildRectangleOnScreen(final View view, final Rect rect, final boolean b) {
        return this.mLayout.requestChildRectangleOnScreen(this, view, rect, b);
    }
    
    public void requestDisallowInterceptTouchEvent(final boolean b) {
        for (int size = this.mOnItemTouchListeners.size(), i = 0; i < size; ++i) {
            this.mOnItemTouchListeners.get(i).onRequestDisallowInterceptTouchEvent(b);
        }
        super.requestDisallowInterceptTouchEvent(b);
    }
    
    public void requestLayout() {
        if (!this.mEatRequestLayout && !this.mLayoutFrozen) {
            super.requestLayout();
            return;
        }
        this.mLayoutRequestEaten = true;
    }
    
    void resumeRequestLayout(final boolean b) {
        if (this.mEatRequestLayout) {
            if (b && this.mLayoutRequestEaten && !this.mLayoutFrozen && this.mLayout != null && this.mAdapter != null) {
                this.dispatchLayout();
            }
            this.mEatRequestLayout = false;
            if (!this.mLayoutFrozen) {
                this.mLayoutRequestEaten = false;
            }
        }
    }
    
    void saveOldPositions() {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.saveOldPosition();
            }
        }
    }
    
    public void scrollBy(int n, int n2) {
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        }
        else if (!this.mLayoutFrozen) {
            final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
            final boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (canScrollHorizontally || canScrollVertically) {
                if (!canScrollHorizontally) {
                    n = 0;
                }
                if (!canScrollVertically) {
                    n2 = 0;
                }
                this.scrollByInternal(n, n2, null);
            }
        }
    }
    
    boolean scrollByInternal(final int n, final int n2, final MotionEvent motionEvent) {
        int n3 = 0;
        final int n4 = 0;
        int n5 = 0;
        final int n6 = 0;
        int scrollHorizontallyBy = 0;
        final int n7 = 0;
        int scrollVerticallyBy = 0;
        final int n8 = 0;
        this.consumePendingUpdateOperations();
        if (this.mAdapter != null) {
            this.eatRequestLayout();
            this.onEnterLayoutOrScroll();
            TraceCompat.beginSection("RV Scroll");
            scrollHorizontallyBy = n7;
            n3 = n4;
            if (n != 0) {
                scrollHorizontallyBy = this.mLayout.scrollHorizontallyBy(n, this.mRecycler, this.mState);
                n3 = n - scrollHorizontallyBy;
            }
            scrollVerticallyBy = n8;
            n5 = n6;
            if (n2 != 0) {
                scrollVerticallyBy = this.mLayout.scrollVerticallyBy(n2, this.mRecycler, this.mState);
                n5 = n2 - scrollVerticallyBy;
            }
            TraceCompat.endSection();
            this.repositionShadowingViews();
            this.onExitLayoutOrScroll();
            this.resumeRequestLayout(false);
        }
        if (!this.mItemDecorations.isEmpty()) {
            this.invalidate();
        }
        if (this.dispatchNestedScroll(scrollHorizontallyBy, scrollVerticallyBy, n3, n5, this.mScrollOffset)) {
            this.mLastTouchX -= this.mScrollOffset[0];
            this.mLastTouchY -= this.mScrollOffset[1];
            if (motionEvent != null) {
                motionEvent.offsetLocation((float)this.mScrollOffset[0], (float)this.mScrollOffset[1]);
            }
            final int[] mNestedOffsets = this.mNestedOffsets;
            mNestedOffsets[0] += this.mScrollOffset[0];
            final int[] mNestedOffsets2 = this.mNestedOffsets;
            mNestedOffsets2[1] += this.mScrollOffset[1];
        }
        else if (ViewCompat.getOverScrollMode((View)this) != 2) {
            if (motionEvent != null) {
                this.pullGlows(motionEvent.getX(), n3, motionEvent.getY(), n5);
            }
            this.considerReleasingGlowsOnScroll(n, n2);
        }
        if (scrollHorizontallyBy != 0 || scrollVerticallyBy != 0) {
            this.dispatchOnScrolled(scrollHorizontallyBy, scrollVerticallyBy);
        }
        if (!this.awakenScrollBars()) {
            this.invalidate();
        }
        return scrollHorizontallyBy != 0 || scrollVerticallyBy != 0;
    }
    
    public void scrollTo(final int n, final int n2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }
    
    public void scrollToPosition(final int n) {
        if (this.mLayoutFrozen) {
            return;
        }
        this.stopScroll();
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        this.mLayout.scrollToPosition(n);
        this.awakenScrollBars();
    }
    
    public void sendAccessibilityEventUnchecked(final AccessibilityEvent accessibilityEvent) {
        if (this.shouldDeferAccessibilityEvent(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }
    
    public void setAccessibilityDelegateCompat(final RecyclerViewAccessibilityDelegate mAccessibilityDelegate) {
        ViewCompat.setAccessibilityDelegate((View)this, this.mAccessibilityDelegate = mAccessibilityDelegate);
    }
    
    public void setAdapter(final Adapter adapter) {
        this.setLayoutFrozen(false);
        this.setAdapterInternal(adapter, false, true);
        this.requestLayout();
    }
    
    public void setChildDrawingOrderCallback(final ChildDrawingOrderCallback mChildDrawingOrderCallback) {
        if (mChildDrawingOrderCallback == this.mChildDrawingOrderCallback) {
            return;
        }
        this.mChildDrawingOrderCallback = mChildDrawingOrderCallback;
        this.setChildrenDrawingOrderEnabled(this.mChildDrawingOrderCallback != null);
    }
    
    public void setClipToPadding(final boolean mClipToPadding) {
        if (mClipToPadding != this.mClipToPadding) {
            this.invalidateGlows();
        }
        super.setClipToPadding(this.mClipToPadding = mClipToPadding);
        if (this.mFirstLayoutComplete) {
            this.requestLayout();
        }
    }
    
    public void setHasFixedSize(final boolean mHasFixedSize) {
        this.mHasFixedSize = mHasFixedSize;
    }
    
    public void setItemAnimator(final ItemAnimator mItemAnimator) {
        if (this.mItemAnimator != null) {
            this.mItemAnimator.endAnimations();
            this.mItemAnimator.setListener(null);
        }
        this.mItemAnimator = mItemAnimator;
        if (this.mItemAnimator != null) {
            this.mItemAnimator.setListener(this.mItemAnimatorListener);
        }
    }
    
    public void setItemViewCacheSize(final int viewCacheSize) {
        this.mRecycler.setViewCacheSize(viewCacheSize);
    }
    
    public void setLayoutFrozen(final boolean b) {
        if (b != this.mLayoutFrozen) {
            this.assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
            if (b) {
                final long uptimeMillis = SystemClock.uptimeMillis();
                this.onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
                this.mLayoutFrozen = b;
                this.mIgnoreMotionEventTillDown = true;
                this.stopScroll();
                return;
            }
            this.mLayoutFrozen = b;
            if (this.mLayoutRequestEaten && this.mLayout != null && this.mAdapter != null) {
                this.requestLayout();
            }
            this.mLayoutRequestEaten = false;
        }
    }
    
    public void setLayoutManager(final LayoutManager mLayout) {
        if (mLayout == this.mLayout) {
            return;
        }
        if (this.mLayout != null) {
            if (this.mIsAttached) {
                this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
            }
            this.mLayout.setRecyclerView(null);
        }
        this.mRecycler.clear();
        this.mChildHelper.removeAllViewsUnfiltered();
        if ((this.mLayout = mLayout) != null) {
            if (mLayout.mRecyclerView != null) {
                throw new IllegalArgumentException("LayoutManager " + mLayout + " is already attached to a RecyclerView: " + mLayout.mRecyclerView);
            }
            this.mLayout.setRecyclerView(this);
            if (this.mIsAttached) {
                this.mLayout.dispatchAttachedToWindow(this);
            }
        }
        this.requestLayout();
    }
    
    public void setNestedScrollingEnabled(final boolean nestedScrollingEnabled) {
        this.mScrollingChildHelper.setNestedScrollingEnabled(nestedScrollingEnabled);
    }
    
    @Deprecated
    public void setOnScrollListener(final OnScrollListener mScrollListener) {
        this.mScrollListener = mScrollListener;
    }
    
    public void setRecycledViewPool(final RecycledViewPool recycledViewPool) {
        this.mRecycler.setRecycledViewPool(recycledViewPool);
    }
    
    public void setRecyclerListener(final RecyclerListener mRecyclerListener) {
        this.mRecyclerListener = mRecyclerListener;
    }
    
    public void setScrollingTouchSlop(final int n) {
        final ViewConfiguration value = ViewConfiguration.get(this.getContext());
        switch (n) {
            default: {
                Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + n + "; using default value");
            }
            case 0: {
                this.mTouchSlop = value.getScaledTouchSlop();
            }
            case 1: {
                this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(value);
            }
        }
    }
    
    public void setViewCacheExtension(final ViewCacheExtension viewCacheExtension) {
        this.mRecycler.setViewCacheExtension(viewCacheExtension);
    }
    
    boolean shouldDeferAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        if (this.isComputingLayout()) {
            int contentChangeTypes = 0;
            if (accessibilityEvent != null) {
                contentChangeTypes = AccessibilityEventCompat.getContentChangeTypes(accessibilityEvent);
            }
            int n;
            if ((n = contentChangeTypes) == 0) {
                n = 0;
            }
            this.mEatenAccessibilityChangeFlags |= n;
            return true;
        }
        return false;
    }
    
    public void smoothScrollBy(int n, int n2) {
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        }
        else if (!this.mLayoutFrozen) {
            if (!this.mLayout.canScrollHorizontally()) {
                n = 0;
            }
            if (!this.mLayout.canScrollVertically()) {
                n2 = 0;
            }
            if (n != 0 || n2 != 0) {
                this.mViewFlinger.smoothScrollBy(n, n2);
            }
        }
    }
    
    public void smoothScrollToPosition(final int n) {
        if (this.mLayoutFrozen) {
            return;
        }
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        this.mLayout.smoothScrollToPosition(this, this.mState, n);
    }
    
    public boolean startNestedScroll(final int n) {
        return this.mScrollingChildHelper.startNestedScroll(n);
    }
    
    public void stopNestedScroll() {
        this.mScrollingChildHelper.stopNestedScroll();
    }
    
    public void stopScroll() {
        this.setScrollState(0);
        this.stopScrollersInternal();
    }
    
    public void swapAdapter(final Adapter adapter, final boolean b) {
        this.setLayoutFrozen(false);
        this.setAdapterInternal(adapter, true, b);
        this.setDataSetChangedAfterLayout();
        this.requestLayout();
    }
    
    void viewRangeUpdate(final int n, final int n2, final Object o) {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final View unfilteredChild = this.mChildHelper.getUnfilteredChildAt(i);
            final ViewHolder childViewHolderInt = getChildViewHolderInt(unfilteredChild);
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= n && childViewHolderInt.mPosition < n + n2) {
                childViewHolderInt.addFlags(2);
                childViewHolderInt.addChangePayload(o);
                ((LayoutParams)unfilteredChild.getLayoutParams()).mInsetsDirty = true;
            }
        }
        this.mRecycler.viewRangeUpdate(n, n2);
    }
    
    public abstract static class Adapter<VH extends ViewHolder>
    {
        private boolean mHasStableIds;
        private final AdapterDataObservable mObservable;
        
        public Adapter() {
            this.mObservable = new AdapterDataObservable();
            this.mHasStableIds = false;
        }
        
        public final void bindViewHolder(final VH vh, final int mPosition) {
            vh.mPosition = mPosition;
            if (this.hasStableIds()) {
                vh.mItemId = this.getItemId(mPosition);
            }
            ((ViewHolder)vh).setFlags(1, 519);
            TraceCompat.beginSection("RV OnBindView");
            this.onBindViewHolder(vh, mPosition, ((ViewHolder)vh).getUnmodifiedPayloads());
            ((ViewHolder)vh).clearPayload();
            TraceCompat.endSection();
        }
        
        public final VH createViewHolder(final ViewGroup viewGroup, final int mItemViewType) {
            TraceCompat.beginSection("RV CreateView");
            final ViewHolder onCreateViewHolder = this.onCreateViewHolder(viewGroup, mItemViewType);
            onCreateViewHolder.mItemViewType = mItemViewType;
            TraceCompat.endSection();
            return (VH)onCreateViewHolder;
        }
        
        public abstract int getItemCount();
        
        public long getItemId(final int n) {
            return -1L;
        }
        
        public int getItemViewType(final int n) {
            return 0;
        }
        
        public final boolean hasObservers() {
            return this.mObservable.hasObservers();
        }
        
        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }
        
        public final void notifyDataSetChanged() {
            this.mObservable.notifyChanged();
        }
        
        public final void notifyItemChanged(final int n) {
            this.mObservable.notifyItemRangeChanged(n, 1);
        }
        
        public final void notifyItemChanged(final int n, final Object o) {
            this.mObservable.notifyItemRangeChanged(n, 1, o);
        }
        
        public final void notifyItemInserted(final int n) {
            this.mObservable.notifyItemRangeInserted(n, 1);
        }
        
        public final void notifyItemMoved(final int n, final int n2) {
            this.mObservable.notifyItemMoved(n, n2);
        }
        
        public final void notifyItemRangeChanged(final int n, final int n2) {
            this.mObservable.notifyItemRangeChanged(n, n2);
        }
        
        public final void notifyItemRangeChanged(final int n, final int n2, final Object o) {
            this.mObservable.notifyItemRangeChanged(n, n2, o);
        }
        
        public final void notifyItemRangeInserted(final int n, final int n2) {
            this.mObservable.notifyItemRangeInserted(n, n2);
        }
        
        public final void notifyItemRangeRemoved(final int n, final int n2) {
            this.mObservable.notifyItemRangeRemoved(n, n2);
        }
        
        public final void notifyItemRemoved(final int n) {
            this.mObservable.notifyItemRangeRemoved(n, 1);
        }
        
        public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        }
        
        public abstract void onBindViewHolder(final VH p0, final int p1);
        
        public void onBindViewHolder(final VH vh, final int n, final List<Object> list) {
            this.onBindViewHolder(vh, n);
        }
        
        public abstract VH onCreateViewHolder(final ViewGroup p0, final int p1);
        
        public void onDetachedFromRecyclerView(final RecyclerView recyclerView) {
        }
        
        public boolean onFailedToRecycleView(final VH vh) {
            return false;
        }
        
        public void onViewAttachedToWindow(final VH vh) {
        }
        
        public void onViewDetachedFromWindow(final VH vh) {
        }
        
        public void onViewRecycled(final VH vh) {
        }
        
        public void registerAdapterDataObserver(final AdapterDataObserver adapterDataObserver) {
            this.mObservable.registerObserver((Object)adapterDataObserver);
        }
        
        public void setHasStableIds(final boolean mHasStableIds) {
            if (this.hasObservers()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.mHasStableIds = mHasStableIds;
        }
        
        public void unregisterAdapterDataObserver(final AdapterDataObserver adapterDataObserver) {
            this.mObservable.unregisterObserver((Object)adapterDataObserver);
        }
    }
    
    static class AdapterDataObservable extends Observable<AdapterDataObserver>
    {
        public boolean hasObservers() {
            return !this.mObservers.isEmpty();
        }
        
        public void notifyChanged() {
            for (int i = this.mObservers.size() - 1; i >= 0; --i) {
                ((AdapterDataObserver)this.mObservers.get(i)).onChanged();
            }
        }
        
        public void notifyItemMoved(final int n, final int n2) {
            for (int i = this.mObservers.size() - 1; i >= 0; --i) {
                ((AdapterDataObserver)this.mObservers.get(i)).onItemRangeMoved(n, n2, 1);
            }
        }
        
        public void notifyItemRangeChanged(final int n, final int n2) {
            this.notifyItemRangeChanged(n, n2, null);
        }
        
        public void notifyItemRangeChanged(final int n, final int n2, final Object o) {
            for (int i = this.mObservers.size() - 1; i >= 0; --i) {
                ((AdapterDataObserver)this.mObservers.get(i)).onItemRangeChanged(n, n2, o);
            }
        }
        
        public void notifyItemRangeInserted(final int n, final int n2) {
            for (int i = this.mObservers.size() - 1; i >= 0; --i) {
                ((AdapterDataObserver)this.mObservers.get(i)).onItemRangeInserted(n, n2);
            }
        }
        
        public void notifyItemRangeRemoved(final int n, final int n2) {
            for (int i = this.mObservers.size() - 1; i >= 0; --i) {
                ((AdapterDataObserver)this.mObservers.get(i)).onItemRangeRemoved(n, n2);
            }
        }
    }
    
    public abstract static class AdapterDataObserver
    {
        public void onChanged() {
        }
        
        public void onItemRangeChanged(final int n, final int n2) {
        }
        
        public void onItemRangeChanged(final int n, final int n2, final Object o) {
            this.onItemRangeChanged(n, n2);
        }
        
        public void onItemRangeInserted(final int n, final int n2) {
        }
        
        public void onItemRangeMoved(final int n, final int n2, final int n3) {
        }
        
        public void onItemRangeRemoved(final int n, final int n2) {
        }
    }
    
    public interface ChildDrawingOrderCallback
    {
        int onGetChildDrawingOrder(final int p0, final int p1);
    }
    
    public abstract static class ItemAnimator
    {
        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        public static final int FLAG_CHANGED = 2;
        public static final int FLAG_INVALIDATED = 4;
        public static final int FLAG_MOVED = 2048;
        public static final int FLAG_REMOVED = 8;
        private long mAddDuration;
        private long mChangeDuration;
        private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners;
        private ItemAnimatorListener mListener;
        private long mMoveDuration;
        private long mRemoveDuration;
        
        public ItemAnimator() {
            this.mListener = null;
            this.mFinishedListeners = new ArrayList<ItemAnimatorFinishedListener>();
            this.mAddDuration = 120L;
            this.mRemoveDuration = 120L;
            this.mMoveDuration = 250L;
            this.mChangeDuration = 250L;
        }
        
        static int buildAdapterChangeFlagsForAnimations(final ViewHolder viewHolder) {
            final int n = viewHolder.mFlags & 0xE;
            if (viewHolder.isInvalid()) {
                return 4;
            }
            int n2 = n;
            if ((n & 0x4) == 0x0) {
                final int oldPosition = viewHolder.getOldPosition();
                final int adapterPosition = viewHolder.getAdapterPosition();
                n2 = n;
                if (oldPosition != -1) {
                    n2 = n;
                    if (adapterPosition != -1) {
                        n2 = n;
                        if (oldPosition != adapterPosition) {
                            n2 = (n | 0x800);
                        }
                    }
                }
            }
            return n2;
        }
        
        public abstract boolean animateAppearance(@NonNull final ViewHolder p0, @Nullable final ItemHolderInfo p1, @NonNull final ItemHolderInfo p2);
        
        public abstract boolean animateChange(@NonNull final ViewHolder p0, @NonNull final ViewHolder p1, @NonNull final ItemHolderInfo p2, @NonNull final ItemHolderInfo p3);
        
        public abstract boolean animateDisappearance(@NonNull final ViewHolder p0, @NonNull final ItemHolderInfo p1, @Nullable final ItemHolderInfo p2);
        
        public abstract boolean animatePersistence(@NonNull final ViewHolder p0, @NonNull final ItemHolderInfo p1, @NonNull final ItemHolderInfo p2);
        
        public boolean canReuseUpdatedViewHolder(final ViewHolder viewHolder) {
            return true;
        }
        
        public final void dispatchAnimationFinished(final ViewHolder viewHolder) {
            this.onAnimationFinished(viewHolder);
            if (this.mListener != null) {
                this.mListener.onAnimationFinished(viewHolder);
            }
        }
        
        public final void dispatchAnimationStarted(final ViewHolder viewHolder) {
            this.onAnimationStarted(viewHolder);
        }
        
        public final void dispatchAnimationsFinished() {
            for (int size = this.mFinishedListeners.size(), i = 0; i < size; ++i) {
                this.mFinishedListeners.get(i).onAnimationsFinished();
            }
            this.mFinishedListeners.clear();
        }
        
        public abstract void endAnimation(final ViewHolder p0);
        
        public abstract void endAnimations();
        
        public long getAddDuration() {
            return this.mAddDuration;
        }
        
        public long getChangeDuration() {
            return this.mChangeDuration;
        }
        
        public long getMoveDuration() {
            return this.mMoveDuration;
        }
        
        public long getRemoveDuration() {
            return this.mRemoveDuration;
        }
        
        public abstract boolean isRunning();
        
        public final boolean isRunning(final ItemAnimatorFinishedListener itemAnimatorFinishedListener) {
            final boolean running = this.isRunning();
            if (itemAnimatorFinishedListener != null) {
                if (running) {
                    this.mFinishedListeners.add(itemAnimatorFinishedListener);
                    return running;
                }
                itemAnimatorFinishedListener.onAnimationsFinished();
            }
            return running;
        }
        
        public ItemHolderInfo obtainHolderInfo() {
            return new ItemHolderInfo();
        }
        
        public void onAnimationFinished(final ViewHolder viewHolder) {
        }
        
        public void onAnimationStarted(final ViewHolder viewHolder) {
        }
        
        @NonNull
        public ItemHolderInfo recordPostLayoutInformation(@NonNull final State state, @NonNull final ViewHolder from) {
            return this.obtainHolderInfo().setFrom(from);
        }
        
        @NonNull
        public ItemHolderInfo recordPreLayoutInformation(@NonNull final State state, @NonNull final ViewHolder from, final int n, @NonNull final List<Object> list) {
            return this.obtainHolderInfo().setFrom(from);
        }
        
        public abstract void runPendingAnimations();
        
        public void setAddDuration(final long mAddDuration) {
            this.mAddDuration = mAddDuration;
        }
        
        public void setChangeDuration(final long mChangeDuration) {
            this.mChangeDuration = mChangeDuration;
        }
        
        void setListener(final ItemAnimatorListener mListener) {
            this.mListener = mListener;
        }
        
        public void setMoveDuration(final long mMoveDuration) {
            this.mMoveDuration = mMoveDuration;
        }
        
        public void setRemoveDuration(final long mRemoveDuration) {
            this.mRemoveDuration = mRemoveDuration;
        }
        
        @Retention(RetentionPolicy.SOURCE)
        public @interface AdapterChanges {
        }
        
        public interface ItemAnimatorFinishedListener
        {
            void onAnimationsFinished();
        }
        
        interface ItemAnimatorListener
        {
            void onAnimationFinished(final ViewHolder p0);
        }
        
        public static class ItemHolderInfo
        {
            public int bottom;
            public int changeFlags;
            public int left;
            public int right;
            public int top;
            
            public ItemHolderInfo setFrom(final ViewHolder viewHolder) {
                return this.setFrom(viewHolder, 0);
            }
            
            public ItemHolderInfo setFrom(final ViewHolder viewHolder, final int n) {
                final View itemView = viewHolder.itemView;
                this.left = itemView.getLeft();
                this.top = itemView.getTop();
                this.right = itemView.getRight();
                this.bottom = itemView.getBottom();
                return this;
            }
        }
    }
    
    private class ItemAnimatorRestoreListener implements ItemAnimatorListener
    {
        @Override
        public void onAnimationFinished(final ViewHolder viewHolder) {
            viewHolder.setIsRecyclable(true);
            if (viewHolder.mShadowedHolder != null && viewHolder.mShadowingHolder == null) {
                viewHolder.mShadowedHolder = null;
            }
            viewHolder.mShadowingHolder = null;
            if (!viewHolder.shouldBeKeptAsChild() && !RecyclerView.this.removeAnimatingView(viewHolder.itemView) && viewHolder.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
            }
        }
    }
    
    public abstract static class ItemDecoration
    {
        @Deprecated
        public void getItemOffsets(final Rect rect, final int n, final RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }
        
        public void getItemOffsets(final Rect rect, final View view, final RecyclerView recyclerView, final State state) {
            this.getItemOffsets(rect, ((LayoutParams)view.getLayoutParams()).getViewLayoutPosition(), recyclerView);
        }
        
        @Deprecated
        public void onDraw(final Canvas canvas, final RecyclerView recyclerView) {
        }
        
        public void onDraw(final Canvas canvas, final RecyclerView recyclerView, final State state) {
            this.onDraw(canvas, recyclerView);
        }
        
        @Deprecated
        public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView) {
        }
        
        public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView, final State state) {
            this.onDrawOver(canvas, recyclerView);
        }
    }
    
    public abstract static class LayoutManager
    {
        ChildHelper mChildHelper;
        private boolean mIsAttachedToWindow;
        RecyclerView mRecyclerView;
        private boolean mRequestedSimpleAnimations;
        @Nullable
        SmoothScroller mSmoothScroller;
        
        public LayoutManager() {
            this.mRequestedSimpleAnimations = false;
            this.mIsAttachedToWindow = false;
        }
        
        private void addViewInt(final View view, final int n, final boolean b) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (b || childViewHolderInt.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt);
            }
            else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
            }
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
                if (childViewHolderInt.isScrap()) {
                    childViewHolderInt.unScrap();
                }
                else {
                    childViewHolderInt.clearReturnedFromScrapFlag();
                }
                this.mChildHelper.attachViewToParent(view, n, view.getLayoutParams(), false);
            }
            else if (view.getParent() == this.mRecyclerView) {
                final int indexOfChild = this.mChildHelper.indexOfChild(view);
                int childCount;
                if ((childCount = n) == -1) {
                    childCount = this.mChildHelper.getChildCount();
                }
                if (indexOfChild == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.mRecyclerView.indexOfChild(view));
                }
                if (indexOfChild != childCount) {
                    this.mRecyclerView.mLayout.moveView(indexOfChild, childCount);
                }
            }
            else {
                this.mChildHelper.addView(view, n, false);
                layoutParams.mInsetsDirty = true;
                if (this.mSmoothScroller != null && this.mSmoothScroller.isRunning()) {
                    this.mSmoothScroller.onChildAttachedToWindow(view);
                }
            }
            if (layoutParams.mPendingInvalidate) {
                childViewHolderInt.itemView.invalidate();
                layoutParams.mPendingInvalidate = false;
            }
        }
        
        private void detachViewInternal(final int n, final View view) {
            this.mChildHelper.detachViewFromParent(n);
        }
        
        public static int getChildMeasureSpec(int n, int n2, final int n3, final boolean b) {
            final int max = Math.max(0, n - n2);
            n2 = 0;
            n = 0;
            if (b) {
                if (n3 >= 0) {
                    n2 = n3;
                    n = 1073741824;
                }
                else {
                    n2 = 0;
                    n = 0;
                }
            }
            else if (n3 >= 0) {
                n2 = n3;
                n = 1073741824;
            }
            else if (n3 == -1) {
                n2 = max;
                n = 1073741824;
            }
            else if (n3 == -2) {
                n2 = max;
                n = Integer.MIN_VALUE;
            }
            return View$MeasureSpec.makeMeasureSpec(n2, n);
        }
        
        public static Properties getProperties(final Context context, final AttributeSet set, final int n, final int n2) {
            final Properties properties = new Properties();
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.RecyclerView, n, n2);
            properties.orientation = obtainStyledAttributes.getInt(R.styleable.RecyclerView_android_orientation, 1);
            properties.spanCount = obtainStyledAttributes.getInt(R.styleable.RecyclerView_spanCount, 1);
            properties.reverseLayout = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_reverseLayout, false);
            properties.stackFromEnd = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_stackFromEnd, false);
            obtainStyledAttributes.recycle();
            return properties;
        }
        
        private void onSmoothScrollerStopped(final SmoothScroller smoothScroller) {
            if (this.mSmoothScroller == smoothScroller) {
                this.mSmoothScroller = null;
            }
        }
        
        private void scrapOrRecycleView(final Recycler recycler, final int n, final View view) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.shouldIgnore()) {
                return;
            }
            if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !this.mRecyclerView.mAdapter.hasStableIds()) {
                this.removeViewAt(n);
                recycler.recycleViewHolderInternal(childViewHolderInt);
                return;
            }
            this.detachViewAt(n);
            recycler.scrapView(view);
        }
        
        public void addDisappearingView(final View view) {
            this.addDisappearingView(view, -1);
        }
        
        public void addDisappearingView(final View view, final int n) {
            this.addViewInt(view, n, true);
        }
        
        public void addView(final View view) {
            this.addView(view, -1);
        }
        
        public void addView(final View view, final int n) {
            this.addViewInt(view, n, false);
        }
        
        public void assertInLayoutOrScroll(final String s) {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.assertInLayoutOrScroll(s);
            }
        }
        
        public void assertNotInLayoutOrScroll(final String s) {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.assertNotInLayoutOrScroll(s);
            }
        }
        
        public void attachView(final View view) {
            this.attachView(view, -1);
        }
        
        public void attachView(final View view, final int n) {
            this.attachView(view, n, (LayoutParams)view.getLayoutParams());
        }
        
        public void attachView(final View view, final int n, final LayoutParams layoutParams) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt);
            }
            else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
            }
            this.mChildHelper.attachViewToParent(view, n, (ViewGroup$LayoutParams)layoutParams, childViewHolderInt.isRemoved());
        }
        
        public void calculateItemDecorationsForChild(final View view, final Rect rect) {
            if (this.mRecyclerView == null) {
                rect.set(0, 0, 0, 0);
                return;
            }
            rect.set(this.mRecyclerView.getItemDecorInsetsForChild(view));
        }
        
        public boolean canScrollHorizontally() {
            return false;
        }
        
        public boolean canScrollVertically() {
            return false;
        }
        
        public boolean checkLayoutParams(final LayoutParams layoutParams) {
            return layoutParams != null;
        }
        
        public int computeHorizontalScrollExtent(final State state) {
            return 0;
        }
        
        public int computeHorizontalScrollOffset(final State state) {
            return 0;
        }
        
        public int computeHorizontalScrollRange(final State state) {
            return 0;
        }
        
        public int computeVerticalScrollExtent(final State state) {
            return 0;
        }
        
        public int computeVerticalScrollOffset(final State state) {
            return 0;
        }
        
        public int computeVerticalScrollRange(final State state) {
            return 0;
        }
        
        public void detachAndScrapAttachedViews(final Recycler recycler) {
            for (int i = this.getChildCount() - 1; i >= 0; --i) {
                this.scrapOrRecycleView(recycler, i, this.getChildAt(i));
            }
        }
        
        public void detachAndScrapView(final View view, final Recycler recycler) {
            this.scrapOrRecycleView(recycler, this.mChildHelper.indexOfChild(view), view);
        }
        
        public void detachAndScrapViewAt(final int n, final Recycler recycler) {
            this.scrapOrRecycleView(recycler, n, this.getChildAt(n));
        }
        
        public void detachView(final View view) {
            final int indexOfChild = this.mChildHelper.indexOfChild(view);
            if (indexOfChild >= 0) {
                this.detachViewInternal(indexOfChild, view);
            }
        }
        
        public void detachViewAt(final int n) {
            this.detachViewInternal(n, this.getChildAt(n));
        }
        
        void dispatchAttachedToWindow(final RecyclerView recyclerView) {
            this.mIsAttachedToWindow = true;
            this.onAttachedToWindow(recyclerView);
        }
        
        void dispatchDetachedFromWindow(final RecyclerView recyclerView, final Recycler recycler) {
            this.mIsAttachedToWindow = false;
            this.onDetachedFromWindow(recyclerView, recycler);
        }
        
        public void endAnimation(final View view) {
            if (this.mRecyclerView.mItemAnimator != null) {
                this.mRecyclerView.mItemAnimator.endAnimation(RecyclerView.getChildViewHolderInt(view));
            }
        }
        
        public View findViewByPosition(final int n) {
            for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                final View child = this.getChildAt(i);
                final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(child);
                if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == n && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.isPreLayout() || !childViewHolderInt.isRemoved())) {
                    return child;
                }
            }
            return null;
        }
        
        public abstract LayoutParams generateDefaultLayoutParams();
        
        public LayoutParams generateLayoutParams(final Context context, final AttributeSet set) {
            return new LayoutParams(context, set);
        }
        
        public LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            if (viewGroup$LayoutParams instanceof LayoutParams) {
                return new LayoutParams((LayoutParams)viewGroup$LayoutParams);
            }
            if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
                return new LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
            }
            return new LayoutParams(viewGroup$LayoutParams);
        }
        
        public int getBaseline() {
            return -1;
        }
        
        public int getBottomDecorationHeight(final View view) {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.bottom;
        }
        
        public View getChildAt(final int n) {
            if (this.mChildHelper != null) {
                return this.mChildHelper.getChildAt(n);
            }
            return null;
        }
        
        public int getChildCount() {
            if (this.mChildHelper != null) {
                return this.mChildHelper.getChildCount();
            }
            return 0;
        }
        
        public boolean getClipToPadding() {
            return this.mRecyclerView != null && this.mRecyclerView.mClipToPadding;
        }
        
        public int getColumnCountForAccessibility(final Recycler recycler, final State state) {
            if (this.mRecyclerView != null && this.mRecyclerView.mAdapter != null && this.canScrollHorizontally()) {
                return this.mRecyclerView.mAdapter.getItemCount();
            }
            return 1;
        }
        
        public int getDecoratedBottom(final View view) {
            return view.getBottom() + this.getBottomDecorationHeight(view);
        }
        
        public int getDecoratedLeft(final View view) {
            return view.getLeft() - this.getLeftDecorationWidth(view);
        }
        
        public int getDecoratedMeasuredHeight(final View view) {
            final Rect mDecorInsets = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredHeight() + mDecorInsets.top + mDecorInsets.bottom;
        }
        
        public int getDecoratedMeasuredWidth(final View view) {
            final Rect mDecorInsets = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredWidth() + mDecorInsets.left + mDecorInsets.right;
        }
        
        public int getDecoratedRight(final View view) {
            return view.getRight() + this.getRightDecorationWidth(view);
        }
        
        public int getDecoratedTop(final View view) {
            return view.getTop() - this.getTopDecorationHeight(view);
        }
        
        public View getFocusedChild() {
            if (this.mRecyclerView != null) {
                final View focusedChild = this.mRecyclerView.getFocusedChild();
                if (focusedChild != null) {
                    final View view = focusedChild;
                    if (!this.mChildHelper.isHidden(focusedChild)) {
                        return view;
                    }
                }
                return null;
            }
            return null;
        }
        
        public int getHeight() {
            if (this.mRecyclerView != null) {
                return this.mRecyclerView.getHeight();
            }
            return 0;
        }
        
        public int getItemCount() {
            Object adapter;
            if (this.mRecyclerView != null) {
                adapter = this.mRecyclerView.getAdapter();
            }
            else {
                adapter = null;
            }
            if (adapter != null) {
                return ((Adapter)adapter).getItemCount();
            }
            return 0;
        }
        
        public int getItemViewType(final View view) {
            return RecyclerView.getChildViewHolderInt(view).getItemViewType();
        }
        
        public int getLayoutDirection() {
            return ViewCompat.getLayoutDirection((View)this.mRecyclerView);
        }
        
        public int getLeftDecorationWidth(final View view) {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.left;
        }
        
        public int getMinimumHeight() {
            return ViewCompat.getMinimumHeight((View)this.mRecyclerView);
        }
        
        public int getMinimumWidth() {
            return ViewCompat.getMinimumWidth((View)this.mRecyclerView);
        }
        
        public int getPaddingBottom() {
            if (this.mRecyclerView != null) {
                return this.mRecyclerView.getPaddingBottom();
            }
            return 0;
        }
        
        public int getPaddingEnd() {
            if (this.mRecyclerView != null) {
                return ViewCompat.getPaddingEnd((View)this.mRecyclerView);
            }
            return 0;
        }
        
        public int getPaddingLeft() {
            if (this.mRecyclerView != null) {
                return this.mRecyclerView.getPaddingLeft();
            }
            return 0;
        }
        
        public int getPaddingRight() {
            if (this.mRecyclerView != null) {
                return this.mRecyclerView.getPaddingRight();
            }
            return 0;
        }
        
        public int getPaddingStart() {
            if (this.mRecyclerView != null) {
                return ViewCompat.getPaddingStart((View)this.mRecyclerView);
            }
            return 0;
        }
        
        public int getPaddingTop() {
            if (this.mRecyclerView != null) {
                return this.mRecyclerView.getPaddingTop();
            }
            return 0;
        }
        
        public int getPosition(final View view) {
            return ((LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
        }
        
        public int getRightDecorationWidth(final View view) {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.right;
        }
        
        public int getRowCountForAccessibility(final Recycler recycler, final State state) {
            if (this.mRecyclerView != null && this.mRecyclerView.mAdapter != null && this.canScrollVertically()) {
                return this.mRecyclerView.mAdapter.getItemCount();
            }
            return 1;
        }
        
        public int getSelectionModeForAccessibility(final Recycler recycler, final State state) {
            return 0;
        }
        
        public int getTopDecorationHeight(final View view) {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.top;
        }
        
        public int getWidth() {
            if (this.mRecyclerView != null) {
                return this.mRecyclerView.getWidth();
            }
            return 0;
        }
        
        public boolean hasFocus() {
            return this.mRecyclerView != null && this.mRecyclerView.hasFocus();
        }
        
        public void ignoreView(final View view) {
            if (view.getParent() != this.mRecyclerView || this.mRecyclerView.indexOfChild(view) == -1) {
                throw new IllegalArgumentException("View should be fully attached to be ignored");
            }
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.addFlags(128);
            this.mRecyclerView.mViewInfoStore.removeViewHolder(childViewHolderInt);
        }
        
        public boolean isAttachedToWindow() {
            return this.mIsAttachedToWindow;
        }
        
        public boolean isFocused() {
            return this.mRecyclerView != null && this.mRecyclerView.isFocused();
        }
        
        public boolean isLayoutHierarchical(final Recycler recycler, final State state) {
            return false;
        }
        
        public boolean isSmoothScrolling() {
            return this.mSmoothScroller != null && this.mSmoothScroller.isRunning();
        }
        
        public void layoutDecorated(final View view, final int n, final int n2, final int n3, final int n4) {
            final Rect mDecorInsets = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            view.layout(mDecorInsets.left + n, mDecorInsets.top + n2, n3 - mDecorInsets.right, n4 - mDecorInsets.bottom);
        }
        
        public void measureChild(final View view, final int n, final int n2) {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            final Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
            view.measure(getChildMeasureSpec(this.getWidth(), this.getPaddingLeft() + this.getPaddingRight() + (n + (itemDecorInsetsForChild.left + itemDecorInsetsForChild.right)), layoutParams.width, this.canScrollHorizontally()), getChildMeasureSpec(this.getHeight(), this.getPaddingTop() + this.getPaddingBottom() + (n2 + (itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom)), layoutParams.height, this.canScrollVertically()));
        }
        
        public void measureChildWithMargins(final View view, final int n, final int n2) {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            final Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
            view.measure(getChildMeasureSpec(this.getWidth(), this.getPaddingLeft() + this.getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin + (n + (itemDecorInsetsForChild.left + itemDecorInsetsForChild.right)), layoutParams.width, this.canScrollHorizontally()), getChildMeasureSpec(this.getHeight(), this.getPaddingTop() + this.getPaddingBottom() + layoutParams.topMargin + layoutParams.bottomMargin + (n2 + (itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom)), layoutParams.height, this.canScrollVertically()));
        }
        
        public void moveView(final int n, final int n2) {
            final View child = this.getChildAt(n);
            if (child == null) {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + n);
            }
            this.detachViewAt(n);
            this.attachView(child, n2);
        }
        
        public void offsetChildrenHorizontal(final int n) {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.offsetChildrenHorizontal(n);
            }
        }
        
        public void offsetChildrenVertical(final int n) {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.offsetChildrenVertical(n);
            }
        }
        
        public void onAdapterChanged(final Adapter adapter, final Adapter adapter2) {
        }
        
        public boolean onAddFocusables(final RecyclerView recyclerView, final ArrayList<View> list, final int n, final int n2) {
            return false;
        }
        
        @CallSuper
        public void onAttachedToWindow(final RecyclerView recyclerView) {
        }
        
        @Deprecated
        public void onDetachedFromWindow(final RecyclerView recyclerView) {
        }
        
        @CallSuper
        public void onDetachedFromWindow(final RecyclerView recyclerView, final Recycler recycler) {
            this.onDetachedFromWindow(recyclerView);
        }
        
        @Nullable
        public View onFocusSearchFailed(final View view, final int n, final Recycler recycler, final State state) {
            return null;
        }
        
        public void onInitializeAccessibilityEvent(final Recycler recycler, final State state, final AccessibilityEvent accessibilityEvent) {
            final boolean b = true;
            final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(accessibilityEvent);
            if (this.mRecyclerView != null && record != null) {
                boolean scrollable = b;
                if (!ViewCompat.canScrollVertically((View)this.mRecyclerView, 1)) {
                    scrollable = b;
                    if (!ViewCompat.canScrollVertically((View)this.mRecyclerView, -1)) {
                        scrollable = b;
                        if (!ViewCompat.canScrollHorizontally((View)this.mRecyclerView, -1)) {
                            scrollable = (ViewCompat.canScrollHorizontally((View)this.mRecyclerView, 1) && b);
                        }
                    }
                }
                record.setScrollable(scrollable);
                if (this.mRecyclerView.mAdapter != null) {
                    record.setItemCount(this.mRecyclerView.mAdapter.getItemCount());
                }
            }
        }
        
        public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
            this.onInitializeAccessibilityEvent(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, accessibilityEvent);
        }
        
        void onInitializeAccessibilityNodeInfo(final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            this.onInitializeAccessibilityNodeInfo(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, accessibilityNodeInfoCompat);
        }
        
        public void onInitializeAccessibilityNodeInfo(final Recycler recycler, final State state, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (ViewCompat.canScrollVertically((View)this.mRecyclerView, -1) || ViewCompat.canScrollHorizontally((View)this.mRecyclerView, -1)) {
                accessibilityNodeInfoCompat.addAction(8192);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            if (ViewCompat.canScrollVertically((View)this.mRecyclerView, 1) || ViewCompat.canScrollHorizontally((View)this.mRecyclerView, 1)) {
                accessibilityNodeInfoCompat.addAction(4096);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(this.getRowCountForAccessibility(recycler, state), this.getColumnCountForAccessibility(recycler, state), this.isLayoutHierarchical(recycler, state), this.getSelectionModeForAccessibility(recycler, state)));
        }
        
        public void onInitializeAccessibilityNodeInfoForItem(final Recycler recycler, final State state, final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int position;
            if (this.canScrollVertically()) {
                position = this.getPosition(view);
            }
            else {
                position = 0;
            }
            int position2;
            if (this.canScrollHorizontally()) {
                position2 = this.getPosition(view);
            }
            else {
                position2 = 0;
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(position, 1, position2, 1, false, false));
        }
        
        void onInitializeAccessibilityNodeInfoForItem(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && !this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                this.onInitializeAccessibilityNodeInfoForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, view, accessibilityNodeInfoCompat);
            }
        }
        
        public View onInterceptFocusSearch(final View view, final int n) {
            return null;
        }
        
        public void onItemsAdded(final RecyclerView recyclerView, final int n, final int n2) {
        }
        
        public void onItemsChanged(final RecyclerView recyclerView) {
        }
        
        public void onItemsMoved(final RecyclerView recyclerView, final int n, final int n2, final int n3) {
        }
        
        public void onItemsRemoved(final RecyclerView recyclerView, final int n, final int n2) {
        }
        
        public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2) {
        }
        
        public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2, final Object o) {
            this.onItemsUpdated(recyclerView, n, n2);
        }
        
        public void onLayoutChildren(final Recycler recycler, final State state) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }
        
        public void onMeasure(final Recycler recycler, final State state, final int n, final int n2) {
            this.mRecyclerView.defaultOnMeasure(n, n2);
        }
        
        public boolean onRequestChildFocus(final RecyclerView recyclerView, final State state, final View view, final View view2) {
            return this.onRequestChildFocus(recyclerView, view, view2);
        }
        
        @Deprecated
        public boolean onRequestChildFocus(final RecyclerView recyclerView, final View view, final View view2) {
            return this.isSmoothScrolling() || recyclerView.isComputingLayout();
        }
        
        public void onRestoreInstanceState(final Parcelable parcelable) {
        }
        
        public Parcelable onSaveInstanceState() {
            return null;
        }
        
        public void onScrollStateChanged(final int n) {
        }
        
        boolean performAccessibilityAction(final int n, final Bundle bundle) {
            return this.performAccessibilityAction(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, n, bundle);
        }
        
        public boolean performAccessibilityAction(final Recycler recycler, final State state, int n, final Bundle bundle) {
            if (this.mRecyclerView != null) {
                final boolean b = false;
                final boolean b2 = false;
                final int n2 = 0;
                int n3 = 0;
                switch (n) {
                    default: {
                        n = n2;
                        break;
                    }
                    case 8192: {
                        int n4 = b ? 1 : 0;
                        if (ViewCompat.canScrollVertically((View)this.mRecyclerView, -1)) {
                            n4 = -(this.getHeight() - this.getPaddingTop() - this.getPaddingBottom());
                        }
                        n = n4;
                        if (ViewCompat.canScrollHorizontally((View)this.mRecyclerView, -1)) {
                            n3 = -(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight());
                            n = n4;
                            break;
                        }
                        break;
                    }
                    case 4096: {
                        int n5 = b2 ? 1 : 0;
                        if (ViewCompat.canScrollVertically((View)this.mRecyclerView, 1)) {
                            n5 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
                        }
                        n = n5;
                        if (ViewCompat.canScrollHorizontally((View)this.mRecyclerView, 1)) {
                            n3 = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
                            n = n5;
                            break;
                        }
                        break;
                    }
                }
                if (n != 0 || n3 != 0) {
                    this.mRecyclerView.scrollBy(n3, n);
                    return true;
                }
            }
            return false;
        }
        
        public boolean performAccessibilityActionForItem(final Recycler recycler, final State state, final View view, final int n, final Bundle bundle) {
            return false;
        }
        
        boolean performAccessibilityActionForItem(final View view, final int n, final Bundle bundle) {
            return this.performAccessibilityActionForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, view, n, bundle);
        }
        
        public void postOnAnimation(final Runnable runnable) {
            if (this.mRecyclerView != null) {
                ViewCompat.postOnAnimation((View)this.mRecyclerView, runnable);
            }
        }
        
        public void removeAllViews() {
            for (int i = this.getChildCount() - 1; i >= 0; --i) {
                this.mChildHelper.removeViewAt(i);
            }
        }
        
        public void removeAndRecycleAllViews(final Recycler recycler) {
            for (int i = this.getChildCount() - 1; i >= 0; --i) {
                if (!RecyclerView.getChildViewHolderInt(this.getChildAt(i)).shouldIgnore()) {
                    this.removeAndRecycleViewAt(i, recycler);
                }
            }
        }
        
        void removeAndRecycleScrapInt(final Recycler recycler) {
            final int scrapCount = recycler.getScrapCount();
            for (int i = scrapCount - 1; i >= 0; --i) {
                final View scrapView = recycler.getScrapViewAt(i);
                final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(scrapView);
                if (!childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.setIsRecyclable(false);
                    if (childViewHolderInt.isTmpDetached()) {
                        this.mRecyclerView.removeDetachedView(scrapView, false);
                    }
                    if (this.mRecyclerView.mItemAnimator != null) {
                        this.mRecyclerView.mItemAnimator.endAnimation(childViewHolderInt);
                    }
                    childViewHolderInt.setIsRecyclable(true);
                    recycler.quickRecycleScrapView(scrapView);
                }
            }
            recycler.clearScrap();
            if (scrapCount > 0) {
                this.mRecyclerView.invalidate();
            }
        }
        
        public void removeAndRecycleView(final View view, final Recycler recycler) {
            this.removeView(view);
            recycler.recycleView(view);
        }
        
        public void removeAndRecycleViewAt(final int n, final Recycler recycler) {
            final View child = this.getChildAt(n);
            this.removeViewAt(n);
            recycler.recycleView(child);
        }
        
        public boolean removeCallbacks(final Runnable runnable) {
            return this.mRecyclerView != null && this.mRecyclerView.removeCallbacks(runnable);
        }
        
        public void removeDetachedView(final View view) {
            this.mRecyclerView.removeDetachedView(view, false);
        }
        
        public void removeView(final View view) {
            this.mChildHelper.removeView(view);
        }
        
        public void removeViewAt(final int n) {
            if (this.getChildAt(n) != null) {
                this.mChildHelper.removeViewAt(n);
            }
        }
        
        public boolean requestChildRectangleOnScreen(final RecyclerView recyclerView, final View view, final Rect rect, final boolean b) {
            final int paddingLeft = this.getPaddingLeft();
            final int paddingTop = this.getPaddingTop();
            final int n = this.getWidth() - this.getPaddingRight();
            final int height = this.getHeight();
            final int paddingBottom = this.getPaddingBottom();
            final int n2 = view.getLeft() + rect.left;
            final int n3 = view.getTop() + rect.top;
            final int n4 = n2 + rect.width();
            final int height2 = rect.height();
            int n5 = Math.min(0, n2 - paddingLeft);
            int n6 = Math.min(0, n3 - paddingTop);
            final int max = Math.max(0, n4 - n);
            final int max2 = Math.max(0, n3 + height2 - (height - paddingBottom));
            if (this.getLayoutDirection() == 1) {
                if (max != 0) {
                    n5 = max;
                }
                else {
                    n5 = Math.max(n5, n4 - n);
                }
            }
            else if (n5 == 0) {
                n5 = Math.min(n2 - paddingLeft, max);
            }
            if (n6 == 0) {
                n6 = Math.min(n3 - paddingTop, max2);
            }
            if (n5 != 0 || n6 != 0) {
                if (b) {
                    recyclerView.scrollBy(n5, n6);
                }
                else {
                    recyclerView.smoothScrollBy(n5, n6);
                }
                return true;
            }
            return false;
        }
        
        public void requestLayout() {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.requestLayout();
            }
        }
        
        public void requestSimpleAnimationsInNextLayout() {
            this.mRequestedSimpleAnimations = true;
        }
        
        public int scrollHorizontallyBy(final int n, final Recycler recycler, final State state) {
            return 0;
        }
        
        public void scrollToPosition(final int n) {
        }
        
        public int scrollVerticallyBy(final int n, final Recycler recycler, final State state) {
            return 0;
        }
        
        public void setMeasuredDimension(final int n, final int n2) {
            RecyclerView.access$5400(this.mRecyclerView, n, n2);
        }
        
        void setRecyclerView(final RecyclerView mRecyclerView) {
            if (mRecyclerView == null) {
                this.mRecyclerView = null;
                this.mChildHelper = null;
                return;
            }
            this.mRecyclerView = mRecyclerView;
            this.mChildHelper = mRecyclerView.mChildHelper;
        }
        
        public void smoothScrollToPosition(final RecyclerView recyclerView, final State state, final int n) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }
        
        public void startSmoothScroll(final SmoothScroller mSmoothScroller) {
            if (this.mSmoothScroller != null && mSmoothScroller != this.mSmoothScroller && this.mSmoothScroller.isRunning()) {
                this.mSmoothScroller.stop();
            }
            (this.mSmoothScroller = mSmoothScroller).start(this.mRecyclerView, this);
        }
        
        public void stopIgnoringView(final View view) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.stopIgnoring();
            childViewHolderInt.resetInternal();
            childViewHolderInt.addFlags(4);
        }
        
        void stopSmoothScroller() {
            if (this.mSmoothScroller != null) {
                this.mSmoothScroller.stop();
            }
        }
        
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
        
        public static class Properties
        {
            public int orientation;
            public boolean reverseLayout;
            public int spanCount;
            public boolean stackFromEnd;
        }
    }
    
    public static class LayoutParams extends ViewGroup$MarginLayoutParams
    {
        final Rect mDecorInsets;
        boolean mInsetsDirty;
        boolean mPendingInvalidate;
        ViewHolder mViewHolder;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }
        
        public LayoutParams(final LayoutParams layoutParams) {
            super((ViewGroup$LayoutParams)layoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }
        
        public int getViewAdapterPosition() {
            return this.mViewHolder.getAdapterPosition();
        }
        
        public int getViewLayoutPosition() {
            return this.mViewHolder.getLayoutPosition();
        }
        
        public int getViewPosition() {
            return this.mViewHolder.getPosition();
        }
        
        public boolean isItemChanged() {
            return this.mViewHolder.isUpdated();
        }
        
        public boolean isItemRemoved() {
            return this.mViewHolder.isRemoved();
        }
        
        public boolean isViewInvalid() {
            return this.mViewHolder.isInvalid();
        }
        
        public boolean viewNeedsUpdate() {
            return this.mViewHolder.needsUpdate();
        }
    }
    
    public interface OnChildAttachStateChangeListener
    {
        void onChildViewAttachedToWindow(final View p0);
        
        void onChildViewDetachedFromWindow(final View p0);
    }
    
    public interface OnItemTouchListener
    {
        boolean onInterceptTouchEvent(final RecyclerView p0, final MotionEvent p1);
        
        void onRequestDisallowInterceptTouchEvent(final boolean p0);
        
        void onTouchEvent(final RecyclerView p0, final MotionEvent p1);
    }
    
    public abstract static class OnScrollListener
    {
        public void onScrollStateChanged(final RecyclerView recyclerView, final int n) {
        }
        
        public void onScrolled(final RecyclerView recyclerView, final int n, final int n2) {
        }
    }
    
    public static class RecycledViewPool
    {
        private static final int DEFAULT_MAX_SCRAP = 5;
        private int mAttachCount;
        private SparseIntArray mMaxScrap;
        private SparseArray<ArrayList<ViewHolder>> mScrap;
        
        public RecycledViewPool() {
            this.mScrap = (SparseArray<ArrayList<ViewHolder>>)new SparseArray();
            this.mMaxScrap = new SparseIntArray();
            this.mAttachCount = 0;
        }
        
        private ArrayList<ViewHolder> getScrapHeapForType(final int n) {
            ArrayList<ViewHolder> list;
            if ((list = (ArrayList<ViewHolder>)this.mScrap.get(n)) == null) {
                final ArrayList<ViewHolder> list2 = new ArrayList<ViewHolder>();
                this.mScrap.put(n, (Object)list2);
                list = list2;
                if (this.mMaxScrap.indexOfKey(n) < 0) {
                    this.mMaxScrap.put(n, 5);
                    list = list2;
                }
            }
            return list;
        }
        
        void attach(final Adapter adapter) {
            ++this.mAttachCount;
        }
        
        public void clear() {
            this.mScrap.clear();
        }
        
        void detach() {
            --this.mAttachCount;
        }
        
        public ViewHolder getRecycledView(int n) {
            final ArrayList list = (ArrayList)this.mScrap.get(n);
            if (list != null && !list.isEmpty()) {
                n = list.size() - 1;
                final ViewHolder viewHolder = list.get(n);
                list.remove(n);
                return viewHolder;
            }
            return null;
        }
        
        void onAdapterChanged(final Adapter adapter, final Adapter adapter2, final boolean b) {
            if (adapter != null) {
                this.detach();
            }
            if (!b && this.mAttachCount == 0) {
                this.clear();
            }
            if (adapter2 != null) {
                this.attach(adapter2);
            }
        }
        
        public void putRecycledView(final ViewHolder viewHolder) {
            final int itemViewType = viewHolder.getItemViewType();
            final ArrayList<ViewHolder> scrapHeapForType = this.getScrapHeapForType(itemViewType);
            if (this.mMaxScrap.get(itemViewType) <= scrapHeapForType.size()) {
                return;
            }
            viewHolder.resetInternal();
            scrapHeapForType.add(viewHolder);
        }
        
        public void setMaxRecycledViews(final int n, final int n2) {
            this.mMaxScrap.put(n, n2);
            final ArrayList list = (ArrayList)this.mScrap.get(n);
            if (list != null) {
                while (list.size() > n2) {
                    list.remove(list.size() - 1);
                }
            }
        }
        
        int size() {
            int n = 0;
            int n2;
            for (int i = 0; i < this.mScrap.size(); ++i, n = n2) {
                final ArrayList list = (ArrayList)this.mScrap.valueAt(i);
                n2 = n;
                if (list != null) {
                    n2 = n + list.size();
                }
            }
            return n;
        }
    }
    
    public final class Recycler
    {
        private static final int DEFAULT_CACHE_SIZE = 2;
        final ArrayList<ViewHolder> mAttachedScrap;
        final ArrayList<ViewHolder> mCachedViews;
        private ArrayList<ViewHolder> mChangedScrap;
        private RecycledViewPool mRecyclerPool;
        private final List<ViewHolder> mUnmodifiableAttachedScrap;
        private ViewCacheExtension mViewCacheExtension;
        private int mViewCacheMax;
        
        public Recycler() {
            this.mAttachedScrap = new ArrayList<ViewHolder>();
            this.mChangedScrap = null;
            this.mCachedViews = new ArrayList<ViewHolder>();
            this.mUnmodifiableAttachedScrap = Collections.unmodifiableList((List<? extends ViewHolder>)this.mAttachedScrap);
            this.mViewCacheMax = 2;
        }
        
        private void attachAccessibilityDelegate(final View view) {
            if (RecyclerView.this.isAccessibilityEnabled()) {
                if (ViewCompat.getImportantForAccessibility(view) == 0) {
                    ViewCompat.setImportantForAccessibility(view, 1);
                }
                if (!ViewCompat.hasAccessibilityDelegate(view)) {
                    ViewCompat.setAccessibilityDelegate(view, RecyclerView.this.mAccessibilityDelegate.getItemDelegate());
                }
            }
        }
        
        private void invalidateDisplayListInt(final ViewHolder viewHolder) {
            if (viewHolder.itemView instanceof ViewGroup) {
                this.invalidateDisplayListInt((ViewGroup)viewHolder.itemView, false);
            }
        }
        
        private void invalidateDisplayListInt(final ViewGroup viewGroup, final boolean b) {
            for (int i = viewGroup.getChildCount() - 1; i >= 0; --i) {
                final View child = viewGroup.getChildAt(i);
                if (child instanceof ViewGroup) {
                    this.invalidateDisplayListInt((ViewGroup)child, true);
                }
            }
            if (!b) {
                return;
            }
            if (viewGroup.getVisibility() == 4) {
                viewGroup.setVisibility(0);
                viewGroup.setVisibility(4);
                return;
            }
            final int visibility = viewGroup.getVisibility();
            viewGroup.setVisibility(4);
            viewGroup.setVisibility(visibility);
        }
        
        void addViewHolderToRecycledViewPool(final ViewHolder viewHolder) {
            ViewCompat.setAccessibilityDelegate(viewHolder.itemView, null);
            this.dispatchViewRecycled(viewHolder);
            viewHolder.mOwnerRecyclerView = null;
            this.getRecycledViewPool().putRecycledView(viewHolder);
        }
        
        public void bindViewToPosition(final View view, final int mPreLayoutPosition) {
            boolean mPendingInvalidate = true;
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt == null) {
                throw new IllegalArgumentException("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter");
            }
            final int positionOffset = RecyclerView.this.mAdapterHelper.findPositionOffset(mPreLayoutPosition);
            if (positionOffset < 0 || positionOffset >= RecyclerView.this.mAdapter.getItemCount()) {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + mPreLayoutPosition + "(offset:" + positionOffset + ")." + "state:" + RecyclerView.this.mState.getItemCount());
            }
            childViewHolderInt.mOwnerRecyclerView = RecyclerView.this;
            RecyclerView.this.mAdapter.bindViewHolder(childViewHolderInt, positionOffset);
            this.attachAccessibilityDelegate(view);
            if (RecyclerView.this.mState.isPreLayout()) {
                childViewHolderInt.mPreLayoutPosition = mPreLayoutPosition;
            }
            final ViewGroup$LayoutParams layoutParams = childViewHolderInt.itemView.getLayoutParams();
            LayoutParams layoutParams2;
            if (layoutParams == null) {
                layoutParams2 = (LayoutParams)RecyclerView.this.generateDefaultLayoutParams();
                childViewHolderInt.itemView.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            }
            else if (!RecyclerView.this.checkLayoutParams(layoutParams)) {
                layoutParams2 = (LayoutParams)RecyclerView.this.generateLayoutParams(layoutParams);
                childViewHolderInt.itemView.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            }
            else {
                layoutParams2 = (LayoutParams)layoutParams;
            }
            layoutParams2.mInsetsDirty = true;
            layoutParams2.mViewHolder = childViewHolderInt;
            if (childViewHolderInt.itemView.getParent() != null) {
                mPendingInvalidate = false;
            }
            layoutParams2.mPendingInvalidate = mPendingInvalidate;
        }
        
        public void clear() {
            this.mAttachedScrap.clear();
            this.recycleAndClearCachedViews();
        }
        
        void clearOldPositions() {
            for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
                this.mCachedViews.get(i).clearOldPosition();
            }
            for (int size2 = this.mAttachedScrap.size(), j = 0; j < size2; ++j) {
                this.mAttachedScrap.get(j).clearOldPosition();
            }
            if (this.mChangedScrap != null) {
                for (int size3 = this.mChangedScrap.size(), k = 0; k < size3; ++k) {
                    this.mChangedScrap.get(k).clearOldPosition();
                }
            }
        }
        
        void clearScrap() {
            this.mAttachedScrap.clear();
            if (this.mChangedScrap != null) {
                this.mChangedScrap.clear();
            }
        }
        
        public int convertPreLayoutPositionToPostLayout(final int n) {
            if (n < 0 || n >= RecyclerView.this.mState.getItemCount()) {
                throw new IndexOutOfBoundsException("invalid position " + n + ". State " + "item count is " + RecyclerView.this.mState.getItemCount());
            }
            if (!RecyclerView.this.mState.isPreLayout()) {
                return n;
            }
            return RecyclerView.this.mAdapterHelper.findPositionOffset(n);
        }
        
        void dispatchViewRecycled(final ViewHolder viewHolder) {
            if (RecyclerView.this.mRecyclerListener != null) {
                RecyclerView.this.mRecyclerListener.onViewRecycled(viewHolder);
            }
            if (RecyclerView.this.mAdapter != null) {
                RecyclerView.this.mAdapter.onViewRecycled(viewHolder);
            }
            if (RecyclerView.this.mState != null) {
                RecyclerView.this.mViewInfoStore.removeViewHolder(viewHolder);
            }
        }
        
        ViewHolder getChangedScrapViewForPosition(int i) {
            if (this.mChangedScrap != null) {
                final int size = this.mChangedScrap.size();
                if (size != 0) {
                    for (int j = 0; j < size; ++j) {
                        final ViewHolder viewHolder = this.mChangedScrap.get(j);
                        if (!viewHolder.wasReturnedFromScrap() && viewHolder.getLayoutPosition() == i) {
                            viewHolder.addFlags(32);
                            return viewHolder;
                        }
                    }
                    if (RecyclerView.this.mAdapter.hasStableIds()) {
                        i = RecyclerView.this.mAdapterHelper.findPositionOffset(i);
                        if (i > 0 && i < RecyclerView.this.mAdapter.getItemCount()) {
                            final long itemId = RecyclerView.this.mAdapter.getItemId(i);
                            ViewHolder viewHolder2;
                            for (i = 0; i < size; ++i) {
                                viewHolder2 = this.mChangedScrap.get(i);
                                if (!viewHolder2.wasReturnedFromScrap() && viewHolder2.getItemId() == itemId) {
                                    viewHolder2.addFlags(32);
                                    return viewHolder2;
                                }
                            }
                        }
                    }
                    return null;
                }
            }
            return null;
        }
        
        RecycledViewPool getRecycledViewPool() {
            if (this.mRecyclerPool == null) {
                this.mRecyclerPool = new RecycledViewPool();
            }
            return this.mRecyclerPool;
        }
        
        int getScrapCount() {
            return this.mAttachedScrap.size();
        }
        
        public List<ViewHolder> getScrapList() {
            return this.mUnmodifiableAttachedScrap;
        }
        
        View getScrapViewAt(final int n) {
            return this.mAttachedScrap.get(n).itemView;
        }
        
        ViewHolder getScrapViewForId(final long n, final int n2, final boolean b) {
            for (int i = this.mAttachedScrap.size() - 1; i >= 0; --i) {
                final ViewHolder viewHolder = this.mAttachedScrap.get(i);
                if (viewHolder.getItemId() == n && !viewHolder.wasReturnedFromScrap()) {
                    if (n2 == viewHolder.getItemViewType()) {
                        viewHolder.addFlags(32);
                        ViewHolder viewHolder2 = viewHolder;
                        if (viewHolder.isRemoved()) {
                            viewHolder2 = viewHolder;
                            if (!RecyclerView.this.mState.isPreLayout()) {
                                viewHolder.setFlags(2, 14);
                                viewHolder2 = viewHolder;
                            }
                        }
                        return viewHolder2;
                    }
                    if (!b) {
                        this.mAttachedScrap.remove(i);
                        RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
                        this.quickRecycleScrapView(viewHolder.itemView);
                    }
                }
            }
            for (int j = this.mCachedViews.size() - 1; j >= 0; --j) {
                final ViewHolder viewHolder3 = this.mCachedViews.get(j);
                if (viewHolder3.getItemId() == n) {
                    if (n2 == viewHolder3.getItemViewType()) {
                        final ViewHolder viewHolder2 = viewHolder3;
                        if (!b) {
                            this.mCachedViews.remove(j);
                            return viewHolder3;
                        }
                        return viewHolder2;
                    }
                    else if (!b) {
                        this.recycleCachedViewAt(j);
                    }
                }
            }
            return null;
        }
        
        ViewHolder getScrapViewForPosition(int indexOfChild, int i, final boolean b) {
            final int size = this.mAttachedScrap.size();
            int j = 0;
            while (j < size) {
                final ViewHolder viewHolder = this.mAttachedScrap.get(j);
                if (!viewHolder.wasReturnedFromScrap() && viewHolder.getLayoutPosition() == indexOfChild && !viewHolder.isInvalid() && (RecyclerView.this.mState.mInPreLayout || !viewHolder.isRemoved())) {
                    if (i != -1 && viewHolder.getItemViewType() != i) {
                        Log.e("RecyclerView", "Scrap view for position " + indexOfChild + " isn't dirty but has" + " wrong view type! (found " + viewHolder.getItemViewType() + " but expected " + i + ")");
                        break;
                    }
                    viewHolder.addFlags(32);
                    return viewHolder;
                }
                else {
                    ++j;
                }
            }
            if (!b) {
                final View hiddenNonRemovedView = RecyclerView.this.mChildHelper.findHiddenNonRemovedView(indexOfChild, i);
                if (hiddenNonRemovedView != null) {
                    final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(hiddenNonRemovedView);
                    RecyclerView.this.mChildHelper.unhide(hiddenNonRemovedView);
                    indexOfChild = RecyclerView.this.mChildHelper.indexOfChild(hiddenNonRemovedView);
                    if (indexOfChild == -1) {
                        throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + childViewHolderInt);
                    }
                    RecyclerView.this.mChildHelper.detachViewFromParent(indexOfChild);
                    this.scrapView(hiddenNonRemovedView);
                    childViewHolderInt.addFlags(8224);
                    return childViewHolderInt;
                }
            }
            final int size2 = this.mCachedViews.size();
            i = 0;
            while (i < size2) {
                final ViewHolder viewHolder2 = this.mCachedViews.get(i);
                if (!viewHolder2.isInvalid() && viewHolder2.getLayoutPosition() == indexOfChild) {
                    final ViewHolder viewHolder = viewHolder2;
                    if (!b) {
                        this.mCachedViews.remove(i);
                        return viewHolder2;
                    }
                    return viewHolder;
                }
                else {
                    ++i;
                }
            }
            return null;
        }
        
        public View getViewForPosition(final int n) {
            return this.getViewForPosition(n, false);
        }
        
        View getViewForPosition(final int n, final boolean b) {
            if (n < 0 || n >= RecyclerView.this.mState.getItemCount()) {
                throw new IndexOutOfBoundsException("Invalid item position " + n + "(" + n + "). Item count:" + RecyclerView.this.mState.getItemCount());
            }
            boolean b2 = false;
            ViewHolder changedScrapViewForPosition = null;
            if (RecyclerView.this.mState.isPreLayout()) {
                changedScrapViewForPosition = this.getChangedScrapViewForPosition(n);
                if (changedScrapViewForPosition != null) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
            }
            boolean b3 = b2;
            ViewHolder viewHolder;
            if ((viewHolder = changedScrapViewForPosition) == null) {
                final ViewHolder scrapViewForPosition = this.getScrapViewForPosition(n, -1, b);
                b3 = b2;
                if ((viewHolder = scrapViewForPosition) != null) {
                    if (!this.validateViewHolderForOffsetPosition(scrapViewForPosition)) {
                        if (!b) {
                            scrapViewForPosition.addFlags(4);
                            if (scrapViewForPosition.isScrap()) {
                                RecyclerView.this.removeDetachedView(scrapViewForPosition.itemView, false);
                                scrapViewForPosition.unScrap();
                            }
                            else if (scrapViewForPosition.wasReturnedFromScrap()) {
                                scrapViewForPosition.clearReturnedFromScrapFlag();
                            }
                            this.recycleViewHolderInternal(scrapViewForPosition);
                        }
                        viewHolder = null;
                        b3 = b2;
                    }
                    else {
                        b3 = true;
                        viewHolder = scrapViewForPosition;
                    }
                }
            }
            boolean b4 = b3;
            ViewHolder viewHolder2;
            if ((viewHolder2 = viewHolder) == null) {
                final int positionOffset = RecyclerView.this.mAdapterHelper.findPositionOffset(n);
                if (positionOffset < 0 || positionOffset >= RecyclerView.this.mAdapter.getItemCount()) {
                    throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + n + "(offset:" + positionOffset + ")." + "state:" + RecyclerView.this.mState.getItemCount());
                }
                final int itemViewType = RecyclerView.this.mAdapter.getItemViewType(positionOffset);
                boolean b5 = b3;
                ViewHolder viewHolder3 = viewHolder;
                if (RecyclerView.this.mAdapter.hasStableIds()) {
                    final ViewHolder scrapViewForId = this.getScrapViewForId(RecyclerView.this.mAdapter.getItemId(positionOffset), itemViewType, b);
                    b5 = b3;
                    if ((viewHolder3 = scrapViewForId) != null) {
                        scrapViewForId.mPosition = positionOffset;
                        b5 = true;
                        viewHolder3 = scrapViewForId;
                    }
                }
                ViewHolder viewHolder4;
                if ((viewHolder4 = viewHolder3) == null) {
                    viewHolder4 = viewHolder3;
                    if (this.mViewCacheExtension != null) {
                        final View viewForPositionAndType = this.mViewCacheExtension.getViewForPositionAndType(this, n, itemViewType);
                        viewHolder4 = viewHolder3;
                        if (viewForPositionAndType != null) {
                            final ViewHolder childViewHolder = RecyclerView.this.getChildViewHolder(viewForPositionAndType);
                            if (childViewHolder == null) {
                                throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder");
                            }
                            viewHolder4 = childViewHolder;
                            if (childViewHolder.shouldIgnore()) {
                                throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                            }
                        }
                    }
                }
                ViewHolder viewHolder5;
                if ((viewHolder5 = viewHolder4) == null) {
                    final ViewHolder recycledView = this.getRecycledViewPool().getRecycledView(itemViewType);
                    if ((viewHolder5 = recycledView) != null) {
                        recycledView.resetInternal();
                        viewHolder5 = recycledView;
                        if (RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST) {
                            this.invalidateDisplayListInt(recycledView);
                            viewHolder5 = recycledView;
                        }
                    }
                }
                b4 = b5;
                if ((viewHolder2 = viewHolder5) == null) {
                    viewHolder2 = RecyclerView.this.mAdapter.createViewHolder(RecyclerView.this, itemViewType);
                    b4 = b5;
                }
            }
            if (b4 && !RecyclerView.this.mState.isPreLayout() && viewHolder2.hasAnyOfTheFlags(8192)) {
                viewHolder2.setFlags(0, 8192);
                if (RecyclerView.this.mState.mRunSimpleAnimations) {
                    RecyclerView.this.recordAnimationInfoIfBouncedHiddenView(viewHolder2, RecyclerView.this.mItemAnimator.recordPreLayoutInformation(RecyclerView.this.mState, viewHolder2, ItemAnimator.buildAdapterChangeFlagsForAnimations(viewHolder2) | 0x1000, viewHolder2.getUnmodifiedPayloads()));
                }
            }
            boolean b6 = false;
            if (RecyclerView.this.mState.isPreLayout() && viewHolder2.isBound()) {
                viewHolder2.mPreLayoutPosition = n;
            }
            else if (!viewHolder2.isBound() || viewHolder2.needsUpdate() || viewHolder2.isInvalid()) {
                final int positionOffset2 = RecyclerView.this.mAdapterHelper.findPositionOffset(n);
                viewHolder2.mOwnerRecyclerView = RecyclerView.this;
                RecyclerView.this.mAdapter.bindViewHolder(viewHolder2, positionOffset2);
                this.attachAccessibilityDelegate(viewHolder2.itemView);
                b6 = true;
                if (RecyclerView.this.mState.isPreLayout()) {
                    viewHolder2.mPreLayoutPosition = n;
                    b6 = b6;
                }
            }
            final ViewGroup$LayoutParams layoutParams = viewHolder2.itemView.getLayoutParams();
            LayoutParams layoutParams2;
            if (layoutParams == null) {
                layoutParams2 = (LayoutParams)RecyclerView.this.generateDefaultLayoutParams();
                viewHolder2.itemView.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            }
            else if (!RecyclerView.this.checkLayoutParams(layoutParams)) {
                layoutParams2 = (LayoutParams)RecyclerView.this.generateLayoutParams(layoutParams);
                viewHolder2.itemView.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            }
            else {
                layoutParams2 = (LayoutParams)layoutParams;
            }
            layoutParams2.mViewHolder = viewHolder2;
            layoutParams2.mPendingInvalidate = (b4 && b6);
            return viewHolder2.itemView;
        }
        
        void markItemDecorInsetsDirty() {
            for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
                final LayoutParams layoutParams = (LayoutParams)this.mCachedViews.get(i).itemView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.mInsetsDirty = true;
                }
            }
        }
        
        void markKnownViewsInvalid() {
            if (RecyclerView.this.mAdapter != null && RecyclerView.this.mAdapter.hasStableIds()) {
                for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
                    final ViewHolder viewHolder = this.mCachedViews.get(i);
                    if (viewHolder != null) {
                        viewHolder.addFlags(6);
                        viewHolder.addChangePayload(null);
                    }
                }
            }
            else {
                this.recycleAndClearCachedViews();
            }
        }
        
        void offsetPositionRecordsForInsert(final int n, final int n2) {
            for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
                final ViewHolder viewHolder = this.mCachedViews.get(i);
                if (viewHolder != null && viewHolder.getLayoutPosition() >= n) {
                    viewHolder.offsetPosition(n2, true);
                }
            }
        }
        
        void offsetPositionRecordsForMove(final int n, final int n2) {
            int n3;
            int n4;
            int n5;
            if (n < n2) {
                n3 = n;
                n4 = n2;
                n5 = -1;
            }
            else {
                n3 = n2;
                n4 = n;
                n5 = 1;
            }
            for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
                final ViewHolder viewHolder = this.mCachedViews.get(i);
                if (viewHolder != null && viewHolder.mPosition >= n3 && viewHolder.mPosition <= n4) {
                    if (viewHolder.mPosition == n) {
                        viewHolder.offsetPosition(n2 - n, false);
                    }
                    else {
                        viewHolder.offsetPosition(n5, false);
                    }
                }
            }
        }
        
        void offsetPositionRecordsForRemove(final int n, final int n2, final boolean b) {
            for (int i = this.mCachedViews.size() - 1; i >= 0; --i) {
                final ViewHolder viewHolder = this.mCachedViews.get(i);
                if (viewHolder != null) {
                    if (viewHolder.getLayoutPosition() >= n + n2) {
                        viewHolder.offsetPosition(-n2, b);
                    }
                    else if (viewHolder.getLayoutPosition() >= n) {
                        viewHolder.addFlags(8);
                        this.recycleCachedViewAt(i);
                    }
                }
            }
        }
        
        void onAdapterChanged(final Adapter adapter, final Adapter adapter2, final boolean b) {
            this.clear();
            this.getRecycledViewPool().onAdapterChanged(adapter, adapter2, b);
        }
        
        void quickRecycleScrapView(final View view) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.mScrapContainer = null;
            childViewHolderInt.mInChangeScrap = false;
            childViewHolderInt.clearReturnedFromScrapFlag();
            this.recycleViewHolderInternal(childViewHolderInt);
        }
        
        void recycleAndClearCachedViews() {
            for (int i = this.mCachedViews.size() - 1; i >= 0; --i) {
                this.recycleCachedViewAt(i);
            }
            this.mCachedViews.clear();
        }
        
        void recycleCachedViewAt(final int n) {
            this.addViewHolderToRecycledViewPool(this.mCachedViews.get(n));
            this.mCachedViews.remove(n);
        }
        
        public void recycleView(final View view) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            }
            else if (childViewHolderInt.wasReturnedFromScrap()) {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            this.recycleViewHolderInternal(childViewHolderInt);
        }
        
        void recycleViewHolderInternal(final ViewHolder viewHolder) {
            boolean b = true;
            if (viewHolder.isScrap() || viewHolder.itemView.getParent() != null) {
                final StringBuilder append = new StringBuilder().append("Scrapped or attached views may not be recycled. isScrap:").append(viewHolder.isScrap()).append(" isAttached:");
                if (viewHolder.itemView.getParent() == null) {
                    b = false;
                }
                throw new IllegalArgumentException(append.append(b).toString());
            }
            if (viewHolder.isTmpDetached()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + viewHolder);
            }
            if (viewHolder.shouldIgnore()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
            }
            final boolean access$4700 = viewHolder.doesTransientStatePreventRecycling();
            int n;
            if (RecyclerView.this.mAdapter != null && access$4700 && RecyclerView.this.mAdapter.onFailedToRecycleView(viewHolder)) {
                n = 1;
            }
            else {
                n = 0;
            }
            int n2 = 0;
            final boolean b2 = false;
            final boolean b3 = false;
            boolean b4 = false;
            Label_0274: {
                if (n == 0) {
                    b4 = b3;
                    if (!viewHolder.isRecyclable()) {
                        break Label_0274;
                    }
                }
                boolean b5 = b2;
                if (!viewHolder.hasAnyOfTheFlags(14)) {
                    final int size = this.mCachedViews.size();
                    if (size == this.mViewCacheMax && size > 0) {
                        this.recycleCachedViewAt(0);
                    }
                    b5 = b2;
                    if (size < this.mViewCacheMax) {
                        this.mCachedViews.add(viewHolder);
                        b5 = true;
                    }
                }
                n2 = (b5 ? 1 : 0);
                b4 = b3;
                if (!b5) {
                    this.addViewHolderToRecycledViewPool(viewHolder);
                    b4 = true;
                    n2 = (b5 ? 1 : 0);
                }
            }
            RecyclerView.this.mViewInfoStore.removeViewHolder(viewHolder);
            if (n2 == 0 && !b4 && access$4700) {
                viewHolder.mOwnerRecyclerView = null;
            }
        }
        
        void recycleViewInternal(final View view) {
            this.recycleViewHolderInternal(RecyclerView.getChildViewHolderInt(view));
        }
        
        void scrapView(final View view) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isUpdated() && !childViewHolderInt.isInvalid() && !RecyclerView.this.canReuseUpdatedViewHolder(childViewHolderInt)) {
                if (this.mChangedScrap == null) {
                    this.mChangedScrap = new ArrayList<ViewHolder>();
                }
                childViewHolderInt.setScrapContainer(this, true);
                this.mChangedScrap.add(childViewHolderInt);
                return;
            }
            if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !RecyclerView.this.mAdapter.hasStableIds()) {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
            }
            childViewHolderInt.setScrapContainer(this, false);
            this.mAttachedScrap.add(childViewHolderInt);
        }
        
        void setAdapterPositionsAsUnknown() {
            for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
                final ViewHolder viewHolder = this.mCachedViews.get(i);
                if (viewHolder != null) {
                    viewHolder.addFlags(512);
                }
            }
        }
        
        void setRecycledViewPool(final RecycledViewPool mRecyclerPool) {
            if (this.mRecyclerPool != null) {
                this.mRecyclerPool.detach();
            }
            if ((this.mRecyclerPool = mRecyclerPool) != null) {
                this.mRecyclerPool.attach(RecyclerView.this.getAdapter());
            }
        }
        
        void setViewCacheExtension(final ViewCacheExtension mViewCacheExtension) {
            this.mViewCacheExtension = mViewCacheExtension;
        }
        
        public void setViewCacheSize(final int mViewCacheMax) {
            this.mViewCacheMax = mViewCacheMax;
            for (int n = this.mCachedViews.size() - 1; n >= 0 && this.mCachedViews.size() > mViewCacheMax; --n) {
                this.recycleCachedViewAt(n);
            }
        }
        
        void unscrapView(final ViewHolder viewHolder) {
            if (viewHolder.mInChangeScrap) {
                this.mChangedScrap.remove(viewHolder);
            }
            else {
                this.mAttachedScrap.remove(viewHolder);
            }
            viewHolder.mScrapContainer = null;
            viewHolder.mInChangeScrap = false;
            viewHolder.clearReturnedFromScrapFlag();
        }
        
        boolean validateViewHolderForOffsetPosition(final ViewHolder viewHolder) {
            if (!viewHolder.isRemoved()) {
                if (viewHolder.mPosition < 0 || viewHolder.mPosition >= RecyclerView.this.mAdapter.getItemCount()) {
                    throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + viewHolder);
                }
                if (!RecyclerView.this.mState.isPreLayout() && RecyclerView.this.mAdapter.getItemViewType(viewHolder.mPosition) != viewHolder.getItemViewType()) {
                    return false;
                }
                if (RecyclerView.this.mAdapter.hasStableIds() && viewHolder.getItemId() != RecyclerView.this.mAdapter.getItemId(viewHolder.mPosition)) {
                    return false;
                }
            }
            return true;
        }
        
        void viewRangeUpdate(final int n, final int n2) {
            for (int i = this.mCachedViews.size() - 1; i >= 0; --i) {
                final ViewHolder viewHolder = this.mCachedViews.get(i);
                if (viewHolder != null) {
                    final int layoutPosition = viewHolder.getLayoutPosition();
                    if (layoutPosition >= n && layoutPosition < n + n2) {
                        viewHolder.addFlags(2);
                        this.recycleCachedViewAt(i);
                    }
                }
            }
        }
    }
    
    public interface RecyclerListener
    {
        void onViewRecycled(final ViewHolder p0);
    }
    
    private class RecyclerViewDataObserver extends AdapterDataObserver
    {
        @Override
        public void onChanged() {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapter.hasStableIds()) {
                RecyclerView.this.mState.mStructureChanged = true;
                RecyclerView.this.setDataSetChangedAfterLayout();
            }
            else {
                RecyclerView.this.mState.mStructureChanged = true;
                RecyclerView.this.setDataSetChangedAfterLayout();
            }
            if (!RecyclerView.this.mAdapterHelper.hasPendingUpdates()) {
                RecyclerView.this.requestLayout();
            }
        }
        
        @Override
        public void onItemRangeChanged(final int n, final int n2, final Object o) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeChanged(n, n2, o)) {
                this.triggerUpdateProcessor();
            }
        }
        
        @Override
        public void onItemRangeInserted(final int n, final int n2) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeInserted(n, n2)) {
                this.triggerUpdateProcessor();
            }
        }
        
        @Override
        public void onItemRangeMoved(final int n, final int n2, final int n3) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeMoved(n, n2, n3)) {
                this.triggerUpdateProcessor();
            }
        }
        
        @Override
        public void onItemRangeRemoved(final int n, final int n2) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeRemoved(n, n2)) {
                this.triggerUpdateProcessor();
            }
        }
        
        void triggerUpdateProcessor() {
            if (RecyclerView.this.mPostUpdatesOnAnimation && RecyclerView.this.mHasFixedSize && RecyclerView.this.mIsAttached) {
                ViewCompat.postOnAnimation((View)RecyclerView.this, RecyclerView.this.mUpdateChildViewsRunnable);
                return;
            }
            RecyclerView.this.mAdapterUpdateDuringMeasure = true;
            RecyclerView.this.requestLayout();
        }
    }
    
    public static class SavedState extends View$BaseSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        Parcelable mLayoutState;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
                public SavedState createFromParcel(final Parcel parcel) {
                    return new SavedState(parcel);
                }
                
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            };
        }
        
        SavedState(final Parcel parcel) {
            super(parcel);
            this.mLayoutState = parcel.readParcelable(LayoutManager.class.getClassLoader());
        }
        
        SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        private void copyFrom(final SavedState savedState) {
            this.mLayoutState = savedState.mLayoutState;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            parcel.writeParcelable(this.mLayoutState, 0);
        }
    }
    
    public static class SimpleOnItemTouchListener implements OnItemTouchListener
    {
        @Override
        public boolean onInterceptTouchEvent(final RecyclerView recyclerView, final MotionEvent motionEvent) {
            return false;
        }
        
        @Override
        public void onRequestDisallowInterceptTouchEvent(final boolean b) {
        }
        
        @Override
        public void onTouchEvent(final RecyclerView recyclerView, final MotionEvent motionEvent) {
        }
    }
    
    public abstract static class SmoothScroller
    {
        private LayoutManager mLayoutManager;
        private boolean mPendingInitialRun;
        private RecyclerView mRecyclerView;
        private final Action mRecyclingAction;
        private boolean mRunning;
        private int mTargetPosition;
        private View mTargetView;
        
        public SmoothScroller() {
            this.mTargetPosition = -1;
            this.mRecyclingAction = new Action(0, 0);
        }
        
        private void onAnimation(final int n, final int n2) {
            final RecyclerView mRecyclerView = this.mRecyclerView;
            if (!this.mRunning || this.mTargetPosition == -1 || mRecyclerView == null) {
                this.stop();
            }
            this.mPendingInitialRun = false;
            if (this.mTargetView != null) {
                if (this.getChildPosition(this.mTargetView) == this.mTargetPosition) {
                    this.onTargetFound(this.mTargetView, mRecyclerView.mState, this.mRecyclingAction);
                    this.mRecyclingAction.runIfNecessary(mRecyclerView);
                    this.stop();
                }
                else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.mTargetView = null;
                }
            }
            if (this.mRunning) {
                this.onSeekTargetStep(n, n2, mRecyclerView.mState, this.mRecyclingAction);
                final boolean hasJumpTarget = this.mRecyclingAction.hasJumpTarget();
                this.mRecyclingAction.runIfNecessary(mRecyclerView);
                if (hasJumpTarget) {
                    if (!this.mRunning) {
                        this.stop();
                        return;
                    }
                    this.mPendingInitialRun = true;
                    mRecyclerView.mViewFlinger.postOnAnimation();
                }
            }
        }
        
        public View findViewByPosition(final int n) {
            return this.mRecyclerView.mLayout.findViewByPosition(n);
        }
        
        public int getChildCount() {
            return this.mRecyclerView.mLayout.getChildCount();
        }
        
        public int getChildPosition(final View view) {
            return this.mRecyclerView.getChildLayoutPosition(view);
        }
        
        @Nullable
        public LayoutManager getLayoutManager() {
            return this.mLayoutManager;
        }
        
        public int getTargetPosition() {
            return this.mTargetPosition;
        }
        
        @Deprecated
        public void instantScrollToPosition(final int n) {
            this.mRecyclerView.scrollToPosition(n);
        }
        
        public boolean isPendingInitialRun() {
            return this.mPendingInitialRun;
        }
        
        public boolean isRunning() {
            return this.mRunning;
        }
        
        protected void normalize(final PointF pointF) {
            final double sqrt = Math.sqrt(pointF.x * pointF.x + pointF.y * pointF.y);
            pointF.x /= (float)sqrt;
            pointF.y /= (float)sqrt;
        }
        
        protected void onChildAttachedToWindow(final View mTargetView) {
            if (this.getChildPosition(mTargetView) == this.getTargetPosition()) {
                this.mTargetView = mTargetView;
            }
        }
        
        protected abstract void onSeekTargetStep(final int p0, final int p1, final State p2, final Action p3);
        
        protected abstract void onStart();
        
        protected abstract void onStop();
        
        protected abstract void onTargetFound(final View p0, final State p1, final Action p2);
        
        public void setTargetPosition(final int mTargetPosition) {
            this.mTargetPosition = mTargetPosition;
        }
        
        void start(final RecyclerView mRecyclerView, final LayoutManager mLayoutManager) {
            this.mRecyclerView = mRecyclerView;
            this.mLayoutManager = mLayoutManager;
            if (this.mTargetPosition == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            this.mRecyclerView.mState.mTargetPosition = this.mTargetPosition;
            this.mRunning = true;
            this.mPendingInitialRun = true;
            this.mTargetView = this.findViewByPosition(this.getTargetPosition());
            this.onStart();
            this.mRecyclerView.mViewFlinger.postOnAnimation();
        }
        
        protected final void stop() {
            if (!this.mRunning) {
                return;
            }
            this.onStop();
            this.mRecyclerView.mState.mTargetPosition = -1;
            this.mTargetView = null;
            this.mTargetPosition = -1;
            this.mPendingInitialRun = false;
            this.mRunning = false;
            this.mLayoutManager.onSmoothScrollerStopped(this);
            this.mLayoutManager = null;
            this.mRecyclerView = null;
        }
        
        public static class Action
        {
            public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
            private boolean changed;
            private int consecutiveUpdates;
            private int mDuration;
            private int mDx;
            private int mDy;
            private Interpolator mInterpolator;
            private int mJumpToPosition;
            
            public Action(final int n, final int n2) {
                this(n, n2, Integer.MIN_VALUE, null);
            }
            
            public Action(final int n, final int n2, final int n3) {
                this(n, n2, n3, null);
            }
            
            public Action(final int mDx, final int mDy, final int mDuration, final Interpolator mInterpolator) {
                this.mJumpToPosition = -1;
                this.changed = false;
                this.consecutiveUpdates = 0;
                this.mDx = mDx;
                this.mDy = mDy;
                this.mDuration = mDuration;
                this.mInterpolator = mInterpolator;
            }
            
            private void runIfNecessary(final RecyclerView recyclerView) {
                if (this.mJumpToPosition >= 0) {
                    final int mJumpToPosition = this.mJumpToPosition;
                    this.mJumpToPosition = -1;
                    recyclerView.jumpToPositionForSmoothScroller(mJumpToPosition);
                    this.changed = false;
                    return;
                }
                if (this.changed) {
                    this.validate();
                    if (this.mInterpolator == null) {
                        if (this.mDuration == Integer.MIN_VALUE) {
                            recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy);
                        }
                        else {
                            recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration);
                        }
                    }
                    else {
                        recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
                    }
                    ++this.consecutiveUpdates;
                    if (this.consecutiveUpdates > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.changed = false;
                    return;
                }
                this.consecutiveUpdates = 0;
            }
            
            private void validate() {
                if (this.mInterpolator != null && this.mDuration < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.mDuration < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }
            
            public int getDuration() {
                return this.mDuration;
            }
            
            public int getDx() {
                return this.mDx;
            }
            
            public int getDy() {
                return this.mDy;
            }
            
            public Interpolator getInterpolator() {
                return this.mInterpolator;
            }
            
            boolean hasJumpTarget() {
                return this.mJumpToPosition >= 0;
            }
            
            public void jumpTo(final int mJumpToPosition) {
                this.mJumpToPosition = mJumpToPosition;
            }
            
            public void setDuration(final int mDuration) {
                this.changed = true;
                this.mDuration = mDuration;
            }
            
            public void setDx(final int mDx) {
                this.changed = true;
                this.mDx = mDx;
            }
            
            public void setDy(final int mDy) {
                this.changed = true;
                this.mDy = mDy;
            }
            
            public void setInterpolator(final Interpolator mInterpolator) {
                this.changed = true;
                this.mInterpolator = mInterpolator;
            }
            
            public void update(final int mDx, final int mDy, final int mDuration, final Interpolator mInterpolator) {
                this.mDx = mDx;
                this.mDy = mDy;
                this.mDuration = mDuration;
                this.mInterpolator = mInterpolator;
                this.changed = true;
            }
        }
    }
    
    public static class State
    {
        private SparseArray<Object> mData;
        private int mDeletedInvisibleItemCountSincePreviousLayout;
        private boolean mInPreLayout;
        int mItemCount;
        private int mPreviousLayoutItemCount;
        private boolean mRunPredictiveAnimations;
        private boolean mRunSimpleAnimations;
        private boolean mStructureChanged;
        private int mTargetPosition;
        private boolean mTrackOldChangeHolders;
        
        public State() {
            this.mTargetPosition = -1;
            this.mItemCount = 0;
            this.mPreviousLayoutItemCount = 0;
            this.mDeletedInvisibleItemCountSincePreviousLayout = 0;
            this.mStructureChanged = false;
            this.mInPreLayout = false;
            this.mRunSimpleAnimations = false;
            this.mRunPredictiveAnimations = false;
            this.mTrackOldChangeHolders = false;
        }
        
        static /* synthetic */ int access$1812(final State state, int mDeletedInvisibleItemCountSincePreviousLayout) {
            mDeletedInvisibleItemCountSincePreviousLayout += state.mDeletedInvisibleItemCountSincePreviousLayout;
            return state.mDeletedInvisibleItemCountSincePreviousLayout = mDeletedInvisibleItemCountSincePreviousLayout;
        }
        
        public boolean didStructureChange() {
            return this.mStructureChanged;
        }
        
        public <T> T get(final int n) {
            if (this.mData == null) {
                return null;
            }
            return (T)this.mData.get(n);
        }
        
        public int getItemCount() {
            if (this.mInPreLayout) {
                return this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout;
            }
            return this.mItemCount;
        }
        
        public int getTargetScrollPosition() {
            return this.mTargetPosition;
        }
        
        public boolean hasTargetScrollPosition() {
            return this.mTargetPosition != -1;
        }
        
        public boolean isPreLayout() {
            return this.mInPreLayout;
        }
        
        public void put(final int n, final Object o) {
            if (this.mData == null) {
                this.mData = (SparseArray<Object>)new SparseArray();
            }
            this.mData.put(n, o);
        }
        
        public void remove(final int n) {
            if (this.mData == null) {
                return;
            }
            this.mData.remove(n);
        }
        
        State reset() {
            this.mTargetPosition = -1;
            if (this.mData != null) {
                this.mData.clear();
            }
            this.mItemCount = 0;
            this.mStructureChanged = false;
            return this;
        }
        
        @Override
        public String toString() {
            return "State{mTargetPosition=" + this.mTargetPosition + ", mData=" + this.mData + ", mItemCount=" + this.mItemCount + ", mPreviousLayoutItemCount=" + this.mPreviousLayoutItemCount + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.mDeletedInvisibleItemCountSincePreviousLayout + ", mStructureChanged=" + this.mStructureChanged + ", mInPreLayout=" + this.mInPreLayout + ", mRunSimpleAnimations=" + this.mRunSimpleAnimations + ", mRunPredictiveAnimations=" + this.mRunPredictiveAnimations + '}';
        }
        
        public boolean willRunPredictiveAnimations() {
            return this.mRunPredictiveAnimations;
        }
        
        public boolean willRunSimpleAnimations() {
            return this.mRunSimpleAnimations;
        }
    }
    
    public abstract static class ViewCacheExtension
    {
        public abstract View getViewForPositionAndType(final Recycler p0, final int p1, final int p2);
    }
    
    private class ViewFlinger implements Runnable
    {
        private boolean mEatRunOnAnimationRequest;
        private Interpolator mInterpolator;
        private int mLastFlingX;
        private int mLastFlingY;
        private boolean mReSchedulePostAnimationCallback;
        private ScrollerCompat mScroller;
        
        public ViewFlinger() {
            this.mInterpolator = RecyclerView.sQuinticInterpolator;
            this.mEatRunOnAnimationRequest = false;
            this.mReSchedulePostAnimationCallback = false;
            this.mScroller = ScrollerCompat.create(RecyclerView.this.getContext(), RecyclerView.sQuinticInterpolator);
        }
        
        private int computeScrollDuration(int n, int n2, int n3, int n4) {
            final int abs = Math.abs(n);
            final int abs2 = Math.abs(n2);
            boolean b;
            if (abs > abs2) {
                b = true;
            }
            else {
                b = false;
            }
            n3 = (int)Math.sqrt(n3 * n3 + n4 * n4);
            n2 = (int)Math.sqrt(n * n + n2 * n2);
            if (b) {
                n = RecyclerView.this.getWidth();
            }
            else {
                n = RecyclerView.this.getHeight();
            }
            n4 = n / 2;
            final float min = Math.min(1.0f, 1.0f * n2 / n);
            final float n5 = n4;
            final float n6 = n4;
            final float distanceInfluenceForSnapDuration = this.distanceInfluenceForSnapDuration(min);
            if (n3 > 0) {
                n = Math.round(1000.0f * Math.abs((n5 + n6 * distanceInfluenceForSnapDuration) / n3)) * 4;
            }
            else {
                if (b) {
                    n2 = abs;
                }
                else {
                    n2 = abs2;
                }
                n = (int)((n2 / n + 1.0f) * 300.0f);
            }
            return Math.min(n, 2000);
        }
        
        private void disableRunOnAnimationRequests() {
            this.mReSchedulePostAnimationCallback = false;
            this.mEatRunOnAnimationRequest = true;
        }
        
        private float distanceInfluenceForSnapDuration(final float n) {
            return (float)Math.sin((float)((n - 0.5f) * 0.4712389167638204));
        }
        
        private void enableRunOnAnimationRequests() {
            this.mEatRunOnAnimationRequest = false;
            if (this.mReSchedulePostAnimationCallback) {
                this.postOnAnimation();
            }
        }
        
        public void fling(final int n, final int n2) {
            RecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            this.mScroller.fling(0, 0, n, n2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            this.postOnAnimation();
        }
        
        void postOnAnimation() {
            if (this.mEatRunOnAnimationRequest) {
                this.mReSchedulePostAnimationCallback = true;
                return;
            }
            RecyclerView.this.removeCallbacks((Runnable)this);
            ViewCompat.postOnAnimation((View)RecyclerView.this, this);
        }
        
        @Override
        public void run() {
            this.disableRunOnAnimationRequests();
            RecyclerView.this.consumePendingUpdateOperations();
            final ScrollerCompat mScroller = this.mScroller;
            final SmoothScroller mSmoothScroller = RecyclerView.this.mLayout.mSmoothScroller;
            if (mScroller.computeScrollOffset()) {
                final int currX = mScroller.getCurrX();
                final int currY = mScroller.getCurrY();
                final int n = currX - this.mLastFlingX;
                final int n2 = currY - this.mLastFlingY;
                int n3 = 0;
                int scrollHorizontallyBy = 0;
                int n4 = 0;
                int scrollVerticallyBy = 0;
                this.mLastFlingX = currX;
                this.mLastFlingY = currY;
                int n5 = 0;
                int n6 = 0;
                int n7 = 0;
                int n8 = 0;
                if (RecyclerView.this.mAdapter != null) {
                    RecyclerView.this.eatRequestLayout();
                    RecyclerView.this.onEnterLayoutOrScroll();
                    TraceCompat.beginSection("RV Scroll");
                    if (n != 0) {
                        scrollHorizontallyBy = RecyclerView.this.mLayout.scrollHorizontallyBy(n, RecyclerView.this.mRecycler, RecyclerView.this.mState);
                        n6 = n - scrollHorizontallyBy;
                    }
                    if (n2 != 0) {
                        scrollVerticallyBy = RecyclerView.this.mLayout.scrollVerticallyBy(n2, RecyclerView.this.mRecycler, RecyclerView.this.mState);
                        n8 = n2 - scrollVerticallyBy;
                    }
                    TraceCompat.endSection();
                    RecyclerView.this.repositionShadowingViews();
                    RecyclerView.this.onExitLayoutOrScroll();
                    RecyclerView.this.resumeRequestLayout(false);
                    n3 = scrollHorizontallyBy;
                    n5 = n6;
                    n7 = n8;
                    n4 = scrollVerticallyBy;
                    if (mSmoothScroller != null) {
                        n3 = scrollHorizontallyBy;
                        n5 = n6;
                        n7 = n8;
                        n4 = scrollVerticallyBy;
                        if (!mSmoothScroller.isPendingInitialRun()) {
                            n3 = scrollHorizontallyBy;
                            n5 = n6;
                            n7 = n8;
                            n4 = scrollVerticallyBy;
                            if (mSmoothScroller.isRunning()) {
                                final int itemCount = RecyclerView.this.mState.getItemCount();
                                if (itemCount == 0) {
                                    mSmoothScroller.stop();
                                    n4 = scrollVerticallyBy;
                                    n7 = n8;
                                    n5 = n6;
                                    n3 = scrollHorizontallyBy;
                                }
                                else if (mSmoothScroller.getTargetPosition() >= itemCount) {
                                    mSmoothScroller.setTargetPosition(itemCount - 1);
                                    mSmoothScroller.onAnimation(n - n6, n2 - n8);
                                    n3 = scrollHorizontallyBy;
                                    n5 = n6;
                                    n7 = n8;
                                    n4 = scrollVerticallyBy;
                                }
                                else {
                                    mSmoothScroller.onAnimation(n - n6, n2 - n8);
                                    n3 = scrollHorizontallyBy;
                                    n5 = n6;
                                    n7 = n8;
                                    n4 = scrollVerticallyBy;
                                }
                            }
                        }
                    }
                }
                if (!RecyclerView.this.mItemDecorations.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                if (ViewCompat.getOverScrollMode((View)RecyclerView.this) != 2) {
                    RecyclerView.this.considerReleasingGlowsOnScroll(n, n2);
                }
                if (n5 != 0 || n7 != 0) {
                    final int n9 = (int)mScroller.getCurrVelocity();
                    int n10 = 0;
                    if (n5 != currX) {
                        if (n5 < 0) {
                            n10 = -n9;
                        }
                        else if (n5 > 0) {
                            n10 = n9;
                        }
                        else {
                            n10 = 0;
                        }
                    }
                    int n11 = 0;
                    if (n7 != currY) {
                        if (n7 < 0) {
                            n11 = -n9;
                        }
                        else if (n7 > 0) {
                            n11 = n9;
                        }
                        else {
                            n11 = 0;
                        }
                    }
                    if (ViewCompat.getOverScrollMode((View)RecyclerView.this) != 2) {
                        RecyclerView.this.absorbGlows(n10, n11);
                    }
                    if ((n10 != 0 || n5 == currX || mScroller.getFinalX() == 0) && (n11 != 0 || n7 == currY || mScroller.getFinalY() == 0)) {
                        mScroller.abortAnimation();
                    }
                }
                if (n3 != 0 || n4 != 0) {
                    RecyclerView.this.dispatchOnScrolled(n3, n4);
                }
                if (!RecyclerView.access$3600(RecyclerView.this)) {
                    RecyclerView.this.invalidate();
                }
                boolean b;
                if (n2 != 0 && RecyclerView.this.mLayout.canScrollVertically() && n4 == n2) {
                    b = true;
                }
                else {
                    b = false;
                }
                boolean b2;
                if (n != 0 && RecyclerView.this.mLayout.canScrollHorizontally() && n3 == n) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                boolean b3;
                if ((n == 0 && n2 == 0) || b2 || b) {
                    b3 = true;
                }
                else {
                    b3 = false;
                }
                if (mScroller.isFinished() || !b3) {
                    RecyclerView.this.setScrollState(0);
                }
                else {
                    this.postOnAnimation();
                }
            }
            if (mSmoothScroller != null) {
                if (mSmoothScroller.isPendingInitialRun()) {
                    mSmoothScroller.onAnimation(0, 0);
                }
                if (!this.mReSchedulePostAnimationCallback) {
                    mSmoothScroller.stop();
                }
            }
            this.enableRunOnAnimationRequests();
        }
        
        public void smoothScrollBy(final int n, final int n2) {
            this.smoothScrollBy(n, n2, 0, 0);
        }
        
        public void smoothScrollBy(final int n, final int n2, final int n3) {
            this.smoothScrollBy(n, n2, n3, RecyclerView.sQuinticInterpolator);
        }
        
        public void smoothScrollBy(final int n, final int n2, final int n3, final int n4) {
            this.smoothScrollBy(n, n2, this.computeScrollDuration(n, n2, n3, n4));
        }
        
        public void smoothScrollBy(final int n, final int n2, final int n3, final Interpolator mInterpolator) {
            if (this.mInterpolator != mInterpolator) {
                this.mInterpolator = mInterpolator;
                this.mScroller = ScrollerCompat.create(RecyclerView.this.getContext(), mInterpolator);
            }
            RecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            this.mScroller.startScroll(0, 0, n, n2, n3);
            this.postOnAnimation();
        }
        
        public void stop() {
            RecyclerView.this.removeCallbacks((Runnable)this);
            this.mScroller.abortAnimation();
        }
    }
    
    public abstract static class ViewHolder
    {
        static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        static final int FLAG_BOUND = 1;
        static final int FLAG_IGNORE = 128;
        static final int FLAG_INVALID = 4;
        static final int FLAG_MOVED = 2048;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_RETURNED_FROM_SCRAP = 32;
        static final int FLAG_TMP_DETACHED = 256;
        static final int FLAG_UPDATE = 2;
        private static final List<Object> FULLUPDATE_PAYLOADS;
        public final View itemView;
        private int mFlags;
        private boolean mInChangeScrap;
        private int mIsRecyclableCount;
        long mItemId;
        int mItemViewType;
        int mOldPosition;
        RecyclerView mOwnerRecyclerView;
        List<Object> mPayloads;
        int mPosition;
        int mPreLayoutPosition;
        private Recycler mScrapContainer;
        ViewHolder mShadowedHolder;
        ViewHolder mShadowingHolder;
        List<Object> mUnmodifiedPayloads;
        private int mWasImportantForAccessibilityBeforeHidden;
        
        static {
            FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
        }
        
        public ViewHolder(final View itemView) {
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mItemViewType = -1;
            this.mPreLayoutPosition = -1;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            this.mPayloads = null;
            this.mUnmodifiedPayloads = null;
            this.mIsRecyclableCount = 0;
            this.mScrapContainer = null;
            this.mInChangeScrap = false;
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = itemView;
        }
        
        private void createPayloadsIfNeeded() {
            if (this.mPayloads == null) {
                this.mPayloads = new ArrayList<Object>();
                this.mUnmodifiedPayloads = Collections.unmodifiableList((List<?>)this.mPayloads);
            }
        }
        
        private boolean doesTransientStatePreventRecycling() {
            return (this.mFlags & 0x10) == 0x0 && ViewCompat.hasTransientState(this.itemView);
        }
        
        private void onEnteredHiddenState() {
            this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
            ViewCompat.setImportantForAccessibility(this.itemView, 4);
        }
        
        private void onLeftHiddenState() {
            ViewCompat.setImportantForAccessibility(this.itemView, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }
        
        private boolean shouldBeKeptAsChild() {
            return (this.mFlags & 0x10) != 0x0;
        }
        
        void addChangePayload(final Object o) {
            if (o == null) {
                this.addFlags(1024);
            }
            else if ((this.mFlags & 0x400) == 0x0) {
                this.createPayloadsIfNeeded();
                this.mPayloads.add(o);
            }
        }
        
        void addFlags(final int n) {
            this.mFlags |= n;
        }
        
        void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }
        
        void clearPayload() {
            if (this.mPayloads != null) {
                this.mPayloads.clear();
            }
            this.mFlags &= 0xFFFFFBFF;
        }
        
        void clearReturnedFromScrapFlag() {
            this.mFlags &= 0xFFFFFFDF;
        }
        
        void clearTmpDetachFlag() {
            this.mFlags &= 0xFFFFFEFF;
        }
        
        void flagRemovedAndOffsetPosition(final int mPosition, final int n, final boolean b) {
            this.addFlags(8);
            this.offsetPosition(n, b);
            this.mPosition = mPosition;
        }
        
        public final int getAdapterPosition() {
            if (this.mOwnerRecyclerView == null) {
                return -1;
            }
            return this.mOwnerRecyclerView.getAdapterPositionFor(this);
        }
        
        public final long getItemId() {
            return this.mItemId;
        }
        
        public final int getItemViewType() {
            return this.mItemViewType;
        }
        
        public final int getLayoutPosition() {
            if (this.mPreLayoutPosition == -1) {
                return this.mPosition;
            }
            return this.mPreLayoutPosition;
        }
        
        public final int getOldPosition() {
            return this.mOldPosition;
        }
        
        @Deprecated
        public final int getPosition() {
            if (this.mPreLayoutPosition == -1) {
                return this.mPosition;
            }
            return this.mPreLayoutPosition;
        }
        
        List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 0x400) != 0x0) {
                return ViewHolder.FULLUPDATE_PAYLOADS;
            }
            if (this.mPayloads == null || this.mPayloads.size() == 0) {
                return ViewHolder.FULLUPDATE_PAYLOADS;
            }
            return this.mUnmodifiedPayloads;
        }
        
        boolean hasAnyOfTheFlags(final int n) {
            return (this.mFlags & n) != 0x0;
        }
        
        boolean isAdapterPositionUnknown() {
            return (this.mFlags & 0x200) != 0x0 || this.isInvalid();
        }
        
        boolean isBound() {
            return (this.mFlags & 0x1) != 0x0;
        }
        
        boolean isInvalid() {
            return (this.mFlags & 0x4) != 0x0;
        }
        
        public final boolean isRecyclable() {
            return (this.mFlags & 0x10) == 0x0 && !ViewCompat.hasTransientState(this.itemView);
        }
        
        boolean isRemoved() {
            return (this.mFlags & 0x8) != 0x0;
        }
        
        boolean isScrap() {
            return this.mScrapContainer != null;
        }
        
        boolean isTmpDetached() {
            return (this.mFlags & 0x100) != 0x0;
        }
        
        boolean isUpdated() {
            return (this.mFlags & 0x2) != 0x0;
        }
        
        boolean needsUpdate() {
            return (this.mFlags & 0x2) != 0x0;
        }
        
        void offsetPosition(final int n, final boolean b) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (b) {
                this.mPreLayoutPosition += n;
            }
            this.mPosition += n;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams)this.itemView.getLayoutParams()).mInsetsDirty = true;
            }
        }
        
        void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            this.clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }
        
        void saveOldPosition() {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }
        
        void setFlags(final int n, final int n2) {
            this.mFlags = ((this.mFlags & ~n2) | (n & n2));
        }
        
        public final void setIsRecyclable(final boolean b) {
            int mIsRecyclableCount;
            if (b) {
                mIsRecyclableCount = this.mIsRecyclableCount - 1;
            }
            else {
                mIsRecyclableCount = this.mIsRecyclableCount + 1;
            }
            this.mIsRecyclableCount = mIsRecyclableCount;
            if (this.mIsRecyclableCount < 0) {
                this.mIsRecyclableCount = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            }
            else {
                if (!b && this.mIsRecyclableCount == 1) {
                    this.mFlags |= 0x10;
                    return;
                }
                if (b && this.mIsRecyclableCount == 0) {
                    this.mFlags &= 0xFFFFFFEF;
                }
            }
        }
        
        void setScrapContainer(final Recycler mScrapContainer, final boolean mInChangeScrap) {
            this.mScrapContainer = mScrapContainer;
            this.mInChangeScrap = mInChangeScrap;
        }
        
        boolean shouldIgnore() {
            return (this.mFlags & 0x80) != 0x0;
        }
        
        void stopIgnoring() {
            this.mFlags &= 0xFFFFFF7F;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ViewHolder{" + Integer.toHexString(this.hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
            if (this.isScrap()) {
                final StringBuilder append = sb.append(" scrap ");
                String s;
                if (this.mInChangeScrap) {
                    s = "[changeScrap]";
                }
                else {
                    s = "[attachedScrap]";
                }
                append.append(s);
            }
            if (this.isInvalid()) {
                sb.append(" invalid");
            }
            if (!this.isBound()) {
                sb.append(" unbound");
            }
            if (this.needsUpdate()) {
                sb.append(" update");
            }
            if (this.isRemoved()) {
                sb.append(" removed");
            }
            if (this.shouldIgnore()) {
                sb.append(" ignored");
            }
            if (this.isTmpDetached()) {
                sb.append(" tmpDetached");
            }
            if (!this.isRecyclable()) {
                sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
            }
            if (this.isAdapterPositionUnknown()) {
                sb.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }
        
        void unScrap() {
            this.mScrapContainer.unscrapView(this);
        }
        
        boolean wasReturnedFromScrap() {
            return (this.mFlags & 0x20) != 0x0;
        }
    }
}
