package com.barmej.notesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barmej.notesapp.data.Note;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
private ArrayList<Note> mItem;
private OnClickItem mItemClickListener;
public NoteAdapter (ArrayList<Note> mItem ){
this.mItem = mItem;
}

    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note, viewGroup, false);
        NoteViewHolder noteViewHolder = new NoteViewHolder(view, mItemClickListener);
        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder noteViewHolder, int i) {
        Note note =  mItem.get(i);
        noteViewHolder.noteIv.setText(note.getIdeaNote());
        noteViewHolder.position = i;
        System.out.println(note.getIdeaNote());
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView noteIv;
        private int position;
        public NoteViewHolder(@NonNull View itemView, final OnClickItem mItemClickListener) {
            super(itemView);
            noteIv = itemView.findViewById(R.id.view_note);
itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
mItemClickListener.onClickItem(position);
    }
});
        }

    }
}
