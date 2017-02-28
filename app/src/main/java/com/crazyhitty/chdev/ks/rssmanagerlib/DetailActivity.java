package com.crazyhitty.chdev.ks.rssmanagerlib;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crazyhitty.chdev.ks.rssmanager.Channel;
import com.crazyhitty.chdev.ks.rssmanager.RSS;
import com.crazyhitty.chdev.ks.rssmanager.RssReader;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements RssReader.RssCallback {
    private static final String TAG = "DetailActivity";
    private static final String ARG_FEED_URLS = "feed_urls";

    private Toolbar toolbar;
    private RecyclerView recyclerViewFeeds;
    private ProgressBar progressBar;
    private TextView txtError;

    private FeedsRecyclerAdapter feedsRecyclerAdapter;

    private RssReader rssReader = new RssReader(this);

    public static void startActivity(Context context, String[] urls) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(ARG_FEED_URLS, urls);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Bind views to the activity.
        bindViews();

        // Set toolbar.
        setSupportActionBar(toolbar);

        // Add back button on toolbar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadFeeds(getIntent().getExtras().getStringArray(ARG_FEED_URLS));
    }

    private void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerViewFeeds = (RecyclerView) findViewById(R.id.recycler_view_feeds);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        txtError = (TextView) findViewById(R.id.text_view_error);
    }

    //load feeds
    private void loadFeeds(String[] urls) {
        rssReader.loadFeeds(urls);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rssReader.destroy();
    }

    @Override
    public void rssFeedsLoaded(List<RSS> rssList) {
        Snackbar.make(recyclerViewFeeds, "Feeds loaded", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        progressBar.setVisibility(View.GONE);

        // Initialize recycler view data.
        feedsRecyclerAdapter = new FeedsRecyclerAdapter(rssList);
        feedsRecyclerAdapter.setOnFeedItemClickListener(new FeedsRecyclerAdapter.OnFeedItemClickListener() {
            @Override
            public void onItemClick(int position, Channel.Item item) {
                showDetails(item);
            }
        });
        recyclerViewFeeds.setAdapter(feedsRecyclerAdapter);
    }

    @Override
    public void unableToReadRssFeeds(String errorMessage) {
        Snackbar.make(recyclerViewFeeds, "Error: " + errorMessage, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        txtError.setVisibility(View.VISIBLE);
        txtError.setText(errorMessage);
        progressBar.setVisibility(View.GONE);
    }

    private void showDetails(Channel.Item item) {
        new FeedDetailsDialog(this)
                .attachDetails(item)
                .setTitle("Extra details")
                .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}