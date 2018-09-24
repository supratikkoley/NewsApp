package com.example.supratik.newsapp;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class NewsUtils {

    public static ArrayList<News> extractHeadlines(String Url) {

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        String jsonResponse = "";
        URL url = createUrl(Url);

        ArrayList<News> headlineLists = new ArrayList<>();

        try {
            jsonResponse = makeHttpRequest(url);
            Log.i("json",jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //create root of whole JSONResponse
//            String sourceName,title,newsUrl,imageUrl,date;
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray articles = root.getJSONArray("articles");
            for(int i=0; i<articles.length();i++) {
               JSONObject article = articles.getJSONObject(i);

               String title = article.getString("title");

               String newsUrl = article.getString("url");

               String  imageUrl = article.getString("urlToImage");

               String date = article.getString("publishedAt");

               String sourceName = article.getJSONObject("source").getString("name");

               Log.i("title",title);

               if(imageUrl!=null) {
                   headlineLists.add(new News(title, newsUrl, imageUrl, date, sourceName));
               }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

     return headlineLists;

//      final ArrayList<News> headlineLists = new ArrayList<>();
//
//        AndroidNetworking.get(Url)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            //create root of whole JSONResponse
//                            String sourceName, title, newsUrl, imageUrl, date;
//                            JSONObject root = new JSONObject(response.toString());
//                            // Get the JsonArray(keyName: "articles")
//                            JSONArray articles = root.getJSONArray("articles");
//                            JSONObject article;//article is a JSONObject of articles JSONArray
//
//                            Log.i("Article_length", String.valueOf(articles.length()));
//
//                            for (int i = 0; i < articles.length(); i++) {
//                                article = articles.getJSONObject(i);
//                                sourceName = article.getJSONObject("source").getString("name");
//                                title = article.getString("title");
//                                newsUrl = article.getString("url");
//                                imageUrl = article.getString("urlToImage");
//                                date = article.getString("publishedAt");
//
//                                headlineLists.add(new News(title, newsUrl, imageUrl, date, sourceName));
//
//                                Log.i("title",title);
//
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Log.i("not_happen","NOT GET");
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Log.i("error","ERror occured");
//                    }
//                });
//
//
//        return headlineLists;
    }

    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        return url;
    }

    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        if (url == null)
            return null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

}
