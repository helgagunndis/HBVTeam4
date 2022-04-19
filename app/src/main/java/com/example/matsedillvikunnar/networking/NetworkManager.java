package com.example.matsedillvikunnar.networking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NetworkManager {
    private static final String BASE_URL = "http://10.0.2.2:8080/";
    private final String TAG ="NetworkManager";
    private static NetworkManager mInstance;
    private static RequestQueue mQueue;
    private Context mContext;


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
    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
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
        } finally { conn.disconnect(); }
    }
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    /**
     * Post request to the backend
     * @param url
     * @param requestBody
     * @param callback
     */
    public void post(String url, JSONObject requestBody, NetworkCallback<String> callback ){
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, BASE_URL + url, requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response.toString());
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.toString());
            }
        });

        mQueue.add(request);
    }
    /**
     * Get request to the backend
     * @param url
     * @param callback
     */
    public void get(String url, NetworkCallback<String> callback){
        StringRequest request = new StringRequest(
                Request.Method.GET, BASE_URL + url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null){
                    try {
                        response=new String(response.getBytes("ISO-8859-1"), "UTF-8");

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                callback.onSuccess(response);
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

    public void getRecipe(int id, final NetworkCallback<Recipe> callback) {
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











    /*private static final Uri ENDPOINT = Uri
            .parse("http://10.0.2.2:8080/")
            .buildUpon()
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1")
            .appendQueryParameter("extras", "url_s, geo")
            .build();


    private String buildUrl(String method, String query) {
        Uri.Builder uriBuilder = ENDPOINT.buildUpon()
                .appendQueryParameter("method", method);

        return uriBuilder.build().toString();
    }

    public List<Recipe> fetchMealPlan() {
        String url = buildUrl("rest/mealplan", null);
        return getRecipeList(url);
    }

    private List<Recipe> getRecipeList(String url) {
        List<Recipe> items = new ArrayList<>();
        try {
            String jsonString = getUrlString(url);
            Log.i(TAG, "received JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);

        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }
        return items;
    }
    private void parseItems(List<Recipe> items, JSONObject jsonBody)
            throws IOException, JSONException {

        // TODO: Convert to Gson
        JSONObject recipeJsonObject = jsonBody.getJSONObject("recipeCategory");
        JSONArray recipeJsonArray = recipeJsonObject.getJSONArray("recipeCategory");

        for (int i = 0; i < recipeJsonArray.length(); i++) {
            JSONObject recipesJsonObject = recipeJsonArray.getJSONObject(i);

            Recipe recipe = new Recipe();
            recipe.setRecipeCategory(recipeJsonObject.getInt("recipeCategory"));

            items.add(recipe);
        }
    }*/

}




