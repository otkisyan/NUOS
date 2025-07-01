package org.example.dto;

import org.example.Token;

import java.util.List;

public record LexerResult
        (String output, List<Token> matchedTokens){}

