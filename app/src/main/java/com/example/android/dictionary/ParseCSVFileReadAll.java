package com.example.android.dictionary;

/**
 * Created by Ajeet yadav on 4/18/2017.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class ParseCSVFileReadAll
{
    public static void readFromFile(Context context, String file) {
        try {
            DataBaseHandler db=new DataBaseHandler(context);
            String next[] = {};
            Word w;
            ArrayList<String> list = new ArrayList<String>();

                CSVReader reader = new CSVReader(new InputStreamReader(context.getAssets().open(file)));//Specify asset file name
//in open();
                for(;;) {
                    next = reader.readNext();
                    if(next != null) {
                        w=new Word(next[0].trim(),next[2].trim());
                        db.addWord(w);

                    } else {
                        break;
                    }
                }


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
