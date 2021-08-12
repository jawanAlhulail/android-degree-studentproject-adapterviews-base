package com.barmej.notesapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class AddNewNoteActivity extends AppCompatActivity {
    EditText editIv;
    RadioButton noteSelectRB;
    RadioButton photoSelectRB;
    Uri mSelectedPhotoUri;
    CheckBox checkMark;
    ImageView newPhotoIV;
    private static final int READ_PHOTO_FROM_GALLERY_PERMISSION = 130;
    private static final int PICK_IMAGE = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        editIv = findViewById(R.id.noteEditText);
        newPhotoIV = findViewById(R.id.photoImageView);
        checkMark = findViewById(R.id.check_box_item);
        findViewById(R.id.radioButton6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPhotoIV.setVisibility(View.GONE);
                checkMark.setVisibility(View.INVISIBLE);
            }

        });
        findViewById(R.id.radioButton5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPhotoIV.setVisibility(View.GONE);
                checkMark.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.radioButton4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPhotoIV.setVisibility(View.VISIBLE);
                checkMark.setVisibility(View.INVISIBLE);
                selectPhoto();
            }
        });
        findViewById(R.id.button_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_PHOTO_FROM_GALLERY_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                firePickPhotoIntent();
            } else {
                Toast.makeText(this, R.string.read_permission_needed_to_access_files, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            setSelectedPhoto(data.getData());
            getContentResolver().takePersistableUriPermission(data.getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION);

        } else {
            Toast.makeText(this, R.string.failed_to_get_image, Toast.LENGTH_SHORT).show();
        }
    }

    private void selectPhoto() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_PHOTO_FROM_GALLERY_PERMISSION);
        } else {
            firePickPhotoIntent();
        }

    }

    private void setSelectedPhoto(Uri data) {
        newPhotoIV.setImageURI(data);
        mSelectedPhotoUri = data;
    }

    private void submit() {
        Intent intent = new Intent();
        String temp = editIv.getText().toString();
        Boolean checked = checkMark.isChecked();
        intent.putExtra(Constants.EXTRA_PHOTO, mSelectedPhotoUri);
        System.out.println("AddNewNoteActivity " + mSelectedPhotoUri);
        intent.putExtra(Constants.EXTRA_CHECK, checked);
        intent.putExtra(Constants.EXTRA_TEXT, temp);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void firePickPhotoIntent() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_photo)), PICK_IMAGE);
    }
}