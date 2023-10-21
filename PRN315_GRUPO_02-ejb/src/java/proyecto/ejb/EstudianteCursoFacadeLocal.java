/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.ejb;

import java.util.List;
import javax.ejb.Local;
import proyecto.entidades.EstudianteCurso;

/**
 *
 * @author johec
 */
@Local
public interface EstudianteCursoFacadeLocal {

    void create(EstudianteCurso estudianteCurso);

    void edit(EstudianteCurso estudianteCurso);

    void remove(EstudianteCurso estudianteCurso);

    EstudianteCurso find(Object id);

    List<EstudianteCurso> findAll();

    List<EstudianteCurso> findRange(int[] range);

    int count();
    
}
