package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import proyecto.entidades.Nota;

@Named(value = "notaForm")
@SessionScoped
public class NotaForm implements Serializable {

    private Nota nota;

    @PostConstruct
    public void init() {
        try {
            // Código de inicialización del formulario
            nota = new Nota();
        } catch (Exception e) {
            // Manejar la excepción adecuadamente
        }
    }

    public NotaForm() {
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
}
