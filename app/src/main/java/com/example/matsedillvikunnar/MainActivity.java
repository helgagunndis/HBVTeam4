package com.example.matsedillvikunnar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matsedillvikunnar.activities.MyPage;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleLogin(View v){
        //Netfang: admin
        //Lykilorð: admin
        TextView email =(TextView) findViewById(R.id.login_email);
        TextView password =(TextView) findViewById(R.id.login_password);

        if(email.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
            Intent i= new Intent(this, MyPage.class);
            i.putExtra("email",email.getText().toString());
            startActivity(i);
            Toast.makeText(MainActivity.this,"Tókst að innskrá",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this,"Netfang eða lykilorð vitlaust",Toast.LENGTH_SHORT).show();
        }
    }

    public void  handleSignup(View v){

    }

}