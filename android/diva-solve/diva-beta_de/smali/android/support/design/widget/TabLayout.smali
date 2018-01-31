.class public Landroid/support/design/widget/TabLayout;
.super Landroid/widget/HorizontalScrollView;
.source "TabLayout.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroid/support/design/widget/TabLayout$ViewPagerOnTabSelectedListener;,
        Landroid/support/design/widget/TabLayout$TabLayoutOnPageChangeListener;,
        Landroid/support/design/widget/TabLayout$SlidingTabStrip;,
        Landroid/support/design/widget/TabLayout$TabView;,
        Landroid/support/design/widget/TabLayout$Tab;,
        Landroid/support/design/widget/TabLayout$OnTabSelectedListener;,
        Landroid/support/design/widget/TabLayout$TabGravity;,
        Landroid/support/design/widget/TabLayout$Mode;
    }
.end annotation


# static fields
.field private static final ANIMATION_DURATION:I = 0x12c

.field private static final DEFAULT_GAP_TEXT_ICON:I = 0x8

.field private static final DEFAULT_HEIGHT:I = 0x30

.field private static final DEFAULT_HEIGHT_WITH_TEXT_ICON:I = 0x48

.field private static final FIXED_WRAP_GUTTER_MIN:I = 0x10

.field public static final GRAVITY_CENTER:I = 0x1

.field public static final GRAVITY_FILL:I = 0x0

.field private static final INVALID_WIDTH:I = -0x1

.field public static final MODE_FIXED:I = 0x1

.field public static final MODE_SCROLLABLE:I = 0x0

.field private static final MOTION_NON_ADJACENT_OFFSET:I = 0x18

.field private static final TAB_MIN_WIDTH_MARGIN:I = 0x38


# instance fields
.field private mContentInsetStart:I

.field private mIndicatorAnimator:Landroid/support/design/widget/ValueAnimatorCompat;

.field private mMode:I

.field private mOnTabSelectedListener:Landroid/support/design/widget/TabLayout$OnTabSelectedListener;

.field private final mRequestedTabMaxWidth:I

.field private final mRequestedTabMinWidth:I

.field private mScrollAnimator:Landroid/support/design/widget/ValueAnimatorCompat;

.field private final mScrollableTabMinWidth:I

.field private mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

.field private final mTabBackgroundResId:I

.field private mTabClickListener:Landroid/view/View$OnClickListener;

.field private mTabGravity:I

.field private mTabMaxWidth:I

.field private mTabPaddingBottom:I

.field private mTabPaddingEnd:I

.field private mTabPaddingStart:I

.field private mTabPaddingTop:I

.field private final mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

.field private mTabTextAppearance:I

.field private mTabTextColors:Landroid/content/res/ColorStateList;

.field private mTabTextMultiLineSize:F

.field private mTabTextSize:F

