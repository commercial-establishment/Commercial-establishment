<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="role" property="principal.authorities[0]"/>
<!DOCTYPE html>
<html lang="en">

<t:head title="Редактирование товара">
    <link href="<c:url value="/resources/css/datepicker.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
</t:head>

<t:container products="active">
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
                        <li class="active">
                            <i class="fa fa-table"></i> Список товаров
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <div>
                        <c:choose>
                            <c:when test="${role eq 'ROLE_ADMIN'}">
                                <a href="<c:url value="/admin/products/create"/>"
                                   class="btn btn-lg btn-default">Добавить</a>
                            </c:when>
                            <c:when test="${role eq 'ROLE_PROVIDER'}">
                                <form method="get" action="<c:url value="/provider/products/create"/>">
                                    <button type="submit" class="btn btn-lg btn-default">Создать</button>
                                </form>
                            </c:when>
                        </c:choose>
                        <br>
                    </div>
                    <c:choose>
                        <c:when test="${productProviderList.size() == 0 || products.size() == 0}">
                            <h1>Нет созданных товаров.</h1>
                            <br>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Название товара</th>
                                    <th>Штрих код</th>
                                    <th>Категория</th>
                                    <th>Ед. измерения</th>
                                    <th>Заблокирован</th>
                                    <th>Заблокировать</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${role eq 'ROLE_ADMIN'}">
                                        <c:forEach items="${products}" var="product">
                                            <tr onclick="document.location = '/admin/products/' + '${product.id}';">
                                                <td>${product.id}</td>
                                                <td>${product.name}</td>
                                                <td>${product.barcode}</td>
                                                <td>${product.category.name}</td>
                                                <td>${product.unit.name}</td>
                                                <td>${product.blocked}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${product.blocked == false}">
                                                            <form method="POST"
                                                                  action="<c:url value="/provider/products/${product.id}/lock"/>">
                                                                <button type="submit" class="btn btn-lg btn-danger">
                                                                    Заблокировать
                                                                </button>
                                                            </form>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form method="POST"
                                                                  action="<c:url value="/provider/products/${product.id}/reestablish"/>">
                                                                <button type="submit" class="btn btn-lg btn-danger">
                                                                    Разблокировать
                                                                </button>
                                                            </form>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:when test="${role eq 'ROLE_PROVIDER'}">
                                        <c:forEach items="${productProviderList}" var="productProvider">
                                            <tr onclick="document.location = '/provider/products/' + '${productProvider.id}';">
                                                <td>${productProvider.id}</td>
                                                <td>${productProvider.product.name}</td>
                                                <td>${productProvider.product.barcode}</td>
                                                <td>${productProvider.product.category.name}</td>
                                                <td>${productProvider.product.unit.name}</td>
                                                <td>${productProvider.blocked}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${productProvider.blocked == false}">
                                                            <form method="POST"
                                                                  action="<c:url value="/provider/products/${productProvider.product.id}/lock"/>">
                                                                <button type="submit" class="btn btn-lg btn-danger">
                                                                    Заблокировать
                                                                </button>
                                                            </form>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form method="POST"
                                                                  action="<c:url value="/provider/products/${productProvider.product.id}/reestablish"/>">
                                                                <button type="submit" class="btn btn-lg btn-success">
                                                                    Разблокировать
                                                                </button>
                                                            </form>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
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
