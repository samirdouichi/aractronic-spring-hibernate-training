<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Welcome to University Registration System</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="StyleSheet" href="css/struts2.css" type="text/css" />
<style type="text/css">
<!--
.style1 {
	font-size: 12px
}
-->
</style>
</head>

<body>
<spring:form>
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
					<div align="center"><img src="images/greenhorizontalline.jpg"
						height="5" width="100%" /></div>
					<br />
					<div id="content" align="center">
					<p class="style1">This is the list of available courses</p>
					<table width="96%" border="1" align="center">
						<tr bgcolor="#669966">
							<th scope="col">Session No</th>
							<th scope="col">Course name</th>
							<th scope="col">Department</th>
							<th scope="col">Hours</th>
							<th scope="col">Faculty</th>
							<th scope="col">Term</th>
							<th scope="col">Available Seats</th>
						</tr>
						<c:forEach items="${courseList}" var="courseSession">
							<tr>
								<td><c:out value="${courseSession.id}"></c:out></td>
								<td><c:out value="${courseSession.course.name}"></c:out></td>
								<td><c:out value="${courseSession.course.department}"></c:out></td>
								<td><c:out value="${courseSession.course.creditHours}"></c:out></td>
								<td><c:out value="${courseSession.faculty.userName}"></c:out></td>
								<td><c:out value="${courseSession.term}"></c:out></td>
								<td><c:out
									value="${10 - fn:length(courseSession.students)}"></c:out></td>
							</tr>
						</c:forEach>

						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>

					</div>
					</td>
				</tr>
			</table>
			</td>

		</tr>

	</table>


</spring:form>

</body>

</html>
