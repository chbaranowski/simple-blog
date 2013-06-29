<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="fragments/head.jsp" />

<div id="posts" class="row-fluid">
	<div class="span2">&nbsp;</div>
	<div class="span8">
		<h3>Status</h3>
		<table class="table">
			<tbody>
				<tr>
					<td><b>Version</b></td>
					<td><c:out value="${version}"></c:out></td>
				</tr>
				<tr>
					<td><b>Build Date</b></td>
					<td><c:out value="${buildDate}"></c:out></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="span2">&nbsp;</div>
</div>

<jsp:include page="fragments/body.jsp" />
