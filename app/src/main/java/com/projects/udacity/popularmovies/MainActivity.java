package com.projects.udacity.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public String JSONState;
    ArrayList<GridItem> movies;
    GridView gridView;
    FetchData fetchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JSONState = (String) getLastNonConfigurationInstance();
        setContentView(R.layout.activity_main);
        fetchData = new FetchData();
        gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setOnItemClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSortPopular:
                try {
                    getServerInformation(UrlConstant.POPULAR);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.menuSortRating:
                try {
                    getServerInformation(UrlConstant.TOP_RATED);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        if(JSONState == null){
            JSONState = UrlConstant.POPULAR;
        }
        try {
            getServerInformation(JSONState);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplication(), DetailActivity.class);
        intent.putExtra("title", movies.get(position).getTitle());
        intent.putExtra("poster", movies.get(position).detailPosterPath);
        intent.putExtra("overview", movies.get(position).getOverview());
        intent.putExtra("release_date", movies.get(position).getRelease_date());
        intent.putExtra("average_vote", movies.get(position).getVote_average());
        startActivity(intent);
    }

    public void getServerInformation(String query) throws JSONException, ExecutionException, InterruptedException {
        JSONState = query;
        FetchData fetchData = new FetchData();
        fetchData.execute(query);
        String Json = fetchData.get();
        movies = JSONParser.parseJson(Json);
        gridView.setAdapter(new SampleGridViewAdapter(this, movies));
    }
}