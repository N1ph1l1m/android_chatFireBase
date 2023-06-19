package com.example.chatfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";
    private FirebaseAuth auth;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;

    private EditText confirmPasswordEditText;
    private Button sugnUpButton;
    FirebaseDatabase database;
    DatabaseReference usersDataBase;

    private TextView toggleSignUpTextView;
    private boolean loginModeActive;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        auth = FirebaseAuth.getInstance();

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        sugnUpButton = findViewById(R.id.sugnUpButton);
        toggleSignUpTextView = findViewById(R.id.toggleSignUpTextView);




        database = FirebaseDatabase.getInstance();
        usersDataBase = database.getReference().child("users");

        sugnUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginSignUpUser(emailEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
            }
        });
        if(auth.getCurrentUser() != null){
            startActivity(new Intent(SignInActivity.this,MainActivity.class) );
        }
    }
    private void loginSignUpUser(String email, String password) {

        if(loginModeActive){
           if(emailEditText.getText().toString().trim().equals("")){
                Toast.makeText(this, "Please input your email", Toast.LENGTH_SHORT).show();
            }else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            intent.putExtra("userName",nameEditText.getText().toString().trim());
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Неправельный логин или пароль",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
            }

        }else{
            if (!passwordEditText.getText().toString().trim().equals(
                    confirmPasswordEditText.getText().toString().trim()
            )){
                Toast.makeText(this, "Password don't match", Toast.LENGTH_SHORT).show();
            }else if(passwordEditText.getText().toString().trim().length()<7){
                Toast.makeText(this, "Password must be at least 7 characters", Toast.LENGTH_SHORT).show();
            } else if(passwordEditText.getText().toString().trim().equals("")){
                Toast.makeText(this, "Please input your email", Toast.LENGTH_SHORT).show();
            } else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = auth.getCurrentUser();
                                        createUser(user);
                                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                        intent.putExtra("userName",nameEditText.getText().toString().trim());
                                        startActivity(intent);
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignInActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }
                                }
                        });
            }

        }

    }

    private void createUser(FirebaseUser firebaseUser) {
        User user = new User();
        user.setId(firebaseUser.getUid());
        user.setEmail(firebaseUser.getEmail());
        user.setName(nameEditText.getText().toString().trim());
        usersDataBase.push().setValue(user);
    }

    public void toggleLogInMode(View view) {
        if(loginModeActive){
            loginModeActive = false;
            sugnUpButton.setText("Sign Up");
            toggleSignUpTextView.setText("Tap to Log In");
            confirmPasswordEditText.setVisibility(View.VISIBLE);
        }else{
            loginModeActive = true;
            sugnUpButton.setText("Log in");
            toggleSignUpTextView.setText("Tap To Sign Up");
            confirmPasswordEditText.setVisibility(View.GONE);
        }
    }


}