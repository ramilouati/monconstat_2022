package com.example.monconstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import creation.OnDataPass;


public class etap12_1 extends Fragment {

    TextView suvitAs3,prefirst3,test;
    OnDataPass onDataPass;
    CheckBox cb1,cb2,cb3,cb4,cb5;
    SharedPreferences cret;
    String circ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_etap12_1, container, false);

        suvitAs3 = view.findViewById(R.id.svPoD11);
        prefirst3 = view.findViewById(R.id.backToL11);
        cb1 = view.findViewById(R.id.checkBox2);
        cb2 = view.findViewById(R.id.checkBox3);
        cb3 = view.findViewById(R.id.checkBox4);
        cb4 = view.findViewById(R.id.checkBox6);
        cb5 = view.findViewById(R.id.checkBox7);
        cret = getActivity().getSharedPreferences("sp_cret", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = cret.edit();
        circ = cret.getString("circonstances",null);


        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circ="";

                editor.putString("circonstances"," ");
                if (cb1.isChecked()){

                    circ=circ+"a";

                }

                if (cb2.isChecked()){

                    circ=circ+"b";

                }

                if (cb3.isChecked()){

                    circ=circ+"c";
                }

                if (cb4.isChecked()){

                    circ=circ+"d";

                }

                if (cb5.isChecked()){

                    circ=circ+"e";
                }

                editor.putString("circonstances",circ);
                editor.apply();
                Toast.makeText(getContext(),cret.getString("circonstances",null), Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.etap12_2);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.cameraChoc);
            }
        });


        onDataPass.onDataResceived("Étape 12 : Circonstances");


        return view;
    }
    // OnDataPass onDataPass;
//onDataPass.onDataResceived("Etap 12");
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onDataPass= (OnDataPass) context;
    }
}