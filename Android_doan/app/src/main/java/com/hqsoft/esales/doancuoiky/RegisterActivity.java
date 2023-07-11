package com.hqsoft.esales.doancuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.hqsoft.esales.doancuoiky.ConnectSQL.JDBCController;
import com.hqsoft.esales.doancuoiky.ConnectSQL.JDBCModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    TextView login_part;
    Button register ;
    EditText resUser , resGmail , resPass , resRepass ;

    String DB_name = "csdl.db";
    private  String path ="/databases/";
    SQLiteDatabase database= null;

    private Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public RegisterActivity(){
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    Connection connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhxa();
        login_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this , LoginActivity.class);
                startActivity(intent);

            }
        });
        xulyCoppyData();
       
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = resUser.getText().toString();
                String gmail = resGmail.getText().toString();
                String pass = resPass.getText().toString();
                String repass = resRepass.getText().toString();
                 boolean b = TextUtils.isEmpty(pass) && pass.length() > 5;
                String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

                if (TextUtils.isEmpty(gmail) || !patternMatches(gmail, regexPattern)){
                    Toast.makeText(RegisterActivity.this, "Email invalidate", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(pass) || pass.length()<6){
                    Toast.makeText(RegisterActivity.this, "The pass length must be more than 6 characters", Toast.LENGTH_SHORT).show();
                }
                else if(repass.equals(pass)==false){
                    Toast.makeText(RegisterActivity.this, "CHECK REPASS AGAIN", Toast.LENGTH_SHORT).show();
                }
                else {
                    ContentValues row = new ContentValues();
                    database = openOrCreateDatabase(DB_name, MODE_PRIVATE, null);
                    Cursor cursor1 =database.rawQuery("SELECT Email FROM Users where Email = '"+ gmail +"'", null);
                    Cursor cursor = database.query("Users" , null, null, null , null,null,null);
                    int a = cursor1.getCount();
//                    Toast.makeText(RegisterActivity.this, ""+a, Toast.LENGTH_SHORT).show();
                    while (cursor.moveToNext()){
//                        String gmaildata = cursor1.getString(2);
////                        Toast.makeText(RegisterActivity.this, ""+userdata, Toast.LENGTH_SHORT).show();
                        if (    a > 0  ){
                            Toast.makeText(RegisterActivity.this, "Already existing user or gmail", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            row.put("UserName" ,name);
                            row.put("Email" ,  gmail);
                            row.put("Phone" , "");
                            row.put("Pass" , pass);
                            row.put("Adress" , "");
                            row.put("Level","0");
                            Toast.makeText(RegisterActivity.this, "Thành Công", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    }

                    database.insert("Users" ,null, row);
                    cursor.close();
                    //sql

//                    JDBCModel jdbcModel = new JDBCModel();
//                    connect = jdbcModel.getConnectionOf();
//                    Toast.makeText(RegisterActivity.this, ""+connect, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
    private void  xulyCoppyData(){
        File dbFile = getDatabasePath(DB_name);
        if (!dbFile.exists()){
            copyData();
        }
    }
   
    private void copyData() {
        try {
            InputStream inputStream = getAssets().open(DB_name);
            String oufile = getApplicationInfo().dataDir+path+DB_name;
            File f = new File(getApplicationInfo().dataDir+path);
            if (!f.exists()){
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(oufile);
            byte[] bytes = new byte[1024];
            int len ;
            while ((len=inputStream.read(bytes))>0){
                myOutput.write(bytes, 0,len);
            }
            myOutput.flush();
            inputStream.close();
            myOutput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void anhxa() {
        login_part = findViewById(R.id.login_part);
        register = findViewById(R.id.registerbtn);
        resUser = findViewById(R.id.regis_username);
        resGmail = findViewById(R.id.regis_Email);
        resPass = findViewById(R.id.regis_password);
        resRepass = findViewById(R.id.regis_re_password);

    }
}