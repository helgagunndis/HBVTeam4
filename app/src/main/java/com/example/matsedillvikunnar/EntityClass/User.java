package com.example.matsedillvikunnar.EntityClass;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("ID")
    private int mID;

    @SerializedName("username")
    private String mUsername;
    @SerializedName("userEmail")
    private String mUserEmail;
    @SerializedName("userPassword")
    private String mUserPassword;
    // TODO : Bæta við Category sem fylgir notandanum
    @SerializedName("mealPlanList")
    private List<MPList> mMealPlan;

    public User(String username, String userEmail, String userPassword) {
        mUsername = username;
        mUserEmail = userEmail;
        mUserPassword = userPassword;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public void setUserEmail(String userEmail) {
        mUserEmail = userEmail;
    }

    public String getUserPassword() {
        return mUserPassword;
    }

    public void setUserPassword(String userPassword) {
        mUserPassword = userPassword;
    }

    public List<MPList> getMealPlan() {
        return mMealPlan;
    }

    public void setMealPlan(List<MPList> mealPlan) {
        mMealPlan = mealPlan;
    }
}
