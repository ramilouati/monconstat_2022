package com.example.monconstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SteamocieteAssurances# newInstance} factory method to
 * create an instance of this fragment.
 */
public class SteamocieteAssurances extends Fragment {
    TextView dcin,suvitAs,prefirst;
    SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_steamociete_assurances, container, false);

        //// BOUTTON

        suvitAs= view.findViewById(R.id.suvitAs2);
        prefirst=view.findViewById(R.id.prefirst2);
        suvitAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.creConducteur);
            }
        });
        prefirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.geolocaliCre);
            }
        });
        ////
        sp=this.getActivity().getSharedPreferences("sp_id", Context.MODE_PRIVATE);
        String id=sp.getString("id",null);
        String url="https://achref12.000webhostapp.com/constat/login/contra.php?id="+id;

        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);
                        String id_contrat = obj.getString("id_contrat");
                        dcin = view.findViewById(R.id.PA);
                        dcin.setText(id_contrat);

                        id_contrat = obj.getString("assurance");
                        dcin = view.findViewById(R.id.VA);
                        dcin.setText(id_contrat);

                    //   String nom = obj.getString("immat");

                        String debut = obj.getString("debut");
                        dcin = view.findViewById(R.id.dated);
                        dcin.setText(debut);
                        String fin = obj.getString("fin");
                        dcin = view.findViewById(R.id.datef);
                        dcin.setText(fin);
                        String npermis = obj.getString("id_agence");
                        dcin = view.findViewById(R.id.agn);
                        dcin.setText(npermis);


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

        return view;
    }
}