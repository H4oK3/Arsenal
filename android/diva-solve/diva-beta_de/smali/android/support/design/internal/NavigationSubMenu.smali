.class public Landroid/support/design/internal/NavigationSubMenu;
.super Landroid/support/v7/internal/view/menu/SubMenuBuilder;
.source "NavigationSubMenu.java"


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/support/design/internal/NavigationMenu;Landroid/support/v7/internal/view/menu/MenuItemImpl;)V
    .locals 0
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "menu"    # Landroid/support/design/internal/NavigationMenu;
    .param p3, "item"    # Landroid/support/v7/internal/view/menu/MenuItemImpl;

    .prologue
    .line 34
    invoke-direct {p0, p1, p2, p3}, Landroid/support/v7/internal/view/menu/SubMenuBuilder;-><init>(Landroid/content/Context;Landroid/support/v7/internal/view/menu/MenuBuilder;Landroid/support/v7/internal/view/menu/MenuItemImpl;)V

    .line 35
    return-void
.end method

.method private notifyParent()V
    .locals 2

    .prologue
    .line 66
    invoke-virtual {p0}, Landroid/support/design/internal/NavigationSubMenu;->getParentMenu()Landroid/view/Menu;

    move-result-object v0

    check-cast v0, Landroid/support/v7/internal/view/menu/MenuBuilder;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/support/v7/internal/view/menu/MenuBuilder;->onItemsChanged(Z)V

    .line 67
    return-void
.end method


# virtual methods
.method public add(I)Landroid/view/MenuItem;
    .locals 1
    .param p1, "titleRes"    # I

    .prologue
    .line 46
    invoke-super {p0, p1}, Landroid/support/v7/internal/view/menu/SubMenuBuilder;->add(I)Landroid/view/MenuItem;

    move-result-object v0

    .line 47
    .local v0, "item":Landroid/view/MenuItem;
    invoke-direct {p0}, Landroid/support/design/internal/NavigationSubMenu;->notifyParent()V

    .line 48
    return-object v0
.end method

.method public add(IIII)Landroid/view/MenuItem;
    .locals 1
    .param p1, "groupId"    # I
    .param p2, "itemId"    # I
    .param p3, "order"    # I
    .param p4, "titleRes"    # I

    .prologue
    .line 60
    invoke-super {p0, p1, p2, p3, p4}, Landroid/support/v7/internal/view/menu/SubMenuBuilder;->add(IIII)Landroid/view/MenuItem;

    move-result-object v0

    .line 61
    .local v0, "item":Landroid/view/MenuItem;
    invoke-direct {p0}, Landroid/support/design/internal/NavigationSubMenu;->notifyParent()V

    .line 62
    return-object v0
.end method

.method public add(IIILjava/lang/CharSequence;)Landroid/view/MenuItem;
    .locals 1
    .param p1, "groupId"    # I
    .param p2, "itemId"    # I
    .param p3, "order"    # I
    .param p4, "title"    # Ljava/lang/CharSequence;

    .prologue
    .line 53
    invoke-super {p0, p1, p2, p3, p4}, Landroid/support/v7/internal/view/menu/SubMenuBuilder;->add(IIILjava/lang/CharSequence;)Landroid/view/MenuItem;

    move-result-object v0

    .line 54
    .local v0, "item":Landroid/view/MenuItem;
    invoke-direct {p0}, Landroid/support/design/internal/NavigationSubMenu;->notifyParent()V

    .line 55
    return-object v0
.end method

.method public add(Ljava/lang/CharSequence;)Landroid/view/MenuItem;
    .locals 1
    .param p1, "title"    # Ljava/lang/CharSequence;

    .prologue
    .line 39
    invoke-super {p0, p1}, Landroid/support/v7/internal/view/menu/SubMenuBuilder;->add(Ljava/lang/CharSequence;)Landroid/view/MenuItem;

    move-result-object v0

    .line 40
    .local v0, "item":Landroid/view/MenuItem;
    invoke-direct {p0}, Landroid/support/design/internal/NavigationSubMenu;->notifyParent()V

    .line 41
    return-object v0
.end method
