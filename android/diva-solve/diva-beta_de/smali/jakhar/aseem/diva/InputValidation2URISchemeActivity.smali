.class public Ljakhar/aseem/diva/InputValidation2URISchemeActivity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "InputValidation2URISchemeActivity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 10
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method


# virtual methods
.method public get(Landroid/view/View;)V
    .locals 3
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 24
    const v2, 0x7f0c0081

    invoke-virtual {p0, v2}, Ljakhar/aseem/diva/InputValidation2URISchemeActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    .line 25
    .local v0, "uriText":Landroid/widget/EditText;
    const v2, 0x7f0c0083

    invoke-virtual {p0, v2}, Ljakhar/aseem/diva/InputValidation2URISchemeActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/webkit/WebView;

    .line 27
    .local v1, "wview":Landroid/webkit/WebView;
    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    .line 28
    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 3
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 14
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 16
    const v2, 0x7f040021

    invoke-virtual {p0, v2}, Ljakhar/aseem/diva/InputValidation2URISchemeActivity;->setContentView(I)V

    .line 17
    const v2, 0x7f0c0083

    invoke-virtual {p0, v2}, Ljakhar/aseem/diva/InputValidation2URISchemeActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/webkit/WebView;

    .line 18
    .local v1, "wview":Landroid/webkit/WebView;
    invoke-virtual {v1}, Landroid/webkit/WebView;->getSettings()Landroid/webkit/WebSettings;

    move-result-object v0

    .line 19
    .local v0, "wset":Landroid/webkit/WebSettings;
    const/4 v2, 0x1

    invoke-virtual {v0, v2}, Landroid/webkit/WebSettings;->setJavaScriptEnabled(Z)V

    .line 21
    return-void
.end method
