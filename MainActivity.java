package com.remprac1.ashutosh.remprac1;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

    public class MainActivity extends AppCompatActivity {
    MainActivity ma;

    private static final int DILOG_ID = 0 ;
    EditText docname,docamount,dd;
    ImageButton btn;


    int year_x,month_x,day_x,monthcopy_x;
    static  long calendrum ;

    int alarmid ;
    long intentid =0;
    long intentid1;

    AlarmManager myAlarmManger;

    int n =3;
    static int counter = 0;

String nametaker,nametaker2;
        int i =0;


    DbHelper dbHelper ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new DbHelper(this);   //Database Initialized


        docname = (EditText)findViewById(R.id.doctitle);
        docamount = (EditText)findViewById(R.id.amount);

        dd = (EditText)findViewById(R.id.dateset);
        btn = (ImageButton)findViewById(R.id.cal);

        final Calendar tarik = Calendar.getInstance();
        year_x =  tarik.get(Calendar.YEAR);
        month_x = tarik.get(Calendar.MONTH);
        day_x = tarik.get(Calendar.DAY_OF_MONTH);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DILOG_ID);
                    }
                }
        ) ;



        myAlarmManger = (AlarmManager)getSystemService(ALARM_SERVICE);

    ma = this;
    alarmid = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

    }
    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DILOG_ID)
            return new DatePickerDialog(this,dpickerListner,year_x,month_x,day_x);

        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x=year;
            month_x= month + 1;
            monthcopy_x = month;
            day_x=dayOfMonth;
            dd.setText(day_x +"/"+ month_x +"/" + year_x);
            Log.v("Log1","Date month "+month_x);

        }
    };


    public void AlarmSaver(View view) {
try {
    nametaker = docname.getText().toString();
    nametaker2 = dbHelper.getCloneAll(nametaker);
    if(nametaker.equals(nametaker2))
    {

        i++;
        docname.setText(nametaker+"("+i+")");
        Log.v("Pog0.1"," IT has duplicate value"+nametaker+nametaker2);

    }else
    {
        i=0;
        Log.v("Pog0.2"," ITs fresh "+nametaker+nametaker2);
    }
}catch (Exception e)
{
    Log.v("PogExcep","Errot"+e.getMessage());
}

        if(intentid == 0){
            intentid = intentid1 = System.currentTimeMillis();
            counter++;
            Log.v("Pog1","initial cycle "+(int)intentid+" "+(int)intentid1);
            Log.v("Pog1.1","initial cycle "+counter);
        }
        else
        {
            intentid1 = intentid;
            counter++;
            Log.v("Pog2","second cycle "+intentid+" "+(int)intentid1);
            Log.v("Pog2.1","second cycle"+counter);
        }
        if(counter !=1) {
            intentid = System.currentTimeMillis();
            Log.v("Pog2.2","second cycle"+(int)intentid+" "+(int)intentid1);
            Log.v("Pog2.3","second cycle"+counter);
        }


        boolean inserted = dbHelper.insertData(docname.getText().toString(),docamount.getText().toString(),dd.getText().toString(),(int)intentid);
        if(inserted){

            Toast.makeText(this, "Data Successfully Inserted", Toast.LENGTH_SHORT).show();

        Intent i1 = new Intent(MainActivity.this,AlaReci.class);
        i1.setAction("com.ashutosh.triggered.reciever.hallabulla"); //+(int)intentid
        i1.addCategory("android.intent.category.DEFAULT");
        i1.putExtra("id",(int)intentid);



        PendingIntent pid = PendingIntent.getBroadcast(MainActivity.this, (int) intentid, i1, 0);
        n--;
        Log.v("Pog3","PendingIntent cycle"+(int) intentid);
        Log.v("Logalways","Broadcast Triggered!! ");



            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.YEAR,year_x);
            calendar.set(Calendar.MONTH,monthcopy_x);
            calendar.set(Calendar.DATE,day_x);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar.set(Calendar.HOUR_OF_DAY, 22);
            calendar.set(Calendar.MINUTE,Integer.parseInt(docamount.getText().toString()));  //
            calendar.set(Calendar.SECOND, 0);

            calendrum = calendar.getTimeInMillis();
        myAlarmManger.set(AlarmManager.RTC_WAKEUP,
                            calendrum
                        , pid);

        }else{
            Toast.makeText(this, "Error Inserting", Toast.LENGTH_SHORT).show();
        }

    }


    public void CancelAlarm(View view) {
        Log.v("Log3","CancelAlarm Triggered! ");


    }

    public void camerabuttonpressed(View view) {
        Log.v("LogCamera","Camera Button Working");

        Intent i2 = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(i2);


    }
}
