<%-- 
    Document   : listarPersonas
    Created on : May 23, 2017, 12:28:09 PM
    Author     : sanchez
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Persona> personas = (List<Persona>) request.getAttribute("listaPersonas");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personas</title>
    </head>
    <body>
        <div>
            <div class="panel-body">
                <div class="mostrar_ocultar">
                    <!-- input type="button" id="mostrar_ocultar" class="btn btn-warning" name="mostrar_ocultar" value="Modificar/eliminar"-->
                </div>
                <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>N°</th>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                            <th>Rol</th>
                            <th>Sexo</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int i = 0;
                            for (Persona persona : personas) {
                                out.print("<tr>" + "<td>" + (i + 1)
                                        + "<td>" + persona.getId_persona() + "</td>"
                                        + "<td>" + persona.getNombre_persona() + "</td>"
                                        + "<td>" + persona.getApellidos_persona() + "</td>"
                                        + "<td>" + persona.getRol() + "</td>"
                                        + "<td><a class='btn btn-warning' href=\"/aserradero/PersonaController?action=modificar&id_persona=" + persona.getId_persona() + "\">Modificar</a></td>"
                                        + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/PersonaController?action=eliminar&id_persona=" + persona.getId_persona() + "';};\">Eliminar</a></td>" + "</tr>");
                                i++;
                            }%>
                    </tbody>
                </table>
                <div class="agregar_element">
                    <input type="button" class="btn btn-primary" value="Agregar persona" onClick=" window.location.href = '/Bitacora/persona/nuevoPersona.jsp'">
                </div>
            </div>
        </div>
    </body>
</html>
