package com.example.android.tflitecamerademo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CameraResultFragment extends Fragment {
    public static CameraResultFragment newInstance(List<Map.Entry<String, Float>> labels) {
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

        fragmentResult.setArguments(args);
        return fragmentResult;
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView mRecyclerView;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;

        String[] plant_names = getArguments().getStringArray("plant_names");
        String[] plant_pcts = getArguments().getStringArray("plant_pct");


        plant_names = reverseArray(plant_names);
        plant_pcts = reverseArray(plant_pcts);

        View view = inflater.inflate(R.layout.fragment_camera_result, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.camera_result_list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        // specify an adapter (see also next example)
        mAdapter = new CameraResultAdapter(plant_names, plant_pcts);

        mRecyclerView.setAdapter(mAdapter);

        /*
        TextView textView = view.findViewById(R.id.cameraResult);
        String str = String.format("%s: %f",plant_names[0],plant_pct[0]);
        textView.setText(str); */



        return view;
    }

    private String[] reverseArray(String[] array) {
        List<String> listOfProducts = Arrays.asList(array);
        Collections.reverse(listOfProducts);
        String[] reversed = listOfProducts.toArray(array);
        return reversed;
    }

}

