.class Landroid/support/v4/g/ae$j$1;
.super Ljava/lang/Object;

# interfaces
.implements Landroid/support/v4/g/am$a;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Landroid/support/v4/g/ae$j;->a(Landroid/view/View;Landroid/support/v4/g/z;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic a:Landroid/support/v4/g/z;

.field final synthetic b:Landroid/support/v4/g/ae$j;


# direct methods
.method constructor <init>(Landroid/support/v4/g/ae$j;Landroid/support/v4/g/z;)V
    .locals 0

    iput-object p1, p0, Landroid/support/v4/g/ae$j$1;->b:Landroid/support/v4/g/ae$j;

    iput-object p2, p0, Landroid/support/v4/g/ae$j$1;->a:Landroid/support/v4/g/z;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public a(Landroid/view/View;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 2

    invoke-static {p2}, Landroid/support/v4/g/az;->a(Ljava/lang/Object;)Landroid/support/v4/g/az;

    move-result-object v0

    iget-object v1, p0, Landroid/support/v4/g/ae$j$1;->a:Landroid/support/v4/g/z;

    invoke-interface {v1, p1, v0}, Landroid/support/v4/g/z;->a(Landroid/view/View;Landroid/support/v4/g/az;)Landroid/support/v4/g/az;

    move-result-object v0

    invoke-static {v0}, Landroid/support/v4/g/az;->a(Landroid/support/v4/g/az;)Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method
