package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import proyecto.ejb.CursoFacadeLocal;
import proyecto.entidades.Curso;

@Named(value = "cursoForm")
@SessionScoped
public class CursoForm implements Serializable {

    private Curso curso;

    // Inyectar la fachada local de Cursos usando EJB
    @EJB
    private CursoFacadeLocal cursoFacade;

    @PostConstruct
    public void init() {
        curso = new Curso();
    }

    public CursoForm() {
        // Constructor vacío necesario para JPA
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // Método para guardar un curso en la base de datos
    public void guardarCurso() {
        try {
            cursoFacade.create(curso);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Curso agregado correctamente."));
            curso = new Curso(); // Reiniciar el objeto para limpiar el formulario
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo agregar el curso."));
        }
    }

    // Método para actualizar un curso existente en la base de datos
    public void actualizarCurso() {
        try {
            cursoFacade.edit(curso);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Curso actualizado correctamente."));
            curso = new Curso(); // Opcionalmente reiniciar el objeto
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el curso."));
        }
    }

    // Método para eliminar un curso de la base de datos
    public void eliminarCurso() {
        try {
            cursoFacade.remove(curso);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Curso eliminado correctamente."));
            curso = new Curso(); // Reiniciar el objeto para limpiar el formulario
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el curso."));
        }
    }

    // Método para obtener una lista de todos los cursos
    public List<Curso> getListaCursos() {
        return cursoFacade.findAll();
    }
}
