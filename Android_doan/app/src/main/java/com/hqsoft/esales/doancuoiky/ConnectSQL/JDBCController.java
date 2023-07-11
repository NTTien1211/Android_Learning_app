package com.hqsoft.esales.doancuoiky.ConnectSQL;

import java.sql.Connection;


public class JDBCController {
    JDBCModel JdbcModel = new JDBCModel();
    public Connection ConnnectionData() {
        return JdbcModel.getConnectionOf();
    }

}
