package com.example.journalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class JournalDataSource {

    private SQLiteDatabase database;
    private JournalDBHelper dbHelper;


    public JournalDataSource(Context context){
        dbHelper = new JournalDBHelper(context);
    }

    void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    void close() {
        dbHelper.close();
    }

    public boolean insertJournal(Journal j) {
        boolean didSucceed = false;
        try{
            ContentValues values = new ContentValues();
            values.put("checkin", j.getCheckIn());
            values.put("numberentry", j.getNumberEntry());

            didSucceed = database.insert("journal", null, values) > 0; //change

        }catch(Exception e){

        }
        return didSucceed;
    }

    public boolean updateJournal(Journal j) {
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            values.put("checkin", j.getCheckIn());
            values.put("numberentry", j.getNumberEntry());
            long id = (long) j.getJournalID(); //what is this

            didSucceed = database.update("journal", values, "_id =" + id, null) > 0;

        } catch (Exception e) {

        }
        return didSucceed;

    }

    public ArrayList<Journal> getJournal() {
        ArrayList<Journal> journal = new ArrayList<Journal>();
        try {
            String query = "Select * from journal";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Journal j = new Journal();
                j.setCheckIn(cursor.getString(1));
                j.setNumberEntry(cursor.getInt(2));
                journal.add(j);
                cursor.moveToNext();
            }

            cursor.close();
        } catch (Exception e) {

        }
        return journal;
    }

    public Journal getJournal(int id){
        Journal j = new Journal();
        try{
            String query = "Select * from journal where _id="+id;
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            j.setJournalID(id);
            j.setCheckIn(cursor.getString(1));
            j.setNumberEntry(cursor.getInt(2));
            cursor.close();
        }
        catch(Exception e){

            Log.d("**** GET JOURNAL ****", "FAILED");

        }
        return j;
    }
}
