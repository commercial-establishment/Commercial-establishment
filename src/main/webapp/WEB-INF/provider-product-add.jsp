<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="role" property="principal.authorities[0]"/>
<!DOCTYPE html>
<html lang="en">

<t:head title="Товары"/>

<t:container providers="active">
    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Добавление товара
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <c:choose>
                            <c:when test="${role eq 'ROLE_ADMIN'}">
                                <li>
                                    <i class="fa fa-table"></i> <a href="<c:url value="/admin/providers"/>">Список
                                    поставщиков</a>
                                </li>
                                <li>
                                    <i class="fa fa-desktop"></i> <a href="<c:url value="/admin/providers/${id}"/>">Информация
                                    о
                                    поставщике</a>
                                </li>
                                <li>
                                    <i class="fa fa-table"></i> <a
                                        href="<c:url value="/admin/providers/${id}/products"/>">Список
                                    товаров</a>
                                </li>
                            </c:when>
                            <c:when test="${role eq 'ROLE_PROVIDER'}">
                                <li>
                                    <i class="fa fa-table"></i> <a href="<c:url value="/provider/products"/>">Список
                                    товаров</a>
                                </li>
                            </c:when>
                        </c:choose>
                        <li class="active">
                            <i class="fa fa-table"></i> Добавление товара
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Название</th>
                            <th>Штрих код</th>
                            <th>Категория</th>
                            <th>Ед измерения</th>
                                <%--<th>Цена</th>--%>
                            <th>Добавить</th>
                        </tr>
                        </thead>
                        <tbody>
                            <%--@elvariable id="product" type="kz.hts.ce.model.entity.Product"--%>
                        <c:forEach items="${products}" var="product">
                            <c:choose>
                                <c:when test="${role eq 'ROLE_ADMIN'}">
                                    <form method="post" action="/admin/providers/${providerId}/products/add">
                                </c:when>
                                <c:when test="${role eq 'ROLE_PROVIDER'}">
                                    <form method="post" action="/provider/products/add">
                                </c:when>
                            </c:choose>

                                <tr>
                                    <td>${product.id}</td>
                                    <td>${product.name}</td>
                                    <td>${product.barcode}</td>
                                    <td>${product.category.name}</td>
                                    <td>${product.unit.name}</td>
                                    <td>
                                        <button type="submit" name="productId" value="${product.id}"
                                                class="btn btn-lg btn-default">Добавить
                                        </button>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <t:script/>
</t:container>
</html>
