<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%request.setAttribute("cssPath", request.getContextPath() + "/static/css/style.css");%>
<%request.setAttribute("logOut", request.getContextPath() + "/controller?command=log_out");%>
<%request.setAttribute("authorizationURL", request.getContextPath() + "/controller?command=authorization_page");%>
<%request.setAttribute("registrationURL", request.getContextPath() + "/controller?command=registration_page");%>
<%request.setAttribute("profileURL", request.getContextPath() + "/controller?command=profile");%>
<%request.setAttribute("basketURL", request.getContextPath() + "/controller?command=basket_page");%>
<%request.setAttribute("catalogURL", request.getContextPath() + "/controller?command=catalog");%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,   shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"crossorigin="anonymous">
    <link rel="stylesheet" href="${cssPath}">
    <title>Каталог NNStore</title>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <a class="navbar-brand" href="#" title="На главную">NNStore</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <div class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Поиск" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0 search-button-idents" type="submit">Поиск</button>
        </div>
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${catalogURL}">Каталог<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${basketURL}">Корзина<span class="sr-only">(current)</span></a>
            </li>
            <c:choose>
                <c:when test="${role == 'guest'}">
                    <li class="nav-item">
                        <a class="nav-link" href="${registrationURL}">Регистрация<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${authorizationURL}">Вход<span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:when test="${role != 'guest'}">
                    <li class="nav-item">
                        <a class="nav-link" href="${profileURL}">Личный кабинет<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${logOut}">Выход<span class="sr-only">(current)</span></a>
                    </li>
                 </c:when>
            </c:choose>
        </ul>
    </div>
</nav>
