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
                        Информация о товаре
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>"> Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/providers"/>"> Список поставщиков</a>
                        </li>
                        <li>
                            <i class="fa fa-desktop"></i> <a href="<c:url value="/providers/${id}"/>"> Информация о
                            поставщике</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i><a href="<c:url value="/providers/${id}/products"/>"> Список
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
                            <td>${productProvider.product.name}</td>
                        </tr>
                        <tr>
                            <td><b>Категория:</b></td>
                            <td>${productProvider.product.category.name}</td>
                        </tr>
                        <tr>
                            <td><b>Цена(в тенге):</b></td>
                            <td>${productProvider.price}</td>
                        </tr>
                        <tr>
                            <td><b>Количество:</b></td>
                            <td>${productProvider.amount}</td>
                        </tr>
                        <tr>
                            <td><b>Общая цена:</b></td>
                            <td><b>${sumPrice}</b></td>
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
