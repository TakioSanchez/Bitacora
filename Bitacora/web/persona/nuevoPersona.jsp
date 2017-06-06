<%-- 
    Document   : nuevoPersona
    Created on : May 21, 2017, 5:00:37 PM
    Author     : sanchez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar persona</title>
    </head>
    
        
            <h3>Agregar persona</h3>
            <form action="/Bitacora/PersonaController?action=insertar" method="post" id="formregistro" onsubmit="return validarPersona()">
                <fieldset id="user-details">
                <table>
                    <tr>
                        <td style="padding-left: 10px;"><label>Matricula :</label></td>
                        <td style="padding-left: 10px;"><input type="text" name="id_persona" title="Solo letras" maxlength="45" required=""/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label>Nombre :</label></td>
                        <td style="padding-left: 10px;"><input type="text" name="nombre_persona" pattern="[A-Za-z].{3,}[A-Za-z]" title="Solo letras" maxlength="45" required=""/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label>Apellidos :</label></td>
                        <td style="padding-left: 10px;"><input type="text" name="apellidos_persona" pattern="[A-Za-z].{3,}[A-Za-z]" title="Solo letras" maxlength="45" required=""/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><label>Rol :</label></td>
                        <td style="padding-left: 10px;"><select name="rol">
                                <option value="Alumno">Alumno</option>
                                <option value="Administrador">Administrador</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-left: 10px;"><in</td>
                    </tr>
                </table>
            </fieldset>
            <div class="form-group pull-right col-md-11">
                <a href="/Bitacora/PersonaController?action=listar"><input class="btn btn-warning col-md-5 pull-left" type="button" value="Cancelar"/></a>
                <input class="btn btn-success col-md-5 pull-right" type="submit" id="registrar" value="Guardar">
            </div>
            </form>
        
    

