.class public Landroid/support/v4/math/MathUtils;
.super Ljava/lang/Object;
.source "MathUtils.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 22
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static clamp(DDD)D
    .locals 2
    .param p0, "value"    # D
    .param p2, "min"    # D
    .param p4, "max"    # D

    .prologue
    .line 58
    cmpg-double v0, p0, p2

    if-gez v0, :cond_0

    .line 63
    .end local p2    # "min":D
    :goto_0
    return-wide p2

    .line 60
    .restart local p2    # "min":D
    :cond_0
    cmpl-double v0, p0, p4

    if-lez v0, :cond_1

    move-wide p2, p4

    .line 61
    goto :goto_0

    :cond_1
    move-wide p2, p0

    .line 63
    goto :goto_0
.end method

.method public static clamp(FII)F
    .locals 1
    .param p0, "value"    # F
    .param p1, "min"    # I
    .param p2, "max"    # I

    .prologue
    .line 37
    int-to-float v0, p1

    cmpg-float v0, p0, v0

    if-gez v0, :cond_1

    .line 38
    int-to-float p0, p1

    .line 42
    .end local p0    # "value":F
    :cond_0
    :goto_0
    return p0

    .line 39
    .restart local p0    # "value":F
    :cond_1
    int-to-float v0, p2

    cmpl-float v0, p0, v0

    if-lez v0, :cond_0

    .line 40
    int-to-float p0, p2

    goto :goto_0
.end method

.method public static clamp(III)I
    .locals 0
    .param p0, "value"    # I
    .param p1, "min"    # I
    .param p2, "max"    # I

    .prologue
    .line 79
    if-ge p0, p1, :cond_0

    .line 84
    .end local p1    # "min":I
    :goto_0
    return p1

    .line 81
    .restart local p1    # "min":I
    :cond_0
    if-le p0, p2, :cond_1

    move p1, p2

    .line 82
    goto :goto_0

    :cond_1
    move p1, p0

    .line 84
    goto :goto_0
.end method
