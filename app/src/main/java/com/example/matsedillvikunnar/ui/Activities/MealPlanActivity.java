package com.example.matsedillvikunnar.ui.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.matsedillvikunnar.EntityClass.MealPlan;
import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.example.matsedillvikunnar.EntityClass.User;
import com.example.matsedillvikunnar.LoginActivity;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.Service.RecipeService;
import com.example.matsedillvikunnar.Service.UserService;
import com.example.matsedillvikunnar.lib.Adapters.MyPageListAdapter;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MealPlanActivity extends AppCompatActivity {
    private final String TAG ="MealPLanActivity";
    private final String USER_NAME="com.example.matsedillvikunnar.username";
    private final String SHARED_PREFS="shearedPrefs";

    // ActivityMealplanBinding binding;

    private static final String KEY_MEALPLAN = "mealplan";

    private int mMealPlanIndex = 0;
    private List mMealPlan;
    private UserService mUserService;
    private RecipeService mRecipeService;
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

    private String mUsername;
    private String mUserCategory= "4"; // by default
    private Button mButtonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealplan);
        mUserService = new UserService(this);
        mRecipeService = new RecipeService(this);

        if(savedInstanceState != null){
            mMealPlanIndex = savedInstanceState.getInt(KEY_MEALPLAN, 0);
        }

        // n?? ?? user fr?? sharedPreferences
        loadUsername();
        // n?? ?? notanda til ??ess a?? f?? ?? hva??a category hann vill a?? mealPlani?? sitt s?? ??.
        findUser(mUsername);

        getMealPlan(7, Integer.parseInt(mUserCategory));

        mTextViewUser = (TextView) findViewById(R.id.username_text);
        mTextViewUser.setText(mUsername);

        mButtonConfirm = (Button) findViewById(R.id.confirm_mp_btn);
        mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Athuga vi?? hva??a daga er haka?? vi?? og bara setja ???? daga inn?? mealplani??
                List savedMealPlan = new ArrayList<>();
                if (mCheckBoxMonday.isChecked() == true) { savedMealPlan.add(mMealPlan.get(0)); }
                else { savedMealPlan.add(null); }
                if (mCheckBoxTuesday.isChecked() == true) { savedMealPlan.add(mMealPlan.get(1)); }
                else { savedMealPlan.add(null); }
                if (mCheckBoxWednesday.isChecked() == true) { savedMealPlan.add(mMealPlan.get(2)); }
                else { savedMealPlan.add(null); }
                if (mCheckBoxThursday.isChecked() == true) { savedMealPlan.add(mMealPlan.get(3)); }
                else { savedMealPlan.add(null); }
                if (mCheckBoxFriday.isChecked() == true) { savedMealPlan.add(mMealPlan.get(4)); }
                else { savedMealPlan.add(null); }
                if (mCheckBoxSaturday.isChecked() == true) { savedMealPlan.add(mMealPlan.get(5)); }
                else { savedMealPlan.add(null); }
                if (mCheckBoxSunday.isChecked() == true) { savedMealPlan.add(mMealPlan.get(6)); }
                else { savedMealPlan.add(null); }

                mRecipeService.saveMealPlan(savedMealPlan,mUsername, new NetworkCallback<MealPlan>() {
                    @Override
                    public void onSuccess(MealPlan result) {
                        Log.d(TAG, "T??kst a?? n?? ?? MealPlan"+ result);
                        Toast.makeText(MealPlanActivity.this,"Matse??ill hefur veri?? ??tb??inn. ???? getur n??lgast hann undir m??nar si??ur.",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(String errorString) {
                        Log.e(TAG, "Ekki h??gt a?? n?? ?? MealPlan: " + errorString);
                    }
                });
            }
        });

        mCheckBoxMonday = (CheckBox) findViewById(R.id.checkBox_monday);
        mCheckBoxMonday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                viewMealPlan(mMealPlan);
            }
        });
        mCheckBoxTuesday = (CheckBox) findViewById(R.id.checkBox_tuesday);
        mCheckBoxTuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                viewMealPlan(mMealPlan);
            }
        });
        mCheckBoxWednesday = (CheckBox) findViewById(R.id.checkBox_wednesday);
        mCheckBoxWednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                viewMealPlan(mMealPlan);
            }
        });
        mCheckBoxThursday = (CheckBox) findViewById(R.id.checkBox_thursday);
        mCheckBoxThursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                viewMealPlan(mMealPlan);
            }
        });
        mCheckBoxFriday = (CheckBox) findViewById(R.id.checkBox_friday);
        mCheckBoxFriday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                viewMealPlan(mMealPlan);
            }
        });
        mCheckBoxSaturday = (CheckBox) findViewById(R.id.checkBox_saturday);
        mCheckBoxSaturday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                viewMealPlan(mMealPlan);
            }
        });
        mCheckBoxSunday = (CheckBox) findViewById(R.id.checkBox_sunday);
        mCheckBoxSunday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                viewMealPlan(mMealPlan);
            }
        });

        mTextViewMondayRecipe = (TextView) findViewById(R.id.monday_recipe_textView);
        mTextViewTuesdayRecipe = (TextView) findViewById(R.id.tuesday_recipe_textView);
        mTextViewWednesdayRecipe = (TextView) findViewById(R.id.wednesday_recipe_textView);
        mTextViewThursdayRecipe = (TextView) findViewById(R.id.thursday_recipe_textView);
        mTextViewFridayRecipe = (TextView) findViewById(R.id.friday_recipe_textView);
        mTextViewSaturdayRecipe = (TextView) findViewById(R.id.saturday_recipe_textView);
        mTextViewSundayRecipe = (TextView) findViewById(R.id.sunday_recipe_textView);

        //bottom nav bar kallar ?? hin activity
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

    private void viewMealPlan(List<Recipe> mealplan) {

        if (mCheckBoxMonday.isChecked() == true ) {
            mTextViewMondayRecipe.setText(mealplan.get(0).getRecipeTitle());
            mTextViewMondayRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = String.valueOf(mealplan.get(0).getRecipeID());
                    Intent intent =  new Intent(getApplicationContext(), RecipeActivity.class);
                    intent.putExtra("individual_recipe", s);
                    startActivity(intent);
                }
            });
        }
        else { mTextViewMondayRecipe.setText("Enginn r??ttur valinn"); }

        if (mCheckBoxTuesday.isChecked() == true ) {
            mTextViewTuesdayRecipe.setText(mealplan.get(1).getRecipeTitle());
            mTextViewTuesdayRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = String.valueOf(mealplan.get(1).getRecipeID());
                    Intent intent =  new Intent(getApplicationContext(), RecipeActivity.class);
                    intent.putExtra("individual_recipe", s);
                    startActivity(intent);
                }
            });
        }
        else { mTextViewTuesdayRecipe.setText("Enginn r??ttur valinn"); }

        if (mCheckBoxWednesday.isChecked() == true ) {
            mTextViewWednesdayRecipe.setText(mealplan.get(2).getRecipeTitle());
            mTextViewWednesdayRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = String.valueOf(mealplan.get(2).getRecipeID());
                    Intent intent =  new Intent(getApplicationContext(), RecipeActivity.class);
                    intent.putExtra("individual_recipe", s);
                    startActivity(intent);
                }
            });
        }
        else { mTextViewWednesdayRecipe.setText("Enginn r??ttur valinn"); }

        if (mCheckBoxThursday.isChecked() == true ) {
            mTextViewThursdayRecipe.setText(mealplan.get(3).getRecipeTitle());
            mTextViewThursdayRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = String.valueOf(mealplan.get(3).getRecipeID());
                    Intent intent =  new Intent(getApplicationContext(), RecipeActivity.class);
                    intent.putExtra("individual_recipe", s);
                    startActivity(intent);
                }
            });
        }
        else { mTextViewThursdayRecipe.setText("Enginn r??ttur valinn"); }

        if (mCheckBoxFriday.isChecked() == true ) {
            mTextViewFridayRecipe.setText(mealplan.get(4).getRecipeTitle());
            mTextViewFridayRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = String.valueOf(mealplan.get(4).getRecipeID());
                    Intent intent =  new Intent(getApplicationContext(), RecipeActivity.class);
                    intent.putExtra("individual_recipe", s);
                    startActivity(intent);
                }
            });
        }
        else { mTextViewFridayRecipe.setText("Enginn r??ttur valinn"); }

        if (mCheckBoxSaturday.isChecked() == true ) {
            mTextViewSaturdayRecipe.setText(mealplan.get(5).getRecipeTitle());
            mTextViewSaturdayRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = String.valueOf(mealplan.get(5).getRecipeID());
                    Intent intent =  new Intent(getApplicationContext(), RecipeActivity.class);
                    intent.putExtra("individual_recipe", s);
                    startActivity(intent);
                }
            });
        }
        else { mTextViewSaturdayRecipe.setText("Enginn r??ttur valinn"); }

        if (mCheckBoxSunday.isChecked() == true ) {
            mTextViewSundayRecipe.setText(mealplan.get(6).getRecipeTitle());
            mTextViewSundayRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = String.valueOf(mealplan.get(6).getRecipeID());
                    Intent intent =  new Intent(getApplicationContext(), RecipeActivity.class);
                    intent.putExtra("individual_recipe", s);
                    startActivity(intent);
                }
            });
        }
        else { mTextViewSundayRecipe.setText("Enginn r??ttur valinn"); }
    }

    private void getMealPlan(int days,int category) {
        RecipeService service = new RecipeService(this);
        service.getMealPlan(category, days,new NetworkCallback<List<Recipe>>() {
            @Override
            public void onSuccess(List<Recipe> result) {
                mMealPlan = result;
                viewMealPlan(mMealPlan);
                Log.d(TAG, "Fann ??essar uppskriftir" + result );
            }
            @Override
            public void onFailure(String error) {
                Log.e(TAG, "Ekki h??gt a?? finna uppskrfitir" + error);
            }
        });
    }

    public void loadUsername() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mUsername = sharedPreferences.getString(USER_NAME,null);
    }

    private void findUser(String username) {
        mUserService.findUser(username, new NetworkCallback<User>() {
            @Override
            public void onSuccess(User result) {
                Log.d(TAG, "gat n???? ?? user " +result.getUserCategory());
                mUserCategory = result.getUserCategory();
            }
            @Override
            public void onFailure(String errorString) {
                Log.e(TAG, "Ekki h??gt a?? n?? ?? MealPlan: " + errorString);
            }
        });
    }
}