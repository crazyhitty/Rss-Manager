package com.crazyhitty.chdev.ks.rssmanager;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Author:      Kartik Sharma
 * Email Id:    cr42yh17m4n@gmail.com
 * Created:     2/13/2017 10:03 PM
 * Description: Unavailable
 */

class RssParser {
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();

    private RssParser() {

    }

    static Call parse(String feedUrl) {
        Request request = new Request.Builder()
                .url(feedUrl)
                .get()
                .build();

        return OK_HTTP_CLIENT.newCall(request);
    }
}
