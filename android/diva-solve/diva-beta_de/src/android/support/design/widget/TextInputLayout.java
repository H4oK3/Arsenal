// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.accessibility.AccessibilityEvent;
import android.support.annotation.StyleRes;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.graphics.Canvas;
import android.support.v7.internal.widget.TintManager;
import android.text.TextUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.content.res.TypedArray;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.design.R;
import android.view.animation.Interpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Paint;
import android.widget.EditText;
import android.content.res.ColorStateList;
import android.widget.TextView;
import android.widget.LinearLayout;

public class TextInputLayout extends LinearLayout
{
    private static final int ANIMATION_DURATION = 200;
    private static final int INVALID_MAX_LENGTH = -1;
    private ValueAnimatorCompat mAnimator;
    private final CollapsingTextHelper mCollapsingTextHelper;
    private boolean mCounterEnabled;
    private int mCounterMaxLength;
    private int mCounterOverflowTextAppearance;
    private boolean mCounterOverflowed;
    private int mCounterTextAppearance;
    private TextView mCounterView;
    private ColorStateList mDefaultTextColor;
    private EditText mEditText;
    private boolean mErrorEnabled;
    private boolean mErrorShown;
    private int mErrorTextAppearance;
    private TextView mErrorView;
    private ColorStateList mFocusedTextColor;
    private CharSequence mHint;
    private boolean mHintAnimationEnabled;
    private LinearLayout mIndicatorArea;
    private Paint mTmpPaint;
    
    public TextInputLayout(final Context context) {
        this(context, null);
    }
    
