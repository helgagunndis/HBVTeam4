package com.example.matsedillvikunnar.EntityClass;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MPList {

    @SerializedName("listID")
    private long mListID;

    @SerializedName("recipe")
    private Recipe mRecipe;
    @SerializedName("mealPlan")
    private MealPlan mMealPlan;

    public MPList(Recipe recipe, MealPlan mealPlan) {
        mRecipe = recipe;
        mMealPlan = mealPlan;
    }

    public long getListID() {
        return mListID;
    }

    public void setListID(long listID) {
        mListID = listID;
    }

    public Recipe getRecipe() {
        return mRecipe;
    }

    public void setRecipe(Recipe recipe) {
        mRecipe = recipe;
    }

    public MealPlan getMealPlan() {
        return mMealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        mMealPlan = mealPlan;
    }
}
