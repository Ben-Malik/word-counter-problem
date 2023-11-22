package com.benmaliktchamalam.wordcounter.rest.server;

import com.benmaliktchamalam.wordcounter.rest.controller.WordCounterController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * An integration test class, responsible for testing all the endpoints available at
 * {@linkplain WordCounterController}
 *
 * @author Ben-Malik Tchamalam
 */
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class WordCounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetMostNWordFrequenciesAPI() throws Exception {

        var mostNWordFrequencies = this.mockMvc.perform(get("/api/most-n-word-frequencies/The/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andReturn();

        var responseBodyAsString = mostNWordFrequencies.getResponse().getContentAsString();
        assertEquals("[(\"the\", 1)]", responseBodyAsString);

        this.mockMvc.perform(get("/api/most-n-word-frequencies/The/-2"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetWordFrequency() throws Exception {
        var frequencyForWord = this.mockMvc.perform(get("/api/word-frequency/The/The"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andReturn();
        String responseAsString = frequencyForWord.getResponse().getContentAsString();
        assertEquals(1, Integer.parseInt(responseAsString));
    }

    @Test
    public void testGetHighestFrequency() throws Exception {
        var highestFrequency = this.mockMvc.perform(get("/api/highest-frequency/The"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andReturn();

        String responseAsString = highestFrequency.getResponse().getContentAsString();
        assertEquals(1, Integer.parseInt(responseAsString));

        this.mockMvc.perform(get("/api/highest-frequency/ "))
                .andExpect(status().isBadRequest());
    }
}
