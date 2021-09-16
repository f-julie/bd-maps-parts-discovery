# Mining Device Part Catalogs GILA 

## GILA Roles

### Read more about the roles: [GILA Roles](https://w.amazon.com/bin/view/Amazon_Technical_Academy/Internal/HowTos/GILARoles)

## Activity

This activity will explore using `Map`s to mine data from catalog files. Data mining is the process 
of exploring large sets of data for new information, anomalies, or meaningful patterns. 

Before you start, complete the form below to assign a role to each member. 
If you have 3 people, combine the **Presenter** and **Reflector**.

|Team Roles	|Team Member	|
|---	|---	|
|**Recorder**: records all answers and questions, provides copies to team and facilitator.	|	|
|**Presenter**: talks to facilitator and other teams.	|	|
|**Manager**: keeps track of time and makes sure everyone contributes appropriately.	|	|
|**Reflector**: considers how the team could work and learn more effectively.	|	|

You should complete the coding portion of this activity on the branch: `maps-classroom`. Individual code 
browser links will be submitted via a Canvas quiz after you have completed the activity.

## PART A - `calculateWordCounts`

|A. `calculateWordCounts`	|Start time:	|
|---	|---	|
| 25 minutes	|	|

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

*Code Block 1*

```
public class PartCatalog {
    private PartManufacturer manufacturer;
    private int year;
    private int month;
    private List<String> catalogWordList;

    public PartCatalog(PartManufacturer manufacturer, int year, int month) {
        this.manufacturer = manufacturer;
        this.year = year;
        this.month = month;

        catalogWordList = PartCatalogReader.getCatalogWords(manufacturer, year, month);
    }

    public PartManufacturer getManufacturer() {
        return manufacturer;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public List<String> getCatalogWords() {
        return catalogWordList;
    }
}
```

1. What should the return type for `calculateWordCounts(PartCatalog catalog)` be?
   Be sure to include the generic types if needed.


1. A `PartCatalog` (code included in *Code Block 1*) represents a manufacturer’s part catalog. 
   What method exposed by the `PartCatalog` class can be used to get all the words in the catalog?

   
1. Individually, implement the `calculateWordCounts()` method in the `DevicePartDiscovery` class in the 
   `com.amazon.ata.maps.partsdiscovery` package. The tests in 
   `DevicePartDiscoveryCalculateWordCountsTest` will ensure you have implemented it correctly. 
   You can run these from IntelliJ or use the gradle command `./gradlew -q clean :test --tests DevicePartDiscoveryWordCountsTest` to execute these tests. 
   When your tests are passing, check the box next to your role. If you get stuck, use your group! If your 
   question still can’t be answered, request that an instructor visits your group.

   [] Recorder
   
   [] Presenter
   
   [] Manager
   
   [] Reflector

