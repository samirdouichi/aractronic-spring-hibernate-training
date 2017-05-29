<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<head>
<title>Unregistration Confirmed</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
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
				<h3>Congratulations!!</h3>
				<p class="style1">You have successfully <b>un-registered</b>
				from ${courseSession.course.name}.</p>
				</div>
				</td>
				<!-- content end -->
			</tr>
		</table>
		</td>
	</tr>
</table>

</body>

</html>
