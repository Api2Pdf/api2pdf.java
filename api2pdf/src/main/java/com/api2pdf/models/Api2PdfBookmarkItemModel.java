package com.api2pdf.models;

public class Api2PdfBookmarkItemModel {
	private String title;
	private int page;

	public Api2PdfBookmarkItemModel(int page, String title) {
		this.page = page;
		this.title = title;
	}

	public int getPage() {
		return page;
	}

	public String getTitle() {
		return title;
	}

	public void setPage() {
		this.page = page;
	}

	public void setTitle() {
		this.title = title;
	}
}