package com.example.evgeniy.htcandroid.dummy;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable {

    private final String name;
    private final String address;
    private final String info;

    public User(String name, String address, String info) {
        this.name = name;
        this.address = address;
        this.info = info;
    }

    private User(Parcel in) {
        name = in.readString();
        address = in.readString();
        info = in.readString();
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(info);
    }

}
