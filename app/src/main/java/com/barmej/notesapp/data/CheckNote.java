package com.barmej.notesapp.data;

import android.graphics.drawable.Drawable;

public class CheckNote extends Note {
private Boolean check;
    public CheckNote(String ideaNote, Drawable color, Boolean checked) {
        super(ideaNote,color);
        this.check = checked;
    }
    public Boolean getChecked() {
        return check;
    }

    public void setChecked(Boolean checked) {
        this.check = checked;
    }
}
