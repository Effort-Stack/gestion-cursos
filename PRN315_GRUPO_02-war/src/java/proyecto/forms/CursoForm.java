package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import proyecto.entidades.Curso;

@Named(value = "cursoForm")
@SessionScoped
public class CursoForm implements Serializable {

    private Curso curso;

    @PostConstruct
    public void init() {
        try {
            // Código de inicialización del formulario
            curso = new Curso();
        } catch (Exception e) {
            // Manejar la excepción adecuadamente
        }
    }

    public CursoForm() {
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
