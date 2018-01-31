// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.ViewGroup$MarginLayoutParams;
import android.support.v4.content.ContextCompat;
import android.support.annotation.DrawableRes;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable$Callback;
import android.support.annotation.ColorInt;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.graphics.Typeface;
import android.widget.FrameLayout$LayoutParams;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewParent;
import android.view.ViewGroup;
import android.content.res.TypedArray;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.design.R;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.graphics.Rect;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;

public class CollapsingToolbarLayout extends FrameLayout
{
    private static final int SCRIM_ANIMATION_DURATION = 600;
    private final CollapsingTextHelper mCollapsingTextHelper;
    private boolean mCollapsingTitleEnabled;
    private Drawable mContentScrim;
    private int mCurrentOffset;
    private boolean mDrawCollapsingTitle;
    private View mDummyView;
    private int mExpandedMarginBottom;
    private int mExpandedMarginLeft;
    private int mExpandedMarginRight;
    private int mExpandedMarginTop;
    private WindowInsetsCompat mLastInsets;
    private AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener;
    private boolean mRefreshToolbar;
    private int mScrimAlpha;
    private ValueAnimatorCompat mScrimAnimator;
    private boolean mScrimsAreShown;
    private Drawable mStatusBarScrim;
    private final Rect mTmpRect;
    private Toolbar mToolbar;
    private int mToolbarId;
    
    public CollapsingToolbarLayout(final Context context) {
        this(context, null);
    }
    
