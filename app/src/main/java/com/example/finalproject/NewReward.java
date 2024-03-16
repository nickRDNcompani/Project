package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewReward extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        Button doneButtone;
        EditText rewardTitle, rewardCost;

        SQLDBHelper sQLDBHelper;
        SQLiteDatabase sqLiteDatabase;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_reward);

        doneButtone = findViewById(R.id.rewardDoneButton);
        rewardTitle = findViewById(R.id.editTextRewardTitle);
        rewardCost = findViewById(R.id.editTextRewardCost);

        sQLDBHelper = new SQLDBHelper(this);
        sqLiteDatabase = sQLDBHelper.getWritableDatabase();

        doneButtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(SQLDBHelper.COLUMN_TITLE_REWARDS,rewardTitle.getText().toString());
                contentValues.put(SQLDBHelper.COLUMN_COST_REWARDS,Integer.valueOf(rewardCost.getText().toString()));
                sqLiteDatabase.insert(SQLDBHelper.TABLE_NAME_REWARDS,null,contentValues);
                Intent intent = new Intent(NewReward.this,ParentRewardsList.class);
                startActivity(intent);
            }
        });


    }
}