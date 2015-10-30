<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<t:head title="Информация об администраторе"/>

<t:container providers="active">

    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Информация об администраторе
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/providers"/>">Список поставщиков</a>
                        </li>
                        <li>
                            <i class="fa fa-desktop"></i> <a href="<c:url value="/providers/${id}"/>">Информация о
                            поставщике</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i><a href="<c:url value="/providers/${id}/products"/>">Список
                            товаров</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-table"></i> Информация о товаре
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <tbody>
                        <tr>
                            <td><b>Название:</b></td>
                            <td>${admin.name}</td>
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
