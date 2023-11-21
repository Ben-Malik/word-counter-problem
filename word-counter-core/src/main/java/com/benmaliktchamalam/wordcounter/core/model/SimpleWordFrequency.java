package com.benmaliktchamalam.wordcounter.core.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * An implementation to the
 */
@NoArgsConstructor
@AllArgsConstructor
public class SimpleWordFrequency implements WordFrequency {

    private String word;
    private int frequency;

    @Override
    public int getFrequency() {
        return frequency;
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(\"")
                .append(word)
                .append("\", ")
                .append(frequency)
                .append(")");
        return sb.toString();
    }

}