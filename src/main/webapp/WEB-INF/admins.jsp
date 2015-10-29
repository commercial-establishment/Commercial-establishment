<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<t:head title="Администраторы"/>

<t:container admins="active">
    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Список администраторов
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/admin"/>">Главная</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-table"></i> Список администраторов
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <div>
                        <a href="<c:url value="/admins/create"/>" class="btn btn-lg btn-default">Добавить
                            администратора</a>
                        <br/> <br/>
                    </div>
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Имя пользователя</th>
                            <th>Имя</th>
                            <th>Отчество</th>
                            <th>Фамилия</th>
                            <th>Заблокирован</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${admins}" var="admin">
                            <tr onclick="document.location = '/admins/' + ${admin.id};">
                                <td>${admin.id}</td>
                                <td>${admin.username}</td>
                                <td>${admin.name}</td>
                                <td>${admin.patronymic}</td>
                                <td>${admin.surname}</td>
                                <td>${admin.blocked}</td>
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
