package com.lashkevich.stores.controller;

import com.lashkevich.stores.command.Command;
import com.lashkevich.stores.command.CommandResult;
import com.lashkevich.stores.command.CommandType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class StoreController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = CommandType.CATALOG.getCommand();
        CommandResult commandResult = command.execute(req);
        if (commandResult.getResponseType() == CommandResult.ResponseType.FORWARD) {
            req.getRequestDispatcher(commandResult.getPage()).forward(req, resp);
        } else {
            resp.sendRedirect(commandResult.getPage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
