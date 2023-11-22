package com.benmaliktchamalam.wordcounter.rest.dto;

import com.benmaliktchamalam.wordcounter.rest.constant.WordCounterConstants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
public class GetHighestWordFrequencyResponseDTO {

    public GetHighestWordFrequencyResponseSuccessDTO success = new GetHighestWordFrequencyResponseSuccessDTO();
    public GetHighestWordFrequencyResponseErrorDTO error = new GetHighestWordFrequencyResponseErrorDTO();

    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetHighestWordFrequencyResponseErrorDTO {
        public String message = WordCounterConstants.HIGHEST_FREQUENCY_WORD_ERROR;
        public String type = HttpStatus.INTERNAL_SERVER_ERROR.name();
        public int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetHighestWordFrequencyResponseSuccessDTO {
        public int highestFrequency = WordCounterConstants.DEFAULT_WORD_FREQUENCY;
        public String type = WordCounterConstants.SUCCESS;
        public int statusCode = HttpStatus.OK.value();
    }
}
