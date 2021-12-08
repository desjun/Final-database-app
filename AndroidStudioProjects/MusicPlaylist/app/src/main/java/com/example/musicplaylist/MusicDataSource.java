package com.example.musicplaylist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MusicDataSource {

    private SQLiteDatabase database;
    private MusicDBHelper dbHelper;


    public MusicDataSource(Context context){
        dbHelper = new MusicDBHelper(context);
    }

    void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    void close() {
        dbHelper.close();
    }

    public boolean insertMusic(Music m) {
        boolean didSucceed = false;
        try{
            ContentValues values = new ContentValues();
            values.put("songname", m.getSongName());
            values.put("artistname", m.getArtistName());
            values.put("releaseyear", m.getReleaseYear());

            didSucceed = database.insert("music", null, values) > 0; //change

        }catch(Exception e){

        }
        return didSucceed;
    }

    public boolean updateMusic(Music m) {
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            values.put("songname", m.getSongName());
            values.put("artistname", m.getArtistName());
            values.put("releaseyear", m.getReleaseYear());
            long id = (long) m.getMusicID(); //what is this

            didSucceed = database.update("music", values, "_id =" + id, null) > 0;

        } catch (Exception e) {

        }
        return didSucceed;

    }

    public ArrayList<Music> getMusic() {
        ArrayList<Music> music = new ArrayList<Music>();
        try {
            String query = "Select * from music";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Music m = new Music();
                m.setSongName(cursor.getString(1));
                m.setArtistName(cursor.getString(2));
                m.setReleaseYear(cursor.getInt(3));
                music.add(m);
                cursor.moveToNext();
            }

            cursor.close();
        } catch (Exception e) {

        }
        return music;
    }

    public Music getMusic(int id){
        Music m = new Music();
        try{
            String query = "Select * from music where _id="+id;
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            m.setMusicID(id);
            m.setSongName(cursor.getString(1));
            m.setArtistName(cursor.getString(2));
            m.setReleaseYear(cursor.getInt(3));
            cursor.close();
        }
        catch(Exception e){

        }
        return m;
    }


}
