// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.os.ParcelableCompat;
import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.os.Parcelable$Creator;
import android.view.ViewParent;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.view.View$BaseSavedState;
import android.support.v4.content.ContextCompat;
import android.graphics.drawable.ColorDrawable;
import android.util.SparseArray;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import java.io.Serializable;
import android.view.ViewGroup$MarginLayoutParams;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import java.util.HashMap;
import android.text.TextUtils;
import android.support.v4.view.GravityCompat;
import java.util.Collections;
import android.util.Log;
import android.support.v4.view.ViewCompat;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.design.R;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.view.ViewGroup$OnHierarchyChangeListener;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.WindowInsetsCompat;
import java.util.List;
import java.lang.reflect.Constructor;
import java.util.Map;
import android.view.View;
import java.util.Comparator;
import android.support.v4.view.NestedScrollingParent;
import android.view.ViewGroup;

public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent
{
    static final Class<?>[] CONSTRUCTOR_PARAMS;
    static final CoordinatorLayoutInsetsHelper INSETS_HELPER;
    static final String TAG = "CoordinatorLayout";
    static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
    private static final int TYPE_ON_INTERCEPT = 0;
    private static final int TYPE_ON_TOUCH = 1;
    static final String WIDGET_PACKAGE_NAME;
    static final ThreadLocal<Map<String, Constructor<Behavior>>> sConstructors;
    private View mBehaviorTouchView;
    private final List<View> mDependencySortedChildren;
    private boolean mDrawStatusBarBackground;
    private boolean mIsAttachedToWindow;
    private int[] mKeylines;
    private WindowInsetsCompat mLastInsets;
    final Comparator<View> mLayoutDependencyComparator;
    private boolean mNeedsPreDrawListener;
    private View mNestedScrollingDirectChild;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    private ViewGroup$OnHierarchyChangeListener mOnHierarchyChangeListener;
    private OnPreDrawListener mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List<View> mTempDependenciesList;
    private final int[] mTempIntPair;
    private final List<View> mTempList1;
    private final Rect mTempRect1;
    private final Rect mTempRect2;
    private final Rect mTempRect3;
    
    static {
        final Package package1 = CoordinatorLayout.class.getPackage();
        String name;
        if (package1 != null) {
            name = package1.getName();
        }
        else {
            name = null;
        }
        WIDGET_PACKAGE_NAME = name;
        if (Build$VERSION.SDK_INT >= 21) {
            TOP_SORTED_CHILDREN_COMPARATOR = new ViewElevationComparator();
            INSETS_HELPER = new CoordinatorLayoutInsetsHelperLollipop();
        }
        else {
            TOP_SORTED_CHILDREN_COMPARATOR = null;
            INSETS_HELPER = null;
        }
        CONSTRUCTOR_PARAMS = new Class[] { Context.class, AttributeSet.class };
        sConstructors = new ThreadLocal<Map<String, Constructor<Behavior>>>();
    }
    
    public CoordinatorLayout(final Context context) {
        this(context, null);
    }
    
    public CoordinatorLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public CoordinatorLayout(final Context context, final AttributeSet set, int i) {
        super(context, set, i);
        this.mLayoutDependencyComparator = new Comparator<View>() {
            @Override
            public int compare(final View view, final View view2) {
                if (view == view2) {
                    return 0;
                }
                if (((LayoutParams)view.getLayoutParams()).dependsOn(CoordinatorLayout.this, view, view2)) {
                    return 1;
                }
                if (((LayoutParams)view2.getLayoutParams()).dependsOn(CoordinatorLayout.this, view2, view)) {
                    return -1;
                }
                return 0;
            }
        };
        this.mDependencySortedChildren = new ArrayList<View>();
        this.mTempList1 = new ArrayList<View>();
        this.mTempDependenciesList = new ArrayList<View>();
        this.mTempRect1 = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRect3 = new Rect();
        this.mTempIntPair = new int[2];
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        ThemeUtils.checkAppCompatTheme(context);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.CoordinatorLayout, i, R.style.Widget_Design_CoordinatorLayout);
        i = obtainStyledAttributes.getResourceId(R.styleable.CoordinatorLayout_keylines, 0);
        if (i != 0) {
            final Resources resources = context.getResources();
            this.mKeylines = resources.getIntArray(i);
            final float density = resources.getDisplayMetrics().density;
            int length;
            int[] mKeylines;
            for (length = this.mKeylines.length, i = 0; i < length; ++i) {
                mKeylines = this.mKeylines;
                mKeylines[i] *= (int)density;
            }
        }
        this.mStatusBarBackground = obtainStyledAttributes.getDrawable(R.styleable.CoordinatorLayout_statusBarBackground);
        obtainStyledAttributes.recycle();
        if (CoordinatorLayout.INSETS_HELPER != null) {
            CoordinatorLayout.INSETS_HELPER.setupForWindowInsets((View)this, new ApplyInsetsListener());
        }
        super.setOnHierarchyChangeListener((ViewGroup$OnHierarchyChangeListener)new HierarchyChangeListener());
    }
    
    private void dispatchChildApplyWindowInsets(WindowInsetsCompat windowInsetsCompat) {
        if (!windowInsetsCompat.isConsumed()) {
            WindowInsetsCompat dispatchApplyWindowInsets;
            for (int i = 0; i < this.getChildCount(); ++i, windowInsetsCompat = dispatchApplyWindowInsets) {
                final View child = this.getChildAt(i);
                dispatchApplyWindowInsets = windowInsetsCompat;
                if (ViewCompat.getFitsSystemWindows(child)) {
                    final Behavior behavior = ((LayoutParams)child.getLayoutParams()).getBehavior();
                    WindowInsetsCompat onApplyWindowInsets = windowInsetsCompat;
                    if (behavior != null) {
                        onApplyWindowInsets = behavior.onApplyWindowInsets(this, child, windowInsetsCompat);
                        if (onApplyWindowInsets.isConsumed()) {
                            break;
                        }
                    }
                    dispatchApplyWindowInsets = ViewCompat.dispatchApplyWindowInsets(child, onApplyWindowInsets);
                    if (dispatchApplyWindowInsets.isConsumed()) {
                        break;
                    }
                }
            }
        }
    }
    
    private int getKeyline(final int n) {
        if (this.mKeylines == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + n);
            return 0;
        }
        if (n < 0 || n >= this.mKeylines.length) {
            Log.e("CoordinatorLayout", "Keyline index " + n + " out of range for " + this);
            return 0;
        }
        return this.mKeylines[n];
    }
    
    private void getTopSortedChildren(final List<View> list) {
        list.clear();
        final boolean childrenDrawingOrderEnabled = this.isChildrenDrawingOrderEnabled();
        final int childCount = this.getChildCount();
        for (int i = childCount - 1; i >= 0; --i) {
            int childDrawingOrder;
            if (childrenDrawingOrderEnabled) {
                childDrawingOrder = this.getChildDrawingOrder(childCount, i);
            }
            else {
                childDrawingOrder = i;
            }
            list.add(this.getChildAt(childDrawingOrder));
        }
        if (CoordinatorLayout.TOP_SORTED_CHILDREN_COMPARATOR != null) {
            Collections.sort((List<Object>)list, (Comparator<? super Object>)CoordinatorLayout.TOP_SORTED_CHILDREN_COMPARATOR);
        }
    }
    
    private void layoutChild(final View view, final int n) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final Rect mTempRect1 = this.mTempRect1;
        mTempRect1.set(this.getPaddingLeft() + layoutParams.leftMargin, this.getPaddingTop() + layoutParams.topMargin, this.getWidth() - this.getPaddingRight() - layoutParams.rightMargin, this.getHeight() - this.getPaddingBottom() - layoutParams.bottomMargin);
        if (this.mLastInsets != null && ViewCompat.getFitsSystemWindows((View)this) && !ViewCompat.getFitsSystemWindows(view)) {
            mTempRect1.left += this.mLastInsets.getSystemWindowInsetLeft();
            mTempRect1.top += this.mLastInsets.getSystemWindowInsetTop();
            mTempRect1.right -= this.mLastInsets.getSystemWindowInsetRight();
            mTempRect1.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
        }
        final Rect mTempRect2 = this.mTempRect2;
        GravityCompat.apply(resolveGravity(layoutParams.gravity), view.getMeasuredWidth(), view.getMeasuredHeight(), mTempRect1, mTempRect2, n);
        view.layout(mTempRect2.left, mTempRect2.top, mTempRect2.right, mTempRect2.bottom);
    }
    
    private void layoutChildWithAnchor(final View view, final View view2, final int n) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final Rect mTempRect1 = this.mTempRect1;
        final Rect mTempRect2 = this.mTempRect2;
        this.getDescendantRect(view2, mTempRect1);
        this.getDesiredAnchoredChildRect(view, n, mTempRect1, mTempRect2);
        view.layout(mTempRect2.left, mTempRect2.top, mTempRect2.right, mTempRect2.bottom);
    }
    
    private void layoutChildWithKeyline(final View view, int max, int max2) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(layoutParams.gravity), max2);
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        int n = max;
        if (max2 == 1) {
            n = width - max;
        }
        max = this.getKeyline(n) - measuredWidth;
        max2 = 0;
        switch (absoluteGravity & 0x7) {
            case 5: {
                max += measuredWidth;
                break;
            }
            case 1: {
                max += measuredWidth / 2;
                break;
            }
        }
        switch (absoluteGravity & 0x70) {
            case 80: {
                max2 = 0 + measuredHeight;
                break;
            }
            case 16: {
                max2 = 0 + measuredHeight / 2;
                break;
            }
        }
        max = Math.max(this.getPaddingLeft() + layoutParams.leftMargin, Math.min(max, width - this.getPaddingRight() - measuredWidth - layoutParams.rightMargin));
        max2 = Math.max(this.getPaddingTop() + layoutParams.topMargin, Math.min(max2, height - this.getPaddingBottom() - measuredHeight - layoutParams.bottomMargin));
        view.layout(max, max2, max + measuredWidth, max2 + measuredHeight);
    }
    
    static Behavior parseBehavior(final Context context, final AttributeSet set, String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        Label_0152: {
            if (!s.startsWith(".")) {
                break Label_0152;
            }
            s = context.getPackageName() + s;
            try {
                // iftrue(Label_0202:, TextUtils.isEmpty((CharSequence)CoordinatorLayout.WIDGET_PACKAGE_NAME))
                // iftrue(Label_0164:, s.indexOf(46) < 0)
            Label_0202_Outer:
                while (true) {
                    Map<String, Constructor<Behavior>> map;
                    if ((map = CoordinatorLayout.sConstructors.get()) == null) {
                        map = new HashMap<String, Constructor<Behavior>>();
                        CoordinatorLayout.sConstructors.set(map);
                    }
                    Constructor<?> constructor;
                    if ((constructor = map.get(s)) == null) {
                        constructor = Class.forName(s, true, context.getClassLoader()).getConstructor(CoordinatorLayout.CONSTRUCTOR_PARAMS);
                        constructor.setAccessible(true);
                        map.put(s, (Constructor<Behavior>)constructor);
                    }
                    return constructor.newInstance(context, set);
                Block_7:
                    while (true) {
                        continue Label_0202_Outer;
                        Label_0164: {
                            break Block_7;
                        }
                        continue;
                    }
                    s = CoordinatorLayout.WIDGET_PACKAGE_NAME + '.' + s;
                    continue Label_0202_Outer;
                }
            }
            catch (Exception ex) {
                throw new RuntimeException("Could not inflate Behavior subclass " + s, ex);
            }
        }
    }
    
    private boolean performIntercept(final MotionEvent motionEvent, final int n) {
        boolean b = false;
        int n2 = 0;
        MotionEvent motionEvent2 = null;
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        final List<View> mTempList1 = this.mTempList1;
        this.getTopSortedChildren(mTempList1);
        final int size = mTempList1.size();
        int n3 = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n3 >= size) {
                break;
            }
            final View mBehaviorTouchView = mTempList1.get(n3);
            final LayoutParams layoutParams = (LayoutParams)mBehaviorTouchView.getLayoutParams();
            final Behavior behavior = layoutParams.getBehavior();
            MotionEvent obtain;
            boolean b3;
            int n4;
            if ((b || n2 != 0) && actionMasked != 0) {
                obtain = motionEvent2;
                b3 = b;
                n4 = n2;
                if (behavior != null) {
                    if ((obtain = motionEvent2) == null) {
                        final long uptimeMillis = SystemClock.uptimeMillis();
                        obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                    }
                    switch (n) {
                        default: {
                            n4 = n2;
                            b3 = b;
                            break;
                        }
                        case 0: {
                            behavior.onInterceptTouchEvent(this, mBehaviorTouchView, obtain);
                            b3 = b;
                            n4 = n2;
                            break;
                        }
                        case 1: {
                            behavior.onTouchEvent(this, mBehaviorTouchView, obtain);
                            b3 = b;
                            n4 = n2;
                            break;
                        }
                    }
                }
            }
            else {
                b2 = b;
                if (!b) {
                    b2 = b;
                    if (behavior != null) {
                        switch (n) {
                            case 0: {
                                b = behavior.onInterceptTouchEvent(this, mBehaviorTouchView, motionEvent);
                                break;
                            }
                            case 1: {
                                b = behavior.onTouchEvent(this, mBehaviorTouchView, motionEvent);
                                break;
                            }
                        }
                        b2 = b;
                        if (b) {
                            this.mBehaviorTouchView = mBehaviorTouchView;
                            b2 = b;
                        }
                    }
                }
                final boolean didBlockInteraction = layoutParams.didBlockInteraction();
                final boolean blockingInteractionBelow = layoutParams.isBlockingInteractionBelow(this, mBehaviorTouchView);
                boolean b4;
                if (blockingInteractionBelow && !didBlockInteraction) {
                    b4 = true;
                }
                else {
                    b4 = false;
                }
                obtain = motionEvent2;
                b3 = b2;
                n4 = (b4 ? 1 : 0);
                if (blockingInteractionBelow) {
                    obtain = motionEvent2;
                    b3 = b2;
                    if ((n4 = (b4 ? 1 : 0)) == 0) {
                        break;
                    }
                }
            }
            ++n3;
            motionEvent2 = obtain;
            b = b3;
            n2 = n4;
        }
        mTempList1.clear();
        return b2;
    }
    
    private void prepareChildren() {
        this.mDependencySortedChildren.clear();
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            this.getResolvedLayoutParams(child).findAnchorView(this, child);
            this.mDependencySortedChildren.add(child);
        }
        selectionSort(this.mDependencySortedChildren, this.mLayoutDependencyComparator);
    }
    
    private void resetTouchBehaviors() {
        if (this.mBehaviorTouchView != null) {
            final Behavior behavior = ((LayoutParams)this.mBehaviorTouchView.getLayoutParams()).getBehavior();
            if (behavior != null) {
                final long uptimeMillis = SystemClock.uptimeMillis();
                final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                behavior.onTouchEvent(this, this.mBehaviorTouchView, obtain);
                obtain.recycle();
            }
            this.mBehaviorTouchView = null;
        }
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            ((LayoutParams)this.getChildAt(i).getLayoutParams()).resetTouchBehaviorTracking();
        }
    }
    
    private static int resolveAnchoredChildGravity(final int n) {
        int n2 = n;
        if (n == 0) {
            n2 = 17;
        }
        return n2;
    }
    
    private static int resolveGravity(final int n) {
        int n2 = n;
        if (n == 0) {
            n2 = 8388659;
        }
        return n2;
    }
    
    private static int resolveKeylineGravity(final int n) {
        int n2 = n;
        if (n == 0) {
            n2 = 8388661;
        }
        return n2;
    }
    
    private static void selectionSort(final List<View> list, final Comparator<View> comparator) {
        if (list != null && list.size() >= 2) {
            final View[] array = new View[list.size()];
            list.toArray(array);
            final int length = array.length;
            for (int i = 0; i < length; ++i) {
                int n = i;
                int n2;
                for (int j = i + 1; j < length; ++j, n = n2) {
                    n2 = n;
                    if (comparator.compare(array[j], array[n]) < 0) {
                        n2 = j;
                    }
                }
                if (i != n) {
                    final View view = array[n];
                    array[n] = array[i];
                    array[i] = view;
                }
            }
            list.clear();
            for (int k = 0; k < length; ++k) {
                list.add(array[k]);
            }
        }
    }
    
    private void setWindowInsets(final WindowInsetsCompat mLastInsets) {
        final boolean b = true;
        if (this.mLastInsets != mLastInsets) {
            this.mLastInsets = mLastInsets;
            this.mDrawStatusBarBackground = (mLastInsets != null && mLastInsets.getSystemWindowInsetTop() > 0);
            this.setWillNotDraw(!this.mDrawStatusBarBackground && this.getBackground() == null && b);
            this.dispatchChildApplyWindowInsets(mLastInsets);
            this.requestLayout();
        }
    }
    
    void addPreDrawListener() {
        if (this.mIsAttachedToWindow) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new OnPreDrawListener();
            }
            this.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = true;
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof LayoutParams && super.checkLayoutParams(viewGroup$LayoutParams);
    }
    
    void dispatchDependentViewRemoved(final View view) {
        final int size = this.mDependencySortedChildren.size();
        int n = 0;
        int n2;
        for (int i = 0; i < size; ++i, n = n2) {
            final View view2 = this.mDependencySortedChildren.get(i);
            if (view2 == view) {
                n2 = 1;
            }
            else if ((n2 = n) != 0) {
                final LayoutParams layoutParams = (LayoutParams)view2.getLayoutParams();
                final Behavior behavior = layoutParams.getBehavior();
                n2 = n;
                if (behavior != null) {
                    n2 = n;
                    if (layoutParams.dependsOn(this, view2, view)) {
                        behavior.onDependentViewRemoved(this, view2, view);
                        n2 = n;
                    }
                }
            }
        }
    }
    
    public void dispatchDependentViewsChanged(final View view) {
        final int size = this.mDependencySortedChildren.size();
        int n = 0;
        int n2;
        for (int i = 0; i < size; ++i, n = n2) {
            final View view2 = this.mDependencySortedChildren.get(i);
            if (view2 == view) {
                n2 = 1;
            }
            else if ((n2 = n) != 0) {
                final LayoutParams layoutParams = (LayoutParams)view2.getLayoutParams();
                final Behavior behavior = layoutParams.getBehavior();
                n2 = n;
                if (behavior != null) {
                    n2 = n;
                    if (layoutParams.dependsOn(this, view2, view)) {
                        behavior.onDependentViewChanged(this, view2, view);
                        n2 = n;
                    }
                }
            }
        }
    }
    
    void dispatchOnDependentViewChanged(final boolean b) {
        final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
        for (int size = this.mDependencySortedChildren.size(), i = 0; i < size; ++i) {
            final View view = this.mDependencySortedChildren.get(i);
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            for (int j = 0; j < i; ++j) {
                if (layoutParams.mAnchorDirectChild == this.mDependencySortedChildren.get(j)) {
                    this.offsetChildToAnchor(view, layoutDirection);
                }
            }
            final Rect mTempRect1 = this.mTempRect1;
            final Rect mTempRect2 = this.mTempRect2;
            this.getLastChildRect(view, mTempRect1);
            this.getChildRect(view, true, mTempRect2);
            if (!mTempRect1.equals((Object)mTempRect2)) {
                this.recordLastChildRect(view, mTempRect2);
                for (int k = i + 1; k < size; ++k) {
                    final View view2 = this.mDependencySortedChildren.get(k);
                    final LayoutParams layoutParams2 = (LayoutParams)view2.getLayoutParams();
                    final Behavior behavior = layoutParams2.getBehavior();
                    if (behavior != null && behavior.layoutDependsOn(this, view2, view)) {
                        if (!b && layoutParams2.getChangedAfterNestedScroll()) {
                            layoutParams2.resetChangedAfterNestedScroll();
                        }
                        else {
                            final boolean onDependentViewChanged = behavior.onDependentViewChanged(this, view2, view);
                            if (b) {
                                layoutParams2.setChangedAfterNestedScroll(onDependentViewChanged);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean doViewsOverlap(final View view, final View view2) {
        if (view.getVisibility() == 0 && view2.getVisibility() == 0) {
            final Rect mTempRect1 = this.mTempRect1;
            this.getChildRect(view, view.getParent() != this, mTempRect1);
            final Rect mTempRect2 = this.mTempRect2;
            this.getChildRect(view2, view2.getParent() != this, mTempRect2);
            return mTempRect1.left <= mTempRect2.right && mTempRect1.top <= mTempRect2.bottom && mTempRect1.right >= mTempRect2.left && mTempRect1.bottom >= mTempRect2.top;
        }
        return false;
    }
    
    protected boolean drawChild(final Canvas canvas, final View view, final long n) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.mBehavior != null && layoutParams.mBehavior.getScrimOpacity(this, view) > 0.0f) {
            if (this.mScrimPaint == null) {
                this.mScrimPaint = new Paint();
            }
            this.mScrimPaint.setColor(layoutParams.mBehavior.getScrimColor(this, view));
            canvas.drawRect((float)this.getPaddingLeft(), (float)this.getPaddingTop(), (float)(this.getWidth() - this.getPaddingRight()), (float)(this.getHeight() - this.getPaddingBottom()), this.mScrimPaint);
        }
        return super.drawChild(canvas, view, n);
    }
    
    void ensurePreDrawListener() {
        final boolean b = false;
        final int childCount = this.getChildCount();
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= childCount) {
                break;
            }
            if (this.hasDependencies(this.getChildAt(n))) {
                b2 = true;
                break;
            }
            ++n;
        }
        if (b2 != this.mNeedsPreDrawListener) {
            if (!b2) {
                this.removePreDrawListener();
                return;
            }
            this.addPreDrawListener();
        }
    }
    
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }
    
    public LayoutParams generateLayoutParams(final AttributeSet set) {
        return new LayoutParams(this.getContext(), set);
    }
    
    protected LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams)viewGroup$LayoutParams);
        }
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new LayoutParams(viewGroup$LayoutParams);
    }
    
    void getChildRect(final View view, final boolean b, final Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.set(0, 0, 0, 0);
            return;
        }
        if (b) {
            this.getDescendantRect(view, rect);
            return;
        }
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }
    
    public List<View> getDependencies(final View view) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final List<View> mTempDependenciesList = this.mTempDependenciesList;
        mTempDependenciesList.clear();
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child != view && layoutParams.dependsOn(this, view, child)) {
                mTempDependenciesList.add(child);
            }
        }
        return mTempDependenciesList;
    }
    
    void getDescendantRect(final View view, final Rect rect) {
        ViewGroupUtils.getDescendantRect(this, view, rect);
    }
    
    void getDesiredAnchoredChildRect(final View view, int n, final Rect rect, final Rect rect2) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(resolveAnchoredChildGravity(layoutParams.gravity), n);
        final int absoluteGravity2 = GravityCompat.getAbsoluteGravity(resolveGravity(layoutParams.anchorGravity), n);
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        switch (absoluteGravity2 & 0x7) {
            default: {
                n = rect.left;
                break;
            }
            case 5: {
                n = rect.right;
                break;
            }
            case 1: {
                n = rect.left + rect.width() / 2;
                break;
            }
        }
        int n2 = 0;
        switch (absoluteGravity2 & 0x70) {
            default: {
                n2 = rect.top;
                break;
            }
            case 80: {
                n2 = rect.bottom;
                break;
            }
            case 16: {
                n2 = rect.top + rect.height() / 2;
                break;
            }
        }
        int n3 = n;
        switch (absoluteGravity & 0x7) {
            case 1: {
                n3 = n - measuredWidth / 2;
            }
            default: {
                n3 = n - measuredWidth;
            }
            case 5: {
                n = n2;
                switch (absoluteGravity & 0x70) {
                    case 16: {
                        n = n2 - measuredHeight / 2;
                    }
                    default: {
                        n = n2 - measuredHeight;
                    }
                    case 80: {
                        final int width = this.getWidth();
                        final int height = this.getHeight();
                        final int max = Math.max(this.getPaddingLeft() + layoutParams.leftMargin, Math.min(n3, width - this.getPaddingRight() - measuredWidth - layoutParams.rightMargin));
                        n = Math.max(this.getPaddingTop() + layoutParams.topMargin, Math.min(n, height - this.getPaddingBottom() - measuredHeight - layoutParams.bottomMargin));
                        rect2.set(max, n, max + measuredWidth, n + measuredHeight);
                        return;
                    }
                }
                break;
            }
        }
    }
    
    void getLastChildRect(final View view, final Rect rect) {
        rect.set(((LayoutParams)view.getLayoutParams()).getLastChildRect());
    }
    
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }
    
    LayoutParams getResolvedLayoutParams(final View view) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.mBehaviorResolved) {
            return layoutParams;
        }
        Serializable s = view.getClass();
        DefaultBehavior defaultBehavior = null;
        DefaultBehavior defaultBehavior2;
        while (true) {
            defaultBehavior2 = defaultBehavior;
            if (s == null) {
                break;
            }
            defaultBehavior = ((Class<? extends View>)s).getAnnotation(DefaultBehavior.class);
            if ((defaultBehavior2 = defaultBehavior) != null) {
                break;
            }
            s = ((Class<? extends View>)s).getSuperclass();
        }
        while (true) {
            if (defaultBehavior2 == null) {
                break Label_0075;
            }
            try {
                layoutParams.setBehavior((Behavior)defaultBehavior2.value().newInstance());
                layoutParams.mBehaviorResolved = true;
                return layoutParams;
            }
            catch (Exception ex) {
                Log.e("CoordinatorLayout", "Default behavior class " + defaultBehavior2.value().getName() + " could not be instantiated. Did you forget a default constructor?", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }
    
    protected int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), this.getPaddingTop() + this.getPaddingBottom());
    }
    
    protected int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), this.getPaddingLeft() + this.getPaddingRight());
    }
    
    boolean hasDependencies(final View view) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.mAnchorView != null) {
            return true;
        }
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child != view && layoutParams.dependsOn(this, view, child)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isPointInChildBounds(final View view, final int n, final int n2) {
        final Rect mTempRect1 = this.mTempRect1;
        this.getDescendantRect(view, mTempRect1);
        return mTempRect1.contains(n, n2);
    }
    
    void offsetChildToAnchor(final View view, int n) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.mAnchorView != null) {
            final Rect mTempRect1 = this.mTempRect1;
            final Rect mTempRect2 = this.mTempRect2;
            final Rect mTempRect3 = this.mTempRect3;
            this.getDescendantRect(layoutParams.mAnchorView, mTempRect1);
            this.getChildRect(view, false, mTempRect2);
            this.getDesiredAnchoredChildRect(view, n, mTempRect1, mTempRect3);
            n = mTempRect3.left - mTempRect2.left;
            final int n2 = mTempRect3.top - mTempRect2.top;
            if (n != 0) {
                view.offsetLeftAndRight(n);
            }
            if (n2 != 0) {
                view.offsetTopAndBottom(n2);
            }
            if (n != 0 || n2 != 0) {
                final Behavior behavior = layoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onDependentViewChanged(this, view, layoutParams.mAnchorView);
                }
            }
        }
    }
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.resetTouchBehaviors();
        if (this.mNeedsPreDrawListener) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new OnPreDrawListener();
            }
            this.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this.mOnPreDrawListener);
        }
        if (this.mLastInsets == null && ViewCompat.getFitsSystemWindows((View)this)) {
            ViewCompat.requestApplyInsets((View)this);
        }
        this.mIsAttachedToWindow = true;
    }
    
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.resetTouchBehaviors();
        if (this.mNeedsPreDrawListener && this.mOnPreDrawListener != null) {
            this.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this.mOnPreDrawListener);
        }
        if (this.mNestedScrollingTarget != null) {
            this.onStopNestedScroll(this.mNestedScrollingTarget);
        }
        this.mIsAttachedToWindow = false;
    }
    
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            int systemWindowInsetTop;
            if (this.mLastInsets != null) {
                systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
            }
            else {
                systemWindowInsetTop = 0;
            }
            if (systemWindowInsetTop > 0) {
                this.mStatusBarBackground.setBounds(0, 0, this.getWidth(), systemWindowInsetTop);
                this.mStatusBarBackground.draw(canvas);
            }
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            this.resetTouchBehaviors();
        }
        final boolean performIntercept = this.performIntercept(motionEvent, 0);
        if (false) {
            throw new NullPointerException();
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.resetTouchBehaviors();
        }
        return performIntercept;
    }
    
    protected void onLayout(final boolean b, int i, int layoutDirection, int size, final int n) {
        layoutDirection = ViewCompat.getLayoutDirection((View)this);
        View view;
        Behavior behavior;
        for (size = this.mDependencySortedChildren.size(), i = 0; i < size; ++i) {
            view = this.mDependencySortedChildren.get(i);
            behavior = ((LayoutParams)view.getLayoutParams()).getBehavior();
            if (behavior == null || !behavior.onLayoutChild(this, view, layoutDirection)) {
                this.onLayoutChild(view, layoutDirection);
            }
        }
    }
    
    public void onLayoutChild(final View view, final int n) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.checkAnchorChanged()) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        if (layoutParams.mAnchorView != null) {
            this.layoutChildWithAnchor(view, layoutParams.mAnchorView, n);
            return;
        }
        if (layoutParams.keyline >= 0) {
            this.layoutChildWithKeyline(view, layoutParams.keyline, n);
            return;
        }
        this.layoutChild(view, n);
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.prepareChildren();
        this.ensurePreDrawListener();
        final int paddingLeft = this.getPaddingLeft();
        final int paddingTop = this.getPaddingTop();
        final int paddingRight = this.getPaddingRight();
        final int paddingBottom = this.getPaddingBottom();
        final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
        boolean b;
        if (layoutDirection == 1) {
            b = true;
        }
        else {
            b = false;
        }
        final int mode = View$MeasureSpec.getMode(n);
        final int size = View$MeasureSpec.getSize(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        final int size2 = View$MeasureSpec.getSize(n2);
        int n3 = this.getSuggestedMinimumWidth();
        int n4 = this.getSuggestedMinimumHeight();
        int combineMeasuredStates = 0;
        int n5;
        if (this.mLastInsets != null && ViewCompat.getFitsSystemWindows((View)this)) {
            n5 = 1;
        }
        else {
            n5 = 0;
        }
        for (int size3 = this.mDependencySortedChildren.size(), i = 0; i < size3; ++i) {
            final View view = this.mDependencySortedChildren.get(i);
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            int n7;
            final int n6 = n7 = 0;
            Label_0236: {
                if (layoutParams.keyline >= 0) {
                    n7 = n6;
                    if (mode != 0) {
                        final int keyline = this.getKeyline(layoutParams.keyline);
                        final int n8 = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(layoutParams.gravity), layoutDirection) & 0x7;
                        if ((n8 == 3 && !b) || (n8 == 5 && b)) {
                            n7 = Math.max(0, size - paddingRight - keyline);
                        }
                        else {
                            if (n8 != 5 || b) {
                                n7 = n6;
                                if (n8 != 3) {
                                    break Label_0236;
                                }
                                n7 = n6;
                                if (!b) {
                                    break Label_0236;
                                }
                            }
                            n7 = Math.max(0, keyline - paddingLeft);
                        }
                    }
                }
            }
            int measureSpec = n;
            int measureSpec2 = n2;
            if (n5 != 0) {
                measureSpec = n;
                measureSpec2 = n2;
                if (!ViewCompat.getFitsSystemWindows(view)) {
                    final int systemWindowInsetLeft = this.mLastInsets.getSystemWindowInsetLeft();
                    final int systemWindowInsetRight = this.mLastInsets.getSystemWindowInsetRight();
                    final int systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
                    final int systemWindowInsetBottom = this.mLastInsets.getSystemWindowInsetBottom();
                    measureSpec = View$MeasureSpec.makeMeasureSpec(size - (systemWindowInsetLeft + systemWindowInsetRight), mode);
                    measureSpec2 = View$MeasureSpec.makeMeasureSpec(size2 - (systemWindowInsetTop + systemWindowInsetBottom), mode2);
                }
            }
            final Behavior behavior = layoutParams.getBehavior();
            if (behavior == null || !behavior.onMeasureChild(this, view, measureSpec, n7, measureSpec2, 0)) {
                this.onMeasureChild(view, measureSpec, n7, measureSpec2, 0);
            }
            n3 = Math.max(n3, view.getMeasuredWidth() + (paddingLeft + paddingRight) + layoutParams.leftMargin + layoutParams.rightMargin);
            n4 = Math.max(n4, view.getMeasuredHeight() + (paddingTop + paddingBottom) + layoutParams.topMargin + layoutParams.bottomMargin);
            combineMeasuredStates = ViewCompat.combineMeasuredStates(combineMeasuredStates, ViewCompat.getMeasuredState(view));
        }
        this.setMeasuredDimension(ViewCompat.resolveSizeAndState(n3, n, 0xFF000000 & combineMeasuredStates), ViewCompat.resolveSizeAndState(n4, n2, combineMeasuredStates << 16));
    }
    
    public void onMeasureChild(final View view, final int n, final int n2, final int n3, final int n4) {
        this.measureChildWithMargins(view, n, n2, n3, n4);
    }
    
    public boolean onNestedFling(final View view, final float n, final float n2, final boolean b) {
        boolean b2 = false;
        boolean b3;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i, b2 = b3) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (!layoutParams.isNestedScrollAccepted()) {
                b3 = b2;
            }
            else {
                final Behavior behavior = layoutParams.getBehavior();
                b3 = b2;
                if (behavior != null) {
                    b3 = (b2 | behavior.onNestedFling(this, child, view, n, n2, b));
                }
            }
        }
        if (b2) {
            this.dispatchOnDependentViewChanged(true);
        }
        return b2;
    }
    
    public boolean onNestedPreFling(final View view, final float n, final float n2) {
        boolean b = false;
        boolean b2;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i, b = b2) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (!layoutParams.isNestedScrollAccepted()) {
                b2 = b;
            }
            else {
                final Behavior behavior = layoutParams.getBehavior();
                b2 = b;
                if (behavior != null) {
                    b2 = (b | behavior.onNestedPreFling(this, child, view, n, n2));
                }
            }
        }
        return b;
    }
    
    public void onNestedPreScroll(final View view, final int n, final int n2, final int[] array) {
        int n3 = 0;
        int n4 = 0;
        boolean b = false;
        int n5;
        int n6;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i, n3 = n6, n4 = n5) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (!layoutParams.isNestedScrollAccepted()) {
                n5 = n4;
                n6 = n3;
            }
            else {
                final Behavior behavior = layoutParams.getBehavior();
                n6 = n3;
                n5 = n4;
                if (behavior != null) {
                    this.mTempIntPair[this.mTempIntPair[1] = 0] = 0;
                    behavior.onNestedPreScroll(this, child, view, n, n2, this.mTempIntPair);
                    int n7;
                    if (n > 0) {
                        n7 = Math.max(n3, this.mTempIntPair[0]);
                    }
                    else {
                        n7 = Math.min(n3, this.mTempIntPair[0]);
                    }
                    int n8;
                    if (n2 > 0) {
                        n8 = Math.max(n4, this.mTempIntPair[1]);
                    }
                    else {
                        n8 = Math.min(n4, this.mTempIntPair[1]);
                    }
                    b = true;
                    n6 = n7;
                    n5 = n8;
                }
            }
        }
        array[0] = n3;
        array[1] = n4;
        if (b) {
            this.dispatchOnDependentViewChanged(true);
        }
    }
    
    public void onNestedScroll(final View view, final int n, final int n2, final int n3, final int n4) {
        final int childCount = this.getChildCount();
        boolean b = false;
        for (int i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (layoutParams.isNestedScrollAccepted()) {
                final Behavior behavior = layoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onNestedScroll(this, child, view, n, n2, n3, n4);
                    b = true;
                }
            }
        }
        if (b) {
            this.dispatchOnDependentViewChanged(true);
        }
    }
    
    public void onNestedScrollAccepted(final View mNestedScrollingDirectChild, final View mNestedScrollingTarget, final int n) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(mNestedScrollingDirectChild, mNestedScrollingTarget, n);
        this.mNestedScrollingDirectChild = mNestedScrollingDirectChild;
        this.mNestedScrollingTarget = mNestedScrollingTarget;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (layoutParams.isNestedScrollAccepted()) {
                final Behavior behavior = layoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onNestedScrollAccepted(this, child, mNestedScrollingDirectChild, mNestedScrollingTarget, n);
                }
            }
        }
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        final SavedState savedState = (SavedState)parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        final SparseArray<Parcelable> behaviorStates = savedState.behaviorStates;
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            final int id = child.getId();
            final Behavior behavior = this.getResolvedLayoutParams(child).getBehavior();
            if (id != -1 && behavior != null) {
                final Parcelable parcelable2 = (Parcelable)behaviorStates.get(id);
                if (parcelable2 != null) {
                    behavior.onRestoreInstanceState(this, child, parcelable2);
                }
            }
        }
    }
    
    protected Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        final SparseArray behaviorStates = new SparseArray();
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            final int id = child.getId();
            final Behavior behavior = ((LayoutParams)child.getLayoutParams()).getBehavior();
            if (id != -1 && behavior != null) {
                final Parcelable onSaveInstanceState = behavior.onSaveInstanceState(this, child);
                if (onSaveInstanceState != null) {
                    behaviorStates.append(id, (Object)onSaveInstanceState);
                }
            }
        }
        savedState.behaviorStates = (SparseArray<Parcelable>)behaviorStates;
        return (Parcelable)savedState;
    }
    
    public boolean onStartNestedScroll(final View view, final View view2, final int n) {
        boolean b = false;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            final Behavior behavior = layoutParams.getBehavior();
            if (behavior != null) {
                final boolean onStartNestedScroll = behavior.onStartNestedScroll(this, child, view, view2, n);
                b |= onStartNestedScroll;
                layoutParams.acceptNestedScroll(onStartNestedScroll);
            }
            else {
                layoutParams.acceptNestedScroll(false);
            }
        }
        return b;
    }
    
    public void onStopNestedScroll(final View view) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(view);
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (layoutParams.isNestedScrollAccepted()) {
                final Behavior behavior = layoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onStopNestedScroll(this, child, view);
                }
                layoutParams.resetNestedScroll();
                layoutParams.resetChangedAfterNestedScroll();
            }
        }
        this.mNestedScrollingDirectChild = null;
        this.mNestedScrollingTarget = null;
    }
    
    public boolean onTouchEvent(MotionEvent obtain) {
        final boolean b = false;
        boolean b2 = false;
        final MotionEvent motionEvent = null;
        final MotionEvent motionEvent2 = null;
        final int actionMasked = MotionEventCompat.getActionMasked(obtain);
        boolean performIntercept = false;
        boolean onTouchEvent = false;
        Label_0087: {
            if (this.mBehaviorTouchView == null) {
                b2 = (performIntercept = this.performIntercept(obtain, 1));
                onTouchEvent = b;
                if (!b2) {
                    break Label_0087;
                }
            }
            final Behavior behavior = ((LayoutParams)this.mBehaviorTouchView.getLayoutParams()).getBehavior();
            performIntercept = b2;
            onTouchEvent = b;
            if (behavior != null) {
                onTouchEvent = behavior.onTouchEvent(this, this.mBehaviorTouchView, obtain);
                performIntercept = b2;
            }
        }
        boolean b3;
        if (this.mBehaviorTouchView == null) {
            b3 = (onTouchEvent | super.onTouchEvent(obtain));
            obtain = motionEvent2;
        }
        else {
            obtain = motionEvent2;
            b3 = onTouchEvent;
            if (performIntercept) {
                obtain = motionEvent;
                if (!false) {
                    final long uptimeMillis = SystemClock.uptimeMillis();
                    obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                super.onTouchEvent(obtain);
                b3 = onTouchEvent;
            }
        }
        if (b3 || actionMasked == 0) {}
        if (obtain != null) {
            obtain.recycle();
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.resetTouchBehaviors();
        }
        return b3;
    }
    
    void recordLastChildRect(final View view, final Rect lastChildRect) {
        ((LayoutParams)view.getLayoutParams()).setLastChildRect(lastChildRect);
    }
    
    void removePreDrawListener() {
        if (this.mIsAttachedToWindow && this.mOnPreDrawListener != null) {
            this.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = false;
    }
    
    public void requestDisallowInterceptTouchEvent(final boolean b) {
        super.requestDisallowInterceptTouchEvent(b);
        if (b) {
            this.resetTouchBehaviors();
        }
    }
    
    public void setOnHierarchyChangeListener(final ViewGroup$OnHierarchyChangeListener mOnHierarchyChangeListener) {
        this.mOnHierarchyChangeListener = mOnHierarchyChangeListener;
    }
    
    public void setStatusBarBackground(final Drawable mStatusBarBackground) {
        this.mStatusBarBackground = mStatusBarBackground;
        this.invalidate();
    }
    
    public void setStatusBarBackgroundColor(final int n) {
        this.setStatusBarBackground((Drawable)new ColorDrawable(n));
    }
    
    public void setStatusBarBackgroundResource(final int n) {
        Drawable drawable;
        if (n != 0) {
            drawable = ContextCompat.getDrawable(this.getContext(), n);
        }
        else {
            drawable = null;
        }
        this.setStatusBarBackground(drawable);
    }
    
    final class ApplyInsetsListener implements OnApplyWindowInsetsListener
    {
        @Override
        public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
            CoordinatorLayout.this.setWindowInsets(windowInsetsCompat);
            return windowInsetsCompat.consumeSystemWindowInsets();
        }
    }
    
    public abstract static class Behavior<V extends View>
    {
        public Behavior() {
        }
        
        public Behavior(final Context context, final AttributeSet set) {
        }
        
        public static Object getTag(final View view) {
            return ((LayoutParams)view.getLayoutParams()).mBehaviorTag;
        }
        
        public static void setTag(final View view, final Object mBehaviorTag) {
            ((LayoutParams)view.getLayoutParams()).mBehaviorTag = mBehaviorTag;
        }
        
        public boolean blocksInteractionBelow(final CoordinatorLayout coordinatorLayout, final V v) {
            return this.getScrimOpacity(coordinatorLayout, v) > 0.0f;
        }
        
        public final int getScrimColor(final CoordinatorLayout coordinatorLayout, final V v) {
            return -16777216;
        }
        
        public final float getScrimOpacity(final CoordinatorLayout coordinatorLayout, final V v) {
            return 0.0f;
        }
        
        public boolean isDirty(final CoordinatorLayout coordinatorLayout, final V v) {
            return false;
        }
        
        public boolean layoutDependsOn(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
            return false;
        }
        
        public WindowInsetsCompat onApplyWindowInsets(final CoordinatorLayout coordinatorLayout, final V v, final WindowInsetsCompat windowInsetsCompat) {
            return windowInsetsCompat;
        }
        
        public boolean onDependentViewChanged(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
            return false;
        }
        
        public void onDependentViewRemoved(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
        }
        
        public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
            return false;
        }
        
        public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final V v, final int n) {
            return false;
        }
        
        public boolean onMeasureChild(final CoordinatorLayout coordinatorLayout, final V v, final int n, final int n2, final int n3, final int n4) {
            return false;
        }
        
        public boolean onNestedFling(final CoordinatorLayout coordinatorLayout, final V v, final View view, final float n, final float n2, final boolean b) {
            return false;
        }
        
        public boolean onNestedPreFling(final CoordinatorLayout coordinatorLayout, final V v, final View view, final float n, final float n2) {
            return false;
        }
        
        public void onNestedPreScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view, final int n, final int n2, final int[] array) {
        }
        
        public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view, final int n, final int n2, final int n3, final int n4) {
        }
        
        public void onNestedScrollAccepted(final CoordinatorLayout coordinatorLayout, final V v, final View view, final View view2, final int n) {
        }
        
        public void onRestoreInstanceState(final CoordinatorLayout coordinatorLayout, final V v, final Parcelable parcelable) {
        }
        
        public Parcelable onSaveInstanceState(final CoordinatorLayout coordinatorLayout, final V v) {
            return (Parcelable)View$BaseSavedState.EMPTY_STATE;
        }
        
        public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view, final View view2, final int n) {
            return false;
        }
        
        public void onStopNestedScroll(final CoordinatorLayout coordinatorLayout, final V v, final View view) {
        }
        
        public boolean onTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
            return false;
        }
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DefaultBehavior {
        Class<? extends Behavior> value();
    }
    
    final class HierarchyChangeListener implements ViewGroup$OnHierarchyChangeListener
    {
        public void onChildViewAdded(final View view, final View view2) {
            if (CoordinatorLayout.this.mOnHierarchyChangeListener != null) {
                CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }
        
        public void onChildViewRemoved(final View view, final View view2) {
            CoordinatorLayout.this.dispatchDependentViewRemoved(view2);
            if (CoordinatorLayout.this.mOnHierarchyChangeListener != null) {
                CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }
    
    public static class LayoutParams extends ViewGroup$MarginLayoutParams
    {
        public int anchorGravity;
        public int gravity;
        public int keyline;
        View mAnchorDirectChild;
        int mAnchorId;
        View mAnchorView;
        Behavior mBehavior;
        boolean mBehaviorResolved;
        Object mBehaviorTag;
        private boolean mDidAcceptNestedScroll;
        private boolean mDidBlockInteraction;
        private boolean mDidChangeAfterNestedScroll;
        final Rect mLastChildRect;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.mLastChildRect = new Rect();
        }
        
        LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.mLastChildRect = new Rect();
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.CoordinatorLayout_LayoutParams);
            this.gravity = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_LayoutParams_android_layout_gravity, 0);
            this.mAnchorId = obtainStyledAttributes.getResourceId(R.styleable.CoordinatorLayout_LayoutParams_layout_anchor, -1);
            this.anchorGravity = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_LayoutParams_layout_anchorGravity, 0);
            this.keyline = obtainStyledAttributes.getInteger(R.styleable.CoordinatorLayout_LayoutParams_layout_keyline, -1);
            this.mBehaviorResolved = obtainStyledAttributes.hasValue(R.styleable.CoordinatorLayout_LayoutParams_layout_behavior);
            if (this.mBehaviorResolved) {
                this.mBehavior = CoordinatorLayout.parseBehavior(context, set, obtainStyledAttributes.getString(R.styleable.CoordinatorLayout_LayoutParams_layout_behavior));
            }
            obtainStyledAttributes.recycle();
        }
        
        public LayoutParams(final LayoutParams layoutParams) {
            super((ViewGroup$MarginLayoutParams)layoutParams);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.mLastChildRect = new Rect();
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.mLastChildRect = new Rect();
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.mLastChildRect = new Rect();
        }
        
        private void resolveAnchorView(final View view, final CoordinatorLayout coordinatorLayout) {
            this.mAnchorView = coordinatorLayout.findViewById(this.mAnchorId);
            if (this.mAnchorView != null) {
                View mAnchorView = this.mAnchorView;
                ViewParent viewParent = this.mAnchorView.getParent();
                while (viewParent != coordinatorLayout && viewParent != null) {
                    if (viewParent == view) {
                        if (coordinatorLayout.isInEditMode()) {
                            this.mAnchorDirectChild = null;
                            this.mAnchorView = null;
                            return;
                        }
                        throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                    }
                    else {
                        if (viewParent instanceof View) {
                            mAnchorView = (View)viewParent;
                        }
                        viewParent = viewParent.getParent();
                    }
                }
                this.mAnchorDirectChild = mAnchorView;
                return;
            }
            if (coordinatorLayout.isInEditMode()) {
                this.mAnchorDirectChild = null;
                this.mAnchorView = null;
                return;
            }
            throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout.getResources().getResourceName(this.mAnchorId) + " to anchor view " + view);
        }
        
        private boolean verifyAnchorView(final View view, final CoordinatorLayout coordinatorLayout) {
            if (this.mAnchorView.getId() != this.mAnchorId) {
                return false;
            }
            View mAnchorView = this.mAnchorView;
            for (ViewParent viewParent = this.mAnchorView.getParent(); viewParent != coordinatorLayout; viewParent = viewParent.getParent()) {
                if (viewParent == null || viewParent == view) {
                    this.mAnchorDirectChild = null;
                    this.mAnchorView = null;
                    return false;
                }
                if (viewParent instanceof View) {
                    mAnchorView = (View)viewParent;
                }
            }
            this.mAnchorDirectChild = mAnchorView;
            return true;
        }
        
        void acceptNestedScroll(final boolean mDidAcceptNestedScroll) {
            this.mDidAcceptNestedScroll = mDidAcceptNestedScroll;
        }
        
        boolean checkAnchorChanged() {
            return this.mAnchorView == null && this.mAnchorId != -1;
        }
        
        boolean dependsOn(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
            return view2 == this.mAnchorDirectChild || (this.mBehavior != null && this.mBehavior.layoutDependsOn(coordinatorLayout, view, view2));
        }
        
        boolean didBlockInteraction() {
            if (this.mBehavior == null) {
                this.mDidBlockInteraction = false;
            }
            return this.mDidBlockInteraction;
        }
        
        View findAnchorView(final CoordinatorLayout coordinatorLayout, final View view) {
            if (this.mAnchorId == -1) {
                this.mAnchorDirectChild = null;
                return this.mAnchorView = null;
            }
            if (this.mAnchorView == null || !this.verifyAnchorView(view, coordinatorLayout)) {
                this.resolveAnchorView(view, coordinatorLayout);
            }
            return this.mAnchorView;
        }
        
        public int getAnchorId() {
            return this.mAnchorId;
        }
        
        public Behavior getBehavior() {
            return this.mBehavior;
        }
        
        boolean getChangedAfterNestedScroll() {
            return this.mDidChangeAfterNestedScroll;
        }
        
        Rect getLastChildRect() {
            return this.mLastChildRect;
        }
        
        void invalidateAnchor() {
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
        }
        
        boolean isBlockingInteractionBelow(final CoordinatorLayout coordinatorLayout, final View view) {
            return this.mDidBlockInteraction || (this.mDidBlockInteraction |= (this.mBehavior != null && this.mBehavior.blocksInteractionBelow(coordinatorLayout, view)));
        }
        
        boolean isDirty(final CoordinatorLayout coordinatorLayout, final View view) {
            return this.mBehavior != null && this.mBehavior.isDirty(coordinatorLayout, view);
        }
        
        boolean isNestedScrollAccepted() {
            return this.mDidAcceptNestedScroll;
        }
        
        void resetChangedAfterNestedScroll() {
            this.mDidChangeAfterNestedScroll = false;
        }
        
        void resetNestedScroll() {
            this.mDidAcceptNestedScroll = false;
        }
        
        void resetTouchBehaviorTracking() {
            this.mDidBlockInteraction = false;
        }
        
        public void setAnchorId(final int mAnchorId) {
            this.invalidateAnchor();
            this.mAnchorId = mAnchorId;
        }
        
        public void setBehavior(final Behavior mBehavior) {
            if (this.mBehavior != mBehavior) {
                this.mBehavior = mBehavior;
                this.mBehaviorTag = null;
                this.mBehaviorResolved = true;
            }
        }
        
        void setChangedAfterNestedScroll(final boolean mDidChangeAfterNestedScroll) {
            this.mDidChangeAfterNestedScroll = mDidChangeAfterNestedScroll;
        }
        
        void setLastChildRect(final Rect rect) {
            this.mLastChildRect.set(rect);
        }
    }
    
    class OnPreDrawListener implements ViewTreeObserver$OnPreDrawListener
    {
        public boolean onPreDraw() {
            CoordinatorLayout.this.dispatchOnDependentViewChanged(false);
            return true;
        }
    }
    
    protected static class SavedState extends View$BaseSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        SparseArray<Parcelable> behaviorStates;
        
        static {
            CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<SavedState>)new ParcelableCompatCreatorCallbacks<SavedState>() {
                @Override
                public SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
                    return new SavedState(parcel, classLoader);
                }
                
                @Override
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            });
        }
        
        public SavedState(final Parcel parcel, final ClassLoader classLoader) {
            super(parcel);
            final int int1 = parcel.readInt();
            final int[] array = new int[int1];
            parcel.readIntArray(array);
            final Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
            this.behaviorStates = (SparseArray<Parcelable>)new SparseArray(int1);
            for (int i = 0; i < int1; ++i) {
                this.behaviorStates.append(array[i], (Object)parcelableArray[i]);
            }
        }
        
        public SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            int size;
            if (this.behaviorStates != null) {
                size = this.behaviorStates.size();
            }
            else {
                size = 0;
            }
            parcel.writeInt(size);
            final int[] array = new int[size];
            final Parcelable[] array2 = new Parcelable[size];
            for (int i = 0; i < size; ++i) {
                array[i] = this.behaviorStates.keyAt(i);
                array2[i] = (Parcelable)this.behaviorStates.valueAt(i);
            }
            parcel.writeIntArray(array);
            parcel.writeParcelableArray(array2, n);
        }
    }
    
    static class ViewElevationComparator implements Comparator<View>
    {
        @Override
        public int compare(final View view, final View view2) {
            final float z = ViewCompat.getZ(view);
            final float z2 = ViewCompat.getZ(view2);
            if (z > z2) {
                return -1;
            }
            if (z < z2) {
                return 1;
            }
            return 0;
        }
    }
}
