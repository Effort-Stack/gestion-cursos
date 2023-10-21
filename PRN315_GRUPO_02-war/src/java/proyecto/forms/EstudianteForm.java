package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import proyecto.entidades.Estudiante;

@Named(value = "estudianteForm")
@SessionScoped
public class EstudianteForm implements Serializable {

    private Estudiante estudiante;

    /**
     * Creates a new instance of EstudianteForm
     */
    public EstudianteForm() { }

    @PostConstruct
    public void init() {
        try {
            // Código de inicialización de formulario
        } catch (Exception e) {
            // Aquí puedes manejar la excepción si es necesario
        }
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}
