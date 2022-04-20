package com.example.matsedillvikunnar.ui.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.matsedillvikunnar.EntityClass.User;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.Service.UserService;
import com.example.matsedillvikunnar.networking.NetworkCallback;

public class SetupActivity extends AppCompatActivity {
    private final String TAG ="SetupActivity";
    private final String USER_NAME="com.example.matsedillvikunnar.username";
    private final String SHARED_PREFS="shearedPrefs";

    private Button mButtonOmnivore;
    private Button mButtonPescatarian;
    private Button mButtonVegetarian;
    private Button mButtonVegan;
    private TextView mTextViewUsername;
    private UserService mUserService;
    private String mUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        mUserService = new UserService(this);
        loadUsername();

        mTextViewUsername = (TextView) findViewById(R.id.username_text);
        mTextViewUsername.setText(mUsername);

        mButtonOmnivore = (Button) findViewById(R.id.button_omnivore);
        mButtonPescatarian = (Button) findViewById(R.id.button_pescatarian);
        mButtonVegetarian = (Button) findViewById(R.id.button_vegetarian);
        mButtonVegan = (Button) findViewById(R.id.button_vegan);

        mButtonOmnivore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMealPlan(mUsername, "4");
            }
        });

        mButtonPescatarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMealPlan(mUsername, "3");
            }
        });
        mButtonVegetarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMealPlan(mUsername, "2");
            }

        });
        mButtonVegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMealPlan(mUsername, "1");
            }
        });
    }

    private void setMealPlan(String username, String category) {
        mUserService.categoryUser(username, category, new NetworkCallback<User>() {
            @Override
            public void onSuccess(User result) {
                Log.d(TAG, "gat náð í user " +result);
                Intent i= new Intent(SetupActivity.this, MyPageActivity.class);
                startActivity(i);
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
}