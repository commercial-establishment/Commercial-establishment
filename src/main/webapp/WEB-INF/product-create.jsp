<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="role" property="principal.authorities[0]"/>

<!DOCTYPE html>
<html lang="en">

<t:head title="Добавление товара"/>

<t:container admins="active">

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
                                    <i class="fa fa-table"></i> <a href="<c:url value="/admin/products"/>">Список
                                    товаров</a>
                                </li>
                                <li class="active">
                                    <i class="fa fa-edit"></i> Добавление товара
                                </li>
                            </c:when>
                            <c:when test="${role eq 'ROLE_PROVIDER'}">
                                <li>
                                    <i class="fa fa-table"></i> <a href="<c:url value="/provider/products"/>">Список
                                    товаров</a>
                                </li>
                                <li class="active">
                                    <i class="fa fa-edit"></i> Добавление товара
                                </li>
                            </c:when>
                        </c:choose>

                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <c:choose>
                        <c:when test="${role eq 'ROLE_ADMIN'}">
                            <c:set var="url" scope="request" value="/admin/products/create"/>
                            <%--<form:form method="post" action="/admin/products/create-save" modelAttribute="product">--%>
                        </c:when>
                        <c:when test="${role eq 'ROLE_PROVIDER'}">
                            <c:set var="url" scope="request" value="/provider/products/create"/>
                            <%--<form:form method="post" action="/provider/products/create-save" modelAttribute="product">--%>
                        </c:when>
                    </c:choose>
                    <form:form method="post" action="${url}" modelAttribute="product">
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
                                            <option> ${category.name}</option>
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
                                            <option> ${unit.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>- Выберите единицу измерения</td>
                            </tr>
                            <tr>
                                <td><form:label path="barcode">Укажите пределы остатков для типа А:</form:label></td>
                                <td class="form-inline">
                                        <form:input cssClass="form-control" path="barcode"/>
                                        <form:input cssClass="form-control" path=""/>
                                </td>
                                <c:set var="barcodeErrors"><form:errors path="barcode" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty barcodeErrors}">
                                        <td> ${barcodeErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>- Заполните все поля</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="barcode">Укажите пределы остатков для типа B:</form:label></td>
                                <td class="form-inline"><form:input cssClass="form-control" path="barcode"/>
                                    <form:input cssClass="form-control" path=""/>
                                </td>
                                <c:set var="barcodeErrors"><form:errors path="barcode" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty barcodeErrors}">
                                        <td> ${barcodeErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>- Заполните все поля</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="barcode">Укажите пределы остатков для типа C:</form:label></td>
                                <td class="form-inline"><form:input cssClass="form-control form_control_2" path="barcode"/>
                                    <form:input cssClass="form-control form_control_2" path=""/>
                                </td>
                                <c:set var="barcodeErrors"><form:errors path="barcode" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty barcodeErrors}">
                                        <td> ${barcodeErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>- Заполните все поля</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="barcode">Укажите пределы остатков для типа D:</form:label></td>
                                <td class="form-inline"><form:input cssClass="form-control" path="barcode"/>
                                    <form:input cssClass="form-control" path=""/>
                                </td>
                                <c:set var="barcodeErrors"><form:errors path="barcode" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty barcodeErrors}">
                                        <td> ${barcodeErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>- Заполните все поля</td>
                                    </c:otherwise>
                                </c:choose>
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
                                <td/>
                                <c:choose>
                                    <c:when test="${role eq 'ROLE_ADMIN'}">
                                        <td><a href="<c:url value="/admin/products/${id}"/>"
                                               class="btn btn-lg btn-danger">Отмена</a>
                                        </td>
                                    </c:when>
                                    <c:when test="${role eq 'ROLE_PROVIDER'}">
                                        <td><a href="<c:url value="/provider/products"/>" class="btn btn-lg btn-danger">Отмена</a>
                                        </td>
                                    </c:when>
                                </c:choose>
                            </tr>
                            </tbody>
                        </table>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <t:script/>
</t:container>
</html>
