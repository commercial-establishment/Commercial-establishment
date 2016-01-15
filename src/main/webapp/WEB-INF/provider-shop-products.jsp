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
                        <c:when test="${productLimitResidueMap.size() == 0}">
                            <h1 align="center">Список товаров пуст!</h1>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Штрих-код</th>
                                    <th>Название</th>
                                    <th>Категория</th>
                                    <th>Ед. измерения</th>
                                    <th>Мин. предел</th>
                                    <th>Макс. предел</th>
                                    <th>Остаток</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${productLimitResidueMap}" var="productLimitMap">
                                    <c:choose>
                                        <c:when test="${productLimitMap.value < productLimitMap.key.min}">
                                            <c:set var="color" value="red"/>
                                        </c:when>
                                        <c:when test="${productLimitMap.key.min <= productLimitMap.value && productLimitMap.value < productLimitMap.key.max}">
                                            <c:set var="color" value="orange"/>
                                        </c:when>
                                        <c:when test="${productLimitMap.value >= productLimitMap.key.max}">
                                            <c:set var="color" value="green"/>
                                        </c:when>
                                    </c:choose>
                                    <tr class="background-color-${color}">
                                        <td>${productLimitMap.key.productProvider.id}</td>
                                        <td>${productLimitMap.key.productProvider.product.barcode}</td>
                                        <td>${productLimitMap.key.productProvider.product.name}</td>
                                        <td>${productLimitMap.key.productProvider.product.category.name}</td>
                                        <td>${productLimitMap.key.productProvider.product.unit.name}</td>
                                        <td>${productLimitMap.key.min}</td>
                                        <td>${productLimitMap.key.max}</td>
                                        <td>${productLimitMap.value}</td>
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
