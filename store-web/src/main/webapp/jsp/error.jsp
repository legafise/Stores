<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%request.setAttribute("catalogURL", request.getContextPath() + "/controller?command=catalog");%>
<c:import url="header.jsp"/>
<main>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-4"></div>
            <div class="col-xl-4">
                <div class="card text-center registration-form-idents">
                    <div class="card-header authorization bold">
                        Ошибка!
                    </div>
                    <div class="card-body authorization">
                        Вернуться к каталогу
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
</main>
<c:import url="footer.jsp"/>

