package com.example.matsedillvikunnar.ui.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.databinding.ActivityMainBinding;
import com.example.matsedillvikunnar.databinding.ActivityMealplanBinding;
import com.example.matsedillvikunnar.databinding.ActivityMyPageBinding;

public class MealPlanActivity extends AppCompatActivity {
    private final String TAG ="MealPLanActivity";

    ActivityMealplanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealplan);

        //bottom nav bar kallar á hin activity
        binding = ActivityMealplanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.myPages:
                    Intent intent1 = new Intent(MealPlanActivity.this, MyPageActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.newPlan:
                    break;
                case R.id.recipes:
                    Intent intent3 = new Intent(MealPlanActivity.this, RecipesActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        });
    }
}
