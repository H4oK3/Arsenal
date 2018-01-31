.class public Ljakhar/aseem/diva/APICreds2Activity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "APICreds2Activity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 13
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 9
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    const/4 v8, 0x0

    .line 17
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 18
    const v6, 0x7f04001e

    invoke-virtual {p0, v6}, Ljakhar/aseem/diva/APICreds2Activity;->setContentView(I)V

    .line 19
    const v6, 0x7f0c0077

    invoke-virtual {p0, v6}, Ljakhar/aseem/diva/APICreds2Activity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    .line 20
    .local v0, "apicview":Landroid/widget/TextView;
    const v6, 0x7f0c0078

    invoke-virtual {p0, v6}, Ljakhar/aseem/diva/APICreds2Activity;->findViewById(I)Landroid/view/View;

    move-result-object v4

    check-cast v4, Landroid/widget/EditText;

    .line 21
    .local v4, "pintext":Landroid/widget/EditText;
    const v6, 0x7f0c0079

    invoke-virtual {p0, v6}, Ljakhar/aseem/diva/APICreds2Activity;->findViewById(I)Landroid/view/View;

    move-result-object v5

    check-cast v5, Landroid/widget/Button;

    .line 22
    .local v5, "vbutton":Landroid/widget/Button;
    invoke-virtual {p0}, Ljakhar/aseem/diva/APICreds2Activity;->getIntent()Landroid/content/Intent;

    move-result-object v3

    .line 23
    .local v3, "i":Landroid/content/Intent;
    const v6, 0x7f060026

    invoke-virtual {p0, v6}, Ljakhar/aseem/diva/APICreds2Activity;->getString(I)Ljava/lang/String;

    move-result-object v6

    const/4 v7, 0x1

    invoke-virtual {v3, v6, v7}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v2

    .line 25
    .local v2, "bcheck":Z
    if-nez v2, :cond_0

    .line 28
    const-string v1, "TVEETER API Key: secrettveeterapikey\nAPI User name: diva2\nAPI Password: p@ssword2"

    .line 30
    .local v1, "apidetails":Ljava/lang/String;
    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 37
    .end local v1    # "apidetails":Ljava/lang/String;
    :goto_0
    return-void

    .line 33
    :cond_0
    const-string v6, "Register yourself at http://payatu.com to get your PIN and then login with that PIN!"

    invoke-virtual {v0, v6}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 34
    invoke-virtual {v4, v8}, Landroid/widget/EditText;->setVisibility(I)V

    .line 35
    invoke-virtual {v5, v8}, Landroid/widget/Button;->setVisibility(I)V

    goto :goto_0
.end method

.method public viewCreds(Landroid/view/View;)V
    .locals 2
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 44
    const-string v0, "Invalid PIN. Please try again"

    const/4 v1, 0x0

    invoke-static {p0, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 45
    return-void
.end method
