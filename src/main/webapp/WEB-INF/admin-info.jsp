<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<t:head title="Информация об администраторе"/>

<t:container admins="active">

    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Информация об администраторе
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/admin"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/admins"/>">Список администраторов</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-desktop"></i> Информация об администраторе
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <tbody>
                        <tr>
                            <td><b>Имя пользователя:</b></td>
                            <td>${admin.username}</td>
                        </tr>
                        <tr>
                            <td><b>Имя:</b></td>
                            <td>${admin.name}</td>
                        </tr>
                        <tr>
                            <td><b>Отчество:</b></td>
                            <td>${admin.patronymic}</td>
                        </tr>
                        <tr>
                            <td><b>Фамилия:</b></td>
                            <td>${admin.surname}</td>
                        </tr>
                        <tr>
                            <td><b>Email:</b></td>
                            <td>${admin.email}</td>
                        </tr>
                        <c:if test="${not empty admin.phone}">
                        <tr>
                            <td><b>Номер телефона:</b></td>
                            <td>+7${admin.phone}</td>
                        </tr>
                        </c:if>
                        <tr>
                            <td><b>Пол:</b></td>
                            <td>${admin.gender.name}</td>
                        </tr>
                        <tr>
                            <td><b>Дата начала работы:</b></td>
                            <td>${admin.startWorkDate}</td>
                        </tr>
                        <c:if test="${admin.endWorkDate != null}">
                            <tr>
                                <td><b>Дата окончания работы:</b></td>
                                <td>${admin.endWorkDate}</td>
                            </tr>
                        </c:if>
                        <tr>
                            <td><b>Роль:</b></td>
                            <td><b>${admin.role.name}</b></td>
                        </tr>
                        <tr>
                            <td><b>Заблокирован:</b></td>
                            <td>${admin.blocked}</td>
                        </tr>
                        <tr>
                            <td>
                                <form method="GET" action="<c:url value="/admins/${id}/edit"/>">
                                    <input type="submit" class="btn btn-lg btn-default" value="Редактировать">
                                </form>
                                    <%--<a href="<c:url value="/admins/${id}/edit"/>" class="btn btn-lg btn-default">Редактировать</a>--%>
                            </td>

                            <c:choose>
                                <c:when test="${admin.blocked == false}">
                                    <td><a href="<c:url value="/admins/${id}/lock"/>" class="btn btn-lg btn-danger">Заблокировать</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="<c:url value="/admins/${id}/reestablish"/>"
                                           class="btn btn-lg btn-success">Восстановить</a></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <t:script/>
</t:container>
</html>
