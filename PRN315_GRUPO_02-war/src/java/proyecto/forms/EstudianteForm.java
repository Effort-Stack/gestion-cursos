package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import proyecto.entidades.Estudiante;

@Named(value = "estudianteForm")
@SessionScoped
public class EstudianteForm implements Serializable {

    private Estudiante estudiante;

    @PostConstruct
    public void init() {
        try {
            // Código de inicialización del formulario
            estudiante = new Estudiante();
        } catch (Exception e) {
            // Manejar la excepción adecuadamente
        }
    }

    public EstudianteForm() {
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}
