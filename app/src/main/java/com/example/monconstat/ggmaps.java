package com.example.monconstat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.huawei.agconnect.config.AGConnectServicesConfig;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class ggmaps extends Fragment implements OnMapReadyCallback {
    TextView suvitAs,prefirst,ad;
    private MapView mMapview;
    SharedPreferences cret;
    private static final String MAPVIEW_BUNDLE_KEY="MapViewBundleKey";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ggmaps,container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this::onMapReady);
        suvitAs= view.findViewById(R.id.suvitAs);
        ad= view.findViewById(R.id.ad);
        prefirst=view.findViewById(R.id.prefirst);

        suvitAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(ad.getText().toString().isEmpty()))
                Navigation.findNavController(view).navigate(R.id.etap3);
            }
        });
        prefirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.geolocaliCre);
            }
        });

        return view;

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        SharedPreferences prefs = getContext().getSharedPreferences("sp_local", MODE_PRIVATE);
        String lat = prefs.getString("LATITUDE", "36.842475");
        String lon = prefs.getString("LONGITUDE", "10.204810");
        Toast.makeText(getActivity(), lat+":"+lon, Toast.LENGTH_SHORT).show();
        double lati=Double.parseDouble(lat);
        double longi=Double.parseDouble((lon));
        LatLng loaded_position = new LatLng(lati, longi);
        //when map is loaded
        //Initialize Marker options
        MarkerOptions markerOptions = new MarkerOptions();
        //Set position of marker
        markerOptions.position(loaded_position);
        //Set Title of marker
        markerOptions.title(loaded_position.latitude +" : "+loaded_position.longitude);
        //Remove all marker
        googleMap.clear();
        //Animate to zoom the marker
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loaded_position,25));
        //add marker
        googleMap.addMarker(markerOptions);

        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation( loaded_position.latitude,loaded_position.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }
        String address = addresses.get(0).getAddressLine(0);
        ad.setText(address);
        cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);
   /*     SharedPreferences.Editor editor = cret.edit();

        editor.putString("geo", ad.getText().toString());
        editor.apply();*/



       cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = cret.edit();
        editor.putString("geo", (String) ad.getText());
        editor.apply();
                Toast.makeText(getActivity(),address, Toast.LENGTH_SHORT).show();


    }
}
