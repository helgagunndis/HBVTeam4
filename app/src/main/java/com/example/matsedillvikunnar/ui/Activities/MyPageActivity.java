package com.example.matsedillvikunnar.ui.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.matsedillvikunnar.MainActivity;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.databinding.ActivityMainBinding;
import com.example.matsedillvikunnar.databinding.ActivityMyPageBinding;
import com.example.matsedillvikunnar.fragments.MyPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MyPageActivity extends AppCompatActivity {

    private final String TAG ="MyPageActivity";
    private final String USER_NAME="com.example.matsedillvikunnar.username";

    private TextView mTextViewUsername;
    ActivityMyPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        mTextViewUsername = (TextView) findViewById(R.id.username);

        Intent intent = getIntent();
        mTextViewUsername.setText(intent.getStringExtra(USER_NAME));



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
}