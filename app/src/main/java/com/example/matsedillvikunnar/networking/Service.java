package com.example.matsedillvikunnar.networking;

import android.content.Context;
import android.util.Log;

import com.example.matsedillvikunnar.EntityClass.User;
import com.google.gson.Gson;

import org.json.JSONObject;

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

    // TODO : POST MealPlan to MPList when user make new mealplan
    // TODO : GET x Recipes for MealPlan -> x means how many days
    // TODO : GET shopping list for MPList
}