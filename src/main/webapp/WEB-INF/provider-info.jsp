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
                        Информация о поставщике
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/admin/providers"/>">Список
                            поставщиков</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-desktop"></i> Информация о поставщике
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">

                    <table class="table table-hover">
                        <tbody>
                        <tr>
                            <td>
                                <form method="GET" action="/admin/providers/${id}/products">
                                    <input type="submit" class="btn btn-lg btn-default" value="Товары">
                                </form>
                                    <%--<a href="<c:url value="/providers/${id}/products"/>" class="btn btn-lg btn-default">Товары</a>--%>
                            </td>
                            <td><a href="<c:url value="/admin/providers/${id}/shops"/>" class="btn btn-lg btn-default">Магазины</a>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Имя пользователя:</b></td>
                            <td>${provider.username}</td>
                        </tr>
                        <tr>
                            <td><b>Название фирмы:</b></td>
                            <td>${provider.companyName}</td>
                        </tr>
                        <tr>
                            <td><b>Email:</b></td>
                            <td>${provider.email}</td>
                        </tr>
                        <tr>
                            <td><b>Город:</b></td>
                            <td>${provider.city.name}</td>
                        </tr>
                        <tr>
                            <td><b>Адрес:</b></td>
                            <td>${provider.address}</td>
                        </tr>
                        <tr>
                            <td><b>Контактное лицо:</b></td>
                            <td>${provider.contactPerson}</td>
                        </tr>
                        <tr>
                            <td><b>ИИН:</b></td>
                            <c:choose>
                                <c:when test="${provider.identificationNumber == ''}">
                                    <td>ИИН не заполнен</td>
                                </c:when>
                                <c:when test="${provider.identificationNumber != null}">
                                    <td>${provider.identificationNumber}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>ИИН не заполнен</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                        <tr>
                            <td><b>БИН:</b></td>
                            <c:choose>
                                <c:when test="${provider.bin == ''}">
                                    <td>БИН не заполнен</td>
                                </c:when>
                                <c:when test="${provider.bin != null}">
                                    <td>${provider.bin}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>БИН не заполнен</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                        <tr>
                            <td><b>Дата начала работы:</b></td>
                            <td>${provider.startWorkDate}</td>
                        </tr>
                        <c:if test="${provider.endWorkDate != null}">
                            <tr>
                                <td><b>Дата окончания работы:</b></td>
                                <td>${provider.endWorkDate}</td>
                            </tr>
                        </c:if>
                        <tr>
                            <td><b>Роль:</b></td>
                            <td><b>${provider.role.name}</b></td>
                        </tr>
                        <tr>
                            <td><b>Заблокирован:</b></td>
                            <td>${provider.blocked}</td>
                        </tr>
                        <tr>
                            <td>
                                <form method="GET" action="<c:url value="/admin/providers/${id}/edit"/>">
                                    <input type="submit" class="btn btn-lg btn-default" value="Редактировать">
                                </form>
                            </td>
                            <c:choose>
                                <c:when test="${provider.blocked == false}">
                                    <td><a href="<c:url value="/admin/providers/${id}/lock"/>"
                                           class="btn btn-lg btn-danger">Заблокировать</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="<c:url value="/admin/providers/${id}/reestablish"/>"
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
