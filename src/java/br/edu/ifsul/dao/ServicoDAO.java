
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Servico;
import java.io.Serializable;

/**
 *
 * @author Bruno
 */
public class ServicoDAO<T> extends DAOGenerico<Servico> implements Serializable {
    
    public ServicoDAO () {
        super();
        super.setClassePersistente(Servico.class);
        super.setOrdem("id");
    }
    
}
