## XSLT 2.0 processor with Apache FOP (POC)

POC (Proof of Concept) Using Apache FOP with SAXON HE for transforming XML TO PDF using XSLT 2.0 stylesheet


### Features:
- **Spring Boot Integration**: A simple Spring Boot web application with a controller to handle PDF generation.
- **XSLT 2.0 Support**: Uses Saxon as the XSLT processor to leverage XSLT 2.0 features.
- **Apache FOP**: Utilizes Apache FOP to convert XSL-FO (Formatting Objects) documents into PDFs.

### Components:
- **XML Data**: The source XML data (`data.xml`) representing a catalog of books.
- **XSLT Transformation**: The XSLT stylesheet (`template.xsl`) that defines the transformation rules from XML to XSL-FO.
- **Spring Boot Application**: Java code to handle requests and generate the PDF.

### How It Works:
1. The user accesses the endpoint `/generate-pdf`.
2. The application loads the XML and XSLT files.
3. Saxon transforms the XML data into an XSL-FO document using the XSLT stylesheet.
4. Apache FOP processes the XSL-FO document to generate a PDF.
5. The generated PDF is sent to the user's browser.

### Prerequisites:
- Java 8 or higher
- Maven
- Basic knowledge of XML and XSLT

### Running the Application:
1. Clone the repository.
2. Navigate to the project directory and run `mvn spring-boot:run`.
3. Open a browser and go to `http://localhost:8080`.
4. Click on the "Generate PDF" link to view the generated PDF.

---