.field private final mTabs:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Landroid/support/design/widget/TabLayout$Tab;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 221
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Landroid/support/design/widget/TabLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 222
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;

    .prologue
    .line 225
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Landroid/support/design/widget/TabLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 226
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 8
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;
    .param p3, "defStyleAttr"    # I

    .prologue
    const/4 v7, -0x1

    const/4 v6, 0x0

    .line 229
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/HorizontalScrollView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 187
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    iput-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    .line 204
    const v4, 0x7fffffff

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabMaxWidth:I

    .line 231
    invoke-static {p1}, Landroid/support/design/widget/ThemeUtils;->checkAppCompatTheme(Landroid/content/Context;)V

    .line 234
    invoke-virtual {p0, v6}, Landroid/support/design/widget/TabLayout;->setHorizontalScrollBarEnabled(Z)V

    .line 237
    new-instance v4, Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-direct {v4, p0, p1}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;-><init>(Landroid/support/design/widget/TabLayout;Landroid/content/Context;)V

    iput-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    .line 238
    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    const/4 v5, -0x2

    invoke-virtual {p0, v4, v5, v7}, Landroid/support/design/widget/TabLayout;->addView(Landroid/view/View;II)V

    .line 240
    sget-object v4, Landroid/support/design/R$styleable;->TabLayout:[I

    sget v5, Landroid/support/design/R$style;->Widget_Design_TabLayout:I

    invoke-virtual {p1, p2, v4, p3, v5}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 243
    .local v0, "a":Landroid/content/res/TypedArray;
    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    sget v5, Landroid/support/design/R$styleable;->TabLayout_tabIndicatorHeight:I

    invoke-virtual {v0, v5, v6}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v5

    invoke-virtual {v4, v5}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->setSelectedIndicatorHeight(I)V

    .line 245
    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    sget v5, Landroid/support/design/R$styleable;->TabLayout_tabIndicatorColor:I

    invoke-virtual {v0, v5, v6}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v5

    invoke-virtual {v4, v5}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->setSelectedIndicatorColor(I)V

    .line 247
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabPadding:I

    invoke-virtual {v0, v4, v6}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingBottom:I

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingEnd:I

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingTop:I

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingStart:I

    .line 249
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabPaddingStart:I

    iget v5, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingStart:I

    invoke-virtual {v0, v4, v5}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingStart:I

    .line 251
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabPaddingTop:I

    iget v5, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingTop:I

    invoke-virtual {v0, v4, v5}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingTop:I

    .line 253
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabPaddingEnd:I

    iget v5, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingEnd:I

    invoke-virtual {v0, v4, v5}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingEnd:I

    .line 255
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabPaddingBottom:I

    iget v5, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingBottom:I

    invoke-virtual {v0, v4, v5}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingBottom:I

    .line 258
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabTextAppearance:I

    sget v5, Landroid/support/design/R$style;->TextAppearance_Design_Tab:I

    invoke-virtual {v0, v4, v5}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabTextAppearance:I

    .line 262
    iget v4, p0, Landroid/support/design/widget/TabLayout;->mTabTextAppearance:I

    sget-object v5, Landroid/support/design/R$styleable;->TextAppearance:[I

    invoke-virtual {p1, v4, v5}, Landroid/content/Context;->obtainStyledAttributes(I[I)Landroid/content/res/TypedArray;

    move-result-object v3

    .line 265
    .local v3, "ta":Landroid/content/res/TypedArray;
    :try_start_0
    sget v4, Landroid/support/design/R$styleable;->TextAppearance_android_textSize:I

    const/4 v5, 0x0

    invoke-virtual {v3, v4, v5}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v4

    int-to-float v4, v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabTextSize:F

    .line 266
    sget v4, Landroid/support/design/R$styleable;->TextAppearance_android_textColor:I

    invoke-virtual {v3, v4}, Landroid/content/res/TypedArray;->getColorStateList(I)Landroid/content/res/ColorStateList;

    move-result-object v4

    iput-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabTextColors:Landroid/content/res/ColorStateList;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 268
    invoke-virtual {v3}, Landroid/content/res/TypedArray;->recycle()V

    .line 271
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabTextColor:I

    invoke-virtual {v0, v4}, Landroid/content/res/TypedArray;->hasValue(I)Z

    move-result v4

    if-eqz v4, :cond_0

    .line 273
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabTextColor:I

    invoke-virtual {v0, v4}, Landroid/content/res/TypedArray;->getColorStateList(I)Landroid/content/res/ColorStateList;

    move-result-object v4

    iput-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabTextColors:Landroid/content/res/ColorStateList;

    .line 276
    :cond_0
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabSelectedTextColor:I

    invoke-virtual {v0, v4}, Landroid/content/res/TypedArray;->hasValue(I)Z

    move-result v4

    if-eqz v4, :cond_1

    .line 280
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabSelectedTextColor:I

    invoke-virtual {v0, v4, v6}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v2

    .line 281
    .local v2, "selected":I
    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabTextColors:Landroid/content/res/ColorStateList;

    invoke-virtual {v4}, Landroid/content/res/ColorStateList;->getDefaultColor()I

    move-result v4

    invoke-static {v4, v2}, Landroid/support/design/widget/TabLayout;->createColorStateList(II)Landroid/content/res/ColorStateList;

    move-result-object v4

    iput-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabTextColors:Landroid/content/res/ColorStateList;

    .line 284
    .end local v2    # "selected":I
    :cond_1
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabMinWidth:I

    invoke-virtual {v0, v4, v7}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mRequestedTabMinWidth:I

    .line 286
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabMaxWidth:I

    invoke-virtual {v0, v4, v7}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mRequestedTabMaxWidth:I

    .line 288
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabBackground:I

    invoke-virtual {v0, v4, v6}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabBackgroundResId:I

    .line 289
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabContentStart:I

    invoke-virtual {v0, v4, v6}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mContentInsetStart:I

    .line 290
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabMode:I

    const/4 v5, 0x1

    invoke-virtual {v0, v4, v5}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mMode:I

    .line 291
    sget v4, Landroid/support/design/R$styleable;->TabLayout_tabGravity:I

    invoke-virtual {v0, v4, v6}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabGravity:I

    .line 292
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 295
    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    .line 296
    .local v1, "res":Landroid/content/res/Resources;
    sget v4, Landroid/support/design/R$dimen;->design_tab_text_size_2line:I

    invoke-virtual {v1, v4}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v4

    int-to-float v4, v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mTabTextMultiLineSize:F

    .line 297
    sget v4, Landroid/support/design/R$dimen;->design_tab_scrollable_min_width:I

    invoke-virtual {v1, v4}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v4

    iput v4, p0, Landroid/support/design/widget/TabLayout;->mScrollableTabMinWidth:I

    .line 300
    invoke-direct {p0}, Landroid/support/design/widget/TabLayout;->applyModeAndGravity()V

    .line 301
    return-void

    .line 268
    .end local v1    # "res":Landroid/content/res/Resources;
    :catchall_0
    move-exception v4

    invoke-virtual {v3}, Landroid/content/res/TypedArray;->recycle()V

    throw v4
.end method

.method static synthetic access$100(Landroid/support/design/widget/TabLayout;I)V
    .locals 0
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;
    .param p1, "x1"    # I

    .prologue
    .line 96
    invoke-direct {p0, p1}, Landroid/support/design/widget/TabLayout;->updateTab(I)V

    return-void
.end method

.method static synthetic access$1000(Landroid/support/design/widget/TabLayout;)F
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;

    .prologue
    .line 96
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabTextMultiLineSize:F

    return v0
.end method

.method static synthetic access$1100(Landroid/support/design/widget/TabLayout;)I
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;

    .prologue
    .line 96
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mMode:I

    return v0
.end method

.method static synthetic access$1200(Landroid/support/design/widget/TabLayout;)I
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;

    .prologue
    .line 96
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabTextAppearance:I

    return v0
.end method

.method static synthetic access$1300(Landroid/support/design/widget/TabLayout;)Landroid/content/res/ColorStateList;
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;

    .prologue
    .line 96
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabTextColors:Landroid/content/res/ColorStateList;

    return-object v0
.end method

.method static synthetic access$1400(Landroid/support/design/widget/TabLayout;I)I
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;
    .param p1, "x1"    # I

    .prologue
    .line 96
    invoke-direct {p0, p1}, Landroid/support/design/widget/TabLayout;->dpToPx(I)I

    move-result v0

    return v0
.end method

.method static synthetic access$1500(Landroid/support/design/widget/TabLayout;)I
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;

    .prologue
    .line 96
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabGravity:I

    return v0
.end method

.method static synthetic access$1502(Landroid/support/design/widget/TabLayout;I)I
    .locals 0
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;
    .param p1, "x1"    # I

    .prologue
    .line 96
    iput p1, p0, Landroid/support/design/widget/TabLayout;->mTabGravity:I

    return p1
.end method

.method static synthetic access$1600(Landroid/support/design/widget/TabLayout;Z)V
    .locals 0
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;
    .param p1, "x1"    # Z

    .prologue
    .line 96
    invoke-direct {p0, p1}, Landroid/support/design/widget/TabLayout;->updateTabViews(Z)V

    return-void
.end method

.method static synthetic access$1702(Landroid/support/design/widget/TabLayout;Landroid/support/design/widget/ValueAnimatorCompat;)Landroid/support/design/widget/ValueAnimatorCompat;
    .locals 0
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;
    .param p1, "x1"    # Landroid/support/design/widget/ValueAnimatorCompat;

    .prologue
    .line 96
    iput-object p1, p0, Landroid/support/design/widget/TabLayout;->mIndicatorAnimator:Landroid/support/design/widget/ValueAnimatorCompat;

    return-object p1
.end method

.method static synthetic access$200(Landroid/support/design/widget/TabLayout;)I
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;

    .prologue
    .line 96
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabBackgroundResId:I

    return v0
.end method

.method static synthetic access$300(Landroid/support/design/widget/TabLayout;)I
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;

    .prologue
    .line 96
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingStart:I

    return v0
.end method

.method static synthetic access$400(Landroid/support/design/widget/TabLayout;)I
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;

    .prologue
    .line 96
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingTop:I

    return v0
.end method

.method static synthetic access$500(Landroid/support/design/widget/TabLayout;)I
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;

    .prologue
    .line 96
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingEnd:I

    return v0
.end method

.method static synthetic access$600(Landroid/support/design/widget/TabLayout;)I
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;

    .prologue
    .line 96
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingBottom:I

    return v0
.end method

.method static synthetic access$700(Landroid/support/design/widget/TabLayout;)I
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;

    .prologue
    .line 96
    invoke-direct {p0}, Landroid/support/design/widget/TabLayout;->getTabMaxWidth()I

    move-result v0

    return v0
.end method

.method static synthetic access$800(Landroid/support/design/widget/TabLayout;)I
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;

    .prologue
    .line 96
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabMaxWidth:I

    return v0
.end method

.method static synthetic access$900(Landroid/support/design/widget/TabLayout;)F
    .locals 1
    .param p0, "x0"    # Landroid/support/design/widget/TabLayout;

    .prologue
    .line 96
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabTextSize:F

    return v0
.end method

.method private addTabView(Landroid/support/design/widget/TabLayout$Tab;IZ)V
    .locals 3
    .param p1, "tab"    # Landroid/support/design/widget/TabLayout$Tab;
    .param p2, "position"    # I
    .param p3, "setSelected"    # Z

    .prologue
    .line 698
    invoke-direct {p0, p1}, Landroid/support/design/widget/TabLayout;->createTabView(Landroid/support/design/widget/TabLayout$Tab;)Landroid/support/design/widget/TabLayout$TabView;

    move-result-object v0

    .line 699
    .local v0, "tabView":Landroid/support/design/widget/TabLayout$TabView;
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-direct {p0}, Landroid/support/design/widget/TabLayout;->createLayoutParamsForTabs()Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v2

    invoke-virtual {v1, v0, p2, v2}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->addView(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)V

    .line 700
    if-eqz p3, :cond_0

    .line 701
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/support/design/widget/TabLayout$TabView;->setSelected(Z)V

    .line 703
    :cond_0
    return-void
.end method

.method private addTabView(Landroid/support/design/widget/TabLayout$Tab;Z)V
    .locals 3
    .param p1, "tab"    # Landroid/support/design/widget/TabLayout$Tab;
    .param p2, "setSelected"    # Z

    .prologue
    .line 690
    invoke-direct {p0, p1}, Landroid/support/design/widget/TabLayout;->createTabView(Landroid/support/design/widget/TabLayout$Tab;)Landroid/support/design/widget/TabLayout$TabView;

    move-result-object v0

    .line 691
    .local v0, "tabView":Landroid/support/design/widget/TabLayout$TabView;
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-direct {p0}, Landroid/support/design/widget/TabLayout;->createLayoutParamsForTabs()Landroid/widget/LinearLayout$LayoutParams;

    move-result-object v2

    invoke-virtual {v1, v0, v2}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 692
    if-eqz p2, :cond_0

    .line 693
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/support/design/widget/TabLayout$TabView;->setSelected(Z)V

    .line 695
    :cond_0
    return-void
.end method

.method private animateToTab(I)V
    .locals 5
    .param p1, "newPosition"    # I

    .prologue
    const/16 v4, 0x12c

    const/4 v3, 0x0

    .line 789
    const/4 v2, -0x1

    if-ne p1, v2, :cond_0

    .line 823
    :goto_0
    return-void

    .line 793
    :cond_0
    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getWindowToken()Landroid/os/IBinder;

    move-result-object v2

    if-eqz v2, :cond_1

    invoke-static {p0}, Landroid/support/v4/view/ViewCompat;->isLaidOut(Landroid/view/View;)Z

    move-result v2

    if-eqz v2, :cond_1

    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v2}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->childrenNeedLayout()Z

    move-result v2

    if-eqz v2, :cond_2

    .line 797
    :cond_1
    const/4 v2, 0x1

    invoke-virtual {p0, p1, v3, v2}, Landroid/support/design/widget/TabLayout;->setScrollPosition(IFZ)V

    goto :goto_0

    .line 801
    :cond_2
    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getScrollX()I

    move-result v0

    .line 802
    .local v0, "startScrollX":I
    invoke-direct {p0, p1, v3}, Landroid/support/design/widget/TabLayout;->calculateScrollXForTab(IF)I

    move-result v1

    .line 804
    .local v1, "targetScrollX":I
    if-eq v0, v1, :cond_4

    .line 805
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mScrollAnimator:Landroid/support/design/widget/ValueAnimatorCompat;

    if-nez v2, :cond_3

    .line 806
    invoke-static {}, Landroid/support/design/widget/ViewUtils;->createAnimator()Landroid/support/design/widget/ValueAnimatorCompat;

    move-result-object v2

    iput-object v2, p0, Landroid/support/design/widget/TabLayout;->mScrollAnimator:Landroid/support/design/widget/ValueAnimatorCompat;

    .line 807
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mScrollAnimator:Landroid/support/design/widget/ValueAnimatorCompat;

    sget-object v3, Landroid/support/design/widget/AnimationUtils;->FAST_OUT_SLOW_IN_INTERPOLATOR:Landroid/view/animation/Interpolator;

    invoke-virtual {v2, v3}, Landroid/support/design/widget/ValueAnimatorCompat;->setInterpolator(Landroid/view/animation/Interpolator;)V

    .line 808
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mScrollAnimator:Landroid/support/design/widget/ValueAnimatorCompat;

    invoke-virtual {v2, v4}, Landroid/support/design/widget/ValueAnimatorCompat;->setDuration(I)V

    .line 809
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mScrollAnimator:Landroid/support/design/widget/ValueAnimatorCompat;

    new-instance v3, Landroid/support/design/widget/TabLayout$2;

    invoke-direct {v3, p0}, Landroid/support/design/widget/TabLayout$2;-><init>(Landroid/support/design/widget/TabLayout;)V

    invoke-virtual {v2, v3}, Landroid/support/design/widget/ValueAnimatorCompat;->setUpdateListener(Landroid/support/design/widget/ValueAnimatorCompat$AnimatorUpdateListener;)V

    .line 817
    :cond_3
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mScrollAnimator:Landroid/support/design/widget/ValueAnimatorCompat;

    invoke-virtual {v2, v0, v1}, Landroid/support/design/widget/ValueAnimatorCompat;->setIntValues(II)V

    .line 818
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mScrollAnimator:Landroid/support/design/widget/ValueAnimatorCompat;

    invoke-virtual {v2}, Landroid/support/design/widget/ValueAnimatorCompat;->start()V

    .line 822
    :cond_4
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v2, p1, v4}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->animateIndicatorToPosition(II)V

    goto :goto_0
.end method

.method private applyModeAndGravity()V
    .locals 5

    .prologue
    const/4 v4, 0x1

    const/4 v3, 0x0

    .line 889
    const/4 v0, 0x0

    .line 890
    .local v0, "paddingStart":I
    iget v1, p0, Landroid/support/design/widget/TabLayout;->mMode:I

    if-nez v1, :cond_0

    .line 892
    iget v1, p0, Landroid/support/design/widget/TabLayout;->mContentInsetStart:I

    iget v2, p0, Landroid/support/design/widget/TabLayout;->mTabPaddingStart:I

    sub-int/2addr v1, v2

    invoke-static {v3, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 894
    :cond_0
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-static {v1, v0, v3, v3, v3}, Landroid/support/v4/view/ViewCompat;->setPaddingRelative(Landroid/view/View;IIII)V

    .line 896
    iget v1, p0, Landroid/support/design/widget/TabLayout;->mMode:I

    packed-switch v1, :pswitch_data_0

    .line 905
    :goto_0
    invoke-direct {p0, v4}, Landroid/support/design/widget/TabLayout;->updateTabViews(Z)V

    .line 906
    return-void

    .line 898
    :pswitch_0
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v1, v4}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->setGravity(I)V

    goto :goto_0

    .line 901
    :pswitch_1
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    const v2, 0x800003

    invoke-virtual {v1, v2}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->setGravity(I)V

    goto :goto_0

    .line 896
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private calculateScrollXForTab(IF)I
    .locals 7
    .param p1, "position"    # I
    .param p2, "positionOffset"    # F

    .prologue
    const/4 v1, 0x0

    .line 872
    iget v4, p0, Landroid/support/design/widget/TabLayout;->mMode:I

    if-nez v4, :cond_1

    .line 873
    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v4, p1}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 874
    .local v2, "selectedChild":Landroid/view/View;
    add-int/lit8 v4, p1, 0x1

    iget-object v5, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v5}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->getChildCount()I

    move-result v5

    if-ge v4, v5, :cond_2

    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    add-int/lit8 v5, p1, 0x1

    invoke-virtual {v4, v5}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 877
    .local v0, "nextChild":Landroid/view/View;
    :goto_0
    if-eqz v2, :cond_3

    invoke-virtual {v2}, Landroid/view/View;->getWidth()I

    move-result v3

    .line 878
    .local v3, "selectedWidth":I
    :goto_1
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/view/View;->getWidth()I

    move-result v1

    .line 880
    .local v1, "nextWidth":I
    :cond_0
    invoke-virtual {v2}, Landroid/view/View;->getLeft()I

    move-result v4

    add-int v5, v3, v1

    int-to-float v5, v5

    mul-float/2addr v5, p2

    const/high16 v6, 0x3f000000    # 0.5f

    mul-float/2addr v5, v6

    float-to-int v5, v5

    add-int/2addr v4, v5

    invoke-virtual {v2}, Landroid/view/View;->getWidth()I

    move-result v5

    div-int/lit8 v5, v5, 0x2

    add-int/2addr v4, v5

    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getWidth()I

    move-result v5

    div-int/lit8 v5, v5, 0x2

    sub-int v1, v4, v5

    .line 885
    .end local v0    # "nextChild":Landroid/view/View;
    .end local v1    # "nextWidth":I
    .end local v2    # "selectedChild":Landroid/view/View;
    .end local v3    # "selectedWidth":I
    :cond_1
    return v1

    .line 874
    .restart local v2    # "selectedChild":Landroid/view/View;
    :cond_2
    const/4 v0, 0x0

    goto :goto_0

    .restart local v0    # "nextChild":Landroid/view/View;
    :cond_3
    move v3, v1

    .line 877
    goto :goto_1
