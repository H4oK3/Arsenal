.class public Lsg/vantagepoint/uncrackable2/CodeCheck;
.super Ljava/lang/Object;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private native bar([B)Z
.end method


# virtual methods
.method public a(Ljava/lang/String;)Z
    .locals 1

    invoke-virtual {p1}, Ljava/lang/String;->getBytes()[B

    move-result-object v0

    invoke-direct {p0, v0}, Lsg/vantagepoint/uncrackable2/CodeCheck;->bar([B)Z

    move-result v0

    return v0
.end method
