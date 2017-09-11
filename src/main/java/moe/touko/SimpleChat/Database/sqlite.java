package moe.touko.SimpleChat.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class sqlite {

    private String DBPath = "";
    sqlite(String DBPath) {
        this.DBPath = String.format("jdbc:sqlite:%s", DBPath);
    }

    public abstract void insert (String table, Map<String, Object> data);
    public abstract void insert (String table, Map<String, Object> data, Boolean isCommit);

    public abstract List select (String table);

    public abstract void update(String table, Map<String, Object> data, Map<String, Object> where);
    public abstract void update(String table, Map<String, Object> data, Map<String, Object> where, Boolean isCommit);

    public abstract void delete(String table, Map<String, Object> where);
    public abstract void delete(String table, Map<String, Object> where, Boolean isCommit);


    ArrayList runSQLWithReturnData(String sql) throws SQLException {
        // Init vars.
        ArrayList<HashMap> result = new ArrayList<>();
        // Connect to db.
        Connection connection = DriverManager.getConnection(this.DBPath);
        Statement statement = connection.createStatement();
        // Get MetaData
        ResultSet rs = statement.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();
        // Analysis MetaData.
        int columnCount = metaData.getColumnCount();   //获得列数
        while (rs.next()) {
            HashMap<String, Object> rowData = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(metaData.getColumnName(i), rs.getObject(i));
            }
            result.add(rowData);
        }
        // Close conn.
        connection.close();
        return result;
    }

    void runSQLWithoutReturnData(String sql) throws SQLException {
        // Connect
        Connection connection = DriverManager.getConnection(this.DBPath);
        Statement statement = connection.createStatement();
        // Run SQL
        statement.executeUpdate(sql);
        connection.close();
    }

    ArrayList<String> getAllColumn(String table) throws SQLException {
        // Generate SQL Query.
        String sql = String.format("SELECT * FROM %s", table);
        // Start connection.
        Connection connection = DriverManager.getConnection(this.DBPath);
        Statement statement = connection.createStatement();
        // Get data.
        ResultSet rs = statement.executeQuery(sql);
        // Analysis column names.
        ResultSetMetaData metaData = rs.getMetaData();
        int columnNumber = metaData.getColumnCount();
        ArrayList<String> resultList = new ArrayList<>();
        // Get column names.
        for(int i = 1; i <= columnNumber; i++){
            resultList.add(metaData.getColumnName(i));
        }
        // Close connection.
        connection.close();
        return resultList;
    }
}
