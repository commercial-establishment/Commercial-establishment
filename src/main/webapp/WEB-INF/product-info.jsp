<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="role" property="principal.authorities[0]"/>

<!DOCTYPE html>
<html lang="en">

<t:head title="Информация о товаре"/>

<t:container admins="active">

    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Информация о товаре
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/admin/products"/>">Список товаров</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-desktop"></i> Информация о товаре
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <tbody>
                        <tr>
                            <td><b>Название товара:</b></td>
                            <td>${product.name}</td>
                        </tr>
                        <tr>
                            <td><b>Штрих код:</b></td>
                            <td>${product.barcode}</td>
                        </tr>
                        <tr>
                            <td><b>Категория:</b></td>
                            <td>${product.category.name}</td>
                        </tr>
                        <tr>
                            <td><b>Единица измерения:</b></td>
                            <td>${product.unit.name}</td>
                        </tr>
                        <tr>
                            <td><b>Заблокирован:</b></td>
                            <td>${product.blocked}</td>
                        </tr>
                        <c:if test="${role eq 'ROLE_PROVIDER'}">
                            <c:forEach items="${limits}" var="limit">
                                <tr>
                                    <td><b>Пределы остатков для магазина типа ${limit.type.name}:</b></td>
                                    <td>${limit.min} : ${limit.max}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <tr>
                            <td>
                                <form method="GET" action="<c:url value="/admin/products/${id}/edit"/>">
                                    <input type="submit" class="btn btn-lg btn-default" value="Редактировать">
                                </form>
                                    <%--<a href="<c:url value="/admins/${id}/edit"/>" class="btn btn-lg btn-default">Редактировать</a>--%>
                            </td>

                            <c:choose>
                                <c:when test="${product.blocked == false}">
                                    <td><a href="<c:url value="/admin/products/${id}/lock"/>"
                                           class="btn btn-lg btn-danger">Заблокировать</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="<c:url value="/admin/products/${id}/reestablish"/>"
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
