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
                <div class="col-xl-3">
                    <div class="card text-center card-size">
                        <div class="card-body">
                            <h5 class="card-title short-description"><a href="iphone.html">Iphone</a></h5>
                            <p class="card-text">
                                <a href="iphone.html">
                                    <img src="../img/iphone.jpg" class="img-fluid img-idents img-size" alt="iphone">
                                </a>
                            </p>
                            <a href="iphone.html">
                                <button class="more-button">
                                    Подробнее
                                </button>
                            </a>
                        </div>
                        <div class="card-footer text-muted short-description">
                            <a href="iphone.html">Цена: 1600р</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

<c:import url="footer.jsp"/>

