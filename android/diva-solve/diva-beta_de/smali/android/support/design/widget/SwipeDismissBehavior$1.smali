.class Landroid/support/design/widget/SwipeDismissBehavior$1;
.super Landroid/support/v4/widget/ViewDragHelper$Callback;
.source "SwipeDismissBehavior.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroid/support/design/widget/SwipeDismissBehavior;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field private mOriginalCapturedViewLeft:I

.field final synthetic this$0:Landroid/support/design/widget/SwipeDismissBehavior;


# direct methods
.method constructor <init>(Landroid/support/design/widget/SwipeDismissBehavior;)V
    .locals 0

    .prologue
    .line 201
    .local p0, "this":Landroid/support/design/widget/SwipeDismissBehavior$1;, "Landroid/support/design/widget/SwipeDismissBehavior.1;"
    iput-object p1, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-direct {p0}, Landroid/support/v4/widget/ViewDragHelper$Callback;-><init>()V

    return-void
.end method

.method private shouldDismiss(Landroid/view/View;F)Z
    .locals 8
    .param p1, "child"    # Landroid/view/View;
    .param p2, "xvel"    # F

    .prologue
    .local p0, "this":Landroid/support/design/widget/SwipeDismissBehavior$1;, "Landroid/support/design/widget/SwipeDismissBehavior.1;"
    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v3, 0x1

    .line 241
    cmpl-float v5, p2, v7

    if-eqz v5, :cond_6

    .line 242
    invoke-static {p1}, Landroid/support/v4/view/ViewCompat;->getLayoutDirection(Landroid/view/View;)I

    move-result v5

    if-ne v5, v3, :cond_1

    move v1, v3

    .line 245
    .local v1, "isRtl":Z
    :goto_0
    iget-object v5, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-static {v5}, Landroid/support/design/widget/SwipeDismissBehavior;->access$200(Landroid/support/design/widget/SwipeDismissBehavior;)I

    move-result v5

    const/4 v6, 0x2

    if-ne v5, v6, :cond_2

    .line 263
    .end local v1    # "isRtl":Z
    :cond_0
    :goto_1
    return v3

    :cond_1
    move v1, v4

    .line 242
    goto :goto_0

    .line 248
    .restart local v1    # "isRtl":Z
    :cond_2
    iget-object v5, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-static {v5}, Landroid/support/design/widget/SwipeDismissBehavior;->access$200(Landroid/support/design/widget/SwipeDismissBehavior;)I

    move-result v5

    if-nez v5, :cond_4

    .line 251
    if-eqz v1, :cond_3

    cmpg-float v5, p2, v7

    if-ltz v5, :cond_0

    move v3, v4

    goto :goto_1

    :cond_3
    cmpl-float v5, p2, v7

    if-gtz v5, :cond_0

    move v3, v4

    goto :goto_1

    .line 252
    :cond_4
    iget-object v5, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-static {v5}, Landroid/support/design/widget/SwipeDismissBehavior;->access$200(Landroid/support/design/widget/SwipeDismissBehavior;)I

    move-result v5

    if-ne v5, v3, :cond_7

    .line 255
    if-eqz v1, :cond_5

    cmpl-float v5, p2, v7

    if-gtz v5, :cond_0

    move v3, v4

    goto :goto_1

    :cond_5
    cmpg-float v5, p2, v7

    if-ltz v5, :cond_0

    move v3, v4

    goto :goto_1

    .line 258
    .end local v1    # "isRtl":Z
    :cond_6
    invoke-virtual {p1}, Landroid/view/View;->getLeft()I

    move-result v5

    iget v6, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    sub-int v0, v5, v6

    .line 259
    .local v0, "distance":I
    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v5

    int-to-float v5, v5

    iget-object v6, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-static {v6}, Landroid/support/design/widget/SwipeDismissBehavior;->access$300(Landroid/support/design/widget/SwipeDismissBehavior;)F

    move-result v6

    mul-float/2addr v5, v6

    invoke-static {v5}, Ljava/lang/Math;->round(F)I

    move-result v2

    .line 260
    .local v2, "thresholdDistance":I
    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v5

    if-ge v5, v2, :cond_0

    move v3, v4

    goto :goto_1

    .end local v0    # "distance":I
    .end local v2    # "thresholdDistance":I
    .restart local v1    # "isRtl":Z
    :cond_7
    move v3, v4

    .line 263
    goto :goto_1
