## Screenshots

- **Dynamic Forms**: You can enter any value into the fields, then specify the Sort Order (integer, 1 means the first element).
  ![1.png](/screenshots/1.png)
  ![2.png](/screenshots/2.png)

- **Use the provided XSL template file**: Any other XSLT template can be accepted, just use:
```xml
  <xsl:template match="/print-template-view-model">
  </xsl:template>
```
If you want to refer to any values you enter in your XSLT, use:
```xml
    <fo:block>
  <xsl:value-of select="/print-template-view-model/text-field-lines/*[id='text_field_1']/value-text"/>
</fo:block>
```
Where:

- `text-field-lines` -> collection of values you enter into the system
- `text_field_` -> ID of the value. If your first value is "test" and the second is "test2", then `text_field_1` equals "test" and `text_field_2` equals "test2".

Structure of the XSLT template
-

Example template can be found:
```
ExampleXSLTemplate/test.xsl
```

![8.png](.\screenshots\8.png)

For each to get back every values you enter the system.
![10.png](.\screenshots\10.png)
![11.png](.\screenshots\11.png)
![12.png](.\screenshots\12.png)


## XSLT 2.0+ processor with Apache FOP (POC)

POC (Proof of Concept) Using Apache FOP with SAXON HE for transforming XML TO PDF using XSLT 2.0+ (2.1,3.0,3.1) stylesheet


### Features:
- **Spring Boot Integration**: A simple Spring Boot web application with a controller to handle PDF generation.
- **XSLT 2.0+ Support**: Uses Saxon as the XSLT processor to leverage XSLT 2.0 features.
- **Apache FOP**: Utilizes Apache FOP to convert XSL-FO (Formatting Objects) documents into PDFs.


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


