
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Ramal;
import java.io.Serializable;

/**
 *
 * @author Bruno
 */
public class RamalDAO<T> extends DAOGenerico<Ramal> implements Serializable {
    
    public RamalDAO () {
        super();
        super.setClassePersistente(Ramal.class);
        //super.setMaximoObjetos(1);
        //super.setOrdem("id");
    }
    
}
