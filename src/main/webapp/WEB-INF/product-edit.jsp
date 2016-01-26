<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="role" property="principal.authorities[0]"/>
<!DOCTYPE html>
<html lang="en">

<t:head title="Редактирование товара">
    <%--<link href="<c:url value="/resources/css/datepicker.css"/>" rel="stylesheet" type="text/css">--%>
    <%--<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">--%>
</t:head>

<t:container admins="active">

    <div id="page-wrapper">

        <div class="container-fluid">
            <c:choose>
                <c:when test="${role eq 'ROLE_ADMIN'}">
                    <c:set var="roleForUrl" value="/admin"/>
                </c:when>
                <c:when test="${role eq 'ROLE_PROVIDER'}">
                    <c:set var="roleForUrl" value="/provider"/>
                </c:when>
            </c:choose>
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
                            <i class="fa fa-table"></i> <a href="<c:url value="${roleForUrl}/products"/>">Список
                            товаров</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-desktop"></i> <a
                                href="<c:url value="${roleForUrl}/products/${product.id}"/>">Информация
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
                    <c:if test="${not empty limitError}">
                        <b class="error">${limitError}</b>
                    </c:if>
                    <table class="table table-hover">
                        <tbody>
                        <c:choose>
                        <c:when test="${role eq 'ROLE_PROVIDER'}">
                            <%--@elvariable id="productProvider" type="kz.hts.ce.model.entity.ProductProvider"--%>
                        <form:form method="post" action="${roleForUrl}/products/${productProvider.id}/edit"
                                   modelAttribute="productProvider">
                        <form:hidden path="product.id"/>
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
                                        <option
                                                <c:if test="${productProvider.product.category.name == category.name}">selected</c:if>>
                                                ${category.name}
                                        </option>
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
                                        <option
                                                <c:if test="${productProvider.product.unit.name == unit.name}">selected</c:if>>
                                                ${unit.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>- Выберите единицу измерения</td>
                        </tr>
                        <div class="limitsDiv">
                            <c:forEach items="${productLimitList}" var="productLimit">
                                <tr>
                                    <td>Пределы для магазина типа ${productLimit.type.name}:</td>
                                    <td class="form-inline">
                                        <input id="min${productLimit.type.name}" type="text" name="limits"
                                               placeholder="Минимум" class="form-control limitInput"
                                               value="${productLimit.min}">
                                        <input id="max${productLimit.type.name}" type="text" name="limits"
                                               placeholder="Максимум" class="form-control limitInput"
                                               value="${productLimit.max}">
                                    </td>
                                    <td>- Укажите верные пределы для остатков</td>
                                </tr>
                            </c:forEach>
                        </div>
                        <form:hidden path="blocked"/>
                        <tr>
                            <td>
                                <input type="submit" class="btn btn-lg btn-success" onclick="addLimitTypes()"
                                       value="Сохранить">
                            </td>
                            <td></td>
                            <td><a href="<c:url value="${roleForUrl}/products/${id}"/>"
                                   class="btn btn-lg btn-danger">Отмена</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    </form:form>
                    </c:when>
                    <c:when test="${role eq 'ROLE_ADMIN'}">
                        <form:form method="post" action="${roleForUrl}/products/${id}/edit" modelAttribute="product"
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
                                <form:hidden path="blocked"/>
                                <tr>
                                    <td>
                                        <form:button type="submit"
                                                     class="btn btn-lg btn-success">Сохранить</form:button>
                                    </td>
                                    <td></td>
                                    <td><a href="<c:url value="${roleForUrl}/products/${id}"/>"
                                           class="btn btn-lg btn-danger">Отмена</a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </form:form>
                    </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
    <%--<script src="<c:url value="/resources/js/script.js"/>"></script>--%>
    <%--<script src="<c:url value="/resources/js/jquery.js"/>"></script>--%>
    <%--<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>--%>
    <%--<script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>--%>
    <t:script/>
    <script src="<c:url value="/resources/js/script.js"/>"></script>
</t:container>
</html>
