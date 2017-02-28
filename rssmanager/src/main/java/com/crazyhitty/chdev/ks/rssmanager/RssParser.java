package com.crazyhitty.chdev.ks.rssmanager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:      Kartik Sharma
 * Email Id:    cr42yh17m4n@gmail.com
 * Created:     2/13/2017 10:03 PM
 * Description: Unavailable
 */

class RssParser {
    private static final int TIMEOUT_SECS = 30;

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
            .build();

    private RssParser() {

    }

    static Response parse(String feedUrl) throws IOException {
        Request request = new Request.Builder()
                .url(feedUrl)
                .get()
                .build();

        return OK_HTTP_CLIENT.newCall(request).execute();
    }
}
