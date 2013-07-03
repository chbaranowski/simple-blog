<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../fragments/head.jsp" />

<div class="row-fluid">
	<div class="span2">&nbsp;</div>
	<div class="span8">
	    <h2>Blog Post</h2>
	    <hr />
	    <spring:url value="/mvc/blog/post/save" var="savePost" />
		<form:form class="form-horizontal" action="${savePost}">
			<div class="control-group">
				<label class="control-label" for="title">Titel:</label>
				<div class="controls">
				    <spring:bind path="post.title" ignoreNestedPath="true">
				      <input type="text" name="${status.expression}" value="${status.value}">
				    </spring:bind>
				</div>
			</div>
			<div class="control-group">
                <div class="controls">
                    <spring:bind path="post.content" ignoreNestedPath="true">
                      <textarea rows="20" name="${status.expression}" class="tinymce" >
                        ${status.value}
                      </textarea>
                    </spring:bind>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="tagsNames">Tags:</label>
                <div class="controls">
                   <spring:bind path="tags.names" ignoreNestedPath="true">
                     <input type="text" name="${status.expression}" value="${status.value}">
                   </spring:bind>
                </div>
            </div>
			<hr />
			<input type="hidden" name="id" value="${id}"/>
			<div class="control-group">
				<div class="controls">
					<input class="btn" name="save" type="submit" value="Save" />
					<spring:url value="/mvc/blog/posts" var="posts" />
					<a href="${posts}" class="btn">Cancel</a>
				</div>
			</div>
		</form:form>
	</div>
	<div class="span2">&nbsp;</div>
</div>
	
<jsp:include page="../fragments/body.jsp" />