.end method

.method private configureTab(Landroid/support/design/widget/TabLayout$Tab;I)V
    .locals 3
    .param p1, "tab"    # Landroid/support/design/widget/TabLayout$Tab;
    .param p2, "position"    # I

    .prologue
    .line 673
    invoke-virtual {p1, p2}, Landroid/support/design/widget/TabLayout$Tab;->setPosition(I)V

    .line 674
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v2, p2, p1}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 676
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v0

    .line 677
    .local v0, "count":I
    add-int/lit8 v1, p2, 0x1

    .local v1, "i":I
    :goto_0
    if-ge v1, v0, :cond_0

    .line 678
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/support/design/widget/TabLayout$Tab;

    invoke-virtual {v2, v1}, Landroid/support/design/widget/TabLayout$Tab;->setPosition(I)V

    .line 677
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 680
    :cond_0
    return-void
.end method

.method private static createColorStateList(II)Landroid/content/res/ColorStateList;
    .locals 4
    .param p0, "defaultColor"    # I
    .param p1, "selectedColor"    # I

    .prologue
    const/4 v3, 0x2

    .line 1659
    new-array v2, v3, [[I

    .line 1660
    .local v2, "states":[[I
    new-array v0, v3, [I

    .line 1661
    .local v0, "colors":[I
    const/4 v1, 0x0

    .line 1663
    .local v1, "i":I
    sget-object v3, Landroid/support/design/widget/TabLayout;->SELECTED_STATE_SET:[I

    aput-object v3, v2, v1

    .line 1664
    aput p1, v0, v1

    .line 1665
    add-int/lit8 v1, v1, 0x1

    .line 1668
    sget-object v3, Landroid/support/design/widget/TabLayout;->EMPTY_STATE_SET:[I

    aput-object v3, v2, v1

    .line 1669
    aput p0, v0, v1

    .line 1670
    add-int/lit8 v1, v1, 0x1

    .line 1672
    new-instance v3, Landroid/content/res/ColorStateList;

    invoke-direct {v3, v2, v0}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    return-object v3
.end method

.method private createLayoutParamsForTabs()Landroid/widget/LinearLayout$LayoutParams;
    .locals 3

    .prologue
    .line 706
    new-instance v0, Landroid/widget/LinearLayout$LayoutParams;

    const/4 v1, -0x2

    const/4 v2, -0x1

    invoke-direct {v0, v1, v2}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    .line 708
    .local v0, "lp":Landroid/widget/LinearLayout$LayoutParams;
    invoke-direct {p0, v0}, Landroid/support/design/widget/TabLayout;->updateTabViewLayoutParams(Landroid/widget/LinearLayout$LayoutParams;)V

    .line 709
    return-object v0
.end method

.method private createTabView(Landroid/support/design/widget/TabLayout$Tab;)Landroid/support/design/widget/TabLayout$TabView;
    .locals 2
    .param p1, "tab"    # Landroid/support/design/widget/TabLayout$Tab;

    .prologue
    .line 655
    new-instance v0, Landroid/support/design/widget/TabLayout$TabView;

    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, p0, v1, p1}, Landroid/support/design/widget/TabLayout$TabView;-><init>(Landroid/support/design/widget/TabLayout;Landroid/content/Context;Landroid/support/design/widget/TabLayout$Tab;)V

    .line 656
    .local v0, "tabView":Landroid/support/design/widget/TabLayout$TabView;
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/support/design/widget/TabLayout$TabView;->setFocusable(Z)V

    .line 657
    invoke-direct {p0}, Landroid/support/design/widget/TabLayout;->getTabMinWidth()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/support/design/widget/TabLayout$TabView;->setMinimumWidth(I)V

    .line 659
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mTabClickListener:Landroid/view/View$OnClickListener;

    if-nez v1, :cond_0

    .line 660
    new-instance v1, Landroid/support/design/widget/TabLayout$1;

    invoke-direct {v1, p0}, Landroid/support/design/widget/TabLayout$1;-><init>(Landroid/support/design/widget/TabLayout;)V

    iput-object v1, p0, Landroid/support/design/widget/TabLayout;->mTabClickListener:Landroid/view/View$OnClickListener;

    .line 668
    :cond_0
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mTabClickListener:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v1}, Landroid/support/design/widget/TabLayout$TabView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 669
    return-object v0
.end method

.method private dpToPx(I)I
    .locals 2
    .param p1, "dps"    # I

    .prologue
    .line 723
    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->density:F

    int-to-float v1, p1

    mul-float/2addr v0, v1

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    return v0
.end method

.method private getDefaultHeight()I
    .locals 5

    .prologue
    .line 1676
    const/4 v1, 0x0

    .line 1677
    .local v1, "hasIconAndText":Z
    const/4 v2, 0x0

    .local v2, "i":I
    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v0

    .local v0, "count":I
    :goto_0
    if-ge v2, v0, :cond_0

    .line 1678
    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v4, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroid/support/design/widget/TabLayout$Tab;

    .line 1679
    .local v3, "tab":Landroid/support/design/widget/TabLayout$Tab;
    if-eqz v3, :cond_1

    invoke-virtual {v3}, Landroid/support/design/widget/TabLayout$Tab;->getIcon()Landroid/graphics/drawable/Drawable;

    move-result-object v4

    if-eqz v4, :cond_1

    invoke-virtual {v3}, Landroid/support/design/widget/TabLayout$Tab;->getText()Ljava/lang/CharSequence;

    move-result-object v4

    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_1

    .line 1680
    const/4 v1, 0x1

    .line 1684
    .end local v3    # "tab":Landroid/support/design/widget/TabLayout$Tab;
    :cond_0
    if-eqz v1, :cond_2

    const/16 v4, 0x48

    :goto_1
    return v4

    .line 1677
    .restart local v3    # "tab":Landroid/support/design/widget/TabLayout$Tab;
    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 1684
    .end local v3    # "tab":Landroid/support/design/widget/TabLayout$Tab;
    :cond_2
    const/16 v4, 0x30

    goto :goto_1
.end method

.method private getScrollPosition()F
    .locals 1

    .prologue
    .line 350
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v0}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->getIndicatorPosition()F

    move-result v0

    return v0
.end method

.method private getTabMaxWidth()I
    .locals 1

    .prologue
    .line 1697
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabMaxWidth:I

    return v0
.end method

.method private getTabMinWidth()I
    .locals 2

    .prologue
    .line 1688
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mRequestedTabMinWidth:I

    const/4 v1, -0x1

    if-eq v0, v1, :cond_0

    .line 1690
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mRequestedTabMinWidth:I

    .line 1693
    :goto_0
    return v0

    :cond_0
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mMode:I

    if-nez v0, :cond_1

    iget v0, p0, Landroid/support/design/widget/TabLayout;->mScrollableTabMinWidth:I

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private removeTabViewAt(I)V
    .locals 1
    .param p1, "position"    # I

    .prologue
    .line 784
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v0, p1}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->removeViewAt(I)V

    .line 785
    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->requestLayout()V

    .line 786
    return-void
.end method

.method private setSelectedTabView(I)V
    .locals 4
    .param p1, "position"    # I

    .prologue
    .line 826
    iget-object v3, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v3}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->getChildCount()I

    move-result v2

    .line 827
    .local v2, "tabCount":I
    if-ge p1, v2, :cond_1

    iget-object v3, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v3, p1}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    invoke-virtual {v3}, Landroid/view/View;->isSelected()Z

    move-result v3

    if-nez v3, :cond_1

    .line 828
    const/4 v1, 0x0

    .local v1, "i":I
    :goto_0
    if-ge v1, v2, :cond_1

    .line 829
    iget-object v3, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v3, v1}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 830
    .local v0, "child":Landroid/view/View;
    if-ne v1, p1, :cond_0

    const/4 v3, 0x1

    :goto_1
    invoke-virtual {v0, v3}, Landroid/view/View;->setSelected(Z)V

    .line 828
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 830
    :cond_0
    const/4 v3, 0x0

    goto :goto_1

    .line 833
    .end local v0    # "child":Landroid/view/View;
    .end local v1    # "i":I
    :cond_1
    return-void
.end method

.method private updateAllTabs()V
    .locals 3

    .prologue
    .line 649
    const/4 v0, 0x0

    .local v0, "i":I
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v2}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->getChildCount()I

    move-result v1

    .local v1, "z":I
    :goto_0
    if-ge v0, v1, :cond_0

    .line 650
    invoke-direct {p0, v0}, Landroid/support/design/widget/TabLayout;->updateTab(I)V

    .line 649
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 652
    :cond_0
    return-void
