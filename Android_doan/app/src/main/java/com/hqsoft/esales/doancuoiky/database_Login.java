package com.hqsoft.esales.doancuoiky;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class database_Login extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "csdl.db";
    private static final int  DATABASE_VERSION = 1;
    private final Context context;
    SQLiteDatabase db ;

    private static final String DATABASE_path= "/data/data/com.hqsoft.esales.doancuoiky/databases/";
    private  final  String User_table = "Users";
    public database_Login(@Nullable Context context) {
        super(context, DATABASE_NAME , null , DATABASE_VERSION);
        this.context=context;

        createDB();
    }

    private void createDB() {
        boolean dbexit = checkDBexit();
        if(!dbexit){
            this.getReadableDatabase();
            coppydata();
        }
    }

    private void coppydata() {
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            String outFilename = DATABASE_path + DATABASE_NAME ;
            OutputStream outputStream = new FileOutputStream(outFilename);
            byte[] b = new byte[1024];
            int leght ;
            while ((leght = inputStream.read(b))>0){
                outputStream.write(b , 0 , leght);

            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private boolean checkDBexit() {
        SQLiteDatabase sqLiteDatabase = null ;

        try{
            String path = DATABASE_path + DATABASE_NAME;
            sqLiteDatabase  = SQLiteDatabase.openDatabase(path, null , SQLiteDatabase.OPEN_READONLY);
        }catch (Exception ex){

        }

        if (sqLiteDatabase!= null){
            sqLiteDatabase.close();
            return true;
        }
        return false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private SQLiteDatabase opendatabase(){
        String path = DATABASE_path+ DATABASE_NAME;
        db= SQLiteDatabase.openDatabase(path , null , SQLiteDatabase.OPEN_READWRITE);
        return db;
    }
    public void close(){
        if (db!=null){
            db.close();
        }
    }
    public Cursor DisplayData()
    {
        //Select query
        return db.rawQuery("SELECT * FROM Users", null);
        //return db.query(TABLE_NAME, new String[]{NAME, ROLL,COURSE}, null, null, null, null, null);
    }

    public boolean checkuserpass(String email,String password){
        String[] column = {"Email"};
        db = opendatabase();
        String selec = "Email = ? and Pass = ?";
        String[] selecArgs = {email , password};

        Cursor cursor = db.query(User_table, column , selec , selecArgs , null, null,null);
        int count = cursor.getCount();

        if (count>0){
            return true;

        }else {
            return false;
        }


    }
}
