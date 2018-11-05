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

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/** Basic fragments for the Camera. */
public class SearchResultFragment extends Fragment {

  @Override
  public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef;
    mFirebaseDatabase = FirebaseDatabase.getInstance();
    myRef = mFirebaseDatabase.getReference();
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<Plant> plantList = new ArrayList<>();

    myRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
          String commonName = String.valueOf(ds.getChildren().iterator().next().child("common_name").getValue());
          String conservationStatus = String.valueOf(ds.getChildren().iterator().next().child("conservation_status").getValue());
          String family = String.valueOf(ds.getChildren().iterator().next().child("family").getValue());
          String name = String.valueOf(ds.getChildren().iterator().next().child("name").getValue());
          String nativeStatus = String.valueOf(ds.getChildren().iterator().next().child("native_status").getValue());
          Plant dataPlant = new Plant(commonName, conservationStatus, family, name, nativeStatus);
          nameList.add(commonName);
          plantList.add(dataPlant);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                container.getContext(),
                android.R.layout.simple_selectable_list_item,
                nameList);

        ListView listView = getView().findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //here u can use clickListener
          @Override
          public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
            System.out.println(id);
          }
        });
      }
      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
    return inflater.inflate(R.layout.fragment_search_result, container, false);
  }
}
