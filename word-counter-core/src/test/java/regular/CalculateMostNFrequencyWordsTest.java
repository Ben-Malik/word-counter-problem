package regular;

import com.benmaliktchamalam.wordcounter.core.exception.WordCounterException;
import com.benmaliktchamalam.wordcounter.core.model.WordFrequency;
import com.benmaliktchamalam.wordcounter.core.service.RegularWordFrequencyAnalyzer;
import com.benmaliktchamalam.wordcounter.core.service.WordFrequencyAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.benmaliktchamalam.wordcounter.core.constant.WordCounterConstants.REQUEST_MOST_FREQUENCY_N_WORDS_LIMIT_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@linkplain WordFrequencyAnalyzer#calculateMostFrequencyNWords(String, int)}
 */
public class CalculateMostNFrequencyWordsTest {

    // Test constants
    private static final String VALID_TEXT = "The sun shines over the lake";

    private WordFrequencyAnalyzer wordFrequencyAnalyzer;

    @BeforeEach
    public void init() {
        wordFrequencyAnalyzer = new RegularWordFrequencyAnalyzer();
    }

    @Test
    public void calculateMostFrequencyNWordsReturnsExactlyTheRequestedNumberOfWordFrequenciesAndSortedByFrequencyAndWordsAlphabetically() throws WordCounterException {
        List<WordFrequency> mostFrequencyFiveWords = wordFrequencyAnalyzer.calculateMostFrequencyNWords(VALID_TEXT, 3);
        assertTrue(mostFrequencyFiveWords.size() <= 3);
        assertTrue(mostFrequencyFiveWords.get(0).getWord().equals("the") && mostFrequencyFiveWords.get(0).getFrequency() == 2);
        assertTrue(mostFrequencyFiveWords.get(1).getWord().equals("lake") && mostFrequencyFiveWords.get(1).getFrequency() == 1);
        assertTrue(mostFrequencyFiveWords.get(2).getWord().equals("over") && mostFrequencyFiveWords.get(2).getFrequency() == 1);

        assertEquals("(\"the\", 2)", mostFrequencyFiveWords.get(0).toString());
        assertEquals("(\"lake\", 1)", mostFrequencyFiveWords.get(1).toString());
        assertEquals("(\"over\", 1)", mostFrequencyFiveWords.get(2).toString());
    }

    @Test
    public void calculateMostFrequencyNWordsThrowWordCounterExceptionWithTheCorrectMessageWhenGivenNValueIsNegative() {
        var exception = assertThrows(WordCounterException.class, () ->
                wordFrequencyAnalyzer.calculateMostFrequencyNWords(VALID_TEXT, -3));
        assertEquals(REQUEST_MOST_FREQUENCY_N_WORDS_LIMIT_ERROR, exception.getMessage());
    }

}
