<%@tag description="authentication template" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<body>
<div id="wrapper">
  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">

    <%--Header--%>
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
            <a href="#"><i class="fa fa-fw fa-gear"></i>Кабинет</a>
          </li>
          <li class="divider"></li>
          <li>
            <a href="<c:url value="/logout"/>"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
          </li>
        </ul>
      </li>
    </ul>

    <%--Panel--%>
    <div class="collapse navbar-collapse navbar-ex1-collapse">
      <ul class="nav navbar-nav side-nav">
        <%@ attribute name="home" %>
        <li class="${home}">
          <a href="<c:url value="/home"/>"><i class="fa fa-fw fa-dashboard"></i> Главная</a>
        </li>
        <%@ attribute name="providers" %>
        <li class="${providers}">
          <a href="<c:url value="/providers"/>"><i class="fa fa-fw fa-table"></i> Поставщики</a>
        </li>
        <%@ attribute name="admins" %>
        <li class="${admins}">
          <a href="<c:url value="/admins"/>"><i class="fa fa-fw fa-table"></i>Администраторы</a>
        </li>
        <%@ attribute name="shops" %>
        <li class="${shops}">
          <a href="<c:url value="/shops"/>"><i class="fa fa-fw fa-table"></i> Магазины</a>
        </li>
        <%@ attribute name="products" %>
        <li class="${products}">
          <a href="<c:url value="/products"/>"><i class="fa fa-fw fa-table"></i>Товары</a>
        </li>
        <%@ attribute name="categories" %>
        <li class="${categories}">
          <a href="<c:url value="/categories"/>"><i class="fa fa-fw fa-wrench"></i>Категории товаров</a>
        </li>
        <%--<li>--%>
        <%--<a href="javascript:;" data-toggle="collapse" data-target="#demo"><i--%>
        <%--class="fa fa-fw fa-arrows-v"></i> Dropdown <i class="fa fa-fw fa-caret-down"></i></a>--%>
        <%--<ul id="demo" class="collapse">--%>
        <%--<li>--%>
        <%--<a href="#">Dropdown Item</a>--%>
        <%--</li>--%>
        <%--<li>--%>
        <%--<a href="#">Dropdown Item</a>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <%--</li>--%>
        <%--<li>--%>
        <%--<a href="blank-page.html"><i class="fa fa-fw fa-file"></i> Blank Page</a>--%>
        <%--</li>--%>
      </ul>
    </div>
  </nav>

  <jsp:doBody/>

</div>
</body>
