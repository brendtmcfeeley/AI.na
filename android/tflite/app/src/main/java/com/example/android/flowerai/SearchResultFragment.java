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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Basic fragments for the Camera.
 */
public class SearchResultFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase myFirebaseDatabase;
        DatabaseReference myRef;
        myFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = myFirebaseDatabase.getReference();

        ArrayList<String> nameList = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<String> fragArgs = new ArrayList<>();

                try {
                    fragArgs = getArguments().getStringArrayList("databaseArgs");
                } catch (NullPointerException npe) {
                }

                if (fragArgs.get(1).equals("focusChange")) {
                    nameList.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String nameValue = String.valueOf(ds.getKey());

                        nameList.add(nameValue);
                    }
                }
                else if (fragArgs.get(1).equals("textSubmit")){
                    String nameValue;

                    if (dataSnapshot.child(fragArgs.get(0)).getValue() == null) {
                        nameValue = "Sorry, could not find the plant : " + fragArgs.get(0);
                        nameList.clear();
                        nameList.add(nameValue);
                    } else {
                        nameList.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            nameValue = String.valueOf(ds.getKey());

                            if (nameValue.toLowerCase().contains(fragArgs.get(0).toLowerCase())) {
                                nameList.add(nameValue);
                            }
                        }
                    }
                }
                else {
                    String nameValue;
                    nameList.clear();

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        nameValue = String.valueOf(ds.getKey());

                        if (nameValue.toLowerCase().contains(fragArgs.get(0).toLowerCase())) {
                            nameList.add(nameValue);
                        }
                    }

                    if (nameList.size() <= 0) {
                        nameValue = "Sorry, could not find the plant : " + fragArgs.get(0);
                        nameList.add(nameValue);
                    }
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
                        view.setSelected(true);
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
