package com.lashkevich.stores.reciever;

import com.lashkevich.stores.command.CommandResult;
import com.lashkevich.stores.entity.*;
import com.lashkevich.stores.exception.NNSReceiverException;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.service.*;
import com.lashkevich.stores.service.impl.*;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiteReceiver {
    private static final SiteReceiver INSTANCE = new SiteReceiver();
    private GoodService goodService;
    private CurrencyService currencyService;
    private CityService cityService;
    private UserService userService;
    private BasketService basketService;

    private SiteReceiver() {
        goodService = new NNSGoodService();
        currencyService = new NNSCurrencyService();
        cityService = new NNSCityService();
        userService = new NNSUserService();
        basketService = new NNSBasketService();
    }

    public static SiteReceiver getInstance() {
        return INSTANCE;
    }

    public CommandResult catalogForward(HttpServletRequest request) throws NNSReceiverException {
        try {
            Currency currency = currencyService.findCurrencyById((String) request.getSession().getAttribute("currencyId"));
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
            Currency currency = currencyService.findCurrencyById(String.valueOf(request.getSession().getAttribute("currencyId")));
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

    public CommandResult registration(HttpServletRequest request) throws NNSReceiverException {
        try {
            if (!registrationValidator(request)) {
                request.setAttribute("registrationResult", false);
                return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/registration_result.jsp");
            }

            User user = registrationMapper(request);
            request.setAttribute("registrationResult", userService.addUser(user));
            return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/registration_result.jsp");
        } catch (NNSServiceStoreException e) {
            throw new NNSReceiverException(e);
        }
    }

    public CommandResult authorizationForward(HttpServletRequest request) {
        return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/authorization.jsp");
    }

    public CommandResult authorization(HttpServletRequest request) {
        if (!authorizationValidator(request)) {
            return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/authorization_result.jsp");
        }

        try {
            User user = userService.findUserByEmail(request.getParameter("email"));

            if (BCrypt.checkpw(request.getParameter("password"), user.getPassword())) {
                request.getSession().setAttribute("role", user.getRole().getName());
                request.getSession().setAttribute("userId", user.getId());
                request.getSession().setAttribute("currencyId", String.valueOf(user.getCity().getCountry().getCurrency().getId()));
            }
        } finally {
            return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/authorization_result.jsp");
        }
    }

    public CommandResult logOut(HttpServletRequest request) throws NNSServiceStoreException {
        request.getSession().setAttribute("role", "guest");
        request.getSession().setAttribute("currencyId", String.valueOf(currencyService.findCurrencyByName("USD").getId()));
        return new CommandResult(CommandResult.ResponseType.REDIRECT, request.getContextPath() + "/controller?command=catalog");
    }

    public CommandResult profileForward(HttpServletRequest request) throws NNSServiceStoreException {
        request.setAttribute("user", userService.findUserById((String.valueOf(request.getSession().getAttribute("userId")))));
        return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/profile.jsp");
    }

    public CommandResult basketForward(HttpServletRequest request) throws NNSServiceStoreException {
        Basket currentUserBasket = basketService.findBasketByUserId(String.valueOf(request.getSession().getAttribute("userId")),
                String.valueOf(request.getSession().getAttribute("currencyId")));

        if (currentUserBasket.getGoods().values().size() == 0) {
            request.setAttribute("basketIsEmpty", true);
            return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/basket.jsp");
        }

        request.setAttribute("basketIsEmpty", false);
        request.setAttribute("basketGoods", currentUserBasket.getGoods().entrySet());
        request.setAttribute("currency", currencyService.findCurrencyById(String.valueOf(request.getSession().getAttribute("currencyId"))));
        return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/basket.jsp");
    }

    public CommandResult addGoodInBasket(HttpServletRequest request) throws NNSServiceStoreException {
        Map<Good, Integer> goods = new HashMap<>();
        Good addingGood = goodService.findGoodById(String.valueOf(request.getSession().getAttribute("currentGoodId")), currencyService.findStandardCurrencyId());
        goods.put(addingGood, 1);
        Basket addingBasket = new Basket(goods);
        Basket currentUserBasket = basketService.findBasketByUserId(String.valueOf(request.getSession().getAttribute("userId")), currencyService.findStandardCurrencyId());

        if (currentUserBasket.getGoods().containsKey(addingGood)) {
            currentUserBasket.getGoods().entrySet().forEach(currentEntry -> {
                if (currentEntry.getKey().equals(addingGood)) {
                    currentEntry.setValue(currentEntry.getValue() + 1);
                }
            });

            request.setAttribute("addingResult", basketService.updateBasket(currentUserBasket, String.valueOf(request.getSession().getAttribute("userId"))));
            return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/good_adding_result.jsp");
        }

        request.setAttribute("addingResult", basketService.addBasket(addingBasket, String.valueOf(request.getSession().getAttribute("userId"))));
        return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/good_adding_result.jsp");
    }

    public CommandResult removeGoodFromBasket(HttpServletRequest request) throws NNSServiceStoreException {
        Good removingGood = goodService.findGoodById(request.getParameter("good_id"), currencyService.findStandardCurrencyId());
        Basket currentUserBasket = basketService.findBasketByUserId(String.valueOf(request.getSession().getAttribute("userId")), currencyService.findStandardCurrencyId());

        currentUserBasket.getGoods().entrySet()
                .forEach(currentEntry -> {
                    if (currentEntry.getKey().equals(removingGood)) {
                        currentEntry.setValue(currentEntry.getValue() - 1);
                    }
                });

        if (currentUserBasket.getGoods().get(removingGood) == 0) {
            currentUserBasket.getGoods().remove(removingGood);
        }

        basketService.updateBasket(currentUserBasket, String.valueOf(request.getSession().getAttribute("userId")));
        return new CommandResult(CommandResult.ResponseType.REDIRECT, request.getContextPath() + "/controller?command=basket_page");
    }

    public CommandResult clearBasket(HttpServletRequest request) throws NNSServiceStoreException {
        basketService.removeBasket(String.valueOf(request.getSession().getAttribute("userId")));
        return new CommandResult(CommandResult.ResponseType.REDIRECT, request.getContextPath() + "/controller?command=basket_page");
    }

    public CommandResult guestInfoForward(HttpServletRequest request) {
        return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/guest_info.jsp");
    }

    public CommandResult errorForward(HttpServletRequest request) {
        return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/error.jsp");
    }

    private User registrationMapper(HttpServletRequest request) throws NNSServiceStoreException {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setEmail(request.getParameter("email"));
        user.setCity(cityService.findCityById(request.getParameter("city")));
        user.setPassword(request.getParameter("password"));
        user.setRole(new Role(1, "User"));
        user.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
        return user;
    }

    private boolean registrationValidator(HttpServletRequest request) {
        return !request.getParameter("login").isEmpty() && !request.getParameter("name").isEmpty() && !request.getParameter("surname").isEmpty() &&
                !request.getParameter("email").isEmpty() && !request.getParameter("city").isEmpty() && !request.getParameter("password").isEmpty() && !request.getParameter("birthDate").isEmpty();
    }

    private boolean authorizationValidator(HttpServletRequest request) {
        return !request.getParameter("email").isEmpty() && !request.getParameter("password").isEmpty();
    }
}
