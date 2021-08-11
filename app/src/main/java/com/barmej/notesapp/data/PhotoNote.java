package com.barmej.notesapp.data;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class PhotoNote extends Note implements Parcelable {

private Uri image;
    public PhotoNote(String note, Uri selectedImage){
        super(note);
        image = selectedImage;
    }

    private PhotoNote(Parcel in) {
        image = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<PhotoNote> CREATOR = new Creator<PhotoNote>() {
        @Override
        public PhotoNote createFromParcel(Parcel in) {
            return new PhotoNote(in);
        }

        @Override
        public PhotoNote[] newArray(int size) {
            return new PhotoNote[size];
        }
    };

    public Uri getImage() {
        return image;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(image, i);
    }
}
