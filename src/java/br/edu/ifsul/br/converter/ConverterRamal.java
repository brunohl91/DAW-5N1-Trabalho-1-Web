package br.edu.ifsul.br.converter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Ramal;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bruno
 */
@FacesConverter(value = "converterRamal")
public class ConverterRamal implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")) {
            return null;
        }
        else {
            return EntityManagerUtil.getEntityManager().find(Ramal.class, Integer.parseInt(string));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        }
        else {
            Ramal obj = (Ramal) o;
            return obj.getId().toString();
        }
    }
    
}
