package proyecto.ejb;

import java.util.List;
import javax.ejb.Local;
import proyecto.entidades.Estudiante;

@Local
public interface EstudianteFacadeLocal {

    void create(Estudiante estudiante);

    void edit(Estudiante estudiante);

    void remove(Estudiante estudiante);

    Estudiante find(Object id);

    List<Estudiante> findAll();

    List<Estudiante> findRange(int[] range);

    int count();
    
    List<Estudiante> buscarTodosAlfabeticamente();
    
    Estudiante findByCarnet(String carnet);
    
}
