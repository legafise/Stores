package com.lashkevich.stores.reciever;

import com.lashkevich.stores.command.CommandResult;
import com.lashkevich.stores.entity.*;
import com.lashkevich.stores.exception.NNSReceiverException;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.service.CityService;
import com.lashkevich.stores.service.CurrencyService;
import com.lashkevich.stores.service.GoodService;
import com.lashkevich.stores.service.UserService;
import com.lashkevich.stores.service.impl.NNSCityService;
import com.lashkevich.stores.service.impl.NNSCurrencyService;
import com.lashkevich.stores.service.impl.NNSGoodService;
import com.lashkevich.stores.service.impl.NNSUserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public class SiteReceiver {
    private static final SiteReceiver INSTANCE = new SiteReceiver();
    private GoodService goodService;
    private CurrencyService currencyService;
    private CityService cityService;
    private UserService userService;

    private SiteReceiver() {
        goodService = new NNSGoodService();
        currencyService = new NNSCurrencyService();
        cityService = new NNSCityService();
        userService = new NNSUserService();
    }

    public static SiteReceiver getInstance() {
        return INSTANCE;
    }

    public CommandResult catalogForward(HttpServletRequest request) throws NNSReceiverException {
        try {
            Currency currency = currencyService.findCurrencyById(request.getParameter("currencyId"));
            List<Good> goodList = goodService.findAllGoods(String.valueOf(currency.getId()));
            request.setAttribute("goodList", goodList);
            request.setAttribute("currency", currency);
            return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/catalog.jsp");
        } catch (NNSServiceStoreException e) {
            throw new NNSReceiverException(e);
        }
    }

    public CommandResult goodForward(HttpServletRequest request) throws NNSReceiverException {
        try {
            Currency currency = currencyService.findCurrencyById(request.getParameter("currencyId"));
            Good good = goodService.findGoodById(request.getParameter("goodId"), String.valueOf(currency.getId()));
            request.setAttribute("good", good);
            request.setAttribute("currency", currency);
            return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/good.jsp");
        } catch (NNSServiceStoreException e) {
            throw new NNSReceiverException(e);
        }
    }

    public CommandResult registrationForward(HttpServletRequest request) throws NNSReceiverException {
        try {
            List<City> cities = cityService.findAllCities();
            request.setAttribute("cities", cities);
            return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/registration.jsp");
        } catch (NNSServiceStoreException e) {
            throw new NNSReceiverException(e);
        }
    }

    public CommandResult authorizationForward(HttpServletRequest request) {
        return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/authorization.jsp");
    }

    public CommandResult registration(HttpServletRequest request) throws NNSReceiverException {
        try {
            User user = new User();
            user.setLogin(request.getParameter("login"));
            user.setName(request.getParameter("name"));
            user.setSurname(request.getParameter("surname"));
            user.setEmail(request.getParameter("email"));
            user.setCity(cityService.findCityById(request.getParameter("city")));
            user.setPassword(request.getParameter("password"));
            user.setRole(new Role(1, "User"));
            user.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
            request.setAttribute("registrationResult", userService.addUser(user));
            return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/registration_result.jsp");
        } catch (NNSServiceStoreException e) {
            throw new NNSReceiverException(e);
        }
    }
}
