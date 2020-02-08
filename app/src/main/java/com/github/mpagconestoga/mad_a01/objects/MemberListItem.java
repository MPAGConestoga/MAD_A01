package com.github.mpagconestoga.mad_a01.objects;

public class MemberListItem {
    private int mImage;
    private String mName;
    private int mDeleteImage;

    public MemberListItem(int image, String memberName, int delete)
    {
        mImage = image;
        mName = memberName;
        mDeleteImage = delete;
    }

    public int getmImage()
    {
        return mImage;
    }
    public String getmName()
    {
        return mName;
    }

    public int getmDeleteImage()
    {
        return mDeleteImage;
    }
}