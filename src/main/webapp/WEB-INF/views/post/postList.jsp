<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/mvc/blog/post/new" var="newPost" />

<jsp:include page="../fragments/head.jsp"/>

<div class="row-fluid">
	<div class="span2">&nbsp;</div>
	<div class="span8">

		<h2><spring:message code="blog.posts.title"></spring:message></h2>

		<table class="table">
			<thead>
				<tr>
					<th>Title</th>
					<th class="rightCell">&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" items="${posts}">
					<tr>
						<td><c:out value="${post.title}"></c:out></td>
						<td class="rightCell">
							<spring:url value="/mvc/blog?postId={id}" var="viewPost" >
						         <spring:param name="id" value="${post.id}"/>
						     </spring:url>
						     <a class="btn" href="${viewPost}"><i class="icon-eye-open"></i></a>
						     <spring:url value="/mvc/blog/post/{id}/edit" var="editPost" >
						         <spring:param name="id" value="${post.id}"/>
						     </spring:url>
						     <a class="btn" href="${editPost}"><i class="icon-pencil"></i></a> 
						     <spring:url value="/mvc/blog/post/{id}/delete" var="deletePost" >
                                    <spring:param name="id" value="${post.id}"/>
                                </spring:url>
						     <a class="btn delete" href="${deletePost}"><i class="icon-trash"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<a id="newPost" class="btn btn-primary" href="${newPost}">New Post</a>
		
		<div id="confirmDeleteModal" class="modal hide fade" tabindex="-1">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal">X</button>
		    <h3 id="myModalLabel"><spring:message code="blog.post.confirm.delete.title" /></h3>
		  </div>
		  <div class="modal-body">
		    <p><spring:message code="blog.post.confirm.delete.details" /> </p>
		  </div>
		  <div class="modal-footer">
		     <form id="deletePostForm" method="post">
			    <button type="submit" class="btn btn-primary">Delete</button>
		        <a class="btn" data-dismiss="modal">Cancel</a>
		     </form>
		  </div>
		</div>

	</div>
	<div class="span2">&nbsp;</div>
</div>

<jsp:include page="../fragments/body.jsp" />

