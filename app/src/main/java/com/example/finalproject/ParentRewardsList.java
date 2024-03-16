package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;

public class ParentRewardsList extends AppCompatActivity {

    Button button;
    ListView listView;
    SQLDBHelper sQLDBHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_reward_list);

        button = findViewById(R.id.buttonAddReward);
        listView = findViewById(R.id.rewardListChild);

        sQLDBHelper = new SQLDBHelper(this);
        sqLiteDatabase = sQLDBHelper.getWritableDatabase();

        LinkedList<Reward> linkedList = new LinkedList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLDBHelper.TABLE_NAME_REWARDS, null, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Reward reward = new Reward(cursor.getString(1),cursor.getInt(2));
            linkedList.add(reward);
            cursor.moveToNext();
        }
        String[] keyArray = {"title", "cost"};
        int[] idArrays = {R.id.oneRewardTextView1, R.id.oneRewardTextView2,};

        LinkedList<HashMap<String, Object>> listForAdapter = new LinkedList<>();

        for (int i = 0; i < linkedList.size(); i++) {
            HashMap<String, Object> rewardMap = new HashMap<>();
            rewardMap.put(keyArray[0],linkedList.get(i).title);
            rewardMap.put(keyArray[1],linkedList.get(i).cost);
            listForAdapter.add(rewardMap);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listForAdapter,R.layout.one_reward_list,keyArray,idArrays);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                    sqLiteDatabase.delete(SQLDBHelper.TABLE_NAME_REWARDS,"titleTask = ?",new String[]{linkedList.get(i).title});
                    linkedList.remove(i);
                    listForAdapter.remove(i);
                    simpleAdapter.notifyDataSetChanged();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParentRewardsList.this, NewReward.class);
                startActivity(intent);
            }
        });

    }
}