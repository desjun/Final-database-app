package com.example.journalapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JournalAdapter extends RecyclerView.Adapter {

    private ArrayList<Journal> JournalData;
    private View.OnClickListener onClickListener;

    public class JournalViewHolder extends RecyclerView.ViewHolder{


        public  TextView textViewNumberEntry;
        public TextView textViewCheckIn;


        public JournalViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNumberEntry = itemView.findViewById(R.id.textViewNumberEntry);
            textViewCheckIn = itemView.findViewById(R.id.textViewCheckIn);

            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }

        public TextView getTextViewNumberEntry(){
            return textViewNumberEntry;
        }

        public TextView getTextViewCheckIn() {return textViewCheckIn; }
    }

    public  void setOnClickListener(View.OnClickListener listener){
        onClickListener = listener;
    }


    public JournalAdapter(ArrayList<Journal>arrayList) {JournalData =arrayList;}




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_item_view, parent, false);
        return  new JournalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        JournalViewHolder cvh = (JournalViewHolder) holder;
        cvh.getTextViewNumberEntry().setText(JournalData.get(position).getNumberEntry() + "");
        cvh.getTextViewCheckIn().setText(JournalData.get(position).getCheckIn());

    }

    @Override
    public int getItemCount() {
        return JournalData.size();
    }

}
