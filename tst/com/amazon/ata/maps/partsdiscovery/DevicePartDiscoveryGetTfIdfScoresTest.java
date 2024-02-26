package com.amazon.ata.maps.partsdiscovery;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.amazon.ata.maps.partsdiscovery.DevicePartDiscoveryTestHelper.calculateWordCounts;
import static com.amazon.ata.maps.partsdiscovery.DevicePartDiscoveryTestHelper.getAllWordCounts;
import static com.amazon.ata.maps.partsdiscovery.DevicePartDiscoveryTestHelper.getIdfScores;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DevicePartDiscoveryGetTfIdfScoresTest {

    private DevicePartDiscovery discovery = new DevicePartDiscovery();


    @Test
    public void getTfIdfScores_wordCountMapPopulated_scorePresentForEachWord() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.ENGINETECH, 2019, 10);
        List<Map<String, Integer>> wordCountMaps = getAllWordCounts();
        Map<String, Double> idfScores = getIdfScores(wordCountMaps);
        Map<String, Integer> termFrequency = calculateWordCounts(catalog);

        // WHEN
        Map<String, Double> tfIdfScores = discovery.getTfIdfScores(termFrequency, idfScores);

        // THEN
        assertEquals(termFrequency.size(), tfIdfScores.size(), "Expected a score to be present for each " +
            "word present in the wordCounts map");
    }

    @Test
    public void getTfIdfScores_wordInMaps_scoreCalculatedCorrectly() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.ENGINETECH, 2019, 10);
        List<Map<String, Integer>> wordCountMaps = getAllWordCounts();
        Map<String, Double> idfScores = getIdfScores(wordCountMaps);
        Map<String, Integer> termFrequency = calculateWordCounts(catalog);

        // WHEN
        Map<String, Double> tfIdfScores = discovery.getTfIdfScores(termFrequency, idfScores);

        // THEN
        String testWord = "crankshaft";
        double expectedScore = termFrequency.get(testWord) * idfScores.get(testWord);
        assertEquals(expectedScore, tfIdfScores.get(testWord), String.format("Incorrect score calculated for: %s",
            testWord));
    }
}
