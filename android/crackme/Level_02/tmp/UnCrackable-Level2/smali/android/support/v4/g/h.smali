.class public final Landroid/support/v4/g/h;
.super Ljava/lang/Object;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroid/support/v4/g/h$b;,
        Landroid/support/v4/g/h$a;,
        Landroid/support/v4/g/h$c;
    }
.end annotation


# static fields
.field static final a:Landroid/support/v4/g/h$c;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0xb

    if-lt v0, v1, :cond_0

    new-instance v0, Landroid/support/v4/g/h$b;

    invoke-direct {v0}, Landroid/support/v4/g/h$b;-><init>()V

    sput-object v0, Landroid/support/v4/g/h;->a:Landroid/support/v4/g/h$c;

    :goto_0
    return-void

    :cond_0
    new-instance v0, Landroid/support/v4/g/h$a;

    invoke-direct {v0}, Landroid/support/v4/g/h$a;-><init>()V

    sput-object v0, Landroid/support/v4/g/h;->a:Landroid/support/v4/g/h$c;

    goto :goto_0
.end method

.method public static a(Landroid/view/KeyEvent;)Z
    .locals 1

    sget-object v0, Landroid/support/v4/g/h;->a:Landroid/support/v4/g/h$c;

    invoke-interface {v0, p0}, Landroid/support/v4/g/h$c;->a(Landroid/view/KeyEvent;)Z

    move-result v0

    return v0
.end method
