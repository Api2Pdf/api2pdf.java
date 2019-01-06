package com.api2pdf.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import com.api2pdf.models.Api2PdfResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Api2PdfClient {
	private static final String API2PDF_BASE_URL = "https://v2018.api2pdf.com";
	private static final String API2PDF_MERGE = API2PDF_BASE_URL + "/merge";
	private static final String API2PDF_WKHTMLTOPDF_HTML = API2PDF_BASE_URL + "/wkhtmltopdf/html";
	private static final String API2PDF_WKHTMLTOPDF_URL = API2PDF_BASE_URL + "/wkhtmltopdf/url";
	private static final String API2PDF_CHROME_HTML = API2PDF_BASE_URL + "/chrome/html";
	private static final String API2PDF_CHROME_URL = API2PDF_BASE_URL + "/chrome/url";
	private static final String API2PDF_LIBREOFFICE_CONVERT = API2PDF_BASE_URL + "/libreoffice/convert";

	private String _apiKey;

	public Api2PdfClient(String apiKey) {
		this._apiKey = apiKey;
	}

	private HttpURLConnection getConnection(String endpoint) throws IOException {
		URL obj = new URL(endpoint);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", this._apiKey);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("User-Agent", "Api2Pdf Client");

		// For POST only - START
		con.setDoOutput(true);

		return con;

	}

	private Api2PdfResponse makeRequest(String payload, boolean inlinePdf, String fileName, HttpURLConnection con)
			throws IOException {
		// For POST only - START
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(payload);
		wr.flush();
		wr.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();
		String jsonResponse = response.toString();
		ObjectMapper objectMapper = new ObjectMapper();
		Api2PdfResponse api2pdfResponse = objectMapper.readValue(jsonResponse, Api2PdfResponse.class);
		return api2pdfResponse;
	}

	public Api2PdfResponse libreofficeConvert(String officeFileUrl, boolean inlinePdf, String fileName)
			throws IOException {
		HttpURLConnection con = getConnection(API2PDF_LIBREOFFICE_CONVERT);
		String payload = "{\"url\":\"" + officeFileUrl + "\",\"inlinePdf\":" + inlinePdf + ",\"fileName\":\"" + fileName
				+ "\"}";
		return makeRequest(payload, inlinePdf, fileName, con);
	}

	public Api2PdfResponse merge(String[] firstPdfUrls, boolean inlinePdf, String fileName) throws IOException {
		HttpURLConnection con = getConnection(API2PDF_MERGE);
		StringBuilder urls = new StringBuilder();
		for (String item : firstPdfUrls) {
			urls.append("\"" + item + "\", ");
		}
		urls.setLength(urls.length() - 2);
		String payload = "{\"urls\":[ " + urls + "],\"inlinePdf\":" + inlinePdf + ",\"fileName\":\"" + fileName + "\"}";
		return makeRequest(payload, inlinePdf, fileName, con);
	}

	public Api2PdfResponse wkhtmlToPdfFromHtml(String html, boolean inlinePdf, String fileName) throws IOException {
		HttpURLConnection con = getConnection(API2PDF_WKHTMLTOPDF_HTML);
		String payload = "{\"html\":\"" + html + "\",\"inlinePdf\":" + inlinePdf + ",\"fileName\":\"" + fileName
				+ "\"}";
		return makeRequest(payload, inlinePdf, fileName, con);
	}

	public Api2PdfResponse wkhtmlToPdfFromUrl(String url, boolean inlinePdf, String fileName) throws IOException {
		HttpURLConnection con = getConnection(API2PDF_WKHTMLTOPDF_URL);
		String payload = "{\"url\":\"" + url + "\",\"inlinePdf\":" + inlinePdf + ",\"fileName\":\"" + fileName + "\"}";
		return makeRequest(payload, inlinePdf, fileName, con);
	}

	public Api2PdfResponse headlessChromeFromHtml(String html, boolean inlinePdf, String fileName) throws IOException {
		HttpURLConnection con = getConnection(API2PDF_CHROME_HTML);
		String payload = "{\"html\":\"" + html + "\",\"inlinePdf\":" + inlinePdf + ",\"fileName\":\"" + fileName
				+ "\"}";
		return makeRequest(payload, inlinePdf, fileName, con);
	}

	public Api2PdfResponse headlessChromeFromHtml(String html, boolean inlinePdf, String fileName,
			Map<String, String> options) throws IOException {
		HttpURLConnection con = getConnection(API2PDF_CHROME_HTML);
		StringBuilder optionsPayload = new StringBuilder();
		for (String key : options.keySet()) {
			optionsPayload.append("\"" + key + "\":\"" + options.get(key).toString() + "\", ");
		}
		optionsPayload.setLength(optionsPayload.length() - 2);
		String payload = "{\"html\":\"" + html + "\",\"inlinePdf\":" + inlinePdf + ",\"fileName\":\"" + fileName
				+ "\", \"options\":{ " + optionsPayload + "}}";
		return makeRequest(payload, inlinePdf, fileName, con);
	}

	public Api2PdfResponse headlessChromeFromUrl(String url, boolean inlinePdf, String fileName) throws IOException {
		HttpURLConnection con = getConnection(API2PDF_CHROME_URL);
		String payload = "{\"url\":\"" + url + "\",\"inlinePdf\":" + inlinePdf + ",\"fileName\":\"" + fileName + "\"}";
		return makeRequest(payload, inlinePdf, fileName, con);
	}

	public Api2PdfResponse headlessChromeFromUrl(String url, boolean inlinePdf, String fileName,
			Map<String, String> options) throws IOException {
		HttpURLConnection con = getConnection(API2PDF_CHROME_URL);
		StringBuilder optionsPayload = new StringBuilder();
		for (String key : options.keySet()) {
			optionsPayload.append("\"" + key + "\":\"" + options.get(key).toString() + "\", ");
		}
		optionsPayload.setLength(optionsPayload.length() - 2);
		String payload = "{\"url\":\"" + url + "\",\"inlinePdf\":" + inlinePdf + ",\"fileName\":\"" + fileName
				+ "\", \"options\":{ " + optionsPayload + "}}";
		return makeRequest(payload, inlinePdf, fileName, con);
	}

}
