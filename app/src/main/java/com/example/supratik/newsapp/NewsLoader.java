package com.example.supratik.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.util.Log;

import java.util.ArrayList;

public class NewsLoader extends Loader<ArrayList<News>> {

    private String mUrl ;
    private Fragment mFragment;
    private ArrayList<News> headlineList;

    public NewsLoader(Context context,String url) {
        super(context);
        mUrl = url;
//        mFragment = fragment;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    public ArrayList<News> loadInBackground() {

        if(mUrl == null)
            return null;

        headlineList = NewsUtils.extractHeadlines(mUrl);

        Log.i("headlines", String.valueOf(headlineList));

        return headlineList;
    }
}
