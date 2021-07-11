package com.api2pdf.models;

import java.util.HashMap;
import java.util.Map;

public class Api2PdfRequestModelBase{
	private Boolean inline;
	private String fileName;
	private Map<String, String> options = new HashMap<String, String>();
	
	
	public Boolean getInline() {
		return inline;
	}
	public void setInline(Boolean inline) {
		this.inline = inline;
	}
	public Map<String, String> getOptions() {
		return options;
	}
	public void setOptions(Map<String, String> options) {
		this.options = options;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}