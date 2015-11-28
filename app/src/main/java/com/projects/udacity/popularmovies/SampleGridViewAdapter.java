package com.projects.udacity.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DoublyDead on 27.11.15.
 */
public class SampleGridViewAdapter extends BaseAdapter{

    private Context context;
    private List<GridItem> gridItems = new ArrayList<GridItem>();


    public SampleGridViewAdapter(Context context, List<GridItem> objects) {
        this.context = context;
        gridItems = objects;
    }


    @Override
    public int getCount() {
        return gridItems.size();
    }

    @Override
    public GridItem getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView view = (ImageView) convertView;
        if(view == null){
            view = new ImageView(context);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        GridItem gridItem = gridItems.get(position);
        String imageUrl = gridItem.getPoster_path();

        Picasso.with(context)
                .load(imageUrl)
                .into(view);

        return view;
    }
}
