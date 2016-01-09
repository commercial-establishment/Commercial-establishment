<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="role" property="principal.authorities[0]"/>
<!DOCTYPE html>
<html lang="en">

<t:head title="Магазины"/>

<t:container providers="active">
    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Добавление магазина
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/admin/providers"/>">Список
                            поставщиков</a>
                        </li>
                        <li>
                            <i class="fa fa-desktop"></i> <a href="<c:url value="/admin/providers/${id}"/>">Информация о
                            поставщике</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/admin/providers/${id}/shops"/>">Список
                            магазинов</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-table"></i> Добавление магазина
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <c:choose>
                        <c:when test="${shops.size() == 0}">
                            <h1>Нет непривязанных магазинов.</h1>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Название магазина</th>
                                    <th>Город</th>
                                    <th>Район</th>
                                    <th>Адрес</th>
                                    <th>Тип</th>
                                    <th>Добавить</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${shops}" var="shop">
                                <c:choose>
                                <c:when test="${role eq 'ROLE_ADMIN'}">
                                <form method="post" action="/admin/providers/${providerId}/shops/add">
                                    </c:when>
                                    <c:when test="${role eq 'ROLE_PROVIDER'}">
                                    <form method="post" action="<c:url value="/provider/shops/add"/>">
                                        </c:when>
                                        </c:choose>
                                        <tr>
                                            <td>${shop.id}</td>
                                            <td>${shop.name}</td>
                                            <td>${shop.area.city.name}</td>
                                            <td>${shop.area.name}</td>
                                            <td>${shop.address}</td>
                                            <td>${shop.type.name}</td>
                                            <td>
                                                <button type="submit" name="shopId" value="${shop.id}"
                                                        class="btn btn-lg btn-default">Добавить
                                                </button>
                                            </td>
                                        </tr>
                                    </form>
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
