package com.hello.world;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

@Controller
class AppController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/generate-pdf")
    public void generatePdf(HttpServletResponse response) throws Exception {
        // Set up FopFactory and TransformerFactory
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        TransformerFactory transformerFactory = new net.sf.saxon.TransformerFactoryImpl();

        // Load XSLT and XML files
        ClassPathResource xsltFile = new ClassPathResource("template.xsl");
        ClassPathResource xmlFile = new ClassPathResource("data.xml");

        // Setup output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (InputStream xsltStream = xsltFile.getInputStream();
             InputStream xmlStream = xmlFile.getInputStream()) {

            // Setup FOP
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

            // Setup Transformer
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltStream));

            // Perform transformation and FOP processing
            transformer.transform(new StreamSource(xmlStream), new SAXResult(fop.getDefaultHandler()));
        }

        // Send the PDF as response
        response.setContentType("application/pdf");
        response.setContentLength(out.size());
        response.getOutputStream().write(out.toByteArray());
    }
}
