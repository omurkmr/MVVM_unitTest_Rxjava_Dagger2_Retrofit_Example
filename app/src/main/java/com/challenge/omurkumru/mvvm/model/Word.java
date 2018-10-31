package com.challenge.omurkumru.mvvm.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Word implements Parcelable {

    @SerializedName("text_eng")
    @Expose
    private String textEng;
    @SerializedName("text_spa")
    @Expose
    private String textSpa;

    public Word(String textEng, String textSpa) {
        this.textEng = textEng;
        this.textSpa = textSpa;
    }

    protected Word(Parcel in) {
        textEng = in.readString();
        textSpa = in.readString();
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public String getTextEng() {
        return textEng;
    }

    public void setTextEng(String textEng) {
        this.textEng = textEng;
    }

    public String getTextSpa() {
        return textSpa;
    }

    public void setTextSpa(String textSpa) {
        this.textSpa = textSpa;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.textEng);
        dest.writeString(this.textSpa);
    }

    @Override
    public String toString() {
        return "Word{" +
                "english ='" + textEng + '\'' +
                ", spanish ='" + textSpa + '\'' +
                '}';
    }
}