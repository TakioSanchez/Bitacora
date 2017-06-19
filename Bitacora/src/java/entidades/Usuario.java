package entidades;

/**
 *
 * @author lmarcoss
 */
public class Usuario {

    private String nombre_usuario;
    private String contrasenia;
    private String metodo;
    private String nombre_persona;
    private String apellidos_persona;
    private String rol;

    public Usuario() {
    }

    public Usuario(String nombre_usuario, String contrasenia, String metodo, String nombre_persona, String apellidos_persona, String rol) {
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
        this.metodo = metodo;
        this.nombre_persona = nombre_persona;
        this.apellidos_persona = apellidos_persona;
        this.rol = rol;
    }

    public Usuario(String nombre_usuario, String contrasenia, String metodo) {
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
        this.metodo = metodo;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getApellidos_persona() {
        return apellidos_persona;
    }

    public void setApellidos_persona(String apellidos_persona) {
        this.apellidos_persona = apellidos_persona;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
