package com.lashkevich.stores.invoker;

import com.lashkevich.stores.dao.impl.BasketDaoImpl;
import com.lashkevich.stores.exception.DaoStoreException;


public class TestInvoker {
    public static void main(String[] args) throws DaoStoreException {
        BasketDaoImpl basketDao = new BasketDaoImpl();
            System.out.println(basketDao.getByUser(1));
    }
}
