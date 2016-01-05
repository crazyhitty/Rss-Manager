package com.crazyhitty.chdev.ks.rssmanager;

import android.content.Context;
import android.content.DialogInterface;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kartik_ch on 11/15/2015.
 */
public class RssReader implements OnFeedLoadListener {
    private static String TITLE = "title";
    private static String DESCRIPTION = "description";
    private static String LINK = "link";
    private static String MEDIA_THUMBNAIL = "media|thumbnail";
    private static String MEDIA_CONTENT = "media|content";
    private static String IMAGE = "image";
    private static String CATEGORY = "category";
    private static String PUB_DATE = "pubDate";
    private static String URL = "url";
    private String[] mUrlList, mSourceList, mCategories;
    private int[] mCategoryImgIds;
    private boolean mShowDialog = true;
    private Context mContext;
    private List<RssItem> mRssItems = new ArrayList<>();
    private RssParser mRssParser;
    private int mPosition = 0;
    private MaterialDialog mMaterialDialog;
    private OnRssLoadListener mOnRssLoadListener;

    @Deprecated
    public RssReader(Context context, String[] urlList, String[] sourceList, String[] categories, int[] categoryImgIds, OnRssLoadListener onRssLoadListener) {
        this.mContext = context;
        this.mUrlList = urlList;
        this.mSourceList = sourceList;
        this.mCategories = categories;
        this.mCategoryImgIds = categoryImgIds;
        this.mOnRssLoadListener = onRssLoadListener;
    }

    public RssReader(Context context) {
        this.mContext = context;
    }

    public RssReader urls(String[] urls) {
        this.mUrlList = urls;
        return this;
    }

    public RssReader sources(String[] sources) {
        this.mSourceList = sources;
        return this;
    }

    public RssReader categories(String[] categories) {
        this.mCategories = categories;
        return this;
    }

    @Deprecated
    public RssReader categoryImgIds(int[] categoryImgIds) {
        this.mCategoryImgIds = categoryImgIds;
        return this;
    }

    public RssReader showDialog(boolean status) {
        this.mShowDialog = status;
        return this;
    }

    public void parse(OnRssLoadListener onRssLoadListener) {
        this.mOnRssLoadListener = onRssLoadListener;

        if (mShowDialog) {
            initDialog();
        }

        if (mRssItems != null) {
            mRssItems.clear();
        }

        if (mUrlList != null) {
            parseRss(0);
        } else {
            throw new NullPointerException("Url list cannot be empty");
        }
    }

    private void initDialog() {
        mMaterialDialog = new MaterialDialog.Builder(mContext)
                .title(R.string.loading_feeds)
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .cancelable(false)
                .negativeText(R.string.cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                        if (mRssParser != null) {
                            mRssParser.cancel(true);
                        }
                        mOnRssLoadListener.onFailure("User performed dismiss action");
                    }
                })
                .build();
        mMaterialDialog.show();

        mMaterialDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if (mRssParser != null) {
                    mRssParser.cancel(true);
                }
                mOnRssLoadListener.onFailure("User performed dismiss action");
            }
        });
    }

    @Deprecated
    public void readRssFeeds() {
        initDialog();

        if (mRssItems != null) {
            mRssItems.clear();
        }
        parseRss(0);
    }

    private void parseRss(int position) {
        if (position != mUrlList.length) {
            mRssParser = new RssParser(mUrlList[position], this);
            mRssParser.execute();
            String source = getWebsiteName(mUrlList[position]);
            if (mMaterialDialog != null) {
                mMaterialDialog.setContent(source);
            }
        } else {
            if (mMaterialDialog != null) {
                mMaterialDialog.dismiss();
            }
            mOnRssLoadListener.onSuccess(mRssItems);
        }
    }

    private String getWebsiteName(String url) {
        URI uri;
        try {
            uri = new URI(url);
            return uri.getHost();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public void onSuccess(Elements items) {
        for (Element item : items) {
            mRssItems.add(getRssItem(item));
        }
        mPosition++;
        parseRss(mPosition);
    }

    @Override
    public void onFailure(String message) {
        mOnRssLoadListener.onFailure(message);
    }

    private RssItem getRssItem(Element element) {
        String title = element.select(TITLE).first().text();
        String description = element.select(DESCRIPTION).first().text();
        String link = element.select(LINK).first().text();
        String sourceName = null;
        if (mSourceList != null) {
            sourceName = mSourceList[mPosition];
        }

        String sourceUrl = mUrlList[mPosition];
        String sourceUrlShort = getWebsiteName(mUrlList[mPosition]);

        String imageUrl;
        if (!element.select(MEDIA_THUMBNAIL).isEmpty()) {
            imageUrl = element.select(MEDIA_THUMBNAIL).attr(URL);
        } else if (!element.select(MEDIA_CONTENT).isEmpty()) {
            imageUrl = element.select(MEDIA_CONTENT).attr(URL);
        } else if (!element.select(IMAGE).isEmpty()) {
            imageUrl = element.select(IMAGE).attr(URL);
        } else {
            imageUrl = null;
        }
        String category = null;
        if (mCategories == null) {
            if (!element.select(CATEGORY).isEmpty()) {
                category = element.select(CATEGORY).first().text();
            }
        } else {
            category = mCategories[mPosition];
        }

        String pubDate = null;
        if (!element.select(PUB_DATE).isEmpty()) {
            pubDate = element.select(PUB_DATE).first().text();
        }

        RssItem rssItem = new RssItem();

        rssItem.setTitle(title);
        rssItem.setDescription(description);
        rssItem.setLink(link);
        rssItem.setSourceName(sourceName);
        rssItem.setSourceUrl(sourceUrl);
        rssItem.setSourceUrlShort(sourceUrlShort);
        rssItem.setImageUrl(imageUrl);
        rssItem.setCategory(category);
        rssItem.setPubDate(pubDate);

        return rssItem;
    }
}
