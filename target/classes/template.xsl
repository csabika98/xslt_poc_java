<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">

    <!-- Set the output method to XML -->
    <xsl:output method="xml" indent="yes"/>

    <!-- Define the template that matches the root element -->
    <xsl:template match="/catalog">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simple">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="simple">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="12pt" font-family="sans-serif">
                        <fo:block font-weight="bold" font-size="14pt">Book Catalog</fo:block>
                        <fo:table table-layout="fixed" width="100%">
                            <fo:table-column column-width="2in"/>
                            <fo:table-column column-width="1in"/>
                            <fo:table-column column-width="1in"/>
                            <fo:table-header>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold">Title</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold">Author</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold">Price</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-header>
                            <fo:table-body>
                                <xsl:for-each select="book">
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="title"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="author"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="price"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:for-each>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
