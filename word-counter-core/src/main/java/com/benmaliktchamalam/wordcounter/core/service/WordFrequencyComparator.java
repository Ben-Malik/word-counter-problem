package com.benmaliktchamalam.wordcounter.core.service;

import com.benmaliktchamalam.wordcounter.core.model.WordFrequency;

import java.util.Comparator;

/**
 * A Comparator implementation for {@linkplain WordFrequency}
 *
 * @author Ben-Malik Tchamalam
 */
public class WordFrequencyComparator implements Comparator<WordFrequency> {
    @Override
    public int compare(WordFrequency o1, WordFrequency o2) {
        int frequencyCompare = Integer.compare(o1.getFrequency(), o2.getFrequency());
        if (frequencyCompare != 0) {
            return frequencyCompare;
        }
        return o1.getWord().compareTo(o2.getWord());
    }

    /**
     * While reversing two word frequencies, if they happen to have the same frequency do not reverse the words alphabetically.
     *
     * @return {@linkplain Comparator}
     */
    @Override
    public Comparator<WordFrequency> reversed() {
        return Comparator.comparing(WordFrequency::getFrequency, Comparator.reverseOrder())
                .thenComparing(WordFrequency::getWord);
    }
}
