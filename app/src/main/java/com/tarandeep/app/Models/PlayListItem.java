package com.tarandeep.app.Models;

public class PlayListItem {

	private String kind;
	private String id;
	private PlayListContentDetail contentDetails;
	
	public PlayListContentDetail getContentDetails() {
		return contentDetails;
	}
	public String getId() {
		return id;
	}
	public String getKind() {
		return kind;
	}
	public void setContentDetails(PlayListContentDetail contentDetails) {
		this.contentDetails = contentDetails;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
}
