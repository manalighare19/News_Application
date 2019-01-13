/*
 ************************ Assignment #HOMEWORK 05 *******************************************
 *********************** File Name- News_adapter.java *************************************
 ************************ Full Name- 1. Manali Ghare 2. Anup Deshpande (Group 19) ***********

 */
package com.example.manalighare.hw05;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manalighare.hw05.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class News_adapter extends ArrayAdapter<News> {
    public News_adapter(@NonNull Context context, int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position,  @Nullable View convertView,  @NonNull ViewGroup parent) {
        News news = getItem(position);

        if(convertView== null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_row_layout, parent,false);
        }
        TextView title = (TextView)convertView.findViewById(R.id.news_title);
        TextView author = (TextView)convertView.findViewById(R.id.author);
        TextView news_date = (TextView)convertView.findViewById(R.id.published_date);
        ImageView news_image =(ImageView)convertView.findViewById(R.id.news_image);

        title.setText(news.title);
        author.setText(news.author);
        news_date.setText(news.published_date);

        Log.d("image url is : ",""+news.image_url);

        if(news.image_url.equals("")|| news.image_url.length()==0 || news.image_url.equals("null")){

            news_image.setImageResource(R.drawable.no_image_found);

        }else {

            Picasso.get().load(news.image_url).into(news_image);
        }


        return convertView;
    }
}
