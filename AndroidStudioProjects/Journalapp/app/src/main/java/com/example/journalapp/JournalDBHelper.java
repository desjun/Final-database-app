package com.example.journalapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JournalDBHelper extends SQLiteOpenHelper {



    private static String DATABASE_NAME = "journal.db";
    private static int DATABASE_VERSION =1;


    public JournalDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlCommand = "create table journal (_id integer primary key autoincrement, "
                + "checkin text not null, "
                + "numberentry integer) ";

        sqLiteDatabase.execSQL(sqlCommand);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS journal");
        onCreate(sqLiteDatabase);
    }
}
