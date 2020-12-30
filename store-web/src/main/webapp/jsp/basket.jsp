<%@ page import="com.lashkevich.stores.entity.Basket" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.lashkevich.stores.entity.Good" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%request.setAttribute("removeGoodCommand", request.getContextPath() + "/controller?command=remove_good&good_id=");%>
<%request.setAttribute("clearBasketCommand", request.getContextPath() + "/controller?command=clear_basket");%>
<%request.setAttribute("totalPrice", 0);%>

<c:import url="header.jsp"/>
    <main class="content">
        <div class="container-fluid name">
            <p class="h2 bold">Корзина</p>
        </div>
        <c:choose>
            <c:when test="${basketIsEmpty == true}">
                <div class="container-fluid">
                    <h5 class="basket-good-header">Корзина пуста!</h5>
                </div>
            </c:when>
            <c:when test="${basketIsEmpty == false}">
                <div class="container-fluid">
                    <c:forEach var="entry" items="${basketGoods}">
                        <div class="card card-size">
                            <h5 class="card-header basket-good-header">${entry.key.name}</h5>
                            <div class="card-body">
                                <div class="row basket-description-text">
                                    <div class="col-xl-3">
                                        <img src="${entry.key.imgURL}" class="img-fluid img-idents img-size" alt="">
                                    </div>
                                    <div class="col-xl-9">
                                        <span class="bold">Тип:</span> ${entry.key.summary} <br>
                                        <span class="bold">Описание:</span> ${entry.key.description} <br>
                                        <span class="bold">Кол-во:</span> ${entry.value}
                                    </div>
                                </div>
                                <span class="price-in-basket">Цена: ${entry.key.price * entry.value} ${currency.symbol}</span> <br>
                                <a href="" class="btn buy-button-in-basket">Купить</a>
                                <a href="${removeGoodCommand}${entry.key.id}" class="btn delete-button-in-basket">Удалить</a>
                            </div>
                        </div>
                        <!--${totalPrice = totalPrice + entry.key.price * entry.value}-->
                    </c:forEach>
                </div>
                <div class="container-fluid basket-description-text">
                    <span class="price-in-basket">Сумма заказа: ${totalPrice} ${currency.symbol}</span>
                    <div>
                        <a href="" class="btn buy-button-in-basket">Заказать</a>
                        <a href="${clearBasketCommand}" class="btn delete-button-in-basket">Очистить корзину</a>
                    </div>
                </div>
            </c:when>
        </c:choose>
    </main>
<c:import url="footer.jsp"/>