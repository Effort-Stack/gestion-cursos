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
    public EstudianteForm() {
        estudiante = new Estudiante();  // Se inicializa el estudiante al crear la instancia de este bean
    }

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

    // Método para registrar el estudiante
    public void registrar() {
        try {
            // Aquí, en lugar de imprimir, normalmente guardarías el estudiante en una base de datos
            System.out.println("Estudiante registrado: " + estudiante.getUsuario().getNombre());
        } catch (Exception e) {
            // Aquí puedes manejar la excepción si es necesario
            System.out.println("Error al registrar estudiante: " + e.getMessage());
        }
    }
}
