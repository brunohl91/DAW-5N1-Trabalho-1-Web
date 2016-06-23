
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Senha;
import java.io.Serializable;

/**
 *
 * @author Bruno
 */
public class SenhaDAO<T> extends DAOGenerico<Senha> implements Serializable {
    
    public SenhaDAO () {
        super();
        super.setClassePersistente(Senha.class);
        super.setOrdem("id");
    }
    
}
