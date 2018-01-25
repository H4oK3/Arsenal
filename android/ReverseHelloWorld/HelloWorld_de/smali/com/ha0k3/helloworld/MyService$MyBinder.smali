.class Lcom/ha0k3/helloworld/MyService$MyBinder;
.super Landroid/os/Binder;
.source "MyService.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/ha0k3/helloworld/MyService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "MyBinder"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/ha0k3/helloworld/MyService;


# direct methods
.method constructor <init>(Lcom/ha0k3/helloworld/MyService;)V
    .locals 0
    .param p1, "this$0"    # Lcom/ha0k3/helloworld/MyService;

    .prologue
    .line 44
    iput-object p1, p0, Lcom/ha0k3/helloworld/MyService$MyBinder;->this$0:Lcom/ha0k3/helloworld/MyService;

    invoke-direct {p0}, Landroid/os/Binder;-><init>()V

    return-void
.end method


# virtual methods
.method public startDownload(Ljava/lang/String;)V
    .locals 3
    .param p1, "fileName"    # Ljava/lang/String;

    .prologue
    .line 46
    const-string v0, "Service_Tag"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "startDownload() executed, trying to download: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 48
    return-void
.end method
