### word-counter-core

An implementation to the Word Count problem as a dependency.

### Tech stack

1. Java 17
2. Apache Maven 3.8.6
3. JDK: temurin-17.0.7
4. JUnit
5. Mockito

### Class Definitions

1. `public class WordCounterConstants` The class composed of all constant values and error messages.
2. `public class WordCounterException extends Exception` Thrown throughout the library with correct messages.
3. `public interface WordFrequency extends RandomAccess` An encapsulation of the (word, frequency) pairs.
4. `public class SimpleWordFrequency` An implementation to `WordFrequency`
5. `public interface WordFrequencyAnlayzer` The interface defining all the three operations on WordFrequency.
6. `public class RegularWordFrequencyAnalyzer` An implementation to WordFrequencyAnalyzer.
7. `public class WordFrequencyComparator implements Comparator<WordFrequency>` implements the `compare()`
   and `reversed()` methods.
8. `public WordFrequencyUtil` A utility class for the project.

### Unit Tests

1. `regular.CalculateFrequencyForWordTest`
2. `regular.CalculateHighestFrequencyTest`
3. `regular.CalculateMostNFrequencyWordsTest`
4. `regular.GetSortedWordFrequenciesTest`

### Used Abstract Data Types, Algorithms & Why

1. **MapTree**
   `MapTree` is preferred to `HashMap` since despite the fact that we have enough memory and that the access of elements
   could be O(1), the rehashing costs O(n).
   The tree grants a O(log n) performance and items are consistently added to the tree during the analysis.

2. **Binary Search**
   Given a sorted list of `WordFrequency`, `Collections.binarySearch(List<T> list, K key) with
   a complexity of O(log n) in the worst case scenario.

3. **RandomAccess**
   The interface `WordFrequency` does extend `RandomAccess` merely to indicate that it supports fast (generally constant
   time) random access so that generic algorithms changes their behaviours while conducting sequential access of
   collections.

Unit tests have been written with a line coverage of 100%.
