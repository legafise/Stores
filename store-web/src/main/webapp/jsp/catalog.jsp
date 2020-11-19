<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="header.jsp"/>
    <main>
        <div class="container-fluid name">
            <p class="h2 bold">Каталог</p>
        </div>
        <div class="container-fluid">
            <div class="row">
                <c:forEach var="good" items="${goodList}">
                    <div class="col-xl-3">
                        <div class="card text-center card-size">
                            <div class="card-body">
                                <h5 class="card-title short-description"><a href="controller?command=good&goodId=${good.id}&currencyId=${currency.id}">${good.name}</a></h5>
                                <p class="card-text">
                                    <a href="controller?command=good&goodId=${good.id}&currencyId=${currency.id}">
                                        <img src="${good.imgURL}" class="img-fluid img-idents img-size" alt="${good.name}">
                                    </a>
                                </p>
                                <a href="controller?command=good&goodId=${good.id}&currencyId=${currency.id}">
                                    <button class="more-button">
                                        Подробнее
                                    </button>
                                </a>
                            </div>
                            <div class="card-footer text-muted short-description">
                                <a href="controller?command=good&goodId=${good.id}&currencyId=${currency.id}">Цена: ${good.price} ${currency.symbol}</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </main>

<c:import url="footer.jsp"/>

