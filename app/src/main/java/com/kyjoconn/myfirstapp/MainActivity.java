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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Arrays;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    public static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static Hashtable<String, ArrayList<String>> plants = new Hashtable<String, ArrayList<String>>();

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

                ArrayList<String>  nameAndAddress = new ArrayList<String>();
                nameAndAddress.add(company[1]);
                nameAndAddress.add(company[2]);
                nameAndAddress.add(company[3]);
                nameAndAddress.add(company[4]);
                nameAndAddress.add(company[5]);

                for (int i = 0; i < plant_numbers.length; i++) {
                    plants.put(plant_numbers[i].replace("-",""), nameAndAddress);
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

                String[] company = line.split(cvsSplitBy);

                String[] plant_numbers = company[0].split("\\+");
                //System.out.println(Arrays.toString(plant_numbers));

                ArrayList<String>  nameAndAddress = new ArrayList<String>();
                nameAndAddress.add(company[1]);
                nameAndAddress.add(company[2]);
                nameAndAddress.add(company[3]);
                nameAndAddress.add(company[4]);
                nameAndAddress.add(company[5]);

                for (int i = 0; i < plant_numbers.length; i++) {
                    plants.put(plant_numbers[i], nameAndAddress);
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
        Bundle extras = new Bundle();

        EditText editText = (EditText) findViewById(R.id.editText);
        String plantId = editText.getText().toString();
        plantId = plantId.toUpperCase().replace("-","");

        String companyName = plants.get(plantId).get(0);
        Log.d(TAG, companyName);
        String street = plants.get(plantId).get(1);
        String city = plants.get(plantId).get(2);
        String state = plants.get(plantId).get(3);
        String zip = plants.get(plantId).get(4);

        extras.putString("companyName", companyName);
        extras.putString("street", street);
        extras.putString("city", city);
        extras.putString("state", state);
        extras.putString("zip", zip);

        intent.putExtras(extras);
        //intent.putExtra(EXTRA_MESSAGE, companyName);
        startActivity(intent);
    }
}
