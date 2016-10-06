package com.eshop.dao.impl;

import com.eshop.dao.BaseDataSource;
import com.eshop.dao.TransactionManager;
import com.eshop.dao.UnitOfWork;
import com.eshop.dao.exception.DaoSystemException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class TransactionManagerImpl extends BaseDataSource implements TransactionManager {

    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/eshop?user=root&password=root";
    private static ThreadLocal<Connection> connHolder = new ThreadLocal<>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public <T,E extends Exception> T doInTransaction(UnitOfWork<T,E> unitOfWork) throws E, SQLException {
        Connection conn = DriverManager.getConnection(JDBC_URL);
        conn.setAutoCommit(false);
        connHolder.set(conn); // кладем в кэш потока (привязываю к потоку в кот меня вызвали)
        try {
            T result = unitOfWork.doInTx();
            conn.commit();
            return result;
        } catch (Exception e) {
            conn.rollback();
            throw (E) e;
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException ignore) {}
            connHolder.remove();  //очищаем кэш потока
        }
    }

    @Override
    public Connection getConnection(){
        return connHolder.get();
    }
}
