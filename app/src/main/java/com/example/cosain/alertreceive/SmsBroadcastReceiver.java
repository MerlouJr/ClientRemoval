package com.example.cosain.alertreceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by cosain on 4/21/2017.
 */

public class SmsBroadcastReceiver extends BroadcastReceiver {


    public void onReceive(Context context, Intent intent) {
        // Get the message
        Bundle extras = intent.getExtras();

        // Set object message in android device
        SmsMessage[] smgs = null;

        // Content SMS message
        String infoSMS = "";

        if (extras != null){
            // Retrieve the SMS message received
            Object[] pdus = (Object[]) extras.get("pdus");
            smgs = new SmsMessage[pdus.length];

            for (int i=0; i<smgs.length; i++){
                smgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                infoSMS += smgs[i].getMessageBody().toString();
                infoSMS += "\n";
            }

            final String mes = infoSMS.toString();

            Toast.makeText(context, infoSMS, Toast.LENGTH_LONG).show();
            if(java.util.Objects.equals(mes, "emergency")) {
                Intent a = new Intent();
                a.setClassName("com.example.cosain.alertreceive", "com.example.cosain.alertreceive.EmergencyClass");
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(a);

            }else
            {
                Intent a = new Intent();
                a.setClassName("com.example.cosain.alertreceive", "com.example.cosain.alertreceive.MainActivity");
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(a);
            }
        }

    }
}
