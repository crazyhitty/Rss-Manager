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

import com.crazyhitty.chdev.ks.rssmanager.RSS;
import com.crazyhitty.chdev.ks.rssmanager.RssReader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private EditText mETxtUrl;
    private ListView mListViewFeeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind views to the activity
        bindViews();

        //set toolbar
        setSupportActionBar(mToolbar);

        //start loading rss feeds on fab click listener
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mETxtUrl.getText().toString().isEmpty()) {
                    Snackbar.make(view, "Loading feeds", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    loadFeeds(mETxtUrl.getText().toString());
                } else {
                    Snackbar.make(view, "Please don't leave url blank", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
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
        RssReader.getInstance()
                .callback(new RssReader.RssCallback() {
                    @Override
                    public void rssFeedsLoaded(RSS... rss) {
                        List<String> rssTitles = new ArrayList<>();
                        for (int i = 0; i < rss[0].getChannel().getItems().size(); i++) {
                            String title = rss[0].getChannel().getItems().get(i).getTitle();
                            rssTitles.add(title);
                        }
                        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, rssTitles);
                        mListViewFeeds.setAdapter(stringArrayAdapter);
                        Snackbar.make(mFab, "Feeds loaded", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }

                    @Override
                    public void unableToReadRssFeeds(String errorMessage) {
                        Toast.makeText(MainActivity.this, "Error:\n" + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                })
                .loadFeeds(url);
    }
}
