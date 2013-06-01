<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<jsp:include page="../fragments/head.jsp" />
<body>
	<form method="post">
		<table>
			<tr>
				<td>Username:</td>
				<td><input name="user"></input></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="pwd"></input></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></input></td>
			</tr>
		</table>
	</form>
</body>
</html>