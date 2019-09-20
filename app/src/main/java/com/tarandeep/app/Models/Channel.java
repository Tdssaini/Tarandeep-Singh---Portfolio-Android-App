package com.tarandeep.app.Models;

import java.util.ArrayList;

public class Channel {

	private ArrayList<ChannelDetail> items;
	private String kind;
	private String nextPageToken;
	private String prevPageToken;
	
	public ArrayList<ChannelDetail> getItems() {
		return items;
	}
	public void setItems(ArrayList<ChannelDetail> items) {
		this.items = items;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getKind() {
		return kind;
	}
	public String getNextPageToken() {
		return nextPageToken;
	}
	public String getPrevPageToken() {
		return prevPageToken;
	}
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
	public void setPrevPageToken(String prevPageToken) {
		this.prevPageToken = prevPageToken;
	}
}