.end method

.method private updateTab(I)V
    .locals 2
    .param p1, "position"    # I

    .prologue
    .line 683
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v1, p1}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/support/design/widget/TabLayout$TabView;

    .line 684
    .local v0, "view":Landroid/support/design/widget/TabLayout$TabView;
    if-eqz v0, :cond_0

    .line 685
    invoke-virtual {v0}, Landroid/support/design/widget/TabLayout$TabView;->update()V

    .line 687
    :cond_0
    return-void
.end method

.method private updateTabViewLayoutParams(Landroid/widget/LinearLayout$LayoutParams;)V
    .locals 2
    .param p1, "lp"    # Landroid/widget/LinearLayout$LayoutParams;

    .prologue
    .line 713
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mMode:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabGravity:I

    if-nez v0, :cond_0

    .line 714
    const/4 v0, 0x0

    iput v0, p1, Landroid/widget/LinearLayout$LayoutParams;->width:I

    .line 715
    const/high16 v0, 0x3f800000    # 1.0f

    iput v0, p1, Landroid/widget/LinearLayout$LayoutParams;->weight:F

    .line 720
    :goto_0
    return-void

    .line 717
    :cond_0
    const/4 v0, -0x2

    iput v0, p1, Landroid/widget/LinearLayout$LayoutParams;->width:I

    .line 718
    const/4 v0, 0x0

    iput v0, p1, Landroid/widget/LinearLayout$LayoutParams;->weight:F

    goto :goto_0
