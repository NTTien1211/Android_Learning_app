package com.hqsoft.esales.doancuoiky;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SettingActivity extends AppCompatActivity {
    EditText newpass, oldpass , renewpass;
    Button save ;
    String DB_name = "csdl.db";
    private  String path ="/databases/";
    SQLiteDatabase database= null;
    ImageButton Premium_part;
    String value1;
    String oldpassdata;
    String olpassssss ,newpasssss,renewpasssss;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        xulyCoppyData();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Setting");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#BACDCDC8")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Premium_part = findViewById(R.id.Premium_part);
        Premium_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this , PremiumActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            value1 = bundle.getString("gmail", "");
        }

        oldpass = findViewById(R.id.old_pass_change);
        newpass = findViewById(R.id.new_pass_change);
        renewpass =findViewById(R.id.new_re_pass_change);
        save = findViewById(R.id.btn_pass_change);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                olpassssss = oldpass.getText().toString();
                newpasssss = newpass.getText().toString();
                renewpasssss = renewpass.getText().toString();
                showdata();
                    if(!olpassssss.equals(oldpassdata)){
                        Toast.makeText(SettingActivity.this, "Please check old data", Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(newpasssss) || newpasssss.length()<6){
                        Toast.makeText(SettingActivity.this, "The pass length must be more than 6 characters", Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(newpasssss) || newpasssss.length()<6){
                        Toast.makeText(SettingActivity.this, "The pass length must be more than 6 characters", Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(renewpasssss) || renewpasssss.length()<6){
                        Toast.makeText(SettingActivity.this, "The pass length must be more than 6 characters", Toast.LENGTH_SHORT).show();
                    }
                    else if (!newpasssss.equals(renewpasssss)){
                        Toast.makeText(SettingActivity.this, "Please check new data", Toast.LENGTH_SHORT).show();
                    }
                    else if (newpasssss.equals(olpassssss)){
                        Toast.makeText(SettingActivity.this, "Please, different old pass", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        ContentValues row = new ContentValues();
                        String where = "Email=?";
                        String[] whereArgs = new String[] {String.valueOf(value1)};
                        row.put("Pass" ,newpasssss);
                        database.update("Users" ,row ,where,whereArgs);
                        Toast.makeText(SettingActivity.this, "Save success", Toast.LENGTH_SHORT).show();
                    }




            }
        });
        

    }
    public void  showdata(){
        database = openOrCreateDatabase(DB_name, MODE_PRIVATE, null);
        Cursor cursor =database.rawQuery("SELECT * FROM Users where Email = '"+ value1 +"'", null);
        while (cursor.moveToNext()){
            oldpassdata = cursor.getString(5);

        }
        cursor.close();
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
}