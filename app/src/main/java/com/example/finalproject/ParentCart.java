package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ParentCart extends AppCompatActivity {

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_cart);
        textView = findViewById(R.id.textView13);
        button = findViewById(R.id.clearButton);
        SharedPreferences cartPref = getSharedPreferences("cartPref",MODE_PRIVATE);
        SharedPreferences.Editor cartEditor = cartPref.edit();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartEditor.putString("cart","");
                cartEditor.commit();
                textView.setText("");

            }
        });
    }

    @Override
    protected void onResume() {
        textView = findViewById(R.id.textView13);
        SharedPreferences cartPref = getSharedPreferences("cartPref",MODE_PRIVATE);
        super.onResume();
        textView.setText(cartPref.getString("cart","корзина пуста"));
    }

}