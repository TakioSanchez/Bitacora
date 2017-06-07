<%-- 
    Document   : layoutTemplate
    Created on : Apr 19, 2017, 12:12:12 PM
    Author     : lmarcoss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <jsp:include page="cabecera.jsp" />
    </head>
    <body>
        <%--<jsp:include page='<%=(String) session.getAttribute("menu")%>' />--%>
        <jsp:include page="menu.jsp" />
        <jsp:include page='<%=(String) request.getAttribute("pagina")%>' />
    </body>
    <footer>
        <jsp:include page="footer.jsp" />
    </footer>
</html>
