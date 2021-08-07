package com.barmej.notesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.barmej.notesapp.data.Note;
import com.barmej.notesapp.data.PhotoNote;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_NOTE = 101;
    private static final int ADD_PHOTO = 102;

    private RecyclerView mRecyclerView;
 private ArrayList<Note> mNotes = new ArrayList<>();
 private NoteAdapter mAdapter;
    private static final String TAG = MainActivity.class.getSimpleName();

    Menu mMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view_photos);
        mAdapter = new NoteAdapter(mNotes);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2 ,1);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        findViewById(R.id.floating_button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddNewNote();
            }
        });
    }
    private void startAddNewNote(){
        Intent intent = new Intent(MainActivity.this, AddNewNoteActivity.class);
startActivityForResult(intent, ADD_NOTE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_NOTE){
            if(resultCode == RESULT_OK && data != null){
                Uri photoNoteUri = data.getParcelableExtra(Constants.EXTRA_PHOTO);
                String ideaNote = data.getStringExtra(Constants.EXTRA_TEXT);
                if(ideaNote != null){
                    Note note = new Note(ideaNote);
                    addItem(note);
                    Log.i(TAG,note.getIdeaNote());
                    }
                    if(photoNoteUri != null){
                        PhotoNote photoNote = new PhotoNote(ideaNote,photoNoteUri);
                        addItem(photoNote);
                        System.out.println("photo inserted");
                    }
            }else {
                Toast.makeText(this, "لم يتم اضافة نص", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void addItem(Note note){
        mNotes.add(note);
        mAdapter.notifyItemInserted(mNotes.size() - 1);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        this.mMenu = menu;
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId() == R.id.action_grid){
//            mRecyclerView.setLayoutManager(mGridLayoutManager);
//            item.setVisible(false);
//            mMenu.findItem(R.id.action_list).setVisible(true);
//        } else if(item.getItemId() == R.id.action_list){
//            mRecyclerView.setLayoutManager(mListLayoutManager);
//            item.setVisible(false);
//            mMenu.findItem(R.id.action_grid).setVisible(true);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