    public CollapsingToolbarLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public CollapsingToolbarLayout(final Context context, final AttributeSet set, int dimensionPixelSize) {
        super(context, set, dimensionPixelSize);
        this.mRefreshToolbar = true;
        this.mTmpRect = new Rect();
        ThemeUtils.checkAppCompatTheme(context);
        (this.mCollapsingTextHelper = new CollapsingTextHelper((View)this)).setTextSizeInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.CollapsingToolbarLayout, dimensionPixelSize, R.style.Widget_Design_CollapsingToolbar);
        this.mCollapsingTextHelper.setExpandedTextGravity(obtainStyledAttributes.getInt(R.styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
        this.mCollapsingTextHelper.setCollapsedTextGravity(obtainStyledAttributes.getInt(R.styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.mExpandedMarginBottom = dimensionPixelSize;
        this.mExpandedMarginRight = dimensionPixelSize;
        this.mExpandedMarginTop = dimensionPixelSize;
        this.mExpandedMarginLeft = dimensionPixelSize;
        if (ViewCompat.getLayoutDirection((View)this) == 1) {
            dimensionPixelSize = 1;
        }
        else {
            dimensionPixelSize = 0;
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart)) {
            final int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
            if (dimensionPixelSize != 0) {
                this.mExpandedMarginRight = dimensionPixelSize2;
            }
            else {
                this.mExpandedMarginLeft = dimensionPixelSize2;
            }
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd)) {
            final int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
            if (dimensionPixelSize != 0) {
                this.mExpandedMarginLeft = dimensionPixelSize3;
            }
            else {
                this.mExpandedMarginRight = dimensionPixelSize3;
            }
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop)) {
            this.mExpandedMarginTop = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom)) {
            this.mExpandedMarginBottom = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
        }
        this.mCollapsingTitleEnabled = obtainStyledAttributes.getBoolean(R.styleable.CollapsingToolbarLayout_titleEnabled, true);
        this.setTitle(obtainStyledAttributes.getText(R.styleable.CollapsingToolbarLayout_title));
        this.mCollapsingTextHelper.setExpandedTextAppearance(R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
        this.mCollapsingTextHelper.setCollapsedTextAppearance(R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        if (obtainStyledAttributes.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance)) {
            this.mCollapsingTextHelper.setExpandedTextAppearance(obtainStyledAttributes.getResourceId(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance)) {
            this.mCollapsingTextHelper.setCollapsedTextAppearance(obtainStyledAttributes.getResourceId(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
        }
        this.setContentScrim(obtainStyledAttributes.getDrawable(R.styleable.CollapsingToolbarLayout_contentScrim));
        this.setStatusBarScrim(obtainStyledAttributes.getDrawable(R.styleable.CollapsingToolbarLayout_statusBarScrim));
        this.mToolbarId = obtainStyledAttributes.getResourceId(R.styleable.CollapsingToolbarLayout_toolbarId, -1);
        obtainStyledAttributes.recycle();
        this.setWillNotDraw(false);
        ViewCompat.setOnApplyWindowInsetsListener((View)this, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
                CollapsingToolbarLayout.this.mLastInsets = windowInsetsCompat;
                CollapsingToolbarLayout.this.requestLayout();
                return windowInsetsCompat.consumeSystemWindowInsets();
            }
        });
    }
    
    private void animateScrim(final int n) {
        this.ensureToolbar();
        if (this.mScrimAnimator == null) {
            (this.mScrimAnimator = ViewUtils.createAnimator()).setDuration(600);
            this.mScrimAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.mScrimAnimator.setUpdateListener((ValueAnimatorCompat.AnimatorUpdateListener)new ValueAnimatorCompat.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
                    CollapsingToolbarLayout.this.setScrimAlpha(valueAnimatorCompat.getAnimatedIntValue());
                }
            });
        }
        else if (this.mScrimAnimator.isRunning()) {
            this.mScrimAnimator.cancel();
        }
        this.mScrimAnimator.setIntValues(this.mScrimAlpha, n);
        this.mScrimAnimator.start();
    }
    
    private void ensureToolbar() {
        if (!this.mRefreshToolbar) {
            return;
        }
        Toolbar toolbar = null;
        final Toolbar toolbar2 = null;
        int n = 0;
        final int childCount = this.getChildCount();
        while (true) {
            View child = null;
            Label_0124: {
                Toolbar toolbar3;
                while (true) {
                    toolbar3 = toolbar2;
                    if (n >= childCount) {
                        break;
                    }
                    child = this.getChildAt(n);
                    Toolbar toolbar4 = toolbar;
                    if (child instanceof Toolbar) {
                        if (this.mToolbarId == -1) {
                            break Label_0124;
                        }
                        if (this.mToolbarId == child.getId()) {
                            toolbar3 = (Toolbar)child;
                            break;
                        }
                        if ((toolbar4 = toolbar) == null) {
                            toolbar4 = (Toolbar)child;
                        }
                    }
                    ++n;
                    toolbar = toolbar4;
                }
                Toolbar mToolbar;
                if ((mToolbar = toolbar3) == null) {
                    mToolbar = toolbar;
                }
                this.mToolbar = mToolbar;
                this.updateDummyView();
                this.mRefreshToolbar = false;
                return;
            }
            Toolbar toolbar3 = (Toolbar)child;
            continue;
        }
    }
    
    private static ViewOffsetHelper getViewOffsetHelper(final View view) {
        ViewOffsetHelper viewOffsetHelper;
        if ((viewOffsetHelper = (ViewOffsetHelper)view.getTag(R.id.view_offset_helper)) == null) {
            viewOffsetHelper = new ViewOffsetHelper(view);
            view.setTag(R.id.view_offset_helper, (Object)viewOffsetHelper);
        }
        return viewOffsetHelper;
    }
    
    private void setScrimAlpha(final int mScrimAlpha) {
        if (mScrimAlpha != this.mScrimAlpha) {
            if (this.mContentScrim != null && this.mToolbar != null) {
                ViewCompat.postInvalidateOnAnimation((View)this.mToolbar);
            }
            this.mScrimAlpha = mScrimAlpha;
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    private void updateDummyView() {
        if (!this.mCollapsingTitleEnabled && this.mDummyView != null) {
            final ViewParent parent = this.mDummyView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup)parent).removeView(this.mDummyView);
            }
        }
        if (this.mCollapsingTitleEnabled && this.mToolbar != null) {
            if (this.mDummyView == null) {
                this.mDummyView = new View(this.getContext());
            }
            if (this.mDummyView.getParent() == null) {
                this.mToolbar.addView(this.mDummyView, -1, -1);
            }
        }
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof LayoutParams;
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        this.ensureToolbar();
        if (this.mToolbar == null && this.mContentScrim != null && this.mScrimAlpha > 0) {
            this.mContentScrim.mutate().setAlpha(this.mScrimAlpha);
            this.mContentScrim.draw(canvas);
        }
        if (this.mCollapsingTitleEnabled && this.mDrawCollapsingTitle) {
            this.mCollapsingTextHelper.draw(canvas);
        }
        if (this.mStatusBarScrim != null && this.mScrimAlpha > 0) {
            int systemWindowInsetTop;
            if (this.mLastInsets != null) {
                systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
            }
            else {
                systemWindowInsetTop = 0;
            }
            if (systemWindowInsetTop > 0) {
                this.mStatusBarScrim.setBounds(0, -this.mCurrentOffset, this.getWidth(), systemWindowInsetTop - this.mCurrentOffset);
                this.mStatusBarScrim.mutate().setAlpha(this.mScrimAlpha);
                this.mStatusBarScrim.draw(canvas);
            }
        }
    }
    
    protected boolean drawChild(final Canvas canvas, final View view, final long n) {
        this.ensureToolbar();
        if (view == this.mToolbar && this.mContentScrim != null && this.mScrimAlpha > 0) {
            this.mContentScrim.mutate().setAlpha(this.mScrimAlpha);
            this.mContentScrim.draw(canvas);
        }
        return super.drawChild(canvas, view, n);
    }
    
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(super.generateDefaultLayoutParams());
    }
    
    public FrameLayout$LayoutParams generateLayoutParams(final AttributeSet set) {
        return new LayoutParams(this.getContext(), set);
    }
    
    protected FrameLayout$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return new LayoutParams(viewGroup$LayoutParams);
    }
    
    public int getCollapsedTitleGravity() {
        return this.mCollapsingTextHelper.getCollapsedTextGravity();
    }
    
    @NonNull
    public Typeface getCollapsedTitleTypeface() {
        return this.mCollapsingTextHelper.getCollapsedTypeface();
    }
    
    public Drawable getContentScrim() {
        return this.mContentScrim;
    }
    
    public int getExpandedTitleGravity() {
        return this.mCollapsingTextHelper.getExpandedTextGravity();
    }
    
    @NonNull
    public Typeface getExpandedTitleTypeface() {
        return this.mCollapsingTextHelper.getExpandedTypeface();
    }
    
    final int getScrimTriggerOffset() {
        return ViewCompat.getMinimumHeight((View)this) * 2;
    }
    
    public Drawable getStatusBarScrim() {
        return this.mStatusBarScrim;
    }
    
    @Nullable
    public CharSequence getTitle() {
        if (this.mCollapsingTitleEnabled) {
            return this.mCollapsingTextHelper.getText();
        }
        return null;
    }
    
    public boolean isTitleEnabled() {
        return this.mCollapsingTitleEnabled;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        final ViewParent parent = this.getParent();
        if (parent instanceof AppBarLayout) {
            if (this.mOnOffsetChangedListener == null) {
                this.mOnOffsetChangedListener = new OffsetUpdateListener();
            }
            ((AppBarLayout)parent).addOnOffsetChangedListener(this.mOnOffsetChangedListener);
        }
    }
    
    protected void onDetachedFromWindow() {
        final ViewParent parent = this.getParent();
        if (this.mOnOffsetChangedListener != null && parent instanceof AppBarLayout) {
            ((AppBarLayout)parent).removeOnOffsetChangedListener(this.mOnOffsetChangedListener);
        }
        super.onDetachedFromWindow();
    }
    
    protected void onLayout(final boolean b, int i, int childCount, int systemWindowInsetTop, final int n) {
        super.onLayout(b, i, childCount, systemWindowInsetTop, n);
        if (this.mCollapsingTitleEnabled && this.mDummyView != null) {
            this.mDrawCollapsingTitle = this.mDummyView.isShown();
            if (this.mDrawCollapsingTitle) {
                ViewGroupUtils.getDescendantRect((ViewGroup)this, this.mDummyView, this.mTmpRect);
                this.mCollapsingTextHelper.setCollapsedBounds(this.mTmpRect.left, n - this.mTmpRect.height(), this.mTmpRect.right, n);
                this.mCollapsingTextHelper.setExpandedBounds(this.mExpandedMarginLeft, this.mTmpRect.bottom + this.mExpandedMarginTop, systemWindowInsetTop - i - this.mExpandedMarginRight, n - childCount - this.mExpandedMarginBottom);
                this.mCollapsingTextHelper.recalculate();
            }
        }
        View child;
        for (i = 0, childCount = this.getChildCount(); i < childCount; ++i) {
            child = this.getChildAt(i);
            if (this.mLastInsets != null && !ViewCompat.getFitsSystemWindows(child)) {
                systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
                if (child.getTop() < systemWindowInsetTop) {
                    child.offsetTopAndBottom(systemWindowInsetTop);
                }
            }
            getViewOffsetHelper(child).onViewLayout();
        }
        if (this.mToolbar != null) {
            if (this.mCollapsingTitleEnabled && TextUtils.isEmpty(this.mCollapsingTextHelper.getText())) {
                this.mCollapsingTextHelper.setText(this.mToolbar.getTitle());
            }
            this.setMinimumHeight(this.mToolbar.getHeight());
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.ensureToolbar();
        super.onMeasure(n, n2);
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (this.mContentScrim != null) {
            this.mContentScrim.setBounds(0, 0, n, n2);
        }
    }
    
    public void setCollapsedTitleGravity(final int expandedTextGravity) {
        this.mCollapsingTextHelper.setExpandedTextGravity(expandedTextGravity);
    }
    
    public void setCollapsedTitleTextAppearance(@StyleRes final int collapsedTextAppearance) {
        this.mCollapsingTextHelper.setCollapsedTextAppearance(collapsedTextAppearance);
    }
    
    public void setCollapsedTitleTextColor(@ColorInt final int collapsedTextColor) {
        this.mCollapsingTextHelper.setCollapsedTextColor(collapsedTextColor);
    }
    
    public void setCollapsedTitleTypeface(@Nullable final Typeface collapsedTypeface) {
        this.mCollapsingTextHelper.setCollapsedTypeface(collapsedTypeface);
    }
    
    public void setContentScrim(@Nullable final Drawable drawable) {
        if (this.mContentScrim != drawable) {
            if (this.mContentScrim != null) {
                this.mContentScrim.setCallback((Drawable$Callback)null);
            }
            if (drawable != null) {
                this.mContentScrim = drawable.mutate();
                drawable.setBounds(0, 0, this.getWidth(), this.getHeight());
                drawable.setCallback((Drawable$Callback)this);
                drawable.setAlpha(this.mScrimAlpha);
            }
            else {
                this.mContentScrim = null;
            }
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    public void setContentScrimColor(@ColorInt final int n) {
        this.setContentScrim((Drawable)new ColorDrawable(n));
    }
    
    public void setContentScrimResource(@DrawableRes final int n) {
        this.setContentScrim(ContextCompat.getDrawable(this.getContext(), n));
    }
    
    public void setExpandedTitleColor(@ColorInt final int expandedTextColor) {
        this.mCollapsingTextHelper.setExpandedTextColor(expandedTextColor);
    }
    
    public void setExpandedTitleGravity(final int expandedTextGravity) {
        this.mCollapsingTextHelper.setExpandedTextGravity(expandedTextGravity);
    }
    
    public void setExpandedTitleTextAppearance(@StyleRes final int expandedTextAppearance) {
        this.mCollapsingTextHelper.setExpandedTextAppearance(expandedTextAppearance);
    }
    
    public void setExpandedTitleTypeface(@Nullable final Typeface expandedTypeface) {
        this.mCollapsingTextHelper.setExpandedTypeface(expandedTypeface);
    }
    
    public void setScrimsShown(final boolean b) {
        this.setScrimsShown(b, ViewCompat.isLaidOut((View)this) && !this.isInEditMode());
    }
    
    public void setScrimsShown(final boolean mScrimsAreShown, final boolean b) {
        int scrimAlpha = 255;
        if (this.mScrimsAreShown != mScrimsAreShown) {
            if (b) {
                if (!mScrimsAreShown) {
                    scrimAlpha = 0;
                }
                this.animateScrim(scrimAlpha);
            }
            else {
                if (!mScrimsAreShown) {
                    scrimAlpha = 0;
                }
                this.setScrimAlpha(scrimAlpha);
            }
            this.mScrimsAreShown = mScrimsAreShown;
        }
    }
    
    public void setStatusBarScrim(@Nullable final Drawable mStatusBarScrim) {
        if (this.mStatusBarScrim != mStatusBarScrim) {
            if (this.mStatusBarScrim != null) {
                this.mStatusBarScrim.setCallback((Drawable$Callback)null);
            }
            (this.mStatusBarScrim = mStatusBarScrim).setCallback((Drawable$Callback)this);
            mStatusBarScrim.mutate().setAlpha(this.mScrimAlpha);
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    public void setStatusBarScrimColor(@ColorInt final int n) {
        this.setStatusBarScrim((Drawable)new ColorDrawable(n));
    }
    
    public void setStatusBarScrimResource(@DrawableRes final int n) {
        this.setStatusBarScrim(ContextCompat.getDrawable(this.getContext(), n));
    }
    
    public void setTitle(@Nullable final CharSequence text) {
        this.mCollapsingTextHelper.setText(text);
    }
    
    public void setTitleEnabled(final boolean mCollapsingTitleEnabled) {
        if (mCollapsingTitleEnabled != this.mCollapsingTitleEnabled) {
            this.mCollapsingTitleEnabled = mCollapsingTitleEnabled;
            this.updateDummyView();
            this.requestLayout();
        }
    }
    
    public static class LayoutParams extends FrameLayout$LayoutParams
    {
        public static final int COLLAPSE_MODE_OFF = 0;
        public static final int COLLAPSE_MODE_PARALLAX = 2;
        public static final int COLLAPSE_MODE_PIN = 1;
        private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5f;
        int mCollapseMode;
        float mParallaxMult;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.mCollapseMode = 0;
            this.mParallaxMult = 0.5f;
        }
        
        public LayoutParams(final int n, final int n2, final int n3) {
            super(n, n2, n3);
            this.mCollapseMode = 0;
            this.mParallaxMult = 0.5f;
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.mCollapseMode = 0;
            this.mParallaxMult = 0.5f;
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.CollapsingAppBarLayout_LayoutParams);
            this.mCollapseMode = obtainStyledAttributes.getInt(R.styleable.CollapsingAppBarLayout_LayoutParams_layout_collapseMode, 0);
            this.setParallaxMultiplier(obtainStyledAttributes.getFloat(R.styleable.CollapsingAppBarLayout_LayoutParams_layout_collapseParallaxMultiplier, 0.5f));
            obtainStyledAttributes.recycle();
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.mCollapseMode = 0;
            this.mParallaxMult = 0.5f;
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
            this.mCollapseMode = 0;
            this.mParallaxMult = 0.5f;
        }
        
        public LayoutParams(final FrameLayout$LayoutParams frameLayout$LayoutParams) {
            super(frameLayout$LayoutParams);
            this.mCollapseMode = 0;
            this.mParallaxMult = 0.5f;
        }
        
        public int getCollapseMode() {
            return this.mCollapseMode;
        }
        
        public float getParallaxMultiplier() {
            return this.mParallaxMult;
        }
        
        public void setCollapseMode(final int mCollapseMode) {
            this.mCollapseMode = mCollapseMode;
        }
        
        public void setParallaxMultiplier(final float mParallaxMult) {
            this.mParallaxMult = mParallaxMult;
        }
    }
    
    private class OffsetUpdateListener implements OnOffsetChangedListener
    {
        @Override
        public void onOffsetChanged(final AppBarLayout appBarLayout, final int n) {
            boolean scrimsShown = false;
            CollapsingToolbarLayout.this.mCurrentOffset = n;
            int systemWindowInsetTop;
            if (CollapsingToolbarLayout.this.mLastInsets != null) {
                systemWindowInsetTop = CollapsingToolbarLayout.this.mLastInsets.getSystemWindowInsetTop();
            }
            else {
                systemWindowInsetTop = 0;
            }
            final int totalScrollRange = appBarLayout.getTotalScrollRange();
            for (int i = 0; i < CollapsingToolbarLayout.this.getChildCount(); ++i) {
                final View child = CollapsingToolbarLayout.this.getChildAt(i);
                final CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams)child.getLayoutParams();
                final ViewOffsetHelper access$400 = getViewOffsetHelper(child);
                switch (layoutParams.mCollapseMode) {
                    case 1: {
                        if (CollapsingToolbarLayout.this.getHeight() - systemWindowInsetTop + n >= child.getHeight()) {
                            access$400.setTopAndBottomOffset(-n);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        access$400.setTopAndBottomOffset(Math.round(-n * layoutParams.mParallaxMult));
                        break;
                    }
                }
            }
            if (CollapsingToolbarLayout.this.mContentScrim != null || CollapsingToolbarLayout.this.mStatusBarScrim != null) {
                final CollapsingToolbarLayout this$0 = CollapsingToolbarLayout.this;
                if (CollapsingToolbarLayout.this.getHeight() + n < CollapsingToolbarLayout.this.getScrimTriggerOffset() + systemWindowInsetTop) {
                    scrimsShown = true;
                }
                this$0.setScrimsShown(scrimsShown);
            }
            if (CollapsingToolbarLayout.this.mStatusBarScrim != null && systemWindowInsetTop > 0) {
                ViewCompat.postInvalidateOnAnimation((View)CollapsingToolbarLayout.this);
            }
            CollapsingToolbarLayout.this.mCollapsingTextHelper.setExpansionFraction(Math.abs(n) / (CollapsingToolbarLayout.this.getHeight() - ViewCompat.getMinimumHeight((View)CollapsingToolbarLayout.this) - systemWindowInsetTop));
            if (Math.abs(n) == totalScrollRange) {
                ViewCompat.setElevation((View)appBarLayout, appBarLayout.getTargetElevation());
                return;
            }
            ViewCompat.setElevation((View)appBarLayout, 0.0f);
        }
    }
}
