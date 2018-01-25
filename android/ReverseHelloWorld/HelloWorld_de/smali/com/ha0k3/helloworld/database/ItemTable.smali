.class public Lcom/ha0k3/helloworld/database/ItemTable;
.super Ljava/lang/Object;
.source "ItemTable.java"


# static fields
.field public static final COLUMN_CATEGORY:Ljava/lang/String; = "category"

.field public static final COLUMN_DESCRIPTION:Ljava/lang/String; = "description"

.field public static final COLUMN_ID:Ljava/lang/String; = "itemId"

.field public static final COLUMN_IMAGE:Ljava/lang/String; = "image"

.field public static final COLUMN_NAME:Ljava/lang/String; = "itemName"

.field public static final COLUMN_POSITION:Ljava/lang/String; = "sortPosition"

.field public static final COLUMN_PRICE:Ljava/lang/String; = "price"

.field public static final SQL_CREATE:Ljava/lang/String; = "CREATE TABLE items(itemId TEXT PRIMARY KEY,itemName TEXT,description TEXT,category TEXT,sortPosition INTEGER,price REAL,image TEXT);"

.field public static final SQL_DELETE:Ljava/lang/String; = "DROP TABLE items"

.field public static final TABLE_ITEMS:Ljava/lang/String; = "items"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 7
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
