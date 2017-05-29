<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Update Your Profile</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="StyleSheet" href="css/struts2.css" type="text/css" />
<style type="text/css">
<!--
.style1 {
	font-size: 12
}
-->
</style>
<style>
.error {
	color: red;
}
</style>
</head>

<body>
<spring:form method="post" commandName="registerForm">
	<table width="800" height="100%" border="2">
		<tr>
			<td>
			<div id="header">&nbsp;
			<div align="center"><a href="<c:url value="/home"/>">University
			Registration System</a></div>
			</div>

			<table>
				<tr>
					<td width="100%">
					<table align="right" cellpadding="2">
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
								href="<c:url value="/about.htm"/>"> About</a><br />
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="900">
					<div id="content" align="center">
					<p class="style1"><strong>Update your information</strong></p>
					<spring:errors cssClass="error" />
					</div>
					<table align="center" border="0">

						<tbody>
							<tr>
								<td>First Name:</td>
								<td><input name="firstname"
									value="<c:out value="${student.firstname}"></c:out>"></input></td>
								<td><spring:errors path="firstname" cssClass="error" /></td>
							</tr>
							<tr>
								<td>Last Name:</td>
								<td><input name="lastname"
									value="<c:out value="${student.lastname}"></c:out>"></input></td>
								<td><spring:errors path="lastname" cssClass="error" /></td>
							</tr>
							<tr>
								<td>User Name:</td>
								<td><input name="username" disabled="disabled"
									value="<c:out value="${student.username}"></c:out>"></input></td>
								<td><spring:errors path="username" cssClass="error" /></td>
							</tr>
							<tr>
								<td>Password:</td>
								<td><input name="password" type="password"
									value="<c:out value="${student.password}"></c:out>"></input></td>
								<td><spring:errors path="password" cssClass="error" /></td>
							</tr>
							<!-- TODO: CS8 - lab 4 - Students to add password confirmation field-->
							<tr>
								<td>Email:</td>
								<td><input name="email"
									value="<c:out value="${student.email}"></c:out>"></input></td>
								<td><spring:errors path="email" cssClass="error" /></td>
							</tr>
							<tr>
								<td>Phone No:</td>
								<td><input name="phoneNo"
									value="<c:out value="${student.phoneNo}"></c:out>"></input></td>
								<td><spring:errors path="phoneNo" cssClass="error" /></td>
							</tr>
							<tr>
								<td>Student Classification:</td>
								<td><spring:select path="classification">
									<spring:options />
								</spring:select></td>
								<td><spring:errors path="classification" cssClass="error" />
								</td>
							</tr>
							<tr>
								<td colspan="2"><input value="Update" type="submit"></td>
							</tr>
						</tbody>
					</table>
					</td>
					<!-- content end -->
				</tr>
			</table>
			</td>
		</tr>
	</table>
</spring:form>

</body>

</html>
