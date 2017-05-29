<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Welcome to University Registration System</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="StyleSheet" href="css/struts2.css" type="text/css" />
<style type="text/css">
<!--
.style1 {
	font-size: 12
}
-->
</style>
</head>

<body>
<spring:form method="post" commandName="registerForm">
	<table width="800" border="2" height="100%">
		<tbody>
			<tr>
				<td>
				<div id="header">&nbsp;
				<div align="center"><a href="<c:url value="/home"/>">University
				Registration System</a></div>
				</div>

				<table>
					<tbody>
						<tr>
							<td width="100%">
							<table align="right" cellpadding="2">
								<tbody>
									<tr>
										<td width="90">
										<div id="menu" align="center"><a
											href="<c:url value="/logout"/>"> Logout </a></div>
										</td>
										<td width="160">
										<div id="menu" align="center"><a
											href="<c:url value="/catalog.htm"/>"> Course Catalog </a></div>
										</td>
										<td width="90">
										<div id="menu" align="center"><a
											href="<c:url value="/about.htm"/>"> About</a><br>
										</div>
										</td>
									</tr>
								</tbody>
							</table>
							</td>
						</tr>
						<tr>
							<td width="900">
							<div id="content">
							<table align="center" border="0">

								<tbody>
									<tr>
										<td align="center" colspan="2">
										<h3>New User Registration Page</h3>
										<spring:errors cssClass="error" /></td>
									</tr>
									<tr>
										<td>First Name:</td>
										<td><spring:input path="firstname" /></td>
										<td><spring:errors path="firstname" cssClass="error" />
										</td>
									</tr>
									<tr>
										<td>Last Name:</td>
										<td><spring:input path="lastname" /></td>
										<td><spring:errors path="lastname" cssClass="error" /></td>
									</tr>
									<tr>
										<td>User Name:</td>
										<td><spring:input path="username" /></td>
										<td><spring:errors path="username" cssClass="error" /></td>
									</tr>
									<tr>
										<td>Password:</td>
										<td><spring:password path="password" /></td>
										<td><spring:errors path="password" cssClass="error" /></td>
									</tr>
									<!-- TODO: CS8 - lab 4 - Students to add password confirmation -->
									<tr>
										<td>Email:</td>
										<td><spring:input path="email" /></td>
										<td><spring:errors path="email" cssClass="error" /></td>
									</tr>
									<tr>
										<td>Phone No:</td>
										<td><spring:input path="phoneNo" /></td>
										<td><spring:errors path="phoneNo" cssClass="error" /></td>
									</tr>
									<tr>
										<td>Student Classification:</td>
										<td><spring:select path="classification">
											<spring:options />
											<!-- Binding to an enum -->
										</spring:select></td>
										<td><spring:errors path="classification" cssClass="error" />
										</td>
									</tr>
									<tr>
										<td colspan="2"><input value="Register" type="submit"></td>
									</tr>
								</tbody>
							</table>
							</div>
							</td>
							<!-- content end -->
						</tr>
					</tbody>
				</table>
				</td>
			</tr>
		</tbody>
	</table>

</spring:form>

</body>

</html>
