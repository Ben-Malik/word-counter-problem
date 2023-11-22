package com.benmaliktchamalam.wordcounter.rest.controller;

import com.benmaliktchamalam.wordcounter.core.exception.WordCounterException;
import com.benmaliktchamalam.wordcounter.core.model.WordFrequency;
import com.benmaliktchamalam.wordcounter.core.service.RegularWordFrequencyAnalyzer;
import com.benmaliktchamalam.wordcounter.core.service.WordFrequencyAnalyzer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/word-counter/api")
public class RestWordCounterController {

    private final WordFrequencyAnalyzer wordFrequencyAnalyzer = new RegularWordFrequencyAnalyzer();

    @GetMapping("/most-n-word-frequencies")
    public ResponseEntity<String> getMostNWordFrequencies(@Valid @RequestBody GetMostNWordFrequenciesRequest request) {

        try {
            List<WordFrequency> mostNWordFrequencies = wordFrequencyAnalyzer.calculateMostFrequencyNWords(request.text, request.n);
            return ResponseEntity.ok().body(mostNWordFrequencies.toString());
        } catch (WordCounterException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/highest-frequency/{text}")
    public ResponseEntity<String> getHighestFrequency(@PathVariable("text") String text) {

        try {
            int highestFrequency = wordFrequencyAnalyzer.calculateHighestFrequency(text);
            return ResponseEntity.ok().body(String.valueOf(highestFrequency));
        } catch (WordCounterException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/word-frequency")
    public ResponseEntity<String> getWordFrequencyForWord(@RequestBody GetWordFrequencyRequest request) {

        try {
            int wordFrequency = wordFrequencyAnalyzer.calculateFrequencyForWord(request.text, request.word);
            return ResponseEntity.ok().body(String.valueOf(wordFrequency));
        } catch (WordCounterException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Setter
    @Getter
    public static class GetWordFrequencyRequest {
        @NotBlank
        private String text;
        @NotBlank
        private String word;
    }

    @Getter
    @Setter
    public static class GetMostNWordFrequenciesRequest {
        @NotBlank
        private String text;
        private int n;
    }

}
