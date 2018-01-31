// 
// Decompiled by Procyon v0.5.30
// 

package jakhar.aseem.diva;

import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.Cursor;
import android.database.SQLException;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.text.TextUtils;
import android.database.sqlite.SQLiteDatabase;
import android.content.UriMatcher;
import android.net.Uri;
import android.content.ContentProvider;

public class NotesProvider extends ContentProvider
{
    static final String AUTHORITY = "jakhar.aseem.diva.provider.notesprovider";
    static final Uri CONTENT_URI;
    static final String CREATE_TBL_QRY = " CREATE TABLE notes (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, note TEXT NOT NULL);";
    static final String C_ID = "_id";
    static final String C_NOTE = "note";
    static final String C_TITLE = "title";
    static final String DBNAME = "divanotes.db";
    static final int DBVERSION = 1;
    static final String DROP_TBL_QRY = "DROP TABLE IF EXISTS notes";
    static final int PATH_ID = 2;
    static final int PATH_TABLE = 1;
    static final String TABLE = "notes";
    static final UriMatcher urimatcher;
    SQLiteDatabase mDB;
    
    static {
        CONTENT_URI = Uri.parse("content://jakhar.aseem.diva.provider.notesprovider/notes");
        (urimatcher = new UriMatcher(-1)).addURI("jakhar.aseem.diva.provider.notesprovider", "notes", 1);
        NotesProvider.urimatcher.addURI("jakhar.aseem.diva.provider.notesprovider", "notes/#", 2);
    }
    
    public int delete(final Uri uri, String string, final String[] array) {
        int n = 0;
        switch (NotesProvider.urimatcher.match(uri)) {
            default: {
                throw new IllegalArgumentException("Divanotes(delete): Unsupported URI " + uri);
            }
            case 1: {
                n = this.mDB.delete("notes", string, array);
                break;
            }
            case 2: {
                final String lastPathSegment = uri.getLastPathSegment();
                final SQLiteDatabase mdb = this.mDB;
                final StringBuilder append = new StringBuilder().append("_id = ").append(lastPathSegment);
                if (!TextUtils.isEmpty((CharSequence)string)) {
                    string = " AND (" + string + ')';
                }
                else {
                    string = "";
                }
                n = mdb.delete("notes", append.append(string).toString(), array);
                break;
            }
        }
        this.getContext().getContentResolver().notifyChange(uri, (ContentObserver)null);
        return n;
    }
    
    public String getType(final Uri uri) {
        switch (NotesProvider.urimatcher.match(uri)) {
            default: {
                throw new IllegalArgumentException("Divanotes: Unsupported URI: " + uri);
            }
            case 1: {
                return "vnd.android.cursor.dir/vnd.jakhar.notes";
            }
            case 2: {
                return "vnd.android.cursor.item/vnd.jakhar.notes";
            }
        }
    }
    
    public Uri insert(Uri withAppendedId, final ContentValues contentValues) {
        final long insert = this.mDB.insert("notes", "", contentValues);
        if (insert > 0L) {
            withAppendedId = ContentUris.withAppendedId(NotesProvider.CONTENT_URI, insert);
            this.getContext().getContentResolver().notifyChange(withAppendedId, (ContentObserver)null);
            return withAppendedId;
        }
        throw new SQLException("Divanotes: Fail to add a new record into " + withAppendedId);
    }
    
    public boolean onCreate() {
        this.mDB = new DBHelper(this.getContext()).getWritableDatabase();
        return this.mDB != null;
    }
    
    public Cursor query(final Uri uri, final String[] array, final String s, final String[] array2, final String s2) {
        final SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables("notes");
        switch (NotesProvider.urimatcher.match(uri)) {
            default: {
                throw new IllegalArgumentException("Divanotes(query): Unknown URI " + uri);
            }
            case 2: {
                sqLiteQueryBuilder.appendWhere((CharSequence)("_id=" + uri.getLastPathSegment()));
            }
            case 1: {
                String s3;
                if (s2 == null || (s3 = s2) == "") {
                    s3 = "title";
                }
                final Cursor query = sqLiteQueryBuilder.query(this.mDB, array, s, array2, (String)null, (String)null, s3);
                query.setNotificationUri(this.getContext().getContentResolver(), uri);
                return query;
            }
        }
    }
    
    public int update(final Uri uri, final ContentValues contentValues, String string, final String[] array) {
        int n = 0;
        switch (NotesProvider.urimatcher.match(uri)) {
            default: {
                throw new IllegalArgumentException("Divanotes(update): Unsupported URI " + uri);
            }
            case 1: {
                n = this.mDB.update("notes", contentValues, string, array);
                break;
            }
            case 2: {
                final SQLiteDatabase mdb = this.mDB;
                final StringBuilder append = new StringBuilder().append("_id = ").append(uri.getLastPathSegment());
                if (!TextUtils.isEmpty((CharSequence)string)) {
                    string = " AND (" + string + ')';
                }
                else {
                    string = "";
                }
                n = mdb.update("notes", contentValues, append.append(string).toString(), array);
                break;
            }
        }
        this.getContext().getContentResolver().notifyChange(uri, (ContentObserver)null);
        return n;
    }
    
    private static class DBHelper extends SQLiteOpenHelper
    {
        public DBHelper(final Context context) {
            super(context, "divanotes.db", (SQLiteDatabase$CursorFactory)null, 1);
        }
        
        public void onCreate(final SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS notes");
            sqLiteDatabase.execSQL(" CREATE TABLE notes (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, note TEXT NOT NULL);");
            sqLiteDatabase.execSQL("INSERT INTO notes(title,note) VALUES ('office', '10 Meetings. 5 Calls. Lunch with CEO');");
            sqLiteDatabase.execSQL("INSERT INTO notes(title,note) VALUES ('home', 'Buy toys for baby, Order dinner');");
            sqLiteDatabase.execSQL("INSERT INTO notes(title,note) VALUES ('holiday', 'Either Goa or Amsterdam');");
            sqLiteDatabase.execSQL("INSERT INTO notes(title,note) VALUES ('Expense', 'Spent too much on home theater');");
            sqLiteDatabase.execSQL("INSERT INTO notes(title,note) VALUES ('Exercise', 'Alternate days running');");
            sqLiteDatabase.execSQL("INSERT INTO notes(title,note) VALUES ('Weekend', 'b333333333333r');");
        }
        
        public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
            this.onCreate(sqLiteDatabase);
        }
    }
}
