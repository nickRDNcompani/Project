package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class ParentMainMenu extends AppCompatActivity {

    Button buttonTasks;
    Button buttonMarket;
    Button buttonCoins;
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
        setContentView(R.layout.parent_main_menu);
        buttonMarket = findViewById(R.id.buttonRewardListChild);
        buttonTasks = findViewById(R.id.buttonTaskListChild);
        buttonCoins = findViewById(R.id.buttonCoins);
        textView = findViewById(R.id.textView6);

        buttonMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParentMainMenu.this, ParentRewardMenu.class);
                startActivity(intent);
            }
        });
        buttonTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ParentMainMenu.this,ParentTasksList.class);
                startActivity(intent2);
            }
        });
        buttonCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(ParentMainMenu.this,ParentCoins.class);
                startActivity(intent3);
            }
        });
    }
}
