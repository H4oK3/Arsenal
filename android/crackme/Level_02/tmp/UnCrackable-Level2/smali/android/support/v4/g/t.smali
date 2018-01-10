.class public final Landroid/support/v4/g/t;
.super Ljava/lang/Object;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroid/support/v4/g/t$c;,
        Landroid/support/v4/g/t$b;,
        Landroid/support/v4/g/t$a;,
        Landroid/support/v4/g/t$d;
    }
.end annotation


# static fields
.field static final a:Landroid/support/v4/g/t$d;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0xe

    if-lt v0, v1, :cond_0

    new-instance v0, Landroid/support/v4/g/t$c;

    invoke-direct {v0}, Landroid/support/v4/g/t$c;-><init>()V

    sput-object v0, Landroid/support/v4/g/t;->a:Landroid/support/v4/g/t$d;

    :goto_0
    return-void

    :cond_0
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0xc

    if-lt v0, v1, :cond_1

    new-instance v0, Landroid/support/v4/g/t$b;

    invoke-direct {v0}, Landroid/support/v4/g/t$b;-><init>()V

    sput-object v0, Landroid/support/v4/g/t;->a:Landroid/support/v4/g/t$d;

    goto :goto_0

    :cond_1
    new-instance v0, Landroid/support/v4/g/t$a;

    invoke-direct {v0}, Landroid/support/v4/g/t$a;-><init>()V

    sput-object v0, Landroid/support/v4/g/t;->a:Landroid/support/v4/g/t$d;

    goto :goto_0
.end method

.method public static a(Landroid/view/MotionEvent;I)F
    .locals 1

    sget-object v0, Landroid/support/v4/g/t;->a:Landroid/support/v4/g/t$d;

    invoke-interface {v0, p0, p1}, Landroid/support/v4/g/t$d;->a(Landroid/view/MotionEvent;I)F

    move-result v0

    return v0
.end method

.method public static a(Landroid/view/MotionEvent;)I
    .locals 1

    invoke-virtual {p0}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    and-int/lit16 v0, v0, 0xff

    return v0
.end method

.method public static b(Landroid/view/MotionEvent;)I
    .locals 2

    invoke-virtual {p0}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    const v1, 0xff00

    and-int/2addr v0, v1

    shr-int/lit8 v0, v0, 0x8

    return v0
.end method
