package com.toDo.listViewCust;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.resources.TextAppearance;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class listAdapter extends ArrayAdapter<setData> {

    List<setData> data;
    int resource;
    Context context;

    listAdapter(Context context, int resource, List<setData> data){

        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(resource, null, false);

        TextView textViewTitle = view.findViewById(R.id.textTitle);
        TextView textViewDesc = view.findViewById(R.id.textDescription);
        ImageView itemImage = view.findViewById(R.id.listImage);
        ImageView likeImage = view.findViewById(R.id.like);
        ImageView commentImage = view.findViewById(R.id.comment);
        ImageView moreImage = view.findViewById(R.id.more);
        ImageView shareImage = view.findViewById(R.id.share);
        ImageView delete = view.findViewById(R.id.deleteItem);

        moreImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "More Options Clicked", Toast.LENGTH_SHORT).show();
                if(itemImage.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(parent, new AutoTransition());
                    itemImage.setVisibility(View.GONE);
                }
                else {
                    TransitionManager.beginDelayedTransition(parent, new AutoTransition());
                    itemImage.setVisibility(View.VISIBLE);
                }
                ((ListView)parent.findViewById(R.id.list)).refreshDrawableState();
            }
        });

        commentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Comment Clicked", Toast.LENGTH_SHORT).show();
                if(textViewDesc.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(parent, new AutoTransition());
                    textViewDesc.setVisibility(View.GONE);
                }
                else{
                    TransitionManager.beginDelayedTransition(parent, new AutoTransition());
                    textViewDesc.setVisibility(View.VISIBLE);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemImage.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(parent, new AutoTransition());
                    itemImage.animate().alpha(0).setDuration(100).start();
                    itemImage.setVisibility(View.GONE);
                }
                textViewTitle.setPaintFlags(textViewTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                textViewDesc.setPaintFlags(textViewTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


                final Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
                Handler handle = new Handler();
                handle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        parent.startAnimation(animation);
                        data.remove(position);

                        listAdapter.super.notifyDataSetChanged();
                        animation.cancel();
                    }
                }, 600);

                Toast.makeText(context, "Deleted Item", Toast.LENGTH_SHORT).show();
            }
        });


        final setData newData = data.get(position);
        textViewTitle.setText(newData.getTitle());
        textViewDesc.setText(newData.getDescription());
        Picasso.get().load(newData.getImage()).into(itemImage);
        itemImage.setVisibility(View.GONE);
        textViewDesc.setVisibility(View.GONE);
        return view;
    }

}
