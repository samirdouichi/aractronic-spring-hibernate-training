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
<spring:form name="portalform" method="GET">
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
								href="<c:url value="/logout"/>">Logout </a></div>
							</td>
							<td width="160">
							<div id="menu" align="center"><a
								href="<c:url value="/catalog.htm"/>">Course Catalog </a></div>
							</td>
							<td width="90">
							<div id="menu" align="center"><a
								href="<c:url value="/about.htm"/>">About</a><br />
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="900">
					<table cellpadding="5" width="100%">
						<tr>
							<td>
							<div align="center" id="content">
							<h3>My Portal Page: Welcome <c:out
								value="${student.firstName}"></c:out> to your portal page.....</h3>
							</div>
							<table cellpadding="4" border="1">
								<tr>
									<td width="900">
									<div id="coursesbody" align="left">
									<p class="style1"><strong>Your personal
									information</strong> is below. To change your information, <a
										href="<c:url value="/updateinfo.htm"/>">click here </a></p>
									<p class="style1"><b>Name:</b> <c:out
										value="${student.firstName}" /> <c:out
										value="${student.lastName}" /></p>
									<p class="style1"><b>Username:</b> <c:out
										value="${student.userName}"></c:out></p>
									<p class="style1"><b>Student ID:</b> <c:out
										value="${student.id}"></c:out></p>
									<p class="style1"><b>Email:</b> <c:out
										value="${student.email}"></c:out></p>
									<p class="style1"><b>Phone No:</b> <c:out
										value="${student.phoneNumber}"></c:out></p>
									<p class="style1"><b>Classification:</b> <c:out
										value="${student.classification}"></c:out></p>
									</div>
									</td>
								</tr>
							</table>
							<br />
							<div align="center"><img
								src="images/greenhorizontalline.jpg" height="5" width="100%" />
							</div>
							<div id="content" align="center">
							<p class="content1"><strong>To drop the courses you
							have already registered, please unregister from below table.</strong></p>
							<table width="96%" border="1" align="center">
								<tr bgcolor="#66CC99">
									<th scope="col">Sl. No</th>
									<th scope="col">Course name</th>
									<th scope="col">Department</th>
									<th scope="col">Hours</th>
									<th scope="col">Faculty</th>
									<th scope="col">Term</th>
									<th scope="col">Action</th>
								</tr>
									<%
										int i = 1;
									%>
									<c:forEach items="${student.registeredCourses}"
										var="courseSession">
										<tr align="center">
											<td><%=i%></td>
											<td><c:out value="${courseSession.course.name}"></c:out></td>
											<td><c:out value="${courseSession.course.department}"></c:out></td>
											<td><c:out value="${courseSession.course.creditHours}"></c:out></td>
											<td><c:out value="${courseSession.faculty.userName}"></c:out></td>
											<td><c:out value="${courseSession.term}"></c:out></td>
											<td width="100px" bgcolor="#CCCC99"><a
												href="<c:url value="/courseunreg.htm?courseSessionId=${courseSession.id}"/>">UnRegister</a>
											<%
												i = i + 1;
											%>
											</td>
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


			<div align="center"><img src="images/greenhorizontalline.jpg"
				height="5" width="100%" /></div>
			<br />
			<div id="content" align="center">
			<p class="content1"><strong>To enroll for new courses,
			please register using the course catalog below.</strong></p>
			<table width="96%" border="1" align="center">
				<tr bgcolor="#669966">
					<th scope="col">Session No</th>
					<th scope="col">Course name</th>
					<th scope="col">Department</th>
					<th scope="col">Hours</th>
					<th scope="col">Faculty</th>
					<th scope="col">Term</th>
					<th scope="col">Available Seats</th>
					<th scope="col">Action</th>
				</tr>
				<c:forEach items="${courseList}" var="courseSession">
					<tr>
						<td><c:out value="${courseSession.id}"></c:out></td>
						<td><c:out value="${courseSession.course.name}"></c:out></td>
						<td><c:out value="${courseSession.course.department}"></c:out></td>
						<td><c:out value="${courseSession.course.creditHours}"></c:out></td>
						<td><c:out value="${courseSession.faculty.userName}"></c:out></td>
						<td><c:out value="${courseSession.term}"></c:out></td>
						<td style="text-align:center"><c:out value="${10 - fn:length(courseSession.students)}"></c:out></td>
						<td><a
							href="<c:url value="/coursereg.htm?courseSessionId=${courseSession.id}"/>">Register</a></td>
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
					<td>&nbsp;</td>
				</tr>
			</table>

			</div>
			</td>
		</tr>
	</table>

</spring:form>

</body>

</html>
