<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%request.setAttribute("authorizationURL", request.getContextPath() + "/controller?command=authorization_page");%>
<%request.setAttribute("registrationURL", request.getContextPath() + "/controller?command=registration_page");%>

<c:import url="header.jsp"/>
<main>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-4"></div>
            <div class="col-xl-4">
                <div class="card text-center registration-form-idents">
                    <div class="card-header authorization bold">
                        Для этого вам нужно войти в аккаунт!
                    </div>
                    <div class="card-body authorization">Зарегистрируйтесь или авторизируйтесь<br>
                        <div class="row">
                            <div class="col-xl-3"></div>
                            <div class="col-xl-6">
                                <a href="${registrationURL}"><button class="my-button">Регистрация</button></a>
                                <a href="${authorizationURL}"><button class="my-button">Авторизация</button></a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xl-3"></div>
                            <div class="col-xl-6 back-to-catalog-info">Или вернитесь к каталогу</div>
                        </div>
                            <div class="row">
                                <div class="col-xl-3"></div>
                                <div class="col-xl-6">
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
</main>
<c:import url="footer.jsp"/>

