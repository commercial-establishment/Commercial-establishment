<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="ru">

<t:head title="Главная"/>

<t:container home="active">
    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Главная страница
                    </h1>
                    <ol class="breadcrumb">
                        <li class="active">
                            <i class="fa fa-dashboard"></i> Главная
                        </li>
                    </ol>
                </div>
            </div>

            <div class="jumbotron">
                <h1>Привет, ${pageContext.request.userPrincipal.name}!</h1>

                <p>Какая-то информация Какая-то информация Какая-то информация Какая-то информация Какая-то информация
                    Какая-то информация Какая-то информация Какая-то информация Какая-то информация Какая-то информация
                    Какая-то информация </p>

                <p><a href="#" class="btn btn-primary btn-lg" role="button">Узнать больше о нас »</a>
                </p>
            </div>

        </div>
    </div>

    <t:script/>
</t:container>
</html>