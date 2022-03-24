package com.example.matsedillvikunnar.ui.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.matsedillvikunnar.R;

public class SetupActivity extends AppCompatActivity {

    private Button mButtonOmnivore;
    private Button mButtonPescatarian;
    private Button mButtonVegetarian;
    private Button mButtonVegan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        mButtonOmnivore = (Button) findViewById(R.id.button_omnivore);
        mButtonPescatarian = (Button) findViewById(R.id.button_pescatarian);
        mButtonVegetarian = (Button) findViewById(R.id.button_vegetarian);
        mButtonVegan = (Button) findViewById(R.id.button_vegan);



    }


}