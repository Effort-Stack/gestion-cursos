package proyecto.ejb;

import java.util.List;
import javax.ejb.Local;
import proyecto.entidades.Profesor;


@Local
public interface ProfesorFacadeLocal {

    void create(Profesor profesor);

    void edit(Profesor profesor);

    void remove(Profesor profesor);

    Profesor find(Object id);

    List<Profesor> findAll();

    List<Profesor> findRange(int[] range);

    int count();
    
    List<Profesor> buscarTodosAlfabeticamente();
    
    Profesor findByCarnet(String carnet);
}
