<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<t:head title="Редактирование товара">
    <link href="<c:url value="/resources/css/datepicker.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
</t:head>

<t:container admins="active">

    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Редактирование данных о товаре
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/admin/products"/>">Список товаров</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-desktop"></i> <a href="<c:url value="/admin/products/${product.id}"/>">Информация
                            о товарах
                        </a>
                        </li>
                        <li class="active">
                            <i class="fa fa-edit"></i> Редактирование данных
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <form:form method="post" action="/admin/products/${id}/edit" modelAttribute="product"
                               commandName="product">
                        <table class="table table-hover">
                            <tbody>
                            <tr>
                                <td><form:label path="name">Название товара:</form:label></td>
                                <td><form:input cssClass="form-control" path="name"/></td>
                                <c:set var="nameErrors"><form:errors path="name" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty nameErrors}">
                                        <td> ${nameErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>- Введите корректное название товара</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="barcode">Штрих код:</form:label></td>
                                <td><form:input cssClass="form-control" path="barcode"/></td>
                                <c:set var="barcodeErrors"><form:errors path="barcode" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty barcodeErrors}">
                                        <td> ${barcodeErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>- Введите штрих код товара</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="category">Категория:</form:label></td>
                                <td>
                                    <select name="category" id="category" class="form-control">
                                        <c:forEach items="${categories}" var="category">
                                            <option
                                                    <c:if test="${product.category.name == category.name}">selected</c:if>>
                                                    ${category.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>- Выберите категорию</td>
                            </tr>
                            <tr>
                                <td><form:label path="unit">Единица измерения:</form:label></td>
                                <td>
                                    <select name="unit" id="unit" class="form-control">
                                        <c:forEach items="${units}" var="unit">
                                            <option
                                                    <c:if test="${product.unit.name == unit.name}">selected</c:if>>
                                                    ${unit.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>- Выберите единицу измерения</td>
                            </tr>
                            <tr>
                                <td><form:label path="blocked">Заблокирован:</form:label></td>
                                <td>${product.blocked}</td>
                                <form:hidden path="blocked"/>
                                <td/>
                            </tr>
                            <tr>
                                <td>
                                    <form:button type="submit" class="btn btn-lg btn-success">Сохранить</form:button>
                                </td>
                                <td></td>
                                <td><a href="<c:url value="/admin/products/${id}"/>" class="btn btn-lg btn-danger">Отмена</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

    <script src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
</t:container>
</html>
