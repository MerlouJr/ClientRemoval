package com.example.cosain.alertreceive;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AskUser extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private EditText editText;
    private Button buttonSave, btnEvac;
    private static String SENT = "SMS_SENT";
    private static String DELIVERED = "SMS_DELIVERED";
    private static int MAX_SMS_MESSAGE_LENGTH = 160;
    String txtPhoneNo = "5554";
    String txtMessage = "Alert";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_user);
        editText = (EditText) findViewById(R.id.editText);
        buttonSave = (Button) findViewById(R.id.button2);
        btnEvac = (Button) findViewById(R.id.button7);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnEvac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String status = "Evacuating";
                int i = 0;
                if ((editText.length() < 3)) {
                    editText.setError("At least 3 characters long");
                } else if (editText.equals(i)) {
                    editText.setError("Numbers not allowed");

                } else {

                    String phoneNo = txtPhoneNo.toString();
                    String message = txtMessage.toString();
                    sendSMS(phoneNo, message);

                    User user = new User(editText.getText().toString(), status);
                    databaseReference.child("users").push().setValue(user);
                    //databaseReference.child("status").push().setValue(status);

                    finish();
                }
            }
        });


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String status = "Safe";
                String users = editText.getText().toString();
                int i = 0;
                if ((editText.length() < 3)) {
                    editText.setError("At least 3 characters long");
                } else if (editText.equals(i)) {
                    editText.setError("Numbers not allowed");

                } else {

                    String phoneNo = txtPhoneNo.toString();
                    String message = txtMessage.toString();
                    sendSMS(phoneNo, message);
                    User user = new User(editText.getText().toString(), status);
                    databaseReference.child("users").push().setValue(user);

                    finish();

                }
            }
        });
    }

    public void sendSMS(String phoneNumber, String message) {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }
}


