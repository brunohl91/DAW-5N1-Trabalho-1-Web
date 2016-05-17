
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Tarifa;
import java.io.Serializable;

/**
 *
 * @author Bruno
 */
public class TarifaDAO<T> extends DAOGenerico<Tarifa> implements Serializable {
    
    public TarifaDAO () {
        super();
        super.setClassePersistente(Tarifa.class);
        super.setOrdem("id");
    }
    
}
