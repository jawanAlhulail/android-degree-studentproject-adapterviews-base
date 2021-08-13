package com.barmej.notesapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.barmej.notesapp.data.Note;
import com.barmej.notesapp.data.PhotoNote;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_NOTE = 101;
    private static final int ADD_PHOTO = 102;
    Drawable selectedColor;
private CheckBox checkMark;
    private RecyclerView mRecyclerView;
 static ArrayList<Note> mNotes = new ArrayList<>();
 private NoteAdapter mAdapter;
    RecyclerView.LayoutManager mListLayoutManager;
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkMark = findViewById(R.id.check_box_item);
        mRecyclerView = findViewById(R.id.recycler_view_photos);
        mAdapter = new NoteAdapter(mNotes, new OnClickItem() {

            @Override
            public void onClickItem(int position) {
                System.out.println(mNotes.get(position));
              showNoteDetails(position);
            }
        }, new OnLongClickItem() {
            @Override
            public void onLongClickItem(int position) {
                removeNote(position);
            }
        });
        mListLayoutManager = new LinearLayoutManager(this);

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
//                Boolean checkBox = data.getBooleanExtra(Constants.EXTRA_CHECK,true );
                int color = data.getIntExtra(Constants.EXTRA_COLOR, 1);
                System.out.println("main activity color" + color);
                switch (color){
                    case 0:
                        selectedColor = getResources().getDrawable(R.drawable.note_border);
                        break;
                    case 1:
                        selectedColor = getResources().getDrawable(R.drawable.note_border_purple);
                        break;
                    case 2:
                        selectedColor = getResources().getDrawable(R.drawable.note_border_red);
                        break;



                }
                System.out.println(color);
                if(photoNoteUri != null){
                    PhotoNote photoNote = new PhotoNote(ideaNote,selectedColor,photoNoteUri);
                    addItem(photoNote);
                    System.out.println("photo instere");
               }
                else {
                    Note note = new Note(ideaNote, selectedColor);
                    System.out.println("onResult ");
                    addItem(note);
                }


            }else {
                Toast.makeText(this, "لم يتم اضافة مذكره", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void addItem(Note note){
        mNotes.add(note);
        mAdapter.notifyItemInserted(mNotes.size() - 1);
    }

private void showNoteDetails(int position){
Intent intent = new Intent(this, DetailActivity.class);

intent.putExtra("note id", position);
startActivity(intent);
}
private void removeNote(final int position){
    AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage("هل انت متأكد من ازالة العنصر من القائمه ").setPositiveButton("تأكيد الازاله", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            mNotes.remove(position);
            mAdapter.notifyItemRemoved(position);
        }
    }).setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }).create();
    alertDialog.show();
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
