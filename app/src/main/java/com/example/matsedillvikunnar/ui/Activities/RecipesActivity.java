package com.example.matsedillvikunnar.ui.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.databinding.ActivityMyPageBinding;
import com.example.matsedillvikunnar.databinding.ActivityRecipeBinding;
import com.example.matsedillvikunnar.databinding.ActivityRecipesBinding;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.networking.NetworkManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class RecipesActivity extends AppCompatActivity {
    private final String TAG ="RecipesActivity";
    private static final String KEY_RECIPES = "recipes";

    private int mRecipesIndex = 0;
    private List<Recipe> mRecipes;
    private TextView mTextViewTEST;
    ActivityRecipesBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        //mTextViewTEST = (TextView) findViewById(R.id.recipe_test);

        if(savedInstanceState != null){
            mRecipesIndex = savedInstanceState.getInt(KEY_RECIPES, 0);
        }
        NetworkManager networkManager = NetworkManager.getInstance(this);
        networkManager.getRecipes(new NetworkCallback<List<Recipe>>() {
            @Override
            public void onSuccess(List<Recipe> result) {
                mRecipes = result;
                Log.d(TAG, "Successfully fetched questions.");
                Log.d(TAG, "uppskrift:"+ mRecipes.get(0).getRecipeTitle());
                // TEST skrif Sinneps Lax því það er fyrsta uppskriftinn
                //mTextViewTEST.setText(mRecipes.get(0).getRecipeTitle());

            }
            @Override
            public void onFailure(String errorString) {
                Log.e(TAG, "Failed to get questions: " + errorString);
            }
        });

       /* //bottom nav bar kallar á hin activity
       //TODO: laga þannig að það notar ekki databinding
        binding = ActivityRecipesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.myPages:
                    Intent intent1 = new Intent(RecipesActivity.this, MyPageActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.newPlan:
                    Intent intent2 = new Intent(RecipesActivity.this, MealPlanActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.recipes:
                    break;
            }
            return true;
        });*/

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.myPages:
                        Intent intent1 = new Intent(RecipesActivity.this, MyPageActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.newPlan:
                        Intent intent2 = new Intent(RecipesActivity.this, MealPlanActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.recipes:
                        break;
                }
                return false;
            }
        });

    }


}