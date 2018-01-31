.class public Ljakhar/aseem/diva/AccessControl1Activity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "AccessControl1Activity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 10
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 14
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 15
    const v0, 0x7f040019

    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/AccessControl1Activity;->setContentView(I)V

    .line 16
    return-void
.end method

.method public viewAPICredentials(Landroid/view/View;)V
    .locals 3
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 20
    new-instance v0, Landroid/content/Intent;

    invoke-direct {v0}, Landroid/content/Intent;-><init>()V

    .line 21
    .local v0, "i":Landroid/content/Intent;
    const-string v1, "jakhar.aseem.diva.action.VIEW_CREDS"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->setAction(Ljava/lang/String;)Landroid/content/Intent;

    .line 23
    invoke-virtual {p0}, Ljakhar/aseem/diva/AccessControl1Activity;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/content/Intent;->resolveActivity(Landroid/content/pm/PackageManager;)Landroid/content/ComponentName;

    move-result-object v1

    if-eqz v1, :cond_0

    .line 24
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/AccessControl1Activity;->startActivity(Landroid/content/Intent;)V

    .line 30
    :goto_0
    return-void

    .line 27
    :cond_0
    const-string v1, "Error while getting API details"

    const/4 v2, 0x0

    invoke-static {p0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/Toast;->show()V

    .line 28
    const-string v1, "Diva-aci1"

    const-string v2, "Couldn\'t resolve the Intent VIEW_CREDS to our activity"

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0
.end method
