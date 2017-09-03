package com.remprac1.ashutosh.remprac1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Ashutosh on 9/2/2017.
 */

public class SecondActivity extends AppCompatActivity {


EditText deleterText;
DbHelper dbHelper;
AlarmManager myAlarmManger;
    public String dt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        myAlarmManger = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        dbHelper = new DbHelper(this);


        deleterText = (EditText)findViewById(R.id.deleterSarkar);


    }

    public void Delbut(View view) {

        Log.v("LogDel","Delete Button Working");
        dt = deleterText.getText().toString();
        Log.v("Logdt","String collected for data button "+dt);
        Intent i1 = new Intent(SecondActivity.this,AlaReci.class);
        i1.setAction("com.ashutosh.triggered.reciever.hallabulla");  //(int)intentid1 +dbHelper.getAlarmIDtest("ASH")
        i1.addCategory("android.intent.category.DEFAULT");
        i1.putExtra("id",dbHelper.getAlarmIDtest(dt));

        PendingIntent pid = PendingIntent.getBroadcast(SecondActivity.this, dbHelper.getAlarmIDtest(dt), i1, 0);   //(int)intentid1
        myAlarmManger.cancel(pid);
        Log.v("PogFinal","Cancelled Status "+dbHelper.getAlarmIDtest(dt));
        dbHelper.deleteEntry(dt);

    }
}
