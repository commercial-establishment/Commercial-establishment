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
                            <i class="fa fa-table"></i> <a href="<c:url value="/admin/providers"/>">Список
                            поставщиков</a>
                        </li>
                        <li>
                            <i class="fa fa-desktop"></i> <a href="<c:url value="/admin/providers/${id}"/>">Информация о
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
                        <a href="<c:url value="/admin/providers/${providerId}/shops/all"/>"
                           class="btn btn-lg btn-default">Добавить
                            магазин</a>
                        <br/> <br/>
                    </div>
                    <c:choose>
                        <c:when test="${providerShops.size() == 0}">
                            <h1>Нет привязанных магазинов. Пожалуйтса, добавьте магазин.</h1>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Название магазина</th>
                                    <th>Адрес</th>
                                    <th>Город</th>
                                    <th>Тип</th>
                                    <th>Удалить/Восстановить</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%--@elvariable id="providerShop" type="kz.hts.ce.model.entity.ShopProvider"--%>
                                <c:forEach items="${providerShops}" var="providerShop">
                                    <tr onclick="document.location = '/admin/providers/' + '${providerShop.provider.id}' + '/shops/' + '${providerShop.shop.id}';">
                                        <td>${providerShop.id}</td>
                                        <td>${providerShop.shop.name}</td>
                                        <td>${providerShop.shop.address}</td>
                                        <td>${providerShop.shop.area.city.name}</td>
                                        <td>${providerShop.shop.type.name}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${providerShop.blocked == false}">
                                                    <form method="post"
                                                          action="<c:url value="/admin/providers/${providerShop.provider.id}/shops/${providerShop.shop.id}/lock"/>">
                                                        <button type="submit" name="shopId"
                                                                value="${providerShop.shop.id}"
                                                                class="btn btn-lg btn-danger">Удалить
                                                        </button>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <form method="post"
                                                          action="<c:url value="/admin/providers/${providerShop.provider.id}/shops/${providerShop.shop.id}/reestablish"/>">
                                                        <button type="submit" name="shopId"
                                                                value="${providerShop.shop.id}"
                                                                class="btn btn-lg btn-success">Восстановить
                                                        </button>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>

    <t:script/>
</t:container>
</html>
