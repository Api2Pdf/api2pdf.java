package com.api2pdf.models;

import com.api2pdf.models.Api2PdfBookmarkItemModel;

public class Api2PdfBookmarksRequestModel extends Api2PdfRequestModelBase {
	private String url;
	private Api2PdfBookmarkItemModel[] bookmarks;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Api2PdfBookmarkItemModel[] getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(Api2PdfBookmarkItemModel[] bookmarks) {
		this.bookmarks = bookmarks;
	}
}