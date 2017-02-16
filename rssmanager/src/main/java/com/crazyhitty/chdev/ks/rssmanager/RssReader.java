package com.crazyhitty.chdev.ks.rssmanager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Author:      Kartik Sharma
 * Email Id:    cr42yh17m4n@gmail.com
 * Created:     2/13/2017 11:52 PM
 * Description: Unavailable
 */

public class RssReader {
    private static final String TAG = "RssReader";

    private static RssReader rssReaderInstance;

    private RssCallback rssCallback;

    private RssReader() {

    }

    public static RssReader getInstance() {
        if (rssReaderInstance == null) {
            rssReaderInstance = new RssReader();
        }
        return rssReaderInstance;
    }

    public RssReader callback(RssCallback rssCallback) {
        rssReaderInstance.rssCallback = rssCallback;
        return rssReaderInstance;
    }

    public void loadFeeds(String... urls) {
        RssParser.parse(urls[0])
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        Log.e(TAG, "onFailure", e);
                        if (rssReaderInstance.rssCallback != null) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    rssReaderInstance.rssCallback.unableToReadRssFeeds(e.getMessage());
                                }
                            });
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG, "onResponse: responseCode: " + response.code());
                        if (rssReaderInstance.rssCallback != null) {
                            try {
                                final RSS rss = new Persister().read(RSS.class, response.body().string());
                                Log.d(TAG, "onResponse: RssParsed: " + rss.toString());
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        rssReaderInstance.rssCallback.rssFeedsLoaded(rss);
                                    }
                                });
                            } catch (final Exception e) {
                                e.printStackTrace();
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        rssReaderInstance.rssCallback.unableToReadRssFeeds(e.getMessage());
                                    }
                                });
                            }
                        }
                    }
                });
    }

    public interface RssCallback {
        void rssFeedsLoaded(RSS... rss);

        void unableToReadRssFeeds(String errorMessage);
    }
}
