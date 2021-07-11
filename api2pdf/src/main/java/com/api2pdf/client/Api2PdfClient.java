package com.api2pdf.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.api2pdf.models.Api2PdfFromHtmlRequestModel;
import com.api2pdf.models.Api2PdfFromUrlRequestModel;
import com.api2pdf.models.Api2PdfMergeRequestModel;
import com.api2pdf.models.Api2PdfBookmarksRequestModel;
import com.api2pdf.models.Api2PdfPasswordRequestModel;
import com.api2pdf.models.Api2PdfBookmarkItemModel;
import com.api2pdf.models.Api2PdfResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Api2PdfClient {
	private static final String API2PDF_BASE_URL = "https://v2.api2pdf.com";
	private String _baseUrl;
	private String _apiKey;

	public Api2PdfClient(String apiKey) {
		this._apiKey = apiKey;
		this._baseUrl = API2PDF_BASE_URL;
	}

	public Api2PdfClient(String apiKey, String overrideBaseUrl) {
		this._apiKey = apiKey;
		this._baseUrl = overrideBaseUrl;
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

	private Api2PdfResponse makeRequest(String payload, HttpURLConnection con)
			throws IOException {
		// For POST only - START
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, StandardCharsets.UTF_8));
		writer.write(payload);
		writer.close();

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode + " ");
		System.out.println(payload);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();
		String jsonResponse = response.toString();


		System.out.println(jsonResponse);

		ObjectMapper objectMapper = new ObjectMapper();
		Api2PdfResponse api2pdfResponse = objectMapper.readValue(jsonResponse, Api2PdfResponse.class);
		return api2pdfResponse;
	}

	public Api2PdfResponse libreofficeAnyToPdf(String url, boolean inline, String fileName)
			throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/libreoffice/any-to-pdf");
		Api2PdfFromUrlRequestModel model = new Api2PdfFromUrlRequestModel();
		model.setUrl(url);
		model.setInline(inline);
		model.setFileName(fileName);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse libreofficeHtmlToDocx(String url, boolean inline, String fileName)
			throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/libreoffice/html-to-docx");
		Api2PdfFromUrlRequestModel model = new Api2PdfFromUrlRequestModel();
		model.setUrl(url);
		model.setInline(inline);
		model.setFileName(fileName);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse libreofficeHtmlToXlsx(String url, boolean inline, String fileName)
			throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/libreoffice/html-to-xlsx");
		Api2PdfFromUrlRequestModel model = new Api2PdfFromUrlRequestModel();
		model.setUrl(url);
		model.setInline(inline);
		model.setFileName(fileName);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse libreofficeThumbnail(String url, boolean inline, String fileName)
			throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/libreoffice/thumbnail");
		Api2PdfFromUrlRequestModel model = new Api2PdfFromUrlRequestModel();
		model.setUrl(url);
		model.setInline(inline);
		model.setFileName(fileName);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse libreofficePdfToHtml(String url, boolean inline, String fileName)
			throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/libreoffice/pdf-to-html");
		Api2PdfFromUrlRequestModel model = new Api2PdfFromUrlRequestModel();
		model.setUrl(url);
		model.setInline(inline);
		model.setFileName(fileName);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse pdfsharpMerge(String[] pdfUrls, boolean inline, String fileName) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/pdfsharp/merge");
		Api2PdfMergeRequestModel model = new Api2PdfMergeRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setUrls(pdfUrls);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse pdfsharpAddBookmarks(String url, Api2PdfBookmarkItemModel[] bookmarks, boolean inline, String fileName) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/pdfsharp/bookmarks");
		Api2PdfBookmarksRequestModel model = new Api2PdfBookmarksRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setUrl(url);
		model.setBookmarks(bookmarks);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse pdfsharpAddPassword(String url, String password, boolean inline, String fileName) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/pdfsharp/password");
		Api2PdfPasswordRequestModel model = new Api2PdfPasswordRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setUrl(url);
		model.setPassword(password);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse wkhtmlHtmlToPdf(String html, boolean inline, String fileName) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/wkhtml/pdf/html");
		Api2PdfFromHtmlRequestModel model = new Api2PdfFromHtmlRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setHtml(html);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse wkhtmlHtmlToPdf(String html, boolean inline, String fileName,
			Map<String, String> options) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/wkhtml/pdf/html");
		Api2PdfFromHtmlRequestModel model = new Api2PdfFromHtmlRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setHtml(html);
		model.setOptions(options);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse wkhtmlUrlToPdf(String url, boolean inline, String fileName) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/wkhtml/pdf/url");
		Api2PdfFromUrlRequestModel model = new Api2PdfFromUrlRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setUrl(url);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse wkhtmlUrlToPdf(String url, boolean inline, String fileName, Map<String, String> options)
			throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/wkhtml/pdf/url");
		Api2PdfFromUrlRequestModel model = new Api2PdfFromUrlRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setUrl(url);
		model.setOptions(options);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse chromeHtmlToPdf(String html, boolean inline, String fileName) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/chrome/pdf/html");
		Api2PdfFromHtmlRequestModel model = new Api2PdfFromHtmlRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setHtml(html);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse chromeHtmlToPdf(String html, boolean inline, String fileName,
			Map<String, String> options) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/chrome/pdf/html");
		Api2PdfFromHtmlRequestModel model = new Api2PdfFromHtmlRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setHtml(html);
		model.setOptions(options);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse chromeUrlToPdf(String url, boolean inline, String fileName) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/chrome/pdf/url");
		Api2PdfFromUrlRequestModel model = new Api2PdfFromUrlRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setUrl(url);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse chromeUrlToPdf(String url, boolean inline, String fileName,
			Map<String, String> options) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/chrome/pdf/url");
		Api2PdfFromUrlRequestModel model = new Api2PdfFromUrlRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setUrl(url);
		model.setOptions(options);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse chromeHtmlToImage(String html, boolean inline, String fileName) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/chrome/image/html");
		Api2PdfFromHtmlRequestModel model = new Api2PdfFromHtmlRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setHtml(html);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse chromeHtmlToImage(String html, boolean inline, String fileName,
			Map<String, String> options) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/chrome/image/html");
		Api2PdfFromHtmlRequestModel model = new Api2PdfFromHtmlRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setHtml(html);
		model.setOptions(options);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse chromeUrlToImage(String url, boolean inline, String fileName) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/chrome/image/url");
		Api2PdfFromUrlRequestModel model = new Api2PdfFromUrlRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setUrl(url);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}

	public Api2PdfResponse chromeUrlToImage(String url, boolean inline, String fileName,
			Map<String, String> options) throws IOException {
		HttpURLConnection con = getConnection(this._baseUrl + "/chrome/image/url");
		Api2PdfFromUrlRequestModel model = new Api2PdfFromUrlRequestModel();
		model.setFileName(fileName);
		model.setInline(inline);
		model.setUrl(url);
		model.setOptions(options);
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(model);
		return makeRequest(payload, con);
	}
}
