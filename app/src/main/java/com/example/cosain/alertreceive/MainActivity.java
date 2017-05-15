package com.example.cosain.alertreceive;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonVib,buttonPlans ;
    Vibrator vibrator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.alert);
        mediaPlayer.start();
        mediaPlayer.setVolume(50,50);


        buttonVib = (Button) findViewById(R.id.button3) ;
        buttonPlans = (Button) findViewById(R.id.button9);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(6000000);




        buttonVib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mediaPlayer.pause();
                vibrator.cancel();

            }
        });


        buttonPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {
                        "Fire", "Earthquake"
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Action Plans for :")
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                switch (which) {
                                    case 0:
                                        Intent intent = new Intent(getBaseContext(),ActionsPlans.class);
                                        startActivity(intent);

                                        break;
                                    case 1:

                                        Intent intent1 = new Intent(getBaseContext(),EarthQuake.class);
                                        startActivity(intent1);
                                        break;
                                }

                            }
                        });


//                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                    }
//                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });


    }
    public void setButtonStatus(View view){
        Intent intent = new Intent(this, AskUser.class);
        startActivity(intent);
    }
}
