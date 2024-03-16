package com.example.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLDBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "My database";

    public static final String TABLE_NAME_TASKS = "tasks";
    public static final String COLUMN_TITLE_TASKS = "titleTask";
    public static final String COLUMN_REWARD_TASKS = "rewardTask";
    public static final String COLUMN_IMPORTANCE_TASKS = "importanceTask";
    public static final String COLUMN_REPETABLE_TASKS = "repeatableTask";

    public static final String TABLE_NAME_REWARDS = "rewards";
    public static final String COLUMN_TITLE_REWARDS = "titleTask";
    public static final String COLUMN_COST_REWARDS = "costReward";

    public SQLDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String task = "CREATE TABLE " + TABLE_NAME_TASKS + " (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE_TASKS + " TEXT, " + COLUMN_REWARD_TASKS + " INTEGER, " + COLUMN_IMPORTANCE_TASKS + " TEXT," + COLUMN_REPETABLE_TASKS + " TEXT);" ;
        db.execSQL(task);
        String rewards = "CREATE TABLE " + TABLE_NAME_REWARDS + " (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE_REWARDS + " TEXT, " + COLUMN_COST_REWARDS + " INTEGER);" ;
        db.execSQL(rewards);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_REWARDS);
    }
}
