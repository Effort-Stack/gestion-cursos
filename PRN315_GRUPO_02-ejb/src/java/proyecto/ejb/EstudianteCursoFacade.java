/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import proyecto.entidades.EstudianteCurso;

/**
 *
 * @author johec
 */
@Stateless
public class EstudianteCursoFacade extends AbstractFacade<EstudianteCurso> implements EstudianteCursoFacadeLocal {

    @PersistenceContext(unitName = "PRN315_GRUPO_02-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteCursoFacade() {
        super(EstudianteCurso.class);
    }
    
}
