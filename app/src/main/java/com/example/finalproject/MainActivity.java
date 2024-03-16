package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     Button buttonParents;
     Button buttonChilds;
     TextView textView;
     Button buttonAbout;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences coinPref = getSharedPreferences("coinPref", MODE_PRIVATE);
        textView.setText("   " + Integer.valueOf(coinPref.getInt("coin", 0)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonChilds = findViewById(R.id.child);
        buttonParents = findViewById(R.id.parent);
        textView = findViewById(R.id.textView12);
        buttonAbout = findViewById(R.id.aboutButton);


        SharedPreferences password = getSharedPreferences("password preference2",MODE_PRIVATE);

        buttonParents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getString("password","").equals("")){
                    Intent intent1 = new Intent(MainActivity.this, ParentPasswordFirst.class);
                    startActivity(intent1);
                }else {
                    Intent intent2 = new Intent(MainActivity.this, ParentPassword.class);
                    startActivity(intent2);
                }
            }
        });

        buttonChilds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getString("password","").equals("")){
                    Intent intent1 = new Intent(MainActivity.this, ParentPasswordFirst.class);
                    startActivity(intent1);
                }else {
                    Intent intent3 = new Intent(MainActivity.this, ChildMainMenu.class);
                    startActivity(intent3);
                }
            }
        });
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,About.class);
                startActivity(intent);
            }
        });
    }

}