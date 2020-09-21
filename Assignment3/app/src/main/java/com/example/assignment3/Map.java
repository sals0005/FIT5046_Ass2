package com.example.assignment3;

import android.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapquest.mapping.maps.MapView;
import com.mapquest.mapping.MapQuest;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

public class Map extends Fragment implements View.OnClickListener{
    View vMap;
    private MapboxMap mMapboxMap;
    private MapView mMapView;
    private LatLng[] latLngs;
    private String[] queries;
    private String accessToken;
    private Context appContext;
    private final LatLng SAN_FRAN = new LatLng(37.7749, -122.4194);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        vMap = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) vMap.findViewById(R.id.map_view);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mMapboxMap = mapboxMap;
                mMapView.setStreetMode();
                mMapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SAN_FRAN, 11));
                addMarker(mapboxMap);
            }
        });


        return mMapView;
    }

    private void addMarker(MapboxMap mapboxMap) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SAN_FRAN);
        markerOptions.title("MapQuest");
        markerOptions.snippet("Welcome to San Francisco!");
        mapboxMap.addMarker(markerOptions);
    }
    public void onResume()
    {
        super.onResume();
        mMapView.onResume(); }
    @Override
    public void onPause()
    {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    public void onDestroy()
    {
    mMapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
    @Override
    public void onClick(View v) {

    }

}