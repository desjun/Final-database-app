package com.example.musicplaylist;

import java.util.Calendar;

public class Music {


    private int musicID;
    private String songName;
    private String artistName;
    private int releaseYear;




    public Music(){
        musicID = -1;


    }



    public int getMusicID() {
        return musicID;
    }

    public void setMusicID(int musicID) {
        this.musicID = musicID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) { this.songName = songName;
    }

    public String getArtistName() { return artistName; }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }



}
