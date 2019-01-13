/*
 ************************ Assignment #HOMEWORK 05 *******************************************
 *********************** File Name- NewsSourcesAsyncTask.java *************************************
 ************************ Full Name- 1. Manali Ghare 2. Anup Deshpande (Group 19) ***********

 */

package com.example.manalighare.hw05;

import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsSourcesAsyncTask extends AsyncTask<String, Void, ArrayList<Source>> {
    private LoadData loadData;
    public NewsSourcesAsyncTask(LoadData loadData) {
        this.loadData = loadData;
    }



    @Override
    protected ArrayList<Source> doInBackground(String... strings) {
        HttpURLConnection connection = null;
        ArrayList<Source> result = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF8");

                JSONObject root = new JSONObject(json);
                JSONArray sources = root.getJSONArray("sources");


                for (int i=0; i<sources.length(); i++){
                    JSONObject sourceJson = sources.getJSONObject(i);
                    Source source = new Source();
                    source.id = sourceJson.getString("id");
                    source.name = sourceJson.getString("name");

                    result.add(source);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return  result;

    }

    @Override
    protected void onPostExecute(ArrayList<Source> sources) {
        if (sources.size()!=0){
            loadData.Load_Data(sources);
        }
    }

    public static interface LoadData{
        public void Load_Data(ArrayList<Source> news_sources);
    }
}
