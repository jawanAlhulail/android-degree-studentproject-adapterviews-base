package com.barmej.notesapp.data;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class PhotoNote extends Note {

private Uri image;
    public PhotoNote(String note, Uri selectedImage){
        super(note);
        image = selectedImage;
    }

    public Uri getImage() {
        return image;
    }


}
