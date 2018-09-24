package com.example.supratik.newsapp;

public class News  {
    private String mTitle;
    private String mNewsUrl;
    private String mImageUrl;
    private String mDate;
    private String mSourceName;

    //constructors
    public News(String title, String newsUrl, String imageUrl, String date, String sourceName){
        mTitle = title;
        mNewsUrl = newsUrl;
        mImageUrl = imageUrl;
        mDate = date;
        mSourceName = sourceName;
    }

    //methods
    public String getTitle() {
        return mTitle;
    }

    public String getNewsUrl() {
        return mNewsUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getDate() {
        return mDate;
    }

    public String getSourceName() {
        return mSourceName;
    }
}
