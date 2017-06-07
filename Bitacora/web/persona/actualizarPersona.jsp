<%-- 
    Document   : actualizarPersona
    Created on : Jun 6, 2017, 10:40:26 PM
    Author     : sanchez
--%>

<%@page import="entidades.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Persona persona = (Persona) request.getAttribute("persona");%>
<div>
    <h3>Modificar datos persona</h3>
    <form action="/Bitacora/PersonaController?action=actualizar" method="post" id="formregistro" onsubmit="return validarPersona()">
        <label>Matricula:</label>
        <input type="hidden" name="id_persona_anterior" value="<%=persona.getId_persona()%>" title="Solo letras" maxlength="45" required="" autocomplete="off" readonly="">
        <input type="text" name="id_persona" value="<%=persona.getId_persona()%>" title="Solo letras" maxlength="45" required="" autocomplete="off">

        <label>Nombre:</label>
        <input type="text" name="nombre_persona" value="<%=persona.getNombre_persona()%>" maxlength="60" required="" autocomplete="off">

        <label>Apellidos:</label>
        <input type="text" name="apellidos_persona" value="<%=persona.getApellidos_persona()%>" maxlength="60" required="" autocomplete="off">

        <label>Rol:</label>
        <select name="rol">
            <%if (persona.getRol().equals("Alumno")) {%>
            <option selected="" value="Alumno">Alumno</option>
            <option value="Administrador">Administrador</option>
            <%} else {%>
            <option value="Alumno">Alumno</option>
            <option selected="" value="Administrador">Administrador</option>
            <%}%>
        </select>

        <a href="/Bitacora/PersonaController?action=listar">Cancelar</a>
        <input class="btn btn-success col-md-5 pull-right" type="submit" id="registrar" value="Guardar">
    </form>    
</div>
