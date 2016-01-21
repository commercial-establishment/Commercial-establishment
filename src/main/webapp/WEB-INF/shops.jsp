<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="role" property="principal.authorities[0]"/>
<!DOCTYPE html>
<html lang="en">

<t:head title="Список магазинов">
    <link href="<c:url value="/resources/css/datepicker.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
</t:head>

<t:container shops="active">
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Список магазинов
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-table"></i> Список магазинов
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <div>
                        <c:choose>
                            <c:when test="${role eq 'ROLE_ADMIN'}">
                                <a href="<c:url value="/admin/shops/create"/>"
                                   class="btn btn-lg btn-default">Добавить</a>
                            </c:when>
                        </c:choose>
                    </div>
                    <c:choose>
                        <c:when test="${shops.size() == 0}">
                            <h1>Нет привязанных магазинов.</h1>
                            <h3>Как только сотрудник магазина добавит вас в список - магазин отобразится.</h3>
                            <br>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Название магазина</th>
                                    <th>Город</th>
                                    <th>Район</th>
                                    <th>Адрес</th>
                                    <th>Тип</th>
                                    <th>Заблокирован</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${shops}" var="shop">
                                    <c:choose>
                                        <c:when test="${role eq 'ROLE_ADMIN'}">
                                            <tr onclick="document.location = '/admin/shops/' + '${shop.id}';">
                                                <td>${shop.id}</td>
                                                <td>${shop.name}</td>
                                                <td>${shop.area.city.name}</td>
                                                <td>${shop.area.name}</td>
                                                <td>${shop.address}</td>
                                                <td>${shop.type.name}</td>
                                                <td>${shop.blocked}</td>
                                            </tr>
                                        </c:when>
                                        <c:when test="${role eq 'ROLE_PROVIDER'}">
                                            <tr onclick="document.location = '/provider/shops/' + '${shop.key.id}';"
                                                class="background-color-${shop.value}">
                                                <td>${shop.key.id}</td>
                                                <td>${shop.key.name}</td>
                                                <td>${shop.key.area.city.name}</td>
                                                <td>${shop.key.area.name}</td>
                                                <td>${shop.key.address}</td>
                                                <td>${shop.key.type.name}</td>
                                                <td>${shop.key.blocked}</td>
                                            </tr>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
    <t:script/>
</t:container>
</html>
