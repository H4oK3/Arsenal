.class public Landroid/support/design/widget/AppBarLayout$ScrollingViewBehavior;
.super Landroid/support/design/widget/HeaderScrollingViewBehavior;
.source "AppBarLayout.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroid/support/design/widget/AppBarLayout;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "ScrollingViewBehavior"
.end annotation


# instance fields
.field private mOverlayTop:I


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 1112
    invoke-direct {p0}, Landroid/support/design/widget/HeaderScrollingViewBehavior;-><init>()V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 3
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;

    .prologue
    .line 1115
    invoke-direct {p0, p1, p2}, Landroid/support/design/widget/HeaderScrollingViewBehavior;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 1117
    sget-object v1, Landroid/support/design/R$styleable;->ScrollingViewBehavior_Params:[I

    invoke-virtual {p1, p2, v1}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 1119
    .local v0, "a":Landroid/content/res/TypedArray;
    sget v1, Landroid/support/design/R$styleable;->ScrollingViewBehavior_Params_behavior_overlapTop:I

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v1

    iput v1, p0, Landroid/support/design/widget/AppBarLayout$ScrollingViewBehavior;->mOverlayTop:I

    .line 1121
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 1122
    return-void
.end method

.method private getOverlapForOffset(Landroid/view/View;I)I
    .locals 8
    .param p1, "dependency"    # Landroid/view/View;
    .param p2, "offset"    # I

    .prologue
    const/4 v5, 0x0

    .line 1168
    iget v6, p0, Landroid/support/design/widget/AppBarLayout$ScrollingViewBehavior;->mOverlayTop:I

    if-eqz v6, :cond_1

    instance-of v6, p1, Landroid/support/design/widget/AppBarLayout;

    if-eqz v6, :cond_1

    move-object v0, p1

    .line 1169
    check-cast v0, Landroid/support/design/widget/AppBarLayout;

    .line 1170
    .local v0, "abl":Landroid/support/design/widget/AppBarLayout;
    invoke-virtual {v0}, Landroid/support/design/widget/AppBarLayout;->getTotalScrollRange()I

    move-result v4

    .line 1171
    .local v4, "totalScrollRange":I
    invoke-static {v0}, Landroid/support/design/widget/AppBarLayout;->access$200(Landroid/support/design/widget/AppBarLayout;)I

    move-result v3

    .line 1173
    .local v3, "preScrollDown":I
    if-eqz v3, :cond_0

    add-int v6, v4, p2

    if-gt v6, v3, :cond_0

    .line 1186
    .end local v0    # "abl":Landroid/support/design/widget/AppBarLayout;
    .end local v3    # "preScrollDown":I
    .end local v4    # "totalScrollRange":I
    :goto_0
    return v5

    .line 1177
    .restart local v0    # "abl":Landroid/support/design/widget/AppBarLayout;
    .restart local v3    # "preScrollDown":I
    .restart local v4    # "totalScrollRange":I
    :cond_0
    sub-int v1, v4, v3

    .line 1178
    .local v1, "availScrollRange":I
    if-eqz v1, :cond_1

    .line 1180
    int-to-float v6, p2

    int-to-float v7, v1

    div-float v2, v6, v7

    .line 1181
    .local v2, "percScrolled":F
    const/high16 v6, 0x3f800000    # 1.0f

    add-float/2addr v6, v2

    iget v7, p0, Landroid/support/design/widget/AppBarLayout$ScrollingViewBehavior;->mOverlayTop:I

    int-to-float v7, v7

    mul-float/2addr v6, v7

    invoke-static {v6}, Ljava/lang/Math;->round(F)I

    move-result v6

    iget v7, p0, Landroid/support/design/widget/AppBarLayout$ScrollingViewBehavior;->mOverlayTop:I

    invoke-static {v6, v5, v7}, Landroid/support/design/widget/MathUtils;->constrain(III)I

    move-result v5

    goto :goto_0

    .line 1186
    .end local v0    # "abl":Landroid/support/design/widget/AppBarLayout;
    .end local v1    # "availScrollRange":I
    .end local v2    # "percScrolled":F
    .end local v3    # "preScrollDown":I
    .end local v4    # "totalScrollRange":I
    :cond_1
    iget v5, p0, Landroid/support/design/widget/AppBarLayout$ScrollingViewBehavior;->mOverlayTop:I

    goto :goto_0
.end method

.method private updateOffset(Landroid/support/design/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;)Z
    .locals 4
    .param p1, "parent"    # Landroid/support/design/widget/CoordinatorLayout;
    .param p2, "child"    # Landroid/view/View;
    .param p3, "dependency"    # Landroid/view/View;

    .prologue
    .line 1155
    invoke-virtual {p3}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v2

    check-cast v2, Landroid/support/design/widget/CoordinatorLayout$LayoutParams;

    invoke-virtual {v2}, Landroid/support/design/widget/CoordinatorLayout$LayoutParams;->getBehavior()Landroid/support/design/widget/CoordinatorLayout$Behavior;

    move-result-object v0

    .line 1157
    .local v0, "behavior":Landroid/support/design/widget/CoordinatorLayout$Behavior;
    instance-of v2, v0, Landroid/support/design/widget/AppBarLayout$Behavior;

    if-eqz v2, :cond_0

    .line 1159
    check-cast v0, Landroid/support/design/widget/AppBarLayout$Behavior;

    .end local v0    # "behavior":Landroid/support/design/widget/CoordinatorLayout$Behavior;
    invoke-virtual {v0}, Landroid/support/design/widget/AppBarLayout$Behavior;->getTopBottomOffsetForScrollingSibling()I

    move-result v1

    .line 1160
    .local v1, "offset":I
    invoke-virtual {p3}, Landroid/view/View;->getHeight()I

    move-result v2

    add-int/2addr v2, v1

    invoke-direct {p0, p3, v1}, Landroid/support/design/widget/AppBarLayout$ScrollingViewBehavior;->getOverlapForOffset(Landroid/view/View;I)I

    move-result v3

    sub-int/2addr v2, v3

    invoke-virtual {p0, v2}, Landroid/support/design/widget/AppBarLayout$ScrollingViewBehavior;->setTopAndBottomOffset(I)Z

    .line 1162
    const/4 v2, 0x1

    .line 1164
    .end local v1    # "offset":I
    :goto_0
    return v2

    .restart local v0    # "behavior":Landroid/support/design/widget/CoordinatorLayout$Behavior;
    :cond_0
    const/4 v2, 0x0

    goto :goto_0
.end method


# virtual methods
.method findFirstDependency(Ljava/util/List;)Landroid/view/View;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List",
            "<",
            "Landroid/view/View;",
            ">;)",
            "Landroid/view/View;"
        }
    .end annotation

    .prologue
    .line 1207
    .local p1, "views":Ljava/util/List;, "Ljava/util/List<Landroid/view/View;>;"
    const/4 v0, 0x0

    .local v0, "i":I
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v2

    .local v2, "z":I
    :goto_0
    if-ge v0, v2, :cond_1

    .line 1208
    invoke-interface {p1, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/view/View;

    .line 1209
    .local v1, "view":Landroid/view/View;
    instance-of v3, v1, Landroid/support/design/widget/AppBarLayout;

    if-eqz v3, :cond_0

    .line 1213
    .end local v1    # "view":Landroid/view/View;
    :goto_1
    return-object v1

    .line 1207
    .restart local v1    # "view":Landroid/view/View;
    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 1213
    .end local v1    # "view":Landroid/view/View;
    :cond_1
    const/4 v1, 0x0

    goto :goto_1
.end method

.method public bridge synthetic getLeftAndRightOffset()I
    .locals 1

    .prologue
    .line 1109
    invoke-super {p0}, Landroid/support/design/widget/HeaderScrollingViewBehavior;->getLeftAndRightOffset()I

    move-result v0

    return v0
.end method

.method public getOverlayTop()I
    .locals 1

    .prologue
    .line 1202
    iget v0, p0, Landroid/support/design/widget/AppBarLayout$ScrollingViewBehavior;->mOverlayTop:I

    return v0
.end method

.method getScrollRange(Landroid/view/View;)I
    .locals 1
    .param p1, "v"    # Landroid/view/View;

    .prologue
    .line 1218
    instance-of v0, p1, Landroid/support/design/widget/AppBarLayout;

    if-eqz v0, :cond_0

    .line 1219
    check-cast p1, Landroid/support/design/widget/AppBarLayout;

    .end local p1    # "v":Landroid/view/View;
    invoke-virtual {p1}, Landroid/support/design/widget/AppBarLayout;->getTotalScrollRange()I

    move-result v0

    .line 1221
    :goto_0
    return v0

    .restart local p1    # "v":Landroid/view/View;
    :cond_0
    invoke-super {p0, p1}, Landroid/support/design/widget/HeaderScrollingViewBehavior;->getScrollRange(Landroid/view/View;)I

    move-result v0

    goto :goto_0
.end method

.method public bridge synthetic getTopAndBottomOffset()I
    .locals 1

    .prologue
    .line 1109
    invoke-super {p0}, Landroid/support/design/widget/HeaderScrollingViewBehavior;->getTopAndBottomOffset()I

    move-result v0

    return v0
.end method

.method public layoutDependsOn(Landroid/support/design/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;)Z
    .locals 1
    .param p1, "parent"    # Landroid/support/design/widget/CoordinatorLayout;
    .param p2, "child"    # Landroid/view/View;
    .param p3, "dependency"    # Landroid/view/View;

    .prologue
    .line 1127
    instance-of v0, p3, Landroid/support/design/widget/AppBarLayout;

    return v0
.end method

.method public onDependentViewChanged(Landroid/support/design/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;)Z
    .locals 1
    .param p1, "parent"    # Landroid/support/design/widget/CoordinatorLayout;
    .param p2, "child"    # Landroid/view/View;
    .param p3, "dependency"    # Landroid/view/View;

    .prologue
    .line 1150
    invoke-direct {p0, p1, p2, p3}, Landroid/support/design/widget/AppBarLayout$ScrollingViewBehavior;->updateOffset(Landroid/support/design/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;)Z

    .line 1151
    const/4 v0, 0x0

    return v0
.end method

.method public onLayoutChild(Landroid/support/design/widget/CoordinatorLayout;Landroid/view/View;I)Z
    .locals 4
    .param p1, "parent"    # Landroid/support/design/widget/CoordinatorLayout;
    .param p2, "child"    # Landroid/view/View;
    .param p3, "layoutDirection"    # I

    .prologue
    .line 1133
    invoke-super {p0, p1, p2, p3}, Landroid/support/design/widget/HeaderScrollingViewBehavior;->onLayoutChild(Landroid/support/design/widget/CoordinatorLayout;Landroid/view/View;I)Z

    .line 1137
    invoke-virtual {p1, p2}, Landroid/support/design/widget/CoordinatorLayout;->getDependencies(Landroid/view/View;)Ljava/util/List;

    move-result-object v0

    .line 1138
    .local v0, "dependencies":Ljava/util/List;, "Ljava/util/List<Landroid/view/View;>;"
    const/4 v1, 0x0

    .local v1, "i":I
    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v2

    .local v2, "z":I
    :goto_0
    if-ge v1, v2, :cond_0

    .line 1139
    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroid/view/View;

    invoke-direct {p0, p1, p2, v3}, Landroid/support/design/widget/AppBarLayout$ScrollingViewBehavior;->updateOffset(Landroid/support/design/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 1144
    :cond_0
    const/4 v3, 0x1

    return v3

    .line 1138
    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0
.end method

.method public bridge synthetic onMeasureChild(Landroid/support/design/widget/CoordinatorLayout;Landroid/view/View;IIII)Z
    .locals 1
    .param p1, "x0"    # Landroid/support/design/widget/CoordinatorLayout;
    .param p2, "x1"    # Landroid/view/View;
    .param p3, "x2"    # I
    .param p4, "x3"    # I
    .param p5, "x4"    # I
    .param p6, "x5"    # I

    .prologue
    .line 1109
    invoke-super/range {p0 .. p6}, Landroid/support/design/widget/HeaderScrollingViewBehavior;->onMeasureChild(Landroid/support/design/widget/CoordinatorLayout;Landroid/view/View;IIII)Z

    move-result v0

    return v0
.end method

.method public bridge synthetic setLeftAndRightOffset(I)Z
    .locals 1
    .param p1, "x0"    # I

    .prologue
    .line 1109
    invoke-super {p0, p1}, Landroid/support/design/widget/HeaderScrollingViewBehavior;->setLeftAndRightOffset(I)Z

    move-result v0

    return v0
.end method

.method public setOverlayTop(I)V
    .locals 0
    .param p1, "overlayTop"    # I

    .prologue
    .line 1195
    iput p1, p0, Landroid/support/design/widget/AppBarLayout$ScrollingViewBehavior;->mOverlayTop:I

    .line 1196
    return-void
.end method

.method public bridge synthetic setTopAndBottomOffset(I)Z
    .locals 1
    .param p1, "x0"    # I

    .prologue
    .line 1109
    invoke-super {p0, p1}, Landroid/support/design/widget/HeaderScrollingViewBehavior;->setTopAndBottomOffset(I)Z

    move-result v0

    return v0
.end method
