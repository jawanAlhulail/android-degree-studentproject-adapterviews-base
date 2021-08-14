package com.barmej.notesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.HashSet;

public class DetailActivity extends AppCompatActivity {
EditText editText;
int chosenColor;
Button cancelBtn;
Button saveBtn;
 int noteId;
 ConstraintLayout consNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        editText = findViewById(R.id.noteEditText);
        cancelBtn = findViewById(R.id.cancelBtn);
        saveBtn = findViewById(R.id.saveBtn);
        consNote = findViewById(R.id.cons_layout_note);
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
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.barmej.notesapp", Context.MODE_PRIVATE);
        HashSet<String> set = new HashSet(MainActivity.mNotes);
        sharedPreferences.edit().putStringSet("notes", set).apply();
        if(newText.isEmpty()){
            Toast.makeText(this, "يرجى اضافة نص", Toast.LENGTH_SHORT).show();
        }else{
            setResult(RESULT_OK, intent);
            finish();
        }

    }

}