<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>

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
							<div id="menu" align="center"><a href="#"> Course
							Catalog </a></div>
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
					<table cellpadding="5" width="100%">
						<tr>
							<td>
							<div align="center" id="content">
							<h3>Welcome to portal page....</h3>
							</div>
							<table cellpadding="4" border="1">
								<tr>
									<td width="900">
									<div id="content" align="left">
									<p class="style1"><strong>Your personal
									information</strong> is below. To change your information, <a
										href="<c:url value="/updatemyinfo.htm?${student.studentid}"/>">click
									here </a></p>
									<p class="style1">Username/Password: <c:out
										value="${student.username}/${student.password}"></c:out></p>
									<p class="style1">Student ID: <c:out
										value="${student.studentid}"></c:out></p>
									<p class="style1">Email: <c:out value="${student.email}"></c:out></p>
									<p class="style1">Phone No: <c:out
										value="${student.phoneNo}"></c:out></p>
									</div>
									</td>
								</tr>
							</table>
							<br />
							<div align="center"><img
								src="images/greenhorizontalline.jpg" height="5" width="100%" />
							</div>
							<br />
							<!--<table width="96%" border="1" align="center">
							<tr>
							<td>
								<p class="content1"><strong>
								 To update courses information, please</strong> 
								 <s:url id="url"
											action="adminAction.action">
											 <s:param name="courselink" value="true"></s:param>
								 			 <s:param name="studentlink" value="false"></s:param>
									 </s:url> <s:a href="%{url}">Click here</s:a>
								 </p>
								 <p class="content1"><strong>
								 To get external vendor courses, please</strong> 
								 <s:a href="extVendorAction.action">
									Click here </s:a>
								 </p>
								</td>
								</tr>
							</table>
							--></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</spring:form>

</body>

</html>
