package com.example.monconstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
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


public class etap4 extends Fragment {

    TextView FistToGeo,getdate4,back;
    RadioGroup radioGroup,radioGroup2,R3;
    RadioButton radioButton ,b1,b2;
    int CountSelectB=0;
    int CountSelectB1=0;
    SharedPreferences cret;
    OnDataPass onDataPass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_etap4, container, false);

        cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);
        radioGroup=view.findViewById(R.id.radioGroup2);


        FistToGeo=view.findViewById(R.id.fistToGeo2);
        back=view.findViewById(R.id.prec4);
        getdate4=view.findViewById(R.id.getdate5);
        String date=(String)cret.getString("getdate",null);
        getdate4.setText(date);

        FistToGeo.setTextColor(Color.parseColor("#8c8c8c"));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton=view.findViewById(checkedId);
                SharedPreferences.Editor editor = cret.edit();


                editor.putString("groupB3p2",radioButton.getText().toString() );
                editor.apply();
                Toast.makeText(getActivity(),cret.getString("groupB3p2",null).toString(),Toast.LENGTH_LONG).show();

                CountSelectB=1;
                FistToGeo.setTextColor(Color.parseColor("#5e79a1"));

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.etap3);}
        });

        FistToGeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CountSelectB==1) {
                    Navigation.findNavController(view).navigate(R.id.etap5);}
            }
        });
        onDataPass.onDataResceived("Étape 4 :\n Dégâts Matériels");

        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onDataPass= (OnDataPass) context;
    }
}