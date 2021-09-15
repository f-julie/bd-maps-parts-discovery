package com.amazon.ata.maps.partsdiscovery;

import java.util.List;

/**
 * Represents a monthly part catalog released by a manufacturer.
 */
public class PartCatalog {
    private PartManufacturer manufacturer;
    private int year;
    private int month;
    private List<String> catalogWordList;

    /**
     * Instantiates a PartCatalog object. Parses a catalog file and populates the catalogWordList.
     * @param manufacturer - the manufacturer that published this catalog
     * @param year - the year the catalog was published
     * @param month - the month the catalog was published
     */
    public PartCatalog(PartManufacturer manufacturer, int year, int month) {
        this.manufacturer = manufacturer;
        this.year = year;
        this.month = month;

        catalogWordList = PartCatalogReader.getCatalogWords(manufacturer, year, month);
    }

    public PartManufacturer getManufacturer() {
        return manufacturer;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public List<String> getCatalogWords() {
        return catalogWordList;
    }

}
