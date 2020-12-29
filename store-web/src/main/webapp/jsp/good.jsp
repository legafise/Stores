<%@ page import="com.lashkevich.stores.entity.Good" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%request.setAttribute("addGoodCommand", request.getContextPath() + "/controller?command=add_good");%>
<%Good currentGood = (Good) request.getAttribute("good");%>
<%request.getSession().setAttribute("currentGoodId", currentGood.getId());%>

<c:import url="header.jsp"/>
<main>
    <div class="container-fluid name">
        <p class="h2 bold">${good.name}</p>
    </div>
    <div class="container-fluid good-idents">
        <div class="row">
            <div class="col-xl-4">
                <img src="${good.imgURL}" class="img-fluid img-idents" alt="iphone">
                <p class="price">Цена: ${good.price} ${currency.symbol}</p>
                <div class="buttons-idents">
                    <button class="buy-button">
                        Купить
                    </button>
                    <a href="${addGoodCommand}">
                        <button class="basket-button">
                            В корзину
                        </button>
                    </a>
                </div>
            </div>
            <div class="col-xl-6 description">
                <span class="bold">Тип: </span>${good.summary} <br>
                <span class="bold">Описание: </span>${good.description}
            </div>
        </div>
    </div>
</main>
<c:import url="footer.jsp"/>