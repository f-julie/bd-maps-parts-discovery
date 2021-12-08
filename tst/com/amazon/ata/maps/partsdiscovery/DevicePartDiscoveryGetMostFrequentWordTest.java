package com.amazon.ata.maps.partsdiscovery;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.amazon.ata.maps.partsdiscovery.DevicePartDiscoveryTestHelper.calculateWordCounts;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DevicePartDiscoveryGetMostFrequentWordTest {

    private DevicePartDiscovery discovery = new DevicePartDiscovery();


    @Test
    public void getMostFrequentWord_engineTech_termFrequencyCorrectSize() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.ENGINETECH, 2019, 10);
        Map<String, Integer> wordCountMap = calculateWordCounts(catalog);

        // WHEN
        String mostFrequentWord = discovery.getMostFrequentWord(wordCountMap);

        // THEN
        String message = String.format("Top word for %s is [%s]. Found top word [%s].",
            PartManufacturer.ENGINETECH, "and", mostFrequentWord);
        assertEquals("and", mostFrequentWord, message);
    }

    @Test
    public void getMostFrequentWord_mouser_termFrequencyCorrectSize() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.MOUSER, 2019, 10);
        Map<String, Integer> wordCountMap = calculateWordCounts(catalog);

        // WHEN
        String mostFrequentWord = discovery.getMostFrequentWord(wordCountMap);

        // THEN
        String message = String.format("Top word for %s is [%s]. Found top word [%s].",
            PartManufacturer.MOUSER, "the", mostFrequentWord);
        assertEquals("the", mostFrequentWord, message);
    }

    @Test
    public void getMostFrequentWord_partsTech_termFrequencyCorrectSize() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.PARTSTECH, 2019, 10);
        Map<String, Integer> wordCountMap = calculateWordCounts(catalog);

        // WHEN
        String mostFrequentWord = discovery.getMostFrequentWord(wordCountMap);

        // THEN
        String message = String.format("Top word for %s is [%s]. Found top word [%s].",
            PartManufacturer.PARTSTECH, "and", mostFrequentWord);
        assertEquals("and", mostFrequentWord, message);
    }

    @Test
    public void getMostFrequentWord_techSmartParts_termFrequencyCorrectSize() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.TECHSMART_PARTS, 2019, 10);
        Map<String, Integer> wordCountMap = calculateWordCounts(catalog);

        // WHEN
        String mostFrequentWord = discovery.getMostFrequentWord(wordCountMap);

        // THEN
        String message = String.format("Top word for %s is [%s]. Found top word [%s].",
            PartManufacturer.TECHSMART_PARTS, "the", mostFrequentWord);
        assertEquals("the", mostFrequentWord, message);
    }

    @Test
    public void getMostFrequentWord_wellSearchParts_termFrequencyCorrectSize() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.WELLS_SEARCH_PARTS, 2019, 10);
        Map<String, Integer> wordCountMap = calculateWordCounts(catalog);

        // WHEN
        String mostFrequentWord = discovery.getMostFrequentWord(wordCountMap);

        // THEN
        String message = String.format("Top word for %s is [%s]. Found top word [%s].",
            PartManufacturer.WELLS_SEARCH_PARTS, "the", mostFrequentWord);
        assertEquals("the", mostFrequentWord, message);
    }
}
