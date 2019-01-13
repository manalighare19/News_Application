/*
 ************************ Assignment #HOMEWORK 05 *******************************************
 *********************** File Name- MainActivity.java *************************************
 ************************ Full Name- 1. Manali Ghare 2. Anup Deshpande (Group 19) ***********

 */



package com.example.manalighare.hw05;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsSourcesAsyncTask.LoadData {

    ArrayList<Source> sources = new ArrayList<>();
    ListView listView;
    public static String SOURCE_KEY= "source";
    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Activity");


        listView=(ListView)findViewById(R.id.list_sources);

        if(isConnected()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();

            builder.setTitle("Loading Sources").setView(inflater.inflate(R.layout.dialog_bar,null));

            dialog = builder.create();
            dialog.show();
            new NewsSourcesAsyncTask(MainActivity.this).execute("https://newsapi.org/v2/sources?apiKey=be6624b28c2d46b4983cb56e665785d9");

        }else{
            Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();
        }

    }

    private Boolean isConnected(){

        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        if(networkInfo== null ||!networkInfo.isConnected()){
            return false;
        }

        return true;
    }

    @Override
    public void Load_Data(ArrayList<Source> news_sources) {
        this.sources=news_sources;

        ArrayList<String> sources_names=new ArrayList<>();
        for(int i=0;i<sources.size();i++){
            sources_names.add(sources.get(i).name);
        }

        Log.d("length news sources: "," "+sources_names.size());
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,sources_names);
        listView.setAdapter(adapter);
        dialog.dismiss();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Clicked id :", ""+sources.get(position).id);
                Source source_object = new Source(sources.get(position).id, sources.get(position).name);
                Intent intent= new Intent(MainActivity.this,NewsActivity.class);
                intent.putExtra(SOURCE_KEY,source_object);
                startActivity(intent);
            }
        });

    }
}
