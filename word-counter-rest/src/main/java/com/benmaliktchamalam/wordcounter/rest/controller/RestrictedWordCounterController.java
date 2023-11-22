package com.benmaliktchamalam.wordcounter.rest.controller;

import com.benmaliktchamalam.wordcounter.core.exception.WordCounterException;
import com.benmaliktchamalam.wordcounter.core.model.WordFrequency;
import com.benmaliktchamalam.wordcounter.core.service.RegularWordFrequencyAnalyzer;
import com.benmaliktchamalam.wordcounter.core.service.WordFrequencyAnalyzer;
import com.benmaliktchamalam.wordcounter.rest.constant.WordCounterConstants;
import com.benmaliktchamalam.wordcounter.rest.dto.GetWordFrequencyResponseDTO;
import com.benmaliktchamalam.wordcounter.rest.dto.GetHighestWordFrequencyResponseDTO;
import com.benmaliktchamalam.wordcounter.rest.dto.GetMostNFrequencyWordsResponseErrorDTO;
import com.benmaliktchamalam.wordcounter.rest.dto.UnauthorisedUserResponseDTO;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restricted/api")
public class RestrictedWordCounterController {

    private final WordFrequencyAnalyzer wordFrequencyAnalyzer = new RegularWordFrequencyAnalyzer();

    @GetMapping("/highest-frequency/{text}")
    public ResponseEntity<Object> getHighestFrequency(@PathVariable("text") String text, @RequestParam("apiKey") String apiKey) {

        if (!WordCounterConstants.API_KEY.equals(apiKey)) {
            var unauthorisedUserDTO = new UnauthorisedUserResponseDTO();
            return ResponseEntity.status(unauthorisedUserDTO.statusCode).body(unauthorisedUserDTO);
        }

        try {
            int highestFrequency = wordFrequencyAnalyzer.calculateHighestFrequency(text);
            var successDTO = new GetHighestWordFrequencyResponseDTO.GetHighestWordFrequencyResponseSuccessDTO();
            successDTO.highestFrequency = highestFrequency;

            return ResponseEntity.ok().body(successDTO);

        } catch (WordCounterException e) {
            var errorResponseDTO = new GetHighestWordFrequencyResponseDTO.GetHighestWordFrequencyResponseErrorDTO();
            errorResponseDTO.message = e.getMessage();
            errorResponseDTO.statusCode = HttpStatus.BAD_REQUEST.value();
            errorResponseDTO.type = HttpStatus.BAD_REQUEST.name();
            return ResponseEntity.status(errorResponseDTO.statusCode).body(errorResponseDTO);
        }
    }

    @GetMapping("/word-frequency/{text}/{word}")
    public ResponseEntity<Object> getWordFrequency(@PathVariable("text") String text, @PathVariable("word") String word,
                                                   @PathParam("apiKey") String apiKey) {
        if (!WordCounterConstants.API_KEY.equals(apiKey)) {
            var unauthorisedUserDTO = new UnauthorisedUserResponseDTO();
            return ResponseEntity.status(unauthorisedUserDTO.statusCode).body(unauthorisedUserDTO);
        }

        try {
            int wordFrequency = wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
            var success = new GetWordFrequencyResponseDTO.GetFrequencyResponseSuccessDTO();
            success.wordFrequency = wordFrequency;
            success.word = word;
            return ResponseEntity.status(success.statusCode).body(success);
        } catch (WordCounterException e) {
            var errorResponseDTO = new GetWordFrequencyResponseDTO.GetFrequencyResponseErrorDTO();
            errorResponseDTO.message = e.getMessage();
            errorResponseDTO.statusCode = HttpStatus.BAD_REQUEST.value();
            errorResponseDTO.type = HttpStatus.BAD_REQUEST.name();
            errorResponseDTO.word = word;
            return ResponseEntity.status(errorResponseDTO.statusCode).body(errorResponseDTO);
        }
    }

    @GetMapping("/most-n-word-frequencies/{text}/{n}")
    public ResponseEntity<Object> getMostNWordWordFrequencies(@PathVariable("text") String text, @PathVariable("n") int n,
                                                              @RequestParam("apiKey") String apiKey) {
        if (!WordCounterConstants.API_KEY.equals(apiKey)) {
            var unauthorisedUserDTO = new UnauthorisedUserResponseDTO();
            return ResponseEntity.status(unauthorisedUserDTO.statusCode).body(unauthorisedUserDTO);
        }

        try {

            List<WordFrequency> mostNWordFrequencies = wordFrequencyAnalyzer.calculateMostFrequencyNWords(text, n);
            return ResponseEntity.ok().body(mostNWordFrequencies);
        } catch (WordCounterException e) {

            var errorResponseDTO = new GetMostNFrequencyWordsResponseErrorDTO();
            errorResponseDTO.message = e.getMessage();
            errorResponseDTO.statusCode = HttpStatus.BAD_REQUEST.value();
            errorResponseDTO.type = HttpStatus.BAD_REQUEST.name();
            return ResponseEntity.status(errorResponseDTO.statusCode).body(errorResponseDTO);
        }
    }
}
