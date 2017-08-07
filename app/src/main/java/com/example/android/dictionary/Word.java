package com.example.android.dictionary;

/**
 * Created by SHREYA on 3/28/2017.
 */
public class Word {

    int _id;
    String word;
    String mean;
    String sanitized;
    public Word(){}
    public Word(String word,String mean){
        this.word=word;
        this.mean=mean;
        Sanitize();
    }
    public Word(int id,String word,String mean){
        this._id=id;
        this.word=word;
        this.mean=mean;
        Sanitize();
    }

    public int getId(){
        return _id;
    }

    public String getWord(){
        return word;

    }

    public String getWordLower(){
        return word.toLowerCase();

    }
    public static String Sanitize(String word){
        int i=0;
        String sani="";
        char c;
        word=word.toLowerCase();
        for(i=0;i<word.length();i++)
        {c=word.charAt(i);
         if(c>='a'&&c<='z')
             sani+=c;
        }
        return sani;

    }
    public void Sanitize()
    {
        this.sanitized=Sanitize(this.word);
    }

    public String getSanitized()
    {
        if(this.sanitized==null)
        this.sanitized=Sanitize(this.word);
        return this.sanitized;
    }

    public String getMean(){
        return mean;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setWord(String word) {
        this.word = word;
    }



    public void setMean(String mean) {
        this.mean = mean;
    }
}
