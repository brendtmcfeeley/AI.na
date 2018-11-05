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
import android.os.Bundle;
import android.util.Log;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Main {@code Activity} class for the Camera app. */
public class CameraActivity extends Activity
        implements Camera2BasicFragment.Camera2BasicFragmentSelectedListener {




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

    Toolbar toolbar = findViewById(R.id.toolbar);
    SearchView searchView = findViewById(R.id.search);
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

    imageButton.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        searchView.setIconified(true);
      }
    });

    resultBackButton.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View view) {
              changeFragment(2);
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
        } else {
          // searchView not expanded
          textView.setVisibility(View.VISIBLE);
          imageButton.setVisibility(View.GONE);
          changeFragment(2);
        }
      }
    });
  }

  private void changeFragment(int frag_id){
      List<Map.Entry<String, Float>> labels = new ArrayList<Map.Entry<String,Float>>();
      changeFragment(frag_id, labels);
  }

  private void changeFragment(int frag_id, List<Map.Entry<String, Float>> label){
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
      }
      else if(frag_id == 2) {
          Camera2BasicFragment NAME = new Camera2BasicFragment();
          textView.setText("Plant Search");
          textView.setVisibility(View.VISIBLE);
          imageButton.setVisibility(View.GONE);
          resultBackButton.setVisibility(View.GONE);
          searchView.setVisibility(View.VISIBLE);
          fragmentTransaction.replace(R.id.container_cam, NAME);
      }
      else if (frag_id == 3) {
          CameraResultFragment NAME = CameraResultFragment.newInstance(label);
          textView.setText("Camera Result");
          resultBackButton.setVisibility(View.VISIBLE);
          searchView.setVisibility(View.GONE);
          fragmentTransaction.replace(R.id.container_cam, NAME);
      }
      fragmentTransaction.commit();
  }
    public void onProcess(List<Map.Entry<String, Float>> label) {
        changeFragment(3, label);
    }
}
