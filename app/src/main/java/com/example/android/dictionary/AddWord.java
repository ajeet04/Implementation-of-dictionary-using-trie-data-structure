package com.example.android.dictionary;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddWord extends AppCompatActivity{

    EditText word,meaning;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        word=(EditText)findViewById(R.id.editText_word);
        meaning=(EditText)findViewById(R.id.editText_meaning);

        save=(Button)findViewById(R.id.save_word);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=word.getText().toString().trim();
                String m=meaning.getText().toString().trim();

               /* DataBaseHandler db=new DataBaseHandler(AddWord.this);
                Word word=new Word(n,m);
                db.addWord(word);*/

                ContentValues values=new ContentValues();
                values.put(DataBaseHandler.COLUMN_WORD,n);
                values.put(DataBaseHandler.COLUMN_MEANING, m);


                Uri newUri=getContentResolver().insert(DataProvider.CONTENT_URI,values);
                if (newUri == null)
                    Toast.makeText(AddWord.this, "Error saving word", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddWord.this, "Word saved ", Toast.LENGTH_SHORT).show();



              //  Toast.makeText(AddWord.this, "Word is added", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                //db.close();
                finish();

            }
        });


    }


}
