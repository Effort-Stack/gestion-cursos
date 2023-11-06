package proyecto.ejb;

import java.util.List;
import javax.ejb.Local;
import proyecto.entidades.Nota;

@Local
public interface NotaFacadeLocal {

    void create(Nota nota);

    void edit(Nota nota);

    void remove(Nota nota);

    Nota find(Object id);

    List<Nota> findAll();

    List<Nota> findRange(int[] range);

    int count();
    
}
