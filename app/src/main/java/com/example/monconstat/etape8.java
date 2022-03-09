package com.example.monconstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import creation.OnDataPass;


public class etape8 extends Fragment {
    TextView back,next;
    EditText nom1,prenom1,permis1,adresse1,delivre1;
    SharedPreferences sp;
    SharedPreferences cret;
    OnDataPass onDataPass;
    Switch profil;
    String ncond="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sp=this.getActivity().getSharedPreferences("sp_id", Context.MODE_PRIVATE);
        String id=sp.getString("id",null);

        cret=this.getActivity().getSharedPreferences("sp_cret", Context.MODE_PRIVATE);
        String autrecond=cret.getString("autrecond",null);


        View view= inflater.inflate(R.layout.fragment_etape8, container, false);
        back= view.findViewById(R.id.prec);
        next= view.findViewById(R.id.suvitAs3);

        nom1= view.findViewById(R.id.editTextTextPersonName);
        prenom1= view.findViewById(R.id.editTextTextPersonName3);
        adresse1= view.findViewById(R.id.editTextTextPersonName4);
        permis1= view.findViewById(R.id.editTextTextPersonName2);
        delivre1= view.findViewById(R.id.editTextTextPersonName5);

        profil= view.findViewById(R.id.switch2);

         String url="https://www.monconstat.tech/ConstatMobile/profil.php?id="+id;
                    StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONArray juser = new JSONArray(response);
                                JSONObject obj;
                                for (int i = 0; i < juser.length(); i++) {
                                    obj = juser.getJSONObject(i);
                                    String nom = obj.getString("nom");
                                    nom1.setText(nom);
                                    nom1.setBackgroundResource(android.R.color.transparent);
                                    String prenom = obj.getString("prenom");
                                    prenom1.setText(prenom);
                                    prenom1.setBackgroundResource(android.R.color.transparent);
                                    String adresse = obj.getString("adresse");
                                    adresse1.setText(adresse);
                                    adresse1.setBackgroundResource(android.R.color.transparent);
                                    String npermis = obj.getString("npermis");
                                    permis1.setText(npermis);
                                    permis1.setBackgroundResource(android.R.color.transparent);


                                    String delivre = obj.getString("delivre");
                                    String date_sf = delivre;
                                    SimpleDateFormat dt5 = new SimpleDateFormat("yyyy-mm-dd");
                                    Date date10 = dt5.parse(date_sf);
                                    SimpleDateFormat dt10 = new SimpleDateFormat("dd/mm/yyyy");
                                    delivre1.setText(dt10.format(date10).toString());
                                    delivre1.setBackgroundResource(android.R.color.transparent);

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    },new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                        }

                    });
                    Volley.newRequestQueue(getActivity()).add(req);

        Drawable originalDrawable = nom1.getBackground();

        String nom = sp.getString("nom", null);

        nom1.setText(nom);

        String prenom = sp.getString("prenom", null);
        prenom1.setText(prenom);

        String adresse = sp.getString("adresse", null);
        adresse1.setText(adresse);

        String npermis = sp.getString("npermis", null);
        permis1.setText(npermis);


        String delivre = sp.getString("delivre", null);
        String date_sf = delivre;
        SimpleDateFormat dt5 = new SimpleDateFormat("yyyy-mm-dd");
        Date date10 = null;
        try {
            date10 = dt5.parse(date_sf);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dt10 = new SimpleDateFormat("dd/mm/yyyy");
        delivre1.setText(dt10.format(date10).toString());







        profil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Switch State=", ""+isChecked);
                if (profil.isChecked()){

                    nom1.getText().clear();
                    nom1.setBackgroundDrawable(originalDrawable);
                    prenom1.getText().clear();
                    prenom1.setBackgroundDrawable(originalDrawable);
                    adresse1.getText().clear();
                    adresse1.setBackgroundDrawable(originalDrawable);
                    permis1.getText().clear();
                    permis1.setBackgroundDrawable(originalDrawable);
                    delivre1.getText().clear();
                    delivre1.setBackgroundDrawable(originalDrawable);



                }
                else{
                    String url="https://www.monconstat.tech/ConstatMobile/profil.php?id="+id;
                    StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONArray juser = new JSONArray(response);
                                JSONObject obj;
                                for (int i = 0; i < juser.length(); i++) {
                                    obj = juser.getJSONObject(i);
                                    String nom = obj.getString("nom");
                                    nom1.setText(nom);
                                    nom1.setBackgroundResource(android.R.color.transparent);
                                    String prenom = obj.getString("prenom");
                                    prenom1.setText(prenom);
                                    prenom1.setBackgroundResource(android.R.color.transparent);
                                    String adresse = obj.getString("adresse");
                                    adresse1.setText(adresse);
                                    adresse1.setBackgroundResource(android.R.color.transparent);
                                    String npermis = obj.getString("npermis");
                                    permis1.setText(npermis);
                                    permis1.setBackgroundResource(android.R.color.transparent);
                                    String delivre = obj.getString("delivre");
                                    delivre1.setText(delivre);
                                    delivre1.setBackgroundResource(android.R.color.transparent);

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    },new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                        }

                    });
                    Volley.newRequestQueue(getActivity()).add(req);
                }
            }

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ncond=" nom:"+nom1.getText().toString()+
                        ", prenom:"+prenom1.getText().toString()+
                        ", adresse:"+adresse1.getText().toString()+
                        ", permis:"+permis1.getText().toString()+
                        ", delivre:"+delivre1.getText().toString();
                SharedPreferences.Editor editor = cret.edit();
                editor.putString("autrecond",ncond);
                editor.apply();
                //Toast.makeText(getActivity(),cret.getString("autrecond",null).toString(),Toast.LENGTH_LONG).show();

                Navigation.findNavController(view).navigate(R.id.creConducteur);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.steamocieteAssurances);
            }
        });

        onDataPass.onDataResceived("Étape 7 : \n Identité du Conducteur");
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onDataPass= (OnDataPass) context;
    }
}