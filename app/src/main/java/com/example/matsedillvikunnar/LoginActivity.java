package com.example.matsedillvikunnar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matsedillvikunnar.EntityClass.User;
import com.example.matsedillvikunnar.Service.UserService;
import com.example.matsedillvikunnar.fragments.MyPageFragment;
import com.example.matsedillvikunnar.networking.NetworkManager;
import com.example.matsedillvikunnar.networking.NetworkCallback;
import com.example.matsedillvikunnar.networking.Service;
import com.example.matsedillvikunnar.ui.Activities.CreateAccountActivity;
import com.example.matsedillvikunnar.ui.Activities.MyPageActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private final String TAG ="LoginActivity";
    private static final String KEY_INDEX = "index";
    private final String USER_NAME="com.example.matsedillvikunnar.username";

    private Button mButtonLogin;
    private TextView mTextViewUsername;
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
                mTextViewUsername =(TextView) findViewById(R.id.login_username_new);
                mTextViewPassword =(TextView) findViewById(R.id.login_password_new);
                try {
                    login(mTextViewUsername.getText().toString(),mTextViewPassword.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                /* if(mTextViewEmail.getText().toString().equals("admin") && mTextViewPassword.getText().toString().equals("admin")){
                    Intent i= new Intent(LoginActivity.this, MyPageActivity.class);
                    i.putExtra(USER_NAME,mTextViewEmail.getText().toString());
                    startActivity(i);
                    Toast.makeText(LoginActivity.this,R.string.managed_to_login_toast,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this,R.string.failed_to_login_toast,Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        mTextViewSignUp =(TextView) findViewById(R.id.signup_link);
        mTextViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i= new Intent(LoginActivity.this, RecipesActivity.class);
                Intent i= new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(i);

            }
        });


    }
    private void login(String username, String password) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("userPassword", password);

        UserService service = new UserService(this);
        service.postUser(jsonObject, new NetworkCallback<User>() {
            @Override
            public void onSuccess(User result) {
                Log.d(TAG, "Notandi fannst" + result );
                Intent i= new Intent(LoginActivity.this, MyPageActivity.class);
                i.putExtra(USER_NAME,result.getUsername());
                startActivity(i);
                Toast.makeText(LoginActivity.this,R.string.managed_to_login_toast,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(String error) {
                Log.e(TAG, "Can't find user" + error);
                Toast.makeText(LoginActivity.this,"Gékk ekki að skrá inn",Toast.LENGTH_SHORT).show();
            }
        });
    }

}