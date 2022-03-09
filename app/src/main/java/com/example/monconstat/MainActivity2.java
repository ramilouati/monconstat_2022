package com.example.monconstat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.Bundle;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.monconstat.R;

import static com.example.monconstat.FilePath.getPath;

import creation.OnDataPass;

public class MainActivity2 extends AppCompatActivity {

    Button b1, b2, b3,b4;
    TextView s, t,BackToL;
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
    VideoView vd1,vd2,vd3,vd4;
    int choix, s1,num;
    public int tailleTab;
    SharedPreferences imageSp;
    SharedPreferences cret;
    Drawable originalDrawable;
    public int [] tb = new int[20];
    public String[] tabdata=new String[30];
    public int indexpv,x=0;
    OnDataPass onDataPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity2.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    }, 100);


        }
        if (ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity2.this,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 100);
        }


        imageSp = getApplicationContext().getSharedPreferences("sp_img", getApplicationContext().MODE_PRIVATE);
        cret=getApplicationContext().getSharedPreferences("sp_cret",getApplicationContext().MODE_PRIVATE);
        num = 9;

        img1 = findViewById(R.id.clf);
        img2 = findViewById(R.id.cf);
        img3 = findViewById(R.id.cfr);

        img4 = findViewById(R.id.cl);
        img5 = findViewById(R.id.cu);
        img6 = findViewById(R.id.cr);

        img7 = findViewById(R.id.cbl);
        img8 = findViewById(R.id.cb);
        img9 = findViewById(R.id.cbr);



      /*  b1 = findViewById(R.id.pv1);
        b2 = findViewById(R.id.pv2);
        b3 = findViewById(R.id.pv3);
        b4 = findViewById(R.id.pv4);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);

        vd1 = findViewById(R.id.vd1);
        vd2 = findViewById(R.id.vd2);
        vd3 = findViewById(R.id.vd3);
        vd4 = findViewById(R.id.vd4);

        vd1.setVisibility(vd1.GONE);
        vd2.setVisibility(vd2.GONE);
        vd3.setVisibility(vd3.GONE);
        vd4.setVisibility(vd4.GONE);*/

   /*     vd1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
            }
        });
        vd2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
            }
        });
        vd3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
            }
        });
        vd4.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
            }
        });*/

        SharedPreferences.Editor editor = cret.edit();
        editor.putString("dataBase",null);
        editor.apply();


        indexpv=9;




        s = findViewById(R.id.next);
        BackToL = findViewById(R.id.back);
        t = findViewById(R.id.titchoc);
        choix = 0;
        tailleTab= 0;
    //    originalDrawable = b2.getBackground();


        cret=getApplicationContext().getSharedPreferences("sp_cret",getApplicationContext().MODE_PRIVATE);

        ///////////onclick
     //   verif();
         indexpage(0);

         s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (s1 < 0) {
                    Toast.makeText(getApplicationContext(), "il faut prendre 9 Photos ou videos !", Toast.LENGTH_SHORT).show();
                } else {
          /*          b1.setBackgroundResource(R.drawable.custom_button);

                    b2.setBackgroundDrawable(originalDrawable);
                    b3.setBackgroundDrawable(originalDrawable);
                    b4.setBackgroundDrawable(originalDrawable);*/
                    num++;

                        SharedPreferences.Editor editor = cret.edit();
                        for (int i = 0; i < tabdata.length; i++) {

                                Toast.makeText(getApplicationContext(), tabdata[i], Toast.LENGTH_SHORT).show();
                                editor.putString("dataBase["+String.valueOf(x)+"]", tabdata[i]);
                                x++;
                                editor.putString("LengthT", String.valueOf(x));

                        }
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                        startActivity(intent);
                    indexpv += 9;
             /*       img1.setImageBitmap(null);
                    img2.setImageBitmap(null);
                    img3.setImageBitmap(null);
                    img4.setImageBitmap(null);
                    //////////
                    vd1.setVisibility(vd1.GONE);
                    vd2.setVisibility(vd2.GONE);
                    vd3.setVisibility(vd3.GONE);
                    vd4.setVisibility(vd4.GONE);
                    vd1.setVideoURI(null);
                    vd2.setVideoURI(null);
                    vd3.setVideoURI(null);
                    vd4.setVideoURI(null);*/
                    indexpage(num);
                }
            }
        });

        BackToL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //b1.setBackgroundColor(Color.parseColor("#3F51B5"));
        /*        b1.setBackgroundResource(R.drawable.custom_button);
                b2.setBackgroundDrawable(originalDrawable);
                b3.setBackgroundDrawable(originalDrawable);
                b4.setBackgroundDrawable(originalDrawable);*/
                num--;
                Toast.makeText(getApplicationContext(), String.valueOf(num), Toast.LENGTH_SHORT).show();
                if (num>=0) {
                    indexpv-=9;
                    indexpage(num);
                }
                //  Navigation.findNavController(view).navigate(R.id.cameraChoc);

            }
        });




        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=1;


                selectionB();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=2;
                selectionB();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=3;
                selectionB();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=4;
                selectionB();
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=5;


                selectionB();
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=6;
                selectionB();
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=7;
                selectionB();
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=8;
                selectionB();
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=9;
                selectionB();
            }
        });

