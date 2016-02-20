package com.hackillinois2016.minimap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.hackillinois2016.minimap.R;

/**
 * Created by bburton on 2/20/16.
 */
public class LoginActivity extends FragmentActivity {

    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Firebase.setAndroidContext(this);

        Firebase myFirebaseRef = new Firebase("https://blistering-heat-1288.firebaseio.com/");
        myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");

        mLoginButton = (Button)findViewById(R.id.button_login);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MapsActivity.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


    }
}