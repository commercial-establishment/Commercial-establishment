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

<t:container providers="active">

    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Редактирование данных о поставщике
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/admin/providers"/>">Список поставщиков</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-desktop"></i> <a href="<c:url value="/admin/providers/${provider.id}"/>">Информация
                            о
                            поставщике</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-edit"></i> Редактирование данных
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <form:form method="post" action="/admin/providers/${id}/edit" modelAttribute="provider"
                               commandName="provider">
                        <table class="table table-hover">
                            <tbody>
                            <tr>
                                <td><form:label path="username">Имя пользователя:</form:label></td>
                                <td><form:input cssClass="form-control" path="username"/></td>
                                <form:hidden path="password"/>
                                <c:set var="usernameErrors"><form:errors path="username" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty loginIsOccupied}">
                                        <td class="error"> ${loginIsOccupied} </td>
                                    </c:when>
                                    <c:when test="${not empty usernameErrors}">
                                        <td> ${usernameErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Имя пользователя должно состоять из ...</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="companyName">Название фирмы:</form:label></td>
                                <td><form:input cssClass="form-control" path="companyName"/></td>
                                <c:set var="companyNameErrors"><form:errors path="companyName" cssClass="error"/>
                                </c:set>
                                <c:choose>
                                    <c:when test="${not empty companyNameErrors}">
                                        <td> ${companyNameErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Имя пользователя должно состоять из ...</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="email">Email:</form:label></td>
                                <td><form:input cssClass="form-control" path="email"/></td>
                                <c:set var="emailErrors"><form:errors path="email" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty emailErrors}">
                                        <td> ${emailErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Имя пользователя должно состоять из ...</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="city">Город:</form:label></td>
                                <td>
                                    <select name="city" id="city" class="form-control">
                                        <c:forEach items="${cities}" var="city">
                                            <option <c:if test="${provider.city.name == city.name}">selected</c:if>>
                                                    ${city.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>Имя пользователя должно состоять из ...</td>
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
                                        <td>Имя пользователя должно состоять из ...</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="contactPerson">Контактное лицо:</form:label></td>
                                <td><form:input cssClass="form-control" path="contactPerson"/></td>
                                <c:set var="contactPersonErrors"><form:errors path="contactPerson" cssClass="error"/>
                                </c:set>
                                <c:choose>
                                    <c:when test="${not empty contactPersonErrors}">
                                        <td> ${contactPersonErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Имя пользователя должно состоять из ...</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="iin">ИИН:</form:label></td>
                                <td><form:input cssClass="form-control" path="iin"/></td>
                                <c:set var="iinErrors"><form:errors path="iin" cssClass="error"/>
                                </c:set>
                                <c:choose>
                                    <c:when test="${not empty iinErrors}">
                                        <td> ${iinErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>- Только цифры. Максимальное количество - 20.</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="bin">БИН:</form:label></td>
                                <td><form:input cssClass="form-control" path="bin"/></td>
                                <c:set var="binErrors"><form:errors path="bin" cssClass="error"/>
                                </c:set>
                                <c:choose>
                                    <c:when test="${not empty binErrors}">
                                        <td> ${binErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>- Только цифры. Максимальное количество - 20.</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="startWorkDate">Дата начала работы:</form:label></td>
                                <td>
                                    <form:input path="startWorkDate" cssClass="form-control customDatepicker"/>
                                </td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td><form:label path="role">Роль:</form:label></td>
                                <td><form:label path="role">${provider.role.name}</form:label></td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td><form:label path="blocked">Заблокирован:</form:label></td>
                                <td>${provider.blocked}</td>
                                <form:hidden path="blocked"/>
                                <c:set var="blockedErrors"><form:errors path="blocked" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty blockedErrors}">
                                        <td> ${blockedErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Имя пользователя должно состоять из ...</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td>
                                    <form:button type="submit" class="btn btn-lg btn-success">Сохранить</form:button>
                                </td>
                                <td/>
                                <td><a href="<c:url value="/admin/providers/${id}"/>" class="btn btn-lg btn-danger">Отмена</a>
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
