package com.crazyhitty.chdev.ks.rssmanagerlib;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.crazyhitty.chdev.ks.rssmanager.OnRssLoadListener;
import com.crazyhitty.chdev.ks.rssmanager.RssItem;
import com.crazyhitty.chdev.ks.rssmanager.RssReader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnRssLoadListener {

    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private EditText mETxtUrl;
    private ListView mListViewFeeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind view to the activity
        bindViews();

        //set toolbar
        setSupportActionBar(mToolbar);

        //start loading rss feeds on fab click listener
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Loading feeds", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                loadFeeds(mETxtUrl.getText().toString());
            }
        });
    }

    private void bindViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mETxtUrl = (EditText) findViewById(R.id.edit_text_url);
        mListViewFeeds = (ListView) findViewById(R.id.list_view_feeds);
    }

    //load feeds
    private void loadFeeds(String url) {
        String[] urlArr = {url};
        RssReader rssReader = new RssReader(MainActivity.this, urlArr, null, null, null, this);
        rssReader.readRssFeeds();
    }

    @Override
    public void onSuccess(List<RssItem> rssItems) {
        List<String> rssTitles = new ArrayList<>();
        for (RssItem rssItem : rssItems) {
            rssTitles.add(rssItem.getTitle());
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, rssTitles);
        mListViewFeeds.setAdapter(stringArrayAdapter);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(MainActivity.this, "Error:\n" + message, Toast.LENGTH_SHORT).show();
    }
}
