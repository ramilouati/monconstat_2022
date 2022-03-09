package com.example.monconstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import creation.OnDataPass;

import static android.content.Context.MODE_PRIVATE;


public class  etap5 extends Fragment {

    TextView Nom,Adresse,Tel,FistToGeo,back;
    EditText Nom1,Nom2,Nom3,prenom1,prenom2,prenom3,
            Adresse1,Adresse2,Adresse3,code1,code2,code3
            ,Tel1,Tel2,Tel3;
    EditText Temoins;
CardView c2,c3;
    SharedPreferences cret;
    OnDataPass onDataPass;
    Button b1,b2,b3,b4;
    public String s2,s3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_etap5, container, false);
        /*SharedPreferences editor = getActivity().getSharedPreferences("sp_local", MODE_PRIVATE);
        String nom1 = editor.getString("nom1", ".");
        String nom2 = editor.getString("nom2", " ");
        String nom3 = editor.getString("nom3", " ");
        String nom4 = editor.getString("nom4", " ");
        String nom5 = editor.getString("nom5", " ");
         */
        SharedPreferences prefs = getActivity().getSharedPreferences("sp_cret", MODE_PRIVATE);
        c2=view.findViewById(R.id.c2);
        c3=view.findViewById(R.id.c3);

        Nom1=view.findViewById(R.id.t1);
        prenom1=view.findViewById(R.id.t2);
        Adresse1=view.findViewById(R.id.t3);
        code1=view.findViewById(R.id.t4);
        Tel1=view.findViewById(R.id.t5);

        Nom2=view.findViewById(R.id.t7);
        prenom2=view.findViewById(R.id.t7);
        Adresse2=view.findViewById(R.id.t8);
        code2=view.findViewById(R.id.t9);
        Tel2=view.findViewById(R.id.t10);

        Nom3=view.findViewById(R.id.t11);
        prenom3=view.findViewById(R.id.t12);
        Adresse3=view.findViewById(R.id.t13);
        code3=view.findViewById(R.id.t14);
        Tel3=view.findViewById(R.id.t15);
       /*
        Nom2=view.findViewById(R.id.t4);
        Adresse2=view.findViewById(R.id.t5);
        Tel2=view.findViewById(R.id.t6);

        Nom3=view.findViewById(R.id.t7);
        Adresse3=view.findViewById(R.id.t8);
        Tel3=view.findViewById(R.id.t9);
*/

        FistToGeo=view.findViewById(R.id.fistToGeo3);
        back=view.findViewById(R.id.prec2);
        c2.setVisibility(View.INVISIBLE);
        c3.setVisibility(View.INVISIBLE);
     //   Tel.setVisibility(View.INVISIBLE);
        //bouton d'ajout
        b1=view.findViewById(R.id.button1);
        b2=view.findViewById(R.id.button2);
        b3=view.findViewById(R.id.button8);
        b4=view.findViewById(R.id.button10);





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2.setVisibility(View.VISIBLE);
                //    Adresse.setVisibility(View.VISIBLE);
                //    Tel.setVisibility(View.VISIBLE);
                //  ajout.setVisibility(View.INVISIBLE);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //    Adresse.setVisibility(View.VISIBLE);
                //    Tel.setVisibility(View.VISIBLE);
                //  ajout.setVisibility(View.INVISIBLE);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override

                public void onClick(View view) {
                    if (c2.isShown())
                    {
                        s2="";
                        s2=prenom2.getText().toString()+"\n"+Nom2.getText().toString()+"\n"+Adresse2.getText().toString()+"\n"+code2.getText().toString()+"\n"+Tel2.getText().toString();


                    }    if (c3.isShown())
                    {
                        s3="";
                        s3=prenom3.getText().toString()+"\n"+Nom3.getText().toString()+"\n"+Adresse3.getText().toString()+"\n"+code3.getText().toString()+"\n"+Tel3.getText().toString();
                    }
                    else {
                        Navigation.findNavController(view).navigate(R.id.steamocieteAssurances);
                    }
                    cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);
                    SharedPreferences.Editor editor = cret.edit();
                    editor.putString("temoins","temoin1:"+prenom1.getText().toString()+"\n"+Nom1.getText().toString()+"\n"+Adresse1.getText().toString()+"\n"+code1.getText().toString()+"\n"+Tel1.getText().toString()+"\ntemoins2:"+s2+"\ntemoins3:"+s3);
                    editor.apply();
                    //Toast.makeText(getActivity(),cret.getString("temoins",null),Toast.LENGTH_LONG).show();
                    Navigation.findNavController(view).navigate(R.id.steamocieteAssurances);

                }

        });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (c2.isShown())
                {
                    s2="";
                    s2=prenom2.getText().toString()+","+Nom2.getText().toString()+","+Adresse2.getText().toString()+","+code2.getText().toString()+","+Tel2.getText().toString();


                }    if (c3.isShown())
                {
                    s3="";
                    s3=prenom3.getText().toString()+","+Nom3.getText().toString()+","+Adresse3.getText().toString()+","+code3.getText().toString()+","+Tel3.getText().toString();
                }
                else {
                    Navigation.findNavController(view).navigate(R.id.steamocieteAssurances);
                }
                cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = cret.edit();
                editor.putString("temoins","temoin1:"+prenom1.getText().toString()+","+Nom1.getText().toString()+","+Adresse1.getText().toString()+","+code1.getText().toString()+","+Tel1.getText().toString()+",temoins2:"+s2+",temoins3:"+s3);
                editor.apply();
                //Toast.makeText(getActivity(),cret.getString("temoins",null),Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).navigate(R.id.steamocieteAssurances);

            }

        });


        FistToGeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c2.isShown())
                {
                    s2="";
                    s2=prenom2.getText().toString()+","+Nom2.getText().toString()+","+Adresse2.getText().toString()+","+code2.getText().toString()+","+Tel2.getText().toString();


                }    if (c3.isShown())
                {
                    s3="";
                    s3=prenom3.getText().toString()+","+Nom3.getText().toString()+","+Adresse3.getText().toString()+","+code3.getText().toString()+","+Tel3.getText().toString();
                }
                else {
                    Navigation.findNavController(view).navigate(R.id.steamocieteAssurances);
                }
                cret=getActivity().getSharedPreferences("sp_cret",getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = cret.edit();
                editor.putString("temoins","temoin1:"+prenom1.getText().toString()+","+Nom1.getText().toString()+","+Adresse1.getText().toString()+","+code1.getText().toString()+","+Tel1.getText().toString()+",temoins2:"+s2+",temoins3:"+s3);
                editor.apply();
                //Toast.makeText(getActivity(),cret.getString("temoins",null),Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).navigate(R.id.steamocieteAssurances);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.etap4);}
        });




        onDataPass.onDataResceived("Étape 5 :\n Témoins ");
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onDataPass= (OnDataPass) context;
    }

}


