// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.ViewGroup;
import android.support.v4.view.ViewPropertyAnimatorListener;
import java.util.List;
import android.support.v4.view.ViewCompat;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.annotation.TargetApi;
import android.support.annotation.Nullable;
import android.view.View$MeasureSpec;
import android.content.res.TypedArray;
import android.view.View;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.support.design.R;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.widget.ImageButton;

@CoordinatorLayout.DefaultBehavior(Behavior.class)
public class FloatingActionButton extends ImageButton
{
    private static final int SIZE_MINI = 1;
    private static final int SIZE_NORMAL = 0;
    private ColorStateList mBackgroundTint;
    private PorterDuff$Mode mBackgroundTintMode;
    private int mBorderWidth;
    private int mContentPadding;
    private final FloatingActionButtonImpl mImpl;
    private int mRippleColor;
    private final Rect mShadowPadding;
    private int mSize;
    
    public FloatingActionButton(final Context context) {
        this(context, null);
    }
    
    public FloatingActionButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public FloatingActionButton(final Context context, final AttributeSet set, int sdk_INT) {
        super(context, set, sdk_INT);
        ThemeUtils.checkAppCompatTheme(context);
        this.mShadowPadding = new Rect();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.FloatingActionButton, sdk_INT, R.style.Widget_Design_FloatingActionButton);
        final Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.FloatingActionButton_android_background);
        this.mBackgroundTint = obtainStyledAttributes.getColorStateList(R.styleable.FloatingActionButton_backgroundTint);
        this.mBackgroundTintMode = parseTintMode(obtainStyledAttributes.getInt(R.styleable.FloatingActionButton_backgroundTintMode, -1), null);
        this.mRippleColor = obtainStyledAttributes.getColor(R.styleable.FloatingActionButton_rippleColor, 0);
        this.mSize = obtainStyledAttributes.getInt(R.styleable.FloatingActionButton_fabSize, 0);
        this.mBorderWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FloatingActionButton_borderWidth, 0);
        final float dimension = obtainStyledAttributes.getDimension(R.styleable.FloatingActionButton_elevation, 0.0f);
        final float dimension2 = obtainStyledAttributes.getDimension(R.styleable.FloatingActionButton_pressedTranslationZ, 0.0f);
        obtainStyledAttributes.recycle();
        final ShadowViewDelegate shadowViewDelegate = new ShadowViewDelegate() {
            @Override
            public float getRadius() {
                return FloatingActionButton.this.getSizeDimension() / 2.0f;
            }
            
            @Override
            public void setBackgroundDrawable(final Drawable drawable) {
                FloatingActionButton.access$201(FloatingActionButton.this, drawable);
            }
            
            @Override
            public void setShadowPadding(final int n, final int n2, final int n3, final int n4) {
                FloatingActionButton.this.mShadowPadding.set(n, n2, n3, n4);
                FloatingActionButton.this.setPadding(FloatingActionButton.this.mContentPadding + n, FloatingActionButton.this.mContentPadding + n2, FloatingActionButton.this.mContentPadding + n3, FloatingActionButton.this.mContentPadding + n4);
            }
        };
        sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            this.mImpl = new FloatingActionButtonLollipop((View)this, shadowViewDelegate);
        }
        else if (sdk_INT >= 12) {
            this.mImpl = new FloatingActionButtonHoneycombMr1((View)this, shadowViewDelegate);
        }
        else {
            this.mImpl = new FloatingActionButtonEclairMr1((View)this, shadowViewDelegate);
        }
        sdk_INT = (int)this.getResources().getDimension(R.dimen.design_fab_content_size);
        this.mContentPadding = (this.getSizeDimension() - sdk_INT) / 2;
        this.mImpl.setBackgroundDrawable(drawable, this.mBackgroundTint, this.mBackgroundTintMode, this.mRippleColor, this.mBorderWidth);
        this.mImpl.setElevation(dimension);
        this.mImpl.setPressedTranslationZ(dimension2);
    }
    
    static /* synthetic */ void access$201(final FloatingActionButton floatingActionButton, final Drawable backgroundDrawable) {
        ((View)floatingActionButton).setBackgroundDrawable(backgroundDrawable);
    }
    
    static PorterDuff$Mode parseTintMode(final int n, final PorterDuff$Mode porterDuff$Mode) {
        switch (n) {
            default: {
                return porterDuff$Mode;
            }
            case 3: {
                return PorterDuff$Mode.SRC_OVER;
            }
            case 5: {
                return PorterDuff$Mode.SRC_IN;
            }
            case 9: {
                return PorterDuff$Mode.SRC_ATOP;
            }
            case 14: {
                return PorterDuff$Mode.MULTIPLY;
            }
            case 15: {
                return PorterDuff$Mode.SCREEN;
            }
        }
    }
    
    private static int resolveAdjustedSize(final int n, int size) {
        final int mode = View$MeasureSpec.getMode(size);
        size = View$MeasureSpec.getSize(size);
        switch (mode) {
            default: {
                return n;
            }
            case 0: {
                return n;
            }
            case Integer.MIN_VALUE: {
                return Math.min(n, size);
            }
            case 1073741824: {
                return size;
            }
        }
    }
    
    @Nullable
    private FloatingActionButtonImpl.InternalVisibilityChangedListener wrapOnVisibilityChangedListener(@Nullable final OnVisibilityChangedListener onVisibilityChangedListener) {
        if (onVisibilityChangedListener == null) {
            return null;
        }
        return new FloatingActionButtonImpl.InternalVisibilityChangedListener() {
            @Override
            public void onHidden() {
                onVisibilityChangedListener.onHidden(FloatingActionButton.this);
            }
            
            @Override
            public void onShown() {
                onVisibilityChangedListener.onShown(FloatingActionButton.this);
            }
        };
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.mImpl.onDrawableStateChanged(this.getDrawableState());
    }
    
    @Nullable
    public ColorStateList getBackgroundTintList() {
        return this.mBackgroundTint;
    }
    
    @Nullable
    public PorterDuff$Mode getBackgroundTintMode() {
        return this.mBackgroundTintMode;
    }
    
    final int getSizeDimension() {
        switch (this.mSize) {
            default: {
                return this.getResources().getDimensionPixelSize(R.dimen.design_fab_size_normal);
            }
            case 1: {
                return this.getResources().getDimensionPixelSize(R.dimen.design_fab_size_mini);
            }
        }
    }
    
    public void hide() {
        this.mImpl.hide(null);
    }
    
    public void hide(@Nullable final OnVisibilityChangedListener onVisibilityChangedListener) {
        this.mImpl.hide(this.wrapOnVisibilityChangedListener(onVisibilityChangedListener));
    }
    
    @TargetApi(11)
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.mImpl.jumpDrawableToCurrentState();
    }
    
    protected void onMeasure(int min, final int n) {
        final int sizeDimension = this.getSizeDimension();
        min = Math.min(resolveAdjustedSize(sizeDimension, min), resolveAdjustedSize(sizeDimension, n));
        this.setMeasuredDimension(this.mShadowPadding.left + min + this.mShadowPadding.right, this.mShadowPadding.top + min + this.mShadowPadding.bottom);
    }
    
    public void setBackgroundDrawable(@NonNull final Drawable drawable) {
        if (this.mImpl != null) {
            this.mImpl.setBackgroundDrawable(drawable, this.mBackgroundTint, this.mBackgroundTintMode, this.mRippleColor, this.mBorderWidth);
        }
    }
    
    public void setBackgroundTintList(@Nullable final ColorStateList list) {
        if (this.mBackgroundTint != list) {
            this.mBackgroundTint = list;
            this.mImpl.setBackgroundTintList(list);
        }
    }
    
    public void setBackgroundTintMode(@Nullable final PorterDuff$Mode porterDuff$Mode) {
        if (this.mBackgroundTintMode != porterDuff$Mode) {
            this.mBackgroundTintMode = porterDuff$Mode;
            this.mImpl.setBackgroundTintMode(porterDuff$Mode);
        }
    }
    
    public void setRippleColor(@ColorInt final int n) {
        if (this.mRippleColor != n) {
            this.mRippleColor = n;
            this.mImpl.setRippleColor(n);
        }
    }
    
    public void show() {
        this.mImpl.show(null);
    }
    
    public void show(@Nullable final OnVisibilityChangedListener onVisibilityChangedListener) {
        this.mImpl.show(this.wrapOnVisibilityChangedListener(onVisibilityChangedListener));
    }
    
    public static class Behavior extends CoordinatorLayout.Behavior<FloatingActionButton>
    {
        private static final boolean SNACKBAR_BEHAVIOR_ENABLED;
        private float mFabTranslationY;
        private Rect mTmpRect;
        
        static {
            SNACKBAR_BEHAVIOR_ENABLED = (Build$VERSION.SDK_INT >= 11);
        }
        
        private float getFabTranslationYForSnackbar(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton) {
            float n = 0.0f;
            final List<View> dependencies = coordinatorLayout.getDependencies((View)floatingActionButton);
            float min;
            for (int i = 0; i < dependencies.size(); ++i, n = min) {
                final View view = dependencies.get(i);
                min = n;
                if (view instanceof Snackbar.SnackbarLayout) {
                    min = n;
                    if (coordinatorLayout.doViewsOverlap((View)floatingActionButton, view)) {
                        min = Math.min(n, ViewCompat.getTranslationY(view) - view.getHeight());
                    }
                }
            }
            return n;
        }
        
        private void offsetIfNeeded(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton) {
            final Rect access$000 = floatingActionButton.mShadowPadding;
            if (access$000 != null && access$000.centerX() > 0 && access$000.centerY() > 0) {
                final LayoutParams layoutParams = (LayoutParams)floatingActionButton.getLayoutParams();
                int bottom = 0;
                int right = 0;
                if (floatingActionButton.getRight() >= coordinatorLayout.getWidth() - layoutParams.rightMargin) {
                    right = access$000.right;
                }
                else if (floatingActionButton.getLeft() <= layoutParams.leftMargin) {
                    right = -access$000.left;
                }
                if (floatingActionButton.getBottom() >= coordinatorLayout.getBottom() - layoutParams.bottomMargin) {
                    bottom = access$000.bottom;
                }
                else if (floatingActionButton.getTop() <= layoutParams.topMargin) {
                    bottom = -access$000.top;
                }
                floatingActionButton.offsetTopAndBottom(bottom);
                floatingActionButton.offsetLeftAndRight(right);
            }
        }
        
        private void updateFabTranslationForSnackbar(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final View view) {
            if (floatingActionButton.getVisibility() == 0) {
                final float fabTranslationYForSnackbar = this.getFabTranslationYForSnackbar(coordinatorLayout, floatingActionButton);
                if (this.mFabTranslationY != fabTranslationYForSnackbar) {
                    this.mFabTranslationY = fabTranslationYForSnackbar;
                    if (Math.abs(ViewCompat.getTranslationY((View)floatingActionButton) - fabTranslationYForSnackbar) > floatingActionButton.getHeight() * 0.667f) {
                        ViewCompat.animate((View)floatingActionButton).translationY(fabTranslationYForSnackbar).scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener(null);
                        return;
                    }
                    ViewCompat.animate((View)floatingActionButton).cancel();
                    ViewCompat.setTranslationY((View)floatingActionButton, fabTranslationYForSnackbar);
                }
            }
        }
        
        private boolean updateFabVisibility(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final FloatingActionButton floatingActionButton) {
            if (((LayoutParams)floatingActionButton.getLayoutParams()).getAnchorId() != appBarLayout.getId()) {
                return false;
            }
            if (this.mTmpRect == null) {
                this.mTmpRect = new Rect();
            }
            final Rect mTmpRect = this.mTmpRect;
            ViewGroupUtils.getDescendantRect(coordinatorLayout, (View)appBarLayout, mTmpRect);
            if (mTmpRect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.hide();
            }
            else {
                floatingActionButton.show();
            }
            return true;
        }
        
        public boolean layoutDependsOn(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final View view) {
            return Behavior.SNACKBAR_BEHAVIOR_ENABLED && view instanceof Snackbar.SnackbarLayout;
        }
        
        public boolean onDependentViewChanged(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final View view) {
            if (view instanceof Snackbar.SnackbarLayout) {
                this.updateFabTranslationForSnackbar(coordinatorLayout, floatingActionButton, view);
            }
            else if (view instanceof AppBarLayout) {
                this.updateFabVisibility(coordinatorLayout, (AppBarLayout)view, floatingActionButton);
            }
            return false;
        }
        
        public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final int n) {
            final List<View> dependencies = coordinatorLayout.getDependencies((View)floatingActionButton);
            for (int i = 0; i < dependencies.size(); ++i) {
                final View view = dependencies.get(i);
                if (view instanceof AppBarLayout && this.updateFabVisibility(coordinatorLayout, (AppBarLayout)view, floatingActionButton)) {
                    break;
                }
            }
            coordinatorLayout.onLayoutChild((View)floatingActionButton, n);
            this.offsetIfNeeded(coordinatorLayout, floatingActionButton);
            return true;
        }
    }
    
    public abstract static class OnVisibilityChangedListener
    {
        public void onHidden(final FloatingActionButton floatingActionButton) {
        }
        
        public void onShown(final FloatingActionButton floatingActionButton) {
        }
    }
}
