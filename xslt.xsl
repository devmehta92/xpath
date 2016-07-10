<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html xmlns="http://www.w3.org/1999/xhtml">
            <head><title>Shopping Cart Results</title>
                <style>
                    body {
                    background-color:#F0F0F0;
                    font-family: Calibri;
                    }
                    td, th {
                    border: 1px solid transparent;
                    border-bottom: 1px solid #ddd;
                    height: 31px;
                    }
                    th {
                    background: #000;
                    color: #FFF;
                    font-weight: bold;
                    }
                    td {
                    text-align: center;
                    }
                </style>
            </head>
            <body>
                <center>
                <h3>Shopping Cart Results</h3>
                </center>
                <table>
                    <tr>
                        <th>PRODUCT ID</th>
                        <th>NAME</th>
                        <th>DESCRIPTION</th>
                        <th>SOURCE URL</th>
                        <th>PRICE</th>
                    </tr>
                    <xsl:for-each select="//items/product">
                    <tr>
                        <td>
                            <xsl:value-of select="@id"></xsl:value-of>
                        </td>
                        <td>
                            <xsl:value-of select="name"></xsl:value-of>
                        </td>
                        <td>
                            <xsl:value-of select="fullDescription"></xsl:value-of>
                        </td>
                        <td>
                            <img>
                                <xsl:attribute name="src">
                                    <xsl:value-of select="images/image/sourceURL"></xsl:value-of>
                                </xsl:attribute>
                            </img>
                        </td>
                        <td>
                            <xsl:value-of select="minPrice"></xsl:value-of>
                        </td>
                    </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>