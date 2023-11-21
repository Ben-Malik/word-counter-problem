package com.benmaliktchamalam.wordcounter.core.service;

import com.benmaliktchamalam.wordcounter.core.constant.WordCounterConstants;
import com.benmaliktchamalam.wordcounter.core.exception.WordCounterException;
import com.benmaliktchamalam.wordcounter.core.model.WordFrequency;
import com.benmaliktchamalam.wordcounter.core.util.WordFrequencyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An implementation to the {@linkplain WordFrequencyAnalyzer}
 *
 * It differs from {@linkplain SimpleWordFrequencyAnalyzer} in the sense that {@linkplain WordFrequencyAnalyzer#calculateFrequencyForWord(String, String)}
 * does make use of the binary search algorithm after sorting the list of {@linkplain WordFrequency}
 *
 * @author Ben-Malik Tchamalam
 */
@Service
public class RegularWordFrequencyAnalyzer implements WordFrequencyAnalyzer {

    /**
     * TODO Add @Component to {@linkplain WordFrequencyUtil} and then use @RequiredArgsConstructor on this class.
     */
    private final WordFrequencyUtil wordFrequencyUtil = new WordFrequencyUtil();

    @Override
    public int calculateHighestFrequency(String text) throws WordCounterException {
        List<WordFrequency> sortedWordFrequencyList = wordFrequencyUtil.getWordFrequencies(text, new WordFrequencyUtil.SortRequest(true, true));
        if (CollectionUtils.isEmpty(sortedWordFrequencyList))
            throw new WordCounterException(WordCounterConstants.BLANK_TEXT_ERROR);

        return sortedWordFrequencyList.iterator().next().getFrequency();
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) throws WordCounterException {
        if (word.isBlank())
            throw new WordCounterException(WordCounterConstants.BLANK_WORD_ERROR);

        List<WordFrequency> wordFrequencyList = wordFrequencyUtil.getWordFrequencies(text, new WordFrequencyUtil.SortRequest(false, false));
        Comparator<WordFrequency> comparatorByWord = Comparator.comparing(WordFrequency::getWord);
        Collections.sort(wordFrequencyList, comparatorByWord);
        List<String> sortedWords = wordFrequencyList.stream().map(WordFrequency::getWord).toList();
        int index = Collections.binarySearch(sortedWords, word.toLowerCase());
        if (index < 0)
            return WordCounterConstants.DEFAULT_NOTFOUND_WORD_FREQUENCY;

        return wordFrequencyList.get(index).getFrequency();
    }

    @Override
    public List<WordFrequency> calculateMostFrequencyNWords(String text, int n) throws WordCounterException {
        if (n <= 0)
            throw new WordCounterException(WordCounterConstants.REQUEST_MOST_FREQUENCY_N_WORDS_LIMIT_ERROR);

        return wordFrequencyUtil.getWordFrequencies(text, new WordFrequencyUtil.SortRequest(true, true))
                .stream()
                .limit(n)
                .collect(Collectors.toList());
    }
}
