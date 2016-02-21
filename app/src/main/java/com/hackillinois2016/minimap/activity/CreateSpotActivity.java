package com.hackillinois2016.minimap.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

import com.firebase.client.Firebase;
import com.google.android.gms.maps.GoogleMap;
import com.hackillinois2016.minimap.R;
import com.hackillinois2016.minimap.view.CircularShape;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bburton on 2/20/16.
 */
public class CreateSpotActivity extends FragmentActivity {

    private Button submitButton;

    private Button deleteButton;

    private CircularShape iOpenSource;

    private CircularShape iSoftware;

    private CircularShape iHardware;

    private CircularShape iFood;

    private CircularShape iRestroom;

    private CircularShape iStairs;

    private CircularShape selectedShape;

    private TableLayout mTableLayout;

    private EditText mEditText;

    private int selectedCell;

    private double lat;

    private double longd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_overlay);

        mEditText = (EditText)findViewById(R.id.edit_info);

        submitButton = (Button)findViewById(R.id.button_submit);

        deleteButton = (Button)findViewById(R.id.button_remove);

        iOpenSource = (CircularShape)findViewById(R.id.button_opensource);

        iSoftware = (CircularShape)findViewById(R.id.button_software);

        iHardware = (CircularShape)findViewById(R.id.button_hardware);

        iFood = (CircularShape)findViewById(R.id.button_food);

        iRestroom = (CircularShape)findViewById(R.id.button_bathroom);

        iStairs = (CircularShape)findViewById(R.id.button_stairs);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        lat = getIntent().getExtras().getDouble("lat");
        longd = getIntent().getExtras().getDouble("longd");

        iOpenSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCell = 1;
                clearSelection();
                v.setBackground(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
            }
        });

        iSoftware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCell = 2;
                clearSelection();
                v.setBackground(new ColorDrawable(getResources().getColor(R.color.colorAccent)));

            }
        });

        iHardware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCell = 0;
                clearSelection();
                v.setBackground(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
            }
        });

        iFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCell = 3;
                clearSelection();
                v.setBackground(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
            }
        });

        iRestroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCell = 5;
                clearSelection();
                v.setBackground(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
            }
        });
        iStairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCell = 4;
                clearSelection();
                v.setBackground(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Firebase.setAndroidContext(getApplicationContext());

                Firebase myFirebaseRef = new Firebase("https://brilliant-inferno-1466.firebaseio.com/");

                Map<String, String> post1 = new HashMap<String, String>();
                post1.put("lat", String.valueOf(lat));
                post1.put("long", String.valueOf(longd));
                post1.put("type", String.valueOf(selectedCell));
                post1.put("desc", mEditText.getText().toString());
                post1.put("title", "Announcing COBOL, a New Programming Language");
                post1.put("group", "hacker" );
                post1.put("staff", "");
                post1.put("timestamp", String.valueOf(Calendar.getInstance().getTimeInMillis()));
                myFirebaseRef.child("markers_proto").push().setValue(post1);

                Intent i = new Intent();
                i.putExtra("lat", lat);
                i.putExtra("longd", longd);
                i.putExtra("type", selectedCell);
                i.putExtra("desc", mEditText.getText().toString());
                i.putExtra("group", "hacker");
                i.putExtra("scope", "private");

                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });


    }

    private void clearSelection() {
        iRestroom.setBackground(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        iFood.setBackground(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        iHardware.setBackground(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        iSoftware.setBackground(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        iOpenSource.setBackground(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        iStairs.setBackground(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }


}
