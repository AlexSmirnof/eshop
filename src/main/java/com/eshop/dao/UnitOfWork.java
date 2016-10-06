package com.eshop.dao;

import com.eshop.dao.exception.DaoSystemException;

public interface UnitOfWork<T, E extends Exception> {

    public T doInTx() throws E, DaoSystemException;
}
