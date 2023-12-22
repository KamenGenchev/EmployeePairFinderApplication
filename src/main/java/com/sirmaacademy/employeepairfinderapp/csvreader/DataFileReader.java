package com.sirmaacademy.employeepairfinderapp.csvreader;

import java.io.File;
import java.util.List;

public interface DataFileReader<T> {
    List<T> readDataFile(File file);
}
