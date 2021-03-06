package com.company;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.opencsv.CSVWriter;

public class Main {

    public static void main(String[] args) {

        //BIG NOTE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //Please ensure the folder storing the pictures are not included in this test.
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String folder = "C:\\Users\\dreddy\\FailResultsReader\\SourceFiles\\NewLogFiles";
        String csvFileName = "C:\\Users\\dreddy\\FailResultsReader\\SourceFiles\\Validate data "+dateFormat.format(date)+" "+ date.getTime()+".csv";
        File file = new File(folder);
        File[] listOfFiles = file.listFiles();
        List<String[]> contents = new ArrayList<String[]>();
        try {


            for (int i = 0; i < listOfFiles.length; i++) {
                BufferedReader br = new BufferedReader(new FileReader(listOfFiles[i]));
                String st;
                while ((st = br.readLine()) != null)
                {
                    String res = null;
                    if (st.length()>4) {
                        res = st.substring(0,4);
                        if(res.equalsIgnoreCase("fail"))
                        {
                            contents.add(new String[]{listOfFiles[i].getName(),st});
                        }
                    }

                }
            }

            CSVWriter writer = new CSVWriter(new FileWriter(csvFileName));

            writer.writeAll(contents);
            System.out.println("CSV File written successfully All at a time");
            writer.close();

        } catch (IOException e) {
            System.out.println("CSV File write failed :(");
            e.printStackTrace();
        }
    }
}

