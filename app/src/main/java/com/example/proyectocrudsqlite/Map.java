package com.example.proyectocrudsqlite;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mFrag.getMapAsync((OnMapReadyCallback) Map.this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap goMap){
        gMap = goMap;

        LatLng meltTemuco = new LatLng(-38.73561333388447, -72.60905652623562);
        LatLng meltConce= new LatLng(-36.76827487696509, -73.05902664649463);

        gMap.addMarker(new MarkerOptions().position(meltTemuco).title("Sucursal Temuco"));
        gMap.addMarker(new MarkerOptions().position(meltConce).title("Sucursal Concepción"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(meltTemuco));
    }
}