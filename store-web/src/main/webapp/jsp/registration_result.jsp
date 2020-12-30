<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%request.setAttribute("authorizationURL", request.getContextPath() + "/controller?command=authorization_page");%>
<%request.setAttribute("registrationURL", request.getContextPath() + "/controller?command=registration_page");%>
<%request.setAttribute("catalogURL", request.getContextPath() + "/controller?command=catalog");%>
<c:import url="header.jsp"/>

<main>
    <c:choose>
        <c:when test="${registrationResult == true}">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-4"></div>
                    <div class="col-xl-4">
                        <div class="card text-center registration-form-idents">
                            <div class="card-header authorization bold">
                                Регистрация прошла успешно!
                            </div>
                            <div class="card-body authorization">
                                Вы можете авторизироваться <br>
                                или вернуться к каталогу <br>
                                <div class="row">
                                    <div class="col-xl-3"></div>
                                    <div class="col-xl-6">
                                        <a href="${authorizationURL}"><button class="my-button">Войти</button></a>
                                        <a href="${catalogURL}"><button class="my-button">Каталог</button></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:when>
        <c:when test="${registrationResult == false}">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-4"></div>
                    <div class="col-xl-4">
                        <div class="card text-center registration-form-idents">
                            <div class="card-header authorization bold">
                                Ошибка!
                            </div>
                            <div class="card-body authorization">
                                Попробуйте зарегистрироваться еще раз<br>
                                или вернитесь к каталогу
                                <div class="row">
                                    <div class="col-xl-3"></div>
                                    <div class="col-xl-6">
                                        <a href="${registrationURL}"><button class="my-button">Регистрация</button></a>
                                        <a href="${catalogURL}"><button class="my-button">Каталог</button></a>
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

