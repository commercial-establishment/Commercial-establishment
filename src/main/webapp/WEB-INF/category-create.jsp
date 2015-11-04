<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<t:head title="Добавление категории"/>

<t:container admins="active">

    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Добавление категории
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/admin/categories"/>">Категории</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-edit"></i> Добавление категории
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <form:form method="post" action="/admin/categories/create-save" modelAttribute="category">
                        <table class="table table-hover fixed-table">
                            <tbody>
                            <tr>
                                <td style="width: 250px;"><form:label path="name">Название категории:</form:label></td>
                                <td style="width: 250px;"><form:input cssClass="form-control" path="name"/></td>
                            </tr>
                            <tr>
                                <td style="width: 250px;">
                                    <form:button type="submit" class="btn btn-lg btn-success">Сохранить</form:button>
                                </td>
                                <td style="width: 250px;"><a href="<c:url value="/admin/categories/${id}"/>" class="btn btn-lg btn-danger">Отмена</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <t:script/>
</t:container>
</html>
