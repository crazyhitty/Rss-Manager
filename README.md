#Rss-Manager

[ ![Download](https://api.bintray.com/packages/cr42yh17m4n/maven/rss-manager/images/download.svg) ](https://bintray.com/cr42yh17m4n/maven/rss-manager/_latestVersion)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Rss--Manager-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/2954)

A simple, lightweight and easy to use library to parse rss feeds into your app.

![](http://i.imgur.com/r13X3HT.png)

#Requirements
This library requires <b>minimum sdk version 9</b>. 

Also add Internet permission to your application manifest file.

#Installation

Add this to your project level build.gradle file.

```
repositories {
    maven { url "https://jitpack.io" }
}
```

Add this to your app level build.gradle file.

```
dependencies {
    compile ('com.crazyhitty.chdev.ks:rss-manager:0.50'){
            exclude module: 'stax'
            exclude module: 'stax-api'
            exclude module: 'xpp3'
        }
}
```

#Implementation

Loading a single feed url.

```
    RssReader.getInstance()
             .callback(new RssReader.RssCallback() {
                 @Override
                 public void rssFeedsLoaded(RSS... rss) {
                     // Feeds loaded.
                 }

                 @Override
                 public void unableToReadRssFeeds(String errorMessage) {
                     // Unable to parse feeds.
                 }
             })
             .loadFeeds(url);
```

*Loading multiple feed urls will be available in the next release.*
    
#CONTRIBUTING

You can contribute to the development of this library by posting new bugs/issues or any feature requests that you would like to see in the library.

#Apps that uses this library

* [Munch](https://github.com/crazyhitty/Munch)

If your app uses this library and would like to get it featured here then just mail me at cr42yh17m4n@gmail.com with your app links and I will add it here. You can also mail me if you want new features.
