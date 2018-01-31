.class public Ljakhar/aseem/diva/MainActivity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "MainActivity.java"


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
    .locals 2
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 17
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 18
    const v1, 0x7f040028

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->setContentView(I)V

    .line 19
    const v1, 0x7f0c0097

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/support/v7/widget/Toolbar;

    .line 20
    .local v0, "toolbar":Landroid/support/v7/widget/Toolbar;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->setSupportActionBar(Landroid/support/v7/widget/Toolbar;)V

    .line 22
    return-void
.end method

.method public onCreateOptionsMenu(Landroid/view/Menu;)Z
    .locals 2
    .param p1, "menu"    # Landroid/view/Menu;

    .prologue
    .line 27
    invoke-virtual {p0}, Ljakhar/aseem/diva/MainActivity;->getMenuInflater()Landroid/view/MenuInflater;

    move-result-object v0

    const/high16 v1, 0x7f0d0000

    invoke-virtual {v0, v1, p1}, Landroid/view/MenuInflater;->inflate(ILandroid/view/Menu;)V

    .line 28
    const/4 v0, 0x1

    return v0
.end method

.method public onOptionsItemSelected(Landroid/view/MenuItem;)Z
    .locals 2
    .param p1, "item"    # Landroid/view/MenuItem;

    .prologue
    .line 36
    invoke-interface {p1}, Landroid/view/MenuItem;->getItemId()I

    move-result v0

    .line 39
    .local v0, "id":I
    const v1, 0x7f0c00c2

    if-ne v0, v1, :cond_0

    .line 40
    const/4 v1, 0x1

    .line 43
    :goto_0
    return v1

    :cond_0
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onOptionsItemSelected(Landroid/view/MenuItem;)Z

    move-result v1

    goto :goto_0
.end method

.method public startChallenge(Landroid/view/View;)V
    .locals 2
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 48
    const v1, 0x7f0c009f

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    if-ne p1, v1, :cond_1

    .line 49
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/LogActivity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 50
    .local v0, "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->startActivity(Landroid/content/Intent;)V

    .line 100
    .end local v0    # "i":Landroid/content/Intent;
    :cond_0
    :goto_0
    return-void

    .line 52
    :cond_1
    const v1, 0x7f0c00a0

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    if-ne p1, v1, :cond_2

    .line 53
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/HardcodeActivity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 54
    .restart local v0    # "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->startActivity(Landroid/content/Intent;)V

    goto :goto_0

    .line 56
    .end local v0    # "i":Landroid/content/Intent;
    :cond_2
    const v1, 0x7f0c00a1

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    if-ne p1, v1, :cond_3

    .line 57
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/InsecureDataStorage1Activity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 58
    .restart local v0    # "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->startActivity(Landroid/content/Intent;)V

    goto :goto_0

    .line 60
    .end local v0    # "i":Landroid/content/Intent;
    :cond_3
    const v1, 0x7f0c00a2

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    if-ne p1, v1, :cond_4

    .line 61
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/InsecureDataStorage2Activity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 62
    .restart local v0    # "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->startActivity(Landroid/content/Intent;)V

    goto :goto_0

    .line 64
    .end local v0    # "i":Landroid/content/Intent;
    :cond_4
    const v1, 0x7f0c00a3

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    if-ne p1, v1, :cond_5

    .line 65
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/InsecureDataStorage3Activity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 66
    .restart local v0    # "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->startActivity(Landroid/content/Intent;)V

    goto :goto_0

    .line 68
    .end local v0    # "i":Landroid/content/Intent;
    :cond_5
    const v1, 0x7f0c00a4

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    if-ne p1, v1, :cond_6

    .line 69
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/InsecureDataStorage4Activity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 70
    .restart local v0    # "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->startActivity(Landroid/content/Intent;)V

    goto :goto_0

    .line 72
    .end local v0    # "i":Landroid/content/Intent;
    :cond_6
    const v1, 0x7f0c00a5

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    if-ne p1, v1, :cond_7

    .line 73
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/SQLInjectionActivity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 74
    .restart local v0    # "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->startActivity(Landroid/content/Intent;)V

    goto :goto_0

    .line 76
    .end local v0    # "i":Landroid/content/Intent;
    :cond_7
    const v1, 0x7f0c00a6

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    if-ne p1, v1, :cond_8

    .line 77
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/InputValidation2URISchemeActivity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 78
    .restart local v0    # "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->startActivity(Landroid/content/Intent;)V

    goto/16 :goto_0

    .line 80
    .end local v0    # "i":Landroid/content/Intent;
    :cond_8
    const v1, 0x7f0c00a7

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    if-ne p1, v1, :cond_9

    .line 81
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/AccessControl1Activity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 82
    .restart local v0    # "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->startActivity(Landroid/content/Intent;)V

    goto/16 :goto_0

    .line 84
    .end local v0    # "i":Landroid/content/Intent;
    :cond_9
    const v1, 0x7f0c00a8

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    if-ne p1, v1, :cond_a

    .line 85
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/AccessControl2Activity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 86
    .restart local v0    # "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->startActivity(Landroid/content/Intent;)V

    goto/16 :goto_0

    .line 88
    .end local v0    # "i":Landroid/content/Intent;
    :cond_a
    const v1, 0x7f0c00a9

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    if-ne p1, v1, :cond_b

    .line 89
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/AccessControl3Activity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 90
    .restart local v0    # "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->startActivity(Landroid/content/Intent;)V

    goto/16 :goto_0

    .line 92
    .end local v0    # "i":Landroid/content/Intent;
    :cond_b
    const v1, 0x7f0c00aa

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    if-ne p1, v1, :cond_c

    .line 93
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/Hardcode2Activity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 94
    .restart local v0    # "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->startActivity(Landroid/content/Intent;)V

    goto/16 :goto_0

    .line 96
    .end local v0    # "i":Landroid/content/Intent;
    :cond_c
    const v1, 0x7f0c00ab

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    if-ne p1, v1, :cond_0

    .line 97
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/InputValidation3Activity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 98
    .restart local v0    # "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/MainActivity;->startActivity(Landroid/content/Intent;)V

    goto/16 :goto_0
.end method
