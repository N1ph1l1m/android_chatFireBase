package com.example.chatfirebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

public class AwesomeMessageAdapter extends ArrayAdapter <AwesomeMessage> {

    private  List<AwesomeMessage> messages;
    private Activity activity;
    public AwesomeMessageAdapter(Activity context, int resource, List<AwesomeMessage>messages) {
        super(context, resource,messages);

        this.messages = messages;
        this.activity = context;

    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        LayoutInflater layoutInflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);


        AwesomeMessage awesomeMessage = getItem(position);
        int layoutResource = 0;
        int viewType = getItemViewType(position);


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

    @Override
    public int getItemViewType(int position) {
        int flag;
        AwesomeMessage awesomeMessage = messages.get(position);
        if(awesomeMessage.isMine()){
            flag = 0;
        }else{
            flag =1;
        }
        return flag;
    }

    @Override
    public int getViewTypeCount() {
        return 2;

    }

    private class ViewHolder{
        private TextView messageTextView;
        private ImageView photoImageView;

        private ViewHolder(View view){
            photoImageView = view.findViewById(R.id.PhotoImageView);
            messageTextView = view.findViewById(R.id.MessageTextView);
        }

    }
}
