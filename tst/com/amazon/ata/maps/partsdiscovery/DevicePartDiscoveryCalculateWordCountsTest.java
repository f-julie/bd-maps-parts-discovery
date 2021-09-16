package com.amazon.ata.maps.partsdiscovery;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.amazon.ata.maps.partsdiscovery.DevicePartDiscoveryTestHelper.getMostUsedWord;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DevicePartDiscoveryCalculateWordCountsTest {

    private DevicePartDiscovery discovery = new DevicePartDiscovery();

    @Test
    public void calculateTermFrequency_engineTech_termFrequencyCorrectSize() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.ENGINETECH, 2019, 10);

        // WHEN
        Map<String, Integer> termFrequency = discovery.calculateWordCounts(catalog);

        // THEN
        assertEquals(271, termFrequency.size());
        assertEquals(59, termFrequency.get("and"));
        assertEquals(14, termFrequency.get("the"));
        assertEquals(23, termFrequency.get("view"));
        assertEquals(null, termFrequency.get("dantetm"));
    }

    @Test
    public void calculateTermFrequency_engineTech_correctMostUsedWord() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.ENGINETECH, 2019, 10);

        // WHEN
        Map<String, Integer> termFrequency = discovery.calculateWordCounts(catalog);
        String mostFrequentWord = getMostUsedWord(termFrequency);

        // THEN
        String keyMessage = String.format("Expected most used word for %s is [%s]. Your top word was [%s].",
            PartManufacturer.ENGINETECH, "and", mostFrequentWord);
        assertEquals("and", mostFrequentWord, keyMessage);

        String valueMessage = String.format("Expected most used word for %s to have a value of [%s]. Your top word had a value of [%s].",
            PartManufacturer.ENGINETECH, 59, termFrequency.get(mostFrequentWord));
        assertEquals(59, termFrequency.get(mostFrequentWord), valueMessage);
    }

    @Test
    public void calculateTermFrequency_mouser_termFrequencyCorrectSize() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.MOUSER, 2019, 10);

        // WHEN
        Map<String, Integer> termFrequency = discovery.calculateWordCounts(catalog);

        // THEN
        assertEquals(854, termFrequency.size());
        assertEquals(61, termFrequency.get("and"));
        assertEquals(114, termFrequency.get("the"));
        assertEquals(null, termFrequency.get("view"));
        assertEquals(24, termFrequency.get("dantetm"));
    }

    @Test
    public void calculateTermFrequency_mouser_correctMostUsedWord() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.MOUSER, 2019, 10);

        // WHEN
        Map<String, Integer> termFrequency = discovery.calculateWordCounts(catalog);
        String mostFrequentWord = getMostUsedWord(termFrequency);

        // THEN
        String keyMessage = String.format("Expected most used word for %s is [%s]. Your top word was [%s].",
            PartManufacturer.MOUSER, "the", mostFrequentWord);
        assertEquals("the", mostFrequentWord, keyMessage);

        String valueMessage = String.format("Expected most used word for %s to have a value of [%s]. Your top word had a value of [%s].",
            PartManufacturer.MOUSER, 114, termFrequency.get(mostFrequentWord));
        assertEquals(114, termFrequency.get(mostFrequentWord), valueMessage);
    }

    @Test
    public void calculateTermFrequency_partsTech_termFrequencyCorrectSize() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.PARTSTECH, 2019, 10);

        // WHEN
        Map<String, Integer> termFrequency = discovery.calculateWordCounts(catalog);

        // THEN
        assertEquals(1295, termFrequency.size());
        assertEquals(183, termFrequency.get("and"));
        assertEquals(129, termFrequency.get("the"));
        assertEquals(null, termFrequency.get("view"));
        assertEquals(37, termFrequency.get("sic"));
    }

    @Test
    public void calculateTermFrequency_partsTech_correctMostUsedWord() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.PARTSTECH, 2019, 10);

        // WHEN
        Map<String, Integer> termFrequency = discovery.calculateWordCounts(catalog);
        String mostFrequentWord = getMostUsedWord(termFrequency);

        // THEN
        String keyMessage = String.format("Expected most used word for %s is [%s]. Your top word was [%s].",
            PartManufacturer.PARTSTECH, "and", mostFrequentWord);
        assertEquals("and", mostFrequentWord, keyMessage);

        String valueMessage = String.format("Expected most used word for %s to have a value of [%s]. Your top word had a value of [%s].",
            PartManufacturer.PARTSTECH, 183, termFrequency.get(mostFrequentWord));
        assertEquals(183, termFrequency.get(mostFrequentWord), valueMessage);
    }

    @Test
    public void calculateTermFrequency_techSmartParts_termFrequencyCorrectSize() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.TECHSMART_PARTS, 2019, 10);

        // WHEN
        Map<String, Integer> termFrequency = discovery.calculateWordCounts(catalog);

        // THEN
        assertEquals(1263, termFrequency.size());
        assertEquals(218, termFrequency.get("and"));
        assertEquals(227, termFrequency.get("the"));
        assertEquals(null, termFrequency.get("view"));
        assertEquals(28, termFrequency.get("captivate"));
    }

    @Test
    public void calculateTermFrequency_techSmartParts_correctMostUsedWord() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.TECHSMART_PARTS, 2019, 10);

        // WHEN
        Map<String, Integer> termFrequency = discovery.calculateWordCounts(catalog);
        String mostFrequentWord = getMostUsedWord(termFrequency);


        // THEN
        // THEN
        String keyMessage = String.format("Expected most used word for %s is [%s]. Your top word was [%s].",
            PartManufacturer.TECHSMART_PARTS, "the", mostFrequentWord);
        assertEquals("the", mostFrequentWord, keyMessage);

        String valueMessage = String.format("Expected most used word for %s to have a value of [%s]. Your top word had a value of [%s].",
            PartManufacturer.TECHSMART_PARTS, 227, termFrequency.get(mostFrequentWord));
        assertEquals(227, termFrequency.get(mostFrequentWord), valueMessage);
    }

    @Test
    public void calculateTermFrequency_wellSearchParts_termFrequencyCorrectSize() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.WELLS_SEARCH_PARTS, 2019, 10);

        // WHEN
        Map<String, Integer> termFrequency = discovery.calculateWordCounts(catalog);

        // THEN
        assertEquals(766, termFrequency.size());
        assertEquals(73, termFrequency.get("and"));
        assertEquals(91, termFrequency.get("the"));
        assertEquals(null, termFrequency.get("view"));
        assertEquals(27, termFrequency.get("parts"));
    }

    @Test
    public void calculateTermFrequency_wellSearchParts_correctMostUsedWord() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.WELLS_SEARCH_PARTS, 2019, 10);

        // WHEN
        Map<String, Integer> termFrequency = discovery.calculateWordCounts(catalog);
        String mostFrequentWord = getMostUsedWord(termFrequency);

        // THEN
        // THEN
        String keyMessage = String.format("Expected most used word for %s is [%s]. Your top word was [%s].",
            PartManufacturer.WELLS_SEARCH_PARTS, "the", mostFrequentWord);
        assertEquals("the", mostFrequentWord, keyMessage);

        String valueMessage = String.format("Expected most used word for %s to have a value of [%s]. Your top word had a value of [%s].",
            PartManufacturer.WELLS_SEARCH_PARTS, 91, termFrequency.get(mostFrequentWord));
        assertEquals(91, termFrequency.get(mostFrequentWord), valueMessage);
    }
}
