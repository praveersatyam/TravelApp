package com.travel.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DataParser {
    public static List<String> readFile(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        List<String> listOfPaths = new ArrayList<>();
        while(null != (line=bufferedReader.readLine())){
            listOfPaths.add(line);
        }
        return listOfPaths;
    }
}
