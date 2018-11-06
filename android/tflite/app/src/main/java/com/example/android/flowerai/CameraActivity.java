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
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Main {@code Activity} class for the Camera app.
 */
public class CameraActivity extends Activity {
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

        SearchView searchView = (SearchView) findViewById(R.id.search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView textView = findViewById(R.id.search_text);
        ImageButton imageButton = findViewById(R.id.imageButton2);
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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                textView.setVisibility(View.INVISIBLE);
                imageButton.setVisibility(View.VISIBLE);
                changeFragment(1, query, "textSubmit");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                textView.setVisibility(View.INVISIBLE);
                imageButton.setVisibility(View.VISIBLE);
                changeFragment(1, query, "textChange");
                return true;
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
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

    private void changeFragment(int frag_id, String query, String typeChange) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ArrayList<String> fragmentArgs = new ArrayList<>();
        fragmentArgs.add(query);
        fragmentArgs.add(typeChange);

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("databaseArgs", fragmentArgs);

        if (frag_id == 1) {
            SearchResultFragment NAME = new SearchResultFragment();
            NAME.setArguments(bundle);
            fragmentTransaction.replace(R.id.container_cam, NAME);
        } else {
            Camera2BasicFragment NAME = new Camera2BasicFragment();
            fragmentTransaction.replace(R.id.container_cam, NAME);
        }
        fragmentTransaction.commit();
    }
}