.end method

.method private updateTabViews(Z)V
    .locals 3
    .param p1, "requestLayout"    # Z

    .prologue
    .line 909
    const/4 v1, 0x0

    .local v1, "i":I
    :goto_0
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v2}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->getChildCount()I

    move-result v2

    if-ge v1, v2, :cond_1

    .line 910
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v2, v1}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 911
    .local v0, "child":Landroid/view/View;
    invoke-direct {p0}, Landroid/support/design/widget/TabLayout;->getTabMinWidth()I

    move-result v2

    invoke-virtual {v0, v2}, Landroid/view/View;->setMinimumWidth(I)V

    .line 912
    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v2

    check-cast v2, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {p0, v2}, Landroid/support/design/widget/TabLayout;->updateTabViewLayoutParams(Landroid/widget/LinearLayout$LayoutParams;)V

    .line 913
    if-eqz p1, :cond_0

    .line 914
    invoke-virtual {v0}, Landroid/view/View;->requestLayout()V

    .line 909
    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 917
    .end local v0    # "child":Landroid/view/View;
    :cond_1
    return-void
.end method


# virtual methods
.method public addTab(Landroid/support/design/widget/TabLayout$Tab;)V
    .locals 1
    .param p1, "tab"    # Landroid/support/design/widget/TabLayout$Tab;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param

    .prologue
    .line 360
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    invoke-virtual {p0, p1, v0}, Landroid/support/design/widget/TabLayout;->addTab(Landroid/support/design/widget/TabLayout$Tab;Z)V

    .line 361
    return-void
.end method

.method public addTab(Landroid/support/design/widget/TabLayout$Tab;I)V
    .locals 1
    .param p1, "tab"    # Landroid/support/design/widget/TabLayout$Tab;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .param p2, "position"    # I

    .prologue
    .line 371
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    invoke-virtual {p0, p1, p2, v0}, Landroid/support/design/widget/TabLayout;->addTab(Landroid/support/design/widget/TabLayout$Tab;IZ)V

    .line 372
    return-void
.end method

.method public addTab(Landroid/support/design/widget/TabLayout$Tab;IZ)V
    .locals 2
    .param p1, "tab"    # Landroid/support/design/widget/TabLayout$Tab;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .param p2, "position"    # I
    .param p3, "setSelected"    # Z

    .prologue
    .line 400
    invoke-static {p1}, Landroid/support/design/widget/TabLayout$Tab;->access$000(Landroid/support/design/widget/TabLayout$Tab;)Landroid/support/design/widget/TabLayout;

    move-result-object v0

    if-eq v0, p0, :cond_0

    .line 401
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "Tab belongs to a different TabLayout."

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 404
    :cond_0
    invoke-direct {p0, p1, p2, p3}, Landroid/support/design/widget/TabLayout;->addTabView(Landroid/support/design/widget/TabLayout$Tab;IZ)V

    .line 405
    invoke-direct {p0, p1, p2}, Landroid/support/design/widget/TabLayout;->configureTab(Landroid/support/design/widget/TabLayout$Tab;I)V

    .line 406
    if-eqz p3, :cond_1

    .line 407
    invoke-virtual {p1}, Landroid/support/design/widget/TabLayout$Tab;->select()V

    .line 409
    :cond_1
    return-void
.end method

.method public addTab(Landroid/support/design/widget/TabLayout$Tab;Z)V
    .locals 2
    .param p1, "tab"    # Landroid/support/design/widget/TabLayout$Tab;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .param p2, "setSelected"    # Z

    .prologue
    .line 381
    invoke-static {p1}, Landroid/support/design/widget/TabLayout$Tab;->access$000(Landroid/support/design/widget/TabLayout$Tab;)Landroid/support/design/widget/TabLayout;

    move-result-object v0

    if-eq v0, p0, :cond_0

    .line 382
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "Tab belongs to a different TabLayout."

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 385
    :cond_0
    invoke-direct {p0, p1, p2}, Landroid/support/design/widget/TabLayout;->addTabView(Landroid/support/design/widget/TabLayout$Tab;Z)V

    .line 386
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    invoke-direct {p0, p1, v0}, Landroid/support/design/widget/TabLayout;->configureTab(Landroid/support/design/widget/TabLayout$Tab;I)V

    .line 387
    if-eqz p2, :cond_1

    .line 388
    invoke-virtual {p1}, Landroid/support/design/widget/TabLayout$Tab;->select()V

    .line 390
    :cond_1
    return-void
.end method

.method public getSelectedTabPosition()I
    .locals 1

    .prologue
    .line 456
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    if-eqz v0, :cond_0

    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    invoke-virtual {v0}, Landroid/support/design/widget/TabLayout$Tab;->getPosition()I

    move-result v0

    :goto_0
    return v0

    :cond_0
    const/4 v0, -0x1

    goto :goto_0
.end method

.method public getTabAt(I)Landroid/support/design/widget/TabLayout$Tab;
    .locals 1
    .param p1, "index"    # I
    .annotation build Landroid/support/annotation/Nullable;
    .end annotation

    .prologue
    .line 447
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/support/design/widget/TabLayout$Tab;

    return-object v0
