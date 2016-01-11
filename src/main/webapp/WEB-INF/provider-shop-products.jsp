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
                        Список товаров
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
                                <li class="active">
                                    <i class="fa fa-table"></i> Список товаров
                                </li>
                            </c:when>
                            <c:when test="${role eq 'ROLE_PROVIDER'}">
                                <li>
                                    <i class="fa fa-table"></i> <a href="<c:url value="/provider/shops"/>">Список
                                    магазинов</a>
                                </li>
                                <li>
                                    <i class="fa fa-desktop"></i> <a href="<c:url value="/provider/shops/${shop.id}"/>">Информация
                                    о магазине</a>
                                </li>
                                <li class="active">
                                    <i class="fa fa-table"></i> Список товаров
                                </li>
                            </c:when>
                        </c:choose>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <c:choose>
                        <c:when test="${providerProducts.size() == 0}">
                            <h1 align="center">Список товаров пуст!</h1>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Штрих-код</th>
                                    <th>Название</th>
                                    <th>Категория</th>
                                    <th>Ед. измерения</th>
                                    <th>Остаток</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <%--@elvariable id="product" type="kz.hts.ce.entity.Product"--%>
                                <c:forEach items="${products}" var="product">
                                    <tr>
                                        <td>${product.key.id}</td>
                                        <td>${product.key.barcode}</td>
                                        <td>${product.key.name}</td>
                                        <td>${product.key.category.name}</td>
                                        <td>${product.key.unit.name}</td>
                                        <td>${product.value}</td>
                                    </tr>
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
