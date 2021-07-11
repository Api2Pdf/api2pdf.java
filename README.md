[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.api2pdf.client/api2pdf/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.api2pdf.client/api2pdf)

# api2pdf.java
Java client for [Api2Pdf REST API](https://www.api2pdf.com/documentation/v2) 

Api2Pdf.com is a powerful REST API for instantly generating PDF and Office documents from HTML, URLs, Microsoft Office Documents (Word, Excel, PPT), Email files, and images. You can generate image preview or thumbnail of a PDF, office document, or email file. The API also supports merge / concatenation of two or more PDFs, setting passwords on PDFs, and adding bookmarks to PDFs. Api2Pdf is a wrapper for popular libraries such as **wkhtmltopdf**, **Headless Chrome**, **PdfSharp**, and **LibreOffice**.

- [Installation](#installation)
- [Resources](#resources)
- [Authorization](#authorization)
- [Usage](#usage)
- [FAQ](https://www.api2pdf.com/faq)


## <a name="installation"></a>Installation

The Java client library is available as a Maven Package and can be installed with Maven by including the following dependency in your pom.xml file.
    
    <dependencies>
        <dependency>
            <groupId>com.api2pdf.client</groupId>
            <artifactId>api2pdf</artifactId>
            <version>2.0.0</version>
        </dependency>
    </dependencies>

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

All usage starts by importing the api2pdf library and creating a new object.

    package com.api2pdf.client;
    import com.api2pdf.models.Api2PdfResponse;

    Api2PdfClient a2pClient = new Api2PdfClient('YOUR-API-KEY');


Once you initialize the client, you can make calls like so:

```
Api2PdfResponse response = a2pClient.chromeHtmlToPdf("<p>test</p>", true, "test.pdf");
System.out.println(response.getFile());
```
    
### Successful Result Format

    {
	    'FileUrl': 'https://link-to-pdf-only-available-for-24-hours',
	    'Seconds': 0.08421039581298828,
	    'MbOut': 0.08830547332763672,
	    'Cost': 0.00017251586914062501,
	    'Success': true,
	    'Error': null,
	    'ResponseId': '6e46637a-650d-46d5-af0b-3d7831baccbb'
    }
    
### Failed Result Format

    {
	    'Success': false,
	    'Error': 'some reason for the error',
	    'ResponseId': '6e46637a-650d-46d5-af0b-3d7831baccbb'
    }
    
## <a name="wkhtmltopdf"></a> wkhtmltopdf

**Convert HTML to PDF (load PDF in browser window (true or false) and specify a file name)**. 

```
Api2PdfResponse response = a2pClient.wkhtmlHtmlToPdf("<p>Hello, World</p>", true, "test.pdf");
System.out.println(response.getFile());
```
    
**Convert HTML to PDF (use HashMap for advanced wkhtmltopdf settings)**
[View full list of wkhtmltopdf options available.](https://www.api2pdf.com/documentation/advanced-options-wkhtmltopdf/)

```
Map<String, String> options = new HashMap<String, String>();
options.put("orientation", "landscape");
options.put("pageSize", "A4");
Api2PdfResponse response = a2pClient.wkhtmlHtmlToPdf("<p>Hello, World</p>", true, "test.pdf", options);
System.out.println(response.getFile());
```

**Convert URL to PDF (load PDF in browser window (true or false) and specify a file name)**.

```
Api2PdfResponse response = a2pClient.wkhtmlUrlToPdf("https://www.github.com", true, "test.pdf");
System.out.println(response.getFile());
```
    
**Convert URL to PDF (use HashMap for advanced wkhtmltopdf settings)**
[View full list of wkhtmltopdf options available.](https://www.api2pdf.com/documentation/advanced-options-wkhtmltopdf/)

```
Map<String, String> options = new HashMap<String, String>();
options.put("orientation", "landscape");
options.put("pageSize", "A4");
Api2PdfResponse response = a2pClient.wkhtmlUrlToPdf("https://www.github.com", true, "test.pdf", options);
System.out.println(response.getFile());
```

---

## <a name="chrome"></a>Headless Chrome

**Convert HTML to PDF (load PDF in browser window (true or false) and specify a file name)**

```
Api2PdfResponse response = a2pClient.chromeHtmlToPdf("<p>Hello World</p>", true, "test.pdf");
System.out.println(response.getFile());
```
    
**Convert HTML to PDF (use HashMap for advanced Headless Chrome settings)**
[View full list of Headless Chrome options available.](https://www.api2pdf.com/documentation/advanced-options-headless-chrome/)

```
Map<String, String> options = new HashMap<String, String>();
options.put("landscape", "true");
Api2PdfResponse response = a2pClient.chromeHtmlToPdf("<p>Hello World</p>", true, "test.pdf", options);
System.out.println(response.getFile());
```

**Convert URL to PDF (load PDF in browser window (true or false) and specify a file name)**

```
Api2PdfResponse response = a2pClient.chromeUrlToPdf("https://www.github.com", true, "test.pdf");
System.out.println(response.getFile());
``` 
    
**Convert URL to PDF (use HashMap for advanced Headless Chrome settings)**
[View full list of Headless Chrome options available.](https://www.api2pdf.com/documentation/advanced-options-headless-chrome/)

```
Map<String, String> options = new HashMap<String, String>();
options.put("landscape", "true");
Api2PdfResponse response = a2pClient.chromeUrlToPdf("https://www.github.com", true, "test.pdf", options);
System.out.println(response.getFile());
```

**Convert HTML to Image (load PDF in browser window (true or false) and specify a file name)**

```
Api2PdfResponse response = a2pClient.chromeHtmlToImage("<p>Hello World</p>", true, "test.png");
System.out.println(response.getFile());
```

**Convert URL to Image (load PDF in browser window (true or false) and specify a file name)**

```
Api2PdfResponse response = a2pClient.chromeUrlToImage("https://www.github.com", true, "test.png");
System.out.println(response.getFile());
``` 
    
---

## <a name="libreoffice"></a>LibreOffice

Convert any office file to PDF, image file to PDF, email file to PDF, HTML to Word, HTML to Excel, and PDF to HTML. Any file that can be reasonably opened by LibreOffice should be convertible. Additionally, we have an endpoint for generating a *thumbnail* of the first page of your PDF or Office Document. This is great for generating an image preview of your files to users.

You must provide a url to the file. Our engine will consume the file at that URL and convert it to the PDF.

**Convert Microsoft Office Document or Image to PDF (load PDF in browser window (true or false) and specify a file name)**

```
Api2PdfResponse response = a2pClient.libreofficeAnyToPdf("https://www.api2pdf.com/wp-content/themes/api2pdf/assets/samples/sample-word-doc.docx", true, "test.pdf");
System.out.println(response.getFile());
```

**Thumbnail or Image Preview of a PDF or Office Document or Email file**

```
Api2PdfResponse response = a2pClient.libreofficeThumbnail("https://www.api2pdf.com/wp-content/themes/api2pdf/assets/samples/sample-word-doc.docx", true, "test.png");
System.out.println(response.getFile());
```

**Convert HTML to Microsoft Word or Docx**

```
Api2PdfResponse response = a2pClient.libreofficeHtmlToDocx("http://www.api2pdf.com/wp-content/uploads/2021/01/sampleHtml.html", true, "test.png");
System.out.println(response.getFile());
```

**Convert HTML to Microsoft Excel or Xlsx**

```
Api2PdfResponse response = a2pClient.libreofficeHtmlToDocx("http://www.api2pdf.com/wp-content/uploads/2021/01/sampleTables.html", true, "test.docx");
System.out.println(response.getFile());
```

**Convert HTML to Microsoft Excel or Xlsx**

```
Api2PdfResponse response = a2pClient.libreofficeHtmlToXlsx("http://www.api2pdf.com/wp-content/uploads/2021/01/sampleTables.html", true, "test.xlsx");
System.out.println(response.getFile());
```

**Convert PDF to HTML**

```
Api2PdfResponse response = a2pClient.libreofficeHtmlToXlsx("http://www.api2pdf.com/wp-content/uploads/2021/01/1a082b03-2bd6-4703-989d-0443a88e3b0f-4.pdf", true, "test.png");
System.out.println(response.getFile());
```

---
    
## <a name="merge"></a>PdfSharp

To use the merge endpoint, supply a list of urls to existing PDFs. The engine will consume all of the PDFs and merge them into a single PDF, in the order in which they were provided in the list.

**Merge PDFs from array of URLs to existing PDFs (load PDF in browser window (true or false) and specify a file name)****

```
String[] urls = { "your-url-to-pdf1.pdf", "your-url-to-pdf2.pdf" };
Api2PdfResponse response = a2pClient.pdfsharpMerge(urls, true, "test.pdf");
System.out.println(response.getFile());
```
