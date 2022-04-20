package com.example.matsedillvikunnar.ui.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.example.matsedillvikunnar.EntityClass.MealPlan;
import com.example.matsedillvikunnar.LoginActivity;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.Service.UserService;
import com.example.matsedillvikunnar.lib.Adapters.MyPageListAdapter;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import java.util.List;

public class MyPageActivity extends AppCompatActivity {

    private final String TAG ="MyPageActivity";
    private final String USER_NAME="com.example.matsedillvikunnar.username";
    private final String SHARED_PREFS="shearedPrefs";

    private TextView mTextViewUsername;
    private Button mButtonLogout;
    private Button mButtonChangesCategory;
    private String mUsername;
    private List<MealPlan> mMealPlanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        loadUsername();
        if(mUsername == null){
            Log.d(TAG, "Náði ekki að finna username á shared prefs");
        }
        mTextViewUsername = (TextView) findViewById(R.id.username_meal_plan);
        mTextViewUsername.setText(mUsername);
        findMealPlan(mUsername);

        mButtonLogout = (Button) findViewById(R.id.logout_btn);
        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        mButtonChangesCategory = (Button) findViewById(R.id.category_btn);
        mButtonChangesCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MyPageActivity.this, SetupActivity.class);
                startActivity(i);
            }
        });

        //bottom nav bar kallar á hin activity
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.myPages:
                        break;
                    case R.id.newPlan:
                        Intent intent2 = new Intent(MyPageActivity.this, MealPlanActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.recipes:
                        Intent intent3 = new Intent(MyPageActivity.this, RecipesActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

    }

    private void findMealPlan(String username) {
        UserService service = new UserService(this);
        service.getMealPlan(username, new NetworkCallback<List<MealPlan>>() {
            @Override
            public void onSuccess(List<MealPlan> result) {
                mMealPlanList = result;
                Log.d(TAG, "Tókst að ná í MealPlan");
                ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView_mealPlan);
                MyPageListAdapter adapter = new MyPageListAdapter(MyPageActivity.this, mMealPlanList);
                listView.setAdapter(adapter);
            }
            @Override
            public void onFailure(String errorString) {
                Log.e(TAG, "Ekki hægt að ná í MealPlan: " + errorString);
            }
        });
    }

    public void loadUsername(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mUsername = sharedPreferences.getString(USER_NAME,null);
    }

    public void logout(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(USER_NAME, null);
        editor.apply();
        mUsername = sharedPreferences.getString(USER_NAME,null);

        Intent i= new Intent(MyPageActivity.this, LoginActivity.class);
        startActivity(i);
    }
}