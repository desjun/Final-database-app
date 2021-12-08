package com.example.musicplaylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter {

    private ArrayList<Music> MusicData;
    private View.OnClickListener onClickListener;

    public class MusicViewHolder extends RecyclerView.ViewHolder{


        public TextView textViewSong;
        public  TextView textViewArtist;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSong = itemView.findViewById(R.id.textViewSong);
            textViewArtist = itemView.findViewById(R.id.textViewArtist);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }

        public TextView getSongTextView(){
            return textViewSong;
        }

        public TextView getTextViewArtist() {return textViewArtist; }
    }

    public  void setOnClickListener(View.OnClickListener listener){
        onClickListener = listener;
    }

    public MusicAdapter(ArrayList<Music> arrayList){
        MusicData = arrayList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item_view, parent, false);
        return  new MusicViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MusicViewHolder cvh = (MusicViewHolder) holder;
        cvh.getSongTextView().setText(MusicData.get(position).getSongName());
        cvh.getTextViewArtist().setText(MusicData.get(position).getArtistName());

    }

    @Override
    public int getItemCount() {
        return MusicData.size();
    }




}
