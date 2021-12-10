package com.example.journalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class JournalAddEntry extends AppCompatActivity {

    private Journal currentJournal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_add_entry);

        initAddEntryButton();
        initSaveButton();
        initToggleButton();
        initTextChangedEvents();
        setForEditing(false);
    }

    public void onResume(){
        super.onResume();
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        if(position != -1){
            JournalDataSource ds = new JournalDataSource(this);
            try{
                ds.open();
                currentJournal = ds.getJournal(position+1);
                ds.close();

                EditText checkInEdit = findViewById(R.id.editCheckIn);
                checkInEdit.setText(currentJournal.getCheckIn());
                EditText entryEdit = findViewById(R.id.editNumberEntry);
                entryEdit.setText(currentJournal.getNumberEntry());

            }
            catch (Exception e){

                Toast.makeText(this, "Error accessing entries", Toast.LENGTH_LONG).show();
            }

        }
        else{
            currentJournal = new Journal();
        }
    }

    private void initAddEntryButton(){
        Button addEntry = findViewById(R.id.addEntry);
        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(), "Add Click", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(JournalAddEntry.this, JournalAddEntry.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

    }

    private void initToggleButton() {
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setForEditing(toggleButton.isChecked()); //asked the toggle button if its been turned on
            }
        });
    }

    private void initSaveButton(){
        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                JournalDataSource ds = new JournalDataSource(JournalAddEntry.this);
                try {
                    ds.open();
                    if (currentJournal.getJournalID() == -1) {
                        wasSuccessful = ds.insertJournal(currentJournal);

                    }
                    else {
                        wasSuccessful = ds.updateJournal(currentJournal);

                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
                if (wasSuccessful) {
                    ToggleButton editToggle = findViewById(R.id.toggleButton);
                    editToggle.toggle();
                    setForEditing(false);
                }




            }
        });
    }

    private void setForEditing(boolean enabled) {
        EditText editCheckIn = findViewById(R.id.editNumberEntry);
        EditText editEntry = findViewById(R.id.editNumberEntry);
        Button saveButton = findViewById(R.id.buttonSave);



        editCheckIn.setEnabled(enabled);
        editEntry.setEnabled(enabled);
        saveButton.setEnabled(enabled);

    }

    private void initTextChangedEvents() {
        EditText numberEntry = findViewById(R.id.editNumberEntry);
        numberEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                Toast.makeText(getBaseContext(), "journal " + currentJournal.getCheckIn(), Toast.LENGTH_LONG).show();
                currentJournal.setNumberEntry(Integer.parseInt(numberEntry.getText().toString()));

            }
        });

        EditText checkIn = findViewById(R.id.editCheckIn);
        checkIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                currentJournal.setCheckIn(checkIn.getText().toString());
                currentJournal.setJournalID(-1);




            }
        });

    }

}