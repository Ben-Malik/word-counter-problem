package com.benmaliktchamalam.wordcounter.rest.controller;

import com.benmaliktchamalam.wordcounter.core.exception.WordCounterException;
import com.benmaliktchamalam.wordcounter.core.model.WordFrequency;
import com.benmaliktchamalam.wordcounter.core.service.RegularWordFrequencyAnalyzer;
import com.benmaliktchamalam.wordcounter.core.service.WordFrequencyAnalyzer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class WordCounterController {
    private final WordFrequencyAnalyzer wordFrequencyAnalyzer = new RegularWordFrequencyAnalyzer();

    @GetMapping("/most-n-word-frequencies/{text}/{n}")
    public ResponseEntity<String> getMostNWordFrequencies(@PathVariable("text") String text, @PathVariable("n") int n) {

        try {
            List<WordFrequency> mostNWordFrequencies = wordFrequencyAnalyzer.calculateMostFrequencyNWords(text, n);
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

    @GetMapping("/word-frequency/{text}/{word}")
    public ResponseEntity<String> getWordFrequencyForWord(@PathVariable("text") String text, @PathVariable("word") String word) {

        try {
            int wordFrequency = wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
            return ResponseEntity.ok().body(String.valueOf(wordFrequency));
        } catch (WordCounterException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
