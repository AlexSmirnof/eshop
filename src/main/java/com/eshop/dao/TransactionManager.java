package com.eshop.dao;

import java.sql.SQLException;
import java.util.concurrent.Callable;

public interface TransactionManager {

    <T, E extends Exception> T doInTransaction(UnitOfWork<T,E> unitOfWork) throws E, SQLException;

}


