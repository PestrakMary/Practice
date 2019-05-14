<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text"/>
    <xsl:template match="/">
        =============
        | Lang list |
        =============
        <xsl:for-each select="languages/language">
            <xsl:value-of select="concat('&#13;','• ', name, ' by ', author, ', ', words, ' words. Year: ', @year,'. Extension: ', @extension, '.')"/>
        </xsl:for-each>


        <xsl:value-of select="concat('&#13;', 'Count of words: ', sum(languages/language/words), '&#13;')"/>
        <xsl:value-of select="concat('Average words count: ', sum(languages/language/words) div count(languages/language), '&#13;')"/>

    </xsl:template>
</xsl:stylesheet>