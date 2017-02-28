package com.crazyhitty.chdev.ks.rssmanagerlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Toolbar toolbar;
    private RecyclerView recyclerViewUrls;

    private UrlRecyclerAdapter urlRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Bind views to the activity.
        bindViews();

        // Set toolbar.
        setSupportActionBar(toolbar);

        // Initialize recycler view.
        initRecyclerViewUrls();
    }

    private void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerViewUrls = (RecyclerView) findViewById(R.id.recycler_view_urls);
    }

    private void initRecyclerViewUrls() {
        urlRecyclerAdapter = new UrlRecyclerAdapter();
        recyclerViewUrls.setAdapter(urlRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                urlRecyclerAdapter.addNewUrl();
                recyclerViewUrls.smoothScrollToPosition(urlRecyclerAdapter.getItemCount() - 1);
                break;
            case R.id.action_done:
                if (urlRecyclerAdapter.getUrls().length != 0) {
                    DetailActivity.startActivity(getApplicationContext(), urlRecyclerAdapter.getUrls());
                } else {
                    Toast.makeText(getApplicationContext(), R.string.no_url_available, Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}