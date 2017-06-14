<%-- 
    Document   : nuevoSala
    Created on : Jun 12, 2017, 4:20:43 PM
    Author     : sanchez
--%>

<h3>Registrar Sala</h3>
<form action="/Bitacora/SalaController?action=insertar" method="post" id="formregistro" onsubmit="return validarPersona()">
    <label>Nombre de sala:</label>
    <input type="text" name="nombre_sala" maxlength="60" required="" autocomplete="off">
    <label>Numero de maquinas:</label>
    <input type="text" name="num_maquinas" maxlength="60" required="" autocomplete="off">
    <label>Id_encargado:</label>
    <input type="text" name="id_encargado" maxlength="60" required="" autocomplete="off">
    <label>Nombre:</label>
    <input type="text" name="nombre_persona" maxlength="60" required="" autocomplete="off">
    <label>Apellidos:</label>
    <input type="text" name="apellidos_persona" maxlength="60" required="" autocomplete="off">    

    <a href="/Bitacora/SalaController?action=listar">Cancelar</a>
    <input class="btn btn-success col-md-5 pull-right" type="submit" id="registrar" value="Guardar">
</form>