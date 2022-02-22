package com.example.matsedillvikunnar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.matsedillvikunnar.R;

public class MyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        Intent i = getIntent();
        String email = i.getStringExtra("email");
        ((TextView) findViewById(R.id.username)).setText(email);
    }
}