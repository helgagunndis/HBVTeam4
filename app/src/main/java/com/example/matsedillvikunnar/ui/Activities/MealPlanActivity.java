package com.example.matsedillvikunnar.ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.databinding.ActivityMealplanBinding;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.networking.NetworkManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONObject;

import java.util.List;

public class MealPlanActivity extends AppCompatActivity {
    private final String TAG ="MealPLanActivity";

    ActivityMealplanBinding binding;

    private static final String KEY_MEALPLAN = "mealplan";

    private int mMealPlanIndex = 0;
    private List mMealPlan;
    private TextView mTextViewTEST2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealplan);

        mTextViewTEST2 = (TextView) findViewById(R.id.textView18);


        if(savedInstanceState != null){
            mMealPlanIndex = savedInstanceState.getInt(KEY_MEALPLAN, 0);
        }
        JSONObject jsonObject = new JSONObject();
        NetworkManager networkManager = NetworkManager.getInstance(this);
        networkManager.getMealPlan(jsonObject, new NetworkCallback<List>() {

            @Override
            public void onSuccess(List result) {


                mMealPlan = result;
                Log.d(TAG, "Successfully fetched MEALPLAN.");

                //Toast.makeText(MealPlanActivity.this,"tókst að ná í mealplan",Toast.LENGTH_SHORT).show();
                // TEST skrif Sinneps Lax því það er fyrsta uppskriftinn
                //MealPlan mpList = mMealPlan.getMpLists();
                //mTextViewTEST2.setText(mMealPlan.getMpLists().getRecipe(0));
            }
            @Override
            public void onFailure(String errorString) {
                mTextViewTEST2.setText("Virkar ekki");
                Log.e(TAG, "Failed to get questions: " + errorString);
            }
        });

        //bottom nav bar kallar á hin activity
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.myPages:
                        Intent intent1 = new Intent(MealPlanActivity.this, MyPageActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.newPlan:
                        break;
                    case R.id.recipes:
                        Intent intent3 = new Intent(MealPlanActivity.this, RecipesActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });
    }
}
