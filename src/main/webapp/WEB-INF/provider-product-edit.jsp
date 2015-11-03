<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<t:head title="Товары"/>

<t:container providers="active">
    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Редактирование товара
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
                            <i class="fa fa-desktop"></i> <a
                                href="<c:url value="/admin/providers/${productProvider.provider.id}"/>">Информация о
                            поставщике</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a
                                href="<c:url value="/admin/providers/${productProvider.provider.id}/products"/>">Список
                            товаров</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-table"></i> Редактирование товара
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <form:form method="post" action="/admin/providers/${productProvider.provider.id}/products/add"
                               modelAttribute="productProvider" commandName="productProvider">
                        <table class="table table-bordered table-hover table-striped">
                            <thead>
                            <tr>
                                <th>Название</th>
                                <th>Категория</th>
                                <th>Количество</th>
                                <th>Цена</th>
                            </tr>
                            </thead>
                            <tbody>

                            <tr>
                                <td>${productProvider.product.name}</td>
                                <td>${productProvider.product.category.name}</td>
                                <td><input type="number" name="amount" id="amount" class="form-control" size="5"
                                           min="1" max="9999" value="${productProvider.amount}"></td>
                                <td><input type="number" name="price" id="price" class="form-control" size="5"
                                           min="1"
                                           max="9999" value="${productProvider.price}"></td>
                            </tr>
                            </tbody>
                        </table>
                        <div align="right">
                            <button type="submit" name="productId" value="${product.id}"
                                    class="btn btn-lg btn-success">Сохранить
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

    <t:script/>
</t:container>
</html>
