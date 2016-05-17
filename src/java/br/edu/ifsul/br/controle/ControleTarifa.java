/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.br.controle;

import br.edu.ifsul.dao.TarifaDAO;
import br.edu.ifsul.modelo.Tarifa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Bruno
 */
@ManagedBean
@SessionScoped
public class ControleTarifa implements Serializable {
    
    private TarifaDAO<Tarifa> dao;
    private Tarifa objeto;

    public ControleTarifa() {
        dao = new TarifaDAO<>();
    }
    
public String listar () {
        return "/privado/tarifa/listar?faces-redirect=true";
    }
    
    public String novo () {
        objeto = new Tarifa();
        return "formulario";
    }
    
    public String salvar () {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        }
        else {
            persistiu = dao.merge(objeto);
        }
        
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar";
        }
        else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }
    
    public String cancelar () {
        return "listar";
    }
    
    public String editar (Integer id) {
        try {
            objeto = dao.localizar(id);
            return "formulario";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
            return "listar";
        }
    }
    
    public void remover (Integer id) {
        try {
            objeto = dao.localizar(id);
            if (dao.remover(objeto)) {
                Util.mensagemInformacao(dao.getMensagem());
            }
            else {
                Util.mensagemErro(dao.getMensagem());
            }
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public TarifaDAO<Tarifa> getDao() {
        return dao;
    }

    public void setDao(TarifaDAO<Tarifa> dao) {
        this.dao = dao;
    }

    public Tarifa getObjeto() {
        return objeto;
    }

    public void setObjeto(Tarifa objeto) {
        this.objeto = objeto;
    }
    
}
