<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                version="2.0">

    <xsl:template match="/print-template-view-model">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="my-page"
                                       page-height="11in" page-width="8.5in" margin-top=".1in"
                                       margin-bottom=".9in" padding-left="2in" padding-right="2in">
                    <fo:region-body margin-top=".9in" margin-bottom=".1in"/>
                    <fo:region-before extent="1.8in" margin-top=".2in" margin-left=".2in"/>
                    <fo:region-after region-name="Footer"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="my-page">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block>
                        <fo:block>Title</fo:block>
                    </fo:block>
                </fo:static-content>
                <fo:static-content flow-name="Footer">
                    <fo:block>
                        <fo:retrieve-marker retrieve-class-name="footer" retrieve-position="last-starting-within-page"/>
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <xsl:for-each select="/print-template-view-model/text-field-lines/*">
                            <fo:block>
                                <fo:inline font-weight="bold">Value: </fo:inline>
                                <fo:inline><xsl:value-of select="value-text"/></fo:inline>
                            </fo:block>
                        </xsl:for-each>

                    </fo:block>
                    <fo:block>
                        Single value reference:
                        <xsl:value-of select="/print-template-view-model/text-field-lines/*[id='text_field_1']/value-text"/>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>


