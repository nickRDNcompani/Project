package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ParentPassword extends AppCompatActivity {

    Button doneButton;
    EditText editTextPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_password);
        doneButton = findViewById(R.id.doneButton);
        editTextPassword = findViewById(R.id.editTextPassword);
        SharedPreferences passwordPreference = getSharedPreferences("password preference2",MODE_PRIVATE);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordPreference.getString("password","").equals(editTextPassword.getText().toString()) && !passwordPreference.getString("password","").equals("")){
                    Intent intent5 = new Intent(ParentPassword.this,ParentMainMenu.class);
                    startActivity(intent5);
                }else{
                    Toast.makeText(ParentPassword.this, "неверный пароль!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
