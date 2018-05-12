package com.example.khanj.koreantime;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        current = (Button)findViewById(R.id.current);
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SoundSirenActivity.class);
                startActivity(intent);
            }
        });
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

        // Add a marker in Sydney and move the camera
        LatLng zero = new LatLng(37.60062040, 126.8643880);
        LatLng one = new LatLng(37.6005390, 126.8653321);
        LatLng two = new LatLng(37.6004710, 126.8659008);
        LatLng three = new LatLng(37.5927013, 126.8812966);
        LatLng four = new LatLng(37.6150731, 126.8328881);
        LatLng five = new LatLng(37.4211885, 126.7518640);
        mMap.addMarker(new MarkerOptions().position(zero).title("약속장소").icon(BitmapDescriptorFactory.fromResource(R.drawable.zero)));
        mMap.addMarker(new MarkerOptions().position(one).title("한주").icon(BitmapDescriptorFactory.fromResource(R.drawable.one)));
        mMap.addMarker(new MarkerOptions().position(two).title("민섭").icon(BitmapDescriptorFactory.fromResource(R.drawable.two)));
        mMap.addMarker(new MarkerOptions().position(three).title("현욱").icon(BitmapDescriptorFactory.fromResource(R.drawable.three)));
        mMap.addMarker(new MarkerOptions().position(four).title("준원").icon(BitmapDescriptorFactory.fromResource(R.drawable.four)));
        mMap.addMarker(new MarkerOptions().position(five).title("동혁").icon(BitmapDescriptorFactory.fromResource(R.drawable.five)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zero,13));

    }
}
