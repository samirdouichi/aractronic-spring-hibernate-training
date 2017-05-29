<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<head>
   <title>Welcome to University Registration System </title>
   <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
   <link rel="StyleSheet" href="css/struts2.css" type="text/css"/>
</head>

<body>
<!-- Header begin -->
  <table width="800" height="100%" border="2" bordercolor="#339999">
  
  <tr>
  <td> 
   <div id="header">&nbsp;
     <div align="center"><a href="<c:url value="/"/>"> University Registration System</a></div>
     
     
   </div>
   <!-- header end -->
   <!-- left nav begin -->
   
   <table>
   <tr> 
   <!--content begin -->
   <td width="900"> <div id="content">
  The credentials could not be verified. Unsuccessful Login.  Do you want to <a href="<c:url value="/register.htm"/>">register as a new user</a>?
  <br/>To login to the portal, please go to the <a href="<c:url value="/login.htm"/>">Portal Login Page</a>.
  <p><b>Thank You!</b></p>
  <p>Please contact webmaster, if you have any questions.</p>
  </div></td>
<!-- content end -->
   </tr>
   </table>
 
    </td>
  <!-- content end -->
   </tr>
   </table>
    

</body>

</html>