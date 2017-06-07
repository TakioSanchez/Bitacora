<%-- 
    Document   : cabecera
    Created on : Apr 20, 2017, 9:46:05 PM
    Author     : lmarcoss
--%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="" />
<%
    String titulo = (String) request.getAttribute("titulo");
    if (titulo != null) {
%>
<title><%=titulo%></title>
<%} else {%>
<title>Bitácora</title>
<%}%>



<!--enlaces de css--> 