1. EXTENSION: Consider the Map method [`getOrDefault()`](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html#getOrDefault-java.lang.Object-V-). 
   Can you simplify the implementation of `calculateWordCounts()` by using this method?

## PART B - `removeWord`

|B. `removeWord`	|Start time:	|
|---	|---	|
| 10 minutes 	|	|

It turns out there are quite a few words that are mentioned a lot, but aren’t relevant. This 
includes words like  “the”, “a”, “is”, etc. We need a way to remove these words from our word 
count map. Next, you will be implementing the method: `void removeWord(String word, Map<String, Integer>)`.

1. What `Map` operation can you use?

1. What is the runtime of this operation?

1. Describe the algorithm you think HashMap uses to remove the **entry** from its internal representation.

1. Individually, implement the `removeWord()` method in the `DevicePartDiscovery` class. 
   The tests in `DevicePartDiscoveryRemoveWordTest` will ensure you have implemented it 
   correctly. You can run these from IntelliJ or run the 
   `./gradlew -q clean :test --tests DevicePartDiscoveryRemoveWordTest` 
   gradle command to execute these tests. When your tests are passing, check the box next to your role. If you 
   get stuck, use your group! If your question still can’t be answered, request that an 
   instructor visits your group.

   [] Recorder
   
   [] Presenter
   
   [] Manager
   
   [] Reflector

## PART C - `getMostFrequentWord`

|C. `getMostFrequentWord`	|Start time:	|
|---	|---	|
| 15 minutes	|	|

Now that we’ve removed the common words from our word count map, let’s find the most 
frequent word in our word counts map. You will be implementing the method: 
`String getMostFrequentWord(Map<String, Integer> wordCounts)`. 

1. There are two pieces of data you will need to track as you iterate through the 
   word counts map. What are they? 

1. Will you need to iterate over the keys, values, or entries? 

1. What `Map` operation can you use to get the set of data you'll iterate over? 

1. What is the return type of the Map operation you answered for question 3? 

1. Individually, implement the `getMostFrequentWord()` method in the `DevicePartDiscovery` 
   class. The tests in `DevicePartDiscoveryGetMostFrequentWordTest` will ensure you have 
   implemented it correctly. You can run these from IntelliJ or run the 
   `./gradlew -q clean :test --tests DevicePartDiscoveryGetMostFrequentWordTest` gradle command to execute these tests. When your tests are passing, 
   check the box next to your role. If you get stuck, use your group! If your question still can’t 
   be answered, request that an instructor visits your group.

   [] Recorder
   
   [] Presenter
   
   [] Manager
   
   [] Reflector

## PART D - `getTfIdfScores`

|D. `getTfIdfScores`	|Start time:	|
|---	|---	|
| 20 minutes 	|	|

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
1. Then we calculate the IDF scores for each word in the new monthly catalog. This 
   stands for Inverse Document Frequency, meaning we provide scores that are higher 
   if the word is less frequent (the inverse of more frequent). For example, if we 
   have 100 previous monthly catalogs, and “the” appears in all of them we will have 
   a score of 0, but “MAX32664” appears in only 1 of them, we will have a score of
   2. If you are interested in the math that happens in this step you
   can read more [here](https://www.onely.com/blog/what-is-tf-idf/). This step yields: 
   `Map<String, Double> idfScores`.
1. To calculate a words TF - IDF score, we multiply the TF score by the IDF score. 
   So for “the” the score will be 0 (100 * 0), and for “MAX32664” the score will be 
   40 (20 * 2). If we do this for each word in our new monthly catalog this yields: 
   `Map<String, Double> tfIdfScores`.


In this part you will be implementing a method that calculates the TF-IDF score for each 
word in a catalog. The wordCounts are provided to you, as well as the TF-IDF scores:
`Map<String, Double> getTfIdfScores(Map<String, Integer> wordCounts, Map<String, Double> idfScores)`

1. Let's check our understanding with the example of calculating TF-IDF for one word, "adapter". Fill in
   the code to implement the `getTfIdfScoreForAdapter()` method below.
   ```
   public double getTfIdfScoreForAdapter(Map<String, Integer> wordCounts, Map<String, Double> idfScores) {
 
   }
   ```
1. How do we go from calculating the score for one word to all the words in the catalog? 

1. Why do we use a `Double` as the `Map`’s value in our return from  `getTfIdfScores()`? Why not an `Integer`? 

1. Individually, implement the `getTfIdfScores()` method in the `DevicePartDiscovery` class in 
   the `com.amazon.ata.maps.partsdiscovery` package. The tests in 
   `DevicePartDiscoveryCalculateTfIdfScoresTest` will ensure you have implemented it correctly. You 
   can run these from IntelliJ or run the `./gradlew -q clean :test --tests DevicePartDiscoveryCalculateTfIdfScoresTest` gradle command to execute these 
   tests. When your tests are passing, include your alias here. If you get stuck, 
   use your group! If your question still can’t be answered, request that an instructor 
   visits your group.

   [] Recorder
   
   [] Presenter
   
   [] Manager
   
   [] Reflector

## EXTENSION 1 - `getBestScoredWords`

Now that we have implemented a method to get the TF-IDF scores for each word, we now have completed the 
prequesites needed to select the "best" words for our catalogs. In this case, "best" means the words with
the highest TF-IDF scores.

You will be implementing the method: 
`List<String> getBestScoredWords(Map<String, Double> tfIdfScores)`. 

1. There are two pieces of data you will need to track as you iterate through the 
   tf-idf scores map. What are they? 

1. Will you need to iterate over the keys, values, or entries? 

1. How do you want to sort the set of data in tfIdfScores? By keys, or by values?

1. What resource can you use to implement the sorting requirements you answered for question 3?
[Hint: here's a javadoc to help!](https://docs.oracle.com/javase/8/docs/api/java/util/Map.Entry.html)

1. Individually, implement the `getBestScoredWords()` method in the `DevicePartDiscovery` class in 
   the `com.amazon.ata.maps.partsdiscovery` package. The tests in 
   `DevicePartDiscoveryGetBestScoredWordTest` will ensure you have implemented it correctly. You 
   can run these from IntelliJ or run the `./gradlew -q clean :test --tests DevicePartDiscoveryGetBestScoredWrodTest` 
   gradle command to execute these tests. When your tests are passing, include your alias here. If you get stuck, 
   use your group! If you are still stuck, and are working on this outside of class time, please file
   a CQA and sign up for Office Hours to discuss this with an instructor or TA.
   
   [] Recorder
  
   [] Presenter
  
   [] Manager
  
   [] Reflector

## EXTENSION 2 - `getIdfScores`
Returning the best-matched words for a catalog (using TF-IDF) requires implementation of the
following steps:

1. Create a map for the TF score for each word in the new monthly catalog. 
   This stands for Term Frequency, just a fancy way of saying the word counts 
   we calculated in step 1. For example, we might have 100 counts of “the” and 20 
   counts of “MAX32664”. This step yields: `Map<String, Integer> wordCounts` and is
   implemented by `calculateWordCounts()`.
1. Calculate the IDF scores for each word in the new monthly catalog. This 
   stands for Inverse Document Frequency, meaning we provide scores that are higher 
   if the word is less frequent (the inverse of more frequent). For example, if we 
   have 100 previous monthly catalogs, and “the” appears in all of them we will have 
   a score of 0, but “MAX32664” appears in only 1 of them, we will have a score of
   2. You can read about the math that happens in this step [here](https://www.onely.com/blog/what-is-tf-idf/).
   This step yields: `Map<String, Double> idfScores`.
1. Calculate a words TF - IDF score, by multiplying the TF score by the IDF score. 
   If we do this for each word in our new monthly catalog this yields: 
   `Map<String, Double> tfIdfScores`, and is implemented by `getTfIdfScores()`.
1. Sort the list of the words in a document by their TF - IDF scores and return the
   top 10 results by score. This step yields `List<String> bestScoredWoreds` and is
   implemented by `getBestScoredWords()`.
   
At this point, you have implemented steps 1,3, and 4 in this process, and the IDF scores
have been provided by the tests for step 3.

In this step, you will be implementing the method: 
`Map<String, Double> calculateIdfScores(List<Map<String, Integer>> catalogWordCounts)`.
`catalogWordCounts` is a list of maps representing the Term Frequency (word count) for
a collection of catalogs. Each map associates a word with the number of times the word
appears in a given document. This method will return a `Map<String,Double> idfScores`,
which associates a word with its IDF score.

You can view the math behind TF-IDF [here.](https://www.onely.com/blog/what-is-tf-idf/)
What's most important to note is the formula for calculating IDF.:
This formula should be idf = log(N/DF), where N is the total number of catalogs and DF is the
number of catalogs which contain the word.

The exact math of the log() operation isn't important to this lesson, but here is a summary:
The log() operation returns the value of a number on a [logarithmic scale.](https://en.wikipedia.org/wiki/Logarithmic_scale)
Each log operation also has a number called the logarithmic base. Generally, the logarithmic base used
in calculating IDF is 2. However, in our implementation, we will be using a logarithmic base of 10, for
simplicity of implementation.

Before implementing, consider:

1. Given the input `List<Map<String, Integer>> catalogWordCounts`, how can you calculate N
   (the total number of catalogs provided)?
1. What `Map` operation can you use to determine whether or not a word is present in a given catalog?
1. How can you associate the number of documents a word appears in (DF) with a word?
1. When dividing two `Integers` in java, will the result be a `double`? If not, how can you get a `double`
   result from a division of two `Integers`?
1. In java, how can you calculate the base 10 logarithmic value of a given `double`?
    [Here is a javadoc that may help](https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html)
   
1. Individually, implement the `calculateIdfScores()` method in the `DevicePartDiscovery` class in 
   the `com.amazon.ata.maps.partsdiscovery` package. The tests in 
   `DevicePartDiscoveryCalculateIdfScoresTest` will ensure you have implemented it correctly. You 
   can run these from IntelliJ or run the `./gradlew -q clean :test --tests DevicePartDiscoveryCalculateIdfScoresTest` gradle command to execute these 
   tests. When your tests are passing, include your alias here. If you get stuck, 
   use your group! If you are still stuck, and are working on this outside of class time, please file
   a CQA and sign up for Office Hours to discuss this with an instructor or TA.
   
   [] Recorder
  
   [] Presenter
  
   [] Manager
  
   [] Reflector
 
When you have completed all of these steps and extensions, make sure to celebrate! You have
successfully implemented a TF-IDF word-scoring algorithm end-to-end!
