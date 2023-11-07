package proyecto.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
    
    @Override
    public Profesor findByCarnet(String carnet) throws NoResultException {
        TypedQuery<Profesor> query = em.createNamedQuery("Profesor.findByCarnet", Profesor.class);
        query.setParameter("carnet", carnet);
        try {
            return query.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            throw new NoResultException("No se encontró un profesor con el carnet: " + carnet);
        }
    }
    
}
