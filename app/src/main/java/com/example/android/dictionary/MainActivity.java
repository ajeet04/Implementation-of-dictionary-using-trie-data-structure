package com.example.android.dictionary;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int WORD_LOADER=0;

    EditText search;
    ListView lv;

    newCursorAdapter mCursorAdapter;

    List<Word> wordList;
    public static ArrayList<HashMap<String,String>> arr=new ArrayList<HashMap<String,String>>();
    public static ArrayList<HashMap<String,String>> dyn_map =new ArrayList<HashMap<String,String>>();
    public static HashMap <String,Word> wordmap = new HashMap<String, Word>();
    SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ParseCSVFileReadAll.readFromFile(MainActivity.this,"dictionary.csv");
                Intent intent=new Intent(MainActivity.this,AddWord.class);
                startActivity(intent);
            }
        });

        search=(EditText)findViewById(R.id.search);
        lv=(ListView)findViewById(R.id.list);

       /* mCursorAdapter=new newCursorAdapter(this,null);
        lv.setAdapter(mCursorAdapter);*/
       DataBaseHandler db=new DataBaseHandler(MainActivity.this);
        wordList=db.getAllWords();
        final NodeClass autocomplete = new NodeClass();
        int i=0;


        for(i=0;i<wordList.size();i++)
        {
            Word word=wordList.get(i);
            HashMap<String,String> list=new HashMap<String,String>();
            list.put("Id",String.valueOf(word.getId()));
            list.put("Word", word.getWord());
            list.put("Meaning",word.getMean());

            autocomplete.InsertNode(autocomplete, word.getSanitized());
            wordmap.put(word.getSanitized(),word);

            dyn_map.add(list);

        }


        simpleAdapter=new SimpleAdapter(getApplicationContext(),dyn_map,R.layout.list_item,new String[]{"Word","Meaning"},new int[]{R.id.name,R.id.summary});
        lv.setAdapter(simpleAdapter);




        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //UpdateAdapter(autocomplete,charSequence.toString());
                //MainActivity.this.simpleAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //UpdateAdapter(autocomplete,charSequence.toString());
                //MainActivity.this.simpleAdapter.getFilter().filter(charSequence);
                dyn_map.clear();
                String word = charSequence.toString();
                Word tmp;
                if(word.equals("Purge97")) {
                    Log.d("delete","delete all");
                    DataBaseHandler db=new DataBaseHandler(MainActivity.this);
                    db.Purge();
                }
                ArrayList<String> result=autocomplete.Autocomplete(autocomplete,Word.Sanitize(word));
//                for(int p=0;p<result.size();p++){
//                    Log.d("result",result.get(p));
//                }
//                for(int p=0;p<26;p++){
//                    if(autocomplete.children[p]!=null)
//                    Log.d("trie",autocomplete.children[p].occurences+" "+autocomplete.my_index);
//                }
                for(int p=0;p<result.size();p++){
                    tmp = wordmap.get(result.get(p));
//                    Log.d("res",result.get(p));
                    HashMap<String,String> list=new HashMap<String,String>();
                    list.put("Id",String.valueOf(tmp.getId()));
                    list.put("Word", tmp.getWord());
                    list.put("Meaning",tmp.getMean());
                    dyn_map.add(list);
                }
//                Log.d("final",dyn_map.toString());
                lv.setAdapter(simpleAdapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


       // getLoaderManager().initLoader(WORD_LOADER,null,this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void UpdateAdapter(NodeClass autocomplete,String text){



    }

  /*  @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String [] projection ={
                DataBaseHandler._ID,
                DataBaseHandler.COLUMN_WORD,
                DataBaseHandler.COLUMN_MEANING
        };
        return new CursorLoader(this,DataProvider.CONTENT_URI,projection,null,null,null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);

    }*/
}
