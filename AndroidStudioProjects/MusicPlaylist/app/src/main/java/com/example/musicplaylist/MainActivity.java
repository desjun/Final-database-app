package com.example.musicplaylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent intent = new Intent(MainActivity.this, MusicAddSong.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAddButton();

    }
    @Override
    public void onResume(){
        super.onResume();

        MusicDataSource ds = new MusicDataSource(this);
        ArrayList<Music> music;
        try{
            ds.open();
            music = ds.getMusic();
            ds.close();
            RecyclerView musicPlaylist = findViewById(R.id.rvMusic);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            musicPlaylist.setLayoutManager(layoutManager);
            MusicAdapter musicAdapter = new MusicAdapter(music);
            musicAdapter.setOnClickListener(onClickListener);
            musicPlaylist.setAdapter(musicAdapter);
        }
        catch (Exception e){
            Toast.makeText(this, "Error retrieving music", Toast.LENGTH_LONG).show();
        }


    }

    private void initAddButton(){
        Button addButton = findViewById(R.id.addSong);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(), "Add Click", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, MusicAddSong.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }
}