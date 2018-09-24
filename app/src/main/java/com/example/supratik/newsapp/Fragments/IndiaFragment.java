package com.example.supratik.newsapp.Fragments;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.supratik.newsapp.News;
import com.example.supratik.newsapp.NewsAdapter;
import com.example.supratik.newsapp.NewsUtils;
import com.example.supratik.newsapp.R;

import java.util.ArrayList;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class IndiaFragment extends Fragment {
    private String techUrl =
            "https://newsapi.org/v2/top-headlines?country=in&query=india&category=general&pageSize=100&apiKey=f10ff2dc6015435488deae959371f83a";

    public IndiaFragment() {
        // Required empty public constructor
    }

    NewsAdapter mAdapter;
    RecyclerView recyclerView;
    private ArrayList<News> headLineList;
    private View fragmentView;
    private TextView mEmptyView;

    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




//        ConnectivityManager connectivityManager =
//                (ConnectivityManager)getActivity().getSystemService(CONNECTIVITY_SERVICE);
//
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//
//        boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
//

        asynRun();


    }

    public boolean isInternetConnected() {
        if (getActivity() != null) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);

            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

            boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();


            return isConnected;
        }

        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater,container,savedInstanceState);

        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_one, container, false);
        progressBar = fragmentView.findViewById(R.id.progressbar);
//        progressBar.setVisibility(View.GONE);
//        android.support.v4.app.LoaderManager loaderManager = getLoaderManager();

//        loaderManager.initLoader(0, null, this)
        mEmptyView = fragmentView.findViewById(R.id.emptyView);

        return fragmentView;

    }

    public  void  asynRun(){
        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(techUrl);
    }


//    @NonNull
//    @Override
//    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
//        Uri baseUri = Uri.parse(techUrl.toString());
//        Uri.Builder uriBuilder = baseUri.buildUpon();
//
//        String[] projection = new String[]{techUrl};
//
//        return null;
//    }
//
//    @Override
//    public void onLoadFinished(@NonNull android.support.v4.content.Loader loader, Object data) {
////        mAdapter.setData();
//    }
//
//    @Override
//    public void onLoaderReset(@NonNull android.support.v4.content.Loader loader) {
//        mAdapter.notifyDataSetChanged();
//    }

    public Void updateUI(ArrayList<News> news) {

        // booklist = BookUtils.extractBookList(BOOKS_REQUEST_URL);

        recyclerView = fragmentView.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new NewsAdapter(news, getActivity());

        recyclerView.setAdapter(mAdapter);
//        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Book book = mAdapter.getItem(position);
//                Intent i = new Intent();
//                i.setAction(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(book.getInfoLink()));
//                startActivity(i);
//            }
//        });

        return null;
    }

    public class NewsAsyncTask extends AsyncTask<String, Void, ArrayList<News>> {


        @Override
        protected ArrayList<News> doInBackground(String... urls) {
            headLineList = NewsUtils.extractHeadlines(urls[0]);
            return headLineList;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {

//            ProgressBar progressBar = findViewById(R.id.progressbar);
//            progressBar.setVisibility(View.GONE);

            if(!isInternetConnected()){
                progressBar.setVisibility(View.GONE);
                mEmptyView.setText("Check Your Internet Connection");
            }
            else if (news != null && !news.isEmpty()) {
                progressBar.setVisibility(View.GONE);
                updateUI(news);
            }

            else {
                progressBar.setVisibility(View.GONE);
                mEmptyView.setText("Sorry!!! No news found");
            }

        }

    }

}
