package com.kyjoconn.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Arrays;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static Hashtable<String, String> plants = new Hashtable<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        //String csvFile = getAssets("mpi_directory1.csv");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            InputStreamReader is = new InputStreamReader(getAssets().open("mpi_directory1.csv"));
            br = new BufferedReader(is);
            while ((line = br.readLine()) != null) {

                String[] nums = {"1","2","3","4","5","6","7","8","9"};
                boolean skipLine = true;
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i].charAt(0) == line.charAt(1)) {
                        skipLine = false;
                    }
                }

                if (skipLine) {
                    continue;
                }

                // use comma as separator
                String[] company = line.split(cvsSplitBy);

                String[] plant_numbers = company[0].split("\\+");
                //System.out.println(Arrays.toString(plant_numbers));

                for (int i = 0; i < plant_numbers.length; i++) {
                    plants.put(plant_numbers[i], company[1]);
                }


                //System.out.println("Plant [code= " + company[0] + " , name=" + company[1] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //csvFile = "../mpi_directory2.csv";
        br = null;
        line = "";
        cvsSplitBy = ",";

        try {

            InputStreamReader is = new InputStreamReader(getAssets().open("mpi_directory2.csv"));
            br = new BufferedReader(is);
            while ((line = br.readLine()) != null) {

                String[] nums = {"1","2","3","4","5","6","7","8","9"};
                boolean skipLine = true;
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i].charAt(0) == line.charAt(1)) {
                        skipLine = false;
                    }
                }

                if (skipLine) {
                    continue;
                }

                // use comma as separator
                String[] company = line.split(cvsSplitBy);

                String[] plant_numbers = company[0].split("\\+");
                //System.out.println(Arrays.toString(plant_numbers));

                for (int i = 0; i < plant_numbers.length; i++) {
                    plants.put(plant_numbers[i], company[1]);
                }


                //System.out.println("Plant [code= " + company[0] + " , name=" + company[1] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    } // end of onCreate

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        message = plants.get(message);
        message = message.replace("\"", "");
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
