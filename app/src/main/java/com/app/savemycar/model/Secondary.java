package com.app.savemycar.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Secondary implements Parcelable {

    private String id;
    private String name;
    private String diagnosis;

    public Secondary() {
    }

    protected Secondary(Parcel in) {
        id = in.readString();
        name = in.readString();
        name = in.readString();
    }

    public static final Creator<Secondary> CREATOR = new Creator<Secondary>() {
        @Override
        public Secondary createFromParcel(Parcel in) {
            return new Secondary(in);
        }

        @Override
        public Secondary[] newArray(int size) {
            return new Secondary[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(diagnosis);
    }
}
