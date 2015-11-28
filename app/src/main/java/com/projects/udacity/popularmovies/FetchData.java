package com.projects.udacity.popularmovies;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DoublyDead on 27.11.15.
 */
public class FetchData extends AsyncTask<String, Void, String> {

    static final String TAG_FETCH_DATA = "mytag";

    @Override
    protected void onPostExecute(String jsonString) {
        super.onPostExecute(jsonString);
    }

    @Override
    protected String doInBackground(String... params) {
        final String sorted_by = params[0];
        StringBuilder stringBuilder;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        InputStream inputStream;

        StringBuffer jsonResponseBuffer = new StringBuffer();
        String jsonResponseString = null;
        try{
            if(params[0].equals(UrlConstant.POPULAR)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(UrlConstant.BASE_URL)
                        .append(UrlConstant.MOVIE)
                        .append(UrlConstant.POPULAR)
                        .append(UrlConstant.API_PREFIX);
            }else if(params[0].equals(UrlConstant.TOP_RATED)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(UrlConstant.BASE_URL)
                        .append(UrlConstant.MOVIE)
                        .append(UrlConstant.TOP_RATED)
                        .append(UrlConstant.API_PREFIX);
            }else {
                stringBuilder = null;
                return null;
            }


            URL TMDBurl = new URL(stringBuilder.toString().concat(UrlConstant.API));

            urlConnection = (HttpURLConnection) TMDBurl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            if(inputStream == null){
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while((line = reader.readLine()) != null){
                jsonResponseBuffer.append(line + "\n");
            }

            if(jsonResponseBuffer.length() == 0){
                return null;
            }

            jsonResponseString = jsonResponseBuffer.toString();



        }
            catch (IOException e){

        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonResponseString;
    }
}
