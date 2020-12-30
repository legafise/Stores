<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="header.jsp"/>
<main>
    <div class="container-fluid name">
        <div class="container-fluid name">
            <div class="row">
                <div class="col-xl-4"></div>
                <div class="card user-info-card">
                    <div class="card-header">
                        <p class="h2 bold" align="center">Кабинет</p>
                    </div>
                    <div class="card-body">
                        <span class="user-info">${user.login}</span>
                        <div class="row">
                            <div class="col-xl-6">
                                <img src="https://phoenix.market/components/com_virtuemart/assets/images/vmgeneral/coming-soon-1000x1000.png"
                                     alt="Это вы" class="img-fluid img-idents profile-img"/>
                            </div>
                            <div class="col-xl-6">
                                <p class="h4 bold">Личная информация</p>
                                <div class="user-info">
                                    Имя: ${user.name} <br>
                                    Фамилия: ${user.surname} <br>
                                    Email: ${user.email} <br>
                                    Страна: ${user.city.country.name} <br>
                                    Город: ${user.city.name} <br>
                                    Дата рождения: ${user.birthDate}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<c:import url="footer.jsp"/>



