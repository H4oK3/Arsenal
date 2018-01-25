.class public Lcom/ha0k3/helloworld/DisplayMessageActivity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "DisplayMessageActivity.java"


# static fields
.field public static final DisplayTag:Ljava/lang/String; = "Display_Tag"


# instance fields
.field public msg:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 14
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    .line 15
    const-string v0, ""

    iput-object v0, p0, Lcom/ha0k3/helloworld/DisplayMessageActivity;->msg:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public isExternalStorageReadable()Z
    .locals 2

    .prologue
    .line 54
    invoke-static {}, Landroid/os/Environment;->getExternalStorageState()Ljava/lang/String;

    move-result-object v0

    .line 55
    .local v0, "state":Ljava/lang/String;
    const-string v1, "mounted"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    const-string v1, "mounted_ro"

    .line 56
    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 57
    :cond_0
    const/4 v1, 0x1

    .line 59
    :goto_0
    return v1

    :cond_1
    const/4 v1, 0x0

    goto :goto_0
.end method

.method public isExternalStorageWritable()Z
    .locals 2

    .prologue
    .line 45
    invoke-static {}, Landroid/os/Environment;->getExternalStorageState()Ljava/lang/String;

    move-result-object v0

    .line 46
    .local v0, "state":Ljava/lang/String;
    const-string v1, "mounted"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 47
    const/4 v1, 0x1

    .line 49
    :goto_0
    return v1

    :cond_0
    const/4 v1, 0x0

    goto :goto_0
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 3
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 19
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 20
    const v2, 0x7f04001b

    invoke-virtual {p0, v2}, Lcom/ha0k3/helloworld/DisplayMessageActivity;->setContentView(I)V

    .line 22
    invoke-virtual {p0}, Lcom/ha0k3/helloworld/DisplayMessageActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    .line 23
    .local v0, "intent":Landroid/content/Intent;
    const-string v2, "com.ha0k3.helloworld.MESSAGE"

    invoke-virtual {v0, v2}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    iput-object v2, p0, Lcom/ha0k3/helloworld/DisplayMessageActivity;->msg:Ljava/lang/String;

    .line 25
    const v2, 0x7f0b005e

    invoke-virtual {p0, v2}, Lcom/ha0k3/helloworld/DisplayMessageActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/TextView;

    .line 26
    .local v1, "tv":Landroid/widget/TextView;
    iget-object v2, p0, Lcom/ha0k3/helloworld/DisplayMessageActivity;->msg:Ljava/lang/String;

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 27
    return-void
.end method

.method public saveFile(Landroid/view/View;)V
    .locals 6
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 31
    const-string v3, "Display_Tag"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "saveFile() called with msg:"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget-object v5, p0, Lcom/ha0k3/helloworld/DisplayMessageActivity;->msg:Ljava/lang/String;

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 32
    const-string v1, "TestFile"

    .line 36
    .local v1, "filename":Ljava/lang/String;
    const/4 v3, 0x0

    :try_start_0
    invoke-virtual {p0, v1, v3}, Lcom/ha0k3/helloworld/DisplayMessageActivity;->openFileOutput(Ljava/lang/String;I)Ljava/io/FileOutputStream;

    move-result-object v2

    .line 37
    .local v2, "outputStream":Ljava/io/FileOutputStream;
    iget-object v3, p0, Lcom/ha0k3/helloworld/DisplayMessageActivity;->msg:Ljava/lang/String;

    invoke-virtual {v3}, Ljava/lang/String;->getBytes()[B

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/io/FileOutputStream;->write([B)V

    .line 38
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->close()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 42
    .end local v2    # "outputStream":Ljava/io/FileOutputStream;
    :goto_0
    return-void

    .line 39
    :catch_0
    move-exception v0

    .line 40
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_0
.end method
