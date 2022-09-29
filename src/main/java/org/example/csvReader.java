package org.example;

import java.io.*;
import java.util.Scanner;

public class csvReader {
    public static String[] readCSV() throws FileNotFoundException {

        String line = "";
        String splitBy = ",";
        String[] userInfo = new String[0];
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/userInfo.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                userInfo = line.split(splitBy);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

}
