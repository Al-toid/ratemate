package ratemate;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVExample {

    private final String filePath1= "/Users/irgimemia/Downloads/ratemate/motor_data11-14lats.csv"; // Class variable to hold the file path
    private final String filePath2= "/Users/irgimemia/Downloads/ratemate/motor_data14-2018.csv"; // Class variable to hold the file path


    public List<DataRow> readCSV() {
        List<DataRow> dataset = new ArrayList<>();
        int gender;
        int prodYear;
        double premium;
        int x=0;
        int i=0;
        try (Reader reader = new FileReader(filePath1)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);

            for (CSVRecord record : records) {
                if(x==10){break;};
                if(i<=1){i++;continue;}
                gender = Integer.parseInt(record.get(0));
                try{
                    prodYear = Integer.parseInt(record.get(8));
                }catch(Exception e){
                    prodYear=2005;
                }
                try{
                    premium = Double.parseDouble(record.get(6));
                }catch(Exception e){
                    premium=5000.00;
                }

                // Create DataRow object
                DataRow row = new DataRow(gender, prodYear, premium);
                System.out.println(row);
                dataset.add(row);
                x++;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        i=0;
        x=0;
        try (Reader reader2 = new FileReader(filePath2)) {
            Iterable<CSVRecord> records2 = CSVFormat.DEFAULT.parse(reader2);
            for (CSVRecord record : records2) {
                if(x==10){break;}
                if(i==0){i++;continue;}
                // Use indices to dynamically fetch required columns
                gender = Integer.parseInt(record.get(0));
                try{
                    prodYear = Integer.parseInt(record.get(8));
                }catch(Exception e){
                    prodYear=2005;
                }
                try{
                    premium = Double.parseDouble(record.get(6));
                }catch(Exception e){
                    premium=5000.00;
                }

                // Create DataRow object
                DataRow row = new DataRow(gender, prodYear, premium);
                dataset.add(row);
                i++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public static void main(String[] args) {
        CSVExample example = new CSVExample();
        List<DataRow> data = example.readCSV();

        // Print dynamically extracted data
        for (DataRow row : data) {
            System.out.println(row); // Calls toString() to display data
        }
    }
}


