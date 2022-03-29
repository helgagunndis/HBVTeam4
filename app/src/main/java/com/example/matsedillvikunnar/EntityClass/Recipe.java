package com.example.matsedillvikunnar.EntityClass;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {
    @SerializedName("recipeID")
    private long mRecipeID;
    @SerializedName("recipeTitle")
    private String mRecipeTitle;
    @SerializedName("recipeCategory")
    private int mRecipeCategory;
    @SerializedName("recipeSummary")
    private String mRecipeSummary;
    @SerializedName("recipeImage")
    private String mRecipeImage;
    @SerializedName("recipeMethod")
    private String mRecipeMethod;
    @SerializedName("recipeTime")
    private String mRecipeTime;
    @SerializedName("recipeServings")
    private int mRecipeServings;
    @SerializedName("recipeCredit")
    private String mRecipeCredit;
    @SerializedName("ingredients")
    private List<Ingredient> mIngredients;
    @SerializedName("mpLists")
    private List<MPList> mMpLists;

    public Recipe(String recipeTitle, int recipeCategory, String recipeSummary, String recipeImage, String recipeMethod, String recipeTime, int recipeServings, String recipeCredit, List<Ingredient> ingredients) {
        mRecipeTitle = recipeTitle;
        mRecipeCategory = recipeCategory;
        mRecipeSummary = recipeSummary;
        mRecipeImage = recipeImage;
        mRecipeMethod = recipeMethod;
        mRecipeTime = recipeTime;
        mRecipeServings = recipeServings;
        mRecipeCredit = recipeCredit;
        mIngredients = ingredients;
    }
    public Recipe() {
    }

    public String getRecipeTitle() {
        return mRecipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        mRecipeTitle = recipeTitle;
    }

    public long getRecipeID() {
        return mRecipeID;
    }

    public void setRecipeID(long recipeID) {
        mRecipeID = recipeID;
    }

    public int getRecipeCategory() {
        return mRecipeCategory;
    }

    public void setRecipeCategory(int recipeCategory) {
        mRecipeCategory = recipeCategory;
    }

    public String getRecipeSummary() {
        return mRecipeSummary;
    }

    public void setRecipeSummary(String recipeSummary) {
        mRecipeSummary = recipeSummary;
    }

    public String getRecipeImage() {
        return mRecipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        mRecipeImage = recipeImage;
    }

    public String getRecipeMethod() {
        return mRecipeMethod;
    }

    public void setRecipeMethod(String recipeMethod) {
        mRecipeMethod = recipeMethod;
    }

    public String getRecipeTime() {
        return mRecipeTime;
    }

    public void setRecipeTime(String recipeTime) {
        mRecipeTime = recipeTime;
    }

    public int getRecipeServings() {
        return mRecipeServings;
    }

    public void setRecipeServings(int recipeServings) {
        mRecipeServings = recipeServings;
    }

    public String getRecipeCredit() {
        return mRecipeCredit;
    }

    public void setRecipeCredit(String recipeCredit) {
        mRecipeCredit = recipeCredit;
    }

    public List<Ingredient> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        mIngredients = ingredients;
    }

    public List<MPList> getMpLists() {
        return mMpLists;
    }

    public void setMpLists(List<MPList> mpLists) {
        mMpLists = mpLists;
    }
}
