package com.api2pdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Api2PdfResponse {

	@JsonProperty("ResponseId")
	private String ResponseId;

	@JsonProperty("Error")
	private String Error;

	@JsonProperty("MbOut")
	private String MbOut;

	@JsonProperty("FileUrl")
	private String FileUrl;

	@JsonProperty("Success")
	private Boolean Success;

	@JsonProperty("Cost")
	private String Cost;

	@JsonProperty("Seconds")
	private String Seconds;

	public String getResponseId ()
	{
		return ResponseId;
	}

	public void setResponseId (String responseId)
	{
		this.ResponseId = responseId;
	}

	public String getError ()
	{
		return Error;
	}

	public void setError (String error)
	{
		this.Error = error;
	}

	public String getMbOut ()
	{
		return MbOut;
	}

	public void setMbOut (String mbOut)
	{
		this.MbOut = mbOut;
	}

	public String getFile ()
	{
		return FileUrl;
	}

	public void setFile (String fileUrl)
	{
		this.FileUrl = fileUrl;
	}

	public Boolean getSuccess ()
	{
		return Success;
	}

	public void setSuccess (Boolean success)
	{
		this.Success = success;
	}

	public String getCost ()
	{
		return Cost;
	}

	public void setCost (String cost)
	{
		this.Cost = cost;
	}

	public String getSeconds ()
	{
		return Seconds;
	}

	public void setSeconds (String seconds)
	{
		this.Seconds = seconds;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [responseId = "+ResponseId+", error = "+Error+", mbOut = "+MbOut+", pdf = "+FileUrl+", success = "+Success+", cost = "+Cost+", seconds = "+Seconds+"]";
	}
}
