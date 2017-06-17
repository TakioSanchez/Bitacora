package dao;

import entidades.Registro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmarcoss
 */
public class RegistroCRUD extends Conexion {

    public void registrarRegistro(Registro registro) throws Exception {
        String Consulta = "INSERT INTO registro (id_registro,id_sala, num_equipo, matricula, fecha, hora_entrada, hora_salida, observaciones) VALUES (?,?,?,?,?,?,?,?)";
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(Consulta);
            st = cargarRegistro(st, registro);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public PreparedStatement cargarRegistro(PreparedStatement st, Registro registro) throws SQLException {
        int contador = 0;
        st.setInt(++contador, registro.getId_registro());
        st.setInt(++contador, registro.getId_sala());
        st.setInt(++contador, registro.getNum_equipo());
        st.setString(++contador, registro.getMatricula());
        st.setDate(++contador, registro.getFecha());
        st.setTime(++contador, registro.getHora_entrada());
        st.setTime(++contador, registro.getHora_salida());
        st.setString(++contador, registro.getObservaciones());
        return st;
    }

    public <T> List listarRegistro() throws Exception {
        List<Registro> registros;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM registro")) {
                registros = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Registro registro = (Registro) extraerRegistro(rs);
                        registros.add(registro);
                    }
                }
            } catch (Exception e) {
                registros = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return registros;
    }

    public Registro extraerRegistro(ResultSet rs) throws SQLException {
        Registro registro = new Registro();
        registro.setId_registro(rs.getInt("id_registro"));
        registro.setId_sala(rs.getInt("id_sala"));
        registro.setNum_equipo(rs.getInt("num_equipo"));
        registro.setMatricula(rs.getString("matricula"));
        registro.setFecha(rs.getDate("fecha"));
        registro.setHora_entrada(rs.getTime("hora_entrada"));
        registro.setHora_salida(rs.getTime("hora_salida"));
        registro.setObservaciones(rs.getString("observaciones"));
        return registro;
    }

    public Registro buscarRegistro(int id_registro) throws Exception {
        Registro registro = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM registro WHERE id_registro = ?")) {
            st.setInt(1, id_registro);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    registro = extraerRegistro(rs);
                }
            }
        }
        return registro;
    }

    public void actualizarRegistro(Registro registro) throws Exception {
        int contador = 0;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "UPDATE registro SET id_sala = ?, num_equipo = ?, matricula = ?, fecha = ?, hora_entrada = ?, hora_salida = ?, observaciones = ? WHERE id_registro = ?");
            st.setInt(++contador, registro.getId_sala());
            st.setInt(++contador, registro.getNum_equipo());
            st.setString(++contador, registro.getMatricula());
            st.setDate(++contador, registro.getFecha());
            st.setTime(++contador, registro.getHora_entrada());
            st.setTime(++contador, registro.getHora_salida());
            st.setString(++contador, registro.getObservaciones());
            st.setInt(++contador, registro.getId_registro());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public void eliminarRegistro(int id_registro) throws Exception {
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM registro WHERE id_registro = ?");
            st.setInt(1, id_registro);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

}
