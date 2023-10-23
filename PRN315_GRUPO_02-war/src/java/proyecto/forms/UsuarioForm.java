package proyecto.forms;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import proyecto.ejb.UsuarioFacadeLocal;
import proyecto.entidades.Usuario;
import javax.annotation.PostConstruct;

@Named(value = "usuarioForm")
@SessionScoped
public class UsuarioForm implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    private String correo;
    private String contrasena;
    private Usuario usuarioActual;

    public UsuarioForm() {
    }

    @PostConstruct
    public void init() {
        usuarioActual = new Usuario(); 
        // Puedes agregar más lógica de inicialización si lo necesitas
    }

    public String iniciarSesion() {
        usuarioActual = usuarioFacade.findByEmailAndPassword(correo, contrasena);
        if (usuarioActual != null) {
            if (usuarioActual.getProfesor() != null) {
                return "frmProfesor?faces-redirect=true"; // redirigir a la página del profesor
            } else if (usuarioActual.getEstudiante() != null) {
                return "frmEstudiante?faces-redirect=true"; // redirigir a la página del estudiante
            }
        }
        // Si las credenciales no son válidas o hay algún otro problema, se queda en la misma página
        return null;
    }

    // Getter y Setter de correo y contrasena
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Getter y Setter para usuarioActual
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    // Método para cerrar sesión
    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "frmUsuario?faces-redirect=true"; // redirigir a la página de inicio de sesión
    }
}
