/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.example.android.flowerai;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Main {@code Activity} class for the Camera app.
 */
public class CameraActivity extends Activity
        implements Camera2BasicFragment.Camera2BasicFragmentSelectedListener {
    private static final String TAG = "CameraActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (null == savedInstanceState) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_cam, Camera2BasicFragment.newInstance())
                    .commit();
        }

        // Creates new searchable view and searchle object to allow user to type data into
        SearchView searchView = (SearchView) findViewById(R.id.search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView textView = findViewById(R.id.search_text);
        ImageButton imageButton = findViewById(R.id.imageButton2);
        ImageButton resultBackButton = findViewById(R.id.resultBack);
        toolbar.bringToFront();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(false);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(true);
            }
        });

        resultBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(2);
            }
        });


        //Query listener that triggers when a user enters input from their keyboard
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // Grabs the user input and changes the fragment by passing in its value when they click submit
            @Override
            public boolean onQueryTextSubmit(String query) {
                textView.setVisibility(View.INVISIBLE);
                imageButton.setVisibility(View.VISIBLE);
                changeFragment(1, query, "textSubmit");
                return true;
            }

            // Grabs the user input and changes the fragment by passing in its value when they continuously type
            @Override
            public boolean onQueryTextChange(String query) {
                textView.setVisibility(View.INVISIBLE);
                imageButton.setVisibility(View.VISIBLE);
                changeFragment(1, query, "textChange");
                return true;
            }
        });

        // Query listener when users initially click on the search bar
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            // Automatically loads all the data by default or changes back to the old fragment screen depending on where the user is
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // searchView expanded
                    textView.setVisibility(View.INVISIBLE);
                    imageButton.setVisibility(View.VISIBLE);
                    changeFragment(1, null, "focusChange");
                } else {
                    // searchView not expanded
                    textView.setVisibility(View.VISIBLE);
                    imageButton.setVisibility(View.GONE);
                    changeFragment(2, null, "focusChange");
                }
            }
        });
    }

<<<<<<< HEAD
    Toolbar toolbar = findViewById(R.id.toolbar);
    SearchView searchView = findViewById(R.id.search);
    TextView textView = findViewById(R.id.search_text);
    ImageButton imageButton = findViewById(R.id.imageButton2);
    toolbar.bringToFront();

    textView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        searchView.setIconified(false);
      }
    });

    imageButton.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        searchView.setIconified(true);
      }
    });



    searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
          // searchView expanded
          textView.setVisibility(View.INVISIBLE);
          imageButton.setVisibility(View.VISIBLE);
          changeFragment(1);
=======
    /**
     * Changes the fragment the user is looking at
     *
     * @param frag_id    (int) - Which fragment to switch to, 1 for a searchable fragment or 2 for the camera view
     * @param query      (String) - The user input that we queried for
     * @param typeChange (String) - Identifies what type of fragment change we should be using
     */
    private void changeFragment(int frag_id, String query, String typeChange) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Creates an array list of the user input and the fragment trasition typE
        ArrayList<String> fragmentArgs = new ArrayList<>();
        fragmentArgs.add(query);
        fragmentArgs.add(typeChange);

        // Creates the bundle so we can pass arguments into the fragment
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("databaseArgs", fragmentArgs);

        if (frag_id == 1) {
            // Links the newly create fragment with the bundle
            SearchResultFragment NAME = new SearchResultFragment();
            NAME.setArguments(bundle);
            fragmentTransaction.replace(R.id.container_cam, NAME);
>>>>>>> master
        } else {
            Camera2BasicFragment NAME = new Camera2BasicFragment();
            fragmentTransaction.replace(R.id.container_cam, NAME);
        }
        // Commits the fragment change
        fragmentTransaction.commit();
    }

    private void changeFragment(int frag_id) {
        List<Map.Entry<String, Float>> labels = new ArrayList<Map.Entry<String, Float>>();
        Bitmap bitmap = null;
        changeFragment(frag_id, labels, bitmap);
    }

    private void changeFragment(int frag_id, List<Map.Entry<String, Float>> label, Bitmap bitmap) {
        TextView textView = findViewById(R.id.search_text);
        ImageButton imageButton = findViewById(R.id.imageButton2);
        ImageButton resultBackButton = findViewById(R.id.resultBack);
        SearchView searchView = findViewById(R.id.search);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (frag_id == 1) {
            SearchResultFragment NAME = new SearchResultFragment();
            textView.setVisibility(View.INVISIBLE);
            imageButton.setVisibility(View.VISIBLE);
            fragmentTransaction.replace(R.id.container_cam, NAME);
        } else if (frag_id == 2) {
            Camera2BasicFragment NAME = new Camera2BasicFragment();
            textView.setText("Plant Search");
            textView.setVisibility(View.VISIBLE);
            imageButton.setVisibility(View.GONE);
            resultBackButton.setVisibility(View.GONE);
            searchView.setVisibility(View.VISIBLE);
            fragmentTransaction.replace(R.id.container_cam, NAME);
        } else if (frag_id == 3) {
            CameraResultFragment NAME = CameraResultFragment.newInstance(label, bitmap);
            textView.setText("Camera Result");
            resultBackButton.setVisibility(View.VISIBLE);
            searchView.setVisibility(View.GONE);
            fragmentTransaction.replace(R.id.container_cam, NAME);
        }
        fragmentTransaction.commit();
    }

    public void onProcess(List<Map.Entry<String, Float>> label, Bitmap bitmap) {
        changeFragment(3, label, bitmap);
    }
}
