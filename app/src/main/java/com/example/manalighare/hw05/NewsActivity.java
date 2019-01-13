/*
 ************************ Assignment #HOMEWORK 05 *******************************************
 *********************** File Name- NewsActivity.java *************************************
 ************************ Full Name- 1. Manali Ghare 2. Anup Deshpande (Group 19) ***********

 */


package com.example.manalighare.hw05;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.manalighare.hw05.MainActivity.SOURCE_KEY;

public class NewsActivity extends AppCompatActivity implements GetNewsAsyncTask.Get_News_Interface {

    private ListView listView;
    private WebView webView;
    private ArrayList<News> newsArrayList = new ArrayList<>();
    public static String SELECTED_NEWS="News";
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        listView = (ListView)findViewById(R.id.list_news);

        if (getIntent()!=null && getIntent().getExtras()!=null){
            Source source = (Source) getIntent().getExtras().getSerializable(SOURCE_KEY);
            Log.d("source id",""+source.id);
            setTitle(source.name);


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();

            builder.setTitle("Loading Stories").setView(inflater.inflate(R.layout.dialog_bar,null));

            dialog = builder.create();
            dialog.show();

            new GetNewsAsyncTask(source.id, NewsActivity.this).execute("https://newsapi.org/v2/top-headlines");


        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(NewsActivity.this,WebActivity.class);
                intent.putExtra(SELECTED_NEWS,newsArrayList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void Get_Data(ArrayList<News> newsArrayList) {
        this.newsArrayList=newsArrayList;

        News_adapter news_adapter = new News_adapter(this,R.layout.news_row_layout,this.newsArrayList);

        listView.setAdapter(news_adapter);
        dialog.dismiss();

        Log.d("size of array is ",""+this.newsArrayList.size());
    }
}
