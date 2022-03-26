package com.example.matsedillvikunnar.ui.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matsedillvikunnar.EntityClass.User;
import com.example.matsedillvikunnar.LoginActivity;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.networking.NetworkManager;
import com.example.matsedillvikunnar.networking.Service;

import org.json.JSONException;
import org.json.JSONObject;


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
        Intent i= new Intent(CreateAccountActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void  handleSignup(View v){
        mTextViewUsername =(TextView) findViewById(R.id.login_username_new);
        mTextViewEmail = (TextView) findViewById(R.id.login_email_new);
        mTextViewPassword =(TextView) findViewById(R.id.login_password_new);
        if (mTextViewUsername.getText().toString().isEmpty() ||
                mTextViewEmail.getText().toString().isEmpty()||
                mTextViewPassword.getText().toString().isEmpty()) {
            Toast.makeText(CreateAccountActivity.this, "Vinsamlega fylltu í alla reiti", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
        signUp(mTextViewUsername.getText().toString(),mTextViewEmail.getText().toString(),mTextViewPassword.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void signUp(String username, String email, String password) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("userEmail", email);
        jsonObject.put("userPassword", password);

        Service service = new Service(this);
        service.postSignup(jsonObject, new NetworkCallback<User>() {
            @Override
            public void onSuccess(User result) {
                Log.d(TAG, "Tókst að setja notanda í gagnagrun" + result );
                // TODO : Að hann sé beint innskráður þegar hann sækjir um aðgang
                // Intent i= new Intent(CreateAccountActivity.this, SetupActivity.class);
                Intent i= new Intent(CreateAccountActivity.this, LoginActivity.class);
                startActivity(i);
                Toast.makeText(CreateAccountActivity.this,"Velkomin/nn nú getur skráð þig inn",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(String error) {
                Log.e(TAG, "Can't make a new account " + error);
                Toast.makeText(CreateAccountActivity.this,"Þetta notendarnafn er frátekið",Toast.LENGTH_SHORT).show();
            }
        });
    }
}