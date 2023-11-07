package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityNotFoundException;
import proyecto.ejb.CursoFacadeLocal;
import proyecto.entidades.Curso;

@Named(value = "cursoForm")
@SessionScoped
public class CursoForm implements Serializable {

    private Curso curso;
    private Integer selectedCursoId;
    private List<Curso> listaCursos;
    
    // Inyectar la fachada local de Cursos usando EJB
    @EJB
    private CursoFacadeLocal cursoFacade;

    @PostConstruct
    public void init() {
        curso = new Curso();
        actualizarListaCursos();
    }

    public CursoForm() {
        // Constructor vacío necesario para JPA
    }

    private void actualizarListaCursos() {
        listaCursos = cursoFacade.findAll();
    }

    public List<Curso> getListaCursos() {
        return listaCursos;
    }
    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public Integer getSelectedCursoId() {
    return selectedCursoId;
    }

    public void setSelectedCursoId(Integer selectedCursoId) {
        this.selectedCursoId = selectedCursoId;
    }

    // Método para guardar un curso en la base de datos
    public void guardarCurso() {
        try {
            cursoFacade.create(curso);
            actualizarListaCursos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Curso agregado correctamente."));
            curso = new Curso(); // Reiniciar el objeto para limpiar el formulario
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo agregar el curso."));
        }
    }

    public void actualizarCurso() {
        try {
            if(selectedCursoId != null) {
                Curso cursoExistente = cursoFacade.find(selectedCursoId);
                if(cursoExistente != null) {
                    cursoExistente.setNombre(curso.getNombre());
                    cursoExistente.setDescripcion(curso.getDescripcion());
                    cursoExistente.setDuracion(curso.getDuracion());
                    cursoFacade.edit(cursoExistente);
                    actualizarListaCursos();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Curso actualizado correctamente."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Curso no encontrado."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Debe seleccionar un curso."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el curso."));
            e.printStackTrace();
        }
    }

    public void eliminarCurso() {
        try {
            // Buscar el curso por ID para obtener la entidad correcta
            Curso cursoExistente = cursoFacade.find(selectedCursoId);
            if (cursoExistente != null) {
                cursoFacade.remove(cursoExistente);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Curso eliminado correctamente."));
                // Actualizar la lista de cursos para reflejar la eliminación
                listaCursos = cursoFacade.findAll();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Curso no encontrado."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el curso."));
            e.printStackTrace(); // Para depuración
        }
    }

}
