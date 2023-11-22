package com.benmaliktchamalam.wordcounter.rest.dto;

import com.benmaliktchamalam.wordcounter.rest.constant.WordCounterConstants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import static com.benmaliktchamalam.wordcounter.rest.constant.WordCounterConstants.SUCCESS;

@AllArgsConstructor
@NoArgsConstructor
public class GetWordFrequencyResponseDTO {


    public GetFrequencyResponseSuccessDTO success = new GetFrequencyResponseSuccessDTO();
    public GetFrequencyResponseErrorDTO error = new GetFrequencyResponseErrorDTO();

    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetFrequencyResponseErrorDTO {
        public String message = WordCounterConstants.WORD_NOT_FOUND_MESSAGE;
        public String word = StringUtils.EMPTY;
        public String type = HttpStatus.NOT_FOUND.name();
        public int statusCode = HttpStatus.NOT_FOUND.value();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetFrequencyResponseSuccessDTO {
        public String word = StringUtils.EMPTY;
        public int wordFrequency = WordCounterConstants.DEFAULT_WORD_FREQUENCY;
        public String type = SUCCESS;
        public int statusCode = HttpStatus.OK.value();
    }

}
