package com.example.musicplaylist;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAddSong extends AppCompatActivity {

    private Music currentMusic;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        currentMusic= new Music();

        initAddButton();
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
            MusicDataSource ds = new MusicDataSource(this);
            try{
                ds.open();
                currentMusic = ds.getMusic(position+1);
                ds.close();

                EditText songEdit = findViewById(R.id.editSongName);
                songEdit.setText(currentMusic.getSongName());
                EditText yearEdit = findViewById(R.id.editYear);
                yearEdit.setText(currentMusic.getReleaseYear());
                EditText artistEdit = findViewById(R.id.editArtist);
                artistEdit.setText(currentMusic.getArtistName());

            }
            catch (Exception e){

                Toast.makeText(this, "Error accessing music", Toast.LENGTH_LONG).show();
            }

        }
    }

    private void initAddButton(){
        Button addButton = findViewById(R.id.addSong);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MusicAddSong.this, MusicAddSong.class);
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
                MusicDataSource ds = new MusicDataSource(MusicAddSong.this);
                try {
                    ds.open();
                    if (currentMusic.getMusicID() == -1) {
                        wasSuccessful = ds.insertMusic(currentMusic);

                    }
                    else {
                        wasSuccessful = ds.updateMusic(currentMusic);

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
        EditText editSong = findViewById(R.id.editSongName);
        EditText editArtist = findViewById(R.id.editArtist);
        EditText editYear = findViewById(R.id.editYear);
        Button saveButton = findViewById(R.id.buttonSave);

        editSong.setEnabled(enabled);
        editArtist.setEnabled(enabled);
        editYear.setEnabled(enabled);
        saveButton.setEnabled(enabled);

    }

    private void initTextChangedEvents() {
        EditText songEdit = findViewById(R.id.editSongName);
        songEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                currentMusic.setSongName(songEdit.getText().toString());
                currentMusic.setMusicID(-1);

            }
        });

        EditText artistEdit = findViewById(R.id.editArtist);
        artistEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                currentMusic.setArtistName(artistEdit.getText().toString());


            }
        });

        EditText yearEdit = findViewById(R.id.editYear);
        yearEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                currentMusic.setReleaseYear(Integer.parseInt(yearEdit.getText().toString()));

            }
        });





    }


}
