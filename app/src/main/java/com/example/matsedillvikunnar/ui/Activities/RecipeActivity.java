package com.example.matsedillvikunnar.ui.Activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matsedillvikunnar.EntityClass.Ingredient;
import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.Service.RecipeService;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.networking.NetworkManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {
    private TextView mRecipeName;
    private ImageView mRecipeImage;
    private TextView mRecipeMethod;
    private ListView mRecipeIngredients;
    private TextView mRecipeTime;
    private TextView mRecipeServings;

    List<Recipe> recipeList;
    private int mRecipe;
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
    ArrayList ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String recipeID;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                recipeID= null;
            } else {
                recipeID= extras.getString("individual_recipe");
            }
        } else {
            recipeID= (String) savedInstanceState.getSerializable("individual_recipe");
        }

        mRecipe = Integer.parseInt(recipeID);
        getRecipe();
    }

    public void getRecipe(){
        RecipeService service = new RecipeService(this);
        service.getRecipes(new NetworkCallback<List<Recipe>>() {
            @Override
            public void onSuccess(List<Recipe> result) {
                recipeList = result;
                Recipe recipe = null;

                for (Recipe r:recipeList) {
                    if (r.getRecipeID() == mRecipe){
                        recipe = r;
                    }
                else {
                        Log.d(TAG, "failed to get recipeID");
                    }
                }

                mRecipeName = (TextView) findViewById(R.id.recipeName);
                mRecipeImage = (ImageView) findViewById(R.id.recipe_image);
                mRecipeMethod = (TextView) findViewById(R.id.recipe_method);
                mRecipeIngredients = (ListView) findViewById(R.id.recipe_ingredients);
                mRecipeTime = (TextView)findViewById(R.id.recipe_time);
                mRecipeServings = (TextView) findViewById(R.id.recipe_servings);

                mRecipeName.setText(recipe.getRecipeTitle());
                Picasso.get().load(recipe.getRecipeImage()).fit().into(mRecipeImage);
                mRecipeMethod.setText(recipe.getRecipeMethod());
                mRecipeServings.setText("Fyrir " + recipe.getRecipeServings());
                mRecipeTime.setText(recipe.getRecipeTime());

                ingredientList = new ArrayList();
                for (Ingredient ingredient:recipe.getIngredients()) {
                    ingredientList.add(ingredient.getAmount()+ " " +ingredient.getIngredientInfo().getIngredientName());
                }

                mRecipeIngredients = (ListView)findViewById(R.id.recipe_ingredients);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(RecipeActivity.this, R.layout.listview_ingredients,R.id.ingredient, ingredientList);
                mRecipeIngredients.setAdapter(arrayAdapter);

            }
            @Override
            public void onFailure(String errorString) {
                Log.e(TAG, "Failed to get recipes: " + errorString);
            }
        });
    }
}
