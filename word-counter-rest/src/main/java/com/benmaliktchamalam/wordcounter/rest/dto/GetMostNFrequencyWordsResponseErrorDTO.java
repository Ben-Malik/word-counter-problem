package com.benmaliktchamalam.wordcounter.rest.dto;

import com.benmaliktchamalam.wordcounter.rest.constant.WordCounterConstants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
public class GetMostNFrequencyWordsResponseErrorDTO {
    public String message = WordCounterConstants.REQUEST_MOST_FREQUENCY_N_WORDS_ERROR;
    public String type = HttpStatus.INTERNAL_SERVER_ERROR.name();
    public int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
}
