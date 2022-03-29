package com.example.matsedillvikunnar.Service;

import android.content.Context;
import android.net.Uri;

import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.networking.NetworkManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class RecipeService {
    private static final String TAG = "RecipeService";
    NetworkManager mNetworkManager;

    public RecipeService(Context context) {
        this.mNetworkManager = NetworkManager.getInstance(context);
    }

    public void getRecipes(final NetworkCallback<List<Recipe>> callback){
        mNetworkManager.get("rest/recipes", new NetworkCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Recipe>>(){}.getType();
                List<Recipe> recipes = gson.fromJson(result, listType);
                callback.onSuccess(recipes);
            }
            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        });
    }

    public void getMealPlan(int category, int days , NetworkCallback<List<Recipe>> callback) {
        Uri.Builder uri = Uri.parse("rest/mealplan").buildUpon();
        uri.appendQueryParameter("recipeCategory", Integer.toString(category));
        uri.appendQueryParameter("numberOfWeekDay", Integer.toString(days));

        mNetworkManager.get(uri.build().toString(), new NetworkCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Recipe>>(){}.getType();
                List<Recipe> recipes = gson.fromJson(result, listType);
                callback.onSuccess(recipes);
            }
            @Override
            public void onFailure(String errorString) {
                callback.onFailure("  " + errorString);
            }
        });
    }
}
