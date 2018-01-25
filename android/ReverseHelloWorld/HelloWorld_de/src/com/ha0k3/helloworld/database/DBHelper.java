// 
// Decompiled by Procyon v0.5.30
// 

package com.ha0k3.helloworld.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DB_FILE_NAME = "database.db";
    public static final int DB_VERSION = 1;
    
    public DBHelper(final Context context) {
        super(context, "database.db", (SQLiteDatabase$CursorFactory)null, 1);
    }
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE items(itemId TEXT PRIMARY KEY,itemName TEXT,description TEXT,category TEXT,sortPosition INTEGER,price REAL,image TEXT);");
    }
    
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        sqLiteDatabase.execSQL("DROP TABLE items");
        this.onCreate(sqLiteDatabase);
    }
}
