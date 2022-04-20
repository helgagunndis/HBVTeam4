package com.example.matsedillvikunnar.Service;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.matsedillvikunnar.EntityClass.MealPlan;
import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.example.matsedillvikunnar.EntityClass.User;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.networking.NetworkManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class UserService {
    private static final String TAG = "UserService";
    NetworkManager mNetworkManager;

    public UserService(Context context) {
        this.mNetworkManager = NetworkManager.getInstance(context);
    }

    /**
     * Post new User to rest UserRestControler that adds it to the database
     * @param requestBody
     * @param callback
     */
    public void postSignup(JSONObject requestBody, NetworkCallback<User> callback) {
        mNetworkManager.post("rest/signup", requestBody, new NetworkCallback<String>() {
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

    /**
     * POST a User that is already in the database
     * @param requestBody
     * @param callback
     */

    public void postUser(JSONObject requestBody, NetworkCallback<User> callback) {
        mNetworkManager.post("rest/login", requestBody, new NetworkCallback<String>() {
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

    /**
     * GET mealPlan that the user has made already
     * @param username
     * @param callback
     */

    public void getMealPlan(String username , NetworkCallback<List<MealPlan>> callback) {
        Uri.Builder uri = Uri.parse("rest/user/mealplan").buildUpon();
        uri.appendQueryParameter("username",username);

        mNetworkManager.get(uri.build().toString(), new NetworkCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<MealPlan>>(){}.getType();
                List<MealPlan> mealPlan = gson.fromJson(result, listType);
                callback.onSuccess(mealPlan);
            }
            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        });
    }

    /**
     * Set new category on user
     * @param username
     * @param category
     * @param callback
     */
    public void categoryUser(String username , String category, NetworkCallback<User> callback) {
        Uri.Builder uri = Uri.parse("rest/user/category").buildUpon();
        uri.appendQueryParameter("username",username);
        uri.appendQueryParameter("category",category);

        mNetworkManager.get(uri.build().toString(), new NetworkCallback<String>() {
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

    /**
     * Find a user that has that username.
     * @param username
     * @param callback
     */
    public void findUser(String username, NetworkCallback<User> callback) {
        Uri.Builder uri = Uri.parse("rest/user/user").buildUpon();
        uri.appendQueryParameter("username",username);

        mNetworkManager.get(uri.build().toString(), new NetworkCallback<String>() {
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


}
