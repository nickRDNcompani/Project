package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ParentPasswordFirst extends AppCompatActivity {

    Button doneButton;
    EditText password;
    EditText confirmPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_password_first);

        doneButton = findViewById(R.id.doneButton2);
        password = findViewById(R.id.editTextPassword2);
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
        SharedPreferences passwordPreference = getSharedPreferences("password preference2",MODE_PRIVATE);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals(confirmPassword.getText().toString())){
                    SharedPreferences.Editor passwordEditor = passwordPreference.edit();
                    passwordEditor.putString("password",password.getText().toString());
                    passwordEditor.commit();
                    Intent intent4 = new Intent(ParentPasswordFirst.this,ParentMainMenu.class);
                    startActivity(intent4);
                }else{
                    Toast.makeText(ParentPasswordFirst.this, "проверьте пожалуйста правильность паролей!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
