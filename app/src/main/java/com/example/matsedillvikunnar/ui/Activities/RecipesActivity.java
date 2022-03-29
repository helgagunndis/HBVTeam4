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
import com.example.matsedillvikunnar.databinding.ActivityMyPageBinding;
import com.example.matsedillvikunnar.databinding.ActivityRecipeBinding;
import com.example.matsedillvikunnar.databinding.ActivityRecipesBinding;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.networking.NetworkManager;
import com.example.matsedillvikunnar.networking.Service;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends AppCompatActivity {
    private final String TAG = "RecipesActivity";
    private static final String KEY_RECIPES = "recipes";

    private int mRecipesIndex = 0;
    private List<Recipe> mRecipes;
    private RecyclerView recipeCards;
    private CardAdapter cardAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

<<<<<<< HEAD
=======
        mTextViewTEST = (TextView) findViewById(R.id.cardTitle);
        mImageTEST = (ImageView) findViewById(R.id.cardImage);

>>>>>>> ec248b792bd9290597468967b927b4916c7ff3f6
        if (savedInstanceState != null) {
            mRecipesIndex = savedInstanceState.getInt(KEY_RECIPES, 0);
        }

<<<<<<< HEAD
        getRecipes();

        getNavigationBar();
    }

    private void getRecipes() {
        recipeCards = findViewById(R.id.idRecipes);

        NetworkManager networkManager = NetworkManager.getInstance(this);
        networkManager.getRecipes(new NetworkCallback<List<Recipe>>() {
=======
        RecipeService service = new RecipeService(this);
        service.getRecipes(new NetworkCallback<List<Recipe>>() {
>>>>>>> ec248b792bd9290597468967b927b4916c7ff3f6
            @Override
            public void onSuccess(List<Recipe> result) {
                mRecipes = result;
                Log.d(TAG, "Successfully fetched recipes.");

                recipeCards.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                recipeCards.setLayoutManager(linearLayoutManager);
                cardAdapter = new CardAdapter(getApplicationContext(),new ArrayList<>());
                cardAdapter = new CardAdapter(mContext, mRecipes);
                recipeCards.setAdapter(cardAdapter);
            }

            @Override
            public void onFailure(String errorString) {
                Log.e(TAG, "Failed to get recipes: " + errorString);
            }
        });
    }

    //bottom nav bar kallar á hin activity
    private void getNavigationBar(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item){
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