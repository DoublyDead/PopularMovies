package com.projects.udacity.popularmovies;

/**
 * Created by DoublyDead on 28.11.15.
 */
public class DetailInformation {
    String title;
    String details;
    String image = null;
    public DetailInformation(String title, String details, String image){
        this.title = title;
        this.details = details;
        this.image = image;
    }

    public DetailInformation(String title, String details){
        this.title = title;
        this.details = details;
        this.image = null;
    }
}
