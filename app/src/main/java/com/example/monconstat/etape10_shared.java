package com.example.monconstat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class etape10_shared extends AppCompatActivity {
    ImageView f,fl,fr,m,ml,mr,b,bl,br;
    ImageView f2,fl2,fr2,m2,ml2,mr2,b2,bl2,br2;
    TextView back,next;
    String code,RoE;
    CardView top;
    SharedPreferences rvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape10_shared);
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
        top = findViewById(R.id.cardView3);
        top.setMinimumHeight(height/2);
        f = findViewById(R.id.cf);
        fr = findViewById(R.id.cfr);
        fl = findViewById(R.id.clf);
        m = findViewById(R.id.cu);
        mr = findViewById(R.id.cr);
        ml = findViewById(R.id.cl);
        b = findViewById(R.id.cb);
        br = findViewById(R.id.cbr);
        bl = findViewById(R.id.cbl);

        f2 = findViewById(R.id.cf2);
        fr2 = findViewById(R.id.cfr2);
        fl2 = findViewById(R.id.clf2);
        m2 = findViewById(R.id.cu2);
        mr2 = findViewById(R.id.cr2);
        ml2 = findViewById(R.id.cl2);
        b2 = findViewById(R.id.cb2);
        br2 = findViewById(R.id.cbr2);
        bl2 = findViewById(R.id.cbl2);

        f.setVisibility(View.INVISIBLE);
        fr.setVisibility(View.INVISIBLE);
        fl.setVisibility(View.INVISIBLE);
        m.setVisibility(View.INVISIBLE);
        mr.setVisibility(View.INVISIBLE);
        ml.setVisibility(View.INVISIBLE);
        b.setVisibility(View.INVISIBLE);
        br.setVisibility(View.INVISIBLE);
        bl.setVisibility(View.INVISIBLE);

        f2.setVisibility(View.INVISIBLE);
        fr2.setVisibility(View.INVISIBLE);
        fl2.setVisibility(View.INVISIBLE);
        m2.setVisibility(View.INVISIBLE);
        mr2.setVisibility(View.INVISIBLE);
        ml2.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        br2.setVisibility(View.INVISIBLE);
        bl2.setVisibility(View.INVISIBLE);

        next = findViewById(R.id.next5);
        back = findViewById(R.id.back5);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i1 = new Intent(etape10_shared.this, Final.class);
                i1.putExtra("RoE", RoE);
                i1.putExtra("code", code);
                startActivity(i1);

           /*     Intent intent2 = new Intent(etape10_shared.this, etape12_shared.class);
                intent2.putExtra("RoE", RoE);
                intent2.putExtra("code", code);
                startActivity(intent2);*/
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(etape10_shared.this, etape9_shared.class);
                intent2.putExtra("RoE", RoE);
                intent2.putExtra("code", code);
                startActivity(intent2);
            }
        });


        rvInfo=getSharedPreferences("rvInfo",MODE_PRIVATE);
        Intent intentg = getIntent();
        if (intentg.getStringExtra("code") != null) {
            code = intentg.getStringExtra("code");
            Toast.makeText(this, code, Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor = rvInfo.edit();
            editor.putString("code", code);
            editor.apply();
        } else code = rvInfo.getString("code", null);

        onshow();
    }
    public void onshow(){
        String url="https://www.monconstat.tech/ConstatMobile/getinfo.php?id="+code+"&info=i";
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray juser = new JSONArray(response);
                    JSONObject obj;
                    for (int i = 0; i < juser.length(); i++) {
                        obj = juser.getJSONObject(i);

                        String degatE = obj.getString("pointchocE");
                        Toast.makeText(etape10_shared.this,degatE,Toast.LENGTH_SHORT);

                        if (degatE.contains(",lf,")){
                            fl.setVisibility(View.VISIBLE);
                            fl.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","fl");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatE.contains(",f,")){
                            f.setVisibility(View.VISIBLE);
                            f.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","f");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatE.contains(",rf,")){
                            fr.setVisibility(View.VISIBLE);
                            fr.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","fr");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatE.contains(",l,")){
                            ml.setVisibility(View.VISIBLE);
                            ml.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","ml");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatE.contains(",u,")){
                            m.setVisibility(View.VISIBLE);
                            m.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","m");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatE.contains(",r,")){
                            mr.setVisibility(View.VISIBLE);
                            mr.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","mr");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatE.contains(",lb,")){
                            bl.setVisibility(View.VISIBLE);
                            bl.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","bl");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatE.contains(",ba,")){
                            b.setVisibility(View.VISIBLE);
                            b.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","b");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatE.contains(",rb,")){
                            br.setVisibility(View.VISIBLE);
                            br.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","br");
                                    startActivity(intent2);
                                }
                            });
                        }

                        String degatR = obj.getString("pointchocR");
                        Toast.makeText(etape10_shared.this,degatR,Toast.LENGTH_SHORT);

                        if (degatR.contains(",lf,")){
                            fl2.setVisibility(View.VISIBLE);
                            fl2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","fl2");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatR.contains(",f,")){
                            f2.setVisibility(View.VISIBLE);
                            f2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","f2");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatR.contains(",rf,")){
                            fr2.setVisibility(View.VISIBLE);
                            fr2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","fr2");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatR.contains(",l,")){
                            ml2.setVisibility(View.VISIBLE);
                            ml2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","ml2");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatR.contains(",u,")){
                            m2.setVisibility(View.VISIBLE);
                            m2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","m2");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatR.contains(",r,")){
                            mr2.setVisibility(View.VISIBLE);
                            mr2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","mr2");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatR.contains(",lb,")){
                            bl2.setVisibility(View.VISIBLE);
                            bl2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","bl2");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatR.contains(",ba,")){
                            b2.setVisibility(View.VISIBLE);
                            b2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","b2");
                                    startActivity(intent2);
                                }
                            });
                        }
                        if (degatR.contains(",rb,")){
                            br2.setVisibility(View.VISIBLE);
                            br2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(etape10_shared.this, Apercu.class);
                                    intent2.putExtra("RoE", RoE);
                                    intent2.putExtra("code", code);
                                    intent2.putExtra("point","br2");
                                    startActivity(intent2);
                                }
                            });
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(etape10_shared.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(etape10_shared.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(etape10_shared.this).add(req);
    }
}