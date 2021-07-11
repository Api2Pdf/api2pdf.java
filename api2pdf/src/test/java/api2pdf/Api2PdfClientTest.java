package api2pdf;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.api2pdf.client.Api2PdfClient;
import com.api2pdf.models.Api2PdfResponse;
import com.api2pdf.models.Api2PdfBookmarkItemModel;

public class Api2PdfClientTest {

	private static Api2PdfClient a2pClient;

	@BeforeClass
	public static void initApi2PdfClient() {
		a2pClient = new Api2PdfClient("YOUR-API-KEY");
	}

	@Test
	public void testLibreofficeAnyToPdf() throws IOException {
		Api2PdfResponse response = a2pClient.libreofficeAnyToPdf("https://www.api2pdf.com/wp-content/themes/api2pdf/assets/samples/sample-word-doc.docx", true,
				"test.pdf");
		Assert.assertEquals(response.getSuccess(), true);
	}

	@Test
	public void testLibreofficeHtmlToDocx() throws IOException {
		Api2PdfResponse response = a2pClient.libreofficeHtmlToDocx("http://www.api2pdf.com/wp-content/uploads/2021/01/sampleHtml.html", true,
				"test.docx");
		Assert.assertEquals(response.getSuccess(), true);
	}

	@Test
	public void testLibreofficeHtmlToXlsx() throws IOException {
		Api2PdfResponse response = a2pClient.libreofficeHtmlToXlsx("http://www.api2pdf.com/wp-content/uploads/2021/01/sampleTables.html", true,
				"test.xlsx");
		Assert.assertEquals(response.getSuccess(), true);
	}

	@Test
	public void testLibreofficeThumbnail() throws IOException {
		Api2PdfResponse response = a2pClient.libreofficeThumbnail("https://www.api2pdf.com/wp-content/themes/api2pdf/assets/samples/sample-word-doc.docx", true,
				"test.png");
		Assert.assertEquals(response.getSuccess(), true);
	}

	@Test
	public void testLibreofficePdfToHtml() throws IOException {
		Api2PdfResponse response = a2pClient.libreofficePdfToHtml("http://www.api2pdf.com/wp-content/uploads/2021/01/1a082b03-2bd6-4703-989d-0443a88e3b0f-4.pdf", true,
				"test.html");
		Assert.assertEquals(response.getSuccess(), true);
	}

	@Test
	public void testPdfSharpMerge() throws IOException {
		String[] urls = { "http://www.api2pdf.com/wp-content/uploads/2021/01/1a082b03-2bd6-4703-989d-0443a88e3b0f-4.pdf",
				"http://www.api2pdf.com/wp-content/uploads/2021/01/1a082b03-2bd6-4703-989d-0443a88e3b0f-4.pdf" };
		Api2PdfResponse response = a2pClient.pdfsharpMerge(urls, true, "test.pdf");
		Assert.assertEquals(response.getSuccess(), true);
	}

	@Test
	public void testPdfSharpAddBookmark() throws IOException {
		String url = "http://www.api2pdf.com/wp-content/uploads/2021/01/1a082b03-2bd6-4703-989d-0443a88e3b0f-4.pdf";
		Api2PdfBookmarkItemModel[] bookmarks = new Api2PdfBookmarkItemModel[] { new Api2PdfBookmarkItemModel(0, "Title page") };
		Api2PdfResponse response = a2pClient.pdfsharpAddBookmarks(url, bookmarks, true, "test.pdf");
		Assert.assertEquals(response.getSuccess(), true);
	}

	@Test
	public void testPdfSharpAddPassword() throws IOException {
		String url = "http://www.api2pdf.com/wp-content/uploads/2021/01/1a082b03-2bd6-4703-989d-0443a88e3b0f-4.pdf";
		String password = "hello";
		Api2PdfResponse response = a2pClient.pdfsharpAddPassword(url, password, true, "test.pdf");
		Assert.assertEquals(response.getSuccess(), true);
	}

	@Test
	public void testWkhtmlHtmlToPdf() throws IOException {
		Api2PdfResponse response = a2pClient.wkhtmlHtmlToPdf("<p>test</p>",
				true, "test.pdf");
		Assert.assertEquals(response.getSuccess(), true);
	}

	@Test
	public void testWkhtmlHtmlToPdfWithOptions() throws IOException {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("orientation", "landscape");
		options.put("pageSize", "A4");
		Api2PdfResponse response = a2pClient.wkhtmlHtmlToPdf("<p>test</p>",
				true, "test.pdf", options);
		Assert.assertEquals(response.getSuccess(), true);
	}
	
	@Test
	public void testWkhtmlUrlToPdf() throws IOException {
		Api2PdfResponse response = a2pClient.wkhtmlUrlToPdf("https://www.api2pdf.com",
				true, "test.pdf");
		Assert.assertEquals(response.getSuccess(), true);
	}
	
	@Test
	public void testWkhtmlUrlToPdfWithOptions() throws IOException {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("orientation", "landscape");
		options.put("pageSize", "A4");
		Api2PdfResponse response = a2pClient.wkhtmlUrlToPdf("https://www.api2pdf.com",
				true, "test.pdf", options);
		Assert.assertEquals(response.getSuccess(), true);
	}
	
	@Test
	public void testChromeHtmlToPdf() throws IOException {
		Api2PdfResponse response = a2pClient.chromeHtmlToPdf("<p>test</p>",
				true, "test.pdf");
		Assert.assertEquals(response.getSuccess(), true);
	}
	
	@Test
	public void testChromeHtmlToPdfWithOptions() throws IOException {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("landscape", "true");
		Api2PdfResponse response = a2pClient.chromeHtmlToPdf("<p>test</p>",
				true, "test.pdf", options);
		Assert.assertEquals(response.getSuccess(), true);
	}
	
	@Test
	public void testChromeUrlToPdf() throws IOException {
		Api2PdfResponse response = a2pClient.chromeUrlToPdf("https://www.api2pdf.com",
				true, "test.pdf");
		Assert.assertEquals(response.getSuccess(), true);
	}
	
	@Test
	public void testChromeUrlToPdfWithOptions() throws IOException {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("orientation", "landscape");
		Api2PdfResponse response = a2pClient.chromeUrlToPdf("https://www.api2pdf.com",
				true, "test.pdf", options);
		Assert.assertEquals(response.getSuccess(), true);
	}

	@Test
	public void testChromeHtmlToImage() throws IOException {
		Api2PdfResponse response = a2pClient.chromeHtmlToImage("<p>test</p>",
				true, "test.png");
		Assert.assertEquals(response.getSuccess(), true);
	}
	
	@Test
	public void testChromeHtmlToImageWithOptions() throws IOException {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("fullPage", "false");
		Api2PdfResponse response = a2pClient.chromeHtmlToPdf("<p>test</p>",
				true, "test.png", options);
		Assert.assertEquals(response.getSuccess(), true);
	}

	@Test
	public void testChromeUrlToImage() throws IOException {
		Api2PdfResponse response = a2pClient.chromeUrlToImage("https://www.api2pdf.com",
				true, "test.png");
		Assert.assertEquals(response.getSuccess(), true);
	}
	
	@Test
	public void testChromeUrlToImageWithOptions() throws IOException {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("fullPage", "false");
		Api2PdfResponse response = a2pClient.chromeUrlToImage("https://www.api2pdf.com",
				true, "test.png", options);
		Assert.assertEquals(response.getSuccess(), true);
	}
}
