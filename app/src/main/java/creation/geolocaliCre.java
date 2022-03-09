package creation;

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
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.monconstat.R;
import com.example.monconstat.page1createdc;
import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link geolocaliCre#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class geolocaliCre extends Fragment implements LocationListener {
    TextView geoAdd, titAddres,prefirst;
    ImageView map;
    //FusedLocationProviderClient fusedLocationProviderClient;
    LocationManager locationManager;
    SharedPreferences cret;

    OnDataPass onDataPass;

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    @Override
    public void onLocationChanged(Location location) {
        // Toast.makeText(getActivity(), ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);
            geoAdd.setText(address);
            SharedPreferences.Editor editor = getContext().getSharedPreferences("sp_local", MODE_PRIVATE).edit();
            editor.putString("LATITUDE",String.valueOf(location.getLatitude()));
            editor.putString("LONGITUDE",String.valueOf(location.getLongitude()));
            editor.putString("adr",address);
            editor.commit();





            cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);
            SharedPreferences.Editor editor1 = cret.edit();
            editor1.putString("geo",address);
            editor1.apply();

            Toast.makeText(getActivity(), "************"+cret.getString("geo",null), Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }


        getLocation();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_geolocali_cre, container, false);
        geoAdd = view.findViewById(R.id.geoAdd);
        titAddres = view.findViewById(R.id.titAddres);
            map=view.findViewById(R.id.map);

            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(550); //You can manage the blinking time with this parameter
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            map.startAnimation(anim);
        prefirst=view.findViewById(R.id.prefirst);





        prefirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getContext(), page1createdc.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent2);
            }
        });


      //fusedLocationProviderClient = (FusedLocationProviderClient) LocationServices.getFusedLocationProviderClient(getActivity());
       map.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                boolean HisAvailable= false,GisAvailable = false;
                String G="no",H="no";
                int Hresult = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(getActivity());
                int Gresult = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getActivity());
                HisAvailable = (com.huawei.hms.api.ConnectionResult.SUCCESS == Hresult);
                if (HisAvailable){
                    Navigation.findNavController(view).navigate(R.id.hmaps);
                  //  Toast.makeText(getActivity(),"switching to Huawei map",Toast.LENGTH_SHORT).show();
                }else{
                     Navigation.findNavController(view).navigate(R.id.ggmaps);
                  //  Toast.makeText(getActivity(),"switching to google map",Toast.LENGTH_SHORT).show();


                }
            }
        });


        onDataPass.onDataResceived("Étape 2 :\n Lieu");

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onDataPass= (OnDataPass) context;}


    @SuppressLint("MissingPermission")
    private void getLocation() {
        try {
            locationManager =(LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,500,5, (android.location.LocationListener) this);
        }catch (Exception e){
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "onRequestPermissionsResult: apply LOCATION PERMISSION successful");
            } else {
                Log.i(TAG, "onRequestPermissionsResult: apply LOCATION PERMISSSION  failed");
            }
        }

        if (requestCode == 2) {
            if (grantResults.length > 2 && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "onRequestPermissionsResult: apply ACCESS_BACKGROUND_LOCATION successful");
            } else {
                Log.i(TAG, "onRequestPermissionsResult: apply ACCESS_BACKGROUND_LOCATION  failed");
            }
        }
    }

}