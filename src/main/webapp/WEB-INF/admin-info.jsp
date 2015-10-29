<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Информация об администраторе</title>

    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/sb-admin.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

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
                        Информация об администраторе
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="<c:url value="/admin"/>">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-table"></i> <a href="<c:url value="/admins"/>">Список администраторов</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-desktop"></i> Информация об администраторе
                        </li>
                    </ol>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <tbody>
                        <tr>
                            <td><b>Имя пользователя:</b></td>
                            <td>${admin.username}</td>
                        </tr>
                        <tr>
                            <td><b>Имя:</b></td>
                            <td>${admin.name}</td>
                        </tr>
                        <tr>
                            <td><b>Отчество:</b></td>
                            <td>${admin.patronymic}</td>
                        </tr>
                        <tr>
                            <td><b>Фамилия:</b></td>
                            <td>${admin.surname}</td>
                        </tr>
                        <tr>
                            <td><b>Email:</b></td>
                            <td>${admin.email}</td>
                        </tr>
                        <tr>
                            <td><b>Номер телефона:</b></td>
                            <td><b>+7</b>${admin.phone}</td>
                        </tr>
                        <tr>
                            <td><b>Пол:</b></td>
                            <td>${admin.gender.name}</td>
                        </tr>
                        <tr>
                            <td><b>Дата начала работы:</b></td>
                            <td>${admin.startWorkDate}</td>
                        </tr>
                        <c:if test="${admin.endWorkDate != null}">
                            <tr>
                                <td><b>Дата окончания работы:</b></td>
                                <td>${admin.endWorkDate}</td>
                            </tr>
                        </c:if>
                        <tr>
                            <td><b>Роль:</b></td>
                            <td><b>${admin.role.name}</b></td>
                        </tr>
                        <tr>
                            <td><b>Заблокирован:</b></td>
                            <td>${admin.blocked}</td>
                        </tr>
                        <tr>
                            <td>
                                <form method="GET" action="<c:url value="/admins/${id}/edit"/>">
                                    <input type="submit" class="btn btn-lg btn-default" value="Редактировать">
                                </form>
                                <%--<a href="<c:url value="/admins/${id}/edit"/>" class="btn btn-lg btn-default">Редактировать</a>--%>
                            </td>

                            <c:choose>
                                <c:when test="${admin.blocked == false}">
                                    <td><a href="<c:url value="/admins/${id}/lock"/>" class="btn btn-lg btn-danger">Заблокировать</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="<c:url value="/admins/${id}/reestablish"/>"
                                           class="btn btn-lg btn-success">Восстановить</a></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

</body>

</html>
