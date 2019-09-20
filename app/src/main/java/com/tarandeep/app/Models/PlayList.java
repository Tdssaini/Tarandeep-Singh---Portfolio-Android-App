package com.tarandeep.app.Models;

import java.util.ArrayList;

public class PlayList {

	private String etag;
	private String kind;
	private ArrayList<PlayListItem> items;

	public ArrayList<PlayListItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<PlayListItem> items) {
		this.items = items;
	}
	public String getEtag() {
		return etag;
	}
	public String getKind() {
		return kind;
	}
	public void setEtag(String etag) {
		this.etag = etag;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
}
