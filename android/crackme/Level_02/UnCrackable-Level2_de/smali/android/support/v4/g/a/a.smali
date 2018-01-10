.class public final Landroid/support/v4/g/a/a;
.super Ljava/lang/Object;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroid/support/v4/g/a/a$c;,
        Landroid/support/v4/g/a/a$b;,
        Landroid/support/v4/g/a/a$a;,
        Landroid/support/v4/g/a/a$d;,
        Landroid/support/v4/g/a/a$e;
    }
.end annotation


# static fields
.field private static final a:Landroid/support/v4/g/a/a$e;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x13

    if-lt v0, v1, :cond_0

    new-instance v0, Landroid/support/v4/g/a/a$c;

    invoke-direct {v0}, Landroid/support/v4/g/a/a$c;-><init>()V

    sput-object v0, Landroid/support/v4/g/a/a;->a:Landroid/support/v4/g/a/a$e;

    :goto_0
    return-void

    :cond_0
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x10

    if-lt v0, v1, :cond_1

    new-instance v0, Landroid/support/v4/g/a/a$b;

    invoke-direct {v0}, Landroid/support/v4/g/a/a$b;-><init>()V

    sput-object v0, Landroid/support/v4/g/a/a;->a:Landroid/support/v4/g/a/a$e;

    goto :goto_0

    :cond_1
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0xe

    if-lt v0, v1, :cond_2

    new-instance v0, Landroid/support/v4/g/a/a$a;

    invoke-direct {v0}, Landroid/support/v4/g/a/a$a;-><init>()V

    sput-object v0, Landroid/support/v4/g/a/a;->a:Landroid/support/v4/g/a/a$e;

    goto :goto_0

    :cond_2
    new-instance v0, Landroid/support/v4/g/a/a$d;

    invoke-direct {v0}, Landroid/support/v4/g/a/a$d;-><init>()V

    sput-object v0, Landroid/support/v4/g/a/a;->a:Landroid/support/v4/g/a/a$e;

    goto :goto_0
.end method

.method public static a(Landroid/view/accessibility/AccessibilityEvent;)Landroid/support/v4/g/a/h;
    .locals 1

    new-instance v0, Landroid/support/v4/g/a/h;

    invoke-direct {v0, p0}, Landroid/support/v4/g/a/h;-><init>(Ljava/lang/Object;)V

    return-object v0
.end method
