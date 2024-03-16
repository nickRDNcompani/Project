package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;

public class ParentTasksList extends AppCompatActivity {

    ListView listView;
    Button button, buttonDeny, buttonDone, button2;
    SQLDBHelper sQLDBHelper;
    SQLiteDatabase sqLiteDatabase;
    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_tasks_list);

        listView = findViewById(R.id.tasksListChild);
        button = findViewById(R.id.buttonAddTask);
        buttonDeny = findViewById(R.id.buttonDeny);
        buttonDone = findViewById(R.id.buttonDone);
        button2 = findViewById(R.id.buttonDeleteTask);

        sQLDBHelper = new SQLDBHelper(this);
        sqLiteDatabase = sQLDBHelper.getWritableDatabase();

        LinkedList<Task> linkedList = new LinkedList<>();

        Cursor cursor2 = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLDBHelper.TABLE_NAME_TASKS, null, null);
        cursor2.moveToFirst();

        for (int i = 0; i < cursor2.getCount(); i++) {
            Task task = new Task(cursor2.getString(1),String.valueOf(cursor2.getInt(2)),cursor2.getString(3),cursor2.getString(4));
            linkedList.add(task);
            cursor2.moveToNext();
        }

        String[] keyArray = {"title", "reward", "importance", "repeatable", };
        int[] idArrays = {R.id.oneTaskTextView1, R.id.oneTaskTextView2, R.id.oneTaskTextView3, R.id.oneTaskTextView4,};

        LinkedList<HashMap<String, Object>> listForAdapter = new LinkedList<>();

        for (int i = 0; i < linkedList.size(); i++) {
            HashMap<String, Object> tasksMap = new HashMap<>();
            tasksMap.put(keyArray[0],linkedList.get(i).title);
            tasksMap.put(keyArray[1],"  " + linkedList.get(i).reward);
            tasksMap.put(keyArray[2],linkedList.get(i).importance);
            tasksMap.put(keyArray[3],linkedList.get(i).repeatable);
            listForAdapter.add(tasksMap);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listForAdapter,R.layout.one_task_list,keyArray,idArrays);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                SharedPreferences coinPref = getSharedPreferences("coinPref",MODE_PRIVATE);
                SharedPreferences.Editor coinEditor = coinPref.edit();

                if(value==0){
                    if(linkedList.get(i).repeatable.equals("")) {
                        sqLiteDatabase.delete(SQLDBHelper.TABLE_NAME_TASKS, "titleTask = ?", new String[]{linkedList.get(i).title});
                    }
                    coinEditor.putInt("coin",coinPref.getInt("coin",0) + Integer.valueOf(linkedList.get(i).reward));
                    coinEditor.commit();
                    linkedList.remove(i);
                    listForAdapter.remove(i);
                    simpleAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "отмеченно как выполненное", Toast.LENGTH_SHORT).show();

                }else if(value == 1){
                    if(linkedList.get(i).repeatable.equals("")) {
                        sqLiteDatabase.delete(SQLDBHelper.TABLE_NAME_TASKS, "titleTask = ?", new String[]{linkedList.get(i).title});
                    }
                    //sqLiteDatabase.delete(SQLDBHelper.TABLE_NAME_TASKS, "titleTask = ?", new String[]{linkedList.get(i).title});
                    coinEditor.putInt("coin",coinPref.getInt("coin",0) - Integer.valueOf(linkedList.get(i).reward));
                    coinEditor.commit();

                    linkedList.remove(i);
                    listForAdapter.remove(i);
                    simpleAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "омеченно как не выполненное", Toast.LENGTH_SHORT).show();
                }else if (value == 2){
                    sqLiteDatabase.delete(SQLDBHelper.TABLE_NAME_TASKS,"titleTask = ?",new String[]{linkedList.get(i).title});
                    linkedList.remove(i);
                    listForAdapter.remove(i);
                    simpleAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "удалено", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParentTasksList.this,NewTask.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = 2;
                Toast.makeText(getApplicationContext(), "нажмите на задачу", Toast.LENGTH_SHORT).show();
            }

        });
        buttonDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = 1;
                Toast.makeText(getApplicationContext(), "нажмите на задачу", Toast.LENGTH_SHORT).show();
            }
        });
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value  = 0;
                Toast.makeText(getApplicationContext(), "нажмите на задачу", Toast.LENGTH_SHORT).show();
            }
        });

    }
}