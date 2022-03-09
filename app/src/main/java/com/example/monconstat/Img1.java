package com.example.monconstat;

import static java.security.AccessController.getContext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.SharedPreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import static com.example.monconstat.FilePath.getPath;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Img1 extends AppCompatActivity {
    CardView card;
    Button b1, b2, b3, b4,finish;
    TextView b, n;
    ImageView img1,img2,img3,img4;
    int choix, s1;
    SharedPreferences cret;
  String  code;


    String imgv1,imgv2,imgv3,imgv4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img1);
        cret=getSharedPreferences("sp_cret",MODE_PRIVATE);
        Intent intent2= getIntent();

        code = intent2.getStringExtra("code");
        b1 = findViewById(R.id.button);
        finish = findViewById(R.id.button6);
        b2 = findViewById(R.id.button3);
        b3 = findViewById(R.id.button4);
        b4 = findViewById(R.id.button5);
        img1 =findViewById(R.id.imageView7);
        img2 =findViewById(R.id.imageView8);
        img3 =findViewById(R.id.imageView9);
        img4 =findViewById(R.id.imageView10);
        b = findViewById(R.id.back);
        n = findViewById(R.id.next);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choix = 1;
                s1 = 1;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choix = 1;
                s1 = 2;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);

           //     img1.setImageBitmap(capteurImage);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choix = 1;
                s1 = 3;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choix = 1;
                s1 = 4;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    Navigation.findNavController(view).navigate(R.id.a);
            }
        });

        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //    Navigation.findNavController(view).navigate(R.id.);

            }

        });
        finish.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                StringRequest strq = new StringRequest(Request.Method.POST, "https://www.monconstat.tech/ConstatMobile/imgupload.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Img1.this, response, Toast.LENGTH_SHORT).show();
                       /* if(response.contains("1")){
                            Toast.makeText(Img1.this, "!", Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(Img1.this, Accueil.class);
                            startActivity(intent2);
                            finish();
                        }*/
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Img1.this, error.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("code","1234");

                        params.put("img1",imgv1);
                        params.put("img2",imgv2);
                        params.put("img3",imgv3);
                        params.put("img4",imgv4);

                        //Toast.makeText(Img1.this, bt2.toString(), Toast.LENGTH_SHORT).show();


                        return params;
                    }

                };
                Volley.newRequestQueue(Img1.this).add(strq);
                //  requestQueue.add(strq);

            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences.Editor editor = cret.edit();

        if (choix == 1) {
            if (requestCode == 100) {
                Bitmap capteurImage = (Bitmap) data.getExtras().get("data");
                if (s1==1) {
                    String base64S1 = ImageUtil.convert(capteurImage);
                    Bitmap b1 = ImageUtil.convert1(base64S1);
                    img1.setImageBitmap(b1);

                 this.imgv1=base64S1;
                    Toast.makeText(getApplicationContext(), imgv1, Toast.LENGTH_SHORT).show();

                   /* editor.putString("imgv1",base64S1.toString());
                    editor.apply();*/
                }else if(s1==2){
                    String base64S2 = ImageUtil.convert(capteurImage);
                    Bitmap b2 = ImageUtil.convert1(base64S2);
                    img2.setImageBitmap(b2);
                    Toast.makeText(getApplicationContext(), imgv2, Toast.LENGTH_SHORT).show();

                    this.imgv2=base64S2;

                 /*   editor.putString("imgv2",base64S2.toString());
                    editor.apply();*/
                }else if(s1==3){
                    String base64S3 = ImageUtil.convert(capteurImage);
                    Bitmap b3 = ImageUtil.convert1(base64S3);
                    img3.setImageBitmap(b3);
                    this.imgv3=base64S3;
                    Toast.makeText(getApplicationContext(), imgv3, Toast.LENGTH_SHORT).show();

                   /* editor.putString("imgv3",base64S3.toString());
                    editor.apply();*/
                }
                else{
                    String base64S4 = ImageUtil.convert(capteurImage);
                    Bitmap b4 = ImageUtil.convert1(base64S4);
                    img4.setImageBitmap(b4);
                    this.imgv4=base64S4;
                    Toast.makeText(getApplicationContext(), imgv4, Toast.LENGTH_SHORT).show();

                  /*  editor.putString("imgv4",base64S4.toString());
                    editor.apply();*/
                }

            }


        }
    }
        public Uri getImageUri(Context inContext, Bitmap inImage) {
            Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000,true);
            String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage, "Title", null);
            return Uri.parse(path);
        }

}