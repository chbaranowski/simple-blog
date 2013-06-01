<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Simple Blog</title>

<!-- Load jQuery -->
<spring:url value="/css/application.css" var="applicationCss" />
<spring:url value="/mvc/static/bootstrap/2.3.2/css/bootstrap.min.css" var="bootstrapCss" />

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${applicationCss}" rel="stylesheet" />

</head>