package com.example.matsedillvikunnar.networking;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.example.matsedillvikunnar.EntityClass.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkManager {
    private static final String BASE_URL = "http://10.0.2.2:8080/";
    private final String TAG ="NetworkManager";
    private static NetworkManager mInstance;
    private static RequestQueue mQueue;
    private Context mContext;
    private String token;

    public static synchronized NetworkManager getInstance(Context context){
        if(mInstance == null) {
            mInstance = new NetworkManager(context);
        }
        return mInstance;
    }

    private NetworkManager(Context context) {
        mContext = context;
        mQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if(mQueue == null) {
            mQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mQueue;
    }

    public void setToken(String token ){ this.token = token;}

    /**
     * Getting response form server
     * @param urlSpec
     * @param method
     * @return
     * @throws IOException
     */
    public byte[] getUrlBytes(String urlSpec, String method) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        if(token!=null) conn.addRequestProperty("Authorization","Bearer"+ token);
        conn.setRequestMethod(method);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = conn.getInputStream();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(conn.getResponseMessage() + ": with " + urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            conn.disconnect();
        }
    }

    public String getUrlString(String urlSpec, String method) throws IOException{
        return new String(getUrlBytes(urlSpec,method));
    }

    /**
     *
     * @param callback
     */
    public void getRecipes(final NetworkCallback<List<Recipe>> callback){
        StringRequest request = new StringRequest(
                Request.Method.GET, BASE_URL + "recipes", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Recipe>>(){}.getType();
                List<Recipe> recipes = gson.fromJson(response, listType);
                callback.onSuccess(recipes);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.toString());
            }
        }
        );
        mQueue.add(request);
    }

    public void getRecipe(int id, final NetworkCallback<Recipe> callback){
        String url = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("recipes")
                .appendPath(String.valueOf(id))
                .build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Recipe recipe = gson.fromJson(response, Recipe.class);
                callback.onSuccess(recipe);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.toString());
            }
        }
        );
        mQueue.add(request);
    }
    public boolean createUser(String username,String email, String password){
        String url = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("signup1")
                .appendQueryParameter("username",username)
                .appendQueryParameter("userEmail",email)
                .appendQueryParameter("userPassword", password)
                .build()
                .toString();
        try {
            String response = getUrlString(url,"POST");
            Log.d(TAG, "Tókst að posta gögnum "+ response);
        }
        catch (Exception e){
            Log.e(TAG, "error createUser");
            return false;
        }
        return  true;
    }






    // TODO : POST user , til að staðfesta að user sé í gagnagrunni
    // TODO : GET user
    // TODO : POST MealPlan to MPList when user make new mealplan
    // TODO : GET x Recipes for MealPlan -> x means how many days
    // TODO : GET shopping list for MPList

}
