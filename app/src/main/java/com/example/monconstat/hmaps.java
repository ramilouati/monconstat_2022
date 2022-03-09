package com.example.monconstat;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.ResolvableApiException;
import com.huawei.hms.location.FusedLocationProviderClient;
import com.huawei.hms.location.LocationAvailability;
import com.huawei.hms.location.LocationCallback;
import com.huawei.hms.location.LocationRequest;
import com.huawei.hms.location.LocationResult;
import com.huawei.hms.location.LocationServices;
import com.huawei.hms.location.LocationSettingsRequest;
import com.huawei.hms.location.LocationSettingsResponse;
import com.huawei.hms.location.LocationSettingsStatusCodes;
import com.huawei.hms.location.SettingsClient;
import com.huawei.hms.maps.CameraUpdate;
import com.huawei.hms.maps.CameraUpdateFactory;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.MapView;
import com.huawei.hms.maps.MapsInitializer;
import com.huawei.hms.maps.OnMapReadyCallback;
import com.huawei.hms.maps.SupportMapFragment;
import com.huawei.hms.maps.model.BitmapDescriptorFactory;
import com.huawei.hms.maps.model.CameraPosition;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.MarkerOptions;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.Manifest;
import android.annotation.SuppressLint;
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
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.huawei.hms.maps.CameraUpdateFactory.zoomTo;

public class hmaps extends Fragment implements OnMapReadyCallback  {
    private HuaweiMap hmap;
    TextView suvitAs,prefirst,ad;
    SharedPreferences cret;
    private MapView HMAPview;
    LocationManager locationManager;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    Switch switch1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        String provider = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(!provider.contains("gps")){ //if gps is disabled
            Intent inte =new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(inte);
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hmaps,container, false);
        HMAPview= view.findViewById(R.id.Hmap);
        suvitAs= view.findViewById(R.id.suvitAs);
        ad= view.findViewById(R.id.ad);
       // suvitAs.setTextColor(Color.parseColor("#8c8c8c"));
        prefirst=view.findViewById(R.id.prefirst);



        suvitAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (!(geoAdd.getText().toString().isEmpty()))
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {



        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }


        AGConnectServicesConfig config = AGConnectServicesConfig.fromContext(this.getActivity());
        MapsInitializer.setApiKey(config.getString("client/api_key"));
        HMAPview.onCreate(mapViewBundle);
        HMAPview.getMapAsync(this);


    }


    @Override
    public void onMapReady(HuaweiMap map) {


        SharedPreferences prefs = getActivity().getSharedPreferences("sp_local", MODE_PRIVATE);
        String lat = prefs.getString("LATITUDE", "36.842475");
        String lon = prefs.getString("LONGITUDE", "10.204810");
        double lati=Double.parseDouble(lat);
        double longi=Double.parseDouble((lon));
        LatLng loaded_position = new LatLng(lati, longi);
        //Log.d(TAG, "onMapReady: ");
        hmap = map;
        hmap.setMyLocationEnabled(true);
        hmap.getUiSettings().setMyLocationButtonEnabled(true);
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(loaded_position.latitude,loaded_position.longitude,1);
            String avenue = addresses.get(0).getThoroughfare();
            String city = addresses.get(0).getLocality();
            ad.setText(city+","+avenue);
            /*cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);
            SharedPreferences.Editor editor = cret.edit();
            editor.putString("geo",ad.getText().toString());*/
        }catch (Exception e){
            e.printStackTrace();
        }
       /* cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = cret.edit();
        editor.putString("geo", (String) ad.getText());
        editor.apply();
*/


        // move camera by CameraPosition param ,latlag and zoom params can set here
        CameraPosition build = new CameraPosition.Builder().target(loaded_position).zoom(22).build();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(build);
        hmap.animateCamera(cameraUpdate);

        //Geocoder geocoder;
       // List<Address> addresses = null;
       // geocoder = new Geocoder(this.getActivity(), Locale.getDefault());

        //try {
        //    addresses = geocoder.getFromLocation( loaded_position.latitude,loaded_position.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        //} catch (IOException e) {
         //   e.printStackTrace();
        //}
        //String address = addresses.get(0).getAddressLine(0);
        //String adr = prefs.getString("adr", "erreur");




        }

        //Toast.makeText(getActivity(), address, Toast.LENGTH_SHORT).show();
    }


