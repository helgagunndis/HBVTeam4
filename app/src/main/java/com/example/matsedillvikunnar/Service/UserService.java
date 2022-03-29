package com.example.matsedillvikunnar.Service;

import android.content.Context;
import android.util.Log;

import com.example.matsedillvikunnar.EntityClass.User;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.networking.NetworkManager;
import com.google.gson.Gson;

import org.json.JSONObject;

public class UserService {
    private static final String TAG = "UserService";
    NetworkManager mNetworkManager;

    public UserService(Context context) {
        this.mNetworkManager = NetworkManager.getInstance(context);
    }
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

}
