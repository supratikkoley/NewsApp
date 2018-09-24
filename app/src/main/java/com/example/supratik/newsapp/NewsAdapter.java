package com.example.supratik.newsapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public TextView title;

        public TextView source;

        public TextView date;

        public LinearLayout itemLayoyt;


        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imView);
            title = itemView.findViewById(R.id.title);
            source = itemView.findViewById(R.id.source);
            date = itemView.findViewById(R.id.date);

            itemLayoyt = (LinearLayout) itemView.findViewById(R.id.item_layout);
        }
    }

    private List<News> newsList;
    private Context context;

    private final LayoutInflater mInflater;
    private Context mContext;

    public NewsAdapter(List<News> newsList, Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.newsList = newsList;
    }

//    public void setData(Cursor cursor) {
//        mCursor = cursor;
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final News currentNews = newsList.get(position);

        holder.title.setText(currentNews.getTitle());
        holder.source.setText(currentNews.getSourceName());
        holder.date.setText(currentNews.getDate());

        holder.itemLayoyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("newsUrl", currentNews.getNewsUrl());
                mContext.startActivity(intent);
            }
        });

//        DownloadImageTask task = new DownloadImageTask(holder.imageView);
//        task.execute(currentNews.getImageUrl());
        try {
            Picasso.get().load(currentNews.getImageUrl()).into(holder.imageView);
        }
        catch (Exception e){

        }

    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

//    public NewsAdapter(Activity context,  ArrayList<News> arrayList) {
//        super(context,0, arrayList);
//    }

//    private News currentNews;

//    @Override
//    public View getView(int position,  View convertView, ViewGroup parent) {
//        View listItemView = convertView;
//
//        if(listItemView == null) {
//            if (listItemView == null) {
//                listItemView = LayoutInflater.from(getContext()).inflate(
//                        R.layout.list_item, parent, false);
//            }
//
//            ImageView imageView = listItemView.findViewById(R.id.imageView);
//
//            TextView title = listItemView.findViewById(R.id.title);
//
//            TextView source = listItemView.findViewById(R.id.source);
//
//            TextView date = listItemView.findViewById(R.id.date);
//
//            currentNews = getItem(position);
//
//            title.setText(currentNews.getTitle());
//            source.setText(currentNews.getSourceName());
//            date.setText(currentNews.getDate());
//
//            DownloadImageTask task = new DownloadImageTask(imageView);
//            task.execute(currentNews.getImageUrl());
//        }
//
//        return listItemView;
//    }
//
//


//    public class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {
//
//        ImageView imageView;
//
//        public DownloadImageTask(ImageView bmImage) {
//            imageView = bmImage;
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... urls) {
//            Bitmap bmp = null;
//
//            try {
//                InputStream in = new java.net.URL(urls[0]).openStream();
//                bmp = BitmapFactory.decodeStream(in);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return bmp;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            imageView.setImageBitmap(bitmap);
//        }
//    }

}