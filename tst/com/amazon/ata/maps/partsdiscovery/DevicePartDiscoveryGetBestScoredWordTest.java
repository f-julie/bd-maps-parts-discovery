package com.amazon.ata.maps.partsdiscovery;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.amazon.ata.maps.partsdiscovery.DevicePartDiscoveryTestHelper.calculateWordCounts;
import static com.amazon.ata.maps.partsdiscovery.DevicePartDiscoveryTestHelper.getAllWordCounts;
import static com.amazon.ata.maps.partsdiscovery.DevicePartDiscoveryTestHelper.getIdfScores;
import static com.amazon.ata.maps.partsdiscovery.DevicePartDiscoveryTestHelper.getTfIdfScores;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DevicePartDiscoveryGetBestScoredWordTest {
    private DevicePartDiscovery discovery = new DevicePartDiscovery();

    @Test
    public void getBestScoredWords_wordInMaps_containsBestWord() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.ENGINETECH, 2019, 10);
        List<Map<String, Integer>> wordCountMaps = getAllWordCounts();
        Map<String, Double> idfScores = getIdfScores(wordCountMaps);
        Map<String, Integer> termFrequency = calculateWordCounts(catalog);

        // WHEN
        Map<String, Double> tfIdfScores = getTfIdfScores(termFrequency, idfScores);
        List<String> bestScoredWords = discovery.getBestScoredWords(tfIdfScores);

        // THEN
        assertEquals(10, bestScoredWords.size(), "Should return only top 10 words");
        List<String> expectedWords = ImmutableList.of("oil", "engine", "fuel", "view", "bearings",
            "tank", "schematic", "ball", "ohv", "pto");
        assertTrue(bestScoredWords.containsAll(expectedWords),
            String.format("Expected best scored list to be the same for %s. Expected {%s}, found {%s}",
            PartManufacturer.ENGINETECH, expectedWords, bestScoredWords));
    }
}
