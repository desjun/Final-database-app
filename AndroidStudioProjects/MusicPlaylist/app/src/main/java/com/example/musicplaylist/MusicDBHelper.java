package com.example.musicplaylist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MusicDBHelper extends SQLiteOpenHelper {


    private static String DATABASE_NAME = "music.db";
    private static int DATABASE_VERSION =1;


    public MusicDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlCommand = "create table music (_id integer primary key autoincrement, "
                + "songname text not null, "
                + "artistname text, "
                + "releaseyear integer) ";

        sqLiteDatabase.execSQL(sqlCommand);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS music");
        onCreate(sqLiteDatabase);
    }
}
