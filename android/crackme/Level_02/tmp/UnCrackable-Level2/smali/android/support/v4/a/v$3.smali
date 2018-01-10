.class final Landroid/support/v4/a/v$3;
.super Ljava/lang/Object;

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Landroid/support/v4/a/v;->a(Landroid/view/ViewGroup;Landroid/view/View;Landroid/support/v4/f/a;Landroid/support/v4/a/v$a;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation


# instance fields
.field final synthetic a:Landroid/support/v4/a/k;

.field final synthetic b:Landroid/support/v4/a/k;

.field final synthetic c:Z

.field final synthetic d:Landroid/support/v4/f/a;

.field final synthetic e:Landroid/view/View;

.field final synthetic f:Landroid/graphics/Rect;


# direct methods
.method constructor <init>(Landroid/support/v4/a/k;Landroid/support/v4/a/k;ZLandroid/support/v4/f/a;Landroid/view/View;Landroid/graphics/Rect;)V
    .locals 0

    iput-object p1, p0, Landroid/support/v4/a/v$3;->a:Landroid/support/v4/a/k;

    iput-object p2, p0, Landroid/support/v4/a/v$3;->b:Landroid/support/v4/a/k;

    iput-boolean p3, p0, Landroid/support/v4/a/v$3;->c:Z

    iput-object p4, p0, Landroid/support/v4/a/v$3;->d:Landroid/support/v4/f/a;

    iput-object p5, p0, Landroid/support/v4/a/v$3;->e:Landroid/view/View;

    iput-object p6, p0, Landroid/support/v4/a/v$3;->f:Landroid/graphics/Rect;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 5

    iget-object v0, p0, Landroid/support/v4/a/v$3;->a:Landroid/support/v4/a/k;

    iget-object v1, p0, Landroid/support/v4/a/v$3;->b:Landroid/support/v4/a/k;

    iget-boolean v2, p0, Landroid/support/v4/a/v$3;->c:Z

    iget-object v3, p0, Landroid/support/v4/a/v$3;->d:Landroid/support/v4/f/a;

    const/4 v4, 0x0

    invoke-static {v0, v1, v2, v3, v4}, Landroid/support/v4/a/v;->a(Landroid/support/v4/a/k;Landroid/support/v4/a/k;ZLandroid/support/v4/f/a;Z)V

    iget-object v0, p0, Landroid/support/v4/a/v$3;->e:Landroid/view/View;

    if-eqz v0, :cond_0

    iget-object v0, p0, Landroid/support/v4/a/v$3;->e:Landroid/view/View;

    iget-object v1, p0, Landroid/support/v4/a/v$3;->f:Landroid/graphics/Rect;

    invoke-static {v0, v1}, Landroid/support/v4/a/w;->a(Landroid/view/View;Landroid/graphics/Rect;)V

    :cond_0
    return-void
.end method
