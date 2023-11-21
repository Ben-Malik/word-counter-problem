package com.benmaliktchamalam.wordcounter.core.util;

import com.benmaliktchamalam.wordcounter.core.constant.WordCounterConstants;
import com.benmaliktchamalam.wordcounter.core.exception.WordCounterException;
import com.benmaliktchamalam.wordcounter.core.model.SimpleWordFrequency;
import com.benmaliktchamalam.wordcounter.core.model.WordFrequency;
import com.benmaliktchamalam.wordcounter.core.service.WordFrequencyComparator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A word frequency utility class
 *
 * @author Ben-Malik
 */
public class WordFrequencyUtil {

    /**
     * A helper method that grabs a given string, parses it in accordance with the {@link WordCounterConstants#WORD_REGEX }
     * and then groups all the words with their occurrences.
     *
     * @param text        The text to be parsed.
     * @param sortRequest Whether to sort the resulting list of word frequency in ascending or descending order.
     * @return a list of {@linkplain WordFrequency}.
     */
    public List<WordFrequency> getWordFrequencies(String text, SortRequest sortRequest) throws WordCounterException {
        if (text.isBlank())
            throw new WordCounterException(WordCounterConstants.BLANK_TEXT_ERROR);

        Map<String, WordFrequency> wordToWordFrequencyMap = new TreeMap<>();
        String[] words = text.split(WordCounterConstants.WORD_REGEX);

        for (String word : words) {
            String wordToLowerCase = word.toLowerCase();
            if (wordToWordFrequencyMap.containsKey(wordToLowerCase)) {
                var current = wordToWordFrequencyMap.get(wordToLowerCase);
                wordToWordFrequencyMap.put(wordToLowerCase, new SimpleWordFrequency(wordToLowerCase, current.getFrequency() + 1));
            } else if (!wordToLowerCase.isBlank()) {
                wordToWordFrequencyMap.put(wordToLowerCase, new SimpleWordFrequency(wordToLowerCase, 1));
            }
        }

        List<WordFrequency> wordFrequencyList = new ArrayList<>(wordToWordFrequencyMap.values().stream().toList());

        if (!sortRequest.sorted)
            return wordFrequencyList;

        Comparator<WordFrequency> comparator = new WordFrequencyComparator();
        if (sortRequest.desc)
            comparator = comparator.reversed();

        wordFrequencyList.sort(comparator);
        return wordFrequencyList;
    }

    /**
     * A static class to indicate whether to sort the returned word frequency list or not and if so, if desc ordered or not.
     */
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SortRequest {
        public boolean sorted;
        public boolean desc;
    }
}
