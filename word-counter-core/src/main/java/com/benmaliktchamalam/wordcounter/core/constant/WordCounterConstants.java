package com.benmaliktchamalam.wordcounter.core.constant;

/**
 * A final class composed of constants values used throughout the project.
 *
 * @author Ben-Malik Tchamalam
 */
public final class WordCounterConstants {

    // Regular expressions
    public static final String WORD_REGEX = "\\W";

    public static final int DEFAULT_NOTFOUND_WORD_FREQUENCY = -1;

    // Exception messages
    public static final String REQUEST_MOST_FREQUENCY_N_WORDS_LIMIT_ERROR = "You're attempting to get frequency words with a  negative limit.";
    public static final String BLANK_TEXT_ERROR = "The text you are attempting to analyze is empty.";
    public static final String BLANK_WORD_ERROR = "The word you are attempting to analyze is empty.";

}
