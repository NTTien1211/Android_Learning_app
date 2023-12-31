package com.hqsoft.esales.doancuoiky.ConnectSQL;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

@SuppressLint("NewApi")
public class JDBCModel {
    String ip, database, password, port, username;
    public Connection getConnectionOf(){
        ip = "192.168.4.112";
        database="Doan_Android" ;
        username = "sa";
        password = "12112001";
        port="1433";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection objConn = null;
        String sConnURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver") ;
            sConnURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+" ;user=" + username +  " ;password="+ password + ";";
            objConn = DriverManager.getConnection(sConnURL);
        }catch (Exception ex){
            Log.d("Tab", ex.getMessage());
        }
        return objConn;
    }
}
