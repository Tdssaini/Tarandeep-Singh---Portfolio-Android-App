package com.tarandeep.app.Utils;

import com.tarandeep.app.MainActivity;
import com.tarandeep.app.Models.Channel;
import com.tarandeep.app.Models.HomePageModel;
import com.tarandeep.app.Models.PlayList;


/**
 * Created by Tarandeep
 */
public class DataPersistence {

    private MainActivity applicationContext;
    private static DataPersistence dataPersistence;
    private Channel channel;
    private String developerKey = "--Youtube Developer Key--";
    private PlayList playListData;
    private HomePageModel homePageModel;

    public static DataPersistence getInstance(){
        if(dataPersistence == null){
            dataPersistence = new DataPersistence();
        }
       return dataPersistence;
    }

    public HomePageModel getHomePageModel() {
        return homePageModel;
    }

    public void setHomePageModel(HomePageModel homePageModel) {
        this.homePageModel = homePageModel;
    }

    public Channel getChannel() {
        return channel;
    }

    public PlayList getPlayListData() {
        return playListData;
    }

    public String getDeveloperKey() {
        return developerKey;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setDeveloperKey(String developerKey) {
        this.developerKey = developerKey;
    }

    public void setPlayListData(PlayList playListData) {
        this.playListData = playListData;
    }

    private DataPersistence(){ }

    public MainActivity getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(MainActivity applicationContext) {
        this.applicationContext = applicationContext;
    }

}
