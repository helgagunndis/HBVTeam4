package com.example.matsedillvikunnar.ui.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.example.matsedillvikunnar.EntityClass.User;
import com.example.matsedillvikunnar.LoginActivity;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.Service.RecipeService;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.networking.NetworkManager;
import com.example.matsedillvikunnar.networking.Service;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class RecipesActivity extends AppCompatActivity implements RecipesAdapter.OnNoteListener{
    private final String TAG ="RecipesActivity";
    private static final String KEY_RECIPES = "recipes";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private int mRecipesIndex = 0;
    private List<Recipe> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (savedInstanceState != null) {
            mRecipesIndex = savedInstanceState.getInt(KEY_RECIPES, 0);
        }

        getRecipes();

        getBottomNav();
    }

    public void getRecipes(){
        RecipeService service = new RecipeService(this);
        service.getRecipes(new NetworkCallback<List<Recipe>>() {
            @Override
            public void onSuccess(List<Recipe> result) {
                mRecipes = result;
                Log.d(TAG, "Successfully fetched recipes.");

                recyclerView = findViewById(R.id.recipeCards);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(RecipesActivity
            .this);
                recyclerView.setLayoutManager(layoutManager);

                mAdapter = new RecipesAdapter(mRecipes, RecipesActivity.this, RecipesActivity.this::onNoteClick);
                recyclerView.setAdapter(mAdapter);

            }
            @Override
            public void onFailure(String errorString) {
                Log.e(TAG, "Failed to get recipes: " + errorString);
            }
        });
    }

    public void getBottomNav(){
        //bottom nav bar kallar á hin activity
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

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: success");

        String s = String.valueOf(mRecipes.get(position).getRecipeID());
        Log.d(TAG, "onNoteClick: recpipeID"+ mRecipes.get(position).getRecipeID());
        Log.d(TAG, "onNoteClick: recipe name"+ mRecipes.get(position).getRecipeTitle());
        Intent intent =  new Intent(this, RecipeActivity.class);
        intent.putExtra("individual_recipe", s);
        startActivity(intent);


    }
}