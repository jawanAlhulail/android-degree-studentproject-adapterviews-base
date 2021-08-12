package com.barmej.notesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barmej.notesapp.data.Note;
import com.barmej.notesapp.data.PhotoNote;

import java.io.Serializable;
import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> implements Serializable {
    private final int NOTE_PHOTO = 0;
    private final int NOTE_CHECK = 1;
    private final int NOTE_TEXT = 2;

    private ArrayList<Note> mItem;
    private OnClickItem mItemClickListener;
    private OnLongClickItem mItemLongClickListener;

    public NoteAdapter(ArrayList<Note> mItem, OnClickItem mItemClickListener, OnLongClickItem mItemLongClickListener) {
        this.mItem = mItem;
        this.mItemClickListener = mItemClickListener;
        this.mItemLongClickListener = mItemLongClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        Note note = mItem.get(position);
        if(note instanceof PhotoNote) {
            return NOTE_PHOTO;
        } else {
            return NOTE_TEXT;
        }
    }

    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       View view;
//        switch (viewType) {
//            case NOTE_PHOTO:
//                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note_photo, viewGroup, false);
//                return new PhotoViewHolder(view, mItemClickListener, mItemLongClickListener);
//            default:
//                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note, viewGroup, false);
//               return new NoteViewHolder(view, mItemClickListener, mItemLongClickListener);
//        }
        NoteViewHolder viewHolder;
        if(viewType == NOTE_PHOTO){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note_photo, viewGroup, false);
            viewHolder = new PhotoViewHolder(view, mItemClickListener, mItemLongClickListener);
        }else if (viewType == NOTE_CHECK){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note_check, viewGroup, false);
            viewHolder = new CheckNoteViewHolder(view, mItemClickListener, mItemLongClickListener);
        }else{
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note, viewGroup, false);
            viewHolder = new NoteViewHolder(view, mItemClickListener, mItemLongClickListener);
        }
        return viewHolder;



    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder noteViewHolder, int i) {
        final Note note = mItem.get(i);
        noteViewHolder.bind(note);

    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder implements Serializable {

        private TextView noteIv;
private int position;

        public NoteViewHolder(@NonNull View itemView, final OnClickItem mItemClickListener, final OnLongClickItem mItemLongClickListener) {
            super(itemView);
            noteIv = itemView.findViewById(R.id.view_note);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onClickItem(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mItemLongClickListener.onLongClickItem(getAdapterPosition());
                    return true;
                }
            });

        }

        void bind(Note note) {
            noteIv.setText(note.getIdeaNote());
        }

    }

    static class PhotoViewHolder extends NoteViewHolder implements Serializable {

        ImageView imageView;

        public PhotoViewHolder(@NonNull View itemView, OnClickItem mItemClickListener, OnLongClickItem mItemLongClickListener) {
            super(itemView, mItemClickListener, mItemLongClickListener);
            imageView = itemView.findViewById(R.id.image_view_list_item_photo);
        }

        void bind(Note note) {
            super.bind(note);
            PhotoNote photoNote = (PhotoNote) note;
            imageView.setImageURI(photoNote.getImage());
        }

    }
    static class CheckNoteViewHolder extends NoteViewHolder implements Serializable {
            CheckBox checkBox;
        public CheckNoteViewHolder(@NonNull View itemView, OnClickItem mItemClickListener, OnLongClickItem mItemLongClickListener) {
            super(itemView, mItemClickListener, mItemLongClickListener);
            checkBox = itemView.findViewById(R.id.check_box_item);
        }


    }
}
