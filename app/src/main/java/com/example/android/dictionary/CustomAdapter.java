package com.example.android.dictionary;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static android.support.v7.widget.RecyclerView.*;
import static com.example.android.dictionary.R.layout.list_item;

/**
 * Created by Ajeet yadav on 4/3/2017.
 */

class  CustomAdapter extends ArrayAdapter<HashMap<String, String>>
{


    public CustomAdapter(Context context, int number,
                         ArrayList<HashMap<String, String>> Strings) {

        //let android do the initializing :)
        super(context, number, Strings);

    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return 0;
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    private class ViewHolder
    {

        TextView name,sumarry;

    }
    ViewHolder viewHolder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.name=(TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder=(ViewHolder) convertView.getTag();
        viewHolder.name.setText(MainActivity.arr.get(position).get("word").toString());
        return convertView;
    }


    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public void notifyDataSetChanged() {
    }
}
