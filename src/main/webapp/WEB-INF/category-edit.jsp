<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<t:head title="Редактирование категории">
    <link href="<c:url value="/resources/css/datepicker.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
</t:head>

<t:container categories="active">

    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Редактирование категории
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/categories"/>">Категории</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-desktop"></i> <a href="<c:url value="/admin/categories/${category.id}"/>">Информация
                            о
                            категориях</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-edit"></i> Редактирование данных
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <form:form method="post" action="/admin/categories/${id}/edit" modelAttribute="category"
                               commandName="category">
                        <table class="table table-hover">
                            <tbody>
                            <tr>
                                <td class = "td_category"><form:label path="name">Название категории:</form:label></td>
                                <td class = "td_category"><form:input cssClass="form-control" path="name"/></td>
                            </tr>
                            <tr>
                                <td>
                                    <form:button type="submit" class="btn btn-lg btn-success">Сохранить</form:button>
                                        <%--<input type="submit" class="btn btn-lg btn-success" value="Сохранить">--%>
                                </td>
                                <td><a href="<c:url value="/admin/categories/${id}"/>" class="btn btn-lg btn-danger">Отмена</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

    <script src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
</t:container>
</html>
