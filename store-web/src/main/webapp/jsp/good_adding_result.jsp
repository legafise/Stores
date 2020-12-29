<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%request.setAttribute("basketURL", request.getContextPath() + "/controller?command=basket_page");%>
<%request.setAttribute("registrationURL", request.getContextPath() + "/controller?command=registration_page");%>
<%request.setAttribute("catalogURL", request.getContextPath() + "/controller?command=catalog");%>
<c:import url="header.jsp"/>

<main>
    <c:choose>
        <c:when test="${addingResult == true}">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-4"></div>
                    <div class="col-xl-4">
                        <div class="card text-center registration-form-idents">
                            <div class="card-header authorization bold">
                                Товар добавлен в корзину!
                            </div>
                            <div class="card-body authorization">
                                Вы можете посмотреть корзину <br>
                                или вернуться к каталогу <br>
                                <div class="row">
                                    <div class="col-xl-4"></div>
                                    <div class="col-xl-4">
                                        <a href="${basketURL}"><button class="my-button">Корзина</button></a>
                                        <a href="${catalogURL}"><button class="my-button">Каталог</button></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:when>
        <c:when test="${addingResult == false}">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-4"></div>
                    <div class="col-xl-4">
                        <div class="card text-center registration-form-idents">
                            <div class="card-header authorization bold">
                                Ошибка!
                            </div>
                            <div class="card-body authorization">
                                Что-то пошло не так!<br>
                                Вы можете вернуться к каталогу и попробовать еще раз
                                <div class="row">
                                    <div class="col-xl-4"></div>
                                    <div class="col-xl-4">
                                        <a href="${catalogURL}">
                                            <button class="my-button">Каталог</button>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:when>
    </c:choose>
</main>

<c:import url="footer.jsp"/>

