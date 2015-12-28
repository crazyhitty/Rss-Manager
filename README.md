#Rss-Manager
A simple, easy to use library to parse rss feeds into your app.

![](http://i.imgur.com/r13X3HT.png)

#Requirements
This library requires minimum sdk version 9. 

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
