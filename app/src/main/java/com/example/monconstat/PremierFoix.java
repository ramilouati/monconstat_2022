package com.example.monconstat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import creation.PhotoVideo;

import static com.example.monconstat.FilePath.getPath;

public class PremierFoix extends AppCompatActivity {
    private static final String TAG =PremierFoix.class.getSimpleName(); ;
    TextView envPrem;
    ImageView leftcarP, frontcarP, rightcarP, backcarP, upcarP;
    int choix = 0;
    SharedPreferences sp;
    public String p1, p2, p3, p4, p5;
    String SERVER_URL ;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premier_foix);
        envPrem = findViewById(R.id.envPrem);
        leftcarP = findViewById(R.id.leftcarP);
        frontcarP = findViewById(R.id.frontcarP);
        rightcarP = findViewById(R.id.rightcarP);
        backcarP = findViewById(R.id.backcarP);
        upcarP = findViewById(R.id.upcarP);
        sp = getSharedPreferences("sp_id", MODE_PRIVATE);
        String id = sp.getString("id", null);
        SERVER_URL = "https://www.monconstat.tech/ConstatMobile/uploadFile.php?id="+id;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.CAMERA
                    }, 100);
        }
        leftcarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choix = 1;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);

            }
        });
        frontcarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choix = 2;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);

            }
        });
        rightcarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choix = 3;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);

            }
        });
        backcarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choix = 4;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);

            }
        });
        upcarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choix = 5;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);

            }
        });
        envPrem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PremierFoix.this, p1, Toast.LENGTH_SHORT).show();

                if ((p1!=null)&&(p2!=null)&&(p3!=null)&&(p4!=null)&&(p5!=null)) {
                    verif(p1);
                    verif(p2);
                    verif(p3);
                    verif(p4);
                    verif(p5);
                    send();
                }else Toast.makeText(PremierFoix.this, "compléter la formule de photo", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap capteurImage = (Bitmap) data.getExtras().get("data");

            if (choix == 1) {
                leftcarP.setImageBitmap(capteurImage);
                p1 = getPath(this, getImageUri(this, capteurImage));
            }
            if (choix == 2) {
                frontcarP.setImageBitmap(capteurImage);
                p2 = getPath(this, getImageUri(this, capteurImage));
            }
            if (choix == 3) {
                rightcarP.setImageBitmap(capteurImage);
                p3 = getPath(this, getImageUri(this, capteurImage));
            }
            if (choix == 4) {
                backcarP.setImageBitmap(capteurImage);
                p4 = getPath(this, getImageUri(this, capteurImage));
            }
            if (choix == 5) {
                upcarP.setImageBitmap(capteurImage);
                p5 = getPath(this, getImageUri(this, capteurImage));
            }
        }


    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }


    public void verif(String x)
    {
        if (x != null) {
            dialog = ProgressDialog.show(PremierFoix.this, "", "Uploading File…", true);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    //creating new thread to handle Http Operations
                    uploadFile(x);
                }
            }).start();
        }
    }


    public void send() {
        String id = sp.getString("id", null);
        StringRequest strq = new StringRequest(Request.Method.POST, "https://www.monconstat.tech/ConstatMobile/login.php?id=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.contains("1")) {
                    Intent intent = new Intent(PremierFoix.this, Accueil.class);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("premierfoix", "0");
                    editor.apply();
                    startActivity(intent);
                } else if (response.contains("0"))
                    Toast.makeText(PremierFoix.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PremierFoix.this, error.toString(), Toast.LENGTH_LONG).show();
            }

        });
        Volley.newRequestQueue(PremierFoix.this).add(strq);
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000, true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage, "Title", null);
        return Uri.parse(path);
    }

    public int uploadFile(final String selectedFilePath) {

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);

        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length - 1];

        if (!selectedFile.isFile()) {
            dialog.dismiss();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(PremierFoix.this, "Source File Doesn't Exist: " + selectedFilePath, Toast.LENGTH_SHORT).show();
                }
            });
            return 0;
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(SERVER_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file", selectedFilePath);


                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);

                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0) {
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();

                Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                //response code of 200 indicates the server status OK
                if (serverResponseCode == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(PremierFoix.this, "Uploaded succes", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PremierFoix.this, "File Not Found", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(PremierFoix.this, "URL error!", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(PremierFoix.this, "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            return serverResponseCode;
        }

    }

}







