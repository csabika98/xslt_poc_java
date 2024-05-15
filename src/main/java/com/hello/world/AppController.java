package com.hello.world;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {
    @GetMapping("/")
    public String home() {
        return "form";
    }

    @PostMapping("/generate-pdf")
    public void generatePdf(
            @RequestParam("fields") List<String> fields,
            @RequestParam("sortOrders") List<Optional<Integer>> sortOrders,
            @RequestParam("xslFile") MultipartFile xslFile,
            HttpServletResponse response
    ) throws Exception {
        // Generate XML from form fields
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlBuilder.append("<print-template-view-model>");
        xmlBuilder.append("<id>forms</id>");
        xmlBuilder.append("<country>us</country>");
        xmlBuilder.append("<language>en</language>");
        xmlBuilder.append("<text-field-lines xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:java=\"http://java.sun.com\" xsi:type=\"java.PrintAttributeLineTemplateViewModel\">");

        for (int i = 0; i < fields.size(); i++) {
            String field = fields.get(i);
            Optional<Integer> sortOrderOpt = sortOrders.get(i);

            if (field == null || field.isEmpty() || sortOrderOpt.isEmpty()) {
                continue; // Skip empty fields or sort orders
            }

            int sortOrder = sortOrderOpt.get();
            String column = sortOrder % 2 == 0 ? "right-column-attribute" : "left-column-attribute";

            xmlBuilder.append("<").append(column).append(" sort-order=\"").append(sortOrder).append("\">");
            xmlBuilder.append("<data-type>STRING</data-type>");
            xmlBuilder.append("<value-text>").append(field).append("</value-text>");
            xmlBuilder.append("<value>").append(field).append("</value>");
            xmlBuilder.append("<id>").append("text_field_").append(i + 1).append("</id>");
            xmlBuilder.append("</").append(column).append(">");
        }

        xmlBuilder.append("</text-field-lines>");
        xmlBuilder.append("</print-template-view-model>");
        String xmlContent = xmlBuilder.toString();
        System.out.println(xmlContent);

        // Convert XML string to InputStream
        try (InputStream xmlStream = new ByteArrayInputStream(xmlContent.getBytes());
             InputStream xsltStream = xslFile.getInputStream();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // Setup FopFactory and TransformerFactory
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            TransformerFactory transformerFactory = new net.sf.saxon.TransformerFactoryImpl();

            // Setup FOP
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

            // Setup Transformer
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltStream));

            // Perform transformation and FOP processing
            transformer.transform(new StreamSource(xmlStream), new SAXResult(fop.getDefaultHandler()));

            // Send the PDF as response
            response.setContentType("application/pdf");
            response.setContentLength(out.size());
            response.getOutputStream().write(out.toByteArray());
        }
    }
}

