.class Landroid/support/design/widget/TabLayout$SlidingTabStrip$2;
.super Landroid/support/design/widget/ValueAnimatorCompat$AnimatorListenerAdapter;
.source "TabLayout.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Landroid/support/design/widget/TabLayout$SlidingTabStrip;->animateIndicatorToPosition(II)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

.field final synthetic val$position:I


# direct methods
.method constructor <init>(Landroid/support/design/widget/TabLayout$SlidingTabStrip;I)V
    .locals 0

    .prologue
    .line 1628
    iput-object p1, p0, Landroid/support/design/widget/TabLayout$SlidingTabStrip$2;->this$1:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    iput p2, p0, Landroid/support/design/widget/TabLayout$SlidingTabStrip$2;->val$position:I

    invoke-direct {p0}, Landroid/support/design/widget/ValueAnimatorCompat$AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationCancel(Landroid/support/design/widget/ValueAnimatorCompat;)V
    .locals 2
    .param p1, "animator"    # Landroid/support/design/widget/ValueAnimatorCompat;

    .prologue
    .line 1637
    iget-object v0, p0, Landroid/support/design/widget/TabLayout$SlidingTabStrip$2;->this$1:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    iget v1, p0, Landroid/support/design/widget/TabLayout$SlidingTabStrip$2;->val$position:I

    invoke-static {v0, v1}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->access$1902(Landroid/support/design/widget/TabLayout$SlidingTabStrip;I)I

    .line 1638
    iget-object v0, p0, Landroid/support/design/widget/TabLayout$SlidingTabStrip$2;->this$1:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->access$2002(Landroid/support/design/widget/TabLayout$SlidingTabStrip;F)F

    .line 1639
    return-void
.end method

.method public onAnimationEnd(Landroid/support/design/widget/ValueAnimatorCompat;)V
    .locals 2
    .param p1, "animator"    # Landroid/support/design/widget/ValueAnimatorCompat;

    .prologue
    .line 1631
    iget-object v0, p0, Landroid/support/design/widget/TabLayout$SlidingTabStrip$2;->this$1:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    iget v1, p0, Landroid/support/design/widget/TabLayout$SlidingTabStrip$2;->val$position:I

    invoke-static {v0, v1}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->access$1902(Landroid/support/design/widget/TabLayout$SlidingTabStrip;I)I

    .line 1632
    iget-object v0, p0, Landroid/support/design/widget/TabLayout$SlidingTabStrip$2;->this$1:Landroid/support/design/widget/TabLayout$SlidingTabStrip;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Landroid/support/design/widget/TabLayout$SlidingTabStrip;->access$2002(Landroid/support/design/widget/TabLayout$SlidingTabStrip;F)F

    .line 1633
    return-void
.end method
