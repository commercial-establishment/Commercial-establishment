<%@tag description="authentication template" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="role" property="principal.authorities[0]"/>

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
            <a class="navbar-brand" href="<c:url value="/home"/>">Торговое предприятие</a>
        </div>
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                        class="fa fa-user"></i> ${pageContext.request.userPrincipal.name}<b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="<c:url value="/cabinet"/>"><i class="fa fa-fw fa-gear"></i>Кабинет</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="<c:url value="/logout"/>"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                    </li>
                </ul>
            </li>
        </ul>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <%@ attribute name="products" %>
                <%@ attribute name="providers" %>
                <%@ attribute name="admins" %>
                <%@ attribute name="shops" %>
                <%@ attribute name="categories" %>
                <%@ attribute name="home" %>

                <c:choose>
                    <c:when test="${role eq 'ROLE_ADMIN'}">
                        <li class="${home}">
                            <a href="<c:url value="/home"/>"><i class="fa fa-fw fa-dashboard"></i> Главная</a>
                        </li>
                        <li class="${providers}">
                            <a href="<c:url value="/admin/providers"/>"><i class="fa fa-fw fa-table"></i> Поставщики</a>
                        </li>
                        <li class="${admins}">
                            <a href="<c:url value="/admin/admins"/>"><i class="fa fa-fw fa-table"></i>Администраторы</a>
                        </li>
                        <li class="${shops}">
                            <a href="<c:url value="/admin/shops"/>"><i class="fa fa-fw fa-table"></i> Магазины</a>
                        </li>
                        <li class="${products}">
                            <a href="<c:url value="/admin/products"/>"><i class="fa fa-fw fa-table"></i>Товары</a>
                        </li>
                        <li class="${categories}">
                            <a href="<c:url value="/categories"/>"><i class="fa fa-fw fa-wrench"></i>Категории
                                товаров</a>
                        </li>
                    </c:when>
                    <c:when test="${role eq 'ROLE_PROVIDER'}">
                        <li class="${home}">
                            <a href="<c:url value="/home"/>"><i class="fa fa-fw fa-dashboard"></i> Главная</a>
                        </li>
                        <li class="${shops}">
                            <a href="<c:url value="/provider/shops"/>"><i class="fa fa-fw fa-table"></i> Магазины</a>
                        </li>
                        <li class="${products}">
                            <a href="<c:url value="/provider/products"/>"><i class="fa fa-fw fa-table"></i>Товары</a>
                        </li>
                        <li class="${categories}">
                            <a href="<c:url value="/categories"/>"><i class="fa fa-fw fa-wrench"></i>Категории
                                товаров</a>
                        </li>
                    </c:when>
                </c:choose>
            </ul>
        </div>
    </nav>
    <jsp:doBody/>
</div>
</body>
