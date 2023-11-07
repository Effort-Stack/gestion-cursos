package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import proyecto.ejb.ProfesorFacadeLocal;
import proyecto.entidades.Profesor;

@Named(value = "profesorForm")
@SessionScoped
public class ProfesorForm implements Serializable {

    private Profesor profesor;
    private Profesor selectedProfesor; // Añadido para manejar la selección en la tabla

    @EJB
    private ProfesorFacadeLocal profesorFacade;

    @PostConstruct
    public void init() {
        profesor = new Profesor();
    }

    public ProfesorForm() {
        // Constructor vacío necesario para JPA
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
    public Profesor getSelectedProfesor() {
        return selectedProfesor;
    }

    public void setSelectedProfesor(Profesor selectedProfesor) {
        this.selectedProfesor = selectedProfesor;
    }

    public void guardarProfesor() {
        try {
            profesorFacade.create(profesor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Profesor agregado correctamente."));
            profesor = new Profesor(); // Reiniciar el objeto profesor para limpiar el formulario
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza de la excepción para depuración
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo agregar el profesor."));
        }
    }

    
    public void actualizarProfesor() {
        try {
            // Primero buscar el profesor por el carnet para obtener el id correcto
            Profesor profesorExistente = profesorFacade.findByCarnet(profesor.getCarnet());
            if (profesorExistente != null) {
                profesor.setId(profesorExistente.getId());
                profesorFacade.edit(profesor);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Profesor actualizado correctamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Profesor no encontrado."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el profesor."));
            e.printStackTrace(); // Para depuración
        }
    }

    public void eliminarProfesor() {
        try {
            // Similar a actualizar, primero encontrar por carnet para asegurarnos del id correcto
            Profesor profesorExistente = profesorFacade.findByCarnet(profesor.getCarnet());
            if (profesorExistente != null) {
                profesorFacade.remove(profesorExistente);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Profesor eliminado correctamente."));
                profesor = new Profesor(); // Reiniciar el objeto para limpiar el formulario
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Profesor no encontrado."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el profesor."));
            e.printStackTrace(); // Para depuración
        }
    }

    public List<Profesor> getListaProfesores() {
        return profesorFacade.findAll();
    }
}
