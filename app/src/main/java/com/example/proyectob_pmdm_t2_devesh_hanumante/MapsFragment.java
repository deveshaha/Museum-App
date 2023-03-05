package com.example.proyectob_pmdm_t2_devesh_hanumante;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectob_pmdm_t2_devesh_hanumante.apidata.Museum;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {

    List<Museum> museumsMapList;
    GoogleMap map;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;
            map.getUiSettings().setZoomControlsEnabled(true);
            map.getUiSettings().setZoomGesturesEnabled(true);
            map.getUiSettings().setCompassEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);

            LatLng madrid = new LatLng(40.416775, -3.703790);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 10));

            map.clear();

            if (museumsMapList != null) {
                for (Museum museum : museumsMapList) {
                    LatLng marker = new LatLng(museum.getLocation().getLatitude(), museum.getLocation().getLongitude());
                    map.addMarker(new MarkerOptions().position(marker).title(museum.getTitle()));
                }
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public void passMuseumInfo(List<Museum> museumList) {
        this.museumsMapList = museumList;

        if (map != null) {
            map.clear();
            for (Museum museum : museumsMapList) {
                LatLng marker = new LatLng(museum.getLocation().getLatitude(), museum.getLocation().getLongitude());
                map.addMarker(new MarkerOptions().position(marker).title(museum.getTitle()));
            }
        }
    }
}