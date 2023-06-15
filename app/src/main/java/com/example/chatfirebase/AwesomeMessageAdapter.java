package com.example.chatfirebase;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class AwesomeMessageAdapter extends ArrayAdapter <AwesomeMessage> {
    public AwesomeMessageAdapter(Context context, int resource, List<AwesomeMessage>messages) {
        super(context, resource,messages);
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        if(convertView ==null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.message_item, parent,false);

        }
        ImageView photoImageView = convertView.findViewById(R.id.photoImageView);
        TextView textView = convertView.findViewById(R.id.textView);
        TextView textViewName = convertView.findViewById(R.id.Name);

        AwesomeMessage message = getItem(position);

        boolean isText = message.getImagUrl() == null;

        if(isText){

            textView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            textViewName.setText(message.getText());
        }else{
            textViewName.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext()).load(message.getImagUrl()).into(photoImageView);
        }
        textViewName.setText(message.getName());

        return convertView;
    }
}
