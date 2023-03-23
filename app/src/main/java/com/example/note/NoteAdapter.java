package com.example.note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.noteViewHolder> {

    List<NoteModel> noteModelList;

    public NoteAdapter(List<NoteModel> noteModelList) {
        this.noteModelList = noteModelList;
    }

    @NonNull
    @Override
    public noteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new noteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,null));
    }

    @Override
    public void onBindViewHolder(@NonNull noteViewHolder holder, int position) {
        NoteModel noteModel = noteModelList.get(position);
        holder.textViewTitle.setText(noteModel.getTitle());
        holder.textViewBody.setText(noteModel.getBody());

    }

    @Override
    public int getItemCount() {
        return noteModelList.size();
    }

    static class noteViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle, textViewBody;

        public noteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewBody = itemView.findViewById(R.id.textViewBody);
        }
    }
}
