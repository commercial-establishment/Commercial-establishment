<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<t:head title="Список категорий">
    <link href="<c:url value="/resources/css/datepicker.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
</t:head>

<t:container categories="active">

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Список товаров
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>"> Главная</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-table"></i> Категории
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <div>
                        <a href="<c:url value="/admin/categories/create"/>" class="btn btn-lg btn-default">Добавить</a>
                        <br/> <br/>
                    </div>
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Категория</th>
                        </tr>
                        </thead>
                        <tbody>
                            <%--@elvariable id="shop" type="kz.hts.ce.entity.Category"--%>
                        <c:forEach items="${categories}" var="category">
                            <tr onclick="document.location = '/admin/categories/' + ${category.id};">
                                <td class="td_id">${category.id}</td>
                                <td class="td_category">${category.name}</td>
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
