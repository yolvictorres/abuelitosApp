package co.edu.konradlorenz.a506132023.abuelitosservices.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

/**
 * Created by astro on 29/05/2017.
 */

public class DataProvider extends ContentProvider {
    public static final String TAG ="DataProvider";

    public static final Uri CONTENT_URI_USERS = Uri.parse("content://co.edu.konradlorenz.a506132023.abuelitosservices.provider/users");

    public static final String COL_ID = "_id";

    public enum MessageType {
        INCOMING, OUTGOING
    }

    public enum GCMType {
        OTHERS, CHAT, RELATIONSHIP,SERVICE
    }

    public enum ServiceType {
        OTHERS,BILL, WAITER
    }

    public enum RelationshipType {
        OTHERS, FRIENDLY,NAME,NUMBER,CITY,STATE,COUNTRY,LOCATION,FACEBOOK,INSTAGRAM,PHOTOS
    }
    //DATA FROM BACKEND
    public static final String TABLE_USERS                              = "usuarios";
    public static final String TABLE_USERS_ID                           = "cedula";
    public static final String TABLE_USERS_NAME                         = "nombre";
    public static final String TABLE_USERS_FOTO                     = "foto";
    public static final String TABLE_USERS_CONTRASENA               ="contraseña";
    public static final String TABLE_USERS_TYPEUSER                 ="tipo usuario";


    //+ TABLE_PROFILE_COL_COUNT + " integer default 0);");

    private DbHelper dbHelper;

    private static final int USERS_ALLROWS = 1;
    private static final int USERS_SINGLE_ROW = 2;



    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("co.edu.konradlorenz.a506132023.abuelitosservices.provider", "users", USERS_ALLROWS);
        uriMatcher.addURI("co.edu.konradlorenz.a506132023.abuelitosservices.provider", "users/#", USERS_SINGLE_ROW);
        }


    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        switch(uriMatcher.match(uri)) {
            case USERS_ALLROWS:
                qb.setTables(TABLE_USERS);
                break;

            case USERS_SINGLE_ROW:
                qb.setTables(TABLE_USERS);
                qb.appendWhere(TABLE_USERS_ID+" = " + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.i(TAG,"Llega aca");
        long id;
        switch(uriMatcher.match(uri)) {
            case USERS_ALLROWS:
                Log.i(TAG,"Llega aca2");
                id = db.insertOrThrow(TABLE_USERS, null, values);
                break;

            default:
                Log.i(TAG,"Llega aca3");
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        Uri insertUri = ContentUris.withAppendedId(uri, id);
        getContext().getContentResolver().notifyChange(insertUri, null);
        return insertUri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int count;
        switch(uriMatcher.match(uri)) {
            case USERS_ALLROWS:
                count = db.update(TABLE_USERS, values, selection, selectionArgs);
                break;

            case USERS_SINGLE_ROW:
                count = db.update(TABLE_USERS, values, TABLE_USERS_ID+" = ?", new String[]{uri.getLastPathSegment()});
                break;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int count;
        switch(uriMatcher.match(uri)) {
            case USERS_ALLROWS:
                count = db.delete(TABLE_USERS, selection, selectionArgs);
                break;

            case USERS_SINGLE_ROW:
                count = db.delete(TABLE_USERS, TABLE_USERS_ID+" = ?", new String[]{uri.getLastPathSegment()});
                break;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }


    private static class DbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "plupin.db";
        private static final int DATABASE_VERSION = 1;
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table "+TABLE_USERS+" ("
                    + TABLE_USERS_ID                                +" text primary key, "
                    + TABLE_USERS_NAME        	    +" text, "
                    + TABLE_USERS_FOTO            +"text "
                    +TABLE_USERS_CONTRASENA         +"text"
                    +TABLE_USERS_TYPEUSER           +"text");


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
