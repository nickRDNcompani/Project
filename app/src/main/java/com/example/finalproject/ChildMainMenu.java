package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChildMainMenu extends AppCompatActivity {

    TextView textView;
    Button buttonTask,buttonRewards;
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences coinPref = getSharedPreferences("coinPref", MODE_PRIVATE);
        textView.setText("   " + Integer.valueOf(coinPref.getInt("coin", 0)));
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_main_menu);

        textView = findViewById(R.id.textView8);
        buttonTask = findViewById(R.id.buttonTaskListChild);
        buttonRewards = findViewById(R.id.buttonRewardListChild);

        buttonTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChildMainMenu.this,ChildTaskList.class);
                startActivity(intent);
            }
        });
        buttonRewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ChildMainMenu.this,ChildRewardList.class);
                startActivity(intent2);
            }
        });

    }
}
