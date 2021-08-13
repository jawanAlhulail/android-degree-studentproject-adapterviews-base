package com.barmej.notesapp.data;

public class Note {

    private String ideaNote;
    private int color;

    public Note(String ideaNote, int color) {
        this.ideaNote = ideaNote;
        this.color = color;
    }

    public Note() {
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
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
