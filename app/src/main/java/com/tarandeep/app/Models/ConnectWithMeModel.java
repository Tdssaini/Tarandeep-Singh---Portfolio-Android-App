package com.tarandeep.app.Models;

/**
 * Created by Tarandeep Singh on 8/25/2017.
 */

public class ConnectWithMeModel extends BaseModel {

    private String url;
    private String label;

    public ConnectWithMeModel( String label, String url){
        this.label = label;
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
