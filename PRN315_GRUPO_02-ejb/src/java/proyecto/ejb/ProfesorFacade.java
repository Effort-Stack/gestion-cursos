package proyecto.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import proyecto.entidades.Profesor;

@Stateless
public class ProfesorFacade extends AbstractFacade<Profesor> implements ProfesorFacadeLocal {

    @PersistenceContext(unitName = "PRN315_GRUPO_02-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesorFacade() {
        super(Profesor.class);
    }

    @Override
    public List<Profesor> buscarTodosAlfabeticamente() {
         try {
            em.getEntityManagerFactory().getCache().evictAll();
            String consulta = "SELECT * FROM [Profesor] order by nombre";
            Query query = em.createNativeQuery(consulta, Profesor.class);
            List<Profesor> listaResultado = query.getResultList();
            return listaResultado;
        } catch (Exception e) {
            return null;
        }
    }
    
}
