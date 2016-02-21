package com.hackillinois2016.minimap.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackillinois2016.minimap.R;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "HELLI";
    private GoogleMap mMap;

    private static final int ACTIVITY_CALLBACK = 30;


    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient mGoogleApiClient;

    /**
     * Represents a geographical location.
     */
    protected Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        buildGoogleApiClient();

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onResume() {

        super.onResume();



    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == ACTIVITY_CALLBACK) {

            double lat = data.getDoubleExtra("lat", getIntent().getDoubleExtra("lat", 0.0d));
            double longd = data.getDoubleExtra("longd", getIntent().getDoubleExtra("long", 0.0d));

            int type = data.getIntExtra("type", -1);
            String desc = data.getStringExtra("desc");

            //0 hardware
            //1 open source
            //2 software
            //3 food
            //4 stairsb
            //5 bathroom
            //6 custom
            BitmapDescriptor mBDesc;
            int ident;

            switch (type)
            {
                case 0:
                    ident = R.drawable.hardware;
                    break;
                case 1:
                    ident = R.drawable.opensource;
                    break;
                case 2:
                    ident = R.drawable.software;
                    break;
                case 3:
                    ident = R.drawable.food;
                    break;
                case 4:
                    ident = R.drawable.stairs;
                    break;
                case 5:
                    ident = R.drawable.bathroom;
                    break;
                default:
                    ident = android.R.drawable.ic_delete;
                    break;
            }

            Bitmap bm = BitmapFactory.decodeResource(getResources(), ident);
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bm, 160, 160, false);

            mMap.addMarker(new MarkerOptions().position(new LatLng(lat, longd)).title(desc).icon(BitmapDescriptorFactory.fromBitmap(resizedBitmap)));

            Firebase.setAndroidContext(getApplicationContext());

            Firebase myFirebaseRef = new Firebase("https://brilliant-inferno-1466.firebaseio.com/");

            myFirebaseRef.
            myFirebaseRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //String newCondition = new S
                    Log.i(TAG, "ChildAdded" + s);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            //mMap.
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.1147593, -88.2284846), 20));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Intent i = new Intent(MapsActivity.this, CreateSpotActivity.class);

                Bundle b = new Bundle();
                i.putExtra("lat", latLng.latitude);
                i.putExtra("longd", latLng.longitude);

                startActivityForResult(i, ACTIVITY_CALLBACK);

                //mMap.addMarker(new MarkerOptions().position(latLng).title("New Tag!").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_delete)));

            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });

        if (mMap != null) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //mMap.setMyLocationEnabled(true);

                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPoint, 14));

            } else {
                // Show rationale and request permission.
            }

            // Add a marker in Sydney and move the camera
            LatLng currentPoint = new LatLng(40.114740, -88.228249);
            mMap.addMarker(new MarkerOptions().position(currentPoint).title("Close To Champaign").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_popup_reminder)));
        }
    }

    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            if (mLastLocation != null) {
                //Toast.makeText(this, "Location works.", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(this, "No location.", Toast.LENGTH_LONG).show();
            }

            return;
        }


    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

}
