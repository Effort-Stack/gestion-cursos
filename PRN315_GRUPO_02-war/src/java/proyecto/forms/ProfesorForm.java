package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import proyecto.entidades.Profesor;

@Named(value = "profesorForm")
@SessionScoped
public class ProfesorForm implements Serializable {

    private Profesor profesor;

    @PostConstruct
    public void init() {
        try {
            // Código de inicialización del formulario
            profesor = new Profesor();
        } catch (Exception e) {
            // Manejar la excepción adecuadamente
        }
    }

    public ProfesorForm() {
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
