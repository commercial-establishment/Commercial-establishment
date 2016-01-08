<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<t:head title="Добавление администратора"/>

<t:container admins="active">

    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Добавление администратора
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/admin/admins"/>">Список администраторов</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-edit"></i> Добавление администратора
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <form:form method="post" action="/admin/admins/create-save" modelAttribute="admin">
                        <table class="table table-hover">
                            <tbody>
                            <tr>
                                <td><form:label path="username">Имя пользователя:</form:label></td>
                                <td><form:input cssClass="form-control" path="username"/></td>
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
                                <td><form:label path="password">Пароль:</form:label></td>
                                <td><form:password cssClass="form-control" path="password"/></td>
                                <c:set var="passwordErrors"><form:errors path="password" cssClass="error"/> </c:set>
                                <c:choose>
                                    <c:when test="${not empty passwordErrors}">
                                        <td> ${passwordErrors} </td>
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
                                            <option>${gender.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <%--<tr>--%>
                                <%--<td><form:label path="role">Роль:</form:label></td>--%>
                                <%--<td><form:label path="role">ADMIN</form:label></td>--%>
                                <%--<td>Имя пользователя должно состоять из ...</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td><form:label path="blocked">Заблокирован:</form:label></td>--%>
                                <%--<td>${admin.blocked}</td>--%>
                                <%--<form:hidden path="blocked"/>--%>
                                <%--<td>Имя пользователя должно состоять из ...</td>--%>
                            <%--</tr>--%>
                            <tr>
                                <td>
                                    <form:button type="submit" class="btn btn-lg btn-success">Сохранить</form:button>
                                </td>
                                <td/>
                                <td><a href="<c:url value="/admin/admins/${id}"/>" class="btn btn-lg btn-danger">Отмена</a>
                                </td>
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
