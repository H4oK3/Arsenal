// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.content.res.Resources;
import android.view.ViewParent;
import android.support.v4.widget.TextViewCompat;
import android.widget.Toast;
import android.view.accessibility.AccessibilityNodeInfo;
import android.annotation.TargetApi;
import android.support.v7.app.ActionBar;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup$MarginLayoutParams;
import android.text.Layout;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View$OnLongClickListener;
import java.lang.ref.WeakReference;
import android.support.v7.internal.widget.TintManager;
import android.support.annotation.DrawableRes;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.graphics.drawable.Drawable;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.LinearLayout;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import android.support.annotation.ColorInt;
import java.util.Iterator;
import android.view.View$MeasureSpec;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.LinearLayout$LayoutParams;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.design.R;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import java.util.ArrayList;
import android.content.res.ColorStateList;
import android.view.View$OnClickListener;
import android.widget.HorizontalScrollView;

public class TabLayout extends HorizontalScrollView
{
    private static final int ANIMATION_DURATION = 300;
    private static final int DEFAULT_GAP_TEXT_ICON = 8;
    private static final int DEFAULT_HEIGHT = 48;
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
    private static final int FIXED_WRAP_GUTTER_MIN = 16;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    private static final int INVALID_WIDTH = -1;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    private static final int MOTION_NON_ADJACENT_OFFSET = 24;
    private static final int TAB_MIN_WIDTH_MARGIN = 56;
    private int mContentInsetStart;
    private ValueAnimatorCompat mIndicatorAnimator;
    private int mMode;
    private OnTabSelectedListener mOnTabSelectedListener;
    private final int mRequestedTabMaxWidth;
    private final int mRequestedTabMinWidth;
    private ValueAnimatorCompat mScrollAnimator;
    private final int mScrollableTabMinWidth;
    private Tab mSelectedTab;
    private final int mTabBackgroundResId;
    private View$OnClickListener mTabClickListener;
    private int mTabGravity;
    private int mTabMaxWidth;
    private int mTabPaddingBottom;
    private int mTabPaddingEnd;
    private int mTabPaddingStart;
    private int mTabPaddingTop;
    private final SlidingTabStrip mTabStrip;
    private int mTabTextAppearance;
    private ColorStateList mTabTextColors;
    private float mTabTextMultiLineSize;
    private float mTabTextSize;
    private final ArrayList<Tab> mTabs;
    
    public TabLayout(final Context context) {
        this(context, null);
    }
    
