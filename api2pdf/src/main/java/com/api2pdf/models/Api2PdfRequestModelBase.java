package com.api2pdf.models;

import java.util.HashMap;
import java.util.Map;

public class Api2PdfRequestModelBase{
	private Boolean inlinePdf;
	private String fileName;
	private Map<String, String> options = new HashMap<String, String>();
	
	
	public Boolean getInlinePdf() {
		return inlinePdf;
	}
	public void setInlinePdf(Boolean inlinePdf) {
		this.inlinePdf = inlinePdf;
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