package proyecto.ejb;

import java.util.List;
import javax.ejb.Local;
import proyecto.entidades.Usuario;

@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    // Añadimos método para buscar usuario por correo y contraseña
    Usuario findByEmailAndPassword(String correo, String contrasena);
}

