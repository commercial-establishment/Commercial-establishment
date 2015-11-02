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
                        Список товаров
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
                        <a href="<c:url value="/providers/${providerId}/products/all"/>" class="btn btn-lg btn-default">Добавить товар</a>
                        <br/> <br/>
                    </div>
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Название</th>
                            <th>Категория</th>
                            <th>Цена(в тенге)</th>
                            <th>Количество</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${providerProducts}" var="providerProduct">
                            <tr onclick="document.location = '/providers/' + ${providerProduct.provider.id}
                                    + '/products/' + ${providerProduct.id};">
                                <td>${providerProduct.id}</td>
                                <td>${providerProduct.product.name}</td>
                                <td>${providerProduct.product.category.name}</td>
                                <td>${providerProduct.price}</td>
                                <td>${providerProduct.amount}</td>
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
