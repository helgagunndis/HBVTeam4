package com.example.matsedillvikunnar.ui.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.matsedillvikunnar.EntityClass.MealPlan;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.networking.NetworkManager;

public class MealPlanActivity extends AppCompatActivity {
    private final String TAG ="MealPLanActivity";
    private static final String KEY_MEALPLAN = "mealplan";

    //private int mRecipesIndex = 0;
    private MealPlan mMealPlan;
    private TextView mTextViewTEST2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealplan);

        mTextViewTEST2 = (TextView) findViewById(R.id.textView18);


        NetworkManager networkManager = NetworkManager.getInstance(this);
        networkManager.getMealPlan(new NetworkCallback<MealPlan>() {
            @Override
            public void onSuccess(MealPlan result) {
                mMealPlan = result;
                Log.d(TAG, "Successfully fetched MEALPLAN.");
                // TEST skrif Sinneps Lax því það er fyrsta uppskriftinn
                //mTextViewTEST2.setText(mMealPlan.get(0).getRecipeTitle());
            }
            @Override
            public void onFailure(String errorString) {
                Log.e(TAG, "Failed to get questions: " + errorString);
            }
        });

    }
}
