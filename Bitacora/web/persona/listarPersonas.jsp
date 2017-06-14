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

<div>
    <div>
        <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>N°</th>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Rol</th>
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
                                + "<td><a class='btn btn-warning' href=\"/Bitacora/PersonaController?action=modificar&id_persona=" + persona.getId_persona() + "\">Modificar</a></td>"
                                + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/Bitacora/PersonaController?action=eliminar&id_persona=" + persona.getId_persona() + "';};\">Eliminar</a></td>" + "</tr>");
                        i++;
                    }%>
            </tbody>
        </table>
        <div class="agregar_element">
            <input type="button" class="btn btn-primary" value="Agregar persona" onClick=" window.location.href = '/Bitacora/PersonaController?action=nuevo'">
        </div>
    </div>
</div>