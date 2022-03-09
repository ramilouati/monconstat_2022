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


public class etap12_3 extends Fragment {
    String circ3;
    TextView suvitAs3,prefirst3,test,hht;
    SharedPreferences cret;
    CheckBox cb14,cb15,cb16,cb17;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_etap12_3, container, false);
        cret = getActivity().getSharedPreferences("sp_cret", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = cret.edit();
        suvitAs3= view.findViewById(R.id.svPoD2);
        prefirst3=view.findViewById(R.id.backToL2);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circ3="";
                if (cb14.isChecked()){

                    circ3=circ3+"n";
                }

                if (cb15.isChecked()){

                    circ3=circ3+"o";
                }

                if (cb16.isChecked()){

                    circ3=circ3+"p";
                }

                if (cb16.isChecked()){

                   circ3=circ3+"q";
                }
                editor.putString("circonstances",cret.getString("circonstances",null)+circ3);
                editor.apply();
                //Toast.makeText(getContext(),cret.getString("circonstances",null), Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.videoPanoramique);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.etap12_2);
            }
        });
        test= view.findViewById(R.id.textView104);
        cb14 = view.findViewById(R.id.checkBox11);
        cb15 = view.findViewById(R.id.checkBox12);
        cb16 = view.findViewById(R.id.checkBox13);
        cb17 = view.findViewById(R.id.checkBox14);
        return view;
    }
}