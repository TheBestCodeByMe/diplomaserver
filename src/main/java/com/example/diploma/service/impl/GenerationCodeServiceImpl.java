package com.example.diploma.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GenerationCodeServiceImpl {
    public static String generateCode() {
//        byte[] array = new byte[7];
//        new Random().nextBytes(array);
//        String generatedString = new String(array, StandardCharsets.UTF_8);
        return System.currentTimeMillis()+""; // + generatedString;
    }
}