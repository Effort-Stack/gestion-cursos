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
            profesorFacade.edit(selectedProfesor); // Actualizar el profesor seleccionado
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Profesor actualizado correctamente."));
            selectedProfesor = null; // Reiniciar la selección
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el profesor."));
        }
    }

    public void eliminarProfesor() {
        try {
            profesorFacade.remove(selectedProfesor); // Eliminar el profesor seleccionado
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Profesor eliminado correctamente."));
            selectedProfesor = null; // Reiniciar la selección
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el profesor."));
        }
    }

    public List<Profesor> getListaProfesores() {
        return profesorFacade.findAll();
    }
}
