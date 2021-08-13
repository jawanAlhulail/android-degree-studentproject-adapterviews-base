package com.barmej.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
EditText editText;
Button cancelBtn;
Button saveBtn;
 int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        editText = findViewById(R.id.noteEditText);
        cancelBtn = findViewById(R.id.cancelBtn);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNewNote();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        noteId = intent.getIntExtra("note id", -1);
        if (noteId != -1) {
            editText.setText(MainActivity.mNotes.get(noteId).getIdeaNote());

        }
    }
    private void saveNewNote(){
        String newText = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Constants.UPDATED_NOTE, newText);
        intent.putExtra("updated note id", noteId);
        setResult(RESULT_OK, intent);
        finish();
    }

}