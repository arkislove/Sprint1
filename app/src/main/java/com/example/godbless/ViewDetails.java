package com.example.godbless;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ViewDetails extends AppCompatActivity {

    private DatabaseReference databaseReference;

    String binNumber;
    String fillLevel;
    Button viewLocation;
    double lati = 0;
    double longi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_details);

        TextView binNumberValue = findViewById(R.id.binNumberValue);
        TextView binStatusValue = findViewById(R.id.binStatusValue);
        TextView fillLevelValue = findViewById(R.id.fillLevelValue);
        TextView binLevel = findViewById(R.id.binLevel);
        ProgressBar binProgress = findViewById(R.id.binProgress);
        viewLocation = findViewById(R.id.viewLocation);

        Bundle extras = getIntent().getExtras();

        if (extras != null)
        {
            binNumber = extras.getString("binNumber");
            fillLevel = extras.getString("fillLevel");
            lati = extras.getInt("latitude");
            longi = extras.getInt("longitude");
            Log.d(TAG,"binNumber = " + binNumber);
            Log.d(TAG,"fillLevel = " + fillLevel);
            Log.d(TAG,"lati = " + lati);
            Log.d(TAG,"longi = " + longi);
        }

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Bins");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String value = fillLevel;
                    Log.d(TAG, "value = " + value);
                    binNumberValue.setText(binNumber);
                    fillLevelValue.setText(value);
                    binLevel.setText(value + " %");

                    String flValueString = fillLevelValue.getText().toString();

                    int flValueInt = Integer.parseInt(flValueString);

                    fillLevelValue.setText(binLevel.getText());

                    binProgress.setMax(100);
                    binProgress.setProgress(flValueInt);

                    int green = 0xFF00FF00;
                    int yellow = 0xFFFFF000;
                    int orange = 0xFFFF8000;
                    int red = 0xFFFF0000;

                    if (flValueInt <= 25) {
                        binProgress.getIndeterminateDrawable().setColorFilter(green, PorterDuff.Mode.SRC_IN);
                        binProgress.getProgressDrawable().setColorFilter(green, PorterDuff.Mode.SRC_IN);
                    } else if (flValueInt <= 50) {
                        binProgress.getIndeterminateDrawable().setColorFilter(yellow, PorterDuff.Mode.SRC_IN);
                        binProgress.getProgressDrawable().setColorFilter(yellow, PorterDuff.Mode.SRC_IN);
                    } else if (flValueInt <= 75) {
                        binProgress.getIndeterminateDrawable().setColorFilter(orange, PorterDuff.Mode.SRC_IN);
                        binProgress.getProgressDrawable().setColorFilter(orange, PorterDuff.Mode.SRC_IN);
                    } else {
                        binProgress.getIndeterminateDrawable().setColorFilter(red, PorterDuff.Mode.SRC_IN);
                        binProgress.getProgressDrawable().setColorFilter(red, PorterDuff.Mode.SRC_IN);
                    }

                    if (flValueInt <= 75)
                        binStatusValue.setText(R.string.fillLevelLow);
                    else
                        binStatusValue.setText(R.string.fillLevelHigh);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        viewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent viewLoc = new Intent(view.getContext(), MapsActivity.class);

                viewLoc.putExtra("longitude", longi);
                viewLoc.putExtra("latitude", lati);

                view.getContext().startActivity(viewLoc);

            }
        });




    }


}