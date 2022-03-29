package com.example.matsedillvikunnar.networking;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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
    NetworkManager mNetworkManager;

    public Service(Context context) {
        this.mNetworkManager = NetworkManager.getInstance(context);
    }

    // Það má eyða þessum vill ekki gera það alveg stax ef þið eruð að setja hérna inn


    // TODO : POST MealPlan to MPList when user make new mealplan
    // TODO : GET x Recipes for MealPlan -> x means how many days
    // TODO : GET shopping list for MPList
}
