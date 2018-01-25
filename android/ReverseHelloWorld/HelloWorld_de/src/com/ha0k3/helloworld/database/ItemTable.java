// 
// Decompiled by Procyon v0.5.30
// 

package com.ha0k3.helloworld.database;

public class ItemTable
{
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ID = "itemId";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_NAME = "itemName";
    public static final String COLUMN_POSITION = "sortPosition";
    public static final String COLUMN_PRICE = "price";
    public static final String SQL_CREATE = "CREATE TABLE items(itemId TEXT PRIMARY KEY,itemName TEXT,description TEXT,category TEXT,sortPosition INTEGER,price REAL,image TEXT);";
    public static final String SQL_DELETE = "DROP TABLE items";
    public static final String TABLE_ITEMS = "items";
}
