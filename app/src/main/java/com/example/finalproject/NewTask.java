package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class NewTask extends AppCompatActivity {

    Button taskDone;
    EditText taskName, taskReward, taskRepetable;
    CheckBox checkBoxImportance, checkBoxRepetable;
    SQLDBHelper sQLDBHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);
        taskDone = findViewById(R.id.taskDoneButton);
        taskName = findViewById(R.id.editTextNewTaskName);
        taskReward = findViewById(R.id.editTextTaskReward);
        taskRepetable = findViewById(R.id.editTextRepeatableTask);
        checkBoxImportance = findViewById(R.id.checkBoxImportance);
        checkBoxRepetable = findViewById(R.id.checkBoxRepeatable);

        sQLDBHelper = new SQLDBHelper(this);
        sqLiteDatabase = sQLDBHelper.getWritableDatabase();

        checkBoxRepetable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            int visibility = 0;
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(visibility == 0){
                        taskRepetable.setVisibility(View.VISIBLE);
                        visibility = 1;
                    }else{
                        taskRepetable.setVisibility(View.GONE);
                        visibility = 0;
                    }

                }
        });

        taskDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(SQLDBHelper.COLUMN_TITLE_TASKS,taskName.getText().toString());
                contentValues.put(SQLDBHelper.COLUMN_REWARD_TASKS,taskReward.getText().toString());
                String i;
                if(checkBoxImportance.isChecked()){
                    i = "важо!";
                }else{
                    i = "не очень важно";
                }
                contentValues.put(SQLDBHelper.COLUMN_IMPORTANCE_TASKS,i);
                contentValues.put(SQLDBHelper.COLUMN_REPETABLE_TASKS,taskRepetable.getText().toString());
                sqLiteDatabase.insert(SQLDBHelper.TABLE_NAME_TASKS,null,contentValues);
                Intent intent = new Intent(NewTask.this,ParentTasksList.class);
                startActivity(intent);
            }
        });

    }
}