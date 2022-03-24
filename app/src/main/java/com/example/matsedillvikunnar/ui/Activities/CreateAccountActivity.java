package com.example.matsedillvikunnar.ui.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matsedillvikunnar.LoginActivity;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.networking.NetworkManager;


public class CreateAccountActivity extends AppCompatActivity {

    private final String TAG ="CreateAccountActivity";
    private static final String KEY_ACCOUNT = "createAccount";
    private int mAccountIndex = 0;

    private Button mButtonSignup;
    private TextView mTextViewUsername;
    private TextView mTextViewEmail;
    private TextView mTextViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        if(savedInstanceState != null){
            mAccountIndex = savedInstanceState.getInt(KEY_ACCOUNT, 0);
        }
    }

    public void backToLogin (View v){
        Intent i= new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void  handleSignup(View v){
     // TODO: athuga hvort öll skilyrði fyrir nýjan notenda sé uppfyllt -> skrá nýjan notenda
        mTextViewUsername =(TextView) findViewById(R.id.login_username_new);
        mTextViewEmail = (TextView) findViewById(R.id.login_email_new);
        mTextViewPassword =(TextView) findViewById(R.id.login_password_new);
        if (mTextViewUsername.getText().toString().isEmpty() ||
                mTextViewEmail.getText().toString().isEmpty()||
                mTextViewPassword.getText().toString().isEmpty()) {
            Toast.makeText(CreateAccountActivity.this, "Vinsamlega fylltu í alla reiti", Toast.LENGTH_SHORT).show();
            return;
        }
        NetworkManager networkManager = NetworkManager.getInstance(this);
        networkManager.createUser(mTextViewUsername.getText().toString(),mTextViewEmail.getText().toString(),mTextViewPassword.getText().toString());
    }
}