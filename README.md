#Rss-Manager
A simple, easy to use library to parse rss feeds into your app.

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

    }
    
#TODO

* Provide better documentation
* Optimize code
