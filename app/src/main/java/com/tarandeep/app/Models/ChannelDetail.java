package com.tarandeep.app.Models;


public class ChannelDetail {

	private String id;
	private String kind;
	private String etag;
	private ChannelItems snippet;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getEtag() {
		return etag;
	}
	public void setEtag(String etag) {
		this.etag = etag;
	}
	public ChannelItems getSnippet() {
		return snippet;
	}
	public void setSnippet(ChannelItems snippet) {
		this.snippet = snippet;
	}


}
