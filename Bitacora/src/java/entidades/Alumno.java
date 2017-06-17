package entidades;

/**
 *
 * @author sanchez
 */
public class Alumno {
    private String matricula;
    private String matricula_anterior;
    private String nombre_persona;
    private String apellidos_persona;
    private String carrera;
    private String semestre;
    private String grupo;
    
    public Alumno(){}
    
    public Alumno(String matricula, String matricula_anteriror, String nombre_persona, String apellidos_persona, String carrera, String semestre, String grupo){
        this.matricula = matricula;
        this.matricula_anterior = matricula_anteriror;
        this.nombre_persona = nombre_persona;
        this.apellidos_persona = apellidos_persona;
        this.carrera = carrera;
        this.semestre = semestre;
        this.grupo = grupo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula_anterior() {
        return matricula_anterior;
    }

    public void setMatricula_anterior(String matricula_anterior) {
        this.matricula_anterior = matricula_anterior;
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

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
}
