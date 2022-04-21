package com.example.matsedillvikunnar.EntityClass;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("ID")
    private long mID;

    @SerializedName("username")
    private String mUsername;
    @SerializedName("userEmail")
    private String mUserEmail;
    @SerializedName("userPassword")
    private String mUserPassword;
    @SerializedName("mealPlanList")
    private List<MealPlan> mMealPlanList;
    @SerializedName("userCategory")
    private String mUserCategory;



    public User(String username, String userEmail, String userPassword, String userCategory) {
        mUsername = username;
        mUserEmail = userEmail;
        mUserPassword = userPassword;
        mUserCategory = userCategory;
    }
    public User() {

    }

    public long getID() {
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

    public List<MealPlan> getMealPlan() {
        return mMealPlanList;
    }

    public void setMealPlan(List<MealPlan> mealPlanList) {
        mMealPlanList = mealPlanList;
    }

    public String getUserCategory() {
        return mUserCategory;
    }

    public void setUserCategory(String userCategory) {
        mUserCategory = userCategory;
    }
}
