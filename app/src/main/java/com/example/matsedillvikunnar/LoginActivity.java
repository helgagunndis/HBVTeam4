package com.example.matsedillvikunnar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matsedillvikunnar.EntityClass.MealPlan;
import com.example.matsedillvikunnar.EntityClass.User;
import com.example.matsedillvikunnar.Service.NotificationReceiver;
import com.example.matsedillvikunnar.Service.UserService;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.ui.Activities.CreateAccountActivity;
import com.example.matsedillvikunnar.ui.Activities.MyPageActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    private final String TAG ="LoginActivity";
    private static final String KEY_INDEX = "index";

    private final String USER_NAME="com.example.matsedillvikunnar.username";
    private final String SHARED_PREFS="shearedPrefs";

    private Button mButtonLogin;
    private TextView mTextViewUsername;
    private TextView mTextViewPassword;
    private TextView mTextViewSignUp;
    private UserService mUserService;

    private User user= new User();
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUserService = new UserService(this);
        createNotificationChannel();

        loadUsername();
        if (mUsername!=null){
            Intent i= new Intent(LoginActivity.this, MyPageActivity.class);
            startActivity(i);
        }

        mButtonLogin = (Button) findViewById(R.id.login_btn);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewUsername =(TextView) findViewById(R.id.login_username_new);
                mTextViewPassword =(TextView) findViewById(R.id.login_password_new);

                try {
                    login(mTextViewUsername.getText().toString(),mTextViewPassword.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //When user is logged in he gets notifications on Sunday evenings to remind him to make a mealplan
                Intent intent = new Intent(LoginActivity.this,NotificationReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(LoginActivity.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK,7); //user is notified on sunday evenings
                calendar.set(Calendar.HOUR_OF_DAY,8);
                calendar.set(Calendar.MINUTE,16);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            }
        });

        mTextViewSignUp =(TextView) findViewById(R.id.signup_link);
        mTextViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(i);
            }
        });
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "MealPlanReminderChannel";
            String description = "Channel for Meal Plan Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notification", name, importance);
            channel.setDescription(description);
        }
    }
    private void login(String username, String password) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("userPassword", password);
        mUserService.postUser(jsonObject, new NetworkCallback<User>() {
            @Override
            public void onSuccess(User result) {
                user= result;
                Log.d(TAG, "Notandi fannst " + user.getUsername() );
                saveUsername(user.getUsername());
                Intent i= new Intent(LoginActivity.this, MyPageActivity.class);
                startActivity(i);
                Toast.makeText(LoginActivity.this,R.string.managed_to_login_toast,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(String error) {
                Log.e(TAG, "Can't find user" + error);
                Toast.makeText(LoginActivity.this,"Gekk ekki að skrá inn",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveUsername(String username){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();

        editor.putString(USER_NAME, username);

        editor.apply();
    }
    public void loadUsername(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mUsername = sharedPreferences.getString(USER_NAME,null);
    }
}