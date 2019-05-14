<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:variable name="apos">'</xsl:variable>

    <xsl:output method="html"/>

    <xsl:template match="/">
        <html>
            <head>
                <style>
                    table {
                    background: url("https://html5book.ru/wp-content/uploads/2019/04/suggestions.png") 98% 86% no-repeat;
                    }
                    th {
                    font-weight: normal;
                    font-size: 14px;
                    color: black;
                    padding: 10px 12px;
                    background: white;
                    }
                    td {
                    font-weight: bold;
                    color: black;
                    border-top: 1px solid white;
                    padding: 10px 12px;
                    background: rgba(51, 51, 153, .2);
                    transition: .3s;
                    text-align:center;
                    }
                    tr:hover td {
                    background: rgba(51, 51, 153, .1);
                    }
                </style>
            </head>
            <body>
                <table>
                    <tr>
                        <td align="center"><strong>Name</strong></td>
                        <td align="center"><strong>Author</strong></td>
                        <td align="center"><strong>Extension</strong></td>
                        <td align="center"><strong>Year</strong></td>
                        <td align="center"><strong>Words</strong></td>
                    </tr>

                    <xsl:for-each select="languages/language">
                        <tr>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="author"/></td>
                            <td><xsl:value-of select="@extension"/></td>
                            <td><xsl:value-of select="@year"/></td>
                            <td><xsl:value-of select="words"/></td>
                        </tr>
                    </xsl:for-each>
                    <tr>
                        <td></td><td></td><td></td>
                        <td><strong>Count of words: </strong></td>
                        <td><xsl:value-of select="sum(languages/language/words)"/></td>
                    </tr>

                    <tr>
                        <td></td><td></td><td></td>
                        <td><strong>Average word count: </strong></td>
                        <td><xsl:value-of select="sum(languages/language/words) div count(languages/language)"/></td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>