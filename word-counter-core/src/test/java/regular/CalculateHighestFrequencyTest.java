package regular;

import com.benmaliktchamalam.wordcounter.core.exception.WordCounterException;
import com.benmaliktchamalam.wordcounter.core.service.RegularWordFrequencyAnalyzer;
import com.benmaliktchamalam.wordcounter.core.service.WordFrequencyAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@linkplain WordFrequencyAnalyzer#calculateHighestFrequency(String)}
 */
public class CalculateHighestFrequencyTest {

    // Test constants
    private static final String VALID_TEXT_WITH_MULTIPLE_DELIMITERS = "The sun shines over the lake, and ThEn, tWo of "
            + "thE new developers happened to easily integrate well to THE company!";
    private WordFrequencyAnalyzer wordFrequencyAnalyzer;

    @BeforeEach
    public void init() {
        wordFrequencyAnalyzer = new RegularWordFrequencyAnalyzer();
    }

    @Test
    public void calculateHighestFrequencyReturnsCorrectFrequency() throws WordCounterException {

        int highestFrequency = wordFrequencyAnalyzer.calculateHighestFrequency(VALID_TEXT_WITH_MULTIPLE_DELIMITERS);
        assertEquals(4, highestFrequency); //  {The, the, thE, THE} -> 4
    }

}
