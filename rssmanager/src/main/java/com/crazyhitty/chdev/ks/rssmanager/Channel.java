package com.crazyhitty.chdev.ks.rssmanager;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.util.List;

// Source: https://gist.github.com/macsystems/01d7e80554efd344b1f9

@NamespaceList({
        @Namespace(reference = "http://www.w3.org/2005/Atom", prefix = "atom")
})
@Root(strict = false)
public class Channel {
    // Tricky part in Simple XML because the link is named twice
    @ElementList(entry = "link", inline = true, required = false)
    private List<Link> links;

    @ElementList(name = "item", required = true, inline = true)
    private List<Item> items;


    @Element
    private String title;
    @Element(required = false)
    private String language;

    @Element(name = "ttl", required = false)
    private int ttl;

    @Element(name = "pubDate", required = false)
    private String pubDate;

    public List<Link> getLinks() {
        return links;
    }

    void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Item> getItems() {
        return items;
    }

    void setItems(List<Item> items) {
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    void setLanguage(String language) {
        this.language = language;
    }

    public int getTtl() {
        return ttl;
    }

    void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public String getPubDate() {
        return pubDate;
    }

    void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "links=" + links +
                ", items=" + items +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", ttl=" + ttl +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }

    public static class Link {
        @Attribute(required = false)
        private String href;

        @Attribute(required = false)
        private String rel;

        @Attribute(name = "type", required = false)
        private String contentType;

        @Text(required = false)
        private String link;

        public String getHref() {
            return href;
        }

        void setHref(String href) {
            this.href = href;
        }

        public String getRel() {
            return rel;
        }

        void setRel(String rel) {
            this.rel = rel;
        }

        public String getContentType() {
            return contentType;
        }

        void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getLink() {
            return link;
        }

        void setLink(String link) {
            this.link = link;
        }
    }

    @Root(name = "item", strict = false)
    public static class Item {

        @Path("title")
        @Text(required = false)
        private String title;//The title of the item.	Venice Film Festival Tries to Quit Sinking
        @Path("link")
        @Text(required = false)
        private String link;//The URL of the item.	http://www.nytimes.com/2002/09/07/movies/07FEST.html
        @Path("description")
        @Text(required = false)
        private String description;//The item synopsis.	Some of the most heated chatter at the Venice Film Festival this week was about the way that the arrival of the stars at the Palazzo del Cinema was being staged.
        @Path("author")
        @Text(required = false)
        private String author;//Email address of the author of the item. More.	oprah@oxygen.net
        @Path("category")
        @Text(required = false)
        private String category;//Includes the item in one or more categories. More.	Simpsons Characters
        @Path("comments")
        @Text(required = false)
        private String comments;//URL of a page for comments relating to the item. More.	http://www.myblog.org/cgi-local/mt/mt-comments.cgi?entry_id=290
        @Path("enclosure")
        @Text(required = false)
        private String enclosure;//	Describes a media object that is attached to the item. More.	<enclosure url="http://live.curry.com/mp3/celebritySCms.mp3" length="1069871" type="audio/mpeg"/>
        @Path("guid")
        @Text(required = false)
        private String guid;//A string that uniquely identifies the item. More.	<guid isPermaLink="true">http://inessential.com/2002/09/01.php#a2</guid>
        @Path("pubDate")
        @Text(required = false)
        private String pubDate;//	Indicates when the item was published. More.	Sun, 19 May 2002 15:21:36 GMT
        @Path("source")
        @Text(required = false)
        private String source;//	The RSS channel that the item came from. More.

        public String getTitle() {
            return title;
        }

        void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        void setLink(String link) {
            this.link = link;
        }

        public String getDescription() {
            return description;
        }

        void setDescription(String description) {
            this.description = description;
        }

        public String getAuthor() {
            return author;
        }

        void setAuthor(String author) {
            this.author = author;
        }

        public String getCategory() {
            return category;
        }

        void setCategory(String category) {
            this.category = category;
        }

        public String getComments() {
            return comments;
        }

        void setComments(String comments) {
            this.comments = comments;
        }

        public String getEnclosure() {
            return enclosure;
        }

        void setEnclosure(String enclosure) {
            this.enclosure = enclosure;
        }

        public String getGuid() {
            return guid;
        }

        void setGuid(String guid) {
            this.guid = guid;
        }

        public String getPubDate() {
            return pubDate;
        }

        void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getSource() {
            return source;
        }

        void setSource(String source) {
            this.source = source;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "title='" + title + '\'' +
                    ", link='" + link + '\'' +
                    ", description='" + description + '\'' +
                    ", author='" + author + '\'' +
                    ", category='" + category + '\'' +
                    ", comments='" + comments + '\'' +
                    ", enclosure='" + enclosure + '\'' +
                    ", guid='" + guid + '\'' +
                    ", pubDate='" + pubDate + '\'' +
                    ", source='" + source + '\'' +
                    '}';
        }
    }
}
