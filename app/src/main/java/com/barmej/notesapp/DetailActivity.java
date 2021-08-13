package com.barmej.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        editText = findViewById(R.id.noteEditText);

        Intent intent = getIntent();
        final int noteId = intent.getIntExtra("note id", -1);
        if(noteId != -1){

            editText.setText(MainActivity.mNotes.get(noteId).getIdeaNote());

        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            MainActivity.mNotes.set(noteId,  Note.setIdeaNote());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}