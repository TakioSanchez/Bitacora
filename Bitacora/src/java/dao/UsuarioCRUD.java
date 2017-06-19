package dao;

import entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmarcoss password = "4@*" + usuario.contrasenia + "&#+"; // Clave de
 * Seguridad para contraseña
 */
public class UsuarioCRUD extends Conexion {

    private final String clave1 = "UnS15";
    private final String clave2 = "51SnU";

    // ('COHA820724HOCRNN02COHA8207','Antonio',concat('$|2A',sha2('^4%m@C*antonio&%c#L+=',224),'!T!A0'));
    public void registrar(Usuario usuario) throws Exception {
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO USUARIO (nombre_usuario,contrasenia,metodo) VALUES (?,sha2(concat(?,?,?),224),?)");
            st.setString(1, usuario.getNombre_usuario());
            st.setString(2, clave1);
            st.setString(3, usuario.getContrasenia());
            st.setString(4, clave2);
            st.setString(5, usuario.getMetodo());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public List<Usuario> listar(String id_jefe) throws Exception {
        List<Usuario> usuarios;
        String consulta = "SELECT * FROM vista_usuarios";
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                usuarios = new ArrayList();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Usuario usuario = extraerUsuario(rs);
                        usuarios.add(usuario);
                    }
                }
            } catch (Exception e) {
                usuarios = null;
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return usuarios;
    }

    public Usuario buscar(String nom_usuario) throws Exception {
        Usuario usuario = null;
        String consulta = "SELECT * FROM usuario WHERE nombre_usuario = ?";
        this.abrirConexion();
        try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
            st.setString(1, nom_usuario);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    usuario = new Usuario(rs.getString("nombre_usuario"), rs.getString("contrasenia"), rs.getString("metodo"));
                }
            }
        }
        return usuario;
    }

    public void actualizar(Usuario usuario) throws Exception {
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE usuario SET contrasenia = sha2(concat(?,?,?),224) WHERE nombre_usuario = ?");
            st.setString(2, clave1);
            st.setString(3, usuario.getContrasenia());
            st.setString(4, clave2);
            st.setString(5, usuario.getNombre_usuario());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public void eliminar(String nombre_usuario) throws Exception {
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM usuario WHERE nombre_usuario = ?");
            st.setString(1, nombre_usuario);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    public Usuario validarUsuario(String nombre_usuario, String contrasenia) throws Exception {
        String consulta = "SELECT * FROM vista_usuarios WHERE nombre_usuario =  ? AND contrasenia = sha2(concat(?,?,?),224)";
        Usuario user = null;
        try {
            this.abrirConexion();
            try (PreparedStatement st = this.conexion.prepareStatement(consulta)) {
                st.setString(1, nombre_usuario);
                st.setString(2, clave1);
                st.setString(3, contrasenia);
                st.setString(4, clave2);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        user = extraerUsuario(rs);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.cerrarConexion();
        }
        return user;
    }

    private Usuario extraerUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
        usuario.setContrasenia(rs.getString("contrasenia"));
        usuario.setMetodo(rs.getString("metodo"));
        usuario.setNombre_persona(rs.getString("nombre_persona"));
        usuario.setApellidos_persona(rs.getString("apellidos_persona"));
        usuario.setRol(rs.getString("rol"));
        return usuario;
    }

}
