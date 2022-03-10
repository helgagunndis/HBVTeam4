package com.example.matsedillvikunnar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matsedillvikunnar.ui.Activities.CreateAccountActivity;
import com.example.matsedillvikunnar.ui.Activities.MyPageActivity;


public class MainActivity extends AppCompatActivity {

    private final String TAG ="MainActivity";
    private final String USER_NAME="com.example.matsedillvikunnar.username";

    private TextView mTextViewEmail;
    private TextView mTextViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Þetta er eins og hlustari bara snyrtilegra :)
    // er búinn að skilgreina í layout onClick sem handleLogin
    public void handleLogin(View v){
        // Netfang: admin
        // Lykilorð: admin
        mTextViewEmail =(TextView) findViewById(R.id.login_username);
        mTextViewPassword =(TextView) findViewById(R.id.login_password);

        if(mTextViewEmail.getText().toString().equals("admin") && mTextViewPassword.getText().toString().equals("admin")){
            Intent i= new Intent(this, MyPageActivity.class);
            i.putExtra(USER_NAME,mTextViewEmail.getText().toString());
            startActivity(i);
            Toast.makeText(MainActivity.this,R.string.managed_to_login_tost,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this,R.string.failed_to_login_tost,Toast.LENGTH_SHORT).show();
        }
    }

    public void  handleCreateAccount(View v){
        Intent i= new Intent(this, CreateAccountActivity.class);
        startActivity(i);
    }
}