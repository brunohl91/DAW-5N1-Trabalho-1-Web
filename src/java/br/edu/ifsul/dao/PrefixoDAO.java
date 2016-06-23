
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Prefixo;
import java.io.Serializable;

/**
 *
 * @author Bruno
 */
public class PrefixoDAO<T> extends DAOGenerico<Prefixo> implements Serializable {
    
    public PrefixoDAO () {
        super();
        super.setClassePersistente(Prefixo.class);
        super.setOrdem("id");
    }
    
}
