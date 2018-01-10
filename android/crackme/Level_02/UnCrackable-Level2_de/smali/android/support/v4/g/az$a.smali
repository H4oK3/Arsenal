.class Landroid/support/v4/g/az$a;
.super Landroid/support/v4/g/az$c;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroid/support/v4/g/az;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "a"
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroid/support/v4/g/az$c;-><init>()V

    return-void
.end method


# virtual methods
.method public a(Ljava/lang/Object;)I
    .locals 1

    invoke-static {p1}, Landroid/support/v4/g/ba;->a(Ljava/lang/Object;)I

    move-result v0

    return v0
.end method

.method public a(Ljava/lang/Object;IIII)Landroid/support/v4/g/az;
    .locals 2

    new-instance v0, Landroid/support/v4/g/az;

    invoke-static {p1, p2, p3, p4, p5}, Landroid/support/v4/g/ba;->a(Ljava/lang/Object;IIII)Ljava/lang/Object;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/support/v4/g/az;-><init>(Ljava/lang/Object;)V

    return-object v0
.end method

.method public b(Ljava/lang/Object;)I
    .locals 1

    invoke-static {p1}, Landroid/support/v4/g/ba;->b(Ljava/lang/Object;)I

    move-result v0

    return v0
.end method

.method public c(Ljava/lang/Object;)I
    .locals 1

    invoke-static {p1}, Landroid/support/v4/g/ba;->c(Ljava/lang/Object;)I

    move-result v0

    return v0
.end method

.method public d(Ljava/lang/Object;)I
    .locals 1

    invoke-static {p1}, Landroid/support/v4/g/ba;->d(Ljava/lang/Object;)I

    move-result v0

    return v0
.end method
