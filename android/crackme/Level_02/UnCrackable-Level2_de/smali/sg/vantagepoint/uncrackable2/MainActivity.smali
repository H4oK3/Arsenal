.class public Lsg/vantagepoint/uncrackable2/MainActivity;
.super Landroid/support/v7/app/c;


# instance fields
.field private m:Lsg/vantagepoint/uncrackable2/CodeCheck;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const-string v0, "foo"

    invoke-static {v0}, Ljava/lang/System;->loadLibrary(Ljava/lang/String;)V

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroid/support/v7/app/c;-><init>()V

    return-void
.end method

.method private a(Ljava/lang/String;)V
    .locals 4

    new-instance v0, Landroid/app/AlertDialog$Builder;

    invoke-direct {v0, p0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/app/AlertDialog;->setTitle(Ljava/lang/CharSequence;)V

    const-string v1, "This in unacceptable. The app is now going to exit."

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog;->setMessage(Ljava/lang/CharSequence;)V

    const/4 v1, -0x3

    const-string v2, "OK"

    new-instance v3, Lsg/vantagepoint/uncrackable2/MainActivity$1;

    invoke-direct {v3, p0}, Lsg/vantagepoint/uncrackable2/MainActivity$1;-><init>(Lsg/vantagepoint/uncrackable2/MainActivity;)V

    invoke-virtual {v0, v1, v2, v3}, Landroid/app/AlertDialog;->setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog;->setCancelable(Z)V

    invoke-virtual {v0}, Landroid/app/AlertDialog;->show()V

    return-void
.end method

.method static synthetic a(Lsg/vantagepoint/uncrackable2/MainActivity;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0, p1}, Lsg/vantagepoint/uncrackable2/MainActivity;->a(Ljava/lang/String;)V

    return-void
.end method

.method private native init()V
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 4

    const/4 v3, 0x0

#    invoke-direct {p0}, Lsg/vantagepoint/uncrackable2/MainActivity;->init()V

    invoke-static {}, Lsg/vantagepoint/a/b;->a()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-static {}, Lsg/vantagepoint/a/b;->b()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-static {}, Lsg/vantagepoint/a/b;->c()Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const-string v0, "Root detected hmm modified by Hao!"

    invoke-direct {p0, v0}, Lsg/vantagepoint/uncrackable2/MainActivity;->a(Ljava/lang/String;)V

    :cond_1
    invoke-virtual {p0}, Lsg/vantagepoint/uncrackable2/MainActivity;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lsg/vantagepoint/a/a;->a(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_2

    const-string v0, "App is debuggable!"

    invoke-direct {p0, v0}, Lsg/vantagepoint/uncrackable2/MainActivity;->a(Ljava/lang/String;)V

    :cond_2
    new-instance v0, Lsg/vantagepoint/uncrackable2/MainActivity$2;

    invoke-direct {v0, p0}, Lsg/vantagepoint/uncrackable2/MainActivity$2;-><init>(Lsg/vantagepoint/uncrackable2/MainActivity;)V

    const/4 v1, 0x3

    new-array v1, v1, [Ljava/lang/Void;

    const/4 v2, 0x0

    aput-object v3, v1, v2

    const/4 v2, 0x1

    aput-object v3, v1, v2

    const/4 v2, 0x2

    aput-object v3, v1, v2

    invoke-virtual {v0, v1}, Lsg/vantagepoint/uncrackable2/MainActivity$2;->execute([Ljava/lang/Object;)Landroid/os/AsyncTask;

    new-instance v0, Lsg/vantagepoint/uncrackable2/CodeCheck;

    invoke-direct {v0}, Lsg/vantagepoint/uncrackable2/CodeCheck;-><init>()V

    iput-object v0, p0, Lsg/vantagepoint/uncrackable2/MainActivity;->m:Lsg/vantagepoint/uncrackable2/CodeCheck;

    invoke-super {p0, p1}, Landroid/support/v7/app/c;->onCreate(Landroid/os/Bundle;)V

    const v0, 0x7f04001b

    invoke-virtual {p0, v0}, Lsg/vantagepoint/uncrackable2/MainActivity;->setContentView(I)V

    return-void
.end method

.method public verify(Landroid/view/View;)V
    .locals 4

    const v0, 0x7f0b005e

    invoke-virtual {p0, v0}, Lsg/vantagepoint/uncrackable2/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    new-instance v1, Landroid/app/AlertDialog$Builder;

    invoke-direct {v1, p0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    invoke-virtual {v1}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v1

    iget-object v2, p0, Lsg/vantagepoint/uncrackable2/MainActivity;->m:Lsg/vantagepoint/uncrackable2/CodeCheck;

    invoke-virtual {v2, v0}, Lsg/vantagepoint/uncrackable2/CodeCheck;->a(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    const-string v0, "Success!"

    invoke-virtual {v1, v0}, Landroid/app/AlertDialog;->setTitle(Ljava/lang/CharSequence;)V

    const-string v0, "This is the correct secret."

    invoke-virtual {v1, v0}, Landroid/app/AlertDialog;->setMessage(Ljava/lang/CharSequence;)V

    :goto_0
    const/4 v0, -0x3

    const-string v2, "OK"

    new-instance v3, Lsg/vantagepoint/uncrackable2/MainActivity$3;

    invoke-direct {v3, p0}, Lsg/vantagepoint/uncrackable2/MainActivity$3;-><init>(Lsg/vantagepoint/uncrackable2/MainActivity;)V

    invoke-virtual {v1, v0, v2, v3}, Landroid/app/AlertDialog;->setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V

    invoke-virtual {v1}, Landroid/app/AlertDialog;->show()V

    return-void

    :cond_0
    const-string v0, "Nope..."

    invoke-virtual {v1, v0}, Landroid/app/AlertDialog;->setTitle(Ljava/lang/CharSequence;)V

    const-string v0, "That\'s not it. Try again."

    invoke-virtual {v1, v0}, Landroid/app/AlertDialog;->setMessage(Ljava/lang/CharSequence;)V

    goto :goto_0
.end method
