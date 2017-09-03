package com.remprac1.ashutosh.remprac1;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Date;

/**
 * Created by Ashutosh on 9/1/2017.
 */

public class AlaReci extends BroadcastReceiver {

    DbHelper dbrec;
    int recieverid;
    Bundle recExtra;
    @Override
    public void onReceive(Context context, Intent intent) {

        dbrec = new DbHelper(context);

        recExtra = intent.getExtras();
        recieverid = (int) recExtra.get("id");



        Log.v("Pogreciever","On Recieving notification "+recieverid);

        NotificationManagerCompat myManager = NotificationManagerCompat.from(context);
        NotificationCompat.Builder myNoti = new NotificationCompat.Builder(context);


        myNoti.setContentTitle(dbrec.NaamKaAlaram(recieverid));
        myNoti.setContentText(dbrec.AmountKaAlaram(recieverid));
        myNoti.setSmallIcon(R.mipmap.ic_launcher_round);

        myNoti.setDefaults(Notification.DEFAULT_ALL);
        myNoti.setAutoCancel(true);



        myManager.notify((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), myNoti.build());
    }
}
