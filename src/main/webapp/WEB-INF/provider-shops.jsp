<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<t:head title="Магазины"/>

<t:container providers="active">
    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Список магазинов
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
                        <li class="active">
                            <i class="fa fa-table"></i> Список магазинов
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <div>
                        <a href="<c:url value="/providers/${providerId}/shops/all"/>" class="btn btn-lg btn-default">Добавить магазин</a>
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
                        <c:forEach items="${providerShops}" var="providerShop">
                            <tr onclick="document.location = '/providers/' + ${providerShop.provider.id} + '/shops/' + ${providerShop.shop.id};">
                                <td>${providerShop.id}</td>
                                <td>${providerShop.shop.name}</td>
                                <td>${providerShop.shop.address}</td>
                                <td>${providerShop.shop.city.name}</td>
                                <td>${providerShop.shop.type.name}</td>
                                <td>${providerShop.blocked}</td>
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