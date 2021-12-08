package com.amazon.ata.maps.partsdiscovery;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class responsible for reading and parsing a catalog into individual words.
 */
public class PartCatalogReader {
    private static final String BASE_CATALOG_PATH = "src/main/resources/partcatalogs";

    /**
     * Gets the text of a Catalog, removes all punctuation, and parses the text, splitting based on white space.
     * @param company The name of the company that publishes the catalog.
     * @param year The year of the edition of the catalog.
     * @param month The month in the year that the edition of the catalog was published.
     * @return Each word of the catalog collected into a List.
     */
    public static List<String> getCatalogWords(PartManufacturer company, int year, int month) {
        try {
            List<String> words = new ArrayList<>();
            String catalogPath = String.format("%s/%s%d-%d.txt",
                BASE_CATALOG_PATH, company.toString().toLowerCase(), year, month);
            System.out.println(System.getProperty("user.dir"));
            File catalogFile = new File(catalogPath);
            String catalogText = FileUtils.readFileToString(catalogFile, Charset.defaultCharset());
            String catalogTextNoPunctuation = catalogText.replaceAll("[^\\w\\s]|", "");
            words.addAll(Arrays.asList(catalogTextNoPunctuation.split("\\s+")));

            for (int i = 0; i < words.size(); i++) {
                words.set(i, words.get(i).toLowerCase());
            }

            return words;
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to access catalog data.", e);
        }
    }
}
