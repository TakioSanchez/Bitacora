<%-- 
    Document  : nuevoPersona
    Created on: May 21, 2017, 5:00:37 PM
    Author    : sanchez
--%>

<h3>Registrar persona</h3>
<form action="/Bitacora/PersonaController?action=insertar" method="post" id="formregistro" onsubmit="return validarPersona()">
    <label>Matricula:</label>
    <input type="text" name="id_persona" title="Solo letras" maxlength="15" required="" autocomplete="off">
    <label>Nombre:</label>
    <input type="text" name="nombre_persona" maxlength="60" required="" autocomplete="off">
    <label>Apellidos:</label>
    <input type="text" name="apellidos_persona" maxlength="60" required="" autocomplete="off">
    <label>Rol:</label>
    <select name="rol">
        <option value="Alumno">Alumno</option>
        <option value="Administrador">Administrador</option>
    </select>

    <a href="/Bitacora/PersonaController?action=listar">Cancelar</a>
    <input class="btn btn-success col-md-5 pull-right" type="submit" id="registrar" value="Guardar">
</form>