package com.sirmaacademy.employeepairfinderapp.csvreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;



public class CsvReader implements DataFileReader<String[]> {
    @Override
    public List<String[]> readDataFile(File file) {
        List<String[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                list.add(arr);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading csv file" );
        }

        return list;
    }
}