    public TextInputLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public TextInputLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set);
        this.mCollapsingTextHelper = new CollapsingTextHelper((View)this);
        ThemeUtils.checkAppCompatTheme(context);
        this.setOrientation(1);
        this.setWillNotDraw(false);
        this.setAddStatesFromChildren(true);
        this.mCollapsingTextHelper.setTextSizeInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        this.mCollapsingTextHelper.setPositionInterpolator((Interpolator)new AccelerateInterpolator());
        this.mCollapsingTextHelper.setCollapsedTextGravity(8388659);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.TextInputLayout, n, R.style.Widget_Design_TextInputLayout);
        this.setHint(obtainStyledAttributes.getText(R.styleable.TextInputLayout_android_hint));
        this.mHintAnimationEnabled = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_hintAnimationEnabled, true);
        if (obtainStyledAttributes.hasValue(R.styleable.TextInputLayout_android_textColorHint)) {
            final ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.TextInputLayout_android_textColorHint);
            this.mFocusedTextColor = colorStateList;
            this.mDefaultTextColor = colorStateList;
        }
        if (obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
            this.setHintTextAppearance(obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, 0));
        }
        this.mErrorTextAppearance = obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, 0);
        final boolean boolean1 = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_errorEnabled, false);
        final boolean boolean2 = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_counterEnabled, false);
        this.setCounterMaxLength(obtainStyledAttributes.getInt(R.styleable.TextInputLayout_counterMaxLength, -1));
        this.mCounterTextAppearance = obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, 0);
        this.mCounterOverflowTextAppearance = obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        obtainStyledAttributes.recycle();
        this.setErrorEnabled(boolean1);
        this.setCounterEnabled(boolean2);
        if (ViewCompat.getImportantForAccessibility((View)this) == 0) {
            ViewCompat.setImportantForAccessibility((View)this, 1);
        }
        ViewCompat.setAccessibilityDelegate((View)this, new TextInputAccessibilityDelegate());
    }
    
    private void addIndicator(final TextView textView, final int n, final LinearLayout$LayoutParams linearLayout$LayoutParams) {
        if (this.mIndicatorArea == null) {
            (this.mIndicatorArea = new LinearLayout(this.getContext())).setOrientation(0);
            this.addView((View)this.mIndicatorArea);
            if (this.mEditText != null) {
                this.adjustIndicatorPadding();
            }
        }
        this.mIndicatorArea.addView((View)textView, n, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
    }
    
    private void adjustIndicatorPadding() {
        ViewCompat.setPaddingRelative((View)this.mIndicatorArea, ViewCompat.getPaddingStart((View)this.mEditText), 0, ViewCompat.getPaddingEnd((View)this.mEditText), this.mEditText.getPaddingBottom());
    }
    
    private void animateToExpansionFraction(final float n) {
        if (this.mCollapsingTextHelper.getExpansionFraction() == n) {
            return;
        }
        if (this.mAnimator == null) {
            (this.mAnimator = ViewUtils.createAnimator()).setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
            this.mAnimator.setDuration(200);
            this.mAnimator.setUpdateListener((ValueAnimatorCompat.AnimatorUpdateListener)new ValueAnimatorCompat.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
                    TextInputLayout.this.mCollapsingTextHelper.setExpansionFraction(valueAnimatorCompat.getAnimatedFloatValue());
                }
            });
        }
        this.mAnimator.setFloatValues(this.mCollapsingTextHelper.getExpansionFraction(), n);
        this.mAnimator.start();
    }
    
    private static boolean arrayContains(final int[] array, final int n) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (array[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    private void collapseHint(final boolean b) {
        if (this.mAnimator != null && this.mAnimator.isRunning()) {
            this.mAnimator.cancel();
        }
        if (b && this.mHintAnimationEnabled) {
            this.animateToExpansionFraction(1.0f);
            return;
        }
        this.mCollapsingTextHelper.setExpansionFraction(1.0f);
    }
    
    private void expandHint(final boolean b) {
        if (this.mAnimator != null && this.mAnimator.isRunning()) {
            this.mAnimator.cancel();
        }
        if (b && this.mHintAnimationEnabled) {
            this.animateToExpansionFraction(0.0f);
            return;
        }
        this.mCollapsingTextHelper.setExpansionFraction(0.0f);
    }
    
    private int getThemeAttrColor(final int n) {
        final TypedValue typedValue = new TypedValue();
        if (this.getContext().getTheme().resolveAttribute(n, typedValue, true)) {
            return typedValue.data;
        }
        return -65281;
    }
    
    private void removeIndicator(final TextView textView) {
        this.mIndicatorArea.removeView((View)textView);
        if (this.mIndicatorArea.getChildCount() == 0) {
            this.removeView((View)this.mIndicatorArea);
        }
    }
    
    private void setEditText(final EditText mEditText) {
        if (this.mEditText != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        this.mEditText = mEditText;
        this.mCollapsingTextHelper.setTypefaces(this.mEditText.getTypeface());
        this.mCollapsingTextHelper.setExpandedTextSize(this.mEditText.getTextSize());
        this.mCollapsingTextHelper.setExpandedTextGravity(this.mEditText.getGravity());
        this.mEditText.addTextChangedListener((TextWatcher)new TextWatcher() {
            public void afterTextChanged(final Editable editable) {
                TextInputLayout.this.updateLabelVisibility(true);
                if (TextInputLayout.this.mCounterEnabled) {
                    TextInputLayout.this.updateCounter(editable.length());
                }
            }
            
            public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
            }
            
            public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
            }
        });
        if (this.mDefaultTextColor == null) {
            this.mDefaultTextColor = this.mEditText.getHintTextColors();
        }
        if (TextUtils.isEmpty(this.mHint)) {
            this.setHint(this.mEditText.getHint());
            this.mEditText.setHint((CharSequence)null);
        }
        if (this.mCounterView != null) {
            this.updateCounter(this.mEditText.getText().length());
        }
        if (this.mIndicatorArea != null) {
            this.adjustIndicatorPadding();
        }
        this.updateLabelVisibility(false);
    }
    
    private void updateCounter(final int n) {
        final boolean mCounterOverflowed = this.mCounterOverflowed;
        if (this.mCounterMaxLength == -1) {
            this.mCounterView.setText((CharSequence)String.valueOf(n));
            this.mCounterOverflowed = false;
        }
        else {
            this.mCounterOverflowed = (n > this.mCounterMaxLength);
            if (mCounterOverflowed != this.mCounterOverflowed) {
                final TextView mCounterView = this.mCounterView;
                final Context context = this.getContext();
                int n2;
                if (this.mCounterOverflowed) {
                    n2 = this.mCounterOverflowTextAppearance;
                }
                else {
                    n2 = this.mCounterTextAppearance;
                }
                mCounterView.setTextAppearance(context, n2);
            }
            this.mCounterView.setText((CharSequence)this.getContext().getString(R.string.character_counter_pattern, new Object[] { n, this.mCounterMaxLength }));
        }
        if (this.mEditText != null && mCounterOverflowed != this.mCounterOverflowed) {
            this.updateEditTextBackground();
        }
    }
    
    private void updateEditTextBackground() {
        if (this.mErrorShown && this.mErrorView != null) {
            ViewCompat.setBackgroundTintList((View)this.mEditText, ColorStateList.valueOf(this.mErrorView.getCurrentTextColor()));
            return;
        }
        if (this.mCounterOverflowed && this.mCounterView != null) {
            ViewCompat.setBackgroundTintList((View)this.mEditText, ColorStateList.valueOf(this.mCounterView.getCurrentTextColor()));
            return;
        }
        ViewCompat.setBackgroundTintList((View)this.mEditText, TintManager.get(this.getContext()).getTintList(R.drawable.abc_edit_text_material));
    }
    
    private LinearLayout$LayoutParams updateEditTextMargin(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        LinearLayout$LayoutParams linearLayout$LayoutParams;
        if (viewGroup$LayoutParams instanceof LinearLayout$LayoutParams) {
            linearLayout$LayoutParams = (LinearLayout$LayoutParams)viewGroup$LayoutParams;
        }
        else {
            linearLayout$LayoutParams = new LinearLayout$LayoutParams(viewGroup$LayoutParams);
        }
        if (this.mTmpPaint == null) {
            this.mTmpPaint = new Paint();
        }
        this.mTmpPaint.setTypeface(this.mCollapsingTextHelper.getCollapsedTypeface());
        this.mTmpPaint.setTextSize(this.mCollapsingTextHelper.getCollapsedTextSize());
        linearLayout$LayoutParams.topMargin = (int)(-this.mTmpPaint.ascent());
        return linearLayout$LayoutParams;
    }
    
    private void updateLabelVisibility(final boolean b) {
        boolean b2;
        if (this.mEditText != null && !TextUtils.isEmpty((CharSequence)this.mEditText.getText())) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final boolean arrayContains = arrayContains(this.getDrawableState(), 16842908);
        boolean b3;
        if (!TextUtils.isEmpty(this.getError())) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        if (this.mDefaultTextColor != null && this.mFocusedTextColor != null) {
            this.mCollapsingTextHelper.setExpandedTextColor(this.mDefaultTextColor.getDefaultColor());
            final CollapsingTextHelper mCollapsingTextHelper = this.mCollapsingTextHelper;
            int collapsedTextColor;
            if (arrayContains) {
                collapsedTextColor = this.mFocusedTextColor.getDefaultColor();
            }
            else {
                collapsedTextColor = this.mDefaultTextColor.getDefaultColor();
            }
            mCollapsingTextHelper.setCollapsedTextColor(collapsedTextColor);
        }
        if (b2 || arrayContains || b3) {
            this.collapseHint(b);
            return;
        }
        this.expandHint(b);
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (view instanceof EditText) {
            this.setEditText((EditText)view);
            super.addView(view, 0, (ViewGroup$LayoutParams)this.updateEditTextMargin(viewGroup$LayoutParams));
            return;
        }
        super.addView(view, n, viewGroup$LayoutParams);
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        this.mCollapsingTextHelper.draw(canvas);
    }
    
    public int getCounterMaxLength() {
        return this.mCounterMaxLength;
    }
    
    @Nullable
    public EditText getEditText() {
        return this.mEditText;
    }
    
    @Nullable
    public CharSequence getError() {
        if (this.mErrorEnabled && this.mErrorView != null && this.mErrorView.getVisibility() == 0) {
            return this.mErrorView.getText();
        }
        return null;
    }
    
    @Nullable
    public CharSequence getHint() {
        return this.mHint;
    }
    
    @NonNull
    public Typeface getTypeface() {
        return this.mCollapsingTextHelper.getCollapsedTypeface();
    }
    
    public boolean isErrorEnabled() {
        return this.mErrorEnabled;
    }
    
    public boolean isHintAnimationEnabled() {
        return this.mHintAnimationEnabled;
    }
    
    protected void onLayout(final boolean b, int n, final int n2, int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        if (this.mEditText != null) {
            n = this.mEditText.getLeft() + this.mEditText.getCompoundPaddingLeft();
            n3 = this.mEditText.getRight() - this.mEditText.getCompoundPaddingRight();
            this.mCollapsingTextHelper.setExpandedBounds(n, this.mEditText.getTop() + this.mEditText.getCompoundPaddingTop(), n3, this.mEditText.getBottom() - this.mEditText.getCompoundPaddingBottom());
            this.mCollapsingTextHelper.setCollapsedBounds(n, this.getPaddingTop(), n3, n4 - n2 - this.getPaddingBottom());
            this.mCollapsingTextHelper.recalculate();
        }
    }
    
    public void refreshDrawableState() {
        super.refreshDrawableState();
        this.updateLabelVisibility(ViewCompat.isLaidOut((View)this));
    }
    
    public void setCounterEnabled(final boolean mCounterEnabled) {
        if (this.mCounterEnabled != mCounterEnabled) {
            if (mCounterEnabled) {
                (this.mCounterView = new TextView(this.getContext())).setMaxLines(1);
                this.mCounterView.setTextAppearance(this.getContext(), this.mCounterTextAppearance);
                final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -2);
                linearLayout$LayoutParams.gravity = ((linearLayout$LayoutParams.gravity & 0x70) | 0x800005);
                ViewCompat.setAccessibilityLiveRegion((View)this.mCounterView, 1);
                this.addIndicator(this.mCounterView, -1, linearLayout$LayoutParams);
                if (this.mEditText == null) {
                    this.updateCounter(0);
                }
                else {
                    this.updateCounter(this.mEditText.getText().length());
                }
            }
            else {
                this.removeIndicator(this.mCounterView);
                this.mCounterView = null;
            }
            this.mCounterEnabled = mCounterEnabled;
        }
    }
    
    public void setCounterMaxLength(int length) {
        if (this.mCounterMaxLength != length) {
            if (length > 0) {
                this.mCounterMaxLength = length;
            }
            else {
                this.mCounterMaxLength = -1;
            }
            if (this.mCounterEnabled) {
                if (this.mEditText == null) {
                    length = 0;
                }
                else {
                    length = this.mEditText.getText().length();
                }
                this.updateCounter(length);
            }
        }
    }
    
    public void setError(@Nullable final CharSequence text) {
        Label_0020: {
            if (this.mErrorEnabled) {
                break Label_0020;
            }
            if (!TextUtils.isEmpty(text)) {
                this.setErrorEnabled(true);
                break Label_0020;
            }
            return;
        }
        if (!TextUtils.isEmpty(text)) {
            ViewCompat.setAlpha((View)this.mErrorView, 0.0f);
            this.mErrorView.setText(text);
            ViewCompat.animate((View)this.mErrorView).alpha(1.0f).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener(new ViewPropertyAnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(final View view) {
                    view.setVisibility(0);
                }
            }).start();
            this.mErrorShown = true;
            this.updateEditTextBackground();
            this.updateLabelVisibility(true);
            return;
        }
        if (this.mErrorView.getVisibility() == 0) {
            ViewCompat.animate((View)this.mErrorView).alpha(0.0f).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener(new ViewPropertyAnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(final View view) {
                    view.setVisibility(4);
                    TextInputLayout.this.updateLabelVisibility(true);
                }
            }).start();
            this.mErrorShown = false;
            this.updateEditTextBackground();
        }
    }
    
    public void setErrorEnabled(final boolean mErrorEnabled) {
        if (this.mErrorEnabled != mErrorEnabled) {
            if (this.mErrorView != null) {
                ViewCompat.animate((View)this.mErrorView).cancel();
            }
            if (mErrorEnabled) {
                (this.mErrorView = new TextView(this.getContext())).setTextAppearance(this.getContext(), this.mErrorTextAppearance);
                this.mErrorView.setVisibility(4);
                ViewCompat.setAccessibilityLiveRegion((View)this.mErrorView, 1);
                final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(0, -2);
                linearLayout$LayoutParams.weight = 1.0f;
                this.addIndicator(this.mErrorView, 0, linearLayout$LayoutParams);
            }
            else {
                this.mErrorShown = false;
                this.updateEditTextBackground();
                this.removeIndicator(this.mErrorView);
                this.mErrorView = null;
            }
            this.mErrorEnabled = mErrorEnabled;
        }
    }
    
    public void setHint(@Nullable final CharSequence charSequence) {
        this.mHint = charSequence;
        this.mCollapsingTextHelper.setText(charSequence);
        this.sendAccessibilityEvent(2048);
    }
    
    public void setHintAnimationEnabled(final boolean mHintAnimationEnabled) {
        this.mHintAnimationEnabled = mHintAnimationEnabled;
    }
    
    public void setHintTextAppearance(@StyleRes final int collapsedTextAppearance) {
        this.mCollapsingTextHelper.setCollapsedTextAppearance(collapsedTextAppearance);
        this.mFocusedTextColor = ColorStateList.valueOf(this.mCollapsingTextHelper.getCollapsedTextColor());
        if (this.mEditText != null) {
            this.updateLabelVisibility(false);
            this.mEditText.setLayoutParams((ViewGroup$LayoutParams)this.updateEditTextMargin(this.mEditText.getLayoutParams()));
            this.mEditText.requestLayout();
        }
    }
    
    public void setTypeface(@Nullable final Typeface typefaces) {
        this.mCollapsingTextHelper.setTypefaces(typefaces);
    }
    
    private class TextInputAccessibilityDelegate extends AccessibilityDelegateCompat
    {
        @Override
        public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName((CharSequence)TextInputLayout.class.getSimpleName());
        }
        
        @Override
        public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(TextInputLayout.class.getSimpleName());
            final CharSequence text = TextInputLayout.this.mCollapsingTextHelper.getText();
            if (!TextUtils.isEmpty(text)) {
                accessibilityNodeInfoCompat.setText(text);
            }
            if (TextInputLayout.this.mEditText != null) {
                accessibilityNodeInfoCompat.setLabelFor((View)TextInputLayout.this.mEditText);
            }
            CharSequence text2;
            if (TextInputLayout.this.mErrorView != null) {
                text2 = TextInputLayout.this.mErrorView.getText();
            }
            else {
                text2 = null;
            }
            if (!TextUtils.isEmpty(text2)) {
                accessibilityNodeInfoCompat.setContentInvalid(true);
                accessibilityNodeInfoCompat.setError(text2);
            }
        }
        
        @Override
        public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            final CharSequence text = TextInputLayout.this.mCollapsingTextHelper.getText();
            if (!TextUtils.isEmpty(text)) {
                accessibilityEvent.getText().add(text);
            }
        }
    }
}