.end method

.method public getTabCount()I
    .locals 1

    .prologue
    .line 439
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    return v0
.end method

.method public getTabGravity()I
    .locals 1

    .prologue
    .line 563
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabGravity:I

    return v0
.end method

.method public getTabMode()I
    .locals 1

    .prologue
    .line 541
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mMode:I

    return v0
.end method

.method public getTabTextColors()Landroid/content/res/ColorStateList;
    .locals 1
    .annotation build Landroid/support/annotation/Nullable;
    .end annotation

    .prologue
    .line 581
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabTextColors:Landroid/content/res/ColorStateList;

    return-object v0
.end method

.method public newTab()Landroid/support/design/widget/TabLayout$Tab;
    .locals 1
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .prologue
    .line 430
    new-instance v0, Landroid/support/design/widget/TabLayout$Tab;

    invoke-direct {v0, p0}, Landroid/support/design/widget/TabLayout$Tab;-><init>(Landroid/support/design/widget/TabLayout;)V

    return-object v0
.end method

.method protected onMeasure(II)V
    .locals 11
    .param p1, "widthMeasureSpec"    # I
    .param p2, "heightMeasureSpec"    # I

    .prologue
    const/high16 v10, 0x40000000    # 2.0f

    const/4 v7, 0x1

    const/4 v8, 0x0

    .line 730
    invoke-direct {p0}, Landroid/support/design/widget/TabLayout;->getDefaultHeight()I

    move-result v6

    invoke-direct {p0, v6}, Landroid/support/design/widget/TabLayout;->dpToPx(I)I

    move-result v6

    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getPaddingTop()I

    move-result v9

    add-int/2addr v6, v9

    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getPaddingBottom()I

    move-result v9

    add-int v3, v6, v9

    .line 731
    .local v3, "idealHeight":I
    invoke-static {p2}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v6

    sparse-switch v6, :sswitch_data_0

    .line 742
    :goto_0
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v5

    .line 743
    .local v5, "specWidth":I
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v6

    if-eqz v6, :cond_0

    .line 746
    iget v6, p0, Landroid/support/design/widget/TabLayout;->mRequestedTabMaxWidth:I

    if-lez v6, :cond_2

    iget v6, p0, Landroid/support/design/widget/TabLayout;->mRequestedTabMaxWidth:I

    :goto_1
    iput v6, p0, Landroid/support/design/widget/TabLayout;->mTabMaxWidth:I

    .line 752
    :cond_0
    invoke-super {p0, p1, p2}, Landroid/widget/HorizontalScrollView;->onMeasure(II)V

    .line 754
    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getChildCount()I

    move-result v6

    if-ne v6, v7, :cond_1

    .line 757
    invoke-virtual {p0, v8}, Landroid/support/design/widget/TabLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 758
    .local v0, "child":Landroid/view/View;
    const/4 v4, 0x0

    .line 760
    .local v4, "remeasure":Z
    iget v6, p0, Landroid/support/design/widget/TabLayout;->mMode:I

    packed-switch v6, :pswitch_data_0

    .line 772
    :goto_2
    if-eqz v4, :cond_1

    .line 774
    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getPaddingTop()I

    move-result v6

    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getPaddingBottom()I

    move-result v7

    add-int/2addr v6, v7

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v7

    iget v7, v7, Landroid/view/ViewGroup$LayoutParams;->height:I

    invoke-static {p2, v6, v7}, Landroid/support/design/widget/TabLayout;->getChildMeasureSpec(III)I

    move-result v1

    .line 776
    .local v1, "childHeightMeasureSpec":I
    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getMeasuredWidth()I

    move-result v6

    invoke-static {v6, v10}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v2

    .line 778
    .local v2, "childWidthMeasureSpec":I
    invoke-virtual {v0, v2, v1}, Landroid/view/View;->measure(II)V

    .line 781
    .end local v0    # "child":Landroid/view/View;
    .end local v1    # "childHeightMeasureSpec":I
    .end local v2    # "childWidthMeasureSpec":I
    .end local v4    # "remeasure":Z
    :cond_1
    return-void

    .line 733
    .end local v5    # "specWidth":I
    :sswitch_0
    invoke-static {p2}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v6

    invoke-static {v3, v6}, Ljava/lang/Math;->min(II)I

    move-result v6

    invoke-static {v6, v10}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p2

    .line 736
    goto :goto_0

    .line 738
    :sswitch_1
    invoke-static {v3, v10}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p2

    goto :goto_0

    .line 746
    .restart local v5    # "specWidth":I
    :cond_2
    const/16 v6, 0x38

    invoke-direct {p0, v6}, Landroid/support/design/widget/TabLayout;->dpToPx(I)I

    move-result v6

    sub-int v6, v5, v6

    goto :goto_1

    .line 764
    .restart local v0    # "child":Landroid/view/View;
    .restart local v4    # "remeasure":Z
    :pswitch_0
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v6

    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getMeasuredWidth()I

    move-result v9

    if-ge v6, v9, :cond_3

    move v4, v7

    .line 765
    :goto_3
    goto :goto_2

    :cond_3
    move v4, v8

    .line 764
    goto :goto_3

    .line 768
    :pswitch_1
    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v6

    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getMeasuredWidth()I

    move-result v9

    if-eq v6, v9, :cond_4

    move v4, v7

    :goto_4
    goto :goto_2

    :cond_4
    move v4, v8

    goto :goto_4

    .line 731
    nop

    :sswitch_data_0
    .sparse-switch
        -0x80000000 -> :sswitch_0
        0x0 -> :sswitch_1
    .end sparse-switch

    .line 760
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method public removeAllTabs()V
    .locals 3

    .prologue
    .line 503
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v2}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->removeAllViews()V

    .line 505
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    .local v0, "i":Ljava/util/Iterator;, "Ljava/util/Iterator<Landroid/support/design/widget/TabLayout$Tab;>;"
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 506
    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/support/design/widget/TabLayout$Tab;

    .line 507
    .local v1, "tab":Landroid/support/design/widget/TabLayout$Tab;
    const/4 v2, -0x1

    invoke-virtual {v1, v2}, Landroid/support/design/widget/TabLayout$Tab;->setPosition(I)V

    .line 508
    invoke-interface {v0}, Ljava/util/Iterator;->remove()V

    goto :goto_0

    .line 511
    .end local v1    # "tab":Landroid/support/design/widget/TabLayout$Tab;
    :cond_0
    const/4 v2, 0x0

    iput-object v2, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    .line 512
    return-void
.end method

.method public removeTab(Landroid/support/design/widget/TabLayout$Tab;)V
    .locals 2
    .param p1, "tab"    # Landroid/support/design/widget/TabLayout$Tab;

    .prologue
    .line 466
    invoke-static {p1}, Landroid/support/design/widget/TabLayout$Tab;->access$000(Landroid/support/design/widget/TabLayout$Tab;)Landroid/support/design/widget/TabLayout;

    move-result-object v0

    if-eq v0, p0, :cond_0

    .line 467
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "Tab does not belong to this TabLayout."

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 470
    :cond_0
    invoke-virtual {p1}, Landroid/support/design/widget/TabLayout$Tab;->getPosition()I

    move-result v0

    invoke-virtual {p0, v0}, Landroid/support/design/widget/TabLayout;->removeTabAt(I)V

    .line 471
    return-void
.end method