.end method


# virtual methods
.method public clampViewPositionHorizontal(Landroid/view/View;II)I
    .locals 5
    .param p1, "child"    # Landroid/view/View;
    .param p2, "left"    # I
    .param p3, "dx"    # I

    .prologue
    .local p0, "this":Landroid/support/design/widget/SwipeDismissBehavior$1;, "Landroid/support/design/widget/SwipeDismissBehavior.1;"
    const/4 v3, 0x1

    .line 273
    invoke-static {p1}, Landroid/support/v4/view/ViewCompat;->getLayoutDirection(Landroid/view/View;)I

    move-result v4

    if-ne v4, v3, :cond_0

    move v0, v3

    .line 277
    .local v0, "isRtl":Z
    :goto_0
    iget-object v4, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-static {v4}, Landroid/support/design/widget/SwipeDismissBehavior;->access$200(Landroid/support/design/widget/SwipeDismissBehavior;)I

    move-result v4

    if-nez v4, :cond_2

    .line 278
    if-eqz v0, :cond_1

    .line 279
    iget v3, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v4

    sub-int v2, v3, v4

    .line 280
    .local v2, "min":I
    iget v1, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    .line 298
    .local v1, "max":I
    :goto_1
    invoke-static {v2, p2, v1}, Landroid/support/design/widget/SwipeDismissBehavior;->access$400(III)I

    move-result v3

    return v3

    .line 273
    .end local v0    # "isRtl":Z
    .end local v1    # "max":I
    .end local v2    # "min":I
    :cond_0
    const/4 v0, 0x0

    goto :goto_0

    .line 282
    .restart local v0    # "isRtl":Z
    :cond_1
    iget v2, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    .line 283
    .restart local v2    # "min":I
    iget v3, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v4

    add-int v1, v3, v4

    .restart local v1    # "max":I
    goto :goto_1

    .line 285
    .end local v1    # "max":I
    .end local v2    # "min":I
    :cond_2
    iget-object v4, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-static {v4}, Landroid/support/design/widget/SwipeDismissBehavior;->access$200(Landroid/support/design/widget/SwipeDismissBehavior;)I

    move-result v4

    if-ne v4, v3, :cond_4

    .line 286
    if-eqz v0, :cond_3

    .line 287
    iget v2, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    .line 288
    .restart local v2    # "min":I
    iget v3, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v4

    add-int v1, v3, v4

    .restart local v1    # "max":I
    goto :goto_1

    .line 290
    .end local v1    # "max":I
    .end local v2    # "min":I
    :cond_3
    iget v3, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v4

    sub-int v2, v3, v4

    .line 291
    .restart local v2    # "min":I
    iget v1, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    .restart local v1    # "max":I
    goto :goto_1

    .line 294
    .end local v1    # "max":I
    .end local v2    # "min":I
    :cond_4
    iget v3, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v4

    sub-int v2, v3, v4

    .line 295
    .restart local v2    # "min":I
    iget v3, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v4

    add-int v1, v3, v4

    .restart local v1    # "max":I
    goto :goto_1
.end method

.method public clampViewPositionVertical(Landroid/view/View;II)I
    .locals 1
    .param p1, "child"    # Landroid/view/View;
    .param p2, "top"    # I
    .param p3, "dy"    # I

    .prologue
    .line 303
    .local p0, "this":Landroid/support/design/widget/SwipeDismissBehavior$1;, "Landroid/support/design/widget/SwipeDismissBehavior.1;"
    invoke-virtual {p1}, Landroid/view/View;->getTop()I

    move-result v0

    return v0
.end method

