package com.example.android.dictionary;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by AJEET YADAV on 4/1/2017.
 */
public class NodeClass {

        int ALPHABET_SIZE=60;
    int CASE='a';
    NodeClass parent=null;
    NodeClass children[]=new NodeClass[ALPHABET_SIZE];
    int occurences=0;
    char my_index;

    NodeClass(int my_index){
        this.CASE = (my_index>90)? 'a':'A';

        this.my_index=(char)(my_index+'a');
    }

    NodeClass(){

        this.my_index=' '; //root Node
        this.CASE=0;
    }

    void InsertNode(NodeClass trieTree, String word){
        NodeClass currentNode=trieTree;
        int i=0;

        while(i<word.length()){
            int c=word.charAt(i);
            c-=(c>90)? 'a':'A';
            c=(c<0)? 0:c;
//            Log.d("insert",word+" "+c);
            if(currentNode.children[c]==null){
                currentNode.children[c]=new NodeClass(c);
                currentNode.children[c].parent=currentNode;

            }
            currentNode=currentNode.children[c];
            i++;
        }
        ++currentNode.occurences;
    }

    NodeClass Search(NodeClass trieTree, String word){
        int i;
        String str="";
        for(i=0;i<word.length();i++){
            int c=word.charAt(i);
            c-=(c>90)? 'a':'A';
            c=(c<0)? 0:c;
            str=str+c;
//            for(int j=0;j<26;j++) {
//                if(trieTree.children[j]!=null)
//                    Log.d("child", trieTree.children[j].my_index+"");
//                else
//                    Log.d("child", ""+(j+'a'));
//
//            }

            if(trieTree.children[c]!=null){
                trieTree=trieTree.children[c];

            }
            else return null;

        }
        return trieTree;
    }
    ArrayList<String> Autocomplete(NodeClass trieTree, String word){
        ArrayList<String> res = ListAllWords(Search(trieTree, word));
        int i;
        if(word.length()>1) {
            for (i = 0; i < res.size(); i++) {
                res.set(i, word + res.get(i).substring(1));
            }
        }
        return res;
    }
    ArrayList<String> ListAllWords(NodeClass Node){

        ArrayList<String> arr = new ArrayList<String>();

        if(Node==null) return new ArrayList<String>();
        int i;
        boolean flag=true;
        for(i=0;i<26;i++){

            if(Node.children[i]!=null) {
                arr.addAll(ListAllWords(Node.children[i]));
                flag=false;
            }

        }
        if(flag)
            arr.add("");
        if(Node.my_index!=' ')
        {
            for (i = 0; i < arr.size(); i++) {
                arr.set(i, Node.my_index + arr.get(i));
            }
        }
        return arr;
    }

}


