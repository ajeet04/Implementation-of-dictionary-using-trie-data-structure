package com.example.android.dictionary;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.HashMap;


public class newCursorAdapter extends CursorAdapter {
    public newCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView=(TextView)view.findViewById(R.id.name);
        TextView summaryTextView=(TextView)view.findViewById(R.id.summary);

        int index=cursor.getColumnIndex(DataBaseHandler._ID);
        int wordColumnIndex=cursor.getColumnIndex(DataBaseHandler.COLUMN_WORD);
        int meanColumnIndex=cursor.getColumnIndex(DataBaseHandler.COLUMN_MEANING);

        String word=cursor.getString(wordColumnIndex);
        String meaning=cursor.getString(meanColumnIndex);

        HashMap<String,String> list=new HashMap<String,String>();
        list.put("Id",String.valueOf(index));
        list.put("Word", word);
        list.put("Meaning",meaning);
        MainActivity.arr.add(list);

        nameTextView.setText(word);
        summaryTextView.setText(meaning);


    }
}
