<%-- 
    Document   : listarAlumnos
    Created on : Jun 7, 2017, 12:47:38 PM
    Author     : sanchez
--%>

<%@page import="entidades.Persona"%>
<%@page import="entidades.Alumno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Alumno> alumnos = (List<Alumno>) request.getAttribute("listaAlumnos");
%>
<div>
    <div>
        <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>N°</th>
                    <th>Matricula</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Carrera</th>
                    <th>Semestre</th>
                    <th>Grupo</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    int i = 0;
                    for (Alumno alumno : alumnos) {
                        out.print("<tr>" + "<td>" + (i + 1)
                                + "<td>" + alumno.getMatricula()+ "</td>"
                                + "<td>" + alumno.getNombre_persona()+ "</td>"
                                + "<td>" + alumno.getApellidos_persona()+ "</td>"
                                + "<td>" + alumno.getCarrera()+ "</td>"
                                + "<td>" + alumno.getSemestre()+ "</td>"
                                + "<td>" + alumno.getGrupo()+ "</td>"
                                + "<td><a class='btn btn-warning' href=\"/Bitacora/AlumnoController?action=modificar&matricula=" + alumno.getMatricula()+ "\">Modificar</a></td>"
                                + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/Bitacora/AlumnoController?action=eliminar&matricula=" + alumno.getMatricula()+ "';};\">Eliminar</a></td>" + "</tr>");
                        i++;
                    }%>
            </tbody>
        </table>
        <div class="agregar_element">
            <input type="button" class="btn btn-primary" value="Agregar alumno" onClick=" window.location.href = '/Bitacora/AlumnoController?action=nuevo'">
        </div>
    </div>
</div>
