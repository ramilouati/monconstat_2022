package com.example.monconstat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import creation.OnDataPass;


public class etap3 extends Fragment {
    TextView  FistToGeo,getdate4,back;
    RadioGroup radioGroup,radioGroup2;
    RadioButton radioButton;
    int CountSelectB=0;
    SharedPreferences cret;
    OnDataPass onDataPass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_etap3, container, false);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.MANAGE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{
                            Manifest.permission.MANAGE_EXTERNAL_STORAGE
                    }, 100);
        }
        cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);
        radioGroup=view.findViewById(R.id.radioGroupe);
        FistToGeo=view.findViewById(R.id.fistToGeo);
        back=view.findViewById(R.id.prec3);
        getdate4=view.findViewById(R.id.getdate4);
        String date=(String)cret.getString("getdate",null);
        getdate4.setText(date);

        FistToGeo.setTextColor(Color.parseColor("#8c8c8c"));
      //  //Toast.makeText(getActivity(), cret.getString("geo",null), Toast.LENGTH_SHORT).show();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton=view.findViewById(checkedId);
                SharedPreferences.Editor editor = cret.edit();
                editor.putString("groupB1p1",radioButton.getText().toString());
                editor.apply();
                CountSelectB=1;
                    FistToGeo.setTextColor(Color.parseColor("#5e79a1"));

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.geolocaliCre);}
        });

        FistToGeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CountSelectB==1) {
                    Navigation.findNavController(view).navigate(R.id.etap4);
                }
            }
        });
        onDataPass.onDataResceived("Étape 3 :\n Blessées");
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onDataPass= (OnDataPass) context;
    }
}