<%-- 
    Document   : listarRegistros
    Created on : 13/06/2017, 09:54:12 PM
    Author     : lmarcoss
--%>
<%@page import="java.util.List"%>
<%@page import="entidades.Registro"%>
<%
    List<Registro> listaRegistros = (List<Registro>) request.getAttribute("listaRegistros");
%>
<div>
    <div>
        <table id="tabla" class="display cell-border" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>N°</th>
                    <th>Sala</th>
                    <th>Equipo</th>
                    <th>Matricula</th>
                    <th>Alumno</th>
                    <th>Fecha</th>
                    <th>Hora entrada</th>
                    <th>Hora salida</th>
                    <th>Observaciones</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    int i = 0;
                    for (Registro registro : listaRegistros) {
                        out.print("<tr>" + "<td>" + (i + 1)
                                + "<td>" + registro.getId_sala() + "</td>"
                                + "<td>" + registro.getNum_equipo() + "</td>"
                                + "<td>" + registro.getMatricula() + "</td>"
                                + "<td>" + registro.getNombre_completo() + "</td>"
                                + "<td>" + registro.getFecha() + "</td>"
                                + "<td>" + registro.getHora_entrada() + "</td>"
                                + "<td>" + registro.getHora_salida() + "</td>"
                                + "<td><p>" + registro.getObservaciones() + "</p></td>"
                                + "<td><a class='btn btn-warning' href=\"/Bitacora/RegistroController?action=modificar&id_registro=" + registro.getId_registro() + "\">Modificar</a></td>"
                                + "<td><a class='btn btn-danger' href=\"javascript:if (confirm('¿Estás seguro de eliminar?')){parent.location='/Bitacora/RegistroController?action=eliminar&id_registro=" + registro.getId_registro() + "';};\">Eliminar</a></td>" + "</tr>");
                        i++;
                    }%>
            </tbody>
        </table>
        <div class="agregar_element">
            <input type="button" class="btn btn-primary" value="Agregar registro" onClick=" window.location.href = '/Bitacora/RegistroController?action=nuevo'">
        </div>
    </div>
</div>
