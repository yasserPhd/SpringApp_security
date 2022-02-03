package brain.security.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import brain.security.Model.Food;


public class ReadFood {
	
	public static List<Food> readBooksFromCSV(String fileName) {
        List<Food> foods = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");
                   System.out.println("food"+attributes[0]);
               // Food food = new Food(attributes[0],attributes[1],Integer.parseInt(attributes[2]),Integer.parseInt(attributes[3]),Integer.parseInt(attributes[4]),Integer.parseInt(attributes[5]),Integer.parseInt(attributes[6]),Integer.parseInt(attributes[7]),Integer.parseInt(attributes[8]),attributes[9]);

                // adding book into ArrayList
              //  foods.add(food);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return foods;
    }
}
