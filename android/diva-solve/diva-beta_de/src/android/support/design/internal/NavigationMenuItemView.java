// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.graphics.drawable.Drawable$ConstantState;
import android.widget.TextView;
import android.support.v4.widget.TextViewCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.ViewStub;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.graphics.drawable.StateListDrawable;
import android.view.ViewGroup;
import android.support.design.R;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.CheckedTextView;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.content.res.ColorStateList;
import android.widget.FrameLayout;
import android.support.v7.internal.view.menu.MenuView;

public class NavigationMenuItemView extends ForegroundLinearLayout implements ItemView
{
    private static final int[] CHECKED_STATE_SET;
    private FrameLayout mActionArea;
    private final int mIconSize;
    private ColorStateList mIconTintList;
    private MenuItemImpl mItemData;
    private final CheckedTextView mTextView;
    
    static {
        CHECKED_STATE_SET = new int[] { 16842912 };
    }
    
    public NavigationMenuItemView(final Context context) {
        this(context, null);
    }
    
    public NavigationMenuItemView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public NavigationMenuItemView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.setOrientation(0);
        LayoutInflater.from(context).inflate(R.layout.design_navigation_menu_item, (ViewGroup)this, true);
        this.mIconSize = context.getResources().getDimensionPixelSize(R.dimen.design_navigation_icon_size);
        (this.mTextView = (CheckedTextView)this.findViewById(R.id.design_menu_item_text)).setDuplicateParentStateEnabled(true);
    }
    
    private StateListDrawable createDefaultBackground() {
        final TypedValue typedValue = new TypedValue();
        if (this.getContext().getTheme().resolveAttribute(R.attr.colorControlHighlight, typedValue, true)) {
            final StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(NavigationMenuItemView.CHECKED_STATE_SET, (Drawable)new ColorDrawable(typedValue.data));
            stateListDrawable.addState(NavigationMenuItemView.EMPTY_STATE_SET, (Drawable)new ColorDrawable(0));
            return stateListDrawable;
        }
        return null;
    }
    
    private void setActionView(final View view) {
        if (this.mActionArea == null) {
            this.mActionArea = (FrameLayout)((ViewStub)this.findViewById(R.id.design_menu_item_action_area_stub)).inflate();
        }
        this.mActionArea.removeAllViews();
        if (view != null) {
            this.mActionArea.addView(view);
        }
    }
    
    @Override
    public MenuItemImpl getItemData() {
        return this.mItemData;
    }
    
    @Override
    public void initialize(final MenuItemImpl mItemData, int visibility) {
        this.mItemData = mItemData;
        if (mItemData.isVisible()) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        this.setVisibility(visibility);
        if (this.getBackground() == null) {
            this.setBackgroundDrawable((Drawable)this.createDefaultBackground());
        }
        this.setCheckable(mItemData.isCheckable());
        this.setChecked(mItemData.isChecked());
        ((MenuView.ItemView)this).setEnabled(mItemData.isEnabled());
        this.setTitle(mItemData.getTitle());
        this.setIcon(mItemData.getIcon());
        this.setActionView(mItemData.getActionView());
    }
    
    protected int[] onCreateDrawableState(final int n) {
        final int[] onCreateDrawableState = super.onCreateDrawableState(n + 1);
        if (this.mItemData != null && this.mItemData.isCheckable() && this.mItemData.isChecked()) {
            mergeDrawableStates(onCreateDrawableState, NavigationMenuItemView.CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }
    
    @Override
    public boolean prefersCondensedTitle() {
        return false;
    }
    
    public void recycle() {
        if (this.mActionArea != null) {
            this.mActionArea.removeAllViews();
        }
        this.mTextView.setCompoundDrawables((Drawable)null, (Drawable)null, (Drawable)null, (Drawable)null);
    }
    
    @Override
    public void setCheckable(final boolean b) {
        this.refreshDrawableState();
    }
    
    @Override
    public void setChecked(final boolean checked) {
        this.refreshDrawableState();
        this.mTextView.setChecked(checked);
    }
    
    @Override
    public void setIcon(Drawable drawable) {
        Drawable mutate = drawable;
        if (drawable != null) {
            final Drawable$ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            mutate = DrawableCompat.wrap(drawable).mutate();
            mutate.setBounds(0, 0, this.mIconSize, this.mIconSize);
            DrawableCompat.setTintList(mutate, this.mIconTintList);
        }
        TextViewCompat.setCompoundDrawablesRelative((TextView)this.mTextView, mutate, null, null, null);
    }
    
    void setIconTintList(final ColorStateList mIconTintList) {
        this.mIconTintList = mIconTintList;
        if (this.mItemData != null) {
            this.setIcon(this.mItemData.getIcon());
        }
    }
    
    @Override
    public void setShortcut(final boolean b, final char c) {
    }
    
    public void setTextAppearance(final Context context, final int n) {
        this.mTextView.setTextAppearance(context, n);
    }
    
    public void setTextColor(final ColorStateList textColor) {
        this.mTextView.setTextColor(textColor);
    }
    
    @Override
    public void setTitle(final CharSequence text) {
        this.mTextView.setText(text);
    }
    
    @Override
    public boolean showsIcon() {
        return true;
    }
}
