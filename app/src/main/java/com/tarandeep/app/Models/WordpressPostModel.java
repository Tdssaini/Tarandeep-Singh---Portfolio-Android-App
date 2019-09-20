package com.tarandeep.app.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Tarandeep Singh on 8/18/2017.
 */

public class WordpressPostModel extends  BaseModel{

    private String date;
    private int id;
    private String date_gmt;
    private String slug;
    private String status;
    private String type;
    private String link;
    private String author;
    private WordpressPostContent title;
    private WordpressPostContent content;
    private WordpressPostContent excerpt;
    @SerializedName("_links")
    private WordpressLinks links;
    private String source_url;

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getSlug() {
        return slug;
    }

    public String getLink() {
        return link;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getDate_gmt() {
        return date_gmt;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public WordpressLinks getLinks() {
        return links;
    }

    public WordpressPostContent getContent() {
        return content;
    }

    public WordpressPostContent getExcerpt() {
        return excerpt;
    }

    public WordpressPostContent getTitle() {
        return title;
    }

    public void setDate_gmt(String date_gmt) {
        this.date_gmt = date_gmt;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(WordpressPostContent content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setExcerpt(WordpressPostContent excerpt) {
        this.excerpt = excerpt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLinks(WordpressLinks links) {
        this.links = links;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(WordpressPostContent title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public class WordpressPostContent extends  BaseModel{

        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }
    }

    public class WordpressLinks extends  BaseModel{

        @SerializedName("wp:featuredmedia")
        private ArrayList<WordpressLink> featuredMedia;

        public ArrayList<WordpressLink> getFeaturedMedia() {
            return featuredMedia;
        }

        public void setFeaturedMedia(ArrayList<WordpressLink> featuredMedia) {
            this.featuredMedia = featuredMedia;
        }
    }

    public class WordpressLink extends  BaseModel{
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
