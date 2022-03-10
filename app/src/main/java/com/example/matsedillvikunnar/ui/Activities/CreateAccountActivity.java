package com.example.matsedillvikunnar.ui.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.matsedillvikunnar.MainActivity;
import com.example.matsedillvikunnar.R;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void backToLogin (View v){
        Intent i= new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void  handleSignup(View v){
     // TODO: athuga hvort öll skilyrði fyrir nýjan notenda sé uppfyllt -> skrá nýjan notenda
    }
}