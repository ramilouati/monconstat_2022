/*package com.example.monconstat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.PowerManager;
import android.provider.Settings;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

*/
/*
public class geolocaliCre extends Fragment implements LocationListener {
    TextView geoAdd, titAddres,suvitAs,prefirst;
    ImageView map;
    //FusedLocationProviderClient fusedLocationProviderClient;
    LocationManager locationManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_geolocali_cre, container, false);
        geoAdd = view.findViewById(R.id.geoAdd);
        titAddres = view.findViewById(R.id.titAddres);
        suvitAs= view.findViewById(R.id.suvitAs);
        suvitAs.setTextColor(Color.parseColor("#8c8c8c"));
        map=view.findViewById(R.id.map);
        prefirst=view.findViewById(R.id.prefirst);

        suvitAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (!(geoAdd.getText().toString().isEmpty()))
                Navigation.findNavController(view).navigate(R.id.steamocieteAssurances);
            }
        });
        prefirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.firstCre);
            }
        });


      //fusedLocationProviderClient = (FusedLocationProviderClient) LocationServices.getFusedLocationProviderClient(getActivity());
       map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },100);
                }


                 String provider = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
                   if(!provider.contains("gps")){ //if gps is disabled
                       Intent inte =new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                       startActivity(inte);
                   }
                getLocation();

            }
        });




        return view;
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        try {
            locationManager =(LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5, (android.location.LocationListener) this);
        }catch (Exception e){
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getActivity(), ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);
            geoAdd.setText(address);
            SharedPreferences.Editor editor = getContext().getSharedPreferences("sp_local", MODE_PRIVATE).edit();
            editor.putString("LATITUDE",String.valueOf(location.getLatitude()));
            editor.putString("LONGITUDE",String.valueOf(location.getLongitude()));
            editor.apply();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    /*private void getloc() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if(location !=null) {
                    try {

                        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        geoAdd.setText(""+addresses.get(0).getAddressLine(0));
                        Toast.makeText(getActivity(), " "+addresses.get(0).getAddressLine(0), Toast.LENGTH_SHORT).show();
                        if (!(geoAdd.getText().toString().isEmpty()))
                            suvitAs.setTextColor(Color.parseColor("#009685"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }*/
/*
}*/