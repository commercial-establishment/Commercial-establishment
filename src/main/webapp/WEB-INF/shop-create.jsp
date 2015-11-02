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
                        Добавление магазина
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/shops"/>">Список магазинов</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-edit"></i> Добавление магазина
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <form:form method="post" action="/shops/create-save" modelAttribute="shop">
                        <table class="table table-hover">
                            <tbody>
                            <tr>
                                <td><form:label path="name">Наименование магазина:</form:label></td>
                                <td><form:input cssClass="form-control" path="name"/></td>
                                    <%--<form:hidden path="password"/>--%>
                                    <%--<c:set var="usernameErrors"><form:errors path="username" cssClass="error"/> </c:set>--%>
                                    <%--<c:choose>--%>
                                    <%--<c:when test="${not empty usernameErrors}">--%>
                                    <%--<td> ${usernameErrors} </td>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                    <%--<td>Имя пользователя должно состоять из ...</td>--%>
                                    <%--</c:otherwise>--%>
                                    <%--</c:choose>--%>
                            </tr>
                            <tr>
                                <td><form:label path="address">Адрес магазина:</form:label></td>
                                <td><form:input cssClass="form-control" path="address"/></td>
                                    <%--<c:set var="companyNameErrors"><form:errors path="address" cssClass="error"/>--%>
                                    <%--</c:set>--%>
                                    <%--<c:choose>--%>
                                    <%--<c:when test="${not empty companyNameErrors}">--%>
                                    <%--<td> ${companyNameErrors} </td>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                    <%--<td>Имя пользователя должно состоять из ...</td>--%>
                                    <%--</c:otherwise>--%>
                                    <%--</c:choose>--%>
                            </tr>
                            <tr>
                                <td><form:label path="city">Город:</form:label></td>
                                <td>
                                    <select name="city" id="city" class="form-control">
                                        <c:forEach items="${cities}" var="city">
                                            <option> ${city.name}</option>
                                        </c:forEach>
                                    </select>
                                    </select>
                                </td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td><form:label path="type">Тип:</form:label></td>
                                <td>
                                    <select name="type" id="type" class="form-control">
                                        <c:forEach items="${types}" var="type">
                                            <option>${type.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td>
                                    <form:button type="submit" class="btn btn-lg btn-success">Сохранить</form:button>
                                </td>
                                <td/>
                                <td><a href="<c:url value="/shops/${id}"/>" class="btn btn-lg btn-danger">Отмена</a>
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
