package com.barmej.notesapp.data;

import android.widget.CheckBox;

public class CheckNote extends Note {
private CheckBox checkBox;
    public CheckNote(String ideaNote, CheckBox checkBox) {
        super(ideaNote);
        this.checkBox = checkBox;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
