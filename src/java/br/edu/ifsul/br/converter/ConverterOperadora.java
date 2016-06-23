/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Operadora;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bruno
 */
@FacesConverter(value = "converterOperadora")
public class ConverterOperadora implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")) {
            return null;
        }
        else {
            return EntityManagerUtil.getEntityManager().find(Operadora.class, Integer.parseInt(string));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        }
        else {
            Operadora obj = (Operadora) o;
            return obj.getId().toString();
        }
    }
    
}
