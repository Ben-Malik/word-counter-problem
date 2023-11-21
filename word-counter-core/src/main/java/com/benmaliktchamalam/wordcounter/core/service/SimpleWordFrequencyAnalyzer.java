package com.benmaliktchamalam.wordcounter.core.service;

import com.benmaliktchamalam.wordcounter.core.constant.WordCounterConstants;
import com.benmaliktchamalam.wordcounter.core.exception.WordCounterException;
import com.benmaliktchamalam.wordcounter.core.model.WordFrequency;
import com.benmaliktchamalam.wordcounter.core.util.WordFrequencyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A Simple implementation to {@linkplain WordFrequencyAnalyzer}
 *
 * @author Ben-Malik Tchamalam
 */
@Service
public class SimpleWordFrequencyAnalyzer implements WordFrequencyAnalyzer {

    private final WordFrequencyUtil wordFrequencyUtil = new WordFrequencyUtil();

    /**
     * Calculates the highest frequency given a text.
     *
     * @param text The text whose highest frequency is sought.
     * @return The highest frequency of the word in the given text.
     * -1 if the string is empty.
     */
    @Override
    public int calculateHighestFrequency(String text) throws WordCounterException {
        List<WordFrequency> sortedWordFrequencyList = wordFrequencyUtil.getWordFrequencies(text, new WordFrequencyUtil.SortRequest(true, true));
        if (CollectionUtils.isEmpty(sortedWordFrequencyList))
            throw new WordCounterException(WordCounterConstants.BLANK_TEXT_ERROR);

        return sortedWordFrequencyList.iterator().next().getFrequency();
    }

    /**
     * Calculates the frequency of the given word inside the given input text.
     *
     * @param text The text in which the frequency is to be searched for.
     * @param word The word whose frequency is sought.
     * @return The frequency value of the given word.
     */
    @Override
    public int calculateFrequencyForWord(String text, String word) throws WordCounterException {
        ensureWordValidity(word);
        var sortRequest = new WordFrequencyUtil.SortRequest(false, false);
        List<WordFrequency> wordFrequencyList = wordFrequencyUtil.getWordFrequencies(text, sortRequest);
        Optional<WordFrequency> wordFrequencyOptional = wordFrequencyList.stream().filter(wf -> wf.getWord().equals(word.toLowerCase())).findFirst();

        return wordFrequencyOptional.map(WordFrequency::getFrequency).orElse(WordCounterConstants.DEFAULT_NOTFOUND_WORD_FREQUENCY);
    }

    /**
     * Finds the most frequent <code>n</code> words in a given string text.
     *
     * @param text The text to be analysed.
     * @param n    The number of most frequency looked for.
     * @return a list of most frequent <code>n</code> words.
     */
    @Override
    public List<WordFrequency> calculateMostFrequencyNWords(String text, int n) throws WordCounterException {
        if (n <= 0)
            throw new WordCounterException(WordCounterConstants.REQUEST_MOST_FREQUENCY_N_WORDS_LIMIT_ERROR);

        return wordFrequencyUtil.getWordFrequencies(text, new WordFrequencyUtil.SortRequest(true, true))
                .stream()
                .limit(n)
                .collect(Collectors.toList());
    }

    private void ensureWordValidity(String word) throws WordCounterException {
        if (word.isBlank())
            throw new WordCounterException(WordCounterConstants.BLANK_WORD_ERROR);

    }
}
