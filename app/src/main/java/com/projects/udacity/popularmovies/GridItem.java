package com.projects.udacity.popularmovies;

/**
 * Created by DoublyDead on 27.11.15.
 */
public class GridItem {

    String title;
    String release_date;
    String overview;
    String poster_path;
    String vote_average;
    String detailPosterPath;

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public GridItem(String title, String release_date, String overview, String poster_path,
                    String vote_average, String detailPosterPath) {
        this.title = title;
        this.release_date = release_date;
        this.overview = overview;
        this.poster_path = poster_path;
        this.vote_average = vote_average;
        this.detailPosterPath = detailPosterPath;
    }
}
