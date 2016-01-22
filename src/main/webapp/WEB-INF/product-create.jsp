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
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="${roleForUrl}/products"/>">Список
                            товаров</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-edit"></i> Добавление товара
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <c:choose>
                        <c:when test="${role eq 'ROLE_ADMIN'}">
                            <c:set var="roleForUrl" value="/admin"/>
                        </c:when>
                        <c:when test="${role eq 'ROLE_PROVIDER'}">
                            <c:set var="roleForUrl" value="/provider"/>
                        </c:when>
                    </c:choose>
                    <c:if test="${not empty limitError}">
                        <b class="error">${limitError}</b>
                    </c:if>
                    <table class="table table-hover">
                        <tbody>
                            <%--@elvariable id="productProvider" type="kz.hts.ce.model.entity.ProductProvider"--%>
                        <form:form method="post" action="${roleForUrl}/products/create" modelAttribute="productProvider">
                            <tr>
                                <td><form:label path="product.name">Название товара:</form:label></td>
                                <td><form:input cssClass="form-control" path="product.name"/></td>
                                <c:set var="nameErrors"><form:errors path="product.name" cssClass="error"/></c:set>
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
                                <td><form:label path="product.barcode">Штрих код:</form:label></td>
                                <td><form:input cssClass="form-control" path="product.barcode"/></td>
                                <c:set var="barcodeErrors"><form:errors path="product.barcode" cssClass="error"/>
                                </c:set>
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
                                <td><form:label path="product.category">Категория:</form:label></td>
                                <td>
                                    <select name="product.category" id="product.category" class="form-control">
                                        <c:forEach items="${categories}" var="category">
                                            <option> ${category.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>- Выберите категорию</td>
                            </tr>
                            <tr>
                                <td><form:label path="product.unit">Единица измерения:</form:label></td>
                                <td>
                                    <select name="product.unit" class="form-control">
                                        <c:forEach items="${units}" var="unit">
                                            <option> ${unit.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>- Выберите единицу измерения</td>
                            </tr>
                            <c:if test="${role eq 'ROLE_PROVIDER'}">
                                <%--@elvariable id="type" type="kz.hts.ce.model.entity.Type"--%>
                                <div class="limitsDiv">
                                    <c:forEach items="${types}" var="type">
                                        <tr>
                                            <td>Укажите пределы остатков для типа ${type.name}:</td>
                                            <td class="form-inline">
                                                <input id="min${type.name}" type="text" name="limits"
                                                       placeholder="Минимум"
                                                       class="form-control limitInput">
                                                <input id="max${type.name}" type="text" name="limits"
                                                       placeholder="Максимум"
                                                       class="form-control limitInput">
                                            </td>
                                            <td>- Укажите верные пределы для остатков</td>
                                        </tr>
                                    </c:forEach>
                                </div>
                            </c:if>
                            <form:hidden path="blocked"/>
                            <tr>
                                <td>
                                    <input type="submit" class="btn btn-lg btn-success" onclick="addLimitTypes()"
                                           value="Сохранить">
                                        <%--<form:button type="submit" class="btn btn-lg btn-success">Сохранить</form:button>--%>
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
                        </form:form>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <t:script/>
    <script src="<c:url value="/resources/js/script.js"/>"></script>
</t:container>
</html>
