.class Landroid/support/v4/view/ViewCompatICS$TooltipHandler$1;
.super Ljava/lang/Object;
.source "ViewCompatICS.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroid/support/v4/view/ViewCompatICS$TooltipHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Landroid/support/v4/view/ViewCompatICS$TooltipHandler;


# direct methods
.method constructor <init>(Landroid/support/v4/view/ViewCompatICS$TooltipHandler;)V
    .locals 0
    .param p1, "this$0"    # Landroid/support/v4/view/ViewCompatICS$TooltipHandler;

    .prologue
    .line 48
    iput-object p1, p0, Landroid/support/v4/view/ViewCompatICS$TooltipHandler$1;->this$0:Landroid/support/v4/view/ViewCompatICS$TooltipHandler;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    .prologue
    .line 51
    iget-object v0, p0, Landroid/support/v4/view/ViewCompatICS$TooltipHandler$1;->this$0:Landroid/support/v4/view/ViewCompatICS$TooltipHandler;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Landroid/support/v4/view/ViewCompatICS$TooltipHandler;->access$000(Landroid/support/v4/view/ViewCompatICS$TooltipHandler;I)V

    .line 52
    return-void
.end method
