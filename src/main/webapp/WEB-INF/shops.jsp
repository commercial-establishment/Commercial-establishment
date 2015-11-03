<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Список магазинов
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-table"></i> Список магазинов
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <div>
                        <a href="<c:url value="/admin/shops/create"/>" class="btn btn-lg btn-default">Добавить</a>
                        <br/> <br/>
                    </div>
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Название магазина</th>
                            <th>Адрес</th>
                            <th>Город</th>
                            <th>Тип</th>
                            <th>Заблокирован</th>
                        </tr>
                        </thead>
                        <tbody>
                            <%--@elvariable id="shop" type="kz.hts.ce.entity.Shop"--%>
                        <c:forEach items="${shops}" var="shop">
                            <tr onclick="document.location = '/admin/shops/' + ${shop.id};">
                                <td>${shop.id}</td>
                                <td>${shop.name}</td>
                                <td>${shop.address}</td>
                                <td>${shop.city.name}</td>
                                <td>${shop.type.name}</td>
                                <td>${shop.blocked}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </div>

    <t:script/>
</t:container>
</html>
