package com.example.matsedillvikunnar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.matsedillvikunnar.databinding.ActivityMainBinding;
import com.example.matsedillvikunnar.ui.Activities.MealPlanActivity;
import com.example.matsedillvikunnar.ui.Activities.MyPageActivity;
import com.example.matsedillvikunnar.ui.Activities.RecipesActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.myPages:
                    break;
                case R.id.newPlan:
                    Intent intent2 = new Intent(MainActivity.this, MealPlanActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.recipes:
                    Intent intent3 = new Intent(MainActivity.this, RecipesActivity.class);
                    startActivity(intent3);
                    break;
            }

            return true;
        });
    }
}

