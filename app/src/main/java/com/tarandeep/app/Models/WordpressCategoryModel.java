package com.tarandeep.app.Models;

/**
 * Created by Tarandeep Singh on 8/17/2017.
 */

public class WordpressCategoryModel extends BaseModel {

    private int id;
    private String description;
    private String link;
    private String name;
    private String slug;
    private String taxonomy;
    private int count;

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }
}
