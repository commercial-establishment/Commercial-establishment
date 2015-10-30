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

<t:container admins="active">

    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Редактирование данных об администраторе
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/admins"/>">Список администраторов</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-desktop"></i> <a href="<c:url value="/admins/${admin.id}"/>">Информация об
                            администраторе</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-edit"></i> Редактирование данных
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <form:form method="post" action="/admins/${id}/edit" modelAttribute="admin" commandName="admin">
                        <table class="table table-hover">
                            <tbody>
                            <tr>
                                <td><form:label path="username">Имя пользователя:</form:label></td>
                                <td><form:input cssClass="form-control" path="username"/></td>
                                <form:hidden path="password"/>
                                <c:set var="usernameErrors"><form:errors path="username" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty usernameErrors}">
                                        <td> ${usernameErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Имя пользователя должно состоять из ...</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="name">Имя:</form:label></td>
                                <td><form:input cssClass="form-control" path="name"/></td>
                                <c:set var="nameErrors"><form:errors path="name" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty nameErrors}">
                                        <td> ${nameErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Имя пользователя должно состоять из ...</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="patronymic">Отчество:</form:label></td>
                                <td><form:input cssClass="form-control" path="patronymic"/></td>
                                <c:set var="patronymicErrors"><form:errors path="patronymic" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty patronymicErrors}">
                                        <td> ${patronymicErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Имя пользователя должно состоять из ...</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="surname">Фамилия:</form:label></td>
                                <td><form:input cssClass="form-control" path="surname"/></td>
                                <c:set var="surnameErrors"><form:errors path="surname" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty surnameErrors}">
                                        <td> ${surnameErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Имя пользователя должно состоять из ...</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="email">Email:</form:label></td>
                                <td><form:input type="email" cssClass="form-control" path="email"/></td>
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
                                <td><form:label path="phone">Номер телефона (без +7):</form:label></td>
                                <td><form:input cssClass="form-control" path="phone"/></td>
                                <c:set var="phoneErrors"><form:errors path="phone" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty phoneErrors}">
                                        <td> ${phoneErrors} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Имя пользователя должно состоять из ...</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td><form:label path="gender">Пол:</form:label></td>
                                <td>
                                    <select name="gender" id="gender" class="form-control">
                                        <c:forEach items="${genders}" var="gender">
                                            <option <c:if test="${admin.gender.name == gender.name}">selected</c:if>>
                                                    ${gender.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>Имя пользователя должно состоять из ...</td>
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
                                <td><form:label path="role">${admin.role.name}</form:label></td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td><form:label path="blocked">Заблокирован:</form:label></td>
                                <td>${admin.blocked}</td>
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
                                <td></td>
                                <td><a href="<c:url value="/admins/${id}"/>" class="btn btn-lg btn-danger">Отмена</a>
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
