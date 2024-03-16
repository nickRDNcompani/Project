package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;

public class ChildTaskList extends AppCompatActivity {
    ListView listView;
    SQLDBHelper sQLDBHelper;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_tasks_list);
        listView = findViewById(R.id.tasksListChild);
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
    }
}