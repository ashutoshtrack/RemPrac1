package com.remprac1.ashutosh.remprac1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ashutosh on 9/2/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "T43.db";
    public static final String TABLE_NAME = "Task_tablerman";

    public static final String COL_1 = "ID";

    public static final String COL_2 = "NAME";

    public static final String COL_3 = "AMOUNT";

    public static final String COL_4 = "DATE";


    public static final String COL_5 = "ALARMID";









    public DbHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT NOT NULL,AMOUNT INTEGER NOT NULL,DATE TEXT," + "ALARMID INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
    }




    //customm process chaalu

    public boolean insertData(String name,String amount,String dater,int alarmid) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, name);
        contentValues.put(COL_3, amount);
        contentValues.put(COL_4, dater);
        contentValues.put(COL_5, alarmid);


        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public int getIdkarantest(){
        int nam = 0;
        SQLiteDatabase db = this.getWritableDatabase();


        String query = "select * from " +TABLE_NAME;
        Cursor resi = db.rawQuery(query,null);
        if(resi != null && resi.moveToLast()){

            nam = Integer.parseInt(resi.getString(0));
            resi.close();
        }
        return   nam;
    }

    public String NaamKaAlaram(int alarmidea){
        String nam = new String();
        SQLiteDatabase db = this.getWritableDatabase();


        String query = "select * from " +TABLE_NAME+ " where "+COL_5+ " = "+alarmidea ;
        Cursor resi = db.rawQuery(query,null);
        if(resi != null && resi.moveToLast()){

            nam = resi.getString(1);
            resi.close();
        }
        return   nam;


    }

    public String AmountKaAlaram(int alarmidea){
        String nam = new String();
        SQLiteDatabase db = this.getWritableDatabase();


        String query = "select * from " +TABLE_NAME+ " where "+COL_5+ " = "+alarmidea ;
        Cursor resi = db.rawQuery(query,null);
        if(resi != null && resi.moveToLast()){

            nam = resi.getString(2);
            resi.close();
        }
        return   nam;


    }

    public Cursor getALLData() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " +TABLE_NAME;
        Cursor res = db.rawQuery(query,null);
        Log.i("getAlldata","Executed Safely");
        return res;
    }
    public void deleteEntry(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Deletes a row given its rowId, but I want to be able to pass
        // in the name of the KEY_NAME and have it delete that row.
        //db.delete(TABLE_NAME, COL_1 + "=" + id, null);
        db.delete(TABLE_NAME,COL_2 + "= '" +name+"'",null);
    }


    public String getNaamkarantest(int id){
        String nam = new String();
        SQLiteDatabase db = this.getWritableDatabase();


        String query = "select * from " +TABLE_NAME+ " where "+COL_1+ " = "+id ;
        Cursor resi = db.rawQuery(query,null);
        if(resi != null && resi.moveToLast()){

            nam = resi.getString(1);
            resi.close();
        }
        return   nam;
    }



    public String getAmountkarantest(){
        String nam = new String();
        SQLiteDatabase db = this.getWritableDatabase();


        String query = "select * from " +TABLE_NAME;
        Cursor resi = db.rawQuery(query,null);
        if(resi != null && resi.moveToLast()){
            nam = resi.getString(2);
            resi.close();
        }
        return   nam;
    }

    public int getAlarmIDtest(String doctitle){
        int nam =0;

        SQLiteDatabase db = this.getWritableDatabase();


        String query = "select * from " +TABLE_NAME+ " where " +COL_2+ " = '"+doctitle+"'" ;
        Cursor resi = db.rawQuery(query,null);
        if(resi != null && resi.moveToLast()){
            nam = resi.getInt(4);
            resi.close();
        }

        return nam;
    }

   public String getCloneAll(String namesetter){
       String nam = new String();
       SQLiteDatabase db = this.getWritableDatabase();


       String query = "select * from " +TABLE_NAME+ " where "+COL_2+ " = '"+namesetter+"'" ;
       Cursor resi = db.rawQuery(query,null);
       if(resi != null && resi.moveToLast()){

           nam = resi.getString(1);
           resi.close();
       }
       return   nam;
    }

}
