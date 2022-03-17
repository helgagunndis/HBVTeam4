package com.example.matsedillvikunnar.EntityClass;


import com.google.gson.annotations.SerializedName;

public class MealPlan {
    @SerializedName("mealPlanID")
    private long mMealPlanID;

    @SerializedName("numberOfWeekDay")
    private int mNumberOfWeekDay;
    @SerializedName("recipeCategory")
    private int  mRecipeCategory;

    public MealPlan(long mealPlanID, int numberOfWeekDay, int recipeCategory) {
        mMealPlanID = mealPlanID;
        mNumberOfWeekDay = numberOfWeekDay;
        mRecipeCategory = recipeCategory;
    }

    public long getMealPlanID() {
        return mMealPlanID;
    }

    public void setMealPlanID(long mealPlanID) {
        mMealPlanID = mealPlanID;
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
}
