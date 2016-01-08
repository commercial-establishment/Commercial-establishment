<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        Список поставщиков
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-table"></i> Список поставщиков
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <div>
                        <form method="GET" action="<c:url value="/admin/providers/create"/>">
                            <input type="submit" class="btn btn-lg btn-default" value="Добавить">
                        </form>
                        <%--<a href="<c:url value="/admin/providers/create"/>" class="btn btn-lg btn-default">Добавить--%>
                            <%--поставщика</a>--%>
                        <%--<br/> --%>
                        <br/>
                    </div>
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Имя пользователя</th>
                            <th>Название компании</th>
                            <th>Контактное лицо</th>
                            <th>Email</th>
                            <th>Заблокирован</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${providers}" var="provider">
                            <tr onclick="document.location = '/admin/providers/' + ${provider.id};">
                                <td>${provider.id}</td>
                                <td>${provider.username}</td>
                                <td>${provider.companyName}</td>
                                <td>${provider.contactPerson}</td>
                                <td>${provider.email}</td>
                                <td>${provider.blocked}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <t:script/>
</t:container>
</html>
