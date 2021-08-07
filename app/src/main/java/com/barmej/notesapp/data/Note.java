package com.barmej.notesapp.data;

import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class Note {
private String ideaNote;


public Note(String ideaNote){
    this.ideaNote = ideaNote;
}

    public Note() {
        
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