.method public getViewHorizontalDragRange(Landroid/view/View;)I
    .locals 1
    .param p1, "child"    # Landroid/view/View;

    .prologue
    .line 268
    .local p0, "this":Landroid/support/design/widget/SwipeDismissBehavior$1;, "Landroid/support/design/widget/SwipeDismissBehavior.1;"
    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v0

    return v0
.end method

.method public onViewDragStateChanged(I)V
    .locals 1
    .param p1, "state"    # I

    .prologue
    .line 212
    .local p0, "this":Landroid/support/design/widget/SwipeDismissBehavior$1;, "Landroid/support/design/widget/SwipeDismissBehavior.1;"
    iget-object v0, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-static {v0}, Landroid/support/design/widget/SwipeDismissBehavior;->access$000(Landroid/support/design/widget/SwipeDismissBehavior;)Landroid/support/design/widget/SwipeDismissBehavior$OnDismissListener;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 213
    iget-object v0, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-static {v0}, Landroid/support/design/widget/SwipeDismissBehavior;->access$000(Landroid/support/design/widget/SwipeDismissBehavior;)Landroid/support/design/widget/SwipeDismissBehavior$OnDismissListener;

    move-result-object v0

    invoke-interface {v0, p1}, Landroid/support/design/widget/SwipeDismissBehavior$OnDismissListener;->onDragStateChanged(I)V

    .line 215
    :cond_0
    return-void
.end method

.method public onViewPositionChanged(Landroid/view/View;IIII)V
    .locals 8
    .param p1, "child"    # Landroid/view/View;
    .param p2, "left"    # I
    .param p3, "top"    # I
    .param p4, "dx"    # I
    .param p5, "dy"    # I

    .prologue
    .local p0, "this":Landroid/support/design/widget/SwipeDismissBehavior$1;, "Landroid/support/design/widget/SwipeDismissBehavior.1;"
    const/4 v7, 0x0

    const/high16 v6, 0x3f800000    # 1.0f

    .line 308
    iget v3, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    int-to-float v3, v3

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v4

    int-to-float v4, v4

    iget-object v5, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-static {v5}, Landroid/support/design/widget/SwipeDismissBehavior;->access$500(Landroid/support/design/widget/SwipeDismissBehavior;)F

    move-result v5

    mul-float/2addr v4, v5

    add-float v2, v3, v4

    .line 310
    .local v2, "startAlphaDistance":F
    iget v3, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    int-to-float v3, v3

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v4

    int-to-float v4, v4

    iget-object v5, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-static {v5}, Landroid/support/design/widget/SwipeDismissBehavior;->access$600(Landroid/support/design/widget/SwipeDismissBehavior;)F

    move-result v5

    mul-float/2addr v4, v5

    add-float v1, v3, v4

    .line 313
    .local v1, "endAlphaDistance":F
    int-to-float v3, p2

    cmpg-float v3, v3, v2

    if-gtz v3, :cond_0

    .line 314
    invoke-static {p1, v6}, Landroid/support/v4/view/ViewCompat;->setAlpha(Landroid/view/View;F)V

    .line 322
    :goto_0
    return-void

    .line 315
    :cond_0
    int-to-float v3, p2

    cmpl-float v3, v3, v1

    if-ltz v3, :cond_1

    .line 316
    invoke-static {p1, v7}, Landroid/support/v4/view/ViewCompat;->setAlpha(Landroid/view/View;F)V

    goto :goto_0

    .line 319
    :cond_1
    int-to-float v3, p2

    invoke-static {v2, v1, v3}, Landroid/support/design/widget/SwipeDismissBehavior;->fraction(FFF)F

    move-result v0

    .line 320
    .local v0, "distance":F
    sub-float v3, v6, v0

    invoke-static {v7, v3, v6}, Landroid/support/design/widget/SwipeDismissBehavior;->access$700(FFF)F

    move-result v3

    invoke-static {p1, v3}, Landroid/support/v4/view/ViewCompat;->setAlpha(Landroid/view/View;F)V

    goto :goto_0
.end method

