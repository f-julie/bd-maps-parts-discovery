package com.amazon.ata.maps.partsdiscovery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DevicePartDiscoveryTestHelper {
    private DevicePartDiscoveryTestHelper(){}

    protected static  Map<String, Integer> calculateWordCounts(PartCatalog catalog) {
        Map<String, Integer> wordCounts = new HashMap<>();
        List<String> words = catalog.getCatalogWords();
        for (String word : words) {
            Integer count = wordCounts.get(word);
            if (count == null) {
                count = 0;
            }
            count = count + 1;
            wordCounts.put(word, count);
        }
        return wordCounts;
    }

    protected static String getMostUsedWord(Map<String, Integer> wordCounts) {
        List<Map.Entry<String,Integer>> entries = new ArrayList<>(wordCounts.entrySet());

        if (entries.isEmpty()) {
            return null;
        }

        entries.sort(Map.Entry.comparingByValue());
        Collections.reverse(entries);

        return entries.get(0).getKey();
    }

    protected static String getBestScoredWord(Map<String, Double> tfIdfScores) {
        List<Map.Entry<String,Double>> entries = new ArrayList<>(tfIdfScores.entrySet());

        if (entries.isEmpty()) {
            return null;
        }

        entries.sort(Map.Entry.comparingByValue());
        Collections.reverse(entries);

        return entries.get(0).getKey();
    }

    protected static Map<String, Double> getIdfScores(List<Map<String,Integer>> wordCountMaps) {
        Map<String, Double> idfScores = new HashMap<>();

        Map<String, Integer> dfScores = getDfScores(wordCountMaps);

        for(Map.Entry<String, Integer> dfScore : dfScores.entrySet()) {
            idfScores.put(dfScore.getKey(), Math.log10(wordCountMaps.size() / (double) dfScore.getValue()));
        }

        return idfScores;
    }

    protected static List<Map<String,Integer>> getAllWordCounts() {
        List<Map<String,Integer>> wordCountMaps = new ArrayList<>();
        for(PartManufacturer maufacturer : PartManufacturer.values()) {
            PartCatalog catalog = new PartCatalog(maufacturer, 2019, 10);
            wordCountMaps.add(calculateWordCounts(catalog));
        }
        return wordCountMaps;
    }

    protected static Map<String,Double> getTfIdfScores(Map<String, Integer> wordCounts, Map<String, Double> idfScores) {
        Map<String,Double> tfIdfScores = new HashMap<>();
        for (Map.Entry<String, Integer> wordCount : wordCounts.entrySet()) {
            String word = wordCount.getKey();
            Integer count = wordCount.getValue();
            tfIdfScores.put(word, count * idfScores.get(word));
        }

        return tfIdfScores;
    }

    private static Map<String,Integer> getDfScores(List<Map<String,Integer>> wordCountMaps) {
        Map<String, Integer> dfScores = new HashMap<>();
        for (Map<String,Integer> wordCounts : wordCountMaps) {
            for (String word : wordCounts.keySet()) {
                Integer value = dfScores.getOrDefault(word, 0);
                dfScores.put(word, value + 1);
            }
        }
        return dfScores;
    }
}
