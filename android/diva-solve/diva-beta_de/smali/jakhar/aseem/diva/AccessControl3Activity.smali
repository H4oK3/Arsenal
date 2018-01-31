.class public Ljakhar/aseem/diva/AccessControl3Activity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "AccessControl3Activity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 13
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method


# virtual methods
.method public addPin(Landroid/view/View;)V
    .locals 7
    .param p1, "view"    # Landroid/view/View;

    .prologue
    const/4 v6, 0x0

    .line 31
    invoke-static {p0}, Landroid/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v3

    .line 32
    .local v3, "spref":Landroid/content/SharedPreferences;
    invoke-interface {v3}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v2

    .line 33
    .local v2, "spedit":Landroid/content/SharedPreferences$Editor;
    const v5, 0x7f0c0070

    invoke-virtual {p0, v5}, Ljakhar/aseem/diva/AccessControl3Activity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/EditText;

    .line 34
    .local v1, "pinTxt":Landroid/widget/EditText;
    invoke-virtual {v1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    .line 36
    .local v0, "pin":Ljava/lang/String;
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_1

    .line 37
    :cond_0
    const-string v5, "Please Enter a valid pin!"

    invoke-static {p0, v5, v6}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v5

    invoke-virtual {v5}, Landroid/widget/Toast;->show()V

    .line 49
    :goto_0
    return-void

    .line 40
    :cond_1
    const v5, 0x7f0c0072

    invoke-virtual {p0, v5}, Ljakhar/aseem/diva/AccessControl3Activity;->findViewById(I)Landroid/view/View;

    move-result-object v4

    check-cast v4, Landroid/widget/Button;

    .line 41
    .local v4, "vbutton":Landroid/widget/Button;
    const v5, 0x7f06004a

    invoke-virtual {p0, v5}, Ljakhar/aseem/diva/AccessControl3Activity;->getString(I)Ljava/lang/String;

    move-result-object v5

    invoke-interface {v2, v5, v0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 42
    invoke-interface {v2}, Landroid/content/SharedPreferences$Editor;->commit()Z

    .line 43
    invoke-virtual {v4}, Landroid/widget/Button;->getVisibility()I

    move-result v5

    if-eqz v5, :cond_2

    .line 44
    invoke-virtual {v4, v6}, Landroid/widget/Button;->setVisibility(I)V

    .line 47
    :cond_2
    const-string v5, "PIN Created successfully. Private notes are now protected with PIN"

    invoke-static {p0, v5, v6}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v5

    invoke-virtual {v5}, Landroid/widget/Toast;->show()V

    goto :goto_0
.end method

.method public goToNotes(Landroid/view/View;)V
    .locals 2
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 52
    new-instance v0, Landroid/content/Intent;

    const-class v1, Ljakhar/aseem/diva/AccessControl3NotesActivity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 53
    .local v0, "i":Landroid/content/Intent;
    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/AccessControl3Activity;->startActivity(Landroid/content/Intent;)V

    .line 54
    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 5
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 18
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 19
    const v3, 0x7f04001b

    invoke-virtual {p0, v3}, Ljakhar/aseem/diva/AccessControl3Activity;->setContentView(I)V

    .line 21
    invoke-static {p0}, Landroid/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v1

    .line 22
    .local v1, "spref":Landroid/content/SharedPreferences;
    const v3, 0x7f06004a

    invoke-virtual {p0, v3}, Ljakhar/aseem/diva/AccessControl3Activity;->getString(I)Ljava/lang/String;

    move-result-object v3

    const-string v4, ""

    invoke-interface {v1, v3, v4}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 24
    .local v0, "pin":Ljava/lang/String;
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_0

    .line 25
    const v3, 0x7f0c0072

    invoke-virtual {p0, v3}, Ljakhar/aseem/diva/AccessControl3Activity;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/Button;

    .line 26
    .local v2, "vbutton":Landroid/widget/Button;
    const/4 v3, 0x0

    invoke-virtual {v2, v3}, Landroid/widget/Button;->setVisibility(I)V

    .line 28
    .end local v2    # "vbutton":Landroid/widget/Button;
    :cond_0
    return-void
.end method
