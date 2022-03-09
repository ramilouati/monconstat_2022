package com.example.monconstat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kyanogen.signatureview.SignatureView;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */

public class observation extends Fragment {
    TextView suvitAs3,prefirst3;
//,  ggt = View.findViewById(R.id.textView119)
            //// BOUTTON
    Button b1;
    SharedPreferences sp,cret,imageSp;
    SignatureView sv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_observation, container, false);
 sv = view.findViewById(R.id.signature_view);

       // ggt.setText(cret.getString("circonstances",null));
        suvitAs3= view.findViewById(R.id.suvitAs7);
        b1= view.findViewById(R.id.button7);
        prefirst3=view.findViewById(R.id.prefirst7);
        b1.setOnClickListener(new View.OnClickListener() {

                                  @Override
                                  public void onClick(View v) {
                                      sv.clearCanvas();

                                  }
                              }

        );
        suvitAs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dlg= new AlertDialog.Builder(getContext())

                        .setTitle("Etes-vous sûr que vos informations sont correctes ")
                        .setMessage("\n\n-Cliquez sur Confirmer pour continuer si vos informations sont correctes \n\n-Cliquez sur Modifier pour changer les information\n\n")
                        .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                                Intent intent2 = new Intent(getContext(), bluetoothConnect.class);
                                startActivity(intent2);
                            }
                        }).setNegativeButton("Modifier",null).create();
               // dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.MAGENTA));

                dlg.show();
            }
        });

        prefirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.cameraChoc);
            }
        });
        ////



        return view;
    }

}