<%-- 
    Document   : actualizarRegistro
    Created on : 13/06/2017, 09:54:01 PM
    Author     : lmarcoss
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.Registro"%>
<%@page import="entidades.Sala"%>
<%
    List<Sala> listaSalas = (List<Sala>) request.getAttribute("listaSalas");
    Registro registro = (Registro) request.getAttribute("registro");
%>
<h3>Nuevo Registro</h3>
<form action="/Bitacora/RegistroController?action=actualizar&id_registro=<%=registro.getId_registro()%>" method="post" id="formregistro">
    <label>Sala</label>
    <select name="id_sala" required="">
        <option></option>
        <%
            for (Sala sala : listaSalas) {
                if (sala.getId_sala() == registro.getId_sala()) {%>
        <option selected="" value="<%=sala.getId_sala()%>"><%=sala.getNombre_sala()%></option>
        <%
        } else {%>
        <option value="<%=sala.getId_sala()%>"><%=sala.getNombre_sala()%></option>
        <%}
            }
        %>
    </select>

    <select name="num_maquinas" onchange="establecerNumMaxEquipo()" style="display: none;">
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
    <input type="number" name="num_equipo" id="num_equipo" value="<%=registro.getNum_equipo()%>" step="1" required="" autocomplete="off">

    <label>Matricula:</label>
    <input type="text" name="matricula" value="<%=registro.getMatricula()%>" maxlength="15" required="" autocomplete="off">
    <label>fecha:</label>
    <input type="date" name="fecha" value="<%=registro.getFecha()%>"required="" autocomplete="off">
    <label>hora entrada:</label>
    <input type="datetime" name="hora_entrada" value="<%=registro.getHora_entrada()%>" required="" autocomplete="off">
    <input type="datetime" name="hora_salida" value="<%=registro.getHora_salida()%>" required="" autocomplete="off">
    <label>Observaciones:</label>
    <textarea name="observaciones" maxlength="255"><%=registro.getObservaciones()%></textarea>

    <a href="/Bitacora/RegistroController?action=listar">Cancelar</a>
    <input type="submit" id="registrar" value="Guardar">
</form>
