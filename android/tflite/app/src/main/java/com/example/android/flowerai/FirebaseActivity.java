package com.example.android.flowerai;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
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
    private static final String TAG = "FirebaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference myRef;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        ArrayList<String> nameList = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String nameValue = String.valueOf(ds.getKey());

                    nameList.add(nameValue);

                }

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        FirebaseActivity.this,
                        android.R.layout.simple_selectable_list_item,
                        nameList);

                ListView listView = findViewById(R.id.listview);
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

    }
}
