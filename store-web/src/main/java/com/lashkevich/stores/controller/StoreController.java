package com.lashkevich.stores.controller;

import com.lashkevich.stores.command.Command;
import com.lashkevich.stores.command.CommandFactory;
import com.lashkevich.stores.command.CommandResult;
import com.lashkevich.stores.exception.NNSCommandFactoryException;
import com.lashkevich.stores.exception.NNSConnectionPoolException;
import com.lashkevich.stores.exception.NNSReceiverException;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.pool.NNSConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class StoreController extends HttpServlet {
    @Override
    public void init() {
        try {
            NNSConnectionPool.getInstance().initializeConnectionPool(3);
        } catch (NNSConnectionPoolException e) {

        }
    }

    @Override
    public void destroy() {
        try {
            NNSConnectionPool.getInstance().closeConnections();
        } catch (NNSConnectionPoolException e) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Command command = CommandFactory.getInstance().initCommand(req);
            CommandResult commandResult = command.execute(req);
            if (commandResult.getResponseType() == CommandResult.ResponseType.FORWARD) {
                req.getRequestDispatcher(commandResult.getPage()).forward(req, resp);
            } else {
                resp.sendRedirect(commandResult.getPage());
            }
        } catch (NNSCommandFactoryException | NNSReceiverException | NNSServiceStoreException | InterruptedException e) {
            System.err.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
