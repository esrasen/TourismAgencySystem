package dao;

import core.DbConnection;

import java.sql.Connection;

public abstract class BaseDao {
    protected Connection conn;

    public BaseDao() {
        this.conn = DbConnection.getInstance();
    }
}
