<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<jsp:include page="../fragments/head.jsp" />
<body>
	<div class="row-fluid">
		<div class="span2">&nbsp;</div>
		<div class="span8">
			<h2>Login</h2>
			<hr />
			<form method="post" class="form-horizontal">

				<div class="control-group">
					<label class="control-label" for="inputEmail">Username</label>
					<div class="controls">
						<input type="text" id="user" name="user">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="inputEmail">Password</label>
					<div class="controls">
						<input type="password" id="password" name="pwd">
					</div>
				</div>

				<div class="control-group">
					<div class="controls">
						<input class="btn" type="submit" value="Login" />
					</div>
				</div>

			</form>
		</div>
		<div class="span2">&nbsp;</div>
	</div>
</body>
</html>