    public TabLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public TabLayout(Context context, final AttributeSet set, int n) {
        super(context, set, n);
        this.mTabs = new ArrayList<Tab>();
        this.mTabMaxWidth = Integer.MAX_VALUE;
        ThemeUtils.checkAppCompatTheme(context);
        this.setHorizontalScrollBarEnabled(false);
        this.addView((View)(this.mTabStrip = new SlidingTabStrip(context)), -2, -1);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.TabLayout, n, R.style.Widget_Design_TabLayout);
        this.mTabStrip.setSelectedIndicatorHeight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabIndicatorHeight, 0));
        this.mTabStrip.setSelectedIndicatorColor(obtainStyledAttributes.getColor(R.styleable.TabLayout_tabIndicatorColor, 0));
        n = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPadding, 0);
        this.mTabPaddingBottom = n;
        this.mTabPaddingEnd = n;
        this.mTabPaddingTop = n;
        this.mTabPaddingStart = n;
        this.mTabPaddingStart = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingStart, this.mTabPaddingStart);
        this.mTabPaddingTop = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingTop, this.mTabPaddingTop);
        this.mTabPaddingEnd = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingEnd, this.mTabPaddingEnd);
        this.mTabPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingBottom, this.mTabPaddingBottom);
        this.mTabTextAppearance = obtainStyledAttributes.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab);
        context = (Context)context.obtainStyledAttributes(this.mTabTextAppearance, R.styleable.TextAppearance);
        try {
            this.mTabTextSize = ((TypedArray)context).getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, 0);
            this.mTabTextColors = ((TypedArray)context).getColorStateList(R.styleable.TextAppearance_android_textColor);
            ((TypedArray)context).recycle();
            if (obtainStyledAttributes.hasValue(R.styleable.TabLayout_tabTextColor)) {
                this.mTabTextColors = obtainStyledAttributes.getColorStateList(R.styleable.TabLayout_tabTextColor);
            }
            if (obtainStyledAttributes.hasValue(R.styleable.TabLayout_tabSelectedTextColor)) {
                n = obtainStyledAttributes.getColor(R.styleable.TabLayout_tabSelectedTextColor, 0);
                this.mTabTextColors = createColorStateList(this.mTabTextColors.getDefaultColor(), n);
            }
            this.mRequestedTabMinWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabMinWidth, -1);
            this.mRequestedTabMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabMaxWidth, -1);
            this.mTabBackgroundResId = obtainStyledAttributes.getResourceId(R.styleable.TabLayout_tabBackground, 0);
            this.mContentInsetStart = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabContentStart, 0);
            this.mMode = obtainStyledAttributes.getInt(R.styleable.TabLayout_tabMode, 1);
            this.mTabGravity = obtainStyledAttributes.getInt(R.styleable.TabLayout_tabGravity, 0);
            obtainStyledAttributes.recycle();
            context = (Context)this.getResources();
            this.mTabTextMultiLineSize = ((Resources)context).getDimensionPixelSize(R.dimen.design_tab_text_size_2line);
            this.mScrollableTabMinWidth = ((Resources)context).getDimensionPixelSize(R.dimen.design_tab_scrollable_min_width);
            this.applyModeAndGravity();
        }
        finally {
            ((TypedArray)context).recycle();
        }
    }
    
    private void addTabView(final Tab tab, final int n, final boolean b) {
        final TabView tabView = this.createTabView(tab);
        this.mTabStrip.addView((View)tabView, n, (ViewGroup$LayoutParams)this.createLayoutParamsForTabs());
        if (b) {
            tabView.setSelected(true);
        }
    }
    
    private void addTabView(final Tab tab, final boolean b) {
        final TabView tabView = this.createTabView(tab);
        this.mTabStrip.addView((View)tabView, (ViewGroup$LayoutParams)this.createLayoutParamsForTabs());
        if (b) {
            tabView.setSelected(true);
        }
    }
    
    private void animateToTab(final int n) {
        if (n == -1) {
            return;
        }
        if (this.getWindowToken() == null || !ViewCompat.isLaidOut((View)this) || this.mTabStrip.childrenNeedLayout()) {
            this.setScrollPosition(n, 0.0f, true);
            return;
        }
        final int scrollX = this.getScrollX();
        final int calculateScrollXForTab = this.calculateScrollXForTab(n, 0.0f);
        if (scrollX != calculateScrollXForTab) {
            if (this.mScrollAnimator == null) {
                (this.mScrollAnimator = ViewUtils.createAnimator()).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                this.mScrollAnimator.setDuration(300);
                this.mScrollAnimator.setUpdateListener((ValueAnimatorCompat.AnimatorUpdateListener)new ValueAnimatorCompat.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
                        TabLayout.this.scrollTo(valueAnimatorCompat.getAnimatedIntValue(), 0);
                    }
                });
            }
            this.mScrollAnimator.setIntValues(scrollX, calculateScrollXForTab);
            this.mScrollAnimator.start();
        }
        this.mTabStrip.animateIndicatorToPosition(n, 300);
    }
    
    private void applyModeAndGravity() {
        int max = 0;
        if (this.mMode == 0) {
            max = Math.max(0, this.mContentInsetStart - this.mTabPaddingStart);
        }
        ViewCompat.setPaddingRelative((View)this.mTabStrip, max, 0, 0, 0);
        switch (this.mMode) {
            case 1: {
                this.mTabStrip.setGravity(1);
                break;
            }
            case 0: {
                this.mTabStrip.setGravity(8388611);
                break;
            }
        }
        this.updateTabViews(true);
    }
    
    private int calculateScrollXForTab(int width, final float n) {
        int n2 = 0;
        final int n3 = 0;
        if (this.mMode == 0) {
            final View child = this.mTabStrip.getChildAt(width);
            View child2;
            if (width + 1 < this.mTabStrip.getChildCount()) {
                child2 = this.mTabStrip.getChildAt(width + 1);
            }
            else {
                child2 = null;
            }
            if (child != null) {
                width = child.getWidth();
            }
            else {
                width = 0;
            }
            int width2 = n3;
            if (child2 != null) {
                width2 = child2.getWidth();
            }
            n2 = child.getLeft() + (int)((width + width2) * n * 0.5f) + child.getWidth() / 2 - this.getWidth() / 2;
        }
        return n2;
    }
    
    private void configureTab(final Tab tab, int i) {
        tab.setPosition(i);
        this.mTabs.add(i, tab);
        int size;
        for (size = this.mTabs.size(), ++i; i < size; ++i) {
            this.mTabs.get(i).setPosition(i);
        }
    }
    
    private static ColorStateList createColorStateList(final int n, int n2) {
        final int[][] array = new int[2][];
        final int[] array2 = new int[2];
        array[0] = TabLayout.SELECTED_STATE_SET;
        array2[0] = n2;
        n2 = 0 + 1;
        array[n2] = TabLayout.EMPTY_STATE_SET;
        array2[n2] = n;
        return new ColorStateList(array, array2);
    }
    
    private LinearLayout$LayoutParams createLayoutParamsForTabs() {
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -1);
        this.updateTabViewLayoutParams(linearLayout$LayoutParams);
        return linearLayout$LayoutParams;
    }
    
    private TabView createTabView(final Tab tab) {
        final TabView tabView = new TabView(this.getContext(), tab);
        tabView.setFocusable(true);
        tabView.setMinimumWidth(this.getTabMinWidth());
        if (this.mTabClickListener == null) {
            this.mTabClickListener = (View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    ((TabView)view).getTab().select();
                }
            };
        }
        tabView.setOnClickListener(this.mTabClickListener);
        return tabView;
    }
    
    private int dpToPx(final int n) {
        return Math.round(this.getResources().getDisplayMetrics().density * n);
    }
    
    private int getDefaultHeight() {
        final boolean b = false;
        int n = 0;
        final int size = this.mTabs.size();
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= size) {
                break;
            }
            final Tab tab = this.mTabs.get(n);
            if (tab != null && tab.getIcon() != null && !TextUtils.isEmpty(tab.getText())) {
                b2 = true;
                break;
            }
            ++n;
        }
        if (b2) {
            return 72;
        }
        return 48;
    }
    
    private float getScrollPosition() {
        return this.mTabStrip.getIndicatorPosition();
    }
    
    private int getTabMaxWidth() {
        return this.mTabMaxWidth;
    }
    
    private int getTabMinWidth() {
        if (this.mRequestedTabMinWidth != -1) {
            return this.mRequestedTabMinWidth;
        }
        if (this.mMode == 0) {
            return this.mScrollableTabMinWidth;
        }
        return 0;
    }
    
    private void removeTabViewAt(final int n) {
        this.mTabStrip.removeViewAt(n);
        this.requestLayout();
    }
    
    private void setSelectedTabView(final int n) {
        final int childCount = this.mTabStrip.getChildCount();
        if (n < childCount && !this.mTabStrip.getChildAt(n).isSelected()) {
            for (int i = 0; i < childCount; ++i) {
                this.mTabStrip.getChildAt(i).setSelected(i == n);
            }
        }
    }
    
    private void updateAllTabs() {
        for (int i = 0; i < this.mTabStrip.getChildCount(); ++i) {
            this.updateTab(i);
        }
    }
    
    private void updateTab(final int n) {
        final TabView tabView = (TabView)this.mTabStrip.getChildAt(n);
        if (tabView != null) {
            tabView.update();
        }
    }
    
    private void updateTabViewLayoutParams(final LinearLayout$LayoutParams linearLayout$LayoutParams) {
        if (this.mMode == 1 && this.mTabGravity == 0) {
            linearLayout$LayoutParams.width = 0;
            linearLayout$LayoutParams.weight = 1.0f;
            return;
        }
        linearLayout$LayoutParams.width = -2;
        linearLayout$LayoutParams.weight = 0.0f;
    }
    
    private void updateTabViews(final boolean b) {
        for (int i = 0; i < this.mTabStrip.getChildCount(); ++i) {
            final View child = this.mTabStrip.getChildAt(i);
            child.setMinimumWidth(this.getTabMinWidth());
            this.updateTabViewLayoutParams((LinearLayout$LayoutParams)child.getLayoutParams());
            if (b) {
                child.requestLayout();
            }
        }
    }
    
    public void addTab(@NonNull final Tab tab) {
        this.addTab(tab, this.mTabs.isEmpty());
    }
    
    public void addTab(@NonNull final Tab tab, final int n) {
        this.addTab(tab, n, this.mTabs.isEmpty());
    }
    
    public void addTab(@NonNull final Tab tab, final int n, final boolean b) {
        if (tab.mParent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        this.addTabView(tab, n, b);
        this.configureTab(tab, n);
        if (b) {
            tab.select();
        }
    }
    
    public void addTab(@NonNull final Tab tab, final boolean b) {
        if (tab.mParent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        this.addTabView(tab, b);
        this.configureTab(tab, this.mTabs.size());
        if (b) {
            tab.select();
        }
    }
    
    public int getSelectedTabPosition() {
        if (this.mSelectedTab != null) {
            return this.mSelectedTab.getPosition();
        }
        return -1;
    }
    
    @Nullable
    public Tab getTabAt(final int n) {
        return this.mTabs.get(n);
    }
    
    public int getTabCount() {
        return this.mTabs.size();
    }
    
    public int getTabGravity() {
        return this.mTabGravity;
    }
    
    public int getTabMode() {
        return this.mMode;
    }
    
    @Nullable
    public ColorStateList getTabTextColors() {
        return this.mTabTextColors;
    }
    
    @NonNull
    public Tab newTab() {
        return new Tab(this);
    }
    
    protected void onMeasure(int childMeasureSpec, int n) {
        final int n2 = this.dpToPx(this.getDefaultHeight()) + this.getPaddingTop() + this.getPaddingBottom();
        switch (View$MeasureSpec.getMode(n)) {
            case Integer.MIN_VALUE: {
                n = View$MeasureSpec.makeMeasureSpec(Math.min(n2, View$MeasureSpec.getSize(n)), 1073741824);
                break;
            }
            case 0: {
                n = View$MeasureSpec.makeMeasureSpec(n2, 1073741824);
                break;
            }
        }
        final int size = View$MeasureSpec.getSize(childMeasureSpec);
        if (View$MeasureSpec.getMode(childMeasureSpec) != 0) {
            int mRequestedTabMaxWidth;
            if (this.mRequestedTabMaxWidth > 0) {
                mRequestedTabMaxWidth = this.mRequestedTabMaxWidth;
            }
            else {
                mRequestedTabMaxWidth = size - this.dpToPx(56);
            }
            this.mTabMaxWidth = mRequestedTabMaxWidth;
        }
        super.onMeasure(childMeasureSpec, n);
        if (this.getChildCount() == 1) {
            final View child = this.getChildAt(0);
            childMeasureSpec = 0;
            switch (this.mMode) {
                case 0: {
                    if (child.getMeasuredWidth() < this.getMeasuredWidth()) {
                        childMeasureSpec = 1;
                    }
                    else {
                        childMeasureSpec = 0;
                    }
                    break;
                }
                case 1: {
                    if (child.getMeasuredWidth() != this.getMeasuredWidth()) {
                        childMeasureSpec = 1;
                    }
                    else {
                        childMeasureSpec = 0;
                    }
                    break;
                }
            }
            if (childMeasureSpec != 0) {
                childMeasureSpec = getChildMeasureSpec(n, this.getPaddingTop() + this.getPaddingBottom(), child.getLayoutParams().height);
                child.measure(View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824), childMeasureSpec);
            }
        }
    }
    
    public void removeAllTabs() {
        this.mTabStrip.removeAllViews();
        final Iterator<Tab> iterator = this.mTabs.iterator();
        while (iterator.hasNext()) {
            iterator.next().setPosition(-1);
            iterator.remove();
        }
        this.mSelectedTab = null;
    }
    
    public void removeTab(final Tab tab) {
        if (tab.mParent != this) {
            throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
        }
        this.removeTabAt(tab.getPosition());
    }
    
    public void removeTabAt(final int n) {
        int position;
        if (this.mSelectedTab != null) {
            position = this.mSelectedTab.getPosition();
        }
        else {
            position = 0;
        }
        this.removeTabViewAt(n);
        final Tab tab = this.mTabs.remove(n);
        if (tab != null) {
            tab.setPosition(-1);
        }
        for (int size = this.mTabs.size(), i = n; i < size; ++i) {
            this.mTabs.get(i).setPosition(i);
        }
        if (position == n) {
            Tab tab2;
            if (this.mTabs.isEmpty()) {
                tab2 = null;
            }
            else {
                tab2 = this.mTabs.get(Math.max(0, n - 1));
            }
            this.selectTab(tab2);
        }
    }
    
    void selectTab(final Tab tab) {
        this.selectTab(tab, true);
    }
    
    void selectTab(final Tab mSelectedTab, final boolean b) {
        if (this.mSelectedTab == mSelectedTab) {
            if (this.mSelectedTab != null) {
                if (this.mOnTabSelectedListener != null) {
                    this.mOnTabSelectedListener.onTabReselected(this.mSelectedTab);
                }
                this.animateToTab(mSelectedTab.getPosition());
            }
        }
        else {
            if (b) {
                int position;
                if (mSelectedTab != null) {
                    position = mSelectedTab.getPosition();
                }
                else {
                    position = -1;
                }
                if (position != -1) {
                    this.setSelectedTabView(position);
                }
                if ((this.mSelectedTab == null || this.mSelectedTab.getPosition() == -1) && position != -1) {
                    this.setScrollPosition(position, 0.0f, true);
                }
                else {
                    this.animateToTab(position);
                }
            }
            if (this.mSelectedTab != null && this.mOnTabSelectedListener != null) {
                this.mOnTabSelectedListener.onTabUnselected(this.mSelectedTab);
            }
            this.mSelectedTab = mSelectedTab;
            if (this.mSelectedTab != null && this.mOnTabSelectedListener != null) {
                this.mOnTabSelectedListener.onTabSelected(this.mSelectedTab);
            }
        }
    }
    
    public void setOnTabSelectedListener(final OnTabSelectedListener mOnTabSelectedListener) {
        this.mOnTabSelectedListener = mOnTabSelectedListener;
    }
    
    public void setScrollPosition(final int n, final float n2, final boolean b) {
        if ((this.mIndicatorAnimator == null || !this.mIndicatorAnimator.isRunning()) && n >= 0 && n < this.mTabStrip.getChildCount()) {
            this.mTabStrip.setIndicatorPositionFromTabPosition(n, n2);
            this.scrollTo(this.calculateScrollXForTab(n, n2), 0);
            if (b) {
                this.setSelectedTabView(Math.round(n + n2));
            }
        }
    }
    
    public void setSelectedTabIndicatorColor(@ColorInt final int selectedIndicatorColor) {
        this.mTabStrip.setSelectedIndicatorColor(selectedIndicatorColor);
    }
    
    public void setSelectedTabIndicatorHeight(final int selectedIndicatorHeight) {
        this.mTabStrip.setSelectedIndicatorHeight(selectedIndicatorHeight);
    }
    
    public void setTabGravity(final int mTabGravity) {
        if (this.mTabGravity != mTabGravity) {
            this.mTabGravity = mTabGravity;
            this.applyModeAndGravity();
        }
    }
    
    public void setTabMode(final int mMode) {
        if (mMode != this.mMode) {
            this.mMode = mMode;
            this.applyModeAndGravity();
        }
    }
    
    public void setTabTextColors(final int n, final int n2) {
        this.setTabTextColors(createColorStateList(n, n2));
    }
    
    public void setTabTextColors(@Nullable final ColorStateList mTabTextColors) {
        if (this.mTabTextColors != mTabTextColors) {
            this.mTabTextColors = mTabTextColors;
            this.updateAllTabs();
        }
    }
    
    public void setTabsFromPagerAdapter(@NonNull final PagerAdapter pagerAdapter) {
        this.removeAllTabs();
        for (int i = 0; i < pagerAdapter.getCount(); ++i) {
            this.addTab(this.newTab().setText(pagerAdapter.getPageTitle(i)));
        }
    }
    
    public void setupWithViewPager(@NonNull final ViewPager viewPager) {
        final PagerAdapter adapter = viewPager.getAdapter();
        if (adapter == null) {
            throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
        }
        this.setTabsFromPagerAdapter(adapter);
        viewPager.addOnPageChangeListener((ViewPager.OnPageChangeListener)new TabLayoutOnPageChangeListener(this));
        this.setOnTabSelectedListener((OnTabSelectedListener)new ViewPagerOnTabSelectedListener(viewPager));
        if (adapter.getCount() > 0) {
            final int currentItem = viewPager.getCurrentItem();
            if (this.getSelectedTabPosition() != currentItem) {
                this.selectTab(this.getTabAt(currentItem));
            }
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }
    
    public interface OnTabSelectedListener
    {
        void onTabReselected(final Tab p0);
        
        void onTabSelected(final Tab p0);
        
        void onTabUnselected(final Tab p0);
    }
    
    private class SlidingTabStrip extends LinearLayout
    {
        private ValueAnimatorCompat mCurrentAnimator;
        private int mIndicatorLeft;
        private int mIndicatorRight;
        private int mSelectedIndicatorHeight;
        private final Paint mSelectedIndicatorPaint;
        private int mSelectedPosition;
        private float mSelectionOffset;
        
        SlidingTabStrip(final Context context) {
            super(context);
            this.mSelectedPosition = -1;
            this.mIndicatorLeft = -1;
            this.mIndicatorRight = -1;
            this.setWillNotDraw(false);
            this.mSelectedIndicatorPaint = new Paint();
        }
        
        private void setIndicatorPosition(final int mIndicatorLeft, final int mIndicatorRight) {
            if (mIndicatorLeft != this.mIndicatorLeft || mIndicatorRight != this.mIndicatorRight) {
                this.mIndicatorLeft = mIndicatorLeft;
                this.mIndicatorRight = mIndicatorRight;
                ViewCompat.postInvalidateOnAnimation((View)this);
            }
        }
        
        private void updateIndicatorPosition() {
            final View child = this.getChildAt(this.mSelectedPosition);
            int n;
            int n2;
            if (child != null && child.getWidth() > 0) {
                final int left = child.getLeft();
                final int right = child.getRight();
                n = left;
                n2 = right;
                if (this.mSelectionOffset > 0.0f) {
                    n = left;
                    n2 = right;
                    if (this.mSelectedPosition < this.getChildCount() - 1) {
                        final View child2 = this.getChildAt(this.mSelectedPosition + 1);
                        n = (int)(this.mSelectionOffset * child2.getLeft() + (1.0f - this.mSelectionOffset) * left);
                        n2 = (int)(this.mSelectionOffset * child2.getRight() + (1.0f - this.mSelectionOffset) * right);
                    }
                }
            }
            else {
                n2 = -1;
                n = -1;
            }
            this.setIndicatorPosition(n, n2);
        }
        
        void animateIndicatorToPosition(final int n, final int duration) {
            final boolean b = ViewCompat.getLayoutDirection((View)this) == 1;
            final View child = this.getChildAt(n);
            final int left = child.getLeft();
            final int right = child.getRight();
            int mIndicatorLeft;
            int mIndicatorRight;
            if (Math.abs(n - this.mSelectedPosition) <= 1) {
                mIndicatorLeft = this.mIndicatorLeft;
                mIndicatorRight = this.mIndicatorRight;
            }
            else {
                final int access$1400 = TabLayout.this.dpToPx(24);
                if (n < this.mSelectedPosition) {
                    if (b) {
                        mIndicatorRight = (mIndicatorLeft = left - access$1400);
                    }
                    else {
                        mIndicatorRight = (mIndicatorLeft = right + access$1400);
                    }
                }
                else if (b) {
                    mIndicatorRight = (mIndicatorLeft = right + access$1400);
                }
                else {
                    mIndicatorRight = (mIndicatorLeft = left - access$1400);
                }
            }
            if (mIndicatorLeft != left || mIndicatorRight != right) {
                final ValueAnimatorCompat access$1401 = TabLayout.this.mIndicatorAnimator = ViewUtils.createAnimator();
                access$1401.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                access$1401.setDuration(duration);
                access$1401.setFloatValues(0.0f, 1.0f);
                access$1401.setUpdateListener((ValueAnimatorCompat.AnimatorUpdateListener)new ValueAnimatorCompat.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
                        final float animatedFraction = valueAnimatorCompat.getAnimatedFraction();
                        SlidingTabStrip.this.setIndicatorPosition(AnimationUtils.lerp(mIndicatorLeft, left, animatedFraction), AnimationUtils.lerp(mIndicatorRight, right, animatedFraction));
                    }
                });
                access$1401.setListener((ValueAnimatorCompat.AnimatorListener)new ValueAnimatorCompat.AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(final ValueAnimatorCompat valueAnimatorCompat) {
                        SlidingTabStrip.this.mSelectedPosition = n;
                        SlidingTabStrip.this.mSelectionOffset = 0.0f;
                    }
                    
                    @Override
                    public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
                        SlidingTabStrip.this.mSelectedPosition = n;
                        SlidingTabStrip.this.mSelectionOffset = 0.0f;
                    }
                });
                access$1401.start();
                this.mCurrentAnimator = access$1401;
            }
        }
        
        boolean childrenNeedLayout() {
            for (int i = 0; i < this.getChildCount(); ++i) {
                if (this.getChildAt(i).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }
        
        public void draw(final Canvas canvas) {
            super.draw(canvas);
            if (this.mIndicatorLeft >= 0 && this.mIndicatorRight > this.mIndicatorLeft) {
                canvas.drawRect((float)this.mIndicatorLeft, (float)(this.getHeight() - this.mSelectedIndicatorHeight), (float)this.mIndicatorRight, (float)this.getHeight(), this.mSelectedIndicatorPaint);
            }
        }
        
        float getIndicatorPosition() {
            return this.mSelectedPosition + this.mSelectionOffset;
        }
        
        protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
            super.onLayout(b, n, n2, n3, n4);
            if (this.mCurrentAnimator != null && this.mCurrentAnimator.isRunning()) {
                this.mCurrentAnimator.cancel();
                this.animateIndicatorToPosition(this.mSelectedPosition, Math.round((1.0f - this.mCurrentAnimator.getAnimatedFraction()) * this.mCurrentAnimator.getDuration()));
                return;
            }
            this.updateIndicatorPosition();
        }
        
        protected void onMeasure(final int n, final int n2) {
            super.onMeasure(n, n2);
            if (View$MeasureSpec.getMode(n) == 1073741824 && TabLayout.this.mMode == 1 && TabLayout.this.mTabGravity == 1) {
                final int childCount = this.getChildCount();
                int width = 0;
                int max;
                for (int i = 0; i < childCount; ++i, width = max) {
                    final View child = this.getChildAt(i);
                    max = width;
                    if (child.getVisibility() == 0) {
                        max = Math.max(width, child.getMeasuredWidth());
                    }
                }
                if (width > 0) {
                    final int access$1400 = TabLayout.this.dpToPx(16);
                    int n3 = 0;
                    int n5;
                    if (width * childCount <= this.getMeasuredWidth() - access$1400 * 2) {
                        int n4 = 0;
                        while (true) {
                            n5 = n3;
                            if (n4 >= childCount) {
                                break;
                            }
                            final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)this.getChildAt(n4).getLayoutParams();
                            if (linearLayout$LayoutParams.width != width || linearLayout$LayoutParams.weight != 0.0f) {
                                linearLayout$LayoutParams.width = width;
                                linearLayout$LayoutParams.weight = 0.0f;
                                n3 = 1;
                            }
                            ++n4;
                        }
                    }
                    else {
                        TabLayout.this.mTabGravity = 0;
                        TabLayout.this.updateTabViews(false);
                        n5 = 1;
                    }
                    if (n5 != 0) {
                        super.onMeasure(n, n2);
                    }
                }
            }
        }
        
        void setIndicatorPositionFromTabPosition(final int mSelectedPosition, final float mSelectionOffset) {
            this.mSelectedPosition = mSelectedPosition;
            this.mSelectionOffset = mSelectionOffset;
            this.updateIndicatorPosition();
        }
        
        void setSelectedIndicatorColor(final int color) {
            if (this.mSelectedIndicatorPaint.getColor() != color) {
                this.mSelectedIndicatorPaint.setColor(color);
                ViewCompat.postInvalidateOnAnimation((View)this);
            }
        }
        
        void setSelectedIndicatorHeight(final int mSelectedIndicatorHeight) {
            if (this.mSelectedIndicatorHeight != mSelectedIndicatorHeight) {
                this.mSelectedIndicatorHeight = mSelectedIndicatorHeight;
                ViewCompat.postInvalidateOnAnimation((View)this);
            }
        }
    }
    
    public static final class Tab
    {
        public static final int INVALID_POSITION = -1;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private final TabLayout mParent;
        private int mPosition;
        private Object mTag;
        private CharSequence mText;
        
        Tab(final TabLayout mParent) {
            this.mPosition = -1;
            this.mParent = mParent;
        }
        
        @Nullable
        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }
        
        @Nullable
        public View getCustomView() {
            return this.mCustomView;
        }
        
        @Nullable
        public Drawable getIcon() {
            return this.mIcon;
        }
        
        public int getPosition() {
            return this.mPosition;
        }
        
        @Nullable
        public Object getTag() {
            return this.mTag;
        }
        
        @Nullable
        public CharSequence getText() {
            return this.mText;
        }
        
        public boolean isSelected() {
            return this.mParent.getSelectedTabPosition() == this.mPosition;
        }
        
        public void select() {
            this.mParent.selectTab(this);
        }
        
        @NonNull
        public Tab setContentDescription(@StringRes final int n) {
            return this.setContentDescription(this.mParent.getResources().getText(n));
        }
        
        @NonNull
        public Tab setContentDescription(@Nullable final CharSequence mContentDesc) {
            this.mContentDesc = mContentDesc;
            if (this.mPosition >= 0) {
                this.mParent.updateTab(this.mPosition);
            }
            return this;
        }
        
        @NonNull
        public Tab setCustomView(@LayoutRes final int n) {
            return this.setCustomView(LayoutInflater.from(this.mParent.getContext()).inflate(n, (ViewGroup)null));
        }
        
        @NonNull
        public Tab setCustomView(@Nullable final View mCustomView) {
            this.mCustomView = mCustomView;
            if (this.mPosition >= 0) {
                this.mParent.updateTab(this.mPosition);
            }
            return this;
        }
        
        @NonNull
        public Tab setIcon(@DrawableRes final int n) {
            return this.setIcon(TintManager.getDrawable(this.mParent.getContext(), n));
        }
        
        @NonNull
        public Tab setIcon(@Nullable final Drawable mIcon) {
            this.mIcon = mIcon;
            if (this.mPosition >= 0) {
                this.mParent.updateTab(this.mPosition);
            }
            return this;
        }
        
        void setPosition(final int mPosition) {
            this.mPosition = mPosition;
        }
        
        @NonNull
        public Tab setTag(@Nullable final Object mTag) {
            this.mTag = mTag;
            return this;
        }
        
        @NonNull
        public Tab setText(@StringRes final int n) {
            return this.setText(this.mParent.getResources().getText(n));
        }
        
        @NonNull
        public Tab setText(@Nullable final CharSequence mText) {
            this.mText = mText;
            if (this.mPosition >= 0) {
                this.mParent.updateTab(this.mPosition);
            }
            return this;
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabGravity {
    }
    
    public static class TabLayoutOnPageChangeListener implements OnPageChangeListener
    {
        private int mPreviousScrollState;
        private int mScrollState;
        private final WeakReference<TabLayout> mTabLayoutRef;
        
        public TabLayoutOnPageChangeListener(final TabLayout tabLayout) {
            this.mTabLayoutRef = new WeakReference<TabLayout>(tabLayout);
        }
        
        @Override
        public void onPageScrollStateChanged(final int mScrollState) {
            this.mPreviousScrollState = this.mScrollState;
            this.mScrollState = mScrollState;
        }
        
        @Override
        public void onPageScrolled(final int n, final float n2, final int n3) {
            final boolean b = true;
            final TabLayout tabLayout = this.mTabLayoutRef.get();
            if (tabLayout != null) {
                boolean b2 = b;
                if (this.mScrollState != 1) {
                    b2 = (this.mScrollState == 2 && this.mPreviousScrollState == 1 && b);
                }
                tabLayout.setScrollPosition(n, n2, b2);
            }
        }
        
        @Override
        public void onPageSelected(final int n) {
            final TabLayout tabLayout = this.mTabLayoutRef.get();
            if (tabLayout != null && tabLayout.getSelectedTabPosition() != n) {
                tabLayout.selectTab(tabLayout.getTabAt(n), this.mScrollState == 0);
            }
        }
    }
    
    class TabView extends LinearLayout implements View$OnLongClickListener
    {
        private ImageView mCustomIconView;
        private TextView mCustomTextView;
        private View mCustomView;
        private int mDefaultMaxLines;
        private ImageView mIconView;
        private final Tab mTab;
        private TextView mTextView;
        
        public TabView(final Context context, final Tab mTab) {
            super(context);
            this.mDefaultMaxLines = 2;
            this.mTab = mTab;
            if (TabLayout.this.mTabBackgroundResId != 0) {
                this.setBackgroundDrawable(TintManager.getDrawable(context, TabLayout.this.mTabBackgroundResId));
            }
            ViewCompat.setPaddingRelative((View)this, TabLayout.this.mTabPaddingStart, TabLayout.this.mTabPaddingTop, TabLayout.this.mTabPaddingEnd, TabLayout.this.mTabPaddingBottom);
            this.setGravity(17);
            this.setOrientation(1);
            this.update();
        }
        
        private float approximateLineWidth(final Layout layout, final int n, final float n2) {
            return layout.getLineWidth(n) * (n2 / layout.getPaint().getTextSize());
        }
        
        private void updateTextAndIcon(final Tab tab, final TextView textView, final ImageView imageView) {
            final Drawable icon = tab.getIcon();
            final CharSequence text = tab.getText();
            if (imageView != null) {
                if (icon != null) {
                    imageView.setImageDrawable(icon);
                    imageView.setVisibility(0);
                    this.setVisibility(0);
                }
                else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable((Drawable)null);
                }
                imageView.setContentDescription(tab.getContentDescription());
            }
            boolean b;
            if (!TextUtils.isEmpty(text)) {
                b = true;
            }
            else {
                b = false;
            }
            if (textView != null) {
                if (b) {
                    textView.setText(text);
                    textView.setContentDescription(tab.getContentDescription());
                    textView.setVisibility(0);
                    this.setVisibility(0);
                }
                else {
                    textView.setVisibility(8);
                    textView.setText((CharSequence)null);
                }
            }
            if (imageView != null) {
                final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)imageView.getLayoutParams();
                int access$1400;
                final int n = access$1400 = 0;
                if (b) {
                    access$1400 = n;
                    if (imageView.getVisibility() == 0) {
                        access$1400 = TabLayout.this.dpToPx(8);
                    }
                }
                if (access$1400 != viewGroup$MarginLayoutParams.bottomMargin) {
                    viewGroup$MarginLayoutParams.bottomMargin = access$1400;
                    imageView.requestLayout();
                }
            }
            if (!b && !TextUtils.isEmpty(tab.getContentDescription())) {
                this.setOnLongClickListener((View$OnLongClickListener)this);
                return;
            }
            this.setOnLongClickListener((View$OnLongClickListener)null);
            this.setLongClickable(false);
        }
        
        public Tab getTab() {
            return this.mTab;
        }
        
        @TargetApi(14)
        public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName((CharSequence)ActionBar.Tab.class.getName());
        }
        
        @TargetApi(14)
        public void onInitializeAccessibilityNodeInfo(final AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName((CharSequence)ActionBar.Tab.class.getName());
        }
        
        public boolean onLongClick(final View view) {
            final int[] array = new int[2];
            this.getLocationOnScreen(array);
            final Context context = this.getContext();
            final int width = this.getWidth();
            final int height = this.getHeight();
            final int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
            final Toast text = Toast.makeText(context, this.mTab.getContentDescription(), 0);
            text.setGravity(49, array[0] + width / 2 - widthPixels / 2, height);
            text.show();
            return true;
        }
        
        public void onMeasure(final int n, final int n2) {
            final int size = View$MeasureSpec.getSize(n);
            final int access$700 = TabLayout.this.getTabMaxWidth();
            if (access$700 > 0 && (size == 0 || size > access$700)) {
                super.onMeasure(View$MeasureSpec.makeMeasureSpec(TabLayout.this.mTabMaxWidth, Integer.MIN_VALUE), n2);
            }
            else {
                super.onMeasure(n, n2);
            }
            if (this.mTextView != null) {
                this.getResources();
                final float access$701 = TabLayout.this.mTabTextSize;
                final int mDefaultMaxLines = this.mDefaultMaxLines;
                int maxLines;
                float access$702;
                if (this.mIconView != null && this.mIconView.getVisibility() == 0) {
                    maxLines = 1;
                    access$702 = access$701;
                }
                else {
                    maxLines = mDefaultMaxLines;
                    access$702 = access$701;
                    if (this.mTextView != null) {
                        maxLines = mDefaultMaxLines;
                        access$702 = access$701;
                        if (this.mTextView.getLineCount() > 1) {
                            access$702 = TabLayout.this.mTabTextMultiLineSize;
                            maxLines = mDefaultMaxLines;
                        }
                    }
                }
                final float textSize = this.mTextView.getTextSize();
                final int lineCount = this.mTextView.getLineCount();
                final int maxLines2 = TextViewCompat.getMaxLines(this.mTextView);
                if (access$702 != textSize || (maxLines2 >= 0 && maxLines != maxLines2)) {
                    boolean b2;
                    final boolean b = b2 = true;
                    Label_0223: {
                        if (TabLayout.this.mMode == 1) {
                            b2 = b;
                            if (access$702 > textSize) {
                                b2 = b;
                                if (lineCount == 1) {
                                    final Layout layout = this.mTextView.getLayout();
                                    if (layout != null) {
                                        b2 = b;
                                        if (this.approximateLineWidth(layout, 0, access$702) <= layout.getWidth()) {
                                            break Label_0223;
                                        }
                                    }
                                    b2 = false;
                                }
                            }
                        }
                    }
                    if (b2) {
                        this.mTextView.setTextSize(0, access$702);
                        this.mTextView.setMaxLines(maxLines);
                        super.onMeasure(n, n2);
                    }
                }
            }
        }
        
        public void setSelected(final boolean selected) {
            boolean b;
            if (this.isSelected() != selected) {
                b = true;
            }
            else {
                b = false;
            }
            super.setSelected(selected);
            if (b && selected) {
                this.sendAccessibilityEvent(4);
                if (this.mTextView != null) {
                    this.mTextView.setSelected(selected);
                }
                if (this.mIconView != null) {
                    this.mIconView.setSelected(selected);
                }
            }
        }
        
        final void update() {
            final Tab mTab = this.mTab;
            final View customView = mTab.getCustomView();
            if (customView != null) {
                final ViewParent parent = customView.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup)parent).removeView(customView);
                    }
                    this.addView(customView);
                }
                this.mCustomView = customView;
                if (this.mTextView != null) {
                    this.mTextView.setVisibility(8);
                }
                if (this.mIconView != null) {
                    this.mIconView.setVisibility(8);
                    this.mIconView.setImageDrawable((Drawable)null);
                }
                this.mCustomTextView = (TextView)customView.findViewById(16908308);
                if (this.mCustomTextView != null) {
                    this.mDefaultMaxLines = TextViewCompat.getMaxLines(this.mCustomTextView);
                }
                this.mCustomIconView = (ImageView)customView.findViewById(16908294);
            }
            else {
                if (this.mCustomView != null) {
                    this.removeView(this.mCustomView);
                    this.mCustomView = null;
                }
                this.mCustomTextView = null;
                this.mCustomIconView = null;
            }
            if (this.mCustomView == null) {
                if (this.mIconView == null) {
                    final ImageView mIconView = (ImageView)LayoutInflater.from(this.getContext()).inflate(R.layout.design_layout_tab_icon, (ViewGroup)this, false);
                    this.addView((View)mIconView, 0);
                    this.mIconView = mIconView;
                }
                if (this.mTextView == null) {
                    final TextView mTextView = (TextView)LayoutInflater.from(this.getContext()).inflate(R.layout.design_layout_tab_text, (ViewGroup)this, false);
                    this.addView((View)mTextView);
                    this.mTextView = mTextView;
                    this.mDefaultMaxLines = TextViewCompat.getMaxLines(this.mTextView);
                }
                this.mTextView.setTextAppearance(this.getContext(), TabLayout.this.mTabTextAppearance);
                if (TabLayout.this.mTabTextColors != null) {
                    this.mTextView.setTextColor(TabLayout.this.mTabTextColors);
                }
                this.updateTextAndIcon(mTab, this.mTextView, this.mIconView);
            }
            else if (this.mCustomTextView != null || this.mCustomIconView != null) {
                this.updateTextAndIcon(mTab, this.mCustomTextView, this.mCustomIconView);
            }
        }
    }
    
    public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener
    {
        private final ViewPager mViewPager;
        
        public ViewPagerOnTabSelectedListener(final ViewPager mViewPager) {
            this.mViewPager = mViewPager;
        }
        
        @Override
        public void onTabReselected(final Tab tab) {
        }
        
        @Override
        public void onTabSelected(final Tab tab) {
            this.mViewPager.setCurrentItem(tab.getPosition());
        }
        
        @Override
        public void onTabUnselected(final Tab tab) {
        }
    }
}
