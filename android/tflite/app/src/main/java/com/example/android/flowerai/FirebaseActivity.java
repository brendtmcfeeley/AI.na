package com.example.android.flowerai;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Main {@code Activity} class for the Camera app.
 */
public class FirebaseActivity extends Activity {
    private static final String TAG = "FirebaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        Bundle data = getIntent().getExtras();

        // Get the plant selected!
        Plant plant = data.getParcelable("Plant");
    }
}
