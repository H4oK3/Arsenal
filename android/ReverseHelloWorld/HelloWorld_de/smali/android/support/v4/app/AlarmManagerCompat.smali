.class public final Landroid/support/v4/app/AlarmManagerCompat;
.super Ljava/lang/Object;
.source "AlarmManagerCompat.java"


# direct methods
.method private constructor <init>()V
    .locals 0

    .prologue
    .line 229
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 230
    return-void
.end method

.method public static setAlarmClock(Landroid/app/AlarmManager;JLandroid/app/PendingIntent;Landroid/app/PendingIntent;)V
    .locals 3
    .param p0, "alarmManager"    # Landroid/app/AlarmManager;
    .param p1, "triggerTime"    # J
    .param p3, "showIntent"    # Landroid/app/PendingIntent;
    .param p4, "operation"    # Landroid/app/PendingIntent;

    .prologue
    .line 57
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x15

    if-lt v0, v1, :cond_0

    .line 58
    invoke-static {p0, p1, p2, p3, p4}, Landroid/support/v4/app/AlarmManagerCompatApi21;->setAlarmClock(Landroid/app/AlarmManager;JLandroid/app/PendingIntent;Landroid/app/PendingIntent;)V

    .line 64
    :goto_0
    return-void

    .line 61
    :cond_0
    const/4 v0, 0x0

    invoke-static {p0, v0, p1, p2, p4}, Landroid/support/v4/app/AlarmManagerCompat;->setExact(Landroid/app/AlarmManager;IJLandroid/app/PendingIntent;)V

    goto :goto_0
.end method

.method public static setAndAllowWhileIdle(Landroid/app/AlarmManager;IJLandroid/app/PendingIntent;)V
    .locals 2
    .param p0, "alarmManager"    # Landroid/app/AlarmManager;
    .param p1, "type"    # I
    .param p2, "triggerAtMillis"    # J
    .param p4, "operation"    # Landroid/app/PendingIntent;

    .prologue
    .line 115
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x17

    if-lt v0, v1, :cond_0

    .line 116
    invoke-static {p0, p1, p2, p3, p4}, Landroid/support/v4/app/AlarmManagerCompatApi23;->setAndAllowWhileIdle(Landroid/app/AlarmManager;IJLandroid/app/PendingIntent;)V

    .line 121
    :goto_0
    return-void

    .line 119
    :cond_0
    invoke-virtual {p0, p1, p2, p3, p4}, Landroid/app/AlarmManager;->set(IJLandroid/app/PendingIntent;)V

    goto :goto_0
.end method

.method public static setExact(Landroid/app/AlarmManager;IJLandroid/app/PendingIntent;)V
    .locals 2
    .param p0, "alarmManager"    # Landroid/app/AlarmManager;
    .param p1, "type"    # I
    .param p2, "triggerAtMillis"    # J
    .param p4, "operation"    # Landroid/app/PendingIntent;

    .prologue
    .line 161
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x13

    if-lt v0, v1, :cond_0

    .line 162
    invoke-static {p0, p1, p2, p3, p4}, Landroid/support/v4/app/AlarmManagerCompatKitKat;->setExact(Landroid/app/AlarmManager;IJLandroid/app/PendingIntent;)V

    .line 166
    :goto_0
    return-void

    .line 164
    :cond_0
    invoke-virtual {p0, p1, p2, p3, p4}, Landroid/app/AlarmManager;->set(IJLandroid/app/PendingIntent;)V

    goto :goto_0
.end method

.method public static setExactAndAllowWhileIdle(Landroid/app/AlarmManager;IJLandroid/app/PendingIntent;)V
    .locals 2
    .param p0, "alarmManager"    # Landroid/app/AlarmManager;
    .param p1, "type"    # I
    .param p2, "triggerAtMillis"    # J
    .param p4, "operation"    # Landroid/app/PendingIntent;

    .prologue
    .line 221
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x17

    if-lt v0, v1, :cond_0

    .line 222
    invoke-static {p0, p1, p2, p3, p4}, Landroid/support/v4/app/AlarmManagerCompatApi23;->setExactAndAllowWhileIdle(Landroid/app/AlarmManager;IJLandroid/app/PendingIntent;)V

    .line 227
    :goto_0
    return-void

    .line 225
    :cond_0
    invoke-static {p0, p1, p2, p3, p4}, Landroid/support/v4/app/AlarmManagerCompat;->setExact(Landroid/app/AlarmManager;IJLandroid/app/PendingIntent;)V

    goto :goto_0
.end method
