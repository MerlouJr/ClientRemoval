package com.example.cosain.alertreceive;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmergencyClass extends AppCompatActivity {


    private Button safe ,evac, actions;
    private EditText name;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_class);

        safe = (Button) findViewById(R.id.button4);
        actions = (Button) findViewById(R.id.button5);
        evac = (Button) findViewById(R.id.button8);
        name = (EditText) findViewById(R.id.editText2);

        db = FirebaseDatabase.getInstance().getReference();

        safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String status = "safe";
                User user = new User(name.getText().toString(),status);
                db.child("users").push().setValue(user);
                finish();
            }
        });


        evac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String status = "evacuating";
                User user = new User(name.getText().toString(),status);
                db.child("users").push().setValue(user);
                finish();

            }
        });

        actions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent();
//                i.setClassName("com.example.cosain.alertreceive", "com.example.cosain.alertreceive.ActionPlans");
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
                    final CharSequence[] items1 = {
                            "Fire", "Emergency"
                    };

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(EmergencyClass.this);
                    builder1.setTitle("Choose Emergency Action according to your situation: ")
                            .setSingleChoiceItems(items1, -1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    switch (which) {

                                        case 0:

                                            break;

                                        case 1:


                                          break;

                                        case 2:
                                            // TODO Some codes for deletion of item


                                            break;


                                    }


                                }
                            });

                    AlertDialog alert = builder1.create();
                    alert.show();

                }



        });



    }
}
