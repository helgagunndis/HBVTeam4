package com.example.matsedillvikunnar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matsedillvikunnar.EntityClass.Recipe;
import com.example.matsedillvikunnar.EntityClass.User;
import com.example.matsedillvikunnar.fragments.MyPageFragment;
import com.example.matsedillvikunnar.networking.NetworkManager;
import com.example.matsedillvikunnar.ui.Activities.CreateAccountActivity;
import com.example.matsedillvikunnar.ui.Activities.MyPageActivity;

import java.util.List;

import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.networking.NetworkManager;
import com.example.matsedillvikunnar.ui.Activities.RecipesActivity;


public class LoginActivity extends AppCompatActivity {

    private final String TAG ="MainActivity";
    private static final String KEY_INDEX = "index";
    private final String USER_NAME="com.example.matsedillvikunnar.username";

    private Button mButtonLogin;
    private TextView mTextViewEmail;
    private TextView mTextViewPassword;

    private TextView mTextViewSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mButtonLogin = (Button) findViewById(R.id.login_btn);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewEmail =(TextView) findViewById(R.id.login_username);
                mTextViewPassword =(TextView) findViewById(R.id.login_password);

                if(mTextViewEmail.getText().toString().equals("admin") && mTextViewPassword.getText().toString().equals("admin")){
                    Intent i= new Intent(LoginActivity.this, MyPageActivity.class);
                    i.putExtra(USER_NAME,mTextViewEmail.getText().toString());
                    startActivity(i);
                    Toast.makeText(LoginActivity.this,R.string.managed_to_login_toast,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this,R.string.failed_to_login_toast,Toast.LENGTH_SHORT).show();
                }
            }
        });

        mTextViewSignUp =(TextView) findViewById(R.id.signup_link);
        mTextViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(LoginActivity.this, RecipesActivity.class);
                // Intent i= new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(i);

            }
        });
    }

}