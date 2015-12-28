#Rss-Manager

[ ![Download](https://api.bintray.com/packages/cr42yh17m4n/maven/rss-manager/images/download.svg) ](https://bintray.com/cr42yh17m4n/maven/rss-manager/_latestVersion)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Rss--Manager-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/2954)

A simple, easy to use library to parse rss feeds into your app.

![](http://i.imgur.com/r13X3HT.png)

#Requirements
This library requires <b>minimum sdk version 9</b>. 

Also add Internet permission to your application manifest file.

#Installation

Add this to your build.gradle version (app module)

```
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.crazyhitty.chdev.ks:rss-manager:0.10'
}
```

#How to use

Here is a simple example code on how to implement this library.

    //load feeds
    private void loadFeeds() {
        //you can also pass multiple urls
        String[] urlArr = {"http://feeds.bbci.co.uk/news/rss.xml"};
        RssReader rssReader = new RssReader(MainActivity.this, urlArr, null, null, null, this);
        rssReader.readRssFeeds();
    }
    
    @Override
    public void onSuccess(List<RssItem> rssItems) {
        Toast.makeText(MainActivity.this, "Item: "+rssItems.get(0).getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(MainActivity.this, "Error: "+message, Toast.LENGTH_SHORT).show();
    }
    
#TODO

* Provide better documentation
* Optimize code
* Make loading dialog optional

#3rd party library used

* [Jsoup](https://github.com/jhy/jsoup/)
* [Material Dialogs](https://github.com/afollestad/material-dialogs)

#App that uses this library

* [Munch](https://github.com/crazyhitty/Munch)

If you use this library and would like to get it featured here then just mail me at cr42yh17m4n@gmail.com with your app links and I will add it here. You can also mail me if you want new features.
