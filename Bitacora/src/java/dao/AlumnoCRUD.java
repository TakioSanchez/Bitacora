package dao;

import entidades.Alumno;
import entidades.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sanchez
 */
public class AlumnoCRUD extends Conexion{
    
    public int registrarAlumno(Alumno alumno) throws Exception {
        String Consulta = "SELECT insertar_alumno (?,?,?,?);";
        int resultado = -999;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(Consulta);
            st = cargarAlumno(st, alumno);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    resultado = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return resultado;
    }
    
    public PreparedStatement cargarAlumno(PreparedStatement st, Alumno alumno) throws SQLException {
        st.setString(1, alumno.getMatricula());
        st.setString(2, alumno.getCarrera());
        st.setString(3, alumno.getSemestre());
        st.setString(4, alumno.getGrupo());
        return st;
    }
    
    public <T> List listarAlumno() throws Exception {
        List<Alumno> alumnos;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_ALUMNOS")) {
                alumnos = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Alumno alumno = (Alumno) extraerAlumno(rs);
                        alumnos.add(alumno);
                    }
                }
            } catch (Exception e) {
                alumnos = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return alumnos;
    }
    
    public Alumno extraerAlumno(ResultSet rs) throws SQLException {
        Alumno alumno = new Alumno();
        alumno.setMatricula(rs.getString("id_persona"));
        alumno.setNombre_persona(rs.getString("nombre_persona"));
        alumno.setApellidos_persona(rs.getString("apellidos_persona"));
        alumno.setCarrera(rs.getString("carrera"));
        alumno.setSemestre(rs.getString("semestre"));
        alumno.setGrupo(rs.getString("grupo"));
        return alumno;
    }
    
    public Alumno buscarAlumno(String id_persona) throws Exception {
        Alumno alumno = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM alumno WHERE matricula = ?")) {//uso de vista??
            st.setString(1, id_persona);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    alumno = extraerAlumno(rs);
                }
            }
        }
        return alumno;
    }
    
    public void actualizarAlumno(Alumno alumno) throws Exception {
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "SELECT actualizar_alumno(?,?,?,?,?,?,?);");
            st.setString(1, alumno.getMatricula());
            st.setString(2, alumno.getMatricula_anterior());
            st.setString(3, alumno.getNombre_persona());
            st.setString(4, alumno.getApellidos_persona());
            st.setString(5, alumno.getCarrera());
            st.setString(6, alumno.getSemestre());
            st.setString(7, alumno.getGrupo());
            st.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }
    
    public void eliminarAlumno(String matricula) throws Exception {
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM persona WHERE id_persona = ?");
            st.setString(1, matricula);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }
    
    public Object modificarAlumno(Object objeto) throws Exception {
        Alumno alumnoM = (Alumno) objeto;
        Alumno alumno = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM alumno WHERE matricula = ? ")) {
            st.setString(1, alumnoM.getMatricula());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    alumno = (Alumno) extraerAlumno(rs);
                }
            }
        }
        return alumno;
    }
    
    public Object buscarAlumno(Object objeto) throws Exception {
        Alumno alumnoM = (Alumno) objeto;
        Alumno alumno = null;
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM VISTA_ALUMNOS WHERE id_persona = ? ")) {
            st.setString(1, alumnoM.getMatricula());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    alumno = (Alumno) extraerAlumno(rs);
                }
            }
        }
        return alumno;
    }    
}
