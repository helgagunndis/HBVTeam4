package com.example.matsedillvikunnar.ui.Activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.networking.NetworkManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class RecipesActivity extends AppCompatActivity {
    private final String TAG ="RecipesActivity";
    private static final String KEY_RECIPES = "recipes";

    private int mRecipesIndex = 0;
    private List<Recipe> mRecipes;
    private TextView mTextViewTEST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        mTextViewTEST = (TextView) findViewById(R.id.recipe_test);

        if(savedInstanceState != null){
            mRecipesIndex = savedInstanceState.getInt(KEY_RECIPES, 0);
        }
        NetworkManager networkManager = NetworkManager.getInstance(this);
        networkManager.getRecipes(new NetworkCallback<List<Recipe>>() {
            @Override
            public void onSuccess(List<Recipe> result) {
                mRecipes = result;
                Log.d(TAG, "Successfully fetched questions.");
                // TEST skrif Sinneps Lax því það er fyrsta uppskriftinn
                mTextViewTEST.setText(mRecipes.get(0).getRecipeTitle());
            }
            @Override
            public void onFailure(String errorString) {
                Log.e(TAG, "Failed to get questions: " + errorString);
            }
        });


    }
}