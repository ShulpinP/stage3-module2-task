package com.mjc.school.repository.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LineReader {
    public static List<String> readLines(String path, int bound) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found: " + path);
        }
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            for (int i = 0; i < bound; i++) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    throw new IllegalArgumentException("File contains less than 20 lines!");
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return lines;
        }
    }
    public static LocalDateTime getRandomDate() {
        Random random = new Random();
        int year = LocalDateTime.now().getYear()+random.nextInt(10)-10;
        int month = random.nextInt(12);
        int day = random.nextInt(28);
        int hour = random.nextInt(23);
        int minute = random.nextInt(59);
        return LocalDateTime.of(year, Month.of(month), day, hour, minute);

    }
    }

