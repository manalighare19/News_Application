/*
 ************************ Assignment #HOMEWORK 05 *******************************************
 *********************** File Name- GetNewsAsyncTask.java *************************************
 ************************ Full Name- 1. Manali Ghare 2. Anup Deshpande (Group 19) ***********

 */


package com.example.manalighare.hw05;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class GetNewsAsyncTask extends AsyncTask<String, Void, ArrayList<News>> {

    String source_id;
    Get_News_Interface context_object;

    public GetNewsAsyncTask(String source_id, Get_News_Interface context_object) {
        this.source_id = source_id;
        this.context_object = context_object;
    }

    @Override
    protected ArrayList<News> doInBackground(String... strings) {
        HttpURLConnection connection = null;
        ArrayList<News> result = new ArrayList<>();
        try {
            String strUrl = strings[0] + "?" +
                    "sources=" + URLEncoder.encode(this.source_id, "UTF-8") + "&" +
                    "apiKey=" + URLEncoder.encode("be6624b28c2d46b4983cb56e665785d9", "UTF-8") ;
            URL url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {


                String json = IOUtils.toString(connection.getInputStream(), "UTF8");

                JSONObject root = new JSONObject(json);
                JSONArray articles = root.getJSONArray("articles");


                for (int i=0; i<articles.length(); i++){
                    JSONObject sourceJson = articles.getJSONObject(i);
                    News news=new News();
                    news.title=sourceJson.getString("title");
                    news.author=sourceJson.getString("author");
                    news.image_url=sourceJson.getString("urlToImage");
                    news.published_date=sourceJson.getString("publishedAt").substring(0,10);
                    news.url=sourceJson.getString("url");
                    result.add(news);
                }



            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<News> newsArrayList) {

        context_object.Get_Data(newsArrayList);
    }

    public static interface Get_News_Interface {
        public void Get_Data(ArrayList<News>newsArrayList);
}
}
