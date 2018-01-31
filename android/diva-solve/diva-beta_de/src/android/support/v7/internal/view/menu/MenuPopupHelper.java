// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view.menu;

import java.util.ArrayList;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.util.AttributeSet;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.view.View$MeasureSpec;
import android.content.res.Resources;
import android.support.v7.appcompat.R;
import android.view.ViewTreeObserver;
import android.support.v7.widget.ListPopupWindow;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.widget.PopupWindow$OnDismissListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View$OnKeyListener;
import android.widget.AdapterView$OnItemClickListener;

public class MenuPopupHelper implements AdapterView$OnItemClickListener, View$OnKeyListener, ViewTreeObserver$OnGlobalLayoutListener, PopupWindow$OnDismissListener, MenuPresenter
{
    static final int ITEM_LAYOUT;
    private static final String TAG = "MenuPopupHelper";
    private final MenuAdapter mAdapter;
    private View mAnchorView;
    private int mContentWidth;
    private final Context mContext;
    private int mDropDownGravity;
    boolean mForceShowIcon;
    private boolean mHasContentWidth;
    private final LayoutInflater mInflater;
    private ViewGroup mMeasureParent;
    private final MenuBuilder mMenu;
    private final boolean mOverflowOnly;
    private ListPopupWindow mPopup;
    private final int mPopupMaxWidth;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private Callback mPresenterCallback;
    private ViewTreeObserver mTreeObserver;
    
    static {
        ITEM_LAYOUT = R.layout.abc_popup_menu_item_layout;
    }
    
    public MenuPopupHelper(final Context context, final MenuBuilder menuBuilder) {
        this(context, menuBuilder, null, false, R.attr.popupMenuStyle);
    }
    
    public MenuPopupHelper(final Context context, final MenuBuilder menuBuilder, final View view) {
        this(context, menuBuilder, view, false, R.attr.popupMenuStyle);
    }
    
    public MenuPopupHelper(final Context context, final MenuBuilder menuBuilder, final View view, final boolean b, final int n) {
        this(context, menuBuilder, view, b, n, 0);
    }
    
    public MenuPopupHelper(final Context mContext, final MenuBuilder mMenu, final View mAnchorView, final boolean mOverflowOnly, final int mPopupStyleAttr, final int mPopupStyleRes) {
        this.mDropDownGravity = 0;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mMenu = mMenu;
        this.mAdapter = new MenuAdapter(this.mMenu);
        this.mOverflowOnly = mOverflowOnly;
        this.mPopupStyleAttr = mPopupStyleAttr;
        this.mPopupStyleRes = mPopupStyleRes;
        final Resources resources = mContext.getResources();
        this.mPopupMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.mAnchorView = mAnchorView;
        mMenu.addMenuPresenter(this, mContext);
    }
    
