package com.projects.udacity.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailInformationAdapter extends ArrayAdapter<DetailInformation> {

    public DetailInformationAdapter(Context context, List<DetailInformation> details) {
        super(context, R.layout.content_information, details );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetailInformation detailInformation = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.content_information, null);
        }
        ((TextView)convertView.findViewById(R.id.title_ap)).setText(detailInformation.title);
        ((TextView)convertView.findViewById(R.id.info_ap)).setText(detailInformation.details);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_ap);
        Picasso.with(getContext()).load(detailInformation.image).into(imageView);
        return convertView;
    }
}
