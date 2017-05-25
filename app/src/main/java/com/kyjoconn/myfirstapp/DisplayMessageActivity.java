package com.kyjoconn.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

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

        TextView distanceText = (TextView) findViewById(R.id.distanceText);
        distanceText.setText("Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude));

        String address = street + " " + city + " " + state + " " + zip;
        LatLng plantCoords = getLocationFromAddress(getApplicationContext(), address);

        TextView plantText = (TextView) findViewById(R.id.plantText);
        plantText.setText("Longitude:" + Double.toString(plantCoords.longitude) + "\nLatitude:" + Double.toString(plantCoords.latitude));

        Location locationA = new Location("me");

        locationA.setLatitude(latitude);
        locationA.setLongitude(longitude);

        Location locationB = new Location("plant");

        locationB.setLatitude(plantCoords.latitude);
        locationB.setLongitude(plantCoords.longitude);

        float distance = locationA.distanceTo(locationB);
        double distance_d = Double.parseDouble(String.valueOf(distance));
        double num_miles = getMiles((distance_d));
        TextView differenceText = (TextView) findViewById(R.id.differenceText);
        differenceText.setText(String.valueOf((int)num_miles) + " miles away");

    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    double getMiles(double i) {
        return i*0.000621371192;
    }
}
