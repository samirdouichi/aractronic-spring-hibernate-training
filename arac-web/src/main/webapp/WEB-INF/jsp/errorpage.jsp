<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<head>
<title>Welcome to University Registration System</title>
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
		<div align="center"><a href="<c:url value="/home"/>">
		University Registration System</a></div>
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
				<h4 class="style1">You cannot register for this course. Unsuccessful Register.</h4>
				<br />
				<a href="<c:url value="/login.htm"/>">Please Login again </a></div>
				</td>
				<!-- content end -->
			</tr>
		</table>
		</td>
	</tr>
</table>

</body>

</html>
