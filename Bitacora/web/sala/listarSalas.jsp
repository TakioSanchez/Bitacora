<%-- 
    Document   : listarSalas
    Created on : Jun 12, 2017, 4:05:45 PM
    Author     : sanchez
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.Sala"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Sala> salas = (List<Sala>) request.getAttribute("listaSalas");
%>

<div>
    <div>
        <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>N°</th>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Num de equipos</th>
                    <th>Encargado</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    int i = 0;
                    for (Sala sala : salas) {
                        out.print("<tr>" + "<td>" + (i + 1)
                                + "<td>" + sala.getId_sala() + "</td>"
                                + "<td>" + sala.getNombre_sala() + "</td>"
                                + "<td>" + sala.getNum_maquinas()+ "</td>"
                                + "<td>" + sala.getId_encargado() + "</td>"
                                + "<td>" + sala.getNombre_persona()+ "</td>"
                                + "<td>" + sala.getApellidos_persona()+ "</td>"
                                + "<td><a class='btn btn-warning' href=\"/Bitacora/SalaController?action=modificar&id_sala=" + sala.getId_sala() + "\">Modificar</a></td>"
                                + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/Bitacora/SalaController?action=eliminar&id_sala=" + sala.getId_sala() + "';};\">Eliminar</a></td>" + "</tr>");
                        i++;
                    }%>
            </tbody>
        </table>
        <div class="agregar_element">
            <input type="button" class="btn btn-primary" value="Agregar sala" onClick=" window.location.href = '/Bitacora/SalaController?action=nuevo'">
        </div>
    </div>
</div>