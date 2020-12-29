<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%request.setAttribute("urlHandler", request.getContextPath() + "/controller?command=authorization");%>
<c:import url="header.jsp"/>

<main>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-4"> </div>
            <div class="col-xl-4">
                <div class="card text-center authorization-form-idents">
                    <div class="card-header authorization bold">
                        Авторизация
                    </div>
                    <div class="card-body">
                        <form action="${urlHandler}" method="post">
                            <div class="form-group">
                                <label for="InputEmail1" class="authorization">Email</label>
                                <input type="email" class="form-control" id="InputEmail1" aria-describedby="emailHelp" name="email">
                            </div>
                            <div class="form-group">
                                <label for="passwordInput" class="authorization">Пароль</label>
                                <input type="password" class="form-control" id="passwordInput" name="password">
                            </div>
                            <button type="submit" class="btn btn-secondary authorization authorization-button">Вход</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<c:import url="footer.jsp"/>
