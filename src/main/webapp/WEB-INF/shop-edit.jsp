<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<t:head title="Редактирование администратора">
    <link href="<c:url value="/resources/css/datepicker.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
</t:head>

<t:container shops="active">

    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Редактирование данных о магазине
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/admin/shops"/>">Список магазинов</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-desktop"></i> <a href="<c:url value="/admin/shops/${shop.id}"/>">Информация
                            о магазинах
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
                    <form:form method="post" action="/admin/shops/${id}/edit" modelAttribute="shop" commandName="shop">
                        <table class="table table-hover">
                            <tbody>
                            <tr>
                                <td><form:label path="name">Наименование магазина:</form:label></td>
                                <td><form:input cssClass="form-control" path="name"/></td>
                                <c:set var="nameErrors"><form:errors path="name" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty nameErrors}">
                                        <td> ${nameErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>- Введите корректное название магазина</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="address">Адрес:</form:label></td>
                                <td><form:input cssClass="form-control" path="address"/></td>
                                <c:set var="addressErrors"><form:errors path="address" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty addressErrors}">
                                        <td> ${addressErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>- Введите корректный адрес</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><label>Город:</label></td>
                                <td>
                                    <select name="city" id="city" onchange="selectCity()" class="form-control">
                                        <c:forEach items="${cities}" var="city">
                                            <option <c:if test="${shop.area.city.name == city.name}">selected</c:if>>
                                                    ${city.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>- Выберите город</td>
                            </tr>
                            <tr>
                                <td><label>Район:</label></td>
                                <td>
                                    <select disabled="disabled" name="area" id="area" class="form-control">
                                        <option selected value="${shop.area.name}">${shop.area.name}</option>
                                    </select>
                                </td>
                                <td>- Выберите район</td>
                            </tr>
                            <tr>
                                <td><form:label path="type">Тип:</form:label></td>
                                <td>
                                    <select name="type" id="type" class="form-control">
                                        <c:forEach items="${types}" var="type">
                                            <option <c:if test="${shop.type.name == type.name}">selected</c:if>>
                                                    ${type.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <input type="hidden" name="blocked" value="${shop.blocked}">
                                <form:hidden path="blocked"/>
                            </tr>
                            <tr>
                                <td>
                                    <form:button type="submit" class="btn btn-lg btn-success">Сохранить</form:button>
                                </td>
                                <td></td>
                                <td><a href="<c:url value="/admin/shops/${id}"/>"
                                       class="btn btn-lg btn-danger">Отмена</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

    <script src="<c:url value="/resources/js/script.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
</t:container>
</html>
