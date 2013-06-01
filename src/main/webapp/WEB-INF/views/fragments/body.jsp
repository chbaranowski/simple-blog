<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/mvc/static/tinymce-jquery/3.4.9/jscripts/tiny_mce/jquery.tinymce.js" var="tinymceJqueryJs" />
<spring:url value="/mvc/static/jquery/2.0.0/jquery.min.js" var="jqueryJs" />

<script type="text/javascript" src="${jqueryJs}"></script>
<spring:url value="/mvc/static/bootstrap/2.3.2/js/bootstrap.min.js" var="bootstrapJs" />
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${tinymceJqueryJs}"></script>
<spring:url value="/js/backend.js" var="backendJs" />
<script type="text/javascript" src="${backendJs}"></script>
