package com.example.android.flowerai;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ImageView;
import android.graphics.Bitmap;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CameraResultFragment extends Fragment {
    String[] plant_names;
    String[] plant_pcts;

    public static CameraResultFragment newInstance(List<Map.Entry<String, Float>> labels, Bitmap retrievedImage) {
        CameraResultFragment fragmentResult = new CameraResultFragment();
        Bundle args = new Bundle();
        int l_size = labels.size();

        String label_str[] = new String[l_size];
        String label_num[] = new String[l_size];
        for(int i = 0; i < l_size; i++){
            Map.Entry<String, Float> label = labels.get(i);
            label_str[i] = label.getKey();
            label_num[i] = String.format("%4.2f", label.getValue());
        }

        args.putStringArray("plant_names" , label_str);
        args.putStringArray("plant_pct", label_num);
        args.putParcelable("retrieved_bitmap", retrievedImage);

        fragmentResult.setArguments(args);
        return fragmentResult;
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;
        ArrayList<Plant> plantList = new ArrayList<>();
        ArrayList<String> plantNamePercent = new ArrayList<>();

        plant_names = reverseArray(getArguments().getStringArray("plant_names"));
        plant_pcts = reverseArray(getArguments().getStringArray("plant_pct"));

        for(int i = 0; i < plant_names.length; i++) {
            plantNamePercent.add(plant_pcts[i] + "% Possibility: " + plant_names[i]);
        }
        plantNamePercent.add("Upload");

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
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
                    plantList.add(dataPlant);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                container.getContext(),
                android.R.layout.simple_list_item_1,
                plantNamePercent);

        View view = inflater.inflate(R.layout.fragment_camera_result, container, false);
        ImageView image = view.findViewById(R.id.resultImage);
        Bitmap retrievedBitmap = getArguments().getParcelable("retrieved_bitmap");
        if (retrievedBitmap != null) {
            image.setImageBitmap(retrievedBitmap);
        }

        ListView listView = view.findViewById(R.id.camera_result_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //here u can use clickListener
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                Intent myIntent = new Intent(getActivity(), InfoActivity.class);
                if (plantNamePercent.get(position) == "Upload") {
                    // Upload image
                } else {
                    for (int i = 0; i < plantList.size(); i++) {
                        if (plant_names[position].contains(plantList.get(i).common_name)) {
                            myIntent.putExtra("Plant", plantList.get(i));
                            startActivity(myIntent);
                        }
                    }
                }
            }
        });

        return view;
    }

    private String[] reverseArray(String[] array) {
        List<String> listOfProducts = Arrays.asList(array);
        Collections.reverse(listOfProducts);
        String[] reversed = listOfProducts.toArray(array);
        return reversed;
    }
}

