<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<html>
<head>
<title>Welcome to University Registration System</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="StyleSheet" href="css/struts2.css" type="text/css" />
</head>
<style>
.error {
	color: red;
}
</style>

<body>
<spring:form modelAttribute="loginForm" action="login" method="post">
	<!-- Header begin -->
	<table width="800" height="100%" border="2" bordercolor="#339999">
		<tr>
			<td>
			<div id="header">&nbsp;
			<div align="center">University Registration System</div>
			</div>
			<!-- header end -->
			<table>
				<tr>
					<!--content begin -->
					<td colspan="2">
					<div id="content">
					<h3>Portal Login Page:</h3>
					</div>
					</td>
				</tr>
				<tr>
					<td>User Name</td>
					<td><spring:input path="username" /> <spring:errors
						path="username" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><spring:password path="password" /> <spring:errors
						path="password" cssClass="error" /> <br />
					</td>
				</tr>
				<tr>
					<td colspan="1"><input type='submit' value="Login"></input> <br />
					</td>
				</tr>
				<tr>
					<br />
					<br />
				</tr>
				<tr>
					<td>
					<div id="content">New Users:</div>
					<div id="content"><a href="<c:url value="/register.htm"/>">Register
					here</a></div>
					</td>
				</tr>
			</table>
			<br />
			</td>
		</tr>
	</table>

</spring:form>

</body>

</html>
