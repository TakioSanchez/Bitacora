<%-- 
    Document   : actualizarAlumnos
    Created on : Jun 8, 2017, 9:43:37 AM
    Author     : sanchez
--%>

<%@page import="entidades.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Alumno alumno = (Alumno) request.getAttribute("alumno");%>
<div>
    <h3>Modificar datos alumno</h3>
    <form action="/Bitacora/AlumnoController?action=actualizar" method="post" id="formregistro" onsubmit="return validarAlumno()">
        <label>Matricula:</label>
        <input type="hidden" name="id_persona_anterior" value="<%=alumno.getMatricula()%>" title="Solo letras" maxlength="45" required="" autocomplete="off" readonly="">
        <input type="text" name="id_persona" value="<%=alumno.getMatricula()%>" title="Solo letras" maxlength="45" required="" autocomplete="off">

        <label>Nombre:</label>
        <input type="text" name="nombre_persona" value="<%=alumno.getNombre_persona()%>" maxlength="60" required="" autocomplete="off">

        <label>Apellidos:</label>
        <input type="text" name="apellidos_persona" value="<%=alumno.getApellidos_persona()%>" maxlength="60" required="" autocomplete="off">
        
        <label>Carrera:</label>
        <input type="text" name="carrera" value="<%=alumno.getCarrera()%>" maxlength="60" required="" autocomplete="off">
        
        <label>Semestre:</label>
        <input type="text" name="semestre" value="<%=alumno.getSemestre()%>" maxlength="60" required="" autocomplete="off">
        
        <label>Grupo:</label>
        <input type="text" name="grupo" value="<%=alumno.getGrupo()%>" maxlength="60" required="" autocomplete="off">
        
        <a href="/Bitacora/AlumnoController?action=listar">Cancelar</a>
        <input class="btn btn-success col-md-5 pull-right" type="submit" id="registrar" value="Guardar">
    </form>    
</div>
