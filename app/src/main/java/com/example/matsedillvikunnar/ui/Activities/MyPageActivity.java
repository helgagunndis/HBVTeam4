package com.example.matsedillvikunnar.ui.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.matsedillvikunnar.EntityClass.MPList;
import com.example.matsedillvikunnar.EntityClass.MealPlan;
import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.Service.UserService;
import com.example.matsedillvikunnar.lib.MyExpandableListAdapter;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.ui.Fragment.gridMealPlanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MyPageActivity extends AppCompatActivity {

    private final String TAG ="MyPageActivity";
    private final String USER_NAME="com.example.matsedillvikunnar.username";

    private RecyclerView mRecyclerView;
    private TextView mTextViewUsername;
    private String username;
    private List<MealPlan> mMealPlanList;

    SparseArray<MealPlan> mealPlan = new SparseArray<MealPlan>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);



        mTextViewUsername = (TextView) findViewById(R.id.username);
        Intent intent = getIntent();
        username= intent.getStringExtra(USER_NAME);
        mTextViewUsername.setText(username);
        findMealPlan(intent.getStringExtra(USER_NAME));



         //openMealPlanFragment();

        //bottom nav bar kallar á hin activity
       /* BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
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
        });*/

    }
    private void openMealPlanFragment(){
        Bundle bundle= new Bundle();
        bundle.putString("username",username);
        Fragment fragment = new gridMealPlanFragment();
        fragment.setArguments(bundle);
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
    }
    private void findMealPlan(String username) {
        UserService service = new UserService(this);
        service.getMealPlan(username, new NetworkCallback<List<MealPlan>>() {
            @Override
            public void onSuccess(List<MealPlan> result) {
                mMealPlanList = result;
                Log.d(TAG, "Tókst að ná í MealPlan");
                ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView_mealPlan);
                MyExpandableListAdapter adapter = new MyExpandableListAdapter(MyPageActivity.this, mMealPlanList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(String errorString) {
                Log.e(TAG, "Ekki hægt að ná í MealPlan: " + errorString);
            }
        });
    }

}