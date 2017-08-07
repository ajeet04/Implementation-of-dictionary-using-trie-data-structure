package com.example.android.dictionary;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;


/**
 * Created by SHREYA on 3/30/2017.
 */
public class DataProvider extends ContentProvider {


    public static final String LOG_TAG=DataProvider.class.getSimpleName();

    private static final int DATA=100;

    private static final UriMatcher sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

    public final static String CONTENT_AUTHORITY="com.example.android.dictionary";
    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+CONTENT_AUTHORITY);
    public final static String PATH_DATA="store";
    public final static Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI, PATH_DATA);


    /**
     * The MIME type of the {@link #CONTENT_URI} for a list of words.
     */
    public static final String CONTENT_LIST_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DATA;



    static
    {
        sUriMatcher.addURI(CONTENT_AUTHORITY,PATH_DATA,DATA);
    }

    private DataBaseHandler mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper=new DataBaseHandler(getContext());

        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database=mDbHelper.getReadableDatabase();
        Cursor cursor=null;

        int match=sUriMatcher.match(uri);
        switch(match)
        {
            case DATA:
                cursor=database.query(DataBaseHandler.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown uri "+uri);
        }

        return cursor;
    }


    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case DATA:
                return CONTENT_LIST_TYPE;

            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }

    }


    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match=sUriMatcher.match(uri);
        switch(match){
            case DATA:
                return insertWord(uri,contentValues);
            default:

                throw new IllegalArgumentException("Insertion is not supported for "+uri);

        }



    }

    private Uri insertWord(Uri uri,ContentValues values){
        SQLiteDatabase database=mDbHelper.getWritableDatabase();
        long id=database.insert(DataBaseHandler.TABLE_NAME,null,values);
        if(id==-1){
            Log.e(LOG_TAG,"Failed to insert row for "+uri);
            return null;
        }
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
