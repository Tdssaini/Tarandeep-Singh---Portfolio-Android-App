package com.tarandeep.app.Models;

/**
 * Created by Tarandeep Singh on 8/25/2017.
 */

public class SkillSetModel extends BaseModel {

    private String title;
    private String rating;

    public SkillSetModel(String title, String rating){
        this.rating = rating;
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
