package creation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.monconstat.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SteamocieteAssurances extends Fragment {
    TextView dcin,dcin1,dcin2,dcin3,dcin4,dcin5,dcin6,suvitAs,prefirst;
    SharedPreferences sp;
    OnDataPass onDataPass;
    public boolean isInternetAvailable()
    {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_steamociete_assurances, container, false);
        dcin = view.findViewById(R.id.PA);

        dcin1 = view.findViewById(R.id.VA);

        dcin2 = view.findViewById(R.id.dated);

        dcin3 = view.findViewById(R.id.datef);

        dcin4 = view.findViewById(R.id.agn);
        //// BOUTTON

        suvitAs= view.findViewById(R.id.suvitAs2);
        prefirst=view.findViewById(R.id.prefirst2);
        suvitAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.etape8);
            }
        });
        prefirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.etap5);
            }
        });
        ////
        sp=this.getActivity().getSharedPreferences("sp_id", Context.MODE_PRIVATE);
        String id=sp.getString("id",null);


        String url="https://monconstat.tech/ConstatMobile/contra.php?id="+id;
   /*     StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){


            @Override
            public void onResponse(String response) {
                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);
                        String id_contrat = obj.getString("id_contrat");
                        dcin.setText(id_contrat);

                        id_contrat = obj.getString("assurance");
                        dcin1.setText(id_contrat);

                        String nom = obj.getString("immat");



                        String debut = obj.getString("debut");
                        String date_s = debut;
                        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
                        Date date = dt.parse(date_s);
                        SimpleDateFormat dt1 = new SimpleDateFormat("dd/mm/yyyy");
                        dcin2.setText(dt1.format(date).toString());

                        String fin = obj.getString("fin");
                        String date_sf = fin;
                        SimpleDateFormat dt5 = new SimpleDateFormat("yyyy-mm-dd");
                        Date date10 = dt5.parse(date_sf);
                        SimpleDateFormat dt10 = new SimpleDateFormat("dd/mm/yyyy");
                        dcin3.setText(dt10.format(date10).toString());



                        String npermis = obj.getString("id_agence");
                        dcin4.setText(npermis);


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

*/



            String id_contrat1 = sp.getString("id_contrat", null);

        dcin.setText(id_contrat1);

            String assurance1 = sp.getString("assurance", null);
            dcin1.setText(assurance1);

            String id_agence1 = sp.getString("id_agence", null);
            dcin4.setText(id_agence1);

            String debut1 = sp.getString("debut", null);
            dcin2.setText(debut1);

            String fin1 = sp.getString("fin", null);
            dcin3.setText(fin1);




        onDataPass.onDataResceived("Étape 6 :\nSociété d'Assurance");
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onDataPass= (OnDataPass) context;
    }

}