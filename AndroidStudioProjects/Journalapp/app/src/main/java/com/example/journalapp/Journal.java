package com.example.journalapp;

public class Journal {

    private int journalID;
    private String checkIn;
    private int numberEntry;




    public Journal(){
        journalID = -1;


    }



    public int getJournalID() {
        return journalID;
    }
    public void setJournalID(int journalID) {
        this.journalID = journalID;
    }


    public int getNumberEntry() { return numberEntry; }
    public void setNumberEntry(int numberEntry) {this.numberEntry = numberEntry;}


    public String getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(String checkIn) { this.checkIn = checkIn; }


}


