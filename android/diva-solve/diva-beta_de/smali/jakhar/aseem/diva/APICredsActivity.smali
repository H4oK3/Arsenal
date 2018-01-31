.class public Ljakhar/aseem/diva/APICredsActivity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "APICredsActivity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 7
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 3
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 11
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 12
    const v2, 0x7f04001d

    invoke-virtual {p0, v2}, Ljakhar/aseem/diva/APICredsActivity;->setContentView(I)V

    .line 13
    const v2, 0x7f0c0076

    invoke-virtual {p0, v2}, Ljakhar/aseem/diva/APICredsActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    .line 16
    .local v0, "apicview":Landroid/widget/TextView;
    const-string v1, "API Key: 123secretapikey123\nAPI User name: diva\nAPI Password: p@ssword"

    .line 19
    .local v1, "apidetails":Ljava/lang/String;
    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 20
    return-void
.end method
