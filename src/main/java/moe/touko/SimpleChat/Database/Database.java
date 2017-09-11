package moe.touko.SimpleChat.Database;

import java.sql.SQLException;
import java.util.*;

import moe.touko.SimpleChat.Common.Common;

public class Database extends sqlite {

    Database(String DBPath){
        super(DBPath);
    }

    // Update function
    @Override
    public final void update(String table, Map<String, Object> data, Map<String, Object> where){
        update(table, data, where, true);
    }
    @Override
    public final void update(String table, Map<String, Object> data, Map<String, Object> where, Boolean isCommit){
        String valueString = Common.BeautifyDictionary(data);
        String whereString = Common.BeautifyDictionary(where);
        String sql = String.format("UPDATE %s SET %s WHERE %s", table, valueString, whereString);
        System.out.println(String.format("SQL: %s", sql));
        if (isCommit){
            try{
                this.runSQLWithoutReturnData(sql);
            }catch (SQLException e){
                System.out.println(String.format("UPDATE func Error: %s", e.getMessage()));
            }
        }
    }


    // Delete function
    @Override
    public final void delete(String table, Map<String, Object> where){
        delete(table, where, true);
    }
    @Override
    public final void delete(String table, Map<String, Object> where, Boolean isCommit){
        // BeautifyDictionary
        String whereText = Common.BeautifyDictionary(where);
        // Gen SQL
        String sql = String.format("DELETE FROM %s WHERE %s", table, whereText);
        System.out.println(String.format("SQL: %s", sql));
        if (isCommit){
            try{
                this.runSQLWithoutReturnData(sql);
            }catch (SQLException e){
                System.out.println(String.format("DELETE func Error: %s", e.getMessage()));
            }
        }
    }

    // Select function block
    @Override
    public final List select(String table){
        List result = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s", table);
        try{
            result = this.runSQLWithReturnData(sql);
        }
        catch (SQLException e){
            System.out.println(String.format("SELECT func Error: %s", e.getMessage()));
        }
        return result;
    }

    // Insert function block.
    @Override
    public final void insert(String table, Map<String, Object> data){
        insert(table, data, true);
    }
    @Override
    public final void insert(String table, Map<String, Object> data, Boolean isCommit) {
        // Get all column names.
        ArrayList<String> columns;
        try{
            columns = this.getAllColumn(table);
        }
        catch (SQLException e){
            System.out.println(String.format("Func getAllColumns Error! Info is %s", e.getMessage()));
            return;
        }
        // BeautifyDictionary
        String valueText = Common.BeautifyDictionary(data, columns);
        // Gen SQL
        String sql = String.format("INSERT INTO %s VALUES (%s)", table, valueText);
        System.out.println(String.format("SQL: %s", sql));
        if (isCommit){
            try{
                this.runSQLWithoutReturnData(sql);
            }
            catch (SQLException e){
                System.out.println(String.format("Insert Error! Info is %s", e.getMessage()));
            }
        }
    }
}
