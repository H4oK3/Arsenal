.class public final Landroid/support/v7/internal/widget/TintManager;
.super Ljava/lang/Object;
.source "TintManager.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroid/support/v7/internal/widget/TintManager$ColorFilterLruCache;
    }
.end annotation


# static fields
.field private static final COLORFILTER_COLOR_BACKGROUND_MULTIPLY:[I

.field private static final COLORFILTER_COLOR_CONTROL_ACTIVATED:[I

.field private static final COLORFILTER_TINT_COLOR_CONTROL_NORMAL:[I

.field private static final COLOR_FILTER_CACHE:Landroid/support/v7/internal/widget/TintManager$ColorFilterLruCache;

.field private static final DEBUG:Z = false

.field private static final DEFAULT_MODE:Landroid/graphics/PorterDuff$Mode;

.field private static final INSTANCE_CACHE:Ljava/util/WeakHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/WeakHashMap",
            "<",
            "Landroid/content/Context;",
            "Landroid/support/v7/internal/widget/TintManager;",
            ">;"
        }
    .end annotation
.end field

.field public static final SHOULD_BE_USED:Z

.field private static final TAG:Ljava/lang/String; = "TintManager"

.field private static final TINT_CHECKABLE_BUTTON_LIST:[I

.field private static final TINT_COLOR_CONTROL_NORMAL:[I

.field private static final TINT_COLOR_CONTROL_STATE_LIST:[I


# instance fields
.field private final mContextRef:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference",
            "<",
            "Landroid/content/Context;",
            ">;"
        }
    .end annotation
.end field

.field private mDefaultColorStateList:Landroid/content/res/ColorStateList;

.field private mTintLists:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray",
            "<",
            "Landroid/content/res/ColorStateList;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 8

    .prologue
    const/4 v7, 0x4

    const/4 v6, 0x3

    const/4 v5, 0x2

    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 51
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v3, 0x15

    if-ge v0, v3, :cond_0

    move v0, v1

    :goto_0
    sput-boolean v0, Landroid/support/v7/internal/widget/TintManager;->SHOULD_BE_USED:Z

    .line 55
    sget-object v0, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    sput-object v0, Landroid/support/v7/internal/widget/TintManager;->DEFAULT_MODE:Landroid/graphics/PorterDuff$Mode;

    .line 57
    new-instance v0, Ljava/util/WeakHashMap;

    invoke-direct {v0}, Ljava/util/WeakHashMap;-><init>()V

    sput-object v0, Landroid/support/v7/internal/widget/TintManager;->INSTANCE_CACHE:Ljava/util/WeakHashMap;

    .line 58
    new-instance v0, Landroid/support/v7/internal/widget/TintManager$ColorFilterLruCache;

    const/4 v3, 0x6

    invoke-direct {v0, v3}, Landroid/support/v7/internal/widget/TintManager$ColorFilterLruCache;-><init>(I)V

    sput-object v0, Landroid/support/v7/internal/widget/TintManager;->COLOR_FILTER_CACHE:Landroid/support/v7/internal/widget/TintManager$ColorFilterLruCache;

    .line 64
    new-array v0, v6, [I

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_textfield_search_default_mtrl_alpha:I

    aput v3, v0, v2

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_textfield_default_mtrl_alpha:I

    aput v3, v0, v1

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_ab_share_pack_mtrl_alpha:I

    aput v3, v0, v5

    sput-object v0, Landroid/support/v7/internal/widget/TintManager;->COLORFILTER_TINT_COLOR_CONTROL_NORMAL:[I

    .line 74
    const/16 v0, 0xc

    new-array v0, v0, [I

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_ic_ab_back_mtrl_am_alpha:I

    aput v3, v0, v2

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_ic_go_search_api_mtrl_alpha:I

    aput v3, v0, v1

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_ic_search_api_mtrl_alpha:I

    aput v3, v0, v5

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_ic_commit_search_api_mtrl_alpha:I

    aput v3, v0, v6

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_ic_clear_mtrl_alpha:I

    aput v3, v0, v7

    const/4 v3, 0x5

    sget v4, Landroid/support/v7/appcompat/R$drawable;->abc_ic_menu_share_mtrl_alpha:I

    aput v4, v0, v3

    const/4 v3, 0x6

    sget v4, Landroid/support/v7/appcompat/R$drawable;->abc_ic_menu_copy_mtrl_am_alpha:I

    aput v4, v0, v3

    const/4 v3, 0x7

    sget v4, Landroid/support/v7/appcompat/R$drawable;->abc_ic_menu_cut_mtrl_alpha:I

    aput v4, v0, v3

    const/16 v3, 0x8

    sget v4, Landroid/support/v7/appcompat/R$drawable;->abc_ic_menu_selectall_mtrl_alpha:I

    aput v4, v0, v3

    const/16 v3, 0x9

    sget v4, Landroid/support/v7/appcompat/R$drawable;->abc_ic_menu_paste_mtrl_am_alpha:I

    aput v4, v0, v3

    const/16 v3, 0xa

    sget v4, Landroid/support/v7/appcompat/R$drawable;->abc_ic_menu_moreoverflow_mtrl_alpha:I

    aput v4, v0, v3

    const/16 v3, 0xb

    sget v4, Landroid/support/v7/appcompat/R$drawable;->abc_ic_voice_search_api_mtrl_alpha:I

    aput v4, v0, v3

    sput-object v0, Landroid/support/v7/internal/widget/TintManager;->TINT_COLOR_CONTROL_NORMAL:[I

    .line 93
    new-array v0, v7, [I

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_textfield_activated_mtrl_alpha:I

    aput v3, v0, v2

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_textfield_search_activated_mtrl_alpha:I

    aput v3, v0, v1

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_cab_background_top_mtrl_alpha:I

    aput v3, v0, v5

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_text_cursor_material:I

    aput v3, v0, v6

    sput-object v0, Landroid/support/v7/internal/widget/TintManager;->COLORFILTER_COLOR_CONTROL_ACTIVATED:[I

    .line 104
    new-array v0, v6, [I

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_popup_background_mtrl_mult:I

    aput v3, v0, v2

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_cab_background_internal_bg:I

    aput v3, v0, v1

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_menu_hardkey_panel_mtrl_mult:I

    aput v3, v0, v5

    sput-object v0, Landroid/support/v7/internal/widget/TintManager;->COLORFILTER_COLOR_BACKGROUND_MULTIPLY:[I

    .line 114
    const/16 v0, 0xa

    new-array v0, v0, [I

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_edit_text_material:I

    aput v3, v0, v2

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_tab_indicator_material:I

    aput v3, v0, v1

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_textfield_search_material:I

    aput v3, v0, v5

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_spinner_mtrl_am_alpha:I

    aput v3, v0, v6

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_spinner_textfield_background_material:I

    aput v3, v0, v7

    const/4 v3, 0x5

    sget v4, Landroid/support/v7/appcompat/R$drawable;->abc_ratingbar_full_material:I

    aput v4, v0, v3

    const/4 v3, 0x6

    sget v4, Landroid/support/v7/appcompat/R$drawable;->abc_switch_track_mtrl_alpha:I

    aput v4, v0, v3

    const/4 v3, 0x7

    sget v4, Landroid/support/v7/appcompat/R$drawable;->abc_switch_thumb_material:I

    aput v4, v0, v3

    const/16 v3, 0x8

    sget v4, Landroid/support/v7/appcompat/R$drawable;->abc_btn_default_mtrl_shape:I

    aput v4, v0, v3

    const/16 v3, 0x9

    sget v4, Landroid/support/v7/appcompat/R$drawable;->abc_btn_borderless_material:I

    aput v4, v0, v3

    sput-object v0, Landroid/support/v7/internal/widget/TintManager;->TINT_COLOR_CONTROL_STATE_LIST:[I

    .line 132
    new-array v0, v5, [I

    sget v3, Landroid/support/v7/appcompat/R$drawable;->abc_btn_check_material:I

    aput v3, v0, v2

    sget v2, Landroid/support/v7/appcompat/R$drawable;->abc_btn_radio_material:I

    aput v2, v0, v1

    sput-object v0, Landroid/support/v7/internal/widget/TintManager;->TINT_CHECKABLE_BUTTON_LIST:[I

    return-void

    :cond_0
    move v0, v2

    .line 51
    goto/16 :goto_0
.end method

.method private constructor <init>(Landroid/content/Context;)V
    .locals 1
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 165
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 166
    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {v0, p1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Landroid/support/v7/internal/widget/TintManager;->mContextRef:Ljava/lang/ref/WeakReference;

    .line 167
    return-void
.end method

.method private static arrayContains([II)Z
    .locals 5
    .param p0, "array"    # [I
    .param p1, "value"    # I

    .prologue
    .line 264
    move-object v0, p0

    .local v0, "arr$":[I
    array-length v3, v0

    .local v3, "len$":I
    const/4 v1, 0x0

    .local v1, "i$":I
    :goto_0
    if-ge v1, v3, :cond_1

    aget v2, v0, v1

    .line 265
    .local v2, "id":I
    if-ne v2, p1, :cond_0

    .line 266
    const/4 v4, 0x1

    .line 269
    .end local v2    # "id":I
    :goto_1
    return v4

    .line 264
    .restart local v2    # "id":I
    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 269
    .end local v2    # "id":I
    :cond_1
    const/4 v4, 0x0

    goto :goto_1
.end method

.method private createButtonColorStateList(Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    .locals 6
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "baseColorAttr"    # I

    .prologue
    const/4 v5, 0x4

    .line 508
    new-array v4, v5, [[I

    .line 509
    .local v4, "states":[[I
    new-array v2, v5, [I

    .line 510
    .local v2, "colors":[I
    const/4 v3, 0x0

    .line 512
    .local v3, "i":I
    invoke-static {p1, p2}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v0

    .line 513
    .local v0, "baseColor":I
    sget v5, Landroid/support/v7/appcompat/R$attr;->colorControlHighlight:I

    invoke-static {p1, v5}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v1

    .line 516
    .local v1, "colorControlHighlight":I
    sget-object v5, Landroid/support/v7/internal/widget/ThemeUtils;->DISABLED_STATE_SET:[I

    aput-object v5, v4, v3

    .line 517
    sget v5, Landroid/support/v7/appcompat/R$attr;->colorButtonNormal:I

    invoke-static {p1, v5}, Landroid/support/v7/internal/widget/ThemeUtils;->getDisabledThemeAttrColor(Landroid/content/Context;I)I

    move-result v5

    aput v5, v2, v3

    .line 518
    add-int/lit8 v3, v3, 0x1

    .line 520
    sget-object v5, Landroid/support/v7/internal/widget/ThemeUtils;->PRESSED_STATE_SET:[I

    aput-object v5, v4, v3

    .line 521
    invoke-static {v1, v0}, Landroid/support/v4/graphics/ColorUtils;->compositeColors(II)I

    move-result v5

    aput v5, v2, v3

    .line 522
    add-int/lit8 v3, v3, 0x1

    .line 524
    sget-object v5, Landroid/support/v7/internal/widget/ThemeUtils;->FOCUSED_STATE_SET:[I

    aput-object v5, v4, v3

    .line 525
    invoke-static {v1, v0}, Landroid/support/v4/graphics/ColorUtils;->compositeColors(II)I

    move-result v5

    aput v5, v2, v3

    .line 526
    add-int/lit8 v3, v3, 0x1

    .line 529
    sget-object v5, Landroid/support/v7/internal/widget/ThemeUtils;->EMPTY_STATE_SET:[I

    aput-object v5, v4, v3

    .line 530
    aput v0, v2, v3

    .line 531
    add-int/lit8 v3, v3, 0x1

    .line 533
    new-instance v5, Landroid/content/res/ColorStateList;

    invoke-direct {v5, v4, v2}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    return-object v5
.end method

.method private createCheckableButtonColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;
    .locals 4
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    const/4 v3, 0x3

    .line 388
    new-array v2, v3, [[I

    .line 389
    .local v2, "states":[[I
    new-array v0, v3, [I

    .line 390
    .local v0, "colors":[I
    const/4 v1, 0x0

    .line 393
    .local v1, "i":I
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->DISABLED_STATE_SET:[I

    aput-object v3, v2, v1

    .line 394
    sget v3, Landroid/support/v7/appcompat/R$attr;->colorControlNormal:I

    invoke-static {p1, v3}, Landroid/support/v7/internal/widget/ThemeUtils;->getDisabledThemeAttrColor(Landroid/content/Context;I)I

    move-result v3

    aput v3, v0, v1

    .line 395
    add-int/lit8 v1, v1, 0x1

    .line 397
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->CHECKED_STATE_SET:[I

    aput-object v3, v2, v1

    .line 398
    sget v3, Landroid/support/v7/appcompat/R$attr;->colorControlActivated:I

    invoke-static {p1, v3}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v3

    aput v3, v0, v1

    .line 399
    add-int/lit8 v1, v1, 0x1

    .line 402
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->EMPTY_STATE_SET:[I

    aput-object v3, v2, v1

    .line 403
    sget v3, Landroid/support/v7/appcompat/R$attr;->colorControlNormal:I

    invoke-static {p1, v3}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v3

    aput v3, v0, v1

    .line 404
    add-int/lit8 v1, v1, 0x1

    .line 406
    new-instance v3, Landroid/content/res/ColorStateList;

    invoke-direct {v3, v2, v0}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    return-object v3
.end method

.method private createColoredButtonColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;
    .locals 1
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 504
    sget v0, Landroid/support/v7/appcompat/R$attr;->colorAccent:I

    invoke-direct {p0, p1, v0}, Landroid/support/v7/internal/widget/TintManager;->createButtonColorStateList(Landroid/content/Context;I)Landroid/content/res/ColorStateList;

    move-result-object v0

    return-object v0
.end method

.method private createDefaultButtonColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;
    .locals 1
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 500
    sget v0, Landroid/support/v7/appcompat/R$attr;->colorButtonNormal:I

    invoke-direct {p0, p1, v0}, Landroid/support/v7/internal/widget/TintManager;->createButtonColorStateList(Landroid/content/Context;I)Landroid/content/res/ColorStateList;

    move-result-object v0

    return-object v0
.end method

.method private createEditTextColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;
    .locals 4
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    const/4 v3, 0x3

    .line 478
    new-array v2, v3, [[I

    .line 479
    .local v2, "states":[[I
    new-array v0, v3, [I

    .line 480
    .local v0, "colors":[I
    const/4 v1, 0x0

    .line 483
    .local v1, "i":I
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->DISABLED_STATE_SET:[I

    aput-object v3, v2, v1

    .line 484
    sget v3, Landroid/support/v7/appcompat/R$attr;->colorControlNormal:I

    invoke-static {p1, v3}, Landroid/support/v7/internal/widget/ThemeUtils;->getDisabledThemeAttrColor(Landroid/content/Context;I)I

    move-result v3

    aput v3, v0, v1

    .line 485
    add-int/lit8 v1, v1, 0x1

    .line 487
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->NOT_PRESSED_OR_FOCUSED_STATE_SET:[I

    aput-object v3, v2, v1

    .line 488
    sget v3, Landroid/support/v7/appcompat/R$attr;->colorControlNormal:I

    invoke-static {p1, v3}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v3

    aput v3, v0, v1

    .line 489
    add-int/lit8 v1, v1, 0x1

    .line 492
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->EMPTY_STATE_SET:[I

    aput-object v3, v2, v1

    .line 493
    sget v3, Landroid/support/v7/appcompat/R$attr;->colorControlActivated:I

    invoke-static {p1, v3}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v3

    aput v3, v0, v1

    .line 494
    add-int/lit8 v1, v1, 0x1

    .line 496
    new-instance v3, Landroid/content/res/ColorStateList;

    invoke-direct {v3, v2, v0}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    return-object v3
.end method

.method private createSeekbarThumbColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;
    .locals 4
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    const/4 v3, 0x2

    .line 558
    new-array v2, v3, [[I

    .line 559
    .local v2, "states":[[I
    new-array v0, v3, [I

    .line 560
    .local v0, "colors":[I
    const/4 v1, 0x0

    .line 563
    .local v1, "i":I
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->DISABLED_STATE_SET:[I

    aput-object v3, v2, v1

    .line 564
    sget v3, Landroid/support/v7/appcompat/R$attr;->colorControlActivated:I

    invoke-static {p1, v3}, Landroid/support/v7/internal/widget/ThemeUtils;->getDisabledThemeAttrColor(Landroid/content/Context;I)I

    move-result v3

    aput v3, v0, v1

    .line 565
    add-int/lit8 v1, v1, 0x1

    .line 567
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->EMPTY_STATE_SET:[I

    aput-object v3, v2, v1

    .line 568
    sget v3, Landroid/support/v7/appcompat/R$attr;->colorControlActivated:I

    invoke-static {p1, v3}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v3

    aput v3, v0, v1

    .line 569
    add-int/lit8 v1, v1, 0x1

    .line 571
    new-instance v3, Landroid/content/res/ColorStateList;

    invoke-direct {v3, v2, v0}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    return-object v3
.end method

.method private createSpinnerColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;
    .locals 4
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    const/4 v3, 0x3

    .line 537
    new-array v2, v3, [[I

    .line 538
    .local v2, "states":[[I
    new-array v0, v3, [I

    .line 539
    .local v0, "colors":[I
    const/4 v1, 0x0

    .line 542
    .local v1, "i":I
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->DISABLED_STATE_SET:[I

    aput-object v3, v2, v1

    .line 543
    sget v3, Landroid/support/v7/appcompat/R$attr;->colorControlNormal:I

    invoke-static {p1, v3}, Landroid/support/v7/internal/widget/ThemeUtils;->getDisabledThemeAttrColor(Landroid/content/Context;I)I

    move-result v3

    aput v3, v0, v1

    .line 544
    add-int/lit8 v1, v1, 0x1

    .line 546
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->NOT_PRESSED_OR_FOCUSED_STATE_SET:[I

    aput-object v3, v2, v1

    .line 547
    sget v3, Landroid/support/v7/appcompat/R$attr;->colorControlNormal:I

    invoke-static {p1, v3}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v3

    aput v3, v0, v1

    .line 548
    add-int/lit8 v1, v1, 0x1

    .line 550
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->EMPTY_STATE_SET:[I

    aput-object v3, v2, v1

    .line 551
    sget v3, Landroid/support/v7/appcompat/R$attr;->colorControlActivated:I

    invoke-static {p1, v3}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v3

    aput v3, v0, v1

    .line 552
    add-int/lit8 v1, v1, 0x1

    .line 554
    new-instance v3, Landroid/content/res/ColorStateList;

    invoke-direct {v3, v2, v0}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    return-object v3
.end method

.method private createSwitchThumbColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;
    .locals 6
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    const/4 v4, 0x3

    .line 432
    new-array v2, v4, [[I

    .line 433
    .local v2, "states":[[I
    new-array v0, v4, [I

    .line 434
    .local v0, "colors":[I
    const/4 v1, 0x0

    .line 436
    .local v1, "i":I
    sget v4, Landroid/support/v7/appcompat/R$attr;->colorSwitchThumbNormal:I

    invoke-static {p1, v4}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColorStateList(Landroid/content/Context;I)Landroid/content/res/ColorStateList;

    move-result-object v3

    .line 439
    .local v3, "thumbColor":Landroid/content/res/ColorStateList;
    if-eqz v3, :cond_0

    invoke-virtual {v3}, Landroid/content/res/ColorStateList;->isStateful()Z

    move-result v4

    if-eqz v4, :cond_0

    .line 444
    sget-object v4, Landroid/support/v7/internal/widget/ThemeUtils;->DISABLED_STATE_SET:[I

    aput-object v4, v2, v1

    .line 445
    aget-object v4, v2, v1

    const/4 v5, 0x0

    invoke-virtual {v3, v4, v5}, Landroid/content/res/ColorStateList;->getColorForState([II)I

    move-result v4

    aput v4, v0, v1

    .line 446
    add-int/lit8 v1, v1, 0x1

    .line 448
    sget-object v4, Landroid/support/v7/internal/widget/ThemeUtils;->CHECKED_STATE_SET:[I

    aput-object v4, v2, v1

    .line 449
    sget v4, Landroid/support/v7/appcompat/R$attr;->colorControlActivated:I

    invoke-static {p1, v4}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v4

    aput v4, v0, v1

    .line 450
    add-int/lit8 v1, v1, 0x1

    .line 453
    sget-object v4, Landroid/support/v7/internal/widget/ThemeUtils;->EMPTY_STATE_SET:[I

    aput-object v4, v2, v1

    .line 454
    invoke-virtual {v3}, Landroid/content/res/ColorStateList;->getDefaultColor()I

    move-result v4

    aput v4, v0, v1

    .line 455
    add-int/lit8 v1, v1, 0x1

    .line 474
    :goto_0
    new-instance v4, Landroid/content/res/ColorStateList;

    invoke-direct {v4, v2, v0}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    return-object v4

    .line 460
    :cond_0
    sget-object v4, Landroid/support/v7/internal/widget/ThemeUtils;->DISABLED_STATE_SET:[I

    aput-object v4, v2, v1

    .line 461
    sget v4, Landroid/support/v7/appcompat/R$attr;->colorSwitchThumbNormal:I

    invoke-static {p1, v4}, Landroid/support/v7/internal/widget/ThemeUtils;->getDisabledThemeAttrColor(Landroid/content/Context;I)I

    move-result v4

    aput v4, v0, v1

    .line 462
    add-int/lit8 v1, v1, 0x1

    .line 464
    sget-object v4, Landroid/support/v7/internal/widget/ThemeUtils;->CHECKED_STATE_SET:[I

    aput-object v4, v2, v1

    .line 465
    sget v4, Landroid/support/v7/appcompat/R$attr;->colorControlActivated:I

    invoke-static {p1, v4}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v4

    aput v4, v0, v1

    .line 466
    add-int/lit8 v1, v1, 0x1

    .line 469
    sget-object v4, Landroid/support/v7/internal/widget/ThemeUtils;->EMPTY_STATE_SET:[I

    aput-object v4, v2, v1

    .line 470
    sget v4, Landroid/support/v7/appcompat/R$attr;->colorSwitchThumbNormal:I

    invoke-static {p1, v4}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v4

    aput v4, v0, v1

    .line 471
    add-int/lit8 v1, v1, 0x1

    goto :goto_0
.end method

.method private createSwitchTrackColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;
    .locals 6
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    const v5, 0x1010030

    const/4 v3, 0x3

    const v4, 0x3e99999a    # 0.3f

    .line 410
    new-array v2, v3, [[I

    .line 411
    .local v2, "states":[[I
    new-array v0, v3, [I

    .line 412
    .local v0, "colors":[I
    const/4 v1, 0x0

    .line 415
    .local v1, "i":I
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->DISABLED_STATE_SET:[I

    aput-object v3, v2, v1

    .line 416
    const v3, 0x3dcccccd    # 0.1f

    invoke-static {p1, v5, v3}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;IF)I

    move-result v3

    aput v3, v0, v1

    .line 417
    add-int/lit8 v1, v1, 0x1

    .line 419
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->CHECKED_STATE_SET:[I

    aput-object v3, v2, v1

    .line 420
    sget v3, Landroid/support/v7/appcompat/R$attr;->colorControlActivated:I

    invoke-static {p1, v3, v4}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;IF)I

    move-result v3

    aput v3, v0, v1

    .line 421
    add-int/lit8 v1, v1, 0x1

    .line 424
    sget-object v3, Landroid/support/v7/internal/widget/ThemeUtils;->EMPTY_STATE_SET:[I

    aput-object v3, v2, v1

    .line 425
    invoke-static {p1, v5, v4}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;IF)I

    move-result v3

    aput v3, v0, v1

    .line 426
    add-int/lit8 v1, v1, 0x1

    .line 428
    new-instance v3, Landroid/content/res/ColorStateList;

    invoke-direct {v3, v2, v0}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    return-object v3
.end method

.method private static createTintFilter(Landroid/content/res/ColorStateList;Landroid/graphics/PorterDuff$Mode;[I)Landroid/graphics/PorterDuffColorFilter;
    .locals 2
    .param p0, "tint"    # Landroid/content/res/ColorStateList;
    .param p1, "tintMode"    # Landroid/graphics/PorterDuff$Mode;
    .param p2, "state"    # [I

    .prologue
    .line 646
    if-eqz p0, :cond_0

    if-nez p1, :cond_1

    .line 647
    :cond_0
    const/4 v1, 0x0

    .line 650
    :goto_0
    return-object v1

    .line 649
    :cond_1
    const/4 v1, 0x0

    invoke-virtual {p0, p2, v1}, Landroid/content/res/ColorStateList;->getColorForState([II)I

    move-result v0

    .line 650
    .local v0, "color":I
    invoke-static {v0, p1}, Landroid/support/v7/internal/widget/TintManager;->getPorterDuffColorFilter(ILandroid/graphics/PorterDuff$Mode;)Landroid/graphics/PorterDuffColorFilter;

    move-result-object v1

    goto :goto_0
.end method

.method public static get(Landroid/content/Context;)Landroid/support/v7/internal/widget/TintManager;
    .locals 2
    .param p0, "context"    # Landroid/content/Context;

    .prologue
    .line 157
    sget-object v1, Landroid/support/v7/internal/widget/TintManager;->INSTANCE_CACHE:Ljava/util/WeakHashMap;

    invoke-virtual {v1, p0}, Ljava/util/WeakHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/support/v7/internal/widget/TintManager;

    .line 158
    .local v0, "tm":Landroid/support/v7/internal/widget/TintManager;
    if-nez v0, :cond_0

    .line 159
    new-instance v0, Landroid/support/v7/internal/widget/TintManager;

    .end local v0    # "tm":Landroid/support/v7/internal/widget/TintManager;
    invoke-direct {v0, p0}, Landroid/support/v7/internal/widget/TintManager;-><init>(Landroid/content/Context;)V

    .line 160
    .restart local v0    # "tm":Landroid/support/v7/internal/widget/TintManager;
    sget-object v1, Landroid/support/v7/internal/widget/TintManager;->INSTANCE_CACHE:Ljava/util/WeakHashMap;

    invoke-virtual {v1, p0, v0}, Ljava/util/WeakHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 162
    :cond_0
    return-object v0
.end method

.method private getDefaultColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;
    .locals 7
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    const/4 v6, 0x7

    .line 338
    iget-object v5, p0, Landroid/support/v7/internal/widget/TintManager;->mDefaultColorStateList:Landroid/content/res/ColorStateList;

    if-nez v5, :cond_0

    .line 344
    sget v5, Landroid/support/v7/appcompat/R$attr;->colorControlNormal:I

    invoke-static {p1, v5}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v1

    .line 345
    .local v1, "colorControlNormal":I
    sget v5, Landroid/support/v7/appcompat/R$attr;->colorControlActivated:I

    invoke-static {p1, v5}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v0

    .line 348
    .local v0, "colorControlActivated":I
    new-array v4, v6, [[I

    .line 349
    .local v4, "states":[[I
    new-array v2, v6, [I

    .line 350
    .local v2, "colors":[I
    const/4 v3, 0x0

    .line 353
    .local v3, "i":I
    sget-object v5, Landroid/support/v7/internal/widget/ThemeUtils;->DISABLED_STATE_SET:[I

    aput-object v5, v4, v3

    .line 354
    sget v5, Landroid/support/v7/appcompat/R$attr;->colorControlNormal:I

    invoke-static {p1, v5}, Landroid/support/v7/internal/widget/ThemeUtils;->getDisabledThemeAttrColor(Landroid/content/Context;I)I

    move-result v5

    aput v5, v2, v3

    .line 355
    add-int/lit8 v3, v3, 0x1

    .line 357
    sget-object v5, Landroid/support/v7/internal/widget/ThemeUtils;->FOCUSED_STATE_SET:[I

    aput-object v5, v4, v3

    .line 358
    aput v0, v2, v3

    .line 359
    add-int/lit8 v3, v3, 0x1

    .line 361
    sget-object v5, Landroid/support/v7/internal/widget/ThemeUtils;->ACTIVATED_STATE_SET:[I

    aput-object v5, v4, v3

    .line 362
    aput v0, v2, v3

    .line 363
    add-int/lit8 v3, v3, 0x1

    .line 365
    sget-object v5, Landroid/support/v7/internal/widget/ThemeUtils;->PRESSED_STATE_SET:[I

    aput-object v5, v4, v3

    .line 366
    aput v0, v2, v3

    .line 367
    add-int/lit8 v3, v3, 0x1

    .line 369
    sget-object v5, Landroid/support/v7/internal/widget/ThemeUtils;->CHECKED_STATE_SET:[I

    aput-object v5, v4, v3

    .line 370
    aput v0, v2, v3

    .line 371
    add-int/lit8 v3, v3, 0x1

    .line 373
    sget-object v5, Landroid/support/v7/internal/widget/ThemeUtils;->SELECTED_STATE_SET:[I

    aput-object v5, v4, v3

    .line 374
    aput v0, v2, v3

    .line 375
    add-int/lit8 v3, v3, 0x1

    .line 378
    sget-object v5, Landroid/support/v7/internal/widget/ThemeUtils;->EMPTY_STATE_SET:[I

    aput-object v5, v4, v3

    .line 379
    aput v1, v2, v3

    .line 380
    add-int/lit8 v3, v3, 0x1

    .line 382
    new-instance v5, Landroid/content/res/ColorStateList;

    invoke-direct {v5, v4, v2}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    iput-object v5, p0, Landroid/support/v7/internal/widget/TintManager;->mDefaultColorStateList:Landroid/content/res/ColorStateList;

    .line 384
    .end local v0    # "colorControlActivated":I
    .end local v1    # "colorControlNormal":I
    .end local v2    # "colors":[I
    .end local v3    # "i":I
    .end local v4    # "states":[[I
    :cond_0
    iget-object v5, p0, Landroid/support/v7/internal/widget/TintManager;->mDefaultColorStateList:Landroid/content/res/ColorStateList;

    return-object v5
.end method

.method public static getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;
    .locals 1
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "resId"    # I

    .prologue
    .line 146
    invoke-static {p1}, Landroid/support/v7/internal/widget/TintManager;->isInTintList(I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 147
    invoke-static {p0}, Landroid/support/v7/internal/widget/TintManager;->get(Landroid/content/Context;)Landroid/support/v7/internal/widget/TintManager;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/support/v7/internal/widget/TintManager;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 149
    :goto_0
    return-object v0

    :cond_0
    invoke-static {p0, p1}, Landroid/support/v4/content/ContextCompat;->getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    goto :goto_0
.end method

.method private static getPorterDuffColorFilter(ILandroid/graphics/PorterDuff$Mode;)Landroid/graphics/PorterDuffColorFilter;
    .locals 2
    .param p0, "color"    # I
    .param p1, "mode"    # Landroid/graphics/PorterDuff$Mode;

    .prologue
    .line 655
    sget-object v1, Landroid/support/v7/internal/widget/TintManager;->COLOR_FILTER_CACHE:Landroid/support/v7/internal/widget/TintManager$ColorFilterLruCache;

    invoke-virtual {v1, p0, p1}, Landroid/support/v7/internal/widget/TintManager$ColorFilterLruCache;->get(ILandroid/graphics/PorterDuff$Mode;)Landroid/graphics/PorterDuffColorFilter;

    move-result-object v0

    .line 657
    .local v0, "filter":Landroid/graphics/PorterDuffColorFilter;
    if-nez v0, :cond_0

    .line 659
    new-instance v0, Landroid/graphics/PorterDuffColorFilter;

    .end local v0    # "filter":Landroid/graphics/PorterDuffColorFilter;
    invoke-direct {v0, p0, p1}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    .line 660
    .restart local v0    # "filter":Landroid/graphics/PorterDuffColorFilter;
    sget-object v1, Landroid/support/v7/internal/widget/TintManager;->COLOR_FILTER_CACHE:Landroid/support/v7/internal/widget/TintManager$ColorFilterLruCache;

    invoke-virtual {v1, p0, p1, v0}, Landroid/support/v7/internal/widget/TintManager$ColorFilterLruCache;->put(ILandroid/graphics/PorterDuff$Mode;Landroid/graphics/PorterDuffColorFilter;)Landroid/graphics/PorterDuffColorFilter;

    .line 663
    :cond_0
    return-object v0
.end method

.method private static isInTintList(I)Z
    .locals 1
    .param p0, "drawableId"    # I

    .prologue
    .line 273
    sget-object v0, Landroid/support/v7/internal/widget/TintManager;->TINT_COLOR_CONTROL_NORMAL:[I

    invoke-static {v0, p0}, Landroid/support/v7/internal/widget/TintManager;->arrayContains([II)Z

    move-result v0

    if-nez v0, :cond_0

    sget-object v0, Landroid/support/v7/internal/widget/TintManager;->COLORFILTER_TINT_COLOR_CONTROL_NORMAL:[I

    invoke-static {v0, p0}, Landroid/support/v7/internal/widget/TintManager;->arrayContains([II)Z

    move-result v0

    if-nez v0, :cond_0

    sget-object v0, Landroid/support/v7/internal/widget/TintManager;->COLORFILTER_COLOR_CONTROL_ACTIVATED:[I

    invoke-static {v0, p0}, Landroid/support/v7/internal/widget/TintManager;->arrayContains([II)Z

    move-result v0

    if-nez v0, :cond_0

    sget-object v0, Landroid/support/v7/internal/widget/TintManager;->TINT_COLOR_CONTROL_STATE_LIST:[I

    invoke-static {v0, p0}, Landroid/support/v7/internal/widget/TintManager;->arrayContains([II)Z

    move-result v0

    if-nez v0, :cond_0

    sget-object v0, Landroid/support/v7/internal/widget/TintManager;->COLORFILTER_COLOR_BACKGROUND_MULTIPLY:[I

    invoke-static {v0, p0}, Landroid/support/v7/internal/widget/TintManager;->arrayContains([II)Z

    move-result v0

    if-nez v0, :cond_0

    sget-object v0, Landroid/support/v7/internal/widget/TintManager;->TINT_CHECKABLE_BUTTON_LIST:[I

    invoke-static {v0, p0}, Landroid/support/v7/internal/widget/TintManager;->arrayContains([II)Z

    move-result v0

    if-nez v0, :cond_0

    sget v0, Landroid/support/v7/appcompat/R$drawable;->abc_cab_background_top_material:I

    if-ne p0, v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private static setPorterDuffColorFilter(Landroid/graphics/drawable/Drawable;ILandroid/graphics/PorterDuff$Mode;)V
    .locals 1
    .param p0, "d"    # Landroid/graphics/drawable/Drawable;
    .param p1, "color"    # I
    .param p2, "mode"    # Landroid/graphics/PorterDuff$Mode;

    .prologue
    .line 667
    if-nez p2, :cond_0

    sget-object p2, Landroid/support/v7/internal/widget/TintManager;->DEFAULT_MODE:Landroid/graphics/PorterDuff$Mode;

    .end local p2    # "mode":Landroid/graphics/PorterDuff$Mode;
    :cond_0
    invoke-static {p1, p2}, Landroid/support/v7/internal/widget/TintManager;->getPorterDuffColorFilter(ILandroid/graphics/PorterDuff$Mode;)Landroid/graphics/PorterDuffColorFilter;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 668
    return-void
.end method

.method private static shouldMutateBackground(Landroid/graphics/drawable/Drawable;)Z
    .locals 10
    .param p0, "drawable"    # Landroid/graphics/drawable/Drawable;

    .prologue
    const/16 v9, 0x10

    const/4 v7, 0x0

    const/4 v6, 0x1

    .line 619
    sget v8, Landroid/os/Build$VERSION;->SDK_INT:I

    if-lt v8, v9, :cond_1

    .line 641
    :cond_0
    :goto_0
    return v6

    .line 624
    :cond_1
    instance-of v8, p0, Landroid/graphics/drawable/LayerDrawable;

    if-eqz v8, :cond_2

    .line 625
    sget v8, Landroid/os/Build$VERSION;->SDK_INT:I

    if-ge v8, v9, :cond_0

    move v6, v7

    goto :goto_0

    .line 626
    :cond_2
    instance-of v8, p0, Landroid/graphics/drawable/InsetDrawable;

    if-eqz v8, :cond_3

    .line 627
    sget v8, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v9, 0xe

    if-ge v8, v9, :cond_0

    move v6, v7

    goto :goto_0

    .line 628
    :cond_3
    instance-of v8, p0, Landroid/graphics/drawable/DrawableContainer;

    if-eqz v8, :cond_0

    .line 630
    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->getConstantState()Landroid/graphics/drawable/Drawable$ConstantState;

    move-result-object v5

    .line 631
    .local v5, "state":Landroid/graphics/drawable/Drawable$ConstantState;
    instance-of v8, v5, Landroid/graphics/drawable/DrawableContainer$DrawableContainerState;

    if-eqz v8, :cond_0

    move-object v2, v5

    .line 632
    check-cast v2, Landroid/graphics/drawable/DrawableContainer$DrawableContainerState;

    .line 634
    .local v2, "containerState":Landroid/graphics/drawable/DrawableContainer$DrawableContainerState;
    invoke-virtual {v2}, Landroid/graphics/drawable/DrawableContainer$DrawableContainerState;->getChildren()[Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .local v0, "arr$":[Landroid/graphics/drawable/Drawable;
    array-length v4, v0

    .local v4, "len$":I
    const/4 v3, 0x0

    .local v3, "i$":I
    :goto_1
    if-ge v3, v4, :cond_0

    aget-object v1, v0, v3

    .line 635
    .local v1, "child":Landroid/graphics/drawable/Drawable;
    invoke-static {v1}, Landroid/support/v7/internal/widget/TintManager;->shouldMutateBackground(Landroid/graphics/drawable/Drawable;)Z

    move-result v8

    if-nez v8, :cond_4

    move v6, v7

    .line 636
    goto :goto_0

    .line 634
    :cond_4
    add-int/lit8 v3, v3, 0x1

    goto :goto_1
.end method

.method public static tintDrawable(Landroid/graphics/drawable/Drawable;Landroid/support/v7/internal/widget/TintInfo;[I)V
    .locals 2
    .param p0, "drawable"    # Landroid/graphics/drawable/Drawable;
    .param p1, "tint"    # Landroid/support/v7/internal/widget/TintInfo;
    .param p2, "state"    # [I

    .prologue
    .line 597
    invoke-static {p0}, Landroid/support/v7/internal/widget/TintManager;->shouldMutateBackground(Landroid/graphics/drawable/Drawable;)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    if-eq v0, p0, :cond_1

    .line 598
    const-string v0, "TintManager"

    const-string v1, "Mutated drawable is not the same instance as the input."

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 616
    :cond_0
    :goto_0
    return-void

    .line 602
    :cond_1
    iget-boolean v0, p1, Landroid/support/v7/internal/widget/TintInfo;->mHasTintList:Z

    if-nez v0, :cond_2

    iget-boolean v0, p1, Landroid/support/v7/internal/widget/TintInfo;->mHasTintMode:Z

    if-eqz v0, :cond_5

    .line 603
    :cond_2
    iget-boolean v0, p1, Landroid/support/v7/internal/widget/TintInfo;->mHasTintList:Z

    if-eqz v0, :cond_3

    iget-object v0, p1, Landroid/support/v7/internal/widget/TintInfo;->mTintList:Landroid/content/res/ColorStateList;

    :goto_1
    iget-boolean v1, p1, Landroid/support/v7/internal/widget/TintInfo;->mHasTintMode:Z

    if-eqz v1, :cond_4

    iget-object v1, p1, Landroid/support/v7/internal/widget/TintInfo;->mTintMode:Landroid/graphics/PorterDuff$Mode;

    :goto_2
    invoke-static {v0, v1, p2}, Landroid/support/v7/internal/widget/TintManager;->createTintFilter(Landroid/content/res/ColorStateList;Landroid/graphics/PorterDuff$Mode;[I)Landroid/graphics/PorterDuffColorFilter;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 611
    :goto_3
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0xa

    if-gt v0, v1, :cond_0

    .line 614
    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->invalidateSelf()V

    goto :goto_0

    .line 603
    :cond_3
    const/4 v0, 0x0

    goto :goto_1

    :cond_4
    sget-object v1, Landroid/support/v7/internal/widget/TintManager;->DEFAULT_MODE:Landroid/graphics/PorterDuff$Mode;

    goto :goto_2

    .line 608
    :cond_5
    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->clearColorFilter()V

    goto :goto_3
.end method


# virtual methods
.method public getDrawable(I)Landroid/graphics/drawable/Drawable;
    .locals 1
    .param p1, "resId"    # I

    .prologue
    .line 170
    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Landroid/support/v7/internal/widget/TintManager;->getDrawable(IZ)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    return-object v0
.end method

.method public getDrawable(IZ)Landroid/graphics/drawable/Drawable;
    .locals 9
    .param p1, "resId"    # I
    .param p2, "failIfNotKnown"    # Z

    .prologue
    .line 174
    iget-object v6, p0, Landroid/support/v7/internal/widget/TintManager;->mContextRef:Ljava/lang/ref/WeakReference;

    invoke-virtual {v6}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/content/Context;

    .line 175
    .local v0, "context":Landroid/content/Context;
    if-nez v0, :cond_1

    const/4 v1, 0x0

    .line 218
    :cond_0
    :goto_0
    return-object v1

    .line 177
    :cond_1
    invoke-static {v0, p1}, Landroid/support/v4/content/ContextCompat;->getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 179
    .local v1, "drawable":Landroid/graphics/drawable/Drawable;
    if-eqz v1, :cond_0

    .line 180
    sget v6, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v7, 0x8

    if-lt v6, v7, :cond_2

    .line 182
    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 185
    :cond_2
    invoke-virtual {p0, p1}, Landroid/support/v7/internal/widget/TintManager;->getTintList(I)Landroid/content/res/ColorStateList;

    move-result-object v3

    .line 186
    .local v3, "tintList":Landroid/content/res/ColorStateList;
    if-eqz v3, :cond_3

    .line 188
    invoke-static {v1}, Landroid/support/v4/graphics/drawable/DrawableCompat;->wrap(Landroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 189
    invoke-static {v1, v3}, Landroid/support/v4/graphics/drawable/DrawableCompat;->setTintList(Landroid/graphics/drawable/Drawable;Landroid/content/res/ColorStateList;)V

    .line 192
    invoke-virtual {p0, p1}, Landroid/support/v7/internal/widget/TintManager;->getTintMode(I)Landroid/graphics/PorterDuff$Mode;

    move-result-object v4

    .line 193
    .local v4, "tintMode":Landroid/graphics/PorterDuff$Mode;
    if-eqz v4, :cond_0

    .line 194
    invoke-static {v1, v4}, Landroid/support/v4/graphics/drawable/DrawableCompat;->setTintMode(Landroid/graphics/drawable/Drawable;Landroid/graphics/PorterDuff$Mode;)V

    goto :goto_0

    .line 196
    .end local v4    # "tintMode":Landroid/graphics/PorterDuff$Mode;
    :cond_3
    sget v6, Landroid/support/v7/appcompat/R$drawable;->abc_cab_background_top_material:I

    if-ne p1, v6, :cond_4

    .line 197
    new-instance v1, Landroid/graphics/drawable/LayerDrawable;

    .end local v1    # "drawable":Landroid/graphics/drawable/Drawable;
    const/4 v6, 0x2

    new-array v6, v6, [Landroid/graphics/drawable/Drawable;

    const/4 v7, 0x0

    sget v8, Landroid/support/v7/appcompat/R$drawable;->abc_cab_background_internal_bg:I

    invoke-virtual {p0, v8}, Landroid/support/v7/internal/widget/TintManager;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v8

    aput-object v8, v6, v7

    const/4 v7, 0x1

    sget v8, Landroid/support/v7/appcompat/R$drawable;->abc_cab_background_top_mtrl_alpha:I

    invoke-virtual {p0, v8}, Landroid/support/v7/internal/widget/TintManager;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v8

    aput-object v8, v6, v7

    invoke-direct {v1, v6}, Landroid/graphics/drawable/LayerDrawable;-><init>([Landroid/graphics/drawable/Drawable;)V

    goto :goto_0

    .line 201
    .restart local v1    # "drawable":Landroid/graphics/drawable/Drawable;
    :cond_4
    sget v6, Landroid/support/v7/appcompat/R$drawable;->abc_seekbar_track_material:I

    if-ne p1, v6, :cond_5

    move-object v2, v1

    .line 202
    check-cast v2, Landroid/graphics/drawable/LayerDrawable;

    .line 203
    .local v2, "ld":Landroid/graphics/drawable/LayerDrawable;
    const/high16 v6, 0x1020000

    invoke-virtual {v2, v6}, Landroid/graphics/drawable/LayerDrawable;->findDrawableByLayerId(I)Landroid/graphics/drawable/Drawable;

    move-result-object v6

    sget v7, Landroid/support/v7/appcompat/R$attr;->colorControlNormal:I

    invoke-static {v0, v7}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v7

    sget-object v8, Landroid/support/v7/internal/widget/TintManager;->DEFAULT_MODE:Landroid/graphics/PorterDuff$Mode;

    invoke-static {v6, v7, v8}, Landroid/support/v7/internal/widget/TintManager;->setPorterDuffColorFilter(Landroid/graphics/drawable/Drawable;ILandroid/graphics/PorterDuff$Mode;)V

    .line 205
    const v6, 0x102000f

    invoke-virtual {v2, v6}, Landroid/graphics/drawable/LayerDrawable;->findDrawableByLayerId(I)Landroid/graphics/drawable/Drawable;

    move-result-object v6

    sget v7, Landroid/support/v7/appcompat/R$attr;->colorControlNormal:I

    invoke-static {v0, v7}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v7

    sget-object v8, Landroid/support/v7/internal/widget/TintManager;->DEFAULT_MODE:Landroid/graphics/PorterDuff$Mode;

    invoke-static {v6, v7, v8}, Landroid/support/v7/internal/widget/TintManager;->setPorterDuffColorFilter(Landroid/graphics/drawable/Drawable;ILandroid/graphics/PorterDuff$Mode;)V

    .line 207
    const v6, 0x102000d

    invoke-virtual {v2, v6}, Landroid/graphics/drawable/LayerDrawable;->findDrawableByLayerId(I)Landroid/graphics/drawable/Drawable;

    move-result-object v6

    sget v7, Landroid/support/v7/appcompat/R$attr;->colorControlActivated:I

    invoke-static {v0, v7}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v7

    sget-object v8, Landroid/support/v7/internal/widget/TintManager;->DEFAULT_MODE:Landroid/graphics/PorterDuff$Mode;

    invoke-static {v6, v7, v8}, Landroid/support/v7/internal/widget/TintManager;->setPorterDuffColorFilter(Landroid/graphics/drawable/Drawable;ILandroid/graphics/PorterDuff$Mode;)V

    goto/16 :goto_0

    .line 210
    .end local v2    # "ld":Landroid/graphics/drawable/LayerDrawable;
    :cond_5
    invoke-virtual {p0, p1, v1}, Landroid/support/v7/internal/widget/TintManager;->tintDrawableUsingColorFilter(ILandroid/graphics/drawable/Drawable;)Z

    move-result v5

    .line 211
    .local v5, "usedColorFilter":Z
    if-nez v5, :cond_0

    if-eqz p2, :cond_0

    .line 214
    const/4 v1, 0x0

    goto/16 :goto_0
.end method

.method public final getTintList(I)Landroid/content/res/ColorStateList;
    .locals 3
    .param p1, "resId"    # I

    .prologue
    const/4 v1, 0x0

    .line 293
    iget-object v2, p0, Landroid/support/v7/internal/widget/TintManager;->mContextRef:Ljava/lang/ref/WeakReference;

    invoke-virtual {v2}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/content/Context;

    .line 294
    .local v0, "context":Landroid/content/Context;
    if-nez v0, :cond_1

    .line 334
    :cond_0
    :goto_0
    return-object v1

    .line 297
    :cond_1
    iget-object v2, p0, Landroid/support/v7/internal/widget/TintManager;->mTintLists:Landroid/util/SparseArray;

    if-eqz v2, :cond_2

    iget-object v2, p0, Landroid/support/v7/internal/widget/TintManager;->mTintLists:Landroid/util/SparseArray;

    invoke-virtual {v2, p1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/content/res/ColorStateList;

    move-object v1, v2

    .line 299
    .local v1, "tint":Landroid/content/res/ColorStateList;
    :cond_2
    if-nez v1, :cond_0

    .line 301
    sget v2, Landroid/support/v7/appcompat/R$drawable;->abc_edit_text_material:I

    if-ne p1, v2, :cond_5

    .line 302
    invoke-direct {p0, v0}, Landroid/support/v7/internal/widget/TintManager;->createEditTextColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;

    move-result-object v1

    .line 325
    :cond_3
    :goto_1
    if-eqz v1, :cond_0

    .line 326
    iget-object v2, p0, Landroid/support/v7/internal/widget/TintManager;->mTintLists:Landroid/util/SparseArray;

    if-nez v2, :cond_4

    .line 328
    new-instance v2, Landroid/util/SparseArray;

    invoke-direct {v2}, Landroid/util/SparseArray;-><init>()V

    iput-object v2, p0, Landroid/support/v7/internal/widget/TintManager;->mTintLists:Landroid/util/SparseArray;

    .line 331
    :cond_4
    iget-object v2, p0, Landroid/support/v7/internal/widget/TintManager;->mTintLists:Landroid/util/SparseArray;

    invoke-virtual {v2, p1, v1}, Landroid/util/SparseArray;->append(ILjava/lang/Object;)V

    goto :goto_0

    .line 303
    :cond_5
    sget v2, Landroid/support/v7/appcompat/R$drawable;->abc_switch_track_mtrl_alpha:I

    if-ne p1, v2, :cond_6

    .line 304
    invoke-direct {p0, v0}, Landroid/support/v7/internal/widget/TintManager;->createSwitchTrackColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;

    move-result-object v1

    goto :goto_1

    .line 305
    :cond_6
    sget v2, Landroid/support/v7/appcompat/R$drawable;->abc_switch_thumb_material:I

    if-ne p1, v2, :cond_7

    .line 306
    invoke-direct {p0, v0}, Landroid/support/v7/internal/widget/TintManager;->createSwitchThumbColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;

    move-result-object v1

    goto :goto_1

    .line 307
    :cond_7
    sget v2, Landroid/support/v7/appcompat/R$drawable;->abc_btn_default_mtrl_shape:I

    if-eq p1, v2, :cond_8

    sget v2, Landroid/support/v7/appcompat/R$drawable;->abc_btn_borderless_material:I

    if-ne p1, v2, :cond_9

    .line 309
    :cond_8
    invoke-direct {p0, v0}, Landroid/support/v7/internal/widget/TintManager;->createDefaultButtonColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;

    move-result-object v1

    goto :goto_1

    .line 310
    :cond_9
    sget v2, Landroid/support/v7/appcompat/R$drawable;->abc_btn_colored_material:I

    if-ne p1, v2, :cond_a

    .line 311
    invoke-direct {p0, v0}, Landroid/support/v7/internal/widget/TintManager;->createColoredButtonColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;

    move-result-object v1

    goto :goto_1

    .line 312
    :cond_a
    sget v2, Landroid/support/v7/appcompat/R$drawable;->abc_spinner_mtrl_am_alpha:I

    if-eq p1, v2, :cond_b

    sget v2, Landroid/support/v7/appcompat/R$drawable;->abc_spinner_textfield_background_material:I

    if-ne p1, v2, :cond_c

    .line 314
    :cond_b
    invoke-direct {p0, v0}, Landroid/support/v7/internal/widget/TintManager;->createSpinnerColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;

    move-result-object v1

    goto :goto_1

    .line 315
    :cond_c
    sget-object v2, Landroid/support/v7/internal/widget/TintManager;->TINT_COLOR_CONTROL_NORMAL:[I

    invoke-static {v2, p1}, Landroid/support/v7/internal/widget/TintManager;->arrayContains([II)Z

    move-result v2

    if-eqz v2, :cond_d

    .line 316
    sget v2, Landroid/support/v7/appcompat/R$attr;->colorControlNormal:I

    invoke-static {v0, v2}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColorStateList(Landroid/content/Context;I)Landroid/content/res/ColorStateList;

    move-result-object v1

    goto :goto_1

    .line 317
    :cond_d
    sget-object v2, Landroid/support/v7/internal/widget/TintManager;->TINT_COLOR_CONTROL_STATE_LIST:[I

    invoke-static {v2, p1}, Landroid/support/v7/internal/widget/TintManager;->arrayContains([II)Z

    move-result v2

    if-eqz v2, :cond_e

    .line 318
    invoke-direct {p0, v0}, Landroid/support/v7/internal/widget/TintManager;->getDefaultColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;

    move-result-object v1

    goto :goto_1

    .line 319
    :cond_e
    sget-object v2, Landroid/support/v7/internal/widget/TintManager;->TINT_CHECKABLE_BUTTON_LIST:[I

    invoke-static {v2, p1}, Landroid/support/v7/internal/widget/TintManager;->arrayContains([II)Z

    move-result v2

    if-eqz v2, :cond_f

    .line 320
    invoke-direct {p0, v0}, Landroid/support/v7/internal/widget/TintManager;->createCheckableButtonColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;

    move-result-object v1

    goto :goto_1

    .line 321
    :cond_f
    sget v2, Landroid/support/v7/appcompat/R$drawable;->abc_seekbar_thumb_material:I

    if-ne p1, v2, :cond_3

    .line 322
    invoke-direct {p0, v0}, Landroid/support/v7/internal/widget/TintManager;->createSeekbarThumbColorStateList(Landroid/content/Context;)Landroid/content/res/ColorStateList;

    move-result-object v1

    goto :goto_1
.end method

.method final getTintMode(I)Landroid/graphics/PorterDuff$Mode;
    .locals 2
    .param p1, "resId"    # I

    .prologue
    .line 283
    const/4 v0, 0x0

    .line 285
    .local v0, "mode":Landroid/graphics/PorterDuff$Mode;
    sget v1, Landroid/support/v7/appcompat/R$drawable;->abc_switch_thumb_material:I

    if-ne p1, v1, :cond_0

    .line 286
    sget-object v0, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    .line 289
    :cond_0
    return-object v0
.end method

.method public final tintDrawableUsingColorFilter(ILandroid/graphics/drawable/Drawable;)Z
    .locals 8
    .param p1, "resId"    # I
    .param p2, "drawable"    # Landroid/graphics/drawable/Drawable;

    .prologue
    const/4 v6, 0x0

    .line 222
    iget-object v7, p0, Landroid/support/v7/internal/widget/TintManager;->mContextRef:Ljava/lang/ref/WeakReference;

    invoke-virtual {v7}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroid/content/Context;

    .line 223
    .local v4, "context":Landroid/content/Context;
    if-nez v4, :cond_1

    .line 260
    :cond_0
    :goto_0
    return v6

    .line 225
    :cond_1
    sget-object v5, Landroid/support/v7/internal/widget/TintManager;->DEFAULT_MODE:Landroid/graphics/PorterDuff$Mode;

    .line 226
    .local v5, "tintMode":Landroid/graphics/PorterDuff$Mode;
    const/4 v3, 0x0

    .line 227
    .local v3, "colorAttrSet":Z
    const/4 v2, 0x0

    .line 228
    .local v2, "colorAttr":I
    const/4 v0, -0x1

    .line 230
    .local v0, "alpha":I
    sget-object v7, Landroid/support/v7/internal/widget/TintManager;->COLORFILTER_TINT_COLOR_CONTROL_NORMAL:[I

    invoke-static {v7, p1}, Landroid/support/v7/internal/widget/TintManager;->arrayContains([II)Z

    move-result v7

    if-eqz v7, :cond_4

    .line 231
    sget v2, Landroid/support/v7/appcompat/R$attr;->colorControlNormal:I

    .line 232
    const/4 v3, 0x1

    .line 246
    :cond_2
    :goto_1
    if-eqz v3, :cond_0

    .line 247
    invoke-static {v4, v2}, Landroid/support/v7/internal/widget/ThemeUtils;->getThemeAttrColor(Landroid/content/Context;I)I

    move-result v1

    .line 248
    .local v1, "color":I
    invoke-static {v1, v5}, Landroid/support/v7/internal/widget/TintManager;->getPorterDuffColorFilter(ILandroid/graphics/PorterDuff$Mode;)Landroid/graphics/PorterDuffColorFilter;

    move-result-object v6

    invoke-virtual {p2, v6}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 250
    const/4 v6, -0x1

    if-eq v0, v6, :cond_3

    .line 251
    invoke-virtual {p2, v0}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 258
    :cond_3
    const/4 v6, 0x1

    goto :goto_0

    .line 233
    .end local v1    # "color":I
    :cond_4
    sget-object v7, Landroid/support/v7/internal/widget/TintManager;->COLORFILTER_COLOR_CONTROL_ACTIVATED:[I

    invoke-static {v7, p1}, Landroid/support/v7/internal/widget/TintManager;->arrayContains([II)Z

    move-result v7

    if-eqz v7, :cond_5

    .line 234
    sget v2, Landroid/support/v7/appcompat/R$attr;->colorControlActivated:I

    .line 235
    const/4 v3, 0x1

    goto :goto_1

    .line 236
    :cond_5
    sget-object v7, Landroid/support/v7/internal/widget/TintManager;->COLORFILTER_COLOR_BACKGROUND_MULTIPLY:[I

    invoke-static {v7, p1}, Landroid/support/v7/internal/widget/TintManager;->arrayContains([II)Z

    move-result v7

    if-eqz v7, :cond_6

    .line 237
    const v2, 0x1010031

    .line 238
    const/4 v3, 0x1

    .line 239
    sget-object v5, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    goto :goto_1

    .line 240
    :cond_6
    sget v7, Landroid/support/v7/appcompat/R$drawable;->abc_list_divider_mtrl_alpha:I

    if-ne p1, v7, :cond_2

    .line 241
    const v2, 0x1010030

    .line 242
    const/4 v3, 0x1

    .line 243
    const v7, 0x42233333    # 40.8f

    invoke-static {v7}, Ljava/lang/Math;->round(F)I

    move-result v0

    goto :goto_1
.end method
