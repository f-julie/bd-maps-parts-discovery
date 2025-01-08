package com.amazon.ata.maps.partsdiscovery;

import java.util.*;

/**
 * Helps expose key words from new editions of part catalogs.
 */
public class DevicePartDiscovery {

    // --- Part A ---
    /**
     * Calculate how often each word appears in a Catalog.
     * @param catalog The catalog to calculate word frequencies for.
     * @return A Map of words that appear in the catalog to the number of times they appear.
     */
    public Map<String, Integer> calculateWordCounts(PartCatalog catalog) {
        // PARTICIPANTS: Implement calculateWordCounts()
        // Map key = word
        // May value = count for the word
        Map<String, Integer> returnedMap = new HashMap<>();

        // Alternate solution for accessing words in the catalog using two lines instead of one (below)
        // List<String> theWords = catalog.getCatalogWords();
        // for (String aWord : theWords) { }

        // Go through the PartCatalog list of words and look at each word
        for (String aWord : catalog.getCatalogWords()) {

        // Check to see if the word is already in our Map
            if (returnedMap.containsKey(aWord)) {           // If it is, increment its count
                int currentCount = returnedMap.get(aWord);  // Retrieve current count for the word from the Map
                currentCount++;                             // Increment the current count for the word
                returnedMap.put(aWord, currentCount);       // Store the updated current count back in the Map
            } else { // If it is not
                returnedMap.put(aWord, 1); // Add the word to the Map with count of 1
            }

        } // end of the for-each loop

        return returnedMap;
    }

    // --- Part B ---
    /**
     * Removes a word from the provided word count map.
     * @param word the word to be removed
     * @param wordCounts the map to remove the word from
     */
    public void removeWord(String word, Map<String, Integer> wordCounts) {
        // PARTICIPANTS: Implement removeWord()
        // Remove the word given in parameter 1 from the Map given in parameter 2
        wordCounts.remove(word);
        return; // Optional since the method has a void return type
    }

    // --- Part C ---
    /**
     * Find the word that appears most frequently based on the word counts from a catalog.
     * @param wordCounts an association between a word and the total number of times it appeared in a catalog
     * @return The word that appears most frequently in the catalog to the number of times they appear.
     */
    public String getMostFrequentWord(Map<String, Integer> wordCounts) {
        // PARTICIPANTS: Implement getMostFrequentWord()

        // Remember the word and its count of the highest counted word so far
        String mostFrequentWord = null; // Word with the highest count so far
        int highestCountSoFar = -1; // Count for the highest word so far
        // Initialized so the very first word will have a higher count

        // We can't use a TreeMap to store word counts because the count is the value in the map
        // TreeMap stores entries in Key sequence - There is NO version of Map to store in value sequence

        // Go through the Map of words and store the word with the highest word count
        // .entrySet() - returns a set of Map entries
        // Map.Entry<> - define a single entry in a Map
        // one-entry-in-the-map : set-of-entries-in-the-map
        for (Map.Entry<String, Integer> anEntry : wordCounts.entrySet()) {
            // Compare the count of the current word to the count of the most frequent word so far
            if (anEntry.getValue() > highestCountSoFar) {   // If current word count is higher than the current highest word count
                mostFrequentWord = anEntry.getKey();        // remember the current word
                highestCountSoFar = anEntry.getValue();     // remember its count
            }
        } // end of for-loop
        // So when we're done going through the Map, we'll have the most frequently occurring word
        return mostFrequentWord;
    }

    // --- Part D ---
    /**
     * Calculates the TF-IDF score for each word in a catalog. The TF-IDF score for a word
     * is equal to the count * idf score. You can assume there will be an idfScore for each word
     * in wordCounts.
     * @param wordCounts - associates a count for each word from a catalog
     * @param idfScores - associates an IDF score for each word in the catalog
     * @return a map associating each word with its TF-IDF score.
     */
    public Map<String, Double> getTfIdfScores(Map<String, Integer> wordCounts, Map<String, Double> idfScores) {
        // PARTICIPANTS: Implement getTfIdfScores()
        // Instantiate the return object
        Map<String, Double> tfIdFScores = new TreeMap<>(); // Store entries in key sequence

        // Iterate through the wordCounts Map
        for (Map.Entry<String, Integer> anEntry : wordCounts.entrySet()) {
            // Calculate the Tf-Idf score for each entry and store it in the returned map
            // the-current-word, the-current-count * the-idfScore-for-current-word
            tfIdFScores.put(anEntry.getKey(), anEntry.getValue() * idfScores.get(anEntry.getKey()));
        }

        return tfIdFScores;
    }
}
