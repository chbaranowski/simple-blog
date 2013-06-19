<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="fragments/head.jsp"/>

<div id="posts" class="row-fluid">
    <div class="span2">&nbsp;</div>
    <div class="span8">

          <c:forEach var="post" items="${posts}">
               <div class="post">
                   <h3><c:out value="${post.title}"></c:out></h3>
                   <div class="content">
                       <c:out value="${post.content}" escapeXml="false"></c:out>
                   </div>
                   <div class="tags">
                     <c:forEach var="tag" items="${post.tags}">
                      <span><i class="icon-tags"></i> <c:out value="${tag.name}" /> </span>
                     </c:forEach>
                   </div>
                   <sec:authorize access="hasRole('ROLE_ADMIN')" >
                       <spring:url value="/mvc/blog/post/{id}/edit" var="postEditUrl">
                           <spring:param name="id" value="${post.id}" />
                       </spring:url>
                       <a href="${postEditUrl}"><i class="icon-pencil"></i></a>
                   </sec:authorize>
               </div>
               <hr />
          </c:forEach>
		
	</div>
	<div class="span2">&nbsp;</div>
</div>
	
<jsp:include page="fragments/body.jsp" />
