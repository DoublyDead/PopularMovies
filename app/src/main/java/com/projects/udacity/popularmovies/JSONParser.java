package com.projects.udacity.popularmovies;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JSONParser {

    public static ArrayList<GridItem> parseJson(String jsonStr, Context ctx) throws Exception {

        if(isOnline(ctx)){
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObject.getJSONArray(JSONConstant.RESULTS);
            ArrayList<GridItem> gridItems = new ArrayList<>();


            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonFimlObj = jsonArray.getJSONObject(i);
                String title = jsonFimlObj.getString(JSONConstant.TITLE);
                String release_date = jsonFimlObj.getString(JSONConstant.RELEASE_DATE);
                String vote_average = jsonFimlObj.getString(JSONConstant.VOTE_AVERAGE);
                String overview = jsonFimlObj.getString(JSONConstant.OVERVIEW);
                String posterPath = "https://image.tmdb.org/t/p/w185" + jsonFimlObj.getString(
                        JSONConstant.POSTER_PATH);
                String detailPosterPath = "https://image.tmdb.org/t/p/w320" + jsonFimlObj.getString(
                        JSONConstant.POSTER_PATH);

                GridItem gridItem = new GridItem(title, release_date, overview, posterPath, vote_average,
                        detailPosterPath);
                gridItems.add(gridItem);
            }

            return gridItems;
        }else {
            throw new Exception("Your internet is offline");
        }
    }

    private static boolean isOnline(Context context){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        return isConnected;
    }

}
