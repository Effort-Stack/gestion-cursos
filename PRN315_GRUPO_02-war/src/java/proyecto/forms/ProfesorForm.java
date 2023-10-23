/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.forms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import proyecto.ejb.ProfesorFacadeLocal;
import proyecto.entidades.Profesor;
import proyecto.entidades.Usuario;

/**
 *
 * @author johec
 */
@Named(value = "profesorForm")
@SessionScoped
public class ProfesorForm implements Serializable {

    @EJB
    private ProfesorFacadeLocal profesorFacade;

    private Profesor profesor;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        profesor = new Profesor();
        usuario = new Usuario();
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String registrar() {
        usuario.setId(profesor.getUsuarioId()); 

        profesor.setUsuario(usuario);
        usuario.setProfesor(profesor);

        profesorFacade.create(profesor);

        return "success"; // Puedes redirigir a una página de éxito o donde quieras después del registro
    }
}