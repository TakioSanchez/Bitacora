<%-- 
    Document   : listarAlumnos
    Created on : May 23, 2017, 12:44:22 PM
    Author     : sanchez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alumnos</title>
    </head>
    <body>
        <div>
            <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Matricula</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int i = 0;
                        for (Usuario usuario : usuarios) {
                            out.print("<tr>"
                                    + "<td>" + (i + 1) + "</td>"
                                    + "<td>" + usuario.getEmpleado() + "</a></td>"
                                    + "<td>" + usuario.getNombre_usuario() + "</td>"
                                    + "<td><a class='btn btn-primary' href=\"/aserradero/UsuarioController?action=modificar&nombre_usuario=" + usuario.getNombre_usuario() + "\">Cambiar contraseña</a></td>");
                            if (!usuario.getId_empleado().equals(usuario.getId_jefe())) {
                                out.print("<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/aserradero/UsuarioController?action=eliminar&nombre_usuario=" + usuario.getNombre_usuario() + "';};\">Eliminar</a></td>");
                            } else {
                                out.print("<td>Administrador</td>");
                            }
                            out.print("</tr>");
                            i++;
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
