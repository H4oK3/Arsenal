.class public Ljakhar/aseem/diva/InputValidation3Activity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "InputValidation3Activity.java"


# instance fields
.field private djni:Ljakhar/aseem/diva/DivaJni;


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 9
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
    const v0, 0x7f040022

    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/InputValidation3Activity;->setContentView(I)V

    .line 18
    new-instance v0, Ljakhar/aseem/diva/DivaJni;

    invoke-direct {v0}, Ljakhar/aseem/diva/DivaJni;-><init>()V

    iput-object v0, p0, Ljakhar/aseem/diva/InputValidation3Activity;->djni:Ljakhar/aseem/diva/DivaJni;

    .line 19
    return-void
.end method

.method public push(Landroid/view/View;)V
    .locals 4
    .param p1, "view"    # Landroid/view/View;

    .prologue
    const/4 v3, 0x0

    .line 22
    const v1, 0x7f0c0085

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/InputValidation3Activity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    .line 24
    .local v0, "cTxt":Landroid/widget/EditText;
    iget-object v1, p0, Ljakhar/aseem/diva/InputValidation3Activity;->djni:Ljakhar/aseem/diva/DivaJni;

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljakhar/aseem/diva/DivaJni;->initiateLaunchSequence(Ljava/lang/String;)I

    move-result v1

    if-eqz v1, :cond_0

    .line 25
    const-string v1, "Launching in T - 10 ..."

    invoke-static {p0, v1, v3}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/Toast;->show()V

    .line 31
    :goto_0
    return-void

    .line 28
    :cond_0
    const-string v1, "Access denied!"

    invoke-static {p0, v1, v3}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/Toast;->show()V

    goto :goto_0
.end method