.method public removeTabAt(I)V
    .locals 7
    .param p1, "position"    # I

    .prologue
    const/4 v5, 0x0

    .line 480
    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    if-eqz v4, :cond_1

    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    invoke-virtual {v4}, Landroid/support/design/widget/TabLayout$Tab;->getPosition()I

    move-result v3

    .line 481
    .local v3, "selectedTabPosition":I
    :goto_0
    invoke-direct {p0, p1}, Landroid/support/design/widget/TabLayout;->removeTabViewAt(I)V

    .line 483
    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v4, p1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/support/design/widget/TabLayout$Tab;

    .line 484
    .local v2, "removedTab":Landroid/support/design/widget/TabLayout$Tab;
    if-eqz v2, :cond_0

    .line 485
    const/4 v4, -0x1

    invoke-virtual {v2, v4}, Landroid/support/design/widget/TabLayout$Tab;->setPosition(I)V

    .line 488
    :cond_0
    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v1

    .line 489
    .local v1, "newTabCount":I
    move v0, p1

    .local v0, "i":I
    :goto_1
    if-ge v0, v1, :cond_2

    .line 490
    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v4, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroid/support/design/widget/TabLayout$Tab;

    invoke-virtual {v4, v0}, Landroid/support/design/widget/TabLayout$Tab;->setPosition(I)V

    .line 489
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .end local v0    # "i":I
    .end local v1    # "newTabCount":I
    .end local v2    # "removedTab":Landroid/support/design/widget/TabLayout$Tab;
    .end local v3    # "selectedTabPosition":I
    :cond_1
    move v3, v5

    .line 480
    goto :goto_0

    .line 493
    .restart local v0    # "i":I
    .restart local v1    # "newTabCount":I
    .restart local v2    # "removedTab":Landroid/support/design/widget/TabLayout$Tab;
    .restart local v3    # "selectedTabPosition":I
    :cond_2
    if-ne v3, p1, :cond_3

    .line 494
    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_4

    const/4 v4, 0x0

    :goto_2
    invoke-virtual {p0, v4}, Landroid/support/design/widget/TabLayout;->selectTab(Landroid/support/design/widget/TabLayout$Tab;)V

    .line 496
    :cond_3
    return-void

    .line 494
    :cond_4
    iget-object v4, p0, Landroid/support/design/widget/TabLayout;->mTabs:Ljava/util/ArrayList;

    add-int/lit8 v6, p1, -0x1

    invoke-static {v5, v6}, Ljava/lang/Math;->max(II)I

    move-result v5

    invoke-virtual {v4, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroid/support/design/widget/TabLayout$Tab;

    goto :goto_2
.end method

.method selectTab(Landroid/support/design/widget/TabLayout$Tab;)V
    .locals 1
    .param p1, "tab"    # Landroid/support/design/widget/TabLayout$Tab;

    .prologue
    .line 836
    const/4 v0, 0x1

    invoke-virtual {p0, p1, v0}, Landroid/support/design/widget/TabLayout;->selectTab(Landroid/support/design/widget/TabLayout$Tab;Z)V

    .line 837
    return-void
.end method

.method selectTab(Landroid/support/design/widget/TabLayout$Tab;Z)V
    .locals 3
    .param p1, "tab"    # Landroid/support/design/widget/TabLayout$Tab;
    .param p2, "updateIndicator"    # Z

    .prologue
    const/4 v1, -0x1

    .line 840
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    if-ne v2, p1, :cond_2

    .line 841
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    if-eqz v1, :cond_1

    .line 842
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mOnTabSelectedListener:Landroid/support/design/widget/TabLayout$OnTabSelectedListener;

    if-eqz v1, :cond_0

    .line 843
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mOnTabSelectedListener:Landroid/support/design/widget/TabLayout$OnTabSelectedListener;

    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    invoke-interface {v1, v2}, Landroid/support/design/widget/TabLayout$OnTabSelectedListener;->onTabReselected(Landroid/support/design/widget/TabLayout$Tab;)V

    .line 845
    :cond_0
    invoke-virtual {p1}, Landroid/support/design/widget/TabLayout$Tab;->getPosition()I

    move-result v1

    invoke-direct {p0, v1}, Landroid/support/design/widget/TabLayout;->animateToTab(I)V

    .line 869
    :cond_1
    :goto_0
    return-void

    .line 848
    :cond_2
    if-eqz p2, :cond_5

    .line 849
    if-eqz p1, :cond_7

    invoke-virtual {p1}, Landroid/support/design/widget/TabLayout$Tab;->getPosition()I

    move-result v0

    .line 850
    .local v0, "newPosition":I
    :goto_1
    if-eq v0, v1, :cond_3

    .line 851
    invoke-direct {p0, v0}, Landroid/support/design/widget/TabLayout;->setSelectedTabView(I)V

    .line 853
    :cond_3
    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    if-eqz v2, :cond_4

    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    invoke-virtual {v2}, Landroid/support/design/widget/TabLayout$Tab;->getPosition()I

    move-result v2

    if-ne v2, v1, :cond_8

    :cond_4
    if-eq v0, v1, :cond_8

    .line 856
    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-virtual {p0, v0, v1, v2}, Landroid/support/design/widget/TabLayout;->setScrollPosition(IFZ)V

    .line 861
    .end local v0    # "newPosition":I
    :cond_5
    :goto_2
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    if-eqz v1, :cond_6

    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mOnTabSelectedListener:Landroid/support/design/widget/TabLayout$OnTabSelectedListener;

    if-eqz v1, :cond_6

    .line 862
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mOnTabSelectedListener:Landroid/support/design/widget/TabLayout$OnTabSelectedListener;

    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    invoke-interface {v1, v2}, Landroid/support/design/widget/TabLayout$OnTabSelectedListener;->onTabUnselected(Landroid/support/design/widget/TabLayout$Tab;)V

    .line 864
    :cond_6
    iput-object p1, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    .line 865
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    if-eqz v1, :cond_1

    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mOnTabSelectedListener:Landroid/support/design/widget/TabLayout$OnTabSelectedListener;

    if-eqz v1, :cond_1

    .line 866
    iget-object v1, p0, Landroid/support/design/widget/TabLayout;->mOnTabSelectedListener:Landroid/support/design/widget/TabLayout$OnTabSelectedListener;

    iget-object v2, p0, Landroid/support/design/widget/TabLayout;->mSelectedTab:Landroid/support/design/widget/TabLayout$Tab;

    invoke-interface {v1, v2}, Landroid/support/design/widget/TabLayout$OnTabSelectedListener;->onTabSelected(Landroid/support/design/widget/TabLayout$Tab;)V

    goto :goto_0

    :cond_7
    move v0, v1

    .line 849
    goto :goto_1

    .line 858
    .restart local v0    # "newPosition":I
    :cond_8
    invoke-direct {p0, v0}, Landroid/support/design/widget/TabLayout;->animateToTab(I)V

    goto :goto_2
.end method

.method public setOnTabSelectedListener(Landroid/support/design/widget/TabLayout$OnTabSelectedListener;)V
    .locals 0
    .param p1, "onTabSelectedListener"    # Landroid/support/design/widget/TabLayout$OnTabSelectedListener;

    .prologue
    .line 418
    iput-object p1, p0, Landroid/support/design/widget/TabLayout;->mOnTabSelectedListener:Landroid/support/design/widget/TabLayout$OnTabSelectedListener;

    .line 419
    return-void
.end method

.method public setScrollPosition(IFZ)V
    .locals 2
    .param p1, "position"    # I
    .param p2, "positionOffset"    # F
    .param p3, "updateSelectedText"    # Z

    .prologue
    .line 332
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mIndicatorAnimator:Landroid/support/design/widget/ValueAnimatorCompat;

    if-eqz v0, :cond_1

    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mIndicatorAnimator:Landroid/support/design/widget/ValueAnimatorCompat;

    invoke-virtual {v0}, Landroid/support/design/widget/ValueAnimatorCompat;->isRunning()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 347
    :cond_0
    :goto_0
    return-void

    .line 335
    :cond_1
    if-ltz p1, :cond_0

    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v0}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->getChildCount()I

    move-result v0

    if-ge p1, v0, :cond_0

    .line 340
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v0, p1, p2}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->setIndicatorPositionFromTabPosition(IF)V

    .line 341
    invoke-direct {p0, p1, p2}, Landroid/support/design/widget/TabLayout;->calculateScrollXForTab(IF)I

    move-result v0

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Landroid/support/design/widget/TabLayout;->scrollTo(II)V

    .line 344
    if-eqz p3, :cond_0

    .line 345
    int-to-float v0, p1

    add-float/2addr v0, p2

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    invoke-direct {p0, v0}, Landroid/support/design/widget/TabLayout;->setSelectedTabView(I)V

    goto :goto_0
