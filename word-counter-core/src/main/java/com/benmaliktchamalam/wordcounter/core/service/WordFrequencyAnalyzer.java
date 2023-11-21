package com.benmaliktchamalam.wordcounter.core.service;

import com.benmaliktchamalam.wordcounter.core.exception.WordCounterException;
import com.benmaliktchamalam.wordcounter.core.model.WordFrequency;

import java.util.List;

/**
 * The word frequency analyzer interface.
 *
 * @author Ben-Malik Tchamalam
 */
public interface WordFrequencyAnalyzer {

    /**
     * Calculates the highest frequency given a text.
     *
     * @param text The text whose highest frequency is sought.
     * @return The highest frequency of the word in the given text.
     * -1 if the string is empty.
     */
    int calculateHighestFrequency(String text) throws WordCounterException;

    /**
     * Calculates the frequency of the given word inside the given input text.
     *
     * @param text The text in which the frequency is to be searched for.
     * @param word The word whose frequency is sought.
     * @return The frequency value of the given word.
     */
    int calculateFrequencyForWord(String text, String word) throws WordCounterException;

    /**
     * Finds the most frequent <code>n</code> words in a given string text.
     *
     * @param text The text to be analysed.
     * @param n    The number of most frequency looked for.
     * @return a list of most frequent <code>n</code> words.
     */
    List<WordFrequency> calculateMostFrequencyNWords(String text, int n) throws WordCounterException;
}
