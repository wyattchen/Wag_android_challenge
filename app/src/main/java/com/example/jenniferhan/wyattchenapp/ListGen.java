package com.example.jenniferhan.wyattchenapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.Bitmap;
//import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.BitmapDrawable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Callback;
import java.io.File;
import java.util.ArrayList;
import java.lang.annotation.Annotation;


public class ListGen extends ArrayAdapter<Items> { //generate the list of items
    ArrayList<Items> items;
    Context context;
    int resource;

    public ListGen(Context context, int resource, ArrayList<Items> items) {
        super(context, resource, items);
        this.items = items;
        this.context = context;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){ //get the item object and get all the gravatar, display_name, badges, and user_type of the object

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_list_layout, null, true);
        }


        final Items item= getItem(position);
        final ImageView imgView =(ImageView) convertView.findViewById(R.id.imageViewProduct);
        final ProgressBar spinner=(ProgressBar)convertView.findViewById(R.id.progress_bar);

        imgView.setVisibility(View.VISIBLE);

        Picasso.with(context).load(item.getGravatar()).networkPolicy(NetworkPolicy.OFFLINE).into(imgView, new Callback() {
         // check the network if it is offline and load the images
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        // Try again online if cache failed
                        Picasso.with(context)
                                .load(item.getGravatar())
                                .into(imgView);
                    }
                });

        Picasso.with(context).load(item.getGravatar()).into(imgView);

        TextView dp_name =(TextView) convertView.findViewById(R.id.dpName);
        dp_name.setText(item.getDp_name());

        TextView badges =(TextView) convertView.findViewById(R.id.badges);
        badges.setText(item.getBadges());
        TextView user_type =(TextView) convertView.findViewById(R.id.user_type);
        user_type.setText(item.getUser_type());

        return convertView;

    }
}
