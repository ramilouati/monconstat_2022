package com.example.monconstat;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PointDeChoc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PointDeChoc extends Fragment {
    TextView suvitAs3,prefirst3;
    CheckBox f,lf,rf,l,r,lb,rb,ba,u;
    SharedPreferences imageSp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_point_de_choc, container, false);
        imageSp=getActivity().getSharedPreferences("sp_img",getActivity().MODE_PRIVATE);

        ////////
        SharedPreferences.Editor editor = imageSp.edit();
        editor.putString("f","no");
        editor.putString("lf","no");
        editor.putString("rf","no");
        editor.putString("l","no");
        editor.putString("u","no");
        editor.putString("r","no");
        editor.putString("lb","no");
        editor.putString("ba","no");
        editor.putString("rb","no");
        editor.apply();
        ///////

        //// BOUTTON
      f=view.findViewById(R.id.f);
      f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
              SharedPreferences.Editor editor = imageSp.edit();
              if(f.isChecked())
              {
                  editor.putString("f","checked");
                  editor.apply();
              }
          }
      });
        lf=view.findViewById(R.id.lf);
        lf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = imageSp.edit();
                if(lf.isChecked())
                {
                    editor.putString("lf","checked");
                    editor.apply();
                }
            }
        });
        rf=view.findViewById(R.id.rf);
        rf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = imageSp.edit();
                if(rf.isChecked())
                {
                    editor.putString("rf","checked");
                    editor.apply();
                }
            }
        });
        l=view.findViewById(R.id.l);
        l.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = imageSp.edit();
                if(l.isChecked())
                {
                    editor.putString("l","checked");
                    editor.apply();
                }
            }
        });
        u=view.findViewById(R.id.u);
        u.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = imageSp.edit();
                if(u.isChecked())
                {
                    editor.putString("u","checked");
                    editor.apply();
                }
            }
        });
        r=view.findViewById(R.id.r);
        r.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = imageSp.edit();
                if(r.isChecked())
                {
                    editor.putString("r","checked");
                    editor.apply();
                }
            }
        });
        lb=view.findViewById(R.id.lb);
        lb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = imageSp.edit();
                if(lb.isChecked())
                {
                    editor.putString("lb","checked");
                    editor.apply();
                }
            }
        });
        ba=view.findViewById(R.id.ba);
        ba.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = imageSp.edit();
                if(ba.isChecked())
                {
                    editor.putString("ba","checked");
                    editor.apply();
                }
            }
        });
        rb=view.findViewById(R.id.rb);
        rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = imageSp.edit();
                if(rb.isChecked())
                {
                    editor.putString("rb","checked");
                    editor.apply();
                }
            }
        });

///////////////////

        suvitAs3= view.findViewById(R.id.suvitAs5);
        prefirst3=view.findViewById(R.id.prefirst5);
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.cameraChoc);
            }
        });
        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.identiteVehicule);
            }
        });
        ////


        return view;
    }
}