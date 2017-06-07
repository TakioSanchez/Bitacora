package entidades;

/**
 *
 * @author sanchez
 */
public class Persona {
    private String id_persona;
    private String nombre_persona;
    private String apellidos_persona;
    private String rol;
    
    public Persona(){
    }
    
    public Persona(String id_persona,String nombre_persona, String apellidos_persona, String rol){
        this.id_persona = id_persona;
        this.nombre_persona = nombre_persona;
        this.apellidos_persona = apellidos_persona;
        this.rol = rol;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
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