//        onDataPass.onDataResceived("Étape 11 : \n Dégât apparents ");


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Toast.makeText(getContext(), uri.toString() , Toast.LENGTH_SHORT).show();
        //Toast.makeText(PhotoVideo.this, FilePath.getPath(getContext(), uri) , Toast.LENGTH_SHORT).show();
        if (choix == 1) {
            if (requestCode == 100) {
                Bitmap capteurImage = (Bitmap) data.getExtras().get("data");
                Uri uri = getImageUri(getApplicationContext(), capteurImage);

                if (s1==1) {
                    img1.setBackgroundResource (R.drawable.borderv);

                }else if(s1==2){
                    img2.setBackgroundResource (R.drawable.borderv);

                }else if(s1==3){
                    img3.setBackgroundResource (R.drawable.borderv);

                }else if(s1==4){
                    img4.setBackgroundResource (R.drawable.borderv);

                }else if(s1==5){
                    img5.setBackgroundResource (R.drawable.borderv);

                }else if(s1==6){
                    img6.setBackgroundResource (R.drawable.borderv);

                }else if(s1==7){
                    img7.setBackgroundResource (R.drawable.borderv);

                }else if(s1==8){
                    img8.setBackgroundResource (R.drawable.borderv);

                }else {
                    img9.setBackgroundResource (R.drawable.borderv);

                }
                tabdata[indexpv-s1]=  getPath(getApplicationContext(),uri);
                Toast.makeText(getApplicationContext(), tabdata[indexpv-s1] +" "+(indexpv-s1)+" "+s1, Toast.LENGTH_SHORT).show();
            }

        } else if (choix == 2) {
            if (requestCode == 100) {
                Uri uri=data.getData();
                if (s1==1) {
                    img1.setBackgroundResource(R.drawable.borderv);

                }else if(s1==2){
                    img2.setBackgroundResource(R.drawable.borderv);

                }else if(s1==3){
                    img3.setBackgroundResource(R.drawable.borderv);

                }else if(s1==4){
                    img4.setBackgroundResource(R.drawable.borderv);

                }else if(s1==5){
                    img5.setBackgroundResource(R.drawable.borderv);

                }else if(s1==6){
                    img6.setBackgroundResource(R.drawable.borderv);

                }else if(s1==7){
                    img7.setBackgroundResource(R.drawable.borderv);

                }else if(s1==8){
                    img8.setBackgroundResource(R.drawable.borderv);

                }else {
                    img9.setBackgroundResource(R.drawable.borderv);

                }

                tabdata[indexpv-s1]=  getPath(getApplicationContext(),uri);
            }

        }
    }



    public void verif() {
        int i=0;
        if (imageSp.getString("lf", null).equals("checked"))  {
            tb[i]=1;
            i++;
        }if (imageSp.getString("f", null).equals("checked"))  {
            tb[i]=2;
            i++;
        }if (imageSp.getString("rf", null).equals("checked")) {
            tb[i]=3;
            i++;

        }if (imageSp.getString("l", null).equals("checked")){
            tb[i]=4;
            i++;
        } if (imageSp.getString("u", null).equals("checked"))  {
            tb[i]=5;
            i++;
        } if (imageSp.getString("r", null).equals("checked"))  {
            tb[i]=6;
            i++;
        }  if (imageSp.getString("lb", null).equals("checked"))  {
            tb[i]=7;
            i++;
        }  if (imageSp.getString("ba", null).equals("checked"))  {
            tb[i]=8;
            i++;
        }  if (imageSp.getString("rb", null).equals("checked"))  {
            tb[i]=9;
            i++;
        }
        tailleTab=i;

    }



    public void  selectionB(){



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Selection Type Video/Photo")
                .setPositiveButton("Photo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        choix = 1;
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 100);
                    }
                })
                .setNegativeButton("Video", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        choix = 2;
                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
                        startActivityForResult(intent, 100);
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();


        /*card.setVisibility(card.VISIBLE);
     p.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick (View view){
        card.setVisibility(card.INVISIBLE);
        choix = 1;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);
    }
    });

     v.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick (View view){
        card.setVisibility(card.INVISIBLE);
        choix = 2;
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
        startActivityForResult(intent, 100);
    }
    });

    a.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick (View view){
        card.setVisibility(card.INVISIBLE);
    }
    });*/
    }
    public void indexpage(int i){
        if (tb[i] == 1) {
            t.setText("left-front");
        } else if (tb[i] == 2) {
            t.setText("Front");
        } else if (tb[i] == 3) {
            t.setText("Right-front");
        } else if (tb[i] == 4) {
            t.setText("Left");
        } else if (tb[i] == 5) {
            t.setText("Rev");
        } else if (tb[i] == 6) {
            t.setText("Right");
        } else if (tb[i] == 7) {
            t.setText("Left-back");
        } else if (tb[i] == 8) {
            t.setText("Back");
        } else if (tb[i] == 9) {
            t.setText("Right-back");
        } else t.setText("non");
        t.setText("back");
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {

        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000,true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage,
                "Title", null);
        return Uri.parse(path);
    }





}