package com.tarandeep.app.Models;

import java.io.Serializable;
import java.util.HashMap;

public class ChannelItems implements Serializable {

	private static final long serialVersionUID = 1L;
	private String publishedAt;
	private String channelId;
	private String title;
	private String description;
	private String channelTitle;
	private String playlistId;
	private String position;
	private HashMap<String, HashMap<String, String>> thumbnails;
	private HashMap<String, String> resourceId;
	
	public String getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getChannelTitle() {
		return channelTitle;
	}
	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}
	public String getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(String playlistId) {
		this.playlistId = playlistId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public HashMap<String, HashMap<String, String>> getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(HashMap<String, HashMap<String, String>> thumbnails) {
		this.thumbnails = thumbnails;
	}
	public HashMap<String, String> getResourceId() {
		return resourceId;
	}
	public void setResourceId(HashMap<String, String> resourceId) {
		this.resourceId = resourceId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