.end method

.method public setSelectedTabIndicatorColor(I)V
    .locals 1
    .param p1, "color"    # I
        .annotation build Landroid/support/annotation/ColorInt;
        .end annotation
    .end param

    .prologue
    .line 309
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v0, p1}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->setSelectedIndicatorColor(I)V

    .line 310
    return-void
.end method

.method public setSelectedTabIndicatorHeight(I)V
    .locals 1
    .param p1, "height"    # I

    .prologue
    .line 318
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabStrip:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    invoke-virtual {v0, p1}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->setSelectedIndicatorHeight(I)V

    .line 319
    return-void
.end method

.method public setTabGravity(I)V
    .locals 1
    .param p1, "gravity"    # I

    .prologue
    .line 550
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mTabGravity:I

    if-eq v0, p1, :cond_0

    .line 551
    iput p1, p0, Landroid/support/design/widget/TabLayout;->mTabGravity:I

    .line 552
    invoke-direct {p0}, Landroid/support/design/widget/TabLayout;->applyModeAndGravity()V

    .line 554
    :cond_0
    return-void
.end method

.method public setTabMode(I)V
    .locals 1
    .param p1, "mode"    # I

    .prologue
    .line 528
    iget v0, p0, Landroid/support/design/widget/TabLayout;->mMode:I

    if-eq p1, v0, :cond_0

    .line 529
    iput p1, p0, Landroid/support/design/widget/TabLayout;->mMode:I

    .line 530
    invoke-direct {p0}, Landroid/support/design/widget/TabLayout;->applyModeAndGravity()V

    .line 532
    :cond_0
    return-void
.end method

.method public setTabTextColors(II)V
    .locals 1
    .param p1, "normalColor"    # I
    .param p2, "selectedColor"    # I

    .prologue
    .line 588
    invoke-static {p1, p2}, Landroid/support/design/widget/TabLayout;->createColorStateList(II)Landroid/content/res/ColorStateList;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroid/support/design/widget/TabLayout;->setTabTextColors(Landroid/content/res/ColorStateList;)V

    .line 589
    return-void
.end method

.method public setTabTextColors(Landroid/content/res/ColorStateList;)V
    .locals 1
    .param p1, "textColor"    # Landroid/content/res/ColorStateList;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param

    .prologue
    .line 570
    iget-object v0, p0, Landroid/support/design/widget/TabLayout;->mTabTextColors:Landroid/content/res/ColorStateList;

    if-eq v0, p1, :cond_0

    .line 571
    iput-object p1, p0, Landroid/support/design/widget/TabLayout;->mTabTextColors:Landroid/content/res/ColorStateList;

    .line 572
    invoke-direct {p0}, Landroid/support/design/widget/TabLayout;->updateAllTabs()V

    .line 574
    :cond_0
    return-void
.end method

.method public setTabsFromPagerAdapter(Landroid/support/v4/view/PagerAdapter;)V
    .locals 4
    .param p1, "adapter"    # Landroid/support/v4/view/PagerAdapter;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param

    .prologue
    .line 642
    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->removeAllTabs()V

    .line 643
    const/4 v1, 0x0

    .local v1, "i":I
    invoke-virtual {p1}, Landroid/support/v4/view/PagerAdapter;->getCount()I

    move-result v0

    .local v0, "count":I
    :goto_0
    if-ge v1, v0, :cond_0

    .line 644
    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->newTab()Landroid/support/design/widget/TabLayout$Tab;

    move-result-object v2

    invoke-virtual {p1, v1}, Landroid/support/v4/view/PagerAdapter;->getPageTitle(I)Ljava/lang/CharSequence;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/support/design/widget/TabLayout$Tab;->setText(Ljava/lang/CharSequence;)Landroid/support/design/widget/TabLayout$Tab;

    move-result-object v2

    invoke-virtual {p0, v2}, Landroid/support/design/widget/TabLayout;->addTab(Landroid/support/design/widget/TabLayout$Tab;)V

    .line 643
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 646
    :cond_0
    return-void
.end method

.method public setupWithViewPager(Landroid/support/v4/view/ViewPager;)V
    .locals 4
    .param p1, "viewPager"    # Landroid/support/v4/view/ViewPager;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param

    .prologue
    .line 609
    invoke-virtual {p1}, Landroid/support/v4/view/ViewPager;->getAdapter()Landroid/support/v4/view/PagerAdapter;

    move-result-object v0

    .line 610
    .local v0, "adapter":Landroid/support/v4/view/PagerAdapter;
    if-nez v0, :cond_0

    .line 611
    new-instance v2, Ljava/lang/IllegalArgumentException;

    const-string v3, "ViewPager does not have a PagerAdapter set"

    invoke-direct {v2, v3}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v2

    .line 615
    :cond_0
    invoke-virtual {p0, v0}, Landroid/support/design/widget/TabLayout;->setTabsFromPagerAdapter(Landroid/support/v4/view/PagerAdapter;)V

    .line 618
    new-instance v2, Landroid/support/design/widget/TabLayout$TabLayoutOnPageChangeListener;

    invoke-direct {v2, p0}, Landroid/support/design/widget/TabLayout$TabLayoutOnPageChangeListener;-><init>(Landroid/support/design/widget/TabLayout;)V

    invoke-virtual {p1, v2}, Landroid/support/v4/view/ViewPager;->addOnPageChangeListener(Landroid/support/v4/view/ViewPager$OnPageChangeListener;)V

    .line 621
    new-instance v2, Landroid/support/design/widget/TabLayout$ViewPagerOnTabSelectedListener;

    invoke-direct {v2, p1}, Landroid/support/design/widget/TabLayout$ViewPagerOnTabSelectedListener;-><init>(Landroid/support/v4/view/ViewPager;)V

    invoke-virtual {p0, v2}, Landroid/support/design/widget/TabLayout;->setOnTabSelectedListener(Landroid/support/design/widget/TabLayout$OnTabSelectedListener;)V

    .line 624
    invoke-virtual {v0}, Landroid/support/v4/view/PagerAdapter;->getCount()I

    move-result v2

    if-lez v2, :cond_1

    .line 625
    invoke-virtual {p1}, Landroid/support/v4/view/ViewPager;->getCurrentItem()I

    move-result v1

    .line 626
    .local v1, "curItem":I
    invoke-virtual {p0}, Landroid/support/design/widget/TabLayout;->getSelectedTabPosition()I

    move-result v2

    if-eq v2, v1, :cond_1

    .line 627
    invoke-virtual {p0, v1}, Landroid/support/design/widget/TabLayout;->getTabAt(I)Landroid/support/design/widget/TabLayout$Tab;

    move-result-object v2

    invoke-virtual {p0, v2}, Landroid/support/design/widget/TabLayout;->selectTab(Landroid/support/design/widget/TabLayout$Tab;)V

    .line 630
    .end local v1    # "curItem":I
    :cond_1
    return-void
.end method
