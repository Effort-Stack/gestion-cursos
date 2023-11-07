package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import proyecto.ejb.EstudianteFacadeLocal;
import proyecto.entidades.Estudiante;

@Named(value = "estudianteForm")
@SessionScoped
public class EstudianteForm implements Serializable {

    private Estudiante estudiante;
    private List<Estudiante> listaEstudiantes;
    
    @EJB
    private EstudianteFacadeLocal estudianteFacade;

    @PostConstruct
    public void init() {
        estudiante = new Estudiante();
        listaEstudiantes = estudianteFacade.findAll();
    }

    public EstudianteForm() {
        // Constructor vacío necesario para JPA
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    public List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void guardarEstudiante() {
        try {
            estudianteFacade.create(estudiante);
            listaEstudiantes = estudianteFacade.findAll(); // Actualizar la lista
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Estudiante agregado correctamente."));
            estudiante = new Estudiante(); // Reiniciar el objeto para limpiar el formulario
        } catch (ConstraintViolationException e) {
            for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
                String message = violation.getPropertyPath() + ": " + violation.getMessage();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", message));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo realizar la operación."));
            e.printStackTrace(); // Para depuración
        }
    }
    
    public void actualizarEstudiante() {
        try {
            // Primero buscar el estudiante por el carnet para obtener el id correcto
            Estudiante estudianteExistente = estudianteFacade.findByCarnet(estudiante.getCarnet());
            if (estudianteExistente != null) {
                estudiante.setId(estudianteExistente.getId());
                estudianteFacade.edit(estudiante);
                listaEstudiantes = estudianteFacade.findAll(); // Actualizar la lista
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Estudiante actualizado correctamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Estudiante no encontrado."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el estudiante."));
            e.printStackTrace(); // Para depuración
        }
    }

    public void eliminarEstudiante() {
        try {
            // Similar a actualizar, primero encontrar por carnet para asegurarnos del id correcto
            Estudiante estudianteExistente = estudianteFacade.findByCarnet(estudiante.getCarnet());
            if (estudianteExistente != null) {
                estudianteFacade.remove(estudianteExistente);
                listaEstudiantes = estudianteFacade.findAll(); // Actualizar la lista
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Estudiante eliminado correctamente."));
                estudiante = new Estudiante(); // Reiniciar el objeto para limpiar el formulario
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Estudiante no encontrado."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el estudiante."));
            e.printStackTrace(); // Para depuración
        }
    }
}

