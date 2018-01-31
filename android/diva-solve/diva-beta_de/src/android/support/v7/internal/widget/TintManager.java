// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.support.v4.util.LruCache;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.DrawableContainer$DrawableContainerState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.ColorFilter;
import android.support.v4.content.ContextCompat;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.appcompat.R;
import android.os.Build$VERSION;
import android.util.SparseArray;
import android.content.res.ColorStateList;
import java.lang.ref.WeakReference;
import android.content.Context;
import java.util.WeakHashMap;
import android.graphics.PorterDuff$Mode;

public final class TintManager
{
    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
    private static final ColorFilterLruCache COLOR_FILTER_CACHE;
    private static final boolean DEBUG = false;
    private static final PorterDuff$Mode DEFAULT_MODE;
    private static final WeakHashMap<Context, TintManager> INSTANCE_CACHE;
    public static final boolean SHOULD_BE_USED;
    private static final String TAG = "TintManager";
    private static final int[] TINT_CHECKABLE_BUTTON_LIST;
    private static final int[] TINT_COLOR_CONTROL_NORMAL;
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
    private final WeakReference<Context> mContextRef;
    private ColorStateList mDefaultColorStateList;
    private SparseArray<ColorStateList> mTintLists;
    
    static {
        SHOULD_BE_USED = (Build$VERSION.SDK_INT < 21);
        DEFAULT_MODE = PorterDuff$Mode.SRC_IN;
        INSTANCE_CACHE = new WeakHashMap<Context, TintManager>();
        COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
        COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha };
        TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_ic_ab_back_mtrl_am_alpha, R.drawable.abc_ic_go_search_api_mtrl_alpha, R.drawable.abc_ic_search_api_mtrl_alpha, R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_ic_clear_mtrl_alpha, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha, R.drawable.abc_ic_menu_moreoverflow_mtrl_alpha, R.drawable.abc_ic_voice_search_api_mtrl_alpha };
        COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[] { R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material };
        COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[] { R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult };
        TINT_COLOR_CONTROL_STATE_LIST = new int[] { R.drawable.abc_edit_text_material, R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material, R.drawable.abc_spinner_mtrl_am_alpha, R.drawable.abc_spinner_textfield_background_material, R.drawable.abc_ratingbar_full_material, R.drawable.abc_switch_track_mtrl_alpha, R.drawable.abc_switch_thumb_material, R.drawable.abc_btn_default_mtrl_shape, R.drawable.abc_btn_borderless_material };
        TINT_CHECKABLE_BUTTON_LIST = new int[] { R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material };
    }
    
    private TintManager(final Context context) {
        this.mContextRef = new WeakReference<Context>(context);
    }
    
    private static boolean arrayContains(final int[] array, final int n) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (array[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    private ColorStateList createButtonColorStateList(final Context context, int themeAttrColor) {
        final int[][] array = new int[4][];
        final int[] array2 = new int[4];
        themeAttrColor = ThemeUtils.getThemeAttrColor(context, themeAttrColor);
        final int themeAttrColor2 = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlHighlight);
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorButtonNormal);
        final int n = 0 + 1;
        array[n] = ThemeUtils.PRESSED_STATE_SET;
        array2[n] = ColorUtils.compositeColors(themeAttrColor2, themeAttrColor);
        final int n2 = n + 1;
        array[n2] = ThemeUtils.FOCUSED_STATE_SET;
        array2[n2] = ColorUtils.compositeColors(themeAttrColor2, themeAttrColor);
        final int n3 = n2 + 1;
        array[n3] = ThemeUtils.EMPTY_STATE_SET;
        array2[n3] = themeAttrColor;
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createCheckableButtonColorStateList(final Context context) {
        final int[][] array = new int[3][];
        final int[] array2 = new int[3];
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
        final int n = 0 + 1;
        array[n] = ThemeUtils.CHECKED_STATE_SET;
        array2[n] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
        final int n2 = n + 1;
        array[n2] = ThemeUtils.EMPTY_STATE_SET;
        array2[n2] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createColoredButtonColorStateList(final Context context) {
        return this.createButtonColorStateList(context, R.attr.colorAccent);
    }
    
    private ColorStateList createDefaultButtonColorStateList(final Context context) {
        return this.createButtonColorStateList(context, R.attr.colorButtonNormal);
    }
    
    private ColorStateList createEditTextColorStateList(final Context context) {
        final int[][] array = new int[3][];
        final int[] array2 = new int[3];
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
        final int n = 0 + 1;
        array[n] = ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET;
        array2[n] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
        final int n2 = n + 1;
        array[n2] = ThemeUtils.EMPTY_STATE_SET;
        array2[n2] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createSeekbarThumbColorStateList(final Context context) {
        final int[][] array = new int[2][];
        final int[] array2 = new int[2];
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlActivated);
        final int n = 0 + 1;
        array[n] = ThemeUtils.EMPTY_STATE_SET;
        array2[n] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createSpinnerColorStateList(final Context context) {
        final int[][] array = new int[3][];
        final int[] array2 = new int[3];
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
        final int n = 0 + 1;
        array[n] = ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET;
        array2[n] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
        final int n2 = n + 1;
        array[n2] = ThemeUtils.EMPTY_STATE_SET;
        array2[n2] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createSwitchThumbColorStateList(final Context context) {
        final int[][] array = new int[3][];
        final int[] array2 = new int[3];
        final ColorStateList themeAttrColorStateList = ThemeUtils.getThemeAttrColorStateList(context, R.attr.colorSwitchThumbNormal);
        if (themeAttrColorStateList != null && themeAttrColorStateList.isStateful()) {
            array[0] = ThemeUtils.DISABLED_STATE_SET;
            array2[0] = themeAttrColorStateList.getColorForState(array[0], 0);
            final int n = 0 + 1;
            array[n] = ThemeUtils.CHECKED_STATE_SET;
            array2[n] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
            final int n2 = n + 1;
            array[n2] = ThemeUtils.EMPTY_STATE_SET;
            array2[n2] = themeAttrColorStateList.getDefaultColor();
        }
        else {
            array[0] = ThemeUtils.DISABLED_STATE_SET;
            array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
            final int n3 = 0 + 1;
            array[n3] = ThemeUtils.CHECKED_STATE_SET;
            array2[n3] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
            final int n4 = n3 + 1;
            array[n4] = ThemeUtils.EMPTY_STATE_SET;
            array2[n4] = ThemeUtils.getThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
        }
        return new ColorStateList(array, array2);
    }
    
    private ColorStateList createSwitchTrackColorStateList(final Context context) {
        final int[][] array = new int[3][];
        final int[] array2 = new int[3];
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        array2[0] = ThemeUtils.getThemeAttrColor(context, 16842800, 0.1f);
        final int n = 0 + 1;
        array[n] = ThemeUtils.CHECKED_STATE_SET;
        array2[n] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated, 0.3f);
        final int n2 = n + 1;
        array[n2] = ThemeUtils.EMPTY_STATE_SET;
        array2[n2] = ThemeUtils.getThemeAttrColor(context, 16842800, 0.3f);
        return new ColorStateList(array, array2);
    }
    
    private static PorterDuffColorFilter createTintFilter(final ColorStateList list, final PorterDuff$Mode porterDuff$Mode, final int[] array) {
        if (list == null || porterDuff$Mode == null) {
            return null;
        }
        return getPorterDuffColorFilter(list.getColorForState(array, 0), porterDuff$Mode);
    }
    
    public static TintManager get(final Context context) {
        TintManager tintManager;
        if ((tintManager = TintManager.INSTANCE_CACHE.get(context)) == null) {
            tintManager = new TintManager(context);
            TintManager.INSTANCE_CACHE.put(context, tintManager);
        }
        return tintManager;
    }
    
    private ColorStateList getDefaultColorStateList(final Context context) {
        if (this.mDefaultColorStateList == null) {
            final int themeAttrColor = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
            final int themeAttrColor2 = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
            final int[][] array = new int[7][];
            final int[] array2 = new int[7];
            array[0] = ThemeUtils.DISABLED_STATE_SET;
            array2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
            final int n = 0 + 1;
            array[n] = ThemeUtils.FOCUSED_STATE_SET;
            array2[n] = themeAttrColor2;
            final int n2 = n + 1;
            array[n2] = ThemeUtils.ACTIVATED_STATE_SET;
            array2[n2] = themeAttrColor2;
            final int n3 = n2 + 1;
            array[n3] = ThemeUtils.PRESSED_STATE_SET;
            array2[n3] = themeAttrColor2;
            final int n4 = n3 + 1;
            array[n4] = ThemeUtils.CHECKED_STATE_SET;
            array2[n4] = themeAttrColor2;
            final int n5 = n4 + 1;
            array[n5] = ThemeUtils.SELECTED_STATE_SET;
            array2[n5] = themeAttrColor2;
            final int n6 = n5 + 1;
            array[n6] = ThemeUtils.EMPTY_STATE_SET;
            array2[n6] = themeAttrColor;
            this.mDefaultColorStateList = new ColorStateList(array, array2);
        }
        return this.mDefaultColorStateList;
    }
    
    public static Drawable getDrawable(final Context context, final int n) {
        if (isInTintList(n)) {
            return get(context).getDrawable(n);
        }
        return ContextCompat.getDrawable(context, n);
    }
    
    private static PorterDuffColorFilter getPorterDuffColorFilter(final int n, final PorterDuff$Mode porterDuff$Mode) {
        PorterDuffColorFilter value;
        if ((value = TintManager.COLOR_FILTER_CACHE.get(n, porterDuff$Mode)) == null) {
            value = new PorterDuffColorFilter(n, porterDuff$Mode);
            TintManager.COLOR_FILTER_CACHE.put(n, porterDuff$Mode, value);
        }
        return value;
    }
    
    private static boolean isInTintList(final int n) {
        return arrayContains(TintManager.TINT_COLOR_CONTROL_NORMAL, n) || arrayContains(TintManager.COLORFILTER_TINT_COLOR_CONTROL_NORMAL, n) || arrayContains(TintManager.COLORFILTER_COLOR_CONTROL_ACTIVATED, n) || arrayContains(TintManager.TINT_COLOR_CONTROL_STATE_LIST, n) || arrayContains(TintManager.COLORFILTER_COLOR_BACKGROUND_MULTIPLY, n) || arrayContains(TintManager.TINT_CHECKABLE_BUTTON_LIST, n) || n == R.drawable.abc_cab_background_top_material;
    }
    
    private static void setPorterDuffColorFilter(final Drawable drawable, final int n, final PorterDuff$Mode porterDuff$Mode) {
        PorterDuff$Mode default_MODE = porterDuff$Mode;
        if (porterDuff$Mode == null) {
            default_MODE = TintManager.DEFAULT_MODE;
        }
        drawable.setColorFilter((ColorFilter)getPorterDuffColorFilter(n, default_MODE));
    }
    
    private static boolean shouldMutateBackground(final Drawable drawable) {
        if (Build$VERSION.SDK_INT < 16) {
            if (drawable instanceof LayerDrawable) {
                if (Build$VERSION.SDK_INT < 16) {
                    return false;
                }
            }
            else if (drawable instanceof InsetDrawable) {
                if (Build$VERSION.SDK_INT < 14) {
                    return false;
                }
            }
            else if (drawable instanceof DrawableContainer) {
                final Drawable$ConstantState constantState = drawable.getConstantState();
                if (constantState instanceof DrawableContainer$DrawableContainerState) {
                    final Drawable[] children = ((DrawableContainer$DrawableContainerState)constantState).getChildren();
                    for (int length = children.length, i = 0; i < length; ++i) {
                        if (!shouldMutateBackground(children[i])) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public static void tintDrawable(final Drawable drawable, final TintInfo tintInfo, final int[] array) {
        if (shouldMutateBackground(drawable) && drawable.mutate() != drawable) {
            Log.d("TintManager", "Mutated drawable is not the same instance as the input.");
        }
        else {
            if (tintInfo.mHasTintList || tintInfo.mHasTintMode) {
                ColorStateList mTintList;
                if (tintInfo.mHasTintList) {
                    mTintList = tintInfo.mTintList;
                }
                else {
                    mTintList = null;
                }
                PorterDuff$Mode porterDuff$Mode;
                if (tintInfo.mHasTintMode) {
                    porterDuff$Mode = tintInfo.mTintMode;
                }
                else {
                    porterDuff$Mode = TintManager.DEFAULT_MODE;
                }
                drawable.setColorFilter((ColorFilter)createTintFilter(mTintList, porterDuff$Mode, array));
            }
            else {
                drawable.clearColorFilter();
            }
            if (Build$VERSION.SDK_INT <= 10) {
                drawable.invalidateSelf();
            }
        }
    }
    
    public Drawable getDrawable(final int n) {
        return this.getDrawable(n, false);
    }
    
    public Drawable getDrawable(final int n, final boolean b) {
        final Context context = this.mContextRef.get();
        Drawable drawable;
        if (context == null) {
            drawable = null;
        }
        else {
            final Drawable drawable2 = ContextCompat.getDrawable(context, n);
            if ((drawable = drawable2) != null) {
                Drawable mutate = drawable2;
                if (Build$VERSION.SDK_INT >= 8) {
                    mutate = drawable2.mutate();
                }
                final ColorStateList tintList = this.getTintList(n);
                if (tintList != null) {
                    final Drawable wrap = DrawableCompat.wrap(mutate);
                    DrawableCompat.setTintList(wrap, tintList);
                    final PorterDuff$Mode tintMode = this.getTintMode(n);
                    drawable = wrap;
                    if (tintMode != null) {
                        DrawableCompat.setTintMode(wrap, tintMode);
                        return wrap;
                    }
                }
                else {
                    if (n == R.drawable.abc_cab_background_top_material) {
                        return (Drawable)new LayerDrawable(new Drawable[] { this.getDrawable(R.drawable.abc_cab_background_internal_bg), this.getDrawable(R.drawable.abc_cab_background_top_mtrl_alpha) });
                    }
                    if (n == R.drawable.abc_seekbar_track_material) {
                        final LayerDrawable layerDrawable = (LayerDrawable)mutate;
                        setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), TintManager.DEFAULT_MODE);
                        setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), TintManager.DEFAULT_MODE);
                        setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), TintManager.DEFAULT_MODE);
                        return mutate;
                    }
                    drawable = mutate;
                    if (!this.tintDrawableUsingColorFilter(n, mutate)) {
                        drawable = mutate;
                        if (b) {
                            return null;
                        }
                    }
                }
            }
        }
        return drawable;
    }
    
    public final ColorStateList getTintList(final int n) {
        ColorStateList list = null;
        ColorStateList list2 = null;
        final Context context = this.mContextRef.get();
        if (context != null) {
            if (this.mTintLists != null) {
                list = (ColorStateList)this.mTintLists.get(n);
            }
            if ((list2 = list) == null) {
                if (n == R.drawable.abc_edit_text_material) {
                    list = this.createEditTextColorStateList(context);
                }
                else if (n == R.drawable.abc_switch_track_mtrl_alpha) {
                    list = this.createSwitchTrackColorStateList(context);
                }
                else if (n == R.drawable.abc_switch_thumb_material) {
                    list = this.createSwitchThumbColorStateList(context);
                }
                else if (n == R.drawable.abc_btn_default_mtrl_shape || n == R.drawable.abc_btn_borderless_material) {
                    list = this.createDefaultButtonColorStateList(context);
                }
                else if (n == R.drawable.abc_btn_colored_material) {
                    list = this.createColoredButtonColorStateList(context);
                }
                else if (n == R.drawable.abc_spinner_mtrl_am_alpha || n == R.drawable.abc_spinner_textfield_background_material) {
                    list = this.createSpinnerColorStateList(context);
                }
                else if (arrayContains(TintManager.TINT_COLOR_CONTROL_NORMAL, n)) {
                    list = ThemeUtils.getThemeAttrColorStateList(context, R.attr.colorControlNormal);
                }
                else if (arrayContains(TintManager.TINT_COLOR_CONTROL_STATE_LIST, n)) {
                    list = this.getDefaultColorStateList(context);
                }
                else if (arrayContains(TintManager.TINT_CHECKABLE_BUTTON_LIST, n)) {
                    list = this.createCheckableButtonColorStateList(context);
                }
                else if (n == R.drawable.abc_seekbar_thumb_material) {
                    list = this.createSeekbarThumbColorStateList(context);
                }
                if ((list2 = list) != null) {
                    if (this.mTintLists == null) {
                        this.mTintLists = (SparseArray<ColorStateList>)new SparseArray();
                    }
                    this.mTintLists.append(n, (Object)list);
                    return list;
                }
            }
        }
        return list2;
    }
    
    final PorterDuff$Mode getTintMode(final int n) {
        PorterDuff$Mode multiply = null;
        if (n == R.drawable.abc_switch_thumb_material) {
            multiply = PorterDuff$Mode.MULTIPLY;
        }
        return multiply;
    }
    
    public final boolean tintDrawableUsingColorFilter(final int n, final Drawable drawable) {
        final Context context = this.mContextRef.get();
        if (context != null) {
            final PorterDuff$Mode default_MODE = TintManager.DEFAULT_MODE;
            int n2 = 0;
            int n3 = 0;
            int round = -1;
            PorterDuff$Mode multiply;
            if (arrayContains(TintManager.COLORFILTER_TINT_COLOR_CONTROL_NORMAL, n)) {
                n3 = R.attr.colorControlNormal;
                n2 = 1;
                multiply = default_MODE;
            }
            else if (arrayContains(TintManager.COLORFILTER_COLOR_CONTROL_ACTIVATED, n)) {
                n3 = R.attr.colorControlActivated;
                n2 = 1;
                multiply = default_MODE;
            }
            else if (arrayContains(TintManager.COLORFILTER_COLOR_BACKGROUND_MULTIPLY, n)) {
                n3 = 16842801;
                n2 = 1;
                multiply = PorterDuff$Mode.MULTIPLY;
            }
            else {
                multiply = default_MODE;
                if (n == R.drawable.abc_list_divider_mtrl_alpha) {
                    n3 = 16842800;
                    n2 = 1;
                    round = Math.round(40.8f);
                    multiply = default_MODE;
                }
            }
            if (n2 != 0) {
                drawable.setColorFilter((ColorFilter)getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(context, n3), multiply));
                if (round != -1) {
                    drawable.setAlpha(round);
                }
                return true;
            }
        }
        return false;
    }
    
    private static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter>
    {
        public ColorFilterLruCache(final int n) {
            super(n);
        }
        
        private static int generateCacheKey(final int n, final PorterDuff$Mode porterDuff$Mode) {
            return (n + 31) * 31 + porterDuff$Mode.hashCode();
        }
        
        PorterDuffColorFilter get(final int n, final PorterDuff$Mode porterDuff$Mode) {
            return this.get(generateCacheKey(n, porterDuff$Mode));
        }
        
        PorterDuffColorFilter put(final int n, final PorterDuff$Mode porterDuff$Mode, final PorterDuffColorFilter porterDuffColorFilter) {
            return this.put(generateCacheKey(n, porterDuff$Mode), porterDuffColorFilter);
        }
    }
}
