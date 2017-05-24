package com.kyjoconn.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

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
    }
}
