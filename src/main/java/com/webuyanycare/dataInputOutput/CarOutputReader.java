package com.webuyanycare.dataInputOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * By Naresh Savalia
 */
public class CarOutputReader {
    public static Map<String, CarDetails> loadExpectedCarDetails(String filePath) {
        Map<String, CarDetails> carDetailsMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] details = line.split(","); // CSV format
                if (details.length == 4) {
                    CarDetails carDetails = new CarDetails(details[0].replace(" ", ""), details[1], details[2], details[3]);
                    carDetailsMap.put(details[0].replace(" ", ""), carDetails); // Use registration as key
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carDetailsMap;
    }
}