    private int measureContentWidth() {
        int n = 0;
        View view = null;
        int n2 = 0;
        final MenuAdapter mAdapter = this.mAdapter;
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
        final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(0, 0);
        final int count = ((ListAdapter)mAdapter).getCount();
        int n3 = 0;
        int mPopupMaxWidth;
        while (true) {
            mPopupMaxWidth = n;
            if (n3 >= count) {
                break;
            }
            final int itemViewType = ((ListAdapter)mAdapter).getItemViewType(n3);
            int n4;
            if (itemViewType != (n4 = n2)) {
                n4 = itemViewType;
                view = null;
            }
            if (this.mMeasureParent == null) {
                this.mMeasureParent = (ViewGroup)new FrameLayout(this.mContext);
            }
            view = ((ListAdapter)mAdapter).getView(n3, view, this.mMeasureParent);
            view.measure(measureSpec, measureSpec2);
            final int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= this.mPopupMaxWidth) {
                mPopupMaxWidth = this.mPopupMaxWidth;
                break;
            }
            int n5;
            if (measuredWidth > (n5 = n)) {
                n5 = measuredWidth;
            }
            ++n3;
            n2 = n4;
            n = n5;
        }
        return mPopupMaxWidth;
    }
    
    public boolean collapseItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    public void dismiss() {
        if (this.isShowing()) {
            this.mPopup.dismiss();
        }
    }
    
    public boolean expandItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    public boolean flagActionItems() {
        return false;
    }
    
    public int getGravity() {
        return this.mDropDownGravity;
    }
    
    public int getId() {
        return 0;
    }
    
    public MenuView getMenuView(final ViewGroup viewGroup) {
        throw new UnsupportedOperationException("MenuPopupHelpers manage their own views");
    }
    
    public ListPopupWindow getPopup() {
        return this.mPopup;
    }
    
    public void initForMenu(final Context context, final MenuBuilder menuBuilder) {
    }
    
    public boolean isShowing() {
        return this.mPopup != null && this.mPopup.isShowing();
    }
    
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        if (menuBuilder == this.mMenu) {
            this.dismiss();
            if (this.mPresenterCallback != null) {
                this.mPresenterCallback.onCloseMenu(menuBuilder, b);
            }
        }
    }
    
    public void onDismiss() {
        this.mPopup = null;
        this.mMenu.close();
        if (this.mTreeObserver != null) {
            if (!this.mTreeObserver.isAlive()) {
                this.mTreeObserver = this.mAnchorView.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
            this.mTreeObserver = null;
        }
    }
    
    public void onGlobalLayout() {
        if (this.isShowing()) {
            final View mAnchorView = this.mAnchorView;
            if (mAnchorView == null || !mAnchorView.isShown()) {
                this.dismiss();
            }
            else if (this.isShowing()) {
                this.mPopup.show();
            }
        }
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        final MenuAdapter mAdapter = this.mAdapter;
        mAdapter.mAdapterMenu.performItemAction((MenuItem)mAdapter.getItem(n), 0);
    }
    
    public boolean onKey(final View view, final int n, final KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 && n == 82) {
            this.dismiss();
            return true;
        }
        return false;
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
    }
    
    public Parcelable onSaveInstanceState() {
        return null;
    }
    
    public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
        if (subMenuBuilder.hasVisibleItems()) {
            final MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.mContext, subMenuBuilder, this.mAnchorView);
            menuPopupHelper.setCallback(this.mPresenterCallback);
            final boolean b = false;
            final int size = subMenuBuilder.size();
            int n = 0;
            boolean forceShowIcon;
            while (true) {
                forceShowIcon = b;
                if (n >= size) {
                    break;
                }
                final MenuItem item = subMenuBuilder.getItem(n);
                if (item.isVisible() && item.getIcon() != null) {
                    forceShowIcon = true;
                    break;
                }
                ++n;
            }
            menuPopupHelper.setForceShowIcon(forceShowIcon);
            if (menuPopupHelper.tryShow()) {
                if (this.mPresenterCallback != null) {
                    this.mPresenterCallback.onOpenSubMenu(subMenuBuilder);
                }
                return true;
            }
        }
        return false;
    }
    
    public void setAnchorView(final View mAnchorView) {
        this.mAnchorView = mAnchorView;
    }
    
    public void setCallback(final Callback mPresenterCallback) {
        this.mPresenterCallback = mPresenterCallback;
    }
    
    public void setForceShowIcon(final boolean mForceShowIcon) {
        this.mForceShowIcon = mForceShowIcon;
    }
    
    public void setGravity(final int mDropDownGravity) {
        this.mDropDownGravity = mDropDownGravity;
    }
    
    public void show() {
        if (!this.tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }
    
    public boolean tryShow() {
        boolean b = false;
        (this.mPopup = new ListPopupWindow(this.mContext, null, this.mPopupStyleAttr, this.mPopupStyleRes)).setOnDismissListener((PopupWindow$OnDismissListener)this);
        this.mPopup.setOnItemClickListener((AdapterView$OnItemClickListener)this);
        this.mPopup.setAdapter((ListAdapter)this.mAdapter);
        this.mPopup.setModal(true);
        final View mAnchorView = this.mAnchorView;
        if (mAnchorView != null) {
            if (this.mTreeObserver == null) {
                b = true;
            }
            this.mTreeObserver = mAnchorView.getViewTreeObserver();
            if (b) {
                this.mTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
            }
            this.mPopup.setAnchorView(mAnchorView);
            this.mPopup.setDropDownGravity(this.mDropDownGravity);
            if (!this.mHasContentWidth) {
                this.mContentWidth = this.measureContentWidth();
                this.mHasContentWidth = true;
            }
            this.mPopup.setContentWidth(this.mContentWidth);
            this.mPopup.setInputMethodMode(2);
            this.mPopup.show();
            this.mPopup.getListView().setOnKeyListener((View$OnKeyListener)this);
            return true;
        }
        return false;
    }
    
    public void updateMenuView(final boolean b) {
        this.mHasContentWidth = false;
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }
    
    private class MenuAdapter extends BaseAdapter
    {
        private MenuBuilder mAdapterMenu;
        private int mExpandedIndex;
        
        public MenuAdapter(final MenuBuilder mAdapterMenu) {
            this.mExpandedIndex = -1;
            this.mAdapterMenu = mAdapterMenu;
            this.findExpandedIndex();
        }
        
        void findExpandedIndex() {
            final MenuItemImpl expandedItem = MenuPopupHelper.this.mMenu.getExpandedItem();
            if (expandedItem != null) {
                final ArrayList<MenuItemImpl> nonActionItems = MenuPopupHelper.this.mMenu.getNonActionItems();
                for (int size = nonActionItems.size(), i = 0; i < size; ++i) {
                    if (nonActionItems.get(i) == expandedItem) {
                        this.mExpandedIndex = i;
                        return;
                    }
                }
            }
            this.mExpandedIndex = -1;
        }
        
        public int getCount() {
            ArrayList<MenuItemImpl> list;
            if (MenuPopupHelper.this.mOverflowOnly) {
                list = this.mAdapterMenu.getNonActionItems();
            }
            else {
                list = this.mAdapterMenu.getVisibleItems();
            }
            if (this.mExpandedIndex < 0) {
                return list.size();
            }
            return list.size() - 1;
        }
        
        public MenuItemImpl getItem(final int n) {
            ArrayList<MenuItemImpl> list;
            if (MenuPopupHelper.this.mOverflowOnly) {
                list = this.mAdapterMenu.getNonActionItems();
            }
            else {
                list = this.mAdapterMenu.getVisibleItems();
            }
            int n2 = n;
            if (this.mExpandedIndex >= 0 && (n2 = n) >= this.mExpandedIndex) {
                n2 = n + 1;
            }
            return list.get(n2);
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            View inflate = view;
            if (view == null) {
                inflate = MenuPopupHelper.this.mInflater.inflate(MenuPopupHelper.ITEM_LAYOUT, viewGroup, false);
            }
            final ListMenuItemView listMenuItemView = (ListMenuItemView)inflate;
            if (MenuPopupHelper.this.mForceShowIcon) {
                ((ListMenuItemView)inflate).setForceShowIcon(true);
            }
            ((MenuView.ItemView)listMenuItemView).initialize(this.getItem(n), 0);
            return inflate;
        }
        
        public void notifyDataSetChanged() {
            this.findExpandedIndex();
            super.notifyDataSetChanged();
        }
    }
}
