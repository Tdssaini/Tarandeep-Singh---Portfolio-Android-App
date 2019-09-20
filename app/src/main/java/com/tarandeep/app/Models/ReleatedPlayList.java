package com.tarandeep.app.Models;

public class ReleatedPlayList {

	private String likes;
	private String favorites;
	private String uploads;
	
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public void setFavorites(String favorites) {
		this.favorites = favorites;
	}
	public void setUploads(String uploads) {
		this.uploads = uploads;
	}
	public String getFavorites() {
		return favorites;
	}
	public String getUploads() {
		return uploads;
	}
}
