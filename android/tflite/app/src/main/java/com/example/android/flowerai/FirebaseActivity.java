package com.example.android.flowerai;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Main {@code Activity} class for the Camera app.
 */
public class FirebaseActivity extends Activity {
//    private static final String TAG = "FirebaseActivity";
//
//    // Firebase data stuff
//    private FirebaseDatabase mFirebaseDatabase;
//    private DatabaseReference myRef;

    String[] test = {"test1", "test2", "test3"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        myRef = mFirebaseDatabase.getReference();

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                ArrayList<String> nameList = new ArrayList<>();
//

//                for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                    String nameValue = ds.child("name").getValue(String.class);
//
//                    nameList.add(nameValue);
//
//                }
//                String[] nameArray = new String[nameList.size()];
//                nameArray = nameList.toArray(nameArray);

        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                R.layout.activity_firebase,
                test);

        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }
}
