package com.hqsoft.esales.doancuoiky;

import static android.graphics.Color.*;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ProfileActivity extends AppCompatActivity {
    ImageButton background_chang ;
    LinearLayout bacground_profile ;
    ImageButton im1 , im2 , im3, im4 ,im5, im6;
    String DB_name = "csdl.db";
    private  String path ="/databases/";
    SQLiteDatabase database= null;
    Button btnUserprofile_save;
    EditText userdatashow;
    TextView gmaildatashow;
    EditText phonedatashow ;
    EditText adddatashow ;
    String userdata, gmaildata ,phonedata ,address;
    String value1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#BACDCDC8")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        anhxa();
        xulyCoppyData();
        background_chang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Showdilalog_background(Gravity.CENTER);
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            value1 = bundle.getString("gmail", "");
        }
        showdata();
        btnUserprofile_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues row = new ContentValues();
                row.put("UserName" ,userdatashow.getText().toString());
                row.put("Email" , gmaildatashow.getText().toString());
                row.put("Phone" ,phonedatashow.getText().toString());
                row.put("Adress" ,adddatashow.getText().toString());
                database = openOrCreateDatabase(DB_name, MODE_PRIVATE, null);
                String where = "Email=?";
                String[] whereArgs = new String[] {String.valueOf(value1)};
                database.update("Users" ,row ,where,whereArgs);
                Toast.makeText(ProfileActivity.this, "Thành công", Toast.LENGTH_SHORT).show();

            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void showdata() {

        database = openOrCreateDatabase(DB_name, MODE_PRIVATE, null);
        Cursor cursor1 =database.rawQuery("SELECT * FROM Users where Email = '"+ value1 +"'", null);
        while (cursor1.moveToNext()){
            userdata =cursor1.getString(1);
            gmaildata = cursor1.getString(2);
            phonedata = cursor1.getString(3);
            address = cursor1.getString(4);
            Toast.makeText(this, ""+userdata + "  "+gmaildata+ "  "+phonedata, Toast.LENGTH_SHORT).show();
            userdatashow.setText(userdata);
            gmaildatashow.setText(gmaildata);
            phonedatashow.setText(phonedata);
            adddatashow.setText(address);
//
        }


        cursor1.close();

    }
    private void Showdilalog_background(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_backgroud_profile);
        Window  window = dialog.getWindow();

        if (window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
        Context context ;
        WindowManager.LayoutParams windowAtt = window.getAttributes();
        windowAtt.gravity = gravity;
        window.setAttributes(windowAtt);
        GridLayout grid = (GridLayout) dialog.findViewById(R.id.gridlayout_backgroud_profile);
        im1 = dialog.findViewById(R.id.gridlayout_1);
        im2 = dialog.findViewById(R.id.gridlayout_2);
        im3 = dialog.findViewById(R.id.gridlayout_3);
        im4 = dialog.findViewById(R.id.gridlayout_4);
        im5 = dialog.findViewById(R.id.gridlayout_5);
        im6 = dialog.findViewById(R.id.gridlayout_6);

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bacground_profile.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_bg));
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bacground_profile.setBackgroundDrawable(getResources().getDrawable(R.drawable.suong));
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bacground_profile.setBackgroundDrawable(getResources().getDrawable(R.drawable.mau));
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bacground_profile.setBackgroundDrawable(getResources().getDrawable(R.drawable.nen));
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bacground_profile.setBackgroundDrawable(getResources().getDrawable(R.mipmap.rrrr));
            }
        });
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bacground_profile.setBackgroundDrawable(getResources().getDrawable(R.mipmap.d8d2a9c9f1b564ba3c801fbd55bc80de   ));
            }
        });




        dialog.show();


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
        background_chang = findViewById(R.id.background_profile_change);
        bacground_profile = findViewById(R.id.background_profile);
        userdatashow = findViewById(R.id.userprofile_name);
        gmaildatashow = findViewById(R.id.userprofile_email);
        phonedatashow = findViewById(R.id.userprofile_phone);
        adddatashow = findViewById(R.id.userprofile_address);
        btnUserprofile_save= findViewById(R.id.btnUserprofile_save);


    }
}