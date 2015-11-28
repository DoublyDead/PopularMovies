package com.projects.udacity.popularmovies;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    ImageView posterIv;
    ListView information;
    ArrayList<DetailInformation>detailInformations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String title = getIntent().getStringExtra("title");
        String poster = getIntent().getStringExtra("poster");
        String overview = getIntent().getStringExtra("overview");
        String releaseDate = getIntent().getStringExtra("release_date");
        String averageVote = getIntent().getStringExtra("average_vote");

        detailInformations = new ArrayList<>();
        DetailInformation titleAndPosterDetail = new DetailInformation(title, null, poster);
        DetailInformation overviewDetail = new DetailInformation("Overview", overview);
        DetailInformation releaseDateDetail = new DetailInformation("Release Date", releaseDate);
        DetailInformation averageVoteDetail = new DetailInformation("Average Vote", averageVote);

        posterIv = (ImageView) findViewById(R.id.iv_big);
        information = (ListView) findViewById(R.id.lv_information_container);

        detailInformations = new ArrayList<>();
        detailInformations.add(titleAndPosterDetail);
        detailInformations.add(overviewDetail);
        detailInformations.add(releaseDateDetail);
        detailInformations.add(averageVoteDetail);

        information.setAdapter(new DetailInformationAdapter(getApplication(), detailInformations));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
