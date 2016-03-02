package com.bignerdranch.android.parkinsons;

/**
 * Created by user on 2016-01-05.
 */
public class HowtoContents {
    private int mHowtoImage;
    private String mHowtoTitle;
    private String mHowtoText;

    public HowtoContents(int id_img, String str_title, String str_txt) {
        this.mHowtoImage = id_img;
        this.mHowtoTitle = str_title;
        this.mHowtoText = str_txt;
    }

    public int getmHowtoImage() { return mHowtoImage; }
    public String getmHowtoTitle() { return mHowtoTitle; }
    public String getmHowtoText() { return mHowtoText; }
}
