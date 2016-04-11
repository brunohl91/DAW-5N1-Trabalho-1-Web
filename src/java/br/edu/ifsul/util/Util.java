
package br.edu.ifsul.util;

/**
 *
 * @author Bruno
 */
public class Util {
    
    public static String getMensagemErro (Exception e) {
        while (e.getCause() != null) {
            // último níve de encapsulamento tem exceção nula
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        if (retorno.contains(("foreign key"))) {
            retorno = "Registro não pode ser excluído por possuir referência em outros objetos!";
        }
        return retorno;
    }
    
}
