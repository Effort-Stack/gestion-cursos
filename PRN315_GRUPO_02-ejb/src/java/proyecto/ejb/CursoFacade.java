package proyecto.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import proyecto.entidades.Curso;

@Stateless
public class CursoFacade extends AbstractFacade<Curso> implements CursoFacadeLocal {

    @PersistenceContext(unitName = "PRN315_GRUPO_02-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoFacade() {
        super(Curso.class);
    }

    @Override
    public Curso findById(Integer id) throws EntityNotFoundException {
        Curso curso = em.find(Curso.class, id);
        if (curso == null) {
            throw new EntityNotFoundException("Curso con ID: " + id + " no encontrado.");
        }
        return curso;
    }

}

