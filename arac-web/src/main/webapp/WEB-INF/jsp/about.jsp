<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Welcome to University Registration System using Spring 3</title>
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
<!-- Header begin -->
<table width="800" height="100%" border="2" bordercolor="#339999">
	<tr>
		<td>
		<div id="header">&nbsp;
		<div align="center"><a href="<c:url value="/home"/>">University
		Registration System</a></div>
		</div>
		<!-- header end -->
		<table>
			<tr>
				<td width="100%">
				<table align="right" cellpadding="2">
					<tr>
						<td width="90">
						<div id="menu" align="center"><a
							href="<c:url value="/login.htm"/>"> Logout </a></div>
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
				<!--content begin -->
				<td width="900">
				<div id="content">This simple static page was configured with
				the &lt;mvc:controller /&gt; tag.</div>
				</td>
				<!-- content end -->
			</tr>
		</table>
		</td>
	</tr>
</table>


</body>

</html>
