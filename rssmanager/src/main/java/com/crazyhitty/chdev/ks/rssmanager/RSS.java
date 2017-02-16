package com.crazyhitty.chdev.ks.rssmanager;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

// Source: https://gist.github.com/macsystems/01d7e80554efd344b1f9

@Root
public class RSS {

    @Attribute
    private String version;

    @Element
    private Channel channel;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "RSS{" +
                "version='" + version + '\'' +
                ", channel=" + channel +
                '}';
    }
}