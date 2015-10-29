<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Редактирование данных об администраторе</title>

    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/sb-admin.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="<c:url value="/resources/css/datepicker.css"/>" rel="stylesheet" type="text/css">

</head>

<body>

<div id="wrapper">

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">SB Admin</a>
        </div>
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b
                        class="caret"></b></a>
                <ul class="dropdown-menu message-dropdown">
                    <li class="message-preview">
                        <a href="#">
                            <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>

                                <div class="media-body">
                                    <h5 class="media-heading"><strong>John Smith</strong>
                                    </h5>

                                    <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>

                                    <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="message-preview">
                        <a href="#">
                            <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>

                                <div class="media-body">
                                    <h5 class="media-heading"><strong>John Smith</strong>
                                    </h5>

                                    <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>

                                    <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="message-preview">
                        <a href="#">
                            <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>

                                <div class="media-body">
                                    <h5 class="media-heading"><strong>John Smith</strong>
                                    </h5>

                                    <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>

                                    <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="message-footer">
                        <a href="#">Read All New Messages</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b
                        class="caret"></b></a>
                <ul class="dropdown-menu alert-dropdown">
                    <li>
                        <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">View All</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                    </li>
                </ul>
            </li>
        </ul>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li>
                    <a href="<c:url value="/home"/>"><i class="fa fa-fw fa-dashboard"></i> Главная</a>
                </li>
                <li>
                    <a href="<c:url value="/providers"/>"><i class="fa fa-fw fa-table"></i> Поставщики</a>
                </li>
                <li class="active">
                    <a href="<c:url value="/admins"/>"><i class="fa fa-fw fa-table"></i>Администраторы</a>
                </li>
                <li>
                    <a href="<c:url value="/shops"/>"><i class="fa fa-fw fa-table"></i> Магазины</a>
                </li>
                <li>
                    <a href="<c:url value="/products"/>"><i class="fa fa-fw fa-table"></i>Товары</a>
                </li>
                <li>
                    <a href="<c:url value="/categories"/>"><i class="fa fa-fw fa-wrench"></i>Категории товаров</a>
                </li>
            </ul>
        </div>
    </nav>

    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Редактирование данных об администраторе
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/admin"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/admins"/>">Список администраторов</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-edit"></i> Добавление администратора
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <form:form method="post" action="/admins/create-save" modelAttribute="admin">
                        <table class="table table-hover">
                            <tbody>
                            <tr>
                                <td><form:label path="username">Имя пользователя:</form:label></td>
                                <td><form:input cssClass="form-control" path="username"/></td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td><form:label path="password">Пароль:</form:label></td>
                                <td><form:input cssClass="form-control" path="password"/></td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td><form:label path="name">Имя:</form:label></td>
                                <td><form:input cssClass="form-control" path="name"/></td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td><form:label path="patronymic">Отчество:</form:label></td>
                                <td><form:input cssClass="form-control" path="patronymic"/></td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td><form:label path="surname">Фамилия:</form:label></td>
                                <td><form:input cssClass="form-control" path="surname"/></td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td><form:label path="email">Email:</form:label></td>
                                <td><form:input type="email" cssClass="form-control" path="email"/></td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td><form:label path="phone">Номер телефона (без +7):</form:label></td>
                                <td><form:input cssClass="form-control" path="phone"/></td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td><form:label path="gender">Пол:</form:label></td>
                                <td>
                                    <select name="gender" id="gender" class="form-control">
                                        <c:forEach items="${genders}" var="gender">
                                            <option> ${gender.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td><form:label path="role">Роль:</form:label></td>
                                <td><form:label path="role">ADMIN</form:label></td>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td><form:label path="blocked">Заблокирован:</form:label></td>
                                <td>${admin.blocked}</td>
                                <form:hidden path="blocked"/>
                                <td>Имя пользователя должно состоять из ...</td>
                            </tr>
                            <tr>
                                <td>
                                    <form:button type="submit" class="btn btn-lg btn-success">Сохранить</form:button>
                                </td>
                                <td/>
                                <td><a href="<c:url value="/admins/${id}"/>" class="btn btn-lg btn-danger">Отмена</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
</body>

</html>
