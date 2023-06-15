package com.example.chatfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView messageListView;
    private AwesomeMessageAdapter adapter;
    private ProgressBar processBar;
    private ImageButton sendImageButton;
    private Button sendMessageButton;
    private EditText messageEditText;
    private String  userName;
    FirebaseDatabase database;
    DatabaseReference messageDataBaseReferences;
    DatabaseReference messageDataBaseReferences2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<AwesomeMessage> awesomeMessages = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        messageDataBaseReferences = database.getReference().child("messages");
        messageDataBaseReferences.setValue("Hello FireBase");

        messageDataBaseReferences2 =  database.getReference().child("Persone");
        messageDataBaseReferences2.child("First Name").setValue("Mike");
        messageDataBaseReferences2.child("Bugs").setValue("red");
        messageDataBaseReferences2.child("Age").setValue(22);


        userName = "Default User";
        messageListView = findViewById(R.id.listView);
        processBar = findViewById(R.id.progressBar);
        sendMessageButton = findViewById(R.id.buttonId);
        sendImageButton = findViewById(R.id.sendPhoto);
        messageEditText = findViewById(R.id.messageEditText);
        adapter = new AwesomeMessageAdapter(this , R.layout.message_item,awesomeMessages);
        messageListView.setAdapter(adapter);


        processBar.setVisibility(ProgressBar.INVISIBLE);

        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length()>0){
                    sendMessageButton.setEnabled(true);
                }else{
                    sendMessageButton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
                messageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(500)});
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageEditText.setText("");
            }
        });
        sendImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}