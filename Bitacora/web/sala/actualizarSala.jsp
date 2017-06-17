<%-- 
    Document   : actualizarSala
    Created on : Jun 17, 2017, 4:47:49 PM
    Author     : sanchez
--%>

<%@page import="entidades.Persona"%>
<%@page import="java.util.List"%>
<%@page import="entidades.Sala"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Sala sala = (Sala) request.getAttribute("sala");%>
<%
    List<Persona> personas = (List<Persona>) request.getAttribute("listaPersonas");
%>

<div>
    <h3>Modificar datos sala</h3>
    <form action="/Bitacora/SalaController?action=actualizar" method="post" id="formregistro" onsubmit="return validarPersona()">
        <label>id_sala:</label>
        <input type="text" readonly=""  name="id_sala" value="<%=sala.getId_sala()%>" title="Solo letras" maxlength="45" required="" autocomplete="off" >

        <label>Nombre:</label>
        <input type="text" name="nombre_sala" value="<%=sala.getNombre_sala()%>" maxlength="60" required="" autocomplete="off">

        <label>numero de maquinas:</label>
        <input type="text" name="num_maquinas" value="<%=sala.getNum_maquinas()%>" maxlength="60" required="" autocomplete="off">

        <label>encargado:</label>
        <select name="id_encargado">
            
            <% for (Persona persona : personas){ %>
            <% if(persona.getId_persona() == sala.getId_encargado()){%>
            <option selected="" value="<%=persona.getId_persona()%>"><%=persona.getNombre_persona() + " " + persona.getApellidos_persona()%></option>
            <% }else{%>
            <option value="<%=persona.getId_persona()%>"><%=persona.getNombre_persona() + " " + persona.getApellidos_persona()%></option>
            <%}%>
            <%}%>
        </select>

        <a href="/Bitacora/PersonaController?action=listar">Cancelar</a>
        <input class="btn btn-success col-md-5 pull-right" type="submit" id="registrar" value="Guardar">
    </form>    
</div>
