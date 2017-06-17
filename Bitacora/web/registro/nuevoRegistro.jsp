<%-- 
    Document   : nuevoRegistro
    Created on : 13/06/2017, 09:53:48 PM
    Author     : lmarcoss
--%>
<%@page import="java.util.List"%>
<%@page import="entidades.Sala"%>
<%
    List<Sala> listaSalas = (List<Sala>) request.getAttribute("listaSalas");
%>
<h3>Nuevo Registro</h3>
<form action="/Bitacora/RegistroController?action=insertar" method="post" id="formregistro">
    <label>Sala</label>
    <select name="id_sala" required="">
        <option></option>
        <%
            for (Sala sala : listaSalas) {
        %>
        <option value="<%=sala.getId_sala()%>"><%=sala.getNombre_sala()%></option>
        <%
            }
        %>
    </select>

    <select name="num_maquinas" required="" onchange="establecerNumMaxEquipo()" style="display: none;">
        <option></option>
        <%
            for (Sala sala : listaSalas) {
        %>
        <option value="<%=sala.getNum_maquinas()%>"><%=sala.getNum_maquinas()%></option>
        <%
            }
        %>
    </select>

    <label>Equipo</label>
    <input type="number" name="num_equipo" id="num_equipo" step="1" required="" autocomplete="off">

    <label>Matricula:</label>
    <input type="text" name="matricula" maxlength="15" required="" autocomplete="off">
    <label>fecha:</label>
    <input type="date" name="fecha" required="" autocomplete="off">
    <label>hora entrada:</label>
    <input type="time" name="hora_entrada" required="" autocomplete="off">
    <input type="time" name="hora_salida" required="" autocomplete="off">
    <label>Observaciones:</label>
    <textarea maxlength="255"></textarea>
    <a href="/Bitacora/RegistroController?action=listar">Cancelar</a>
    <input class="btn btn-success col-md-5 pull-right" type="submit" id="registrar" value="Guardar">
</form>
