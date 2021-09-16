package com.amazon.ata.maps.partsdiscovery;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.amazon.ata.maps.partsdiscovery.DevicePartDiscoveryTestHelper.getAllWordCounts;
import static com.amazon.ata.maps.partsdiscovery.DevicePartDiscoveryTestHelper.getIdfScores;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DevicePartDiscoveryCalculateIdfScoresTest {

    private DevicePartDiscovery discovery = new DevicePartDiscovery();

    @Test
    public void calculateIdfScores_wordCountMapsPopulated_scorePresentForEachWord() {
        // GIVEN
        List<Map<String, Integer>> wordCountMaps = getAllWordCounts();
        Map<String, Double> expectedIdfScores = getIdfScores(wordCountMaps);

        // WHEN
        Map<String, Double> idfScores = discovery.calculateIdfScores(wordCountMaps);

        // THEN
        assertEquals(expectedIdfScores.size(), idfScores.size(), "Expected a score to be present for each " +
            "word present in each wordCounts map");
    }

    @Test
    public void calculateIdfScores_wordInMaps_scoreCalculatedCorrectly() {
        // GIVEN
        List<Map<String, Integer>> wordCountMaps = getAllWordCounts();
        Map<String, Double> expectedIdfScores = getIdfScores(wordCountMaps);

        // WHEN
        Map<String, Double> actualIdfScores = discovery.calculateIdfScores(wordCountMaps);

        // THEN
        String testWord = "crankshaft";
        assertEquals(expectedIdfScores.get(testWord), actualIdfScores.get(testWord),
            String.format("Incorrect score calculated for: %s", testWord));
    }
}
