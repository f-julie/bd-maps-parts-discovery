package com.amazon.ata.maps.partsdiscovery;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.amazon.ata.maps.partsdiscovery.DevicePartDiscoveryTestHelper.calculateWordCounts;
import static org.junit.jupiter.api.Assertions.assertNull;


public class DevicePartDiscoveryRemoveWordTest {

    private DevicePartDiscovery discovery = new DevicePartDiscovery();


    @Test
    public void removeWord_wordPresent_wordNotInMap() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.MOUSER, 2019, 10);
        Map<String, Integer> termFrequency = calculateWordCounts(catalog);
        String termToRemove = "dantetm";

        // WHEN
        discovery.removeWord(termToRemove, termFrequency);

        // THEN
        assertNull(termFrequency.get(termToRemove), String.format("Expected %s to be removed from word count map: %s.",
            termToRemove, termFrequency));
    }

    @Test
    public void removeWord_wordNotPresent_wordNotInMap() {
        // GIVEN
        PartCatalog catalog = new PartCatalog(PartManufacturer.MOUSER, 2019, 10);
        Map<String, Integer> termFrequency = calculateWordCounts(catalog);
        String termToRemove = "ata";

        // WHEN
        discovery.removeWord(termToRemove, termFrequency);

        // THEN
        assertNull(termFrequency.get(termToRemove), String.format("Expected %s to never be present in word count " +
            "map: %s.", termToRemove, termFrequency));
    }
}
