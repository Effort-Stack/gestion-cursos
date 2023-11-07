package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import proyecto.ejb.NotaFacadeLocal;
import proyecto.ejb.CursoFacadeLocal;
import proyecto.ejb.EstudianteFacadeLocal;
import proyecto.entidades.Nota;
import proyecto.entidades.Curso;
import proyecto.entidades.Estudiante;

@Named(value = "notaForm")
@SessionScoped
public class NotaForm implements Serializable {

    private Nota nota;
    private List<Nota> listaNotas;
    private List<Curso> listaCursos;
    private List<Estudiante> listaEstudiantes;
    private Integer selectedCursoId;
    private Integer selectedEstudianteId;

    @EJB
    private NotaFacadeLocal notaFacade;
    @EJB
    private CursoFacadeLocal cursoFacade;
    @EJB
    private EstudianteFacadeLocal estudianteFacade;

    @PostConstruct
    public void init() {
        nota = new Nota();
        listaNotas = notaFacade.findAll(); // Cargar la lista de notas al iniciar
        listaCursos = cursoFacade.findAll(); // Cargar la lista de cursos al iniciar
        listaEstudiantes = estudianteFacade.findAll(); // Cargar la lista de estudiantes al iniciar
    }
    
    public Integer getSelectedCursoId() {
    return selectedCursoId;
    }

    public void setSelectedCursoId(Integer selectedCursoId) {
        this.selectedCursoId = selectedCursoId;
    }

    public Integer getSelectedEstudianteId() {
        return selectedEstudianteId;
    }

    public void setSelectedEstudianteId(Integer selectedEstudianteId) {
        this.selectedEstudianteId = selectedEstudianteId;
    }

    public NotaForm() {
        // Constructor vacío necesario para JPA
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public List<Nota> getListaNotas() {
        return listaNotas;
    }

    public List<Curso> getListaCursos() {
        return listaCursos;
    }

    public List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    // Métodos para operaciones CRUD
    public void guardarNota() {
        try {
            Curso curso = cursoFacade.find(selectedCursoId);
            Estudiante estudiante = estudianteFacade.find(selectedEstudianteId);
            nota.setCursoId(curso);
            nota.setEstudianteId(estudiante);
            nota.calcularNotaFinal(); // Calcular la nota final antes de guardar
            notaFacade.create(nota);
            listaNotas = notaFacade.findAll(); // Actualizar la lista
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Nota agregada correctamente."));
            nota = new Nota(); // Reiniciar el objeto nota para limpiar el formulario
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo agregar la nota."));
            e.printStackTrace(); // Agregar esto para imprimir la excepción en la consola y facilitar la depuración
        }
    }

    public void actualizarNota() {
        try {
            Curso curso = cursoFacade.find(selectedCursoId);
            Estudiante estudiante = estudianteFacade.find(selectedEstudianteId);
            nota.setCursoId(curso);
            nota.setEstudianteId(estudiante);
            nota.calcularNotaFinal(); // Calcular la nota final antes de actualizar
            notaFacade.edit(nota);
            listaNotas = notaFacade.findAll(); // Actualizar la lista
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Nota actualizada correctamente."));
            nota = null; // Reiniciar la selección
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar la nota."));
            e.printStackTrace(); // Agregar esto para imprimir la excepción en la consola y facilitar la depuración
        }
    }

    public void eliminarNota() {
        try {
            notaFacade.remove(nota);
            listaNotas = notaFacade.findAll(); // Actualizar la lista
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Nota eliminada correctamente."));
            nota = null; // Reiniciar la selección
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la nota."));
        }
    }
}
