<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="role" property="principal.authorities[0]"/>

<!DOCTYPE html>
<html lang="en">

<t:head title="Информация о магазине">
    <link href="<c:url value="/resources/css/datepicker.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
</t:head>

<t:container shops="active">

    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Информация о магазине
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/provider/shops"/>">Список магазинов</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-desktop"></i> Информация о магазине
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <tbody>
                        <tr>
                            <td><b>Название магазина:</b></td>
                            <td>${shop.name}</td>
                        </tr>
                        <tr>
                            <td><b>Город:</b></td>
                            <td>${shop.area.city.name}</td>
                        </tr>
                        <tr>
                            <td><b>Район:</b></td>
                            <td>${shop.area.name}</td>
                        </tr>
                        <tr>
                            <td><b>Адрес:</b></td>
                            <td>${shop.address}</td>
                        </tr>
                        <tr>
                            <td><b>Тип:</b></td>
                            <td>${shop.type.name}</td>
                        </tr>
                        <tr>
                            <td><b>Заблокирован:</b></td>
                            <td>${shop.blocked}</td>
                        </tr>
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${role eq 'ROLE_ADMIN'}">
                                        <form method="GET" action="<c:url value="/admin/shops/${id}/edit"/>">
                                            <input type="submit" class="btn btn-lg btn-default" value="Редактировать">
                                        </form>
                                    </c:when>
                                    <c:when test="${role eq 'ROLE_PROVIDER'}">
                                        <form method="GET"
                                              action="<c:url value="/provider/shops/${shop.id}/products"/>">
                                            <input type="submit" class="btn btn-lg btn-default" value="Товары">
                                        </form>
                                    </c:when>
                                </c:choose>
                            </td>

                            <c:choose>
                                <c:when test="${shop.blocked == false}">
                                    <td><a href="<c:url value="/admin/shops/${id}/lock"/>"
                                           class="btn btn-lg btn-danger">Заблокировать</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="<c:url value="/admin/shops/${id}/reestablish"/>"
                                           class="btn btn-lg btn-success">Восстановить</a></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <t:script/>
</t:container>
</html>
