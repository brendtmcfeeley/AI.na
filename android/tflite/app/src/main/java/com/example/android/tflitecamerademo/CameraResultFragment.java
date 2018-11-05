package com.example.android.tflitecamerademo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class CameraResultFragment extends Fragment {
    public static CameraResultFragment newInstance(List<Map.Entry<String, Float>> labels) {
        CameraResultFragment fragmentResult = new CameraResultFragment();
        Bundle args = new Bundle();
        int l_size = labels.size();

        String label_str[] = new String[l_size];
        float label_num[] = new float[l_size];
        for(int i = 0; i < l_size; i++){
            Map.Entry<String, Float> label = labels.get(i);
            label_str[i] = label.getKey();
            label_num[i] = label.getValue();
        }

        args.putStringArray("plant_names" , label_str);
        args.putFloatArray("plant_pct", label_num);

        fragmentResult.setArguments(args);
        return fragmentResult;
    }

    //TextView cameraResults = getActivity().findViewById(R.id.cameraResults);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        String[] plant_names = getArguments().getStringArray("plant+names");
        float[] plant_pct = getArguments().getFloatArray("plant_pct");
        //TODO use these to make the buttons for the results in a view
        //cameraResults.setText(plant_names[0] + plant_pct[0]);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }
}
