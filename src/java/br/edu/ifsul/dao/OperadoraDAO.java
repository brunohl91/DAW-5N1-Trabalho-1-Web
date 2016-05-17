
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Operadora;
import java.io.Serializable;

/**
 *
 * @author Bruno
 */
public class OperadoraDAO<T> extends DAOGenerico<Operadora> implements Serializable {
    
    public OperadoraDAO () {
        super();
        super.setClassePersistente(Operadora.class);
        super.setOrdem("nome");
    }
    
}
