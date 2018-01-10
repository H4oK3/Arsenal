.class Landroid/support/v4/a/l$a;
.super Landroid/support/v4/a/o;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroid/support/v4/a/l;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "a"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroid/support/v4/a/o",
        "<",
        "Landroid/support/v4/a/l;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic a:Landroid/support/v4/a/l;


# direct methods
.method public constructor <init>(Landroid/support/v4/a/l;)V
    .locals 0

    iput-object p1, p0, Landroid/support/v4/a/l$a;->a:Landroid/support/v4/a/l;

    invoke-direct {p0, p1}, Landroid/support/v4/a/o;-><init>(Landroid/support/v4/a/l;)V

    return-void
.end method


# virtual methods
.method public a(I)Landroid/view/View;
    .locals 1

    iget-object v0, p0, Landroid/support/v4/a/l$a;->a:Landroid/support/v4/a/l;

    invoke-virtual {v0, p1}, Landroid/support/v4/a/l;->findViewById(I)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method public a(Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    .locals 1
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "NewApi"
        }
    .end annotation

    iget-object v0, p0, Landroid/support/v4/a/l$a;->a:Landroid/support/v4/a/l;

    invoke-virtual {v0, p1, p2, p3, p4}, Landroid/support/v4/a/l;->dump(Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V

    return-void
.end method

.method public a()Z
    .locals 1

    iget-object v0, p0, Landroid/support/v4/a/l$a;->a:Landroid/support/v4/a/l;

    invoke-virtual {v0}, Landroid/support/v4/a/l;->getWindow()Landroid/view/Window;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/view/Window;->peekDecorView()Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public a(Landroid/support/v4/a/k;)Z
    .locals 1

    iget-object v0, p0, Landroid/support/v4/a/l$a;->a:Landroid/support/v4/a/l;

    invoke-virtual {v0}, Landroid/support/v4/a/l;->isFinishing()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public b()Landroid/view/LayoutInflater;
    .locals 2

    iget-object v0, p0, Landroid/support/v4/a/l$a;->a:Landroid/support/v4/a/l;

    invoke-virtual {v0}, Landroid/support/v4/a/l;->getLayoutInflater()Landroid/view/LayoutInflater;

    move-result-object v0

    iget-object v1, p0, Landroid/support/v4/a/l$a;->a:Landroid/support/v4/a/l;

    invoke-virtual {v0, v1}, Landroid/view/LayoutInflater;->cloneInContext(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    return-object v0
.end method

.method public b(Landroid/support/v4/a/k;)V
    .locals 1

    iget-object v0, p0, Landroid/support/v4/a/l$a;->a:Landroid/support/v4/a/l;

    invoke-virtual {v0, p1}, Landroid/support/v4/a/l;->a(Landroid/support/v4/a/k;)V

    return-void
.end method

.method public c()V
    .locals 1

    iget-object v0, p0, Landroid/support/v4/a/l$a;->a:Landroid/support/v4/a/l;

    invoke-virtual {v0}, Landroid/support/v4/a/l;->c()V

    return-void
.end method

.method public d()Z
    .locals 1

    iget-object v0, p0, Landroid/support/v4/a/l$a;->a:Landroid/support/v4/a/l;

    invoke-virtual {v0}, Landroid/support/v4/a/l;->getWindow()Landroid/view/Window;

    move-result-object v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public e()I
    .locals 1

    iget-object v0, p0, Landroid/support/v4/a/l$a;->a:Landroid/support/v4/a/l;

    invoke-virtual {v0}, Landroid/support/v4/a/l;->getWindow()Landroid/view/Window;

    move-result-object v0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    :goto_0
    return v0

    :cond_0
    invoke-virtual {v0}, Landroid/view/Window;->getAttributes()Landroid/view/WindowManager$LayoutParams;

    move-result-object v0

    iget v0, v0, Landroid/view/WindowManager$LayoutParams;->windowAnimations:I

    goto :goto_0
.end method
