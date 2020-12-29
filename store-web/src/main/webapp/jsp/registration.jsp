<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%request.setAttribute("actionURL", request.getContextPath() + "/controller?command=registration");%>
<c:import url="header.jsp"/>

<main>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-4"></div>
            <div class="col-xl-4">
                <div class="card text-center registration-form-idents">
                    <div class="card-header authorization bold">
                        Регистрация
                    </div>
                    <div class="card-body authorization">
                        <form action="${actionURL}" method="post" class="reg-inputs">
                            <div class="form-group">
                                <label for="login">Логин(никнейм)</label>
                                <input type="text" class="form-control" id="login" name="login">
                            </div>
                            <div class="row row-in-registration">
                                <div class="col-xl-6">
                                    <div class="form-group">
                                        <label for="name">Имя</label>
                                        <input type="text" class="form-control" id="name" name="name">
                                    </div>
                                </div>
                                <div class="col-xl-6">
                                    <div class="form-group">
                                        <label for="surname">Фамилия</label>
                                        <input type="text" class="form-control" id="surname" name="surname">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" class="form-control" id="email" name="email"
                                       aria-describedby="emailHelp">
                            </div>
                            <div class="row row-in-registration">
                                <div class="col-xl-6">
                                    <div class="form-group">
                                        <label for="birth-date">Дата рождения</label>
                                        <input type="date" class="form-control" id="birth-date" name="birthDate">
                                    </div>
                                </div>
                                <div class="col-xl-6">
                                    <div class="form-group">
                                        <label for="city">Город</label>
                                        <select class="form-control" id="city" name="city">
                                            <c:forEach var="city" items="${cities}">
                                                <option value="${city.id}">${city.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password">Пароль</label>
                                <input type="password" class="form-control" id="password" name="password">
                            </div>
                            <button type="submit" class="btn btn-secondary authorization authorization-button">
                                Зарегистрироваться
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<script src="<c:url value="/js/registrationValidator.js"/>"></script>

<c:import url="footer.jsp"/>

