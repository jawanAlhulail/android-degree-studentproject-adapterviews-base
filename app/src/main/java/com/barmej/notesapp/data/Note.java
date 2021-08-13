package com.barmej.notesapp.data;

import android.graphics.drawable.Drawable;

public class Note {

    private String ideaNote;
    private Drawable color;

    public Note(String ideaNote, Drawable color) {
        this.ideaNote = ideaNote;
        this.color = color;
    }

    public Note() {
    }

    public void setColor(Drawable color) {
        this.color = color;
    }

    public Drawable getColor() {
        return color;
    }

    public void setIdeaNote(String ideaNote) {
        this.ideaNote = ideaNote;
    }

    public String getIdeaNote() {
        return ideaNote;
    }

//public static ArrayList<Note> getDefaultList(){
//            ArrayList<Note> defaultList = new ArrayList();
//            return defaultList;
//}


}
