package com.example.matsedillvikunnar.EntityClass;


import com.google.gson.annotations.SerializedName;

public class IngredientInfo {
    @SerializedName("id")
    private long mId;

    @SerializedName("ingredientName")
    private String mIngredientName;

    public IngredientInfo( String ingredientName) {
        mIngredientName = ingredientName;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getIngredientName() {
        return mIngredientName;
    }

    public void setIngredientName(String ingredientName) {
        mIngredientName = ingredientName;
    }
}
