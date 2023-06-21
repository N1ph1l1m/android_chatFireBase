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
        if(viewType == 0 ){
            layoutResource = R.layout.my_message_item;
        }else{
            layoutResource = R.layout.your_message_item;
        }


        if(convertView!=null){
            viewHolder = (ViewHolder) convertView.getTag();
        }else {
            convertView = layoutInflater.inflate(layoutResource,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        boolean isText = awesomeMessage.getImagUrl() ==null;
        if (isText) {
            viewHolder.messageTextView.setVisibility(View.VISIBLE);
            viewHolder.photoImageView.setVisibility(View.GONE);
            viewHolder.messageTextView.setText(awesomeMessage.getText());
        }else {
            viewHolder.messageTextView.setVisibility(View.GONE);
            viewHolder.photoImageView.setVisibility(View.VISIBLE);
            viewHolder.messageTextView.setText(awesomeMessage.getText());
            Glide.with(viewHolder.photoImageView.getContext()).load(awesomeMessage.getImagUrl()).into(viewHolder.photoImageView);
        }

        if(convertView ==null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.message_item, parent,false);

        }


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
