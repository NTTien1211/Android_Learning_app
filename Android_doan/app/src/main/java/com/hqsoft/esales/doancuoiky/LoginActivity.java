package com.hqsoft.esales.doancuoiky;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class LoginActivity extends AppCompatActivity  {
    private EditText email , pass ;
    TextView hien;
    private Button btnlog ;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googleBtn;
    Button register_part;
    String DB_name = "csdl.db";
    private  String path ="/databases/";
    SQLiteDatabase database= null;
    database_Login database_esales;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        hien = findViewById(R.id.mess_hien1);
        btnlog = findViewById(R.id.btnLogin);
        register_part = findViewById(R.id.register_part);
        xulyCoppyData();
        database_esales =new database_Login(this);
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textemail  = email.getText().toString();
                String textPass = pass.getText().toString();
                boolean isexit = database_esales.checkuserpass(textemail, textPass);
                if (textemail.isEmpty()|| textPass.isEmpty()){
                    hien.setText("Empty");
                    hien.setTextColor(Color.RED);

                }
//                else {
//
////                    Account ac = new Account(textemail , textPass);
////                    loginPresenter.login(ac);
////                    Cursor cursor1 =database.rawQuery("SELECT * FROM Users where Email = '"+ textemail +"'and Pass = '" + textPass +"'", null);
//                    database = openOrCreateDatabase(DB_name, MODE_PRIVATE, null);
//                    Cursor cursor1 =database.rawQuery("SELECT * FROM Users where Email = '"+ textemail +"' and Pass ='"+textPass +"'", null);
//                    int a = cursor1.getCount();
////                        String gmaildata = cursor1.getString(2);
//////                        Toast.makeText(RegisterActivity.this, ""+userdata, Toast.LENGTH_SHORT).show();
//                        if (a>0){
//                            Toast.makeText(LoginActivity.this, "Login thành công", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(LoginActivity.this , MenumainActivity.class);
//                            startActivity(intent);
//                        }
//                        cursor1.close();
//                }
                if (isexit){
                        Toast.makeText(LoginActivity.this, "Login thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this , MenumainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("gmail",textemail);
                            intent.putExtras(bundle);
                            startActivity(intent);
                }
                else {
                    hien.setText("Fail");
                    hien.setTextColor(Color.RED);
                }
            }
        });
        googleBtn = findViewById(R.id.login_gg);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToSecondActivity();
        }


        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        register_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(LoginActivity.this,MenumainActivity.class);
        startActivity(intent);
    }

}