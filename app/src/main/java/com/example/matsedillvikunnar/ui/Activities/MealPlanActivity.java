package com.example.matsedillvikunnar.ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.Service.RecipeService;
import com.example.matsedillvikunnar.databinding.ActivityMealplanBinding;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MealPlanActivity extends AppCompatActivity {
    private final String TAG ="MealPLanActivity";

    ActivityMealplanBinding binding;

    private static final String KEY_MEALPLAN = "mealplan";

    private int mMealPlanIndex = 0;
    private List mMealPlan;
    private TextView mTextViewUser;

    private CheckBox mCheckBoxMonday;
    private CheckBox mCheckBoxTuesday;
    private CheckBox mCheckBoxWednesday;
    private CheckBox mCheckBoxThursday;
    private CheckBox mCheckBoxFriday;
    private CheckBox mCheckBoxSaturday;
    private CheckBox mCheckBoxSunday;

    private TextView mTextViewMondayRecipe;
    private TextView mTextViewTuesdayRecipe;
    private TextView mTextViewWednesdayRecipe;
    private TextView mTextViewThursdayRecipe;
    private TextView mTextViewFridayRecipe;
    private TextView mTextViewSaturdayRecipe;
    private TextView mTextViewSundayRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealplan);

        mTextViewUser = (TextView) findViewById(R.id.user_text);
        mCheckBoxMonday = (CheckBox) findViewById(R.id.checkBox_monday);
        mCheckBoxTuesday = (CheckBox) findViewById(R.id.checkBox_tuesday);
        mCheckBoxWednesday = (CheckBox) findViewById(R.id.checkBox_wednesday);
        mCheckBoxThursday = (CheckBox) findViewById(R.id.checkBox_thursday);
        mCheckBoxFriday = (CheckBox) findViewById(R.id.checkBox_friday);
        mCheckBoxSaturday = (CheckBox) findViewById(R.id.checkBox_saturday);
        mCheckBoxSunday = (CheckBox) findViewById(R.id.checkBox_sunday);

        mTextViewMondayRecipe = (TextView) findViewById(R.id.monday_recipe_textView);
        mTextViewTuesdayRecipe = (TextView) findViewById(R.id.tuesday_recipe_textView);
        mTextViewWednesdayRecipe = (TextView) findViewById(R.id.wednesday_recipe_textView);
        mTextViewThursdayRecipe = (TextView) findViewById(R.id.thursday_recipe_textView);
        mTextViewFridayRecipe = (TextView) findViewById(R.id.friday_recipe_textView);
        mTextViewSaturdayRecipe = (TextView) findViewById(R.id.saturday_recipe_textView);
        mTextViewSundayRecipe = (TextView) findViewById(R.id.sunday_recipe_textView);

        if(savedInstanceState != null){
            mMealPlanIndex = savedInstanceState.getInt(KEY_MEALPLAN, 0);
        }

        // Hversu marga daga á að ná í og í hvaða flokk
        getMealPlan(7,2);

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

    public void  generateMealPlan(View v){
        getMealPlan(7,2);
    }

    private void getMealPlan(int days,int category){
        RecipeService service = new RecipeService(this);
        service.getMealPlan(category, days,new NetworkCallback<List<Recipe>>() {
            @Override
            public void onSuccess(List<Recipe> result) {
                if (mCheckBoxMonday.isChecked() == true ) {
                    mTextViewMondayRecipe.setText(result.get(0).getRecipeTitle());
                }
                else {
                    mTextViewMondayRecipe.setText("Enginn réttur valinn");
                }

                if (mCheckBoxTuesday.isChecked() == true ) {
                    mTextViewTuesdayRecipe.setText(result.get(1).getRecipeTitle());
                }
                else {
                    mTextViewTuesdayRecipe.setText("Enginn réttur valinn");
                }

                if (mCheckBoxWednesday.isChecked() == true ) {
                    mTextViewWednesdayRecipe.setText(result.get(2).getRecipeTitle());
                }
                else {
                    mTextViewWednesdayRecipe.setText("Enginn réttur valinn");
                }

                if (mCheckBoxThursday.isChecked() == true ) {
                    mTextViewThursdayRecipe.setText(result.get(3).getRecipeTitle());
                }
                else {
                    mTextViewThursdayRecipe.setText("Enginn réttur valinn");
                }

                if (mCheckBoxFriday.isChecked() == true ) {
                    mTextViewFridayRecipe.setText(result.get(4).getRecipeTitle());
                }
                else {
                    mTextViewFridayRecipe.setText("Enginn réttur valinn");
                }

                if (mCheckBoxSaturday.isChecked() == true ) {
                    mTextViewSaturdayRecipe.setText(result.get(5).getRecipeTitle());
                }
                else {
                    mTextViewSaturdayRecipe.setText("Enginn réttur valinn");
                }

                if (mCheckBoxSunday.isChecked() == true ) {
                    mTextViewSundayRecipe.setText(result.get(6).getRecipeTitle());
                }
                else {
                    mTextViewSundayRecipe.setText("Enginn réttur valinn");
                }

                Log.d(TAG, "Fann þessar uppskriftir" + result );
            }
            @Override
            public void onFailure(String error) {
                Log.e(TAG, "Ekki hægt að finna uppskrfitir" + error);
            }
        });
    }
}
