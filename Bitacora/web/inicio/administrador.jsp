<%-- 
    Document   : administrador
    Created on : May 21, 2017, 3:55:00 PM
    Author     : sanchez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio Administrador</title>
    </head>
    <body>
        <h3>Administrador</h3>
        <a href="/Bitacora/PersonaController?action=listar"><label>Personas</label></a>
        <br>
        <a href="/Bitacora/AlumnoController?action=listar"><label>Alumnos</label></a>
        <br>
        <a href="Bitacora/SalaController?action=listar"><label>Salas</label></a>
        <br>
        <a href="/Bitacora/RegistroController?action=listar"><label>Registros</label></a>
    </body>
</html>
