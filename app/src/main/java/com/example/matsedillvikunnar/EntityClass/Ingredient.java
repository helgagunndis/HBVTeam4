package com.example.matsedillvikunnar.EntityClass;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ingredient {
    @SerializedName("id")
    private long mId;

    @SerializedName("amount")
    private String mAmount;

    // TODO : bæta við unit á amount ?

    @SerializedName("recipe")
    private Recipe mRecipe;
    @SerializedName("ingredientInfo")
    private IngredientInfo mIngredientInfo;

    public Ingredient(String amount, Recipe recipe, IngredientInfo ingredientInfo) {
        mAmount = amount;
        mRecipe = recipe;
        mIngredientInfo = ingredientInfo;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        mAmount = amount;
    }

    public Recipe getRecipe() {
        return mRecipe;
    }

    public void setRecipe(Recipe recipe) {
        mRecipe = recipe;
    }

    public IngredientInfo getIngredientInfo() {
        return mIngredientInfo;
    }

    public void setIngredientInfo(IngredientInfo ingredientInfo) {
        mIngredientInfo = ingredientInfo;
    }
}
