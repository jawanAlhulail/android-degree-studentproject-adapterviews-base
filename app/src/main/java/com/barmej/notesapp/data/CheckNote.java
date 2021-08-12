package com.barmej.notesapp.data;

public class CheckNote extends Note {
private Boolean checkBox;
    public CheckNote(String ideaNote, Boolean checkBox) {
        super(ideaNote);
        this.checkBox = checkBox;
    }

    public Boolean getCheckBox() {
        return checkBox;
    }
}
