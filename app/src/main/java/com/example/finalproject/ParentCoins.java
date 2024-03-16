package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ParentCoins extends AppCompatActivity {

    EditText minus, plus;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_coins);
        minus = findViewById(R.id.minusCoins);
        plus = findViewById(R.id.plusCoins);
        done = findViewById(R.id.doneButtonCoins);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences coinPref = getSharedPreferences("coinPref", MODE_PRIVATE);
                SharedPreferences.Editor coinEditor = coinPref.edit();
                coinEditor.putInt("coin",coinPref.getInt("coin",0) - Integer.valueOf(minus.getText().toString()));
                coinEditor.putInt("coin",coinPref.getInt("coin",0) + Integer.valueOf(plus.getText().toString()));
                Intent intent2 = new Intent(ParentCoins.this,ParentMainMenu.class);
                startActivity(intent2);
            }
        });
    }

}