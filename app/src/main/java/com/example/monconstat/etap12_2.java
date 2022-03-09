package com.example.monconstat;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


public class etap12_2 extends Fragment {
    SharedPreferences cret;
    TextView suvitAs3,prefirst3;
    CheckBox cb6,cb7,cb8,cb9,cb10,cb11,cb12,cb13;
    String circ2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_etap12_2, container, false);
        cret = getActivity().getSharedPreferences("sp_cret", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = cret.edit();
        suvitAs3= view.findViewById(R.id.svPoD4);
        prefirst3=view.findViewById(R.id.backToL4);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circ2="";
                if (cb6.isChecked()){
                    circ2=circ2+"f";
                }

                if (cb7.isChecked()){
                    circ2=circ2+"g";
                }

                if (cb8.isChecked()){

                    circ2=circ2+"h";

                }

                if (cb9.isChecked()){

                  circ2=circ2+"i";

                }

                if (cb10.isChecked()){

                    circ2=circ2+"j";

                }

                if (cb11.isChecked()){
                    circ2=circ2+"k";

                }

                if (cb12.isChecked()){

                    circ2=circ2+"l";
                }

                if (cb13.isChecked()){

                    circ2=circ2+"m";

                }
                editor.putString("circonstances",cret.getString("circonstances",null)+circ2);
                editor.apply();
                //Toast.makeText(getContext(),cret.getString("circonstances",null), Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.etap12_3);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.etap12_1);
            }
        });

        cb6 = view.findViewById(R.id.checkBox16);
        cb7 = view.findViewById(R.id.checkBox17);
        cb8 = view.findViewById(R.id.checkBox18);
        cb9 = view.findViewById(R.id.checkBox19);
        cb10 = view.findViewById(R.id.checkBox20);
        cb11 = view.findViewById(R.id.checkBox21);
        cb12 = view.findViewById(R.id.checkBox22);
        cb13 = view.findViewById(R.id.checkBox23);





        return view;
    }
}