.method public onViewReleased(Landroid/view/View;FF)V
    .locals 5
    .param p1, "child"    # Landroid/view/View;
    .param p2, "xvel"    # F
    .param p3, "yvel"    # F

    .prologue
    .line 219
    .local p0, "this":Landroid/support/design/widget/SwipeDismissBehavior$1;, "Landroid/support/design/widget/SwipeDismissBehavior.1;"
    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v0

    .line 221
    .local v0, "childWidth":I
    const/4 v1, 0x0

    .line 223
    .local v1, "dismiss":Z
    invoke-direct {p0, p1, p2}, Landroid/support/design/widget/SwipeDismissBehavior$1;->shouldDismiss(Landroid/view/View;F)Z

    move-result v3

    if-eqz v3, :cond_2

    .line 224
    invoke-virtual {p1}, Landroid/view/View;->getLeft()I

    move-result v3

    iget v4, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    if-ge v3, v4, :cond_1

    iget v3, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    sub-int v2, v3, v0

    .line 227
    .local v2, "targetLeft":I
    :goto_0
    const/4 v1, 0x1

    .line 233
    :goto_1
    iget-object v3, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-static {v3}, Landroid/support/design/widget/SwipeDismissBehavior;->access$100(Landroid/support/design/widget/SwipeDismissBehavior;)Landroid/support/v4/widget/ViewDragHelper;

    move-result-object v3

    invoke-virtual {p1}, Landroid/view/View;->getTop()I

    move-result v4

    invoke-virtual {v3, v2, v4}, Landroid/support/v4/widget/ViewDragHelper;->settleCapturedViewAt(II)Z

    move-result v3

    if-eqz v3, :cond_3

    .line 234
    new-instance v3, Landroid/support/design/widget/SwipeDismissBehavior$SettleRunnable;

    iget-object v4, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-direct {v3, v4, p1, v1}, Landroid/support/design/widget/SwipeDismissBehavior$SettleRunnable;-><init>(Landroid/support/design/widget/SwipeDismissBehavior;Landroid/view/View;Z)V

    invoke-static {p1, v3}, Landroid/support/v4/view/ViewCompat;->postOnAnimation(Landroid/view/View;Ljava/lang/Runnable;)V

    .line 238
    :cond_0
    :goto_2
    return-void

    .line 224
    .end local v2    # "targetLeft":I
    :cond_1
    iget v3, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    add-int v2, v3, v0

    goto :goto_0

    .line 230
    :cond_2
    iget v2, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    .restart local v2    # "targetLeft":I
    goto :goto_1

    .line 235
    :cond_3
    if-eqz v1, :cond_0

    iget-object v3, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-static {v3}, Landroid/support/design/widget/SwipeDismissBehavior;->access$000(Landroid/support/design/widget/SwipeDismissBehavior;)Landroid/support/design/widget/SwipeDismissBehavior$OnDismissListener;

    move-result-object v3

    if-eqz v3, :cond_0

    .line 236
    iget-object v3, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->this$0:Landroid/support/design/widget/SwipeDismissBehavior;

    invoke-static {v3}, Landroid/support/design/widget/SwipeDismissBehavior;->access$000(Landroid/support/design/widget/SwipeDismissBehavior;)Landroid/support/design/widget/SwipeDismissBehavior$OnDismissListener;

    move-result-object v3

    invoke-interface {v3, p1}, Landroid/support/design/widget/SwipeDismissBehavior$OnDismissListener;->onDismiss(Landroid/view/View;)V

    goto :goto_2
.end method

.method public tryCaptureView(Landroid/view/View;I)Z
    .locals 1
    .param p1, "child"    # Landroid/view/View;
    .param p2, "pointerId"    # I

    .prologue
    .line 206
    .local p0, "this":Landroid/support/design/widget/SwipeDismissBehavior$1;, "Landroid/support/design/widget/SwipeDismissBehavior.1;"
    invoke-virtual {p1}, Landroid/view/View;->getLeft()I

    move-result v0

    iput v0, p0, Landroid/support/design/widget/SwipeDismissBehavior$1;->mOriginalCapturedViewLeft:I

    .line 207
    const/4 v0, 0x1

    return v0
.end method
