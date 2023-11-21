package simple;

import com.benmaliktchamalam.wordcounter.core.exception.WordCounterException;
import com.benmaliktchamalam.wordcounter.core.service.SimpleWordFrequencyAnalyzer;
import com.benmaliktchamalam.wordcounter.core.service.WordFrequencyAnalyzer;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.benmaliktchamalam.wordcounter.core.constant.WordCounterConstants.BLANK_WORD_ERROR;
import static com.benmaliktchamalam.wordcounter.core.constant.WordCounterConstants.DEFAULT_NOTFOUND_WORD_FREQUENCY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@linkplain WordFrequencyAnalyzer#calculateFrequencyForWord(String, String)}
 */
public class CalculateFrequencyForWordTest {

    // Test constants
    private static final String VALID_TEXT = "The sun shines over the lake";

    private static final String NON_EXISTING_WORD_IN_TEXT = "leemans";

    private WordFrequencyAnalyzer wordFrequencyAnalyzer;

    @BeforeEach
    public void init() {
        wordFrequencyAnalyzer = new SimpleWordFrequencyAnalyzer();
    }

    @Test
    public void calculateFrequencyForWordComputesTheFrequencyCorrectly() throws WordCounterException {
        int frequency = wordFrequencyAnalyzer.calculateFrequencyForWord(VALID_TEXT, "the");
        assertEquals(2, frequency);
    }

    @Test
    public void calculateFrequencyForWordThrowsWordCounterExceptionWithTheCorrectMessageWhenGivenWordIsEmpty() {
        var exception = assertThrows(WordCounterException.class, () ->
                wordFrequencyAnalyzer.calculateFrequencyForWord(VALID_TEXT, StringUtils.EMPTY));
        assertEquals(BLANK_WORD_ERROR, exception.getMessage());
    }

    @Test
    public void calculateFrequencyForWordReturnsDEFAULT_NOTFOUND_WORD_FREQUENCYWhenWordIsNotInTheGivenText() throws WordCounterException {
        int frequency = wordFrequencyAnalyzer.calculateFrequencyForWord(VALID_TEXT, NON_EXISTING_WORD_IN_TEXT);
        assertEquals(DEFAULT_NOTFOUND_WORD_FREQUENCY, frequency);
    }

}
