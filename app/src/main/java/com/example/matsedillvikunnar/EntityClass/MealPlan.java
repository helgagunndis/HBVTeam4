package com.example.matsedillvikunnar.EntityClass;


import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.List;

public class MealPlan {
    @SerializedName("mealPlanID")
    private long mMealPlanID;
    @SerializedName("numberOfWeekDay")
    private int mNumberOfWeekDay;
    @SerializedName("recipeCategory")
    private int  mRecipeCategory;
    @SerializedName("mpLists")
    private List<MPList> mMpLists;

    @SerializedName("created")
    private Timestamp mCreated;

    public MealPlan(long mealPlanID, int recipeCategory, List<MPList> mpLists) {
        mMealPlanID = mealPlanID;
        mRecipeCategory = recipeCategory;
        mMpLists = mpLists;
    }

    public long getMealPlanID() {
        return mMealPlanID;
    }

    public int getNumberOfWeekDay() {
        return mNumberOfWeekDay;
    }

    public void setNumberOfWeekDay(int numberOfWeekDay) {
        mNumberOfWeekDay = numberOfWeekDay;
    }

    public int getRecipeCategory() {
        return mRecipeCategory;
    }

    public void setRecipeCategory(int recipeCategory) {
        mRecipeCategory = recipeCategory;
    }

    public List<MPList> getMpLists() {
        return mMpLists;
    }

    public void setMpLists(List<MPList> mpLists) {
        mMpLists = mpLists;
    }

    public Timestamp getCreated() {
        return mCreated;
    }

    public void setCreated(Timestamp created) {
        mCreated = created;
    }

}
