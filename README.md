# Mining Device Part Catalogs Guided Project

## Activity

This activity will explore using `Map`s to mine data from catalog files. Data mining is the process 
of exploring large sets of data for new information, anomalies, or meaningful patterns. 

## PART A - `calculateWordCounts`

To find newly released parts and technology, Amazon is experimenting with mining data from 
part manufacturer catalogs. This process has not yet been perfected. Manufacturers release 
part catalogs monthly. At first, we were generating every difference between monthly releases 
and having a human take a look at each. Smaller changes aren’t too bad to review, but a lot 
of time is wasted with changes that are irrelevant to parts, and larger changes like moving 
around the sections can be a real nightmare!

The team believes that we can be more efficient if we instead review the **counts** of words 
in a catalog. The new parts and technology must be mentioned a lot! 

You will be helping by implementing the method `calculateWordCounts(PartCatalog catalog)` which 
will return counts for each word in the catalog.

Implement the `calculateWordCounts()` method in the `DevicePartDiscovery` class in the `com.amazon.ata.maps.partsdiscovery` package. 

The tests in  `DevicePartDiscoveryCalculateWordCountsTest` will ensure you have implemented it correctly. You can run these from IntelliJ.

## PART B - `removeWord`

It turns out there are quite a few words that are mentioned a lot, but aren’t relevant. This 
includes words like  “the”, “a”, “is”, etc. We need a way to remove these words from our word 
count map. Next, you will be implementing the method: `void removeWord(String word, Map<String, Integer>)`.

Implement the `removeWord()` method in the `DevicePartDiscovery` class. 
   
The tests in `DevicePartDiscoveryRemoveWordTest` will ensure you have implemented it correctly. You can run these from IntelliJ.


## PART C - `getMostFrequentWord`

Now that we’ve removed the common words from our word count map, let’s find the most 
frequent word in our word counts map. You will be implementing the method: 
`String getMostFrequentWord(Map<String, Integer> wordCounts)`. 

Implement the `getMostFrequentWord()` method in the `DevicePartDiscovery` 
   class. 

The tests in `DevicePartDiscoveryGetMostFrequentWordTest` will ensure you have 
   implemented it correctly. You can run these from Intellij.
## PART D - `getTfIdfScores`

This still isn’t highlighting the words we were hoping to find. There seems to be a lot 
of noise (when we say there is noise in the data, we might mean corrupted data or in 
this case meaningless information). Every monthly part catalog seems to mention the 
same words, a lot! When we look through the most frequent words we aren’t finding anything 
new.

We think we’ve uncovered something useful though: TF-IDF. TF-IDF stands for 
Term Frequency - Inverse Document Frequency. This is a score that can help tell us how 
unique a word is in our catalog. The higher the score, the more unique the word is. It 
requires many related documents in order to get to know the common words in a space. We 
can use all the previous monthly part catalogs for this. The word “the” may appear many 
times in our new monthly catalog, but it also appears in every other monthly catalog. 
The word “the” will have a very low score, maybe even 0. Next lets consider the word 
"MAX32664" which appears often in our new catalog, but not in any others. This will 
result in a high score, and is likely a new part!

First, we decide on a document to calculate the TF-IDF scores for. This will be our 
newest monthly part catalog. Then we do the following:

1. Create a map for the TF score for each word in the new monthly catalog. 
   This stands for Term Frequency, just a fancy way of saying the word counts 
   we calculated in step 1. For example, we might have 100 counts of “the” and 20 
   counts of “MAX32664”. This step yields: `Map<String, Integer> wordCounts`.
<br/><br/>
2. Then we calculate the IDF scores for each word in the new monthly catalog. This 
   stands for Inverse Document Frequency, meaning we provide scores that are higher 
   if the word is less frequent (the inverse of more frequent). For example, if we 
   have 100 previous monthly catalogs, and “the” appears in all of them we will have 
   a score of 0, but “MAX32664” appears in only 1 of them, we will have a score of
<br/><br/>
   If you are interested in the math that happens in this step you
   can read more [here](https://www.onely.com/blog/what-is-tf-idf/). This step yields: 
   `Map<String, Double> idfScores`.
   <br/><br/>   
3. To calculate a words TF - IDF score, we multiply the TF score by the IDF score. 
   So for “the” the score will be 0 (100 * 0), and for “MAX32664” the score will be 
   40 (20 * 2). If we do this for each word in our new monthly catalog this yields: 
   `Map<String, Double> tfIdfScores`.
<br/><br/>

In this part you will be implementing a method that calculates the TF-IDF score for each 
word in a catalog. The wordCounts are provided to you, as well as the TF-IDF scores:
`Map<String, Double> getTfIdfScores(Map<String, Integer> wordCounts, Map<String, Double> idfScores)`

Implement the `getTfIdfScores()` method in the `DevicePartDiscovery` class in 
   the `com.amazon.ata.maps.partsdiscovery` package. 

The tests in 
   `DevicePartDiscoveryGetTfIdfScoresTest` will ensure you have implemented it correctly. You 
   can run these from IntelliJ.