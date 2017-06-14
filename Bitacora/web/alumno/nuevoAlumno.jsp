<%-- 
    Document   : nuevoAlumno
    Created on : May 21, 2017, 4:59:02 PM
    Author     : sanchez
--%>

<h3>Registrar alumno</h3>
<form action="/Bitacora/AlumnoController?action=insertar" method="post" id="formregistro" onsubmit="return validarAlumno()">
    <label>Matricula:</label>
    <input type="text" name="id_persona" title="Solo letras" maxlength="15" required="" autocomplete="off">
    <label>Nombre:</label>
    <input type="text" name="nombre_persona" maxlength="60" required="" autocomplete="off">
    <label>Apellidos:</label>
    <input type="text" name="apellidos_persona" maxlength="60" required="" autocomplete="off">
    <input type="hidden" name="rol" value="Alumno">
    <label>Carrera:</label>
    <input type="text" name="carrera" maxlength="15" required="" autocomplete="off">
    <label>Semestre:</label>
    <input type="text" name="semestre" maxlength="15" required="" autocomplete="off">
    <label>Grupo:</label>
    <input type="text" name="grupo" maxlength="10" required="" autocomplete="off">

    <a href="/Bitacora/AlumnoController?action=listar">Cancelar</a>
    <input class="btn btn-success col-md-5 pull-right" type="submit" id="registrar" value="Guardar">
</form>
