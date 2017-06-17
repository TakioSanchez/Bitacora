<%-- 
    Document   : nuevoSala
    Created on : Jun 12, 2017, 4:20:43 PM
    Author     : sanchez
--%>
<%@page import="entidades.Persona"%>
<%@page import="java.util.List"%>
<%
    List<Persona> personas = (List<Persona>) request.getAttribute("listaPersonas");
%>

<h3>Registrar Sala</h3>
<form action="/Bitacora/SalaController?action=insertar" method="post" id="formregistro" onsubmit="return validarPersona()">
    <label>Nombre de sala:</label>
    <input type="text" name="nombre_sala" maxlength="60" required="" autocomplete="off">
    <label>Numero de maquinas:</label>
    <input type="text" name="num_maquinas" maxlength="60" required="" autocomplete="off">
    <label>Encargado:</label>
    <select name="id_encargado" required="">
        <option></option>
        <%
            for (Persona persona : personas) {
        %>
        <option value="<%=persona.getId_persona()%>"><%=persona.getNombre_persona() + " " + persona.getApellidos_persona()%></option>
        <%
            }
        %>
    </select>

    <a href="/Bitacora/SalaController?action=listar">Cancelar</a>
    <input class="btn btn-success col-md-5 pull-right" type="submit" id="registrar" value="Guardar">
</form>