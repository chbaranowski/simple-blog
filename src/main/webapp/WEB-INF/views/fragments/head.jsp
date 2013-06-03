<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Simple Blog</title>

<!-- Load jQuery -->
<spring:url value="/css/application.css" var="applicationCss" />
<spring:url value="/mvc/static/bootstrap/2.3.2/css/bootstrap.min.css" var="bootstrapCss" />

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${applicationCss}" rel="stylesheet" />

</head>
<body>

<div class="navbar navbar-fixed-top">
<div class="navbar-inner">
	<div class="container-fluid">
		<spring:url value="/mvc/blog" var="blogUrl" />
		<a class="brand" href="${blogUrl}">Simple Blog</a>
		<ul class="nav pull-right">
			<sec:authorize access="isAnonymous()" >
				<li>
					<spring:url value="/mvc/auth/login" var="loginUrl" />
					<a href="${loginUrl}">login</a>
				</li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()" >
				<li>
					<spring:url value="/mvc/auth/logout" var="logoutUrl" />
					<a href="${logoutUrl}">logout</a>
				</li>
			</sec:authorize>
		</ul>
	</div>
</div>
</div>
