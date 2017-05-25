package com.kyjoconn.myfirstapp;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMessageActivity extends AppCompatActivity {

    private TrackGPS gps;
    double longitude;
    double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);



        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String companyName = extras.getString("companyName");
        String street = extras.getString("street");
        String city = extras.getString("city");
        String state = extras.getString("state");
        String zip = extras.getString("zip");
        //String companyName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(companyName + "\n" + street + "\n" + city + "\n" + state + "\n" + zip);

        gps = new TrackGPS(DisplayMessageActivity.this);


        if (gps.canGetLocation()) {


            longitude = gps.getLongitude();
            latitude = gps.getLatitude();


        }

        Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
        TextView distanceText = (TextView) findViewById(R.id.distanceText);
        distanceText.setText("Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude));
    }
}
