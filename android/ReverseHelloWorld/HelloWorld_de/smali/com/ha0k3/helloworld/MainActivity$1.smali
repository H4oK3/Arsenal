.class Lcom/ha0k3/helloworld/MainActivity$1;
.super Ljava/lang/Object;
.source "MainActivity.java"

# interfaces
.implements Landroid/content/ServiceConnection;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/ha0k3/helloworld/MainActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/ha0k3/helloworld/MainActivity;


# direct methods
.method constructor <init>(Lcom/ha0k3/helloworld/MainActivity;)V
    .locals 0
    .param p1, "this$0"    # Lcom/ha0k3/helloworld/MainActivity;

    .prologue
    .line 49
    iput-object p1, p0, Lcom/ha0k3/helloworld/MainActivity$1;->this$0:Lcom/ha0k3/helloworld/MainActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V
    .locals 2
    .param p1, "name"    # Landroid/content/ComponentName;
    .param p2, "service"    # Landroid/os/IBinder;

    .prologue
    .line 56
    const-string v0, "evil.bin"

    .line 57
    .local v0, "test_file_name":Ljava/lang/String;
    iget-object v1, p0, Lcom/ha0k3/helloworld/MainActivity$1;->this$0:Lcom/ha0k3/helloworld/MainActivity;

    check-cast p2, Lcom/ha0k3/helloworld/MyService$MyBinder;

    .end local p2    # "service":Landroid/os/IBinder;
    invoke-static {v1, p2}, Lcom/ha0k3/helloworld/MainActivity;->access$002(Lcom/ha0k3/helloworld/MainActivity;Lcom/ha0k3/helloworld/MyService$MyBinder;)Lcom/ha0k3/helloworld/MyService$MyBinder;

    .line 58
    iget-object v1, p0, Lcom/ha0k3/helloworld/MainActivity$1;->this$0:Lcom/ha0k3/helloworld/MainActivity;

    invoke-static {v1}, Lcom/ha0k3/helloworld/MainActivity;->access$000(Lcom/ha0k3/helloworld/MainActivity;)Lcom/ha0k3/helloworld/MyService$MyBinder;

    move-result-object v1

    invoke-virtual {v1, v0}, Lcom/ha0k3/helloworld/MyService$MyBinder;->startDownload(Ljava/lang/String;)V

    .line 59
    return-void
.end method

.method public onServiceDisconnected(Landroid/content/ComponentName;)V
    .locals 0
    .param p1, "name"    # Landroid/content/ComponentName;

    .prologue
    .line 52
    return-void
.end method
