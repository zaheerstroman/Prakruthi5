package com.prakruthi.ui.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.prakruthi.R;

public class ShareitActivity extends AppCompatActivity {

    Button rate;
    Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareit);

        rate = findViewById(R.id.btnRate);
        share = findViewById(R.id.btnShare);


        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://play.google.com/store/search?q=prakruthi&c=apps");


                Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(myAppLinkToMarket);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ShareitActivity.this, "Unable to open", Toast.LENGTH_LONG).show();
                }
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String sAux = "\nJust clicked & install this application:\n\n";
//
                    sAux = sAux + " https://play.google.com/store/search?q=prakruthi&c=apps";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "Choose One"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}