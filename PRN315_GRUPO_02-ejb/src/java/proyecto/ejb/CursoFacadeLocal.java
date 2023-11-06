package proyecto.ejb;

import java.util.List;
import javax.ejb.Local;
import proyecto.entidades.Curso;

@Local
public interface CursoFacadeLocal {

    void create(Curso curso);

    void edit(Curso curso);

    void remove(Curso curso);

    Curso find(Object id);

    List<Curso> findAll();

    List<Curso> findRange(int[] range);

    int count();
    
}
