package com.example.matsedillvikunnar.networking;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.example.matsedillvikunnar.EntityClass.MealPlan;
import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.example.matsedillvikunnar.EntityClass.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class Service {
    private static final String TAG = "Service";
    private static final String BASE_URL = "http://10.0.2.2:8080/";
    NetworkManager mNetworkManager;

    public Service(Context context) {
        this.mNetworkManager = NetworkManager.getInstance(context);
    }

    public void postSignup(JSONObject requestBody, NetworkCallback<User> callback) {
        mNetworkManager.post(BASE_URL + "rest/signup", requestBody, new NetworkCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                User user = gson.fromJson(result, User.class);
                callback.onSuccess(user);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        });
    }
    public void postUser(JSONObject requestBody, NetworkCallback<User> callback) {
        mNetworkManager.post(BASE_URL + "rest/login", requestBody, new NetworkCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                User user = gson.fromJson(result, User.class);
                Log.d(TAG, "user: " + user );

                callback.onSuccess(user);
            }
            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        });
    }

    public void postMealplan(JSONObject requestBody, NetworkCallback<List<Recipe>> callback) {
        mNetworkManager.post(BASE_URL + "rest/mealplan", requestBody, new NetworkCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "result " + result );
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Recipe>>(){}.getType();
                List<Recipe> recipes = gson.fromJson(result, listType);
                Log.d(TAG, "uppskriftir " + recipes );
                callback.onSuccess(recipes);
            }
            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        });
    }

    public void getMealPlan(String day, String category, NetworkCallback<List<Recipe>>callback) {
        String url = Uri.parse(BASE_URL+"rest/mealplan")
                .buildUpon()
                //.appendQueryParameter("numberOfWeekDay", day)
                //.appendQueryParameter("recipeCategory", category)
                .build().toString();
        System.out.println(url);

        mNetworkManager.get(url, new NetworkCallback<String>(){
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

    // TODO : POST MealPlan to MPList when user make new mealplan
    // TODO : GET x Recipes for MealPlan -> x means how many days
    // TODO : GET shopping list for MPList
}
