<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="ru">

<t:head title="Авторизация пользователя"/>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="home.jsp">Торговое предприятие</a>
    </div>
</nav>

<div id="page-wrapper">

    <div class="container-fluid">

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Пожалуйста, авторизуйтесь
                </h1>
            </div>
        </div>

        <div class="center">
            <div class="col-lg-10">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <b>Войти</b></h3>
                    </div>
                    <div class="panel-body">
                        <c:url value="/j_spring_security_check" var="loginUrl"/>
                        <form action="${loginUrl}" method="post" class="form-signin">
                            <c:if test="${error != null}">
                                <div align="center" class="alert alert-danger">
                                    <p><spring:message code="${error}"/></p>
                                </div>
                            </c:if>
                            <div class="jumbotron" style="background-color: white">
                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <input id="username" name="j_username" type="text" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label for="password">Password <a href="<c:url value="/recovery"/>">(forgot
                                        password)</a></label>
                                    <input id="password" name="j_password" type="password" class="form-control">
                                </div>

                                <p>
                                    <button class="btn btn-default btn-lg">Войти</button>
                                </p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<t:script/>

</body>

</html>
