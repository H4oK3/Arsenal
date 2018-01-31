.class Landroid/support/design/widget/FloatingActionButtonEclairMr1$1;
.super Landroid/support/design/widget/AnimationUtils$AnimationListenerAdapter;
.source "FloatingActionButtonEclairMr1.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Landroid/support/design/widget/FloatingActionButtonEclairMr1;->hide(Landroid/support/design/widget/FloatingActionButtonImpl$InternalVisibilityChangedListener;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Landroid/support/design/widget/FloatingActionButtonEclairMr1;

.field final synthetic val$listener:Landroid/support/design/widget/FloatingActionButtonImpl$InternalVisibilityChangedListener;


# direct methods
.method constructor <init>(Landroid/support/design/widget/FloatingActionButtonEclairMr1;Landroid/support/design/widget/FloatingActionButtonImpl$InternalVisibilityChangedListener;)V
    .locals 0

    .prologue
    .line 185
    iput-object p1, p0, Landroid/support/design/widget/FloatingActionButtonEclairMr1$1;->this$0:Landroid/support/design/widget/FloatingActionButtonEclairMr1;

    iput-object p2, p0, Landroid/support/design/widget/FloatingActionButtonEclairMr1$1;->val$listener:Landroid/support/design/widget/FloatingActionButtonImpl$InternalVisibilityChangedListener;

    invoke-direct {p0}, Landroid/support/design/widget/AnimationUtils$AnimationListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/view/animation/Animation;)V
    .locals 2
    .param p1, "animation"    # Landroid/view/animation/Animation;

    .prologue
    .line 193
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonEclairMr1$1;->this$0:Landroid/support/design/widget/FloatingActionButtonEclairMr1;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Landroid/support/design/widget/FloatingActionButtonEclairMr1;->access$202(Landroid/support/design/widget/FloatingActionButtonEclairMr1;Z)Z

    .line 194
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonEclairMr1$1;->this$0:Landroid/support/design/widget/FloatingActionButtonEclairMr1;

    iget-object v0, v0, Landroid/support/design/widget/FloatingActionButtonEclairMr1;->mView:Landroid/view/View;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 195
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonEclairMr1$1;->val$listener:Landroid/support/design/widget/FloatingActionButtonImpl$InternalVisibilityChangedListener;

    if-eqz v0, :cond_0

    .line 196
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonEclairMr1$1;->val$listener:Landroid/support/design/widget/FloatingActionButtonImpl$InternalVisibilityChangedListener;

    invoke-interface {v0}, Landroid/support/design/widget/FloatingActionButtonImpl$InternalVisibilityChangedListener;->onHidden()V

    .line 198
    :cond_0
    return-void
.end method

.method public onAnimationStart(Landroid/view/animation/Animation;)V
    .locals 2
    .param p1, "animation"    # Landroid/view/animation/Animation;

    .prologue
    .line 188
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonEclairMr1$1;->this$0:Landroid/support/design/widget/FloatingActionButtonEclairMr1;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Landroid/support/design/widget/FloatingActionButtonEclairMr1;->access$202(Landroid/support/design/widget/FloatingActionButtonEclairMr1;Z)Z

    .line 189
    return-void
.end method
