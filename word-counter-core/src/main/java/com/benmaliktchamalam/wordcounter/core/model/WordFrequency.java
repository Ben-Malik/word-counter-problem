package com.benmaliktchamalam.wordcounter.core.model;

import java.util.RandomAccess;

/**
 * The WordFrequency interface.
 *
 * @author Ben-Malik Tchamalam
 */
public interface WordFrequency extends RandomAccess {

    /**
     * Gets the frequency at which the word occurs in a given text.
     * @return an integer value of the frequency.
     */
    int getFrequency();

    /**
     * Gets the words of the WordFrequency pair.
      * @return a string value of the word.
     */
    String getWord();
}
