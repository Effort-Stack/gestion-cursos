package proyecto.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
            // Corrige el nombre de la tabla a "Estudiante"
            String consulta = "SELECT * FROM [Estudiante] order by nombre";
            Query query = em.createNativeQuery(consulta, Estudiante.class);
            List<Estudiante> listaResultado = query.getResultList();
            return listaResultado;
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Estudiante findByCarnet(String carnet) throws NoResultException {
        TypedQuery<Estudiante> query = em.createNamedQuery("Estudiante.findByCarnet", Estudiante.class);
        query.setParameter("carnet", carnet);
        try {
            return query.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            throw new NoResultException("No se encontró un estudiante con el carnet: " + carnet);
        }
    }


    
}
