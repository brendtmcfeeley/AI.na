package com.example.android.flowerai;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * {@code Activity} class for Search Info.
 */
public class InfoActivity extends Activity {
    private static final String TAG = "InfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // TextViews
        TextView commonNameTV = findViewById(R.id.cNameTextView);
        TextView sciNameTV = findViewById(R.id.sciNameTextView);
        TextView statusTV = findViewById(R.id.statusTextView);
        TextView familyTV = findViewById(R.id.familyTextView);
        TextView nativeTV = findViewById(R.id.nativeTextView);

        // Strings from Plant Class


        commonNameTV.setText("Common Name");
        sciNameTV.setText("Scientific Name");
        statusTV.setText("Status");
        familyTV.setText("Family");
        nativeTV.setText("Native Status");

    }
}
