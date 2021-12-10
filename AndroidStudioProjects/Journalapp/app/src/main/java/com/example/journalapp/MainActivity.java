package com.example.journalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent intent = new Intent(MainActivity.this, JournalAddEntry.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAddEntryButton();
    }


    @Override
    public void onResume(){
        super.onResume();

        JournalDataSource ds = new JournalDataSource(this);
        ArrayList<Journal> journal;
        try{
            ds.open();
            journal = ds.getJournal();
            ds.close();
            RecyclerView journalEntries = findViewById(R.id.rvJournal);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            journalEntries.setLayoutManager(layoutManager);
            JournalAdapter journalAdapter = new JournalAdapter(journal);
            journalAdapter.setOnClickListener(onClickListener);
            journalEntries.setAdapter(journalAdapter);
        }
        catch (Exception e){
            Toast.makeText(this, "Error retrieving entries", Toast.LENGTH_LONG).show();
        }


    }



    private void initAddEntryButton(){
        Button addEntry = findViewById(R.id.addEntry);
        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(), "Add Click", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, JournalAddEntry.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

    }


    private void initSettingButtons(){
        Button settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(), "Add Click", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, JournalSettings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }
}