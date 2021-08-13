package com.barmej.notesapp.data;

public class CheckNote extends Note {
private Boolean check;
    public CheckNote(String ideaNote,int color, Boolean checked) {
        super(ideaNote,color);
        this.check = checked;
    }
    public Boolean isChecked() {
        return check;
    }

    public void setChecked(boolean checked) {
        this.check = checked;
    }
}
