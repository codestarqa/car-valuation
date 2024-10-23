package com.webuyanycare.dataInputOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * By Naresh Savalia
 */
public class VehicleRegExtractor {
    // Regex pattern to extract vehicle registration numbers (simple UK plate pattern)
    private static final String REG_PATTERN = "\\b[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}\\b";

    public static List<String> extractVehicleRegs(String filePath) {
        List<String> vehicleRegs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = Pattern.compile(REG_PATTERN).matcher(line);
                while (matcher.find()) {
                    vehicleRegs.add(matcher.group().replace(" ", ""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vehicleRegs;
    }
}
