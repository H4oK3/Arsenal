.class Landroid/support/v4/g/ae$j;
.super Landroid/support/v4/g/ae$i;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroid/support/v4/g/ae;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "j"
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroid/support/v4/g/ae$i;-><init>()V

    return-void
.end method


# virtual methods
.method public a(Landroid/view/View;Landroid/support/v4/g/az;)Landroid/support/v4/g/az;
    .locals 1

    invoke-static {p2}, Landroid/support/v4/g/az;->a(Landroid/support/v4/g/az;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {p1, v0}, Landroid/support/v4/g/am;->a(Landroid/view/View;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Landroid/support/v4/g/az;->a(Ljava/lang/Object;)Landroid/support/v4/g/az;

    move-result-object v0

    return-object v0
.end method

.method public a(Landroid/view/View;Landroid/content/res/ColorStateList;)V
    .locals 0

    invoke-static {p1, p2}, Landroid/support/v4/g/am;->a(Landroid/view/View;Landroid/content/res/ColorStateList;)V

    return-void
.end method

.method public a(Landroid/view/View;Landroid/graphics/PorterDuff$Mode;)V
    .locals 0

    invoke-static {p1, p2}, Landroid/support/v4/g/am;->a(Landroid/view/View;Landroid/graphics/PorterDuff$Mode;)V

    return-void
.end method

.method public a(Landroid/view/View;Landroid/support/v4/g/z;)V
    .locals 1

    if-nez p2, :cond_0

    const/4 v0, 0x0

    invoke-static {p1, v0}, Landroid/support/v4/g/am;->a(Landroid/view/View;Landroid/support/v4/g/am$a;)V

    :goto_0
    return-void

    :cond_0
    new-instance v0, Landroid/support/v4/g/ae$j$1;

    invoke-direct {v0, p0, p2}, Landroid/support/v4/g/ae$j$1;-><init>(Landroid/support/v4/g/ae$j;Landroid/support/v4/g/z;)V

    invoke-static {p1, v0}, Landroid/support/v4/g/am;->a(Landroid/view/View;Landroid/support/v4/g/am$a;)V

    goto :goto_0
.end method

.method public c(Landroid/view/View;F)V
    .locals 0

    invoke-static {p1, p2}, Landroid/support/v4/g/am;->a(Landroid/view/View;F)V

    return-void
.end method

.method public j(Landroid/view/View;)Ljava/lang/String;
    .locals 1

    invoke-static {p1}, Landroid/support/v4/g/am;->a(Landroid/view/View;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public l(Landroid/view/View;)V
    .locals 0

    invoke-static {p1}, Landroid/support/v4/g/am;->b(Landroid/view/View;)V

    return-void
.end method

.method public n(Landroid/view/View;)Landroid/content/res/ColorStateList;
    .locals 1

    invoke-static {p1}, Landroid/support/v4/g/am;->c(Landroid/view/View;)Landroid/content/res/ColorStateList;

    move-result-object v0

    return-object v0
.end method

.method public o(Landroid/view/View;)Landroid/graphics/PorterDuff$Mode;
    .locals 1

    invoke-static {p1}, Landroid/support/v4/g/am;->d(Landroid/view/View;)Landroid/graphics/PorterDuff$Mode;

    move-result-object v0

    return-object v0
.end method

.method public p(Landroid/view/View;)V
    .locals 0

    invoke-static {p1}, Landroid/support/v4/g/am;->e(Landroid/view/View;)V

    return-void
.end method
