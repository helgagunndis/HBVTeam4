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

        TextView email =(TextView) findViewById(R.id.login_email);
        TextView password =(TextView) findViewById(R.id.login_password);
        Button loginbtn = (Button) findViewById(R.id.login_btn);

        //Netfang: admin
        //Lykilorð: admin
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    startActivity(new Intent(MainActivity.this, MyPage.class));
                    Toast.makeText(MainActivity.this,"Tókst að innskrá",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this,"Netfang eða lykilorð vitlaust",Toast.LENGTH_SHORT).show();
            }
        });


    }
}