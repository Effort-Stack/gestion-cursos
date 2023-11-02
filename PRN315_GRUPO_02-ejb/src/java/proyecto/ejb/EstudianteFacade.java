package proyecto.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import proyecto.entidades.Estudiante;

@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> implements EstudianteFacadeLocal {

    @PersistenceContext(unitName = "PRN315_GRUPO_02-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }

    @Override
    public List<Estudiante> buscarTodosAlfabeticamente() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            String consulta = "SELECT * FROM [Profesor] order by nombre";
            Query query = em.createNativeQuery(consulta, Estudiante.class);
            List<Estudiante> listaResultado = query.getResultList();
            return listaResultado;
        } catch (Exception e) {
            return null;
        }
    }
    
}
