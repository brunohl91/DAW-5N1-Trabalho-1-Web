
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Conta;
import java.io.Serializable;

/**
 *
 * @author Bruno
 */
public class ContaDAO<T> extends DAOGenerico<Conta> implements Serializable {
    
    public ContaDAO () {
        super();
        super.setClassePersistente(Conta.class);
        //super.setOrdem("id");
    }
    
}
