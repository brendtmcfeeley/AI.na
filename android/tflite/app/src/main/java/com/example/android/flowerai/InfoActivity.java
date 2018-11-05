package com.example.android.flowerai;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

/**
 * {@code Activity} class for Search Info.
 */
public class InfoActivity extends Activity {
    private static final String TAG = "InfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Bundle data = getIntent().getExtras();
        Plant plant = data.getParcelable("Plant");




        // TextViews
        TextView commonNameTV = findViewById(R.id.cNameTextView);
        TextView sciNameTV = findViewById(R.id.sciNameTextView);
        TextView statusTV = findViewById(R.id.statusTextView);
        TextView familyTV = findViewById(R.id.familyTextView);
        TextView nativeTV = findViewById(R.id.nativeTextView);
        ImageView plantIV = findViewById(R.id.flowerImage);

        switch(plant.common_name) {
            case "Plumeria":
                plantIV.setImageResource(R.drawable.plumeria);
                break;
            case "Bird of Paradise":
                plantIV.setImageResource(R.drawable.birdofparadise);
                break;
            case "Ohia":
                plantIV.setImageResource(R.drawable.ohia);
                break;
            case "Hibiscus":
                plantIV.setImageResource(R.drawable.hibiscus);
                break;
            default:
                plantIV.setImageResource(R.drawable.noimage);
                break;
        }

//        plantIV.setImageURI(R.drawable.birdofparadise);


        // Strings from Plant Class
        commonNameTV.setText(plant.common_name);
        sciNameTV.setText(plant.name);
        statusTV.setText(plant.conservation_status);
        familyTV.setText(plant.family);
        nativeTV.setText(plant.native_status);

    }
}
