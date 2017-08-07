package com.example.android.dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Created by SHREYA on 3/28/2017.
 */
public class DataBaseHandler extends SQLiteOpenHelper implements BaseColumns{

    private static final String DATABASE_NAME="dictionary.db";
    private static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="store";

    public static final String _ID=BaseColumns._ID;
    public static final String COLUMN_WORD="word";
    public static final String COLUMN_MEANING="meaning";


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_DICT_TABLE="CREATE TABLE "+ TABLE_NAME+"("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_WORD+" TEXT,"+COLUMN_MEANING+" TEXT);";
        db.execSQL(SQL_CREATE_DICT_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public void Purge() {
        SQLiteDatabase db=this.getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }


    public void addWord(Word word){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(COLUMN_WORD,word.getWord());
        values.put(COLUMN_MEANING,word.getMean());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Word> getAllWords(){
        List<Word> wordList=new ArrayList<Word>();

             String selectQuery=   "SELECT *FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Word word=new Word();
                word.set_id(Integer.parseInt(cursor.getString(0)));
                word.setWord(cursor.getString(1));
                word.setMean(cursor.getString(2));
                wordList.add(word);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return wordList;

    }

}
