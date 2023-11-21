package util;

import com.benmaliktchamalam.wordcounter.core.exception.WordCounterException;
import com.benmaliktchamalam.wordcounter.core.model.WordFrequency;
import com.benmaliktchamalam.wordcounter.core.util.WordFrequencyUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.benmaliktchamalam.wordcounter.core.constant.WordCounterConstants.BLANK_TEXT_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the {@linkplain WordFrequencyUtil#getWordFrequencies(String, WordFrequencyUtil.SortRequest)}
 */
public class GetSortedWordFrequenciesTest {

    // Test constants
    private static final String VALID_TEXT = "The sun shines over the lake";
    private final WordFrequencyUtil.SortRequest defaultSortRequest = new WordFrequencyUtil.SortRequest(true, true);
    private WordFrequencyUtil wordFrequencyUtil;

    @BeforeEach
    public void init() {
        wordFrequencyUtil = new WordFrequencyUtil();
    }

    @Test
    public void findSortedWordFrequenciesReturnsSortedWordFrequenciesInDescOrderWhenGivenTextIsValidAndDescIsTrue() throws WordCounterException {

        List<WordFrequency> wordFrequencyList = wordFrequencyUtil.getWordFrequencies(VALID_TEXT, defaultSortRequest);
        assertTrue(wordFrequencyList.get(0).getWord().equals("the") && wordFrequencyList.get(0).getFrequency() == 2);
        assertTrue(wordFrequencyList.get(1).getWord().equals("lake") && wordFrequencyList.get(1).getFrequency() == 1);
        assertTrue(wordFrequencyList.get(2).getWord().equals("over") && wordFrequencyList.get(2).getFrequency() == 1);
        assertTrue(wordFrequencyList.get(3).getWord().equals("shines") && wordFrequencyList.get(3).getFrequency() == 1);
        assertTrue(wordFrequencyList.get(4).getWord().equals("sun") && wordFrequencyList.get(4).getFrequency() == 1);
    }

    @Test
    public void findSortedWordFrequenciesReturnsSortedWordFrequenciesInAscOrderWhenGivenTextIsValidAndDescIsFalse() throws WordCounterException {
        WordFrequencyUtil.SortRequest sortRequest = new WordFrequencyUtil.SortRequest(false, false);
        List<WordFrequency> wordFrequencyList = wordFrequencyUtil.getWordFrequencies(VALID_TEXT, sortRequest);
        assertTrue(wordFrequencyList.get(0).getWord().equals("lake") && wordFrequencyList.get(0).getFrequency() == 1);
        assertTrue(wordFrequencyList.get(1).getWord().equals("over") && wordFrequencyList.get(1).getFrequency() == 1);
        assertTrue(wordFrequencyList.get(2).getWord().equals("shines") && wordFrequencyList.get(2).getFrequency() == 1);
        assertTrue(wordFrequencyList.get(3).getWord().equals("sun") && wordFrequencyList.get(3).getFrequency() == 1);
        assertTrue(wordFrequencyList.get(4).getWord().equals("the") && wordFrequencyList.get(4).getFrequency() == 2);
    }

    @Test
    public void findSortedWordFrequenciesThrowsWordCounterExceptionWhenGivenTextIsEmpty() {
        var exception = assertThrows(WordCounterException.class, () ->
                wordFrequencyUtil.getWordFrequencies(StringUtils.EMPTY, defaultSortRequest));
        assertEquals(BLANK_TEXT_ERROR, exception.getMessage());
    }

}
