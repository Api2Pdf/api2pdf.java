# api2pdf.java
Java client for [Api2Pdf REST API](https://www.api2pdf.com/documentation) 

Api2Pdf.com is a REST API for instantly generating PDF documents from HTML, URLs, Microsoft Office Documents (Word, Excel, PPT), and images. The API also supports merge / concatenation of two or more PDFs. Api2Pdf is a wrapper for popular libraries such as **wkhtmltopdf**, **Headless Chrome**, and **LibreOffice**.

- [Installation](#installation)
- [Resources](#resources)
- [Authorization](#authorization)
- [Usage](#usage)
- [FAQ](https://www.api2pdf.com/faq)


## <a name="installation"></a>Installation

    Coming soon

## <a name="resources"></a>Resources

Resources this API supports:

- [wkhtmltopdf](#wkhtmltopdf)
- [Headless Chrome](#chrome)
- [LibreOffice](#libreoffice)
- [Merge / Concatenate PDFs](#merge)

## <a name="authorization"></a>Authorization

### Acquire API Key

Create an account at [portal.api2pdf.com](https://portal.api2pdf.com/register) to get your API key.
    
## <a name="#usage"></a>Usage

### Initialize the Client

All usage starts by requiring api2pdf and creating a new object.

    package com.api2pdf.client;
    import com.api2pdf.models.Api2PdfResponse;

    Api2PdfClient a2pClient = new Api2PdfClient('YOUR-API-KEY');


Once you initialize the client, you can make calls like so:

```
Api2PdfResponse pdfResponse = a2pClient.headlessChromeFromHtml("<p>test</p>", true, "test.pdf");
System.out.println(pdfResponse.getPdf());
```
    
### Successful Result Format

    {
	    'pdf': 'https://link-to-pdf-only-available-for-24-hours',
	    'mbIn': 0.08421039581298828,
	    'mbOut': 0.08830547332763672,
	    'cost': 0.00017251586914062501,
	    'success': true,
	    'error': null,
	    'responseId': '6e46637a-650d-46d5-af0b-3d7831baccbb'
    }
    
### Failed Result Format

    {
	    'success': false,
	    'error': 'some reason for the error',
	    'responseId': '6e46637a-650d-46d5-af0b-3d7831baccbb'
    }
    
## <a name="wkhtmltopdf"></a> wkhtmltopdf

**Convert HTML to PDF (load PDF in browser window (true or false) and specify a file name)**. 

```
Api2PdfResponse pdfResponse = a2pClient.wkhtmlToPdfFromHtml("<p>Hello, World</p>", true, "test.pdf");
System.out.println(pdfResponse.getPdf());
```
    
**Convert HTML to PDF (use HashMap for advanced wkhtmltopdf settings)**
[View full list of wkhtmltopdf options available.](https://www.api2pdf.com/documentation/advanced-options-wkhtmltopdf/)

```
Map<String, String> options = new HashMap<String, String>();
options.put("orientation", "landscape");
options.put("pageSize", "A4");
Api2PdfResponse pdfResponse = a2pClient.wkhtmlToPdfFromHtml("<p>Hello, World</p>", true, "test.pdf", options);
System.out.println(pdfResponse.getPdf());
```

**Convert URL to PDF (load PDF in browser window (true or false) and specify a file name)**.

```
Api2PdfResponse pdfResponse = a2pClient.wkhtmlToPdfFromUrl("https://www.github.com", true, "test.pdf");
System.out.println(pdfResponse.getPdf());
```
    
**Convert URL to PDF (use HashMap for advanced wkhtmltopdf settings)**
[View full list of wkhtmltopdf options available.](https://www.api2pdf.com/documentation/advanced-options-wkhtmltopdf/)

```
Map<String, String> options = new HashMap<String, String>();
options.put("orientation", "landscape");
options.put("pageSize", "A4");
Api2PdfResponse pdfResponse = a2pClient.wkhtmlToPdfFromUrl("https://www.github.com", true, "test.pdf", options);
System.out.println(pdfResponse.getPdf());
```

---

## <a name="chrome"></a>Headless Chrome

**Convert HTML to PDF (load PDF in browser window (true or false) and specify a file name)**

```
Api2PdfResponse pdfResponse = a2pClient.headlessChromeFromHtml("<p>Hello World</p>", true, "test.pdf");
System.out.println(pdfResponse.getPdf());
```
    
**Convert HTML to PDF (use HashMap for advanced Headless Chrome settings)**
[View full list of Headless Chrome options available.](https://www.api2pdf.com/documentation/advanced-options-headless-chrome/)

```
Map<String, String> options = new HashMap<String, String>();
options.put("landscape", "true");
Api2PdfResponse pdfResponse = a2pClient.headlessChromeFromHtml("<p>Hello World</p>", true, "test.pdf", options);
System.out.println(pdfResponse.getPdf());
```

**Convert URL to PDF (load PDF in browser window (true or false) and specify a file name)**

```
Api2PdfResponse pdfResponse = a2pClient.headlessChromeFromUrl("https://www.github.com", true, "test.pdf");
System.out.println(pdfResponse.getPdf());
``` 
    
**Convert URL to PDF (use HashMap for advanced Headless Chrome settings)**
[View full list of Headless Chrome options available.](https://www.api2pdf.com/documentation/advanced-options-headless-chrome/)

```
Map<String, String> options = new HashMap<String, String>();
options.put("landscape", "true");
Api2PdfResponse pdfResponse = a2pClient.headlessChromeFromUrl("https://www.github.com", true, "test.pdf", options);
System.out.println(pdfResponse.getPdf());
```
    
---

## <a name="libreoffice"></a>LibreOffice

LibreOffice supports the conversion to PDF from the following file formats:

- doc, docx, xls, xlsx, ppt, pptx, gif, jpg, png, bmp, rtf, txt, html

You must provide a url to the file. Our engine will consume the file at that URL and convert it to the PDF.

**Convert Microsoft Office Document or Image to PDF (load PDF in browser window (true or false) and specify a file name)**

```
Api2PdfResponse pdfResponse = a2pClient.libreofficeConvert("https://your-url-to-file", true, "test.pdf");
System.out.println(pdfResponse.getPdf());
```
    
---
    
## <a name="merge"></a>Merge / Concatenate Two or More PDFs

To use the merge endpoint, supply a list of urls to existing PDFs. The engine will consume all of the PDFs and merge them into a single PDF, in the order in which they were provided in the list.

**Merge PDFs from array of URLs to existing PDFs (load PDF in browser window (true or false) and specify a file name)****

```
String[] urls = { "your-url-to-pdf1.pdf", "your-url-to-pdf2.pdf" };
Api2PdfResponse pdfResponse = a2pClient.merge(urls, true, "test.pdf");
System.out.println(pdfResponse.getPdf());
```