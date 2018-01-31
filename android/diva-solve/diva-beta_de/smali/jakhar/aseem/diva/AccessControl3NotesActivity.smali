.class public Ljakhar/aseem/diva/AccessControl3NotesActivity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "AccessControl3NotesActivity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 17
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method


# virtual methods
.method public accessNotes(Landroid/view/View;)V
    .locals 14
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 26
    const v1, 0x7f0c0073

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/AccessControl3NotesActivity;->findViewById(I)Landroid/view/View;

    move-result-object v10

    check-cast v10, Landroid/widget/EditText;

    .line 27
    .local v10, "pinTxt":Landroid/widget/EditText;
    const v1, 0x7f0c0074

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/AccessControl3NotesActivity;->findViewById(I)Landroid/view/View;

    move-result-object v7

    check-cast v7, Landroid/widget/Button;

    .line 28
    .local v7, "abutton":Landroid/widget/Button;
    invoke-static {p0}, Landroid/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v11

    .line 29
    .local v11, "spref":Landroid/content/SharedPreferences;
    const v1, 0x7f06004a

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/AccessControl3NotesActivity;->getString(I)Ljava/lang/String;

    move-result-object v1

    const-string v2, ""

    invoke-interface {v11, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    .line 30
    .local v9, "pin":Ljava/lang/String;
    invoke-virtual {v10}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v12

    .line 33
    .local v12, "userpin":Ljava/lang/String;
    invoke-virtual {v12, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 35
    const v1, 0x7f0c0075

    invoke-virtual {p0, v1}, Ljakhar/aseem/diva/AccessControl3NotesActivity;->findViewById(I)Landroid/view/View;

    move-result-object v8

    check-cast v8, Landroid/widget/ListView;

    .line 36
    .local v8, "lview":Landroid/widget/ListView;
    invoke-virtual {p0}, Ljakhar/aseem/diva/AccessControl3NotesActivity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    sget-object v1, Ljakhar/aseem/diva/NotesProvider;->CONTENT_URI:Landroid/net/Uri;

    const/4 v2, 0x3

    new-array v2, v2, [Ljava/lang/String;

    const/4 v6, 0x0

    const-string v13, "_id"

    aput-object v13, v2, v6

    const/4 v6, 0x1

    const-string v13, "title"

    aput-object v13, v2, v6

    const/4 v6, 0x2

    const-string v13, "note"

    aput-object v13, v2, v6

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-virtual/range {v0 .. v5}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v3

    .line 37
    .local v3, "cr":Landroid/database/Cursor;
    const/4 v1, 0x2

    new-array v4, v1, [Ljava/lang/String;

    const/4 v1, 0x0

    const-string v2, "title"

    aput-object v2, v4, v1

    const/4 v1, 0x1

    const-string v2, "note"

    aput-object v2, v4, v1

    .line 38
    .local v4, "columns":[Ljava/lang/String;
    const/4 v1, 0x2

    new-array v5, v1, [I

    fill-array-data v5, :array_0

    .line 39
    .local v5, "fields":[I
    new-instance v0, Landroid/widget/SimpleCursorAdapter;

    const v2, 0x7f040036

    const/4 v6, 0x0

    move-object v1, p0

    invoke-direct/range {v0 .. v6}, Landroid/widget/SimpleCursorAdapter;-><init>(Landroid/content/Context;ILandroid/database/Cursor;[Ljava/lang/String;[II)V

    .line 40
    .local v0, "adapter":Landroid/widget/SimpleCursorAdapter;
    invoke-virtual {v8, v0}, Landroid/widget/ListView;->setAdapter(Landroid/widget/ListAdapter;)V

    .line 41
    const/4 v1, 0x4

    invoke-virtual {v10, v1}, Landroid/widget/EditText;->setVisibility(I)V

    .line 42
    const/4 v1, 0x4

    invoke-virtual {v7, v1}, Landroid/widget/Button;->setVisibility(I)V

    .line 50
    .end local v0    # "adapter":Landroid/widget/SimpleCursorAdapter;
    .end local v3    # "cr":Landroid/database/Cursor;
    .end local v4    # "columns":[Ljava/lang/String;
    .end local v5    # "fields":[I
    .end local v8    # "lview":Landroid/widget/ListView;
    :goto_0
    return-void

    .line 47
    :cond_0
    const-string v1, "Please Enter a valid pin!"

    const/4 v2, 0x0

    invoke-static {p0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/Toast;->show()V

    goto :goto_0

    .line 38
    nop

    :array_0
    .array-data 4
        0x7f0c00b3
        0x7f0c00b4
    .end array-data
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 21
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 22
    const v0, 0x7f04001c

    invoke-virtual {p0, v0}, Ljakhar/aseem/diva/AccessControl3NotesActivity;->setContentView(I)V

    .line 23
    return-void
.end method
