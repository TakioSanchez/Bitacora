<%-- 
    Document   : listarPersonas
    Created on : May 23, 2017, 12:28:09 PM
    Author     : sanchez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personas</title>
    </head>
    <body>
        <div>
            <form method="post" action="/bitacora/PersonaController?action=buscar">
                <table>
                    <tr>
                        <td>
                            <select>
                                <option value=""></option>
                            </select>
                        </td>
                    </tr>
                </table>                
            </form>
        </div>
    </body>
</html>
