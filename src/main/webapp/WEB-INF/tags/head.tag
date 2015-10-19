<%@tag description="authentication template" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <%@ attribute name="title" %>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>${title}</title>

        <!-- Bootstrap Core CSS -->
        <link href="<c:url value="/static/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">

        <!-- Custom CSS -->
        <link href="<c:url value="/static/css/sb-admin.css"/>" rel="stylesheet" type="text/css">

        <!-- Morris Charts CSS -->
        <link href="<c:url value="/static/css/plugins/morris.css"/>" rel="stylesheet" type="text/css">

        <!-- Custom Fonts -->
        <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
</fmt:bundle>