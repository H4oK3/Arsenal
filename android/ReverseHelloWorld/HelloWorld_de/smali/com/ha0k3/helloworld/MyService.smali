.class public Lcom/ha0k3/helloworld/MyService;
.super Landroid/app/Service;
.source "MyService.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/ha0k3/helloworld/MyService$MyBinder;
    }
.end annotation


# static fields
.field public static final ServiceTag:Ljava/lang/String; = "Service_Tag"


# instance fields
.field private mBinder:Lcom/ha0k3/helloworld/MyService$MyBinder;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 13
    invoke-direct {p0}, Landroid/app/Service;-><init>()V

    .line 16
    new-instance v0, Lcom/ha0k3/helloworld/MyService$MyBinder;

    invoke-direct {v0, p0}, Lcom/ha0k3/helloworld/MyService$MyBinder;-><init>(Lcom/ha0k3/helloworld/MyService;)V

    iput-object v0, p0, Lcom/ha0k3/helloworld/MyService;->mBinder:Lcom/ha0k3/helloworld/MyService$MyBinder;

    return-void
.end method


# virtual methods
.method public onBind(Landroid/content/Intent;)Landroid/os/IBinder;
    .locals 3
    .param p1, "intent"    # Landroid/content/Intent;

    .prologue
    .line 39
    const-string v1, "Bind_TAG"

    invoke-virtual {p1, v1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 40
    .local v0, "msg":Ljava/lang/String;
    const-string v1, "Service_Tag"

    const-string v2, "onBind() executed"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 41
    iget-object v1, p0, Lcom/ha0k3/helloworld/MyService;->mBinder:Lcom/ha0k3/helloworld/MyService$MyBinder;

    return-object v1
.end method

.method public onCreate()V
    .locals 2

    .prologue
    .line 19
    const-string v0, "Service_Tag"

    const-string v1, "Created Srvis.."

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 20
    invoke-super {p0}, Landroid/app/Service;->onCreate()V

    .line 21
    return-void
.end method

.method public onDestroy()V
    .locals 2

    .prologue
    .line 31
    invoke-super {p0}, Landroid/app/Service;->onDestroy()V

    .line 32
    const-string v0, "Service_Tag"

    const-string v1, "onDestroy() executed"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 33
    return-void
.end method

.method public onStartCommand(Landroid/content/Intent;II)I
    .locals 2
    .param p1, "intent"    # Landroid/content/Intent;
    .param p2, "flags"    # I
    .param p3, "startId"    # I

    .prologue
    .line 25
    const-string v0, "Service_Tag"

    const-string v1, "onStartCommand() executed"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 26
    invoke-super {p0, p1, p2, p3}, Landroid/app/Service;->onStartCommand(Landroid/content/Intent;II)I

    move-result v0

    return v0
.end method
