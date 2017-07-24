package com.nolock.prog;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naomi on 9/15/16.
 */
public class ReadFile {

    public static List<String> readInputFile(String arg) {
        BufferedReader br = null;
        String line = "";
        List<String> records = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(arg));
            try {
                while ((line = br.readLine()) != null) {
                records.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return records;

    }
}
