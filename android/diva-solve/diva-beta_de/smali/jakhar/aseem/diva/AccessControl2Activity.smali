.class public Ljakhar/aseem/diva/AccessControl2Activity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "AccessControl2Activity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 11
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 15
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 16
    const v0, 0x7f04001a

    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/AccessControl2Activity;->setContentView(I)V

    .line 17
    return-void
.end method

.method public viewAPICredentials(Landroid/view/View;)V
    .locals 5
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 21
    const v3, 0x7f0c006d

    invoke-virtual {p0, v3}, Ljakhar/aseem/diva/AccessControl2Activity;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/RadioButton;

    .line 22
    .local v2, "rbregnow":Landroid/widget/RadioButton;
    new-instance v1, Landroid/content/Intent;

    invoke-direct {v1}, Landroid/content/Intent;-><init>()V

    .line 23
    .local v1, "i":Landroid/content/Intent;
    invoke-virtual {v2}, Landroid/widget/RadioButton;->isChecked()Z

    move-result v0

    .line 26
    .local v0, "chk_pin":Z
    const-string v3, "jakhar.aseem.diva.action.VIEW_CREDS2"

    invoke-virtual {v1, v3}, Landroid/content/Intent;->setAction(Ljava/lang/String;)Landroid/content/Intent;

    .line 27
    const v3, 0x7f060026

    invoke-virtual {p0, v3}, Ljakhar/aseem/diva/AccessControl2Activity;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3, v0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    .line 29
    invoke-virtual {p0}, Ljakhar/aseem/diva/AccessControl2Activity;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v3

    invoke-virtual {v1, v3}, Landroid/content/Intent;->resolveActivity(Landroid/content/pm/PackageManager;)Landroid/content/ComponentName;

    move-result-object v3

    if-eqz v3, :cond_0

    .line 30
    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/AccessControl2Activity;->startActivity(Landroid/content/Intent;)V

    .line 36
    :goto_0
    return-void

    .line 33
    :cond_0
    const-string v3, "Error while getting Tveeter API details"

    const/4 v4, 0x0

    invoke-static {p0, v3, v4}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v3

    invoke-virtual {v3}, Landroid/widget/Toast;->show()V

    .line 34
    const-string v3, "Diva-aci1"

    const-string v4, "Couldn\'t resolve the Intent VIEW_CREDS2 to our activity"

    invoke-static {v3, v4}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0
.end method
