package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ParentRewardMenu extends AppCompatActivity {

    Button button1,button2;
    TextView textView;
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
        setContentView(R.layout.parent_reward_menu);
        button1 = findViewById(R.id.buttonTaskListChild);
        button2 = findViewById(R.id.childsPurchase);
        textView = findViewById(R.id.textView9);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParentRewardMenu.this,ParentRewardsList.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ParentRewardMenu.this,ParentCart.class);
                startActivity(intent2);
            }
        });
    }

}