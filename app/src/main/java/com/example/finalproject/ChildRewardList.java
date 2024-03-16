package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;

public class ChildRewardList extends AppCompatActivity {
    SQLDBHelper sQLDBHelper;
    SQLiteDatabase sqLiteDatabase;
    ListView listView;

    TextView textView;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences coinPref = getSharedPreferences("coinPref", MODE_PRIVATE);
        textView.setText("   " + Integer.valueOf(coinPref.getInt("coin", 0)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_reward_list);

        textView = findViewById(R.id.textView18);

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

        SharedPreferences coinPref = getSharedPreferences("coinPref", MODE_PRIVATE);
        SharedPreferences.Editor coinEditor = coinPref.edit();
        SharedPreferences cartPref = getSharedPreferences("cartPref",MODE_PRIVATE);
        SharedPreferences.Editor cartEditor = cartPref.edit();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                if(coinPref.getInt("coin",0)>=linkedList.get(i).cost) {
                    sqLiteDatabase.delete(SQLDBHelper.TABLE_NAME_REWARDS, "titleTask = ?", new String[]{linkedList.get(i).title});
                    coinEditor.putInt("coin",coinPref.getInt("coin",0) - linkedList.get(i).cost);
                    cartEditor.putString("cart",cartPref.getString("cart","") + "\n" + linkedList.get(i).title);
                    linkedList.remove(i);
                    listForAdapter.remove(i);
                    simpleAdapter.notifyDataSetChanged();
                    coinEditor.commit();
                    cartEditor.commit();
                    textView.setText("   " + Integer.valueOf(coinPref.getInt("coin", 0)));
                    Toast.makeText(getApplicationContext(), "передано родителям!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "недостаточно монет!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}